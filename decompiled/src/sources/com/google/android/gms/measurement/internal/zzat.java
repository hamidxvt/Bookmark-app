package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzat {
    final /* synthetic */ zzav zza;
    private final String zzb;
    private long zzc;

    public zzat(zzav zzavVar, String str) {
        Objects.requireNonNull(zzavVar);
        this.zza = zzavVar;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        this.zzc = -1L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0100  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zza() {
        Cursor cursor;
        Cursor cursor2;
        List arrayList = new ArrayList();
        int i = 0;
        try {
            cursor2 = this.zza.zze().query("raw_events", new String[]{"rowid", AppMeasurementSdk.ConditionalUserProperty.NAME, "timestamp", "metadata_fingerprint", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "realtime"}, "app_id = ? and rowid > ?", new String[]{this.zzb, String.valueOf(this.zzc)}, null, null, "rowid", "1000");
            try {
                if (cursor2.moveToFirst()) {
                    while (true) {
                        long j = cursor2.getLong(i);
                        long j2 = cursor2.getLong(3);
                        boolean z = cursor2.getLong(5) == 1 ? 1 : i;
                        byte[] blob = cursor2.getBlob(4);
                        if (j > this.zzc) {
                            this.zzc = j;
                        }
                        try {
                            com.google.android.gms.internal.measurement.zzhr zzhrVar = (com.google.android.gms.internal.measurement.zzhr) zzpk.zzw(com.google.android.gms.internal.measurement.zzhs.zzk(), blob);
                            String string = cursor2.getString(1);
                            if (string == null) {
                                string = "";
                            }
                            zzhrVar.zzl(string);
                            zzhrVar.zzo(cursor2.getLong(2));
                            arrayList.add(new zzas(j, j2, z, (com.google.android.gms.internal.measurement.zzhs) zzhrVar.zzbc()));
                        } catch (IOException e) {
                            this.zza.zzu.zzaV().zzb().zzc("Data loss. Failed to merge raw event. appId", zzgu.zzl(this.zzb), e);
                        }
                        if (!cursor2.moveToNext()) {
                            break;
                        }
                        i = 0;
                    }
                } else {
                    arrayList = Collections.emptyList();
                }
            } catch (SQLiteException e2) {
                e = e2;
                cursor = cursor2;
                try {
                    this.zza.zzu.zzaV().zzb().zzc("Data loss. Error querying raw events batch. appId", zzgu.zzl(this.zzb), e);
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
            } catch (Throwable th2) {
                th = th2;
                cursor = cursor2;
                if (cursor != null) {
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
        if (cursor2 != null) {
            cursor2.close();
        }
        return arrayList;
    }

    public zzat(zzav zzavVar, String str, long j) {
        Objects.requireNonNull(zzavVar);
        this.zza = zzavVar;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        this.zzc = zzavVar.zzah("select rowid from raw_events where app_id = ? and timestamp < ? order by rowid desc limit 1", new String[]{str, String.valueOf(j)}, -1L);
    }
}
