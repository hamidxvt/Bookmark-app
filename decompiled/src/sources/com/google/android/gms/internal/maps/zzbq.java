package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
final class zzbq extends zzbk {
    static final zzbk zza = new zzbq(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzbq(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzbc.zza(i, this.zzc, FirebaseAnalytics.Param.INDEX);
        return Objects.requireNonNull(this.zzb[i]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.maps.zzbk, com.google.android.gms.internal.maps.zzbh
    final int zza(Object[] objArr, int i) {
        Object[] objArr2 = this.zzb;
        int i2 = this.zzc;
        System.arraycopy(objArr2, 0, objArr, 0, i2);
        return i2;
    }

    @Override // com.google.android.gms.internal.maps.zzbh
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.maps.zzbh
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.maps.zzbh
    final Object[] zze() {
        return this.zzb;
    }
}
