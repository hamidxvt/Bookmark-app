package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzaed extends zzaef {
    private zzaed() {
        super(null);
    }

    /* synthetic */ zzaed(zzaec zzaecVar) {
        super(null);
    }

    @Override // com.google.android.libraries.places.internal.zzaef
    final void zza(Object obj, long j) {
        ((zzadr) zzagd.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.libraries.places.internal.zzaef
    final void zzb(Object obj, Object obj2, long j) {
        zzadr zzadrVar = (zzadr) zzagd.zzf(obj, j);
        zzadr zzadrVar2 = (zzadr) zzagd.zzf(obj2, j);
        int size = zzadrVar.size();
        int size2 = zzadrVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzadrVar.zzc()) {
                zzadrVar = zzadrVar.zzf(size2 + size);
            }
            zzadrVar.addAll(zzadrVar2);
        }
        if (size > 0) {
            zzadrVar2 = zzadrVar;
        }
        zzagd.zzs(obj, j, zzadrVar2);
    }
}
