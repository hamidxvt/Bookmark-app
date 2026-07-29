import jwt from 'jsonwebtoken';
import { AppError } from './errorHandler.js';

const JWT_SECRET = process.env.JWT_SECRET;

export const authMiddleware = (req, res, next) => {
  try {
    const auth = req.headers.authorization ?? '';
    if (!auth.startsWith('Bearer ')) {
      throw new AppError('UNAUTHORIZED', 401, 'Authentication token required');
    }

    const token = auth.slice(7);
    const payload = jwt.verify(token, JWT_SECRET);
    req.user = payload; // { id, email, role }
    next();
  } catch (err) {
    if (err.name === 'JsonWebTokenError' || err.name === 'TokenExpiredError') {
      return next(new AppError('INVALID_TOKEN', 401, 'Invalid or expired token'));
    }
    next(err);
  }
};
