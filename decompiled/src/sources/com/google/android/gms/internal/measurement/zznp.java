package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import kotlin.jvm.internal.CharCompanionObject;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
final class zznp<T> implements zznx<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzop.zzq();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zznm zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzoi zzl;
    private final zzls zzm;

    private zznp(int[] iArr, Object[] objArr, int i, int i2, zznm zznmVar, boolean z, int[] iArr2, int i3, int i4, zznr zznrVar, zzmy zzmyVar, zzoi zzoiVar, zzls zzlsVar, zznh zznhVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        boolean z2 = false;
        if (zzlsVar != null && (zznmVar instanceof zzmc)) {
            z2 = true;
        }
        this.zzh = z2;
        this.zzi = iArr2;
        this.zzj = i3;
        this.zzk = i4;
        this.zzl = zzoiVar;
        this.zzm = zzlsVar;
        this.zzg = zznmVar;
    }

    private static boolean zzA(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzmf) {
            return ((zzmf) obj).zzcf();
        }
        return true;
    }

    private static void zzB(Object obj) {
        if (zzA(obj)) {
            return;
        }
        String valueOf = String.valueOf(obj);
        String.valueOf(valueOf);
        throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(valueOf)));
    }

    private static double zzC(Object obj, long j) {
        return ((Double) zzop.zzn(obj, j)).doubleValue();
    }

    private static float zzD(Object obj, long j) {
        return ((Float) zzop.zzn(obj, j)).floatValue();
    }

    private static int zzE(Object obj, long j) {
        return ((Integer) zzop.zzn(obj, j)).intValue();
    }

    private static long zzF(Object obj, long j) {
        return ((Long) zzop.zzn(obj, j)).longValue();
    }

    private static boolean zzG(Object obj, long j) {
        return ((Boolean) zzop.zzn(obj, j)).booleanValue();
    }

    private final boolean zzH(Object obj, Object obj2, int i) {
        return zzJ(obj, i) == zzJ(obj2, i);
    }

    private final boolean zzI(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzJ(obj, i) : (i3 & i4) != 0;
    }

    private final boolean zzJ(Object obj, int i) {
        int zzy = zzy(i);
        long j = zzy & 1048575;
        if (j != 1048575) {
            return (zzop.zzd(obj, j) & (1 << (zzy >>> 20))) != 0;
        }
        int zzx = zzx(i);
        long j2 = zzx & 1048575;
        switch (zzz(zzx)) {
            case 0:
                return Double.doubleToRawLongBits(zzop.zzl(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzop.zzj(obj, j2)) != 0;
            case 2:
                return zzop.zzf(obj, j2) != 0;
            case 3:
                return zzop.zzf(obj, j2) != 0;
            case 4:
                return zzop.zzd(obj, j2) != 0;
            case 5:
                return zzop.zzf(obj, j2) != 0;
            case 6:
                return zzop.zzd(obj, j2) != 0;
            case 7:
                return zzop.zzh(obj, j2);
            case 8:
                Object zzn = zzop.zzn(obj, j2);
                if (zzn instanceof String) {
                    return !((String) zzn).isEmpty();
                }
                if (zzn instanceof zzlh) {
                    return !zzlh.zzb.equals(zzn);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzop.zzn(obj, j2) != null;
            case 10:
                return !zzlh.zzb.equals(zzop.zzn(obj, j2));
            case 11:
                return zzop.zzd(obj, j2) != 0;
            case 12:
                return zzop.zzd(obj, j2) != 0;
            case 13:
                return zzop.zzd(obj, j2) != 0;
            case 14:
                return zzop.zzf(obj, j2) != 0;
            case 15:
                return zzop.zzd(obj, j2) != 0;
            case 16:
                return zzop.zzf(obj, j2) != 0;
            case 17:
                return zzop.zzn(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final void zzK(Object obj, int i) {
        int zzy = zzy(i);
        long j = 1048575 & zzy;
        if (j == 1048575) {
            return;
        }
        zzop.zze(obj, j, (1 << (zzy >>> 20)) | zzop.zzd(obj, j));
    }

    private final boolean zzL(Object obj, int i, int i2) {
        return zzop.zzd(obj, (long) (zzy(i2) & 1048575)) == i;
    }

    private final void zzM(Object obj, int i, int i2) {
        zzop.zze(obj, zzy(i2) & 1048575, i);
    }

    private final int zzN(int i, int i2) {
        int[] iArr = this.zzc;
        int length = (iArr.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = iArr[i4];
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

    private static final int zzO(byte[] bArr, int i, int i2, zzot zzotVar, Class cls, zzkw zzkwVar) throws IOException {
        zzot zzotVar2 = zzot.DOUBLE;
        switch (zzotVar) {
            case DOUBLE:
                int i3 = i + 8;
                zzkwVar.zzc = Double.valueOf(Double.longBitsToDouble(zzkx.zze(bArr, i)));
                return i3;
            case FLOAT:
                int i4 = i + 4;
                zzkwVar.zzc = Float.valueOf(Float.intBitsToFloat(zzkx.zzd(bArr, i)));
                return i4;
            case INT64:
            case UINT64:
                int zzc = zzkx.zzc(bArr, i, zzkwVar);
                zzkwVar.zzc = Long.valueOf(zzkwVar.zzb);
                return zzc;
            case INT32:
            case UINT32:
            case ENUM:
                int zza2 = zzkx.zza(bArr, i, zzkwVar);
                zzkwVar.zzc = Integer.valueOf(zzkwVar.zza);
                return zza2;
            case FIXED64:
            case SFIXED64:
                int i5 = i + 8;
                zzkwVar.zzc = Long.valueOf(zzkx.zze(bArr, i));
                return i5;
            case FIXED32:
            case SFIXED32:
                int i6 = i + 4;
                zzkwVar.zzc = Integer.valueOf(zzkx.zzd(bArr, i));
                return i6;
            case BOOL:
                int zzc2 = zzkx.zzc(bArr, i, zzkwVar);
                zzkwVar.zzc = Boolean.valueOf(zzkwVar.zzb != 0);
                return zzc2;
            case STRING:
                return zzkx.zzf(bArr, i, zzkwVar);
            case GROUP:
            default:
                throw new RuntimeException("unsupported field type.");
            case MESSAGE:
                return zzkx.zzh(zznu.zza().zzb(cls), bArr, i, i2, zzkwVar);
            case BYTES:
                return zzkx.zzg(bArr, i, zzkwVar);
            case SINT32:
                int zza3 = zzkx.zza(bArr, i, zzkwVar);
                zzkwVar.zzc = Integer.valueOf(zzlj.zzb(zzkwVar.zza));
                return zza3;
            case SINT64:
                int zzc3 = zzkx.zzc(bArr, i, zzkwVar);
                zzkwVar.zzc = Long.valueOf(zzlj.zzc(zzkwVar.zzb));
                return zzc3;
        }
    }

    private static final void zzP(int i, Object obj, zzov zzovVar) throws IOException {
        if (obj instanceof String) {
            zzovVar.zzm(i, (String) obj);
        } else {
            zzovVar.zzn(i, (zzlh) obj);
        }
    }

    static zzoj zzg(Object obj) {
        zzmf zzmfVar = (zzmf) obj;
        zzoj zzojVar = zzmfVar.zzc;
        if (zzojVar != zzoj.zza()) {
            return zzojVar;
        }
        zzoj zzb2 = zzoj.zzb();
        zzmfVar.zzc = zzb2;
        return zzb2;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0366  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static zznp zzl(Class cls, zznj zznjVar, zznr zznrVar, zzmy zzmyVar, zzoi zzoiVar, zzls zzlsVar, zznh zznhVar) {
        int i;
        int charAt;
        int charAt2;
        int i2;
        int i3;
        int i4;
        int[] iArr;
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
        zznw zznwVar;
        int i18;
        int objectFieldOffset;
        int i19;
        int i20;
        String str;
        int i21;
        int i22;
        Field zzm;
        int i23;
        char charAt11;
        int i24;
        int i25;
        Field zzm2;
        Field zzm3;
        int i26;
        char charAt12;
        int i27;
        char charAt13;
        int i28;
        char charAt14;
        int i29;
        char charAt15;
        if (!(zznjVar instanceof zznw)) {
            throw null;
        }
        zznw zznwVar2 = (zznw) zznjVar;
        String zzd = zznwVar2.zzd();
        int length = zzd.length();
        char c = 55296;
        if (zzd.charAt(0) >= 55296) {
            int i30 = 1;
            while (true) {
                i = i30 + 1;
                if (zzd.charAt(i30) < 55296) {
                    break;
                }
                i30 = i;
            }
        } else {
            i = 1;
        }
        int i31 = i + 1;
        int charAt16 = zzd.charAt(i);
        if (charAt16 >= 55296) {
            int i32 = charAt16 & 8191;
            int i33 = 13;
            while (true) {
                i29 = i31 + 1;
                charAt15 = zzd.charAt(i31);
                if (charAt15 < 55296) {
                    break;
                }
                i32 |= (charAt15 & 8191) << i33;
                i33 += 13;
                i31 = i29;
            }
            charAt16 = i32 | (charAt15 << i33);
            i31 = i29;
        }
        if (charAt16 == 0) {
            i4 = 0;
            charAt = 0;
            charAt2 = 0;
            i2 = 0;
            i5 = 0;
            i3 = 0;
            iArr = zza;
            i6 = 0;
        } else {
            int i34 = i31 + 1;
            int charAt17 = zzd.charAt(i31);
            if (charAt17 >= 55296) {
                int i35 = charAt17 & 8191;
                int i36 = 13;
                while (true) {
                    i14 = i34 + 1;
                    charAt10 = zzd.charAt(i34);
                    if (charAt10 < 55296) {
                        break;
                    }
                    i35 |= (charAt10 & 8191) << i36;
                    i36 += 13;
                    i34 = i14;
                }
                charAt17 = i35 | (charAt10 << i36);
                i34 = i14;
            }
            int i37 = i34 + 1;
            int charAt18 = zzd.charAt(i34);
            if (charAt18 >= 55296) {
                int i38 = charAt18 & 8191;
                int i39 = 13;
                while (true) {
                    i13 = i37 + 1;
                    charAt9 = zzd.charAt(i37);
                    if (charAt9 < 55296) {
                        break;
                    }
                    i38 |= (charAt9 & 8191) << i39;
                    i39 += 13;
                    i37 = i13;
                }
                charAt18 = i38 | (charAt9 << i39);
                i37 = i13;
            }
            int i40 = i37 + 1;
            int charAt19 = zzd.charAt(i37);
            if (charAt19 >= 55296) {
                int i41 = charAt19 & 8191;
                int i42 = 13;
                while (true) {
                    i12 = i40 + 1;
                    charAt8 = zzd.charAt(i40);
                    if (charAt8 < 55296) {
                        break;
                    }
                    i41 |= (charAt8 & 8191) << i42;
                    i42 += 13;
                    i40 = i12;
                }
                charAt19 = i41 | (charAt8 << i42);
                i40 = i12;
            }
            int i43 = i40 + 1;
            int charAt20 = zzd.charAt(i40);
            if (charAt20 >= 55296) {
                int i44 = charAt20 & 8191;
                int i45 = 13;
                while (true) {
                    i11 = i43 + 1;
                    charAt7 = zzd.charAt(i43);
                    if (charAt7 < 55296) {
                        break;
                    }
                    i44 |= (charAt7 & 8191) << i45;
                    i45 += 13;
                    i43 = i11;
                }
                charAt20 = i44 | (charAt7 << i45);
                i43 = i11;
            }
            int i46 = i43 + 1;
            charAt = zzd.charAt(i43);
            if (charAt >= 55296) {
                int i47 = charAt & 8191;
                int i48 = 13;
                while (true) {
                    i10 = i46 + 1;
                    charAt6 = zzd.charAt(i46);
                    if (charAt6 < 55296) {
                        break;
                    }
                    i47 |= (charAt6 & 8191) << i48;
                    i48 += 13;
                    i46 = i10;
                }
                charAt = i47 | (charAt6 << i48);
                i46 = i10;
            }
            int i49 = i46 + 1;
            charAt2 = zzd.charAt(i46);
            if (charAt2 >= 55296) {
                int i50 = charAt2 & 8191;
                int i51 = 13;
                while (true) {
                    i9 = i49 + 1;
                    charAt5 = zzd.charAt(i49);
                    if (charAt5 < 55296) {
                        break;
                    }
                    i50 |= (charAt5 & 8191) << i51;
                    i51 += 13;
                    i49 = i9;
                }
                charAt2 = i50 | (charAt5 << i51);
                i49 = i9;
            }
            int i52 = i49 + 1;
            int charAt21 = zzd.charAt(i49);
            if (charAt21 >= 55296) {
                int i53 = charAt21 & 8191;
                int i54 = 13;
                while (true) {
                    i8 = i52 + 1;
                    charAt4 = zzd.charAt(i52);
                    if (charAt4 < 55296) {
                        break;
                    }
                    i53 |= (charAt4 & 8191) << i54;
                    i54 += 13;
                    i52 = i8;
                }
                charAt21 = i53 | (charAt4 << i54);
                i52 = i8;
            }
            int i55 = i52 + 1;
            int charAt22 = zzd.charAt(i52);
            if (charAt22 >= 55296) {
                int i56 = charAt22 & 8191;
                int i57 = 13;
                while (true) {
                    i7 = i55 + 1;
                    charAt3 = zzd.charAt(i55);
                    if (charAt3 < 55296) {
                        break;
                    }
                    i56 |= (charAt3 & 8191) << i57;
                    i57 += 13;
                    i55 = i7;
                }
                charAt22 = i56 | (charAt3 << i57);
                i55 = i7;
            }
            int i58 = charAt17 + charAt17 + charAt18;
            int[] iArr2 = new int[charAt22 + charAt2 + charAt21];
            i2 = charAt19;
            i3 = charAt22;
            i4 = i58;
            iArr = iArr2;
            i5 = charAt20;
            i6 = charAt17;
            i31 = i55;
        }
        Unsafe unsafe = zzb;
        Object[] zze = zznwVar2.zze();
        Class<?> cls2 = zznwVar2.zzb().getClass();
        int i59 = i3 + charAt2;
        int i60 = charAt + charAt;
        int[] iArr3 = new int[charAt * 3];
        Object[] objArr = new Object[i60];
        int i61 = i3;
        int i62 = i59;
        int i63 = 0;
        int i64 = 0;
        while (i31 < length) {
            int i65 = i31 + 1;
            int charAt23 = zzd.charAt(i31);
            if (charAt23 >= c) {
                int i66 = charAt23 & 8191;
                int i67 = i65;
                int i68 = 13;
                while (true) {
                    i28 = i67 + 1;
                    charAt14 = zzd.charAt(i67);
                    if (charAt14 < c) {
                        break;
                    }
                    i66 |= (charAt14 & 8191) << i68;
                    i68 += 13;
                    i67 = i28;
                }
                charAt23 = i66 | (charAt14 << i68);
                i15 = i28;
            } else {
                i15 = i65;
            }
            int i69 = i15 + 1;
            int charAt24 = zzd.charAt(i15);
            if (charAt24 >= c) {
                int i70 = charAt24 & 8191;
                int i71 = i69;
                int i72 = 13;
                while (true) {
                    i27 = i71 + 1;
                    charAt13 = zzd.charAt(i71);
                    if (charAt13 < c) {
                        break;
                    }
                    i70 |= (charAt13 & 8191) << i72;
                    i72 += 13;
                    i71 = i27;
                }
                charAt24 = i70 | (charAt13 << i72);
                i16 = i27;
            } else {
                i16 = i69;
            }
            if ((charAt24 & 1024) != 0) {
                iArr[i63] = i64;
                i63++;
            }
            int i73 = charAt24 & 255;
            int i74 = length;
            int i75 = charAt24 & 2048;
            int i76 = i5;
            if (i73 >= 51) {
                int i77 = i16 + 1;
                int charAt25 = zzd.charAt(i16);
                if (charAt25 >= 55296) {
                    int i78 = charAt25 & 8191;
                    int i79 = i77;
                    int i80 = 13;
                    while (true) {
                        i26 = i79 + 1;
                        charAt12 = zzd.charAt(i79);
                        i17 = i2;
                        if (charAt12 < 55296) {
                            break;
                        }
                        i78 |= (charAt12 & 8191) << i80;
                        i80 += 13;
                        i79 = i26;
                        i2 = i17;
                    }
                    charAt25 = i78 | (charAt12 << i80);
                    i25 = i26;
                } else {
                    i17 = i2;
                    i25 = i77;
                }
                int i81 = i73 - 51;
                int i82 = i25;
                if (i81 == 9 || i81 == 17) {
                    int i83 = i64 / 3;
                    objArr[i83 + i83 + 1] = zze[i4];
                    i4++;
                } else if (i81 == 12) {
                    if (zznwVar2.zzc() == 1 || i75 != 0) {
                        int i84 = i64 / 3;
                        objArr[i84 + i84 + 1] = zze[i4];
                        i4++;
                    } else {
                        i75 = 0;
                    }
                }
                int i85 = charAt25 + charAt25;
                Object obj = zze[i85];
                if (obj instanceof Field) {
                    zzm2 = (Field) obj;
                } else {
                    zzm2 = zzm(cls2, (String) obj);
                    zze[i85] = zzm2;
                }
                int objectFieldOffset2 = (int) unsafe.objectFieldOffset(zzm2);
                int i86 = i85 + 1;
                Object obj2 = zze[i86];
                int i87 = i75;
                if (obj2 instanceof Field) {
                    zzm3 = (Field) obj2;
                } else {
                    zzm3 = zzm(cls2, (String) obj2);
                    zze[i86] = zzm3;
                }
                str = zzd;
                objectFieldOffset = objectFieldOffset2;
                i19 = (int) unsafe.objectFieldOffset(zzm3);
                i20 = i4;
                i21 = i82;
                i75 = i87;
                i22 = 0;
                zznwVar = zznwVar2;
            } else {
                i17 = i2;
                int i88 = i4 + 1;
                Field zzm4 = zzm(cls2, (String) zze[i4]);
                if (i73 == 9) {
                    zznwVar = zznwVar2;
                } else if (i73 == 17) {
                    zznwVar = zznwVar2;
                } else {
                    if (i73 == 27) {
                        zznwVar = zznwVar2;
                        i24 = i88 + 1;
                    } else if (i73 == 49) {
                        i24 = i88 + 1;
                        zznwVar = zznwVar2;
                    } else {
                        if (i73 == 12 || i73 == 30 || i73 == 44) {
                            zznwVar = zznwVar2;
                            if (zznwVar2.zzc() == 1 || i75 != 0) {
                                int i89 = i64 / 3;
                                objArr[i89 + i89 + 1] = zze[i88];
                                i88++;
                            } else {
                                i75 = 0;
                            }
                        } else if (i73 == 50) {
                            int i90 = i88 + 1;
                            int i91 = i61 + 1;
                            iArr[i61] = i64;
                            int i92 = i64 / 3;
                            int i93 = i92 + i92;
                            objArr[i93] = zze[i88];
                            if (i75 != 0) {
                                i88 = i90 + 1;
                                objArr[i93 + 1] = zze[i90];
                                i61 = i91;
                            } else {
                                i88 = i90;
                                i61 = i91;
                                i75 = 0;
                            }
                            zznwVar = zznwVar2;
                        } else {
                            zznwVar = zznwVar2;
                        }
                        i18 = i88;
                        objectFieldOffset = (int) unsafe.objectFieldOffset(zzm4);
                        i19 = 1048575;
                        if ((charAt24 & 4096) != 0 || i73 > 17) {
                            i20 = i18;
                            str = zzd;
                            i21 = i16;
                            i22 = 0;
                        } else {
                            i21 = i16 + 1;
                            int charAt26 = zzd.charAt(i16);
                            if (charAt26 >= 55296) {
                                int i94 = charAt26 & 8191;
                                int i95 = 13;
                                while (true) {
                                    i23 = i21 + 1;
                                    charAt11 = zzd.charAt(i21);
                                    if (charAt11 < 55296) {
                                        break;
                                    }
                                    i94 |= (charAt11 & 8191) << i95;
                                    i95 += 13;
                                    i21 = i23;
                                }
                                charAt26 = i94 | (charAt11 << i95);
                                i21 = i23;
                            }
                            int i96 = i6 + i6 + (charAt26 / 32);
                            Object obj3 = zze[i96];
                            i20 = i18;
                            if (obj3 instanceof Field) {
                                zzm = (Field) obj3;
                            } else {
                                zzm = zzm(cls2, (String) obj3);
                                zze[i96] = zzm;
                            }
                            str = zzd;
                            i22 = charAt26 % 32;
                            i19 = (int) unsafe.objectFieldOffset(zzm);
                        }
                        if (i73 >= 18 && i73 <= 49) {
                            iArr[i62] = objectFieldOffset;
                            i62++;
                        }
                    }
                    int i97 = i64 / 3;
                    objArr[i97 + i97 + 1] = zze[i88];
                    i88 = i24;
                    i18 = i88;
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zzm4);
                    i19 = 1048575;
                    if ((charAt24 & 4096) != 0) {
                    }
                    i20 = i18;
                    str = zzd;
                    i21 = i16;
                    i22 = 0;
                    if (i73 >= 18) {
                        iArr[i62] = objectFieldOffset;
                        i62++;
                    }
                }
                int i98 = i64 / 3;
                objArr[i98 + i98 + 1] = zzm4.getType();
                i18 = i88;
                objectFieldOffset = (int) unsafe.objectFieldOffset(zzm4);
                i19 = 1048575;
                if ((charAt24 & 4096) != 0) {
                }
                i20 = i18;
                str = zzd;
                i21 = i16;
                i22 = 0;
                if (i73 >= 18) {
                }
            }
            int i99 = i64 + 1;
            iArr3[i64] = charAt23;
            int i100 = i99 + 1;
            iArr3[i99] = ((charAt24 & 512) != 0 ? 536870912 : 0) | ((charAt24 & 256) != 0 ? 268435456 : 0) | (i75 != 0 ? Integer.MIN_VALUE : 0) | (i73 << 20) | objectFieldOffset;
            i64 = i100 + 1;
            iArr3[i100] = (i22 << 20) | i19;
            i31 = i21;
            zzd = str;
            length = i74;
            i5 = i76;
            zznwVar2 = zznwVar;
            i4 = i20;
            i2 = i17;
            c = 55296;
        }
        return new zznp(iArr3, objArr, i2, i5, zznwVar2.zzb(), false, iArr, i3, i59, zznrVar, zzmyVar, zzoiVar, zzlsVar, zznhVar);
    }

    private static Field zzm(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(name).length() + 29 + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString(), e);
        }
    }

    private final void zzn(Object obj, Object obj2, int i) {
        if (zzJ(obj2, i)) {
            int zzx = zzx(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = zzx;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                int i2 = this.zzc[i];
                String obj3 = obj2.toString();
                StringBuilder sb = new StringBuilder(String.valueOf(i2).length() + 38 + obj3.length());
                sb.append("Source subfield ");
                sb.append(i2);
                sb.append(" is present but null: ");
                sb.append(obj3);
                throw new IllegalStateException(sb.toString());
            }
            zznx zzp = zzp(i);
            if (!zzJ(obj, i)) {
                if (zzA(object)) {
                    Object zza2 = zzp.zza();
                    zzp.zzd(zza2, object);
                    unsafe.putObject(obj, j, zza2);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzK(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzA(object2)) {
                Object zza3 = zzp.zza();
                zzp.zzd(zza3, object2);
                unsafe.putObject(obj, j, zza3);
                object2 = zza3;
            }
            zzp.zzd(object2, object);
        }
    }

    private final void zzo(Object obj, Object obj2, int i) {
        int[] iArr = this.zzc;
        int i2 = iArr[i];
        if (zzL(obj2, i2, i)) {
            int zzx = zzx(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = zzx;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                int i3 = iArr[i];
                String obj3 = obj2.toString();
                StringBuilder sb = new StringBuilder(String.valueOf(i3).length() + 38 + obj3.length());
                sb.append("Source subfield ");
                sb.append(i3);
                sb.append(" is present but null: ");
                sb.append(obj3);
                throw new IllegalStateException(sb.toString());
            }
            zznx zzp = zzp(i);
            if (!zzL(obj, i2, i)) {
                if (zzA(object)) {
                    Object zza2 = zzp.zza();
                    zzp.zzd(zza2, object);
                    unsafe.putObject(obj, j, zza2);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzM(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzA(object2)) {
                Object zza3 = zzp.zza();
                zzp.zzd(zza3, object2);
                unsafe.putObject(obj, j, zza3);
                object2 = zza3;
            }
            zzp.zzd(object2, object);
        }
    }

    private final zznx zzp(int i) {
        Object[] objArr = this.zzd;
        int i2 = i / 3;
        int i3 = i2 + i2;
        zznx zznxVar = (zznx) objArr[i3];
        if (zznxVar != null) {
            return zznxVar;
        }
        zznx zzb2 = zznu.zza().zzb((Class) objArr[i3 + 1]);
        objArr[i3] = zzb2;
        return zzb2;
    }

    private final Object zzq(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final zzmk zzr(int i) {
        int i2 = i / 3;
        return (zzmk) this.zzd[i2 + i2 + 1];
    }

    private final Object zzs(Object obj, int i) {
        zznx zzp = zzp(i);
        int zzx = zzx(i) & 1048575;
        if (!zzJ(obj, i)) {
            return zzp.zza();
        }
        Object object = zzb.getObject(obj, zzx);
        if (zzA(object)) {
            return object;
        }
        Object zza2 = zzp.zza();
        if (object != null) {
            zzp.zzd(zza2, object);
        }
        return zza2;
    }

    private final void zzt(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzx(i) & 1048575, obj2);
        zzK(obj, i);
    }

    private final Object zzu(Object obj, int i, int i2) {
        zznx zzp = zzp(i2);
        if (!zzL(obj, i, i2)) {
            return zzp.zza();
        }
        Object object = zzb.getObject(obj, zzx(i2) & 1048575);
        if (zzA(object)) {
            return object;
        }
        Object zza2 = zzp.zza();
        if (object != null) {
            zzp.zzd(zza2, object);
        }
        return zza2;
    }

    private final void zzv(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzx(i2) & 1048575, obj2);
        zzM(obj, i, i2);
    }

    private static boolean zzw(Object obj, int i, zznx zznxVar) {
        return zznxVar.zzk(zzop.zzn(obj, i & 1048575));
    }

    private final int zzx(int i) {
        return this.zzc[i + 1];
    }

    private final int zzy(int i) {
        return this.zzc[i + 2];
    }

    private static int zzz(int i) {
        return (i >>> 20) & 255;
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final Object zza() {
        return ((zzmf) this.zzg).zzch();
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final boolean zzb(Object obj, Object obj2) {
        boolean zzB;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzx = zzx(i);
            long j = zzx & 1048575;
            switch (zzz(zzx)) {
                case 0:
                    if (zzH(obj, obj2, i) && Double.doubleToLongBits(zzop.zzl(obj, j)) == Double.doubleToLongBits(zzop.zzl(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzH(obj, obj2, i) && Float.floatToIntBits(zzop.zzj(obj, j)) == Float.floatToIntBits(zzop.zzj(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzH(obj, obj2, i) && zzop.zzf(obj, j) == zzop.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzH(obj, obj2, i) && zzop.zzf(obj, j) == zzop.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzH(obj, obj2, i) && zzop.zzd(obj, j) == zzop.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzH(obj, obj2, i) && zzop.zzf(obj, j) == zzop.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzH(obj, obj2, i) && zzop.zzd(obj, j) == zzop.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzH(obj, obj2, i) && zzop.zzh(obj, j) == zzop.zzh(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzH(obj, obj2, i) && zznz.zzB(zzop.zzn(obj, j), zzop.zzn(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzH(obj, obj2, i) && zznz.zzB(zzop.zzn(obj, j), zzop.zzn(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzH(obj, obj2, i) && zznz.zzB(zzop.zzn(obj, j), zzop.zzn(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzH(obj, obj2, i) && zzop.zzd(obj, j) == zzop.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzH(obj, obj2, i) && zzop.zzd(obj, j) == zzop.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzH(obj, obj2, i) && zzop.zzd(obj, j) == zzop.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzH(obj, obj2, i) && zzop.zzf(obj, j) == zzop.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzH(obj, obj2, i) && zzop.zzd(obj, j) == zzop.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzH(obj, obj2, i) && zzop.zzf(obj, j) == zzop.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzH(obj, obj2, i) && zznz.zzB(zzop.zzn(obj, j), zzop.zzn(obj2, j))) {
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
                    zzB = zznz.zzB(zzop.zzn(obj, j), zzop.zzn(obj2, j));
                    break;
                case 50:
                    zzB = zznz.zzB(zzop.zzn(obj, j), zzop.zzn(obj2, j));
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
                    long zzy = zzy(i) & 1048575;
                    if (zzop.zzd(obj, zzy) == zzop.zzd(obj2, zzy) && zznz.zzB(zzop.zzn(obj, j), zzop.zzn(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzB) {
                return false;
            }
        }
        if (!((zzmf) obj).zzc.equals(((zzmf) obj2).zzc)) {
            return false;
        }
        if (this.zzh) {
            return ((zzmc) obj).zzb.equals(((zzmc) obj2).zzb);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final int zzc(Object obj) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.zzc;
            if (i >= iArr.length) {
                int hashCode = (i2 * 53) + ((zzmf) obj).zzc.hashCode();
                return this.zzh ? (hashCode * 53) + ((zzmc) obj).zzb.zza.hashCode() : hashCode;
            }
            int zzx = zzx(i);
            int i3 = 1048575 & zzx;
            int zzz = zzz(zzx);
            int i4 = iArr[i];
            long j = i3;
            switch (zzz) {
                case 0:
                    long doubleToLongBits = Double.doubleToLongBits(zzop.zzl(obj, j));
                    byte[] bArr = zzmp.zzb;
                    i2 = (i2 * 53) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 1:
                    i2 = (i2 * 53) + Float.floatToIntBits(zzop.zzj(obj, j));
                    break;
                case 2:
                    long zzf = zzop.zzf(obj, j);
                    byte[] bArr2 = zzmp.zzb;
                    i2 = (i2 * 53) + ((int) (zzf ^ (zzf >>> 32)));
                    break;
                case 3:
                    long zzf2 = zzop.zzf(obj, j);
                    byte[] bArr3 = zzmp.zzb;
                    i2 = (i2 * 53) + ((int) (zzf2 ^ (zzf2 >>> 32)));
                    break;
                case 4:
                    i2 = (i2 * 53) + zzop.zzd(obj, j);
                    break;
                case 5:
                    long zzf3 = zzop.zzf(obj, j);
                    byte[] bArr4 = zzmp.zzb;
                    i2 = (i2 * 53) + ((int) (zzf3 ^ (zzf3 >>> 32)));
                    break;
                case 6:
                    i2 = (i2 * 53) + zzop.zzd(obj, j);
                    break;
                case 7:
                    i2 = (i2 * 53) + zzmp.zzb(zzop.zzh(obj, j));
                    break;
                case 8:
                    i2 = (i2 * 53) + ((String) zzop.zzn(obj, j)).hashCode();
                    break;
                case 9:
                    int i5 = i2 * 53;
                    Object zzn = zzop.zzn(obj, j);
                    i2 = i5 + (zzn != null ? zzn.hashCode() : 37);
                    break;
                case 10:
                    i2 = (i2 * 53) + zzop.zzn(obj, j).hashCode();
                    break;
                case 11:
                    i2 = (i2 * 53) + zzop.zzd(obj, j);
                    break;
                case 12:
                    i2 = (i2 * 53) + zzop.zzd(obj, j);
                    break;
                case 13:
                    i2 = (i2 * 53) + zzop.zzd(obj, j);
                    break;
                case 14:
                    long zzf4 = zzop.zzf(obj, j);
                    byte[] bArr5 = zzmp.zzb;
                    i2 = (i2 * 53) + ((int) (zzf4 ^ (zzf4 >>> 32)));
                    break;
                case 15:
                    i2 = (i2 * 53) + zzop.zzd(obj, j);
                    break;
                case 16:
                    long zzf5 = zzop.zzf(obj, j);
                    byte[] bArr6 = zzmp.zzb;
                    i2 = (i2 * 53) + ((int) (zzf5 ^ (zzf5 >>> 32)));
                    break;
                case 17:
                    int i6 = i2 * 53;
                    Object zzn2 = zzop.zzn(obj, j);
                    i2 = i6 + (zzn2 != null ? zzn2.hashCode() : 37);
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
                    i2 = (i2 * 53) + zzop.zzn(obj, j).hashCode();
                    break;
                case 50:
                    i2 = (i2 * 53) + zzop.zzn(obj, j).hashCode();
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        long doubleToLongBits2 = Double.doubleToLongBits(zzC(obj, j));
                        byte[] bArr7 = zzmp.zzb;
                        i2 = (i2 * 53) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + Float.floatToIntBits(zzD(obj, j));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        long zzF = zzF(obj, j);
                        byte[] bArr8 = zzmp.zzb;
                        i2 = (i2 * 53) + ((int) (zzF ^ (zzF >>> 32)));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        long zzF2 = zzF(obj, j);
                        byte[] bArr9 = zzmp.zzb;
                        i2 = (i2 * 53) + ((int) (zzF2 ^ (zzF2 >>> 32)));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzE(obj, j);
                        break;
                    }
                case 56:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        long zzF3 = zzF(obj, j);
                        byte[] bArr10 = zzmp.zzb;
                        i2 = (i2 * 53) + ((int) (zzF3 ^ (zzF3 >>> 32)));
                        break;
                    }
                case 57:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzE(obj, j);
                        break;
                    }
                case 58:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzmp.zzb(zzG(obj, j));
                        break;
                    }
                case 59:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + ((String) zzop.zzn(obj, j)).hashCode();
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzop.zzn(obj, j).hashCode();
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzop.zzn(obj, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzE(obj, j);
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzE(obj, j);
                        break;
                    }
                case 64:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzE(obj, j);
                        break;
                    }
                case 65:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        long zzF4 = zzF(obj, j);
                        byte[] bArr11 = zzmp.zzb;
                        i2 = (i2 * 53) + ((int) (zzF4 ^ (zzF4 >>> 32)));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzE(obj, j);
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        long zzF5 = zzF(obj, j);
                        byte[] bArr12 = zzmp.zzb;
                        i2 = (i2 * 53) + ((int) (zzF5 ^ (zzF5 >>> 32)));
                        break;
                    }
                case 68:
                    if (!zzL(obj, i4, i)) {
                        break;
                    } else {
                        i2 = (i2 * 53) + zzop.zzn(obj, j).hashCode();
                        break;
                    }
            }
            i += 3;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final void zzd(Object obj, Object obj2) {
        zzB(obj);
        if (obj2 == null) {
            throw null;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.zzc;
            if (i >= iArr.length) {
                zznz.zzD(this.zzl, obj, obj2);
                if (this.zzh) {
                    zznz.zzC(this.zzm, obj, obj2);
                    return;
                }
                return;
            }
            int zzx = zzx(i);
            int i2 = 1048575 & zzx;
            int zzz = zzz(zzx);
            int i3 = iArr[i];
            long j = i2;
            switch (zzz) {
                case 0:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzm(obj, j, zzop.zzl(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 1:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzk(obj, j, zzop.zzj(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 2:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzg(obj, j, zzop.zzf(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 3:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzg(obj, j, zzop.zzf(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 4:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zze(obj, j, zzop.zzd(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 5:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzg(obj, j, zzop.zzf(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 6:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zze(obj, j, zzop.zzd(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 7:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzi(obj, j, zzop.zzh(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 8:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzo(obj, j, zzop.zzn(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 9:
                    zzn(obj, obj2, i);
                    break;
                case 10:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzo(obj, j, zzop.zzn(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 11:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zze(obj, j, zzop.zzd(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 12:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zze(obj, j, zzop.zzd(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 13:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zze(obj, j, zzop.zzd(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 14:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzg(obj, j, zzop.zzf(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 15:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zze(obj, j, zzop.zzd(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 16:
                    if (!zzJ(obj2, i)) {
                        break;
                    } else {
                        zzop.zzg(obj, j, zzop.zzf(obj2, j));
                        zzK(obj, i);
                        break;
                    }
                case 17:
                    zzn(obj, obj2, i);
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
                    zzmo zzmoVar = (zzmo) zzop.zzn(obj, j);
                    zzmo zzmoVar2 = (zzmo) zzop.zzn(obj2, j);
                    int size = zzmoVar.size();
                    int size2 = zzmoVar2.size();
                    if (size > 0 && size2 > 0) {
                        if (!zzmoVar.zza()) {
                            zzmoVar = zzmoVar.zzg(size2 + size);
                        }
                        zzmoVar.addAll(zzmoVar2);
                    }
                    if (size > 0) {
                        zzmoVar2 = zzmoVar;
                    }
                    zzop.zzo(obj, j, zzmoVar2);
                    break;
                case 50:
                    int i4 = zznz.zza;
                    zzop.zzo(obj, j, zznh.zza(zzop.zzn(obj, j), zzop.zzn(obj2, j)));
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
                    if (!zzL(obj2, i3, i)) {
                        break;
                    } else {
                        zzop.zzo(obj, j, zzop.zzn(obj2, j));
                        zzM(obj, i3, i);
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    zzo(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (!zzL(obj2, i3, i)) {
                        break;
                    } else {
                        zzop.zzo(obj, j, zzop.zzn(obj2, j));
                        zzM(obj, i3, i);
                        break;
                    }
                case 68:
                    zzo(obj, obj2, i);
                    break;
            }
            i += 3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v119, types: [int] */
    /* JADX WARN: Type inference failed for: r0v120 */
    /* JADX WARN: Type inference failed for: r0v125, types: [int] */
    /* JADX WARN: Type inference failed for: r0v126 */
    /* JADX WARN: Type inference failed for: r0v137, types: [int] */
    /* JADX WARN: Type inference failed for: r0v138 */
    /* JADX WARN: Type inference failed for: r0v140, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v143, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v145, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v150, types: [int] */
    /* JADX WARN: Type inference failed for: r0v151 */
    /* JADX WARN: Type inference failed for: r0v156, types: [int] */
    /* JADX WARN: Type inference failed for: r0v157 */
    /* JADX WARN: Type inference failed for: r0v168, types: [int] */
    /* JADX WARN: Type inference failed for: r0v169 */
    /* JADX WARN: Type inference failed for: r0v174, types: [int] */
    /* JADX WARN: Type inference failed for: r0v175 */
    /* JADX WARN: Type inference failed for: r0v219, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v300, types: [int] */
    /* JADX WARN: Type inference failed for: r0v305 */
    /* JADX WARN: Type inference failed for: r0v306 */
    /* JADX WARN: Type inference failed for: r0v307 */
    /* JADX WARN: Type inference failed for: r0v308 */
    /* JADX WARN: Type inference failed for: r0v309 */
    /* JADX WARN: Type inference failed for: r0v310 */
    /* JADX WARN: Type inference failed for: r0v311 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v185 */
    /* JADX WARN: Type inference failed for: r1v188 */
    /* JADX WARN: Type inference failed for: r1v189 */
    /* JADX WARN: Type inference failed for: r1v191 */
    /* JADX WARN: Type inference failed for: r1v192 */
    /* JADX WARN: Type inference failed for: r1v80 */
    /* JADX WARN: Type inference failed for: r1v82, types: [int] */
    /* JADX WARN: Type inference failed for: r1v83 */
    /* JADX WARN: Type inference failed for: r2v103 */
    /* JADX WARN: Type inference failed for: r2v104 */
    /* JADX WARN: Type inference failed for: r2v105 */
    /* JADX WARN: Type inference failed for: r2v106 */
    /* JADX WARN: Type inference failed for: r2v107 */
    /* JADX WARN: Type inference failed for: r2v108 */
    /* JADX WARN: Type inference failed for: r2v109 */
    /* JADX WARN: Type inference failed for: r2v34, types: [int] */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v47 */
    /* JADX WARN: Type inference failed for: r2v51, types: [int] */
    /* JADX WARN: Type inference failed for: r2v52 */
    /* JADX WARN: Type inference failed for: r2v57 */
    /* JADX WARN: Type inference failed for: r2v58, types: [int] */
    /* JADX WARN: Type inference failed for: r2v85 */
    /* JADX WARN: Type inference failed for: r2v86 */
    /* JADX WARN: Type inference failed for: r2v88, types: [int] */
    /* JADX WARN: Type inference failed for: r2v89 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28, types: [int] */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31, types: [int] */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v40, types: [int] */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v47, types: [int] */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r3v58 */
    /* JADX WARN: Type inference failed for: r3v59 */
    /* JADX WARN: Type inference failed for: r3v60 */
    /* JADX WARN: Type inference failed for: r3v61 */
    /* JADX WARN: Type inference failed for: r3v62 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v30, types: [int] */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v37, types: [int] */
    /* JADX WARN: Type inference failed for: r4v38 */
    /* JADX WARN: Type inference failed for: r4v41 */
    /* JADX WARN: Type inference failed for: r4v42 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [int] */
    @Override // com.google.android.gms.internal.measurement.zznx
    public final int zze(Object obj) {
        int i;
        int i2;
        ?? r5;
        ?? r2;
        int zzB;
        int zzB2;
        ?? r3;
        int zzD;
        ?? r1;
        ?? r4;
        ?? r22;
        Unsafe unsafe = zzb;
        boolean z = false;
        int i3 = 1048575;
        ?? r12 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        while (true) {
            int[] iArr = this.zzc;
            if (i4 >= iArr.length) {
                int zzi = i5 + ((zzmf) obj).zzc.zzi();
                if (!this.zzh) {
                    return zzi;
                }
                zzoe zzoeVar = ((zzmc) obj).zzb.zza;
                int zzc = zzoeVar.zzc();
                int i7 = 0;
                for (int i8 = 0; i8 < zzc; i8++) {
                    Map.Entry zzd = zzoeVar.zzd(i8);
                    i7 += zzlw.zzj((zzlv) ((zzob) zzd).zza(), zzd.getValue());
                }
                for (Map.Entry entry : zzoeVar.zze()) {
                    i7 += zzlw.zzj((zzlv) entry.getKey(), entry.getValue());
                }
                return zzi + i7;
            }
            int zzx = zzx(i4);
            int zzz = zzz(zzx);
            int i9 = iArr[i4];
            int i10 = iArr[i4 + 2];
            int i11 = i10 & i3;
            if (zzz <= 17) {
                if (i11 != i6) {
                    r12 = i11 == i3 ? z : unsafe.getInt(obj, i11);
                    i6 = i11;
                }
                i = i6;
                i2 = r12;
                r5 = 1 << (i10 >>> 20);
            } else {
                i = i6;
                i2 = r12;
                r5 = z;
            }
            int i12 = zzx & i3;
            if (zzz >= zzlx.DOUBLE_LIST_PACKED.zza()) {
                zzlx.SINT64_LIST_PACKED.zza();
            }
            long j = i12;
            switch (zzz) {
                case 0:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA(unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA(unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA(unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + 1;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzI(obj, i4, i, i2, r5)) {
                        int i13 = i9 << 3;
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzlh) {
                            int zzz2 = zzlm.zzz(i13);
                            int zzc2 = ((zzlh) object).zzc();
                            i5 += zzz2 + zzlm.zzz(zzc2) + zzc2;
                            break;
                        } else {
                            i5 += zzlm.zzz(i13) + zzlm.zzB((String) object);
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zznz.zzz(i9, unsafe.getObject(obj, j), zzp(i4));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzI(obj, i4, i, i2, r5)) {
                        zzlh zzlhVar = (zzlh) unsafe.getObject(obj, j);
                        int zzz3 = zzlm.zzz(i9 << 3);
                        int zzc3 = zzlhVar.zzc();
                        i5 += zzz3 + zzlm.zzz(zzc3) + zzc3;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA(unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzz(i9 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzI(obj, i4, i, i2, r5)) {
                        int i14 = unsafe.getInt(obj, j);
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz((i14 >> 31) ^ (i14 + i14));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzI(obj, i4, i, i2, r5)) {
                        long j2 = unsafe.getLong(obj, j);
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA((j2 >> 63) ^ (j2 + j2));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzI(obj, i4, i, i2, r5)) {
                        i5 += zzlm.zzG(i9, (zznm) unsafe.getObject(obj, j), zzp(i4));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    i5 += zznz.zzy(i9, (List) unsafe.getObject(obj, j), z);
                    break;
                case 19:
                    i5 += zznz.zzw(i9, (List) unsafe.getObject(obj, j), z);
                    break;
                case 20:
                    List list = (List) unsafe.getObject(obj, j);
                    int i15 = zznz.zza;
                    i5 += list.size() == 0 ? z : zznz.zzo(list) + (list.size() * zzlm.zzz(i9 << 3));
                    break;
                case 21:
                    List list2 = (List) unsafe.getObject(obj, j);
                    int i16 = zznz.zza;
                    int size = list2.size();
                    i5 += size == 0 ? z : zznz.zzp(list2) + (size * zzlm.zzz(i9 << 3));
                    break;
                case 22:
                    List list3 = (List) unsafe.getObject(obj, j);
                    int i17 = zznz.zza;
                    int size2 = list3.size();
                    i5 += size2 == 0 ? z : zznz.zzs(list3) + (size2 * zzlm.zzz(i9 << 3));
                    break;
                case 23:
                    i5 += zznz.zzy(i9, (List) unsafe.getObject(obj, j), z);
                    break;
                case 24:
                    i5 += zznz.zzw(i9, (List) unsafe.getObject(obj, j), z);
                    break;
                case 25:
                    List list4 = (List) unsafe.getObject(obj, j);
                    int i18 = zznz.zza;
                    int size3 = list4.size();
                    i5 += size3 == 0 ? z : size3 * (zzlm.zzz(i9 << 3) + 1);
                    break;
                case 26:
                    ?? r0 = (List) unsafe.getObject(obj, j);
                    int i19 = zznz.zza;
                    int size4 = r0.size();
                    if (size4 == 0) {
                        r2 = z;
                    } else {
                        int zzz4 = zzlm.zzz(i9 << 3) * size4;
                        if (r0 instanceof zzmx) {
                            zzmx zzmxVar = (zzmx) r0;
                            r2 = zzz4;
                            for (?? r32 = z; r32 < size4; r32++) {
                                Object zzc4 = zzmxVar.zzc();
                                if (zzc4 instanceof zzlh) {
                                    int zzc5 = ((zzlh) zzc4).zzc();
                                    zzB2 = (r2 == true ? 1 : 0) + zzlm.zzz(zzc5) + zzc5;
                                } else {
                                    zzB2 = (r2 == true ? 1 : 0) + zzlm.zzB((String) zzc4);
                                }
                                r2 = zzB2;
                            }
                        } else {
                            r2 = zzz4;
                            for (?? r33 = z; r33 < size4; r33++) {
                                Object obj2 = r0.get(r33);
                                if (obj2 instanceof zzlh) {
                                    int zzc6 = ((zzlh) obj2).zzc();
                                    zzB = (r2 == true ? 1 : 0) + zzlm.zzz(zzc6) + zzc6;
                                } else {
                                    zzB = (r2 == true ? 1 : 0) + zzlm.zzB((String) obj2);
                                }
                                r2 = zzB;
                            }
                        }
                    }
                    i5 += r2;
                    break;
                case 27:
                    ?? r02 = (List) unsafe.getObject(obj, j);
                    zznx zzp = zzp(i4);
                    int i20 = zznz.zza;
                    int size5 = r02.size();
                    if (size5 == 0) {
                        r3 = z;
                    } else {
                        r3 = zzlm.zzz(i9 << 3) * size5;
                        for (?? r42 = z; r42 < size5; r42++) {
                            Object obj3 = r02.get(r42);
                            if (obj3 instanceof zzmw) {
                                int zzb2 = ((zzmw) obj3).zzb();
                                zzD = (r3 == true ? 1 : 0) + zzlm.zzz(zzb2) + zzb2;
                            } else {
                                zzD = (r3 == true ? 1 : 0) + zzlm.zzD((zznm) obj3, zzp);
                            }
                            r3 = zzD;
                        }
                    }
                    i5 += r3;
                    break;
                case 28:
                    ?? r03 = (List) unsafe.getObject(obj, j);
                    int i21 = zznz.zza;
                    int size6 = r03.size();
                    if (size6 == 0) {
                        r1 = z;
                    } else {
                        r1 = size6 * zzlm.zzz(i9 << 3);
                        for (?? r23 = z; r23 < r03.size(); r23++) {
                            int zzc7 = ((zzlh) r03.get(r23)).zzc();
                            r1 = (r1 == true ? 1 : 0) + zzlm.zzz(zzc7) + zzc7;
                        }
                    }
                    i5 += r1;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    List list5 = (List) unsafe.getObject(obj, j);
                    int i22 = zznz.zza;
                    int size7 = list5.size();
                    i5 += size7 == 0 ? z : zznz.zzt(list5) + (size7 * zzlm.zzz(i9 << 3));
                    break;
                case 30:
                    List list6 = (List) unsafe.getObject(obj, j);
                    int i23 = zznz.zza;
                    int size8 = list6.size();
                    i5 += size8 == 0 ? z : zznz.zzr(list6) + (size8 * zzlm.zzz(i9 << 3));
                    break;
                case 31:
                    i5 += zznz.zzw(i9, (List) unsafe.getObject(obj, j), z);
                    break;
                case 32:
                    i5 += zznz.zzy(i9, (List) unsafe.getObject(obj, j), z);
                    break;
                case 33:
                    List list7 = (List) unsafe.getObject(obj, j);
                    int i24 = zznz.zza;
                    int size9 = list7.size();
                    i5 += size9 == 0 ? z : zznz.zzu(list7) + (size9 * zzlm.zzz(i9 << 3));
                    break;
                case 34:
                    List list8 = (List) unsafe.getObject(obj, j);
                    int i25 = zznz.zza;
                    int size10 = list8.size();
                    i5 += size10 == 0 ? z : zznz.zzq(list8) + (size10 * zzlm.zzz(i9 << 3));
                    break;
                case 35:
                    int zzx2 = zznz.zzx((List) unsafe.getObject(obj, j));
                    if (zzx2 > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzx2) + zzx2;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    int zzv = zznz.zzv((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzv) + zzv;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    int zzo = zznz.zzo((List) unsafe.getObject(obj, j));
                    if (zzo > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzo) + zzo;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    int zzp2 = zznz.zzp((List) unsafe.getObject(obj, j));
                    if (zzp2 > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzp2) + zzp2;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    int zzs = zznz.zzs((List) unsafe.getObject(obj, j));
                    if (zzs > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzs) + zzs;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    int zzx3 = zznz.zzx((List) unsafe.getObject(obj, j));
                    if (zzx3 > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzx3) + zzx3;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    int zzv2 = zznz.zzv((List) unsafe.getObject(obj, j));
                    if (zzv2 > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzv2) + zzv2;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    List list9 = (List) unsafe.getObject(obj, j);
                    int i26 = zznz.zza;
                    int size11 = list9.size();
                    if (size11 > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(size11) + size11;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    int zzt = zznz.zzt((List) unsafe.getObject(obj, j));
                    if (zzt > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzt) + zzt;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    int zzr = zznz.zzr((List) unsafe.getObject(obj, j));
                    if (zzr > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzr) + zzr;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    int zzv3 = zznz.zzv((List) unsafe.getObject(obj, j));
                    if (zzv3 > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzv3) + zzv3;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    int zzx4 = zznz.zzx((List) unsafe.getObject(obj, j));
                    if (zzx4 > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzx4) + zzx4;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    int zzu = zznz.zzu((List) unsafe.getObject(obj, j));
                    if (zzu > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzu) + zzu;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    int zzq = zznz.zzq((List) unsafe.getObject(obj, j));
                    if (zzq > 0) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzq) + zzq;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    ?? r04 = (List) unsafe.getObject(obj, j);
                    zznx zzp3 = zzp(i4);
                    int i27 = zznz.zza;
                    int size12 = r04.size();
                    if (size12 == 0) {
                        r4 = z;
                    } else {
                        boolean z2 = z;
                        r4 = z2;
                        ?? r34 = z2;
                        while (r34 < size12) {
                            int zzG = zzlm.zzG(i9, (zznm) r04.get(r34), zzp3);
                            r34++;
                            r4 = (r4 == true ? 1 : 0) + zzG;
                        }
                    }
                    i5 += r4;
                    break;
                case 50:
                    zzng zzngVar = (zzng) unsafe.getObject(obj, j);
                    zznf zznfVar = (zznf) zzq(i4);
                    if (zzngVar.isEmpty()) {
                        r22 = z;
                    } else {
                        r22 = z;
                        for (Map.Entry entry2 : zzngVar.entrySet()) {
                            r22 = (r22 == true ? 1 : 0) + zznfVar.zzd(i9, entry2.getKey(), entry2.getValue());
                        }
                    }
                    i5 += r22;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA(zzF(obj, j));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA(zzF(obj, j));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA(zzE(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + 1;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzL(obj, i9, i4)) {
                        int i28 = i9 << 3;
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzlh) {
                            int zzz5 = zzlm.zzz(i28);
                            int zzc8 = ((zzlh) object2).zzc();
                            i5 += zzz5 + zzlm.zzz(zzc8) + zzc8;
                            break;
                        } else {
                            i5 += zzlm.zzz(i28) + zzlm.zzB((String) object2);
                            break;
                        }
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (zzL(obj, i9, i4)) {
                        i5 += zznz.zzz(i9, unsafe.getObject(obj, j), zzp(i4));
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzL(obj, i9, i4)) {
                        zzlh zzlhVar2 = (zzlh) unsafe.getObject(obj, j);
                        int zzz6 = zzlm.zzz(i9 << 3);
                        int zzc9 = zzlhVar2.zzc();
                        i5 += zzz6 + zzlm.zzz(zzc9) + zzc9;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz(zzE(obj, j));
                        break;
                    } else {
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA(zzE(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzz(i9 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzL(obj, i9, i4)) {
                        int zzE = zzE(obj, j);
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzz((zzE >> 31) ^ (zzE + zzE));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzL(obj, i9, i4)) {
                        long zzF = zzF(obj, j);
                        i5 += zzlm.zzz(i9 << 3) + zzlm.zzA((zzF >> 63) ^ (zzF + zzF));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzL(obj, i9, i4)) {
                        i5 += zzlm.zzG(i9, (zznm) unsafe.getObject(obj, j), zzp(i4));
                        break;
                    } else {
                        break;
                    }
            }
            i4 += 3;
            i6 = i;
            r12 = i2;
            z = false;
            i3 = 1048575;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final void zzf(Object obj, zzov zzovVar) throws IOException {
        Map.Entry entry;
        int i;
        int i2;
        int i3;
        Map.Entry entry2;
        if (this.zzh) {
            zzlw zzlwVar = ((zzmc) obj).zzb;
            entry = !zzlwVar.zza.isEmpty() ? (Map.Entry) zzlwVar.zzc().next() : null;
        } else {
            entry = null;
        }
        int[] iArr = this.zzc;
        Unsafe unsafe = zzb;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        while (i7 < iArr.length) {
            int zzx = zzx(i7);
            int zzz = zzz(zzx);
            int i8 = iArr[i7];
            if (zzz <= 17) {
                int i9 = iArr[i7 + 2];
                int i10 = i9 & i4;
                if (i10 != i5) {
                    i6 = i10 == i4 ? 0 : unsafe.getInt(obj, i10);
                    i5 = i10;
                }
                i = i5;
                i2 = i6;
                i3 = 1 << (i9 >>> 20);
            } else {
                i = i5;
                i2 = i6;
                i3 = 0;
            }
            if (entry != null) {
                throw null;
            }
            long j = zzx & i4;
            switch (zzz) {
                case 0:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzf(i8, zzop.zzl(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zze(i8, zzop.zzj(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzc(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzh(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzi(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzj(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzk(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzl(i8, zzop.zzh(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzP(i8, unsafe.getObject(obj, j), zzovVar);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzr(i8, unsafe.getObject(obj, j), zzp(i7));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzn(i8, (zzlh) unsafe.getObject(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzo(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzg(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzb(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzd(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzp(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzq(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    entry2 = entry;
                    if (zzI(obj, i7, i, i2, i3)) {
                        zzovVar.zzs(i8, unsafe.getObject(obj, j), zzp(i7));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zznz.zza(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 19:
                    zznz.zzb(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 20:
                    zznz.zzc(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 21:
                    zznz.zzd(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 22:
                    zznz.zzh(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 23:
                    zznz.zzf(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 24:
                    zznz.zzk(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 25:
                    zznz.zzn(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 26:
                    int i11 = iArr[i7];
                    List list = (List) unsafe.getObject(obj, j);
                    int i12 = zznz.zza;
                    if (list != null) {
                        if (list.isEmpty()) {
                            entry2 = entry;
                            break;
                        } else {
                            zzovVar.zzF(i11, list);
                            entry2 = entry;
                            break;
                        }
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 27:
                    int i13 = iArr[i7];
                    List list2 = (List) unsafe.getObject(obj, j);
                    zznx zzp = zzp(i7);
                    int i14 = zznz.zza;
                    if (list2 != null) {
                        if (list2.isEmpty()) {
                            entry2 = entry;
                            break;
                        } else {
                            for (int i15 = 0; i15 < list2.size(); i15++) {
                                ((zzln) zzovVar).zzr(i13, list2.get(i15), zzp);
                            }
                            entry2 = entry;
                            break;
                        }
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 28:
                    int i16 = iArr[i7];
                    List list3 = (List) unsafe.getObject(obj, j);
                    int i17 = zznz.zza;
                    if (list3 != null) {
                        if (list3.isEmpty()) {
                            entry2 = entry;
                            break;
                        } else {
                            zzovVar.zzG(i16, list3);
                            entry2 = entry;
                            break;
                        }
                    } else {
                        entry2 = entry;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    zznz.zzi(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 30:
                    zznz.zzm(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 31:
                    zznz.zzl(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 32:
                    zznz.zzg(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 33:
                    zznz.zzj(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 34:
                    zznz.zze(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, false);
                    entry2 = entry;
                    break;
                case 35:
                    zznz.zza(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 36:
                    zznz.zzb(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 37:
                    zznz.zzc(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 38:
                    zznz.zzd(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 39:
                    zznz.zzh(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 40:
                    zznz.zzf(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 41:
                    zznz.zzk(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 42:
                    zznz.zzn(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 43:
                    zznz.zzi(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 44:
                    zznz.zzm(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 45:
                    zznz.zzl(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 46:
                    zznz.zzg(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 47:
                    zznz.zzj(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    zznz.zze(iArr[i7], (List) unsafe.getObject(obj, j), zzovVar, true);
                    entry2 = entry;
                    break;
                case 49:
                    int i18 = iArr[i7];
                    List list4 = (List) unsafe.getObject(obj, j);
                    zznx zzp2 = zzp(i7);
                    int i19 = zznz.zza;
                    if (list4 != null) {
                        if (list4.isEmpty()) {
                            entry2 = entry;
                            break;
                        } else {
                            for (int i20 = 0; i20 < list4.size(); i20++) {
                                ((zzln) zzovVar).zzs(i18, list4.get(i20), zzp2);
                            }
                            entry2 = entry;
                            break;
                        }
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 50:
                    Object object = unsafe.getObject(obj, j);
                    if (object != null) {
                        zzovVar.zzM(i8, ((zznf) zzq(i7)).zze(), (zzng) object);
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzf(i8, zzC(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zze(i8, zzD(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzc(i8, zzF(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzh(i8, zzF(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzi(i8, zzE(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 56:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzj(i8, zzF(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 57:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzk(i8, zzE(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 58:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzl(i8, zzG(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 59:
                    if (zzL(obj, i8, i7)) {
                        zzP(i8, unsafe.getObject(obj, j), zzovVar);
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzr(i8, unsafe.getObject(obj, j), zzp(i7));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzn(i8, (zzlh) unsafe.getObject(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 62:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzo(i8, zzE(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzg(i8, zzE(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 64:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzb(i8, zzE(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 65:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzd(i8, zzF(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzp(i8, zzE(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzq(i8, zzF(obj, j));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                case 68:
                    if (zzL(obj, i8, i7)) {
                        zzovVar.zzs(i8, unsafe.getObject(obj, j), zzp(i7));
                        entry2 = entry;
                        break;
                    } else {
                        entry2 = entry;
                        break;
                    }
                default:
                    entry2 = entry;
                    break;
            }
            i7 += 3;
            i5 = i;
            entry = entry2;
            i6 = i2;
            i4 = 1048575;
        }
        Map.Entry entry3 = entry;
        if (entry3 != null) {
            throw null;
        }
        ((zzmf) obj).zzc.zzg(zzovVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:232:0x0bea, code lost:
    
        throw new com.google.android.gms.internal.measurement.zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0efd, code lost:
    
        if (r7 == 1048575) goto L581;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0eff, code lost:
    
        r0.putInt(r10, r7, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0f03, code lost:
    
        r0 = r1.zzj;
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0f09, code lost:
    
        if (r0 >= r1.zzk) goto L737;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0f0b, code lost:
    
        r4 = r1.zzi;
        r6 = r1.zzl;
        r7 = r1.zzc;
        r4 = r4[r0];
        r7 = r7[r4];
        r8 = com.google.android.gms.internal.measurement.zzop.zzn(r10, r1.zzx(r4) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0f22, code lost:
    
        if (r8 == null) goto L740;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0f24, code lost:
    
        r12 = r1.zzr(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0f28, code lost:
    
        if (r12 == null) goto L739;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0f2a, code lost:
    
        r4 = ((com.google.android.gms.internal.measurement.zznf) r1.zzq(r4)).zze();
        r8 = ((com.google.android.gms.internal.measurement.zzng) r8).entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0f42, code lost:
    
        if (r8.hasNext() == false) goto L738;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0f44, code lost:
    
        r13 = (java.util.Map.Entry) r8.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0f58, code lost:
    
        if (r12.zza(((java.lang.Integer) r13.getValue()).intValue()) != false) goto L741;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0f5a, code lost:
    
        if (r3 != 0) goto L595;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0f5c, code lost:
    
        r3 = r6.zza(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0f60, code lost:
    
        r14 = com.google.android.gms.internal.measurement.zznf.zzc(r4, r13.getKey(), r13.getValue());
        r15 = com.google.android.gms.internal.measurement.zzlh.zzb;
        r15 = new byte[r14];
        r16 = com.google.android.gms.internal.measurement.zzlm.zzb;
        r9 = new com.google.android.gms.internal.measurement.zzlk(r15, 0, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0f78, code lost:
    
        com.google.android.gms.internal.measurement.zznf.zzb(r9, r4, r13.getKey(), r13.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0f83, code lost:
    
        r3.zzk((r7 << 3) | 2, com.google.android.gms.internal.measurement.zzle.zza(r9, r15));
        r8.remove();
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0f9d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0fa3, code lost:
    
        throw new java.lang.RuntimeException(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0fbc, code lost:
    
        r0 = r0 + 1;
        r1 = r31;
        r3 = (com.google.android.gms.internal.measurement.zzoj) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0fc4, code lost:
    
        if (r3 == 0) goto L608;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0fc6, code lost:
    
        ((com.google.android.gms.internal.measurement.zzmf) r10).zzc = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0fce, code lost:
    
        if (r11 != 0) goto L614;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0fd2, code lost:
    
        if (r2 != r35) goto L612;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0fdc, code lost:
    
        throw new com.google.android.gms.internal.measurement.zzmr(r19);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0fe5, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0fdd, code lost:
    
        r1 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0fe1, code lost:
    
        if (r2 > r35) goto L618;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0fe3, code lost:
    
        if (r5 != r11) goto L618;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0feb, code lost:
    
        throw new com.google.android.gms.internal.measurement.zzmr(r1);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0a98 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0aa8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0e8a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0e9a A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v176, types: [int] */
    /* JADX WARN: Type inference failed for: r3v211 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.google.android.gms.internal.measurement.zzoj] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final int zzh(Object obj, byte[] bArr, int i, int i2, int i3, zzkw zzkwVar) throws IOException {
        Object obj2;
        String str;
        Unsafe unsafe;
        int i4;
        int i5;
        int zzN;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        zznp<T> zznpVar;
        Unsafe unsafe2;
        int i16;
        String str2;
        Object obj3;
        zzmo zzmoVar;
        zznp<T> zznpVar2;
        int i17;
        Unsafe unsafe3;
        int i18;
        String str3;
        zznp<T> zznpVar3;
        int zza2;
        Object obj4;
        zznp<T> zznpVar4;
        int zzl;
        int i19;
        int i20;
        Unsafe unsafe4;
        int i21;
        Unsafe unsafe5;
        zzng zzngVar;
        Object obj5;
        zzne zzneVar;
        Object obj6;
        int i22;
        zznp<T> zznpVar5 = this;
        Object obj7 = obj;
        int i23 = i2;
        int i24 = i3;
        zzB(obj);
        Unsafe unsafe6 = zzb;
        int i25 = i;
        int i26 = -1;
        int i27 = 0;
        int i28 = 0;
        int i29 = 0;
        int i30 = 1048575;
        while (true) {
            if (i25 < i23) {
                int i31 = i25 + 1;
                int i32 = bArr[i25];
                if (i32 < 0) {
                    i5 = zzkx.zzb(i32, bArr, i31, zzkwVar);
                    i4 = zzkwVar.zza;
                } else {
                    i4 = i32;
                    i5 = i31;
                }
                int i33 = i4 >>> 3;
                if (i33 > i26) {
                    zzN = (i33 < zznpVar5.zze || i33 > zznpVar5.zzf) ? -1 : zznpVar5.zzN(i33, i27 / 3);
                } else {
                    zzN = (i33 < zznpVar5.zze || i33 > zznpVar5.zzf) ? -1 : zznpVar5.zzN(i33, 0);
                }
                if (zzN != -1) {
                    int i34 = i4 & 7;
                    int[] iArr = zznpVar5.zzc;
                    int i35 = i4;
                    int i36 = iArr[zzN + 1];
                    str = "Failed to parse the message.";
                    int zzz = zzz(i36);
                    long j = i36 & 1048575;
                    int i37 = i33;
                    if (zzz > 17) {
                        int i38 = i5;
                        i10 = zzN;
                        zznp<T> zznpVar6 = zznpVar5;
                        i7 = i29;
                        i8 = i30;
                        if (zzz != 27) {
                            if (zzz > 49) {
                                unsafe2 = unsafe6;
                                i16 = i38;
                                i23 = i2;
                                if (zzz != 50) {
                                    unsafe = unsafe2;
                                    long j2 = iArr[i10 + 2] & 1048575;
                                    switch (zzz) {
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i22 = i10;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 1) {
                                                i25 = i16 + 8;
                                                unsafe.putObject(obj2, j, Double.valueOf(Double.longBitsToDouble(zzkx.zze(bArr, i16))));
                                                unsafe.putInt(obj2, j2, i9);
                                                if (i25 != i16) {
                                                    i6 = i25;
                                                    i10 = i22;
                                                    break;
                                                } else {
                                                    i24 = i3;
                                                    i28 = i11;
                                                    i26 = i9;
                                                    i27 = i22;
                                                    i29 = i7;
                                                    i30 = i8;
                                                    unsafe6 = unsafe;
                                                    obj7 = obj2;
                                                    i23 = i2;
                                                }
                                            }
                                            i25 = i16;
                                            if (i25 != i16) {
                                            }
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i22 = i10;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 5) {
                                                i25 = i16 + 4;
                                                unsafe.putObject(obj2, j, Float.valueOf(Float.intBitsToFloat(zzkx.zzd(bArr, i16))));
                                                unsafe.putInt(obj2, j2, i9);
                                                if (i25 != i16) {
                                                }
                                            }
                                            i25 = i16;
                                            if (i25 != i16) {
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i22 = i10;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 0) {
                                                i25 = zzkx.zzc(bArr, i16, zzkwVar);
                                                unsafe.putObject(obj2, j, Long.valueOf(zzkwVar.zzb));
                                                unsafe.putInt(obj2, j2, i9);
                                                if (i25 != i16) {
                                                }
                                            }
                                            i25 = i16;
                                            if (i25 != i16) {
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                                        case 62:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i22 = i10;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 0) {
                                                i25 = zzkx.zza(bArr, i16, zzkwVar);
                                                unsafe.putObject(obj2, j, Integer.valueOf(zzkwVar.zza));
                                                unsafe.putInt(obj2, j2, i9);
                                                if (i25 != i16) {
                                                }
                                            }
                                            i25 = i16;
                                            if (i25 != i16) {
                                            }
                                            break;
                                        case 56:
                                        case 65:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i22 = i10;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 1) {
                                                i25 = i16 + 8;
                                                unsafe.putObject(obj2, j, Long.valueOf(zzkx.zze(bArr, i16)));
                                                unsafe.putInt(obj2, j2, i9);
                                                if (i25 != i16) {
                                                }
                                            }
                                            i25 = i16;
                                            if (i25 != i16) {
                                            }
                                            break;
                                        case 57:
                                        case 64:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i22 = i10;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 5) {
                                                i25 = i16 + 4;
                                                unsafe.putObject(obj2, j, Integer.valueOf(zzkx.zzd(bArr, i16)));
                                                unsafe.putInt(obj2, j2, i9);
                                                if (i25 != i16) {
                                                }
                                            }
                                            i25 = i16;
                                            if (i25 != i16) {
                                            }
                                            break;
                                        case 58:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i22 = i10;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 0) {
                                                i25 = zzkx.zzc(bArr, i16, zzkwVar);
                                                unsafe.putObject(obj2, j, Boolean.valueOf(zzkwVar.zzb != 0));
                                                unsafe.putInt(obj2, j2, i9);
                                                if (i25 != i16) {
                                                }
                                            }
                                            i25 = i16;
                                            if (i25 != i16) {
                                            }
                                            break;
                                        case 59:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 2) {
                                                i25 = zzkx.zza(bArr, i16, zzkwVar);
                                                int i39 = zzkwVar.zza;
                                                if (i39 == 0) {
                                                    unsafe.putObject(obj2, j, "");
                                                    i22 = i10;
                                                } else {
                                                    i22 = i10;
                                                    int i40 = i25 + i39;
                                                    if ((i36 & 536870912) != 0 && !zzos.zza(bArr, i25, i40)) {
                                                        throw new zzmr("Protocol message had invalid UTF-8.");
                                                    }
                                                    unsafe.putObject(obj2, j, new String(bArr, i25, i39, zzmp.zza));
                                                    i25 = i40;
                                                }
                                                unsafe.putInt(obj2, j2, i9);
                                                if (i25 != i16) {
                                                }
                                            } else {
                                                i22 = i10;
                                                i25 = i16;
                                                if (i25 != i16) {
                                                }
                                            }
                                            break;
                                        case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 2) {
                                                Object zzu = zznpVar5.zzu(obj2, i9, i10);
                                                i25 = zzkx.zzj(zzu, zznpVar5.zzp(i10), bArr, i16, i2, zzkwVar);
                                                zznpVar5.zzv(obj2, i9, i10, zzu);
                                                i22 = i10;
                                                if (i25 != i16) {
                                                }
                                            } else {
                                                i22 = i10;
                                                i25 = i16;
                                                if (i25 != i16) {
                                                }
                                            }
                                            break;
                                        case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 2) {
                                                i25 = zzkx.zzg(bArr, i16, zzkwVar);
                                                unsafe.putObject(obj2, j, zzkwVar.zzc);
                                                unsafe.putInt(obj2, j2, i9);
                                                i22 = i10;
                                                if (i25 != i16) {
                                                }
                                            } else {
                                                i22 = i10;
                                                i25 = i16;
                                                if (i25 != i16) {
                                                }
                                            }
                                            break;
                                        case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                                            obj2 = obj;
                                            str = str;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 0) {
                                                i25 = zzkx.zza(bArr, i16, zzkwVar);
                                                int i41 = zzkwVar.zza;
                                                zzmk zzr = zznpVar5.zzr(i10);
                                                if (zzr == null) {
                                                    i11 = i35;
                                                } else if (zzr.zza(i41)) {
                                                    i11 = i35;
                                                } else {
                                                    i11 = i35;
                                                    zzg(obj).zzk(i11, Long.valueOf(i41));
                                                    i22 = i10;
                                                    if (i25 != i16) {
                                                    }
                                                }
                                                unsafe.putObject(obj2, j, Integer.valueOf(i41));
                                                unsafe.putInt(obj2, j2, i9);
                                                i22 = i10;
                                                if (i25 != i16) {
                                                }
                                            } else {
                                                i11 = i35;
                                                i22 = i10;
                                                i25 = i16;
                                                if (i25 != i16) {
                                                }
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                                            obj2 = obj;
                                            str = str;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 0) {
                                                i25 = zzkx.zza(bArr, i16, zzkwVar);
                                                unsafe.putObject(obj2, j, Integer.valueOf(zzlj.zzb(zzkwVar.zza)));
                                                unsafe.putInt(obj2, j2, i9);
                                                i22 = i10;
                                                i11 = i35;
                                                if (i25 != i16) {
                                                }
                                            } else {
                                                i22 = i10;
                                                i11 = i35;
                                                i25 = i16;
                                                if (i25 != i16) {
                                                }
                                            }
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                                            obj2 = obj;
                                            str = str;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            if (i34 == 0) {
                                                int zzc = zzkx.zzc(bArr, i16, zzkwVar);
                                                unsafe.putObject(obj2, j, Long.valueOf(zzlj.zzc(zzkwVar.zzb)));
                                                unsafe.putInt(obj2, j2, i9);
                                                i25 = zzc;
                                                i22 = i10;
                                                i11 = i35;
                                                if (i25 != i16) {
                                                }
                                            } else {
                                                i22 = i10;
                                                i11 = i35;
                                                i25 = i16;
                                                if (i25 != i16) {
                                                }
                                            }
                                            break;
                                        case 68:
                                            if (i34 == 3) {
                                                Object zzu2 = zzu(obj, i37, i10);
                                                str = str;
                                                i9 = i37;
                                                obj2 = obj;
                                                zznpVar5 = this;
                                                i25 = zzkx.zzk(zzu2, zzp(i10), bArr, i16, i2, (i35 & (-8)) | 4, zzkwVar);
                                                zznpVar5.zzv(obj2, i9, i10, zzu2);
                                                i22 = i10;
                                                i11 = i35;
                                                if (i25 != i16) {
                                                }
                                            } else {
                                                obj2 = obj;
                                                str = str;
                                                i9 = i37;
                                                zznpVar5 = this;
                                                i22 = i10;
                                                i11 = i35;
                                                i25 = i16;
                                                if (i25 != i16) {
                                                }
                                            }
                                            break;
                                        default:
                                            obj2 = obj;
                                            i11 = i35;
                                            str = str;
                                            i22 = i10;
                                            i9 = i37;
                                            zznpVar5 = this;
                                            i25 = i16;
                                            if (i25 != i16) {
                                            }
                                            break;
                                    }
                                } else if (i34 == 2) {
                                    Object zzq = zzq(i10);
                                    Unsafe unsafe7 = unsafe2;
                                    Object object = unsafe7.getObject(obj7, j);
                                    if (!((zzng) object).zze()) {
                                        zzng zzc2 = zzng.zza().zzc();
                                        zznh.zza(zzc2, object);
                                        unsafe7.putObject(obj7, j, zzc2);
                                        object = zzc2;
                                    }
                                    zzne zze = ((zznf) zzq).zze();
                                    zzng zzngVar2 = (zzng) object;
                                    int zza3 = zzkx.zza(bArr, i16, zzkwVar);
                                    int i42 = zzkwVar.zza;
                                    if (i42 >= 0 && i42 <= i23 - zza3) {
                                        int i43 = zza3 + i42;
                                        Object obj8 = zze.zzb;
                                        Object obj9 = zze.zzd;
                                        Object obj10 = obj8;
                                        Object obj11 = obj9;
                                        while (zza3 < i43) {
                                            Object obj12 = obj11;
                                            int i44 = zza3 + 1;
                                            byte b = bArr[zza3];
                                            if (b < 0) {
                                                i44 = zzkx.zzb(b, bArr, i44, zzkwVar);
                                                b = zzkwVar.zza;
                                            }
                                            Object obj13 = obj10;
                                            int i45 = b & 7;
                                            switch (b >>> 3) {
                                                case 1:
                                                    zzngVar = zzngVar2;
                                                    zzneVar = zze;
                                                    unsafe5 = unsafe7;
                                                    obj5 = obj13;
                                                    obj6 = obj9;
                                                    zzot zzotVar = zzneVar.zza;
                                                    if (i45 != zzotVar.zzb()) {
                                                        zza3 = zzkx.zzp(b, bArr, i44, i23, zzkwVar);
                                                        zzngVar2 = zzngVar;
                                                        obj11 = obj12;
                                                        obj10 = obj5;
                                                        zze = zzneVar;
                                                        obj9 = obj6;
                                                        unsafe7 = unsafe5;
                                                        break;
                                                    } else {
                                                        zza3 = zzO(bArr, i44, i2, zzotVar, null, zzkwVar);
                                                        obj10 = zzkwVar.zzc;
                                                        zzngVar2 = zzngVar;
                                                        obj11 = obj12;
                                                        zze = zzneVar;
                                                        obj9 = obj6;
                                                        unsafe7 = unsafe5;
                                                        break;
                                                    }
                                                case 2:
                                                    zzng zzngVar3 = zzngVar2;
                                                    zzot zzotVar2 = zze.zzc;
                                                    zzne zzneVar2 = zze;
                                                    if (i45 != zzotVar2.zzb()) {
                                                        unsafe5 = unsafe7;
                                                        zzngVar = zzngVar3;
                                                        obj5 = obj13;
                                                        zzneVar = zzneVar2;
                                                        obj6 = obj9;
                                                        zza3 = zzkx.zzp(b, bArr, i44, i23, zzkwVar);
                                                        zzngVar2 = zzngVar;
                                                        obj11 = obj12;
                                                        obj10 = obj5;
                                                        zze = zzneVar;
                                                        obj9 = obj6;
                                                        unsafe7 = unsafe5;
                                                        break;
                                                    } else {
                                                        zza3 = zzO(bArr, i44, i2, zzotVar2, obj9.getClass(), zzkwVar);
                                                        obj11 = zzkwVar.zzc;
                                                        zzngVar2 = zzngVar3;
                                                        obj10 = obj13;
                                                        zze = zzneVar2;
                                                        obj9 = obj9;
                                                        unsafe7 = unsafe7;
                                                        break;
                                                    }
                                                default:
                                                    zzngVar = zzngVar2;
                                                    zzneVar = zze;
                                                    unsafe5 = unsafe7;
                                                    obj5 = obj13;
                                                    obj6 = obj9;
                                                    zza3 = zzkx.zzp(b, bArr, i44, i23, zzkwVar);
                                                    zzngVar2 = zzngVar;
                                                    obj11 = obj12;
                                                    obj10 = obj5;
                                                    zze = zzneVar;
                                                    obj9 = obj6;
                                                    unsafe7 = unsafe5;
                                                    break;
                                            }
                                        }
                                        Object obj14 = obj11;
                                        zzng zzngVar4 = zzngVar2;
                                        Unsafe unsafe8 = unsafe7;
                                        Object obj15 = obj10;
                                        if (zza3 != i43) {
                                            throw new zzmr(str);
                                        }
                                        zzngVar4.put(obj15, obj14);
                                        if (i43 != i16) {
                                            obj7 = obj;
                                            i24 = i3;
                                            i28 = i35;
                                            i27 = i10;
                                            i25 = i43;
                                            i29 = i7;
                                            i26 = i37;
                                            i30 = i8;
                                            unsafe6 = unsafe8;
                                            zznpVar5 = this;
                                        } else {
                                            obj2 = obj;
                                            i11 = i35;
                                            i6 = i43;
                                            i9 = i37;
                                            unsafe = unsafe8;
                                            zznpVar5 = this;
                                        }
                                    }
                                } else {
                                    str2 = str;
                                    obj2 = obj;
                                    i11 = i35;
                                    i6 = i16;
                                    str = str2;
                                    i9 = i37;
                                    unsafe = unsafe2;
                                    zznpVar5 = this;
                                }
                            } else {
                                long j3 = i36;
                                zzmo zzmoVar2 = (zzmo) unsafe6.getObject(obj7, j);
                                if (zzmoVar2.zza()) {
                                    obj3 = "";
                                    zzmoVar = zzmoVar2;
                                } else {
                                    int size = zzmoVar2.size();
                                    obj3 = "";
                                    zzmo zzg = zzmoVar2.zzg(size + size);
                                    unsafe6.putObject(obj7, j, zzg);
                                    zzmoVar = zzg;
                                }
                                switch (zzz) {
                                    case 18:
                                    case 35:
                                        zznpVar2 = this;
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        str3 = str;
                                        if (i34 == 2) {
                                            int i46 = zzkx.zza;
                                            zzlo zzloVar = (zzlo) zzmoVar;
                                            i25 = zzkx.zza(bArr, i18, zzkwVar);
                                            int i47 = zzkwVar.zza;
                                            int i48 = i25 + i47;
                                            if (i48 > bArr.length) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            zzloVar.zzh(zzloVar.size() + (i47 / 8));
                                            while (i25 < i48) {
                                                zzloVar.zzf(Double.longBitsToDouble(zzkx.zze(bArr, i25)));
                                                i25 += 8;
                                            }
                                            if (i25 != i48) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                                obj2 = obj7;
                                                i11 = i35;
                                                i6 = i25;
                                                zznpVar5 = zznpVar3;
                                                i9 = i37;
                                                unsafe = unsafe3;
                                                break;
                                            } else {
                                                i24 = i3;
                                                i28 = i35;
                                                i23 = i17;
                                                zznpVar5 = zznpVar3;
                                                i27 = i10;
                                                i29 = i7;
                                                i26 = i37;
                                                i30 = i8;
                                                unsafe6 = unsafe3;
                                            }
                                        } else if (i34 == 1) {
                                            i25 = i18 + 8;
                                            int i49 = zzkx.zza;
                                            zzlo zzloVar2 = (zzlo) zzmoVar;
                                            zzloVar2.zzf(Double.longBitsToDouble(zzkx.zze(bArr, i18)));
                                            while (i25 < i17) {
                                                int zza4 = zzkx.zza(bArr, i25, zzkwVar);
                                                if (i35 == zzkwVar.zza) {
                                                    zzloVar2.zzf(Double.longBitsToDouble(zzkx.zze(bArr, zza4)));
                                                    i25 = zza4 + 8;
                                                } else {
                                                    str = str3;
                                                    zznpVar3 = zznpVar2;
                                                    if (i25 != i18) {
                                                    }
                                                }
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                    case 19:
                                    case 36:
                                        zznpVar2 = this;
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        str3 = str;
                                        if (i34 == 2) {
                                            int i50 = zzkx.zza;
                                            zzly zzlyVar = (zzly) zzmoVar;
                                            i25 = zzkx.zza(bArr, i18, zzkwVar);
                                            int i51 = zzkwVar.zza;
                                            int i52 = i25 + i51;
                                            if (i52 > bArr.length) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            zzlyVar.zzh(zzlyVar.size() + (i51 / 4));
                                            while (i25 < i52) {
                                                zzlyVar.zzf(Float.intBitsToFloat(zzkx.zzd(bArr, i25)));
                                                i25 += 4;
                                            }
                                            if (i25 != i52) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else if (i34 == 5) {
                                            i25 = i18 + 4;
                                            int i53 = zzkx.zza;
                                            zzly zzlyVar2 = (zzly) zzmoVar;
                                            zzlyVar2.zzf(Float.intBitsToFloat(zzkx.zzd(bArr, i18)));
                                            while (i25 < i17) {
                                                int zza5 = zzkx.zza(bArr, i25, zzkwVar);
                                                if (i35 == zzkwVar.zza) {
                                                    zzlyVar2.zzf(Float.intBitsToFloat(zzkx.zzd(bArr, zza5)));
                                                    i25 = zza5 + 4;
                                                } else {
                                                    str = str3;
                                                    zznpVar3 = zznpVar2;
                                                    if (i25 != i18) {
                                                    }
                                                }
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                    case 20:
                                    case 21:
                                    case 37:
                                    case 38:
                                        zznpVar2 = this;
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        str3 = str;
                                        if (i34 == 2) {
                                            int i54 = zzkx.zza;
                                            zzna zznaVar = (zzna) zzmoVar;
                                            i25 = zzkx.zza(bArr, i18, zzkwVar);
                                            int i55 = zzkwVar.zza + i25;
                                            while (i25 < i55) {
                                                i25 = zzkx.zzc(bArr, i25, zzkwVar);
                                                zznaVar.zzf(zzkwVar.zzb);
                                            }
                                            if (i25 != i55) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else if (i34 == 0) {
                                            int i56 = zzkx.zza;
                                            zzna zznaVar2 = (zzna) zzmoVar;
                                            i25 = zzkx.zzc(bArr, i18, zzkwVar);
                                            zznaVar2.zzf(zzkwVar.zzb);
                                            while (i25 < i17) {
                                                int zza6 = zzkx.zza(bArr, i25, zzkwVar);
                                                if (i35 == zzkwVar.zza) {
                                                    i25 = zzkx.zzc(bArr, zza6, zzkwVar);
                                                    zznaVar2.zzf(zzkwVar.zzb);
                                                } else {
                                                    str = str3;
                                                    zznpVar3 = zznpVar2;
                                                    if (i25 != i18) {
                                                    }
                                                }
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                    case 22:
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                                    case 39:
                                    case 43:
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        if (i34 == 2) {
                                            i25 = zzkx.zzm(bArr, i18, zzmoVar, zzkwVar);
                                            str = str;
                                            zznpVar3 = this;
                                        } else if (i34 == 0) {
                                            i25 = zzkx.zzl(i35, bArr, i18, i2, zzmoVar, zzkwVar);
                                            str = str;
                                            zznpVar3 = this;
                                        } else {
                                            str = str;
                                            zznpVar3 = this;
                                            i25 = i18;
                                        }
                                        if (i25 != i18) {
                                        }
                                        break;
                                    case 23:
                                    case 32:
                                    case 40:
                                    case 46:
                                        zznpVar2 = this;
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        str3 = str;
                                        if (i34 == 2) {
                                            int i57 = zzkx.zza;
                                            zzna zznaVar3 = (zzna) zzmoVar;
                                            i25 = zzkx.zza(bArr, i18, zzkwVar);
                                            int i58 = zzkwVar.zza;
                                            int i59 = i25 + i58;
                                            if (i59 > bArr.length) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            zznaVar3.zzh(zznaVar3.size() + (i58 / 8));
                                            while (i25 < i59) {
                                                zznaVar3.zzf(zzkx.zze(bArr, i25));
                                                i25 += 8;
                                            }
                                            if (i25 != i59) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else if (i34 == 1) {
                                            i25 = i18 + 8;
                                            int i60 = zzkx.zza;
                                            zzna zznaVar4 = (zzna) zzmoVar;
                                            zznaVar4.zzf(zzkx.zze(bArr, i18));
                                            while (i25 < i17) {
                                                int zza7 = zzkx.zza(bArr, i25, zzkwVar);
                                                if (i35 == zzkwVar.zza) {
                                                    zznaVar4.zzf(zzkx.zze(bArr, zza7));
                                                    i25 = zza7 + 8;
                                                } else {
                                                    str = str3;
                                                    zznpVar3 = zznpVar2;
                                                    if (i25 != i18) {
                                                    }
                                                }
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                    case 24:
                                    case 31:
                                    case 41:
                                    case 45:
                                        zznpVar2 = this;
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        str3 = str;
                                        if (i34 == 2) {
                                            int i61 = zzkx.zza;
                                            zzmg zzmgVar = (zzmg) zzmoVar;
                                            i25 = zzkx.zza(bArr, i18, zzkwVar);
                                            int i62 = zzkwVar.zza;
                                            int i63 = i25 + i62;
                                            if (i63 > bArr.length) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            zzmgVar.zzi(zzmgVar.size() + (i62 / 4));
                                            while (i25 < i63) {
                                                zzmgVar.zzh(zzkx.zzd(bArr, i25));
                                                i25 += 4;
                                            }
                                            if (i25 != i63) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else if (i34 == 5) {
                                            i25 = i18 + 4;
                                            int i64 = zzkx.zza;
                                            zzmg zzmgVar2 = (zzmg) zzmoVar;
                                            zzmgVar2.zzh(zzkx.zzd(bArr, i18));
                                            while (i25 < i17) {
                                                int zza8 = zzkx.zza(bArr, i25, zzkwVar);
                                                if (i35 == zzkwVar.zza) {
                                                    zzmgVar2.zzh(zzkx.zzd(bArr, zza8));
                                                    i25 = zza8 + 4;
                                                } else {
                                                    str = str3;
                                                    zznpVar3 = zznpVar2;
                                                    if (i25 != i18) {
                                                    }
                                                }
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                    case 25:
                                    case 42:
                                        zznpVar2 = this;
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        str3 = str;
                                        if (i34 == 2) {
                                            int i65 = zzkx.zza;
                                            zzky zzkyVar = (zzky) zzmoVar;
                                            i25 = zzkx.zza(bArr, i18, zzkwVar);
                                            int i66 = zzkwVar.zza + i25;
                                            while (i25 < i66) {
                                                i25 = zzkx.zzc(bArr, i25, zzkwVar);
                                                zzkyVar.zzf(zzkwVar.zzb != 0);
                                            }
                                            if (i25 != i66) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else if (i34 == 0) {
                                            int i67 = zzkx.zza;
                                            zzky zzkyVar2 = (zzky) zzmoVar;
                                            i25 = zzkx.zzc(bArr, i18, zzkwVar);
                                            zzkyVar2.zzf(zzkwVar.zzb != 0);
                                            while (i25 < i17) {
                                                int zza9 = zzkx.zza(bArr, i25, zzkwVar);
                                                if (i35 == zzkwVar.zza) {
                                                    i25 = zzkx.zzc(bArr, zza9, zzkwVar);
                                                    zzkyVar2.zzf(zzkwVar.zzb != 0);
                                                } else {
                                                    str = str3;
                                                    zznpVar3 = zznpVar2;
                                                    if (i25 != i18) {
                                                    }
                                                }
                                            }
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            str = str3;
                                            zznpVar3 = zznpVar2;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                    case 26:
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        if (i34 == 2) {
                                            if ((j3 & 536870912) == 0) {
                                                zza2 = zzkx.zza(bArr, i18, zzkwVar);
                                                int i68 = zzkwVar.zza;
                                                if (i68 < 0) {
                                                    throw new zzmr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                }
                                                if (i68 == 0) {
                                                    obj4 = obj3;
                                                    zzmoVar.add(obj4);
                                                } else {
                                                    obj4 = obj3;
                                                    zzmoVar.add(new String(bArr, zza2, i68, zzmp.zza));
                                                    zza2 += i68;
                                                }
                                                while (zza2 < i17) {
                                                    int zza10 = zzkx.zza(bArr, zza2, zzkwVar);
                                                    if (i35 == zzkwVar.zza) {
                                                        zza2 = zzkx.zza(bArr, zza10, zzkwVar);
                                                        int i69 = zzkwVar.zza;
                                                        if (i69 < 0) {
                                                            throw new zzmr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                        }
                                                        if (i69 == 0) {
                                                            zzmoVar.add(obj4);
                                                        } else {
                                                            zzmoVar.add(new String(bArr, zza2, i69, zzmp.zza));
                                                            zza2 += i69;
                                                        }
                                                    }
                                                }
                                            } else {
                                                Object obj16 = obj3;
                                                zza2 = zzkx.zza(bArr, i18, zzkwVar);
                                                int i70 = zzkwVar.zza;
                                                if (i70 < 0) {
                                                    throw new zzmr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                }
                                                if (i70 == 0) {
                                                    zzmoVar.add(obj16);
                                                } else {
                                                    int i71 = zza2 + i70;
                                                    if (!zzos.zza(bArr, zza2, i71)) {
                                                        throw new zzmr("Protocol message had invalid UTF-8.");
                                                    }
                                                    zzmoVar.add(new String(bArr, zza2, i70, zzmp.zza));
                                                    zza2 = i71;
                                                }
                                                while (zza2 < i17) {
                                                    int zza11 = zzkx.zza(bArr, zza2, zzkwVar);
                                                    if (i35 == zzkwVar.zza) {
                                                        zza2 = zzkx.zza(bArr, zza11, zzkwVar);
                                                        int i72 = zzkwVar.zza;
                                                        if (i72 < 0) {
                                                            throw new zzmr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                        }
                                                        if (i72 == 0) {
                                                            zzmoVar.add(obj16);
                                                        } else {
                                                            int i73 = zza2 + i72;
                                                            if (!zzos.zza(bArr, zza2, i73)) {
                                                                throw new zzmr("Protocol message had invalid UTF-8.");
                                                            }
                                                            zzmoVar.add(new String(bArr, zza2, i72, zzmp.zza));
                                                            zza2 = i73;
                                                        }
                                                    }
                                                }
                                            }
                                            i25 = zza2;
                                            str = str;
                                            zznpVar3 = this;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            str = str;
                                            zznpVar3 = this;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                    case 27:
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        if (i34 == 2) {
                                            i37 = i37;
                                            i25 = zzkx.zzn(zzp(i10), i35, bArr, i18, i2, zzmoVar, zzkwVar);
                                            i17 = i17;
                                            zznpVar3 = this;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            i37 = i37;
                                            zznpVar3 = this;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                    case 28:
                                        zznpVar2 = this;
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        if (i34 == 2) {
                                            int zza12 = zzkx.zza(bArr, i18, zzkwVar);
                                            int i74 = zzkwVar.zza;
                                            if (i74 < 0) {
                                                throw new zzmr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                            }
                                            if (i74 > bArr.length - zza12) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            if (i74 == 0) {
                                                zzmoVar.add(zzlh.zzb);
                                            } else {
                                                zzmoVar.add(zzlh.zzh(bArr, zza12, i74));
                                                zza12 += i74;
                                            }
                                            while (zza12 < i17) {
                                                int zza13 = zzkx.zza(bArr, zza12, zzkwVar);
                                                if (i35 == zzkwVar.zza) {
                                                    zza12 = zzkx.zza(bArr, zza13, zzkwVar);
                                                    int i75 = zzkwVar.zza;
                                                    if (i75 < 0) {
                                                        throw new zzmr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                    }
                                                    if (i75 > bArr.length - zza12) {
                                                        throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                    }
                                                    if (i75 == 0) {
                                                        zzmoVar.add(zzlh.zzb);
                                                    } else {
                                                        zzmoVar.add(zzlh.zzh(bArr, zza12, i75));
                                                        zza12 += i75;
                                                    }
                                                } else {
                                                    i25 = zza12;
                                                    i37 = i37;
                                                    zznpVar3 = zznpVar2;
                                                    if (i25 != i18) {
                                                    }
                                                }
                                            }
                                            i25 = zza12;
                                            i37 = i37;
                                            zznpVar3 = zznpVar2;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            i37 = i37;
                                            zznpVar3 = zznpVar2;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                    case 30:
                                    case 44:
                                        i17 = i2;
                                        i18 = i38;
                                        if (i34 == 2) {
                                            zzl = zzkx.zzm(bArr, i18, zzmoVar, zzkwVar);
                                            zznpVar4 = this;
                                        } else if (i34 == 0) {
                                            zznpVar4 = this;
                                            zzl = zzkx.zzl(i35, bArr, i18, i2, zzmoVar, zzkwVar);
                                        } else {
                                            unsafe3 = unsafe6;
                                            zznpVar3 = this;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        zzmk zzr2 = zznpVar4.zzr(i10);
                                        zzoi zzoiVar = zznpVar4.zzl;
                                        int i76 = zznz.zza;
                                        if (zzr2 == null) {
                                            i19 = zzl;
                                            unsafe3 = unsafe6;
                                            i20 = i37;
                                        } else if (zzmoVar instanceof RandomAccess) {
                                            int size2 = zzmoVar.size();
                                            i19 = zzl;
                                            Object obj17 = null;
                                            int i77 = 0;
                                            int i78 = 0;
                                            while (i77 < size2) {
                                                int intValue = ((Integer) zzmoVar.get(i77)).intValue();
                                                if (zzr2.zza(intValue)) {
                                                    if (i77 != i78) {
                                                        zzmoVar.set(i78, Integer.valueOf(intValue));
                                                    }
                                                    i78++;
                                                    unsafe4 = unsafe6;
                                                    i21 = i37;
                                                } else {
                                                    unsafe4 = unsafe6;
                                                    i21 = i37;
                                                    obj17 = zznz.zzE(obj7, i21, intValue, obj17, zzoiVar);
                                                }
                                                i77++;
                                                i37 = i21;
                                                unsafe6 = unsafe4;
                                            }
                                            unsafe3 = unsafe6;
                                            i20 = i37;
                                            if (i78 != size2) {
                                                zzmoVar.subList(i78, size2).clear();
                                            }
                                        } else {
                                            i19 = zzl;
                                            unsafe3 = unsafe6;
                                            i20 = i37;
                                            Iterator it = zzmoVar.iterator();
                                            Object obj18 = null;
                                            while (it.hasNext()) {
                                                int intValue2 = ((Integer) it.next()).intValue();
                                                if (!zzr2.zza(intValue2)) {
                                                    obj18 = zznz.zzE(obj7, i20, intValue2, obj18, zzoiVar);
                                                    it.remove();
                                                }
                                            }
                                        }
                                        i37 = i20;
                                        i25 = i19;
                                        zznpVar3 = this;
                                        if (i25 != i18) {
                                        }
                                        break;
                                    case 33:
                                    case 47:
                                        i17 = i2;
                                        i18 = i38;
                                        if (i34 == 2) {
                                            int i79 = zzkx.zza;
                                            zzmg zzmgVar3 = (zzmg) zzmoVar;
                                            i25 = zzkx.zza(bArr, i18, zzkwVar);
                                            int i80 = zzkwVar.zza + i25;
                                            while (i25 < i80) {
                                                i25 = zzkx.zza(bArr, i25, zzkwVar);
                                                zzmgVar3.zzh(zzlj.zzb(zzkwVar.zza));
                                            }
                                            if (i25 != i80) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            unsafe3 = unsafe6;
                                            zznpVar3 = this;
                                            if (i25 != i18) {
                                            }
                                        } else if (i34 == 0) {
                                            int i81 = zzkx.zza;
                                            zzmg zzmgVar4 = (zzmg) zzmoVar;
                                            i25 = zzkx.zza(bArr, i18, zzkwVar);
                                            zzmgVar4.zzh(zzlj.zzb(zzkwVar.zza));
                                            while (i25 < i17) {
                                                int zza14 = zzkx.zza(bArr, i25, zzkwVar);
                                                if (i35 == zzkwVar.zza) {
                                                    i25 = zzkx.zza(bArr, zza14, zzkwVar);
                                                    zzmgVar4.zzh(zzlj.zzb(zzkwVar.zza));
                                                } else {
                                                    unsafe3 = unsafe6;
                                                    zznpVar3 = this;
                                                    if (i25 != i18) {
                                                    }
                                                }
                                            }
                                            unsafe3 = unsafe6;
                                            zznpVar3 = this;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            unsafe3 = unsafe6;
                                            zznpVar3 = this;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                    case 34:
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                                        if (i34 == 2) {
                                            int i82 = zzkx.zza;
                                            zzna zznaVar5 = (zzna) zzmoVar;
                                            i18 = i38;
                                            i25 = zzkx.zza(bArr, i18, zzkwVar);
                                            int i83 = zzkwVar.zza + i25;
                                            while (i25 < i83) {
                                                i25 = zzkx.zzc(bArr, i25, zzkwVar);
                                                zznaVar5.zzf(zzlj.zzc(zzkwVar.zzb));
                                            }
                                            if (i25 != i83) {
                                                throw new zzmr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                            }
                                            i17 = i2;
                                            unsafe3 = unsafe6;
                                            zznpVar3 = this;
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            i18 = i38;
                                            if (i34 == 0) {
                                                int i84 = zzkx.zza;
                                                zzna zznaVar6 = (zzna) zzmoVar;
                                                i25 = zzkx.zzc(bArr, i18, zzkwVar);
                                                zznaVar6.zzf(zzlj.zzc(zzkwVar.zzb));
                                                while (true) {
                                                    i17 = i2;
                                                    if (i25 < i17) {
                                                        int zza15 = zzkx.zza(bArr, i25, zzkwVar);
                                                        if (i35 == zzkwVar.zza) {
                                                            i25 = zzkx.zzc(bArr, zza15, zzkwVar);
                                                            zznaVar6.zzf(zzlj.zzc(zzkwVar.zzb));
                                                        }
                                                    }
                                                }
                                                unsafe3 = unsafe6;
                                                zznpVar3 = this;
                                                if (i25 != i18) {
                                                }
                                            } else {
                                                i17 = i2;
                                                unsafe3 = unsafe6;
                                                zznpVar3 = this;
                                                i25 = i18;
                                                if (i25 != i18) {
                                                }
                                            }
                                        }
                                        break;
                                    default:
                                        i17 = i2;
                                        unsafe3 = unsafe6;
                                        i18 = i38;
                                        if (i34 == 3) {
                                            int i85 = (i35 & (-8)) | 4;
                                            zznx zzp = zzp(i10);
                                            str = str;
                                            zznpVar3 = this;
                                            i25 = zzkx.zzi(zzp, bArr, i18, i2, i85, zzkwVar);
                                            zzmoVar.add(zzkwVar.zzc);
                                            while (i25 < i17) {
                                                int zza16 = zzkx.zza(bArr, i25, zzkwVar);
                                                if (i35 == zzkwVar.zza) {
                                                    i25 = zzkx.zzi(zzp, bArr, zza16, i2, i85, zzkwVar);
                                                    zzmoVar.add(zzkwVar.zzc);
                                                } else if (i25 != i18) {
                                                }
                                            }
                                            if (i25 != i18) {
                                            }
                                        } else {
                                            str = str;
                                            zznpVar3 = this;
                                            i25 = i18;
                                            if (i25 != i18) {
                                            }
                                        }
                                        break;
                                }
                            }
                        } else if (i34 == 2) {
                            zzmo zzmoVar3 = (zzmo) unsafe6.getObject(obj7, j);
                            if (!zzmoVar3.zza()) {
                                int size3 = zzmoVar3.size();
                                zzmoVar3 = zzmoVar3.zzg(size3 == 0 ? 10 : size3 + size3);
                                unsafe6.putObject(obj7, j, zzmoVar3);
                            }
                            i25 = zzkx.zzn(zznpVar6.zzp(i10), i35, bArr, i38, i2, zzmoVar3, zzkwVar);
                            i24 = i3;
                            i28 = i35;
                            zznpVar5 = zznpVar6;
                            i27 = i10;
                            i29 = i7;
                            i26 = i37;
                            i30 = i8;
                            i23 = i2;
                        } else {
                            unsafe2 = unsafe6;
                            i16 = i38;
                            str2 = str;
                            obj2 = obj;
                            i11 = i35;
                            i6 = i16;
                            str = str2;
                            i9 = i37;
                            unsafe = unsafe2;
                            zznpVar5 = this;
                        }
                    } else {
                        int i86 = iArr[zzN + 2];
                        int i87 = 1 << (i86 >>> 20);
                        char c = CharCompanionObject.MAX_VALUE;
                        int i88 = i86 & 1048575;
                        if (i88 != i30) {
                            if (i30 != 1048575) {
                                i12 = i5;
                                unsafe6.putInt(obj7, i30, i29);
                            } else {
                                i12 = i5;
                            }
                            c = CharCompanionObject.MAX_VALUE;
                            i29 = i88 == 1048575 ? 0 : unsafe6.getInt(obj7, i88);
                        } else {
                            i12 = i5;
                            i88 = i30;
                        }
                        switch (zzz) {
                            case 0:
                                i13 = i88;
                                i14 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 1) {
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    i25 = i14 + 8;
                                    i29 |= i87;
                                    zzop.zzm(obj7, j, Double.longBitsToDouble(zzkx.zze(bArr, i14)));
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i27 = i10;
                                    i30 = i13;
                                    i26 = i37;
                                }
                            case 1:
                                i13 = i88;
                                i14 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 5) {
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    i25 = i14 + 4;
                                    i29 |= i87;
                                    zzop.zzk(obj7, j, Float.intBitsToFloat(zzkx.zzd(bArr, i14)));
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i27 = i10;
                                    i30 = i13;
                                    i26 = i37;
                                }
                            case 2:
                            case 3:
                                i13 = i88;
                                i14 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 0) {
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    int zzc3 = zzkx.zzc(bArr, i14, zzkwVar);
                                    unsafe6.putLong(obj, j, zzkwVar.zzb);
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i27 = i10;
                                    i29 = i87 | i29;
                                    i25 = zzc3;
                                    i30 = i13;
                                    i26 = i37;
                                }
                            case 4:
                            case 11:
                                i13 = i88;
                                i14 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 0) {
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    i29 |= i87;
                                    i25 = zzkx.zza(bArr, i14, zzkwVar);
                                    unsafe6.putInt(obj7, j, zzkwVar.zza);
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i27 = i10;
                                    i30 = i13;
                                    i26 = i37;
                                }
                            case 5:
                            case 14:
                                i13 = i88;
                                i14 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 1) {
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    unsafe6.putLong(obj, j, zzkx.zze(bArr, i14));
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i27 = i10;
                                    i29 = i87 | i29;
                                    i25 = i14 + 8;
                                    i30 = i13;
                                    i26 = i37;
                                }
                            case 6:
                            case 13:
                                i13 = i88;
                                i14 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 5) {
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    i25 = i14 + 4;
                                    i29 |= i87;
                                    unsafe6.putInt(obj7, j, zzkx.zzd(bArr, i14));
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i27 = i10;
                                    i30 = i13;
                                    i26 = i37;
                                }
                            case 7:
                                i13 = i88;
                                i14 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 0) {
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    i29 |= i87;
                                    i25 = zzkx.zzc(bArr, i14, zzkwVar);
                                    zzop.zzi(obj7, j, zzkwVar.zzb != 0);
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i27 = i10;
                                    i30 = i13;
                                    i26 = i37;
                                }
                            case 8:
                                i13 = i88;
                                i14 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 2) {
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    if ((i36 & 536870912) != 0) {
                                        i29 |= i87;
                                        i25 = zzkx.zzf(bArr, i14, zzkwVar);
                                    } else {
                                        i25 = zzkx.zza(bArr, i14, zzkwVar);
                                        int i89 = zzkwVar.zza;
                                        if (i89 < 0) {
                                            throw new zzmr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                        }
                                        int i90 = i29 | i87;
                                        if (i89 == 0) {
                                            zzkwVar.zzc = "";
                                            i29 = i90;
                                        } else {
                                            zzkwVar.zzc = new String(bArr, i25, i89, zzmp.zza);
                                            i25 += i89;
                                            i29 = i90;
                                        }
                                    }
                                    unsafe6.putObject(obj7, j, zzkwVar.zzc);
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i27 = i10;
                                    i30 = i13;
                                    i26 = i37;
                                }
                            case 9:
                                i13 = i88;
                                int i91 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 2) {
                                    i14 = i91;
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    Object zzs = zzs(obj7, i10);
                                    i25 = zzkx.zzj(zzs, zzp(i10), bArr, i91, i2, zzkwVar);
                                    zzt(obj7, i10, zzs);
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i29 |= i87;
                                    i27 = i10;
                                    i30 = i13;
                                    i26 = i37;
                                    i23 = i2;
                                }
                            case 10:
                                i13 = i88;
                                int i92 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 2) {
                                    i14 = i92;
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    i29 |= i87;
                                    i25 = zzkx.zzg(bArr, i92, zzkwVar);
                                    unsafe6.putObject(obj7, j, zzkwVar.zzc);
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    zznpVar5 = this;
                                    i27 = i10;
                                    i30 = i13;
                                    i26 = i37;
                                }
                            case 12:
                                i13 = i88;
                                int i93 = i12;
                                i15 = i35;
                                i10 = zzN;
                                if (i34 != 0) {
                                    i14 = i93;
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    i25 = zzkx.zza(bArr, i93, zzkwVar);
                                    int i94 = zzkwVar.zza;
                                    zzmk zzr3 = zzr(i10);
                                    if ((i36 & Integer.MIN_VALUE) == 0 || zzr3 == null || zzr3.zza(i94)) {
                                        i29 |= i87;
                                        unsafe6.putInt(obj7, j, i94);
                                        i23 = i2;
                                        i24 = i3;
                                        i28 = i15;
                                        zznpVar5 = this;
                                        i27 = i10;
                                        i30 = i13;
                                        i26 = i37;
                                    } else {
                                        zzg(obj).zzk(i15, Long.valueOf(i94));
                                        i23 = i2;
                                        i24 = i3;
                                        i28 = i15;
                                        zznpVar5 = this;
                                        i27 = i10;
                                        i30 = i13;
                                        i26 = i37;
                                    }
                                }
                                break;
                            case 15:
                                i13 = i88;
                                int i95 = i12;
                                i15 = i35;
                                i10 = zzN;
                                if (i34 != 0) {
                                    zznpVar = this;
                                    i14 = i95;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    i29 |= i87;
                                    i25 = zzkx.zza(bArr, i95, zzkwVar);
                                    unsafe6.putInt(obj7, j, zzlj.zzb(zzkwVar.zza));
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i15;
                                    i27 = i10;
                                    i30 = i13;
                                    i26 = i37;
                                    zznpVar5 = this;
                                }
                            case 16:
                                if (i34 != 0) {
                                    i13 = i88;
                                    i15 = i35;
                                    i10 = zzN;
                                    zznpVar = this;
                                    i14 = i12;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    int i96 = i29 | i87;
                                    int zzc4 = zzkx.zzc(bArr, i12, zzkwVar);
                                    unsafe6.putLong(obj, j, zzlj.zzc(zzkwVar.zzb));
                                    i23 = i2;
                                    i24 = i3;
                                    i28 = i35;
                                    i29 = i96;
                                    i27 = zzN;
                                    i25 = zzc4;
                                    i30 = i88;
                                    i26 = i37;
                                    zznpVar5 = this;
                                }
                            default:
                                i13 = i88;
                                i14 = i12;
                                i10 = zzN;
                                i15 = i35;
                                if (i34 != 3) {
                                    zznpVar = this;
                                    obj2 = obj7;
                                    i11 = i15;
                                    i7 = i29;
                                    i6 = i14;
                                    unsafe = unsafe6;
                                    zznpVar5 = zznpVar;
                                    i8 = i13;
                                    i9 = i37;
                                    break;
                                } else {
                                    Object zzs2 = zzs(obj7, i10);
                                    i25 = zzkx.zzk(zzs2, zzp(i10), bArr, i14, i2, (i37 << 3) | 4, zzkwVar);
                                    zzt(obj7, i10, zzs2);
                                    i24 = i3;
                                    i28 = i15;
                                    i29 |= i87;
                                    i27 = i10;
                                    zznpVar5 = this;
                                    i30 = i13;
                                    i26 = i37;
                                    i23 = i2;
                                }
                        }
                    }
                } else {
                    obj2 = obj7;
                    i6 = i5;
                    i7 = i29;
                    i8 = i30;
                    str = "Failed to parse the message.";
                    unsafe = unsafe6;
                    i9 = i33;
                    i10 = 0;
                    i11 = i4;
                }
                i24 = i3;
                if (i11 != i24 || i24 == 0) {
                    if (zznpVar5.zzh) {
                        zzlr zzlrVar = zzkwVar.zzd;
                        int i97 = zzlr.zzb;
                        int i98 = zznu.zza;
                        if (zzlrVar != zzlr.zza) {
                            zznm zznmVar = zznpVar5.zzg;
                            int i99 = zzkx.zza;
                            if (zzlrVar.zzb(zznmVar, i9) != null) {
                                throw null;
                            }
                            i25 = zzkx.zzo(i11, bArr, i6, i2, zzg(obj), zzkwVar);
                            i28 = i11;
                            i26 = i9;
                            i27 = i10;
                            i29 = i7;
                            i30 = i8;
                            unsafe6 = unsafe;
                            obj7 = obj2;
                            i23 = i2;
                        }
                    }
                    i25 = zzkx.zzo(i11, bArr, i6, i2, zzg(obj), zzkwVar);
                    i28 = i11;
                    i26 = i9;
                    i27 = i10;
                    i29 = i7;
                    i30 = i8;
                    unsafe6 = unsafe;
                    obj7 = obj2;
                    i23 = i2;
                } else {
                    i25 = i6;
                    i28 = i11;
                    i29 = i7;
                    i30 = i8;
                }
            } else {
                obj2 = obj7;
                str = "Failed to parse the message.";
                unsafe = unsafe6;
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzkw zzkwVar) throws IOException {
        zzh(obj, bArr, i, i2, 0, zzkwVar);
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final void zzj(Object obj) {
        if (zzA(obj)) {
            if (obj instanceof zzmf) {
                zzmf zzmfVar = (zzmf) obj;
                zzmfVar.zzcm(Integer.MAX_VALUE);
                zzmfVar.zza = 0;
                zzmfVar.zzcg();
            }
            int[] iArr = this.zzc;
            for (int i = 0; i < iArr.length; i += 3) {
                int zzx = zzx(i);
                long j = 1048575 & zzx;
                switch (zzz(zzx)) {
                    case 9:
                    case 17:
                        if (zzJ(obj, i)) {
                            zzp(i).zzj(zzb.getObject(obj, j));
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
                        ((zzmo) zzop.zzn(obj, j)).zzb();
                        break;
                    case 50:
                        Unsafe unsafe = zzb;
                        Object object = unsafe.getObject(obj, j);
                        if (object != null) {
                            ((zzng) object).zzd();
                            unsafe.putObject(obj, j, object);
                            break;
                        } else {
                            break;
                        }
                    case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    case 68:
                        if (zzL(obj, iArr[i], i)) {
                            zzp(i).zzj(zzb.getObject(obj, j));
                            break;
                        } else {
                            break;
                        }
                }
            }
            this.zzl.zzb(obj);
            if (this.zzh) {
                this.zzm.zza(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznx
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.zzj) {
            int[] iArr = this.zzi;
            int[] iArr2 = this.zzc;
            int i6 = iArr[i4];
            int i7 = iArr2[i6];
            int zzx = zzx(i6);
            int i8 = iArr2[i6 + 2];
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
            if ((268435456 & zzx) != 0 && !zzI(obj, i6, i, i2, i10)) {
                return false;
            }
            switch (zzz(zzx)) {
                case 9:
                case 17:
                    if (zzI(obj, i6, i, i2, i10) && !zzw(obj, zzx, zzp(i6))) {
                        return false;
                    }
                    break;
                case 27:
                case 49:
                    List list = (List) zzop.zzn(obj, zzx & 1048575);
                    if (!list.isEmpty()) {
                        zznx zzp = zzp(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!zzp.zzk(list.get(i11))) {
                                return false;
                            }
                        }
                        break;
                    } else {
                        continue;
                    }
                case 50:
                    zzng zzngVar = (zzng) zzop.zzn(obj, zzx & 1048575);
                    if (!zzngVar.isEmpty() && ((zznf) zzq(i6)).zze().zzc.zza() == zzou.MESSAGE) {
                        zznx zznxVar = null;
                        for (Object obj2 : zzngVar.values()) {
                            if (zznxVar == null) {
                                zznxVar = zznu.zza().zzb(obj2.getClass());
                            }
                            if (!zznxVar.zzk(obj2)) {
                                return false;
                            }
                        }
                        break;
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case 68:
                    if (zzL(obj, i7, i6) && !zzw(obj, zzx, zzp(i6))) {
                        return false;
                    }
                    break;
            }
            i4++;
            i5 = i;
            i3 = i2;
        }
        return !this.zzh || ((zzmc) obj).zzb.zze();
    }
}
