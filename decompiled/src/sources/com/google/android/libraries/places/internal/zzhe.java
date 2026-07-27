package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzhe {
    private final zzgq zza;
    private final zzhc zzb;

    private zzhe(zzhc zzhcVar, byte[] bArr) {
        zzgp zzgpVar = zzgp.zza;
        this.zzb = zzhcVar;
        this.zza = zzgpVar;
    }

    public static zzhe zzb(char c) {
        return new zzhe(new zzhc(new zzgn(ClassUtils.PACKAGE_SEPARATOR_CHAR)), null);
    }

    public final List zzc(CharSequence charSequence) {
        zzhb zzhbVar = new zzhb(this.zzb, this, "2.6.0");
        ArrayList arrayList = new ArrayList();
        while (zzhbVar.hasNext()) {
            arrayList.add((String) zzhbVar.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
