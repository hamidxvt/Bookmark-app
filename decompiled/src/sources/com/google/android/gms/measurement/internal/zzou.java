package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzou extends zzol {
    zzou(zzpg zzpgVar) {
        super(zzpgVar);
    }

    private final String zzd(String str) {
        String zzc = this.zzg.zzh().zzc(str);
        if (TextUtils.isEmpty(zzc)) {
            return (String) zzfy.zzq.zzb(null);
        }
        Uri parse = Uri.parse((String) zzfy.zzq.zzb(null));
        Uri.Builder buildUpon = parse.buildUpon();
        String authority = parse.getAuthority();
        StringBuilder sb = new StringBuilder(String.valueOf(zzc).length() + 1 + String.valueOf(authority).length());
        sb.append(zzc);
        sb.append(".");
        sb.append(authority);
        buildUpon.authority(sb.toString());
        return buildUpon.build().toString();
    }

    private static final boolean zzf(String str) {
        String str2 = (String) zzfy.zzs.zzb(null);
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        for (String str3 : str2.split(",")) {
            if (str.equalsIgnoreCase(str3.trim())) {
                return true;
            }
        }
        return false;
    }

    public final zzot zza(String str) {
        zzh zzu;
        zzpg zzpgVar = this.zzg;
        zzh zzu2 = zzpgVar.zzj().zzu(str);
        zzot zzotVar = null;
        if (zzu2 == null || !zzu2.zzai()) {
            return new zzot(zzd(str), Collections.emptyMap(), zzls.GOOGLE_ANALYTICS, null);
        }
        com.google.android.gms.internal.measurement.zzil zzb = com.google.android.gms.internal.measurement.zzis.zzb();
        zzb.zzb(2);
        zzb.zza((com.google.android.gms.internal.measurement.zzin) Preconditions.checkNotNull(com.google.android.gms.internal.measurement.zzin.zzb(zzu2.zzaL())));
        String zzd = zzu2.zzd();
        com.google.android.gms.internal.measurement.zzgl zzb2 = zzpgVar.zzh().zzb(str);
        if (zzb2 == null || (zzu = zzpgVar.zzj().zzu(str)) == null || ((!zzb2.zzp() || zzb2.zzq().zzc() != 100) && !this.zzu.zzk().zzaa(str, zzu.zzay()) && (TextUtils.isEmpty(zzd) || Math.abs(zzd.hashCode() % 100) >= zzb2.zzq().zzc()))) {
            zzb.zzc(3);
            return new zzot(zzd(str), Collections.emptyMap(), zzls.GOOGLE_ANALYTICS, (com.google.android.gms.internal.measurement.zzis) zzb.zzbc());
        }
        String zzc = zzu2.zzc();
        zzb.zzb(2);
        com.google.android.gms.internal.measurement.zzgl zzb3 = zzpgVar.zzh().zzb(zzu2.zzc());
        if (zzb3 == null || !zzb3.zzp()) {
            this.zzu.zzaV().zzk().zzb("[sgtm] Missing sgtm_setting in remote config. appId", zzc);
            zzb.zzc(4);
        } else {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(zzu2.zzay())) {
                hashMap.put("x-gtm-server-preview", zzu2.zzay());
            }
            String zzd2 = zzb3.zzq().zzd();
            com.google.android.gms.internal.measurement.zzin zzb4 = com.google.android.gms.internal.measurement.zzin.zzb(zzu2.zzaL());
            if (zzb4 != null && zzb4 != com.google.android.gms.internal.measurement.zzin.CLIENT_UPLOAD_ELIGIBLE) {
                zzb.zza(zzb4);
            } else if (zzf(zzu2.zzc())) {
                zzb.zza(com.google.android.gms.internal.measurement.zzin.PINNED_TO_SERVICE_UPLOAD);
            } else if (TextUtils.isEmpty(zzd2)) {
                zzb.zza(com.google.android.gms.internal.measurement.zzin.MISSING_SGTM_SERVER_URL);
            } else {
                this.zzu.zzaV().zzk().zzb("[sgtm] Eligible for client side upload. appId", zzc);
                zzb.zzb(3);
                zzb.zza(com.google.android.gms.internal.measurement.zzin.CLIENT_UPLOAD_ELIGIBLE);
                zzotVar = new zzot(zzd2, hashMap, zzls.SGTM_CLIENT, (com.google.android.gms.internal.measurement.zzis) zzb.zzbc());
            }
            zzb3.zzq().zza();
            zzb3.zzq().zzb();
            zzic zzicVar = this.zzu;
            zzicVar.zzaU();
            if (TextUtils.isEmpty(zzd2)) {
                zzb.zzc(6);
                zzicVar.zzaV().zzk().zzb("[sgtm] Local service, missing sgtm_server_url", zzu2.zzc());
            } else {
                zzicVar.zzaV().zzk().zzb("[sgtm] Eligible for local service direct upload. appId", zzc);
                zzb.zzb(5);
                zzb.zzc(2);
                zzotVar = new zzot(zzd2, hashMap, zzls.SGTM, (com.google.android.gms.internal.measurement.zzis) zzb.zzbc());
            }
        }
        return zzotVar != null ? zzotVar : new zzot(zzd(str), Collections.emptyMap(), zzls.GOOGLE_ANALYTICS, (com.google.android.gms.internal.measurement.zzis) zzb.zzbc());
    }

    final boolean zzc(String str, com.google.android.gms.internal.measurement.zzin zzinVar) {
        com.google.android.gms.internal.measurement.zzgl zzb;
        zzg();
        return (zzinVar != com.google.android.gms.internal.measurement.zzin.CLIENT_UPLOAD_ELIGIBLE || zzf(str) || (zzb = this.zzg.zzh().zzb(str)) == null || !zzb.zzp() || zzb.zzq().zzd().isEmpty()) ? false : true;
    }
}
