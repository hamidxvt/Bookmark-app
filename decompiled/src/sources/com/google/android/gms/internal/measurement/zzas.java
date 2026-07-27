package com.google.android.gms.internal.measurement;

import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.CharUtils;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzas implements Iterable, zzao {
    private final String zza;

    public zzas(String str) {
        if (str == null) {
            throw new IllegalArgumentException("StringValue cannot be null.");
        }
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzas) {
            return this.zza.equals(((zzas) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new zzar(this);
    }

    public final String toString() {
        String str = this.zza;
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append("\"");
        sb.append(str);
        sb.append("\"");
        return sb.toString();
    }

    final /* synthetic */ String zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        return this.zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:135:0x04b5  */
    /* JADX WARN: Type inference failed for: r1v117 */
    /* JADX WARN: Type inference failed for: r1v92 */
    /* JADX WARN: Type inference failed for: r1v93, types: [int] */
    /* JADX WARN: Type inference failed for: r21v0, types: [java.lang.Object, java.lang.String] */
    @Override // com.google.android.gms.internal.measurement.zzao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzao zzcA(String str, zzg zzgVar, List list) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        char c;
        String str7;
        zzas zzasVar;
        int i;
        String str8;
        int indexOf;
        int i2;
        boolean z;
        zzg zzgVar2;
        int i3;
        if ("charAt".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("concat".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("hasOwnProperty".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("indexOf".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("lastIndexOf".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("match".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("replace".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if (FirebaseAnalytics.Event.SEARCH.equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("slice".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("split".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("substring".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("toLowerCase".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("toLocaleLowerCase".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("toString".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else if ("toUpperCase".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "hasOwnProperty";
        } else {
            str2 = "toLocaleUpperCase";
            if (str2.equals(str)) {
                str3 = "hasOwnProperty";
            } else {
                str3 = "hasOwnProperty";
                if (!"trim".equals(str)) {
                    throw new IllegalArgumentException(String.format("%s is not a String function", str));
                }
            }
        }
        switch (str.hashCode()) {
            case -1789698943:
                str4 = "charAt";
                str5 = "toString";
                str6 = str3;
                if (str.equals(str6)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1776922004:
                str4 = "charAt";
                str5 = "toString";
                if (str.equals(str5)) {
                    c = 14;
                    str6 = str3;
                    break;
                } else {
                    str6 = str3;
                    c = 65535;
                    break;
                }
            case -1464939364:
                str4 = "charAt";
                if (str.equals("toLocaleLowerCase")) {
                    c = '\f';
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case -1361633751:
                str4 = "charAt";
                if (str.equals(str4)) {
                    str6 = str3;
                    str5 = "toString";
                    c = 0;
                    break;
                }
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case -1354795244:
                if (str.equals("concat")) {
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    c = 1;
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case -1137582698:
                if (str.equals("toLowerCase")) {
                    c = CharUtils.CR;
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case -906336856:
                if (str.equals(FirebaseAnalytics.Event.SEARCH)) {
                    c = 7;
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case -726908483:
                if (str.equals(str2)) {
                    c = 11;
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case -467511597:
                if (str.equals("lastIndexOf")) {
                    c = 4;
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case -399551817:
                if (str.equals("toUpperCase")) {
                    c = 15;
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case 3568674:
                if (str.equals("trim")) {
                    c = 16;
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case 103668165:
                if (str.equals("match")) {
                    c = 5;
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case 109526418:
                if (str.equals("slice")) {
                    c = '\b';
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case 109648666:
                if (str.equals("split")) {
                    c = '\t';
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case 530542161:
                if (str.equals("substring")) {
                    c = '\n';
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case 1094496948:
                if (str.equals("replace")) {
                    c = 6;
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            case 1943291465:
                if (str.equals("indexOf")) {
                    c = 3;
                    str4 = "charAt";
                    str6 = str3;
                    str5 = "toString";
                    break;
                }
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
            default:
                str4 = "charAt";
                str6 = str3;
                str5 = "toString";
                c = 65535;
                break;
        }
        str7 = "undefined";
        String str9 = str6;
        String str10 = str4;
        double d = Utils.DOUBLE_EPSILON;
        switch (c) {
            case 0:
                zzh.zzc(str10, 1, list);
                int zzi = !list.isEmpty() ? (int) zzh.zzi(zzgVar.zza((zzao) list.get(0)).zzd().doubleValue()) : 0;
                String str11 = this.zza;
                return (zzi < 0 || zzi >= str11.length()) ? zzao.zzm : new zzas(String.valueOf(str11.charAt(zzi)));
            case 1:
                zzasVar = this;
                if (!list.isEmpty()) {
                    StringBuilder sb = new StringBuilder(zzasVar.zza);
                    for (int i4 = 0; i4 < list.size(); i4++) {
                        sb.append(zzgVar.zza((zzao) list.get(i4)).zzc());
                    }
                    return new zzas(sb.toString());
                }
                break;
            case 2:
                zzh.zza(str9, 1, list);
                String str12 = this.zza;
                zzao zza = zzgVar.zza((zzao) list.get(0));
                if ("length".equals(zza.zzc())) {
                    return zzaf.zzk;
                }
                double doubleValue = zza.zzd().doubleValue();
                return (doubleValue != Math.floor(doubleValue) || (i = (int) doubleValue) < 0 || i >= str12.length()) ? zzaf.zzl : zzaf.zzk;
            case 3:
                zzh.zzc("indexOf", 2, list);
                String str13 = this.zza;
                String zzc = list.size() <= 0 ? "undefined" : zzgVar.zza((zzao) list.get(0)).zzc();
                if (list.size() >= 2) {
                    d = zzgVar.zza((zzao) list.get(1)).zzd().doubleValue();
                }
                return new zzah(Double.valueOf(str13.indexOf(zzc, (int) zzh.zzi(d))));
            case 4:
                zzh.zzc("lastIndexOf", 2, list);
                String str14 = this.zza;
                String zzc2 = list.size() <= 0 ? "undefined" : zzgVar.zza((zzao) list.get(0)).zzc();
                return new zzah(Double.valueOf(str14.lastIndexOf(zzc2, (int) (Double.isNaN(list.size() < 2 ? Double.NaN : zzgVar.zza((zzao) list.get(1)).zzd().doubleValue()) ? Double.POSITIVE_INFINITY : zzh.zzi(r4)))));
            case 5:
                zzh.zzc("match", 1, list);
                Matcher matcher = Pattern.compile(list.size() <= 0 ? "" : zzgVar.zza((zzao) list.get(0)).zzc()).matcher(this.zza);
                return matcher.find() ? new zzae(Arrays.asList(new zzas(matcher.group()))) : zzao.zzg;
            case 6:
                zzasVar = this;
                zzh.zzc("replace", 2, list);
                zzao zzaoVar = zzao.zzf;
                if (!list.isEmpty()) {
                    str7 = zzgVar.zza((zzao) list.get(0)).zzc();
                    if (list.size() > 1) {
                        zzaoVar = zzgVar.zza((zzao) list.get(1));
                        str8 = str7;
                        String str15 = zzasVar.zza;
                        indexOf = str15.indexOf(str8);
                        if (indexOf >= 0) {
                            if (zzaoVar instanceof zzai) {
                                zzaoVar = ((zzai) zzaoVar).zza(zzgVar, Arrays.asList(new zzas(str8), new zzah(Double.valueOf(indexOf)), zzasVar));
                            }
                            String substring = str15.substring(0, indexOf);
                            String zzc3 = zzaoVar.zzc();
                            String substring2 = str15.substring(indexOf + str8.length());
                            StringBuilder sb2 = new StringBuilder(String.valueOf(substring).length() + String.valueOf(zzc3).length() + String.valueOf(substring2).length());
                            sb2.append(substring);
                            sb2.append(zzc3);
                            sb2.append(substring2);
                            return new zzas(sb2.toString());
                        }
                    }
                }
                str8 = str7;
                String str152 = zzasVar.zza;
                indexOf = str152.indexOf(str8);
                if (indexOf >= 0) {
                }
                break;
            case 7:
                zzh.zzc(FirebaseAnalytics.Event.SEARCH, 1, list);
                return Pattern.compile(list.isEmpty() ? "undefined" : zzgVar.zza((zzao) list.get(0)).zzc()).matcher(this.zza).find() ? new zzah(Double.valueOf(r0.start())) : new zzah(Double.valueOf(-1.0d));
            case '\b':
                zzh.zzc("slice", 2, list);
                String str16 = this.zza;
                double zzi2 = zzh.zzi(!list.isEmpty() ? zzgVar.zza((zzao) list.get(0)).zzd().doubleValue() : 0.0d);
                double max = zzi2 < Utils.DOUBLE_EPSILON ? Math.max(str16.length() + zzi2, Utils.DOUBLE_EPSILON) : Math.min(zzi2, str16.length());
                double zzi3 = zzh.zzi(list.size() > 1 ? zzgVar.zza((zzao) list.get(1)).zzd().doubleValue() : str16.length());
                int i5 = (int) max;
                return new zzas(str16.substring(i5, Math.max(0, ((int) (zzi3 < Utils.DOUBLE_EPSILON ? Math.max(str16.length() + zzi3, Utils.DOUBLE_EPSILON) : Math.min(zzi3, str16.length()))) - i5) + i5));
            case '\t':
                zzh.zzc("split", 2, list);
                String str17 = this.zza;
                if (str17.length() == 0) {
                    return new zzae(Arrays.asList(this));
                }
                ArrayList arrayList = new ArrayList();
                if (list.isEmpty()) {
                    arrayList.add(this);
                } else {
                    String zzc4 = zzgVar.zza((zzao) list.get(0)).zzc();
                    long zzh = list.size() > 1 ? zzh.zzh(zzgVar.zza((zzao) list.get(1)).zzd().doubleValue()) : 2147483647L;
                    if (zzh == 0) {
                        return new zzae();
                    }
                    String[] split = str17.split(Pattern.quote(zzc4), ((int) zzh) + 1);
                    int length = split.length;
                    if (!zzc4.isEmpty() || length <= 0) {
                        i2 = length;
                        z = false;
                    } else {
                        boolean isEmpty = split[0].isEmpty();
                        i2 = length - 1;
                        if (!split[i2].isEmpty()) {
                            i2 = length;
                        }
                        z = isEmpty;
                    }
                    if (length > zzh) {
                        i2--;
                    }
                    for (?? r1 = z; r1 < i2; r1++) {
                        arrayList.add(new zzas(split[r1]));
                    }
                }
                return new zzae(arrayList);
            case '\n':
                zzh.zzc("substring", 2, list);
                String str18 = this.zza;
                if (list.isEmpty()) {
                    zzgVar2 = zzgVar;
                    i3 = 0;
                } else {
                    zzgVar2 = zzgVar;
                    i3 = (int) zzh.zzi(zzgVar2.zza((zzao) list.get(0)).zzd().doubleValue());
                }
                int zzi4 = list.size() > 1 ? (int) zzh.zzi(zzgVar2.zza((zzao) list.get(1)).zzd().doubleValue()) : str18.length();
                int min = Math.min(Math.max(i3, 0), str18.length());
                int min2 = Math.min(Math.max(zzi4, 0), str18.length());
                return new zzas(str18.substring(Math.min(min, min2), Math.max(min, min2)));
            case 11:
                zzh.zza(str2, 0, list);
                return new zzas(this.zza.toUpperCase());
            case '\f':
                zzh.zza("toLocaleLowerCase", 0, list);
                return new zzas(this.zza.toLowerCase());
            case '\r':
                zzh.zza("toLowerCase", 0, list);
                return new zzas(this.zza.toLowerCase(Locale.ENGLISH));
            case 14:
                String str19 = str5;
                zzasVar = this;
                zzh.zza(str19, 0, list);
                break;
            case 15:
                zzh.zza("toUpperCase", 0, list);
                return new zzas(this.zza.toUpperCase(Locale.ENGLISH));
            case 16:
                zzh.zza("toUpperCase", 0, list);
                return new zzas(this.zza.trim());
            default:
                throw new IllegalArgumentException("Command not supported");
        }
        return zzasVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        String str = this.zza;
        if (str.isEmpty()) {
            return Double.valueOf(Utils.DOUBLE_EPSILON);
        }
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return Double.valueOf(Double.NaN);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return new zzaq(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        return new zzas(this.zza);
    }
}
