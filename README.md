# Bookmark SFA — Field Force Automation

Production-ready Sales Force Automation system for Bookmark publishing.

## Stack

| Layer | Technology |
|-------|-----------|
| Mobile | Flutter 3.x (Dart), Riverpod, Drift, Dio, WorkManager |
| Backend | Node.js 20, Express.js, Prisma ORM, MySQL 8, BullMQ, Redis |
| Admin Panel | React 19, Vite, Tailwind CSS, Google Maps JS API |

## Quick Start

### 1. Backend
```bash
cd backend
cp .env.example .env     # Set DATABASE_URL and JWT_SECRET
npm install
npx prisma migrate dev --name init
npx prisma db seed
npm run dev              # http://localhost:3000
```

### 2. Flutter App
```bash
cd mobile
flutter pub get
flutter run              # Select your emulator or device
```

### 3. Admin Panel
```bash
cd admin
cp .env.example .env.local
npm install
npm run dev              # http://localhost:5173
```

## Architecture
See [ARCHITECTURE.md](./ARCHITECTURE.md) for full system documentation, database schema, API spec, and scheduler logic.

## Project Structure
```
bookmark_field_force_manager/
├── mobile/          Flutter app (Dart)
├── backend/         Node.js REST API
├── admin/           React admin panel
└── ARCHITECTURE.md  Single source of truth
```
