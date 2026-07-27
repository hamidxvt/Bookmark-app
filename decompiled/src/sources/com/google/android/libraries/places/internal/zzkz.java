package com.google.android.libraries.places.internal;

import java.io.IOException;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzkz extends zzlb {
    final char[] zza;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    zzkz(String str, String str2) {
        super(r4, null);
        char[] cArr;
        zzky zzkyVar = new zzky("base16()", "0123456789ABCDEF".toCharArray());
        this.zza = new char[512];
        cArr = zzkyVar.zzf;
        zzha.zzd(cArr.length == 16);
        for (int i = 0; i < 256; i++) {
            this.zza[i] = zzkyVar.zza(i >>> 4);
            this.zza[i | 256] = zzkyVar.zza(i & 15);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzlb, com.google.android.libraries.places.internal.zzlc
    final void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzha.zzg(0, i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i3] & 255;
            appendable.append(this.zza[i4]);
            appendable.append(this.zza[i4 | 256]);
        }
    }
}
