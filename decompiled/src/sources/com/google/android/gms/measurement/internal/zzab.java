package com.google.android.gms.measurement.internal;

import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.common.internal.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
abstract class zzab {
    final String zzb;
    final int zzc;
    Boolean zzd;
    Boolean zze;
    Long zzf;
    Long zzg;

    zzab(String str, int i) {
        this.zzb = str;
        this.zzc = i;
    }

    private static Boolean zzd(String str, int i, boolean z, String str2, List list, String str3, zzgu zzguVar) {
        if (i == 7) {
            if (list == null || list.isEmpty()) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 2) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i - 1) {
            case 1:
                if (str3 != null) {
                    try {
                        break;
                    } catch (PatternSyntaxException e) {
                        if (zzguVar != null) {
                            zzguVar.zze().zzb("Invalid regular expression in REGEXP audience filter. expression", str3);
                        }
                        return null;
                    }
                }
                break;
            case 6:
                if (list != null) {
                    break;
                }
                break;
        }
        return null;
    }

    static Boolean zze(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    static Boolean zzf(String str, com.google.android.gms.internal.measurement.zzfr zzfrVar, zzgu zzguVar) {
        List list;
        Preconditions.checkNotNull(zzfrVar);
        if (str == null || !zzfrVar.zza() || zzfrVar.zzj() == 1 || (zzfrVar.zzj() != 7 ? !zzfrVar.zzb() : zzfrVar.zzg() == 0)) {
            return null;
        }
        int zzj = zzfrVar.zzj();
        boolean zze = zzfrVar.zze();
        String zzc = (zze || zzj == 2 || zzj == 7) ? zzfrVar.zzc() : zzfrVar.zzc().toUpperCase(Locale.ENGLISH);
        if (zzfrVar.zzg() == 0) {
            list = null;
        } else {
            List zzf = zzfrVar.zzf();
            if (zze) {
                list = zzf;
            } else {
                ArrayList arrayList = new ArrayList(zzf.size());
                Iterator it = zzf.iterator();
                while (it.hasNext()) {
                    arrayList.add(((String) it.next()).toUpperCase(Locale.ENGLISH));
                }
                list = Collections.unmodifiableList(arrayList);
            }
        }
        return zzd(str, zzj, zze, zzc, list, zzj == 2 ? zzc : null, zzguVar);
    }

    static Boolean zzg(long j, com.google.android.gms.internal.measurement.zzfl zzflVar) {
        try {
            return zzj(new BigDecimal(j), zzflVar, Utils.DOUBLE_EPSILON);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static Boolean zzh(double d, com.google.android.gms.internal.measurement.zzfl zzflVar) {
        try {
            return zzj(new BigDecimal(d), zzflVar, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static Boolean zzi(String str, com.google.android.gms.internal.measurement.zzfl zzflVar) {
        if (!zzpk.zzm(str)) {
            return null;
        }
        try {
            return zzj(new BigDecimal(str), zzflVar, Utils.DOUBLE_EPSILON);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static Boolean zzj(BigDecimal bigDecimal, com.google.android.gms.internal.measurement.zzfl zzflVar, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        Preconditions.checkNotNull(zzflVar);
        if (zzflVar.zza()) {
            if (zzflVar.zzm() != 1) {
                if (zzflVar.zzm() == 5) {
                    if (!zzflVar.zzf() || !zzflVar.zzh()) {
                        return null;
                    }
                } else if (!zzflVar.zzd()) {
                    return null;
                }
                int zzm = zzflVar.zzm();
                if (zzflVar.zzm() == 5) {
                    if (!zzpk.zzm(zzflVar.zzg()) || !zzpk.zzm(zzflVar.zzi())) {
                        return null;
                    }
                    try {
                        BigDecimal bigDecimal5 = new BigDecimal(zzflVar.zzg());
                        bigDecimal4 = new BigDecimal(zzflVar.zzi());
                        bigDecimal3 = bigDecimal5;
                        bigDecimal2 = null;
                    } catch (NumberFormatException e) {
                        return null;
                    }
                } else {
                    if (!zzpk.zzm(zzflVar.zze())) {
                        return null;
                    }
                    try {
                        bigDecimal2 = new BigDecimal(zzflVar.zze());
                        bigDecimal3 = null;
                        bigDecimal4 = null;
                    } catch (NumberFormatException e2) {
                        return null;
                    }
                }
                if (zzm == 5) {
                    if (bigDecimal3 == null) {
                        return null;
                    }
                } else if (bigDecimal2 == null) {
                    return null;
                }
                switch (zzm - 1) {
                    case 1:
                        if (bigDecimal2 == null) {
                            return null;
                        }
                        return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) < 0);
                    case 2:
                        if (bigDecimal2 == null) {
                            return null;
                        }
                        return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) > 0);
                    case 3:
                        if (bigDecimal2 == null) {
                            return null;
                        }
                        if (d != Utils.DOUBLE_EPSILON) {
                            return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) > 0 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d).multiply(new BigDecimal(2)))) < 0);
                        }
                        return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) == 0);
                    case 4:
                        if (bigDecimal3 != null) {
                            return Boolean.valueOf(bigDecimal.compareTo(bigDecimal3) >= 0 && bigDecimal.compareTo(bigDecimal4) <= 0);
                        }
                        return null;
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    abstract int zza();

    abstract boolean zzb();

    abstract boolean zzc();
}
