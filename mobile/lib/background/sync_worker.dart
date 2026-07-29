import '../data/local/app_database.dart';
import '../data/local/daos/visits_dao.dart';
import '../core/network/dio_client.dart';
import '../core/constants/api_constants.dart';
import '../core/storage/secure_storage.dart';

class SyncWorker {
  final AppDatabase _db;

  SyncWorker(this._db);

  Future<bool> performWork() async {
    try {
      final storage = SecureStorage();
      final token = await storage.getToken();
      if (token == null) return true;

      final visitsDao = VisitsDao(_db);
      final pending = await visitsDao.getPendingSync();
      if (pending.isEmpty) return true;

      final dio = DioClient(storage);
      for (final visit in pending) {
        try {
          await dio.post(ApiConstants.visitComplete(visit['id']), data: {
            'contactPerson': visit['contact_person'],
            'phone': visit['contact_phone'],
            'notes': visit['notes'],
            'visitType': visit['visit_type'],
            'syncedOffline': true,
          });
          await visitsDao.markSynced(visit['id']);
        } catch (_) {
          continue;
        }
      }
      return true;
    } catch (_) {
      return false;
    }
  }
}
