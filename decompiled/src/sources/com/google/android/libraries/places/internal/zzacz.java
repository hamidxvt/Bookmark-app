package com.google.android.libraries.places.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzacz {
    private static volatile zzacz zzc;
    private final Map zzd;
    private static volatile boolean zzb = false;
    static final zzacz zza = new zzacz(true);

    zzacz() {
        this.zzd = new HashMap();
    }

    public static zzacz zza() {
        zzacz zzaczVar = zzc;
        if (zzaczVar == null) {
            synchronized (zzacz.class) {
                zzaczVar = zzc;
                if (zzaczVar == null) {
                    zzaczVar = zza;
                    zzc = zzaczVar;
                }
            }
        }
        return zzaczVar;
    }

    zzacz(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
