import 'dotenv/config';
import { createServer } from 'http';
import { Server as SocketIOServer } from 'socket.io';
import app from './app.js';
import { startSchedulers } from './schedulers/index.js';
import logger from './utils/logger.js';

const PORT = process.env.PORT || 3001;
const ALLOWED = (process.env.ALLOWED_ORIGINS || '*').split(',').map(s => s.trim());

// Create HTTP server so Socket.io and Express share the same port
const httpServer = createServer(app);

// Socket.io — real-time GPS for admin dashboard
const io = new SocketIOServer(httpServer, {
  cors: {
    origin: ALLOWED,
    methods: ['GET', 'POST'],
    credentials: true,
  },
  transports: ['websocket', 'polling'],
});

// Attach io to app so controllers can emit events
app.set('io', io);

io.on('connection', (socket) => {
  logger.info(`[socket] Admin connected: ${socket.id}`);

  // Admin joins a room to watch a specific city or all bookers
  socket.on('watch:city', (cityId) => {
    socket.join(`city:${cityId}`);
    logger.info(`[socket] ${socket.id} watching city:${cityId}`);
  });

  socket.on('watch:all', () => {
    socket.join('all');
    logger.info(`[socket] ${socket.id} watching all bookers`);
  });

  socket.on('disconnect', () => {
    logger.info(`[socket] Disconnected: ${socket.id}`);
  });
});

httpServer.listen(PORT, '0.0.0.0', () => {
  logger.info(`Bookmark SFA API running on port ${PORT} [${process.env.NODE_ENV}]`);
  logger.info(`Socket.io ready — admin connects to ws://[host]:${PORT}`);
  startSchedulers();
});
