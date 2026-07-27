package com.google.android.libraries.places.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzkd extends zzjs {
    private static final Set zza = Collections.unmodifiableSet(new HashSet(Arrays.asList(zzit.zza, zziz.zza)));
    private static final zzjk zzb = zzjn.zza(zza).zzd();
    private final String zzc;
    private final Level zzd;

    /* synthetic */ zzkd(String str, String str2, boolean z, boolean z2, Level level, zzkc zzkcVar) {
        super(str2);
        if (str2.length() > 23) {
            int i = -1;
            for (int length = str2.length() - 1; length >= 0; length--) {
                char charAt = str2.charAt(length);
                if (charAt == '.' || charAt == '$') {
                    i = length;
                    break;
                }
            }
            str2 = str2.substring(i + 1);
        }
        String valueOf = String.valueOf(str2);
        String concat = valueOf.length() != 0 ? "".concat(valueOf) : new String("");
        this.zzc = concat.substring(0, Math.min(concat.length(), 23));
        this.zzd = level;
    }
}
