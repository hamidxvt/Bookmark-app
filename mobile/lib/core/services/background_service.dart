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
        // autoStart=true: service restarts after app is swiped away, after phone reboots,
        // and after the system kills it for memory. This is the key for persistent tracking.
        autoStart: true,
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
  final service = FlutterBackgroundService();
  final running = await service.isRunning();
  if (!running) {
    await service.startService();
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

  service.on('stop').listen((_) {
    service.stopSelf();
  });

  service.on('update_token').listen((data) async {
    if (data?['token'] != null) {
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString(_kTokenKey, data!['token'] as String);
    }
  });

  // Ping GPS every 10 seconds for near-real-time tracking
  Timer.periodic(const Duration(seconds: 10), (_) async {
    await _sendGpsPing(service);
  });

  // Immediate first ping
  await _sendGpsPing(service);
}

@pragma('vm:entry-point')
Future<bool> _iosBackground(ServiceInstance service) async {
  WidgetsFlutterBinding.ensureInitialized();
  DartPluginRegistrant.ensureInitialized();
  return true;
}

Future<void> _sendGpsPing(ServiceInstance service) async {
  try {
    final perm = await Geolocator.checkPermission();
    if (perm == LocationPermission.denied || perm == LocationPermission.deniedForever) return;

    final pos = await Geolocator.getCurrentPosition(
      desiredAccuracy: LocationAccuracy.high,
      timeLimit: const Duration(seconds: 10),
    ).catchError((_) async => Geolocator.getLastKnownPosition());

    if (pos == null) return;

    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString(_kTokenKey);
    if (token == null) return;

    final res = await http.post(
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
        // Filter GPS jitter: speeds under 1 km/h are stationary noise
        'speed_kmh': (pos.speed * 3.6) < 1.0 ? 0.0 : double.parse((pos.speed * 3.6).toStringAsFixed(2)),
        'heading': pos.heading >= 0 ? pos.heading : null,
        'altitude': pos.altitude,
      }),
    ).timeout(const Duration(seconds: 10));

    service.invoke('gps_update', {
      'lat': pos.latitude,
      'lng': pos.longitude,
      'time': DateTime.now().toIso8601String(),
      'ok': res.statusCode == 200,
    });
  } catch (_) {
    // Silently ignore — network may be unavailable
  }
}
