import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:geolocator/geolocator.dart';
import 'package:dio/dio.dart';

import '../constants/api_constants.dart';
import '../network/dio_client.dart';

class GpsService {
  final DioClient _dio;
  Timer? _pingTimer;
  Position? _lastPosition;

  GpsService(this._dio);

  /// Start periodic GPS pings every 60 seconds
  void startTracking() {
    stopTracking();
    _pingTimer = Timer.periodic(const Duration(seconds: 60), (_) => _ping());
    _ping(); // immediate first ping
  }

  void stopTracking() {
    _pingTimer?.cancel();
    _pingTimer = null;
  }

  /// Returns current position or null if unavailable / mocked
  Future<Position?> getCurrentPosition() async {
    try {
      final perm = await Geolocator.requestPermission();
      if (perm == LocationPermission.denied ||
          perm == LocationPermission.deniedForever) return null;

      final pos = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high,
        timeLimit: const Duration(seconds: 15),
      );
      _lastPosition = pos;
      return pos;
    } catch (_) {
      return _lastPosition;
    }
  }

  /// Check if the device is within [radiusMeters] of [targetLat]/[targetLng]
  bool isWithinGeofence({
    required double currentLat,
    required double currentLng,
    required double targetLat,
    required double targetLng,
    double radiusMeters = 200,
  }) {
    final distanceInMeters = Geolocator.distanceBetween(
      currentLat,
      currentLng,
      targetLat,
      targetLng,
    );
    return distanceInMeters <= radiusMeters;
  }

  Future<void> _ping() async {
    if (kIsWeb) return;
    try {
      final pos = await getCurrentPosition();
      if (pos == null) return;

      await _dio.post(ApiConstants.gpsPing, data: {
        'lat': pos.latitude,
        'lng': pos.longitude,
        'accuracy': pos.accuracy,
        'isMock': pos.isMocked,
      });
    } on DioException {
      // Silently ignore — network may not be available
    } catch (_) {}
  }

  Position? get lastKnownPosition => _lastPosition;

  void dispose() => stopTracking();
}

final gpsServiceProvider = Provider<GpsService>((ref) {
  final service = GpsService(ref.read(dioClientProvider));
  ref.onDispose(service.dispose);
  return service;
});
