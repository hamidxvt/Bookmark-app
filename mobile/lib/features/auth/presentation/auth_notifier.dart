import 'package:dio/dio.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../data/auth_repository.dart';
import '../domain/auth_models.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/utils/error_messages.dart';

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
    } on DioException catch (e) {
      // Raw Dio errors get routed through the same friendly-message pipeline
      // as ApiException so wrong password shows "Wrong email or password"
      // instead of "Cannot reach server".
      state = state.copyWith(isLoading: false, error: ApiException.fromDio(e).message);
    } catch (e) {
      state = state.copyWith(isLoading: false, error: friendlyError(e));
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
