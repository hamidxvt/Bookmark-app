package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzlr {
    public static final /* synthetic */ int zzb = 0;
    private static volatile zzlr zzd;
    private final Map zze;
    private static volatile boolean zzc = false;
    static final zzlr zza = new zzlr(true);

    zzlr() {
        this.zze = new HashMap();
    }

    public static zzlr zza() {
        zzlr zzlrVar = zzd;
        if (zzlrVar != null) {
            return zzlrVar;
        }
        synchronized (zzlr.class) {
            zzlr zzlrVar2 = zzd;
            if (zzlrVar2 != null) {
                return zzlrVar2;
            }
            int i = zznu.zza;
            zzlr zzb2 = zzlz.zzb(zzlr.class);
            zzd = zzb2;
            return zzb2;
        }
    }

    public final zzme zzb(zznm zznmVar, int i) {
        return (zzme) this.zze.get(new zzlq(zznmVar, i));
    }

    zzlr(boolean z) {
        this.zze = Collections.emptyMap();
    }
}
