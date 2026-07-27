import 'package:drift/drift.dart';

import '../app_database.dart';
import '../tables/attendance_table.dart';

part 'attendance_dao.g.dart';

@DriftAccessor(tables: [AttendanceTable])
class AttendanceDao extends DatabaseAccessor<AppDatabase>
    with _$AttendanceDaoMixin {
  AttendanceDao(super.db);

  Future<AttendanceTableData?> getTodayAttendance(int userId) {
    final today = DateTime.now();
    final start = DateTime(today.year, today.month, today.day);
    final end = start.add(const Duration(days: 1));
    return (select(attendanceTable)
          ..where((t) =>
              t.userId.equals(userId) &
              t.date.isBetweenValues(start, end)))
        .getSingleOrNull();
  }

  Future<void> upsertAttendance(AttendanceTableCompanion entry) {
    return into(attendanceTable).insertOnConflictUpdate(entry);
  }
}
