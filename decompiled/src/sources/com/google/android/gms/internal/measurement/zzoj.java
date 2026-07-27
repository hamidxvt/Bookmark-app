package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzoj {
    private static final zzoj zza = new zzoj(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzoj() {
        this(0, new int[8], new Object[8], true);
    }

    private zzoj(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzoj zza() {
        return zza;
    }

    static zzoj zzb() {
        return new zzoj(0, new int[8], new Object[8], true);
    }

    static zzoj zzc(zzoj zzojVar, zzoj zzojVar2) {
        int i = zzojVar.zzb + zzojVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzojVar.zzc, i);
        System.arraycopy(zzojVar2.zzc, 0, copyOf, zzojVar.zzb, zzojVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzojVar.zzd, i);
        System.arraycopy(zzojVar2.zzd, 0, copyOf2, zzojVar.zzb, zzojVar2.zzb);
        return new zzoj(i, copyOf, copyOf2, true);
    }

    private final void zzm(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzoj)) {
            return false;
        }
        zzoj zzojVar = (zzoj) obj;
        int i = this.zzb;
        if (i == zzojVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzojVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzojVar.zzd;
                    int i3 = this.zzb;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
                if (iArr[i2] != iArr2[i2]) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = i + 527;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = ((i2 * 31) + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final void zzd() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    final void zze() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    final void zzf(zzov zzovVar) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzovVar.zzv(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzg(zzov zzovVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                switch (i3) {
                    case 0:
                        zzovVar.zzc(i4, ((Long) obj).longValue());
                        break;
                    case 1:
                        zzovVar.zzj(i4, ((Long) obj).longValue());
                        break;
                    case 2:
                        zzovVar.zzn(i4, (zzlh) obj);
                        break;
                    case 3:
                        zzovVar.zzt(i4);
                        ((zzoj) obj).zzg(zzovVar);
                        zzovVar.zzu(i4);
                        break;
                    case 4:
                    default:
                        throw new RuntimeException(new zzmq("Protocol message tag had invalid wire type."));
                    case 5:
                        zzovVar.zzk(i4, ((Integer) obj).intValue());
                        break;
                }
            }
        }
    }

    public final int zzh() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3] >>> 3;
            zzlh zzlhVar = (zzlh) this.zzd[i3];
            int zzz = zzlm.zzz(8);
            int zzz2 = zzlm.zzz(16) + zzlm.zzz(i4);
            int zzz3 = zzlm.zzz(24);
            int zzc = zzlhVar.zzc();
            i2 += zzz + zzz + zzz2 + zzz3 + zzlm.zzz(zzc) + zzc;
        }
        this.zze = i2;
        return i2;
    }

    public final int zzi() {
        int zzz;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            switch (i4 & 7) {
                case 0:
                    zzz = zzlm.zzz(i5 << 3) + zzlm.zzA(((Long) this.zzd[i3]).longValue());
                    break;
                case 1:
                    ((Long) this.zzd[i3]).longValue();
                    zzz = zzlm.zzz(i5 << 3) + 8;
                    break;
                case 2:
                    int i6 = i5 << 3;
                    zzlh zzlhVar = (zzlh) this.zzd[i3];
                    int zzz2 = zzlm.zzz(i6);
                    int zzc = zzlhVar.zzc();
                    zzz = zzz2 + zzlm.zzz(zzc) + zzc;
                    break;
                case 3:
                    int zzz3 = zzlm.zzz(i5 << 3);
                    zzz = zzz3 + zzz3 + ((zzoj) this.zzd[i3]).zzi();
                    break;
                case 4:
                default:
                    throw new IllegalStateException(new zzmq("Protocol message tag had invalid wire type."));
                case 5:
                    ((Integer) this.zzd[i3]).intValue();
                    zzz = zzlm.zzz(i5 << 3) + 4;
                    break;
            }
            i2 += zzz;
        }
        this.zze = i2;
        return i2;
    }

    final void zzj(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzno.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zzk(int i, Object obj) {
        zze();
        zzm(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    final zzoj zzl(zzoj zzojVar) {
        if (zzojVar.equals(zza)) {
            return this;
        }
        zze();
        int i = this.zzb + zzojVar.zzb;
        zzm(i);
        System.arraycopy(zzojVar.zzc, 0, this.zzc, this.zzb, zzojVar.zzb);
        System.arraycopy(zzojVar.zzd, 0, this.zzd, this.zzb, zzojVar.zzb);
        this.zzb = i;
        return this;
    }
}
