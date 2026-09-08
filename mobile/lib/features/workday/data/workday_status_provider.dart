import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';

class WorkdayStatus {
  final bool dayStarted;
  final bool dayEnded;
  final bool isLoading;
  final int? plannedVisits;

  const WorkdayStatus({
    this.dayStarted = false,
    this.dayEnded = false,
    this.isLoading = true,
    this.plannedVisits,
  });

  bool get canStartDay => !dayStarted;
  bool get canEndDay => dayStarted && !dayEnded;
}

class WorkdayStatusNotifier extends AutoDisposeAsyncNotifier<WorkdayStatus> {
  @override
  Future<WorkdayStatus> build() => _fetch();

  Future<WorkdayStatus> _fetch() async {
    final dio = ref.read(dioClientProvider);
    final res = await dio.get(ApiConstants.workdayStatus);
    final data = res.data['data'];
    if (data == null) return const WorkdayStatus(dayStarted: false, dayEnded: false, isLoading: false);

    return WorkdayStatus(
      dayStarted: data['startAt'] != null,
      dayEnded: data['endAt'] != null,
      isLoading: false,
      plannedVisits: (data['visitCount'] as num?)?.toInt(),
    );
  }

  Future<void> refresh() async {
    state = const AsyncLoading();
    state = await AsyncValue.guard(_fetch);
  }
}

final workdayStatusProvider =
    AsyncNotifierProvider.autoDispose<WorkdayStatusNotifier, WorkdayStatus>(
        WorkdayStatusNotifier.new);
