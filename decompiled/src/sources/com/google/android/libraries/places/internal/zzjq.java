package com.google.android.libraries.places.internal;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzjq {
    private static String zza = "com.google.android.libraries.places.internal.zzjv";
    private static String zzb = "com.google.common.flogger.backend.google.GooglePlatform";
    private static String zzc = "com.google.common.flogger.backend.system.DefaultPlatform";
    private static final String[] zzd = {zza, zzb, zzc};

    public static int zza() {
        return zzkv.zza();
    }

    public static long zzb() {
        zzjq zzjqVar;
        zzjqVar = zzjo.zza;
        return zzjqVar.zzc();
    }

    public static zzja zzd(String className) {
        zzjq zzjqVar;
        zzjqVar = zzjo.zza;
        return zzjqVar.zze(className);
    }

    public static zzjc zzf() {
        return zzi().zza();
    }

    public static zzjp zzg() {
        zzjq zzjqVar;
        zzjqVar = zzjo.zza;
        return zzjqVar.zzh();
    }

    public static zzke zzi() {
        zzjq zzjqVar;
        zzjqVar = zzjo.zza;
        return zzjqVar.zzj();
    }

    public static zzkr zzk() {
        return zzi().zzc();
    }

    public static String zzl() {
        zzjq zzjqVar;
        zzjqVar = zzjo.zza;
        return zzjqVar.zzm();
    }

    public static boolean zzn(String loggerName, Level level, boolean isEnabled) {
        zzi().zzd(loggerName, level, isEnabled);
        return false;
    }

    protected long zzc() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }

    protected abstract zzja zze(String str);

    protected abstract zzjp zzh();

    protected zzke zzj() {
        return zzke.zze();
    }

    protected abstract String zzm();
}
