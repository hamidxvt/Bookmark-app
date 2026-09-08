import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

class AppDatabase {
  static final AppDatabase _instance = AppDatabase._internal();
  static Database? _database;

  factory AppDatabase() {
    return _instance;
  }

  AppDatabase._internal();

  Future<Database> get database async {
    _database ??= await _initDatabase();
    return _database!;
  }

  Future<Database> _initDatabase() async {
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, 'bookmark_sfa.db');
    return await openDatabase(
      path,
      version: 1,
      onCreate: _createDb,
    );
  }

  Future<void> _createDb(Database db, int version) async {
    // Users table
    await db.execute('''
      CREATE TABLE IF NOT EXISTS users (
        id INTEGER PRIMARY KEY,
        name TEXT NOT NULL,
        email TEXT UNIQUE NOT NULL,
        phone TEXT,
        role TEXT,
        city_id INTEGER,
        area_id INTEGER,
        created_at TEXT,
        updated_at TEXT
      )
    ''');

    // Attendance table
    await db.execute('''
      CREATE TABLE IF NOT EXISTS attendance (
        id INTEGER PRIMARY KEY,
        user_id INTEGER NOT NULL,
        date TEXT NOT NULL,
        day_start_time TEXT,
        day_end_time TEXT,
        day_start_lat REAL,
        day_start_lng REAL,
        day_end_lat REAL,
        day_end_lng REAL,
        status TEXT,
        UNIQUE(user_id, date),
        FOREIGN KEY(user_id) REFERENCES users(id)
      )
    ''');

    // Visits table
    await db.execute('''
      CREATE TABLE IF NOT EXISTS visits (
        id INTEGER PRIMARY KEY,
        user_id INTEGER NOT NULL,
        location_id INTEGER,
        location_name TEXT,
        location_lat REAL,
        location_lng REAL,
        scheduled_date TEXT NOT NULL,
        daily_sequence INTEGER,
        status TEXT,
        visit_type TEXT,
        arrival_time TEXT,
        completion_time TEXT,
        contact_person TEXT,
        contact_phone TEXT,
        notes TEXT,
        photo_url TEXT,
        carry_forward_cnt INTEGER DEFAULT 0,
        sync_status TEXT DEFAULT 'pending_sync',
        created_at TEXT,
        updated_at TEXT,
        FOREIGN KEY(user_id) REFERENCES users(id)
      )
    ''');

    // GPS Logs table
    await db.execute('''
      CREATE TABLE IF NOT EXISTS gps_logs (
        id INTEGER PRIMARY KEY,
        user_id INTEGER NOT NULL,
        latitude REAL NOT NULL,
        longitude REAL NOT NULL,
        accuracy REAL,
        timestamp TEXT NOT NULL,
        is_mock_location INTEGER DEFAULT 0,
        FOREIGN KEY(user_id) REFERENCES users(id)
      )
    ''');

    // Create indexes
    await db.execute('CREATE INDEX idx_visits_user_date ON visits(user_id, scheduled_date)');
    await db.execute('CREATE INDEX idx_attendance_user_date ON attendance(user_id, date)');
    await db.execute('CREATE INDEX idx_gps_logs_user_time ON gps_logs(user_id, timestamp)');
  }
}
