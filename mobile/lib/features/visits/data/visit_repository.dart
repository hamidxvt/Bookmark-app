import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';
import 'visit_models.dart';

class VisitRepository {
  final DioClient _dio;
  VisitRepository(this._dio);

  Future<List<Visit>> getTodayVisits() async {
    final res = await _dio.get(ApiConstants.todayVisits);
    final list = res.data['data'] as List;
    return list.map((e) => Visit.fromJson(e as Map<String, dynamic>)).toList();
  }

  Future<void> completeVisit(int id, Map<String, dynamic> payload) async {
    await _dio.post(ApiConstants.visitComplete(id), data: payload);
  }

  Future<void> markMissed(int id, String reason) async {
    await _dio.post(ApiConstants.visitMiss(id), data: {'reason': reason});
  }
}

final visitRepositoryProvider = Provider<VisitRepository>((ref) {
  return VisitRepository(ref.read(dioClientProvider));
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
