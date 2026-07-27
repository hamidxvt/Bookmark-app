import 'package:drift/drift.dart';

class GpsLogsTable extends Table {
  IntColumn get id => integer().autoIncrement()();
  IntColumn get userId => integer()();
  RealColumn get latitude => real()();
  RealColumn get longitude => real()();
  RealColumn get accuracy => real()();
  BoolColumn get isMocked => boolean().withDefault(const Constant(false))();
  IntColumn get batteryLevel => integer().nullable()();
  TextColumn get syncStatus => text().withDefault(const Constant('pending_sync'))();
  DateTimeColumn get recordedAt => dateTime()();
}
