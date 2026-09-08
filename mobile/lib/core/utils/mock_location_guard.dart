import 'dart:io';
import 'package:geolocator/geolocator.dart';

class MockLocationGuard {
  /// Returns true if the location appears to be mocked/spoofed.
  /// Blocks action and should trigger a security ping to backend.
  static Future<bool> isMocked(Position position) async {
    if (position.isMocked) return true;

    // Extra check on Android via developer options heuristic
    if (Platform.isAndroid) {
      // Accuracy below 1m is suspiciously perfect — flag as potential mock
      if (position.accuracy < 1.0) return true;
    }
    return false;
  }
}

class MockLocationException implements Exception {
  final String message;
  const MockLocationException(this.message);

  @override
  String toString() => message;
}

class GeofenceException implements Exception {
  final String message;
  const GeofenceException(this.message);

  @override
  String toString() => message;
}
