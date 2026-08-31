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
  Timer? _heartbeat;
  StreamSubscription<Position>? _posStream;
  Position? _lastPosition;
  DateTime? _lastPingAt;
  bool _pinging = false;

  GpsService(this._dio);

  /// Real-time tracking:
  ///  • Position stream fires on any OS-provided position update (distanceFilter 0)
  ///  • 3-second heartbeat sends last-known position so admin stays fresh even if stationary
  Future<void> startTracking({String? jwtToken}) async {
    stopTracking();

    if (jwtToken != null) {
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('auth_token', jwtToken);
    }

    if (!kIsWeb) await startBackgroundGps();

    final perm = await Geolocator.checkPermission();
    if (perm == LocationPermission.denied ||
        perm == LocationPermission.deniedForever) {
      await Geolocator.requestPermission();
    }

    // Subscribe to OS position stream — fires whenever device has a new fix
    // distanceFilter: 0 → get every single update the OS delivers
    if (!kIsWeb) {
      _posStream = Geolocator.getPositionStream(
        locationSettings: const LocationSettings(
          accuracy: LocationAccuracy.bestForNavigation,
          distanceFilter: 0,
        ),
      ).listen(_onNewPosition, onError: (_) {});
    }

    // 3-second heartbeat — re-sends _lastPosition so admin never goes stale
    _heartbeat = Timer.periodic(const Duration(seconds: 3), (_) {
      if (_lastPosition != null) _sendPing(_lastPosition!);
    });

    // Get first fix immediately (don't wait for stream)
    _initPosition();
  }

  void stopTracking() {
    _heartbeat?.cancel();
    _heartbeat = null;
    _posStream?.cancel();
    _posStream = null;
    if (!kIsWeb) stopBackgroundGps();
  }

  void _onNewPosition(Position pos) {
    _lastPosition = pos;
    // Send immediately on position change — no throttle on movement
    _sendPing(pos);
  }

  Future<void> _initPosition() async {
    if (kIsWeb) return;
    try {
      final perm = await Geolocator.checkPermission();
      if (perm == LocationPermission.denied ||
          perm == LocationPermission.deniedForever) return;

      // Quick fix with short timeout — fallback to stream
      final pos = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high,
        timeLimit: const Duration(seconds: 8),
      );
      _lastPosition = pos;
      _sendPing(pos);
    } catch (_) {}
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

  Future<void> _sendPing(Position pos) async {
    if (kIsWeb || _pinging) return;
    _pinging = true;
    try {
      await _dio.post(ApiConstants.gpsPing, data: {
        'lat': pos.latitude,
        'lng': pos.longitude,
        'accuracy': pos.accuracy,
        'isMock': pos.isMocked,
        'speed_kmh': double.parse((pos.speed * 3.6).toStringAsFixed(2)),
        'altitude': pos.altitude,
        'heading': pos.heading >= 0 ? pos.heading : null,
        'timestamp': DateTime.now().toIso8601String(),
      });
    } on DioException {
      // silent
    } catch (_) {
    } finally {
      _pinging = false;
    }
  }

  Position? get lastKnownPosition => _lastPosition;
  void dispose() => stopTracking();
}

final gpsServiceProvider = Provider<GpsService>((ref) {
  final service = GpsService(ref.read(dioClientProvider));
  ref.onDispose(service.dispose);
  return service;
});
