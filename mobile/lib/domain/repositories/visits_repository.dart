import '../../domain/entities/visit.dart';

abstract class VisitsRepository {
  Future<List<Visit>> getTodayVisits();
  Future<Visit> startVisit(int visitId, double lat, double lng);
  Future<void> completeVisit({
    required int visitId,
    required String contactPerson,
    required String designation,
    required String phone,
    required String notes,
    required String visitType,
    required int sampleDistributed,
    String? followUpDate,
  });
  Future<void> markMissed({
    required int visitId,
    required String reason,
    required String photoUrl,
  });
  Future<void> editVisit({required int visitId, required Map<String, dynamic> fields});
  Future<void> syncPendingVisits();
}
