# Bookmark Field Force Manager — Instructions

## Project Structure

```
bookmark_field_force_manager/
├── _railway/        ← ADMIN DASHBOARD (Next.js) → GitHub: hamidxvt/bookmark
├── mobile/          ← MOBILE APP (Flutter)       → GitHub: hamidxvt/Bookmark-app
├── data/            ← Source XLSX files (local only, never pushed)
└── INSTRUCTIONS.md  ← This file
```

---

## 1. Admin Dashboard (`_railway/`)

**What it is:** Next.js 14 app with Prisma ORM + PostgreSQL. Deployed on Railway.

**GitHub Repo:** `https://github.com/hamidxvt/bookmark`

**How to push changes:**
```bash
cd _railway
git add -A
git commit -m "your message"
git push origin main
```

**Tech Stack:**
- Framework: Next.js 14 (App Router)
- Database: PostgreSQL via Prisma ORM
- Styling: Tailwind CSS
- Auth: NextAuth.js
- Deployment: Railway (auto-deploys on push to main)

**Key directories inside `_railway/`:**
```
src/
├── app/
│   ├── (auth)/login/       ← Login page
│   ├── (dashboard)/        ← All admin pages (customers, visits, etc.)
│   └── api/v1/             ← Backend API routes
├── components/
│   ├── layout/             ← Sidebar, Header
│   ├── customers/          ← Customer list & forms
│   ├── visits/             ← Visits list
│   └── ...                 ← Other feature components
├── lib/
│   ├── prisma.ts           ← Database client
│   └── scheduler.ts        ← Cron job logic
└── data/
    ├── seed-customers.json ← 7,406 customers from XLSX (seeded once)
    └── seed-products.json  ← 461 products from XLSX (seeded once)

prisma/
└── schema.prisma           ← Database schema
```

**⚠️ IMPORTANT — Never edit:**
- `prisma/schema.prisma` without running `npx prisma db push` after
- `src/data/seed-*.json` manually — regenerate using the Python script in root

---

## 2. Mobile App (`mobile/`)

**What it is:** Flutter app for field officers (Android/iOS).

**GitHub Repo:** `https://github.com/hamidxvt/Bookmark-app`

**How to push changes:**
```bash
cd mobile
git add -A
git commit -m "your message"
git push origin main
```

**Tech Stack:**
- Framework: Flutter (Dart)
- State Management: Riverpod (Notifier / AsyncNotifier)
- Navigation: GoRouter
- HTTP: Dio
- Storage: flutter_secure_storage (JWT tokens)
- Maps: Google Maps / url_launcher

**Key directories inside `mobile/`:**
```
lib/
├── core/
│   ├── theme/              ← AppColors, AppSpacing, AppRadius, AppTheme
│   ├── network/            ← DioClient (auto-attaches JWT)
│   └── router/             ← GoRouter app.dart
└── features/
    ├── auth/               ← Login, OTP, profile
    ├── visits/             ← Visit scheduling & check-in
    ├── customers/          ← Customer list & add
    ├── attendance/         ← Day start/end
    ├── samples/            ← Sample management
    └── notifications/      ← Push notifications
```

**To build APK:**
```bash
cd mobile
flutter build apk --release
# APK: build/app/outputs/flutter-apk/app-release.apk
```

**To install on emulator:**
```bash
flutter emulators --launch <emulator_id>
flutter run
```

---

## Data Seeding

The XLSX data (customers + products) is seeded **once** into the Railway PostgreSQL database.
- After the first successful seed, data lives permanently in the DB.
- The seed re-runs automatically on server restart **only if** fewer than 10 `A+` customers exist.
- To manually re-trigger: go to `/data-import` in the admin dashboard and click "Import Everything".

**Category mapping (applied during seed):**
| XLSX Category | Database Category |
|---------------|-------------------|
| TYPE - A      | A+                |
| TYPE - B      | A                 |
| TYPE - C      | B                 |
| BOOKSHOPS     | BOOKSHOPS         |

---

## Common Mistakes to Avoid

| ❌ Wrong | ✅ Correct |
|----------|------------|
| `cd _railway && git push` to Bookmark-app | `cd _railway && git push` to bookmark |
| `cd mobile && git push` to bookmark | `cd mobile && git push` to Bookmark-app |
| Editing seed JSON files directly | Re-run the Python conversion script |
| Pushing schema changes without db push | Always run `npx prisma db push` in `_railway/` |

---

## Environment Variables (Railway)

Set these in Railway → Your Project → Variables:
```
DATABASE_URL        = postgresql://...   (auto-set by Railway Postgres plugin)
NEXTAUTH_SECRET     = any-random-string
NEXTAUTH_URL        = https://your-app.railway.app
GOOGLE_MAPS_API_KEY = (optional, for advanced tracking)
```

---

*Last updated: Aug 2026*
