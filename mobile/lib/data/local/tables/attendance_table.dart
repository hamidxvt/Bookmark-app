import 'package:drift/drift.dart';

class AttendanceTable extends Table {
  IntColumn get id => integer()();
  IntColumn get userId => integer()();
  DateTimeColumn get date => dateTime()();
  DateTimeColumn get dayStartTime => dateTime().nullable()();
  RealColumn get dayStartLat => real().nullable()();
  RealColumn get dayStartLng => real().nullable()();
  DateTimeColumn get dayEndTime => dateTime().nullable()();
  TextColumn get status => text().withDefault(const Constant('absent'))();
  TextColumn get syncStatus => text().withDefault(const Constant('pending_sync'))();

  @override
  Set<Column> get primaryKey => {id};
}
