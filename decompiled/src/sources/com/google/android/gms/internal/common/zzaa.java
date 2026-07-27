package com.google.android.gms.internal.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jspecify.annotations.NullMarked;

/* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
@NullMarked
/* loaded from: classes16.dex */
public final class zzaa {
    private final zzr zza;
    private final boolean zzb;
    private final zzx zzc;

    private zzaa(zzx zzxVar, boolean z, zzr zzrVar, int i) {
        this.zzc = zzxVar;
        this.zzb = z;
        this.zza = zzrVar;
    }

    public static zzaa zzc(zzr zzrVar) {
        return new zzaa(new zzx(zzrVar), false, zzq.zza, Integer.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Iterator zzh(CharSequence charSequence) {
        zzx zzxVar = this.zzc;
        return new zzw(zzxVar, this, charSequence, zzxVar.zza);
    }

    public final zzaa zzb() {
        return new zzaa(this.zzc, true, this.zza, Integer.MAX_VALUE);
    }

    public final Iterable zzd(CharSequence charSequence) {
        return new zzy(this, charSequence);
    }

    public final List zzf(CharSequence charSequence) {
        if (charSequence == null) {
            throw null;
        }
        Iterator zzh = zzh(charSequence);
        ArrayList arrayList = new ArrayList();
        while (zzh.hasNext()) {
            arrayList.add((String) zzh.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
