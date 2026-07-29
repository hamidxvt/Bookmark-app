import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'app.dart';
import 'core/services/background_service.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  // Initialize background GPS service (must run before runApp)
  await initBackgroundService();
  runApp(const ProviderScope(child: BookmarkSFAApp()));
}
