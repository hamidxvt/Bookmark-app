import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../core/network/dio_client.dart';
import 'leaves_repository.dart';

final leavesRepositoryProvider = Provider((ref) {
  final dio = ref.watch(dioClientProvider);
  return LeavesRepository(dio);
});

final leaveBalancesProvider = FutureProvider((ref) async {
  final repo = ref.watch(leavesRepositoryProvider);
  return repo.getBalances();
});

final leaveHistoryProvider = FutureProvider((ref) async {
  final repo = ref.watch(leavesRepositoryProvider);
  return repo.getHistory();
});

final submitLeaveProvider = FutureProvider.family<void, ({String type, DateTime from, DateTime to, String reason})>((ref, params) async {
  final repo = ref.watch(leavesRepositoryProvider);
  await repo.submitRequest(
    type: params.type,
    from: params.from,
    to: params.to,
    reason: params.reason,
  );
  ref.invalidate(leaveHistoryProvider);
  ref.invalidate(leaveBalancesProvider);
});
