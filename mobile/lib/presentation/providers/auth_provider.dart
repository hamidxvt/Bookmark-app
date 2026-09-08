import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../domain/entities/user.dart';
import '../../core/storage/secure_storage.dart';
import '../../core/network/dio_client.dart';
import '../../core/constants/api_constants.dart';

// ─── State ───────────────────────────────────────────────────────────────────

class AuthState {
  final User? user;
  final bool isLoading;
  final String? error;

  const AuthState({this.user, this.isLoading = false, this.error});

  bool get isAuthenticated => user != null;

  AuthState copyWith({User? user, bool? isLoading, String? error}) {
    return AuthState(
      user: user ?? this.user,
      isLoading: isLoading ?? this.isLoading,
      error: error,
    );
  }
}

// ─── Notifier ────────────────────────────────────────────────────────────────

class AuthNotifier extends StateNotifier<AuthState> {
  final DioClient _dio;
  final SecureStorage _storage;

  AuthNotifier(this._dio, this._storage) : super(const AuthState()) {
    _tryRestoreSession();
  }

  Future<void> _tryRestoreSession() async {
    final token = await _storage.getToken();
    if (token == null) return;
    // Token exists — treat as authenticated. User data will be fetched lazily.
    state = state.copyWith(isLoading: false);
  }

  Future<void> login(String email, String password) async {
    state = state.copyWith(isLoading: true, error: null);
    try {
      final res = await _dio.post(ApiConstants.login,
          data: {'email': email, 'password': password});
      final data = res.data['data'];
      await _storage.saveToken(data['token'] as String);
      final user = User.fromJson(data['user'] as Map<String, dynamic>);
      await _storage.saveUserId(user.id);
      await _storage.saveUserRole(user.role);
      state = state.copyWith(user: user, isLoading: false);
    } on ApiException catch (e) {
      state = state.copyWith(isLoading: false, error: e.message);
    } catch (e) {
      state = state.copyWith(
          isLoading: false, error: 'Connection failed. Check your network.');
    }
  }

  Future<void> logout() async {
    await _storage.clearAll();
    state = const AuthState();
  }
}

// ─── Provider ────────────────────────────────────────────────────────────────

final authProvider = StateNotifierProvider<AuthNotifier, AuthState>((ref) {
  return AuthNotifier(
    ref.read(dioClientProvider),
    ref.read(secureStorageProvider),
  );
});
