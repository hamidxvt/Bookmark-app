package com.google.android.libraries.places.internal;

import java.util.Comparator;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzkj implements Comparator {
    zzkj() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        zzkq zza = zzkq.zza(obj);
        zzkq zza2 = zzkq.zza(obj2);
        if (zza != zza2) {
            return zza.compareTo(zza2);
        }
        switch (zza) {
            case BOOLEAN:
                return ((Boolean) obj).compareTo((Boolean) obj2);
            case STRING:
                return ((String) obj).compareTo((String) obj2);
            case LONG:
                return ((Long) obj).compareTo((Long) obj2);
            case DOUBLE:
                return ((Double) obj).compareTo((Double) obj2);
            default:
                throw null;
        }
    }
}
