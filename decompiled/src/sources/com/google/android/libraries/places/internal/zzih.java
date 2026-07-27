package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzih extends zzhs {
    final /* synthetic */ zzii zza;

    zzih(zzii zziiVar) {
        this.zza = zziiVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzc;
        zzha.zza(i, i2, FirebaseAnalytics.Param.INDEX);
        zzii zziiVar = this.zza;
        int i3 = i + i;
        objArr = zziiVar.zzb;
        Object obj = objArr[i3];
        obj.getClass();
        objArr2 = zziiVar.zzb;
        Object obj2 = objArr2[i3 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zza.zzc;
        return i;
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    public final boolean zzf() {
        return true;
    }
}
