import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:geolocator/geolocator.dart';

import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';
import '../../../core/services/gps_service.dart';
import '../../../core/services/offline_queue_service.dart';
import 'visit_models.dart';

/// Result of geofence check
class GeofenceResult {
  final bool allowed;
  final double distanceMeters;
  final String? error;

  const GeofenceResult({
    required this.allowed,
    required this.distanceMeters,
    this.error,
  });
}

class VisitRepository {
  final DioClient _dio;
  final GpsService _gps;
  final OfflineQueueService _queue;

  VisitRepository(this._dio, this._gps, this._queue);

  Future<List<Visit>> getTodayVisits() async {
    final res = await _dio.get(ApiConstants.todayVisits);
    final raw = res.data['data'];
    final list = raw is List ? raw : (raw is Map ? (raw['visits'] as List? ?? []) : []);
    return list.map((e) => Visit.fromJson(e as Map<String, dynamic>)).toList();
  }

  /// Check if current GPS position is within 200m of the visit customer location.
  /// If customer has no coordinates, we allow the visit (no geofence configured).
  Future<GeofenceResult> checkGeofence(Visit visit) async {
    final customerLat = visit.customerLat;
    final customerLng = visit.customerLng;

    // No coordinates stored for this customer — skip enforcement
    if (customerLat == null || customerLng == null) {
      return const GeofenceResult(allowed: true, distanceMeters: 0);
    }

    final position = await _gps.getCurrentPosition();
    if (position == null) {
      // Can't get GPS — allow with warning (don't block officer)
      return const GeofenceResult(allowed: true, distanceMeters: -1, error: 'GPS unavailable');
    }

    final distance = Geolocator.distanceBetween(
      position.latitude,
      position.longitude,
      customerLat,
      customerLng,
    );

    return GeofenceResult(
      allowed: distance <= 200,
      distanceMeters: distance,
    );
  }

  Future<void> startVisit(int id) async {
    final position = await _gps.getCurrentPosition();
    final data = {
      if (position != null) 'lat': position.latitude,
      if (position != null) 'lng': position.longitude,
    };
    try {
      await _dio.post(ApiConstants.visitStart(id), data: data);
    } on DioException catch (e) {
      if (e.type == DioExceptionType.connectionError) {
        await _queue.enqueue(QueuedRequest(
          method: 'POST',
          path: ApiConstants.visitStart(id),
          data: data,
          queuedAt: DateTime.now(),
        ));
      } else {
        rethrow;
      }
    }
  }

  Future<void> completeVisit(int id, Map<String, dynamic> payload) async {
    try {
      await _dio.post(ApiConstants.visitComplete(id), data: payload);
    } on DioException catch (e) {
      if (e.type == DioExceptionType.connectionError) {
        await _queue.enqueue(QueuedRequest(
          method: 'POST',
          path: ApiConstants.visitComplete(id),
          data: payload,
          queuedAt: DateTime.now(),
        ));
      } else {
        rethrow;
      }
    }
  }

  Future<Map<String, dynamic>?> getVisitDetail(int id) async {
    try {
      final res = await _dio.get('/visits/$id');
      final data = res.data['data'];
      if (data is Map<String, dynamic>) return data;
      // Fallback: search today's visits
      final listRes = await _dio.get(ApiConstants.todayVisits);
      final list = listRes.data['data'] as List? ?? [];
      return list.firstWhere((e) => e['id'] == id, orElse: () => null);
    } catch (_) {
      return null;
    }
  }

  Future<void> markMissed(int id, String reason, {List<String>? photoBase64}) async {
    final data = {
      'reason': reason,
      if (photoBase64 != null && photoBase64.isNotEmpty) 'photoUrls': photoBase64,
    };
    try {
      await _dio.post(ApiConstants.visitMiss(id), data: data);
    } on DioException catch (e) {
      if (e.type == DioExceptionType.connectionError) {
        await _queue.enqueue(QueuedRequest(
          method: 'POST',
          path: ApiConstants.visitMiss(id),
          data: data,
          queuedAt: DateTime.now(),
        ));
      } else {
        rethrow;
      }
    }
  }
}

final visitRepositoryProvider = Provider<VisitRepository>((ref) {
  return VisitRepository(
    ref.read(dioClientProvider),
    ref.read(gpsServiceProvider),
    ref.read(offlineQueueProvider),
  );
});

// ── Visit List Notifier ───────────────────────────────────────────────────────

class VisitListNotifier extends AsyncNotifier<List<Visit>> {
  @override
  Future<List<Visit>> build() => _load();

  Future<List<Visit>> _load() async {
    return ref.read(visitRepositoryProvider).getTodayVisits();
  }

  Future<void> refresh() async {
    state = const AsyncLoading();
    state = await AsyncValue.guard(_load);
  }
}

final visitListProvider =
    AsyncNotifierProvider<VisitListNotifier, List<Visit>>(VisitListNotifier.new);
