package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzkn implements Iterator {
    final /* synthetic */ zzko zza;
    private int zzb = 0;

    zzkn(zzko zzkoVar) {
        this.zza = zzkoVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.zzb;
        zzko zzkoVar = this.zza;
        return i < zzkoVar.zza() - zzkoVar.zzb();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object[] objArr;
        int i = this.zzb;
        zzko zzkoVar = this.zza;
        if (i >= zzkoVar.zza() - zzkoVar.zzb()) {
            throw new NoSuchElementException();
        }
        zzko zzkoVar2 = this.zza;
        objArr = zzkoVar2.zzb.zzb;
        Object obj = objArr[zzkoVar2.zzb() + i];
        this.zzb = i + 1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
