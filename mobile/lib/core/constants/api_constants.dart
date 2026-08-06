class ApiConstants {
  ApiConstants._();

  // Production: Next.js admin dashboard serves the mobile API
  static const String _prodBase = 'https://bookmark-production-00c6.up.railway.app/api/mobile';

  // Local dev: Node.js backend on port 3001 (needs: adb reverse tcp:3001 tcp:3001)
  static const String _devBase = 'http://10.0.2.2:3001/api/v1';

  // Use production — no local backend needed
  static const bool _useProduction = true;
  static String get baseUrl => _useProduction ? _prodBase : _devBase;

  // ── Auth ────────────────────────────────────────────────────────────────
  static const String login = '/auth';
  static const String changePassword = '/auth/change-password';
  static const String me = '/me';

  // ── Workday ─────────────────────────────────────────────────────────────
  static const String dayStart = '/workday';
  static const String dayEnd = '/workday';
  static const String cannotWork = '/workday';
  static const String workdayStatus = '/workday';

  // ── Visits ──────────────────────────────────────────────────────────────
  static const String todayVisits = '/visits';
  static const String adhocVisit = '/visits'; // POST with customerId
  static const String customersSearch = '/customers'; // GET ?q=search
  static String visitStart(int id) => '/visits/$id/start';
  static String visitComplete(int id) => '/visits/$id/complete';
  static String visitMiss(int id) => '/visits/$id/miss';
  static String visitEdit(int id) => '/visits/$id/edit';

  // ── GPS Tracking ─────────────────────────────────────────────────────────
  static const String gpsPing = '/gps';
  static const String livePositions = '/gps/live';

  // ── Route Optimization ────────────────────────────────────────────────────
  static const String routeOptimized = '/route';

  // ── Profile ───────────────────────────────────────────────────────────────
  static const String profile = '/profile';

  // ── Payroll / Earnings ───────────────────────────────────────────────────
  static const String myPayroll = '/payroll';

  // ── FCM / Push Notifications ─────────────────────────────────────────────
  static const String registerFcm = '/fcm';
}
