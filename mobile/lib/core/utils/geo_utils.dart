import 'dart:math';

class GeoUtils {
  GeoUtils._();

  /// Haversine formula — returns distance in meters between two GPS coordinates
  static double haversine(double lat1, double lng1, double lat2, double lng2) {
    const earthRadius = 6371000.0; // meters
    final dLat = _toRad(lat2 - lat1);
    final dLng = _toRad(lng2 - lng1);
    final a = sin(dLat / 2) * sin(dLat / 2) +
        cos(_toRad(lat1)) * cos(_toRad(lat2)) * sin(dLng / 2) * sin(dLng / 2);
    final c = 2 * atan2(sqrt(a), sqrt(1 - a));
    return earthRadius * c;
  }

  static double _toRad(double deg) => deg * pi / 180;

  /// Speed in km/h between two pings given time difference in seconds
  static double speedKmh(double distanceMeters, int elapsedSeconds) {
    if (elapsedSeconds == 0) return 0;
    return (distanceMeters / elapsedSeconds) * 3.6;
  }
}
