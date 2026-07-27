import '../data/local/app_database.dart';
import '../core/network/dio_client.dart';
import '../core/constants/api_constants.dart';
import '../core/storage/secure_storage.dart';

const String syncTaskName = 'bookmark-sync';

class SyncWorker {
  final AppDatabase _db;

  SyncWorker(this._db);

  Future<bool> performWork() async {
    try {
      final storage = SecureStorage();
      final token = await storage.getToken();
      if (token == null) return true; // Not logged in — skip

      final pending = await _db.visitsDao.getPendingSync();
      if (pending.isEmpty) return true;

      final dio = DioClient(storage);

      for (final visit in pending) {
        try {
          await dio.post(ApiConstants.visitComplete(visit.id), data: {
            'contactPerson': visit.contactPerson,
            'designation': visit.designation,
            'phone': visit.phone,
            'notes': visit.notes,
            'visitType': visit.visitType,
            'sampleDistributed': visit.sampleDistributed,
            'syncedOffline': true,
          });
          await _db.visitsDao.markSynced(visit.id);
        } catch (_) {
          // One failure doesn't abort the whole batch
          continue;
        }
      }
      return true;
    } catch (_) {
      return false; // WorkManager will retry
    }
  }
}
