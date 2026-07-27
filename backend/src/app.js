import express from 'express';
import helmet from 'helmet';
import cors from 'cors';
import compression from 'compression';
import morgan from 'morgan';
import rateLimit from 'express-rate-limit';

import router from './routes/index.js';
import { errorHandler } from './middleware/errorHandler.js';

const app = express();

// Security
app.use(helmet());
app.use(cors({ origin: process.env.ALLOWED_ORIGINS?.split(',') || '*' }));
app.use(rateLimit({ windowMs: 15 * 60 * 1000, max: 500, standardHeaders: true }));

// Body parsing
app.use(express.json({ limit: '2mb' }));
app.use(compression());

// Logging
app.use(morgan('combined'));

// Routes
app.use('/api/v1', router);

// Health check
app.get('/health', (_, res) => res.json({ status: 'ok', ts: new Date() }));

// Error handler (must be last)
app.use(errorHandler);

export default app;
