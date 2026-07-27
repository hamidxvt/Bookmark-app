package com.google.android.libraries.places.internal;

import java.io.IOException;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzaev implements zzafc {
    private final zzaer zza;
    private final zzaft zzb;
    private final boolean zzc;
    private final zzada zzd;

    private zzaev(zzaft zzaftVar, zzada zzadaVar, zzaer zzaerVar) {
        this.zzb = zzaftVar;
        this.zzc = zzadaVar.zzc(zzaerVar);
        this.zzd = zzadaVar;
        this.zza = zzaerVar;
    }

    static zzaev zzg(zzaft zzaftVar, zzada zzadaVar, zzaer zzaerVar) {
        return new zzaev(zzaftVar, zzadaVar, zzaerVar);
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final int zza(Object obj) {
        zzaft zzaftVar = this.zzb;
        int zzb = zzaftVar.zzb(zzaftVar.zzc(obj));
        if (!this.zzc) {
            return zzb;
        }
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzc(obj).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final void zzc(Object obj) {
        this.zzb.zze(obj);
        this.zzd.zzb(obj);
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final void zzd(Object obj, Object obj2) {
        zzafe.zzD(this.zzb, obj, obj2);
        if (this.zzc) {
            zzafe.zzC(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final boolean zze(Object obj, Object obj2) {
        if (!this.zzb.zzc(obj).equals(this.zzb.zzc(obj2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(obj);
        this.zzd.zza(obj2);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final boolean zzf(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final void zzi(Object obj, zzacy zzacyVar) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }
}
