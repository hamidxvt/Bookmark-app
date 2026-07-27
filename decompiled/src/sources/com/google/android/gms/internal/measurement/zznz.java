package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
final class zznz {
    public static final /* synthetic */ int zza = 0;
    private static final zzoi zzb;

    static {
        int i = zznu.zza;
        zzb = new zzok();
    }

    public static zzoi zzA() {
        return zzb;
    }

    static boolean zzB(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static void zzC(zzls zzlsVar, Object obj, Object obj2) {
        if (((zzmc) obj2).zzb.zza.isEmpty()) {
            return;
        }
        throw null;
    }

    static void zzD(zzoi zzoiVar, Object obj, Object obj2) {
        zzmf zzmfVar = (zzmf) obj;
        zzoj zzojVar = zzmfVar.zzc;
        zzoj zzojVar2 = ((zzmf) obj2).zzc;
        if (!zzoj.zza().equals(zzojVar2)) {
            if (zzoj.zza().equals(zzojVar)) {
                zzojVar = zzoj.zzc(zzojVar, zzojVar2);
            } else {
                zzojVar.zzl(zzojVar2);
            }
        }
        zzmfVar.zzc = zzojVar;
    }

    static Object zzE(Object obj, int i, int i2, Object obj2, zzoi zzoiVar) {
        if (obj2 == null) {
            obj2 = zzoiVar.zza(obj);
        }
        ((zzoj) obj2).zzk(i << 3, Long.valueOf(i2));
        return obj2;
    }

    public static void zza(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzC(i, list, z);
    }

    public static void zzb(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzB(i, list, z);
    }

    public static void zzc(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzy(i, list, z);
    }

    public static void zzd(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzz(i, list, z);
    }

    public static void zze(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzL(i, list, z);
    }

    public static void zzf(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzA(i, list, z);
    }

    public static void zzg(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzJ(i, list, z);
    }

    public static void zzh(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzw(i, list, z);
    }

    public static void zzi(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzH(i, list, z);
    }

    public static void zzj(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzK(i, list, z);
    }

    public static void zzk(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzx(i, list, z);
    }

    public static void zzl(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzI(i, list, z);
    }

    public static void zzm(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzD(i, list, z);
    }

    public static void zzn(int i, List list, zzov zzovVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzovVar.zzE(i, list, z);
    }

    static int zzo(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzna) {
            zzna zznaVar = (zzna) list;
            i = 0;
            while (i2 < size) {
                i += zzlm.zzA(zznaVar.zzc(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzlm.zzA(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzp(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzna) {
            zzna zznaVar = (zzna) list;
            i = 0;
            while (i2 < size) {
                i += zzlm.zzA(zznaVar.zzc(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzlm.zzA(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzq(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzna) {
            zzna zznaVar = (zzna) list;
            i = 0;
            while (i2 < size) {
                long zzc = zznaVar.zzc(i2);
                i += zzlm.zzA((zzc >> 63) ^ (zzc + zzc));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i += zzlm.zzA((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    static int zzr(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzmg) {
            zzmg zzmgVar = (zzmg) list;
            i = 0;
            while (i2 < size) {
                i += zzlm.zzA(zzmgVar.zzf(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzlm.zzA(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzs(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzmg) {
            zzmg zzmgVar = (zzmg) list;
            i = 0;
            while (i2 < size) {
                i += zzlm.zzA(zzmgVar.zzf(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzlm.zzA(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzt(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzmg) {
            zzmg zzmgVar = (zzmg) list;
            i = 0;
            while (i2 < size) {
                i += zzlm.zzz(zzmgVar.zzf(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzlm.zzz(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzu(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzmg) {
            zzmg zzmgVar = (zzmg) list;
            i = 0;
            while (i2 < size) {
                int zzf = zzmgVar.zzf(i2);
                i += zzlm.zzz((zzf >> 31) ^ (zzf + zzf));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i += zzlm.zzz((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    static int zzv(List list) {
        return list.size() * 4;
    }

    static int zzw(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzlm.zzz(i << 3) + 4);
    }

    static int zzx(List list) {
        return list.size() * 8;
    }

    static int zzy(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzlm.zzz(i << 3) + 8);
    }

    static int zzz(int i, Object obj, zznx zznxVar) {
        int i2 = i << 3;
        if (!(obj instanceof zzmw)) {
            return zzlm.zzz(i2) + zzlm.zzD((zznm) obj, zznxVar);
        }
        int zzz = zzlm.zzz(i2);
        int zzb2 = ((zzmw) obj).zzb();
        return zzz + zzlm.zzz(zzb2) + zzb2;
    }
}
