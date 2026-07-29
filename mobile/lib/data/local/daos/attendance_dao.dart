import 'package:sqflite/sqflite.dart';
import '../app_database.dart';

class AttendanceDao {
  final AppDatabase db;

  AttendanceDao(this.db);

  Future<Map<String, dynamic>?> getTodayAttendance(int userId) async {
    final database = await db.database;
    final today = DateTime.now().toIso8601String().split('T')[0];
    final results = await database.query(
      'attendance',
      where: 'user_id = ? AND date = ?',
      whereArgs: [userId, today],
    );
    return results.isNotEmpty ? results.first : null;
  }

  Future<void> upsertAttendance(Map<String, dynamic> entry) async {
    final database = await db.database;
    final userId = entry['user_id'];
    final date = entry['date'];

    // Check if record exists
    final existing = await database.query(
      'attendance',
      where: 'user_id = ? AND date = ?',
      whereArgs: [userId, date],
    );

    if (existing.isNotEmpty) {
      await database.update(
        'attendance',
        entry,
        where: 'user_id = ? AND date = ?',
        whereArgs: [userId, date],
      );
    } else {
      await database.insert(
        'attendance',
        entry,
        conflictAlgorithm: ConflictAlgorithm.replace,
      );
    }
  }

  Future<List<Map<String, dynamic>>> getAttendanceRange(
    int userId,
    DateTime start,
    DateTime end,
  ) async {
    final database = await db.database;
    final startStr = start.toIso8601String().split('T')[0];
    final endStr = end.toIso8601String().split('T')[0];
    return database.query(
      'attendance',
      where: 'user_id = ? AND date BETWEEN ? AND ?',
      whereArgs: [userId, startStr, endStr],
      orderBy: 'date DESC',
    );
  }
}
