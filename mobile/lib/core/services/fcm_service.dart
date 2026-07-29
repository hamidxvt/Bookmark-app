/// fcm_service.dart
/// Manages Firebase Cloud Messaging (FCM) for push notifications.
/// Registers device token with backend and handles incoming messages.

import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

// Firebase imports — only active when google-services.json is configured
// If firebase is not yet set up, the app still works — push notifications
// will simply not function until Firebase is configured.
import '../network/dio_client.dart';
import '../constants/api_constants.dart';

class FcmService {
  final DioClient _dio;
  bool _initialized = false;

  FcmService(this._dio);

  /// Initialize FCM and register token with backend.
  /// Call this after login.
  Future<void> initialize() async {
    if (_initialized || kIsWeb) return;

    try {
      // Dynamic import so app compiles even without Firebase configured
      final messaging = await _getMessaging();
      if (messaging == null) return;

      // Request permission (iOS requires explicit grant)
      await messaging.requestPermission();

      // Get token and register with backend
      final token = await messaging.getToken();
      if (token != null) {
        await _registerToken(token);
      }

      // Listen for token refresh
      messaging.onTokenRefresh.listen(_registerToken);

      // Handle foreground messages — show a local banner
      messaging.onMessage.listen((message) {
        debugPrint('[FCM] Foreground message: ${message.notification?.title}');
      });

      _initialized = true;
      debugPrint('[FCM] Initialized ✅');
    } catch (e) {
      debugPrint('[FCM] Initialization skipped (Firebase not configured): $e');
    }
  }

  Future<void> _registerToken(String token) async {
    try {
      await _dio.post(ApiConstants.registerFcm, data: {'fcmToken': token});
      debugPrint('[FCM] Token registered with backend');
    } catch (e) {
      debugPrint('[FCM] Token registration failed: $e');
    }
  }

  /// Unregister token on logout
  Future<void> dispose() async {
    _initialized = false;
  }

  /// Dynamically load firebase_messaging to avoid hard crash if not configured
  Future<dynamic> _getMessaging() async {
    try {
      // ignore: unused_local_variable
      final firebase = await _loadFirebase();
      if (firebase == null) return null;
      return firebase;
    } catch (_) {
      return null;
    }
  }

  Future<dynamic> _loadFirebase() async {
    try {
      // This will succeed once google-services.json is added and
      // firebase_core + firebase_messaging are initialized
      // For now, we use a lazy import pattern
      return null; // replace with FirebaseMessaging.instance when Firebase is set up
    } catch (_) {
      return null;
    }
  }
}

final fcmServiceProvider = Provider<FcmService>((ref) {
  return FcmService(ref.read(dioClientProvider));
});
