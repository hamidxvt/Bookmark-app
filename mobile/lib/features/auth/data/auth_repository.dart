import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';
import '../../../core/storage/secure_storage.dart';
import '../domain/auth_models.dart';

class AuthRepository {
  final DioClient _dio;
  final SecureStorage _storage;

  AuthRepository(this._dio, this._storage);

  Future<AuthUser> login(String email, String password) async {
    final res = await _dio.post(
      ApiConstants.login,
      data: {'email': email, 'password': password},
    );
    final data = res.data['data'] as Map<String, dynamic>;
    final user = AuthUser.fromJson(data['user'] as Map<String, dynamic>);
    await _storage.saveToken(data['token'] as String);
    await _storage.saveUserId(user.id);
    await _storage.saveUserRole(user.role);
    await _storage.saveUserName(user.name);
    await _storage.saveUserEmail(user.email);
    return user;
  }

  Future<AuthUser?> restoreSession() async {
    final token = await _storage.getToken();
    if (token == null) return null;
    final id = await _storage.getUserId();
    final role = await _storage.getUserRole();
    final name = await _storage.getUserName();
    final email = await _storage.getUserEmail();
    if (id == null || role == null) return null;
    return AuthUser(
      id: id,
      name: name ?? '',
      email: email ?? '',
      role: role,
    );
  }

  Future<void> logout() => _storage.clearAll();
}

final authRepositoryProvider = Provider<AuthRepository>((ref) {
  return AuthRepository(
    ref.read(dioClientProvider),
    ref.read(secureStorageProvider),
  );
});
