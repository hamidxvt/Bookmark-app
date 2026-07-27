package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
final class zzbv extends zzbn {
    static final zzbn zza = new zzbv(null, new Object[0], 0);
    final transient Object[] zzb;
    private final transient Object zzc;
    private final transient int zzd;

    private zzbv(Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    static zzbv zzg(int i, Object[] objArr, zzbm zzbmVar) {
        char c;
        int i2 = i;
        Object[] objArr2 = objArr;
        if (i2 == 0) {
            return (zzbv) zza;
        }
        Object[] objArr3 = null;
        if (i2 == 1) {
            zzbf.zza(Objects.requireNonNull(objArr2[0]), Objects.requireNonNull(objArr2[1]));
            return new zzbv(null, objArr2, 1);
        }
        zzbc.zzb(i2, objArr2.length >> 1, FirebaseAnalytics.Param.INDEX);
        int zzf = zzbo.zzf(i);
        if (i2 == 1) {
            zzbf.zza(Objects.requireNonNull(objArr2[0]), Objects.requireNonNull(objArr2[1]));
            i2 = 1;
            c = 2;
        } else {
            int i3 = zzf - 1;
            int i4 = -1;
            if (zzf <= 128) {
                byte[] bArr = new byte[zzf];
                Arrays.fill(bArr, (byte) -1);
                int i5 = 0;
                for (int i6 = 0; i6 < i2; i6++) {
                    int i7 = i5 + i5;
                    int i8 = i6 + i6;
                    Object requireNonNull = Objects.requireNonNull(objArr2[i8]);
                    Object requireNonNull2 = Objects.requireNonNull(objArr2[i8 ^ 1]);
                    zzbf.zza(requireNonNull, requireNonNull2);
                    int zza2 = zzbg.zza(requireNonNull.hashCode());
                    while (true) {
                        int i9 = zza2 & i3;
                        int i10 = bArr[i9] & 255;
                        if (i10 == 255) {
                            bArr[i9] = (byte) i7;
                            if (i5 < i6) {
                                objArr2[i7] = requireNonNull;
                                objArr2[i7 ^ 1] = requireNonNull2;
                            }
                            i5++;
                        } else {
                            if (requireNonNull.equals(objArr2[i10])) {
                                int i11 = i10 ^ 1;
                                zzbl zzblVar = new zzbl(requireNonNull, requireNonNull2, Objects.requireNonNull(objArr2[i11]));
                                objArr2[i11] = requireNonNull2;
                                objArr3 = zzblVar;
                                break;
                            }
                            zza2 = i9 + 1;
                        }
                    }
                }
                if (i5 == i2) {
                    objArr3 = bArr;
                    c = 2;
                } else {
                    c = 2;
                    objArr3 = new Object[]{bArr, Integer.valueOf(i5), objArr3};
                }
            } else if (zzf <= 32768) {
                short[] sArr = new short[zzf];
                Arrays.fill(sArr, (short) -1);
                int i12 = 0;
                for (int i13 = 0; i13 < i2; i13++) {
                    int i14 = i12 + i12;
                    int i15 = i13 + i13;
                    Object requireNonNull3 = Objects.requireNonNull(objArr2[i15]);
                    Object requireNonNull4 = Objects.requireNonNull(objArr2[i15 ^ 1]);
                    zzbf.zza(requireNonNull3, requireNonNull4);
                    int zza3 = zzbg.zza(requireNonNull3.hashCode());
                    while (true) {
                        int i16 = zza3 & i3;
                        char c2 = (char) sArr[i16];
                        if (c2 == 65535) {
                            sArr[i16] = (short) i14;
                            if (i12 < i13) {
                                objArr2[i14] = requireNonNull3;
                                objArr2[i14 ^ 1] = requireNonNull4;
                            }
                            i12++;
                        } else {
                            if (requireNonNull3.equals(objArr2[c2])) {
                                int i17 = c2 ^ 1;
                                zzbl zzblVar2 = new zzbl(requireNonNull3, requireNonNull4, Objects.requireNonNull(objArr2[i17]));
                                objArr2[i17] = requireNonNull4;
                                objArr3 = zzblVar2;
                                break;
                            }
                            zza3 = i16 + 1;
                        }
                    }
                }
                if (i12 == i2) {
                    objArr3 = sArr;
                    c = 2;
                } else {
                    c = 2;
                    objArr3 = new Object[]{sArr, Integer.valueOf(i12), objArr3};
                }
            } else {
                int[] iArr = new int[zzf];
                Arrays.fill(iArr, -1);
                int i18 = 0;
                int i19 = 0;
                while (i18 < i2) {
                    int i20 = i19 + i19;
                    int i21 = i18 + i18;
                    Object requireNonNull5 = Objects.requireNonNull(objArr2[i21]);
                    Object requireNonNull6 = Objects.requireNonNull(objArr2[i21 ^ 1]);
                    zzbf.zza(requireNonNull5, requireNonNull6);
                    int zza4 = zzbg.zza(requireNonNull5.hashCode());
                    while (true) {
                        int i22 = zza4 & i3;
                        int i23 = iArr[i22];
                        if (i23 == i4) {
                            iArr[i22] = i20;
                            if (i19 < i18) {
                                objArr2[i20] = requireNonNull5;
                                objArr2[i20 ^ 1] = requireNonNull6;
                            }
                            i19++;
                        } else {
                            if (requireNonNull5.equals(objArr2[i23])) {
                                int i24 = i23 ^ 1;
                                zzbl zzblVar3 = new zzbl(requireNonNull5, requireNonNull6, Objects.requireNonNull(objArr2[i24]));
                                objArr2[i24] = requireNonNull6;
                                objArr3 = zzblVar3;
                                break;
                            }
                            zza4 = i22 + 1;
                            i4 = -1;
                        }
                    }
                    i18++;
                    i4 = -1;
                }
                if (i19 == i2) {
                    objArr3 = iArr;
                    c = 2;
                } else {
                    c = 2;
                    objArr3 = new Object[]{iArr, Integer.valueOf(i19), objArr3};
                }
            }
        }
        if (objArr3 instanceof Object[]) {
            Object[] objArr4 = objArr3;
            zzbmVar.zzc = (zzbl) objArr4[c];
            Object obj = objArr4[0];
            int intValue = ((Integer) objArr4[1]).intValue();
            objArr2 = Arrays.copyOf(objArr2, intValue + intValue);
            objArr3 = obj;
            i2 = intValue;
        }
        return new zzbv(objArr3, objArr2, i2);
    }

    @Override // com.google.android.gms.internal.maps.zzbn, java.util.Map
    public final Object get(Object obj) {
        Object obj2;
        if (obj == null) {
            obj2 = null;
        } else {
            int i = this.zzd;
            Object[] objArr = this.zzb;
            if (i == 1) {
                if (Objects.requireNonNull(objArr[0]).equals(obj)) {
                    obj2 = Objects.requireNonNull(objArr[1]);
                }
                obj2 = null;
            } else {
                Object obj3 = this.zzc;
                if (obj3 != null) {
                    if (obj3 instanceof byte[]) {
                        byte[] bArr = (byte[]) obj3;
                        int length = bArr.length - 1;
                        int zza2 = zzbg.zza(obj.hashCode());
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
                        int zza3 = zzbg.zza(obj.hashCode());
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
                        int zza4 = zzbg.zza(obj.hashCode());
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

    @Override // com.google.android.gms.internal.maps.zzbn
    final zzbh zza() {
        return new zzbu(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.maps.zzbn
    final zzbo zzd() {
        return new zzbs(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.maps.zzbn
    final zzbo zze() {
        return new zzbt(this, new zzbu(this.zzb, 0, this.zzd));
    }
}
