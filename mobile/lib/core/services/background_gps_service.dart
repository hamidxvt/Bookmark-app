import 'package:background_fetch/background_fetch.dart';
import 'package:geolocator/geolocator.dart';
import 'dart:async';
import '../network/dio_client.dart';
import '../constants/api_constants.dart';
import '../storage/secure_storage.dart';

class BackgroundGpsService {
  static const String taskId = 'bookmark_gps_tracking';
  
  /// Initialize background GPS tracking
  static Future<void> initialize() async {
    // Request permissions first
    await Geolocator.requestPermission();
    
    // Configure background fetch
    await BackgroundFetch.configure(
      BackgroundFetchConfig(
        minimumFetchInterval: 15, // 15 minutes minimum (Android requirement)
        stopOnTerminate: false, // Continue after app termination
        enableHeadless: true, // Run headless tasks
        requiresBatteryNotLow: false,
        requiresDeviceIdle: false,
        startOnBoot: true,
      ),
      _onBackgroundFetch,
      _onBackgroundFetchTimeout,
    );
  }

  /// Start background GPS tracking
  static Future<void> start() async {
    try {
      await BackgroundFetch.start();
    } catch (e) {
      print('[BackgroundGPS] Start error: $e');
    }
  }

  /// Stop background GPS tracking
  static Future<void> stop() async {
    try {
      await BackgroundFetch.stop();
    } catch (e) {
      print('[BackgroundGPS] Stop error: $e');
    }
  }

  /// Background fetch callback (runs periodically)
  static void _onBackgroundFetch(String taskId) async {
    try {
      if (taskId == BackgroundGpsService.taskId) {
        await _sendGpsLocation();
      }
    } catch (e) {
      print('[BackgroundGPS] Fetch error: $e');
    } finally {
      BackgroundFetch.finish(taskId);
    }
  }

  /// Timeout callback
  static void _onBackgroundFetchTimeout(String taskId) {
    print('[BackgroundGPS] Timeout for task: $taskId');
    BackgroundFetch.finish(taskId);
  }

  /// Send current GPS location to backend
  static Future<void> _sendGpsLocation() async {
    try {
      // Check if user is logged in
      final storage = SecureStorage();
      final token = await storage.getToken();
      if (token == null) return; // Not logged in

      // Get current position
      final position = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.best,
        timeLimit: const Duration(seconds: 10),
      ).catchError((_) => null);

      if (position == null) return;

      // Create DioClient and send GPS ping
      final dio = DioClient(storage);
      await dio.post(
        ApiConstants.gpsPing,
        data: {
          'lat': position.latitude,
          'lng': position.longitude,
          'accuracy': position.accuracy,
          'isMock': position.isMocked,
        },
      );

      print('[BackgroundGPS] Location sent: ${position.latitude}, ${position.longitude}');
    } catch (e) {
      print('[BackgroundGPS] Send location error: $e');
    }
  }
}
