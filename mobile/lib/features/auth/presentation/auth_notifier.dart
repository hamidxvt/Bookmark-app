import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../data/auth_repository.dart';
import '../domain/auth_models.dart';
import '../../../core/network/dio_client.dart';

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
    } catch (_) {
      state = state.copyWith(isRestoring: false);
    }
  }

  Future<void> login(String email, String password) async {
    state = state.copyWith(isLoading: true, clearError: true);
    try {
      final user = await _repo.login(email, password);
      state = state.copyWith(user: user, isLoading: false);
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
    state = const AuthState(isRestoring: false);
  }

  void clearError() {
    state = state.copyWith(clearError: true);
  }
}

final authProvider = NotifierProvider<AuthNotifier, AuthState>(AuthNotifier.new);
