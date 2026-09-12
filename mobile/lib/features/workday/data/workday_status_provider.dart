import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';

class WorkdayStatus {
  final bool dayStarted;
  final bool dayEnded;
  final bool isLoading;
  final int? plannedVisits;

  /// Actual timestamps returned by the backend. The dashboard's "SHIFT
  /// STARTED HH:mm" card reads these — before this change the field was
  /// missing entirely so the UI fell back to DateTime.now(), showing the
  /// current wall-clock time instead of the real start time.
  final DateTime? startAt;
  final DateTime? endAt;

  const WorkdayStatus({
    this.dayStarted = false,
    this.dayEnded = false,
    this.isLoading = true,
    this.plannedVisits,
    this.startAt,
    this.endAt,
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
    if (data == null) {
      return const WorkdayStatus(dayStarted: false, dayEnded: false, isLoading: false);
    }

    return WorkdayStatus(
      dayStarted: data['startAt'] != null,
      dayEnded: data['endAt'] != null,
      isLoading: false,
      plannedVisits: (data['visitCount'] as num?)?.toInt(),
      startAt: _parseDate(data['startAt']),
      endAt: _parseDate(data['endAt']),
    );
  }

  Future<void> refresh() async {
    state = const AsyncLoading();
    state = await AsyncValue.guard(_fetch);
  }
}

DateTime? _parseDate(dynamic v) {
  if (v is String && v.isNotEmpty) {
    // Server sends UTC ISO strings; convert to local so displayed
    // "SHIFT STARTED 8:14 AM" matches the officer's watch.
    final parsed = DateTime.tryParse(v);
    return parsed?.toLocal();
  }
  return null;
}

final workdayStatusProvider =
    AsyncNotifierProvider.autoDispose<WorkdayStatusNotifier, WorkdayStatus>(
        WorkdayStatusNotifier.new);
