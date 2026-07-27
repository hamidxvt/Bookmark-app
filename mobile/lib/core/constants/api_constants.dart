class ApiConstants {
  ApiConstants._();

  // 10.0.2.2 maps to host machine localhost when running in Android emulator
  static const String _devBase = 'http://10.0.2.2:3000/api/v1';
  static const String _prodBase = 'https://api.bookmark.services/api/v1';

  static const bool _isProduction = bool.fromEnvironment('dart.vm.product');
  static String get baseUrl => _isProduction ? _prodBase : _devBase;

  // Auth
  static const String login = '/auth/login';
  static const String changePassword = '/auth/change-password';

  // Workday
  static const String dayStart = '/workday/day-start';
  static const String dayEnd = '/workday/day-end';
  static const String cannotWork = '/workday/cannot-work';

  // Visits
  static const String todayVisits = '/visits/today';
  static String visitStart(int id) => '/visits/$id/start';
  static String visitComplete(int id) => '/visits/$id/complete';
  static String visitEdit(int id) => '/visits/$id/edit';
  static String visitMissed(int id) => '/visits/$id/mark-missed';
  static const String adhocVisit = '/visits/adhoc';

  // Tracking
  static const String trackingPing = '/tracking/ping';

  // Samples
  static const String sampleRequest = '/samples/request';
  static String sampleRecover(int id) => '/samples/$id/recover';

  // Leaves
  static const String applyLeave = '/leaves/apply';
}
