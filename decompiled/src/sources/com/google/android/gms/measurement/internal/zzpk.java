package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.measurement.zzqp;
import com.google.android.gms.internal.measurement.zzrb;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.lang3.StringUtils;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzpk extends zzos {
    zzpk(zzpg zzpgVar) {
        super(zzpgVar);
    }

    static final void zzC(com.google.android.gms.internal.measurement.zzhr zzhrVar, String str, Object obj) {
        List zza = zzhrVar.zza();
        int i = 0;
        while (true) {
            if (i >= zza.size()) {
                i = -1;
                break;
            } else if (str.equals(((com.google.android.gms.internal.measurement.zzhw) zza.get(i)).zzb())) {
                break;
            } else {
                i++;
            }
        }
        com.google.android.gms.internal.measurement.zzhv zzn = com.google.android.gms.internal.measurement.zzhw.zzn();
        zzn.zzb(str);
        zzn.zzf(((Long) obj).longValue());
        if (i >= 0) {
            zzhrVar.zze(i, zzn);
        } else {
            zzhrVar.zzg(zzn);
        }
    }

    static final boolean zzD(zzbg zzbgVar, zzr zzrVar) {
        Preconditions.checkNotNull(zzbgVar);
        Preconditions.checkNotNull(zzrVar);
        return !TextUtils.isEmpty(zzrVar.zzb);
    }

    static final Bundle zzE(List list) {
        Bundle bundle = new Bundle();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.google.android.gms.internal.measurement.zzhw zzhwVar = (com.google.android.gms.internal.measurement.zzhw) it.next();
            String zzb = zzhwVar.zzb();
            if (zzhwVar.zzi()) {
                bundle.putDouble(zzb, zzhwVar.zzj());
            } else if (zzhwVar.zzg()) {
                bundle.putFloat(zzb, zzhwVar.zzh());
            } else if (zzhwVar.zzc()) {
                bundle.putString(zzb, zzhwVar.zzd());
            } else if (zzhwVar.zze()) {
                bundle.putLong(zzb, zzhwVar.zzf());
            }
        }
        return bundle;
    }

    static final com.google.android.gms.internal.measurement.zzhw zzF(com.google.android.gms.internal.measurement.zzhs zzhsVar, String str) {
        for (com.google.android.gms.internal.measurement.zzhw zzhwVar : zzhsVar.zza()) {
            if (zzhwVar.zzb().equals(str)) {
                return zzhwVar;
            }
        }
        return null;
    }

    static final Map zzG(com.google.android.gms.internal.measurement.zzhs zzhsVar, String... strArr) {
        Object zzP;
        HashMap hashMap = new HashMap();
        for (com.google.android.gms.internal.measurement.zzhw zzhwVar : zzhsVar.zza()) {
            if (Arrays.asList(strArr).contains(zzhwVar.zzb()) && (zzP = zzP(zzhwVar)) != null) {
                hashMap.put(zzhwVar.zzb(), zzP);
            }
        }
        return hashMap;
    }

    static final Map zzH(com.google.android.gms.internal.measurement.zzhs zzhsVar, String str) {
        Object zzP;
        HashMap hashMap = new HashMap();
        for (com.google.android.gms.internal.measurement.zzhw zzhwVar : zzhsVar.zza()) {
            if (zzhwVar.zzb().startsWith("gad_") && (zzP = zzP(zzhwVar)) != null) {
                hashMap.put(zzhwVar.zzb(), zzP);
            }
        }
        return hashMap;
    }

    static final Object zzI(com.google.android.gms.internal.measurement.zzhs zzhsVar, String str) {
        com.google.android.gms.internal.measurement.zzhw zzF = zzF(zzhsVar, str);
        if (zzF == null) {
            return null;
        }
        return zzP(zzF);
    }

    static final Object zzJ(com.google.android.gms.internal.measurement.zzhs zzhsVar, String str, Object obj) {
        Object zzI = zzI(zzhsVar, str);
        return zzI == null ? obj : zzI;
    }

    private final void zzK(StringBuilder sb, int i, List list) {
        if (list == null) {
            return;
        }
        int i2 = i + 1;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.google.android.gms.internal.measurement.zzhw zzhwVar = (com.google.android.gms.internal.measurement.zzhw) it.next();
            if (zzhwVar != null) {
                zzM(sb, i2);
                sb.append("param {\n");
                zzS(sb, i2, AppMeasurementSdk.ConditionalUserProperty.NAME, zzhwVar.zza() ? this.zzu.zzl().zzb(zzhwVar.zzb()) : null);
                zzS(sb, i2, "string_value", zzhwVar.zzc() ? zzhwVar.zzd() : null);
                zzS(sb, i2, "int_value", zzhwVar.zze() ? Long.valueOf(zzhwVar.zzf()) : null);
                zzS(sb, i2, "double_value", zzhwVar.zzi() ? Double.valueOf(zzhwVar.zzj()) : null);
                if (zzhwVar.zzm() > 0) {
                    zzK(sb, i2, zzhwVar.zzk());
                }
                zzM(sb, i2);
                sb.append("}\n");
            }
        }
    }

    private final void zzL(StringBuilder sb, int i, com.google.android.gms.internal.measurement.zzfh zzfhVar) {
        String str;
        if (zzfhVar == null) {
            return;
        }
        zzM(sb, i);
        sb.append("filter {\n");
        if (zzfhVar.zze()) {
            zzS(sb, i, "complement", Boolean.valueOf(zzfhVar.zzf()));
        }
        if (zzfhVar.zzg()) {
            zzS(sb, i, "param_name", this.zzu.zzl().zzb(zzfhVar.zzh()));
        }
        if (zzfhVar.zza()) {
            int i2 = i + 1;
            com.google.android.gms.internal.measurement.zzfr zzb = zzfhVar.zzb();
            if (zzb != null) {
                zzM(sb, i2);
                sb.append("string_filter {\n");
                if (zzb.zza()) {
                    switch (zzb.zzj()) {
                        case 1:
                            str = "UNKNOWN_MATCH_TYPE";
                            break;
                        case 2:
                            str = "REGEXP";
                            break;
                        case 3:
                            str = "BEGINS_WITH";
                            break;
                        case 4:
                            str = "ENDS_WITH";
                            break;
                        case 5:
                            str = "PARTIAL";
                            break;
                        case 6:
                            str = "EXACT";
                            break;
                        default:
                            str = "IN_LIST";
                            break;
                    }
                    zzS(sb, i2, "match_type", str);
                }
                if (zzb.zzb()) {
                    zzS(sb, i2, "expression", zzb.zzc());
                }
                if (zzb.zzd()) {
                    zzS(sb, i2, "case_sensitive", Boolean.valueOf(zzb.zze()));
                }
                if (zzb.zzg() > 0) {
                    zzM(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String str2 : zzb.zzf()) {
                        zzM(sb, i2 + 2);
                        sb.append(str2);
                        sb.append(StringUtils.LF);
                    }
                    sb.append("}\n");
                }
                zzM(sb, i2);
                sb.append("}\n");
            }
        }
        if (zzfhVar.zzc()) {
            zzT(sb, i + 1, "number_filter", zzfhVar.zzd());
        }
        zzM(sb, i);
        sb.append("}\n");
    }

    private static final void zzM(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static final void zzN(Uri.Builder builder, String str, String str2, Set set) {
        if (set.contains(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        builder.appendQueryParameter(str, str2);
    }

    private static final String zzO(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private static final Object zzP(com.google.android.gms.internal.measurement.zzhw zzhwVar) {
        if (zzhwVar.zzc()) {
            return zzhwVar.zzd();
        }
        if (zzhwVar.zze()) {
            return Long.valueOf(zzhwVar.zzf());
        }
        if (zzhwVar.zzi()) {
            return Double.valueOf(zzhwVar.zzj());
        }
        if (zzhwVar.zzm() > 0) {
            return zzy(zzhwVar.zzk());
        }
        return null;
    }

    private static final void zzQ(Uri.Builder builder, String[] strArr, Bundle bundle, Set set) {
        for (String str : strArr) {
            String[] split = str.split(",");
            String str2 = split[0];
            String str3 = split[split.length - 1];
            String string = bundle.getString(str2);
            if (string != null) {
                zzN(builder, str3, string, set);
            }
        }
    }

    private static final void zzR(StringBuilder sb, int i, String str, com.google.android.gms.internal.measurement.zzii zziiVar) {
        if (zziiVar == null) {
            return;
        }
        zzM(sb, 3);
        sb.append(str);
        sb.append(" {\n");
        if (zziiVar.zzd() != 0) {
            zzM(sb, 4);
            sb.append("results: ");
            int i2 = 0;
            for (Long l : zziiVar.zzc()) {
                int i3 = i2 + 1;
                if (i2 != 0) {
                    sb.append(", ");
                }
                sb.append(l);
                i2 = i3;
            }
            sb.append('\n');
        }
        if (zziiVar.zzb() != 0) {
            zzM(sb, 4);
            sb.append("status: ");
            int i4 = 0;
            for (Long l2 : zziiVar.zza()) {
                int i5 = i4 + 1;
                if (i4 != 0) {
                    sb.append(", ");
                }
                sb.append(l2);
                i4 = i5;
            }
            sb.append('\n');
        }
        if (zziiVar.zzf() != 0) {
            zzM(sb, 4);
            sb.append("dynamic_filter_timestamps: {");
            int i6 = 0;
            for (com.google.android.gms.internal.measurement.zzhq zzhqVar : zziiVar.zze()) {
                int i7 = i6 + 1;
                if (i6 != 0) {
                    sb.append(", ");
                }
                sb.append(zzhqVar.zza() ? Integer.valueOf(zzhqVar.zzb()) : null);
                sb.append(":");
                sb.append(zzhqVar.zzc() ? Long.valueOf(zzhqVar.zzd()) : null);
                i6 = i7;
            }
            sb.append("}\n");
        }
        if (zziiVar.zzh() != 0) {
            zzM(sb, 4);
            sb.append("sequence_filter_timestamps: {");
            int i8 = 0;
            for (com.google.android.gms.internal.measurement.zzik zzikVar : zziiVar.zzg()) {
                int i9 = i8 + 1;
                if (i8 != 0) {
                    sb.append(", ");
                }
                sb.append(zzikVar.zza() ? Integer.valueOf(zzikVar.zzb()) : null);
                sb.append(": [");
                Iterator it = zzikVar.zzc().iterator();
                int i10 = 0;
                while (it.hasNext()) {
                    long longValue = ((Long) it.next()).longValue();
                    int i11 = i10 + 1;
                    if (i10 != 0) {
                        sb.append(", ");
                    }
                    sb.append(longValue);
                    i10 = i11;
                }
                sb.append("]");
                i8 = i9;
            }
            sb.append("}\n");
        }
        zzM(sb, 3);
        sb.append("}\n");
    }

    private static final void zzS(StringBuilder sb, int i, String str, Object obj) {
        if (obj == null) {
            return;
        }
        zzM(sb, i + 1);
        sb.append(str);
        sb.append(": ");
        sb.append(obj);
        sb.append('\n');
    }

    private static final void zzT(StringBuilder sb, int i, String str, com.google.android.gms.internal.measurement.zzfl zzflVar) {
        String str2;
        if (zzflVar == null) {
            return;
        }
        zzM(sb, i);
        sb.append(str);
        sb.append(" {\n");
        if (zzflVar.zza()) {
            switch (zzflVar.zzm()) {
                case 1:
                    str2 = "UNKNOWN_COMPARISON_TYPE";
                    break;
                case 2:
                    str2 = "LESS_THAN";
                    break;
                case 3:
                    str2 = "GREATER_THAN";
                    break;
                case 4:
                    str2 = "EQUAL";
                    break;
                default:
                    str2 = "BETWEEN";
                    break;
            }
            zzS(sb, i, "comparison_type", str2);
        }
        if (zzflVar.zzb()) {
            zzS(sb, i, "match_as_float", Boolean.valueOf(zzflVar.zzc()));
        }
        if (zzflVar.zzd()) {
            zzS(sb, i, "comparison_value", zzflVar.zze());
        }
        if (zzflVar.zzf()) {
            zzS(sb, i, "min_comparison_value", zzflVar.zzg());
        }
        if (zzflVar.zzh()) {
            zzS(sb, i, "max_comparison_value", zzflVar.zzi());
        }
        zzM(sb, i);
        sb.append("}\n");
    }

    static boolean zzm(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean zzn(List list, int i) {
        if (i < list.size() * 64) {
            return ((1 << (i % 64)) & ((Long) list.get(i / 64)).longValue()) != 0;
        }
        return false;
    }

    static List zzp(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i * 64) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    static com.google.android.gms.internal.measurement.zznl zzw(com.google.android.gms.internal.measurement.zznl zznlVar, byte[] bArr) throws com.google.android.gms.internal.measurement.zzmr {
        com.google.android.gms.internal.measurement.zzlr zza = com.google.android.gms.internal.measurement.zzlr.zza();
        return zza != null ? zznlVar.zzaV(bArr, zza) : zznlVar.zzaW(bArr);
    }

    static int zzx(com.google.android.gms.internal.measurement.zzic zzicVar, String str) {
        for (int i = 0; i < zzicVar.zzl(); i++) {
            if (str.equals(zzicVar.zzm(i).zzc())) {
                return i;
            }
        }
        return -1;
    }

    static Bundle[] zzy(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.google.android.gms.internal.measurement.zzhw zzhwVar = (com.google.android.gms.internal.measurement.zzhw) it.next();
            if (zzhwVar != null) {
                Bundle bundle = new Bundle();
                for (com.google.android.gms.internal.measurement.zzhw zzhwVar2 : zzhwVar.zzk()) {
                    if (zzhwVar2.zzc()) {
                        bundle.putString(zzhwVar2.zzb(), zzhwVar2.zzd());
                    } else if (zzhwVar2.zze()) {
                        bundle.putLong(zzhwVar2.zzb(), zzhwVar2.zzf());
                    } else if (zzhwVar2.zzi()) {
                        bundle.putDouble(zzhwVar2.zzb(), zzhwVar2.zzj());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    final zzbg zzA(com.google.android.gms.internal.measurement.zzaa zzaaVar) {
        Object obj;
        Bundle zzB = zzB(zzaaVar.zzf(), true);
        String obj2 = (!zzB.containsKey("_o") || (obj = zzB.get("_o")) == null) ? "app" : obj.toString();
        String zzb = zzjm.zzb(zzaaVar.zzb());
        return new zzbg(zzb == null ? zzaaVar.zzb() : zzb, new zzbe(zzB), obj2, zzaaVar.zza());
    }

    final Bundle zzB(Map map, boolean z) {
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                bundle.putString(str, null);
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList)) {
                bundle.putString(str, obj.toString());
            } else if (z) {
                ArrayList arrayList = (ArrayList) obj;
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    arrayList2.add(zzB((Map) arrayList.get(i), false));
                }
                bundle.putParcelableArray(str, (Parcelable[]) arrayList2.toArray(new Parcelable[0]));
            }
        }
        return bundle;
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    protected final boolean zzbb() {
        return false;
    }

    final void zzc(com.google.android.gms.internal.measurement.zzit zzitVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzitVar.zzd();
        zzitVar.zzf();
        zzitVar.zzh();
        if (obj instanceof String) {
            zzitVar.zzc((String) obj);
            return;
        }
        if (obj instanceof Long) {
            zzitVar.zze(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzitVar.zzg(((Double) obj).doubleValue());
        } else {
            this.zzu.zzaV().zzb().zzb("Ignoring invalid (type) user attribute value", obj);
        }
    }

    final void zzd(com.google.android.gms.internal.measurement.zzhv zzhvVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzhvVar.zze();
        zzhvVar.zzg();
        zzhvVar.zzi();
        zzhvVar.zzm();
        if (obj instanceof String) {
            zzhvVar.zzd((String) obj);
            return;
        }
        if (obj instanceof Long) {
            zzhvVar.zzf(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Double) {
            zzhvVar.zzh(((Double) obj).doubleValue());
            return;
        }
        if (!(obj instanceof Bundle[])) {
            this.zzu.zzaV().zzb().zzb("Ignoring invalid (type) event param value", obj);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : (Bundle[]) obj) {
            if (bundle != null) {
                com.google.android.gms.internal.measurement.zzhv zzn = com.google.android.gms.internal.measurement.zzhw.zzn();
                for (String str : bundle.keySet()) {
                    com.google.android.gms.internal.measurement.zzhv zzn2 = com.google.android.gms.internal.measurement.zzhw.zzn();
                    zzn2.zzb(str);
                    Object obj2 = bundle.get(str);
                    if (obj2 instanceof Long) {
                        zzn2.zzf(((Long) obj2).longValue());
                    } else if (obj2 instanceof String) {
                        zzn2.zzd((String) obj2);
                    } else if (obj2 instanceof Double) {
                        zzn2.zzh(((Double) obj2).doubleValue());
                    }
                    zzn.zzk(zzn2);
                }
                if (zzn.zzj() > 0) {
                    arrayList.add((com.google.android.gms.internal.measurement.zzhw) zzn.zzbc());
                }
            }
        }
        zzhvVar.zzl(arrayList);
    }

    final zzoh zzf(String str, com.google.android.gms.internal.measurement.zzic zzicVar, com.google.android.gms.internal.measurement.zzhr zzhrVar, String str2) {
        int indexOf;
        zzqp.zza();
        zzic zzicVar2 = this.zzu;
        if (!zzicVar2.zzc().zzp(str, zzfy.zzaP)) {
            return null;
        }
        long currentTimeMillis = zzicVar2.zzaZ().currentTimeMillis();
        Set m = zzpk$$ExternalSyntheticBackport0.m(zzicVar2.zzc().zzk(str, zzfy.zzau).split(","));
        zzpg zzpgVar = this.zzg;
        zzou zzf = zzpgVar.zzf();
        String zzc = zzf.zzg.zzh().zzc(str);
        Uri.Builder builder = new Uri.Builder();
        zzic zzicVar3 = zzf.zzu;
        builder.scheme(zzicVar3.zzc().zzk(str, zzfy.zzan));
        if (TextUtils.isEmpty(zzc)) {
            builder.authority(zzicVar3.zzc().zzk(str, zzfy.zzao));
        } else {
            String zzk = zzicVar3.zzc().zzk(str, zzfy.zzao);
            StringBuilder sb = new StringBuilder(String.valueOf(zzc).length() + 1 + String.valueOf(zzk).length());
            sb.append(zzc);
            sb.append(".");
            sb.append(zzk);
            builder.authority(sb.toString());
        }
        builder.path(zzicVar3.zzc().zzk(str, zzfy.zzap));
        zzN(builder, "gmp_app_id", zzicVar.zzac(), m);
        zzicVar2.zzc().zzi();
        zzN(builder, "gmp_version", String.valueOf(133005L), m);
        String zzV = zzicVar.zzV();
        zzal zzc2 = zzicVar2.zzc();
        zzfx zzfxVar = zzfy.zzaS;
        if (zzc2.zzp(str, zzfxVar) && zzpgVar.zzh().zzt(str)) {
            zzV = "";
        }
        zzN(builder, "app_instance_id", zzV, m);
        zzN(builder, "rdid", zzicVar.zzP(), m);
        zzN(builder, "bundle_id", zzicVar.zzK(), m);
        String zzk2 = zzhrVar.zzk();
        String zza = zzjm.zza(zzk2);
        if (true != TextUtils.isEmpty(zza)) {
            zzk2 = zza;
        }
        zzN(builder, "app_event_name", zzk2, m);
        zzN(builder, "app_version", String.valueOf(zzicVar.zzai()), m);
        String zzD = zzicVar.zzD();
        if (zzicVar2.zzc().zzp(str, zzfxVar) && zzpgVar.zzh().zzq(str) && !TextUtils.isEmpty(zzD) && (indexOf = zzD.indexOf(".")) != -1) {
            zzD = zzD.substring(0, indexOf);
        }
        zzN(builder, "os_version", zzD, m);
        zzN(builder, "timestamp", String.valueOf(zzhrVar.zzn()), m);
        if (zzicVar.zzS()) {
            zzN(builder, "lat", "1", m);
        }
        zzN(builder, "privacy_sandbox_version", String.valueOf(zzicVar.zzaG()), m);
        zzN(builder, "trigger_uri_source", "1", m);
        zzN(builder, "trigger_uri_timestamp", String.valueOf(currentTimeMillis), m);
        zzN(builder, "request_uuid", str2, m);
        List<com.google.android.gms.internal.measurement.zzhw> zza2 = zzhrVar.zza();
        Bundle bundle = new Bundle();
        for (com.google.android.gms.internal.measurement.zzhw zzhwVar : zza2) {
            String zzb = zzhwVar.zzb();
            if (zzhwVar.zzi()) {
                bundle.putString(zzb, String.valueOf(zzhwVar.zzj()));
            } else if (zzhwVar.zzg()) {
                bundle.putString(zzb, String.valueOf(zzhwVar.zzh()));
            } else if (zzhwVar.zzc()) {
                bundle.putString(zzb, zzhwVar.zzd());
            } else if (zzhwVar.zze()) {
                bundle.putString(zzb, String.valueOf(zzhwVar.zzf()));
            }
        }
        zzQ(builder, zzicVar2.zzc().zzk(str, zzfy.zzat).split("\\|"), bundle, m);
        List<com.google.android.gms.internal.measurement.zziu> zzk3 = zzicVar.zzk();
        Bundle bundle2 = new Bundle();
        for (com.google.android.gms.internal.measurement.zziu zziuVar : zzk3) {
            String zzc3 = zziuVar.zzc();
            if (zziuVar.zzj()) {
                bundle2.putString(zzc3, String.valueOf(zziuVar.zzk()));
            } else if (zziuVar.zzh()) {
                bundle2.putString(zzc3, String.valueOf(zziuVar.zzi()));
            } else if (zziuVar.zzd()) {
                bundle2.putString(zzc3, zziuVar.zze());
            } else if (zziuVar.zzf()) {
                bundle2.putString(zzc3, String.valueOf(zziuVar.zzg()));
            }
        }
        zzQ(builder, zzicVar2.zzc().zzk(str, zzfy.zzas).split("\\|"), bundle2, m);
        zzN(builder, "dma", true != zzicVar.zzaC() ? "0" : "1", m);
        if (!zzicVar.zzaE().isEmpty()) {
            zzN(builder, "dma_cps", zzicVar.zzaE(), m);
        }
        if (zzicVar.zzaK()) {
            com.google.android.gms.internal.measurement.zzha zzaL = zzicVar.zzaL();
            if (!zzaL.zzb().isEmpty()) {
                zzN(builder, "dl_gclid", zzaL.zzb(), m);
            }
            if (!zzaL.zzd().isEmpty()) {
                zzN(builder, "dl_gbraid", zzaL.zzd(), m);
            }
            if (!zzaL.zzf().isEmpty()) {
                zzN(builder, "dl_gs", zzaL.zzf(), m);
            }
            if (zzaL.zzh() > 0) {
                zzN(builder, "dl_ss_ts", String.valueOf(zzaL.zzh()), m);
            }
            if (!zzaL.zzj().isEmpty()) {
                zzN(builder, "mr_gclid", zzaL.zzj(), m);
            }
            if (!zzaL.zzm().isEmpty()) {
                zzN(builder, "mr_gbraid", zzaL.zzm(), m);
            }
            if (!zzaL.zzo().isEmpty()) {
                zzN(builder, "mr_gs", zzaL.zzo(), m);
            }
            if (zzaL.zzq() > 0) {
                zzN(builder, "mr_click_ts", String.valueOf(zzaL.zzq()), m);
            }
        }
        return new zzoh(builder.build().toString(), currentTimeMillis, 1);
    }

    final com.google.android.gms.internal.measurement.zzhs zzh(zzbb zzbbVar) {
        com.google.android.gms.internal.measurement.zzhr zzk = com.google.android.gms.internal.measurement.zzhs.zzk();
        zzk.zzq(zzbbVar.zze);
        zzbe zzbeVar = zzbbVar.zzf;
        zzbd zzbdVar = new zzbd(zzbeVar);
        while (zzbdVar.hasNext()) {
            String next = zzbdVar.next();
            com.google.android.gms.internal.measurement.zzhv zzn = com.google.android.gms.internal.measurement.zzhw.zzn();
            zzn.zzb(next);
            Object zza = zzbeVar.zza(next);
            Preconditions.checkNotNull(zza);
            zzd(zzn, zza);
            zzk.zzg(zzn);
        }
        String str = zzbbVar.zzc;
        if (!TextUtils.isEmpty(str) && zzbeVar.zza("_o") == null) {
            com.google.android.gms.internal.measurement.zzhv zzn2 = com.google.android.gms.internal.measurement.zzhw.zzn();
            zzn2.zzb("_o");
            zzn2.zzd(str);
            zzk.zzf((com.google.android.gms.internal.measurement.zzhw) zzn2.zzbc());
        }
        return (com.google.android.gms.internal.measurement.zzhs) zzk.zzbc();
    }

    final String zzi(com.google.android.gms.internal.measurement.zzib zzibVar) {
        String str;
        String str2;
        String str3;
        String str4;
        com.google.android.gms.internal.measurement.zzhe zzat;
        if (zzibVar == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzibVar.zzf()) {
            zzS(sb, 0, "upload_subdomain", zzibVar.zzg());
        }
        if (zzibVar.zzd()) {
            zzS(sb, 0, "sgtm_join_id", zzibVar.zze());
        }
        for (com.google.android.gms.internal.measurement.zzid zzidVar : zzibVar.zza()) {
            if (zzidVar != null) {
                zzM(sb, 1);
                sb.append("bundle {\n");
                if (zzidVar.zza()) {
                    zzS(sb, 1, "protocol_version", Integer.valueOf(zzidVar.zzb()));
                }
                zzrb.zza();
                zzic zzicVar = this.zzu;
                if (zzicVar.zzc().zzp(zzidVar.zzA(), zzfy.zzaM) && zzidVar.zzag()) {
                    zzS(sb, 1, "session_stitching_token", zzidVar.zzah());
                }
                zzS(sb, 1, "platform", zzidVar.zzt());
                if (zzidVar.zzC()) {
                    zzS(sb, 1, "gmp_version", Long.valueOf(zzidVar.zzD()));
                }
                if (zzidVar.zzE()) {
                    zzS(sb, 1, "uploading_gmp_version", Long.valueOf(zzidVar.zzF()));
                }
                if (zzidVar.zzac()) {
                    zzS(sb, 1, "dynamite_version", Long.valueOf(zzidVar.zzad()));
                }
                if (zzidVar.zzW()) {
                    zzS(sb, 1, "config_version", Long.valueOf(zzidVar.zzX()));
                }
                zzS(sb, 1, "gmp_app_id", zzidVar.zzP());
                zzS(sb, 1, "app_id", zzidVar.zzA());
                zzS(sb, 1, "app_version", zzidVar.zzB());
                if (zzidVar.zzU()) {
                    zzS(sb, 1, "app_version_major", Integer.valueOf(zzidVar.zzV()));
                }
                zzS(sb, 1, "firebase_instance_id", zzidVar.zzT());
                if (zzidVar.zzK()) {
                    zzS(sb, 1, "dev_cert_hash", Long.valueOf(zzidVar.zzL()));
                }
                zzS(sb, 1, "app_store", zzidVar.zzz());
                if (zzidVar.zzi()) {
                    zzS(sb, 1, "upload_timestamp_millis", Long.valueOf(zzidVar.zzj()));
                }
                if (zzidVar.zzk()) {
                    zzS(sb, 1, "start_timestamp_millis", Long.valueOf(zzidVar.zzm()));
                }
                if (zzidVar.zzn()) {
                    zzS(sb, 1, "end_timestamp_millis", Long.valueOf(zzidVar.zzo()));
                }
                if (zzidVar.zzp()) {
                    zzS(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(zzidVar.zzq()));
                }
                if (zzidVar.zzr()) {
                    zzS(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(zzidVar.zzs()));
                }
                zzS(sb, 1, "app_instance_id", zzidVar.zzJ());
                zzS(sb, 1, "resettable_device_id", zzidVar.zzG());
                zzS(sb, 1, "ds_id", zzidVar.zzZ());
                if (zzidVar.zzH()) {
                    zzS(sb, 1, "limited_ad_tracking", Boolean.valueOf(zzidVar.zzI()));
                }
                zzS(sb, 1, "os_version", zzidVar.zzu());
                zzS(sb, 1, "device_model", zzidVar.zzv());
                zzS(sb, 1, "user_default_language", zzidVar.zzw());
                if (zzidVar.zzx()) {
                    zzS(sb, 1, "time_zone_offset_minutes", Integer.valueOf(zzidVar.zzy()));
                }
                if (zzidVar.zzM()) {
                    zzS(sb, 1, "bundle_sequential_index", Integer.valueOf(zzidVar.zzN()));
                }
                if (zzidVar.zzau()) {
                    zzS(sb, 1, "delivery_index", Integer.valueOf(zzidVar.zzav()));
                }
                if (zzidVar.zzQ()) {
                    zzS(sb, 1, "service_upload", Boolean.valueOf(zzidVar.zzR()));
                }
                zzS(sb, 1, "health_monitor", zzidVar.zzO());
                if (zzidVar.zzaa()) {
                    zzS(sb, 1, "retry_counter", Integer.valueOf(zzidVar.zzab()));
                }
                if (zzidVar.zzae()) {
                    zzS(sb, 1, "consent_signals", zzidVar.zzaf());
                }
                if (zzidVar.zzan()) {
                    zzS(sb, 1, "is_dma_region", Boolean.valueOf(zzidVar.zzao()));
                }
                if (zzidVar.zzap()) {
                    zzS(sb, 1, "core_platform_services", zzidVar.zzaq());
                }
                if (zzidVar.zzal()) {
                    zzS(sb, 1, "consent_diagnostics", zzidVar.zzam());
                }
                if (zzidVar.zzai()) {
                    zzS(sb, 1, "target_os_version", Long.valueOf(zzidVar.zzaj()));
                }
                zzqp.zza();
                if (zzicVar.zzc().zzp(zzidVar.zzA(), zzfy.zzaP)) {
                    zzS(sb, 1, "ad_services_version", Integer.valueOf(zzidVar.zzar()));
                    if (zzidVar.zzas() && (zzat = zzidVar.zzat()) != null) {
                        zzM(sb, 2);
                        sb.append("attribution_eligibility_status {\n");
                        zzS(sb, 2, "eligible", Boolean.valueOf(zzat.zza()));
                        zzS(sb, 2, "no_access_adservices_attribution_permission", Boolean.valueOf(zzat.zzb()));
                        zzS(sb, 2, "pre_r", Boolean.valueOf(zzat.zzc()));
                        zzS(sb, 2, "r_extensions_too_old", Boolean.valueOf(zzat.zzd()));
                        zzS(sb, 2, "adservices_extension_too_old", Boolean.valueOf(zzat.zze()));
                        zzS(sb, 2, "ad_storage_not_allowed", Boolean.valueOf(zzat.zzf()));
                        zzS(sb, 2, "measurement_manager_disabled", Boolean.valueOf(zzat.zzg()));
                        zzM(sb, 2);
                        sb.append("}\n");
                    }
                }
                if (zzidVar.zzaw()) {
                    com.google.android.gms.internal.measurement.zzha zzax = zzidVar.zzax();
                    zzM(sb, 2);
                    sb.append("ad_campaign_info {\n");
                    if (zzax.zza()) {
                        zzS(sb, 2, "deep_link_gclid", zzax.zzb());
                    }
                    if (zzax.zzc()) {
                        zzS(sb, 2, "deep_link_gbraid", zzax.zzd());
                    }
                    if (zzax.zze()) {
                        zzS(sb, 2, "deep_link_gad_source", zzax.zzf());
                    }
                    if (zzax.zzg()) {
                        zzS(sb, 2, "deep_link_session_millis", Long.valueOf(zzax.zzh()));
                    }
                    if (zzax.zzi()) {
                        zzS(sb, 2, "market_referrer_gclid", zzax.zzj());
                    }
                    if (zzax.zzk()) {
                        zzS(sb, 2, "market_referrer_gbraid", zzax.zzm());
                    }
                    if (zzax.zzn()) {
                        zzS(sb, 2, "market_referrer_gad_source", zzax.zzo());
                    }
                    if (zzax.zzp()) {
                        zzS(sb, 2, "market_referrer_click_millis", Long.valueOf(zzax.zzq()));
                    }
                    zzM(sb, 2);
                    sb.append("}\n");
                }
                if (zzidVar.zzaA()) {
                    zzS(sb, 1, "batching_timestamp_millis", Long.valueOf(zzidVar.zzaB()));
                }
                if (zzidVar.zzay()) {
                    com.google.android.gms.internal.measurement.zzis zzaz = zzidVar.zzaz();
                    zzM(sb, 2);
                    sb.append("sgtm_diagnostics {\n");
                    switch (zzaz.zzf()) {
                        case 1:
                            str3 = "UPLOAD_TYPE_UNKNOWN";
                            break;
                        case 2:
                            str3 = "GA_UPLOAD";
                            break;
                        case 3:
                            str3 = "SDK_CLIENT_UPLOAD";
                            break;
                        case 4:
                            str3 = "PACKAGE_SERVICE_UPLOAD";
                            break;
                        default:
                            str3 = "SDK_SERVICE_UPLOAD";
                            break;
                    }
                    zzS(sb, 2, "upload_type", str3);
                    zzS(sb, 2, "client_upload_eligibility", zzaz.zza().name());
                    switch (zzaz.zzg()) {
                        case 1:
                            str4 = "SERVICE_UPLOAD_ELIGIBILITY_UNKNOWN";
                            break;
                        case 2:
                            str4 = "SERVICE_UPLOAD_ELIGIBLE";
                            break;
                        case 3:
                            str4 = "NOT_IN_ROLLOUT";
                            break;
                        case 4:
                            str4 = "MISSING_SGTM_SETTINGS";
                            break;
                        case 5:
                            str4 = "MISSING_SGTM_PROXY_INFO";
                            break;
                        default:
                            str4 = "NON_PLAY_MISSING_SGTM_SERVER_URL";
                            break;
                    }
                    zzS(sb, 2, "service_upload_eligibility", str4);
                    zzM(sb, 2);
                    sb.append("}\n");
                }
                if (zzidVar.zzaC()) {
                    com.google.android.gms.internal.measurement.zzho zzaD = zzidVar.zzaD();
                    zzM(sb, 2);
                    sb.append("consent_info_extra {\n");
                    for (com.google.android.gms.internal.measurement.zzhl zzhlVar : zzaD.zza()) {
                        zzM(sb, 3);
                        sb.append("limited_data_modes {\n");
                        switch (zzhlVar.zzc()) {
                            case 1:
                                str = "CONSENT_TYPE_UNSPECIFIED";
                                break;
                            case 2:
                                str = "AD_STORAGE";
                                break;
                            case 3:
                                str = "ANALYTICS_STORAGE";
                                break;
                            case 4:
                                str = "AD_USER_DATA";
                                break;
                            default:
                                str = "AD_PERSONALIZATION";
                                break;
                        }
                        zzS(sb, 3, "type", str);
                        switch (zzhlVar.zzd()) {
                            case 1:
                                str2 = "NOT_LIMITED";
                                break;
                            case 2:
                                str2 = "LIMITED_MODE";
                                break;
                            default:
                                str2 = "NO_DATA_MODE";
                                break;
                        }
                        zzS(sb, 3, "mode", str2);
                        zzM(sb, 3);
                        sb.append("}\n");
                    }
                    zzM(sb, 2);
                    sb.append("}\n");
                }
                List<com.google.android.gms.internal.measurement.zziu> zzf = zzidVar.zzf();
                if (zzf != null) {
                    for (com.google.android.gms.internal.measurement.zziu zziuVar : zzf) {
                        if (zziuVar != null) {
                            zzM(sb, 2);
                            sb.append("user_property {\n");
                            zzS(sb, 2, "set_timestamp_millis", zziuVar.zza() ? Long.valueOf(zziuVar.zzb()) : null);
                            zzS(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, zzicVar.zzl().zzc(zziuVar.zzc()));
                            zzS(sb, 2, "string_value", zziuVar.zze());
                            zzS(sb, 2, "int_value", zziuVar.zzf() ? Long.valueOf(zziuVar.zzg()) : null);
                            zzS(sb, 2, "double_value", zziuVar.zzj() ? Double.valueOf(zziuVar.zzk()) : null);
                            zzM(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<com.google.android.gms.internal.measurement.zzhg> zzS = zzidVar.zzS();
                if (zzS != null) {
                    for (com.google.android.gms.internal.measurement.zzhg zzhgVar : zzS) {
                        if (zzhgVar != null) {
                            zzM(sb, 2);
                            sb.append("audience_membership {\n");
                            if (zzhgVar.zza()) {
                                zzS(sb, 2, "audience_id", Integer.valueOf(zzhgVar.zzb()));
                            }
                            if (zzhgVar.zzf()) {
                                zzS(sb, 2, "new_audience", Boolean.valueOf(zzhgVar.zzg()));
                            }
                            zzR(sb, 2, "current_data", zzhgVar.zzc());
                            if (zzhgVar.zzd()) {
                                zzR(sb, 2, "previous_data", zzhgVar.zze());
                            }
                            zzM(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<com.google.android.gms.internal.measurement.zzhs> zzc = zzidVar.zzc();
                if (zzc != null) {
                    for (com.google.android.gms.internal.measurement.zzhs zzhsVar : zzc) {
                        if (zzhsVar != null) {
                            zzM(sb, 2);
                            sb.append("event {\n");
                            zzS(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, zzicVar.zzl().zza(zzhsVar.zzd()));
                            if (zzhsVar.zze()) {
                                zzS(sb, 2, "timestamp_millis", Long.valueOf(zzhsVar.zzf()));
                            }
                            if (zzhsVar.zzg()) {
                                zzS(sb, 2, "previous_timestamp_millis", Long.valueOf(zzhsVar.zzh()));
                            }
                            if (zzhsVar.zzi()) {
                                zzS(sb, 2, "count", Integer.valueOf(zzhsVar.zzj()));
                            }
                            if (zzhsVar.zzb() != 0) {
                                zzK(sb, 2, zzhsVar.zza());
                            }
                            zzM(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zzM(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("} // End-of-batch\n");
        return sb.toString();
    }

    final String zzj(com.google.android.gms.internal.measurement.zzff zzffVar) {
        if (zzffVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzffVar.zza()) {
            zzS(sb, 0, "filter_id", Integer.valueOf(zzffVar.zzb()));
        }
        zzS(sb, 0, "event_name", this.zzu.zzl().zza(zzffVar.zzc()));
        String zzO = zzO(zzffVar.zzi(), zzffVar.zzj(), zzffVar.zzm());
        if (!zzO.isEmpty()) {
            zzS(sb, 0, "filter_type", zzO);
        }
        if (zzffVar.zzg()) {
            zzT(sb, 1, "event_count_filter", zzffVar.zzh());
        }
        if (zzffVar.zze() > 0) {
            sb.append("  filters {\n");
            Iterator it = zzffVar.zzd().iterator();
            while (it.hasNext()) {
                zzL(sb, 2, (com.google.android.gms.internal.measurement.zzfh) it.next());
            }
        }
        zzM(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    final String zzk(com.google.android.gms.internal.measurement.zzfn zzfnVar) {
        if (zzfnVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzfnVar.zza()) {
            zzS(sb, 0, "filter_id", Integer.valueOf(zzfnVar.zzb()));
        }
        zzS(sb, 0, "property_name", this.zzu.zzl().zzc(zzfnVar.zzc()));
        String zzO = zzO(zzfnVar.zze(), zzfnVar.zzf(), zzfnVar.zzh());
        if (!zzO.isEmpty()) {
            zzS(sb, 0, "filter_type", zzO);
        }
        zzL(sb, 1, zzfnVar.zzd());
        sb.append("}\n");
        return sb.toString();
    }

    final Parcelable zzl(byte[] bArr, Parcelable.Creator creator) {
        Parcelable parcelable = null;
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            try {
                obtain.unmarshall(bArr, 0, bArr.length);
                obtain.setDataPosition(0);
                parcelable = (Parcelable) creator.createFromParcel(obtain);
            } catch (SafeParcelReader.ParseException e) {
                this.zzu.zzaV().zzb().zza("Failed to load parcelable from buffer");
            }
            return parcelable;
        } finally {
            obtain.recycle();
        }
    }

    final List zzq(List list, List list2) {
        ArrayList arrayList = new ArrayList(list);
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            if (num.intValue() < 0) {
                this.zzu.zzaV().zze().zzb("Ignoring negative bit index to be cleared", num);
            } else {
                int intValue = num.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    this.zzu.zzaV().zze().zzc("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & (~(1 << (num.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        for (int size2 = arrayList.size() - 1; size2 >= 0 && ((Long) arrayList.get(size2)).longValue() == 0; size2--) {
            size = size2;
        }
        return arrayList.subList(0, size);
    }

    final boolean zzs(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(this.zzu.zzaZ().currentTimeMillis() - j) > j2;
    }

    final long zzt(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzic zzicVar = this.zzu;
        zzicVar.zzk().zzg();
        MessageDigest zzO = zzpp.zzO();
        if (zzO != null) {
            return zzpp.zzP(zzO.digest(bArr));
        }
        zzicVar.zzaV().zzb().zza("Failed to get MD5");
        return 0L;
    }

    final long zzu(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        return zzt(str.getBytes(Charset.forName("UTF-8")));
    }

    final byte[] zzv(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            this.zzu.zzaV().zzb().zzb("Failed to gzip content", e);
            throw e;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0032, code lost:
    
        r5 = new java.util.ArrayList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0038, code lost:
    
        if (r4 == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        r3 = (android.os.Parcelable[]) r3;
        r4 = r3.length;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
    
        if (r7 >= r4) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:
    
        r8 = r3[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0044, code lost:
    
        if ((r8 instanceof android.os.Bundle) == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0046, code lost:
    
        r5.add(zzz((android.os.Bundle) r8, false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
    
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0080, code lost:
    
        r0.put(r2, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0054, code lost:
    
        if ((r3 instanceof java.util.ArrayList) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0056, code lost:
    
        r3 = (java.util.ArrayList) r3;
        r4 = r3.size();
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005d, code lost:
    
        if (r7 >= r4) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005f, code lost:
    
        r8 = r3.get(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0065, code lost:
    
        if ((r8 instanceof android.os.Bundle) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
    
        r5.add(zzz((android.os.Bundle) r8, false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0070, code lost:
    
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0075, code lost:
    
        if ((r3 instanceof android.os.Bundle) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0077, code lost:
    
        r5.add(zzz((android.os.Bundle) r3, false));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final Map zzz(Bundle bundle, boolean z) {
        HashMap hashMap = new HashMap();
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            Object obj = bundle.get(next);
            boolean z2 = obj instanceof Parcelable[];
            if (!z2 && !(obj instanceof ArrayList) && !(obj instanceof Bundle)) {
                if (obj != null) {
                    hashMap.put(next, obj);
                }
            }
        }
        return hashMap;
    }
}
