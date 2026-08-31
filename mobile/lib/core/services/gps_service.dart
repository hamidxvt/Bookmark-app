import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:geolocator/geolocator.dart';
import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../constants/api_constants.dart';
import '../network/dio_client.dart';
import 'background_service.dart';

class GpsService {
  final DioClient _dio;
  Timer? _pingTimer;
  StreamSubscription<Position>? _positionStream;
  Position? _lastPosition;
  DateTime? _lastPingAt;

  GpsService(this._dio);

  /// Start real-time GPS: stream every 5 m of movement + 4-second fallback timer
  Future<void> startTracking({String? jwtToken}) async {
    stopTracking();

    if (jwtToken != null) {
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('auth_token', jwtToken);
    }

    if (!kIsWeb) {
      await startBackgroundGps();
    }

    // Position stream — fires every time device moves ≥ 5 m
    // Gives Uber-like real-time movement updates instantly
    final perm = await Geolocator.checkPermission();
    if (perm == LocationPermission.always ||
        perm == LocationPermission.whileInUse) {
      _positionStream = Geolocator.getPositionStream(
        locationSettings: const LocationSettings(
          accuracy: LocationAccuracy.high,
          distanceFilter: 5, // fire on every 5 metres of movement
        ),
      ).listen(_onPosition, onError: (_) {});
    }

    // Fallback heartbeat every 4 s (keeps server fresh even when stationary)
    _pingTimer = Timer.periodic(const Duration(seconds: 4), (_) => _ping());
    _ping(); // immediate first ping
  }

  void stopTracking() {
    _pingTimer?.cancel();
    _pingTimer = null;
    _positionStream?.cancel();
    _positionStream = null;
    if (!kIsWeb) {
      stopBackgroundGps();
    }
  }

  /// Callback for position stream — ping immediately on movement
  void _onPosition(Position pos) {
    _lastPosition = pos;

    // Throttle: don't send faster than every 2 s even if device fires more often
    final now = DateTime.now();
    if (_lastPingAt != null &&
        now.difference(_lastPingAt!).inMilliseconds < 2000) {
      return;
    }
    _lastPingAt = now;
    _sendPing(pos);
  }

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

  bool isWithinGeofence({
    required double currentLat,
    required double currentLng,
    required double targetLat,
    required double targetLng,
    double radiusMeters = 200,
  }) {
    final distance = Geolocator.distanceBetween(
        currentLat, currentLng, targetLat, targetLng);
    return distance <= radiusMeters;
  }

  Future<void> _ping() async {
    if (kIsWeb) return;
    try {
      final pos = await getCurrentPosition();
      if (pos == null) return;
      final now = DateTime.now();
      if (_lastPingAt != null &&
          now.difference(_lastPingAt!).inMilliseconds < 2000) {
        return; // stream already covered this moment
      }
      _lastPingAt = now;
      _sendPing(pos);
    } catch (_) {}
  }

  Future<void> _sendPing(Position pos) async {
    try {
      await _dio.post(ApiConstants.gpsPing, data: {
        'lat': pos.latitude,
        'lng': pos.longitude,
        'accuracy': pos.accuracy,
        'isMock': pos.isMocked,
        'speed_kmh':
            double.parse((pos.speed * 3.6).toStringAsFixed(2)),
        'altitude': pos.altitude,
        'heading': pos.heading >= 0 ? pos.heading : null,
        'timestamp': DateTime.now().toIso8601String(),
      });
    } on DioException {
      // silent
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
