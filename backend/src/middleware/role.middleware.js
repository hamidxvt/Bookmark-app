import { AppError } from './errorHandler.js';

export const requireRole = (roles) => (req, res, next) => {
  if (!roles.includes(req.user?.role)) {
    return next(new AppError('FORBIDDEN', 403, 'You do not have permission to perform this action'));
  }
  next();
};
