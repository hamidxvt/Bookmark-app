package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzji {
    private static zzjh zza = null;

    public static synchronized void zza(zzjh zzjhVar) {
        synchronized (zzji.class) {
            if (zza != null) {
                throw new IllegalStateException("init() already called");
            }
            zza = zzjhVar;
        }
    }

    public static synchronized zzjh zzb() {
        zzjh zzjhVar;
        synchronized (zzji.class) {
            if (zza == null) {
                zza(new zzjl());
            }
            zzjhVar = zza;
        }
        return zzjhVar;
    }
}
