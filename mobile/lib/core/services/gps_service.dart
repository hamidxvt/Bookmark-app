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
  Timer? _timer;
  StreamSubscription<Position>? _posStream;
  Position? _lastPosition;
  DateTime? _lastPingAt;

  GpsService(this._dio);

  /// Real-time tracking: aggressive 2-second polling + stream for real-time updates
  /// Works indoors, on emulator with mock location, everywhere
  Future<void> startTracking({String? jwtToken}) async {
    stopTracking();

    if (jwtToken != null) {
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('auth_token', jwtToken);
    }

    if (!kIsWeb) await startBackgroundGps();

    // Try to get initial position
    _lastPosition = await _getPositionQuick();

    // Subscribe to OS position stream for real-time updates when available
    final perm = await Geolocator.checkPermission();
    if (perm == LocationPermission.always ||
        perm == LocationPermission.whileInUse) {
      _posStream = Geolocator.getPositionStream(
        locationSettings: const LocationSettings(
          accuracy: LocationAccuracy.high,
          distanceFilter: 3, // Fire on 3m movement
        ),
      ).listen((pos) {
        _lastPosition = pos;
        _sendPingAsync(pos);
      }, onError: (_) {});
    }

    // 2-second timer: fetch position aggressively + send ping
    // This ensures frequent updates even if stream doesn't fire (indoors/emulator)
    _timer = Timer.periodic(const Duration(seconds: 2), (_) {
      _timerTick();
    });
  }

  void stopTracking() {
    _timer?.cancel();
    _timer = null;
    _posStream?.cancel();
    _posStream = null;
    if (!kIsWeb) stopBackgroundGps();
  }

  /// Timer callback: fetch position with quick timeout + send
  void _timerTick() {
    if (kIsWeb) return;
    _getPositionQuick().then((pos) {
      if (pos != null) {
        _lastPosition = pos;
        _sendPingAsync(pos);
      }
    });
  }

  /// Quick position fetch — 5 second timeout, falls back to last known
  Future<Position?> _getPositionQuick() async {
    try {
      final perm = await Geolocator.checkPermission();
      if (perm == LocationPermission.denied ||
          perm == LocationPermission.deniedForever) return _lastPosition;

      final pos = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high,
        timeLimit: const Duration(seconds: 5),
      ).timeout(
        const Duration(seconds: 6),
        onTimeout: () => _lastPosition,
      );
      return pos;
    } catch (_) {
      return _lastPosition;
    }
  }

  Future<Position?> getCurrentPosition() async {
    if (_lastPosition != null) return _lastPosition;
    try {
      final perm = await Geolocator.requestPermission();
      if (perm == LocationPermission.denied ||
          perm == LocationPermission.deniedForever) return null;
      final pos = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high,
        timeLimit: const Duration(seconds: 10),
      );
      _lastPosition = pos;
      return pos;
    } catch (_) {
      return null;
    }
  }

  bool isWithinGeofence({
    required double currentLat,
    required double currentLng,
    required double targetLat,
    required double targetLng,
    double radiusMeters = 200,
  }) {
    final d = Geolocator.distanceBetween(
        currentLat, currentLng, targetLat, targetLng);
    return d <= radiusMeters;
  }

  /// Fire and forget ping — don't wait, don't block
  void _sendPingAsync(Position pos) {
    _dio.post(ApiConstants.gpsPing, data: {
      'lat': pos.latitude,
      'lng': pos.longitude,
      'accuracy': pos.accuracy,
      'isMock': pos.isMocked,
      'speed_kmh': double.parse((pos.speed * 3.6).toStringAsFixed(2)),
      'altitude': pos.altitude,
      'heading': pos.heading >= 0 ? pos.heading : null,
      'timestamp': DateTime.now().toIso8601String(),
    }).catchError((_) {});
  }

  Position? get lastKnownPosition => _lastPosition;
  void dispose() => stopTracking();
}

final gpsServiceProvider = Provider<GpsService>((ref) {
  final service = GpsService(ref.read(dioClientProvider));
  ref.onDispose(service.dispose);
  return service;
});
