import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';
import prisma from '../../config/database.js';
import { AppError } from '../../middleware/errorHandler.js';

/**
 * AuthService — all auth business logic lives here.
 * Controllers only validate inputs; services do the work.
 */
export class AuthService {
  async login(email, password) {
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
      { expiresIn: process.env.JWT_EXPIRES_IN || '30d' },
    );

    return {
      token,
      user: {
        id: user.id,
        name: user.name,
        email: user.email,
        role: user.role,
        cityId: user.cityId,
        areaId: user.areaId,
        leaveBalanceSick: user.leaveBalanceSick,
        leaveBalanceCasual: user.leaveBalanceCasual,
      },
    };
  }

  async changePassword(userId, currentPassword, newPassword) {
    const user = await prisma.user.findUnique({ where: { id: userId } });
    if (!(await bcrypt.compare(currentPassword, user.password))) {
      throw new AppError('WRONG_PASSWORD', 400, 'Current password is incorrect');
    }
    const hashed = await bcrypt.hash(newPassword, 12);
    await prisma.user.update({ where: { id: userId }, data: { password: hashed } });
  }

  async forceReset(actorId, userId, newPassword, ipAddress) {
    const hashed = await bcrypt.hash(newPassword, 12);
    await prisma.user.update({ where: { id: userId }, data: { password: hashed } });
    await prisma.auditLog.create({
      data: {
        actorId,
        action: 'force_password_reset',
        targetType: 'user',
        targetId: userId,
        ipAddress,
      },
    });
  }
}
