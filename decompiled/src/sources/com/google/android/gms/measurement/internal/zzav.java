package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.room.util.CursorUtil$wrapMappedColumns$2;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzpr;
import com.google.android.gms.internal.measurement.zzqp;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
final class zzav extends zzos {
    private final zzau zzm;
    private final zzog zzn;
    private static final String[] zzb = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    static final String[] zza = {"associated_row_id", "ALTER TABLE upload_queue ADD COLUMN associated_row_id INTEGER;", "last_upload_timestamp", "ALTER TABLE upload_queue ADD COLUMN last_upload_timestamp INTEGER;"};
    private static final String[] zzc = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzd = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;", "ad_services_version", "ALTER TABLE apps ADD COLUMN ad_services_version INTEGER;", "unmatched_first_open_without_ad_id", "ALTER TABLE apps ADD COLUMN unmatched_first_open_without_ad_id INTEGER;", "npa_metadata_value", "ALTER TABLE apps ADD COLUMN npa_metadata_value INTEGER;", "attribution_eligibility_status", "ALTER TABLE apps ADD COLUMN attribution_eligibility_status INTEGER;", "sgtm_preview_key", "ALTER TABLE apps ADD COLUMN sgtm_preview_key TEXT;", "dma_consent_state", "ALTER TABLE apps ADD COLUMN dma_consent_state INTEGER;", "daily_realtime_dcu_count", "ALTER TABLE apps ADD COLUMN daily_realtime_dcu_count INTEGER;", "bundle_delivery_index", "ALTER TABLE apps ADD COLUMN bundle_delivery_index INTEGER;", "serialized_npa_metadata", "ALTER TABLE apps ADD COLUMN serialized_npa_metadata TEXT;", "unmatched_pfo", "ALTER TABLE apps ADD COLUMN unmatched_pfo INTEGER;", "unmatched_uwa", "ALTER TABLE apps ADD COLUMN unmatched_uwa INTEGER;", "ad_campaign_info", "ALTER TABLE apps ADD COLUMN ad_campaign_info BLOB;", "daily_registered_triggers_count", "ALTER TABLE apps ADD COLUMN daily_registered_triggers_count INTEGER;", "client_upload_eligibility", "ALTER TABLE apps ADD COLUMN client_upload_eligibility INTEGER;", "gmp_version_for_remote_config", "ALTER TABLE apps ADD COLUMN gmp_version_for_remote_config INTEGER;"};
    private static final String[] zze = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzf = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzj = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private static final String[] zzk = {"consent_source", "ALTER TABLE consent_settings ADD COLUMN consent_source INTEGER;", "dma_consent_settings", "ALTER TABLE consent_settings ADD COLUMN dma_consent_settings TEXT;", "storage_consent_at_bundling", "ALTER TABLE consent_settings ADD COLUMN storage_consent_at_bundling TEXT;"};
    private static final String[] zzl = {"idempotent", "CREATE INDEX IF NOT EXISTS trigger_uris_index ON trigger_uris (app_id);"};

    zzav(zzpg zzpgVar) {
        super(zzpgVar);
        this.zzn = new zzog(this.zzu.zzaZ());
        this.zzu.zzc();
        this.zzm = new zzau(this, this.zzu.zzaY(), "google_app_measurement.db");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String zzaA(String str, String[] strArr, String str2) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = zze().rawQuery(str, strArr);
                try {
                    if (rawQuery.moveToFirst()) {
                        String string = rawQuery.getString(0);
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        return string;
                    }
                    if (rawQuery == null) {
                        return "";
                    }
                    rawQuery.close();
                    return "";
                } catch (SQLiteException e) {
                    e = e;
                    this.zzu.zzaV().zzb().zzc("Database error", str, e);
                    throw e;
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
            }
            throw th;
        }
    }

    private final void zzaB(String str, String str2, ContentValues contentValues) {
        try {
            SQLiteDatabase zze2 = zze();
            if (contentValues.getAsString("app_id") == null) {
                this.zzu.zzaV().zzd().zzb("Value of the primary key is not set.", zzgu.zzl("app_id"));
                return;
            }
            StringBuilder sb = new StringBuilder(10);
            sb.append("app_id");
            sb.append(" = ?");
            if (zze2.update("consent_settings", contentValues, sb.toString(), new String[]{r2}) == 0 && zze2.insertWithOnConflict("consent_settings", null, contentValues, 5) == -1) {
                this.zzu.zzaV().zzb().zzc("Failed to insert/update table (got -1). key", zzgu.zzl("consent_settings"), zzgu.zzl("app_id"));
            }
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzd("Error storing into table. key", zzgu.zzl("consent_settings"), zzgu.zzl("app_id"), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final zzbc zzaC(String str, String str2, String str3) {
        Cursor cursor;
        Cursor cursor2;
        Boolean bool;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        zzg();
        zzaw();
        Cursor cursor3 = null;
        try {
            cursor = zze().query(str, (String[]) new ArrayList(Arrays.asList("lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling", "current_session_count")).toArray(new String[0]), "app_id=? and name=?", new String[]{str2, str3}, null, null, null);
            try {
            } catch (SQLiteException e) {
                e = e;
                cursor2 = cursor;
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
        }
        if (!cursor.moveToFirst()) {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
        long j = cursor.getLong(0);
        long j2 = cursor.getLong(1);
        long j3 = cursor.getLong(2);
        long j4 = cursor.isNull(3) ? 0L : cursor.getLong(3);
        Long valueOf = cursor.isNull(4) ? null : Long.valueOf(cursor.getLong(4));
        Long valueOf2 = cursor.isNull(5) ? null : Long.valueOf(cursor.getLong(5));
        Long valueOf3 = cursor.isNull(6) ? null : Long.valueOf(cursor.getLong(6));
        if (cursor.isNull(7)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getLong(7) == 1);
        }
        cursor2 = cursor;
        try {
            zzbc zzbcVar = new zzbc(str2, str3, j, j2, cursor.isNull(8) ? 0L : cursor.getLong(8), j3, j4, valueOf, valueOf2, valueOf3, bool);
            if (cursor2.moveToNext()) {
                this.zzu.zzaV().zzb().zzb("Got multiple records for event aggregates, expected one. appId", zzgu.zzl(str2));
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            return zzbcVar;
        } catch (SQLiteException e3) {
            e = e3;
            cursor = cursor2;
            try {
                zzic zzicVar = this.zzu;
                zzicVar.zzaV().zzb().zzd("Error querying events. appId", zzgu.zzl(str2), zzicVar.zzl().zza(str3), e);
                if (cursor != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor3 = cursor;
                if (cursor3 != null) {
                    cursor3.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cursor3 = cursor2;
            if (cursor3 != null) {
            }
            throw th;
        }
    }

    private final void zzaD(String str, zzbc zzbcVar) {
        Preconditions.checkNotNull(zzbcVar);
        zzg();
        zzaw();
        ContentValues contentValues = new ContentValues();
        String str2 = zzbcVar.zza;
        contentValues.put("app_id", str2);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzbcVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzbcVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzbcVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzbcVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzbcVar.zzg));
        contentValues.put("last_bundled_day", zzbcVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzbcVar.zzi);
        contentValues.put("last_sampling_rate", zzbcVar.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzbcVar.zze));
        Boolean bool = zzbcVar.zzk;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (zze().insertWithOnConflict(str, null, contentValues, 5) == -1) {
                this.zzu.zzaV().zzb().zzb("Failed to insert/update event aggregates (got -1). appId", zzgu.zzl(str2));
            }
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzc("Error storing event aggregates. appId", zzgu.zzl(zzbcVar.zza), e);
        }
    }

    private final void zzaE(String str, String str2) {
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzaw();
        try {
            zze().delete(str, "app_id=?", new String[]{str2});
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzc("Error deleting snapshot. appId", zzgu.zzl(str2), e);
        }
    }

    private final zzpj zzaF(String str, long j, byte[] bArr, String str2, String str3, int i, int i2, long j2, long j3, long j4) {
        if (TextUtils.isEmpty(str2)) {
            this.zzu.zzaV().zzj().zza("Upload uri is null or empty. Destination is unknown. Dropping batch. ");
            return null;
        }
        try {
            com.google.android.gms.internal.measurement.zzhz zzhzVar = (com.google.android.gms.internal.measurement.zzhz) zzpk.zzw(com.google.android.gms.internal.measurement.zzib.zzh(), bArr);
            zzls zzb2 = zzls.zzb(i);
            if (zzb2 != zzls.GOOGLE_SIGNAL && zzb2 != zzls.GOOGLE_SIGNAL_PENDING && i2 > 0) {
                ArrayList arrayList = new ArrayList();
                Iterator it = zzhzVar.zza().iterator();
                while (it.hasNext()) {
                    com.google.android.gms.internal.measurement.zzic zzicVar = (com.google.android.gms.internal.measurement.zzic) ((com.google.android.gms.internal.measurement.zzid) it.next()).zzcl();
                    zzicVar.zzao(i2);
                    arrayList.add((com.google.android.gms.internal.measurement.zzid) zzicVar.zzbc());
                }
                zzhzVar.zzg();
                zzhzVar.zzf(arrayList);
            }
            HashMap hashMap = new HashMap();
            if (str3 != null) {
                String[] split = str3.split("\r\n");
                int length = split.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    String str4 = split[i3];
                    if (str4.isEmpty()) {
                        break;
                    }
                    String[] split2 = str4.split("=", 2);
                    if (split2.length != 2) {
                        this.zzu.zzaV().zzb().zzb("Invalid upload header: ", str4);
                        break;
                    }
                    hashMap.put(split2[0], split2[1]);
                    i3++;
                }
            }
            zzpi zzpiVar = new zzpi();
            zzpiVar.zzb(j);
            zzpiVar.zzc((com.google.android.gms.internal.measurement.zzib) zzhzVar.zzbc());
            zzpiVar.zzd(str2);
            zzpiVar.zze(hashMap);
            zzpiVar.zzf(zzb2);
            zzpiVar.zzg(j2);
            zzpiVar.zzh(j3);
            zzpiVar.zzi(j4);
            zzpiVar.zzj(i2);
            return zzpiVar.zza();
        } catch (IOException e) {
            this.zzu.zzaV().zzb().zzc("Failed to queued MeasurementBatch from upload_queue. appId", str, e);
            return null;
        }
    }

    private final String zzaG() {
        zzic zzicVar = this.zzu;
        long currentTimeMillis = zzicVar.zzaZ().currentTimeMillis();
        Locale locale = Locale.US;
        zzls zzlsVar = zzls.GOOGLE_SIGNAL;
        Integer valueOf = Integer.valueOf(zzlsVar.zza());
        Long valueOf2 = Long.valueOf(currentTimeMillis);
        zzicVar.zzc();
        String format = String.format(locale, "(upload_type = %d AND ABS(creation_timestamp - %d) > %d)", valueOf, valueOf2, Long.valueOf(((Long) zzfy.zzS.zzb(null)).longValue()));
        Locale locale2 = Locale.US;
        Integer valueOf3 = Integer.valueOf(zzlsVar.zza());
        zzicVar.zzc();
        String format2 = String.format(locale2, "(upload_type != %d AND ABS(creation_timestamp - %d) > %d)", valueOf3, valueOf2, Long.valueOf(zzal.zzI()));
        StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 5 + String.valueOf(format2).length() + 1);
        sb.append("(");
        sb.append(format);
        sb.append(" OR ");
        sb.append(format2);
        sb.append(")");
        return sb.toString();
    }

    private static final String zzaH(List list) {
        return list.isEmpty() ? "" : String.format(" AND (upload_type IN (%s))", TextUtils.join(", ", list));
    }

    static final void zzau(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else {
            if (!(obj instanceof Double)) {
                throw new IllegalArgumentException("Invalid value type");
            }
            contentValues.put("value", (Double) obj);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final long zzay(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = zze().rawQuery(str, strArr);
        } catch (SQLiteException e) {
            e = e;
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (!cursor.moveToFirst()) {
                throw new SQLiteException("Database returned empty set");
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e2) {
            e = e2;
            try {
                this.zzu.zzaV().zzb().zzc("Database error", str, e);
                throw e;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final long zzaz(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = zze().rawQuery(str, strArr);
                try {
                    if (rawQuery.moveToFirst()) {
                        j = rawQuery.getLong(0);
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return j;
                } catch (SQLiteException e) {
                    e = e;
                    this.zzu.zzaV().zzb().zzc("Database error", str, e);
                    throw e;
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
            }
            throw th;
        }
    }

    public final long zzA(String str, com.google.android.gms.internal.measurement.zzib zzibVar, String str2, Map map, zzls zzlsVar, Long l) {
        int delete;
        zzg();
        zzaw();
        Preconditions.checkNotNull(zzibVar);
        Preconditions.checkNotEmpty(str);
        zzg();
        zzaw();
        if (zzag()) {
            zzpg zzpgVar = this.zzg;
            long zza2 = zzpgVar.zzq().zzb.zza();
            zzic zzicVar = this.zzu;
            long elapsedRealtime = zzicVar.zzaZ().elapsedRealtime();
            long abs = Math.abs(elapsedRealtime - zza2);
            zzicVar.zzc();
            if (abs > zzal.zzJ()) {
                zzpgVar.zzq().zzb.zzb(elapsedRealtime);
                zzg();
                zzaw();
                if (zzag() && (delete = zze().delete("upload_queue", zzaG(), new String[0])) > 0) {
                    zzicVar.zzaV().zzk().zzb("Deleted stale MeasurementBatch rows from upload_queue. rowsDeleted", Integer.valueOf(delete));
                }
                Preconditions.checkNotEmpty(str);
                zzg();
                zzaw();
                try {
                    int zzm = zzicVar.zzc().zzm(str, zzfy.zzz);
                    if (zzm > 0) {
                        zze().delete("upload_queue", "rowid in (SELECT rowid FROM upload_queue WHERE app_id=? ORDER BY rowid DESC LIMIT -1 OFFSET ?)", new String[]{str, String.valueOf(zzm)});
                    }
                } catch (SQLiteException e) {
                    this.zzu.zzaV().zzb().zzc("Error deleting over the limit queued batches. appId", zzgu.zzl(str), e);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            String str3 = (String) entry.getKey();
            String str4 = (String) entry.getValue();
            StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 1 + String.valueOf(str4).length());
            sb.append(str3);
            sb.append("=");
            sb.append(str4);
            arrayList.add(sb.toString());
        }
        byte[] zzcc = zzibVar.zzcc();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("measurement_batch", zzcc);
        contentValues.put("upload_uri", str2);
        contentValues.put("upload_headers", String.join("\r\n", arrayList));
        contentValues.put("upload_type", Integer.valueOf(zzlsVar.zza()));
        zzic zzicVar2 = this.zzu;
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzicVar2.zzaZ().currentTimeMillis()));
        contentValues.put("retry_count", (Integer) 0);
        if (l != null) {
            contentValues.put("associated_row_id", l);
        }
        try {
            long insert = zze().insert("upload_queue", null, contentValues);
            if (insert != -1) {
                return insert;
            }
            zzicVar2.zzaV().zzb().zzb("Failed to insert MeasurementBatch (got -1) to upload_queue. appId", str);
            return -1L;
        } catch (SQLiteException e2) {
            this.zzu.zzaV().zzb().zzc("Error storing MeasurementBatch to upload_queue. appId", str, e2);
            return -1L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzpj zzB(long j) {
        Cursor cursor;
        zzg();
        zzaw();
        try {
            cursor = zze().query("upload_queue", new String[]{"rowId", "app_id", "measurement_batch", "upload_uri", "upload_headers", "upload_type", "retry_count", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "associated_row_id", "last_upload_timestamp"}, "rowId=?", new String[]{String.valueOf(j)}, null, null, null, "1");
        } catch (SQLiteException e) {
            e = e;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            cursor = null;
        }
        try {
        } catch (SQLiteException e2) {
            e = e2;
            try {
                this.zzu.zzaV().zzb().zzc("Error to querying MeasurementBatch from upload_queue. rowId", Long.valueOf(j), e);
                if (cursor != null) {
                }
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
            }
            throw th;
        }
        if (cursor.moveToFirst()) {
            zzpj zzaF = zzaF((String) Preconditions.checkNotNull(cursor.getString(1)), j, cursor.getBlob(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getLong(7), cursor.getLong(8), cursor.getLong(9));
            if (cursor != null) {
                cursor.close();
            }
            return zzaF;
        }
        if (cursor != null) {
            return null;
        }
        cursor.close();
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0154  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzC(String str, zzoo zzooVar, int i) {
        Cursor cursor;
        List list;
        Cursor cursor2;
        Cursor cursor3;
        Preconditions.checkNotEmpty(str);
        zzg();
        zzaw();
        try {
            SQLiteDatabase zze2 = zze();
            int i2 = 0;
            int i3 = 2;
            String[] strArr = {"rowId", "app_id", "measurement_batch", "upload_uri", "upload_headers", "upload_type", "retry_count", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "associated_row_id", "last_upload_timestamp"};
            String zzaH = zzaH(zzooVar.zza);
            String zzaG = zzaG();
            StringBuilder sb = new StringBuilder(String.valueOf(zzaH).length() + 17 + zzaG.length());
            sb.append("app_id=?");
            sb.append(zzaH);
            sb.append(" AND NOT ");
            sb.append(zzaG);
            int i4 = 6;
            int i5 = 5;
            int i6 = 4;
            cursor2 = zze2.query("upload_queue", strArr, sb.toString(), new String[]{str}, null, null, "creation_timestamp ASC", i > 0 ? String.valueOf(i) : null);
            try {
                list = new ArrayList();
                while (cursor2.moveToNext()) {
                    long j = cursor2.getLong(i2);
                    byte[] blob = cursor2.getBlob(i3);
                    String string = cursor2.getString(3);
                    String string2 = cursor2.getString(i6);
                    int i7 = i5;
                    cursor3 = cursor2;
                    List list2 = list;
                    int i8 = i3;
                    int i9 = i4;
                    int i10 = i2;
                    try {
                        zzpj zzaF = zzaF(str, j, blob, string, string2, cursor2.getInt(i5), cursor2.getInt(i4), cursor2.getLong(7), cursor2.getLong(8), cursor2.getLong(9));
                        if (zzaF != null) {
                            list2.add(zzaF);
                            list = list2;
                            cursor2 = cursor3;
                            i4 = i9;
                            i2 = i10;
                            i5 = i7;
                            i3 = i8;
                            i6 = 4;
                        } else {
                            list = list2;
                            cursor2 = cursor3;
                            i4 = i9;
                            i2 = i10;
                            i5 = i7;
                            i3 = i8;
                            i6 = 4;
                        }
                    } catch (SQLiteException e) {
                        e = e;
                        cursor = cursor3;
                        try {
                            this.zzu.zzaV().zzb().zzc("Error to querying MeasurementBatch from upload_queue. appId", str, e);
                            list = Collections.emptyList();
                            cursor2 = cursor;
                            if (cursor2 != null) {
                            }
                            return list;
                        } catch (Throwable th) {
                            th = th;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = cursor3;
                        if (cursor != null) {
                        }
                        throw th;
                    }
                }
            } catch (SQLiteException e2) {
                e = e2;
                cursor3 = cursor2;
            } catch (Throwable th3) {
                th = th3;
                cursor3 = cursor2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
        }
        if (cursor2 != null) {
            cursor2.close();
        }
        return list;
    }

    public final boolean zzD(String str) {
        zzls[] zzlsVarArr = {zzls.GOOGLE_SIGNAL};
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(zzlsVarArr[0].zza()));
        String zzaH = zzaH(arrayList);
        String zzaG = zzaG();
        StringBuilder sb = new StringBuilder(String.valueOf(zzaH).length() + 61 + zzaG.length());
        sb.append("SELECT COUNT(1) > 0 FROM upload_queue WHERE app_id=?");
        sb.append(zzaH);
        sb.append(" AND NOT ");
        sb.append(zzaG);
        return zzay(sb.toString(), new String[]{str}) != 0;
    }

    public final void zzE(Long l) {
        zzg();
        zzaw();
        Preconditions.checkNotNull(l);
        try {
            if (zze().delete("upload_queue", "rowid=?", new String[]{l.toString()}) != 1) {
                this.zzu.zzaV().zze().zza("Deleted fewer rows from upload_queue than expected");
            }
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzb("Failed to delete a MeasurementBatch in a upload_queue table", e);
            throw e;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String zzF() {
        SQLiteException e;
        Cursor cursor;
        SQLiteDatabase zze2 = zze();
        ?? r1 = 0;
        try {
            try {
                cursor = zze2.rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
                try {
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(0);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return string;
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    this.zzu.zzaV().zzb().zzb("Database error getting next bundle app id", e);
                    if (cursor != null) {
                    }
                    return null;
                }
            } catch (Throwable th) {
                r1 = zze2;
                th = th;
                if (r1 != 0) {
                    r1.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (r1 != 0) {
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public final boolean zzG() {
        return zzay("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    public final void zzH(long j) {
        zzg();
        zzaw();
        try {
            if (zze().delete("queue", "rowid=?", new String[]{String.valueOf(j)}) == 1) {
            } else {
                throw new SQLiteException("Deleted fewer rows from queue than expected");
            }
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzb("Failed to delete a bundle in a queue table", e);
            throw e;
        }
    }

    final void zzI() {
        zzg();
        zzaw();
        if (zzag()) {
            zzpg zzpgVar = this.zzg;
            long zza2 = zzpgVar.zzq().zza.zza();
            zzic zzicVar = this.zzu;
            long elapsedRealtime = zzicVar.zzaZ().elapsedRealtime();
            long abs = Math.abs(elapsedRealtime - zza2);
            zzicVar.zzc();
            if (abs > zzal.zzJ()) {
                zzpgVar.zzq().zza.zzb(elapsedRealtime);
                zzg();
                zzaw();
                if (zzag()) {
                    SQLiteDatabase zze2 = zze();
                    zzicVar.zzc();
                    int delete = zze2.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzicVar.zzaZ().currentTimeMillis()), String.valueOf(zzal.zzI())});
                    if (delete > 0) {
                        zzicVar.zzaV().zzk().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    final void zzJ(List list) {
        zzg();
        zzaw();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzag()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(sb2.length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zzay(sb3.toString(), null) > 0) {
                this.zzu.zzaV().zze().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase zze2 = zze();
                StringBuilder sb4 = new StringBuilder(sb2.length() + 127);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                zze2.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                this.zzu.zzaV().zzb().zzb("Error incrementing retry count. error", e);
            }
        }
    }

    final void zzK(Long l) {
        zzg();
        zzaw();
        Preconditions.checkNotNull(l);
        if (zzag()) {
            StringBuilder sb = new StringBuilder(l.toString().length() + 86);
            sb.append("SELECT COUNT(1) FROM upload_queue WHERE rowid = ");
            sb.append(l);
            sb.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zzay(sb.toString(), null) > 0) {
                this.zzu.zzaV().zze().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase zze2 = zze();
                long currentTimeMillis = this.zzu.zzaZ().currentTimeMillis();
                StringBuilder sb2 = new StringBuilder(String.valueOf(currentTimeMillis).length() + 60);
                sb2.append(" SET retry_count = retry_count + 1, last_upload_timestamp = ");
                sb2.append(currentTimeMillis);
                String sb3 = sb2.toString();
                StringBuilder sb4 = new StringBuilder(sb3.length() + 34 + l.toString().length() + 29);
                sb4.append("UPDATE upload_queue");
                sb4.append(sb3);
                sb4.append(" WHERE rowid = ");
                sb4.append(l);
                sb4.append(" AND retry_count < 2147483647");
                zze2.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                this.zzu.zzaV().zzb().zzb("Error incrementing retry count. error", e);
            }
        }
    }

    final Object zzL(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                this.zzu.zzaV().zzb().zza("Loaded invalid null value from database");
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                this.zzu.zzaV().zzb().zza("Loaded invalid blob type value, ignoring it");
                break;
            default:
                this.zzu.zzaV().zzb().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                break;
        }
        return null;
    }

    public final long zzM() {
        return zzaz("select max(bundle_end_timestamp) from queue", null, 0L);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:0|1|2|3|4|(2:6|(3:8|10|11)(1:14))|15|16|(1:18)(2:21|22)|19|10|11|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00b2, code lost:
    
        r1 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00b3, code lost:
    
        r3 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00b8, code lost:
    
        r13.zzu.zzaV().zzb().zzd("Error inserting column. appId", com.google.android.gms.measurement.internal.zzgu.zzl(r14), "first_open_count", r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00cb, code lost:
    
        r7 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final long zzN(String str, String str2) {
        long j;
        long zzaz;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        zzaw();
        SQLiteDatabase zze2 = zze();
        zze2.beginTransaction();
        long j2 = 0;
        try {
            try {
                StringBuilder sb = new StringBuilder(48);
                sb.append("select ");
                sb.append("first_open_count");
                sb.append(" from app2 where app_id=?");
                j = -1;
                zzaz = zzaz(sb.toString(), new String[]{str}, -1L);
            } catch (SQLiteException e) {
                e = e;
            }
            if (zzaz == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", (Integer) 0);
                contentValues.put("previous_install_count", (Integer) 0);
                if (zze2.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                    this.zzu.zzaV().zzb().zzc("Failed to insert column (got -1). appId", zzgu.zzl(str), "first_open_count");
                    return j;
                }
                zzaz = 0;
            }
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("app_id", str);
            contentValues2.put("first_open_count", Long.valueOf(1 + zzaz));
            if (zze2.update("app2", contentValues2, "app_id = ?", new String[]{str}) == 0) {
                this.zzu.zzaV().zzb().zzc("Failed to update column (got 0). appId", zzgu.zzl(str), "first_open_count");
            } else {
                zze2.setTransactionSuccessful();
                j = zzaz;
            }
            return j;
        } finally {
            zze2.endTransaction();
        }
    }

    public final long zzO() {
        return zzaz("select max(timestamp) from raw_events", null, 0L);
    }

    public final boolean zzP() {
        return zzay("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzQ(String str, String str2) {
        return zzay("select count(1) from raw_events where app_id = ? and name = ?", new String[]{str, str2}) > 0;
    }

    public final boolean zzR() {
        return zzay("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    public final long zzS(String str) {
        Preconditions.checkNotEmpty(str);
        return zzaz("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    public final boolean zzT(String str, Long l, long j, com.google.android.gms.internal.measurement.zzhs zzhsVar) {
        zzg();
        zzaw();
        Preconditions.checkNotNull(zzhsVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        zzic zzicVar = this.zzu;
        byte[] zzcc = zzhsVar.zzcc();
        zzicVar.zzaV().zzk().zzc("Saving complex main event, appId, data size", zzicVar.zzl().zza(str), Integer.valueOf(zzcc.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzcc);
        try {
            if (zze().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzicVar.zzaV().zzb().zzb("Failed to insert complex main event (got -1). appId", zzgu.zzl(str));
            return false;
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzc("Error storing complex main event. appId", zzgu.zzl(str), e);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bundle zzU(String str) {
        Cursor cursor;
        zzg();
        zzaw();
        Cursor cursor2 = null;
        try {
            cursor = zze().rawQuery("select parameters from default_event_params where app_id=?", new String[]{str});
            try {
                if (cursor.moveToFirst()) {
                    try {
                        com.google.android.gms.internal.measurement.zzhs zzhsVar = (com.google.android.gms.internal.measurement.zzhs) ((com.google.android.gms.internal.measurement.zzhr) zzpk.zzw(com.google.android.gms.internal.measurement.zzhs.zzk(), cursor.getBlob(0))).zzbc();
                        this.zzg.zzp();
                        Bundle zzE = zzpk.zzE(zzhsVar.zza());
                        if (cursor != null) {
                            cursor.close();
                        }
                        return zzE;
                    } catch (IOException e) {
                        this.zzu.zzaV().zzb().zzc("Failed to retrieve default event parameters. appId", zzgu.zzl(str), e);
                    }
                } else {
                    this.zzu.zzaV().zzk().zza("Default event parameters not found");
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    this.zzu.zzaV().zzb().zzb("Error selecting default event parameters", e);
                    if (cursor != null) {
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor2 = cursor;
                if (cursor2 != null) {
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    final boolean zzV(String str, long j) {
        try {
            if (zzaz("select count(*) from raw_events where app_id=? and timestamp >= ? and name not like '!_%' escape '!' limit 1;", new String[]{str, String.valueOf(j)}, 0L) > 0) {
                return false;
            }
            return zzaz("select count(*) from raw_events where app_id=? and timestamp >= ? and name like '!_%' escape '!' limit 1;", new String[]{str, String.valueOf(j)}, 0L) > 0;
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzb("Error checking backfill conditions", e);
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:133:0x00e0, code lost:
    
        if (r2 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0088, code lost:
    
        if (r2 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x008a, code lost:
    
        r2.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0131 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02f9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02e0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x021a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzW(String str, Long l, String str2, Bundle bundle) {
        String string;
        String str3;
        Bundle bundle2;
        long update;
        com.google.android.gms.internal.measurement.zzid zzidVar;
        Cursor query;
        String str4 = str;
        Preconditions.checkNotNull(bundle);
        zzg();
        zzaw();
        zzat zzatVar = l != null ? new zzat(this, str4, l.longValue()) : new zzat(this, str4);
        List<zzas> zza2 = zzatVar.zza();
        while (!zza2.isEmpty()) {
            for (zzas zzasVar : zza2) {
                if (!TextUtils.isEmpty(str2)) {
                    Cursor cursor = null;
                    r4 = null;
                    r4 = null;
                    com.google.android.gms.internal.measurement.zzid zzidVar2 = null;
                    cursor = null;
                    try {
                        query = zze().query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{str4, Long.toString(zzasVar.zzb)}, null, null, "rowid", "2");
                        try {
                            try {
                            } catch (SQLiteException e) {
                                e = e;
                                zzidVar = null;
                                cursor = query;
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = query;
                            if (cursor != null) {
                            }
                            throw th;
                        }
                    } catch (SQLiteException e2) {
                        e = e2;
                        zzidVar = null;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    if (query.moveToFirst()) {
                        try {
                            zzidVar = (com.google.android.gms.internal.measurement.zzid) ((com.google.android.gms.internal.measurement.zzic) zzpk.zzw(com.google.android.gms.internal.measurement.zzid.zzaE(), query.getBlob(0))).zzbc();
                            try {
                                if (query.moveToNext()) {
                                    this.zzu.zzaV().zze().zzb("Get multiple raw event metadata records, expected one. appId", zzgu.zzl(str));
                                }
                                query.close();
                                if (query != null) {
                                    query.close();
                                }
                            } catch (SQLiteException e3) {
                                e = e3;
                                cursor = query;
                                try {
                                    this.zzu.zzaV().zzb().zzc("Data loss. Error selecting raw event. appId", zzgu.zzl(str), e);
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    zzidVar2 = zzidVar;
                                    if (zzidVar2 == null) {
                                    }
                                    zzpg zzpgVar = this.zzg;
                                    zzpk zzp = zzpgVar.zzp();
                                    com.google.android.gms.internal.measurement.zzhs zzhsVar = zzasVar.zzd;
                                    Bundle bundle3 = new Bundle();
                                    while (r5.hasNext()) {
                                    }
                                    string = bundle3.getString("_o");
                                    bundle3.remove("_o");
                                    String zzd2 = zzhsVar.zzd();
                                    if (string == null) {
                                    }
                                    zzgv zzgvVar = new zzgv(zzd2, string, bundle3, zzhsVar.zzf());
                                    zzic zzicVar = this.zzu;
                                    Bundle bundle4 = zzgvVar.zzd;
                                    str3 = zzgvVar.zza;
                                    zzpp zzk2 = zzicVar.zzk();
                                    if (str3.equals(Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN)) {
                                    }
                                    zzk2.zzI(bundle4, bundle2);
                                    zzbb zzbbVar = new zzbb(this.zzu, zzgvVar.zzb, str, zzhsVar.zzd(), zzhsVar.zzf(), zzhsVar.zzh(), bundle4);
                                    long j = zzasVar.zza;
                                    long j2 = zzasVar.zzb;
                                    boolean z = zzasVar.zzc;
                                    zzg();
                                    zzaw();
                                    Preconditions.checkNotNull(zzbbVar);
                                    String str5 = zzbbVar.zza;
                                    Preconditions.checkNotEmpty(str5);
                                    byte[] zzcc = zzpgVar.zzp().zzh(zzbbVar).zzcc();
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("app_id", str5);
                                    contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzbbVar.zzb);
                                    contentValues.put("timestamp", Long.valueOf(zzbbVar.zzd));
                                    contentValues.put("metadata_fingerprint", Long.valueOf(j2));
                                    contentValues.put(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, zzcc);
                                    contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
                                    update = zze().update("raw_events", contentValues, "rowid = ?", new String[]{String.valueOf(j)});
                                    if (update == 1) {
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    throw th;
                                }
                            }
                            zzidVar2 = zzidVar;
                        } catch (IOException e4) {
                            this.zzu.zzaV().zzb().zzc("Data loss. Failed to merge raw event metadata. appId", zzgu.zzl(str), e4);
                        }
                        if (zzidVar2 == null) {
                            Iterator it = zzidVar2.zzf().iterator();
                            while (it.hasNext()) {
                                if (((com.google.android.gms.internal.measurement.zziu) it.next()).zzc().equals(str2)) {
                                    break;
                                }
                            }
                        }
                    } else {
                        this.zzu.zzaV().zzb().zzb("Raw event metadata record is missing. appId", zzgu.zzl(str));
                    }
                }
                zzpg zzpgVar2 = this.zzg;
                zzpk zzp2 = zzpgVar2.zzp();
                com.google.android.gms.internal.measurement.zzhs zzhsVar2 = zzasVar.zzd;
                Bundle bundle32 = new Bundle();
                for (com.google.android.gms.internal.measurement.zzhw zzhwVar : zzhsVar2.zza()) {
                    if (zzhwVar.zzi()) {
                        bundle32.putDouble(zzhwVar.zzb(), zzhwVar.zzj());
                    } else if (zzhwVar.zzg()) {
                        bundle32.putFloat(zzhwVar.zzb(), zzhwVar.zzh());
                    } else if (zzhwVar.zze()) {
                        bundle32.putLong(zzhwVar.zzb(), zzhwVar.zzf());
                    } else if (zzhwVar.zzc()) {
                        bundle32.putString(zzhwVar.zzb(), zzhwVar.zzd());
                    } else if (zzhwVar.zzk().isEmpty()) {
                        zzp2.zzu.zzaV().zzb().zzb("Unexpected parameter type for parameter", zzhwVar);
                    } else {
                        bundle32.putParcelableArray(zzhwVar.zzb(), zzpk.zzy(zzhwVar.zzk()));
                    }
                }
                string = bundle32.getString("_o");
                bundle32.remove("_o");
                String zzd22 = zzhsVar2.zzd();
                if (string == null) {
                    string = "";
                }
                zzgv zzgvVar2 = new zzgv(zzd22, string, bundle32, zzhsVar2.zzf());
                zzic zzicVar2 = this.zzu;
                Bundle bundle42 = zzgvVar2.zzd;
                str3 = zzgvVar2.zza;
                zzpp zzk22 = zzicVar2.zzk();
                if (str3.equals(Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN)) {
                    bundle2 = bundle;
                } else {
                    bundle2 = new Bundle(bundle);
                    for (String str6 : bundle.keySet()) {
                        if (str6.startsWith("gad_")) {
                            bundle2.remove(str6);
                        }
                    }
                }
                zzk22.zzI(bundle42, bundle2);
                zzbb zzbbVar2 = new zzbb(this.zzu, zzgvVar2.zzb, str, zzhsVar2.zzd(), zzhsVar2.zzf(), zzhsVar2.zzh(), bundle42);
                long j3 = zzasVar.zza;
                long j22 = zzasVar.zzb;
                boolean z2 = zzasVar.zzc;
                zzg();
                zzaw();
                Preconditions.checkNotNull(zzbbVar2);
                String str52 = zzbbVar2.zza;
                Preconditions.checkNotEmpty(str52);
                byte[] zzcc2 = zzpgVar2.zzp().zzh(zzbbVar2).zzcc();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str52);
                contentValues2.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzbbVar2.zzb);
                contentValues2.put("timestamp", Long.valueOf(zzbbVar2.zzd));
                contentValues2.put("metadata_fingerprint", Long.valueOf(j22));
                contentValues2.put(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, zzcc2);
                contentValues2.put("realtime", Integer.valueOf(z2 ? 1 : 0));
                try {
                    update = zze().update("raw_events", contentValues2, "rowid = ?", new String[]{String.valueOf(j3)});
                    if (update == 1) {
                        zzicVar2.zzaV().zzb().zzc("Failed to update raw event. appId, updatedRows", zzgu.zzl(str52), Long.valueOf(update));
                        str4 = str;
                    } else {
                        str4 = str;
                    }
                } catch (SQLiteException e5) {
                    this.zzu.zzaV().zzb().zzc("Error updating raw event. appId", zzgu.zzl(zzbbVar2.zza), e5);
                    str4 = str;
                }
            }
            zza2 = zzatVar.zza();
            str4 = str;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0040, code lost:
    
        if (r6 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005a, code lost:
    
        if (r6 == null) goto L23;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0069 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzjl zzX(String str) {
        Throwable th;
        SQLiteException e;
        Cursor cursor;
        Preconditions.checkNotNull(str);
        zzg();
        zzaw();
        CursorUtil$wrapMappedColumns$2 cursorUtil$wrapMappedColumns$2 = 0;
        r3 = null;
        r3 = null;
        r3 = null;
        zzjl zzjlVar = null;
        try {
            try {
                cursor = zze().rawQuery("select consent_state, consent_source from consent_settings where app_id=? limit 1;", new String[]{str});
                try {
                } catch (SQLiteException e2) {
                    e = e2;
                    this.zzu.zzaV().zzb().zzb("Error querying database.", e);
                }
            } catch (Throwable th2) {
                th = th2;
                cursorUtil$wrapMappedColumns$2 = "select consent_state, consent_source from consent_settings where app_id=? limit 1;";
                if (cursorUtil$wrapMappedColumns$2 != 0) {
                    cursorUtil$wrapMappedColumns$2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursorUtil$wrapMappedColumns$2 != 0) {
            }
            throw th;
        }
        if (cursor.moveToFirst()) {
            zzjlVar = zzjl.zzf(cursor.getString(0), cursor.getInt(1));
        } else {
            this.zzu.zzaV().zzk().zza("No data found");
            if (cursor != null) {
                cursor.close();
                return zzjlVar != null ? zzjl.zza : zzjlVar;
            }
            if (zzjlVar != null) {
            }
        }
    }

    public final boolean zzY(String str, zzoh zzohVar) {
        zzg();
        zzaw();
        Preconditions.checkNotNull(zzohVar);
        Preconditions.checkNotEmpty(str);
        zzic zzicVar = this.zzu;
        long currentTimeMillis = zzicVar.zzaZ().currentTimeMillis();
        zzfx zzfxVar = zzfy.zzav;
        long longValue = currentTimeMillis - ((Long) zzfxVar.zzb(null)).longValue();
        long j = zzohVar.zzb;
        if (j < longValue || j > ((Long) zzfxVar.zzb(null)).longValue() + currentTimeMillis) {
            zzicVar.zzaV().zze().zzd("Storing trigger URI outside of the max retention time span. appId, now, timestamp", zzgu.zzl(str), Long.valueOf(currentTimeMillis), Long.valueOf(j));
        }
        zzicVar.zzaV().zzk().zza("Saving trigger URI");
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("trigger_uri", zzohVar.zza);
        contentValues.put("source", Integer.valueOf(zzohVar.zzc));
        contentValues.put("timestamp_millis", Long.valueOf(j));
        try {
            if (zze().insert("trigger_uris", null, contentValues) != -1) {
                return true;
            }
            zzicVar.zzaV().zzb().zzb("Failed to insert trigger URI (got -1). appId", zzgu.zzl(str));
            return false;
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzc("Error storing trigger URI. appId", zzgu.zzl(str), e);
            return false;
        }
    }

    public final void zzZ(String str, zzjl zzjlVar) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzjlVar);
        zzg();
        zzaw();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzjlVar.zzl());
        contentValues.put("consent_source", Integer.valueOf(zzjlVar.zzb()));
        zzaB("consent_settings", "app_id", contentValues);
    }

    public final zzaz zzaa(String str) {
        Preconditions.checkNotNull(str);
        zzg();
        zzaw();
        return zzaz.zzg(zzaA("select dma_consent_settings from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    public final void zzab(String str, zzaz zzazVar) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzazVar);
        zzg();
        zzaw();
        zzjl zzX = zzX(str);
        zzjl zzjlVar = zzjl.zza;
        if (zzX == zzjlVar) {
            zzZ(str, zzjlVar);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("dma_consent_settings", zzazVar.zze());
        zzaB("consent_settings", "app_id", contentValues);
    }

    public final void zzac(String str, zzjl zzjlVar) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzjlVar);
        zzg();
        zzaw();
        zzZ(str, zzX(str));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("storage_consent_at_bundling", zzjlVar.zzl());
        zzaB("consent_settings", "app_id", contentValues);
    }

    public final zzjl zzad(String str) {
        Preconditions.checkNotNull(str);
        zzg();
        zzaw();
        return zzjl.zzf(zzaA("select storage_consent_at_bundling from consent_settings where app_id=? limit 1;", new String[]{str}, ""), 100);
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x028e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x028f, code lost:
    
        r23.zzu.zzaV().zzb().zzc("Error storing event filter. appId", com.google.android.gms.measurement.internal.zzgu.zzl(r24), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0380, code lost:
    
        zzaw();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        r0 = zze();
        r3 = r17;
        r0.delete("property_filters", r3, new java.lang.String[]{r24, java.lang.String.valueOf(r10)});
        r0.delete("event_filters", r3, new java.lang.String[]{r24, java.lang.String.valueOf(r10)});
        r17 = r3;
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0258, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x023c, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01e6, code lost:
    
        r0 = r23.zzu.zzaV().zze();
        r9 = com.google.android.gms.measurement.internal.zzgu.zzl(r24);
        r11 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01fe, code lost:
    
        if (r12.zza() == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0200, code lost:
    
        r20 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x020d, code lost:
    
        r0.zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", r9, r11, java.lang.String.valueOf(r20));
        r21 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x020b, code lost:
    
        r20 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x02a4, code lost:
    
        r21 = r7;
        r0 = r0.zzc().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x02b2, code lost:
    
        if (r0.hasNext() == false) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02b4, code lost:
    
        r3 = (com.google.android.gms.internal.measurement.zzfn) r0.next();
        zzaw();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x02ce, code lost:
    
        if (r3.zzc().isEmpty() == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x02fd, code lost:
    
        r7 = r3.zzcc();
        r11 = new android.content.ContentValues();
        r11.put("app_id", r24);
        r11.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0314, code lost:
    
        if (r3.zza() == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0316, code lost:
    
        r12 = java.lang.Integer.valueOf(r3.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0320, code lost:
    
        r11.put("filter_id", r12);
        r22 = r0;
        r11.put("property_name", r3.zzc());
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0332, code lost:
    
        if (r3.zzg() == false) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0334, code lost:
    
        r0 = java.lang.Boolean.valueOf(r3.zzh());
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x033e, code lost:
    
        r11.put("session_scoped", r0);
        r11.put(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0352, code lost:
    
        if (zze().insertWithOnConflict("property_filters", null, r11, 5) != (-1)) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0368, code lost:
    
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0354, code lost:
    
        r23.zzu.zzaV().zzb().zzb("Failed to insert property filter (got -1). appId", com.google.android.gms.measurement.internal.zzgu.zzl(r24));
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x036c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x036d, code lost:
    
        r23.zzu.zzaV().zzb().zzc("Error storing property filter. appId", com.google.android.gms.measurement.internal.zzgu.zzl(r24), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x033d, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x031f, code lost:
    
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x02d0, code lost:
    
        r0 = r23.zzu.zzaV().zze();
        r8 = com.google.android.gms.measurement.internal.zzgu.zzl(r24);
        r9 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x02e8, code lost:
    
        if (r3.zza() == false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x02ea, code lost:
    
        r3 = java.lang.Integer.valueOf(r3.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x02f4, code lost:
    
        r0.zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", r8, r9, java.lang.String.valueOf(r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02f3, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x03b7, code lost:
    
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x017f, code lost:
    
        r11 = r0.zzc().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x018b, code lost:
    
        if (r11.hasNext() == false) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0197, code lost:
    
        if (((com.google.android.gms.internal.measurement.zzfn) r11.next()).zza() != false) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0199, code lost:
    
        r23.zzu.zzaV().zze().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", com.google.android.gms.measurement.internal.zzgu.zzl(r24), java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01b2, code lost:
    
        r11 = r0.zzf().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01c8, code lost:
    
        if (r11.hasNext() == false) goto L177;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01ca, code lost:
    
        r12 = (com.google.android.gms.internal.measurement.zzff) r11.next();
        zzaw();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01e4, code lost:
    
        if (r12.zzc().isEmpty() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0218, code lost:
    
        r3 = r12.zzcc();
        r21 = r7;
        r7 = new android.content.ContentValues();
        r7.put("app_id", r24);
        r7.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0231, code lost:
    
        if (r12.zza() == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0233, code lost:
    
        r9 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x023d, code lost:
    
        r7.put("filter_id", r9);
        r7.put("event_name", r12.zzc());
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x024d, code lost:
    
        if (r12.zzk() == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x024f, code lost:
    
        r9 = java.lang.Boolean.valueOf(r12.zzm());
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0259, code lost:
    
        r7.put("session_scoped", r9);
        r7.put(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x026d, code lost:
    
        if (zze().insertWithOnConflict("event_filters", null, r7, 5) != (-1)) goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0288, code lost:
    
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x026f, code lost:
    
        r23.zzu.zzaV().zzb().zzb("Failed to insert event filter (got -1). appId", com.google.android.gms.measurement.internal.zzgu.zzl(r24));
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0282, code lost:
    
        r7 = r21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final void zzae(String str, List list) {
        String str2;
        boolean z;
        String str3 = "app_id=? and audience_id=?";
        Preconditions.checkNotNull(list);
        int i = 0;
        while (i < list.size()) {
            com.google.android.gms.internal.measurement.zzfc zzfcVar = (com.google.android.gms.internal.measurement.zzfc) ((com.google.android.gms.internal.measurement.zzfd) list.get(i)).zzcl();
            if (zzfcVar.zzd() != 0) {
                int i2 = 0;
                while (i2 < zzfcVar.zzd()) {
                    com.google.android.gms.internal.measurement.zzfe zzfeVar = (com.google.android.gms.internal.measurement.zzfe) zzfcVar.zze(i2).zzcl();
                    com.google.android.gms.internal.measurement.zzfe zzfeVar2 = (com.google.android.gms.internal.measurement.zzfe) zzfeVar.clone();
                    String zzb2 = zzjm.zzb(zzfeVar.zza());
                    if (zzb2 != null) {
                        zzfeVar2.zzb(zzb2);
                        z = true;
                    } else {
                        z = false;
                    }
                    int i3 = 0;
                    while (i3 < zzfeVar.zzc()) {
                        com.google.android.gms.internal.measurement.zzfh zzd2 = zzfeVar.zzd(i3);
                        com.google.android.gms.internal.measurement.zzfe zzfeVar3 = zzfeVar;
                        String str4 = str3;
                        String zzc2 = zzlt.zzc(zzd2.zzh(), zzjn.zza, zzjn.zzb);
                        if (zzc2 != null) {
                            com.google.android.gms.internal.measurement.zzfg zzfgVar = (com.google.android.gms.internal.measurement.zzfg) zzd2.zzcl();
                            zzfgVar.zza(zzc2);
                            zzfeVar2.zze(i3, (com.google.android.gms.internal.measurement.zzfh) zzfgVar.zzbc());
                            z = true;
                        }
                        i3++;
                        zzfeVar = zzfeVar3;
                        str3 = str4;
                    }
                    String str5 = str3;
                    if (z) {
                        zzfcVar.zzf(i2, zzfeVar2);
                        list.set(i, (com.google.android.gms.internal.measurement.zzfd) zzfcVar.zzbc());
                    }
                    i2++;
                    str3 = str5;
                }
                str2 = str3;
            } else {
                str2 = str3;
            }
            if (zzfcVar.zza() != 0) {
                for (int i4 = 0; i4 < zzfcVar.zza(); i4++) {
                    com.google.android.gms.internal.measurement.zzfn zzb3 = zzfcVar.zzb(i4);
                    String zzc3 = zzlt.zzc(zzb3.zzc(), zzjo.zza, zzjo.zzb);
                    if (zzc3 != null) {
                        com.google.android.gms.internal.measurement.zzfm zzfmVar = (com.google.android.gms.internal.measurement.zzfm) zzb3.zzcl();
                        zzfmVar.zza(zzc3);
                        zzfcVar.zzc(i4, zzfmVar);
                        list.set(i, (com.google.android.gms.internal.measurement.zzfd) zzfcVar.zzbc());
                    }
                }
            }
            i++;
            str3 = str2;
        }
        String str6 = str3;
        zzaw();
        zzg();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase zze2 = zze();
        zze2.beginTransaction();
        try {
            zzaw();
            zzg();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase zze3 = zze();
            zze3.delete("property_filters", "app_id=?", new String[]{str});
            zze3.delete("event_filters", "app_id=?", new String[]{str});
            Iterator it = list.iterator();
            while (it.hasNext()) {
                com.google.android.gms.internal.measurement.zzfd zzfdVar = (com.google.android.gms.internal.measurement.zzfd) it.next();
                zzaw();
                zzg();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzfdVar);
                if (zzfdVar.zza()) {
                    int zzb4 = zzfdVar.zzb();
                    Iterator it2 = zzfdVar.zzf().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (!((com.google.android.gms.internal.measurement.zzff) it2.next()).zza()) {
                                this.zzu.zzaV().zze().zzc("Event filter with no ID. Audience definition ignored. appId, audienceId", zzgu.zzl(str), Integer.valueOf(zzb4));
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    this.zzu.zzaV().zze().zzb("Audience with no ID. appId", zzgu.zzl(str));
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                com.google.android.gms.internal.measurement.zzfd zzfdVar2 = (com.google.android.gms.internal.measurement.zzfd) it3.next();
                arrayList.add(zzfdVar2.zza() ? Integer.valueOf(zzfdVar2.zzb()) : null);
            }
            Preconditions.checkNotEmpty(str);
            zzaw();
            zzg();
            SQLiteDatabase zze4 = zze();
            try {
                long zzay = zzay("select count(1) from audience_filter_values where app_id=?", new String[]{str});
                int max = Math.max(0, Math.min(2000, this.zzu.zzc().zzm(str, zzfy.zzU)));
                if (zzay > max) {
                    ArrayList arrayList2 = new ArrayList();
                    int i5 = 0;
                    while (true) {
                        if (i5 >= arrayList.size()) {
                            String join = TextUtils.join(",", arrayList2);
                            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
                            sb.append("(");
                            sb.append(join);
                            sb.append(")");
                            String sb2 = sb.toString();
                            StringBuilder sb3 = new StringBuilder(sb2.length() + 140);
                            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
                            sb3.append(sb2);
                            sb3.append(" order by rowid desc limit -1 offset ?)");
                            zze4.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)});
                            break;
                        }
                        Integer num = (Integer) arrayList.get(i5);
                        if (num == null) {
                            break;
                        }
                        arrayList2.add(Integer.toString(num.intValue()));
                        i5++;
                    }
                }
            } catch (SQLiteException e) {
                this.zzu.zzaV().zzb().zzc("Database error querying filters. appId", zzgu.zzl(str), e);
            }
            zze2.setTransactionSuccessful();
        } finally {
            zze2.endTransaction();
        }
    }

    final zzbc zzaf(String str, com.google.android.gms.internal.measurement.zzhs zzhsVar, String str2) {
        zzbc zzaC = zzaC("events", str, zzhsVar.zzd());
        if (zzaC == null) {
            zzic zzicVar = this.zzu;
            zzicVar.zzaV().zze().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzgu.zzl(str), zzicVar.zzl().zza(str2));
            return new zzbc(str, zzhsVar.zzd(), 1L, 1L, 1L, zzhsVar.zzf(), 0L, null, null, null, null);
        }
        long j = zzaC.zze + 1;
        long j2 = zzaC.zzd + 1;
        return new zzbc(zzaC.zza, zzaC.zzb, zzaC.zzc + 1, j2, j, zzaC.zzf, zzaC.zzg, zzaC.zzh, zzaC.zzi, zzaC.zzj, zzaC.zzk);
    }

    protected final boolean zzag() {
        zzic zzicVar = this.zzu;
        Context zzaY = zzicVar.zzaY();
        zzicVar.zzc();
        return zzaY.getDatabasePath("google_app_measurement.db").exists();
    }

    final /* synthetic */ long zzah(String str, String[] strArr, long j) {
        return zzaz("select rowid from raw_events where app_id = ? and timestamp < ? order by rowid desc limit 1", strArr, -1L);
    }

    final /* synthetic */ zzog zzas() {
        return this.zzn;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x029d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzat(String str, long j, long j2, zzpc zzpcVar) {
        Cursor cursor;
        String str2;
        Cursor cursor2;
        SQLiteDatabase zze2;
        String str3;
        String string;
        zzic zzicVar;
        String[] strArr;
        String str4;
        zzic zzicVar2;
        long j3;
        Preconditions.checkNotNull(zzpcVar);
        zzg();
        zzaw();
        try {
            zze2 = zze();
        } catch (SQLiteException e) {
            e = e;
            str2 = str;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            cursor = null;
        }
        if (TextUtils.isEmpty(str)) {
            String[] strArr2 = j2 != -1 ? new String[]{String.valueOf(j2), String.valueOf(j)} : new String[]{String.valueOf(j)};
            str3 = j2 != -1 ? "rowid <= ? and " : "";
            StringBuilder sb = new StringBuilder(str3.length() + 148);
            sb.append("select app_id, metadata_fingerprint from raw_events where ");
            sb.append(str3);
            sb.append("app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;");
            cursor2 = zze2.rawQuery(sb.toString(), strArr2);
            try {
                try {
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursor2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e2) {
                e = e2;
                str2 = str;
            }
            if (!cursor2.moveToFirst()) {
                if (cursor2 == null) {
                    cursor2.close();
                    return;
                }
                return;
            }
            str2 = cursor2.getString(0);
            try {
                string = cursor2.getString(1);
                cursor2.close();
            } catch (SQLiteException e3) {
                e = e3;
                cursor = cursor2;
                try {
                    this.zzu.zzaV().zzb().zzc("Data loss. Error selecting raw event. appId", zzgu.zzl(str2), e);
                    cursor2 = cursor;
                    if (cursor2 == null) {
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
        } else {
            String[] strArr3 = j2 != -1 ? new String[]{str, String.valueOf(j2)} : new String[]{str};
            str3 = j2 != -1 ? " and rowid <= ?" : "";
            StringBuilder sb2 = new StringBuilder(str3.length() + 84);
            sb2.append("select metadata_fingerprint from raw_events where app_id = ?");
            sb2.append(str3);
            sb2.append(" order by rowid limit 1;");
            cursor2 = zze2.rawQuery(sb2.toString(), strArr3);
            try {
            } catch (SQLiteException e4) {
                e = e4;
                str2 = str;
                cursor = cursor2;
                this.zzu.zzaV().zzb().zzc("Data loss. Error selecting raw event. appId", zzgu.zzl(str2), e);
                cursor2 = cursor;
                if (cursor2 == null) {
                }
            } catch (Throwable th4) {
                th = th4;
                cursor = cursor2;
                if (cursor != null) {
                }
                throw th;
            }
            if (cursor2.moveToFirst()) {
                string = cursor2.getString(0);
                cursor2.close();
                str2 = str;
            } else if (cursor2 == null) {
            }
        }
        cursor2 = zze2.query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{str2, string}, null, null, "rowid", "2");
        if (cursor2.moveToFirst()) {
            try {
                com.google.android.gms.internal.measurement.zzid zzidVar = (com.google.android.gms.internal.measurement.zzid) ((com.google.android.gms.internal.measurement.zzic) zzpk.zzw(com.google.android.gms.internal.measurement.zzid.zzaE(), cursor2.getBlob(0))).zzbc();
                if (cursor2.moveToNext()) {
                    this.zzu.zzaV().zze().zzb("Get multiple raw event metadata records, expected one. appId", zzgu.zzl(str2));
                }
                cursor2.close();
                Preconditions.checkNotNull(zzidVar);
                zzpcVar.zza = zzidVar;
                zzic zzicVar3 = this.zzu;
                if (zzicVar3.zzc().zzp(null, zzfy.zzbk)) {
                    zzicVar = zzicVar3;
                    long zzaz = zzaz("select (rowid - 1) as max_rowid from raw_events where app_id = ? and metadata_fingerprint != ? order by rowid limit 1;", new String[]{str2, string}, -1L);
                    if (j2 != -1) {
                        j3 = j2;
                    } else if (zzaz != -1) {
                        j3 = -1;
                    } else {
                        strArr = new String[]{str2, string};
                        str4 = "app_id = ? and metadata_fingerprint = ?";
                    }
                    if (j3 != -1 && zzaz != -1) {
                        zzaz = Math.min(j3, zzaz);
                    } else if (j3 != -1) {
                        zzaz = j3;
                    }
                    str4 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                    strArr = new String[]{str2, string, String.valueOf(zzaz)};
                } else {
                    zzicVar = zzicVar3;
                    if (j2 != -1) {
                        str4 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                        strArr = new String[]{str2, string, String.valueOf(j2)};
                    } else {
                        strArr = new String[]{str2, string};
                        str4 = "app_id = ? and metadata_fingerprint = ?";
                    }
                }
                zzicVar2 = zzicVar;
                cursor2 = zze2.query("raw_events", new String[]{"rowid", AppMeasurementSdk.ConditionalUserProperty.NAME, "timestamp", Constants.ScionAnalytics.MessageType.DATA_MESSAGE}, str4, strArr, null, null, "rowid", null);
            } catch (IOException e5) {
                this.zzu.zzaV().zzb().zzc("Data loss. Failed to merge raw event metadata. appId", zzgu.zzl(str2), e5);
            }
            try {
                if (cursor2.moveToFirst()) {
                    do {
                        long j4 = cursor2.getLong(0);
                        try {
                            com.google.android.gms.internal.measurement.zzhr zzhrVar = (com.google.android.gms.internal.measurement.zzhr) zzpk.zzw(com.google.android.gms.internal.measurement.zzhs.zzk(), cursor2.getBlob(3));
                            zzhrVar.zzl(cursor2.getString(1));
                            zzhrVar.zzo(cursor2.getLong(2));
                            if (!zzpcVar.zza(j4, (com.google.android.gms.internal.measurement.zzhs) zzhrVar.zzbc())) {
                                break;
                            }
                        } catch (IOException e6) {
                            this.zzu.zzaV().zzb().zzc("Data loss. Failed to merge raw event. appId", zzgu.zzl(str2), e6);
                        }
                    } while (cursor2.moveToNext());
                } else {
                    zzicVar2.zzaV().zze().zzb("Raw event data disappeared while in transaction. appId", zzgu.zzl(str2));
                }
            } catch (SQLiteException e7) {
                e = e7;
                cursor = cursor2;
                this.zzu.zzaV().zzb().zzc("Data loss. Error selecting raw event. appId", zzgu.zzl(str2), e);
                cursor2 = cursor;
                if (cursor2 == null) {
                }
            } catch (Throwable th5) {
                th = th5;
                cursor = cursor2;
                if (cursor != null) {
                }
                throw th;
            }
        } else {
            this.zzu.zzaV().zzb().zzb("Raw event metadata record is missing. appId", zzgu.zzl(str2));
        }
        if (cursor2 == null) {
        }
    }

    public final void zzb() {
        zzaw();
        zze().beginTransaction();
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    protected final boolean zzbb() {
        return false;
    }

    public final void zzc() {
        zzaw();
        zze().setTransactionSuccessful();
    }

    public final void zzd() {
        zzaw();
        zze().endTransaction();
    }

    final SQLiteDatabase zze() {
        zzg();
        try {
            return this.zzm.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzu.zzaV().zze().zzb("Error opening database", e);
            throw e;
        }
    }

    public final zzbc zzf(String str, String str2) {
        return zzaC("events", str, str2);
    }

    public final void zzh(zzbc zzbcVar) {
        zzaD("events", zzbcVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzi(String str) {
        zzbc zzaC;
        zzaE("events_snapshot", str);
        Cursor cursor = null;
        try {
            cursor = zze().query("events", (String[]) Collections.singletonList(AppMeasurementSdk.ConditionalUserProperty.NAME).toArray(new String[0]), "app_id=?", new String[]{str}, null, null, null);
            try {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            String string = cursor.getString(0);
                            if (string != null && (zzaC = zzaC("events", str, string)) != null) {
                                zzaD("events_snapshot", zzaC);
                            }
                        } while (cursor.moveToNext());
                    }
                } catch (SQLiteException e) {
                    e = e;
                    this.zzu.zzaV().zzb().zzc("Error creating snapshot. appId", zzgu.zzl(str), e);
                    if (cursor == null) {
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
            }
            throw th;
        }
        if (cursor == null) {
            cursor.close();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x005e, code lost:
    
        if (r10 != null) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00cb, code lost:
    
        zzaD("events", r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c9, code lost:
    
        if (r10 != null) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzj(String str) {
        boolean z;
        zzbc zzaC;
        boolean z2 = false;
        ArrayList arrayList = new ArrayList(Arrays.asList(AppMeasurementSdk.ConditionalUserProperty.NAME, "lifetime_count"));
        zzbc zzaC2 = zzaC("events", str, "_f");
        zzbc zzaC3 = zzaC("events", str, "_v");
        zzaE("events", str);
        Cursor cursor = null;
        try {
            cursor = zze().query("events_snapshot", (String[]) arrayList.toArray(new String[0]), "app_id=?", new String[]{str}, null, null, null);
            try {
            } catch (SQLiteException e) {
                e = e;
                z = false;
            } catch (Throwable th) {
                th = th;
                z = false;
            }
        } catch (SQLiteException e2) {
            e = e2;
            z = false;
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
        if (!cursor.moveToFirst()) {
            if (cursor != null) {
                cursor.close();
            }
            if (zzaC2 == null) {
            }
            zzaD("events", zzaC2);
            zzaE("events_snapshot", str);
        }
        boolean z3 = false;
        z = false;
        do {
            try {
                String string = cursor.getString(0);
                if (cursor.getLong(1) >= 1) {
                    if ("_f".equals(string)) {
                        z3 = true;
                    } else if ("_v".equals(string)) {
                        z = true;
                    }
                }
                if (string != null && (zzaC = zzaC("events_snapshot", str, string)) != null) {
                    zzaD("events", zzaC);
                }
            } catch (SQLiteException e3) {
                e = e3;
                z2 = z3;
                try {
                    this.zzu.zzaV().zzb().zzc("Error querying snapshot. appId", zzgu.zzl(str), e);
                    z3 = z2;
                    if (cursor != null) {
                    }
                    if (!z3) {
                    }
                    if (!z) {
                    }
                    zzaE("events_snapshot", str);
                } catch (Throwable th3) {
                    th = th3;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (z2 && zzaC2 != null) {
                        zzaD("events", zzaC2);
                    } else if (!z && zzaC3 != null) {
                        zzaD("events", zzaC3);
                    }
                    zzaE("events_snapshot", str);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                z2 = z3;
                if (cursor != null) {
                }
                if (z2) {
                }
                if (!z) {
                    zzaD("events", zzaC3);
                }
                zzaE("events_snapshot", str);
                throw th;
            }
        } while (cursor.moveToNext());
        if (cursor != null) {
            cursor.close();
        }
        if (!z3 || zzaC2 == null) {
            if (!z) {
            }
            zzaE("events_snapshot", str);
        }
        zzaD("events", zzaC2);
        zzaE("events_snapshot", str);
    }

    public final void zzk(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzaw();
        try {
            zze().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzic zzicVar = this.zzu;
            zzicVar.zzaV().zzb().zzd("Error deleting user property. appId", zzgu.zzl(str), zzicVar.zzl().zzc(str2), e);
        }
    }

    public final boolean zzl(zzpn zzpnVar) {
        Preconditions.checkNotNull(zzpnVar);
        zzg();
        zzaw();
        String str = zzpnVar.zza;
        String str2 = zzpnVar.zzc;
        if (zzm(str, str2) == null) {
            if (zzpp.zzh(str2)) {
                if (zzay("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{str}) >= this.zzu.zzc().zzn(str, zzfy.zzV, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(str2)) {
                long zzay = zzay("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{str, zzpnVar.zzb});
                this.zzu.zzc();
                if (zzay >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzpnVar.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, str2);
        contentValues.put("set_timestamp", Long.valueOf(zzpnVar.zzd));
        zzau(contentValues, "value", zzpnVar.zze);
        try {
            if (zze().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                this.zzu.zzaV().zzb().zzb("Failed to insert/update user property (got -1). appId", zzgu.zzl(str));
            }
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzc("Error storing user property. appId", zzgu.zzl(zzpnVar.zza), e);
        }
        return true;
    }

    /* JADX WARN: Not initialized variable reg: 11, insn: 0x00a3: MOVE (r10 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:26:0x00a3 */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzpn zzm(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzaw();
        Cursor cursor3 = null;
        try {
            try {
                cursor = zze().query("user_attributes", new String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (cursor.moveToFirst()) {
                        long j = cursor.getLong(0);
                        Object zzL = zzL(cursor, 1);
                        if (zzL != null) {
                            zzpn zzpnVar = new zzpn(str, cursor.getString(2), str2, j, zzL);
                            if (cursor.moveToNext()) {
                                this.zzu.zzaV().zzb().zzb("Got multiple records for user property, expected one. appId", zzgu.zzl(str));
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            return zzpnVar;
                        }
                    }
                } catch (SQLiteException e) {
                    e = e;
                    zzic zzicVar = this.zzu;
                    zzicVar.zzaV().zzb().zzd("Error querying user property. appId", zzgu.zzl(str), zzicVar.zzl().zzc(str2), e);
                    if (cursor != null) {
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor3 = cursor2;
                if (cursor3 != null) {
                    cursor3.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor3 != null) {
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzn(String str) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzaw();
        List arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                zzic zzicVar = this.zzu;
                zzicVar.zzc();
                cursor = zze().query("user_attributes", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME, "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            String string = cursor.getString(0);
                            String string2 = cursor.getString(1);
                            if (string2 == null) {
                                string2 = "";
                            }
                            String str2 = string2;
                            long j = cursor.getLong(2);
                            Object zzL = zzL(cursor, 3);
                            if (zzL == null) {
                                zzicVar.zzaV().zzb().zzb("Read invalid user property value, ignoring it. appId", zzgu.zzl(str));
                            } else {
                                arrayList.add(new zzpn(str, str2, string, j, zzL));
                            }
                        } while (cursor.moveToNext());
                    }
                } catch (SQLiteException e) {
                    e = e;
                    this.zzu.zzaV().zzb().zzc("Error querying user properties. appId", zzgu.zzl(str), e);
                    arrayList = Collections.emptyList();
                    if (cursor != null) {
                    }
                    return arrayList;
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00bc, code lost:
    
        r0 = r5.zzaV().zzb();
        r5.zzc();
        r0.zzb("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0145  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzo(String str, String str2, String str3) {
        Cursor cursor;
        String str4;
        Cursor cursor2;
        Preconditions.checkNotEmpty(str);
        zzg();
        zzaw();
        List arrayList = new ArrayList();
        try {
            try {
                ArrayList arrayList2 = new ArrayList(3);
                arrayList2.add(str);
                StringBuilder sb = new StringBuilder("app_id=?");
                if (TextUtils.isEmpty(str2)) {
                    str4 = str2;
                } else {
                    str4 = str2;
                    try {
                        arrayList2.add(str4);
                        sb.append(" and origin=?");
                    } catch (SQLiteException e) {
                        e = e;
                        cursor = null;
                        try {
                            this.zzu.zzaV().zzb().zzd("(2)Error querying user properties", zzgu.zzl(str), str4, e);
                            arrayList = Collections.emptyList();
                            cursor2 = cursor;
                            if (cursor2 != null) {
                            }
                            return arrayList;
                        } catch (Throwable th) {
                            th = th;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!TextUtils.isEmpty(str3)) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 1);
                    sb2.append(str3);
                    sb2.append("*");
                    arrayList2.add(sb2.toString());
                    sb.append(" and name glob ?");
                }
                String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
                SQLiteDatabase zze2 = zze();
                String[] strArr2 = {AppMeasurementSdk.ConditionalUserProperty.NAME, "set_timestamp", "value", "origin"};
                String sb3 = sb.toString();
                zzic zzicVar = this.zzu;
                zzicVar.zzc();
                cursor2 = zze2.query("user_attributes", strArr2, sb3, strArr, null, null, "rowid", "1001");
                try {
                    try {
                        if (cursor2.moveToFirst()) {
                            while (true) {
                                int size = arrayList.size();
                                zzicVar.zzc();
                                if (size >= 1000) {
                                    break;
                                }
                                String string = cursor2.getString(0);
                                long j = cursor2.getLong(1);
                                Object zzL = zzL(cursor2, 2);
                                String string2 = cursor2.getString(3);
                                if (zzL == null) {
                                    try {
                                        zzicVar.zzaV().zzb().zzd("(2)Read invalid user property value, ignoring it", zzgu.zzl(str), string2, str3);
                                    } catch (SQLiteException e2) {
                                        e = e2;
                                        cursor = cursor2;
                                        str4 = string2;
                                        this.zzu.zzaV().zzb().zzd("(2)Error querying user properties", zzgu.zzl(str), str4, e);
                                        arrayList = Collections.emptyList();
                                        cursor2 = cursor;
                                        if (cursor2 != null) {
                                        }
                                        return arrayList;
                                    }
                                } else {
                                    arrayList.add(new zzpn(str, string2, string, j, zzL));
                                }
                                if (!cursor2.moveToNext()) {
                                    break;
                                }
                                str4 = string2;
                            }
                        }
                    } catch (SQLiteException e3) {
                        e = e3;
                        cursor = cursor2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursor2;
                    if (cursor != null) {
                    }
                    throw th;
                }
            } catch (SQLiteException e4) {
                e = e4;
                str4 = str2;
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    public final boolean zzp(zzah zzahVar) {
        Preconditions.checkNotNull(zzahVar);
        zzg();
        zzaw();
        String str = zzahVar.zza;
        Preconditions.checkNotNull(str);
        if (zzm(str, zzahVar.zzc.zzb) == null) {
            long zzay = zzay("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            this.zzu.zzc();
            if (zzay >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzahVar.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzahVar.zzc.zzb);
        zzau(contentValues, "value", Preconditions.checkNotNull(zzahVar.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzahVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzahVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzahVar.zzh));
        zzic zzicVar = this.zzu;
        contentValues.put("timed_out_event", zzicVar.zzk().zzae(zzahVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzahVar.zzd));
        contentValues.put("triggered_event", zzicVar.zzk().zzae(zzahVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzahVar.zzc.zzc));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzahVar.zzj));
        contentValues.put("expired_event", zzicVar.zzk().zzae(zzahVar.zzk));
        try {
            if (zze().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzicVar.zzaV().zzb().zzb("Failed to insert/update conditional user property (got -1)", zzgu.zzl(str));
            }
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzc("Error storing conditional user property", zzgu.zzl(str), e);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0144  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzah zzq(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzaw();
        try {
            cursor2 = zze().query("conditional_properties", new String[]{"origin", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                try {
                } catch (SQLiteException e) {
                    e = e;
                    zzic zzicVar = this.zzu;
                    zzicVar.zzaV().zzb().zzd("Error querying conditional property", zzgu.zzl(str), zzicVar.zzl().zzc(str2), e);
                    if (cursor2 != null) {
                    }
                }
            } catch (Throwable th) {
                th = th;
                cursor = cursor2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor2 = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
        if (!cursor2.moveToFirst()) {
            if (cursor2 != null) {
                return null;
            }
            cursor2.close();
            return null;
        }
        String string = cursor2.getString(0);
        if (string == null) {
            string = "";
        }
        Object zzL = zzL(cursor2, 1);
        boolean z = cursor2.getInt(2) != 0;
        String string2 = cursor2.getString(3);
        long j = cursor2.getLong(4);
        zzpg zzpgVar = this.zzg;
        String str3 = string;
        zzah zzahVar = new zzah(str, str3, new zzpl(str2, cursor2.getLong(8), zzL, string), cursor2.getLong(6), z, string2, (zzbg) zzpgVar.zzp().zzl(cursor2.getBlob(5), zzbg.CREATOR), j, (zzbg) zzpgVar.zzp().zzl(cursor2.getBlob(7), zzbg.CREATOR), cursor2.getLong(9), (zzbg) zzpgVar.zzp().zzl(cursor2.getBlob(10), zzbg.CREATOR));
        if (cursor2.moveToNext()) {
            zzic zzicVar2 = this.zzu;
            zzicVar2.zzaV().zzb().zzc("Got multiple records for conditional property, expected one", zzgu.zzl(str), zzicVar2.zzl().zzc(str2));
        }
        if (cursor2 != null) {
            cursor2.close();
        }
        return zzahVar;
    }

    public final int zzr(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzaw();
        try {
            return zze().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzic zzicVar = this.zzu;
            zzicVar.zzaV().zzb().zzd("Error deleting conditional property", zzgu.zzl(str), zzicVar.zzl().zzc(str2), e);
            return 0;
        }
    }

    public final List zzs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzaw();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            String.valueOf(str3);
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzt(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x008f, code lost:
    
        r3 = r5.zzaV().zzb();
        r5.zzc();
        r3.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0165  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzt(String str, String[] strArr) {
        Cursor cursor;
        Cursor cursor2;
        zzic zzicVar;
        zzg();
        zzaw();
        List arrayList = new ArrayList();
        try {
            SQLiteDatabase zze2 = zze();
            String[] strArr2 = {"app_id", "origin", AppMeasurementSdk.ConditionalUserProperty.NAME, "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"};
            zzicVar = this.zzu;
            zzicVar.zzc();
            cursor2 = zze2.query("conditional_properties", strArr2, str, strArr, null, null, "rowid", "1001");
        } catch (SQLiteException e) {
            e = e;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            cursor = null;
        }
        try {
            if (cursor2.moveToFirst()) {
                while (true) {
                    int size = arrayList.size();
                    zzicVar.zzc();
                    if (size >= 1000) {
                        break;
                    }
                    String string = cursor2.getString(0);
                    String string2 = cursor2.getString(1);
                    String string3 = cursor2.getString(2);
                    Object zzL = zzL(cursor2, 3);
                    boolean z = cursor2.getInt(4) != 0;
                    String string4 = cursor2.getString(5);
                    long j = cursor2.getLong(6);
                    zzpg zzpgVar = this.zzg;
                    arrayList.add(new zzah(string, string2, new zzpl(string3, cursor2.getLong(10), zzL, string2), cursor2.getLong(8), z, string4, (zzbg) zzpgVar.zzp().zzl(cursor2.getBlob(7), zzbg.CREATOR), j, (zzbg) zzpgVar.zzp().zzl(cursor2.getBlob(9), zzbg.CREATOR), cursor2.getLong(11), (zzbg) zzpgVar.zzp().zzl(cursor2.getBlob(12), zzbg.CREATOR)));
                    if (!cursor2.moveToNext()) {
                        break;
                    }
                }
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = cursor2;
            try {
                this.zzu.zzaV().zzb().zzb("Error querying conditional user property value", e);
                arrayList = Collections.emptyList();
                cursor2 = cursor;
                if (cursor2 != null) {
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = cursor2;
            if (cursor != null) {
            }
            throw th;
        }
        if (cursor2 != null) {
            cursor2.close();
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x03c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzh zzu(String str) {
        Cursor cursor;
        Cursor cursor2;
        Boolean valueOf;
        Preconditions.checkNotEmpty(str);
        zzg();
        zzaw();
        try {
            cursor2 = zze().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events", "ga_app_id", "session_stitching_token", "sgtm_upload_enabled", "target_os_version", "session_stitching_token_hash", "ad_services_version", "unmatched_first_open_without_ad_id", "npa_metadata_value", "attribution_eligibility_status", "sgtm_preview_key", "dma_consent_state", "daily_realtime_dcu_count", "bundle_delivery_index", "serialized_npa_metadata", "unmatched_pfo", "unmatched_uwa", "ad_campaign_info", "client_upload_eligibility"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                try {
                } catch (SQLiteException e) {
                    e = e;
                    this.zzu.zzaV().zzb().zzc("Error querying app. appId", zzgu.zzl(str), e);
                    if (cursor2 != null) {
                    }
                }
            } catch (Throwable th) {
                th = th;
                cursor = cursor2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor2 = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
        if (!cursor2.moveToFirst()) {
            if (cursor2 != null) {
                return null;
            }
            cursor2.close();
            return null;
        }
        zzpg zzpgVar = this.zzg;
        zzh zzhVar = new zzh(zzpgVar.zzag(), str);
        zzjl zzB = zzpgVar.zzB(str);
        zzjk zzjkVar = zzjk.ANALYTICS_STORAGE;
        if (zzB.zzo(zzjkVar)) {
            zzhVar.zze(cursor2.getString(0));
        }
        zzhVar.zzg(cursor2.getString(1));
        if (zzpgVar.zzB(str).zzo(zzjk.AD_STORAGE)) {
            zzhVar.zzk(cursor2.getString(2));
        }
        zzhVar.zzF(cursor2.getLong(3));
        zzhVar.zzo(cursor2.getLong(4));
        zzhVar.zzq(cursor2.getLong(5));
        zzhVar.zzs(cursor2.getString(6));
        zzhVar.zzw(cursor2.getString(7));
        zzhVar.zzy(cursor2.getLong(8));
        zzhVar.zzA(cursor2.getLong(9));
        zzhVar.zzE(!cursor2.isNull(10) ? cursor2.getInt(10) != 0 : true);
        zzhVar.zzO(cursor2.getLong(11));
        zzhVar.zzQ(cursor2.getLong(12));
        zzhVar.zzS(cursor2.getLong(13));
        zzhVar.zzU(cursor2.getLong(14));
        zzhVar.zzI(cursor2.getLong(15));
        zzhVar.zzK(cursor2.getLong(16));
        zzhVar.zzu(cursor2.isNull(17) ? -2147483648L : cursor2.getInt(17));
        zzhVar.zzm(cursor2.getString(18));
        zzhVar.zzY(cursor2.getLong(19));
        zzhVar.zzW(cursor2.getLong(20));
        zzhVar.zzab(cursor2.getString(21));
        zzhVar.zzad(!cursor2.isNull(23) ? cursor2.getInt(23) != 0 : true);
        zzhVar.zzC(cursor2.isNull(25) ? 0L : cursor2.getLong(25));
        if (!cursor2.isNull(26)) {
            zzhVar.zzah(Arrays.asList(cursor2.getString(26).split(",", -1)));
        }
        if (zzpgVar.zzB(str).zzo(zzjkVar)) {
            zzhVar.zzi(cursor2.getString(28));
        }
        zzhVar.zzaj((cursor2.isNull(29) || cursor2.getInt(29) == 0) ? false : true);
        zzhVar.zzaE(cursor2.getLong(39));
        zzhVar.zzaz(cursor2.getString(36));
        zzhVar.zzal(cursor2.getLong(30));
        zzhVar.zzan(cursor2.getLong(31));
        zzqp.zza();
        zzic zzicVar = this.zzu;
        if (zzicVar.zzc().zzp(str, zzfy.zzaP)) {
            zzhVar.zzap(cursor2.getInt(32));
            zzhVar.zzax(cursor2.getLong(35));
        }
        zzhVar.zzar((cursor2.isNull(33) || cursor2.getInt(33) == 0) ? false : true);
        if (cursor2.isNull(34)) {
            valueOf = null;
        } else {
            valueOf = Boolean.valueOf(cursor2.getInt(34) != 0);
        }
        zzhVar.zzaf(valueOf);
        zzhVar.zzaB(cursor2.getInt(37));
        zzhVar.zzaD(cursor2.getInt(38));
        zzhVar.zzaG(cursor2.isNull(40) ? "" : (String) Preconditions.checkNotNull(cursor2.getString(40)));
        if (!cursor2.isNull(41)) {
            zzhVar.zzat(Long.valueOf(cursor2.getLong(41)));
        }
        if (!cursor2.isNull(42)) {
            zzhVar.zzav(Long.valueOf(cursor2.getLong(42)));
        }
        zzhVar.zzaI(cursor2.getBlob(43));
        if (!cursor2.isNull(44)) {
            zzhVar.zzaK(cursor2.getInt(44));
        }
        zzhVar.zzb();
        if (cursor2.moveToNext()) {
            zzicVar.zzaV().zzb().zzb("Got multiple records for app, expected one. appId", zzgu.zzl(str));
        }
        if (cursor2 != null) {
            cursor2.close();
        }
        return zzhVar;
    }

    public final void zzv(zzh zzhVar, boolean z, boolean z2) {
        Preconditions.checkNotNull(zzhVar);
        zzg();
        zzaw();
        String zzc2 = zzhVar.zzc();
        Preconditions.checkNotNull(zzc2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzc2);
        if (z) {
            contentValues.put("app_instance_id", (String) null);
        } else if (this.zzg.zzB(zzc2).zzo(zzjk.ANALYTICS_STORAGE)) {
            contentValues.put("app_instance_id", zzhVar.zzd());
        }
        contentValues.put("gmp_app_id", zzhVar.zzf());
        zzpg zzpgVar = this.zzg;
        if (zzpgVar.zzB(zzc2).zzo(zzjk.AD_STORAGE)) {
            contentValues.put("resettable_device_id_hash", zzhVar.zzj());
        }
        contentValues.put("last_bundle_index", Long.valueOf(zzhVar.zzG()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzhVar.zzn()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzhVar.zzp()));
        contentValues.put("app_version", zzhVar.zzr());
        contentValues.put("app_store", zzhVar.zzv());
        contentValues.put("gmp_version", Long.valueOf(zzhVar.zzx()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzhVar.zzz()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzhVar.zzD()));
        contentValues.put("day", Long.valueOf(zzhVar.zzN()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzhVar.zzP()));
        contentValues.put("daily_events_count", Long.valueOf(zzhVar.zzR()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzhVar.zzT()));
        contentValues.put("config_fetched_time", Long.valueOf(zzhVar.zzH()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzhVar.zzJ()));
        contentValues.put("app_version_int", Long.valueOf(zzhVar.zzt()));
        contentValues.put("firebase_instance_id", zzhVar.zzl());
        contentValues.put("daily_error_events_count", Long.valueOf(zzhVar.zzX()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzhVar.zzV()));
        contentValues.put("health_monitor_sample", zzhVar.zzZ());
        contentValues.put("android_id", (Long) 0L);
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzhVar.zzac()));
        contentValues.put("dynamite_version", Long.valueOf(zzhVar.zzB()));
        if (zzpgVar.zzB(zzc2).zzo(zzjk.ANALYTICS_STORAGE)) {
            contentValues.put("session_stitching_token", zzhVar.zzh());
        }
        contentValues.put("sgtm_upload_enabled", Boolean.valueOf(zzhVar.zzai()));
        contentValues.put("target_os_version", Long.valueOf(zzhVar.zzak()));
        contentValues.put("session_stitching_token_hash", Long.valueOf(zzhVar.zzam()));
        zzqp.zza();
        zzic zzicVar = this.zzu;
        if (zzicVar.zzc().zzp(zzc2, zzfy.zzaP)) {
            contentValues.put("ad_services_version", Integer.valueOf(zzhVar.zzao()));
            contentValues.put("attribution_eligibility_status", Long.valueOf(zzhVar.zzaw()));
        }
        contentValues.put("unmatched_first_open_without_ad_id", Boolean.valueOf(zzhVar.zzaq()));
        contentValues.put("npa_metadata_value", zzhVar.zzae());
        contentValues.put("bundle_delivery_index", Long.valueOf(zzhVar.zzaF()));
        contentValues.put("sgtm_preview_key", zzhVar.zzay());
        contentValues.put("dma_consent_state", Integer.valueOf(zzhVar.zzaA()));
        contentValues.put("daily_realtime_dcu_count", Integer.valueOf(zzhVar.zzaC()));
        contentValues.put("serialized_npa_metadata", zzhVar.zzaH());
        contentValues.put("client_upload_eligibility", Integer.valueOf(zzhVar.zzaL()));
        List zzag = zzhVar.zzag();
        if (zzag != null) {
            if (zzag.isEmpty()) {
                zzicVar.zzaV().zze().zzb("Safelisted events should not be an empty list. appId", zzc2);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzag));
            }
        }
        zzpr.zza();
        if (zzicVar.zzc().zzp(null, zzfy.zzaK) && !contentValues.containsKey("safelisted_events")) {
            contentValues.put("safelisted_events", (String) null);
        }
        contentValues.put("unmatched_pfo", zzhVar.zzas());
        contentValues.put("unmatched_uwa", zzhVar.zzau());
        contentValues.put("ad_campaign_info", zzhVar.zzaJ());
        try {
            SQLiteDatabase zze2 = zze();
            if (zze2.update("apps", contentValues, "app_id = ?", new String[]{zzc2}) == 0 && zze2.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzicVar.zzaV().zzb().zzb("Failed to insert/update app (got -1). appId", zzgu.zzl(zzc2));
            }
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzc("Error storing app. appId", zzgu.zzl(zzc2), e);
        }
    }

    public final zzar zzw(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        return zzx(j, str, 1L, false, false, z3, false, z5, z6, z7);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x015e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzar zzx(long j, String str, long j2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        Cursor cursor;
        Cursor cursor2;
        Preconditions.checkNotEmpty(str);
        zzg();
        zzaw();
        String[] strArr = {str};
        zzar zzarVar = new zzar();
        try {
            try {
                SQLiteDatabase zze2 = zze();
                cursor2 = zze2.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count", "daily_realtime_dcu_count", "daily_registered_triggers_count"}, "app_id=?", new String[]{str}, null, null, null);
                try {
                    if (cursor2.moveToFirst()) {
                        if (cursor2.getLong(0) == j) {
                            zzarVar.zzb = cursor2.getLong(1);
                            zzarVar.zza = cursor2.getLong(2);
                            zzarVar.zzc = cursor2.getLong(3);
                            zzarVar.zzd = cursor2.getLong(4);
                            zzarVar.zze = cursor2.getLong(5);
                            zzarVar.zzf = cursor2.getLong(6);
                            zzarVar.zzg = cursor2.getLong(7);
                        }
                        if (z) {
                            zzarVar.zzb += j2;
                        }
                        if (z2) {
                            zzarVar.zza += j2;
                        }
                        if (z3) {
                            zzarVar.zzc += j2;
                        }
                        if (z4) {
                            zzarVar.zzd += j2;
                        }
                        if (z5) {
                            zzarVar.zze += j2;
                        }
                        if (z6) {
                            zzarVar.zzf += j2;
                        }
                        if (z7) {
                            zzarVar.zzg += j2;
                        }
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("day", Long.valueOf(j));
                        contentValues.put("daily_public_events_count", Long.valueOf(zzarVar.zza));
                        contentValues.put("daily_events_count", Long.valueOf(zzarVar.zzb));
                        contentValues.put("daily_conversions_count", Long.valueOf(zzarVar.zzc));
                        contentValues.put("daily_error_events_count", Long.valueOf(zzarVar.zzd));
                        contentValues.put("daily_realtime_events_count", Long.valueOf(zzarVar.zze));
                        contentValues.put("daily_realtime_dcu_count", Long.valueOf(zzarVar.zzf));
                        contentValues.put("daily_registered_triggers_count", Long.valueOf(zzarVar.zzg));
                        zze2.update("apps", contentValues, "app_id=?", strArr);
                    } else {
                        this.zzu.zzaV().zze().zzb("Not updating daily counts, app is not known. appId", zzgu.zzl(str));
                    }
                } catch (SQLiteException e) {
                    e = e;
                    this.zzu.zzaV().zzb().zzc("Error updating daily counts. appId", zzgu.zzl(str), e);
                    if (cursor2 != null) {
                    }
                    return zzarVar;
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor2 = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (0 != 0) {
            }
            throw th;
        }
        if (cursor2 != null) {
            cursor2.close();
        }
        return zzarVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzaq zzy(String str) {
        SQLiteException e;
        Cursor cursor;
        Preconditions.checkNotEmpty(str);
        zzg();
        zzaw();
        Cursor cursor2 = null;
        try {
            cursor = zze().query("apps", new String[]{"remote_config", "config_last_modified_time", "e_tag"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                try {
                    if (cursor.moveToFirst()) {
                        byte[] blob = cursor.getBlob(0);
                        String string = cursor.getString(1);
                        String string2 = cursor.getString(2);
                        if (cursor.moveToNext()) {
                            this.zzu.zzaV().zzb().zzb("Got multiple records for app config, expected one. appId", zzgu.zzl(str));
                        }
                        if (blob != null) {
                            zzaq zzaqVar = new zzaq(blob, string, string2);
                            if (cursor != null) {
                                cursor.close();
                            }
                            return zzaqVar;
                        }
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    this.zzu.zzaV().zzb().zzc("Error querying remote config. appId", zzgu.zzl(str), e);
                    if (cursor != null) {
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0043, code lost:
    
        if (r3 > (com.google.android.gms.measurement.internal.zzal.zzI() + r1)) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzz(com.google.android.gms.internal.measurement.zzid zzidVar, boolean z) {
        zzg();
        zzaw();
        Preconditions.checkNotNull(zzidVar);
        Preconditions.checkNotEmpty(zzidVar.zzA());
        Preconditions.checkState(zzidVar.zzn());
        zzI();
        zzic zzicVar = this.zzu;
        long currentTimeMillis = zzicVar.zzaZ().currentTimeMillis();
        long zzo = zzidVar.zzo();
        zzicVar.zzc();
        if (zzo >= currentTimeMillis - zzal.zzI()) {
            long zzo2 = zzidVar.zzo();
            zzicVar.zzc();
        }
        zzicVar.zzaV().zze().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzgu.zzl(zzidVar.zzA()), Long.valueOf(currentTimeMillis), Long.valueOf(zzidVar.zzo()));
        try {
            byte[] zzv = this.zzg.zzp().zzv(zzidVar.zzcc());
            zzic zzicVar2 = this.zzu;
            zzicVar2.zzaV().zzk().zzb("Saving bundle, size", Integer.valueOf(zzv.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzidVar.zzA());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzidVar.zzo()));
            contentValues.put(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, zzv);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzidVar.zzaa()) {
                contentValues.put("retry_count", Integer.valueOf(zzidVar.zzab()));
            }
            try {
                if (zze().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzicVar2.zzaV().zzb().zzb("Failed to insert bundle (got -1). appId", zzgu.zzl(zzidVar.zzA()));
                return false;
            } catch (SQLiteException e) {
                this.zzu.zzaV().zzb().zzc("Error storing bundle. appId", zzgu.zzl(zzidVar.zzA()), e);
                return false;
            }
        } catch (IOException e2) {
            this.zzu.zzaV().zzb().zzc("Data loss. Failed to serialize bundle. appId", zzgu.zzl(zzidVar.zzA()), e2);
            return false;
        }
    }
}
