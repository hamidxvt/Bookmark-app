import 'dart:io';
import 'package:flutter/foundation.dart';
import 'package:package_info_plus/package_info_plus.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:path_provider/path_provider.dart';
import '../../core/network/dio_client.dart';
import '../../core/constants/api_constants.dart';

class AppVersion {
  final int versionCode;
  final String versionName;
  final DateTime releaseDate;
  final String downloadUrl;
  final String releaseNotes;
  final bool isMandatory;
  final int? minVersionCode;
  final String? changelogText;
  final bool updateAvailable;

  AppVersion({
    required this.versionCode,
    required this.versionName,
    required this.releaseDate,
    required this.downloadUrl,
    required this.releaseNotes,
    required this.isMandatory,
    this.minVersionCode,
    this.changelogText,
    required this.updateAvailable,
  });

  factory AppVersion.fromJson(Map<String, dynamic> json) {
    return AppVersion(
      versionCode: json['versionCode'] as int? ?? 0,
      versionName: json['versionName'] as String? ?? '',
      releaseDate: DateTime.tryParse(json['releaseDate'] as String? ?? '') ?? DateTime.now(),
      downloadUrl: json['downloadUrl'] as String? ?? '',
      releaseNotes: json['releaseNotes'] as String? ?? '',
      isMandatory: json['isMandatory'] as bool? ?? false,
      minVersionCode: json['minVersionCode'] as int?,
      changelogText: json['changelogText'] as String?,
      updateAvailable: json['updateAvailable'] as bool? ?? false,
    );
  }
}

class AppUpdateService {
  final DioClient _dio;
  
  AppUpdateService(this._dio);

  /// Get current app version code from package info
  Future<int> getCurrentVersionCode() async {
    try {
      final info = await PackageInfo.fromPlatform();
      final buildNumber = int.tryParse(info.buildNumber) ?? 0;
      debugPrint('[AppUpdate] Current version code: $buildNumber');
      return buildNumber;
    } catch (e) {
      debugPrint('[AppUpdate] Failed to get current version: $e');
      return 0;
    }
  }

  /// Check for available app updates
  /// Returns AppVersion object with updateAvailable flag
  Future<AppVersion?> checkForUpdates() async {
    try {
      final currentVersionCode = await getCurrentVersionCode();
      
      debugPrint('[AppUpdate] Checking for updates... Current: $currentVersionCode');
      
      final res = await _dio.get(
        ApiConstants.appVersion,
        params: {
          'current_version_code': currentVersionCode,
          'platform': 'android',
        },
      );

      if (res.statusCode != 200 || res.data == null) {
        debugPrint('[AppUpdate] Failed: Bad response');
        return null;
      }

      final json = res.data;
      if (json is! Map<String, dynamic>) {
        debugPrint('[AppUpdate] Failed: Invalid response format');
        return null;
      }

      // Check if it's a successful response
      if (json['success'] != true) {
        debugPrint('[AppUpdate] Failed: ${json['error']}');
        return null;
      }

      final data = json['data'] as Map<String, dynamic>?;
      if (data == null) {
        debugPrint('[AppUpdate] Failed: No data in response');
        return null;
      }

      final updateAvailable = data['updateAvailable'] as bool? ?? false;
      
      if (!updateAvailable) {
        debugPrint('[AppUpdate] No updates available');
        return null;
      }

      final latestData = data['latest'] as Map<String, dynamic>?;
      if (latestData == null) {
        debugPrint('[AppUpdate] Failed: No latest version info');
        return null;
      }

      final version = AppVersion(
        versionCode: latestData['versionCode'] as int? ?? 0,
        versionName: latestData['versionName'] as String? ?? '',
        releaseDate: DateTime.tryParse(latestData['releaseDate'] as String? ?? '') ?? DateTime.now(),
        downloadUrl: latestData['downloadUrl'] as String? ?? '',
        releaseNotes: latestData['releaseNotes'] as String? ?? '',
        isMandatory: data['forceUpdate'] as bool? ?? false,
        minVersionCode: latestData['minVersionCode'] as int?,
        changelogText: latestData['changelogText'] as String?,
        updateAvailable: true,
      );

      debugPrint('[AppUpdate] Update available: ${version.versionName} (mandatory: ${version.isMandatory})');
      return version;
    } on Exception catch (e) {
      debugPrint('[AppUpdate] Exception: $e');
      return null;
    }
  }

  /// Download APK from URL (returns path to downloaded file)
  Future<String?> downloadApk(String downloadUrl) async {
    try {
      debugPrint('[AppUpdate] Starting download: $downloadUrl');

      // For local file URLs, just return the path
      if (downloadUrl.startsWith('/')) {
        debugPrint('[AppUpdate] Local file, skipping download: $downloadUrl');
        return downloadUrl;
      }

      // Get temp directory for download
      final tempDir = await getTemporaryDirectory();
      final fileName = 'bookmark_update.apk';
      final savePath = '${tempDir.path}/$fileName';

      debugPrint('[AppUpdate] Saving to: $savePath');

      // For remote URLs, use DioClient to download
      final response = await _dio.download(
        downloadUrl,
        onReceiveProgress: (received, total) {
          if (total != -1) {
            final progress = (received / total * 100).toStringAsFixed(0);
            debugPrint('[AppUpdate] Download progress: $progress%');
          }
        },
      );

      if (response != null && File(response).existsSync()) {
        debugPrint('[AppUpdate] Download completed: $response');
        return response;
      }

      debugPrint('[AppUpdate] Download failed: invalid response');
      return null;
    } catch (e) {
      debugPrint('[AppUpdate] Download exception: $e');
      return null;
    }
  }

  /// Install APK (Android-specific)
  /// Opens the Android package installer
  Future<bool> installApk(String apkPath) async {
    try {
      if (!Platform.isAndroid) {
        debugPrint('[AppUpdate] Install not supported on this platform');
        return false;
      }

      debugPrint('[AppUpdate] Installing APK: $apkPath');

      // On Android, we need to open the file with the default installer
      // This is typically done via Intent and the system handles it
      // For Flutter, we use url_launcher with file:// scheme
      final fileUri = Uri.parse('file://$apkPath');
      
      // Attempt to launch with file manager
      if (await canLaunchUrl(fileUri)) {
        await launchUrl(fileUri);
        debugPrint('[AppUpdate] Opened file with system app');
        return true;
      }

      debugPrint('[AppUpdate] Cannot launch APK installer');
      return false;
    } catch (e) {
      debugPrint('[AppUpdate] Install exception: $e');
      return false;
    }
  }

  /// Complete update flow: check → download → install
  /// Returns true if successful, false otherwise
  Future<bool> performUpdate(AppVersion version) async {
    try {
      debugPrint('[AppUpdate] Performing update flow for ${version.versionName}');

      // Download APK
      final apkPath = await downloadApk(version.downloadUrl);
      if (apkPath == null) {
        debugPrint('[AppUpdate] Download failed');
        return false;
      }

      // Install APK
      final installed = await installApk(apkPath);
      if (!installed) {
        debugPrint('[AppUpdate] Install failed');
        return false;
      }

      debugPrint('[AppUpdate] Update flow completed successfully');
      return true;
    } catch (e) {
      debugPrint('[AppUpdate] Update flow exception: $e');
      return false;
    }
  }
}
