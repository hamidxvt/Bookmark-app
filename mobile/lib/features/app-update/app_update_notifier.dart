import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../core/network/dio_client.dart';
import 'app_update_service.dart';

// Provider for AppUpdateService
final appUpdateServiceProvider = Provider<AppUpdateService>((ref) {
  final dio = ref.watch(dioClientProvider);
  return AppUpdateService(dio);
});

// State for app update
class AppUpdateState {
  final AppVersion? availableVersion;
  final bool isChecking;
  final bool isDownloading;
  final int downloadProgress; // 0-100
  final String? error;
  final bool dismissed; // User dismissed the update notification

  AppUpdateState({
    this.availableVersion,
    this.isChecking = false,
    this.isDownloading = false,
    this.downloadProgress = 0,
    this.error,
    this.dismissed = false,
  });

  AppUpdateState copyWith({
    AppVersion? availableVersion,
    bool? isChecking,
    bool? isDownloading,
    int? downloadProgress,
    String? error,
    bool? dismissed,
  }) {
    return AppUpdateState(
      availableVersion: availableVersion ?? this.availableVersion,
      isChecking: isChecking ?? this.isChecking,
      isDownloading: isDownloading ?? this.isDownloading,
      downloadProgress: downloadProgress ?? this.downloadProgress,
      error: error ?? this.error,
      dismissed: dismissed ?? this.dismissed,
    );
  }
}

// Notifier for app update state
class AppUpdateNotifier extends StateNotifier<AppUpdateState> {
  final AppUpdateService _service;

  AppUpdateNotifier(this._service) : super(AppUpdateState());

  /// Check for available updates
  Future<void> checkForUpdates() async {
    state = state.copyWith(isChecking: true, error: null);
    try {
      final version = await _service.checkForUpdates();
      if (version != null) {
        state = state.copyWith(availableVersion: version, isChecking: false);
      } else {
        state = state.copyWith(isChecking: false);
      }
    } catch (e) {
      state = state.copyWith(isChecking: false, error: e.toString());
    }
  }

  /// Download and install update
  Future<bool> downloadAndInstall() async {
    if (state.availableVersion == null) return false;

    state = state.copyWith(isDownloading: true, error: null);
    try {
      final success = await _service.performUpdate(state.availableVersion!);
      if (success) {
        state = state.copyWith(isDownloading: false);
        return true;
      } else {
        state = state.copyWith(
          isDownloading: false,
          error: 'Failed to complete update',
        );
        return false;
      }
    } catch (e) {
      state = state.copyWith(isDownloading: false, error: e.toString());
      return false;
    }
  }

  /// User dismissed the update notification
  void dismissUpdate() {
    state = state.copyWith(dismissed: true);
  }

  /// Reset dismissed flag (useful for testing or force showing again)
  void resetDismissed() {
    state = state.copyWith(dismissed: false);
  }

  /// Clear error message
  void clearError() {
    state = state.copyWith(error: null);
  }
}

// Riverpod provider for app update state
final appUpdateProvider = StateNotifierProvider<AppUpdateNotifier, AppUpdateState>((ref) {
  final service = ref.watch(appUpdateServiceProvider);
  return AppUpdateNotifier(service);
});

// Provider to check updates (auto-runs on app startup)
final checkUpdatesOnStartupProvider = FutureProvider<void>((ref) async {
  final notifier = ref.watch(appUpdateProvider.notifier);
  await notifier.checkForUpdates();
});
