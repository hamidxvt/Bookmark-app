package com.google.android.libraries.places.internal;

import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzib {
    public static List zza(List list, zzaz zzazVar) {
        return list instanceof RandomAccess ? new zzhy(list, zzazVar, null) : new zzia(list, zzazVar, null);
    }
}
