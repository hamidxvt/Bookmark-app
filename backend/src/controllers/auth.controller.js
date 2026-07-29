import prisma from '../config/database.js';
import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';
import { AppError } from '../middleware/errorHandler.js';

const JWT_SECRET = process.env.JWT_SECRET;
const TOKEN_EXPIRY = '30d';

// POST /api/v1/auth/login
export const login = async (req, res, next) => {
  try {
    const { email, password } = req.body;
    if (!email || !password) throw new AppError('MISSING_FIELDS', 400, 'Email and password are required');

    const booker = await prisma.booker.findUnique({
      where: { email },
      select: {
        id: true, name: true, email: true, phone: true,
        password: true, jobStatus: true, adminApproved: true,
        cityId: true, regionId: true, profilePhoto: true,
        visitTargets: true, ratesPerVisit: true, basicSalary: true,
        leaveBalanceSick: true, leaveBalanceCasual: true,
        gpsStatus: true, city: { select: { name: true } },
      },
    });

    if (!booker) throw new AppError('INVALID_CREDENTIALS', 401, 'Invalid email or password');
    if (booker.adminApproved !== 'APPROVED') throw new AppError('NOT_APPROVED', 403, 'Account pending admin approval');
    if (booker.jobStatus !== 'ACTIVE') throw new AppError('INACTIVE', 403, 'Account is not active. Contact your admin.');

    const valid = await bcrypt.compare(password, booker.password);
    if (!valid) throw new AppError('INVALID_CREDENTIALS', 401, 'Invalid email or password');

    const token = jwt.sign(
      { id: booker.id, email: booker.email, role: 'sales_officer' },
      JWT_SECRET,
      { expiresIn: TOKEN_EXPIRY }
    );

    const { password: _, ...safeBooker } = booker;

    res.json({
      success: true,
      data: {
        token,
        user: { ...safeBooker, role: 'sales_officer' },
      },
    });
  } catch (err) { next(err); }
};

// POST /api/v1/auth/change-password
export const changePassword = async (req, res, next) => {
  try {
    const { currentPassword, newPassword } = req.body;
    const bookerId = req.user.id;

    if (!currentPassword || !newPassword) {
      throw new AppError('MISSING_FIELDS', 400, 'Current and new passwords are required');
    }
    if (newPassword.length < 8) {
      throw new AppError('WEAK_PASSWORD', 400, 'Password must be at least 8 characters');
    }

    const booker = await prisma.booker.findUnique({ where: { id: bookerId }, select: { password: true } });
    const valid = await bcrypt.compare(currentPassword, booker.password);
    if (!valid) throw new AppError('WRONG_PASSWORD', 401, 'Current password is incorrect');

    const hashed = await bcrypt.hash(newPassword, 12);
    await prisma.booker.update({ where: { id: bookerId }, data: { password: hashed } });

    res.json({ success: true, data: { message: 'Password changed successfully' } });
  } catch (err) { next(err); }
};

// GET /api/v1/auth/me — current user profile
export const getMe = async (req, res, next) => {
  try {
    const booker = await prisma.booker.findUnique({
      where: { id: req.user.id },
      select: {
        id: true, name: true, email: true, phone: true, profilePhoto: true,
        cityId: true, regionId: true, visitTargets: true, ratesPerVisit: true,
        basicSalary: true, leaveBalanceSick: true, leaveBalanceCasual: true,
        gpsStatus: true, lastSeenAt: true, annualSampleLimit: true, sampleUsed: true,
        city: { select: { name: true } },
        region: { select: { name: true } },
      },
    });

    if (!booker) throw new AppError('NOT_FOUND', 404, 'Booker not found');

    res.json({ success: true, data: { ...booker, role: 'sales_officer' } });
  } catch (err) { next(err); }
};
