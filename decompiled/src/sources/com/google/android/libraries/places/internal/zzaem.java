package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzaem {
    zzaem() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzael zzaelVar = (zzael) obj;
        if (zzaelVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzaelVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }
}
