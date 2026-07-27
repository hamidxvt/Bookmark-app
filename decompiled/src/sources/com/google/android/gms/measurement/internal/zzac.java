package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzpu;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
final class zzac extends zzab {
    final /* synthetic */ zzad zza;
    private final com.google.android.gms.internal.measurement.zzfn zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzac(zzad zzadVar, String str, int i, com.google.android.gms.internal.measurement.zzfn zzfnVar) {
        super(str, i);
        Objects.requireNonNull(zzadVar);
        this.zza = zzadVar;
        this.zzh = zzfnVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    final int zza() {
        return this.zzh.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    final boolean zzb() {
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    final boolean zzc() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    final boolean zzd(Long l, Long l2, com.google.android.gms.internal.measurement.zziu zziuVar, boolean z) {
        zzpu.zza();
        zzic zzicVar = this.zza.zzu;
        boolean zzp = zzicVar.zzc().zzp(this.zzb, zzfy.zzaD);
        com.google.android.gms.internal.measurement.zzfn zzfnVar = this.zzh;
        boolean zze = zzfnVar.zze();
        boolean zzf = zzfnVar.zzf();
        boolean zzh = zzfnVar.zzh();
        Object[] objArr = (zze || zzf) ? true : zzh;
        Boolean bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        if (z && objArr != true) {
            zzicVar.zzaV().zzk().zzc("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzc), zzfnVar.zza() ? Integer.valueOf(zzfnVar.zzb()) : null);
            return true;
        }
        com.google.android.gms.internal.measurement.zzfh zzd = zzfnVar.zzd();
        boolean zzf2 = zzd.zzf();
        if (zziuVar.zzf()) {
            if (zzd.zzc()) {
                bool = zze(zzg(zziuVar.zzg(), zzd.zzd()), zzf2);
            } else {
                zzicVar.zzaV().zze().zzb("No number filter for long property. property", zzicVar.zzl().zzc(zziuVar.zzc()));
            }
        } else if (zziuVar.zzj()) {
            if (zzd.zzc()) {
                bool = zze(zzh(zziuVar.zzk(), zzd.zzd()), zzf2);
            } else {
                zzicVar.zzaV().zze().zzb("No number filter for double property. property", zzicVar.zzl().zzc(zziuVar.zzc()));
            }
        } else if (!zziuVar.zzd()) {
            zzicVar.zzaV().zze().zzb("User property has no value, property", zzicVar.zzl().zzc(zziuVar.zzc()));
        } else if (zzd.zza()) {
            bool = zze(zzf(zziuVar.zze(), zzd.zzb(), zzicVar.zzaV()), zzf2);
        } else if (!zzd.zzc()) {
            zzicVar.zzaV().zze().zzb("No string or number filter defined. property", zzicVar.zzl().zzc(zziuVar.zzc()));
        } else if (zzpk.zzm(zziuVar.zze())) {
            bool = zze(zzi(zziuVar.zze(), zzd.zzd()), zzf2);
        } else {
            zzicVar.zzaV().zze().zzc("Invalid user property value for Numeric number filter. property, value", zzicVar.zzl().zzc(zziuVar.zzc()), zziuVar.zze());
        }
        zzicVar.zzaV().zzk().zzb("Property filter result", bool == null ? "null" : bool);
        if (bool == null) {
            return false;
        }
        this.zzd = true;
        if (zzh && !bool.booleanValue()) {
            return true;
        }
        if (!z || zzfnVar.zze()) {
            this.zze = bool;
        }
        if (bool.booleanValue() && objArr != false && zziuVar.zza()) {
            long zzb = zziuVar.zzb();
            if (l != null) {
                zzb = l.longValue();
            }
            if (zzp && zzfnVar.zze() && !zzfnVar.zzf() && l2 != null) {
                zzb = l2.longValue();
            }
            if (zzfnVar.zzf()) {
                this.zzg = Long.valueOf(zzb);
            } else {
                this.zzf = Long.valueOf(zzb);
            }
        }
        return true;
    }
}
