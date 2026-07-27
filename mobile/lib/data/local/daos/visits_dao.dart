import 'package:drift/drift.dart';

import '../app_database.dart';
import '../tables/visits_table.dart';

part 'visits_dao.g.dart';

@DriftAccessor(tables: [VisitsTable])
class VisitsDao extends DatabaseAccessor<AppDatabase> with _$VisitsDaoMixin {
  VisitsDao(super.db);

  Future<List<VisitsTableData>> getTodayVisits(DateTime date) {
    final start = DateTime(date.year, date.month, date.day);
    final end = start.add(const Duration(days: 1));
    return (select(visitsTable)
          ..where((t) => t.scheduledDate.isBetweenValues(start, end))
          ..orderBy([(t) => OrderingTerm.asc(t.dailySequence)]))
        .get();
  }

  Future<List<VisitsTableData>> getPendingSync() {
    return (select(visitsTable)
          ..where((t) => t.syncStatus.equals('pending_sync')))
        .get();
  }

  Future<void> markSynced(int id) {
    return (update(visitsTable)..where((t) => t.id.equals(id)))
        .write(const VisitsTableCompanion(syncStatus: Value('synced')));
  }

  Future<void> upsertVisits(List<VisitsTableCompanion> visits) async {
    await batch((b) => b.insertAllOnConflictUpdate(visitsTable, visits));
  }

  Future<void> updateVisit(VisitsTableCompanion companion) {
    return (update(visitsTable)..where((t) => t.id.equals(companion.id.value)))
        .write(companion);
  }
}
