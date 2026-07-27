package com.google.android.gms.internal.location;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
final class zzbw extends zzbx {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbx zzc;

    zzbw(zzbx zzbxVar, int i, int i2) {
        this.zzc = zzbxVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzbr.zza(i, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.location.zzbx, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final boolean zzf() {
        return true;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    @CheckForNull
    final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.location.zzbx
    /* renamed from: zzh */
    public final zzbx subList(int i, int i2) {
        zzbr.zzc(i, i2, this.zzb);
        zzbx zzbxVar = this.zzc;
        int i3 = this.zza;
        return zzbxVar.subList(i + i3, i2 + i3);
    }
}
