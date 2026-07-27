package com.google.android.libraries.places.internal;

import java.util.Arrays;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzhu {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzht zzc;

    public final zzhu zza(Object obj, Object obj2) {
        int i = this.zzb + 1;
        int i2 = i + i;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (i2 > length) {
            int i3 = length + (length >> 1) + 1;
            if (i3 < i2) {
                int highestOneBit = Integer.highestOneBit(i2 - 1);
                i3 = highestOneBit + highestOneBit;
            }
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i3);
        }
        zzhh.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i4 = this.zzb;
        int i5 = i4 + i4;
        objArr2[i5] = obj;
        objArr2[i5 + 1] = obj2;
        this.zzb = i4 + 1;
        return this;
    }

    public final zzhv zzb() {
        zzht zzhtVar = this.zzc;
        if (zzhtVar != null) {
            throw zzhtVar.zza();
        }
        zzil zzf = zzil.zzf(this.zzb, this.zza, this);
        zzht zzhtVar2 = this.zzc;
        if (zzhtVar2 == null) {
            return zzf;
        }
        throw zzhtVar2.zza();
    }
}
