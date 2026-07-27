import 'dotenv/config';
import app from './app.js';
import { startSchedulers } from './schedulers/index.js';
import logger from './utils/logger.js';

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
  logger.info(`Bookmark SFA API running on port ${PORT} [${process.env.NODE_ENV}]`);
  startSchedulers();
});
