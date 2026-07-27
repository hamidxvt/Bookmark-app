package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzacj extends zzacm {
    private final int zzc;

    zzacj(byte[] bArr, int i, int i2) {
        super(bArr);
        zzj(0, i2, bArr.length);
        this.zzc = i2;
    }

    @Override // com.google.android.libraries.places.internal.zzacm, com.google.android.libraries.places.internal.zzacp
    final byte zzb(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.libraries.places.internal.zzacm
    protected final int zzc() {
        return 0;
    }

    @Override // com.google.android.libraries.places.internal.zzacm, com.google.android.libraries.places.internal.zzacp
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzacm, com.google.android.libraries.places.internal.zzacp
    public final byte zza(int i) {
        int i2 = this.zzc;
        if (((i2 - (i + 1)) | i) >= 0) {
            return this.zza[i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(i2);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }
}
