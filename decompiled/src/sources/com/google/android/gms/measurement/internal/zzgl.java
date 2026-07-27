package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzgl extends zzg {
    private static final String[] zza = {"app_version", "ALTER TABLE messages ADD COLUMN app_version TEXT;", "app_version_int", "ALTER TABLE messages ADD COLUMN app_version_int INTEGER;"};
    private final zzgj zzb;
    private boolean zzc;

    zzgl(zzic zzicVar) {
        super(zzicVar);
        Context zzaY = this.zzu.zzaY();
        this.zzu.zzc();
        this.zzb = new zzgj(this, zzaY, "google_app_measurement_local.db");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0172 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0172 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0172 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0182  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zzs(int i, byte[] bArr) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        String[] strArr;
        Cursor cursor2;
        zzg();
        boolean z = false;
        z = false;
        if (!this.zzc) {
            zzic zzicVar = this.zzu;
            zzal zzc = zzicVar.zzc();
            zzfx zzfxVar = zzfy.zzbb;
            String[] strArr2 = null;
            zzr zzh = zzc.zzp(null, zzfxVar) ? this.zzu.zzv().zzh(null) : null;
            ContentValues contentValues = new ContentValues();
            contentValues.put("type", Integer.valueOf(i));
            contentValues.put("entry", bArr);
            if (zzicVar.zzc().zzp(null, zzfxVar) && zzh != null) {
                contentValues.put("app_version", zzh.zzc);
                contentValues.put("app_version_int", Long.valueOf(zzh.zzj));
            }
            zzicVar.zzc();
            int i2 = 0;
            int i3 = 5;
            for (int i4 = 5; i2 < i4; i4 = 5) {
                try {
                    sQLiteDatabase = zzp();
                    if (sQLiteDatabase == null) {
                        this.zzc = true;
                    } else {
                        try {
                            sQLiteDatabase.beginTransaction();
                            cursor2 = sQLiteDatabase.rawQuery("select count(1) from messages", strArr2);
                            long j = 0;
                            if (cursor2 != null) {
                                try {
                                    if (cursor2.moveToFirst()) {
                                        j = cursor2.getLong(z ? 1 : 0);
                                    }
                                } catch (SQLiteDatabaseLockedException e) {
                                    strArr = strArr2;
                                    SystemClock.sleep(i3);
                                    i3 += 20;
                                    if (cursor2 != null) {
                                    }
                                    if (sQLiteDatabase == null) {
                                    }
                                    sQLiteDatabase.close();
                                    i2++;
                                    strArr2 = strArr;
                                    z = false;
                                } catch (SQLiteFullException e2) {
                                    e = e2;
                                    strArr = strArr2;
                                    this.zzu.zzaV().zzb().zzb("Error writing entry; local database full", e);
                                    this.zzc = true;
                                    if (cursor2 != null) {
                                    }
                                    if (sQLiteDatabase == null) {
                                    }
                                    sQLiteDatabase.close();
                                    i2++;
                                    strArr2 = strArr;
                                    z = false;
                                } catch (SQLiteException e3) {
                                    e = e3;
                                    strArr = strArr2;
                                    if (sQLiteDatabase != null) {
                                    }
                                    this.zzu.zzaV().zzb().zzb("Error writing entry to local database", e);
                                    this.zzc = true;
                                    if (cursor2 != null) {
                                    }
                                    if (sQLiteDatabase == null) {
                                    }
                                    sQLiteDatabase.close();
                                    i2++;
                                    strArr2 = strArr;
                                    z = false;
                                }
                            }
                            if (j >= 100000) {
                                try {
                                    zzicVar.zzaV().zzb().zza("Data loss, local db full");
                                    String[] strArr3 = new String[1];
                                    long j2 = (100000 - j) + 1;
                                    strArr3[z ? 1 : 0] = Long.toString(j2);
                                    long delete = sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", strArr3);
                                    if (delete != j2) {
                                        zzicVar.zzaV().zzb().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j2), Long.valueOf(delete), Long.valueOf(j2 - delete));
                                    }
                                } catch (SQLiteDatabaseLockedException e4) {
                                    strArr = null;
                                    SystemClock.sleep(i3);
                                    i3 += 20;
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    if (sQLiteDatabase == null) {
                                        i2++;
                                        strArr2 = strArr;
                                        z = false;
                                    }
                                    sQLiteDatabase.close();
                                    i2++;
                                    strArr2 = strArr;
                                    z = false;
                                } catch (SQLiteFullException e5) {
                                    e = e5;
                                    strArr = null;
                                    this.zzu.zzaV().zzb().zzb("Error writing entry; local database full", e);
                                    this.zzc = true;
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    if (sQLiteDatabase == null) {
                                        i2++;
                                        strArr2 = strArr;
                                        z = false;
                                    }
                                    sQLiteDatabase.close();
                                    i2++;
                                    strArr2 = strArr;
                                    z = false;
                                } catch (SQLiteException e6) {
                                    e = e6;
                                    strArr = null;
                                    if (sQLiteDatabase != null) {
                                        try {
                                            if (sQLiteDatabase.inTransaction()) {
                                                sQLiteDatabase.endTransaction();
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            cursor = cursor2;
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            if (sQLiteDatabase != null) {
                                                sQLiteDatabase.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    this.zzu.zzaV().zzb().zzb("Error writing entry to local database", e);
                                    this.zzc = true;
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    if (sQLiteDatabase == null) {
                                        i2++;
                                        strArr2 = strArr;
                                        z = false;
                                    }
                                    sQLiteDatabase.close();
                                    i2++;
                                    strArr2 = strArr;
                                    z = false;
                                }
                            }
                            strArr = null;
                            try {
                                sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                                sQLiteDatabase.setTransactionSuccessful();
                                sQLiteDatabase.endTransaction();
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                sQLiteDatabase.close();
                                return true;
                            } catch (SQLiteDatabaseLockedException e7) {
                                SystemClock.sleep(i3);
                                i3 += 20;
                                if (cursor2 != null) {
                                }
                                if (sQLiteDatabase == null) {
                                }
                                sQLiteDatabase.close();
                                i2++;
                                strArr2 = strArr;
                                z = false;
                            } catch (SQLiteFullException e8) {
                                e = e8;
                                this.zzu.zzaV().zzb().zzb("Error writing entry; local database full", e);
                                this.zzc = true;
                                if (cursor2 != null) {
                                }
                                if (sQLiteDatabase == null) {
                                }
                                sQLiteDatabase.close();
                                i2++;
                                strArr2 = strArr;
                                z = false;
                            } catch (SQLiteException e9) {
                                e = e9;
                                if (sQLiteDatabase != null) {
                                }
                                this.zzu.zzaV().zzb().zzb("Error writing entry to local database", e);
                                this.zzc = true;
                                if (cursor2 != null) {
                                }
                                if (sQLiteDatabase == null) {
                                }
                                sQLiteDatabase.close();
                                i2++;
                                strArr2 = strArr;
                                z = false;
                            }
                        } catch (SQLiteDatabaseLockedException e10) {
                            strArr = strArr2;
                            cursor2 = strArr;
                        } catch (SQLiteFullException e11) {
                            e = e11;
                            strArr = strArr2;
                            cursor2 = strArr;
                        } catch (SQLiteException e12) {
                            e = e12;
                            strArr = strArr2;
                            cursor2 = strArr;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = strArr2;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            throw th;
                        }
                    }
                } catch (SQLiteDatabaseLockedException e13) {
                    strArr = strArr2;
                    sQLiteDatabase = strArr;
                    cursor2 = sQLiteDatabase;
                } catch (SQLiteFullException e14) {
                    e = e14;
                    strArr = strArr2;
                    sQLiteDatabase = strArr;
                    cursor2 = sQLiteDatabase;
                } catch (SQLiteException e15) {
                    e = e15;
                    strArr = strArr2;
                    sQLiteDatabase = strArr;
                    cursor2 = sQLiteDatabase;
                } catch (Throwable th3) {
                    th = th3;
                    cursor = strArr2;
                    sQLiteDatabase = cursor;
                }
            }
            this.zzu.zzaV().zzk().zza("Failed to write entry to local database");
            return false;
        }
        return z;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    protected final boolean zze() {
        return false;
    }

    public final void zzh() {
        int delete;
        zzg();
        try {
            SQLiteDatabase zzp = zzp();
            if (zzp == null || (delete = zzp.delete("messages", null, null)) <= 0) {
                return;
            }
            this.zzu.zzaV().zzk().zzb("Reset local analytics data. records", Integer.valueOf(delete));
        } catch (SQLiteException e) {
            this.zzu.zzaV().zzb().zzb("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zzi(zzbg zzbgVar) {
        Parcel obtain = Parcel.obtain();
        zzbh.zza(zzbgVar, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzs(0, marshall);
        }
        this.zzu.zzaV().zzc().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zzj(zzpl zzplVar) {
        Parcel obtain = Parcel.obtain();
        zzpm.zza(zzplVar, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzs(1, marshall);
        }
        this.zzu.zzaV().zzc().zza("User property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzk(zzah zzahVar) {
        zzic zzicVar = this.zzu;
        byte[] zzae = zzicVar.zzk().zzae(zzahVar);
        if (zzae.length <= 131072) {
            return zzs(2, zzae);
        }
        zzicVar.zzaV().zzc().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzl(zzbe zzbeVar) {
        zzic zzicVar = this.zzu;
        byte[] zzae = zzicVar.zzk().zzae(zzbeVar);
        if (zzae == null) {
            zzicVar.zzaV().zzc().zza("Null default event parameters; not writing to database");
            return false;
        }
        if (zzae.length <= 131072) {
            return zzs(4, zzae);
        }
        zzicVar.zzaV().zzc().zza("Default event parameters too long for local database. Sending directly to service");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x02ed A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x038a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x038a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0321 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.String, java.util.List] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzm(int i) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        int i2;
        SQLiteDatabase sQLiteDatabase2;
        Cursor cursor2;
        int i3;
        SQLiteDatabase sQLiteDatabase3;
        Cursor cursor3;
        Cursor cursor4;
        SQLiteDatabase sQLiteDatabase4;
        Cursor cursor5;
        long j;
        String str;
        String[] strArr;
        int i4;
        Cursor cursor6;
        String[] strArr2;
        String str2;
        long j2;
        Parcel obtain;
        zzbe zzbeVar;
        zzah zzahVar;
        zzpl zzplVar;
        zzg();
        ?? r6 = 0;
        if (this.zzc) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!zzq()) {
            return arrayList;
        }
        int i5 = 5;
        int i6 = 0;
        int i7 = 5;
        int i8 = 0;
        while (i8 < i5) {
            int i9 = 1;
            try {
                SQLiteDatabase zzp = zzp();
                if (zzp == null) {
                    this.zzc = true;
                    return r6;
                }
                try {
                    zzp.beginTransaction();
                    try {
                        String[] strArr3 = new String[1];
                        strArr3[i6] = "rowid";
                        try {
                            String[] strArr4 = new String[1];
                            strArr4[i6] = "3";
                            try {
                                cursor5 = zzp.query("messages", strArr3, "type=?", strArr4, null, null, "rowid desc", "1");
                                try {
                                    long j3 = -1;
                                    try {
                                        if (cursor5.moveToFirst()) {
                                            j = cursor5.getLong(i6);
                                            if (cursor5 != null) {
                                                try {
                                                    cursor5.close();
                                                } catch (SQLiteDatabaseLockedException e) {
                                                    sQLiteDatabase = zzp;
                                                    i2 = 5;
                                                    sQLiteDatabase4 = sQLiteDatabase;
                                                    cursor4 = null;
                                                    i3 = i8;
                                                    try {
                                                        SystemClock.sleep(i7);
                                                        i7 += 20;
                                                        if (cursor4 != null) {
                                                        }
                                                        if (sQLiteDatabase4 == null) {
                                                        }
                                                        i8 = i3 + 1;
                                                        i5 = i2;
                                                        r6 = 0;
                                                        i6 = 0;
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        cursor = cursor4;
                                                        sQLiteDatabase = sQLiteDatabase4;
                                                        if (cursor != null) {
                                                        }
                                                        if (sQLiteDatabase != null) {
                                                        }
                                                        throw th;
                                                    }
                                                } catch (SQLiteFullException e2) {
                                                    e = e2;
                                                    sQLiteDatabase = zzp;
                                                    i2 = 5;
                                                    i3 = i8;
                                                    sQLiteDatabase3 = sQLiteDatabase;
                                                    cursor3 = null;
                                                    try {
                                                        this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                                        this.zzc = true;
                                                        if (cursor3 != null) {
                                                        }
                                                        if (sQLiteDatabase3 == null) {
                                                        }
                                                        i8 = i3 + 1;
                                                        i5 = i2;
                                                        r6 = 0;
                                                        i6 = 0;
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        cursor = cursor3;
                                                        sQLiteDatabase = sQLiteDatabase3;
                                                        if (cursor != null) {
                                                        }
                                                        if (sQLiteDatabase != null) {
                                                        }
                                                        throw th;
                                                    }
                                                } catch (SQLiteException e3) {
                                                    e = e3;
                                                    sQLiteDatabase = zzp;
                                                    i2 = 5;
                                                    sQLiteDatabase2 = sQLiteDatabase;
                                                    cursor2 = null;
                                                    if (sQLiteDatabase2 != null) {
                                                    }
                                                    this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                                    this.zzc = true;
                                                    if (cursor2 != null) {
                                                    }
                                                    if (sQLiteDatabase2 == null) {
                                                    }
                                                    i8 = i3 + 1;
                                                    i5 = i2;
                                                    r6 = 0;
                                                    i6 = 0;
                                                }
                                            }
                                        } else {
                                            if (cursor5 != null) {
                                                cursor5.close();
                                            }
                                            j = -1;
                                        }
                                        if (j != -1) {
                                            String[] strArr5 = new String[1];
                                            strArr5[i6] = String.valueOf(j);
                                            str = "rowid<?";
                                            strArr = strArr5;
                                        } else {
                                            str = r6;
                                            strArr = str;
                                        }
                                        String[] strArr6 = new String[3];
                                        strArr6[i6] = "rowid";
                                        strArr6[1] = "type";
                                        strArr6[2] = "entry";
                                        zzic zzicVar = this.zzu;
                                        zzal zzc = zzicVar.zzc();
                                        zzfx zzfxVar = zzfy.zzbb;
                                        if (zzc.zzp(r6, zzfxVar)) {
                                            i4 = 5;
                                            try {
                                                strArr6 = new String[5];
                                                strArr6[i6] = "rowid";
                                                strArr6[1] = "type";
                                                strArr6[2] = "entry";
                                                strArr6[3] = "app_version";
                                                strArr6[4] = "app_version_int";
                                            } catch (SQLiteDatabaseLockedException e4) {
                                                i2 = i4;
                                                sQLiteDatabase = zzp;
                                                sQLiteDatabase4 = sQLiteDatabase;
                                                cursor4 = null;
                                                i3 = i8;
                                                SystemClock.sleep(i7);
                                                i7 += 20;
                                                if (cursor4 != null) {
                                                }
                                                if (sQLiteDatabase4 == null) {
                                                }
                                                i8 = i3 + 1;
                                                i5 = i2;
                                                r6 = 0;
                                                i6 = 0;
                                            } catch (SQLiteFullException e5) {
                                                e = e5;
                                                i2 = i4;
                                                sQLiteDatabase = zzp;
                                                i3 = i8;
                                                sQLiteDatabase3 = sQLiteDatabase;
                                                cursor3 = null;
                                                this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                                this.zzc = true;
                                                if (cursor3 != null) {
                                                }
                                                if (sQLiteDatabase3 == null) {
                                                }
                                                i8 = i3 + 1;
                                                i5 = i2;
                                                r6 = 0;
                                                i6 = 0;
                                            } catch (SQLiteException e6) {
                                                e = e6;
                                                i2 = i4;
                                                sQLiteDatabase = zzp;
                                                sQLiteDatabase2 = sQLiteDatabase;
                                                cursor2 = null;
                                                if (sQLiteDatabase2 != null) {
                                                }
                                                this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                                this.zzc = true;
                                                if (cursor2 != null) {
                                                }
                                                if (sQLiteDatabase2 == null) {
                                                }
                                                i8 = i3 + 1;
                                                i5 = i2;
                                                r6 = 0;
                                                i6 = 0;
                                            }
                                        } else {
                                            i4 = 5;
                                        }
                                        i2 = i4;
                                        int i10 = 2;
                                        try {
                                            Cursor query = zzp.query("messages", strArr6, str, strArr, null, null, "rowid asc", Integer.toString(100));
                                            while (query.moveToNext()) {
                                                try {
                                                    j3 = query.getLong(i6);
                                                    int i11 = query.getInt(i9);
                                                    byte[] blob = query.getBlob(i10);
                                                    if (zzicVar.zzc().zzp(null, zzfxVar)) {
                                                        str2 = query.getString(3);
                                                        cursor6 = query;
                                                        j2 = query.getLong(4);
                                                    } else {
                                                        str2 = null;
                                                        cursor6 = query;
                                                        j2 = 0;
                                                    }
                                                    if (i11 == 0) {
                                                        try {
                                                            obtain = Parcel.obtain();
                                                            try {
                                                                try {
                                                                    obtain.unmarshall(blob, i6, blob.length);
                                                                    obtain.setDataPosition(i6);
                                                                    zzbg createFromParcel = zzbg.CREATOR.createFromParcel(obtain);
                                                                    if (createFromParcel != null) {
                                                                        arrayList.add(new zzgk(createFromParcel, str2, j2));
                                                                    }
                                                                } finally {
                                                                }
                                                            } catch (SafeParcelReader.ParseException e7) {
                                                                this.zzu.zzaV().zzb().zza("Failed to load event from local database");
                                                                obtain.recycle();
                                                            }
                                                        } catch (SQLiteDatabaseLockedException e8) {
                                                            sQLiteDatabase = zzp;
                                                            sQLiteDatabase4 = sQLiteDatabase;
                                                            cursor4 = cursor6;
                                                            i3 = i8;
                                                            SystemClock.sleep(i7);
                                                            i7 += 20;
                                                            if (cursor4 != null) {
                                                            }
                                                            if (sQLiteDatabase4 == null) {
                                                            }
                                                            i8 = i3 + 1;
                                                            i5 = i2;
                                                            r6 = 0;
                                                            i6 = 0;
                                                        } catch (SQLiteFullException e9) {
                                                            e = e9;
                                                            sQLiteDatabase = zzp;
                                                            i3 = i8;
                                                            sQLiteDatabase3 = sQLiteDatabase;
                                                            cursor3 = cursor6;
                                                            this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                                            this.zzc = true;
                                                            if (cursor3 != null) {
                                                            }
                                                            if (sQLiteDatabase3 == null) {
                                                            }
                                                            i8 = i3 + 1;
                                                            i5 = i2;
                                                            r6 = 0;
                                                            i6 = 0;
                                                        } catch (SQLiteException e10) {
                                                            e = e10;
                                                            sQLiteDatabase = zzp;
                                                            sQLiteDatabase2 = sQLiteDatabase;
                                                            cursor2 = cursor6;
                                                            if (sQLiteDatabase2 != null) {
                                                            }
                                                            this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                                            this.zzc = true;
                                                            if (cursor2 != null) {
                                                            }
                                                            if (sQLiteDatabase2 == null) {
                                                            }
                                                            i8 = i3 + 1;
                                                            i5 = i2;
                                                            r6 = 0;
                                                            i6 = 0;
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                            sQLiteDatabase = zzp;
                                                            cursor = cursor6;
                                                            if (cursor != null) {
                                                            }
                                                            if (sQLiteDatabase != null) {
                                                            }
                                                            throw th;
                                                        }
                                                    } else if (i11 == 1) {
                                                        obtain = Parcel.obtain();
                                                        try {
                                                            try {
                                                                obtain.unmarshall(blob, i6, blob.length);
                                                                obtain.setDataPosition(i6);
                                                                zzplVar = zzpl.CREATOR.createFromParcel(obtain);
                                                            } catch (SafeParcelReader.ParseException e11) {
                                                                this.zzu.zzaV().zzb().zza("Failed to load user property from local database");
                                                                obtain.recycle();
                                                                zzplVar = null;
                                                            }
                                                            if (zzplVar != null) {
                                                                arrayList.add(new zzgk(zzplVar, str2, j2));
                                                            }
                                                        } finally {
                                                        }
                                                    } else if (i11 == 2) {
                                                        obtain = Parcel.obtain();
                                                        try {
                                                            try {
                                                                obtain.unmarshall(blob, i6, blob.length);
                                                                obtain.setDataPosition(i6);
                                                                zzahVar = zzah.CREATOR.createFromParcel(obtain);
                                                            } finally {
                                                            }
                                                        } catch (SafeParcelReader.ParseException e12) {
                                                            this.zzu.zzaV().zzb().zza("Failed to load conditional user property from local database");
                                                            obtain.recycle();
                                                            zzahVar = null;
                                                        }
                                                        if (zzahVar != null) {
                                                            arrayList.add(new zzgk(zzahVar, str2, j2));
                                                        }
                                                    } else if (i11 == 4) {
                                                        obtain = Parcel.obtain();
                                                        try {
                                                            try {
                                                                obtain.unmarshall(blob, i6, blob.length);
                                                                obtain.setDataPosition(i6);
                                                                zzbeVar = zzbe.CREATOR.createFromParcel(obtain);
                                                            } catch (SafeParcelReader.ParseException e13) {
                                                                this.zzu.zzaV().zzb().zza("Failed to load default event parameters from local database");
                                                                obtain.recycle();
                                                                zzbeVar = null;
                                                            }
                                                            if (zzbeVar != null) {
                                                                arrayList.add(new zzgk(zzbeVar, str2, j2));
                                                            }
                                                        } finally {
                                                        }
                                                    } else if (i11 == 3) {
                                                        this.zzu.zzaV().zzk().zza("Skipping app launch break");
                                                    } else {
                                                        this.zzu.zzaV().zzb().zza("Unknown record type in local database");
                                                    }
                                                    query = cursor6;
                                                    i10 = 2;
                                                    i9 = 1;
                                                } catch (SQLiteDatabaseLockedException e14) {
                                                    cursor6 = query;
                                                } catch (SQLiteFullException e15) {
                                                    e = e15;
                                                    cursor6 = query;
                                                } catch (SQLiteException e16) {
                                                    e = e16;
                                                    cursor6 = query;
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    cursor6 = query;
                                                }
                                            }
                                            cursor6 = query;
                                            strArr2 = new String[1];
                                            strArr2[i6] = Long.toString(j3);
                                            sQLiteDatabase = zzp;
                                        } catch (SQLiteDatabaseLockedException e17) {
                                            sQLiteDatabase = zzp;
                                            sQLiteDatabase4 = sQLiteDatabase;
                                            cursor4 = null;
                                            i3 = i8;
                                            SystemClock.sleep(i7);
                                            i7 += 20;
                                            if (cursor4 != null) {
                                                cursor4.close();
                                            }
                                            if (sQLiteDatabase4 == null) {
                                                sQLiteDatabase4.close();
                                            }
                                            i8 = i3 + 1;
                                            i5 = i2;
                                            r6 = 0;
                                            i6 = 0;
                                        } catch (SQLiteFullException e18) {
                                            e = e18;
                                            sQLiteDatabase = zzp;
                                            i3 = i8;
                                            sQLiteDatabase3 = sQLiteDatabase;
                                            cursor3 = null;
                                            this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                            this.zzc = true;
                                            if (cursor3 != null) {
                                                cursor3.close();
                                            }
                                            if (sQLiteDatabase3 == null) {
                                                sQLiteDatabase3.close();
                                            }
                                            i8 = i3 + 1;
                                            i5 = i2;
                                            r6 = 0;
                                            i6 = 0;
                                        } catch (SQLiteException e19) {
                                            e = e19;
                                            sQLiteDatabase = zzp;
                                            sQLiteDatabase2 = sQLiteDatabase;
                                            cursor2 = null;
                                            if (sQLiteDatabase2 != null) {
                                                try {
                                                    if (sQLiteDatabase2.inTransaction()) {
                                                        sQLiteDatabase2.endTransaction();
                                                    }
                                                } catch (Throwable th5) {
                                                    th = th5;
                                                    Cursor cursor7 = cursor2;
                                                    sQLiteDatabase = sQLiteDatabase2;
                                                    cursor = cursor7;
                                                    if (cursor != null) {
                                                        cursor.close();
                                                    }
                                                    if (sQLiteDatabase != null) {
                                                        sQLiteDatabase.close();
                                                    }
                                                    throw th;
                                                }
                                            }
                                            this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                            this.zzc = true;
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                            if (sQLiteDatabase2 == null) {
                                                sQLiteDatabase2.close();
                                                i3 = i8;
                                            } else {
                                                i3 = i8;
                                            }
                                            i8 = i3 + 1;
                                            i5 = i2;
                                            r6 = 0;
                                            i6 = 0;
                                        }
                                    } catch (Throwable th6) {
                                        th = th6;
                                        sQLiteDatabase = zzp;
                                        cursor = null;
                                        if (cursor != null) {
                                        }
                                        if (sQLiteDatabase != null) {
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th7) {
                                    th = th7;
                                    sQLiteDatabase = zzp;
                                    i2 = 5;
                                    if (cursor5 != null) {
                                        try {
                                            cursor5.close();
                                        } catch (SQLiteDatabaseLockedException e20) {
                                            sQLiteDatabase4 = sQLiteDatabase;
                                            cursor4 = null;
                                            i3 = i8;
                                            SystemClock.sleep(i7);
                                            i7 += 20;
                                            if (cursor4 != null) {
                                            }
                                            if (sQLiteDatabase4 == null) {
                                            }
                                            i8 = i3 + 1;
                                            i5 = i2;
                                            r6 = 0;
                                            i6 = 0;
                                        } catch (SQLiteFullException e21) {
                                            e = e21;
                                            i3 = i8;
                                            sQLiteDatabase3 = sQLiteDatabase;
                                            cursor3 = null;
                                            this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                            this.zzc = true;
                                            if (cursor3 != null) {
                                            }
                                            if (sQLiteDatabase3 == null) {
                                            }
                                            i8 = i3 + 1;
                                            i5 = i2;
                                            r6 = 0;
                                            i6 = 0;
                                        } catch (SQLiteException e22) {
                                            e = e22;
                                            sQLiteDatabase2 = sQLiteDatabase;
                                            cursor2 = null;
                                            if (sQLiteDatabase2 != null) {
                                            }
                                            this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                                            this.zzc = true;
                                            if (cursor2 != null) {
                                            }
                                            if (sQLiteDatabase2 == null) {
                                            }
                                            i8 = i3 + 1;
                                            i5 = i2;
                                            r6 = 0;
                                            i6 = 0;
                                        } catch (Throwable th8) {
                                            th = th8;
                                            cursor = null;
                                            if (cursor != null) {
                                            }
                                            if (sQLiteDatabase != null) {
                                            }
                                            throw th;
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th9) {
                                th = th9;
                                sQLiteDatabase = zzp;
                                i2 = 5;
                                cursor5 = null;
                                if (cursor5 != null) {
                                }
                                throw th;
                            }
                        } catch (Throwable th10) {
                            th = th10;
                            sQLiteDatabase = zzp;
                        }
                    } catch (Throwable th11) {
                        th = th11;
                        i2 = i5;
                        sQLiteDatabase = zzp;
                    }
                } catch (SQLiteDatabaseLockedException e23) {
                    i2 = i5;
                    sQLiteDatabase = zzp;
                } catch (SQLiteFullException e24) {
                    e = e24;
                    i2 = i5;
                    sQLiteDatabase = zzp;
                } catch (SQLiteException e25) {
                    e = e25;
                    i2 = i5;
                    sQLiteDatabase = zzp;
                } catch (Throwable th12) {
                    th = th12;
                    sQLiteDatabase = zzp;
                }
                try {
                    if (sQLiteDatabase.delete("messages", "rowid <= ?", strArr2) < arrayList.size()) {
                        this.zzu.zzaV().zzb().zza("Fewer entries removed from local database than expected");
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    if (cursor6 != null) {
                        cursor6.close();
                    }
                    sQLiteDatabase.close();
                    return arrayList;
                } catch (SQLiteDatabaseLockedException e26) {
                    sQLiteDatabase4 = sQLiteDatabase;
                    cursor4 = cursor6;
                    i3 = i8;
                    SystemClock.sleep(i7);
                    i7 += 20;
                    if (cursor4 != null) {
                    }
                    if (sQLiteDatabase4 == null) {
                    }
                    i8 = i3 + 1;
                    i5 = i2;
                    r6 = 0;
                    i6 = 0;
                } catch (SQLiteFullException e27) {
                    e = e27;
                    i3 = i8;
                    sQLiteDatabase3 = sQLiteDatabase;
                    cursor3 = cursor6;
                    this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                    this.zzc = true;
                    if (cursor3 != null) {
                    }
                    if (sQLiteDatabase3 == null) {
                    }
                    i8 = i3 + 1;
                    i5 = i2;
                    r6 = 0;
                    i6 = 0;
                } catch (SQLiteException e28) {
                    e = e28;
                    sQLiteDatabase2 = sQLiteDatabase;
                    cursor2 = cursor6;
                    if (sQLiteDatabase2 != null) {
                    }
                    this.zzu.zzaV().zzb().zzb("Error reading entries from local database", e);
                    this.zzc = true;
                    if (cursor2 != null) {
                    }
                    if (sQLiteDatabase2 == null) {
                    }
                    i8 = i3 + 1;
                    i5 = i2;
                    r6 = 0;
                    i6 = 0;
                } catch (Throwable th13) {
                    th = th13;
                    cursor = cursor6;
                    if (cursor != null) {
                    }
                    if (sQLiteDatabase != null) {
                    }
                    throw th;
                }
            } catch (SQLiteDatabaseLockedException e29) {
                i2 = i5;
                cursor4 = null;
                sQLiteDatabase4 = null;
            } catch (SQLiteFullException e30) {
                e = e30;
                i2 = i5;
                i3 = i8;
                sQLiteDatabase3 = null;
                cursor3 = null;
            } catch (SQLiteException e31) {
                e = e31;
                i2 = i5;
                sQLiteDatabase2 = null;
                cursor2 = null;
            } catch (Throwable th14) {
                th = th14;
                cursor = null;
                sQLiteDatabase = null;
            }
        }
        this.zzu.zzaV().zze().zza("Failed to read events from database in reasonable time");
        return null;
    }

    public final boolean zzn() {
        return zzs(3, new byte[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzo() {
        int i;
        zzg();
        if (!this.zzc && zzq()) {
            int i2 = 5;
            for (0; i < 5; i + 1) {
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    sQLiteDatabase = zzp();
                    try {
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.beginTransaction();
                            sQLiteDatabase.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                            sQLiteDatabase.setTransactionSuccessful();
                            sQLiteDatabase.endTransaction();
                            sQLiteDatabase.close();
                            return true;
                        }
                        this.zzc = true;
                    } catch (SQLiteDatabaseLockedException e) {
                        SystemClock.sleep(i2);
                        i2 += 20;
                        i = sQLiteDatabase == null ? i + 1 : 0;
                        sQLiteDatabase.close();
                    } catch (SQLiteFullException e2) {
                        e = e2;
                        this.zzu.zzaV().zzb().zzb("Error deleting app launch break from local database", e);
                        this.zzc = true;
                        if (sQLiteDatabase == null) {
                        }
                        sQLiteDatabase.close();
                    } catch (SQLiteException e3) {
                        e = e3;
                        if (sQLiteDatabase != null) {
                            try {
                                if (sQLiteDatabase.inTransaction()) {
                                    sQLiteDatabase.endTransaction();
                                }
                            } catch (Throwable th) {
                                th = th;
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                throw th;
                            }
                        }
                        this.zzu.zzaV().zzb().zzb("Error deleting app launch break from local database", e);
                        this.zzc = true;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                    }
                } catch (SQLiteDatabaseLockedException e4) {
                } catch (SQLiteFullException e5) {
                    e = e5;
                } catch (SQLiteException e6) {
                    e = e6;
                } catch (Throwable th2) {
                    th = th2;
                    if (sQLiteDatabase != null) {
                    }
                    throw th;
                }
            }
            this.zzu.zzaV().zze().zza("Error deleting app launch break from local database in reasonable time");
            return false;
        }
        return false;
    }

    final SQLiteDatabase zzp() throws SQLiteException {
        if (this.zzc) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzb.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzc = true;
        return null;
    }

    final boolean zzq() {
        zzic zzicVar = this.zzu;
        Context zzaY = zzicVar.zzaY();
        zzicVar.zzc();
        return zzaY.getDatabasePath("google_app_measurement_local.db").exists();
    }
}
