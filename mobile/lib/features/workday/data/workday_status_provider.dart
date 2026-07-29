import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';

class WorkdayStatus {
  final bool dayStarted;
  final bool dayEnded;
  final bool isLoading;

  const WorkdayStatus({
    this.dayStarted = false,
    this.dayEnded = false,
    this.isLoading = true,
  });

  bool get canStartDay => !dayStarted;
  bool get canEndDay => dayStarted && !dayEnded;
}

class WorkdayStatusNotifier extends AutoDisposeAsyncNotifier<WorkdayStatus> {
  @override
  Future<WorkdayStatus> build() => _fetch();

  Future<WorkdayStatus> _fetch() async {
    try {
      final dio = ref.read(dioClientProvider);
      final res = await dio.get(ApiConstants.workdayStatus);
      final data = res.data['data'];
      if (data == null) return const WorkdayStatus(dayStarted: false, dayEnded: false, isLoading: false);

      final startAt = data['startAt'];
      final endAt = data['endAt'];
      return WorkdayStatus(
        dayStarted: startAt != null,
        dayEnded: endAt != null,
        isLoading: false,
      );
    } catch (_) {
      return const WorkdayStatus(dayStarted: false, dayEnded: false, isLoading: false);
    }
  }

  Future<void> refresh() async {
    state = const AsyncLoading();
    state = AsyncData(await _fetch());
  }
}

final workdayStatusProvider =
    AsyncNotifierProvider.autoDispose<WorkdayStatusNotifier, WorkdayStatus>(
        WorkdayStatusNotifier.new);
