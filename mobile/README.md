# Bookmark SFA — Flutter Mobile App

## Setup

```bash
flutter pub get
flutter run
```

## Requirements
- Flutter 3.x (`flutter --version`)
- Android emulator or physical device
- Backend running at `http://localhost:3000` (or `http://10.0.2.2:3000` for Android emulator)

## Stack
- **Flutter 3.x + Dart 3.x**
- **Riverpod** — state management
- **Drift** — offline SQLite database
- **Dio** — HTTP client with JWT interceptor
- **go_router** — navigation
- **WorkManager** — background sync
- **geolocator** — GPS + mock detection
- **google_maps_flutter** — maps

## Key Architecture Rules
- Providers → UseCases → Repositories (never skip layers)
- All GPS actions check `MockLocationGuard` first
- Offline data stored with `PENDING_SYNC`, synced via WorkManager
- No hardcoded URLs — all in `lib/core/constants/api_constants.dart`
