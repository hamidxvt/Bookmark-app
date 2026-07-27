package com.google.android.libraries.places.internal;

import java.util.Comparator;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzach implements Comparator {
    zzach() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzacp zzacpVar = (zzacp) obj;
        zzacp zzacpVar2 = (zzacp) obj2;
        zzacg zzacgVar = new zzacg(zzacpVar);
        zzacg zzacgVar2 = new zzacg(zzacpVar2);
        while (zzacgVar.hasNext() && zzacgVar2.hasNext()) {
            int compareTo = Integer.valueOf(zzacgVar.zza() & 255).compareTo(Integer.valueOf(zzacgVar2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzacpVar.zzd()).compareTo(Integer.valueOf(zzacpVar2.zzd()));
    }
}
