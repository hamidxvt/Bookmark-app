import 'package:flutter/material.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';

import 'app.dart';
import 'core/services/background_service.dart';

/// Handle FCM messages received while the app is terminated
@pragma('vm:entry-point')
Future<void> _onBackgroundMessage(RemoteMessage message) async {
  // Firebase must be initialized in isolate for background handler
  await Firebase.initializeApp();
  debugPrint('[FCM-BG] ${message.notification?.title}');
}

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  _registerErrorHandlers();

  // Initialize Firebase (requires google-services.json to be present)
  try {
    debugPrint('[Startup] Initializing Firebase...');
    await Firebase.initializeApp();
    debugPrint('[Startup] Firebase initialized successfully');
    try {
      FirebaseMessaging.onBackgroundMessage(_onBackgroundMessage);
      debugPrint('[Startup] FCM background handler registered');
    } catch (e) {
      debugPrint('[FCM ERROR] Background handler failed (non-fatal): $e');
    }
  } catch (e) {
    debugPrint('[Firebase ERROR] Init failed: $e');
    // Continue even if Firebase fails - app should still work
  }

  // Initialize background GPS service (must run before runApp)
  try {
    debugPrint('[Startup] Initializing background GPS service...');
    await initBackgroundService();
    debugPrint('[Startup] Background GPS service initialized');
  } catch (e) {
    debugPrint('[Background Service ERROR] Init failed (non-fatal): $e');
    // Continue - service is optional for app startup
  }

  debugPrint('[Startup] Running app...');
  runApp(const ProviderScope(child: BookmarkSFAApp()));
}

// Global error handler to catch crashes
void _registerErrorHandlers() {
  // Handle errors from the Flutter framework
  FlutterError.onError = (FlutterErrorDetails details) {
    debugPrint('[FLUTTER ERROR] ${details.exception}');
    debugPrintStack(stackTrace: details.stack);
  };

  // Handle errors from async operations
  PlatformDispatcher.instance.onError = (Object error, StackTrace stack) {
    debugPrint('[PLATFORM ERROR] $error');
    debugPrintStack(stackTrace: stack);
    return true; // Prevent app crash
  };
}
