# Engineering Rules & Vibe Coding Guidelines
## Bookmark SFA — Field Force Management System

---

## General Principles

- **Modular Architecture**: Group files by Feature, not by type.
  - Flutter: `/lib/features/<feature>/` (data, domain, presentation)
  - Node.js: `/src/modules/<module>/` (service, controller, routes)
- Never write a single function or widget longer than **60 lines**.
- No hardcoded magic numbers or raw string literals in UI — use `AppColors`, `AppSpacing`, `AppRadius`, and `AppConstants`.
- Every feature must have: a `Repository/Service` (data), a `Notifier` (state), and a `Screen` (UI). Keep these separate.

---

## Flutter Frontend Guidelines

### State Management
- Use **`Notifier`** or **`AsyncNotifier`** from Riverpod — never legacy `StateNotifier`.
- Every screen that needs async data should use `AsyncValue.when(loading:, error:, data:)`.
- Always show:
  - Loading state: `CircularProgressIndicator` or `Shimmer`
  - Error state: A retry button + error message
  - Pull-to-refresh on all list screens

### Security
- Store JWTs **only** in `flutter_secure_storage`. Never `SharedPreferences`.
- The `DioClient` auto-attaches the Bearer token from secure storage.
- On 401 response, clear token and redirect to `/login`.

### UI Standards
- Theme: **Material 3** with `AppTheme.light`. All components use the design system in `core/theme/app_theme.dart`.
- Colors: always reference `AppColors.*` constants.
- Spacing: always use multiples of 4px (`AppSpacing.xs=4, sm=8, md=16, lg=24, xl=32`).
- Border radius: use `AppRadius.*` constants.
- Animations: use `flutter_animate` with `.animate()` extension. Keep durations 300–600ms. Use `easeOut` or `elasticOut` curves.
- Fonts: Inter via `google_fonts`. Applied globally through `AppTheme`.

### File Naming
```
lib/features/<feature>/
  data/<feature>_repository.dart    # API calls + local DB
  domain/<feature>_models.dart      # Plain Dart models with fromJson/toJson
  presentation/<feature>_notifier.dart  # Riverpod Notifier (state)
  presentation/<feature>_screen.dart    # Widget UI
```

### Do NOT
- Import screens directly into other screens (navigate via GoRouter paths).
- Access `Repository` from a widget directly (always go through a `Notifier`).
- Use `setState` for anything beyond local UI state (toggle visibility, text field control).

---

## Node.js / Express Backend Guidelines

### Architecture Flow
```
Request → Route → Controller (validate input) → Service (business logic) → Prisma DB → Response
```

### Module Structure
```
src/modules/<module>/
  <module>.routes.js      # Router: define paths + middleware
  <module>.controller.js  # Thin controller: extract body/params, call service, return JSON
  <module>.service.js     # All business logic + Prisma calls live here
```

### Validation
- Use `zod` schemas in routes to validate every request body and URL parameter.
- Use the `validate(schema)` middleware — never skip it for public endpoints.

### Error Handling
- Throw `AppError(code, statusCode, message)` from services.
- The global `errorHandler` middleware formats all errors into:
  ```json
  { "success": false, "error": { "code": "ERR_CODE", "message": "Human message" } }
  ```
- Never expose stack traces in production (`NODE_ENV=production`).

### Security Non-Negotiables
- Helmet and rate-limiting are already configured globally in `app.js`.
- JWT secret must come from `process.env.JWT_SECRET` — never hardcode.
- Never execute unparameterized SQL or use Prisma's `$queryRawUnsafe`.
- All admin actions must be logged in the `audit_logs` table via Prisma.

### Standard Response Envelope
```json
// Success
{ "success": true, "data": { ... } }

// Error
{ "success": false, "error": { "code": "SNAKE_CASE_CODE", "message": "Readable message" } }
```

---

## Automated Testing Rules

- **Backend**: Every `*.service.js` file must have a `*.service.test.js` that covers:
  - Happy path (correct input → expected output)
  - Failure path (wrong credentials / missing data → correct error thrown)
- **Flutter**: Every `*_notifier.dart` must have a `*_notifier_test.dart` that covers:
  - Initial state
  - Success state after async call
  - Error state after failed call
- Run backend tests with: `npm test`
- Run Flutter tests with: `flutter test`

---

## How to Add a New Feature (4-Step Sequence)

1. **Database** — Add model to `prisma/schema.prisma`. Run `npm run db:push`.
2. **Backend** — Create `src/modules/<feature>/` with routes, controller, service. Add to `src/routes/index.js`.
3. **Flutter Data** — Create `lib/features/<feature>/data/<feature>_repository.dart` with Dio calls.
4. **Flutter UI** — Create notifier + screen. Wire screen into GoRouter in `app.dart`.

---

## AI Code Generation Rules (Cursor / Claude / Copilot)

- Always follow **Feature-First** file structure — never create global `controllers/` or `screens/` folders.
- Flutter state: use `Notifier` / `AsyncNotifier` — never `ChangeNotifier` or `setState` for app-level state.
- Node.js: never use raw `any` types when migrating to TypeScript. Use Zod inferred types.
- Strictly preserve existing API response contracts — field names, status codes, envelope shape.
- Never install packages not already in `pubspec.yaml` or `package.json` without confirming with the user.
- When in doubt about a design pattern, follow existing feature modules in the codebase.
