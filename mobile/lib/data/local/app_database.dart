import 'package:drift/drift.dart';
import 'package:drift/native.dart';
import 'package:path_provider/path_provider.dart';
import 'package:path/path.dart' as p;
import 'dart:io';

import 'tables/visits_table.dart';
import 'tables/gps_logs_table.dart';
import 'tables/attendance_table.dart';
import 'daos/visits_dao.dart';
import 'daos/attendance_dao.dart';

part 'app_database.g.dart';

@DriftDatabase(
  tables: [VisitsTable, GpsLogsTable, AttendanceTable],
  daos: [VisitsDao, AttendanceDao],
)
class AppDatabase extends _$AppDatabase {
  AppDatabase() : super(_openConnection());

  @override
  int get schemaVersion => 1;

  static AppDatabase? _instance;
  static AppDatabase getInstance() => _instance ??= AppDatabase();
}

LazyDatabase _openConnection() {
  return LazyDatabase(() async {
    final dir = await getApplicationDocumentsDirectory();
    final file = File(p.join(dir.path, 'bookmark_sfa.db'));
    return NativeDatabase(file);
  });
}
