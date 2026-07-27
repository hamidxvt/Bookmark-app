import 'package:drift/drift.dart';

class VisitsTable extends Table {
  IntColumn get id => integer()();
  IntColumn get userId => integer()();
  IntColumn get locationId => integer()();
  TextColumn get locationName => text()();
  RealColumn get locationLat => real()();
  RealColumn get locationLng => real()();
  DateTimeColumn get scheduledDate => dateTime()();
  IntColumn get dailySequence => integer()();
  TextColumn get status => text().withDefault(const Constant('planned'))();
  DateTimeColumn get arrivalTime => dateTime().nullable()();
  RealColumn get arrivalLat => real().nullable()();
  RealColumn get arrivalLng => real().nullable()();
  DateTimeColumn get completionTime => dateTime().nullable()();
  TextColumn get contactPerson => text().nullable()();
  TextColumn get designation => text().nullable()();
  TextColumn get phone => text().nullable()();
  TextColumn get notes => text().nullable()();
  TextColumn get visitType => text().nullable()();
  IntColumn get sampleDistributed => integer().withDefault(const Constant(0))();
  TextColumn get photoUrl => text().nullable()();
  TextColumn get missedReason => text().nullable()();
  IntColumn get carryForwardCnt => integer().withDefault(const Constant(0))();
  BoolColumn get isAdHoc => boolean().withDefault(const Constant(false))();
  TextColumn get syncStatus => text().withDefault(const Constant('synced'))();
  DateTimeColumn get updatedAt => dateTime()();

  @override
  Set<Column> get primaryKey => {id};
}
