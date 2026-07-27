package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzhr extends zzhs {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzhs zzc;

    zzhr(zzhs zzhsVar, int i, int i2) {
        this.zzc = zzhsVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzha.zza(i, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzhs, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    final boolean zzf() {
        return true;
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    @CheckForNull
    final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.libraries.places.internal.zzhs
    /* renamed from: zzh */
    public final zzhs subList(int i, int i2) {
        zzha.zzg(i, i2, this.zzb);
        zzhs zzhsVar = this.zzc;
        int i3 = this.zza;
        return zzhsVar.subList(i + i3, i2 + i3);
    }
}
