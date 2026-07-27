# Bookmark SFA — Node.js Backend

## Setup

```bash
cp .env.example .env
# Edit .env with your DB credentials and JWT secret

npm install
npx prisma migrate dev --name init
npx prisma db seed
npm run dev
```

API runs on: `http://localhost:3000`

## Stack
- **Node.js 20 LTS** + Express.js
- **Prisma ORM** → MySQL 8
- **JWT** (jsonwebtoken) for auth
- **BullMQ** + Redis for async jobs
- **node-cron** for scheduled tasks
- **Zod** for request validation
- **Winston** for structured logging

## Endpoints
See `ARCHITECTURE.md` for full API specification.

## Schedulers
| Time | Task |
|------|------|
| 12:00 AM | Route Planning Engine |
| 11:00 PM | Attendance Engine |
| 9:00 AM | Sample Recovery Reminders |
| Month-end 11:30 PM | Payroll Engine |
