/// fcm_service.dart
/// Full Firebase Cloud Messaging implementation.
/// Registers device FCM token with the backend, handles foreground/background
/// notifications, and surfaces them as in-app banners.

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:go_router/go_router.dart';

import '../network/dio_client.dart';
import '../constants/api_constants.dart';

// ── Notification overlay key — set this from your root widget ───────────────
final GlobalKey<NavigatorState> navigatorKey = GlobalKey<NavigatorState>();

class FcmService {
  final DioClient _dio;
  bool _initialized = false;

  FcmService(this._dio);

  /// Call once after a successful login.
  Future<void> initialize() async {
    if (_initialized) return;

    try {
      final messaging = FirebaseMessaging.instance;

      // Request permission (Android 13+ / iOS both require this)
      final settings = await messaging.requestPermission(
        alert: true,
        badge: true,
        sound: true,
        provisional: false,
      );

      if (settings.authorizationStatus == AuthorizationStatus.denied) {
        debugPrint('[FCM] Permission denied by user');
        return;
      }

      // Register token
      final token = await messaging.getToken();
      if (token != null) await _registerToken(token);

      // Refresh token whenever Firebase rotates it
      messaging.onTokenRefresh.listen(_registerToken);

      // Foreground messages — show an in-app snackbar
      FirebaseMessaging.onMessage.listen(_handleForeground);

      // App opened from a background notification tap
      FirebaseMessaging.onMessageOpenedApp.listen(_handleTap);

      // App launched cold from notification tap
      final initial = await messaging.getInitialMessage();
      if (initial != null) _handleTap(initial);

      _initialized = true;
      debugPrint('[FCM] Initialized — token: ${token?.substring(0, 20)}...');
    } catch (e) {
      debugPrint('[FCM] Initialization error: $e');
    }
  }

  Future<void> _registerToken(String token) async {
    try {
      await _dio.post(ApiConstants.registerFcm, data: {'fcmToken': token});
      debugPrint('[FCM] Token registered');
    } catch (e) {
      debugPrint('[FCM] Token registration failed (will retry on next launch): $e');
    }
  }

  void _handleForeground(RemoteMessage msg) {
    final title = msg.notification?.title ?? '';
    final body = msg.notification?.body ?? '';
    debugPrint('[FCM] Foreground: $title — $body');

    final ctx = navigatorKey.currentContext;
    if (ctx == null) return;

    ScaffoldMessenger.of(ctx).showSnackBar(
      SnackBar(
        content: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            if (title.isNotEmpty)
              Text(title, style: const TextStyle(fontWeight: FontWeight.w700, fontSize: 13)),
            if (body.isNotEmpty)
              Text(body, style: const TextStyle(fontSize: 12)),
          ],
        ),
        backgroundColor: const Color(0xFF1E293B),
        behavior: SnackBarBehavior.floating,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
        duration: const Duration(seconds: 5),
        action: SnackBarAction(
          label: 'Dismiss',
          textColor: const Color(0xFFC8102E),
          onPressed: () => ScaffoldMessenger.of(ctx).hideCurrentSnackBar(),
        ),
      ),
    );
  }

  void _handleTap(RemoteMessage msg) {
    debugPrint('[FCM] Notification tapped: ${msg.data}');
    final type = msg.data['type'] as String?;
    final ctx = navigatorKey.currentContext;
    if (ctx == null) return;

    try {
      final router = GoRouter.of(ctx);
      switch (type) {
        case 'visit':
          router.go('/visits');
          break;
        case 'sample':
          router.go('/samples');
          break;
        case 'leave':
          router.go('/leaves');
          break;
        default:
          router.go('/dashboard');
      }
    } catch (_) {}
  }

  /// Call on logout to clear state
  void dispose() {
    _initialized = false;
  }
}

final fcmServiceProvider = Provider<FcmService>((ref) {
  return FcmService(ref.read(dioClientProvider));
});
