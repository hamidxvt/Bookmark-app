package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzhq extends zzhg {
    private final zzhs zza;

    zzhq(zzhs zzhsVar, int i) {
        super(zzhsVar.size(), i);
        this.zza = zzhsVar;
    }

    @Override // com.google.android.libraries.places.internal.zzhg
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
