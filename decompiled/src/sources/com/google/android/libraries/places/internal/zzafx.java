package com.google.android.libraries.places.internal;

import java.util.Iterator;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzafx implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzafy zzb;

    zzafx(zzafy zzafyVar) {
        zzadz zzadzVar;
        this.zzb = zzafyVar;
        zzadzVar = this.zzb.zza;
        this.zza = zzadzVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
