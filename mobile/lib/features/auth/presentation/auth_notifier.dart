import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:firebase_messaging/firebase_messaging.dart';

import '../data/auth_repository.dart';
import '../domain/auth_models.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/services/fcm_service.dart';

class AuthNotifier extends Notifier<AuthState> {
  @override
  AuthState build() {
    _restore();
    return const AuthState(isRestoring: true);
  }

  AuthRepository get _repo => ref.read(authRepositoryProvider);

  Future<void> _restore() async {
    try {
      // Skip session restore on web for now (flutter_secure_storage not available)
      if (kIsWeb) {
        state = state.copyWith(isRestoring: false);
        return;
      }
      
      final user = await _repo.restoreSession();
      state = state.copyWith(user: user, isRestoring: false);
      if (user != null) _initFcm();
    } catch (_) {
      state = state.copyWith(isRestoring: false);
    }
  }

  /// Register the device with FCM and subscribe to the officers topic.
  /// Fire-and-forget so any failure never blocks the auth flow.
  void _initFcm() {
    Future(() async {
      try {
        await ref.read(fcmServiceProvider).initialize();
        await FirebaseMessaging.instance.subscribeToTopic('officers');
      } catch (e) {
        debugPrint('[auth] FCM init failed: $e');
      }
    });
  }

  Future<void> login(String email, String password) async {
    state = state.copyWith(isLoading: true, clearError: true);
    try {
      final user = await _repo.login(email, password);
      state = state.copyWith(user: user, isLoading: false);
      _initFcm();
    } on ApiException catch (e) {
      state = state.copyWith(isLoading: false, error: e.message);
    } catch (_) {
      state = state.copyWith(
        isLoading: false,
        error: 'Cannot reach server. Make sure the backend is running.',
      );
    }
  }

  Future<void> logout() async {
    if (!kIsWeb) {
      await _repo.logout();
    }
    // Best-effort teardown so a shared device stops receiving pushes for
    // the previous user.
    try {
      await FirebaseMessaging.instance.unsubscribeFromTopic('officers');
      await FirebaseMessaging.instance.deleteToken();
      ref.read(fcmServiceProvider).dispose();
    } catch (_) {}
    state = const AuthState(isRestoring: false);
  }

  void clearError() {
    state = state.copyWith(clearError: true);
  }
}

final authProvider = NotifierProvider<AuthNotifier, AuthState>(AuthNotifier.new);
