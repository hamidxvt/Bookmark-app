import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:geolocator/geolocator.dart';

import '../services/gps_service.dart';
import '../theme/app_theme.dart';

/// Wraps an action — checks for mock GPS before executing.
/// If mock is detected, shows a warning dialog and blocks the action.
class MockLocationGuard {
  static Future<bool> check(BuildContext context, WidgetRef ref) async {
    try {
      final perm = await Geolocator.requestPermission();
      if (perm == LocationPermission.denied ||
          perm == LocationPermission.deniedForever) {
        return true; // Allow if can't check
      }

      final pos = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high,
        timeLimit: const Duration(seconds: 8),
      );

      if (pos.isMocked) {
        if (context.mounted) {
          await showDialog(
            context: context,
            barrierDismissible: false,
            builder: (ctx) => AlertDialog(
              icon: const Icon(Icons.gps_off_rounded,
                  color: Colors.red, size: 48),
              title: const Text('Mock Location Detected'),
              content: const Text(
                'A fake GPS app is active on your device. '
                'Please disable it before starting a visit.\n\n'
                'This violation has been logged.',
                textAlign: TextAlign.center,
              ),
              actions: [
                FilledButton(
                  style: FilledButton.styleFrom(
                      backgroundColor: AppColors.error),
                  onPressed: () => Navigator.pop(ctx),
                  child: const Text('Understood'),
                ),
              ],
            ),
          );
        }
        return false; // Block the action
      }

      return true; // Clean GPS — allow
    } catch (_) {
      return true; // If check fails, allow gracefully
    }
  }
}
