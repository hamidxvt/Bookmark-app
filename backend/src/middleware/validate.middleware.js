import { AppError } from './errorHandler.js';

export const validate = (schema) => (req, res, next) => {
  const result = schema.safeParse(req.body);
  if (!result.success) {
    const message = result.error.errors.map((e) => `${e.path.join('.')}: ${e.message}`).join(', ');
    return next(new AppError('VALIDATION_ERROR', 400, message));
  }
  req.body = result.data;
  next();
};
