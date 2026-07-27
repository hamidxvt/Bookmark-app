# ARCHITECTURE.md
## Bookmark Sales Force Automation System


Last Updated: July 27, 2026 | Status: Production-Ready | Version: 1.0

---

## 1. System Overview & Tech Stack

### Technology Decisions

| Layer | Technology | Rationale |
|-------|-----------|-----------|
| **Mobile** | Kotlin, MVVM, Retrofit, Room DB, WorkManager, Google Maps, Fused Location | Native performance, offline-first, battery efficiency, GPS spoofing detection |
| **Backend** | Laravel 11, PHP 8.3, MySQL 8, Sanctum, Redis, Queue Workers | Rapid feature iteration, battle-tested ORM, built-in scheduler, async jobs |
| **Admin** | React 18, Vite, Tailwind CSS, Google Maps JS API | Fast dev loop, responsive UX, live tracking UX |

### Core Design Principles

1. **Offline-First**: Mobile writes locally, syncs when connected
2. **GPS Integrity**: All location data validated server-side against spoofing
3. **Financial Accuracy**: Immutable salary ledgers, append-only audit logs
4. **Role-Based Access**: Four tiers (super_admin, city_head, coordinator, sales_officer)
5. **Same-Day Edit Window**: Visit details editable until 11:59:59 PM on scheduled date

---

## 2. High-Level System Topology

```
┌─────────────────────────────────────────────────────────────────┐
│                          INTERNET                                │
│                    (HTTPS / TLS 1.3)                            │
└──────────────┬──────────────────────────────────────┬───────────┘
               │                                      │
        ┌──────▼──────┐                       ┌──────▼──────┐
        │   Android   │                       │    React    │
        │    (Kotlin) │                       │   (Vite)    │
        │             │                       │             │
        │ • MVVM      │                       │ • Dashboard │
        │ • Room DB   │◄──────────API────────►│ • Live Map  │
        │ • WorkMgr   │                       │ • Reports   │
        │ • Geo-Fence │                       │             │
        └──────┬──────┘                       └─────────────┘
               │
        ┌──────▼────────────────────────────────────┐
        │                                            │
        │         Laravel 11 REST API                │
        │      (Port 8000, HTTPS Ready)             │
        │                                            │
        │  • Controllers (Request Validation)       │
        │  • Services (Business Logic)              │
        │  • Models (Eloquent ORM)                  │
        │  • Scheduled Commands (Crons)            │
        │  • Queue Workers (Async Jobs)            │
        │  • Redis Cache Layer                      │
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
        └───────────────────────────┘
```

### Data Flow: Offline Sync & Live Tracking

```
OFFLINE MODE (No Internet)
┌─────────────────────────┐
│   Android App           │
│  • User completes visit │
│  • Room DB: PENDING_SYNC│
│  • WorkManager: waiting │
└─────────────────────────┘
          │
    (Network Restored)
          │
          ▼
┌─────────────────────────┐
│   WorkManager           │
│  • Detects connection   │
│  • Batches local data   │
│  • Retries on failure   │
└─────────────────────────┘
          │
          ▼
┌─────────────────────────┐
│   Laravel Queue         │
│  • Validates payload    │
│  • Applies business     │
│  • Updates payroll      │
│  • Responds success     │
└─────────────────────────┘
          │
          ▼
┌─────────────────────────┐
│   Android Room DB       │
│  • Mark as SYNCED       │
│  • Notify UI (success)  │
└─────────────────────────┘
```

### GPS Spoofing Detection & Live Tracking

```
Officer In Field
       │
       ▼
GPS Ping (Every 30 sec)
├─ Latitude
├─ Longitude
├─ Accuracy (meters)
├─ Android Build: location.isMock
└─ Battery Level

       │
       ▼
Server-Side Validation
├─ Is isMock = true? ──► BLOCK + LOG + ALERT
├─ Delta from last > 150 km/h? ──► SUSPECT + LOG
├─ Accuracy > 5000m? ──► WARN
└─ VALID ──► STORE in gps_logs

       │
       ▼
Real-Time Admin Dashboard
└─ Google Maps JS API displays live marker + breadcrumb trail
```

---

## 3. Complete Database Schema (MySQL 8 DDL)

### Foundational Tables

```sql
-- ============================================================
-- USERS (Role-Based Access, Salary Components, Limits)
-- ============================================================
CREATE TABLE users (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('super_admin', 'city_head', 'coordinator', 'sales_officer') NOT NULL,
    
    -- Geographic Assignment
    city_id BIGINT UNSIGNED,
    area_id BIGINT UNSIGNED,
    reporting_city_head_id BIGINT UNSIGNED,
    
    -- Compensation Structure (PKR)
    basic_salary DECIMAL(10,2) DEFAULT 0,
    security_deposit_monthly DECIMAL(10,2) DEFAULT 0,
    daily_performance_rate DECIMAL(10,2) DEFAULT 3000.00,
    annual_sample_limit_pkr DECIMAL(12,2) DEFAULT 0,
    annual_sample_used_pkr DECIMAL(12,2) DEFAULT 0,
    
    -- Leave Entitlement (Fixed: 28 days total)
    leave_sick_balance INT DEFAULT 10,
    leave_casual_balance INT DEFAULT 18,
    
    -- Account Status
    is_active BOOLEAN DEFAULT TRUE,
    last_login_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (city_id) REFERENCES cities(id),
    FOREIGN KEY (area_id) REFERENCES areas(id),
    FOREIGN KEY (reporting_city_head_id) REFERENCES users(id),
    INDEX idx_role (role),
    INDEX idx_city_id (city_id),
    INDEX idx_area_id (area_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- GEOGRAPHIC HIERARCHY
-- ============================================================
CREATE TABLE cities (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    state VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE areas (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    city_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (city_id) REFERENCES cities(id),
    UNIQUE KEY unique_area_per_city (city_id, name),
    INDEX idx_city_id (city_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- LOCATIONS (Schools & Bookshops)
-- ============================================================
CREATE TABLE locations (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    area_id BIGINT UNSIGNED NOT NULL,
    name VARCHAR(255) NOT NULL,
    type ENUM('school', 'bookshop') NOT NULL,
    priority ENUM('high', 'medium', 'low') DEFAULT 'medium',
    
    -- GPS Coordinates
    latitude DECIMAL(10,8) NOT NULL,
    longitude DECIMAL(11,8) NOT NULL,
    address TEXT,
    
    -- Contact Info
    contact_person_name VARCHAR(255),
    contact_person_designation VARCHAR(255),
    contact_phone VARCHAR(20),
    
    -- Tracking
    last_visit_date DATE,
    total_visit_count INT DEFAULT 0,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (area_id) REFERENCES areas(id),
    INDEX idx_area_id (area_id),
    INDEX idx_type_priority (type, priority),
    SPATIAL INDEX idx_geo (POINT(latitude, longitude))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- ATTENDANCE (Day Start/End Tracking)
-- ============================================================
CREATE TABLE attendance (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    date_recorded DATE NOT NULL,
    
    -- Day Boundaries
    day_start_at TIMESTAMP,
    day_start_lat DECIMAL(10,8),
    day_start_lng DECIMAL(11,8),
    
    day_end_at TIMESTAMP,
    day_end_lat DECIMAL(10,8),
    day_end_lng DECIMAL(11,8),
    
    -- Status
    status ENUM('present', 'absent', 'cannot_work') DEFAULT 'absent',
    cannot_work_reason TEXT,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE KEY unique_user_day (user_id, date_recorded),
    INDEX idx_date_recorded (date_recorded)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- GPS LOGS (Periodic Location Breadcrumbs, Spoofing Flags)
-- ============================================================
CREATE TABLE gps_logs (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    visit_id BIGINT UNSIGNED,
    
    latitude DECIMAL(10,8) NOT NULL,
    longitude DECIMAL(11,8) NOT NULL,
    accuracy_meters FLOAT,
    
    is_mock_location BOOLEAN DEFAULT FALSE,
    battery_percent INT,
    
    recorded_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (visit_id) REFERENCES visits(id) ON DELETE SET NULL,
    INDEX idx_user_id_recorded (user_id, recorded_at),
    INDEX idx_visit_id (visit_id),
    INDEX idx_is_mock_location (is_mock_location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- VISITS (Daily 7-Visit Queue, Statuses, Feedback)
-- ============================================================
CREATE TABLE visits (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    location_id BIGINT UNSIGNED NOT NULL,
    scheduled_date DATE NOT NULL,
    
    -- Sequencing (1-7)
    sequence_order INT NOT NULL,
    
    -- Status Pipeline
    status ENUM('planned', 'in_progress', 'completed', 'missed', 'skipped') DEFAULT 'planned',
    source ENUM('auto_planned', 'coordinator_assigned', 'adhoc', 'presched_followup', 'carryforward') NOT NULL,
    
    -- Timing (Locked After Completion)
    planned_arrival_time TIME,
    actual_arrival_at TIMESTAMP,
    actual_completion_at TIMESTAMP,
    
    -- GPS Tracking (Locked)
    arrival_lat DECIMAL(10,8),
    arrival_lng DECIMAL(11,8),
    
    travel_time_minutes INT,
    onsite_time_minutes INT,
    
    -- Outcome Capture (Editable Until EOD)
    contact_person_name VARCHAR(255),
    contact_person_designation VARCHAR(255),
    contact_phone VARCHAR(20),
    visit_type VARCHAR(100),
    feedback_notes TEXT,
    photo_url VARCHAR(2048),
    
    -- Follow-Up Scheduling
    followup_date DATE,
    
    -- Missed Visit Handling
    carry_forward_attempt INT DEFAULT 1,
    missed_reason TEXT,
    missed_photo_url VARCHAR(2048),
    missed_reason_status ENUM('pending_review', 'approved', 'rejected') NULL,
    missed_reason_reviewed_by BIGINT UNSIGNED,
    missed_reason_reviewed_at TIMESTAMP,
    missed_reason_review_comment TEXT,
    
    -- Same-Day Edit Window (until 11:59:59 PM on scheduled_date)
    editable_until TIMESTAMP,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (location_id) REFERENCES locations(id),
    FOREIGN KEY (missed_reason_reviewed_by) REFERENCES users(id),
    INDEX idx_user_date (user_id, scheduled_date),
    INDEX idx_status (status),
    INDEX idx_location_id (location_id),
    INDEX idx_source (source),
    INDEX idx_missed_reason_status (missed_reason_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- PRODUCTS & SAMPLE REQUESTS (Inventory, Cash Tracking)
-- ============================================================
CREATE TABLE products (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    grade_level VARCHAR(50),
    subject VARCHAR(100),
    unit_price_pkr DECIMAL(10,2) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE sample_requests (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    visit_id BIGINT UNSIGNED,
    
    status ENUM('pending', 'approved', 'rejected', 'recovered') DEFAULT 'pending',
    total_pkr DECIMAL(12,2) NOT NULL,
    
    -- Approval Trail
    approved_by BIGINT UNSIGNED,
    approved_at TIMESTAMP,
    
    -- Recovery Reminders
    reminder_10_day_sent_at TIMESTAMP,
    reminder_20_day_sent_at TIMESTAMP,
    recovery_confirmed_at TIMESTAMP,
    
    -- Payroll Integration
    payroll_deduction_applied BOOLEAN DEFAULT FALSE,
    payroll_deduction_at TIMESTAMP,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (visit_id) REFERENCES visits(id) ON DELETE SET NULL,
    FOREIGN KEY (approved_by) REFERENCES users(id),
    INDEX idx_user_id (user_id),
    INDEX idx_status (status),
    INDEX idx_approved_at (approved_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE sample_request_items (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    sample_request_id BIGINT UNSIGNED NOT NULL,
    product_id BIGINT UNSIGNED NOT NULL,
    quantity INT NOT NULL,
    unit_price_pkr DECIMAL(10,2) NOT NULL,
    line_total_pkr DECIMAL(12,2) NOT NULL,
    
    FOREIGN KEY (sample_request_id) REFERENCES sample_requests(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- LEAVE REQUESTS (28-Day Cap: 10 Sick + 18 Casual)
-- ============================================================
CREATE TABLE leave_requests (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    leave_date DATE NOT NULL,
    leave_type ENUM('sick', 'casual') NOT NULL,
    
    status ENUM('pending', 'approved', 'rejected', 'auto_deducted') DEFAULT 'pending',
    reason TEXT,
    
    -- Approval
    approved_by BIGINT UNSIGNED,
    approved_at TIMESTAMP,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (approved_by) REFERENCES users(id),
    INDEX idx_user_date (user_id, leave_date),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- PAYROLL LEDGERS (Monthly Snapshots: Base + Deposit + Performance + Deductions)
-- ============================================================
CREATE TABLE payroll_ledgers (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    month_year DATE NOT NULL, -- First day of month
    
    -- Components
    base_salary_pkr DECIMAL(12,2) NOT NULL,
    security_deposit_withheld_pkr DECIMAL(12,2) NOT NULL,
    daily_performance_earned_pkr DECIMAL(12,2) DEFAULT 0,
    
    -- Deductions
    total_deductions_pkr DECIMAL(12,2) DEFAULT 0,
    deduction_reasons JSON, -- Array of reason strings
    
    -- Net
    net_payable_pkr DECIMAL(12,2) AS (
        base_salary_pkr + daily_performance_earned_pkr - total_deductions_pkr
    ) STORED,
    
    -- Audit
    calculated_by BIGINT UNSIGNED,
    calculated_at TIMESTAMP,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (calculated_by) REFERENCES users(id),
    UNIQUE KEY unique_user_month (user_id, month_year),
    INDEX idx_month_year (month_year)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- AUDIT LOGS (Append-Only, Admin Overrides)
-- ============================================================
CREATE TABLE audit_logs (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    admin_user_id BIGINT UNSIGNED NOT NULL,
    action_type VARCHAR(100) NOT NULL, -- e.g., 'missed_visit_override', 'password_reset'
    entity_type VARCHAR(100),
    entity_id BIGINT UNSIGNED,
    
    old_values JSON,
    new_values JSON,
    
    ip_address VARCHAR(45),
    user_agent TEXT,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (admin_user_id) REFERENCES users(id),
    INDEX idx_created_at (created_at),
    INDEX idx_action_type (action_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## 4. Complete API Endpoint Specification

### Base URL & Authentication

```
Base: /api/v1
Protocol: HTTPS (TLS 1.3)
Auth: Bearer Token (Laravel Sanctum)
Header: Authorization: Bearer {token}
```

### Standard Response Envelope

```json
{
  "success": true,
  "data": { ... },
  "message": "Operation successful",
  "timestamp": "2026-07-27T12:00:00Z"
}
```

### Error Responses

```json
{
  "success": false,
  "error": "VALIDATION_ERROR",
  "message": "Field validation failed",
  "details": {
    "phone": ["Phone number is invalid"]
  },
  "timestamp": "2026-07-27T12:00:00Z"
}
```

### 4.1 Authentication Endpoints

#### POST /api/v1/auth/login
**Public** - No token required

Request:
```json
{
  "phone": "03001234567",
  "password": "secure_password"
}
```

Response (201):
```json
{
  "success": true,
  "data": {
    "user": {
      "id": 123,
      "name": "Hassan Ahmed",
      "phone": "03001234567",
      "role": "sales_officer",
      "city": "Karachi",
      "area": "Gulshan-e-Iqbal",
      "leave_sick_balance": 10,
      "leave_casual_balance": 18,
      "annual_sample_limit_pkr": 300000.00,
      "annual_sample_used_pkr": 125000.00
    },
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```

Errors:
- `401` - Invalid credentials
- `403` - Account disabled

---

#### POST /api/v1/auth/forgot-password
**Public** - No token required

Request:
```json
{
  "phone": "03001234567"
}
```

Response (200):
```json
{
  "success": true,
  "message": "OTP sent to registered phone number"
}
```

---

#### POST /api/v1/auth/verify-otp
**Public** - No token required

Request:
```json
{
  "phone": "03001234567",
  "otp": "123456"
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "reset_token": "abc123xyz456..."
  }
}
```

---

#### POST /api/v1/auth/reset-password
**Public** - No token required

Request:
```json
{
  "reset_token": "abc123xyz456...",
  "new_password": "new_secure_password"
}
```

Response (200):
```json
{
  "success": true,
  "message": "Password reset successfully"
}
```

---

### 4.2 Workday Management

#### POST /api/v1/workday/day-start
**Protected** - Sales Officer

Request:
```json
{
  "latitude": 24.8607,
  "longitude": 67.0011
}
```

Response (201):
```json
{
  "success": true,
  "data": {
    "day_started_at": "2026-07-27T06:00:00Z",
    "visits_planned": 7
  }
}
```

Errors:
- `400` - Invalid GPS coordinates
- `409` - Day already started

---

#### POST /api/v1/workday/day-end
**Protected** - Sales Officer

Request:
```json
{
  "latitude": 24.8607,
  "longitude": 67.0011
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "day_ended_at": "2026-07-27T17:00:00Z",
    "total_time_hours": 11.0,
    "visits_completed": 6,
    "visits_missed": 1
  }
}
```

---

#### POST /api/v1/workday/cannot-work
**Protected** - Sales Officer

Request:
```json
{
  "reason": "Heavy rain and flooding in area"
}
```

Response (201):
```json
{
  "success": true,
  "data": {
    "recorded_at": "2026-07-27T06:30:00Z",
    "status": "cannot_work"
  }
}
```

---

### 4.3 Visit Execution

#### GET /api/v1/visits/today
**Protected** - Sales Officer

Response (200):
```json
{
  "success": true,
  "data": {
    "day_started": true,
    "visits": [
      {
        "id": 1,
        "sequence_order": 1,
        "location": {
          "id": 101,
          "name": "Gulshan Public School",
          "type": "school",
          "priority": "high",
          "latitude": 24.8607,
          "longitude": 67.0011,
          "address": "Block-13, Gulshan-e-Iqbal"
        },
        "status": "planned",
        "source": "auto_planned",
        "contact_person_name": null,
        "carry_forward_attempt": 1
      },
      ...6 more
    ]
  }
}
```

---

#### POST /api/v1/visits/{id}/start-navigation
**Protected** - Sales Officer

Request:
```json
{
  "latitude": 24.8607,
  "longitude": 67.0011
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "visit_id": 1,
    "status": "in_progress",
    "actual_arrival_at": "2026-07-27T09:15:00Z",
    "arrival_lat": 24.8607,
    "arrival_lng": 67.0011,
    "distance_meters": 450,
    "navigation_url": "https://maps.google.com/..."
  }
}
```

---

#### POST /api/v1/visits/{id}/complete
**Protected** - Sales Officer

Request:
```json
{
  "contact_person_name": "Ms. Fatima Khan",
  "contact_person_designation": "Principal",
  "contact_phone": "03005551234",
  "visit_type": "fresh_visit",
  "feedback_notes": "Discussed new O-Level mathematics series. Interested in bulk order.",
  "photo_url": "https://s3.amazonaws.com/...",
  "followup_date": "2026-08-10",
  "samples_distributed": [
    {
      "product_id": 5,
      "quantity": 10
    }
  ]
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "visit_id": 1,
    "status": "completed",
    "completion_time": "2026-07-27T09:45:00Z",
    "onsite_duration_minutes": 30,
    "editable_until": "2026-07-27T23:59:59Z"
  }
}
```

---

#### POST /api/v1/visits/{id}/mark-missed
**Protected** - Sales Officer

Request:
```json
{
  "reason": "School gates locked for assembly",
  "photo_url": "https://s3.amazonaws.com/..."
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "visit_id": 1,
    "status": "missed",
    "reason_submitted_at": "2026-07-27T10:30:00Z",
    "next_visit_scheduled": "2026-07-28",
    "carry_forward_attempt": 2
  }
}
```

---

#### PUT /api/v1/visits/{id}
**Protected** - Sales Officer (Same-day only)

Request (editable fields only):
```json
{
  "contact_person_name": "Dr. Ahmed Khan",
  "feedback_notes": "Updated discussion notes..."
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "visit_id": 1,
    "updated_fields": ["contact_person_name", "feedback_notes"],
    "editable_until": "2026-07-27T23:59:59Z"
  }
}
```

Errors:
- `410` - Edit window closed (past 11:59:59 PM on scheduled_date)

---

### 4.4 Real-Time Tracking

#### POST /api/v1/tracking/ping
**Protected** - Sales Officer (Background ping every 30 seconds)

Request:
```json
{
  "latitude": 24.8607,
  "longitude": 67.0011,
  "accuracy_meters": 8.5,
  "is_mock_location": false,
  "battery_percent": 72,
  "active_visit_id": 1
}
```

Response (204 No Content)

**Server-Side Actions:**
- Store in `gps_logs` table
- If `is_mock_location = true`: Log security alert, reject submission, notify admin
- Update `users.last_lat`, `last_lng`, `last_location_updated_at`
- Broadcast to admin dashboard WebSocket for live tracking

---

### 4.5 Admin Endpoints

#### GET /api/v1/admin/dashboard/stats
**Protected** - Admin, City Head

Response (200):
```json
{
  "success": true,
  "data": {
    "total_officers": 45,
    "officers_online_now": 38,
    "total_visits_today": 315,
    "visits_completed": 287,
    "visits_missed": 18,
    "visits_in_progress": 10,
    "pending_missed_approvals": 5,
    "pending_sample_approvals": 12,
    "total_samples_value_pending_pkr": 425000.00
  }
}
```

---

#### GET /api/v1/admin/visits/{id}/details
**Protected** - Admin, City Head

Response (200):
```json
{
  "success": true,
  "data": {
    "visit": {
      "id": 1,
      "officer_name": "Hassan Ahmed",
      "location_name": "Gulshan Public School",
      "scheduled_date": "2026-07-27",
      "sequence": 1,
      "status": "completed",
      "contact_person": "Ms. Fatima Khan",
      "contact_designation": "Principal",
      "contact_phone": "03005551234",
      "visit_type": "fresh_visit",
      "feedback": "Discussed new series...",
      "actual_arrival": "2026-07-27T09:15:00Z",
      "actual_completion": "2026-07-27T09:45:00Z",
      "travel_time_minutes": 15,
      "onsite_time_minutes": 30,
      "arrival_lat": 24.8607,
      "arrival_lng": 67.0011,
      "gps_breadcrumb_trail": [
        { "lat": 24.860, "lng": 67.001, "timestamp": "09:15:00", "is_mock": false },
        ...
      ],
      "photo_url": "https://s3.amazonaws.com/...",
      "samples_distributed": [
        { "product_name": "Math O-Level Vol 1", "quantity": 10, "value_pkr": 5000 }
      ]
    }
  }
}
```

---

#### POST /api/v1/admin/missed-visits/{id}/approve
**Protected** - City Head, Admin

Request:
```json
{
  "comment": "Approved - weather conditions verified"
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "visit_id": 1,
    "missed_reason_status": "approved",
    "approved_at": "2026-07-27T14:00:00Z"
  }
}
```

**Server-Side:**
- Set `missed_reason_status = 'approved'`
- NO salary deduction
- Audit log entry

---

#### POST /api/v1/admin/missed-visits/{id}/reject
**Protected** - City Head, Admin

Request:
```json
{
  "comment": "Insufficient reason - similar weather patterns reported elsewhere"
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "visit_id": 1,
    "missed_reason_status": "rejected",
    "rejected_at": "2026-07-27T14:05:00Z",
    "penalty_applied": {
      "daily_performance_pkr": -3000.00,
      "reason": "Missed visit (rejected reason)"
    }
  }
}
```

**Server-Side:**
- Set `missed_reason_status = 'rejected'`
- Deduct PKR 3,000 from monthly payroll ledger
- Add reason to `deduction_reasons` JSON array
- Audit log entry
- Queue notification to officer

---

#### POST /api/v1/admin/missed-visits/{id}/override
**Protected** - Super Admin Only

Request:
```json
{
  "decision": "approved",
  "comment": "Admin override - extenuating circumstances"
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "visit_id": 1,
    "missed_reason_status": "approved",
    "overridden_by": "super_admin",
    "overridden_at": "2026-07-27T15:00:00Z",
    "audit_log_id": 789
  }
}
```

**Server-Side:**
- Append to `audit_logs` table
- If changing from 'rejected' to 'approved': Remove deduction, update payroll ledger
- Send notification to City Head

---

#### GET /api/v1/admin/sample-requests/pending
**Protected** - City Head, Admin

Response (200):
```json
{
  "success": true,
  "data": {
    "requests": [
      {
        "id": 1,
        "officer_name": "Hassan Ahmed",
        "total_pkr": 25000.00,
        "status": "pending",
        "items": [
          { "product_name": "Math O-Level Vol 1", "quantity": 5, "unit_price": 5000 }
        ],
        "submitted_at": "2026-07-27T11:00:00Z"
      }
    ]
  }
}
```

---

#### POST /api/v1/admin/sample-requests/{id}/approve
**Protected** - City Head, Admin

Request:
```json
{
  "comment": "Approved - within limit"
}
```

Response (200):
```json
{
  "success": true,
  "data": {
    "request_id": 1,
    "status": "approved",
    "approved_at": "2026-07-27T12:00:00Z",
    "officer_balance_remaining_pkr": 275000.00
  }
}
```

**Server-Side:**
- Deduct total_pkr from user's `annual_sample_used_pkr`
- Set status = 'approved', approved_by, approved_at
- Initialize reminder timers (10, 20 days)

---

#### GET /api/v1/admin/live-positions
**Protected** - Admin, City Head (WebSocket or polling)

Response (200):
```json
{
  "success": true,
  "data": {
    "officers": [
      {
        "user_id": 123,
        "name": "Hassan Ahmed",
        "latitude": 24.8607,
        "longitude": 67.0011,
        "accuracy_meters": 8.5,
        "last_ping_at": "2026-07-27T12:05:30Z",
        "current_visit_id": 1,
        "current_visit_location": "Gulshan Public School",
        "battery_percent": 72,
        "status": "active_visit"
      }
    ]
  }
}
```

---

## 5. Automated System Scheduler (Laravel Crons)

### 5.1 Route Planning Engine (12:00 AM Daily)

**Command:** `php artisan schedule:run` (Laravel Scheduler)

**Trigger:** Every day at 00:00 UTC

**Algorithm:**

```plaintext
FOR EACH active sales_officer:
  
  STEP 1: Fetch coordinator-assigned visits for tomorrow
          Sort by priority, add first
          count = visits_count
          
  STEP 2: Fetch carry-forward missed visits (from yesterday/prior)
          Sort by attempt_count DESC
          Increment attempt counter
          Add up to (7 - count)
          count = visits_count
          
  STEP 3: Fetch pre-scheduled follow-up visits for tomorrow
          Add up to (7 - count)
          count = visits_count
          
  STEP 4: Pool from locations (schools, bookshops) in officer's area
          IF count < 7:
            High-priority schools: Add 2 (sorted by last_visit_date ASC)
            Medium-priority schools: Add 2 (sorted by last_visit_date ASC)
            Bookshops: Add 2 (sorted by last_visit_date ASC)
            
  STEP 5: Route Optimization (Nearest-Neighbor TSP)
          Start from officer's home/last known location
          Build sequence by selecting closest unvisited location
          Assign sequence_order (1-7)
          
  STEP 6: INSERT into visits table
          status = 'planned'
          sequence_order = 1..7
          
  END
```

**Pseudo-Code (Laravel):**

```php
// app/Console/Commands/GenerateDailyVisitsCommand.php

namespace App\Console\Commands;

use App\Models\User;
use App\Models\Visit;
use App\Models\Location;
use Carbon\Carbon;

class GenerateDailyVisitsCommand extends Command
{
    public function handle()
    {
        $tomorrow = Carbon::tomorrow();
        $officers = User::where('role', 'sales_officer')
                        ->where('is_active', true)
                        ->get();
        
        foreach ($officers as $officer) {
            $this->planDailyVisits($officer, $tomorrow);
        }
    }
    
    protected function planDailyVisits(User $officer, $date)
    {
        $queue = collect();
        
        // STEP 1: Coordinator visits
        $coordVisits = Visit::where('user_id', $officer->id)
                           ->where('scheduled_date', $date)
                           ->where('source', 'coordinator_assigned')
                           ->where('status', 'planned')
                           ->get();
        $queue = $queue->merge($coordVisits);
        
        // STEP 2: Carry-forward
        $carryForward = Visit::where('user_id', $officer->id)
                            ->where('status', 'missed')
                            ->where('scheduled_date', '<', $date)
                            ->orderBy('carry_forward_attempt', 'desc')
                            ->limit(7 - $queue->count())
                            ->get();
        foreach ($carryForward as $v) {
            $v->carry_forward_attempt++;
            $v->scheduled_date = $date;
            $v->status = 'planned';
            $v->save();
            $queue->push($v);
        }
        
        // STEP 3: Pre-scheduled
        if ($queue->count() < 7) {
            $preScheduled = Visit::where('user_id', $officer->id)
                                ->where('scheduled_date', $date)
                                ->where('source', 'presched_followup')
                                ->limit(7 - $queue->count())
                                ->get();
            $queue = $queue->merge($preScheduled);
        }
        
        // STEP 4: Pool fill
        if ($queue->count() < 7) {
            $remaining = 7 - $queue->count();
            $locations = $this->poolFromArea($officer->area_id, $remaining);
            foreach ($locations as $loc) {
                $visit = Visit::create([
                    'user_id' => $officer->id,
                    'location_id' => $loc->id,
                    'scheduled_date' => $date,
                    'status' => 'planned',
                    'source' => 'auto_planned',
                ]);
                $queue->push($visit);
            }
        }
        
        // STEP 5: Route optimization
        $optimizedQueue = $this->optimizeRoute($officer, $queue);
        
        // STEP 6: Assign sequence
        foreach ($optimizedQueue as $seq => $visit) {
            $visit->update(['sequence_order' => $seq + 1]);
        }
    }
    
    protected function poolFromArea($areaId, $count)
    {
        $result = collect();
        
        // High-priority schools (2)
        $highSchools = Location::where('area_id', $areaId)
                              ->where('type', 'school')
                              ->where('priority', 'high')
                              ->orderBy('last_visit_date', 'asc')
                              ->limit(2)
                              ->get();
        $result = $result->merge($highSchools);
        
        // Medium-priority schools (2)
        if ($result->count() < $count) {
            $medSchools = Location::where('area_id', $areaId)
                                 ->where('type', 'school')
                                 ->where('priority', 'medium')
                                 ->orderBy('last_visit_date', 'asc')
                                 ->limit(2)
                                 ->get();
            $result = $result->merge($medSchools);
        }
        
        // Bookshops (2)
        if ($result->count() < $count) {
            $shops = Location::where('area_id', $areaId)
                           ->where('type', 'bookshop')
                           ->orderBy('last_visit_date', 'asc')
                           ->limit(2)
                           ->get();
            $result = $result->merge($shops);
        }
        
        return $result;
    }
    
    protected function optimizeRoute(User $officer, $visits)
    {
        // Nearest-neighbor TSP
        $startLat = $officer->last_lat ?? 24.8607;
        $startLng = $officer->last_lng ?? 67.0011;
        
        $remaining = $visits->toArray();
        $optimized = [];
        
        while (!empty($remaining)) {
            $nearest = null;
            $minDist = PHP_INT_MAX;
            
            foreach ($remaining as $key => $visit) {
                $dist = $this->haversine(
                    $startLat, $startLng,
                    $visit->location->latitude,
                    $visit->location->longitude
                );
                if ($dist < $minDist) {
                    $minDist = $dist;
                    $nearest = $key;
                }
            }
            
            $optimized[] = $remaining[$nearest];
            $startLat = $remaining[$nearest]->location->latitude;
            $startLng = $remaining[$nearest]->location->longitude;
            unset($remaining[$nearest]);
        }
        
        return $optimized;
    }
    
    protected function haversine($lat1, $lng1, $lat2, $lng2)
    {
        $earthRadius = 6371000; // meters
        
        $dLat = deg2rad($lat2 - $lat1);
        $dLng = deg2rad($lng2 - $lng1);
        
        $a = sin($dLat / 2) ** 2 +
            cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * sin($dLng / 2) ** 2;
        $c = 2 * atan2(sqrt($a), sqrt(1 - $a));
        
        return $earthRadius * $c;
    }
}
```

---

### 5.2 Attendance Processing (11:00 PM Daily)

**Command:** `php artisan attendance:process`

**Trigger:** Every day at 23:00 UTC

**Logic:**

```plaintext
FOR EACH sales_officer WHERE is_active = true:
  
  today = TODAY
  attendance_rec = SELECT FROM attendance WHERE user_id = officer.id AND date = today
  
  IF attendance_rec NOT FOUND:
    # Officer didn't start or end day
    leave_rec = CREATE leave_request (
      user_id = officer.id,
      leave_date = today,
      leave_type = 'casual',  # Default to casual
      status = 'auto_deducted',
      reason = 'No day-start recorded'
    )
    
    IF officer.leave_casual_balance > 0:
      officer.leave_casual_balance -= 1
    ELSE IF officer.leave_sick_balance > 0:
      officer.leave_sick_balance -= 1
    # If both 0, balance goes negative (tracked but zero'd at next reset)
    
    SEND_NOTIFICATION(officer, "1 day auto-deducted from leave balance")
    
  ELSE IF attendance_rec.status = 'cannot_work':
    # Officer declared cannot work; no auto-deduction
    # Manual leave approval required if they want paid day off
    CONTINUE
    
  ELSE IF attendance_rec.day_start_at IS NULL:
    # Started day but no end; treat as present with partial day
    CONTINUE
    
  END
END
```

---

### 5.3 Sample Recovery Reminders (8:00 AM Daily)

**Command:** `php artisan samples:remind`

**Trigger:** Every day at 08:00 UTC

**Logic:**

```plaintext
FOR EACH sample_request WHERE status = 'approved' AND recovery_confirmed_at IS NULL:
  
  days_since_approval = TODAY - approved_at
  
  IF days_since_approval = 10 AND reminder_10_day_sent_at IS NULL:
    SEND_SMS(officer, "10 days since approval. Please confirm sample recovery or face payroll deduction.")
    reminder_10_day_sent_at = NOW()
    
  ELSE IF days_since_approval = 20 AND reminder_20_day_sent_at IS NULL:
    SEND_SMS(officer, "URGENT: 20 days. If not recovered by tomorrow, payroll deduction applies.")
    reminder_20_day_sent_at = NOW()
    
  ELSE IF days_since_approval >= 30 AND payroll_deduction_applied = false:
    # Apply to current month payroll
    payroll_rec = SELECT payroll_ledgers WHERE user_id = officer.id AND month_year = CURRENT_MONTH
    payroll_rec.total_deductions_pkr += sample_request.total_pkr
    payroll_rec.deduction_reasons.push("Unrecovered sample #" + sample_request.id)
    payroll_rec.save()
    
    sample_request.payroll_deduction_applied = true
    sample_request.payroll_deduction_at = NOW()
    sample_request.save()
    
    SEND_NOTIFICATION(officer, "Sample not recovered. Deducted from payroll: PKR " + total_pkr)
    
  END
END
```

---

### 5.4 End-of-Month Payroll Calculation (1st of Month, 00:30 UTC)

**Command:** `php artisan payroll:calculate`

**Trigger:** 1st day of month at 00:30 UTC

**Logic:**

```plaintext
prev_month = LAST_MONTH
prev_month_start = FIRST_DAY(prev_month)
prev_month_end = LAST_DAY(prev_month)

FOR EACH sales_officer:
  
  # Count working days present (exclude weekends/holidays)
  working_days_present = COUNT(attendance WHERE user_id = officer.id 
                                            AND status IN ('present', 'on_leave') 
                                            AND date BETWEEN prev_month_start AND prev_month_end)
  
  # Performance component: PKR 3,000 per working day present
  performance_earned = working_days_present * 3000
  
  # Deductions: Collect from rejected missed visits
  deductions = SUM(payroll_deduction_pkr FROM visit_outcomes 
                   WHERE officer_id AND missed_reason_status = 'rejected')
  
  # Existing deductions (samples, etc.)
  existing_deductions = SUM(total_deductions FROM payroll_ledgers 
                           WHERE officer_id AND month_year = prev_month)
  
  total_deductions = existing_deductions + deductions
  
  net_payable = (basic_salary + performance_earned) - total_deductions
  
  payroll_ledger = UPSERT payroll_ledgers (
    user_id = officer.id,
    month_year = prev_month,
    base_salary_pkr = officer.basic_salary,
    security_deposit_withheld_pkr = officer.security_deposit_monthly,
    daily_performance_earned_pkr = performance_earned,
    total_deductions_pkr = total_deductions,
    deduction_reasons = [collected reasons],
    calculated_by = SYSTEM_USER_ID,
    calculated_at = NOW()
  )
  
  # Security deposit: Accumulate until year-end
  # (Released on Dec 31 or termination)
  
END
```

---

## 6. Android (Kotlin) Safeguards & Architecture

### 6.1 Clean Architecture Layers

```
┌─────────────────────────────────────────────────┐
│           UI LAYER (Jetpack Compose)            │
│  • Activities, Screens, ViewModels              │
│  • State management with Flow<UiState>         │
│  • User interactions -> Events                  │
└──────────────────┬──────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────┐
│          DOMAIN LAYER (Use Cases)               │
│  • Business logic (offline rules, rules)        │
│  • Pure functions (no Android deps)            │
│  • Repositories interfaces                      │
└──────────────────┬──────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────┐
│  DATA LAYER (Repositories + Data Sources)       │
│  • Room DB (Local SQLite)                       │
│  • Retrofit (Remote API)                        │
│  • SharedPreferences (Session tokens)           │
│  • WorkManager (Offline sync queue)             │
└─────────────────────────────────────────────────┘
```

### 6.2 Anti-GPS Spoofing Enforcement

**Rule:** Every location ping MUST be validated.

```kotlin
// data/datasource/LocationDataSource.kt

class LocationDataSource(
    private val apiService: ApiService,
    private val fusedLocationClient: FusedLocationProviderClient
) {
    suspend fun sendLocationPing(
        latitude: Double,
        longitude: Double,
        accuracy: Float,
        batteryPercent: Int,
        activeVisitId: Long?
    ): Result<Unit> {
        val location = fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationToken
        ).await()
        
        // CRITICAL CHECK: Detect mock location
        val isMock = location?.isMock ?: false
        
        if (isMock) {
            // BLOCK immediately
            logSecurityIncident(
                userId = getCurrentUserId(),
                incidentType = "MOCK_LOCATION_DETECTED",
                latitude = latitude,
                longitude = longitude
            )
            
            // Notify user
            emit(UiEvent.Error("GPS spoofing detected. Location submission blocked."))
            
            // Notify backend
            try {
                apiService.reportSecurityIncident(
                    incident_type = "MOCK_LOCATION",
                    user_id = getCurrentUserId(),
                    timestamp = System.currentTimeMillis()
                )
            } catch (e: Exception) {
                // Retry on next sync
            }
            
            return Result.failure(IllegalStateException("Mock location detected"))
        }
        
        // Submit location
        return try {
            apiService.trackingPing(
                latitude = location.latitude,
                longitude = location.longitude,
                accuracy_meters = location.accuracy,
                is_mock_location = false,
                battery_percent = batteryPercent,
                active_visit_id = activeVisitId
            )
            Result.success(Unit)
        } catch (e: IOException) {
            // Network error; cache for sync later
            saveLocationPingToQueue(...)
            Result.success(Unit) // Don't fail UI; sync later
        }
    }
    
    private fun logSecurityIncident(...) {
        // Local append-only log
        db.securityLogDao().insert(
            SecurityLog(
                timestamp = System.currentTimeMillis(),
                incidentType = incidentType,
                details = "..."
            )
        )
    }
}
```

### 6.3 Offline-First Engine

```kotlin
// data/repository/VisitRepository.kt

class VisitRepository(
    private val roomDao: VisitDao,
    private val apiService: ApiService,
    private val syncQueue: SyncQueueManager
) : VisitRepositoryInterface {
    
    // Complete a visit locally first, sync later
    suspend fun completeVisit(
        visitId: Long,
        outcome: VisitOutcome
    ): Result<Unit> = withContext(Dispatchers.IO) {
        
        try {
            // Step 1: Try remote API
            val response = apiService.completeVisit(visitId, outcome)
            
            // Step 2: If success, update local
            roomDao.updateVisit(
                visitId,
                status = "completed",
                ...outcome fields...
            )
            
            Result.success(Unit)
            
        } catch (e: IOException) {
            // Step 1 (Alt): Network unavailable; save locally
            roomDao.updateVisit(
                visitId,
                status = "completed",
                syncStatus = "PENDING_SYNC", // Local flag
                ...outcome fields...
            )
            
            // Step 2: Queue for sync
            syncQueue.enqueue(
                SyncTask(
                    type = "COMPLETE_VISIT",
                    visitId = visitId,
                    payload = outcome,
                    createdAt = System.currentTimeMillis()
                )
            )
            
            // Step 3: WorkManager will retry when connected
            WorkManager.getInstance().enqueueUniqueWork(
                "visit_sync",
                ExistingWorkPolicy.KEEP,
                OneTimeWorkRequestBuilder<VisitSyncWorker>()
                    .setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()
                    )
                    .build()
            )
            
            Result.success(Unit) // UI succeeds; sync in background
        }
    }
}

// service/VisitSyncWorker.kt

class VisitSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val syncQueue = SyncQueueManager()
        val tasks = syncQueue.getAllPending()
        
        for (task in tasks) {
            try {
                when (task.type) {
                    "COMPLETE_VISIT" -> {
                        apiService.completeVisit(task.visitId, task.payload)
                        syncQueue.markSucceeded(task.id)
                    }
                }
            } catch (e: Exception) {
                if (runAttemptCount < 3) {
                    return@withContext Result.retry()
                } else {
                    // Mark as failed after 3 retries
                    syncQueue.markFailed(task.id, e.message)
                }
            }
        }
        
        return@withContext Result.success()
    }
}
```

### 6.4 Geofencing Proximity Lock

```kotlin
// domain/usecase/ValidateVisitProximityUseCase.kt

class ValidateVisitProximityUseCase(
    private val locationClient: FusedLocationProviderClient,
    private val visitRepository: VisitRepository
) {
    suspend operator fun invoke(visitId: Long): Result<ProximityStatus> {
        
        // Fetch visit location
        val visit = visitRepository.getVisit(visitId).getOrNull()
            ?: return Result.failure(NotFoundException())
        
        val targetLocation = visit.location
        
        // Get current location
        val currentLocation = try {
            locationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).await()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        
        // Calculate distance (haversine)
        val distanceMeters = calculateDistance(
            currentLocation.latitude,
            currentLocation.longitude,
            targetLocation.latitude,
            targetLocation.longitude
        )
        
        // Enforce 200-meter proximity
        return if (distanceMeters <= 200) {
            Result.success(ProximityStatus.WITHIN_RANGE)
        } else {
            Result.failure(ProximityViolation("$distanceMeters meters away. Come within 200m."))
        }
    }
    
    private fun calculateDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Float {
        val earthRadius = 6371000 // meters
        
        val dLat = Math.toRadians(lat2 - lat1)
        val dLng = Math.toRadians(lng2 - lng1)
        
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLng / 2).pow(2)
        
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        
        return (earthRadius * c).toFloat()
    }
}
```

---

## 7. Edge Cases & Defensive Engineering

### 7.1 Same-Day Edit Lock (Server-Side Enforcement)

```php
// app/Http/Controllers/VisitController.php

public function updateVisit(Request $request, $visitId)
{
    $visit = Visit::findOrFail($visitId);
    
    // CRITICAL: Verify edit window
    $now = Carbon::now();
    $editableUntil = $visit->scheduled_date
        ->copy()
        ->endOfDay(); // 23:59:59
    
    if ($now->isAfter($editableUntil)) {
        return response()->json([
            'success' => false,
            'error' => 'EDIT_WINDOW_CLOSED',
            'message' => 'Visit details can only be edited on the scheduled date.'
        ], 410); // 410 Gone
    }
    
    // Allow only editable fields
    $editableFields = ['contact_person_name', 'contact_person_designation', 'contact_phone', 'visit_type', 'feedback_notes'];
    $data = $request->only($editableFields);
    
    $visit->update($data);
    
    return response()->json([
        'success' => true,
        'data' => $visit
    ]);
}
```

### 7.2 Non-Retrievable Passwords

```php
// app/Http/Controllers/Admin/AdminController.php

// NEVER implement this:
public function viewUserPassword($userId) {
    // FORBIDDEN
}

// Instead, force password reset:
public function forcePasswordReset(Request $request, $userId)
{
    $user = User::findOrFail($userId);
    
    // Generate OTP
    $otp = random_int(100000, 999999);
    Cache::put("password_reset_otp:{$user->id}", $otp, now()->addMinutes(15));
    
    // Send OTP to user's phone
    SmsService::send($user->phone, "Your password reset OTP: $otp");
    
    // Log action
    AuditLog::create([
        'admin_user_id' => Auth::id(),
        'action_type' => 'force_password_reset',
        'entity_type' => 'user',
        'entity_id' => $userId,
        'new_values' => ['otp_generated' => true]
    ]);
    
    return response()->json([
        'success' => true,
        'message' => 'OTP sent to user. They must reset via forgot-password flow.'
    ]);
}
```

### 7.3 Audit Trails (Append-Only Logging)

```php
// app/Models/AuditLog.php

class AuditLog extends Model
{
    public $timestamps = false; // created_at only, immutable
    protected $fillable = [
        'admin_user_id', 'action_type', 'entity_type', 'entity_id',
        'old_values', 'new_values', 'ip_address', 'user_agent'
    ];
    
    protected $casts = [
        'old_values' => 'json',
        'new_values' => 'json',
        'created_at' => 'datetime'
    ];
}

// Example: Admin overrides missed visit
$auditLog = AuditLog::create([
    'admin_user_id' => Auth::id(),
    'action_type' => 'missed_visit_override',
    'entity_type' => 'visit',
    'entity_id' => $visitId,
    'old_values' => ['missed_reason_status' => 'rejected'],
    'new_values' => ['missed_reason_status' => 'approved'],
    'ip_address' => $request->ip(),
    'user_agent' => $request->header('User-Agent')
]);

// Audit logs can NEVER be deleted or modified (foreign key constraints ensure this)
```

---

## 8. Developer Onboarding & Maintainability Guide

### 8.1 15-Minute Setup Rule

#### Backend (Laravel 11)

```bash
# Clone repo
git clone https://github.com/bookmark/sfa-backend.git
cd sfa-backend

# Copy env
cp .env.example .env
php artisan key:generate

# Database setup
mysql -u root -pbookmark_dev -e "CREATE DATABASE bookmark_sfa_dev"

# Install
composer install
php artisan migrate --seed
php artisan storage:link

# Start
php artisan serve  # localhost:8000
```

#### Mobile (Android Kotlin)

```bash
# Clone repo
git clone https://github.com/bookmark/sfa-mobile.git

# Open Android Studio
open -a "Android Studio" sfa-mobile/

# Wait for Gradle sync
# Select Emulator: Pixel 7 API 34
# Click Run (green play button)
```

#### Admin (React + Vite)

```bash
# Clone repo
git clone https://github.com/bookmark/sfa-admin.git
cd sfa-admin

# Install
npm install

# Copy env
cp .env.example .env.local
# Edit .env.local: VITE_API_URL=http://localhost:8000

# Start
npm run dev  # localhost:5173
```

### 8.2 Standard Folder Structures

#### Backend (Laravel Service-Repository Pattern)

```
app/
├── Http/
│   ├── Controllers/
│   │   ├── AuthController.php
│   │   ├── VisitController.php
│   │   └── Admin/
│   │       └── AdminVisitController.php
│   └── Middleware/
│       └── RoleMiddleware.php
├── Services/
│   ├── VisitService.php           # Business logic
│   ├── PayrollService.php
│   └── SyncService.php
├── Repositories/
│   ├── VisitRepository.php        # Data access
│   └── UserRepository.php
├── Models/
│   ├── User.php
│   ├── Visit.php
│   └── ...
└── Console/
    └── Commands/
        ├── GenerateDailyVisitsCommand.php
        └── CalculatePayrollCommand.php
```

#### Mobile (Kotlin Clean Architecture)

```
com/bookmark/sfa/
├── ui/
│   ├── auth/
│   │   ├── LoginScreen.kt
│   │   ├── LoginViewModel.kt
│   │   └── LoginEvent.kt
│   ├── visits/
│   │   ├── VisitListScreen.kt
│   │   ├── VisitListViewModel.kt
│   │   └── ...
│   └── MainActivity.kt
├── domain/
│   ├── usecase/
│   │   ├── LoginUseCase.kt
│   │   ├── FetchVisitsUseCase.kt
│   │   └── ...
│   └── repository/ (interfaces)
│       ├── AuthRepository.kt
│       └── VisitRepository.kt
├── data/
│   ├── datasource/
│   │   ├── local/
│   │   │   └── VisitLocalDataSource.kt
│   │   └── remote/
│   │       └── VisitRemoteDataSource.kt
│   ├── repository/ (implementations)
│   │   ├── AuthRepositoryImpl.kt
│   │   └── VisitRepositoryImpl.kt
│   ├── db/
│   │   ├── AppDatabase.kt
│   │   └── dao/
│   │       ├── VisitDao.kt
│   │       └── ...
│   ├── api/
│   │   └── ApiService.kt
│   └── models/
│       ├── VisitDTO.kt
│       └── ...
└── di/
    └── AppModule.kt
```

#### Admin (React + Vite)

```
src/
├── components/
│   ├── Layout.tsx
│   ├── LoadingSpinner.tsx
│   └── ...
├── pages/
│   ├── auth/
│   │   └── LoginPage.tsx
│   ├── visits/
│   │   ├── VisitListPage.tsx
│   │   └── VisitDetailPage.tsx
│   └── admin/
│       ├── DashboardPage.tsx
│       └── ...
├── api/
│   └── client.ts              # Axios instance + endpoints
├── hooks/
│   ├── useAuth.ts
│   └── ...
├── types/
│   └── index.ts               # TypeScript interfaces
├── App.tsx
├── main.tsx
└── index.css
```

### 8.3 Golden Coding Rules

1. **ViewModels NEVER call Repositories directly**
   ```kotlin
   // BAD
   class MyViewModel {
       fun loadVisits() {
           val visits = visitRepository.fetchVisits()
       }
   }
   
   // GOOD
   class MyViewModel(
       private val fetchVisitsUseCase: FetchVisitsUseCase
   ) {
       fun loadVisits() {
           val visits = fetchVisitsUseCase()
       }
   }
   ```

2. **Controllers only validate & return JSON; business logic belongs in Services**
   ```php
   // BAD
   public function completeVisit(Request $request, $visitId) {
       $visit = Visit::find($visitId);
       $visit->status = 'completed';
       $visit->completed_at = now();
       // ... 50 lines of logic ...
   }
   
   // GOOD
   public function completeVisit(Request $request, $visitId) {
       $this->authorize('completeVisit', $visitId);
       $result = $this->visitService->completeVisit($visitId, $request->validated());
       return response()->json($result);
   }
   ```

3. **No hardcoded keys or API URLs**
   ```kotlin
   // BAD
   val apiUrl = "http://10.0.2.2:8000/api"
   
   // GOOD
   val apiUrl = BuildConfig.API_BASE_URL
   // In build.gradle.kts or local.properties
   ```

4. **All public endpoints require tests**
   ```php
   // routes/api.php defines endpoint
   // tests/Feature/VisitControllerTest.php tests it
   
   public function testCompleteVisit() {
       $officer = User::factory()->create(['role' => 'sales_officer']);
       $response = $this->actingAs($officer)
           ->postJson('/api/v1/visits/1/complete', [...]);
       $response->assertStatus(200);
   }
   ```

### 8.4 How to Add a Feature (4-Step Sequence)

**Example: Add "Mark As Revisit" button to visit**

**Step 1: Database**
```php
// database/migrations/xxxx_add_is_revisit_to_visits.php
Schema::table('visits', function (Blueprint $table) {
    $table->boolean('is_revisit')->default(false);
});
```

**Step 2: API Spec**
```
POST /api/v1/visits/{id}/mark-revisit
Request: { }
Response: { success: true, data: { visit_id, is_revisit: true } }
```

**Step 3: Backend Implementation**
```php
// app/Services/VisitService.php
public function markAsRevisit($visitId) {
    $visit = Visit::findOrFail($visitId);
    $visit->is_revisit = true;
    $visit->save();
    return $visit;
}

// app/Http/Controllers/VisitController.php
public function markAsRevisit($visitId) {
    $visit = $this->visitService->markAsRevisit($visitId);
    return response()->json(['success' => true, 'data' => $visit]);
}

// routes/api.php
Route::post('/visits/{id}/mark-revisit', [VisitController::class, 'markAsRevisit']);
```

**Step 4: Mobile UI**
```kotlin
// ui/visits/VisitDetailScreen.kt
Button(onClick = { viewModel.markAsRevisit() }) {
    Text("Mark As Revisit")
}

// viewmodel/VisitDetailViewModel.kt
fun markAsRevisit() = viewModelScope.launch {
    val result = markVisitAsRevisitUseCase(visitId)
    // Update UI state
}
```

---

## 9. AI Code Generation Rules (For Cursor / Claude)

When modifying this codebase, ALL AI agents (Cursor, Claude, Copilot) MUST follow:

### 9.1 Kotlin Rules
- Use immutable `StateFlow<UiState>` patterns; NO mutable state in ViewModels
- Use `@HiltViewModel` + constructor injection; NO manual DI
- Use sealed classes for `UiState` and `UiEvent`
- All network calls wrapped in `try-catch` with local fallback
- NEVER hardcode API URLs or BuildConfig values; use dependency injection

### 9.2 PHP/Laravel Rules
- NEVER execute unparameterized SQL queries; use Eloquent ORM or parameterized `DB::prepared()`
- ALL Controller methods must validate input via `Request::validate()`
- Use Service layer for business logic; Controllers are thin
- NEVER retrieve passwords; use `Hash::check()` for validation only
- ALWAYS log sensitive actions to `AuditLog` table
- NEVER modify audit logs; table has immutable constraints

### 9.3 React Rules
- Use `react-query` for all server state; NO useState for API data
- Component props must be typed with TypeScript interfaces
- NEVER call API directly from components; use custom hooks
- ALWAYS handle loading/error states
- Memoize expensive computations with `useMemo`

### 9.4 Cross-Platform Rules
- Preserve existing API contracts; NEVER rename JSON field names
- ALL timestamps in UTC ISO-8601 format
- Financial values as `DECIMAL(12,2)` or JS `number` (never `string`)
- GPS coordinates always 8 decimal places (lat) + 9 (lng)
- NEVER bypass auth middleware
- ALWAYS validate GPS `isMock` flag before storing

---

## Glossary

- **MVVM:** Model-View-ViewModel architectural pattern
- **Clean Architecture:** Separation of UI, Domain, and Data layers
- **Sanctum:** Laravel's lightweight API token authentication
- **Room DB:** Android's local SQLite ORM
- **WorkManager:** Android background task scheduler
- **Haversine:** Formula for calculating distance between GPS coordinates
- **TSP:** Traveling Salesman Problem (route optimization)
- **isMock:** Android flag indicating GPS spoofing detection
- **Geofencing:** GPS-based proximity validation
- **Payroll Ledger:** Monthly immutable salary snapshot
- **Carry-Forward:** Missed visit scheduled for following day with attempt counter

---

## Quick Links

- **Backend Repo:** `https://github.com/bookmark/sfa-backend`
- **Mobile Repo:** `https://github.com/bookmark/sfa-mobile`
- **Admin Repo:** `https://github.com/bookmark/sfa-admin`
- **BRD:** `Bookmark_BRD.md` (Requirements)
- **Setup Guide:** Follow Section 8.1 for 15-min setup

---

**Last Updated:** July 27, 2026 | **Status:** Production-Ready v1.0
