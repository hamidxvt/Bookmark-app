import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:pretty_dio_logger/pretty_dio_logger.dart';
import 'package:path_provider/path_provider.dart';

import '../constants/api_constants.dart';
import '../storage/secure_storage.dart';

final dioClientProvider = Provider<DioClient>((ref) {
  return DioClient(ref.read(secureStorageProvider));
});

class DioClient {
  late final Dio _dio;
  final SecureStorage _storage;

  DioClient(this._storage) {
    _dio = Dio(BaseOptions(
      baseUrl: ApiConstants.baseUrl,
      connectTimeout: const Duration(seconds: 15),
      receiveTimeout: const Duration(seconds: 15),
      headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
    ));

    _dio.interceptors.add(_authInterceptor());
    _dio.interceptors.add(PrettyDioLogger(
      requestHeader: false,
      requestBody: true,
      responseBody: true,
      error: true,
    ));
  }

  Interceptor _authInterceptor() {
    return InterceptorsWrapper(
      onRequest: (options, handler) async {
        final token = await _storage.getToken();
        if (token != null) {
          options.headers['Authorization'] = 'Bearer $token';
        }
        handler.next(options);
      },
      onError: (error, handler) {
        if (error.response?.statusCode == 401) {
          _storage.clearToken();
        }
        handler.next(error);
      },
    );
  }

  Future<Response> get(String path, {Map<String, dynamic>? params}) =>
      _dio.get(path, queryParameters: params);

  Future<Response> post(String path, {dynamic data}) =>
      _dio.post(path, data: data);

  Future<Response> put(String path, {dynamic data}) =>
      _dio.put(path, data: data);

  Future<Response> patch(String path, {dynamic data}) =>
      _dio.patch(path, data: data);

  Future<Response> delete(String path, {dynamic data}) =>
      _dio.delete(path, data: data);

  Future<String?> download(
    String urlPath, {
    required Function(int received, int total) onReceiveProgress,
  }) async {
    try {
      final tempDir = await getTemporaryDirectory();
      final fileName = urlPath.split('/').last;
      final savePath = '${tempDir.path}/$fileName';

      await _dio.download(
        urlPath,
        savePath,
        onReceiveProgress: onReceiveProgress,
      );

      return savePath;
    } catch (e) {
      return null;
    }
  }
}

class ApiException implements Exception {
  final String code;
  final String message;
  final int statusCode;

  const ApiException({required this.code, required this.message, required this.statusCode});

  /// Convert any DioException into a user-facing message.
  /// Preference order:
  ///   1. Backend's own error text (string or `{code, message}`).
  ///   2. Well-known HTTP status codes (401, 403, 404, 5xx).
  ///   3. Dio's own connection/timeout categories.
  ///   4. Generic fallback — never leaks Dio's internal text.
  factory ApiException.fromDio(DioException e) {
    final code = e.response?.statusCode ?? 0;
    final data = e.response?.data;

    // Backend contract: { success: false, error: "..." } or
    //                   { success: false, error: { code, message } }
    final errField = data is Map ? data['error'] : null;
    String? backendMsg;
    String? backendCode;
    if (errField is String && errField.trim().isNotEmpty) {
      backendMsg = errField.trim();
    } else if (errField is Map) {
      backendMsg = (errField['message'] as String?)?.trim();
      backendCode = errField['code'] as String?;
    }

    String msg;
    if (backendMsg != null && backendMsg.isNotEmpty) {
      msg = backendMsg;
    } else if (code == 401) {
      // Auth failures land here for the login screen (wrong password)
      // and for expired tokens on any protected endpoint.
      msg = 'Wrong email or password';
    } else if (code == 403) {
      msg = 'Your account is not approved yet. Please contact your manager.';
    } else if (code == 404) {
      msg = 'Not found — the record may have been removed.';
    } else if (code == 409) {
      msg = 'Already exists or conflicts with an existing record.';
    } else if (code == 429) {
      msg = 'Too many attempts — please wait a minute and try again.';
    } else if (code >= 500 && code < 600) {
      msg = 'Server error — please try again in a moment.';
    } else if (e.type == DioExceptionType.connectionTimeout ||
        e.type == DioExceptionType.sendTimeout ||
        e.type == DioExceptionType.receiveTimeout) {
      msg = 'Slow connection — please check your internet and try again.';
    } else if (e.type == DioExceptionType.connectionError) {
      msg = 'No internet — please check your connection and try again.';
    } else if (e.type == DioExceptionType.cancel) {
      msg = 'Cancelled.';
    } else {
      // Last-resort generic — never surface Dio's raw error string.
      msg = 'Something went wrong. Please try again.';
    }

    return ApiException(
      statusCode: code,
      code: backendCode ?? _codeForStatus(code, e.type),
      message: msg,
    );
  }

  static String _codeForStatus(int status, DioExceptionType type) {
    if (status == 401) return 'UNAUTHORIZED';
    if (status == 403) return 'FORBIDDEN';
    if (status == 404) return 'NOT_FOUND';
    if (status == 409) return 'CONFLICT';
    if (status == 429) return 'RATE_LIMITED';
    if (status >= 500) return 'SERVER_ERROR';
    if (type == DioExceptionType.connectionError) return 'NO_INTERNET';
    if (type == DioExceptionType.connectionTimeout ||
        type == DioExceptionType.sendTimeout ||
        type == DioExceptionType.receiveTimeout) return 'TIMEOUT';
    return 'ERROR';
  }

  @override
  String toString() => 'ApiException($statusCode): $message';
}
