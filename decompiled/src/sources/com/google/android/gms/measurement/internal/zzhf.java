package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzhf {
    final String zza;
    final /* synthetic */ zzhh zzb;
    private final String zzc;
    private final String zzd;
    private final long zze;

    /* synthetic */ zzhf(zzhh zzhhVar, String str, long j, byte[] bArr) {
        Objects.requireNonNull(zzhhVar);
        this.zzb = zzhhVar;
        Preconditions.checkNotEmpty("health_monitor");
        Preconditions.checkArgument(j > 0);
        this.zza = "health_monitor:start";
        this.zzc = "health_monitor:count";
        this.zzd = "health_monitor:value";
        this.zze = j;
    }

    private final void zzc() {
        zzhh zzhhVar = this.zzb;
        zzhhVar.zzg();
        long currentTimeMillis = zzhhVar.zzu.zzaZ().currentTimeMillis();
        SharedPreferences.Editor edit = zzhhVar.zzd().edit();
        edit.remove(this.zzc);
        edit.remove(this.zzd);
        edit.putLong(this.zza, currentTimeMillis);
        edit.apply();
    }

    private final long zzd() {
        return this.zzb.zzd().getLong(this.zza, 0L);
    }

    public final void zza(String str, long j) {
        zzhh zzhhVar = this.zzb;
        zzhhVar.zzg();
        if (zzd() == 0) {
            zzc();
        }
        if (str == null) {
            str = "";
        }
        SharedPreferences zzd = zzhhVar.zzd();
        String str2 = this.zzc;
        long j2 = zzd.getLong(str2, 0L);
        if (j2 <= 0) {
            SharedPreferences.Editor edit = zzhhVar.zzd().edit();
            edit.putString(this.zzd, str);
            edit.putLong(str2, 1L);
            edit.apply();
            return;
        }
        long nextLong = zzhhVar.zzu.zzk().zzf().nextLong() & Long.MAX_VALUE;
        long j3 = j2 + 1;
        long j4 = Long.MAX_VALUE / j3;
        SharedPreferences.Editor edit2 = zzhhVar.zzd().edit();
        if (nextLong < j4) {
            edit2.putString(this.zzd, str);
        }
        edit2.putLong(str2, j3);
        edit2.apply();
    }

    public final Pair zzb() {
        long abs;
        zzhh zzhhVar = this.zzb;
        zzhhVar.zzg();
        zzhhVar.zzg();
        long zzd = zzd();
        if (zzd == 0) {
            zzc();
            abs = 0;
        } else {
            abs = Math.abs(zzd - zzhhVar.zzu.zzaZ().currentTimeMillis());
        }
        long j = this.zze;
        if (abs < j) {
            return null;
        }
        if (abs > j + j) {
            zzc();
            return null;
        }
        String string = zzhhVar.zzd().getString(this.zzd, null);
        long j2 = zzhhVar.zzd().getLong(this.zzc, 0L);
        zzc();
        return (string == null || j2 <= 0) ? zzhh.zza : new Pair(string, Long.valueOf(j2));
    }
}
