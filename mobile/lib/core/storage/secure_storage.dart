import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

final secureStorageProvider = Provider<SecureStorage>((ref) => SecureStorage());

/// Persists auth data using:
/// - flutter_secure_storage on Android/iOS (survives app close)
/// - In-memory map on web (tab session only)
class SecureStorage {
  static const _tokenKey    = 'auth_token';
  static const _userIdKey   = 'user_id';
  static const _userRoleKey = 'user_role';
  static const _userNameKey = 'user_name';
  static const _userEmailKey = 'user_email';

  // Flutter secure storage (Android Keystore / iOS Keychain)
  static const _secure = FlutterSecureStorage(
    aOptions: AndroidOptions(encryptedSharedPreferences: true),
  );

  // Web fallback — in-memory only
  static final Map<String, String> _mem = {};

  Future<void> _write(String key, String value) async {
    if (kIsWeb) {
      _mem[key] = value;
    } else {
      await _secure.write(key: key, value: value);
    }
  }

  Future<String?> _read(String key) async {
    if (kIsWeb) return _mem[key];
    return _secure.read(key: key);
  }

  Future<void> _delete(String key) async {
    if (kIsWeb) {
      _mem.remove(key);
    } else {
      await _secure.delete(key: key);
    }
  }

  // ── Token ────────────────────────────────────────────────────────────────
  Future<void> saveToken(String token)  => _write(_tokenKey, token);
  Future<String?> getToken()            => _read(_tokenKey);
  Future<void> clearToken()             => _delete(_tokenKey);

  // ── User fields ──────────────────────────────────────────────────────────
  Future<void> saveUserId(int id)       => _write(_userIdKey, id.toString());
  Future<int?> getUserId() async {
    final v = await _read(_userIdKey);
    return v != null ? int.tryParse(v) : null;
  }

  Future<void> saveUserRole(String role)   => _write(_userRoleKey, role);
  Future<String?> getUserRole()            => _read(_userRoleKey);

  Future<void> saveUserName(String name)   => _write(_userNameKey, name);
  Future<String?> getUserName()            => _read(_userNameKey);

  Future<void> saveUserEmail(String email) => _write(_userEmailKey, email);
  Future<String?> getUserEmail()           => _read(_userEmailKey);

  // ── Clear all ────────────────────────────────────────────────────────────
  Future<void> clearAll() async {
    if (kIsWeb) {
      _mem.clear();
    } else {
      await _secure.deleteAll();
    }
  }
}
