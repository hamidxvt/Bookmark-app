# Bookmark Field Force Manager — APK & Web App Analysis

## Overview

**App Name:** Bookmark (Field Force Manager / Sales Force Automation)
**Developer:** Ingenious (Pakistan) — info@bookmark.com.pk
**Website:** https://staging.bookmark.services
**APK Package:** `com.ingenious.androidbookmarksalesupgrade`

---

## APK (Mobile App) Details

| Property | Value |
|---|---|
| Package | `com.ingenious.androidbookmarksalesupgrade` |
| Min SDK | 16 (Android 4.1) |
| Target SDK | 34 (Android 14) |
| Compile SDK | 34 |
| Version | 1.0 |
| Main Activity | `SplashActivity` |
| Firebase API Key | `AIzaSyCu9FtXODUtGU8j4sLeh7Fdjsr0M4GyLcE` |

### Firebase / Google Services
- Firebase Cloud Messaging (push notifications)
- Firebase Analytics
- Firebase Installations
- Google Maps API
- Google Places API
- Google Ads Services (Ad ID, Attribution)

### Key APK Screens (Activities)

| Activity | Purpose |
|---|---|
| `SplashActivity` | App entry / splash screen |
| `LoginActivity` | Login with email/password or OTP |
| `HomeActivity` | Dashboard |
| `AddVisitActivity` | Create a new visit |
| `CheckInActivity` | Geo-check-in at customer location |
| `CompleteVisitActivity` | Mark visit as complete |
| `VisitDetailsActivity` | View visit history/details |
| `VisitAdoptionActivity` | Visit adoption workflow |
| `AddCustomerActivity` | Add new customer |
| `AllProductsActivity` | Browse product catalog |
| `LowStockActivity` | Low stock alerts |
| `CreateRequestActivity` | Submit support requests |
| `RequestDashboard` | View request status |
| `RequestDetailActivity` | Request details |
| `ChatActivity` | In-app chat |
| `LocationActivity` | Location management |
| `ProfileActivity` | User profile |
| `OTPVerificationActivity` | Phone OTP verification |
| `NotificationScreenAct` | Notification list |
| `NotificationDisplayAct` | Notification detail |
| `ForgetPasswordActivity` | Password reset |
| `ResetPasswordActivity` | Set new password |
| `PrivacyPolicyAct` | Privacy policy |
| `HelpScreenAct` | Help/support |
| `SettingScreenAct` | App settings |
| `QuickScreen` | Quick actions |
| `ContactAct` | Contact list |
| `RefillRequestsActivity` | Refill/reorder requests |

### Background Services
- `LocationService` — Foreground service for GPS tracking during visits
- `FirebaseMessagingService` — Push notification handling
- `FirebaseInstanceIDService` — FCM token management
- `SystemJobService` — WorkManager background jobs
- `SystemAlarmService` — Alarm-based background tasks
- `SystemForegroundService` — Foreground task execution
- `Room MultiInstanceInvalidationService` — DB cache invalidation

### Permissions (Notable)
- `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`, `ACCESS_BACKGROUND_LOCATION`
- `FOREGROUND_SERVICE_LOCATION`
- `INTERNET`, `ACCESS_NETWORK_STATE`, `ACCESS_WIFI_STATE`
- `READ_EXTERNAL_STORAGE`, `WRITE_EXTERNAL_STORAGE`, `READ_MEDIA_IMAGES`
- `POST_NOTIFICATIONS`, `RECEIVE_BOOT_COMPLETED`
- `WAKE_LOCK`, `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS`
- `ACCESS_ADSERVICES_AD_ID`, `ACCESS_ADSERVICES_ATTRIBUTION`

### Libraries Used
Firebase (Messaging, Analytics, Installations, CCT/DataTransport), Google Maps, Google Places, AndroidX (full stack: Activity, AppCompat, Core, Fragment, Lifecycle, Navigation, Room, WorkManager, DataStore, RecyclerView, ViewPager2, ConstraintLayout, CoordinatorLayout, WebKit, etc.), Material Components, MPAndroidChart, Picasso, uCrop, Dexter, EasyPermissions, CountryCodePicker, libphonenumber, Kotlin Coroutines, Emoji2, ProfileInstaller, DataBinding, Window Manager

---

## Web App (Admin Panel) Details

**URL:** https://staging.bookmark.services
**Tech Stack:** Laravel (PHP), Blade templates, MySQL, jQuery, DataTables, Select2, Toastr, Morris.js, Moment.js

### Login
- URL: `https://staging.bookmark.services/`
- Credentials used: admin@gmail.com / admin#123

### Dashboard (`/dashboard`)
- This Month Visits: 2 (0 completed, 2 pending)
- Overall Visits Done: 575 (346 completed, 229 pending)
- Total Bookers: 11 (4 Online, 7 Offline)
- Total Customers: 2627
- Open Requests: 17
- Products: 238
- Top City: Karachi (2615 customers)
- Today's Completed Visits: 0
- All Visits: 575
- Online Bookers: 4

### Web App Routes / Modules

| Route | Module | Description |
|---|---|---|
| `/dashboard` | Dashboard | Home/stats |
| `/visits-list-new` | Visits | All visits list |
| `/visits-add` | Visits | Create visit |
| `/today-visits` | Visits | Today's visits |
| `/customer-list` | Customers | All customers |
| `/add-customer` | Customers | Add customer |
| `/booker-list` | Sales Team | Manage sales reps (11 total) |
| `/add-booker` | Sales Team | Add sales rep |
| `/request-list` | Requests | Support requests (17 pending) |
| `/edit-request/{id}` | Requests | Edit request |
| `/delete-request/{id}` | Requests | Delete request |
| `/products-list` | Products | Product catalog (238 items) |
| `/add-products` | Products | Add product |
| `/brand-list` | Brands | Brand management |
| `/add-brand` | Brands | Add brand |
| `/subject-list` | Subjects | Subject categories |
| `/add-subject` | Subjects | Add subject |
| `/Series-list` | Series | Product series |
| `/add-Series` | Series | Add series |
| `/location` | Locations | All locations |
| `/location/karachi` | Locations | Karachi (2615 customers) |
| `/location/lahore` | Locations | Lahore |
| `/location/multan` | Locations | Multan |
| `/cities` | Locations | Manage cities |
| `/zones` | Locations | Manage zones |
| `/areas` | Locations | Manage areas |
| `/edit-profile/1` | Profile | Edit admin profile |
| `/inbox` | Inbox | Messages |
| `/admin/logout` | Auth | Logout |

### Request Categories (from sample data)
- App Issue
- Inventory (stock, refill, delivery)
- Adoption
- Invoice & Signing

---

## How APK Connects to Web App

### Architecture
```
┌─────────────────────┐       ┌──────────────────┐       ┌─────────┐
│  Mobile App (APK)   │ ────▶ │  Laravel Backend  │ ────▶ │  MySQL  │
│  (Sales Reps)       │ ◀──── │  (REST API)       │ ◀──── │   DB    │
└─────────────────────┘       └──────────────────┘       └─────────┘
                                      │ ▲
                                      ▼ │
┌─────────────────────┐       ┌──────────────────┐
│  Web App (Browser)  │ ────▶ │  Laravel Backend  │
│  (Admin/Manager)    │ ◀──── │  (Blade Views)    │
└─────────────────────┘       └──────────────────┘
```

1. **Shared Laravel Backend** — Both APK and web consume the same REST API and database
2. **Firebase Cloud Messaging** — APK uses FCM for push notifications; web triggers pushes via Laravel
3. **Location Sync** — Mobile `LocationService` sends GPS coordinates in real-time, visible on web dashboard
4. **Visit Flow** — Sales reps check in via APK (GPS-verified), complete visits, all synced to web
5. **Request System** — Field agents submit requests from APK, admins manage on web
6. **Customer & Product Data** — Shared catalog managed from web, accessed on mobile

---

## Decompiled Code — Architecture & API

### App Architecture (Kotlin + MVVM + Koin DI)

```
📦 com.ingenious.androidbookmarksalesupgrade
├── ui/activity/          # All screens (Login, Home, Visit, Chat, etc.)
├── viewModel/            # ViewModels (Main, User, Visit, CheckIn, Inventory, etc.)
├── repository/           # Data layer (App, User, Visit, Home, Chat, Product, Inventory)
├── network/              # API layer (Routes, SoService, RemoteConstant, ApiResponseCallback)
│   └── domain/           # DataSource, APIError
├── model/
│   ├── request/          # Request DTOs (Login, AddVisit, AddCustomer, etc.)
│   └── response/         # Response DTOs (HomeResponse, VisitDetails, Products, etc.)
├── storage/              # AppPreferences, Prefs (local data via DataStore)
├── adapter/              # RecyclerView adapters
├── databinding/          # ViewBinding classes
├── extensions/           # Kotlin extensions (File, Dialog, Intent, Permissions)
├── koin/module/          # DI modules (Network, ViewModel, Repository)
├── listener/             # Callback interfaces
├── utils/                # Utils, AppToast, NullCheck
└── helper/MainClass      # Application class
```

### API Endpoints (Extracted from DEX bytecode)

Base URL: `https://staging.bookmark.services/api/`

#### Auth
| Endpoint | Method | Description |
|---|---|---|
| `login` | POST | Login with email/phone + password |
| `forget-password` | POST | Password reset request |
| `reset-password` | POST | Set new password |
| `verify-otp` | POST | Phone OTP verification |
| `registration` | POST | New user registration |

#### Visits
| Endpoint | Description |
|---|---|
| `dashboard` | Home screen data/stats |
| `visit/add-product` | Add product to a visit |
| `visit/update-product` | Update product in a visit |
| `visit/delete-product` | Remove product from a visit |
| `visit/get-product?visit_id=` | Get products for a visit |
| `visit/list` | Visit history |
| `visit/create` | Create new visit |
| `performance/dashboard` | Booker performance stats |

#### Customers
| Endpoint | Description |
|---|---|
| `customer/list` | Customer list |
| `customer/sample?customer_id=` | Get customer samples |
| `customer/create` | Add new customer |
| `customer/details` | Customer details |

#### Products / Inventory
| Endpoint | Description |
|---|---|
| `getProductList` | Product catalog |
| `Inventory/Summary` | Inventory stock summary |
| `Inventory/getBooksBySegment` | Products by segment |
| `products/low-stock` | Low stock alerts |
| `refill/create` | Create refill request |

#### Requests
| Endpoint | Description |
|---|---|
| `requests/list` | Support requests list |
| `requests/create` | Submit new request |
| `request/update/profile` | Update request/profile |

#### Notifications
| Endpoint | Description |
|---|---|
| `notification/list` | Notification list |
| `update-notifications` | Mark notifications read |

#### Chat
| Endpoint | Description |
|---|---|
| `getMessages?page=1` | Chat messages (paginated) |
| `send-message` | Send a chat message |

#### Location
| Endpoint | Description |
|---|---|
| `check-in` | GPS check-in for visit |
| `location-check` | Validate location proximity |
| `updatelocation` | Update user's current location |

#### Activity Log
| Endpoint | Description |
|---|---|
| `Activitylog` | User activity log |
| `Activity/SearchActivitylog?search=` | Search activity log |

#### Adoption
| Endpoint | Description |
|---|---|
| `adoption/create` | Create adoption request |
| `adoption/list` | Adoption history |
| `adoption/products` | Adoption products |

### Data Models (from DTO analysis)
- **Request models**: `LoginRequest`, `AddVisitRequest`, `AddCustomerRequest`, `AddAdoptionRequest`, `CreateProductRefillRequest`, `LocationCheckRequest`, `RegistrationRequest`, `VerifyOtpRequest`, `ResetPasswordRequest`, `ForgetRequest`, `SendMessageRequest`
- **Response models**: `HomeResponse`, `Summary`, `LoginResponse`, `ProfileData`, `CustomerDetails`, `CustomersData`, `VisitDetails`, `VisitDetailsResponse`, `TodayVisitsList`, `PastVisitsList`, `ApprovedVisitsLists`, `Products`, `ProductsList`, `Pagination`, `GlobalResponse`, `AddVisitResponse`, `ImageCheckResponse`, `GradesListResponse`, `SegmentsListData`, `GradesSubjectsData`, `SearchData`, `Messages`, `MessageListResponse`, `ProfileResponse`, `AdoptionBooks`, `AdoptionBooksData`, `AdoptionData`, `AdoptionDetailsData`, `AdoptionProducts`, `AdoptionsList`, `BooksBySegment`, `BooksBySegmentData`, `LowStockProducts`, `RefillByStatusData`, `CreateProductData`, `StockSummaryResponse`, `AllBookerProducts`, `LastVisitCustomerData`, `TodayRecommendedBookerProducts`, `AddVisitData`, `VisitDetailsCustomer`

### Additional Library Detected (from DEX)
- **Volley** — Android HTTP library (networking fallback)
- **Glide** — Image loading (with transformations)
- **Koin** — Dependency injection framework
- **RxJava/Android** — Reactive programming (AsyncSubject, etc.)
- **Code Scanner** — Barcode/QR scanning (com.budiyev.android.codescanner)
- **Signature Pad** — Digital signature capture (com.github.gcacace.signaturepad)
- **Circular Progress Indicator** — Custom progress views
- **Flexbox** — FlexboxLayout for responsive layouts
- **BlurView** — Background blur effects
- **SpinKit** — Loading animations
- **Image Slider** — Image carousel
- **Lottie** — After Effects animations (async updates)
- **SDP/SSP** — Screen-size adaptive dimensions
- **Glide Transformations** — Image cropping/transforms

---

## Decompiled Source Code

The APK was fully decompiled using **jadx**. The full Java source code (646 files) is available at:

```
decompiled/src/sources/com/ingenious/androidbookmarksalesupgrade/
```

### Key Files with Actual Logic

| File | Description |
|---|---|
| `helper/MainClass.java` | Application entry point (Koin DI setup) |
| `network/Routes.java` | All 35 API endpoint path constants |
| `network/SoService.java` | Retrofit API interface (full method signatures) |
| `network/domain/DataSource.java` | API data source implementation |
| `ui/activity/LoginActivity.java` | Login flow (email + OTP), notification permissions, token storage |
| `ui/activity/HomeActivity.java` | Dashboard with visit stats, customer counts |
| `ui/activity/AddVisitActivity.java` | Create visit workflow |
| `ui/activity/CheckInActivity.java` | GPS check-in logic |
| `ui/activity/ChatActivity.java` | In-app messaging |
| `ui/activity/AddCustomerActivity.java` | Add new customer form |
| `utils/LocationService.java` | Foreground GPS service — sends location every 3 seconds to `api/updatelocation` |
| `storage/AppPreferences.java` | Local data persistence (token, login data, location) |
| `viewModel/UserViewModel.java` | Login, profile, registration logic |
| `viewModel/VisitViewModel.java` | Visit CRUD operations |
| `viewModel/InventoryViewModel.java` | Stock/inventory management |
| `viewModel/CheckInViewModel.java` | Location check-in logic |
| `repository/UserRepository.java` | User data operations |
| `repository/VisitRepository.java` | Visit data operations |
| `repository/ProductRepository.java` | Product catalog operations |
| `repository/InventoryRepository.java` | Inventory/stock ops |
| `repository/ChatRepository.java` | Chat message operations |
| `repository/HomeRepository.java` | Dashboard data operations |
