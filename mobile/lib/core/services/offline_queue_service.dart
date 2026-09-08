/// offline_queue_service.dart
/// Queues API calls when offline and syncs them automatically on reconnection.
/// Uses shared_preferences to persist the queue across app restarts.

import 'dart:async';
import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:dio/dio.dart';

import '../network/dio_client.dart';

const _kQueueKey = 'offline_queue';

class QueuedRequest {
  final String method; // POST, PATCH, PUT
  final String path;
  final Map<String, dynamic>? data;
  final DateTime queuedAt;

  QueuedRequest({
    required this.method,
    required this.path,
    this.data,
    required this.queuedAt,
  });

  Map<String, dynamic> toJson() => {
        'method': method,
        'path': path,
        'data': data,
        'queuedAt': queuedAt.toIso8601String(),
      };

  factory QueuedRequest.fromJson(Map<String, dynamic> json) => QueuedRequest(
        method: json['method'] as String,
        path: json['path'] as String,
        data: json['data'] != null
            ? Map<String, dynamic>.from(json['data'] as Map)
            : null,
        queuedAt: DateTime.parse(json['queuedAt'] as String),
      );
}

class OfflineQueueService {
  final DioClient _dio;
  StreamSubscription? _connectivitySub;
  bool _isSyncing = false;

  OfflineQueueService(this._dio);

  /// Enqueue a request when offline
  Future<void> enqueue(QueuedRequest req) async {
    if (kIsWeb) return; // Web always has connectivity
    final prefs = await SharedPreferences.getInstance();
    final raw = prefs.getStringList(_kQueueKey) ?? [];
    raw.add(jsonEncode(req.toJson()));
    await prefs.setStringList(_kQueueKey, raw);
    debugPrint('[OfflineQueue] Queued: ${req.method} ${req.path}');
  }

  /// Start watching connectivity — sync queue when online
  void startMonitoring() {
    if (kIsWeb) return;
    _connectivitySub = Connectivity().onConnectivityChanged.listen((results) {
      final isOnline = results.any((r) => r != ConnectivityResult.none);
      if (isOnline && !_isSyncing) {
        _syncQueue();
      }
    });

    // Also try to sync immediately on start
    _syncQueue();
  }

  void stopMonitoring() {
    _connectivitySub?.cancel();
    _connectivitySub = null;
  }

  Future<void> _syncQueue() async {
    if (kIsWeb || _isSyncing) return;
    _isSyncing = true;

    try {
      final prefs = await SharedPreferences.getInstance();
      final raw = prefs.getStringList(_kQueueKey) ?? [];
      if (raw.isEmpty) return;

      debugPrint('[OfflineQueue] Syncing ${raw.length} queued requests...');
      final remaining = <String>[];

      for (final item in raw) {
        try {
          final req = QueuedRequest.fromJson(
              jsonDecode(item) as Map<String, dynamic>);

          switch (req.method.toUpperCase()) {
            case 'POST':
              await _dio.post(req.path, data: req.data);
              break;
            case 'PATCH':
              await _dio.patch(req.path, data: req.data);
              break;
            case 'PUT':
              await _dio.put(req.path, data: req.data);
              break;
          }

          debugPrint('[OfflineQueue] Synced: ${req.method} ${req.path}');
        } on DioException catch (e) {
          // Network still unavailable — keep in queue
          if (e.type == DioExceptionType.connectionError ||
              e.type == DioExceptionType.connectionTimeout) {
            remaining.add(item);
          }
          // 4xx errors (bad request) — discard to avoid infinite loop
        } catch (_) {
          // Unknown error — discard
        }
      }

      await prefs.setStringList(_kQueueKey, remaining);
      if (remaining.isEmpty) {
        debugPrint('[OfflineQueue] All queued requests synced ✅');
      } else {
        debugPrint('[OfflineQueue] ${remaining.length} requests still pending');
      }
    } finally {
      _isSyncing = false;
    }
  }

  /// Returns count of pending offline requests
  Future<int> get pendingCount async {
    if (kIsWeb) return 0;
    final prefs = await SharedPreferences.getInstance();
    return (prefs.getStringList(_kQueueKey) ?? []).length;
  }

  void dispose() => stopMonitoring();
}

final offlineQueueProvider = Provider<OfflineQueueService>((ref) {
  final service = OfflineQueueService(ref.read(dioClientProvider));
  service.startMonitoring();
  ref.onDispose(service.dispose);
  return service;
});
