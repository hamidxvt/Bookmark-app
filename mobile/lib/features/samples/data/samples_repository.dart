import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';

class SampleRequest {
  final int id;
  final String productName;
  final String? institutionName;
  final int quantity;
  final double totalValue;
  final String status;
  final int daysOut;
  final bool isRecovered;

  const SampleRequest({
    required this.id,
    required this.productName,
    this.institutionName,
    required this.quantity,
    required this.totalValue,
    required this.status,
    required this.daysOut,
    required this.isRecovered,
  });

  factory SampleRequest.fromJson(Map<String, dynamic> json) {
    final createdAt = DateTime.tryParse(json['createdAt'] ?? '') ?? DateTime.now();
    final daysOut = DateTime.now().difference(createdAt).inDays;
    return SampleRequest(
      id: json['id'] as int,
      productName: json['productName'] as String? ?? 'Unknown Product',
      institutionName: json['institutionName'] as String?,
      quantity: json['quantity'] as int? ?? 1,
      totalValue: (json['totalValue'] as num?)?.toDouble() ?? 0,
      status: json['status'] as String? ?? 'pending',
      daysOut: daysOut,
      isRecovered: json['isRecovered'] as bool? ?? false,
    );
  }
}

class SamplesData {
  final double budgetUsed;
  final double budgetTotal;
  final List<SampleRequest> active;
  final List<SampleRequest> recovered;

  const SamplesData({
    required this.budgetUsed,
    required this.budgetTotal,
    required this.active,
    required this.recovered,
  });

  double get budgetRemaining => budgetTotal - budgetUsed;
  double get budgetPercent =>
      budgetTotal > 0 ? (budgetUsed / budgetTotal).clamp(0.0, 1.0) : 0;
}

class SamplesRepository {
  final DioClient _dio;
  SamplesRepository(this._dio);

  Future<SamplesData> getMySamples() async {
    final res = await _dio.get(ApiConstants.mySamples);
    final data = res.data['data'] as Map<String, dynamic>;
    final requests = (data['requests'] as List<dynamic>? ?? [])
        .map((e) => SampleRequest.fromJson(e as Map<String, dynamic>))
        .toList();
    return SamplesData(
      budgetUsed: (data['budgetUsed'] as num?)?.toDouble() ?? 0,
      budgetTotal: (data['budgetTotal'] as num?)?.toDouble() ?? 50000,
      active: requests.where((r) => !r.isRecovered).toList(),
      recovered: requests.where((r) => r.isRecovered).toList(),
    );
  }

  Future<void> requestSamples({
    required int productId,
    required int quantity,
    String? notes,
  }) async {
    await _dio.post(ApiConstants.requestSamples, data: {
      'productId': productId,
      'quantity': quantity,
      if (notes != null) 'notes': notes,
    });
  }

  Future<void> markRecovered(int sampleId) async {
    await _dio.patch(ApiConstants.recoverSample(sampleId), data: {});
  }
}

final samplesRepositoryProvider = Provider<SamplesRepository>((ref) {
  return SamplesRepository(ref.read(dioClientProvider));
});

final samplesProvider = FutureProvider<SamplesData>((ref) async {
  return ref.read(samplesRepositoryProvider).getMySamples();
});
