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

  factory ApiException.fromDio(DioException e) {
    final data = e.response?.data;
    // Support both { error: "string" } and { error: { code, message } } formats
    final errField = data?['error'];
    final msg = errField is String
        ? errField
        : (errField is Map ? errField['message'] : null) ?? e.message ?? 'Something went wrong';
    return ApiException(
      statusCode: e.response?.statusCode ?? 0,
      code: (errField is Map ? errField['code'] : null) ?? 'UNKNOWN_ERROR',
      message: msg,
    );
  }

  @override
  String toString() => 'ApiException($statusCode): $message';
}
