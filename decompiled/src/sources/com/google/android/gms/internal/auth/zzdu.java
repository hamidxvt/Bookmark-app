package com.google.android.gms.internal.auth;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
final class zzdu {
    static int zza(byte[] bArr, int i, zzdt zzdtVar) throws zzfb {
        int zzh = zzh(bArr, i, zzdtVar);
        int i2 = zzdtVar.zza;
        if (i2 < 0) {
            throw zzfb.zzc();
        }
        if (i2 > bArr.length - zzh) {
            throw zzfb.zzf();
        }
        if (i2 == 0) {
            zzdtVar.zzc = zzef.zzb;
            return zzh;
        }
        zzdtVar.zzc = zzef.zzk(bArr, zzh, i2);
        return zzh + i2;
    }

    static int zzb(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = bArr[i + 1] & 255;
        int i4 = bArr[i + 2] & 255;
        return ((bArr[i + 3] & 255) << 24) | (i3 << 8) | i2 | (i4 << 16);
    }

    static int zzc(zzgi zzgiVar, byte[] bArr, int i, int i2, int i3, zzdt zzdtVar) throws IOException {
        Object zzd = zzgiVar.zzd();
        int zzl = zzl(zzd, zzgiVar, bArr, i, i2, i3, zzdtVar);
        zzgiVar.zze(zzd);
        zzdtVar.zzc = zzd;
        return zzl;
    }

    static int zzd(zzgi zzgiVar, byte[] bArr, int i, int i2, zzdt zzdtVar) throws IOException {
        Object zzd = zzgiVar.zzd();
        int zzm = zzm(zzd, zzgiVar, bArr, i, i2, zzdtVar);
        zzgiVar.zze(zzd);
        zzdtVar.zzc = zzd;
        return zzm;
    }

    static int zze(zzgi zzgiVar, int i, byte[] bArr, int i2, int i3, zzez zzezVar, zzdt zzdtVar) throws IOException {
        int zzd = zzd(zzgiVar, bArr, i2, i3, zzdtVar);
        zzezVar.add(zzdtVar.zzc);
        while (zzd < i3) {
            int zzh = zzh(bArr, zzd, zzdtVar);
            if (i != zzdtVar.zza) {
                break;
            }
            zzd = zzd(zzgiVar, bArr, zzh, i3, zzdtVar);
            zzezVar.add(zzdtVar.zzc);
        }
        return zzd;
    }

    static int zzf(byte[] bArr, int i, zzez zzezVar, zzdt zzdtVar) throws IOException {
        zzew zzewVar = (zzew) zzezVar;
        int zzh = zzh(bArr, i, zzdtVar);
        int i2 = zzdtVar.zza + zzh;
        while (zzh < i2) {
            zzh = zzh(bArr, zzh, zzdtVar);
            zzewVar.zze(zzdtVar.zza);
        }
        if (zzh == i2) {
            return zzh;
        }
        throw zzfb.zzf();
    }

    static int zzh(byte[] bArr, int i, zzdt zzdtVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzi(b, bArr, i2, zzdtVar);
        }
        zzdtVar.zza = b;
        return i2;
    }

    static int zzi(int i, byte[] bArr, int i2, zzdt zzdtVar) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzdtVar.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzdtVar.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzdtVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzdtVar.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzdtVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzj(int i, byte[] bArr, int i2, int i3, zzez zzezVar, zzdt zzdtVar) {
        zzew zzewVar = (zzew) zzezVar;
        int zzh = zzh(bArr, i2, zzdtVar);
        zzewVar.zze(zzdtVar.zza);
        while (zzh < i3) {
            int zzh2 = zzh(bArr, zzh, zzdtVar);
            if (i != zzdtVar.zza) {
                break;
            }
            zzh = zzh(bArr, zzh2, zzdtVar);
            zzewVar.zze(zzdtVar.zza);
        }
        return zzh;
    }

    static int zzk(byte[] bArr, int i, zzdt zzdtVar) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzdtVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | ((b & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= (b2 & Byte.MAX_VALUE) << i4;
            i3 = i5;
            b = b2;
        }
        zzdtVar.zzb = j2;
        return i3;
    }

    static int zzl(Object obj, zzgi zzgiVar, byte[] bArr, int i, int i2, int i3, zzdt zzdtVar) throws IOException {
        int zzb = ((zzga) zzgiVar).zzb(obj, bArr, i, i2, i3, zzdtVar);
        zzdtVar.zzc = obj;
        return zzb;
    }

    static int zzm(Object obj, zzgi zzgiVar, byte[] bArr, int i, int i2, zzdt zzdtVar) throws IOException {
        int i3;
        int i4 = i + 1;
        int i5 = bArr[i];
        if (i5 < 0) {
            int zzi = zzi(i5, bArr, i4, zzdtVar);
            i5 = zzdtVar.zza;
            i3 = zzi;
        } else {
            i3 = i4;
        }
        if (i5 < 0 || i5 > i2 - i3) {
            throw zzfb.zzf();
        }
        int i6 = i5 + i3;
        zzgiVar.zzg(obj, bArr, i3, i6, zzdtVar);
        zzdtVar.zzc = obj;
        return i6;
    }

    static long zzn(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48) | ((bArr[i + 7] & 255) << 56);
    }

    static int zzg(int i, byte[] bArr, int i2, int i3, zzha zzhaVar, zzdt zzdtVar) throws zzfb {
        if ((i >>> 3) == 0) {
            throw zzfb.zza();
        }
        switch (i & 7) {
            case 0:
                int zzk = zzk(bArr, i2, zzdtVar);
                zzhaVar.zzh(i, Long.valueOf(zzdtVar.zzb));
                return zzk;
            case 1:
                zzhaVar.zzh(i, Long.valueOf(zzn(bArr, i2)));
                return i2 + 8;
            case 2:
                int zzh = zzh(bArr, i2, zzdtVar);
                int i4 = zzdtVar.zza;
                if (i4 < 0) {
                    throw zzfb.zzc();
                }
                if (i4 > bArr.length - zzh) {
                    throw zzfb.zzf();
                }
                if (i4 == 0) {
                    zzhaVar.zzh(i, zzef.zzb);
                } else {
                    zzhaVar.zzh(i, zzef.zzk(bArr, zzh, i4));
                }
                return zzh + i4;
            case 3:
                int i5 = (i & (-8)) | 4;
                zzha zzd = zzha.zzd();
                int i6 = 0;
                while (true) {
                    if (i2 < i3) {
                        int zzh2 = zzh(bArr, i2, zzdtVar);
                        int i7 = zzdtVar.zza;
                        if (i7 != i5) {
                            i6 = i7;
                            i2 = zzg(i7, bArr, zzh2, i3, zzd, zzdtVar);
                        } else {
                            i6 = i7;
                            i2 = zzh2;
                        }
                    }
                }
                if (i2 > i3 || i6 != i5) {
                    throw zzfb.zzd();
                }
                zzhaVar.zzh(i, zzd);
                return i2;
            case 4:
            default:
                throw zzfb.zza();
            case 5:
                zzhaVar.zzh(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
        }
    }
}
