# Bookmark Field Force Manager — Project Status

> Last updated: Aug 18, 2026

---

## ✅ WORKING

| Feature | Status | Notes |
|---|---|---|
| Officer login (mobile app) | ✅ Working | JWT auth via Railway backend |
| Day Start / Day End | ✅ Working | Records attendance |
| Live GPS tracking (admin) | ✅ Working | Auto-refreshes every 10s, OSM map |
| Background GPS service | ✅ Working | Pings every 30s even when app closed |
| Officer management (admin) | ✅ Working | Add / edit / delete / reset password |
| City assignment for officers | ✅ Working | Dropdown in edit modal |
| Cleanup endpoints | ✅ Working | `/api/v1/cleanup`, `/api/v1/cleanup-test-data` |
| Thana Malakand customers seeded | ✅ Working | 10 customers with GPS coords |
| Admin dashboard auth (NextAuth) | ✅ Working | Sheraz Ahmed Nagani admin account |
| Payroll screen (mobile) | ✅ Working | Salary breakdown per month |
| Profile screen (mobile) | ✅ Working | Visit stats, salary, reward points |
| Export Data (admin) | ✅ Working | CSV export for visits/bookers/customers |
| Notification bell (admin) | ✅ Working | Pending leaves + missed visits |
| City Management (admin) | ✅ Working | CRUD cities with geofence radius |

---

## 🔴 BROKEN / NOT WORKING

| Feature | Issue | Priority |
|---|---|---|
| Route map in app | Shows "0 visits · Today" — visits not planned for today | 🔴 High |
| Visits assigned wrong city | Scheduler fallback picks random far-away customers | 🔴 High |
| Launcher icon | Still shows old icon despite rebuild | 🟡 Medium |
| Route polyline on app map | No line drawn because 0 visits loaded | 🔴 High |

---

## 🟡 PARTIALLY WORKING

| Feature | Issue |
|---|---|
| Admin Live GPS map | Shows OSM correctly but only 1 officer (others offline) |
| Scheduler | Runs but creates visits with wrong customers |
| Customer data | 2637 customers migrated but most have no GPS coords |

---

## 📋 TODO / REMAINING FEATURES

### Immediate Fixes Needed
- [ ] **FIX: Scheduler strict city-only** — only assign customers from officer's own city (in progress)
- [ ] **FIX: Delete today's wrong visits** — clear bad visits so scheduler re-runs clean
- [ ] **FIX: Launcher icon** — run `flutter pub run flutter_launcher_icons` + uninstall + reinstall

### Route Optimization
- [ ] Route map shows 0 visits because visits aren't being created correctly
- [ ] Need to run scheduler AFTER fixing the strict city filter
- [ ] Officer must have GPS-coord customers in their city

### Data Quality
- [ ] Most 2637 migrated customers have no latitude/longitude
- [ ] Need to geocode customer addresses OR add GPS when visiting
- [ ] Current: only 10 Thana customers have GPS (manually seeded)

### Missing Features (Deferred by client)
- [ ] Leave application flow (removed — can restore if needed)
- [ ] Sample request management (removed — can restore if needed)
- [ ] Motivational quotes on day start (removed — can restore if needed)

### Admin Dashboard
- [ ] Visits page filtering by date/officer/status
- [ ] Push notifications (FCM) — backend ready, needs testing
- [ ] Reports page improvements

---

## 🗄️ Database

| Table | Records | Notes |
|---|---|---|
| cities | 3 | THANA MALAKAND, KARACHI, DEFAULT |
| bookers | 13 | All in Thana Malakand, all ACTIVE |
| customers | 2637 | Only 10 have GPS coords (Thana seed) |
| visits | Unknown | Need cleanup — wrong city assignments |

---

## 🔧 Key Commands

```bash
# Set emulator GPS to Thana Malakand
adb emu geo fix 72.0189 34.3512

# Start emulator
/Users/apple/Library/Android/sdk/emulator/emulator -avd BookmarkPhone -no-snapshot-load &

# Build APK
cd /Users/apple/Documents/bookmark_field_force_manager/mobile
flutter pub run flutter_launcher_icons
flutter build apk --release
adb uninstall com.bookmark.sfa
adb install build/app/outputs/flutter-apk/app-release.apk

# Database cleanup
curl -X POST https://bookmark-production-00c6.up.railway.app/api/v1/cleanup

# Run visit scheduler (plan today's visits)
curl -X POST "https://bookmark-production-00c6.up.railway.app/api/v1/scheduler?job=plan_visits"

# Delete today's visits and re-plan
curl -X DELETE "https://bookmark-production-00c6.up.railway.app/api/v1/visits?date=today"
curl -X POST "https://bookmark-production-00c6.up.railway.app/api/v1/scheduler?job=plan_visits"
```

---

## 🌐 URLs

| Service | URL |
|---|---|
| Admin Dashboard | https://bookmark-production-00c6.up.railway.app |
| API Base | https://bookmark-production-00c6.up.railway.app/api/v1 |
| Mobile API Base | https://bookmark-production-00c6.up.railway.app/api/mobile |
| GitHub (Admin) | https://github.com/hamidxvt/bookmark |
| GitHub (App) | https://github.com/hamidxvt/Bookmark-app |
