# Bookmark SFA — Build & Deployment Guide

## Current Status (Aug 24, 2026)

✅ **Mobile App (Flutter)** — All UI/UX updated, ready to build
- Launcher icon: Red Bookmark logo
- Login screen: Compact, card-based, premium design
- Dashboard: Stats positioned below greeting, clean layout, no scroll bugs
- Color scheme: Red (#C8102E) + white + neutral gray
- Currency: Pakistani Rupees (Rs.)

✅ **Admin Dashboard (Next.js)** — Already deployed
- Red/white branding throughout
- Premium login page with brand gradient
- All KPI cards, charts, and tables use red accent
- Live-updating components (bookers, visits, locations, etc.)

---

## Build APK (Mobile App)

### Prerequisites
- Flutter SDK: `/Users/apple/develop/flutter/bin/flutter`
- Android SDK: Configured
- Emulator: Running (optional, for testing)

### Commands

```bash
# Navigate to mobile directory
cd /Users/apple/Documents/bookmark_field_force_manager/mobile

# Clean previous builds
/Users/apple/develop/flutter/bin/flutter clean

# Get dependencies
/Users/apple/develop/flutter/bin/flutter pub get

# Build release APK (split by ABI for smaller files)
/Users/apple/develop/flutter/bin/flutter build apk --release --split-per-abi
```

### APK Output Location

```
/Users/apple/Documents/bookmark_field_force_manager/mobile/build/app/outputs/flutter-apk/
```

**Files generated:**
- `app-armeabi-v7a-release.apk` (32-bit ARM)
- `app-arm64-v8a-release.apk` (64-bit ARM) — **Recommended**
- `app-x86_64-release.apk` (64-bit Intel)

**Copy to Documents:**
```bash
cp /Users/apple/Documents/bookmark_field_force_manager/mobile/build/app/outputs/flutter-apk/app-arm64-v8a-release.apk ~/Documents/bookmark-app-release.apk
```

---

## Test APK in Emulator

### Start Emulator (if not running)

```bash
# List available emulators
/Users/apple/Library/Android/sdk/emulator/emulator -list-avds

# Start Android 13 emulator (example)
/Users/apple/Library/Android/sdk/emulator/emulator -avd Pixel_4_API_33 &
```

### Install & Run APK

```bash
# Wait for emulator to boot (~30-60 seconds)
sleep 30

# Install APK
/Users/apple/Library/Android/sdk/platform-tools/adb install \
  /Users/apple/Documents/bookmark_field_force_manager/mobile/build/app/outputs/flutter-apk/app-arm64-v8a-release.apk

# Launch app
/Users/apple/Library/Android/sdk/platform-tools/adb shell am start -n com.bookmark.sfa/.MainActivity

# View logs
/Users/apple/Library/Android/sdk/platform-tools/adb logcat | grep "flutter"
```

---

## Admin Dashboard

### Status
- **Repository**: `hamidxvt/bookmark` (separate from mobile app)
- **Deployed on**: Railway
- **URL**: Check Railway dashboard for live URL
- **Branch**: `main`

### To update code

```bash
cd /Users/apple/Documents/bookmark_field_force_manager/_railway

# Make changes to files in src/

# Commit & push
git add -A
git commit -m "description of changes"
git push origin main

# Railway auto-deploys from main branch (~2-5 minutes)
```

---

## User Credentials (Test)

### Login (Both Mobile & Admin)
- **Email**: `officer@bookmark.pk`
- **Password**: `password`

OR use any officer account created in the admin panel.

---

## Recent Updates

### Mobile App
1. ✅ Fixed launcher icon — now shows red Bookmark logo
2. ✅ Redesigned login screen — compact 48x48 logo, card-based form
3. ✅ Moved stats to dashboard header (below greeting)
4. ✅ Changed rupee icon to `attach_money_rounded` with "Rs." format
5. ✅ Fixed scroll rendering issues — removed SliverAppBar
6. ✅ Modernized Gradle plugins (no deprecation warnings)
7. ✅ All UI uses red (#C8102E) + white theme

### Admin Dashboard
1. ✅ Brand colors: Bookmark Red (#C8102E) throughout
2. ✅ Login page: Premium gradient brand panel
3. ✅ KPI cards: Red icons & accent bars
4. ✅ Charts: Red chart lines (recharts)
5. ✅ Sidebar: Dark red background with red active states
6. ✅ All components: Tailwind + shadcn UI with red theme

---

## API Endpoints (Mobile)

**Base URL**: `http://your-backend-url/api`

### Auth
- `POST /mobile/auth/login` — Login with email/password
- `POST /mobile/auth/forgot-password` — Forgot password
- `POST /mobile/auth/reset-password` — Reset with OTP
- `PATCH /mobile/profile` — Update officer photo (base64)

### Visits
- `GET /mobile/visits` — List all visits
- `POST /mobile/visits/:id/complete` — Mark visit as completed

### Customers
- `GET /mobile/customers` — List customers
- `GET /mobile/customers/:id` — Customer details

### Leaves & Samples
- `GET /mobile/leaves` — List leaves
- `GET /mobile/samples` — List samples

---

## Troubleshooting

### APK not installing
```bash
# Uninstall previous version
adb uninstall com.bookmark.sfa

# Then reinstall
adb install app-arm64-v8a-release.apk
```

### App crashing on launch
- Check logs: `adb logcat | grep "flutter"`
- Ensure backend API is running
- Verify login credentials

### Launcher icon not updating
- Clear app cache: `adb shell pm clear com.bookmark.sfa`
- Reinstall APK
- May need to clear emulator data if cached

### Build failing
```bash
# Clean everything and rebuild
flutter clean
rm -rf build/
flutter pub get
flutter build apk --release --split-per-abi
```

---

## File Structure

```
/Users/apple/Documents/bookmark_field_force_manager/
├── mobile/                    # Flutter app
│   ├── lib/
│   │   ├── features/         # Feature modules
│   │   ├── core/theme/       # app_theme.dart (colors, spacing)
│   │   └── app.dart          # GoRouter setup
│   └── android/              # Android config
├── _railway/                  # Admin dashboard (Next.js)
│   ├── src/
│   │   ├── app/              # Pages
│   │   ├── components/       # React components
│   │   └── app/globals.css   # Tailwind + brand colors
│   └── public/               # Static assets (logo, favicon)
└── [other files]
```

---

## Next Steps

1. **Build APK** using the commands above
2. **Test in emulator** or real device
3. **Deploy admin** (auto via Railway)
4. **Share APK** from `~/Documents/bookmark-app-release.apk`

---

**Last Updated**: Aug 24, 2026  
**Status**: ✅ Production-ready (UI complete)
