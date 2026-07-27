package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
final class zznq implements zznx {
    private final zznm zza;
    private final zzoi zzb;
    private final boolean zzc;
    private final zzls zzd;

    private zznq(zzoi zzoiVar, zzls zzlsVar, zznm zznmVar) {
        this.zzb = zzoiVar;
        this.zzc = zznmVar instanceof zzmc;
        this.zzd = zzlsVar;
        this.zza = zznmVar;
    }

    static zznq zzg(zzoi zzoiVar, zzls zzlsVar, zznm zznmVar) {
        return new zznq(zzoiVar, zzlsVar, zznmVar);
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final Object zza() {
        zznm zznmVar = this.zza;
        return zznmVar instanceof zzmf ? ((zzmf) zznmVar).zzch() : zznmVar.zzcC().zzbf();
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final boolean zzb(Object obj, Object obj2) {
        if (!((zzmf) obj).zzc.equals(((zzmf) obj2).zzc)) {
            return false;
        }
        if (this.zzc) {
            return ((zzmc) obj).zzb.equals(((zzmc) obj2).zzb);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final int zzc(Object obj) {
        int hashCode = ((zzmf) obj).zzc.hashCode();
        return this.zzc ? (hashCode * 53) + ((zzmc) obj).zzb.zza.hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final void zzd(Object obj, Object obj2) {
        zznz.zzD(this.zzb, obj, obj2);
        if (this.zzc) {
            zznz.zzC(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final int zze(Object obj) {
        int zzh = ((zzmf) obj).zzc.zzh();
        return this.zzc ? zzh + ((zzmc) obj).zzb.zzg() : zzh;
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final void zzf(Object obj, zzov zzovVar) throws IOException {
        Iterator zzc = ((zzmc) obj).zzb.zzc();
        while (zzc.hasNext()) {
            Map.Entry entry = (Map.Entry) zzc.next();
            zzlv zzlvVar = (zzlv) entry.getKey();
            if (zzlvVar.zzc() != zzou.MESSAGE || zzlvVar.zzd() || zzlvVar.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzmt) {
                zzovVar.zzv(zzlvVar.zza(), ((zzmt) entry).zza().zzc());
            } else {
                zzovVar.zzv(zzlvVar.zza(), entry.getValue());
            }
        }
        ((zzmf) obj).zzc.zzf(zzovVar);
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzkw zzkwVar) throws IOException {
        zzmf zzmfVar = (zzmf) obj;
        if (zzmfVar.zzc == zzoj.zza()) {
            zzmfVar.zzc = zzoj.zzb();
        }
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final void zzj(Object obj) {
        this.zzb.zzb(obj);
        this.zzd.zza(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final boolean zzk(Object obj) {
        return ((zzmc) obj).zzb.zze();
    }
}
