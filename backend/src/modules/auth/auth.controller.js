import { AuthService } from './auth.service.js';

const authService = new AuthService();

export const login = async (req, res, next) => {
  try {
    const { email, password } = req.body;
    const data = await authService.login(email, password);
    res.json({ success: true, data });
  } catch (err) { next(err); }
};

export const changePassword = async (req, res, next) => {
  try {
    const { currentPassword, newPassword } = req.body;
    await authService.changePassword(req.user.id, currentPassword, newPassword);
    res.json({ success: true, data: { message: 'Password updated' } });
  } catch (err) { next(err); }
};

export const forceReset = async (req, res, next) => {
  try {
    const { userId, newPassword } = req.body;
    await authService.forceReset(req.user.id, userId, newPassword, req.ip);
    res.json({ success: true, data: { message: 'Password reset successfully' } });
  } catch (err) { next(err); }
};
