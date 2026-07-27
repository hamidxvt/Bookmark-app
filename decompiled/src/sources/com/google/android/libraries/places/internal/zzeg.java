package com.google.android.libraries.places.internal;

import android.content.Context;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzeg implements zzej {
    private Context zza;
    private zzem zzb;
    private zzet zzc;

    private zzeg() {
    }

    /* synthetic */ zzeg(zzef zzefVar) {
    }

    @Override // com.google.android.libraries.places.internal.zzej
    public final /* synthetic */ zzej zza(zzem zzemVar) {
        this.zzb = zzemVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzej
    public final /* synthetic */ zzej zzb(zzet zzetVar) {
        this.zzc = zzetVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzej
    public final /* synthetic */ zzej zzc(Context context) {
        if (context == null) {
            throw null;
        }
        this.zza = context;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzej
    public final zzek zzd() {
        zzagk.zzb(this.zza, Context.class);
        zzagk.zzb(this.zzb, zzem.class);
        zzagk.zzb(this.zzc, zzet.class);
        return new zzei(this.zza, this.zzb, this.zzc, null);
    }
}
