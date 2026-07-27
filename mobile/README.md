# Bookmark SFA - Mobile App

Native Android app built in Kotlin with MVVM architecture, Hilt dependency injection, and Retrofit for API calls.

## Setup

```bash
cd mobile

# Open in Android Studio
open -a "Android Studio" .

# Or via command line
android-studio .
```

Wait for Gradle sync to complete.

## Running

1. Open Android Studio
2. Select Pixel 7 API 34 from device menu
3. Click Run (green play button)
4. App installs on emulator

Emulator tip: Inside emulator, localhost maps to 10.0.2.2

## Architecture

MVVM with clean separation:

- **UI Layer** - Activities and Adapters
- **ViewModel Layer** - State management with LiveData
- **Data Layer** - API client, local storage, models

## Key Files

**Activities** (app/src/main/java/com/bookmark/sfa/ui/)

- SplashActivity - Redirect based on login state
- LoginActivity - Credential entry
- HomeActivity - Daily visit list and navigation
- CheckInActivity - 3-step GPS validation + photo
- CompleteVisitActivity - Visit outcome capture
- MissedVisitActivity - Photo + reason submission
- SampleRequestActivity - Sample shopping cart
- LeaveActivity - Leave application
- EarningsActivity - Salary breakdown

**ViewModels** (app/src/main/java/com/bookmark/sfa/viewmodel/)

- AuthViewModel - Login flow
- HomeViewModel - Daily visit fetch
- AttendanceViewModel - Day start/end
- VisitViewModel - Visit execution and GPS
- SampleViewModel - Sample requests
- LeaveViewModel - Leave applications
- EarningsViewModel - Payroll view

**Services** (app/src/main/java/com/bookmark/sfa/service/)

- LocationTrackingService - Continuous GPS background tracking

**API** (app/src/main/java/com/bookmark/sfa/data/api/)

- ApiService - Retrofit interface with all endpoints
- ApiClient - Retrofit builder with interceptors and auth headers

**Models** (app/src/main/java/com/bookmark/sfa/data/models/)

- Request/response DTOs
- Domain models with @Parcelize for navigation

**Local Storage** (app/src/main/java/com/bookmark/sfa/data/local/)

- SessionManager - DataStore for tokens and user info

**DI** (app/src/main/java/com/bookmark/sfa/di/)

- AppModule - Hilt bindings

## Dependencies

See app/build.gradle.kts for versions. Main:

- Kotlin 2.0.0
- AndroidX Core, AppCompat, Constraint Layout
- Material Components 1.12.0
- Lifecycle, Navigation, ViewModel, LiveData
- Hilt 2.51.1 for dependency injection
- Retrofit 2.11.0 for HTTP
- OkHttp 4.12.0 with logging interceptor
- Google Play Services (Maps, Location)
- Datastore Preferences for local storage
- Room for potential offline caching

## Configuration

API base URL in BuildConfig (set during build):

```kotlin
// Debug
http://10.0.2.2:8000/api/

// Release
https://api.bookmark.services/api/
```

Google Maps API key in AndroidManifest.xml:

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="@string/google_maps_key" />
```

## Permissions (AndroidManifest.xml)

- INTERNET - API calls
- ACCESS_FINE_LOCATION - GPS for visits
- ACCESS_COARSE_LOCATION - Network-based location fallback
- ACCESS_BACKGROUND_LOCATION - Background tracking during visits
- CAMERA - Photo uploads for missed visits
- READ_MEDIA_IMAGES - Photo selection
- POST_NOTIFICATIONS - Push notifications
- RECEIVE_BOOT_COMPLETED - Auto-start scheduler

## Build & Run

```bash
# Debug build
./gradlew assembleDebug

# Build and install on emulator
./gradlew installDebug

# Clean build
./gradlew clean build
```

## Testing on Device

1. Enable USB Debugging on Android phone
2. Connect via USB
3. Android Studio auto-detects device
4. Run app (installs on phone)

## Key Features

- 3-step GPS check-in wizard with proximity validation
- Photo uploads for missed visits
- Google Maps navigation intent
- Background location tracking in foreground service
- Mock GPS detection and blocking
- Same-day outcome editing (locked after midnight)
- Ad-hoc visit creation
- Offline-capable with sync queue

## Notes

- All timestamps in UTC
- GPS coordinates 7 decimal places (10m precision)
- Files stored in app cache first, then backend
- SessionManager persists token across restarts
- ViewModels survive config changes (rotation)
- Material Design components for UI consistency
