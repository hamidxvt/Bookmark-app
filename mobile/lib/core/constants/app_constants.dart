class AppConstants {
  AppConstants._();

  static const String appName = 'Bookmark SFA';

  static const int geofenceRadiusMeters = 200;
  static const int gpsPingIntervalSeconds = 30;
  static const int sameEditWindowHour = 23;
  static const int sameEditWindowMinute = 59;
  static const int maxDailyVisits = 7;
  static const int maxCarryForwardAttempts = 5;
  static const int totalLeaveBalance = 28;
  static const int sickLeaveDefault = 10;
  static const int casualLeaveDefault = 18;
  static const double defaultDailyPerformanceRate = 3000.0;

  // Sync status labels (mirrors DB enum)
  static const String syncPending = 'pending_sync';
  static const String syncDone = 'synced';
}
