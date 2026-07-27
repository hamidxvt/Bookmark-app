# ARCHITECTURE.md
## Bookmark Sales Force Automation System

Last Updated: July 27, 2026 | Status: Production-Ready | Version: 2.0 | Stack: Flutter + Node.js

---

## 1. System Overview & Tech Stack

### Technology Decisions

| Layer | Technology | Rationale |
|-------|-----------|-----------|
| **Mobile** | Flutter 3.x (Dart), Riverpod, Dio, Drift (SQLite), WorkManager Plugin, Google Maps Flutter | Single codebase, smooth 60/120fps UI, strong offline-first support, hot reload for fast dev |
| **Backend** | Node.js 20 LTS, Express.js, Prisma ORM, MySQL 8, JWT, BullMQ, node-cron, Redis | Non-blocking I/O for real-time GPS streams, fast JSON throughput, unified JS toolchain |
| **Admin** | React 19, Vite, Tailwind CSS, Google Maps JS API, React Query, Zustand | Fast dev loop, live tracking UX, responsive dashboards |

### Core Design Principles

1. **Offline-First**: Flutter writes to local Drift DB with `PENDING_SYNC` status; WorkManager pushes when connected
2. **GPS Integrity**: `kIsLocationMocked` check on Flutter side + server-side velocity anomaly detection
3. **Financial Accuracy**: Immutable Prisma transactions for payroll ledgers; append-only audit logs
4. **Role-Based Access**: Four tiers — `super_admin`, `city_head`, `coordinator`, `sales_officer`
5. **Same-Day Edit Window**: Server enforces visit modifications rejected past 11:59:59 PM on scheduled date

---

## 2. High-Level System Topology

```
┌─────────────────────────────────────────────────────────────────┐
│                          INTERNET                                │
│                    (HTTPS / TLS 1.3)                            │
└──────────────┬──────────────────────────────────────┬───────────┘
               │                                      │
        ┌──────▼──────┐                       ┌──────▼──────┐
        │   Flutter   │                       │    React    │
        │   App (Dart)│                       │ Admin Panel │
        │             │                       │   (Vite)    │
        │ • Riverpod  │                       │ • Dashboard │
        │ • Drift DB  │◄──────── REST ───────►│ • Live Map  │
        │ • WorkMgr   │◄──────── JWT ────────►│ • Reports   │
        │ • Geo-Fence │                       │ • Payroll   │
        └──────┬──────┘                       └─────────────┘
               │
        ┌──────▼────────────────────────────────────┐
        │                                            │
        │         Node.js Express REST API           │
        │      (Port 3000, HTTPS Ready)             │
        │                                            │
        │  • Routes → Middleware → Controllers      │
        │  • Services (Business Logic)              │
        │  • Prisma ORM (Type-Safe DB Layer)        │
        │  • BullMQ Workers (Async Jobs)            │
        │  • node-cron (Scheduled Tasks)            │
        │  • Redis Cache + BullMQ Broker            │
        │                                            │
        └──────┬────────────────────────────────────┘
               │
        ┌──────▼──────────────────┐
        │      MySQL 8.0           │
        │                          │
        │ • users (RBAC, salary)  │
        │ • visits (7/day queue)  │
        │ • gps_logs (tracking)   │
        │ • payroll_ledgers       │
        │ • sample_requests       │
        │ • audit_logs            │
        │                          │
        └──────────────────────────┘
```

### Data Flow: Offline Sync (Flutter → Node.js)

```
OFFLINE MODE (No Internet)
┌─────────────────────────────┐
│   Flutter App               │
│  • User completes visit     │
│  • Drift DB: PENDING_SYNC   │
│  • WorkManager: queued      │
└─────────────────────────────┘
          │
    (Network Restored)
          │
          ▼
┌─────────────────────────────┐
│   WorkManager Plugin        │
│  • Detects connectivity     │
│  • Reads all PENDING_SYNC   │
│  • Sends batch to API       │
│  • Retries with backoff     │
└─────────────────────────────┘
          │
          ▼
┌─────────────────────────────┐
│   Node.js BullMQ Worker     │
│  • Validates JWT + payload  │
│  • Applies business rules   │
│  • Writes to MySQL via      │
│    Prisma transaction       │
└─────────────────────────────┘
          │
          ▼
┌─────────────────────────────┐
│   Flutter Drift DB          │
│  • Row marked SYNCED        │
│  • Riverpod notifies UI     │
└─────────────────────────────┘
```

### GPS Spoofing Detection & Live Tracking

```
Officer In Field
       │
       ▼
GPS Ping (Every 30 sec)
├─ Latitude, Longitude
├─ Accuracy (meters)
├─ isMocked (Flutter: position.isMocked)
└─ Battery Level

       │
       ▼
Server-Side Validation (Node.js)
├─ isMocked = true?  ──► BLOCK + LOG + ALERT admin
├─ Speed > 150 km/h? ──► SUSPECT + LOG anomaly
├─ Accuracy > 5000m? ──► WARN
└─ VALID ──► INSERT gps_logs

       │
       ▼
React Admin Dashboard
└─ Google Maps JS API: live officer marker + breadcrumb trail
```

---

## 3. Complete Database Schema (MySQL 8 DDL via Prisma)

> Prisma schema (`prisma/schema.prisma`) is the source of truth. Raw DDL equivalents below for reference.

### schema.prisma (Prisma Source of Truth)

```prisma
generator client {
  provider = "prisma-client-js"
}

datasource db {
  provider = "mysql"
  url      = env("DATABASE_URL")
}

model User {
  id                  Int       @id @default(autoincrement())
  name                String
  email               String    @unique
  password            String
  role                Role      @default(sales_officer)
  cityId              Int?
  areaId              Int?
  reportingCityHeadId Int?
  basicSalary         Decimal   @db.Decimal(10, 2) @default(0)
  securityDeposit     Decimal   @db.Decimal(10, 2) @default(0)
  dailyPerformanceRate Decimal  @db.Decimal(10, 2) @default(3000)
  annualSampleLimitPkr Decimal  @db.Decimal(10, 2) @default(0)
  sampleUsedPkr       Decimal   @db.Decimal(10, 2) @default(0)
  leaveBalanceSick    Int       @default(10)
  leaveBalanceCasual  Int       @default(18)
  isActive            Boolean   @default(true)
  createdAt           DateTime  @default(now())
  updatedAt           DateTime  @updatedAt

  city                City?     @relation(fields: [cityId], references: [id])
  area                Area?     @relation(fields: [areaId], references: [id])
  attendances         Attendance[]
  visits              Visit[]
  gpsLogs             GpsLog[]
  sampleRequests      SampleRequest[]
  leaveRequests       LeaveRequest[]
  payrollLedgers      PayrollLedger[]
  auditLogs           AuditLog[]

  @@index([cityId])
  @@index([areaId])
  @@map("users")
}

enum Role {
  super_admin
  city_head
  coordinator
  sales_officer
}

model City {
  id    Int    @id @default(autoincrement())
  name  String
  users User[]
  areas Area[]

  @@map("cities")
}

model Area {
  id        Int        @id @default(autoincrement())
  name      String
  cityId    Int
  city      City       @relation(fields: [cityId], references: [id])
  locations Location[]
  users     User[]

  @@index([cityId])
  @@map("areas")
}

model Location {
  id           Int      @id @default(autoincrement())
  name         String
  type         LocationType
  areaId       Int
  latitude     Float
  longitude    Float
  priority     Priority @default(medium)
  contactName  String?
  contactPhone String?
  address      String?
  isActive     Boolean  @default(true)
  createdAt    DateTime @default(now())

  area         Area     @relation(fields: [areaId], references: [id])
  visits       Visit[]

  @@index([areaId])
  @@index([latitude, longitude])
  @@map("locations")
}

enum LocationType {
  school
  bookshop
}

enum Priority {
  high
  medium
  low
}

model Attendance {
  id              Int       @id @default(autoincrement())
  userId          Int
  date            DateTime  @db.Date
  dayStartTime    DateTime?
  dayStartLat     Float?
  dayStartLng     Float?
  dayEndTime      DateTime?
  dayEndLat       Float?
  dayEndLng       Float?
  status          AttendanceStatus @default(absent)
  cannotWorkReason String?
  createdAt       DateTime  @default(now())

  user            User      @relation(fields: [userId], references: [id])

  @@unique([userId, date])
  @@index([userId, date])
  @@map("attendance")
}

enum AttendanceStatus {
  present
  absent
  cannot_work
  leave
}

model GpsLog {
  id            Int      @id @default(autoincrement())
  userId        Int
  latitude      Float
  longitude     Float
  accuracy      Float
  isMocked      Boolean  @default(false)
  batteryLevel  Int?
  recordedAt    DateTime @default(now())

  user          User     @relation(fields: [userId], references: [id])

  @@index([userId, recordedAt])
  @@map("gps_logs")
}

model Visit {
  id               Int          @id @default(autoincrement())
  userId           Int
  locationId       Int
  scheduledDate    DateTime     @db.Date
  dailySequence    Int
  status           VisitStatus  @default(planned)
  arrivalTime      DateTime?
  arrivalLat       Float?
  arrivalLng       Float?
  completionTime   DateTime?
  contactPerson    String?
  designation      String?
  phone            String?
  notes            String?      @db.Text
  visitType        String?
  sampleDistributed Int?        @default(0)
  photoUrl         String?
  missedReason     String?
  carryForwardCnt  Int          @default(0)
  approvalStatus   ApprovalStatus?
  approvedById     Int?
  isAdHoc          Boolean      @default(false)
  syncStatus       SyncStatus   @default(synced)
  createdAt        DateTime     @default(now())
  updatedAt        DateTime     @updatedAt

  user             User         @relation(fields: [userId], references: [id])
  location         Location     @relation(fields: [locationId], references: [id])

  @@index([userId, scheduledDate])
  @@index([locationId])
  @@map("visits")
}

enum VisitStatus {
  planned
  in_progress
  completed
  missed
}

enum ApprovalStatus {
  pending
  approved
  rejected
}

enum SyncStatus {
  pending_sync
  synced
}

model Product {
  id       Int     @id @default(autoincrement())
  name     String
  pricePkr Decimal @db.Decimal(10, 2)
  isActive Boolean @default(true)

  sampleRequests SampleRequest[]

  @@map("products")
}

model SampleRequest {
  id              Int           @id @default(autoincrement())
  userId          Int
  productId       Int
  visitId         Int?
  quantity        Int
  totalValuePkr   Decimal       @db.Decimal(10, 2)
  status          SampleStatus  @default(pending)
  approvedById    Int?
  requestedAt     DateTime      @default(now())
  recoveredAt     DateTime?
  reminder10Sent  Boolean       @default(false)
  reminder20Sent  Boolean       @default(false)

  user            User          @relation(fields: [userId], references: [id])
  product         Product       @relation(fields: [productId], references: [id])

  @@index([userId])
  @@index([status])
  @@map("sample_requests")
}

enum SampleStatus {
  pending
  approved
  dispatched
  recovered
  deducted
}

model LeaveRequest {
  id          Int         @id @default(autoincrement())
  userId      Int
  leaveType   LeaveType
  startDate   DateTime    @db.Date
  endDate     DateTime    @db.Date
  days        Int
  reason      String?     @db.Text
  status      ApprovalStatus @default(pending)
  approvedById Int?
  createdAt   DateTime    @default(now())

  user        User        @relation(fields: [userId], references: [id])

  @@index([userId])
  @@map("leave_requests")
}

enum LeaveType {
  sick
  casual
}

model PayrollLedger {
  id                    Int      @id @default(autoincrement())
  userId                Int
  month                 Int
  year                  Int
  presentDays           Int      @default(0)
  basicSalary           Decimal  @db.Decimal(10, 2)
  performanceEarned     Decimal  @db.Decimal(10, 2) @default(0)
  missedVisitPenalty    Decimal  @db.Decimal(10, 2) @default(0)
  sampleDeduction       Decimal  @db.Decimal(10, 2) @default(0)
  securityDepositHeld   Decimal  @db.Decimal(10, 2) @default(0)
  netPayable            Decimal  @db.Decimal(10, 2)
  isFinalized           Boolean  @default(false)
  calculatedAt          DateTime?

  user                  User     @relation(fields: [userId], references: [id])

  @@unique([userId, month, year])
  @@index([userId, year, month])
  @@map("payroll_ledgers")
}

model AuditLog {
  id         Int      @id @default(autoincrement())
  actorId    Int
  action     String
  targetType String
  targetId   Int?
  before     Json?
  after      Json?
  ipAddress  String?
  createdAt  DateTime @default(now())

  actor      User     @relation(fields: [actorId], references: [id])

  @@index([actorId])
  @@index([createdAt])
  @@map("audit_logs")
}
```

---

## 4. Complete API Endpoint Specification

### Base URL
```
Development:  http://localhost:3000/api/v1
Production:   https://api.bookmark.services/api/v1
```

### Standard Response Envelope

```json
// Success
{ "success": true, "data": { ... }, "meta": { ... } }

// Error
{ "success": false, "error": { "code": "VALIDATION_ERROR", "message": "..." } }
```

### HTTP Status Codes
| Code | Meaning |
|------|---------|
| 200 | OK — read/update success |
| 201 | Created — new resource |
| 400 | Bad Request — validation failed |
| 401 | Unauthorized — missing/invalid JWT |
| 403 | Forbidden — insufficient role |
| 404 | Not Found |
| 422 | Unprocessable — business rule violation |
| 500 | Internal Server Error |

---

### 4.1 Auth & Workday

#### `POST /api/v1/auth/login`
```json
// Request
{ "email": "officer@bookmark.pk", "password": "secret123" }

// Response 200
{
  "success": true,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": 12,
      "name": "Ali Raza",
      "role": "sales_officer",
      "cityId": 3,
      "areaId": 7,
      "leaveBalanceSick": 10,
      "leaveBalanceCasual": 18
    }
  }
}
```

#### `POST /api/v1/auth/change-password`
> Requires `Authorization: Bearer <token>`
```json
// Request
{ "currentPassword": "old", "newPassword": "new_strong_pass" }

// Response 200
{ "success": true, "data": { "message": "Password updated" } }
```

#### `POST /api/v1/auth/force-reset` *(admin only)*
```json
// Request
{ "userId": 12, "newPassword": "temp_pass_123" }
```

#### `POST /api/v1/workday/day-start`
```json
// Request
{
  "latitude": 24.8607,
  "longitude": 67.0011,
  "isMocked": false,
  "batteryLevel": 87
}

// Response 201
{
  "success": true,
  "data": {
    "attendanceId": 501,
    "dayStartTime": "2026-07-27T08:02:14Z",
    "todayVisits": [
      { "id": 301, "sequence": 1, "locationName": "City Grammar School", "lat": 24.86, "lng": 67.01 }
    ]
  }
}
```

#### `POST /api/v1/workday/day-end`
```json
// Request
{ "latitude": 24.8607, "longitude": 67.0011, "isMocked": false }

// Response 200
{ "success": true, "data": { "dayEndTime": "2026-07-27T18:45:00Z", "visitsCompleted": 5, "visitsMissed": 2 } }
```

#### `POST /api/v1/workday/cannot-work`
```json
// Request
{ "reason": "severe_weather", "notes": "Road flooded near hub area" }
```

---

### 4.2 Visit Execution

#### `GET /api/v1/visits/today`
```json
// Response 200
{
  "success": true,
  "data": {
    "date": "2026-07-27",
    "visits": [
      {
        "id": 301,
        "sequence": 1,
        "status": "planned",
        "location": {
          "id": 45,
          "name": "City Grammar School",
          "type": "school",
          "priority": "high",
          "latitude": 24.8607,
          "longitude": 67.0011,
          "contactName": "Principal Ahmed",
          "contactPhone": "0300-1234567"
        },
        "carryForwardCnt": 0,
        "isAdHoc": false
      }
    ]
  }
}
```

#### `POST /api/v1/visits/{id}/start`
```json
// Request
{ "arrivalLat": 24.8607, "arrivalLng": 67.0011, "isMocked": false }

// Response 200
{ "success": true, "data": { "status": "in_progress", "arrivalTime": "2026-07-27T10:15:00Z" } }

// Error 422 (geofence)
{ "success": false, "error": { "code": "OUTSIDE_GEOFENCE", "message": "You must be within 200m of the location" } }
```

#### `POST /api/v1/visits/{id}/complete`
```json
// Request
{
  "contactPerson": "Mr. Ahmed",
  "designation": "Principal",
  "phone": "0300-1234567",
  "notes": "Interested in new stock. Follow up next week.",
  "visitType": "sales_call",
  "sampleDistributed": 2,
  "followUpDate": "2026-08-03"
}

// Response 200
{ "success": true, "data": { "status": "completed", "completionTime": "2026-07-27T10:45:00Z" } }
```

#### `POST /api/v1/visits/{id}/edit` *(same-day only)*
```json
// Request — only text fields, GPS/timestamps locked
{ "notes": "Updated: confirmed order for 50 books", "contactPerson": "Ms. Sara" }

// Error 422 (past midnight)
{ "success": false, "error": { "code": "EDIT_WINDOW_CLOSED", "message": "Visit details cannot be edited after 11:59 PM on the scheduled date" } }
```

#### `POST /api/v1/visits/{id}/mark-missed`
```json
// Request
{ "reason": "Location closed. Gate locked.", "photoUrl": "https://cdn.bookmark.pk/evidence/12345.jpg" }

// Response 200
{ "success": true, "data": { "status": "missed", "carryForwardCnt": 2, "approvalStatus": "pending" } }
```

#### `POST /api/v1/visits/adhoc`
```json
// Request
{ "locationId": 52, "notes": "Passed by, introduced products", "visitType": "cold_call" }
```

---

### 4.3 Location Tracking

#### `POST /api/v1/tracking/ping`
```json
// Request
{
  "latitude": 24.8615,
  "longitude": 67.0025,
  "accuracy": 8.5,
  "isMocked": false,
  "batteryLevel": 72,
  "recordedAt": "2026-07-27T10:20:00Z"
}

// Response 200 (valid)
{ "success": true }

// Response 403 (mock detected)
{ "success": false, "error": { "code": "MOCK_LOCATION_DETECTED", "message": "GPS spoofing detected. Incident logged." } }
```

---

### 4.4 Sample Management

#### `POST /api/v1/samples/request`
```json
// Request
{ "productId": 3, "quantity": 5, "visitId": 301 }

// Response 201
{ "success": true, "data": { "id": 88, "totalValuePkr": "2500.00", "remainingLimitPkr": "12500.00" } }

// Error 422 (limit exceeded)
{ "success": false, "error": { "code": "SAMPLE_LIMIT_EXCEEDED", "message": "Request exceeds your annual sample budget" } }
```

#### `POST /api/v1/samples/{id}/recover`
```json
// Response 200
{ "success": true, "data": { "status": "recovered", "recoveredAt": "2026-07-27T14:00:00Z" } }
```

---

### 4.5 Leave Requests

#### `POST /api/v1/leaves/apply`
```json
// Request
{ "leaveType": "casual", "startDate": "2026-08-01", "endDate": "2026-08-02", "reason": "Family function" }

// Response 201
{ "success": true, "data": { "id": 21, "days": 2, "remainingCasual": 16 } }
```

---

### 4.6 Admin Endpoints *(city_head / super_admin)*

#### `GET /api/v1/admin/officers`
Returns paginated list of officers with today's attendance status, visit counts.

#### `GET /api/v1/admin/tracking/live`
```json
// Response 200
{
  "success": true,
  "data": {
    "officers": [
      {
        "userId": 12,
        "name": "Ali Raza",
        "lastPing": "2026-07-27T10:22:00Z",
        "lat": 24.8615,
        "lng": 67.0025,
        "currentVisitId": 301,
        "batteryLevel": 72
      }
    ]
  }
}
```

#### `GET /api/v1/admin/missed-visits`
Returns pending missed visit approvals for city head review.

#### `POST /api/v1/admin/missed-visits/{id}/approve`
```json
// Request
{ "approved": true, "comment": "Verified — location confirmed closed" }
```

#### `GET /api/v1/admin/payroll/{month}/{year}`
Returns full payroll summary for all officers for the given month.

#### `POST /api/v1/admin/samples/{id}/approve`
Approve or reject a sample request.

#### `GET /api/v1/admin/locations/{id}/history`
Full visit history for a location across all officers.

---

## 5. Node.js Project Structure

```
backend/
├── prisma/
│   ├── schema.prisma          ← Single source of truth for DB
│   └── migrations/            ← Auto-generated by Prisma
├── src/
│   ├── index.js               ← App entry point
│   ├── app.js                 ← Express setup, middleware registration
│   ├── config/
│   │   ├── database.js        ← Prisma client singleton
│   │   ├── redis.js           ← ioredis client
│   │   └── env.js             ← Validated env vars (zod)
│   ├── routes/
│   │   ├── index.js           ← Route aggregator
│   │   ├── auth.routes.js
│   │   ├── workday.routes.js
│   │   ├── visits.routes.js
│   │   ├── tracking.routes.js
│   │   ├── samples.routes.js
│   │   ├── leaves.routes.js
│   │   └── admin.routes.js
│   ├── controllers/
│   │   ├── auth.controller.js
│   │   ├── workday.controller.js
│   │   ├── visits.controller.js
│   │   ├── tracking.controller.js
│   │   ├── samples.controller.js
│   │   ├── leaves.controller.js
│   │   └── admin.controller.js
│   ├── services/              ← All business logic lives here
│   │   ├── auth.service.js
│   │   ├── workday.service.js
│   │   ├── visits.service.js
│   │   ├── tracking.service.js
│   │   ├── samples.service.js
│   │   ├── payroll.service.js
│   │   └── planning.service.js
│   ├── middleware/
│   │   ├── auth.middleware.js  ← JWT verification
│   │   ├── role.middleware.js  ← RBAC guard
│   │   ├── validate.middleware.js ← Zod schema validation
│   │   └── errorHandler.js
│   ├── jobs/                  ← BullMQ workers
│   │   ├── queue.js           ← Queue definitions
│   │   ├── syncVisit.worker.js
│   │   └── notification.worker.js
│   ├── schedulers/            ← node-cron tasks
│   │   ├── index.js
│   │   ├── routePlanning.cron.js    ← 12:00 AM
│   │   ├── attendanceEngine.cron.js ← 11:00 PM
│   │   ├── sampleReminder.cron.js   ← Daily check
│   │   └── payrollEngine.cron.js    ← Month-end
│   └── utils/
│       ├── geoDistance.js
│       ├── response.js
│       └── logger.js
├── .env.example
├── package.json
└── README.md
```

---

## 6. Automated System Schedulers (node-cron)

### 6.1 — 12:00 AM Route Planning Engine

**File:** `src/schedulers/routePlanning.cron.js`

```
cron.schedule('0 0 * * *', async () => {
  FOR each active sales_officer:
    1. Get tomorrow's date
    2. Fetch coordinator-assigned priority visits for officer+date (sequence first)
    3. Fetch approved carry-forward missed visits (carryForwardCnt < 5), increment counter
    4. Fetch pre-scheduled follow-ups for that date
    5. Fill remaining slots from area pool:
       - 2 High-Priority Schools
       - 2 Medium-Priority Schools
       - 2 Bookshops
       (exclude locations visited in last 7 days)
    6. Cap total at 7 visits
    7. Order all 7 by geographic proximity using Haversine distance clustering
    8. INSERT into visits with dailySequence 1–7
})
```

### 6.2 — 11:00 PM Attendance Engine

**File:** `src/schedulers/attendanceEngine.cron.js`

```
cron.schedule('0 23 * * *', async () => {
  FOR each active sales_officer where no attendance record for today:
    1. Create attendance row: status = 'absent'
    2. Deduct 1 casual leave day (if balance > 0), else mark unpaid
    3. Log to audit_logs
    4. Notify city head via notification queue
})
```

### 6.3 — Sample Recovery Reminders (Daily 9:00 AM)

**File:** `src/schedulers/sampleReminder.cron.js`

```
cron.schedule('0 9 * * *', async () => {
  FOR each sample_request where status = 'dispatched':
    daysSince = today - requestedAt

    IF daysSince >= 10 AND reminder10Sent = false:
      SEND push notification to officer: "Sample recovery reminder: 10 days elapsed"
      SEND email to city_head
      SET reminder10Sent = true

    IF daysSince >= 20 AND reminder20Sent = false:
      SEND push notification: "URGENT: Sample recovery overdue — 20 days"
      SEND email to super_admin
      SET reminder20Sent = true
      CREATE payroll deduction entry for next payroll run
})
```

### 6.4 — End-of-Month Payroll Engine (Last day of month, 11:30 PM)

**File:** `src/schedulers/payrollEngine.cron.js`

```
cron.schedule('30 23 28-31 * *', async () => {
  IF today != last day of current month: RETURN

  FOR each active sales_officer:
    presentDays    = COUNT(attendance WHERE status='present' AND month=current)
    performanceEarned = presentDays × dailyPerformanceRate (PKR 3,000 default)
    missedPenalty  = SUM(deductions from rejected missed visit approvals)
    sampleDeduction = SUM(unrecovered samples past 20 days)
    securityHeld   = basicSalary × 0.10 (per contract)
    netPayable     = basicSalary + performanceEarned
                     - missedPenalty - sampleDeduction - securityHeld

    INSERT payroll_ledgers (userId, month, year, ..., netPayable, isFinalized=true)
    within Prisma.$transaction (atomic — no partial writes)
})
```

---

## 7. Flutter App Architecture

### Clean Architecture Layers

```
lib/
├── main.dart
├── app.dart                        ← MaterialApp, GoRouter, ProviderScope
├── core/
│   ├── constants/
│   │   ├── api_constants.dart      ← BASE_URL, endpoints
│   │   └── app_constants.dart
│   ├── network/
│   │   ├── dio_client.dart         ← Dio instance + JWT interceptor
│   │   └── api_exception.dart
│   ├── storage/
│   │   └── secure_storage.dart     ← flutter_secure_storage for JWT
│   └── utils/
│       ├── geo_utils.dart          ← Haversine distance calc
│       └── mock_location_guard.dart ← isMocked detection
├── data/
│   ├── local/                      ← Drift (SQLite) tables + DAOs
│   │   ├── app_database.dart
│   │   ├── tables/
│   │   │   ├── visits_table.dart
│   │   │   ├── gps_logs_table.dart
│   │   │   └── attendance_table.dart
│   │   └── daos/
│   │       ├── visits_dao.dart
│   │       └── attendance_dao.dart
│   ├── remote/                     ← Dio API data sources
│   │   ├── auth_remote.dart
│   │   ├── visits_remote.dart
│   │   └── tracking_remote.dart
│   └── repositories/               ← Implements domain interfaces
│       ├── auth_repository_impl.dart
│       ├── visits_repository_impl.dart
│       └── tracking_repository_impl.dart
├── domain/
│   ├── entities/
│   │   ├── visit.dart
│   │   ├── user.dart
│   │   └── attendance.dart
│   ├── repositories/               ← Abstract interfaces
│   │   ├── auth_repository.dart
│   │   └── visits_repository.dart
│   └── usecases/
│       ├── login_usecase.dart
│       ├── get_today_visits_usecase.dart
│       ├── complete_visit_usecase.dart
│       ├── mark_missed_usecase.dart
│       ├── day_start_usecase.dart
│       └── sync_pending_usecase.dart
├── presentation/
│   ├── providers/                  ← Riverpod providers
│   │   ├── auth_provider.dart
│   │   ├── visits_provider.dart
│   │   └── tracking_provider.dart
│   ├── screens/
│   │   ├── splash/
│   │   ├── auth/
│   │   │   └── login_screen.dart
│   │   ├── dashboard/
│   │   │   └── dashboard_screen.dart
│   │   ├── workday/
│   │   │   ├── day_start_screen.dart
│   │   │   └── day_end_screen.dart
│   │   ├── visits/
│   │   │   ├── visit_list_screen.dart
│   │   │   ├── visit_detail_screen.dart
│   │   │   ├── complete_visit_screen.dart
│   │   │   └── missed_visit_screen.dart
│   │   ├── samples/
│   │   ├── leaves/
│   │   └── profile/
│   └── widgets/
│       ├── visit_card.dart
│       ├── gps_status_badge.dart
│       └── sync_indicator.dart
└── background/
    └── sync_worker.dart            ← WorkManager task: push PENDING_SYNC rows
```

### Key Flutter Rules

- **Riverpod providers call UseCases, never Repositories directly**
- **Drift DAOs are injected only into Repository implementations**
- **No hardcoded strings** — all API URLs in `api_constants.dart`, all copy in `app_constants.dart`
- **Every GPS action checks `position.isMocked` before proceeding**
- **WorkManager task runs on `NetworkType.connected` constraint only**

### Anti-GPS Spoofing (Flutter)

```dart
// core/utils/mock_location_guard.dart
Future<bool> isMockLocationActive(Position position) async {
  if (position.isMocked) return true;

  // Additional check on Android
  if (Platform.isAndroid) {
    final isDevMode = await _checkDeveloperOptions();
    if (isDevMode && await _isMockProviderActive()) return true;
  }
  return false;
}

// Usage in DayStartUseCase
final isMocked = await mockLocationGuard.isMockLocationActive(pos);
if (isMocked) {
  await trackingRemote.reportMockAttempt(userId, pos);
  throw MockLocationException('GPS spoofing detected. Action blocked.');
}
```

### Offline-First Sync Worker

```dart
// background/sync_worker.dart
class SyncWorker extends BackgroundWorker {
  @override
  Future<bool> performWork() async {
    final db = await AppDatabase.getInstance();
    final pending = await db.visitsDao.getPendingSync();

    for (final visit in pending) {
      try {
        await visitsRemote.syncVisit(visit);
        await db.visitsDao.markSynced(visit.id);
      } catch (e) {
        return false; // WorkManager will retry
      }
    }
    return true;
  }
}
```

### Geofencing (200m Lock)

```dart
// In CompleteVisitUseCase
final distance = GeoUtils.haversine(
  current.latitude, current.longitude,
  location.latitude, location.longitude
);

if (distance > 200) {
  throw GeofenceException('Move within 200m of the location to check in.');
}
```

---

## 8. pubspec.yaml (Flutter Dependencies)

```yaml
name: bookmark_sfa
description: Bookmark Field Force Automation
version: 1.0.0+1

environment:
  sdk: ">=3.3.0 <4.0.0"

dependencies:
  flutter:
    sdk: flutter

  # State Management
  flutter_riverpod: ^2.5.1
  riverpod_annotation: ^2.3.5

  # Navigation
  go_router: ^14.2.0

  # Network
  dio: ^5.4.3
  pretty_dio_logger: ^1.4.0

  # Local DB (Drift / SQLite)
  drift: ^2.18.0
  sqlite3_flutter_libs: ^0.5.25
  path_provider: ^2.1.3
  path: ^1.9.0

  # Secure Storage (JWT)
  flutter_secure_storage: ^9.2.2

  # Maps & Location
  google_maps_flutter: ^2.7.0
  geolocator: ^12.0.0
  permission_handler: ^11.3.1

  # Background Sync
  workmanager: ^0.5.2

  # Image Picker (missed visit evidence)
  image_picker: ^1.1.2

  # UI
  cached_network_image: ^3.3.1
  lottie: ^3.1.2
  shimmer: ^3.0.0

dev_dependencies:
  flutter_test:
    sdk: flutter
  flutter_lints: ^4.0.0
  build_runner: ^2.4.11
  drift_dev: ^2.18.0
  riverpod_generator: ^2.4.3
```

---

## 9. package.json (Node.js Backend)

```json
{
  "name": "bookmark-sfa-api",
  "version": "1.0.0",
  "description": "Bookmark Field Force Automation — Node.js REST API",
  "main": "src/index.js",
  "type": "module",
  "scripts": {
    "start": "node src/index.js",
    "dev": "nodemon src/index.js",
    "db:migrate": "prisma migrate deploy",
    "db:push": "prisma db push",
    "db:seed": "node prisma/seed.js",
    "db:studio": "prisma studio"
  },
  "dependencies": {
    "@prisma/client": "^5.16.0",
    "bcryptjs": "^2.4.3",
    "bullmq": "^5.10.0",
    "compression": "^1.7.4",
    "cors": "^2.8.5",
    "dotenv": "^16.4.5",
    "express": "^4.19.2",
    "express-rate-limit": "^7.4.0",
    "helmet": "^7.1.0",
    "ioredis": "^5.4.1",
    "jsonwebtoken": "^9.0.2",
    "morgan": "^1.10.0",
    "node-cron": "^3.0.3",
    "winston": "^3.13.0",
    "zod": "^3.23.8"
  },
  "devDependencies": {
    "nodemon": "^3.1.4",
    "prisma": "^5.16.0"
  },
  "engines": {
    "node": ">=20.0.0"
  }
}
```

---

## 10. Developer Onboarding — 15-Minute Setup

### Prerequisites
- Node.js 20 LTS (`brew install node`)
- Flutter 3.x (`brew install flutter` or via official installer)
- MySQL 8 (`brew install mysql && brew services start mysql`)
- Redis (`brew install redis && brew services start redis`)
- Android Studio with Flutter plugin installed

### Backend Setup (Node.js)

```bash
cd backend
cp .env.example .env
# Edit .env: set DATABASE_URL, JWT_SECRET, REDIS_URL

npm install
npx prisma migrate dev --name init
npx prisma db seed           # Seeds cities, areas, admin user
npm run dev                  # Starts on http://localhost:3000
```

`.env.example`:
```env
DATABASE_URL="mysql://root:password@localhost:3306/bookmark_sfa"
JWT_SECRET="change_this_to_a_64_char_random_string"
JWT_EXPIRES_IN="30d"
REDIS_URL="redis://localhost:6379"
PORT=3000
NODE_ENV=development
```

### Flutter App Setup

```bash
cd mobile
flutter pub get
# Open in Android Studio or VS Code
# Select emulator or physical device
flutter run
```

For emulator, `BASE_URL` in `lib/core/constants/api_constants.dart`:
```dart
// Android emulator → host machine localhost
const String baseUrl = 'http://10.0.2.2:3000/api/v1';
```

### Admin Panel Setup (React)

```bash
cd admin
cp .env.example .env.local
# Set VITE_API_URL=http://localhost:3000/api/v1

npm install
npm run dev                  # Starts on http://localhost:5173
```

---

## 11. Edge Cases & Defensive Engineering

### Same-Day Edit Lock
```js
// visits.service.js
const scheduledDate = dayjs(visit.scheduledDate).endOf('day');
if (dayjs().isAfter(scheduledDate)) {
  throw new AppError('EDIT_WINDOW_CLOSED', 422,
    'Visit details cannot be edited after 11:59 PM on the scheduled date');
}
```

### Non-Retrievable Passwords
- Passwords hashed with `bcrypt` (rounds: 12) on create and force-reset
- No endpoint returns or logs raw passwords
- Admin force-reset sets a temporary password; officer must change on first login

### Append-Only Audit Logs
```js
// All admin override actions write to audit_logs
// No UPDATE or DELETE is ever issued on audit_logs table
// Prisma schema has no `update` or `delete` operations defined for AuditLog
await prisma.auditLog.create({
  data: { actorId, action, targetType, targetId, before, after, ipAddress }
});
```

### Mock Location — Incident Escalation Chain
1. Flutter blocks action + shows user warning
2. Flutter calls `POST /api/v1/tracking/ping` with `isMocked: true`
3. Node.js logs to `gps_logs` with `isMocked: true`
4. BullMQ notification job fires → alert sent to city_head
5. `audit_logs` entry created for review

---

## 12. Golden Coding Rules

### Node.js (Express + Prisma)
- **Controllers only validate the request and return JSON. Zero business logic.**
- **All business logic lives in `services/`. Services call Prisma, never raw SQL.**
- **Never execute unparameterized queries. Use Prisma exclusively.**
- **All endpoints require `authMiddleware` unless explicitly public.**
- **Financial operations use `prisma.$transaction([...])` — never partial writes.**

### Flutter (Dart + Riverpod)
- **Riverpod providers call UseCases. UseCases call Repository interfaces. Never skip layers.**
- **Always use immutable state — `@freezed` or `copyWith` patterns only.**
- **All GPS actions guard with `mock_location_guard.dart` before proceeding.**
- **Offline data writes to Drift with `syncStatus: SyncStatus.pendingSync` immediately.**
- **No hardcoded API URLs or keys — always read from `api_constants.dart`.**

### Database (Prisma / MySQL)
- **Never DROP or TRUNCATE in migrations — use soft deletes (`isActive: false`).**
- **All financial fields use `@db.Decimal(10, 2)` — never Float for money.**
- **Audit logs are write-only from the application. No update/delete allowed.**

---

## 13. AI Code Generation Rules (For Cursor / Claude / Copilot)

When modifying this codebase, AI tools must adhere to the following constraints:

1. **Strictly preserve existing Prisma field names and JSON API contracts.** Renaming a field breaks the Flutter app without a coordinated release.
2. **Never add raw SQL to Node.js code.** All queries must go through Prisma client.
3. **Never bypass the UseCase layer in Flutter.** If a screen needs data, add a UseCase.
4. **Never store JWT in SharedPreferences.** Use `flutter_secure_storage` only.
5. **All new API endpoints must be added to this document before implementation.**
6. **New schedulers must log their start, completion, and any errors via `winston` logger.**
7. **Do not use `var` in Dart. Use explicit types or `final`/`const`.**
8. **Do not use `any` type in Node.js — use Zod schemas for all input validation.**

---

*This document is the canonical reference for the Bookmark SFA system. All implementation decisions must align with the architecture defined here.*
