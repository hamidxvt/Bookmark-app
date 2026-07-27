# Bookmark Sales Force Automation (SFA)

Field sales visit planning, live tracking, sample control and performance-linked payroll system for Bookmark Publishing.

## Project Overview

Bookmark SFA is a complete rebuild of the sales force automation system comprising:

- Native Android mobile app (Kotlin, MVVM)
- REST API backend (Laravel 11, PHP 8.3)
- React admin web panel (React 19, Vite, Tailwind)
- MySQL 8 database

The system automates field visit planning, captures live GPS tracking, enforces sample distribution limits, and links compensation to performance metrics.

## Quick Start

### Requirements

- Java 17 (Android development)
- PHP 8.3 and Composer (Laravel)
- Node.js 20+ (React admin)
- MySQL 8
- Android Studio (emulator)

### Setup

```bash
# Run the setup script (after Homebrew installs complete)
bash setup.sh
```

This will:
1. Create Laravel database and run migrations
2. Install admin panel dependencies
3. Link storage for file uploads

### Running Development Servers

Terminal 1 - Backend API:
```bash
cd backend
php artisan serve
# API runs on http://localhost:8000/api
```

Terminal 2 - Admin Panel:
```bash
cd admin
npm run dev
# Panel runs on http://localhost:3000
```

Terminal 3 - Scheduler (for nightly jobs):
```bash
cd backend
php artisan schedule:work
```

Android Studio:
```bash
# Open mobile/ folder as Android Studio project
# Select Pixel 7 API 34 emulator
# Click Run (green play button)
# Mobile app connects to http://10.0.2.2:8000/api
```

## Project Structure

```
bookmark_field_force_manager/
├── backend/                    # Laravel 11 REST API
│   ├── app/
│   │   ├── Http/Controllers/   # Request handlers
│   │   │   ├── AuthController.php
│   │   │   ├── AttendanceController.php
│   │   │   ├── VisitController.php
│   │   │   ├── SampleController.php
│   │   │   ├── LeaveController.php
│   │   │   ├── PayrollController.php
│   │   │   ├── EngagementController.php
│   │   │   └── Admin/
│   │   │       ├── DashboardController.php
│   │   │       ├── OfficerController.php
│   │   │       ├── AdminVisitController.php
│   │   │       ├── MissedVisitController.php
│   │   │       ├── AdminSampleController.php
│   │   │       ├── AdminLeaveController.php
│   │   │       ├── AdminPayrollController.php
│   │   │       ├── InstitutionController.php
│   │   │       └── MasterDataController.php
│   │   ├── Models/             # Database models
│   │   │   ├── User.php
│   │   │   ├── Visit.php
│   │   │   ├── VisitOutcome.php
│   │   │   ├── Attendance.php
│   │   │   ├── SampleRequest.php
│   │   │   ├── SampleItem.php
│   │   │   ├── LeaveRequest.php
│   │   │   ├── PayrollLedger.php
│   │   │   ├── Institution.php
│   │   │   ├── City.php
│   │   │   ├── Area.php
│   │   │   ├── Product.php
│   │   │   └── DailyContent.php
│   │   ├── Console/Commands/   # Scheduled jobs
│   │   │   ├── GenerateDailyVisitsCommand.php
│   │   │   ├── ProcessAttendanceCommand.php
│   │   │   └── SendSampleRemindersCommand.php
│   │   └── Http/Middleware/
│   │       └── RoleMiddleware.php
│   ├── database/
│   │   ├── migrations/         # 7 migrations for all tables
│   │   └── seeders/
│   ├── routes/
│   │   ├── api.php             # REST endpoints
│   │   └── console.php         # Scheduled job timings
│   ├── composer.json
│   ├── .env.example
│   └── README.md
│
├── mobile/                     # Android Kotlin App
│   ├── app/src/main/
│   │   ├── java/com/bookmark/sfa/
│   │   │   ├── BookmarkApp.kt
│   │   │   ├── ui/
│   │   │   │   ├── auth/
│   │   │   │   │   ├── SplashActivity.kt
│   │   │   │   │   ├── LoginActivity.kt
│   │   │   │   │   ├── ForgotPasswordActivity.kt
│   │   │   │   │   ├── OtpVerificationActivity.kt
│   │   │   │   │   ├── ResetPasswordActivity.kt
│   │   │   │   │   └── AuthViewModel.kt
│   │   │   │   ├── home/
│   │   │   │   │   ├── HomeActivity.kt
│   │   │   │   │   └── HomeViewModel.kt
│   │   │   │   ├── visit/
│   │   │   │   │   ├── VisitAdapter.kt
│   │   │   │   │   ├── CheckInActivity.kt
│   │   │   │   │   ├── CompleteVisitActivity.kt
│   │   │   │   │   └── VisitViewModel.kt
│   │   │   │   ├── missed/
│   │   │   │   │   └── MissedVisitActivity.kt
│   │   │   │   ├── sample/
│   │   │   │   │   ├── SampleRequestActivity.kt
│   │   │   │   │   ├── SampleViewModel.kt
│   │   │   │   │   └── SampleProductAdapter.kt
│   │   │   │   ├── leave/
│   │   │   │   │   ├── LeaveActivity.kt
│   │   │   │   │   └── LeaveViewModel.kt
│   │   │   │   ├── earnings/
│   │   │   │   │   ├── EarningsActivity.kt
│   │   │   │   │   └── EarningsViewModel.kt
│   │   │   │   └── attendance/
│   │   │   │       └── AttendanceViewModel.kt
│   │   │   ├── data/
│   │   │   │   ├── api/
│   │   │   │   │   ├── ApiService.kt
│   │   │   │   │   └── ApiClient.kt
│   │   │   │   ├── models/
│   │   │   │   │   └── Models.kt
│   │   │   │   └── local/
│   │   │   │       └── SessionManager.kt
│   │   │   ├── service/
│   │   │   │   └── LocationTrackingService.kt
│   │   │   ├── di/
│   │   │   │   └── AppModule.kt
│   │   │   └── utils/
│   │   │       ├── Extensions.kt
│   │   │       └── LocationHelper.kt
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_login.xml
│   │   │   │   ├── activity_home.xml
│   │   │   │   ├── item_visit.xml
│   │   │   │   ├── activity_complete_visit.xml
│   │   │   │   └── (other layouts)
│   │   │   ├── values/
│   │   │   │   ├── strings.xml
│   │   │   │   ├── colors.xml
│   │   │   │   └── themes.xml
│   │   │   └── drawable/
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   ├── app/build.gradle.kts
│   ├── settings.gradle.kts
│   ├── gradle/
│   │   ├── libs.versions.toml
│   │   ├── wrapper/
│   │   │   └── gradle-wrapper.properties
│   └── gradle.properties
│
├── admin/                      # React Vite Admin Panel
│   ├── src/
│   │   ├── api/
│   │   │   └── client.ts       # Axios instance & all endpoints
│   │   ├── components/
│   │   │   └── Layout.tsx      # Sidebar + routing layout
│   │   ├── pages/
│   │   │   ├── auth/
│   │   │   │   └── LoginPage.tsx
│   │   │   ├── DashboardPage.tsx
│   │   │   ├── visits/
│   │   │   │   ├── VisitsPage.tsx
│   │   │   │   ├── VisitDetailModal.tsx
│   │   │   │   └── MissedVisitsPage.tsx
│   │   │   ├── officers/
│   │   │   │   ├── OfficersPage.tsx
│   │   │   │   └── OfficerFormModal.tsx
│   │   │   ├── samples/
│   │   │   │   └── SamplesPage.tsx
│   │   │   ├── leaves/
│   │   │   │   └── LeavesPage.tsx
│   │   │   ├── payroll/
│   │   │   │   └── PayrollPage.tsx
│   │   │   ├── institutions/
│   │   │   │   └── InstitutionsPage.tsx
│   │   │   └── LiveMapPage.tsx
│   │   ├── hooks/
│   │   │   └── useAuth.ts      # Zustand auth store
│   │   ├── types/
│   │   │   └── index.ts        # TypeScript interfaces
│   │   ├── App.tsx
│   │   ├── main.tsx
│   │   └── index.css
│   ├── vite.config.ts
│   ├── tsconfig.json
│   ├── package.json
│   └── index.html
│
├── setup.sh                    # Post-install automation script
├── .gitignore
└── README.md (this file)
```

## Database Schema

Seven migrations create:

1. **Cities & Areas** - Geographic hierarchy and officer area assignment
2. **Users** - Officers, coordinators, city heads, admin with role-based access
3. **Institutions** - Schools and bookshops with priority, location, and visit history
4. **Visits** - Daily visit plans with GPS, time tracking, and outcome data
5. **Attendance** - Day start/end times and cannot-work declarations
6. **Products & Samples** - Product catalog and sample request workflow
7. **Leaves & Payroll** - Leave applications and salary component ledgers

All timestamps, GPS coordinates, and IDs are properly indexed and locked after completion.

## API Endpoints

**Authentication**
- POST /api/auth/login
- POST /api/auth/forgot-password
- POST /api/auth/verify-otp
- POST /api/auth/reset-password
- POST /api/auth/change-password

**Field Operations (Officer)**
- GET /api/profile
- POST /api/attendance/start-day
- POST /api/attendance/end-day
- POST /api/attendance/cannot-work
- GET /api/visits/today
- POST /api/visits/{id}/checkin
- POST /api/visits/{id}/checkin-photo
- PUT /api/visits/{id}/outcome
- POST /api/visits/{id}/miss
- POST /api/location/update
- GET /api/samples/products
- POST /api/samples/request
- POST /api/leaves/apply
- GET /api/payroll/my-earnings

**Admin Panel**
- GET /api/admin/dashboard/stats
- GET/POST /api/admin/officers
- POST /api/admin/officers/{id}/reset-password
- GET /api/admin/officers/live-positions
- GET/POST /api/admin/visits
- GET /api/admin/missed-visits/pending
- POST /api/admin/missed-visits/{id}/approve
- POST /api/admin/missed-visits/{id}/reject
- POST /api/admin/missed-visits/{id}/override
- GET /api/admin/samples/requests
- GET /api/admin/samples/ledger
- GET /api/admin/leaves
- GET /api/admin/payroll/ledger
- GET /api/admin/institutions/{id}/visit-history

## Scheduled Jobs (Laravel Scheduler)

**Midnight (00:00)**
- `GenerateDailyVisitsCommand` - Builds 7-visit plan for all officers
- Route optimizes using nearest-neighbor algorithm
- Applies standard daily mix: 1 coordinator + 2 high-priority schools + 2 medium schools + 2 bookshops

**11:00 PM**
- `ProcessAttendanceCommand` - Auto-deducts leave for officers with no Day Start

**8:00 AM**
- `SendSampleRemindersCommand` - Sends 10/20-day reminders, triggers 30-day payroll deduction

## Security

- JWT tokens via Laravel Sanctum
- Role-based access control (RBAC) on all endpoints
- GPS data locked after visit completion (uneditable)
- Visit text editable same-day only
- Auto-blocks mock location / GPS spoofing apps (Android)
- Passwords hashed with bcrypt
- Full audit trail of all actions

## Tech Stack Summary

| Layer | Tech | Purpose |
|-------|------|---------|
| Mobile | Kotlin, MVVM, Retrofit, Hilt | Native Android app with dependency injection |
| Backend | Laravel 11, PHP 8.3, MySQL 8 | REST API with scheduler |
| Admin | React 19, Vite, Tailwind, React Query | Real-time dashboard |
| Maps | Google Maps API | Navigation & live tracking |
| Location | Play Services Location | GPS capture & tracking |
| Storage | File storage (local/cloud) | Photo uploads |
| Auth | Laravel Sanctum | Token-based auth |

## Development Workflow

1. Pull latest from main
2. Create feature branch: `git checkout -b feature/description`
3. Make changes in respective module (backend, mobile, or admin)
4. Test locally on all three platforms
5. Commit with clear messages
6. Push and open pull request
7. Merge after review

## Known Limitations & Open Items

- Master data (schools/shops) must be seeded manually
- Google Maps API key required in AndroidManifest.xml
- Play Store account needed for production deployment
- Offline sync has limits (design pending)
- Daily edit cutoff exact time configurable via .env
- Maximum carry-forward attempt count configurable

## Performance Notes

- Mobile app optimized for mid-range Android devices
- Battery usage minimized through efficient GPS polling
- Admin panel uses React Query for caching
- Backend pagination on reports (50 records/page default)
- Database indexes on all lookup fields

## Future Phases

Phase 2 roadmap includes:

- Desktop productivity monitoring
- Automated payroll disbursement
- Paperless accounting & invoicing
- Email marketing campaigns
- AI chatbot for company data

## Support & Maintenance

All code follows clean architecture principles with clear separation of concerns:

- Controllers handle HTTP
- Models represent data
- ViewModels manage state
- Services handle business logic
- Repositories abstract data access

## License

Confidential - Bookmark Publishing Pvt. Ltd.

## Contact

Xvantech Development Team
