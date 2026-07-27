package com.google.android.libraries.places.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzaeu<T> implements zzafc<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzagd.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final zzaer zze;
    private final boolean zzf;
    private final boolean zzg;
    private final int[] zzh;
    private final int zzi;
    private final int zzj;
    private final zzaef zzk;
    private final zzaft zzl;
    private final zzada zzm;
    private final zzaew zzn;
    private final zzaem zzo;

    private zzaeu(int[] iArr, Object[] objArr, int i, int i2, zzaer zzaerVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzaew zzaewVar, zzaef zzaefVar, zzaft zzaftVar, zzada zzadaVar, zzaem zzaemVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzg = z;
        boolean z3 = false;
        if (zzadaVar != null && zzadaVar.zzc(zzaerVar)) {
            z3 = true;
        }
        this.zzf = z3;
        this.zzh = iArr2;
        this.zzi = i3;
        this.zzj = i4;
        this.zzn = zzaewVar;
        this.zzk = zzaefVar;
        this.zzl = zzaftVar;
        this.zzm = zzadaVar;
        this.zze = zzaerVar;
        this.zzo = zzaemVar;
    }

    private final boolean zzA(Object obj, int i) {
        int zzo = zzo(i);
        long j = zzo & 1048575;
        if (j != 1048575) {
            return (zzagd.zzc(obj, j) & (1 << (zzo >>> 20))) != 0;
        }
        int zzq = zzq(i);
        long j2 = zzq & 1048575;
        switch (zzp(zzq)) {
            case 0:
                return Double.doubleToRawLongBits(zzagd.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzagd.zzb(obj, j2)) != 0;
            case 2:
                return zzagd.zzd(obj, j2) != 0;
            case 3:
                return zzagd.zzd(obj, j2) != 0;
            case 4:
                return zzagd.zzc(obj, j2) != 0;
            case 5:
                return zzagd.zzd(obj, j2) != 0;
            case 6:
                return zzagd.zzc(obj, j2) != 0;
            case 7:
                return zzagd.zzw(obj, j2);
            case 8:
                Object zzf = zzagd.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                }
                if (zzf instanceof zzacp) {
                    return !zzacp.zzb.equals(zzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzagd.zzf(obj, j2) != null;
            case 10:
                return !zzacp.zzb.equals(zzagd.zzf(obj, j2));
            case 11:
                return zzagd.zzc(obj, j2) != 0;
            case 12:
                return zzagd.zzc(obj, j2) != 0;
            case 13:
                return zzagd.zzc(obj, j2) != 0;
            case 14:
                return zzagd.zzd(obj, j2) != 0;
            case 15:
                return zzagd.zzc(obj, j2) != 0;
            case 16:
                return zzagd.zzd(obj, j2) != 0;
            case 17:
                return zzagd.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzB(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzA(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzC(Object obj, int i, zzafc zzafcVar) {
        return zzafcVar.zzf(zzagd.zzf(obj, i & 1048575));
    }

    private final boolean zzD(Object obj, int i, int i2) {
        return zzagd.zzc(obj, (long) (zzo(i2) & 1048575)) == i;
    }

    private static boolean zzE(Object obj, long j) {
        return ((Boolean) zzagd.zzf(obj, j)).booleanValue();
    }

    private final void zzF(Object obj, zzacy zzacyVar) throws IOException {
        int i;
        if (this.zzf) {
            this.zzm.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            int zzq = zzq(i4);
            int[] iArr = this.zzc;
            int i6 = iArr[i4];
            int zzp = zzp(zzq);
            if (zzp <= 17) {
                int i7 = iArr[i4 + 2];
                int i8 = i7 & i2;
                if (i8 != i3) {
                    i5 = unsafe.getInt(obj, i8);
                    i3 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            long j = zzq & i2;
            switch (zzp) {
                case 0:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzf(i6, zzagd.zza(obj, j));
                        break;
                    }
                case 1:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzn(i6, zzagd.zzb(obj, j));
                        break;
                    }
                case 2:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzs(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 3:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzH(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 4:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzq(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 5:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzl(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 6:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzj(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 7:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzb(i6, zzagd.zzw(obj, j));
                        break;
                    }
                case 8:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzH(i6, unsafe.getObject(obj, j), zzacyVar);
                        break;
                    }
                case 9:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzu(i6, unsafe.getObject(obj, j), zzs(i4));
                        break;
                    }
                case 10:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzd(i6, (zzacp) unsafe.getObject(obj, j));
                        break;
                    }
                case 11:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzF(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 12:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzh(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 13:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzv(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 14:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzx(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 15:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzz(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 16:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzB(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 17:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacyVar.zzp(i6, unsafe.getObject(obj, j), zzs(i4));
                        break;
                    }
                case 18:
                    zzafe.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 19:
                    zzafe.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 20:
                    zzafe.zzQ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 21:
                    zzafe.zzY(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 22:
                    zzafe.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 23:
                    zzafe.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 24:
                    zzafe.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 25:
                    zzafe.zzH(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 26:
                    zzafe.zzW(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar);
                    break;
                case 27:
                    zzafe.zzR(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, zzs(i4));
                    break;
                case 28:
                    zzafe.zzI(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    zzafe.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 30:
                    zzafe.zzK(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 31:
                    zzafe.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 32:
                    zzafe.zzT(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 33:
                    zzafe.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 34:
                    zzafe.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, false);
                    break;
                case 35:
                    zzafe.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 36:
                    zzafe.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 37:
                    zzafe.zzQ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 38:
                    zzafe.zzY(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 39:
                    zzafe.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 40:
                    zzafe.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 41:
                    zzafe.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 42:
                    zzafe.zzH(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 43:
                    zzafe.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 44:
                    zzafe.zzK(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 45:
                    zzafe.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 46:
                    zzafe.zzT(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 47:
                    zzafe.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    zzafe.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, true);
                    break;
                case 49:
                    zzafe.zzO(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacyVar, zzs(i4));
                    break;
                case 50:
                    zzG(zzacyVar, i6, unsafe.getObject(obj, j), i4);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzf(i6, zzj(obj, j));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzn(i6, zzk(obj, j));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzs(i6, zzr(obj, j));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzH(i6, zzr(obj, j));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzq(i6, zzn(obj, j));
                        break;
                    }
                case 56:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzl(i6, zzr(obj, j));
                        break;
                    }
                case 57:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzj(i6, zzn(obj, j));
                        break;
                    }
                case 58:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzb(i6, zzE(obj, j));
                        break;
                    }
                case 59:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzH(i6, unsafe.getObject(obj, j), zzacyVar);
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzu(i6, unsafe.getObject(obj, j), zzs(i4));
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzd(i6, (zzacp) unsafe.getObject(obj, j));
                        break;
                    }
                case 62:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzF(i6, zzn(obj, j));
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzh(i6, zzn(obj, j));
                        break;
                    }
                case 64:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzv(i6, zzn(obj, j));
                        break;
                    }
                case 65:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzx(i6, zzr(obj, j));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzz(i6, zzn(obj, j));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzB(i6, zzr(obj, j));
                        break;
                    }
                case 68:
                    if (!zzD(obj, i6, i4)) {
                        break;
                    } else {
                        zzacyVar.zzp(i6, unsafe.getObject(obj, j), zzs(i4));
                        break;
                    }
            }
            i4 += 3;
            i2 = 1048575;
        }
        zzaft zzaftVar = this.zzl;
        zzaftVar.zzg(zzaftVar.zzc(obj), zzacyVar);
    }

    private final void zzG(zzacy zzacyVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private static final void zzH(int i, Object obj, zzacy zzacyVar) throws IOException {
        if (obj instanceof String) {
            zzacyVar.zzD(i, (String) obj);
        } else {
            zzacyVar.zzd(i, (zzacp) obj);
        }
    }

    static zzaeu zzg(Class cls, zzaeo zzaeoVar, zzaew zzaewVar, zzaef zzaefVar, zzaft zzaftVar, zzada zzadaVar, zzaem zzaemVar) {
        if (zzaeoVar instanceof zzafb) {
            return zzh((zzafb) zzaeoVar, zzaewVar, zzaefVar, zzaftVar, zzadaVar, zzaemVar);
        }
        throw null;
    }

    static zzaeu zzh(zzafb zzafbVar, zzaew zzaewVar, zzaef zzaefVar, zzaft zzaftVar, zzada zzadaVar, zzaem zzaemVar) {
        int i;
        int charAt;
        int charAt2;
        int charAt3;
        int[] iArr;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        char charAt4;
        int i7;
        char charAt5;
        int i8;
        char charAt6;
        int i9;
        char charAt7;
        int i10;
        char charAt8;
        int i11;
        char charAt9;
        int i12;
        char charAt10;
        int i13;
        char charAt11;
        int i14;
        int i15;
        int i16;
        int[] iArr2;
        int i17;
        int i18;
        int i19;
        int objectFieldOffset;
        Object[] objArr;
        String str;
        int i20;
        int i21;
        int i22;
        int i23;
        Field zzu;
        char charAt12;
        int i24;
        Field zzu2;
        Field zzu3;
        int i25;
        char charAt13;
        int i26;
        char charAt14;
        int i27;
        char charAt15;
        int i28;
        char charAt16;
        boolean z = zzafbVar.zzc() == 2;
        String zzd = zzafbVar.zzd();
        int length = zzd.length();
        char c = 55296;
        if (zzd.charAt(0) >= 55296) {
            int i29 = 1;
            while (true) {
                i = i29 + 1;
                if (zzd.charAt(i29) < 55296) {
                    break;
                }
                i29 = i;
            }
        } else {
            i = 1;
        }
        int i30 = i + 1;
        int charAt17 = zzd.charAt(i);
        if (charAt17 >= 55296) {
            int i31 = charAt17 & 8191;
            int i32 = 13;
            while (true) {
                i28 = i30 + 1;
                charAt16 = zzd.charAt(i30);
                if (charAt16 < 55296) {
                    break;
                }
                i31 |= (charAt16 & 8191) << i32;
                i32 += 13;
                i30 = i28;
            }
            charAt17 = i31 | (charAt16 << i32);
            i30 = i28;
        }
        if (charAt17 == 0) {
            charAt = 0;
            i5 = 0;
            charAt2 = 0;
            i4 = 0;
            charAt3 = 0;
            i2 = 0;
            iArr = zza;
            i3 = 0;
        } else {
            int i33 = i30 + 1;
            int charAt18 = zzd.charAt(i30);
            if (charAt18 >= 55296) {
                int i34 = charAt18 & 8191;
                int i35 = 13;
                while (true) {
                    i13 = i33 + 1;
                    charAt11 = zzd.charAt(i33);
                    if (charAt11 < 55296) {
                        break;
                    }
                    i34 |= (charAt11 & 8191) << i35;
                    i35 += 13;
                    i33 = i13;
                }
                charAt18 = i34 | (charAt11 << i35);
                i33 = i13;
            }
            int i36 = i33 + 1;
            int charAt19 = zzd.charAt(i33);
            if (charAt19 >= 55296) {
                int i37 = charAt19 & 8191;
                int i38 = 13;
                while (true) {
                    i12 = i36 + 1;
                    charAt10 = zzd.charAt(i36);
                    if (charAt10 < 55296) {
                        break;
                    }
                    i37 |= (charAt10 & 8191) << i38;
                    i38 += 13;
                    i36 = i12;
                }
                charAt19 = i37 | (charAt10 << i38);
                i36 = i12;
            }
            int i39 = i36 + 1;
            charAt = zzd.charAt(i36);
            if (charAt >= 55296) {
                int i40 = charAt & 8191;
                int i41 = 13;
                while (true) {
                    i11 = i39 + 1;
                    charAt9 = zzd.charAt(i39);
                    if (charAt9 < 55296) {
                        break;
                    }
                    i40 |= (charAt9 & 8191) << i41;
                    i41 += 13;
                    i39 = i11;
                }
                charAt = i40 | (charAt9 << i41);
                i39 = i11;
            }
            int i42 = i39 + 1;
            int charAt20 = zzd.charAt(i39);
            if (charAt20 >= 55296) {
                int i43 = charAt20 & 8191;
                int i44 = 13;
                while (true) {
                    i10 = i42 + 1;
                    charAt8 = zzd.charAt(i42);
                    if (charAt8 < 55296) {
                        break;
                    }
                    i43 |= (charAt8 & 8191) << i44;
                    i44 += 13;
                    i42 = i10;
                }
                charAt20 = i43 | (charAt8 << i44);
                i42 = i10;
            }
            int i45 = i42 + 1;
            charAt2 = zzd.charAt(i42);
            if (charAt2 >= 55296) {
                int i46 = charAt2 & 8191;
                int i47 = 13;
                while (true) {
                    i9 = i45 + 1;
                    charAt7 = zzd.charAt(i45);
                    if (charAt7 < 55296) {
                        break;
                    }
                    i46 |= (charAt7 & 8191) << i47;
                    i47 += 13;
                    i45 = i9;
                }
                charAt2 = i46 | (charAt7 << i47);
                i45 = i9;
            }
            int i48 = i45 + 1;
            int charAt21 = zzd.charAt(i45);
            if (charAt21 >= 55296) {
                int i49 = charAt21 & 8191;
                int i50 = 13;
                while (true) {
                    i8 = i48 + 1;
                    charAt6 = zzd.charAt(i48);
                    if (charAt6 < 55296) {
                        break;
                    }
                    i49 |= (charAt6 & 8191) << i50;
                    i50 += 13;
                    i48 = i8;
                }
                charAt21 = i49 | (charAt6 << i50);
                i48 = i8;
            }
            int i51 = i48 + 1;
            int charAt22 = zzd.charAt(i48);
            if (charAt22 >= 55296) {
                int i52 = charAt22 & 8191;
                int i53 = 13;
                while (true) {
                    i7 = i51 + 1;
                    charAt5 = zzd.charAt(i51);
                    if (charAt5 < 55296) {
                        break;
                    }
                    i52 |= (charAt5 & 8191) << i53;
                    i53 += 13;
                    i51 = i7;
                }
                charAt22 = i52 | (charAt5 << i53);
                i51 = i7;
            }
            int i54 = i51 + 1;
            charAt3 = zzd.charAt(i51);
            if (charAt3 >= 55296) {
                int i55 = charAt3 & 8191;
                int i56 = 13;
                while (true) {
                    i6 = i54 + 1;
                    charAt4 = zzd.charAt(i54);
                    if (charAt4 < 55296) {
                        break;
                    }
                    i55 |= (charAt4 & 8191) << i56;
                    i56 += 13;
                    i54 = i6;
                }
                charAt3 = i55 | (charAt4 << i56);
                i54 = i6;
            }
            iArr = new int[charAt3 + charAt21 + charAt22];
            i2 = charAt18 + charAt18 + charAt19;
            i3 = charAt18;
            i30 = i54;
            int i57 = charAt21;
            i4 = charAt20;
            i5 = i57;
        }
        Unsafe unsafe = zzb;
        Object[] zze = zzafbVar.zze();
        Class<?> cls = zzafbVar.zza().getClass();
        int[] iArr3 = new int[charAt2 * 3];
        Object[] objArr2 = new Object[charAt2 + charAt2];
        int i58 = charAt3 + i5;
        int i59 = charAt3;
        int i60 = i58;
        int i61 = 0;
        int i62 = 0;
        while (i30 < length) {
            int i63 = i30 + 1;
            int charAt23 = zzd.charAt(i30);
            if (charAt23 >= c) {
                int i64 = charAt23 & 8191;
                int i65 = i63;
                int i66 = 13;
                while (true) {
                    i27 = i65 + 1;
                    charAt15 = zzd.charAt(i65);
                    if (charAt15 < c) {
                        break;
                    }
                    i64 |= (charAt15 & 8191) << i66;
                    i66 += 13;
                    i65 = i27;
                }
                charAt23 = i64 | (charAt15 << i66);
                i14 = i27;
            } else {
                i14 = i63;
            }
            int i67 = i14 + 1;
            int charAt24 = zzd.charAt(i14);
            if (charAt24 >= c) {
                int i68 = charAt24 & 8191;
                int i69 = i67;
                int i70 = 13;
                while (true) {
                    i26 = i69 + 1;
                    charAt14 = zzd.charAt(i69);
                    i15 = length;
                    if (charAt14 < 55296) {
                        break;
                    }
                    i68 |= (charAt14 & 8191) << i70;
                    i70 += 13;
                    i69 = i26;
                    length = i15;
                }
                charAt24 = i68 | (charAt14 << i70);
                i16 = i26;
            } else {
                i15 = length;
                i16 = i67;
            }
            int i71 = charAt24 & 255;
            int i72 = charAt3;
            if ((charAt24 & 1024) != 0) {
                iArr[i62] = i61;
                i62++;
            }
            if (i71 >= 51) {
                int i73 = i16 + 1;
                int charAt25 = zzd.charAt(i16);
                if (charAt25 >= 55296) {
                    int i74 = charAt25 & 8191;
                    int i75 = i73;
                    int i76 = 13;
                    while (true) {
                        i25 = i75 + 1;
                        charAt13 = zzd.charAt(i75);
                        i18 = i4;
                        if (charAt13 < 55296) {
                            break;
                        }
                        i74 |= (charAt13 & 8191) << i76;
                        i76 += 13;
                        i75 = i25;
                        i4 = i18;
                    }
                    charAt25 = i74 | (charAt13 << i76);
                    i24 = i25;
                } else {
                    i18 = i4;
                    i24 = i73;
                }
                int i77 = i71 - 51;
                i22 = i24;
                if (i77 == 9 || i77 == 17) {
                    int i78 = i61 / 3;
                    objArr2[i78 + i78 + 1] = zze[i2];
                    i2++;
                } else if (i77 == 12 && !z) {
                    int i79 = i61 / 3;
                    objArr2[i79 + i79 + 1] = zze[i2];
                    i2++;
                }
                int i80 = charAt25 + charAt25;
                Object obj = zze[i80];
                if (obj instanceof Field) {
                    zzu2 = (Field) obj;
                } else {
                    zzu2 = zzu(cls, (String) obj);
                    zze[i80] = zzu2;
                }
                iArr2 = iArr3;
                i17 = charAt;
                int objectFieldOffset2 = (int) unsafe.objectFieldOffset(zzu2);
                int i81 = i80 + 1;
                Object obj2 = zze[i81];
                if (obj2 instanceof Field) {
                    zzu3 = (Field) obj2;
                } else {
                    zzu3 = zzu(cls, (String) obj2);
                    zze[i81] = zzu3;
                }
                str = zzd;
                i21 = (int) unsafe.objectFieldOffset(zzu3);
                objArr = objArr2;
                objectFieldOffset = objectFieldOffset2;
                i23 = 0;
            } else {
                iArr2 = iArr3;
                i17 = charAt;
                i18 = i4;
                int i82 = i2 + 1;
                Field zzu4 = zzu(cls, (String) zze[i2]);
                if (i71 != 9 && i71 != 17) {
                    if (i71 == 27 || i71 == 49) {
                        int i83 = i61 / 3;
                        objArr2[i83 + i83 + 1] = zze[i82];
                        i82++;
                    } else if (i71 == 12 || i71 == 30 || i71 == 44) {
                        if (!z) {
                            int i84 = i61 / 3;
                            objArr2[i84 + i84 + 1] = zze[i82];
                            i82++;
                        }
                    } else if (i71 == 50) {
                        int i85 = i59 + 1;
                        iArr[i59] = i61;
                        int i86 = i61 / 3;
                        int i87 = i86 + i86;
                        int i88 = i82 + 1;
                        objArr2[i87] = zze[i82];
                        if ((charAt24 & 2048) != 0) {
                            i82 = i88 + 1;
                            objArr2[i87 + 1] = zze[i88];
                            i59 = i85;
                        } else {
                            i59 = i85;
                            i82 = i88;
                        }
                    }
                    i19 = i82;
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zzu4);
                    objArr = objArr2;
                    if ((charAt24 & 4096) == 4096 || i71 > 17) {
                        str = zzd;
                        i20 = i19;
                        i21 = 1048575;
                        i22 = i16;
                        i23 = 0;
                    } else {
                        int i89 = i16 + 1;
                        int charAt26 = zzd.charAt(i16);
                        if (charAt26 >= 55296) {
                            int i90 = charAt26 & 8191;
                            int i91 = 13;
                            while (true) {
                                i22 = i89 + 1;
                                charAt12 = zzd.charAt(i89);
                                if (charAt12 < 55296) {
                                    break;
                                }
                                i90 |= (charAt12 & 8191) << i91;
                                i91 += 13;
                                i89 = i22;
                            }
                            charAt26 = i90 | (charAt12 << i91);
                        } else {
                            i22 = i89;
                        }
                        int i92 = i3 + i3 + (charAt26 / 32);
                        Object obj3 = zze[i92];
                        str = zzd;
                        if (obj3 instanceof Field) {
                            zzu = (Field) obj3;
                        } else {
                            zzu = zzu(cls, (String) obj3);
                            zze[i92] = zzu;
                        }
                        i20 = i19;
                        i21 = (int) unsafe.objectFieldOffset(zzu);
                        i23 = charAt26 % 32;
                    }
                    if (i71 >= 18 || i71 > 49) {
                        i2 = i20;
                    } else {
                        iArr[i60] = objectFieldOffset;
                        i2 = i20;
                        i60++;
                    }
                }
                int i93 = i61 / 3;
                objArr2[i93 + i93 + 1] = zzu4.getType();
                i19 = i82;
                objectFieldOffset = (int) unsafe.objectFieldOffset(zzu4);
                objArr = objArr2;
                if ((charAt24 & 4096) == 4096) {
                }
                str = zzd;
                i20 = i19;
                i21 = 1048575;
                i22 = i16;
                i23 = 0;
                if (i71 >= 18) {
                }
                i2 = i20;
            }
            int i94 = i61 + 1;
            iArr2[i61] = charAt23;
            int i95 = i94 + 1;
            iArr2[i94] = ((charAt24 & 256) != 0 ? 268435456 : 0) | ((charAt24 & 512) != 0 ? 536870912 : 0) | (i71 << 20) | objectFieldOffset;
            i61 = i95 + 1;
            iArr2[i95] = (i23 << 20) | i21;
            charAt = i17;
            charAt3 = i72;
            i30 = i22;
            length = i15;
            objArr2 = objArr;
            zzd = str;
            iArr3 = iArr2;
            i4 = i18;
            c = 55296;
        }
        return new zzaeu(iArr3, objArr2, charAt, i4, zzafbVar.zza(), z, false, iArr, charAt3, i58, zzaewVar, zzaefVar, zzaftVar, zzadaVar, zzaemVar, null);
    }

    private static double zzj(Object obj, long j) {
        return ((Double) zzagd.zzf(obj, j)).doubleValue();
    }

    private static float zzk(Object obj, long j) {
        return ((Float) zzagd.zzf(obj, j)).floatValue();
    }

    private final int zzl(Object obj) {
        int i;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < this.zzc.length) {
            int zzq = zzq(i4);
            int[] iArr = this.zzc;
            int i7 = iArr[i4];
            int zzp = zzp(zzq);
            if (zzp <= 17) {
                int i8 = iArr[i4 + 2];
                int i9 = i8 & i2;
                i = 1 << (i8 >>> 20);
                if (i9 != i3) {
                    i6 = unsafe.getInt(obj, i9);
                    i3 = i9;
                }
            } else {
                i = 0;
            }
            long j = zzq & i2;
            switch (zzp) {
                case 0:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 8;
                        break;
                    }
                case 1:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 4;
                        break;
                    }
                case 2:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzB(unsafe.getLong(obj, j));
                        break;
                    }
                case 3:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzB(unsafe.getLong(obj, j));
                        break;
                    }
                case 4:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzv(unsafe.getInt(obj, j));
                        break;
                    }
                case 5:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 8;
                        break;
                    }
                case 6:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 4;
                        break;
                    }
                case 7:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 1;
                        break;
                    }
                case 8:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (!(object instanceof zzacp)) {
                            i5 += zzacx.zzA(i7 << 3) + zzacx.zzy((String) object);
                            break;
                        } else {
                            int zzA = zzacx.zzA(i7 << 3);
                            int zzd = ((zzacp) object).zzd();
                            i5 += zzA + zzacx.zzA(zzd) + zzd;
                            break;
                        }
                    }
                case 9:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzafe.zzo(i7, unsafe.getObject(obj, j), zzs(i4));
                        break;
                    }
                case 10:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        zzacp zzacpVar = (zzacp) unsafe.getObject(obj, j);
                        int zzA2 = zzacx.zzA(i7 << 3);
                        int zzd2 = zzacpVar.zzd();
                        i5 += zzA2 + zzacx.zzA(zzd2) + zzd2;
                        break;
                    }
                case 11:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzA(unsafe.getInt(obj, j));
                        break;
                    }
                case 12:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzv(unsafe.getInt(obj, j));
                        break;
                    }
                case 13:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 4;
                        break;
                    }
                case 14:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 8;
                        break;
                    }
                case 15:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        int i10 = unsafe.getInt(obj, j);
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzA((i10 >> 31) ^ (i10 + i10));
                        break;
                    }
                case 16:
                    if ((i & i6) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzB((j2 >> 63) ^ (j2 + j2));
                        break;
                    }
                case 17:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacx.zzu(i7, (zzaer) unsafe.getObject(obj, j), zzs(i4));
                        break;
                    }
                case 18:
                    i5 += zzafe.zzh(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 19:
                    i5 += zzafe.zzf(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 20:
                    i5 += zzafe.zzm(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 21:
                    i5 += zzafe.zzx(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 22:
                    i5 += zzafe.zzk(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 23:
                    i5 += zzafe.zzh(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 24:
                    i5 += zzafe.zzf(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 25:
                    i5 += zzafe.zza(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 26:
                    i5 += zzafe.zzu(i7, (List) unsafe.getObject(obj, j));
                    break;
                case 27:
                    i5 += zzafe.zzp(i7, (List) unsafe.getObject(obj, j), zzs(i4));
                    break;
                case 28:
                    i5 += zzafe.zzc(i7, (List) unsafe.getObject(obj, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    i5 += zzafe.zzv(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 30:
                    i5 += zzafe.zzd(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 31:
                    i5 += zzafe.zzf(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 32:
                    i5 += zzafe.zzh(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 33:
                    i5 += zzafe.zzq(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 34:
                    i5 += zzafe.zzs(i7, (List) unsafe.getObject(obj, j), false);
                    break;
                case 35:
                    int zzi = zzafe.zzi((List) unsafe.getObject(obj, j));
                    if (zzi <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzi) + zzi;
                        break;
                    }
                case 36:
                    int zzg = zzafe.zzg((List) unsafe.getObject(obj, j));
                    if (zzg <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzg) + zzg;
                        break;
                    }
                case 37:
                    int zzn = zzafe.zzn((List) unsafe.getObject(obj, j));
                    if (zzn <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzn) + zzn;
                        break;
                    }
                case 38:
                    int zzy = zzafe.zzy((List) unsafe.getObject(obj, j));
                    if (zzy <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzy) + zzy;
                        break;
                    }
                case 39:
                    int zzl = zzafe.zzl((List) unsafe.getObject(obj, j));
                    if (zzl <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzl) + zzl;
                        break;
                    }
                case 40:
                    int zzi2 = zzafe.zzi((List) unsafe.getObject(obj, j));
                    if (zzi2 <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzi2) + zzi2;
                        break;
                    }
                case 41:
                    int zzg2 = zzafe.zzg((List) unsafe.getObject(obj, j));
                    if (zzg2 <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzg2) + zzg2;
                        break;
                    }
                case 42:
                    int zzb2 = zzafe.zzb((List) unsafe.getObject(obj, j));
                    if (zzb2 <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzb2) + zzb2;
                        break;
                    }
                case 43:
                    int zzw = zzafe.zzw((List) unsafe.getObject(obj, j));
                    if (zzw <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzw) + zzw;
                        break;
                    }
                case 44:
                    int zze = zzafe.zze((List) unsafe.getObject(obj, j));
                    if (zze <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zze) + zze;
                        break;
                    }
                case 45:
                    int zzg3 = zzafe.zzg((List) unsafe.getObject(obj, j));
                    if (zzg3 <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzg3) + zzg3;
                        break;
                    }
                case 46:
                    int zzi3 = zzafe.zzi((List) unsafe.getObject(obj, j));
                    if (zzi3 <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzi3) + zzi3;
                        break;
                    }
                case 47:
                    int zzr = zzafe.zzr((List) unsafe.getObject(obj, j));
                    if (zzr <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzr) + zzr;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    int zzt = zzafe.zzt((List) unsafe.getObject(obj, j));
                    if (zzt <= 0) {
                        break;
                    } else {
                        i5 += zzacx.zzz(i7) + zzacx.zzA(zzt) + zzt;
                        break;
                    }
                case 49:
                    i5 += zzafe.zzj(i7, (List) unsafe.getObject(obj, j), zzs(i4));
                    break;
                case 50:
                    zzaem.zza(i7, unsafe.getObject(obj, j), zzt(i4));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 8;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 4;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzB(zzr(obj, j));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzB(zzr(obj, j));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzv(zzn(obj, j));
                        break;
                    }
                case 56:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 8;
                        break;
                    }
                case 57:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 4;
                        break;
                    }
                case 58:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 1;
                        break;
                    }
                case 59:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        Object object2 = unsafe.getObject(obj, j);
                        if (!(object2 instanceof zzacp)) {
                            i5 += zzacx.zzA(i7 << 3) + zzacx.zzy((String) object2);
                            break;
                        } else {
                            int zzA3 = zzacx.zzA(i7 << 3);
                            int zzd3 = ((zzacp) object2).zzd();
                            i5 += zzA3 + zzacx.zzA(zzd3) + zzd3;
                            break;
                        }
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzafe.zzo(i7, unsafe.getObject(obj, j), zzs(i4));
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        zzacp zzacpVar2 = (zzacp) unsafe.getObject(obj, j);
                        int zzA4 = zzacx.zzA(i7 << 3);
                        int zzd4 = zzacpVar2.zzd();
                        i5 += zzA4 + zzacx.zzA(zzd4) + zzd4;
                        break;
                    }
                case 62:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzA(zzn(obj, j));
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzv(zzn(obj, j));
                        break;
                    }
                case 64:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 4;
                        break;
                    }
                case 65:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzA(i7 << 3) + 8;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        int zzn2 = zzn(obj, j);
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzA((zzn2 >> 31) ^ (zzn2 + zzn2));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        long zzr2 = zzr(obj, j);
                        i5 += zzacx.zzA(i7 << 3) + zzacx.zzB((zzr2 >> 63) ^ (zzr2 + zzr2));
                        break;
                    }
                case 68:
                    if (!zzD(obj, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacx.zzu(i7, (zzaer) unsafe.getObject(obj, j), zzs(i4));
                        break;
                    }
            }
            i4 += 3;
            i2 = 1048575;
        }
        zzaft zzaftVar = this.zzl;
        int zza2 = i5 + zzaftVar.zza(zzaftVar.zzc(obj));
        if (!this.zzf) {
            return zza2;
        }
        this.zzm.zza(obj);
        throw null;
    }

    private final int zzm(Object obj) {
        Unsafe unsafe = zzb;
        int i = 0;
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int zzq = zzq(i2);
            int zzp = zzp(zzq);
            int i3 = this.zzc[i2];
            long j = zzq & 1048575;
            if (zzp >= zzadf.DOUBLE_LIST_PACKED.zza() && zzp <= zzadf.SINT64_LIST_PACKED.zza()) {
                int i4 = this.zzc[i2 + 2];
            }
            switch (zzp) {
                case 0:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzB(zzagd.zzd(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzB(zzagd.zzd(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzv(zzagd.zzc(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + 1;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzA(obj, i2)) {
                        Object zzf = zzagd.zzf(obj, j);
                        if (zzf instanceof zzacp) {
                            int zzA = zzacx.zzA(i3 << 3);
                            int zzd = ((zzacp) zzf).zzd();
                            i += zzA + zzacx.zzA(zzd) + zzd;
                            break;
                        } else {
                            i += zzacx.zzA(i3 << 3) + zzacx.zzy((String) zzf);
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if (zzA(obj, i2)) {
                        i += zzafe.zzo(i3, zzagd.zzf(obj, j), zzs(i2));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzA(obj, i2)) {
                        zzacp zzacpVar = (zzacp) zzagd.zzf(obj, j);
                        int zzA2 = zzacx.zzA(i3 << 3);
                        int zzd2 = zzacpVar.zzd();
                        i += zzA2 + zzacx.zzA(zzd2) + zzd2;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzA(zzagd.zzc(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzv(zzagd.zzc(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzA(i3 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzA(obj, i2)) {
                        int zzc = zzagd.zzc(obj, j);
                        i += zzacx.zzA(i3 << 3) + zzacx.zzA((zzc >> 31) ^ (zzc + zzc));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzA(obj, i2)) {
                        long zzd3 = zzagd.zzd(obj, j);
                        i += zzacx.zzA(i3 << 3) + zzacx.zzB((zzd3 >> 63) ^ (zzd3 + zzd3));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzA(obj, i2)) {
                        i += zzacx.zzu(i3, (zzaer) zzagd.zzf(obj, j), zzs(i2));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    i += zzafe.zzh(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 19:
                    i += zzafe.zzf(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 20:
                    i += zzafe.zzm(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 21:
                    i += zzafe.zzx(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 22:
                    i += zzafe.zzk(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 23:
                    i += zzafe.zzh(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 24:
                    i += zzafe.zzf(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 25:
                    i += zzafe.zza(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 26:
                    i += zzafe.zzu(i3, (List) zzagd.zzf(obj, j));
                    break;
                case 27:
                    i += zzafe.zzp(i3, (List) zzagd.zzf(obj, j), zzs(i2));
                    break;
                case 28:
                    i += zzafe.zzc(i3, (List) zzagd.zzf(obj, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    i += zzafe.zzv(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 30:
                    i += zzafe.zzd(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 31:
                    i += zzafe.zzf(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 32:
                    i += zzafe.zzh(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 33:
                    i += zzafe.zzq(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 34:
                    i += zzafe.zzs(i3, (List) zzagd.zzf(obj, j), false);
                    break;
                case 35:
                    int zzi = zzafe.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzi) + zzi;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    int zzg = zzafe.zzg((List) unsafe.getObject(obj, j));
                    if (zzg > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzg) + zzg;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    int zzn = zzafe.zzn((List) unsafe.getObject(obj, j));
                    if (zzn > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzn) + zzn;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    int zzy = zzafe.zzy((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzy) + zzy;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    int zzl = zzafe.zzl((List) unsafe.getObject(obj, j));
                    if (zzl > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzl) + zzl;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    int zzi2 = zzafe.zzi((List) unsafe.getObject(obj, j));
                    if (zzi2 > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzi2) + zzi2;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    int zzg2 = zzafe.zzg((List) unsafe.getObject(obj, j));
                    if (zzg2 > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzg2) + zzg2;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    int zzb2 = zzafe.zzb((List) unsafe.getObject(obj, j));
                    if (zzb2 > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzb2) + zzb2;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    int zzw = zzafe.zzw((List) unsafe.getObject(obj, j));
                    if (zzw > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzw) + zzw;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    int zze = zzafe.zze((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zze) + zze;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    int zzg3 = zzafe.zzg((List) unsafe.getObject(obj, j));
                    if (zzg3 > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzg3) + zzg3;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    int zzi3 = zzafe.zzi((List) unsafe.getObject(obj, j));
                    if (zzi3 > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzi3) + zzi3;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    int zzr = zzafe.zzr((List) unsafe.getObject(obj, j));
                    if (zzr > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzr) + zzr;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    int zzt = zzafe.zzt((List) unsafe.getObject(obj, j));
                    if (zzt > 0) {
                        i += zzacx.zzz(i3) + zzacx.zzA(zzt) + zzt;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    i += zzafe.zzj(i3, (List) zzagd.zzf(obj, j), zzs(i2));
                    break;
                case 50:
                    zzaem.zza(i3, zzagd.zzf(obj, j), zzt(i2));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzB(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzB(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzv(zzn(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + 1;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzD(obj, i3, i2)) {
                        Object zzf2 = zzagd.zzf(obj, j);
                        if (zzf2 instanceof zzacp) {
                            int zzA3 = zzacx.zzA(i3 << 3);
                            int zzd4 = ((zzacp) zzf2).zzd();
                            i += zzA3 + zzacx.zzA(zzd4) + zzd4;
                            break;
                        } else {
                            i += zzacx.zzA(i3 << 3) + zzacx.zzy((String) zzf2);
                            break;
                        }
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (zzD(obj, i3, i2)) {
                        i += zzafe.zzo(i3, zzagd.zzf(obj, j), zzs(i2));
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzD(obj, i3, i2)) {
                        zzacp zzacpVar2 = (zzacp) zzagd.zzf(obj, j);
                        int zzA4 = zzacx.zzA(i3 << 3);
                        int zzd5 = zzacpVar2.zzd();
                        i += zzA4 + zzacx.zzA(zzd5) + zzd5;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzA(zzn(obj, j));
                        break;
                    } else {
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + zzacx.zzv(zzn(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + 4;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzA(i3 << 3) + 8;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzD(obj, i3, i2)) {
                        int zzn2 = zzn(obj, j);
                        i += zzacx.zzA(i3 << 3) + zzacx.zzA((zzn2 >> 31) ^ (zzn2 + zzn2));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzD(obj, i3, i2)) {
                        long zzr2 = zzr(obj, j);
                        i += zzacx.zzA(i3 << 3) + zzacx.zzB((zzr2 >> 63) ^ (zzr2 + zzr2));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzD(obj, i3, i2)) {
                        i += zzacx.zzu(i3, (zzaer) zzagd.zzf(obj, j), zzs(i2));
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzaft zzaftVar = this.zzl;
        return i + zzaftVar.zza(zzaftVar.zzc(obj));
    }

    private static int zzn(Object obj, long j) {
        return ((Integer) zzagd.zzf(obj, j)).intValue();
    }

    private final int zzo(int i) {
        return this.zzc[i + 2];
    }

    private static int zzp(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzq(int i) {
        return this.zzc[i + 1];
    }

    private static long zzr(Object obj, long j) {
        return ((Long) zzagd.zzf(obj, j)).longValue();
    }

    private final zzafc zzs(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzafc zzafcVar = (zzafc) this.zzd[i3];
        if (zzafcVar != null) {
            return zzafcVar;
        }
        zzafc zzb2 = zzaez.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzt(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzu(Class cls, String str) {
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
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    private final void zzv(Object obj, Object obj2, int i) {
        long zzq = zzq(i) & 1048575;
        if (zzA(obj2, i)) {
            Object zzf = zzagd.zzf(obj, zzq);
            Object zzf2 = zzagd.zzf(obj2, zzq);
            if (zzf != null && zzf2 != null) {
                zzagd.zzs(obj, zzq, zzads.zzg(zzf, zzf2));
                zzx(obj, i);
            } else if (zzf2 != null) {
                zzagd.zzs(obj, zzq, zzf2);
                zzx(obj, i);
            }
        }
    }

    private final void zzw(Object obj, Object obj2, int i) {
        int zzq = zzq(i);
        int i2 = this.zzc[i];
        long j = zzq & 1048575;
        if (zzD(obj2, i2, i)) {
            Object zzf = zzD(obj, i2, i) ? zzagd.zzf(obj, j) : null;
            Object zzf2 = zzagd.zzf(obj2, j);
            if (zzf != null && zzf2 != null) {
                zzagd.zzs(obj, j, zzads.zzg(zzf, zzf2));
                zzy(obj, i2, i);
            } else if (zzf2 != null) {
                zzagd.zzs(obj, j, zzf2);
                zzy(obj, i2, i);
            }
        }
    }

    private final void zzx(Object obj, int i) {
        int zzo = zzo(i);
        long j = 1048575 & zzo;
        if (j == 1048575) {
            return;
        }
        zzagd.zzq(obj, j, (1 << (zzo >>> 20)) | zzagd.zzc(obj, j));
    }

    private final void zzy(Object obj, int i, int i2) {
        zzagd.zzq(obj, zzo(i2) & 1048575, i);
    }

    private final boolean zzz(Object obj, Object obj2, int i) {
        return zzA(obj, i) == zzA(obj2, i);
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final int zza(Object obj) {
        return this.zzg ? zzm(obj) : zzl(obj);
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final int zzb(Object obj) {
        int length = this.zzc.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzq = zzq(i2);
            int i3 = this.zzc[i2];
            long j = 1048575 & zzq;
            switch (zzp(zzq)) {
                case 0:
                    i = (i * 53) + zzads.zzc(Double.doubleToLongBits(zzagd.zza(obj, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzagd.zzb(obj, j));
                    break;
                case 2:
                    i = (i * 53) + zzads.zzc(zzagd.zzd(obj, j));
                    break;
                case 3:
                    i = (i * 53) + zzads.zzc(zzagd.zzd(obj, j));
                    break;
                case 4:
                    i = (i * 53) + zzagd.zzc(obj, j);
                    break;
                case 5:
                    i = (i * 53) + zzads.zzc(zzagd.zzd(obj, j));
                    break;
                case 6:
                    i = (i * 53) + zzagd.zzc(obj, j);
                    break;
                case 7:
                    i = (i * 53) + zzads.zza(zzagd.zzw(obj, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzagd.zzf(obj, j)).hashCode();
                    break;
                case 9:
                    Object zzf = zzagd.zzf(obj, j);
                    i = (i * 53) + (zzf != null ? zzf.hashCode() : 37);
                    break;
                case 10:
                    i = (i * 53) + zzagd.zzf(obj, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzagd.zzc(obj, j);
                    break;
                case 12:
                    i = (i * 53) + zzagd.zzc(obj, j);
                    break;
                case 13:
                    i = (i * 53) + zzagd.zzc(obj, j);
                    break;
                case 14:
                    i = (i * 53) + zzads.zzc(zzagd.zzd(obj, j));
                    break;
                case 15:
                    i = (i * 53) + zzagd.zzc(obj, j);
                    break;
                case 16:
                    i = (i * 53) + zzads.zzc(zzagd.zzd(obj, j));
                    break;
                case 17:
                    Object zzf2 = zzagd.zzf(obj, j);
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
                    i = (i * 53) + zzagd.zzf(obj, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzagd.zzf(obj, j).hashCode();
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzads.zzc(Double.doubleToLongBits(zzj(obj, j)));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + Float.floatToIntBits(zzk(obj, j));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzads.zzc(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzads.zzc(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzn(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzads.zzc(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzn(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzads.zza(zzE(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + ((String) zzagd.zzf(obj, j)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzagd.zzf(obj, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzagd.zzf(obj, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzn(obj, j);
                        break;
                    } else {
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzn(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzn(obj, j);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzads.zzc(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzn(obj, j);
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzads.zzc(zzr(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzD(obj, i3, i2)) {
                        i = (i * 53) + zzagd.zzf(obj, j).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzl.zzc(obj).hashCode();
        if (!this.zzf) {
            return hashCode;
        }
        this.zzm.zza(obj);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final void zzc(Object obj) {
        int i;
        int i2 = this.zzi;
        while (true) {
            i = this.zzj;
            if (i2 >= i) {
                break;
            }
            long zzq = zzq(this.zzh[i2]) & 1048575;
            Object zzf = zzagd.zzf(obj, zzq);
            if (zzf != null) {
                ((zzael) zzf).zzb();
                zzagd.zzs(obj, zzq, zzf);
            }
            i2++;
        }
        int length = this.zzh.length;
        while (i < length) {
            this.zzk.zza(obj, this.zzh[i]);
            i++;
        }
        this.zzl.zze(obj);
        if (this.zzf) {
            this.zzm.zzb(obj);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final void zzd(Object obj, Object obj2) {
        if (obj2 == null) {
            throw null;
        }
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzq = zzq(i);
            long j = 1048575 & zzq;
            int i2 = this.zzc[i];
            switch (zzp(zzq)) {
                case 0:
                    if (zzA(obj2, i)) {
                        zzagd.zzo(obj, j, zzagd.zza(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzA(obj2, i)) {
                        zzagd.zzp(obj, j, zzagd.zzb(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzA(obj2, i)) {
                        zzagd.zzr(obj, j, zzagd.zzd(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzA(obj2, i)) {
                        zzagd.zzr(obj, j, zzagd.zzd(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzA(obj2, i)) {
                        zzagd.zzq(obj, j, zzagd.zzc(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzA(obj2, i)) {
                        zzagd.zzr(obj, j, zzagd.zzd(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzA(obj2, i)) {
                        zzagd.zzq(obj, j, zzagd.zzc(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzA(obj2, i)) {
                        zzagd.zzm(obj, j, zzagd.zzw(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzA(obj2, i)) {
                        zzagd.zzs(obj, j, zzagd.zzf(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzv(obj, obj2, i);
                    break;
                case 10:
                    if (zzA(obj2, i)) {
                        zzagd.zzs(obj, j, zzagd.zzf(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzA(obj2, i)) {
                        zzagd.zzq(obj, j, zzagd.zzc(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzA(obj2, i)) {
                        zzagd.zzq(obj, j, zzagd.zzc(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzA(obj2, i)) {
                        zzagd.zzq(obj, j, zzagd.zzc(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzA(obj2, i)) {
                        zzagd.zzr(obj, j, zzagd.zzd(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzA(obj2, i)) {
                        zzagd.zzq(obj, j, zzagd.zzc(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzA(obj2, i)) {
                        zzagd.zzr(obj, j, zzagd.zzd(obj2, j));
                        zzx(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzv(obj, obj2, i);
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
                    zzafe.zzG(this.zzo, obj, obj2, j);
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
                    if (zzD(obj2, i2, i)) {
                        zzagd.zzs(obj, j, zzagd.zzf(obj2, j));
                        zzy(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    zzw(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzD(obj2, i2, i)) {
                        zzagd.zzs(obj, j, zzagd.zzf(obj2, j));
                        zzy(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzw(obj, obj2, i);
                    break;
            }
        }
        zzafe.zzD(this.zzl, obj, obj2);
        if (this.zzf) {
            zzafe.zzC(this.zzm, obj, obj2);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final boolean zze(Object obj, Object obj2) {
        boolean zzF;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzq = zzq(i);
            long j = zzq & 1048575;
            switch (zzp(zzq)) {
                case 0:
                    if (zzz(obj, obj2, i) && Double.doubleToLongBits(zzagd.zza(obj, j)) == Double.doubleToLongBits(zzagd.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzz(obj, obj2, i) && Float.floatToIntBits(zzagd.zzb(obj, j)) == Float.floatToIntBits(zzagd.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzz(obj, obj2, i) && zzagd.zzd(obj, j) == zzagd.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzz(obj, obj2, i) && zzagd.zzd(obj, j) == zzagd.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzz(obj, obj2, i) && zzagd.zzc(obj, j) == zzagd.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzz(obj, obj2, i) && zzagd.zzd(obj, j) == zzagd.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzz(obj, obj2, i) && zzagd.zzc(obj, j) == zzagd.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzz(obj, obj2, i) && zzagd.zzw(obj, j) == zzagd.zzw(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzz(obj, obj2, i) && zzafe.zzF(zzagd.zzf(obj, j), zzagd.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzz(obj, obj2, i) && zzafe.zzF(zzagd.zzf(obj, j), zzagd.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzz(obj, obj2, i) && zzafe.zzF(zzagd.zzf(obj, j), zzagd.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzz(obj, obj2, i) && zzagd.zzc(obj, j) == zzagd.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzz(obj, obj2, i) && zzagd.zzc(obj, j) == zzagd.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzz(obj, obj2, i) && zzagd.zzc(obj, j) == zzagd.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzz(obj, obj2, i) && zzagd.zzd(obj, j) == zzagd.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzz(obj, obj2, i) && zzagd.zzc(obj, j) == zzagd.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzz(obj, obj2, i) && zzagd.zzd(obj, j) == zzagd.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzz(obj, obj2, i) && zzafe.zzF(zzagd.zzf(obj, j), zzagd.zzf(obj2, j))) {
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
                    zzF = zzafe.zzF(zzagd.zzf(obj, j), zzagd.zzf(obj2, j));
                    break;
                case 50:
                    zzF = zzafe.zzF(zzagd.zzf(obj, j), zzagd.zzf(obj2, j));
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
                    long zzo = zzo(i) & 1048575;
                    if (zzagd.zzc(obj, zzo) == zzagd.zzc(obj2, zzo) && zzafe.zzF(zzagd.zzf(obj, j), zzagd.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzF) {
                return false;
            }
        }
        if (!this.zzl.zzc(obj).equals(this.zzl.zzc(obj2))) {
            return false;
        }
        if (!this.zzf) {
            return true;
        }
        this.zzm.zza(obj);
        this.zzm.zza(obj2);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final boolean zzf(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzi) {
            int i6 = this.zzh[i5];
            int i7 = this.zzc[i6];
            int zzq = zzq(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 == i3) {
                i = i3;
                i2 = i4;
            } else if (i9 != 1048575) {
                i2 = zzb.getInt(obj, i9);
                i = i9;
            } else {
                i2 = i4;
                i = i9;
            }
            if ((268435456 & zzq) != 0 && !zzB(obj, i6, i, i2, i10)) {
                return false;
            }
            switch (zzp(zzq)) {
                case 9:
                case 17:
                    if (zzB(obj, i6, i, i2, i10) && !zzC(obj, zzq, zzs(i6))) {
                        return false;
                    }
                    break;
                case 27:
                case 49:
                    List list = (List) zzagd.zzf(obj, zzq & 1048575);
                    if (!list.isEmpty()) {
                        zzafc zzs = zzs(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!zzs.zzf(list.get(i11))) {
                                return false;
                            }
                        }
                        break;
                    } else {
                        continue;
                    }
                case 50:
                    if (!((zzael) zzagd.zzf(obj, zzq & 1048575)).isEmpty()) {
                        throw null;
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case 68:
                    if (zzD(obj, i7, i6) && !zzC(obj, zzq, zzs(i6))) {
                        return false;
                    }
                    break;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        if (!this.zzf) {
            return true;
        }
        this.zzm.zza(obj);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final void zzi(Object obj, zzacy zzacyVar) throws IOException {
        if (!this.zzg) {
            zzF(obj, zzacyVar);
            return;
        }
        if (this.zzf) {
            this.zzm.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzq = zzq(i);
            int i2 = this.zzc[i];
            switch (zzp(zzq)) {
                case 0:
                    if (zzA(obj, i)) {
                        zzacyVar.zzf(i2, zzagd.zza(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzA(obj, i)) {
                        zzacyVar.zzn(i2, zzagd.zzb(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzA(obj, i)) {
                        zzacyVar.zzs(i2, zzagd.zzd(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzA(obj, i)) {
                        zzacyVar.zzH(i2, zzagd.zzd(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzA(obj, i)) {
                        zzacyVar.zzq(i2, zzagd.zzc(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzA(obj, i)) {
                        zzacyVar.zzl(i2, zzagd.zzd(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzA(obj, i)) {
                        zzacyVar.zzj(i2, zzagd.zzc(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzA(obj, i)) {
                        zzacyVar.zzb(i2, zzagd.zzw(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzA(obj, i)) {
                        zzH(i2, zzagd.zzf(obj, zzq & 1048575), zzacyVar);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (zzA(obj, i)) {
                        zzacyVar.zzu(i2, zzagd.zzf(obj, zzq & 1048575), zzs(i));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzA(obj, i)) {
                        zzacyVar.zzd(i2, (zzacp) zzagd.zzf(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzA(obj, i)) {
                        zzacyVar.zzF(i2, zzagd.zzc(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzA(obj, i)) {
                        zzacyVar.zzh(i2, zzagd.zzc(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzA(obj, i)) {
                        zzacyVar.zzv(i2, zzagd.zzc(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzA(obj, i)) {
                        zzacyVar.zzx(i2, zzagd.zzd(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzA(obj, i)) {
                        zzacyVar.zzz(i2, zzagd.zzc(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzA(obj, i)) {
                        zzacyVar.zzB(i2, zzagd.zzd(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzA(obj, i)) {
                        zzacyVar.zzp(i2, zzagd.zzf(obj, zzq & 1048575), zzs(i));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzafe.zzJ(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 19:
                    zzafe.zzN(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 20:
                    zzafe.zzQ(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 21:
                    zzafe.zzY(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 22:
                    zzafe.zzP(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 23:
                    zzafe.zzM(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 24:
                    zzafe.zzL(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 25:
                    zzafe.zzH(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 26:
                    zzafe.zzW(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar);
                    break;
                case 27:
                    zzafe.zzR(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, zzs(i));
                    break;
                case 28:
                    zzafe.zzI(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    zzafe.zzX(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 30:
                    zzafe.zzK(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 31:
                    zzafe.zzS(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 32:
                    zzafe.zzT(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 33:
                    zzafe.zzU(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 34:
                    zzafe.zzV(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                    break;
                case 35:
                    zzafe.zzJ(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 36:
                    zzafe.zzN(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 37:
                    zzafe.zzQ(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 38:
                    zzafe.zzY(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 39:
                    zzafe.zzP(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 40:
                    zzafe.zzM(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 41:
                    zzafe.zzL(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 42:
                    zzafe.zzH(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 43:
                    zzafe.zzX(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 44:
                    zzafe.zzK(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 45:
                    zzafe.zzS(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 46:
                    zzafe.zzT(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 47:
                    zzafe.zzU(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    zzafe.zzV(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                    break;
                case 49:
                    zzafe.zzO(i2, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, zzs(i));
                    break;
                case 50:
                    zzG(zzacyVar, i2, zzagd.zzf(obj, zzq & 1048575), i);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzf(i2, zzj(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzn(i2, zzk(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzs(i2, zzr(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzH(i2, zzr(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzq(i2, zzn(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzl(i2, zzr(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzj(i2, zzn(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzb(i2, zzE(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzD(obj, i2, i)) {
                        zzH(i2, zzagd.zzf(obj, zzq & 1048575), zzacyVar);
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzu(i2, zzagd.zzf(obj, zzq & 1048575), zzs(i));
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzd(i2, (zzacp) zzagd.zzf(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzF(i2, zzn(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzh(i2, zzn(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzv(i2, zzn(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzx(i2, zzr(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzz(i2, zzn(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzB(i2, zzr(obj, zzq & 1048575));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzD(obj, i2, i)) {
                        zzacyVar.zzp(i2, zzagd.zzf(obj, zzq & 1048575), zzs(i));
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzaft zzaftVar = this.zzl;
        zzaftVar.zzg(zzaftVar.zzc(obj), zzacyVar);
    }
}
