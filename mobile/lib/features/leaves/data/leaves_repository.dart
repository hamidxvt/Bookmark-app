import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';

class LeaveBalance {
  final int totalDays;
  final int usedDays;
  final String type;

  int get remainingDays => totalDays - usedDays;

  LeaveBalance({
    required this.totalDays,
    required this.usedDays,
    required this.type,
  });

  factory LeaveBalance.fromJson(Map<String, dynamic> json) {
    return LeaveBalance(
      totalDays: json['totalDays'] ?? 0,
      usedDays: json['usedDays'] ?? 0,
      type: json['type'] ?? 'casual',
    );
  }
}

class LeaveRequest {
  final int id;
  final String type;
  final DateTime from;
  final DateTime to;
  final String reason;
  final String status; // pending, approved, rejected
  final DateTime createdAt;

  int get days => to.difference(from).inDays + 1;

  LeaveRequest({
    required this.id,
    required this.type,
    required this.from,
    required this.to,
    required this.reason,
    required this.status,
    required this.createdAt,
  });

  factory LeaveRequest.fromJson(Map<String, dynamic> json) {
    return LeaveRequest(
      id: json['id'] ?? 0,
      type: json['type'] ?? 'casual',
      from: DateTime.tryParse(json['from'] ?? '') ?? DateTime.now(),
      to: DateTime.tryParse(json['to'] ?? '') ?? DateTime.now(),
      reason: json['reason'] ?? '',
      status: json['status'] ?? 'pending',
      createdAt: DateTime.tryParse(json['createdAt'] ?? '') ?? DateTime.now(),
    );
  }
}

class LeavesRepository {
  final DioClient _dio;

  LeavesRepository(this._dio);

  Future<Map<String, LeaveBalance>> getBalances() async {
    final res = await _dio.get('${ApiConstants.myLeaves}/balance');
    final data = res.data as Map<String, dynamic>;
    if (!data['success']) throw Exception(data['error'] ?? 'Failed to fetch balances');

    final balances = <String, LeaveBalance>{};
    final list = data['data'] as List<dynamic>? ?? [];
    for (final item in list) {
      final balance = LeaveBalance.fromJson(item as Map<String, dynamic>);
      balances[balance.type.toLowerCase()] = balance;
    }
    return balances;
  }

  Future<List<LeaveRequest>> getHistory() async {
    final res = await _dio.get('${ApiConstants.myLeaves}/history');
    final data = res.data as Map<String, dynamic>;
    if (!data['success']) throw Exception(data['error'] ?? 'Failed to fetch history');

    final list = data['data'] as List<dynamic>? ?? [];
    return list.map((item) => LeaveRequest.fromJson(item as Map<String, dynamic>)).toList();
  }

  Future<void> submitRequest({
    required String type,
    required DateTime from,
    required DateTime to,
    required String reason,
  }) async {
    final res = await _dio.post(
      '${ApiConstants.myLeaves}/apply',
      data: {
        'type': type.toLowerCase(),
        'from': from.toIso8601String().split('T')[0],
        'to': to.toIso8601String().split('T')[0],
        'reason': reason,
      },
    );
    final data = res.data as Map<String, dynamic>;
    if (!data['success']) throw Exception(data['error'] ?? 'Failed to submit request');
  }
}
