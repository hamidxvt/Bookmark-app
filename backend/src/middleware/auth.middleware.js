import jwt from 'jsonwebtoken';
import { AppError } from './errorHandler.js';

export const auth = (req, res, next) => {
  const header = req.headers.authorization;
  if (!header?.startsWith('Bearer ')) {
    return next(new AppError('UNAUTHORIZED', 401, 'Missing or invalid authorization header'));
  }
  const token = header.split(' ')[1];
  try {
    const payload = jwt.verify(token, process.env.JWT_SECRET);
    req.user = payload;
    next();
  } catch {
    next(new AppError('TOKEN_INVALID', 401, 'Token is invalid or expired'));
  }
};
