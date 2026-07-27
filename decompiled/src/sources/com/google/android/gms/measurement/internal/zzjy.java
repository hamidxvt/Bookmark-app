package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzjy implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzlj zzb;

    zzjy(zzlj zzljVar, boolean z) {
        this.zza = z;
        Objects.requireNonNull(zzljVar);
        this.zzb = zzljVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlj zzljVar = this.zzb;
        zzic zzicVar = zzljVar.zzu;
        boolean zzB = zzicVar.zzB();
        boolean zzA = zzicVar.zzA();
        boolean z = this.zza;
        zzicVar.zzz(z);
        if (zzA == z) {
            zzicVar.zzaV().zzk().zzb("Default data collection state already set to", Boolean.valueOf(z));
        }
        if (zzicVar.zzB() == zzB || zzicVar.zzB() != zzicVar.zzA()) {
            zzicVar.zzaV().zzh().zzc("Default data collection is different than actual status", Boolean.valueOf(z), Boolean.valueOf(zzB));
        }
        zzljVar.zzal();
    }
}
