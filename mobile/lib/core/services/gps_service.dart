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

  GpsService(this._dio);

  Future<void> startTracking({String? jwtToken}) async {
    stopTracking();

    if (jwtToken != null) {
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('auth_token', jwtToken);
    }

    if (!kIsWeb) await startBackgroundGps();

    // Position stream — fires on movement (works when outdoors/high accuracy)
    final perm = await Geolocator.checkPermission();
    if (!kIsWeb &&
        (perm == LocationPermission.always ||
            perm == LocationPermission.whileInUse)) {
      _posStream = Geolocator.getPositionStream(
        locationSettings: const LocationSettings(
          accuracy: LocationAccuracy.high,
          distanceFilter: 3,
        ),
      ).listen((pos) {
        _lastPosition = pos;
        _sendPingAsync(pos);
      }, onError: (_) {});
    }

    // 5-second timer — reliable fallback for indoors / low-signal
    _timer = Timer.periodic(const Duration(seconds: 5), (_) => _timerTick());
    _timerTick(); // immediate first ping
  }

  void stopTracking() {
    _timer?.cancel();
    _timer = null;
    _posStream?.cancel();
    _posStream = null;
    if (!kIsWeb) stopBackgroundGps();
  }

  void _timerTick() {
    if (kIsWeb) return;
    _fetchAndPing();
  }

  Future<void> _fetchAndPing() async {
    try {
      final perm = await Geolocator.checkPermission();
      if (perm == LocationPermission.denied ||
          perm == LocationPermission.deniedForever) return;

      final pos = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high,
        timeLimit: const Duration(seconds: 4),
      );
      _lastPosition = pos;
      _sendPingAsync(pos);
    } catch (_) {
      // Timeout or permission error — try sending last known position
      if (_lastPosition != null) _sendPingAsync(_lastPosition!);
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

  void _sendPingAsync(Position pos) {
    // Filter GPS noise: speeds < 1 km/h are stationary (GPS jitter)
    final speedKmh = (pos.speed * 3.6);
    final cleanSpeed = speedKmh < 1.0 ? 0.0 : speedKmh;
    
    _dio.post(ApiConstants.gpsPing, data: {
      'lat': pos.latitude,
      'lng': pos.longitude,
      'accuracy': pos.accuracy,
      'isMock': pos.isMocked,
      'speed_kmh': double.parse(cleanSpeed.toStringAsFixed(2)),
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
