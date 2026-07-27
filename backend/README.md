# Bookmark SFA - Backend API

Laravel 11 REST API with MySQL database, role-based access control, and scheduled jobs.

## Setup

```bash
cd backend

# Copy environment
cp .env.example .env

# Generate app key
php artisan key:generate

# Create database
mysql -u root -pbookmark_dev -e "CREATE DATABASE bookmark_sfa CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# Run migrations
php artisan migrate

# Install Sanctum
php artisan vendor:publish --provider="Laravel\Sanctum\SanctumServiceProvider" --force

# Link storage
php artisan storage:link

# Start server
php artisan serve
```

API runs on http://localhost:8000/api

## Environment Variables

```
APP_NAME=Bookmark SFA
APP_ENV=local
APP_KEY=(auto-generated)
APP_DEBUG=true
APP_URL=http://localhost:8000

DB_CONNECTION=mysql
DB_HOST=127.0.0.1
DB_DATABASE=bookmark_sfa
DB_USERNAME=root
DB_PASSWORD=bookmark_dev

SANCTUM_STATEFUL_DOMAINS=localhost:3000
```

## Scheduled Jobs

Run scheduler in separate terminal:

```bash
php artisan schedule:work
```

Jobs execute on schedule:
- 00:00 - Generate daily visit plans
- 23:00 - Process attendance and auto-deduct leave
- 08:00 - Send sample reminders

## Migrations

Seven migrations in database/migrations/:

1. Cities and Areas
2. Users (all roles)
3. Institutions (schools/shops)
4. Visits and Outcomes
5. Attendance
6. Products and Samples
7. Leaves and Payroll

## Controllers

All controllers in app/Http/Controllers/:

**Non-Admin**
- AuthController - Login, password reset, profile
- AttendanceController - Day start/end, cannot-work
- VisitController - Visit CRUD, GPS tracking, outcomes
- LocationController - Continuous GPS updates
- SampleController - Sample requests, recovery
- LeaveController - Leave applications
- PayrollController - Earnings visibility
- EngagementController - Daily quotes and tips

**Admin**
- DashboardController - Stats
- OfficerController - Officer management, live positions
- AdminVisitController - Visit oversight
- MissedVisitController - Missed visit approval/override
- AdminSampleController - Sample approval ledger
- AdminLeaveController - Leave approval
- AdminPayrollController - Salary ledger
- InstitutionController - School/shop history
- MasterDataController - Cities, areas, products

## Models

All models in app/Models/:

- User
- Visit
- VisitOutcome
- Attendance
- SampleRequest
- SampleItem
- LeaveRequest
- PayrollLedger
- Institution
- City
- Area
- Product
- DailyContent

## Routes

All routes in routes/api.php with auth middleware and role checking.

Public routes:
- POST /api/auth/login
- POST /api/auth/forgot-password

Protected routes require bearer token.

Admin routes require role middleware check.

## Middleware

RoleMiddleware in app/Http/Middleware/ enforces RBAC.

## Commands

Artisan commands in app/Console/Commands/:

1. GenerateDailyVisitsCommand - Nightly visit planning with route optimization
2. ProcessAttendanceCommand - Auto-deduct leave for missed day start
3. SendSampleRemindersCommand - Sample reminders and payroll deduction

## Dependencies

See composer.json for all packages. Main:

- laravel/framework ^11.0
- laravel/sanctum ^4.0
- guzzlehttp/guzzle ^7.9

## Testing Locally

Use Postman or curl:

```bash
# Login
curl -X POST http://localhost:8000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"phone":"03001234567","password":"password"}'

# Get profile (use token from login)
curl -X GET http://localhost:8000/api/profile \
  -H "Authorization: Bearer TOKEN_HERE"
```

## Notes

- All endpoints return JSON
- Timestamps in UTC
- Lat/lng stored with 8 decimal precision
- PKR values as decimal(12,2)
- Full audit trail via timestamps and user_id
