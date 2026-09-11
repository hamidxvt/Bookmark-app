import 'dart:async';
import 'dart:convert';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_background_service/flutter_background_service.dart';
import 'package:geolocator/geolocator.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

const _kBaseUrl = 'https://bookmark-production-00c6.up.railway.app/api/mobile';
const _kTokenKey = 'auth_token';
const _kChannelId = 'bookmark_gps';
const _kChannelName = 'GPS Tracking';

/// Call once at app startup (before runApp)
Future<void> initBackgroundService() async {
  try {
    final service = FlutterBackgroundService();

    await service.configure(
      androidConfiguration: AndroidConfiguration(
        onStart: _backgroundMain,
        // autoStart=false: prevents ForegroundServiceStartNotAllowedException on Android 14+.
        // GPS tracking is started explicitly when the officer begins their day.
        autoStart: false,
        isForegroundMode: true,
        notificationChannelId: _kChannelId,
        initialNotificationTitle: 'Bookmark SFA – Tracking Active',
        initialNotificationContent: 'Location is being shared with your manager',
        foregroundServiceNotificationId: 888,
      ),
      iosConfiguration: IosConfiguration(
        autoStart: true,
        onForeground: _backgroundMain,
        onBackground: _iosBackground,
      ),
    );
  } catch (e) {
    debugPrint('[BackgroundService] Failed to initialize: $e');
    // Continue without background service - app should still work
  }
}

/// Start background GPS service (call after day start / login)
Future<void> startBackgroundGps() async {
  try {
    final service = FlutterBackgroundService();
    final running = await service.isRunning();
    if (!running) {
      await service.startService();
    }
  } catch (e) {
    // Android 14+ throws SecurityException if location permission missing
    // or if invoked while app is in background. Caller has already ensured
    // permission; log so failures are diagnosable.
    debugPrint('[BackgroundService] startService failed: $e');
    rethrow;
  }
}

/// Stop background GPS service (call after day end / logout)
Future<void> stopBackgroundGps() async {
  final service = FlutterBackgroundService();
  service.invoke('stop');
}

// ─── Background Isolate Entry Point ──────────────────────────────────────────

@pragma('vm:entry-point')
Future<void> _backgroundMain(ServiceInstance service) async {
  DartPluginRegistrant.ensureInitialized();

  StreamSubscription<Position>? posSub;
  Timer? heartbeat;

  service.on('stop').listen((_) {
    posSub?.cancel();
    heartbeat?.cancel();
    service.stopSelf();
  });

  service.on('update_token').listen((data) async {
    if (data?['token'] != null) {
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString(_kTokenKey, data!['token'] as String);
    }
  });

  // Stream-based tracking:
  //   - emits when the phone actually moves 15m OR every ~30s (timeLimit)
  //   - drastically lower battery + data cost than a 10s polling loop
  //   - the OS coalesces GPS fixes for us, no jitter loop
  try {
    posSub = Geolocator.getPositionStream(
      locationSettings: const LocationSettings(
        accuracy: LocationAccuracy.high,
        distanceFilter: 15,
        timeLimit: Duration(seconds: 30),
      ),
    ).listen(
      (pos) => _sendGpsPingWith(service, pos),
      onError: (Object e) {
        debugPrint('[BackgroundService] position stream error: $e');
      },
      cancelOnError: false,
    );
  } catch (e) {
    debugPrint('[BackgroundService] failed to start position stream: $e');
  }

  // Immediate first ping so admin sees the officer as soon as day starts,
  // even before the stream emits the first fix.
  await _sendGpsPing(service);

  // Heartbeat every 2 minutes: guarantees a ping when the officer is
  // fully stationary (stream may not emit for a long time), and doubles
  // as a self-heal — if the stream died silently, we still update lastSeenAt.
  heartbeat = Timer.periodic(const Duration(minutes: 2), (_) async {
    await _sendGpsPing(service);
  });
}

@pragma('vm:entry-point')
Future<bool> _iosBackground(ServiceInstance service) async {
  WidgetsFlutterBinding.ensureInitialized();
  DartPluginRegistrant.ensureInitialized();
  return true;
}

/// One-shot ping using either the current fix or the last known position.
/// Used for the very first ping after service start and for the heartbeat.
Future<void> _sendGpsPing(ServiceInstance service) async {
  try {
    final perm = await Geolocator.checkPermission();
    if (perm == LocationPermission.denied ||
        perm == LocationPermission.deniedForever) {
      return;
    }

    Position? pos;
    try {
      pos = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high,
        timeLimit: const Duration(seconds: 10),
      );
    } catch (e) {
      debugPrint('[BackgroundService] getCurrentPosition failed: $e');
      pos = await Geolocator.getLastKnownPosition();
    }

    if (pos == null) return;
    await _sendGpsPingWith(service, pos);
  } catch (e) {
    debugPrint('[BackgroundService] _sendGpsPing error: $e');
  }
}

/// POST a specific Position to the backend. Called by both the stream
/// listener and the heartbeat.
Future<void> _sendGpsPingWith(ServiceInstance service, Position pos) async {
  try {
    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString(_kTokenKey);
    if (token == null) return;

    final res = await http
        .post(
          Uri.parse('$_kBaseUrl/gps'),
          headers: {
            'Authorization': 'Bearer $token',
            'Content-Type': 'application/json',
          },
          body: jsonEncode({
            'lat': pos.latitude,
            'lng': pos.longitude,
            'accuracy': pos.accuracy,
            'isMock': pos.isMocked,
            // Filter GPS jitter: speeds under 1 km/h are stationary noise.
            'speed_kmh': (pos.speed * 3.6) < 1.0
                ? 0.0
                : double.parse((pos.speed * 3.6).toStringAsFixed(2)),
            'heading': pos.heading >= 0 ? pos.heading : null,
            'altitude': pos.altitude,
          }),
        )
        .timeout(const Duration(seconds: 10));

    service.invoke('gps_update', {
      'lat': pos.latitude,
      'lng': pos.longitude,
      'time': DateTime.now().toIso8601String(),
      'ok': res.statusCode == 200,
    });
  } catch (e) {
    // Network may be unavailable; the next stream event or heartbeat
    // will retry. Logged for diagnosability.
    debugPrint('[BackgroundService] ping POST failed: $e');
  }
}
