import 'package:flutter/material.dart';
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

  // Initialize Firebase (requires google-services.json to be present)
  try {
    await Firebase.initializeApp();
    FirebaseMessaging.onBackgroundMessage(_onBackgroundMessage);
  } catch (e) {
    debugPrint('[Firebase] Init failed — push notifications disabled: $e');
  }

  // Initialize background GPS service (must run before runApp)
  await initBackgroundService();

  runApp(const ProviderScope(child: BookmarkSFAApp()));
}
