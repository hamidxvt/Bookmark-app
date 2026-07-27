package com.google.android.libraries.places.internal;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzjg {
    private static final zzjj zza = new zzje();
    private static final zzji zzb = new zzjf();
    private final zzjj zze;
    private final Map zzc = new HashMap();
    private final Map zzd = new HashMap();
    private zzji zzf = null;

    public final zzjg zza(zzji zzjiVar) {
        this.zzf = zzjiVar;
        return this;
    }

    public final zzjk zzd() {
        return new zzjh(this, null);
    }

    final void zzg(zzix zzixVar) {
        zzkt.zza(zzixVar, "key");
        if (!zzixVar.zzb()) {
            zzjj zzjjVar = zza;
            zzkt.zza(zzixVar, "key");
            this.zzd.remove(zzixVar);
            this.zzc.put(zzixVar, zzjjVar);
            return;
        }
        zzji zzjiVar = zzb;
        zzkt.zza(zzixVar, "key");
        if (!zzixVar.zzb()) {
            throw new IllegalArgumentException("key must be repeating");
        }
        this.zzc.remove(zzixVar);
        this.zzd.put(zzixVar, zzjiVar);
    }
}
