-- SFA Extension Tables (added for mobile app integration)
-- These are ADDITIVE ONLY — no existing tables are modified

CREATE TABLE IF NOT EXISTS "attendance" (
    "id" SERIAL PRIMARY KEY,
    "booker_id" INTEGER NOT NULL,
    "date" DATE NOT NULL,
    "start_at" TIMESTAMP(3),
    "end_at" TIMESTAMP(3),
    "start_lat" DECIMAL(10,8),
    "start_lng" DECIMAL(11,8),
    "end_lat" DECIMAL(10,8),
    "end_lng" DECIMAL(11,8),
    "status" TEXT NOT NULL DEFAULT 'present',
    "cannot_reason" TEXT,
    "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "attendance_booker_id_date_key" UNIQUE ("booker_id", "date"),
    CONSTRAINT "attendance_booker_id_fkey" FOREIGN KEY ("booker_id") REFERENCES "bookers"("id") ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE INDEX IF NOT EXISTS "attendance_booker_id_idx" ON "attendance"("booker_id");
CREATE INDEX IF NOT EXISTS "attendance_date_idx" ON "attendance"("date");

CREATE TABLE IF NOT EXISTS "leave_requests" (
    "id" SERIAL PRIMARY KEY,
    "booker_id" INTEGER NOT NULL,
    "leave_type" TEXT NOT NULL,
    "from_date" DATE NOT NULL,
    "to_date" DATE NOT NULL,
    "reason" TEXT NOT NULL,
    "status" TEXT NOT NULL DEFAULT 'pending',
    "admin_notes" TEXT,
    "reviewed_by" INTEGER,
    "reviewed_at" TIMESTAMP(3),
    "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "leave_requests_booker_id_fkey" FOREIGN KEY ("booker_id") REFERENCES "bookers"("id") ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE INDEX IF NOT EXISTS "leave_requests_booker_id_idx" ON "leave_requests"("booker_id");
CREATE INDEX IF NOT EXISTS "leave_requests_status_idx" ON "leave_requests"("status");

CREATE TABLE IF NOT EXISTS "missed_visit_reasons" (
    "id" SERIAL PRIMARY KEY,
    "visit_id" INTEGER NOT NULL,
    "booker_id" INTEGER NOT NULL,
    "reason" TEXT NOT NULL,
    "status" TEXT NOT NULL DEFAULT 'pending',
    "admin_note" TEXT,
    "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "missed_visit_reasons_visit_id_key" UNIQUE ("visit_id"),
    CONSTRAINT "missed_visit_reasons_visit_id_fkey" FOREIGN KEY ("visit_id") REFERENCES "visits"("id") ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT "missed_visit_reasons_booker_id_fkey" FOREIGN KEY ("booker_id") REFERENCES "bookers"("id") ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE INDEX IF NOT EXISTS "missed_visit_reasons_booker_id_idx" ON "missed_visit_reasons"("booker_id");
CREATE INDEX IF NOT EXISTS "missed_visit_reasons_status_idx" ON "missed_visit_reasons"("status");
