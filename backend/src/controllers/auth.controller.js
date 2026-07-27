import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';
import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';

export const login = async (req, res, next) => {
  try {
    const { email, password } = req.body;
    const user = await prisma.user.findUnique({ where: { email } });

    if (!user || !(await bcrypt.compare(password, user.password))) {
      throw new AppError('INVALID_CREDENTIALS', 401, 'Invalid email or password');
    }
    if (!user.isActive) {
      throw new AppError('ACCOUNT_INACTIVE', 403, 'Your account has been deactivated');
    }

    const token = jwt.sign(
      { id: user.id, email: user.email, role: user.role, cityId: user.cityId },
      process.env.JWT_SECRET,
      { expiresIn: process.env.JWT_EXPIRES_IN || '30d' }
    );

    res.json({
      success: true,
      data: {
        token,
        user: {
          id: user.id, name: user.name, email: user.email, role: user.role,
          cityId: user.cityId, areaId: user.areaId,
          leaveBalanceSick: user.leaveBalanceSick,
          leaveBalanceCasual: user.leaveBalanceCasual,
        },
      },
    });
  } catch (err) { next(err); }
};

export const changePassword = async (req, res, next) => {
  try {
    const { currentPassword, newPassword } = req.body;
    const user = await prisma.user.findUnique({ where: { id: req.user.id } });
    if (!await bcrypt.compare(currentPassword, user.password)) {
      throw new AppError('WRONG_PASSWORD', 400, 'Current password is incorrect');
    }
    const hashed = await bcrypt.hash(newPassword, 12);
    await prisma.user.update({ where: { id: req.user.id }, data: { password: hashed } });
    res.json({ success: true, data: { message: 'Password updated' } });
  } catch (err) { next(err); }
};

export const forceReset = async (req, res, next) => {
  try {
    const { userId, newPassword } = req.body;
    const hashed = await bcrypt.hash(newPassword, 12);
    await prisma.user.update({ where: { id: userId }, data: { password: hashed } });
    await prisma.auditLog.create({
      data: {
        actorId: req.user.id, action: 'force_password_reset',
        targetType: 'user', targetId: userId,
        ipAddress: req.ip,
      },
    });
    res.json({ success: true, data: { message: 'Password reset successfully' } });
  } catch (err) { next(err); }
};
