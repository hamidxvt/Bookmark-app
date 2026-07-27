package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzil extends zzhv {
    static final zzhv zza = new zzil(null, new Object[0], 0);
    final transient Object[] zzb;

    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzil(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    static zzil zzf(int i, Object[] objArr, zzhu zzhuVar) {
        int i2;
        int i3 = i;
        Object[] objArr2 = objArr;
        if (i3 == 0) {
            return (zzil) zza;
        }
        Object[] objArr3 = null;
        if (i3 == 1) {
            Object obj = objArr2[0];
            obj.getClass();
            Object obj2 = objArr2[1];
            obj2.getClass();
            zzhh.zza(obj, obj2);
            return new zzil(null, objArr2, 1);
        }
        zzha.zzb(i3, objArr2.length >> 1, FirebaseAnalytics.Param.INDEX);
        char c = 2;
        int max = Math.max(i3, 2);
        if (max < 751619276) {
            i2 = Integer.highestOneBit(max - 1);
            do {
                i2 += i2;
            } while (i2 * 0.7d < max);
        } else {
            i2 = 1073741824;
            zzha.zze(max < 1073741824, "collection too large");
        }
        if (i3 == 1) {
            Object obj3 = objArr2[0];
            obj3.getClass();
            Object obj4 = objArr2[1];
            obj4.getClass();
            zzhh.zza(obj3, obj4);
        } else {
            int i4 = i2 - 1;
            int i5 = -1;
            if (i2 <= 128) {
                byte[] bArr = new byte[i2];
                Arrays.fill(bArr, (byte) -1);
                int i6 = 0;
                for (int i7 = 0; i7 < i3; i7++) {
                    int i8 = i7 + i7;
                    int i9 = i6 + i6;
                    Object obj5 = objArr2[i8];
                    obj5.getClass();
                    Object obj6 = objArr2[i8 ^ 1];
                    obj6.getClass();
                    zzhh.zza(obj5, obj6);
                    int zza2 = zzho.zza(obj5.hashCode());
                    while (true) {
                        int i10 = zza2 & i4;
                        int i11 = bArr[i10] & 255;
                        if (i11 == 255) {
                            bArr[i10] = (byte) i9;
                            if (i6 < i7) {
                                objArr2[i9] = obj5;
                                objArr2[i9 ^ 1] = obj6;
                            }
                            i6++;
                        } else {
                            if (obj5.equals(objArr2[i11])) {
                                int i12 = i11 ^ 1;
                                Object obj7 = objArr2[i12];
                                obj7.getClass();
                                zzht zzhtVar = new zzht(obj5, obj6, obj7);
                                objArr2[i12] = obj6;
                                objArr3 = zzhtVar;
                                break;
                            }
                            zza2 = i10 + 1;
                        }
                    }
                }
                if (i6 == i3) {
                    objArr3 = bArr;
                    c = 2;
                } else {
                    objArr3 = new Object[]{bArr, Integer.valueOf(i6), objArr3};
                    c = 2;
                }
            } else if (i2 <= 32768) {
                short[] sArr = new short[i2];
                Arrays.fill(sArr, (short) -1);
                int i13 = 0;
                for (int i14 = 0; i14 < i3; i14++) {
                    int i15 = i14 + i14;
                    int i16 = i13 + i13;
                    Object obj8 = objArr2[i15];
                    obj8.getClass();
                    Object obj9 = objArr2[i15 ^ 1];
                    obj9.getClass();
                    zzhh.zza(obj8, obj9);
                    int zza3 = zzho.zza(obj8.hashCode());
                    while (true) {
                        int i17 = zza3 & i4;
                        char c2 = (char) sArr[i17];
                        if (c2 == 65535) {
                            sArr[i17] = (short) i16;
                            if (i13 < i14) {
                                objArr2[i16] = obj8;
                                objArr2[i16 ^ 1] = obj9;
                            }
                            i13++;
                        } else {
                            if (obj8.equals(objArr2[c2])) {
                                int i18 = c2 ^ 1;
                                Object obj10 = objArr2[i18];
                                obj10.getClass();
                                zzht zzhtVar2 = new zzht(obj8, obj9, obj10);
                                objArr2[i18] = obj9;
                                objArr3 = zzhtVar2;
                                break;
                            }
                            zza3 = i17 + 1;
                        }
                    }
                }
                if (i13 == i3) {
                    objArr3 = sArr;
                    c = 2;
                } else {
                    c = 2;
                    objArr3 = new Object[]{sArr, Integer.valueOf(i13), objArr3};
                }
            } else {
                int[] iArr = new int[i2];
                Arrays.fill(iArr, -1);
                int i19 = 0;
                int i20 = 0;
                while (i19 < i3) {
                    int i21 = i19 + i19;
                    int i22 = i20 + i20;
                    Object obj11 = objArr2[i21];
                    obj11.getClass();
                    Object obj12 = objArr2[i21 ^ 1];
                    obj12.getClass();
                    zzhh.zza(obj11, obj12);
                    int zza4 = zzho.zza(obj11.hashCode());
                    while (true) {
                        int i23 = zza4 & i4;
                        int i24 = iArr[i23];
                        if (i24 == i5) {
                            iArr[i23] = i22;
                            if (i20 < i19) {
                                objArr2[i22] = obj11;
                                objArr2[i22 ^ 1] = obj12;
                            }
                            i20++;
                        } else {
                            if (obj11.equals(objArr2[i24])) {
                                int i25 = i24 ^ 1;
                                Object obj13 = objArr2[i25];
                                obj13.getClass();
                                zzht zzhtVar3 = new zzht(obj11, obj12, obj13);
                                objArr2[i25] = obj12;
                                objArr3 = zzhtVar3;
                                break;
                            }
                            zza4 = i23 + 1;
                            i5 = -1;
                        }
                    }
                    i19++;
                    i5 = -1;
                }
                if (i20 == i3) {
                    objArr3 = iArr;
                    c = 2;
                } else {
                    c = 2;
                    objArr3 = new Object[]{iArr, Integer.valueOf(i20), objArr3};
                }
            }
        }
        if (objArr3 instanceof Object[]) {
            Object[] objArr4 = objArr3;
            zzhuVar.zzc = (zzht) objArr4[c];
            Object obj14 = objArr4[0];
            int intValue = ((Integer) objArr4[1]).intValue();
            objArr2 = Arrays.copyOf(objArr2, intValue + intValue);
            objArr3 = obj14;
            i3 = intValue;
        }
        return new zzil(objArr3, objArr2, i3);
    }

    @Override // com.google.android.libraries.places.internal.zzhv, java.util.Map
    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Object obj2;
        Object obj3 = this.zzc;
        Object[] objArr = this.zzb;
        int i = this.zzd;
        if (obj == null) {
            obj2 = null;
        } else if (i == 1) {
            Object obj4 = objArr[0];
            obj4.getClass();
            if (obj4.equals(obj)) {
                obj2 = objArr[1];
                obj2.getClass();
            }
            obj2 = null;
        } else {
            if (obj3 != null) {
                if (obj3 instanceof byte[]) {
                    byte[] bArr = (byte[]) obj3;
                    int length = bArr.length - 1;
                    int zza2 = zzho.zza(obj.hashCode());
                    while (true) {
                        int i2 = zza2 & length;
                        int i3 = bArr[i2] & 255;
                        if (i3 == 255) {
                            obj2 = null;
                            break;
                        }
                        if (obj.equals(objArr[i3])) {
                            obj2 = objArr[i3 ^ 1];
                            break;
                        }
                        zza2 = i2 + 1;
                    }
                } else if (obj3 instanceof short[]) {
                    short[] sArr = (short[]) obj3;
                    int length2 = sArr.length - 1;
                    int zza3 = zzho.zza(obj.hashCode());
                    while (true) {
                        int i4 = zza3 & length2;
                        char c = (char) sArr[i4];
                        if (c == 65535) {
                            obj2 = null;
                            break;
                        }
                        if (obj.equals(objArr[c])) {
                            obj2 = objArr[c ^ 1];
                            break;
                        }
                        zza3 = i4 + 1;
                    }
                } else {
                    int[] iArr = (int[]) obj3;
                    int length3 = iArr.length - 1;
                    int zza4 = zzho.zza(obj.hashCode());
                    while (true) {
                        int i5 = zza4 & length3;
                        int i6 = iArr[i5];
                        if (i6 == -1) {
                            obj2 = null;
                            break;
                        }
                        if (obj.equals(objArr[i6])) {
                            obj2 = objArr[i6 ^ 1];
                            break;
                        }
                        zza4 = i5 + 1;
                    }
                }
            }
            obj2 = null;
        }
        if (obj2 == null) {
            return null;
        }
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.internal.zzhv
    final zzhp zza() {
        return new zzik(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.libraries.places.internal.zzhv
    final zzhw zzc() {
        return new zzii(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.libraries.places.internal.zzhv
    final zzhw zzd() {
        return new zzij(this, new zzik(this.zzb, 0, this.zzd));
    }
}
