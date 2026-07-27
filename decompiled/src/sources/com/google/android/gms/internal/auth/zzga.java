package com.google.android.gms.internal.auth;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
final class zzga<T> implements zzgi<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzhj.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfx zzg;
    private final int[] zzh;
    private final int zzi;
    private final int zzj;
    private final zzfl zzk;
    private final zzgz zzl;
    private final zzem zzm;
    private final zzgc zzn;
    private final zzfs zzo;

    private zzga(int[] iArr, Object[] objArr, int i, int i2, zzfx zzfxVar, int i3, boolean z, int[] iArr2, int i4, int i5, zzgc zzgcVar, zzfl zzflVar, zzgz zzgzVar, zzem zzemVar, zzfs zzfsVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzh = iArr2;
        this.zzi = i4;
        this.zzj = i5;
        this.zzn = zzgcVar;
        this.zzk = zzflVar;
        this.zzl = zzgzVar;
        this.zzm = zzemVar;
        this.zzg = zzfxVar;
        this.zzo = zzfsVar;
    }

    private final void zzA(Object obj, int i, int i2) {
        zzhj.zzn(obj, zzl(i2) & 1048575, i);
    }

    private final void zzB(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzo(i) & 1048575, obj2);
        zzz(obj, i);
    }

    private final void zzC(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzo(i2) & 1048575, obj2);
        zzA(obj, i, i2);
    }

    private final boolean zzD(Object obj, Object obj2, int i) {
        return zzE(obj, i) == zzE(obj2, i);
    }

    private final boolean zzE(Object obj, int i) {
        int zzl = zzl(i);
        long j = zzl & 1048575;
        if (j != 1048575) {
            return (zzhj.zzc(obj, j) & (1 << (zzl >>> 20))) != 0;
        }
        int zzo = zzo(i);
        long j2 = zzo & 1048575;
        switch (zzn(zzo)) {
            case 0:
                return Double.doubleToRawLongBits(zzhj.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzhj.zzb(obj, j2)) != 0;
            case 2:
                return zzhj.zzd(obj, j2) != 0;
            case 3:
                return zzhj.zzd(obj, j2) != 0;
            case 4:
                return zzhj.zzc(obj, j2) != 0;
            case 5:
                return zzhj.zzd(obj, j2) != 0;
            case 6:
                return zzhj.zzc(obj, j2) != 0;
            case 7:
                return zzhj.zzt(obj, j2);
            case 8:
                Object zzf = zzhj.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                }
                if (zzf instanceof zzef) {
                    return !zzef.zzb.equals(zzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzhj.zzf(obj, j2) != null;
            case 10:
                return !zzef.zzb.equals(zzhj.zzf(obj, j2));
            case 11:
                return zzhj.zzc(obj, j2) != 0;
            case 12:
                return zzhj.zzc(obj, j2) != 0;
            case 13:
                return zzhj.zzc(obj, j2) != 0;
            case 14:
                return zzhj.zzd(obj, j2) != 0;
            case 15:
                return zzhj.zzc(obj, j2) != 0;
            case 16:
                return zzhj.zzd(obj, j2) != 0;
            case 17:
                return zzhj.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzF(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzE(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzG(Object obj, int i, zzgi zzgiVar) {
        return zzgiVar.zzi(zzhj.zzf(obj, i & 1048575));
    }

    private static boolean zzH(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzev) {
            return ((zzev) obj).zzm();
        }
        return true;
    }

    private final boolean zzI(Object obj, int i, int i2) {
        return zzhj.zzc(obj, (long) (zzl(i2) & 1048575)) == i;
    }

    static zzha zzc(Object obj) {
        zzev zzevVar = (zzev) obj;
        zzha zzhaVar = zzevVar.zzc;
        if (zzhaVar != zzha.zza()) {
            return zzhaVar;
        }
        zzha zzd = zzha.zzd();
        zzevVar.zzc = zzd;
        return zzd;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0348  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static zzga zzj(Class cls, zzfu zzfuVar, zzgc zzgcVar, zzfl zzflVar, zzgz zzgzVar, zzem zzemVar, zzfs zzfsVar) {
        int i;
        int charAt;
        int charAt2;
        int i2;
        int[] iArr;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        char charAt3;
        int i8;
        char charAt4;
        int i9;
        char charAt5;
        int i10;
        char charAt6;
        int i11;
        char charAt7;
        int i12;
        char charAt8;
        int i13;
        char charAt9;
        int i14;
        char charAt10;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        Class<?> cls2;
        int objectFieldOffset;
        int i20;
        String str;
        int i21;
        int i22;
        int i23;
        Field zzv;
        char charAt11;
        Field zzv2;
        Field zzv3;
        int i24;
        char charAt12;
        int i25;
        char charAt13;
        int i26;
        char charAt14;
        int i27;
        char charAt15;
        if (!(zzfuVar instanceof zzgh)) {
            throw null;
        }
        zzgh zzghVar = (zzgh) zzfuVar;
        String zzd = zzghVar.zzd();
        int length = zzd.length();
        char c = 55296;
        if (zzd.charAt(0) >= 55296) {
            int i28 = 1;
            while (true) {
                i = i28 + 1;
                if (zzd.charAt(i28) < 55296) {
                    break;
                }
                i28 = i;
            }
        } else {
            i = 1;
        }
        int i29 = i + 1;
        int charAt16 = zzd.charAt(i);
        if (charAt16 >= 55296) {
            int i30 = charAt16 & 8191;
            int i31 = 13;
            while (true) {
                i27 = i29 + 1;
                charAt15 = zzd.charAt(i29);
                if (charAt15 < 55296) {
                    break;
                }
                i30 |= (charAt15 & 8191) << i31;
                i31 += 13;
                i29 = i27;
            }
            charAt16 = i30 | (charAt15 << i31);
            i29 = i27;
        }
        if (charAt16 == 0) {
            charAt = 0;
            charAt2 = 0;
            i3 = 0;
            i6 = 0;
            i2 = 0;
            i4 = 0;
            iArr = zza;
            i5 = 0;
        } else {
            int i32 = i29 + 1;
            int charAt17 = zzd.charAt(i29);
            if (charAt17 >= 55296) {
                int i33 = charAt17 & 8191;
                int i34 = 13;
                while (true) {
                    i14 = i32 + 1;
                    charAt10 = zzd.charAt(i32);
                    if (charAt10 < 55296) {
                        break;
                    }
                    i33 |= (charAt10 & 8191) << i34;
                    i34 += 13;
                    i32 = i14;
                }
                charAt17 = i33 | (charAt10 << i34);
                i32 = i14;
            }
            int i35 = i32 + 1;
            int charAt18 = zzd.charAt(i32);
            if (charAt18 >= 55296) {
                int i36 = charAt18 & 8191;
                int i37 = 13;
                while (true) {
                    i13 = i35 + 1;
                    charAt9 = zzd.charAt(i35);
                    if (charAt9 < 55296) {
                        break;
                    }
                    i36 |= (charAt9 & 8191) << i37;
                    i37 += 13;
                    i35 = i13;
                }
                charAt18 = i36 | (charAt9 << i37);
                i35 = i13;
            }
            int i38 = i35 + 1;
            int charAt19 = zzd.charAt(i35);
            if (charAt19 >= 55296) {
                int i39 = charAt19 & 8191;
                int i40 = 13;
                while (true) {
                    i12 = i38 + 1;
                    charAt8 = zzd.charAt(i38);
                    if (charAt8 < 55296) {
                        break;
                    }
                    i39 |= (charAt8 & 8191) << i40;
                    i40 += 13;
                    i38 = i12;
                }
                charAt19 = i39 | (charAt8 << i40);
                i38 = i12;
            }
            int i41 = i38 + 1;
            int charAt20 = zzd.charAt(i38);
            if (charAt20 >= 55296) {
                int i42 = charAt20 & 8191;
                int i43 = 13;
                while (true) {
                    i11 = i41 + 1;
                    charAt7 = zzd.charAt(i41);
                    if (charAt7 < 55296) {
                        break;
                    }
                    i42 |= (charAt7 & 8191) << i43;
                    i43 += 13;
                    i41 = i11;
                }
                charAt20 = i42 | (charAt7 << i43);
                i41 = i11;
            }
            int i44 = i41 + 1;
            charAt = zzd.charAt(i41);
            if (charAt >= 55296) {
                int i45 = charAt & 8191;
                int i46 = 13;
                while (true) {
                    i10 = i44 + 1;
                    charAt6 = zzd.charAt(i44);
                    if (charAt6 < 55296) {
                        break;
                    }
                    i45 |= (charAt6 & 8191) << i46;
                    i46 += 13;
                    i44 = i10;
                }
                charAt = i45 | (charAt6 << i46);
                i44 = i10;
            }
            int i47 = i44 + 1;
            charAt2 = zzd.charAt(i44);
            if (charAt2 >= 55296) {
                int i48 = charAt2 & 8191;
                int i49 = 13;
                while (true) {
                    i9 = i47 + 1;
                    charAt5 = zzd.charAt(i47);
                    if (charAt5 < 55296) {
                        break;
                    }
                    i48 |= (charAt5 & 8191) << i49;
                    i49 += 13;
                    i47 = i9;
                }
                charAt2 = i48 | (charAt5 << i49);
                i47 = i9;
            }
            int i50 = i47 + 1;
            int charAt21 = zzd.charAt(i47);
            if (charAt21 >= 55296) {
                int i51 = charAt21 & 8191;
                int i52 = 13;
                while (true) {
                    i8 = i50 + 1;
                    charAt4 = zzd.charAt(i50);
                    if (charAt4 < 55296) {
                        break;
                    }
                    i51 |= (charAt4 & 8191) << i52;
                    i52 += 13;
                    i50 = i8;
                }
                charAt21 = i51 | (charAt4 << i52);
                i50 = i8;
            }
            int i53 = i50 + 1;
            int charAt22 = zzd.charAt(i50);
            if (charAt22 >= 55296) {
                int i54 = charAt22 & 8191;
                int i55 = 13;
                while (true) {
                    i7 = i53 + 1;
                    charAt3 = zzd.charAt(i53);
                    if (charAt3 < 55296) {
                        break;
                    }
                    i54 |= (charAt3 & 8191) << i55;
                    i55 += 13;
                    i53 = i7;
                }
                charAt22 = i54 | (charAt3 << i55);
                i53 = i7;
            }
            i2 = charAt17 + charAt17 + charAt18;
            iArr = new int[charAt22 + charAt2 + charAt21];
            i3 = charAt19;
            i4 = charAt22;
            i5 = charAt17;
            i6 = charAt20;
            i29 = i53;
        }
        Unsafe unsafe = zzb;
        Object[] zze = zzghVar.zze();
        Class<?> cls3 = zzghVar.zza().getClass();
        int i56 = i4 + charAt2;
        int i57 = charAt + charAt;
        int[] iArr2 = new int[charAt * 3];
        Object[] objArr = new Object[i57];
        int i58 = 0;
        int i59 = 0;
        int i60 = i4;
        int i61 = i56;
        while (i29 < length) {
            int i62 = i29 + 1;
            int charAt23 = zzd.charAt(i29);
            if (charAt23 >= c) {
                int i63 = charAt23 & 8191;
                int i64 = i62;
                int i65 = 13;
                while (true) {
                    i26 = i64 + 1;
                    charAt14 = zzd.charAt(i64);
                    if (charAt14 < c) {
                        break;
                    }
                    i63 |= (charAt14 & 8191) << i65;
                    i65 += 13;
                    i64 = i26;
                }
                charAt23 = i63 | (charAt14 << i65);
                i15 = i26;
            } else {
                i15 = i62;
            }
            int i66 = i15 + 1;
            int charAt24 = zzd.charAt(i15);
            if (charAt24 >= c) {
                int i67 = charAt24 & 8191;
                int i68 = i66;
                int i69 = 13;
                while (true) {
                    i25 = i68 + 1;
                    charAt13 = zzd.charAt(i68);
                    if (charAt13 < c) {
                        break;
                    }
                    i67 |= (charAt13 & 8191) << i69;
                    i69 += 13;
                    i68 = i25;
                }
                charAt24 = i67 | (charAt13 << i69);
                i16 = i25;
            } else {
                i16 = i66;
            }
            if ((charAt24 & 1024) != 0) {
                iArr[i58] = i59;
                i58++;
            }
            int i70 = charAt24 & 255;
            if (i70 >= 51) {
                int i71 = i16 + 1;
                int charAt25 = zzd.charAt(i16);
                i17 = length;
                char c2 = 55296;
                if (charAt25 >= 55296) {
                    int i72 = charAt25 & 8191;
                    int i73 = 13;
                    while (true) {
                        i24 = i71 + 1;
                        charAt12 = zzd.charAt(i71);
                        if (charAt12 < c2) {
                            break;
                        }
                        i72 |= (charAt12 & 8191) << i73;
                        i73 += 13;
                        i71 = i24;
                        c2 = 55296;
                    }
                    charAt25 = i72 | (charAt12 << i73);
                    i71 = i24;
                }
                int i74 = i70 - 51;
                int i75 = i71;
                if (i74 == 9 || i74 == 17) {
                    int i76 = i59 / 3;
                    objArr[i76 + i76 + 1] = zze[i2];
                    i2++;
                } else if (i74 == 12 && (zzghVar.zzc() == 1 || (charAt24 & 2048) != 0)) {
                    int i77 = i59 / 3;
                    objArr[i77 + i77 + 1] = zze[i2];
                    i2++;
                }
                int i78 = charAt25 + charAt25;
                Object obj = zze[i78];
                if (obj instanceof Field) {
                    zzv2 = (Field) obj;
                } else {
                    zzv2 = zzv(cls3, (String) obj);
                    zze[i78] = zzv2;
                }
                int i79 = i3;
                i18 = i6;
                i23 = (int) unsafe.objectFieldOffset(zzv2);
                int i80 = i78 + 1;
                Object obj2 = zze[i80];
                if (obj2 instanceof Field) {
                    zzv3 = (Field) obj2;
                } else {
                    zzv3 = zzv(cls3, (String) obj2);
                    zze[i80] = zzv3;
                }
                str = zzd;
                i19 = i79;
                cls2 = cls3;
                i21 = i75;
                i20 = (int) unsafe.objectFieldOffset(zzv3);
                i22 = 0;
            } else {
                i17 = length;
                int i81 = i3;
                i18 = i6;
                int i82 = i2 + 1;
                Field zzv4 = zzv(cls3, (String) zze[i2]);
                if (i70 == 9) {
                    i19 = i81;
                } else if (i70 == 17) {
                    i19 = i81;
                } else {
                    if (i70 == 27) {
                        i19 = i81;
                    } else if (i70 == 49) {
                        i19 = i81;
                    } else {
                        if (i70 == 12 || i70 == 30 || i70 == 44) {
                            i19 = i81;
                            if (zzghVar.zzc() == 1 || (charAt24 & 2048) != 0) {
                                int i83 = i59 / 3;
                                objArr[i83 + i83 + 1] = zze[i82];
                                i82++;
                            }
                        } else if (i70 == 50) {
                            int i84 = i60 + 1;
                            iArr[i60] = i59;
                            int i85 = i59 / 3;
                            int i86 = i82 + 1;
                            int i87 = i85 + i85;
                            objArr[i87] = zze[i82];
                            if ((charAt24 & 2048) != 0) {
                                i82 = i86 + 1;
                                objArr[i87 + 1] = zze[i86];
                                i19 = i81;
                                i60 = i84;
                            } else {
                                i60 = i84;
                                i82 = i86;
                                i19 = i81;
                            }
                        } else {
                            i19 = i81;
                        }
                        cls2 = cls3;
                        objectFieldOffset = (int) unsafe.objectFieldOffset(zzv4);
                        i20 = 1048575;
                        if ((charAt24 & 4096) != 0 || i70 > 17) {
                            str = zzd;
                            i21 = i16;
                            i22 = 0;
                        } else {
                            int i88 = i16 + 1;
                            int charAt26 = zzd.charAt(i16);
                            if (charAt26 >= 55296) {
                                int i89 = charAt26 & 8191;
                                int i90 = 13;
                                while (true) {
                                    i21 = i88 + 1;
                                    charAt11 = zzd.charAt(i88);
                                    if (charAt11 < 55296) {
                                        break;
                                    }
                                    i89 |= (charAt11 & 8191) << i90;
                                    i90 += 13;
                                    i88 = i21;
                                }
                                charAt26 = i89 | (charAt11 << i90);
                            } else {
                                i21 = i88;
                            }
                            int i91 = i5 + i5 + (charAt26 / 32);
                            Object obj3 = zze[i91];
                            str = zzd;
                            if (obj3 instanceof Field) {
                                zzv = (Field) obj3;
                            } else {
                                zzv = zzv(cls2, (String) obj3);
                                zze[i91] = zzv;
                            }
                            i22 = charAt26 % 32;
                            i20 = (int) unsafe.objectFieldOffset(zzv);
                        }
                        if (i70 >= 18 || i70 > 49) {
                            i2 = i82;
                            i23 = objectFieldOffset;
                        } else {
                            iArr[i61] = objectFieldOffset;
                            i2 = i82;
                            i23 = objectFieldOffset;
                            i61++;
                        }
                    }
                    int i92 = i59 / 3;
                    objArr[i92 + i92 + 1] = zze[i82];
                    i82++;
                    cls2 = cls3;
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zzv4);
                    i20 = 1048575;
                    if ((charAt24 & 4096) != 0) {
                    }
                    str = zzd;
                    i21 = i16;
                    i22 = 0;
                    if (i70 >= 18) {
                    }
                    i2 = i82;
                    i23 = objectFieldOffset;
                }
                int i93 = i59 / 3;
                objArr[i93 + i93 + 1] = zzv4.getType();
                cls2 = cls3;
                objectFieldOffset = (int) unsafe.objectFieldOffset(zzv4);
                i20 = 1048575;
                if ((charAt24 & 4096) != 0) {
                }
                str = zzd;
                i21 = i16;
                i22 = 0;
                if (i70 >= 18) {
                }
                i2 = i82;
                i23 = objectFieldOffset;
            }
            int i94 = i59 + 1;
            iArr2[i59] = charAt23;
            int i95 = i94 + 1;
            iArr2[i94] = ((charAt24 & 256) != 0 ? 268435456 : 0) | ((charAt24 & 512) != 0 ? 536870912 : 0) | ((charAt24 & 2048) != 0 ? Integer.MIN_VALUE : 0) | (i70 << 20) | i23;
            i59 = i95 + 1;
            iArr2[i95] = (i22 << 20) | i20;
            cls3 = cls2;
            length = i17;
            i3 = i19;
            i29 = i21;
            zzd = str;
            i6 = i18;
            c = 55296;
        }
        return new zzga(iArr2, objArr, i3, i6, zzghVar.zza(), zzghVar.zzc(), false, iArr, i4, i56, zzgcVar, zzflVar, zzgzVar, zzemVar, zzfsVar);
    }

    private static int zzk(Object obj, long j) {
        return ((Integer) zzhj.zzf(obj, j)).intValue();
    }

    private final int zzl(int i) {
        return this.zzc[i + 2];
    }

    private final int zzm(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzn(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzo(int i) {
        return this.zzc[i + 1];
    }

    private static long zzp(Object obj, long j) {
        return ((Long) zzhj.zzf(obj, j)).longValue();
    }

    private final zzey zzq(int i) {
        int i2 = i / 3;
        return (zzey) this.zzd[i2 + i2 + 1];
    }

    private final zzgi zzr(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzgi zzgiVar = (zzgi) this.zzd[i3];
        if (zzgiVar != null) {
            return zzgiVar;
        }
        zzgi zzb2 = zzgf.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzs(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzt(Object obj, int i) {
        zzgi zzr = zzr(i);
        int zzo = zzo(i) & 1048575;
        if (!zzE(obj, i)) {
            return zzr.zzd();
        }
        Object object = zzb.getObject(obj, zzo);
        if (zzH(object)) {
            return object;
        }
        Object zzd = zzr.zzd();
        if (object != null) {
            zzr.zzf(zzd, object);
        }
        return zzd;
    }

    private final Object zzu(Object obj, int i, int i2) {
        zzgi zzr = zzr(i2);
        if (!zzI(obj, i, i2)) {
            return zzr.zzd();
        }
        Object object = zzb.getObject(obj, zzo(i2) & 1048575);
        if (zzH(object)) {
            return object;
        }
        Object zzd = zzr.zzd();
        if (object != null) {
            zzr.zzf(zzd, object);
        }
        return zzd;
    }

    private static Field zzv(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzw(Object obj) {
        if (!zzH(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzx(Object obj, Object obj2, int i) {
        if (zzE(obj2, i)) {
            long zzo = zzo(i) & 1048575;
            Object object = zzb.getObject(obj2, zzo);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzgi zzr = zzr(i);
            if (!zzE(obj, i)) {
                if (zzH(object)) {
                    Object zzd = zzr.zzd();
                    zzr.zzf(zzd, object);
                    zzb.putObject(obj, zzo, zzd);
                } else {
                    zzb.putObject(obj, zzo, object);
                }
                zzz(obj, i);
                return;
            }
            Object object2 = zzb.getObject(obj, zzo);
            if (!zzH(object2)) {
                Object zzd2 = zzr.zzd();
                zzr.zzf(zzd2, object2);
                zzb.putObject(obj, zzo, zzd2);
                object2 = zzd2;
            }
            zzr.zzf(object2, object);
        }
    }

    private final void zzy(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzI(obj2, i2, i)) {
            long zzo = zzo(i) & 1048575;
            Object object = zzb.getObject(obj2, zzo);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzgi zzr = zzr(i);
            if (!zzI(obj, i2, i)) {
                if (zzH(object)) {
                    Object zzd = zzr.zzd();
                    zzr.zzf(zzd, object);
                    zzb.putObject(obj, zzo, zzd);
                } else {
                    zzb.putObject(obj, zzo, object);
                }
                zzA(obj, i2, i);
                return;
            }
            Object object2 = zzb.getObject(obj, zzo);
            if (!zzH(object2)) {
                Object zzd2 = zzr.zzd();
                zzr.zzf(zzd2, object2);
                zzb.putObject(obj, zzo, zzd2);
                object2 = zzd2;
            }
            zzr.zzf(object2, object);
        }
    }

    private final void zzz(Object obj, int i) {
        int zzl = zzl(i);
        long j = 1048575 & zzl;
        if (j == 1048575) {
            return;
        }
        zzhj.zzn(obj, j, (1 << (zzl >>> 20)) | zzhj.zzc(obj, j));
    }

    @Override // com.google.android.gms.internal.auth.zzgi
    public final int zza(Object obj) {
        int length = this.zzc.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzo = zzo(i2);
            int i3 = this.zzc[i2];
            long j = 1048575 & zzo;
            switch (zzn(zzo)) {
                case 0:
                    long doubleToLongBits = Double.doubleToLongBits(zzhj.zza(obj, j));
                    byte[] bArr = zzfa.zzd;
                    i = (i * 53) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzhj.zzb(obj, j));
                    break;
                case 2:
                    long zzd = zzhj.zzd(obj, j);
                    byte[] bArr2 = zzfa.zzd;
                    i = (i * 53) + ((int) (zzd ^ (zzd >>> 32)));
                    break;
                case 3:
                    long zzd2 = zzhj.zzd(obj, j);
                    byte[] bArr3 = zzfa.zzd;
                    i = (i * 53) + ((int) (zzd2 ^ (zzd2 >>> 32)));
                    break;
                case 4:
                    i = (i * 53) + zzhj.zzc(obj, j);
                    break;
                case 5:
                    long zzd3 = zzhj.zzd(obj, j);
                    byte[] bArr4 = zzfa.zzd;
                    i = (i * 53) + ((int) (zzd3 ^ (zzd3 >>> 32)));
                    break;
                case 6:
                    i = (i * 53) + zzhj.zzc(obj, j);
                    break;
                case 7:
                    i = (i * 53) + zzfa.zza(zzhj.zzt(obj, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzhj.zzf(obj, j)).hashCode();
                    break;
                case 9:
                    Object zzf = zzhj.zzf(obj, j);
                    i = (i * 53) + (zzf != null ? zzf.hashCode() : 37);
                    break;
                case 10:
                    i = (i * 53) + zzhj.zzf(obj, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzhj.zzc(obj, j);
                    break;
                case 12:
                    i = (i * 53) + zzhj.zzc(obj, j);
                    break;
                case 13:
                    i = (i * 53) + zzhj.zzc(obj, j);
                    break;
                case 14:
                    long zzd4 = zzhj.zzd(obj, j);
                    byte[] bArr5 = zzfa.zzd;
                    i = (i * 53) + ((int) (zzd4 ^ (zzd4 >>> 32)));
                    break;
                case 15:
                    i = (i * 53) + zzhj.zzc(obj, j);
                    break;
                case 16:
                    long zzd5 = zzhj.zzd(obj, j);
                    byte[] bArr6 = zzfa.zzd;
                    i = (i * 53) + ((int) (zzd5 ^ (zzd5 >>> 32)));
                    break;
                case 17:
                    Object zzf2 = zzhj.zzf(obj, j);
                    i = (i * 53) + (zzf2 != null ? zzf2.hashCode() : 37);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case 49:
                    i = (i * 53) + zzhj.zzf(obj, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzhj.zzf(obj, j).hashCode();
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzI(obj, i3, i2)) {
                        long doubleToLongBits2 = Double.doubleToLongBits(((Double) zzhj.zzf(obj, j)).doubleValue());
                        byte[] bArr7 = zzfa.zzd;
                        i = (i * 53) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + Float.floatToIntBits(((Float) zzhj.zzf(obj, j)).floatValue());
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzI(obj, i3, i2)) {
                        long zzp = zzp(obj, j);
                        byte[] bArr8 = zzfa.zzd;
                        i = (i * 53) + ((int) (zzp ^ (zzp >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzI(obj, i3, i2)) {
                        long zzp2 = zzp(obj, j);
                        byte[] bArr9 = zzfa.zzd;
                        i = (i * 53) + ((int) (zzp2 ^ (zzp2 >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzk(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzI(obj, i3, i2)) {
                        long zzp3 = zzp(obj, j);
                        byte[] bArr10 = zzfa.zzd;
                        i = (i * 53) + ((int) (zzp3 ^ (zzp3 >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzk(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzfa.zza(((Boolean) zzhj.zzf(obj, j)).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + ((String) zzhj.zzf(obj, j)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzhj.zzf(obj, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzhj.zzf(obj, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzk(obj, j);
                        break;
                    } else {
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzk(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzk(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzI(obj, i3, i2)) {
                        long zzp4 = zzp(obj, j);
                        byte[] bArr11 = zzfa.zzd;
                        i = (i * 53) + ((int) (zzp4 ^ (zzp4 >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzk(obj, j);
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzI(obj, i3, i2)) {
                        long zzp5 = zzp(obj, j);
                        byte[] bArr12 = zzfa.zzd;
                        i = (i * 53) + ((int) (zzp5 ^ (zzp5 >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzI(obj, i3, i2)) {
                        i = (i * 53) + zzhj.zzf(obj, j).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        return (i * 53) + this.zzl.zzb(obj).hashCode();
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0e61, code lost:
    
        if (r6 == r11) goto L566;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0e63, code lost:
    
        r13.putInt(r7, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0e67, code lost:
    
        r2 = r0.zzi;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0e6b, code lost:
    
        if (r2 >= r0.zzj) goto L698;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0e6d, code lost:
    
        r3 = r0.zzh[r2];
        r5 = r0.zzc[r3];
        r5 = com.google.android.gms.internal.auth.zzhj.zzf(r7, r0.zzo(r3) & r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0e7f, code lost:
    
        if (r5 != null) goto L572;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0e86, code lost:
    
        if (r0.zzq(r3) != null) goto L697;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0e8b, code lost:
    
        r5 = (com.google.android.gms.internal.auth.zzfr) r5;
        r1 = (com.google.android.gms.internal.auth.zzfq) r0.zzs(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0e93, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0e88, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0e94, code lost:
    
        if (r8 != 0) goto L583;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0e98, code lost:
    
        if (r1 != r42) goto L581;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0e9f, code lost:
    
        throw com.google.android.gms.internal.auth.zzfb.zzd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0ea6, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0ea2, code lost:
    
        if (r1 > r42) goto L587;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0ea4, code lost:
    
        if (r4 != r8) goto L587;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0eab, code lost:
    
        throw com.google.android.gms.internal.auth.zzfb.zzd();
     */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0b25 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0b35 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0e08 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0e1c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0e23 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:616:0x0271  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final int zzb(Object obj, byte[] bArr, int i, int i2, int i3, zzdt zzdtVar) throws IOException {
        int i4;
        Unsafe unsafe;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        Unsafe unsafe2;
        int i15;
        int i16;
        int i17;
        Unsafe unsafe3;
        int i18;
        int i19;
        Unsafe unsafe4;
        int i20;
        int i21;
        int i22;
        Unsafe unsafe5;
        zzez zzezVar;
        int i23;
        int i24;
        int i25;
        Unsafe unsafe6;
        int i26;
        int i27;
        Unsafe unsafe7;
        int i28;
        int i29;
        int zzj;
        int i30;
        int i31;
        int zzh;
        int i32;
        int i33;
        zzdt zzdtVar2;
        int i34;
        zzga<T> zzgaVar = this;
        Object obj2 = obj;
        int i35 = i2;
        int i36 = i3;
        zzdt zzdtVar3 = zzdtVar;
        zzw(obj);
        Unsafe unsafe8 = zzb;
        int i37 = 0;
        int i38 = i;
        int i39 = 0;
        int i40 = 0;
        int i41 = 0;
        int i42 = -1;
        int i43 = 1048575;
        while (true) {
            if (i38 < i35) {
                int i44 = i38 + 1;
                int i45 = bArr[i38];
                if (i45 < 0) {
                    i7 = zzdu.zzi(i45, bArr, i44, zzdtVar3);
                    i6 = zzdtVar3.zza;
                } else {
                    i6 = i45;
                    i7 = i44;
                }
                int i46 = i6 >>> 3;
                int zzm = i46 > i42 ? (i46 < zzgaVar.zze || i46 > zzgaVar.zzf) ? -1 : zzgaVar.zzm(i46, i39 / 3) : (i46 < zzgaVar.zze || i46 > zzgaVar.zzf) ? -1 : zzgaVar.zzm(i46, i37);
                if (zzm != -1) {
                    int i47 = i6 & 7;
                    int[] iArr = zzgaVar.zzc;
                    int i48 = iArr[zzm + 1];
                    int i49 = i6;
                    int zzn = zzn(i48);
                    long j = i48 & 1048575;
                    if (zzn <= 17) {
                        int i50 = iArr[zzm + 2];
                        int i51 = 1 << (i50 >>> 20);
                        int i52 = i50 & 1048575;
                        if (i52 != i43) {
                            if (i43 != 1048575) {
                                i16 = i48;
                                unsafe8.putInt(obj2, i43, i41);
                            } else {
                                i16 = i48;
                            }
                            i10 = i52 == 1048575 ? 0 : unsafe8.getInt(obj2, i52);
                            i11 = i52;
                        } else {
                            i16 = i48;
                            i10 = i41;
                            i11 = i43;
                        }
                        switch (zzn) {
                            case 0:
                                int i53 = zzm;
                                if (i47 != 1) {
                                    i14 = 0;
                                    i17 = i49;
                                    zzm = i53;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4 && i4 != 0) {
                                        i38 = i8;
                                        i40 = i15;
                                        i41 = i10;
                                        unsafe = unsafe2;
                                        i43 = i11;
                                        i5 = 1048575;
                                        break;
                                    } else {
                                        int i54 = i12;
                                        i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                        i35 = i2;
                                        i39 = i13;
                                        i40 = i15;
                                        i42 = i54;
                                        unsafe8 = unsafe2;
                                        i37 = i14;
                                        i41 = i10;
                                        i43 = i11;
                                        zzdtVar3 = zzdtVar;
                                        i36 = i4;
                                    }
                                } else {
                                    zzhj.zzl(obj2, j, Double.longBitsToDouble(zzdu.zzn(bArr, i7)));
                                    i38 = i7 + 8;
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i53;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                }
                            case 1:
                                int i55 = zzm;
                                if (i47 == 5) {
                                    zzhj.zzm(obj2, j, Float.intBitsToFloat(zzdu.zzb(bArr, i7)));
                                    i38 = i7 + 4;
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i55;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i14 = 0;
                                    i17 = i49;
                                    zzm = i55;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i542 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i542;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 2:
                            case 3:
                                int i56 = zzm;
                                if (i47 == 0) {
                                    int zzk = zzdu.zzk(bArr, i7, zzdtVar3);
                                    unsafe8.putLong(obj, j, zzdtVar3.zzb);
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i56;
                                    i38 = zzk;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i14 = 0;
                                    i17 = i49;
                                    zzm = i56;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i5422 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i5422;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 4:
                            case 11:
                                int i57 = zzm;
                                if (i47 == 0) {
                                    i38 = zzdu.zzh(bArr, i7, zzdtVar3);
                                    unsafe8.putInt(obj2, j, zzdtVar3.zza);
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i57;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i14 = 0;
                                    i17 = i49;
                                    zzm = i57;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i54222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i54222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 5:
                            case 14:
                                int i58 = i7;
                                int i59 = zzm;
                                if (i47 == 1) {
                                    unsafe8.putLong(obj, j, zzdu.zzn(bArr, i58));
                                    i38 = i58 + 8;
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i59;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i14 = 0;
                                    i17 = i49;
                                    zzm = i59;
                                    unsafe3 = unsafe8;
                                    i7 = i58;
                                    i12 = i46;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i542222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i542222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 6:
                            case 13:
                                int i60 = i7;
                                int i61 = zzm;
                                if (i47 == 5) {
                                    unsafe8.putInt(obj2, j, zzdu.zzb(bArr, i60));
                                    i38 = i60 + 4;
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i61;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i7 = i60;
                                    i14 = 0;
                                    i17 = i49;
                                    zzm = i61;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i5422222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i5422222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 7:
                                int i62 = i7;
                                int i63 = zzm;
                                if (i47 == 0) {
                                    i38 = zzdu.zzk(bArr, i62, zzdtVar3);
                                    zzhj.zzk(obj2, j, zzdtVar3.zzb != 0);
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i63;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i7 = i62;
                                    i14 = 0;
                                    i17 = i49;
                                    zzm = i63;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i54222222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i54222222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 8:
                                int i64 = i7;
                                int i65 = zzm;
                                if (i47 == 2) {
                                    if ((i16 & 536870912) != 0) {
                                        i38 = zzdu.zzh(bArr, i64, zzdtVar3);
                                        int i66 = zzdtVar3.zza;
                                        if (i66 < 0) {
                                            throw zzfb.zzc();
                                        }
                                        if (i66 == 0) {
                                            zzdtVar3.zzc = "";
                                            i18 = 0;
                                        } else {
                                            int i67 = zzhn.zza;
                                            int length = bArr.length;
                                            if ((((length - i38) - i66) | i38 | i66) < 0) {
                                                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(i38), Integer.valueOf(i66)));
                                            }
                                            int i68 = i38 + i66;
                                            char[] cArr = new char[i66];
                                            int i69 = 0;
                                            while (i38 < i68) {
                                                byte b = bArr[i38];
                                                if (zzhk.zzd(b)) {
                                                    i38++;
                                                    cArr[i69] = (char) b;
                                                    i69++;
                                                } else {
                                                    while (i38 < i68) {
                                                        int i70 = i38 + 1;
                                                        byte b2 = bArr[i38];
                                                        if (zzhk.zzd(b2)) {
                                                            cArr[i69] = (char) b2;
                                                            i38 = i70;
                                                            i69++;
                                                            while (i38 < i68) {
                                                                byte b3 = bArr[i38];
                                                                if (zzhk.zzd(b3)) {
                                                                    i38++;
                                                                    cArr[i69] = (char) b3;
                                                                    i69++;
                                                                }
                                                            }
                                                        } else if (b2 < -32) {
                                                            if (i70 >= i68) {
                                                                throw zzfb.zzb();
                                                            }
                                                            zzhk.zzc(b2, bArr[i70], cArr, i69);
                                                            i38 = i70 + 1;
                                                            i69++;
                                                        } else if (b2 < -16) {
                                                            if (i70 >= i68 - 1) {
                                                                throw zzfb.zzb();
                                                            }
                                                            int i71 = i70 + 1;
                                                            zzhk.zzb(b2, bArr[i70], bArr[i71], cArr, i69);
                                                            i38 = i71 + 1;
                                                            i69++;
                                                        } else {
                                                            if (i70 >= i68 - 2) {
                                                                throw zzfb.zzb();
                                                            }
                                                            int i72 = i70 + 1;
                                                            byte b4 = bArr[i70];
                                                            int i73 = i72 + 1;
                                                            zzhk.zza(b2, b4, bArr[i72], bArr[i73], cArr, i69);
                                                            i69 += 2;
                                                            i38 = i73 + 1;
                                                        }
                                                    }
                                                    i18 = 0;
                                                    zzdtVar3.zzc = new String(cArr, 0, i69);
                                                    i38 = i68;
                                                }
                                            }
                                            while (i38 < i68) {
                                            }
                                            i18 = 0;
                                            zzdtVar3.zzc = new String(cArr, 0, i69);
                                            i38 = i68;
                                        }
                                    } else {
                                        i18 = 0;
                                        i38 = zzdu.zzh(bArr, i64, zzdtVar3);
                                        int i74 = zzdtVar3.zza;
                                        if (i74 < 0) {
                                            throw zzfb.zzc();
                                        }
                                        if (i74 == 0) {
                                            zzdtVar3.zzc = "";
                                        } else {
                                            zzdtVar3.zzc = new String(bArr, i38, i74, zzfa.zzb);
                                            i38 += i74;
                                        }
                                    }
                                    unsafe8.putObject(obj2, j, zzdtVar3.zzc);
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i65;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = i18;
                                } else {
                                    i7 = i64;
                                    i14 = 0;
                                    i17 = i49;
                                    zzm = i65;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i542222222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i542222222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 9:
                                int i75 = zzm;
                                if (i47 == 2) {
                                    Object zzt = zzgaVar.zzt(obj2, i75);
                                    i38 = zzdu.zzm(zzt, zzgaVar.zzr(i75), bArr, i7, i2, zzdtVar);
                                    zzgaVar.zzB(obj2, i75, zzt);
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i75;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i17 = i49;
                                    zzm = i75;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i14 = 0;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i5422222222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i5422222222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 10:
                                int i76 = zzm;
                                if (i47 == 2) {
                                    i38 = zzdu.zza(bArr, i7, zzdtVar3);
                                    unsafe8.putObject(obj2, j, zzdtVar3.zzc);
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i76;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i17 = i49;
                                    zzm = i76;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i14 = 0;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i54222222222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i54222222222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 12:
                                int i77 = zzm;
                                if (i47 == 0) {
                                    i38 = zzdu.zzh(bArr, i7, zzdtVar3);
                                    int i78 = zzdtVar3.zza;
                                    zzey zzq = zzgaVar.zzq(i77);
                                    if ((i16 & Integer.MIN_VALUE) == 0 || zzq == null || zzq.zza()) {
                                        unsafe8.putInt(obj2, j, i78);
                                        i41 = i10 | i51;
                                        i35 = i2;
                                        i36 = i3;
                                        i40 = i49;
                                        i39 = i77;
                                        i42 = i46;
                                        i43 = i11;
                                        i37 = 0;
                                    } else {
                                        zzc(obj).zzh(i49, Long.valueOf(i78));
                                        i35 = i2;
                                        i36 = i3;
                                        i40 = i49;
                                        i39 = i77;
                                        i42 = i46;
                                        i41 = i10;
                                        i43 = i11;
                                        i37 = 0;
                                    }
                                } else {
                                    i17 = i49;
                                    zzm = i77;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i14 = 0;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i542222222222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i542222222222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 15:
                                int i79 = zzm;
                                if (i47 == 0) {
                                    i38 = zzdu.zzh(bArr, i7, zzdtVar3);
                                    unsafe8.putInt(obj2, j, zzej.zzb(zzdtVar3.zza));
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i40 = i49;
                                    i39 = i79;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i17 = i49;
                                    zzm = i79;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i14 = 0;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i5422222222222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i5422222222222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            case 16:
                                if (i47 == 0) {
                                    int zzk2 = zzdu.zzk(bArr, i7, zzdtVar3);
                                    unsafe8.putLong(obj, j, zzej.zzc(zzdtVar3.zzb));
                                    i41 = i10 | i51;
                                    i35 = i2;
                                    i36 = i3;
                                    i38 = zzk2;
                                    i40 = i49;
                                    i39 = zzm;
                                    i42 = i46;
                                    i43 = i11;
                                    i37 = 0;
                                } else {
                                    i17 = i49;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i14 = 0;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i54222222222222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i54222222222222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                            default:
                                int i80 = zzm;
                                if (i47 == 3) {
                                    Object zzt2 = zzgaVar.zzt(obj2, i80);
                                    i39 = i80;
                                    int zzl = zzdu.zzl(zzt2, zzgaVar.zzr(i80), bArr, i7, i2, (i46 << 3) | 4, zzdtVar);
                                    zzgaVar.zzB(obj2, i39, zzt2);
                                    i36 = i3;
                                    zzdtVar3 = zzdtVar3;
                                    unsafe8 = unsafe8;
                                    i35 = i2;
                                    i38 = zzl;
                                    i37 = 0;
                                    i43 = i11;
                                    i41 = i10 | i51;
                                    i42 = i46;
                                    i40 = i49;
                                } else {
                                    i14 = 0;
                                    i17 = i49;
                                    zzm = i80;
                                    unsafe3 = unsafe8;
                                    i12 = i46;
                                    i13 = zzm;
                                    unsafe2 = unsafe3;
                                    i9 = i17;
                                    i8 = i7;
                                    i4 = i3;
                                    i15 = i9;
                                    if (i15 != i4) {
                                    }
                                    int i542222222222222 = i12;
                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                    i35 = i2;
                                    i39 = i13;
                                    i40 = i15;
                                    i42 = i542222222222222;
                                    unsafe8 = unsafe2;
                                    i37 = i14;
                                    i41 = i10;
                                    i43 = i11;
                                    zzdtVar3 = zzdtVar;
                                    i36 = i4;
                                }
                                break;
                        }
                    } else {
                        int i81 = zzm;
                        i11 = i43;
                        int i82 = i49;
                        i14 = 0;
                        int i83 = i41;
                        if (zzn != 27) {
                            if (zzn <= 49) {
                                long j2 = i48;
                                zzez zzezVar2 = (zzez) zzb.getObject(obj2, j);
                                if (zzezVar2.zzc()) {
                                    unsafe5 = unsafe8;
                                    zzezVar = zzezVar2;
                                } else {
                                    int size = zzezVar2.size();
                                    if (size == 0) {
                                        unsafe5 = unsafe8;
                                        i32 = 10;
                                    } else {
                                        unsafe5 = unsafe8;
                                        i32 = size + size;
                                    }
                                    zzez zzd = zzezVar2.zzd(i32);
                                    zzb.putObject(obj2, j, zzd);
                                    zzezVar = zzd;
                                }
                                switch (zzn) {
                                    case 18:
                                    case 35:
                                        i23 = i7;
                                        i35 = i2;
                                        i24 = i46;
                                        i13 = i81;
                                        i25 = i82;
                                        i10 = i83;
                                        unsafe6 = unsafe5;
                                        if (i47 == 2) {
                                            zzek zzekVar = (zzek) zzezVar;
                                            i38 = zzdu.zzh(bArr, i23, zzdtVar3);
                                            int i84 = zzdtVar3.zza + i38;
                                            while (i38 < i84) {
                                                zzekVar.zze(Double.longBitsToDouble(zzdu.zzn(bArr, i38)));
                                                i38 += 8;
                                            }
                                            if (i38 != i84) {
                                                throw zzfb.zzf();
                                            }
                                        } else {
                                            if (i47 == 1) {
                                                zzek zzekVar2 = (zzek) zzezVar;
                                                zzekVar2.zze(Double.longBitsToDouble(zzdu.zzn(bArr, i23)));
                                                i38 = i23 + 8;
                                                while (i38 < i35) {
                                                    int zzh2 = zzdu.zzh(bArr, i38, zzdtVar3);
                                                    if (i25 == zzdtVar3.zza) {
                                                        zzekVar2.zze(Double.longBitsToDouble(zzdu.zzn(bArr, zzh2)));
                                                        i38 = zzh2 + 8;
                                                    }
                                                }
                                            }
                                            i38 = i23;
                                        }
                                        if (i38 != i23) {
                                            i36 = i3;
                                            i40 = i25;
                                            i42 = i24;
                                            i39 = i13;
                                            unsafe8 = unsafe6;
                                            i37 = 0;
                                            i41 = i10;
                                            i43 = i11;
                                            obj2 = obj;
                                        } else {
                                            i8 = i38;
                                            i9 = i25;
                                            i12 = i24;
                                            unsafe2 = unsafe6;
                                            obj2 = obj;
                                            i4 = i3;
                                            i15 = i9;
                                            if (i15 != i4) {
                                            }
                                            int i5422222222222222 = i12;
                                            i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                            i35 = i2;
                                            i39 = i13;
                                            i40 = i15;
                                            i42 = i5422222222222222;
                                            unsafe8 = unsafe2;
                                            i37 = i14;
                                            i41 = i10;
                                            i43 = i11;
                                            zzdtVar3 = zzdtVar;
                                            i36 = i4;
                                        }
                                        break;
                                    case 19:
                                    case 36:
                                        i23 = i7;
                                        i35 = i2;
                                        i24 = i46;
                                        i13 = i81;
                                        i25 = i82;
                                        i10 = i83;
                                        unsafe6 = unsafe5;
                                        if (i47 == 2) {
                                            zzer zzerVar = (zzer) zzezVar;
                                            i38 = zzdu.zzh(bArr, i23, zzdtVar3);
                                            int i85 = zzdtVar3.zza + i38;
                                            while (i38 < i85) {
                                                zzerVar.zze(Float.intBitsToFloat(zzdu.zzb(bArr, i38)));
                                                i38 += 4;
                                            }
                                            if (i38 != i85) {
                                                throw zzfb.zzf();
                                            }
                                        } else {
                                            if (i47 == 5) {
                                                zzer zzerVar2 = (zzer) zzezVar;
                                                zzerVar2.zze(Float.intBitsToFloat(zzdu.zzb(bArr, i23)));
                                                i38 = i23 + 4;
                                                while (i38 < i35) {
                                                    int zzh3 = zzdu.zzh(bArr, i38, zzdtVar3);
                                                    if (i25 == zzdtVar3.zza) {
                                                        zzerVar2.zze(Float.intBitsToFloat(zzdu.zzb(bArr, zzh3)));
                                                        i38 = zzh3 + 4;
                                                    }
                                                }
                                            }
                                            i38 = i23;
                                        }
                                        if (i38 != i23) {
                                        }
                                        break;
                                    case 20:
                                    case 21:
                                    case 37:
                                    case 38:
                                        i23 = i7;
                                        i35 = i2;
                                        i24 = i46;
                                        i13 = i81;
                                        i25 = i82;
                                        i10 = i83;
                                        unsafe6 = unsafe5;
                                        if (i47 == 2) {
                                            zzfm zzfmVar = (zzfm) zzezVar;
                                            i38 = zzdu.zzh(bArr, i23, zzdtVar3);
                                            int i86 = zzdtVar3.zza + i38;
                                            while (i38 < i86) {
                                                i38 = zzdu.zzk(bArr, i38, zzdtVar3);
                                                zzfmVar.zze(zzdtVar3.zzb);
                                            }
                                            if (i38 != i86) {
                                                throw zzfb.zzf();
                                            }
                                        } else {
                                            if (i47 == 0) {
                                                zzfm zzfmVar2 = (zzfm) zzezVar;
                                                i38 = zzdu.zzk(bArr, i23, zzdtVar3);
                                                zzfmVar2.zze(zzdtVar3.zzb);
                                                while (i38 < i35) {
                                                    int zzh4 = zzdu.zzh(bArr, i38, zzdtVar3);
                                                    if (i25 == zzdtVar3.zza) {
                                                        i38 = zzdu.zzk(bArr, zzh4, zzdtVar3);
                                                        zzfmVar2.zze(zzdtVar3.zzb);
                                                    }
                                                }
                                            }
                                            i38 = i23;
                                        }
                                        if (i38 != i23) {
                                        }
                                        break;
                                    case 22:
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                                    case 39:
                                    case 43:
                                        i23 = i7;
                                        i25 = i82;
                                        i10 = i83;
                                        Unsafe unsafe9 = unsafe5;
                                        if (i47 == 2) {
                                            unsafe6 = unsafe9;
                                            i38 = zzdu.zzf(bArr, i23, zzezVar, zzdtVar3);
                                            i24 = i46;
                                            i13 = i81;
                                            i35 = i2;
                                        } else if (i47 == 0) {
                                            unsafe6 = unsafe9;
                                            i24 = i46;
                                            i13 = i81;
                                            i35 = i2;
                                            i38 = zzdu.zzj(i25, bArr, i23, i2, zzezVar, zzdtVar);
                                        } else {
                                            unsafe6 = unsafe9;
                                            i24 = i46;
                                            i13 = i81;
                                            i35 = i2;
                                            i38 = i23;
                                        }
                                        if (i38 != i23) {
                                        }
                                        break;
                                    case 23:
                                    case 32:
                                    case 40:
                                    case 46:
                                        i23 = i7;
                                        i26 = i46;
                                        i27 = i81;
                                        i25 = i82;
                                        i10 = i83;
                                        unsafe7 = unsafe5;
                                        if (i47 == 2) {
                                            zzfm zzfmVar3 = (zzfm) zzezVar;
                                            int zzh5 = zzdu.zzh(bArr, i23, zzdtVar3);
                                            int i87 = zzdtVar3.zza + zzh5;
                                            while (zzh5 < i87) {
                                                zzfmVar3.zze(zzdu.zzn(bArr, zzh5));
                                                zzh5 += 8;
                                            }
                                            if (zzh5 != i87) {
                                                throw zzfb.zzf();
                                            }
                                            unsafe6 = unsafe7;
                                            i38 = zzh5;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else if (i47 == 1) {
                                            zzfm zzfmVar4 = (zzfm) zzezVar;
                                            zzfmVar4.zze(zzdu.zzn(bArr, i23));
                                            i28 = i23 + 8;
                                            while (i28 < i2) {
                                                int zzh6 = zzdu.zzh(bArr, i28, zzdtVar3);
                                                if (i25 == zzdtVar3.zza) {
                                                    zzfmVar4.zze(zzdu.zzn(bArr, zzh6));
                                                    i28 = zzh6 + 8;
                                                } else {
                                                    unsafe6 = unsafe7;
                                                    i38 = i28;
                                                    i24 = i26;
                                                    i13 = i27;
                                                    i35 = i2;
                                                    if (i38 != i23) {
                                                    }
                                                }
                                            }
                                            unsafe6 = unsafe7;
                                            i38 = i28;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else {
                                            unsafe6 = unsafe7;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            i38 = i23;
                                            if (i38 != i23) {
                                            }
                                        }
                                        break;
                                    case 24:
                                    case 31:
                                    case 41:
                                    case 45:
                                        i23 = i7;
                                        i26 = i46;
                                        i27 = i81;
                                        i25 = i82;
                                        i10 = i83;
                                        unsafe7 = unsafe5;
                                        if (i47 == 2) {
                                            zzew zzewVar = (zzew) zzezVar;
                                            int zzh7 = zzdu.zzh(bArr, i23, zzdtVar3);
                                            int i88 = zzdtVar3.zza + zzh7;
                                            while (zzh7 < i88) {
                                                zzewVar.zze(zzdu.zzb(bArr, zzh7));
                                                zzh7 += 4;
                                            }
                                            if (zzh7 != i88) {
                                                throw zzfb.zzf();
                                            }
                                            unsafe6 = unsafe7;
                                            i38 = zzh7;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else if (i47 == 5) {
                                            zzew zzewVar2 = (zzew) zzezVar;
                                            zzewVar2.zze(zzdu.zzb(bArr, i23));
                                            i28 = i23 + 4;
                                            while (i28 < i2) {
                                                int zzh8 = zzdu.zzh(bArr, i28, zzdtVar3);
                                                if (i25 == zzdtVar3.zza) {
                                                    zzewVar2.zze(zzdu.zzb(bArr, zzh8));
                                                    i28 = zzh8 + 4;
                                                } else {
                                                    unsafe6 = unsafe7;
                                                    i38 = i28;
                                                    i24 = i26;
                                                    i13 = i27;
                                                    i35 = i2;
                                                    if (i38 != i23) {
                                                    }
                                                }
                                            }
                                            unsafe6 = unsafe7;
                                            i38 = i28;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else {
                                            unsafe6 = unsafe7;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            i38 = i23;
                                            if (i38 != i23) {
                                            }
                                        }
                                        break;
                                    case 25:
                                    case 42:
                                        i23 = i7;
                                        i26 = i46;
                                        i27 = i81;
                                        i25 = i82;
                                        i10 = i83;
                                        unsafe7 = unsafe5;
                                        if (i47 == 2) {
                                            zzdv zzdvVar = (zzdv) zzezVar;
                                            int zzh9 = zzdu.zzh(bArr, i23, zzdtVar3);
                                            int i89 = zzdtVar3.zza + zzh9;
                                            while (zzh9 < i89) {
                                                zzh9 = zzdu.zzk(bArr, zzh9, zzdtVar3);
                                                zzdvVar.zze(zzdtVar3.zzb != 0);
                                            }
                                            if (zzh9 != i89) {
                                                throw zzfb.zzf();
                                            }
                                            unsafe6 = unsafe7;
                                            i38 = zzh9;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else if (i47 == 0) {
                                            zzdv zzdvVar2 = (zzdv) zzezVar;
                                            i28 = zzdu.zzk(bArr, i23, zzdtVar3);
                                            zzdvVar2.zze(zzdtVar3.zzb != 0);
                                            while (i28 < i2) {
                                                int zzh10 = zzdu.zzh(bArr, i28, zzdtVar3);
                                                if (i25 == zzdtVar3.zza) {
                                                    i28 = zzdu.zzk(bArr, zzh10, zzdtVar3);
                                                    zzdvVar2.zze(zzdtVar3.zzb != 0);
                                                } else {
                                                    unsafe6 = unsafe7;
                                                    i38 = i28;
                                                    i24 = i26;
                                                    i13 = i27;
                                                    i35 = i2;
                                                    if (i38 != i23) {
                                                    }
                                                }
                                            }
                                            unsafe6 = unsafe7;
                                            i38 = i28;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else {
                                            unsafe6 = unsafe7;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            i38 = i23;
                                            if (i38 != i23) {
                                            }
                                        }
                                        break;
                                    case 26:
                                        i23 = i7;
                                        i26 = i46;
                                        i27 = i81;
                                        i25 = i82;
                                        i10 = i83;
                                        unsafe7 = unsafe5;
                                        if (i47 == 2) {
                                            if ((j2 & 536870912) == 0) {
                                                i28 = zzdu.zzh(bArr, i23, zzdtVar3);
                                                int i90 = zzdtVar3.zza;
                                                if (i90 < 0) {
                                                    throw zzfb.zzc();
                                                }
                                                if (i90 == 0) {
                                                    zzezVar.add("");
                                                } else {
                                                    zzezVar.add(new String(bArr, i28, i90, zzfa.zzb));
                                                    i28 += i90;
                                                }
                                                while (i28 < i2) {
                                                    int zzh11 = zzdu.zzh(bArr, i28, zzdtVar3);
                                                    if (i25 == zzdtVar3.zza) {
                                                        i28 = zzdu.zzh(bArr, zzh11, zzdtVar3);
                                                        int i91 = zzdtVar3.zza;
                                                        if (i91 < 0) {
                                                            throw zzfb.zzc();
                                                        }
                                                        if (i91 == 0) {
                                                            zzezVar.add("");
                                                        } else {
                                                            zzezVar.add(new String(bArr, i28, i91, zzfa.zzb));
                                                            i28 += i91;
                                                        }
                                                    }
                                                }
                                            } else {
                                                i28 = zzdu.zzh(bArr, i23, zzdtVar3);
                                                int i92 = zzdtVar3.zza;
                                                if (i92 < 0) {
                                                    throw zzfb.zzc();
                                                }
                                                if (i92 == 0) {
                                                    zzezVar.add("");
                                                } else {
                                                    int i93 = i28 + i92;
                                                    if (!zzhn.zzc(bArr, i28, i93)) {
                                                        throw zzfb.zzb();
                                                    }
                                                    zzezVar.add(new String(bArr, i28, i92, zzfa.zzb));
                                                    i28 = i93;
                                                }
                                                while (i28 < i2) {
                                                    int zzh12 = zzdu.zzh(bArr, i28, zzdtVar3);
                                                    if (i25 == zzdtVar3.zza) {
                                                        i28 = zzdu.zzh(bArr, zzh12, zzdtVar3);
                                                        int i94 = zzdtVar3.zza;
                                                        if (i94 < 0) {
                                                            throw zzfb.zzc();
                                                        }
                                                        if (i94 == 0) {
                                                            zzezVar.add("");
                                                        } else {
                                                            int i95 = i28 + i94;
                                                            if (!zzhn.zzc(bArr, i28, i95)) {
                                                                throw zzfb.zzb();
                                                            }
                                                            zzezVar.add(new String(bArr, i28, i94, zzfa.zzb));
                                                            i28 = i95;
                                                        }
                                                    }
                                                }
                                            }
                                            unsafe6 = unsafe7;
                                            i38 = i28;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else {
                                            unsafe6 = unsafe7;
                                            i24 = i26;
                                            i13 = i27;
                                            i35 = i2;
                                            i38 = i23;
                                            if (i38 != i23) {
                                            }
                                        }
                                        break;
                                    case 27:
                                        i23 = i7;
                                        i13 = i81;
                                        i10 = i83;
                                        if (i47 == 2) {
                                            zzgaVar = this;
                                            i25 = i82;
                                            int zze = zzdu.zze(zzgaVar.zzr(i13), i82, bArr, i23, i2, zzezVar, zzdtVar);
                                            unsafe6 = unsafe5;
                                            zzdtVar3 = zzdtVar3;
                                            i13 = i13;
                                            i35 = i2;
                                            i38 = zze;
                                            i24 = i46;
                                            if (i38 != i23) {
                                            }
                                        } else {
                                            zzgaVar = this;
                                            i25 = i82;
                                            unsafe6 = unsafe5;
                                            i24 = i46;
                                            i35 = i2;
                                            i38 = i23;
                                            if (i38 != i23) {
                                            }
                                        }
                                        break;
                                    case 28:
                                        i23 = i7;
                                        i29 = i2;
                                        i13 = i81;
                                        i10 = i83;
                                        if (i47 == 2) {
                                            int zzh13 = zzdu.zzh(bArr, i23, zzdtVar3);
                                            int i96 = zzdtVar3.zza;
                                            if (i96 < 0) {
                                                throw zzfb.zzc();
                                            }
                                            if (i96 > bArr.length - zzh13) {
                                                throw zzfb.zzf();
                                            }
                                            if (i96 == 0) {
                                                zzezVar.add(zzef.zzb);
                                            } else {
                                                zzezVar.add(zzef.zzk(bArr, zzh13, i96));
                                                zzh13 += i96;
                                            }
                                            while (zzh13 < i29) {
                                                int zzh14 = zzdu.zzh(bArr, zzh13, zzdtVar3);
                                                if (i82 == zzdtVar3.zza) {
                                                    zzh13 = zzdu.zzh(bArr, zzh14, zzdtVar3);
                                                    int i97 = zzdtVar3.zza;
                                                    if (i97 < 0) {
                                                        throw zzfb.zzc();
                                                    }
                                                    if (i97 > bArr.length - zzh13) {
                                                        throw zzfb.zzf();
                                                    }
                                                    if (i97 == 0) {
                                                        zzezVar.add(zzef.zzb);
                                                    } else {
                                                        zzezVar.add(zzef.zzk(bArr, zzh13, i97));
                                                        zzh13 += i97;
                                                    }
                                                } else {
                                                    i38 = zzh13;
                                                    i25 = i82;
                                                    zzgaVar = this;
                                                    i35 = i29;
                                                    i24 = i46;
                                                    unsafe6 = unsafe5;
                                                    if (i38 != i23) {
                                                    }
                                                }
                                            }
                                            i38 = zzh13;
                                            i25 = i82;
                                            zzgaVar = this;
                                            i35 = i29;
                                            i24 = i46;
                                            unsafe6 = unsafe5;
                                            if (i38 != i23) {
                                            }
                                        } else {
                                            zzgaVar = this;
                                            i25 = i82;
                                            i35 = i29;
                                            i24 = i46;
                                            unsafe6 = unsafe5;
                                            i38 = i23;
                                            if (i38 != i23) {
                                            }
                                        }
                                        break;
                                    case 30:
                                    case 44:
                                        if (i47 == 2) {
                                            zzj = zzdu.zzf(bArr, i7, zzezVar, zzdtVar3);
                                            i23 = i7;
                                            i29 = i2;
                                            i13 = i81;
                                            i10 = i83;
                                        } else if (i47 == 0) {
                                            i23 = i7;
                                            i13 = i81;
                                            i10 = i83;
                                            i29 = i2;
                                            zzj = zzdu.zzj(i82, bArr, i23, i2, zzezVar, zzdtVar);
                                        } else {
                                            i23 = i7;
                                            i13 = i81;
                                            i10 = i83;
                                            zzgaVar = this;
                                            i25 = i82;
                                            i35 = i2;
                                            i24 = i46;
                                            unsafe6 = unsafe5;
                                            i38 = i23;
                                            if (i38 != i23) {
                                            }
                                        }
                                        zzey zzq2 = zzgaVar.zzq(i13);
                                        zzgz zzgzVar = zzgaVar.zzl;
                                        int i98 = zzgk.zza;
                                        if (zzq2 == null) {
                                            i30 = zzj;
                                        } else if (zzezVar instanceof RandomAccess) {
                                            int size2 = zzezVar.size();
                                            i30 = zzj;
                                            Object obj3 = null;
                                            int i99 = 0;
                                            for (int i100 = 0; i100 < size2; i100++) {
                                                int intValue = ((Integer) zzezVar.get(i100)).intValue();
                                                if (zzq2.zza()) {
                                                    if (i100 != i99) {
                                                        zzezVar.set(i99, Integer.valueOf(intValue));
                                                    }
                                                    i99++;
                                                } else {
                                                    obj3 = zzgk.zzc(obj2, i46, intValue, obj3, zzgzVar);
                                                }
                                            }
                                            if (i99 != size2) {
                                                zzezVar.subList(i99, size2).clear();
                                            }
                                        } else {
                                            i30 = zzj;
                                            Iterator it = zzezVar.iterator();
                                            Object obj4 = null;
                                            while (it.hasNext()) {
                                                int intValue2 = ((Integer) it.next()).intValue();
                                                if (!zzq2.zza()) {
                                                    obj4 = zzgk.zzc(obj2, i46, intValue2, obj4, zzgzVar);
                                                    it.remove();
                                                }
                                            }
                                        }
                                        zzgaVar = this;
                                        i38 = i30;
                                        i25 = i82;
                                        i35 = i29;
                                        i24 = i46;
                                        unsafe6 = unsafe5;
                                        if (i38 != i23) {
                                        }
                                        break;
                                    case 33:
                                    case 47:
                                        i31 = i82;
                                        if (i47 == 2) {
                                            zzew zzewVar3 = (zzew) zzezVar;
                                            int zzh15 = zzdu.zzh(bArr, i7, zzdtVar3);
                                            int i101 = zzdtVar3.zza + zzh15;
                                            while (zzh15 < i101) {
                                                zzh15 = zzdu.zzh(bArr, zzh15, zzdtVar3);
                                                zzewVar3.zze(zzej.zzb(zzdtVar3.zza));
                                            }
                                            if (zzh15 != i101) {
                                                throw zzfb.zzf();
                                            }
                                            i23 = i7;
                                            i38 = zzh15;
                                            i24 = i46;
                                            i25 = i31;
                                            i13 = i81;
                                            i10 = i83;
                                            unsafe6 = unsafe5;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else if (i47 == 0) {
                                            zzew zzewVar4 = (zzew) zzezVar;
                                            zzh = zzdu.zzh(bArr, i7, zzdtVar3);
                                            zzewVar4.zze(zzej.zzb(zzdtVar3.zza));
                                            while (zzh < i2) {
                                                int zzh16 = zzdu.zzh(bArr, zzh, zzdtVar3);
                                                if (i31 == zzdtVar3.zza) {
                                                    zzh = zzdu.zzh(bArr, zzh16, zzdtVar3);
                                                    zzewVar4.zze(zzej.zzb(zzdtVar3.zza));
                                                } else {
                                                    i23 = i7;
                                                    i38 = zzh;
                                                    i24 = i46;
                                                    i25 = i31;
                                                    i13 = i81;
                                                    i10 = i83;
                                                    unsafe6 = unsafe5;
                                                    i35 = i2;
                                                    if (i38 != i23) {
                                                    }
                                                }
                                            }
                                            i23 = i7;
                                            i38 = zzh;
                                            i24 = i46;
                                            i25 = i31;
                                            i13 = i81;
                                            i10 = i83;
                                            unsafe6 = unsafe5;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else {
                                            i23 = i7;
                                            i24 = i46;
                                            i25 = i31;
                                            i13 = i81;
                                            i10 = i83;
                                            unsafe6 = unsafe5;
                                            i35 = i2;
                                            i38 = i23;
                                            if (i38 != i23) {
                                            }
                                        }
                                        break;
                                    case 34:
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                                        if (i47 == 2) {
                                            zzfm zzfmVar5 = (zzfm) zzezVar;
                                            int zzh17 = zzdu.zzh(bArr, i7, zzdtVar3);
                                            int i102 = zzdtVar3.zza + zzh17;
                                            while (zzh17 < i102) {
                                                zzh17 = zzdu.zzk(bArr, zzh17, zzdtVar3);
                                                zzfmVar5.zze(zzej.zzc(zzdtVar3.zzb));
                                            }
                                            if (zzh17 != i102) {
                                                throw zzfb.zzf();
                                            }
                                            i23 = i7;
                                            i38 = zzh17;
                                            i35 = i2;
                                            i24 = i46;
                                            i13 = i81;
                                            i25 = i82;
                                            i10 = i83;
                                            unsafe6 = unsafe5;
                                            if (i38 != i23) {
                                            }
                                        } else if (i47 == 0) {
                                            zzfm zzfmVar6 = (zzfm) zzezVar;
                                            zzh = zzdu.zzk(bArr, i7, zzdtVar3);
                                            zzfmVar6.zze(zzej.zzc(zzdtVar3.zzb));
                                            while (true) {
                                                if (zzh < i2) {
                                                    int zzh18 = zzdu.zzh(bArr, zzh, zzdtVar3);
                                                    i31 = i82;
                                                    if (i31 == zzdtVar3.zza) {
                                                        zzh = zzdu.zzk(bArr, zzh18, zzdtVar3);
                                                        zzfmVar6.zze(zzej.zzc(zzdtVar3.zzb));
                                                        i82 = i31;
                                                    }
                                                } else {
                                                    i31 = i82;
                                                }
                                            }
                                            i23 = i7;
                                            i38 = zzh;
                                            i24 = i46;
                                            i25 = i31;
                                            i13 = i81;
                                            i10 = i83;
                                            unsafe6 = unsafe5;
                                            i35 = i2;
                                            if (i38 != i23) {
                                            }
                                        } else {
                                            i23 = i7;
                                            i24 = i46;
                                            i25 = i82;
                                            i13 = i81;
                                            i10 = i83;
                                            unsafe6 = unsafe5;
                                            i35 = i2;
                                            i38 = i23;
                                            if (i38 != i23) {
                                            }
                                        }
                                        break;
                                    default:
                                        i23 = i7;
                                        i35 = i2;
                                        i24 = i46;
                                        i13 = i81;
                                        i25 = i82;
                                        i10 = i83;
                                        unsafe6 = unsafe5;
                                        if (i47 == 3) {
                                            zzgi zzr = zzgaVar.zzr(i13);
                                            int i103 = (i25 & (-8)) | 4;
                                            i38 = zzdu.zzc(zzr, bArr, i23, i2, i103, zzdtVar);
                                            zzezVar.add(zzdtVar3.zzc);
                                            while (i38 < i35) {
                                                int zzh19 = zzdu.zzh(bArr, i38, zzdtVar3);
                                                if (i25 == zzdtVar3.zza) {
                                                    i38 = zzdu.zzc(zzr, bArr, zzh19, i2, i103, zzdtVar);
                                                    zzezVar.add(zzdtVar3.zzc);
                                                } else if (i38 != i23) {
                                                }
                                            }
                                            if (i38 != i23) {
                                            }
                                        }
                                        i38 = i23;
                                        if (i38 != i23) {
                                        }
                                        break;
                                }
                            } else {
                                i10 = i83;
                                Unsafe unsafe10 = unsafe8;
                                i19 = i7;
                                i20 = i81;
                                i22 = i46;
                                unsafe4 = unsafe10;
                                if (zzn != 50) {
                                    obj2 = obj;
                                    unsafe2 = unsafe4;
                                    Unsafe unsafe11 = zzb;
                                    long j3 = iArr[i20 + 2] & 1048575;
                                    switch (zzn) {
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                            i13 = i20;
                                            i12 = i22;
                                            i9 = i82;
                                            i33 = i19;
                                            zzdtVar2 = zzdtVar;
                                            if (i47 == 1) {
                                                unsafe11.putObject(obj2, j, Double.valueOf(Double.longBitsToDouble(zzdu.zzn(bArr, i33))));
                                                i34 = i33 + 8;
                                                unsafe11.putInt(obj2, j3, i12);
                                                if (i34 != i33) {
                                                    i35 = i2;
                                                    i36 = i3;
                                                    i38 = i34;
                                                    i42 = i12;
                                                    zzdtVar3 = zzdtVar2;
                                                    i39 = i13;
                                                    i37 = 0;
                                                    i41 = i10;
                                                    unsafe8 = unsafe2;
                                                    i43 = i11;
                                                    i40 = i9;
                                                } else {
                                                    i8 = i34;
                                                    i4 = i3;
                                                    i15 = i9;
                                                    if (i15 != i4) {
                                                    }
                                                    int i54222222222222222 = i12;
                                                    i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                                                    i35 = i2;
                                                    i39 = i13;
                                                    i40 = i15;
                                                    i42 = i54222222222222222;
                                                    unsafe8 = unsafe2;
                                                    i37 = i14;
                                                    i41 = i10;
                                                    i43 = i11;
                                                    zzdtVar3 = zzdtVar;
                                                    i36 = i4;
                                                }
                                            }
                                            i34 = i33;
                                            if (i34 != i33) {
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                                            i13 = i20;
                                            i12 = i22;
                                            i9 = i82;
                                            i33 = i19;
                                            zzdtVar2 = zzdtVar;
                                            if (i47 == 5) {
                                                unsafe11.putObject(obj2, j, Float.valueOf(Float.intBitsToFloat(zzdu.zzb(bArr, i33))));
                                                i34 = i33 + 4;
                                                unsafe11.putInt(obj2, j3, i12);
                                                if (i34 != i33) {
                                                }
                                            }
                                            i34 = i33;
                                            if (i34 != i33) {
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                                            i13 = i20;
                                            i12 = i22;
                                            i9 = i82;
                                            i33 = i19;
                                            zzdtVar2 = zzdtVar;
                                            if (i47 == 0) {
                                                int zzk3 = zzdu.zzk(bArr, i33, zzdtVar2);
                                                unsafe11.putObject(obj2, j, Long.valueOf(zzdtVar2.zzb));
                                                unsafe11.putInt(obj2, j3, i12);
                                                i34 = zzk3;
                                                if (i34 != i33) {
                                                }
                                            }
                                            i34 = i33;
                                            if (i34 != i33) {
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                                        case 62:
                                            i13 = i20;
                                            i12 = i22;
                                            i9 = i82;
                                            i33 = i19;
                                            zzdtVar2 = zzdtVar;
                                            if (i47 == 0) {
                                                i34 = zzdu.zzh(bArr, i33, zzdtVar2);
                                                unsafe11.putObject(obj2, j, Integer.valueOf(zzdtVar2.zza));
                                                unsafe11.putInt(obj2, j3, i12);
                                                if (i34 != i33) {
                                                }
                                            }
                                            i34 = i33;
                                            if (i34 != i33) {
                                            }
                                            break;
                                        case 56:
                                        case 65:
                                            i13 = i20;
                                            i12 = i22;
                                            i9 = i82;
                                            i33 = i19;
                                            zzdtVar2 = zzdtVar;
                                            if (i47 == 1) {
                                                unsafe11.putObject(obj2, j, Long.valueOf(zzdu.zzn(bArr, i33)));
                                                i34 = i33 + 8;
                                                unsafe11.putInt(obj2, j3, i12);
                                                if (i34 != i33) {
                                                }
                                            }
                                            i34 = i33;
                                            if (i34 != i33) {
                                            }
                                            break;
                                        case 57:
                                        case 64:
                                            i13 = i20;
                                            i12 = i22;
                                            i9 = i82;
                                            i33 = i19;
                                            zzdtVar2 = zzdtVar;
                                            if (i47 == 5) {
                                                unsafe11.putObject(obj2, j, Integer.valueOf(zzdu.zzb(bArr, i33)));
                                                i34 = i33 + 4;
                                                unsafe11.putInt(obj2, j3, i12);
                                                if (i34 != i33) {
                                                }
                                            }
                                            i34 = i33;
                                            if (i34 != i33) {
                                            }
                                            break;
                                        case 58:
                                            i13 = i20;
                                            i12 = i22;
                                            i9 = i82;
                                            i33 = i19;
                                            zzdtVar2 = zzdtVar;
                                            if (i47 == 0) {
                                                int zzk4 = zzdu.zzk(bArr, i33, zzdtVar2);
                                                unsafe11.putObject(obj2, j, Boolean.valueOf(zzdtVar2.zzb != 0));
                                                unsafe11.putInt(obj2, j3, i12);
                                                i34 = zzk4;
                                                if (i34 != i33) {
                                                }
                                            }
                                            i34 = i33;
                                            if (i34 != i33) {
                                            }
                                            break;
                                        case 59:
                                            i13 = i20;
                                            i12 = i22;
                                            i9 = i82;
                                            i33 = i19;
                                            zzdtVar2 = zzdtVar;
                                            if (i47 == 2) {
                                                i34 = zzdu.zzh(bArr, i33, zzdtVar2);
                                                int i104 = zzdtVar2.zza;
                                                if (i104 == 0) {
                                                    unsafe11.putObject(obj2, j, "");
                                                    unsafe2 = unsafe2;
                                                } else {
                                                    if ((i48 & 536870912) != 0 && !zzhn.zzc(bArr, i34, i34 + i104)) {
                                                        throw zzfb.zzb();
                                                    }
                                                    unsafe2 = unsafe2;
                                                    unsafe11.putObject(obj2, j, new String(bArr, i34, i104, zzfa.zzb));
                                                    i34 += i104;
                                                }
                                                unsafe11.putInt(obj2, j3, i12);
                                                if (i34 != i33) {
                                                }
                                            } else {
                                                unsafe2 = unsafe2;
                                                i34 = i33;
                                                if (i34 != i33) {
                                                }
                                            }
                                            break;
                                        case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                                            if (i47 == 2) {
                                                Object zzu = zzgaVar.zzu(obj2, i22, i20);
                                                i13 = i20;
                                                i9 = i82;
                                                int zzm2 = zzdu.zzm(zzu, zzgaVar.zzr(i20), bArr, i19, i2, zzdtVar);
                                                zzgaVar.zzC(obj2, i22, i13, zzu);
                                                i34 = zzm2;
                                                i33 = i19;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                if (i34 != i33) {
                                                }
                                            } else {
                                                i13 = i20;
                                                i9 = i82;
                                                i33 = i19;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                i34 = i33;
                                                if (i34 != i33) {
                                                }
                                            }
                                            break;
                                        case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                                            if (i47 == 2) {
                                                i34 = zzdu.zza(bArr, i19, zzdtVar);
                                                unsafe11.putObject(obj2, j, zzdtVar.zzc);
                                                unsafe11.putInt(obj2, j3, i22);
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                if (i34 != i33) {
                                                }
                                            } else {
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                i34 = i33;
                                                if (i34 != i33) {
                                                }
                                            }
                                            break;
                                        case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                                            if (i47 == 0) {
                                                i34 = zzdu.zzh(bArr, i19, zzdtVar);
                                                int i105 = zzdtVar.zza;
                                                zzey zzq3 = zzgaVar.zzq(i20);
                                                if (zzq3 == null || zzq3.zza()) {
                                                    unsafe11.putObject(obj2, j, Integer.valueOf(i105));
                                                    unsafe11.putInt(obj2, j3, i22);
                                                } else {
                                                    zzc(obj).zzh(i82, Long.valueOf(i105));
                                                }
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                if (i34 != i33) {
                                                }
                                            } else {
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                i34 = i33;
                                                if (i34 != i33) {
                                                }
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                                            if (i47 == 0) {
                                                i34 = zzdu.zzh(bArr, i19, zzdtVar);
                                                unsafe11.putObject(obj2, j, Integer.valueOf(zzej.zzb(zzdtVar.zza)));
                                                unsafe11.putInt(obj2, j3, i22);
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                if (i34 != i33) {
                                                }
                                            } else {
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                i34 = i33;
                                                if (i34 != i33) {
                                                }
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                                            if (i47 == 0) {
                                                i34 = zzdu.zzk(bArr, i19, zzdtVar);
                                                unsafe11.putObject(obj2, j, Long.valueOf(zzej.zzc(zzdtVar.zzb)));
                                                unsafe11.putInt(obj2, j3, i22);
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                if (i34 != i33) {
                                                }
                                            } else {
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                i34 = i33;
                                                if (i34 != i33) {
                                                }
                                            }
                                            break;
                                        case 68:
                                            if (i47 == 3) {
                                                Object zzu2 = zzgaVar.zzu(obj2, i22, i20);
                                                int zzl2 = zzdu.zzl(zzu2, zzgaVar.zzr(i20), bArr, i19, i2, (i82 & (-8)) | 4, zzdtVar);
                                                zzgaVar.zzC(obj2, i22, i20, zzu2);
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                i34 = zzl2;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                if (i34 != i33) {
                                                }
                                            } else {
                                                i13 = i20;
                                                i33 = i19;
                                                i9 = i82;
                                                zzdtVar2 = zzdtVar;
                                                i12 = i22;
                                                i34 = i33;
                                                if (i34 != i33) {
                                                }
                                            }
                                            break;
                                        default:
                                            i13 = i20;
                                            i12 = i22;
                                            i9 = i82;
                                            i33 = i19;
                                            zzdtVar2 = zzdtVar;
                                            i34 = i33;
                                            if (i34 != i33) {
                                            }
                                            break;
                                    }
                                } else {
                                    if (i47 == 2) {
                                        Unsafe unsafe12 = zzb;
                                        Object zzs = zzgaVar.zzs(i20);
                                        Object object = unsafe12.getObject(obj, j);
                                        if (!((zzfr) object).zze()) {
                                            zzfr zzb2 = zzfr.zza().zzb();
                                            zzfs.zza(zzb2, object);
                                            unsafe12.putObject(obj, j, zzb2);
                                        }
                                        throw null;
                                    }
                                    i21 = i82;
                                    obj2 = obj;
                                    i13 = i20;
                                    i12 = i22;
                                    i9 = i21;
                                    unsafe2 = unsafe4;
                                    i8 = i19;
                                }
                            }
                        } else if (i47 == 2) {
                            zzez zzezVar3 = (zzez) unsafe8.getObject(obj2, j);
                            if (!zzezVar3.zzc()) {
                                int size3 = zzezVar3.size();
                                zzezVar3 = zzezVar3.zzd(size3 == 0 ? 10 : size3 + size3);
                                unsafe8.putObject(obj2, j, zzezVar3);
                            }
                            zzez zzezVar4 = zzezVar3;
                            i42 = i46;
                            i38 = zzdu.zze(zzgaVar.zzr(i81), i82, bArr, i7, i2, zzezVar4, zzdtVar);
                            i36 = i3;
                            unsafe8 = unsafe8;
                            zzdtVar3 = zzdtVar3;
                            i39 = i81;
                            i35 = i2;
                            i37 = 0;
                            i40 = i82;
                            i41 = i83;
                            i43 = i11;
                        } else {
                            Unsafe unsafe13 = unsafe8;
                            i19 = i7;
                            unsafe4 = unsafe13;
                            i20 = i81;
                            i21 = i82;
                            i10 = i83;
                            i22 = i46;
                            i13 = i20;
                            i12 = i22;
                            i9 = i21;
                            unsafe2 = unsafe4;
                            i8 = i19;
                        }
                    }
                } else {
                    i8 = i7;
                    i9 = i6;
                    i10 = i41;
                    i11 = i43;
                    i12 = i46;
                    i13 = i37;
                    i14 = i13;
                    unsafe2 = unsafe8;
                }
                i4 = i3;
                i15 = i9;
                if (i15 != i4) {
                }
                int i542222222222222222 = i12;
                i38 = zzdu.zzg(i15, bArr, i8, i2, zzc(obj), zzdtVar);
                i35 = i2;
                i39 = i13;
                i40 = i15;
                i42 = i542222222222222222;
                unsafe8 = unsafe2;
                i37 = i14;
                i41 = i10;
                i43 = i11;
                zzdtVar3 = zzdtVar;
                i36 = i4;
            } else {
                i4 = i36;
                unsafe = unsafe8;
                i5 = 1048575;
            }
        }
    }

    @Override // com.google.android.gms.internal.auth.zzgi
    public final Object zzd() {
        return ((zzev) this.zzg).zzc();
    }

    @Override // com.google.android.gms.internal.auth.zzgi
    public final void zze(Object obj) {
        if (zzH(obj)) {
            if (obj instanceof zzev) {
                zzev zzevVar = (zzev) obj;
                zzevVar.zzl(Integer.MAX_VALUE);
                zzevVar.zza = 0;
                zzevVar.zzj();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzo = zzo(i);
                long j = 1048575 & zzo;
                switch (zzn(zzo)) {
                    case 9:
                    case 17:
                        if (zzE(obj, i)) {
                            zzr(i).zze(zzb.getObject(obj, j));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    case 49:
                        this.zzk.zza(obj, j);
                        break;
                    case 50:
                        Object object = zzb.getObject(obj, j);
                        if (object != null) {
                            Unsafe unsafe = zzb;
                            ((zzfr) object).zzc();
                            unsafe.putObject(obj, j, object);
                            break;
                        } else {
                            break;
                        }
                    case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    case 68:
                        if (zzI(obj, this.zzc[i], i)) {
                            zzr(i).zze(zzb.getObject(obj, j));
                            break;
                        } else {
                            break;
                        }
                }
            }
            this.zzl.zze(obj);
        }
    }

    @Override // com.google.android.gms.internal.auth.zzgi
    public final void zzf(Object obj, Object obj2) {
        zzw(obj);
        if (obj2 == null) {
            throw null;
        }
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzo = zzo(i);
            int i2 = this.zzc[i];
            long j = 1048575 & zzo;
            switch (zzn(zzo)) {
                case 0:
                    if (zzE(obj2, i)) {
                        zzhj.zzl(obj, j, zzhj.zza(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzE(obj2, i)) {
                        zzhj.zzm(obj, j, zzhj.zzb(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzE(obj2, i)) {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzE(obj2, i)) {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzE(obj2, i)) {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzE(obj2, i)) {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzE(obj2, i)) {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzE(obj2, i)) {
                        zzhj.zzk(obj, j, zzhj.zzt(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzE(obj2, i)) {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzx(obj, obj2, i);
                    break;
                case 10:
                    if (zzE(obj2, i)) {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzE(obj2, i)) {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzE(obj2, i)) {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzE(obj2, i)) {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzE(obj2, i)) {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzE(obj2, i)) {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzE(obj2, i)) {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzx(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case 49:
                    this.zzk.zzb(obj, obj2, j);
                    break;
                case 50:
                    int i3 = zzgk.zza;
                    zzhj.zzp(obj, j, zzfs.zza(zzhj.zzf(obj, j), zzhj.zzf(obj2, j)));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzI(obj2, i2, i)) {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzA(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    zzy(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzI(obj2, i2, i)) {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzA(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzy(obj, obj2, i);
                    break;
            }
        }
        zzgk.zzd(this.zzl, obj, obj2);
    }

    @Override // com.google.android.gms.internal.auth.zzgi
    public final void zzg(Object obj, byte[] bArr, int i, int i2, zzdt zzdtVar) throws IOException {
        zzb(obj, bArr, i, i2, 0, zzdtVar);
    }

    @Override // com.google.android.gms.internal.auth.zzgi
    public final boolean zzh(Object obj, Object obj2) {
        boolean zzf;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzo = zzo(i);
            long j = zzo & 1048575;
            switch (zzn(zzo)) {
                case 0:
                    if (zzD(obj, obj2, i) && Double.doubleToLongBits(zzhj.zza(obj, j)) == Double.doubleToLongBits(zzhj.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzD(obj, obj2, i) && Float.floatToIntBits(zzhj.zzb(obj, j)) == Float.floatToIntBits(zzhj.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzD(obj, obj2, i) && zzhj.zzt(obj, j) == zzhj.zzt(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case 49:
                    zzf = zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j));
                    break;
                case 50:
                    zzf = zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                case 56:
                case 57:
                case 58:
                case 59:
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                case 68:
                    long zzl = zzl(i) & 1048575;
                    if (zzhj.zzc(obj, zzl) == zzhj.zzc(obj2, zzl) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzf) {
                return false;
            }
        }
        return this.zzl.zzb(obj).equals(this.zzl.zzb(obj2));
    }

    @Override // com.google.android.gms.internal.auth.zzgi
    public final boolean zzi(Object obj) {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.zzi) {
            int i6 = this.zzh[i4];
            int i7 = this.zzc[i6];
            int zzo = zzo(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 == i5) {
                i = i5;
                i2 = i3;
            } else if (i9 != 1048575) {
                i2 = zzb.getInt(obj, i9);
                i = i9;
            } else {
                i2 = i3;
                i = i9;
            }
            if ((268435456 & zzo) != 0 && !zzF(obj, i6, i, i2, i10)) {
                return false;
            }
            switch (zzn(zzo)) {
                case 9:
                case 17:
                    if (zzF(obj, i6, i, i2, i10) && !zzG(obj, zzo, zzr(i6))) {
                        return false;
                    }
                    break;
                case 27:
                case 49:
                    List list = (List) zzhj.zzf(obj, zzo & 1048575);
                    if (!list.isEmpty()) {
                        zzgi zzr = zzr(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!zzr.zzi(list.get(i11))) {
                                return false;
                            }
                        }
                        break;
                    } else {
                        continue;
                    }
                case 50:
                    if (!((zzfr) zzhj.zzf(obj, zzo & 1048575)).isEmpty()) {
                        throw null;
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case 68:
                    if (zzI(obj, i7, i6) && !zzG(obj, zzo, zzr(i6))) {
                        return false;
                    }
                    break;
            }
            i4++;
            i5 = i;
            i3 = i2;
        }
        return true;
    }
}
