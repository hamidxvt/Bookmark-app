package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzes {
    abstract zzes zzb(int i);

    abstract zzet zzc();

    public abstract zzes zzd(int i);

    public final zzet zze() {
        zzet zzc = zzc();
        zzha.zzi(!zzc.zzb().isEmpty(), "Package name must not be empty.");
        return zzc;
    }
}
