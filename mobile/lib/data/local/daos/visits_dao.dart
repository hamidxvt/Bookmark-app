import 'package:sqflite/sqflite.dart';
import '../app_database.dart';

class VisitsDao {
  final AppDatabase db;

  VisitsDao(this.db);

  Future<List<Map<String, dynamic>>> getTodayVisits(DateTime date) async {
    final database = await db.database;
    final dateStr = date.toIso8601String().split('T')[0];
    return database.query(
      'visits',
      where: 'scheduled_date = ?',
      whereArgs: [dateStr],
      orderBy: 'daily_sequence ASC',
    );
  }

  Future<List<Map<String, dynamic>>> getPendingSync() async {
    final database = await db.database;
    return database.query(
      'visits',
      where: 'sync_status = ?',
      whereArgs: ['pending_sync'],
    );
  }

  Future<void> markSynced(int visitId) async {
    final database = await db.database;
    await database.update(
      'visits',
      {'sync_status': 'synced'},
      where: 'id = ?',
      whereArgs: [visitId],
    );
  }

  Future<void> upsertVisits(List<Map<String, dynamic>> visits) async {
    final database = await db.database;
    for (var visit in visits) {
      await database.insert(
        'visits',
        visit,
        conflictAlgorithm: ConflictAlgorithm.replace,
      );
    }
  }

  Future<void> updateVisit(Map<String, dynamic> visitData) async {
    final database = await db.database;
    await database.update(
      'visits',
      visitData,
      where: 'id = ?',
      whereArgs: [visitData['id']],
    );
  }

  Future<Map<String, dynamic>?> getVisitById(int visitId) async {
    final database = await db.database;
    final results = await database.query(
      'visits',
      where: 'id = ?',
      whereArgs: [visitId],
    );
    return results.isNotEmpty ? results.first : null;
  }
}
