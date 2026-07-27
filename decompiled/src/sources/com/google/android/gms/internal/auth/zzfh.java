package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
final class zzfh extends zzfl {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzfh() {
        super(null);
    }

    /* synthetic */ zzfh(zzfg zzfgVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.auth.zzfl
    final void zza(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzhj.zzf(obj, j);
        if (list instanceof zzff) {
            unmodifiableList = ((zzff) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzge) && (list instanceof zzez)) {
                zzez zzezVar = (zzez) list;
                if (zzezVar.zzc()) {
                    zzezVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzhj.zzp(obj, j, unmodifiableList);
    }

    @Override // com.google.android.gms.internal.auth.zzfl
    final void zzb(Object obj, Object obj2, long j) {
        List list = (List) zzhj.zzf(obj2, j);
        int size = list.size();
        List list2 = (List) zzhj.zzf(obj, j);
        if (list2.isEmpty()) {
            list2 = list2 instanceof zzff ? new zzfe(size) : ((list2 instanceof zzge) && (list2 instanceof zzez)) ? ((zzez) list2).zzd(size) : new ArrayList(size);
            zzhj.zzp(obj, j, list2);
        } else if (zza.isAssignableFrom(list2.getClass())) {
            ArrayList arrayList = new ArrayList(list2.size() + size);
            arrayList.addAll(list2);
            zzhj.zzp(obj, j, arrayList);
            list2 = arrayList;
        } else if (list2 instanceof zzhe) {
            zzfe zzfeVar = new zzfe(list2.size() + size);
            zzfeVar.addAll(zzfeVar.size(), (zzhe) list2);
            zzhj.zzp(obj, j, zzfeVar);
            list2 = zzfeVar;
        } else if ((list2 instanceof zzge) && (list2 instanceof zzez)) {
            zzez zzezVar = (zzez) list2;
            if (!zzezVar.zzc()) {
                list2 = zzezVar.zzd(list2.size() + size);
                zzhj.zzp(obj, j, list2);
            }
        }
        int size2 = list2.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list);
        }
        if (size2 > 0) {
            list = list2;
        }
        zzhj.zzp(obj, j, list);
    }
}
