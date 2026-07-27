package com.google.android.libraries.places.internal;

import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzky {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    private final char[] zzf;
    private final byte[] zzg;

    zzky(String str, char[] cArr) {
        this.zze = str;
        if (cArr == null) {
            throw null;
        }
        this.zzf = cArr;
        try {
            int length = cArr.length;
            int zzb = zzaax.zzb(length, RoundingMode.UNNECESSARY);
            this.zzb = zzb;
            int min = Math.min(8, Integer.lowestOneBit(zzb));
            try {
                this.zzc = 8 / min;
                this.zzd = this.zzb / min;
                this.zza = length - 1;
                byte[] bArr = new byte[128];
                Arrays.fill(bArr, (byte) -1);
                int i = 0;
                while (true) {
                    boolean z = true;
                    if (i >= cArr.length) {
                        break;
                    }
                    char c = cArr[i];
                    zzha.zzf(c < 128, "Non-ASCII character: %s", c);
                    if (bArr[c] != -1) {
                        z = false;
                    }
                    zzha.zzf(z, "Duplicate character: %s", c);
                    bArr[c] = (byte) i;
                    i++;
                }
                this.zzg = bArr;
                boolean[] zArr = new boolean[this.zzc];
                for (int i2 = 0; i2 < this.zzd; i2++) {
                    zArr[zzaax.zza(i2 * 8, this.zzb, RoundingMode.CEILING)] = true;
                }
            } catch (ArithmeticException e) {
                String str2 = new String(cArr);
                throw new IllegalArgumentException(str2.length() != 0 ? "Illegal alphabet ".concat(str2) : new String("Illegal alphabet "), e);
            }
        } catch (ArithmeticException e2) {
            int length2 = cArr.length;
            StringBuilder sb = new StringBuilder(35);
            sb.append("Illegal alphabet length ");
            sb.append(length2);
            throw new IllegalArgumentException(sb.toString(), e2);
        }
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzky) {
            return Arrays.equals(this.zzf, ((zzky) obj).zzf);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzf);
    }

    public final String toString() {
        return this.zze;
    }

    final char zza(int i) {
        return this.zzf[i];
    }

    public final boolean zzb(char c) {
        return c < 128 && this.zzg[c] != -1;
    }
}
