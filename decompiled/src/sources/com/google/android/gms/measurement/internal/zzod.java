package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzod {
    private final Map zza = new HashMap();

    zzod(Map map) {
        this.zza.putAll(map);
    }

    private final Bundle zzf() {
        int zzg;
        Map map = this.zza;
        if ("1".equals(map.get("GoogleConsent")) && (zzg = zzg()) >= 0) {
            String str = (String) map.get("PurposeConsents");
            if (!TextUtils.isEmpty(str)) {
                Bundle bundle = new Bundle();
                if (str.length() > 0) {
                    bundle.putString(zzjk.AD_STORAGE.zze, str.charAt(0) == '1' ? "granted" : "denied");
                }
                if (str.length() > 3) {
                    bundle.putString(zzjk.AD_PERSONALIZATION.zze, (str.charAt(2) == '1' && str.charAt(3) == '1') ? "granted" : "denied");
                }
                if (str.length() > 6 && zzg >= 4) {
                    bundle.putString(zzjk.AD_USER_DATA.zze, (str.charAt(0) == '1' && str.charAt(6) == '1') ? "granted" : "denied");
                }
                return bundle;
            }
        }
        return Bundle.EMPTY;
    }

    private final int zzg() {
        try {
            String str = (String) this.zza.get("PolicyVersion");
            if (TextUtils.isEmpty(str)) {
                return -1;
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzod) {
            return zza().equalsIgnoreCase(((zzod) obj).zza());
        }
        return false;
    }

    public final int hashCode() {
        return zza().hashCode();
    }

    public final String toString() {
        return zza();
    }

    /* JADX WARN: Multi-variable type inference failed */
    final String zza() {
        StringBuilder sb = new StringBuilder();
        ImmutableList immutableList = zzof.zza;
        int size = immutableList.size();
        for (int i = 0; i < size; i++) {
            String str = (String) immutableList.get(i);
            Map map = this.zza;
            if (map.containsKey(str)) {
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(str);
                sb.append("=");
                sb.append((String) map.get(str));
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0063, code lost:
    
        if (r0.get("Version") != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0069, code lost:
    
        return zzf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x006e, code lost:
    
        if (zzg() >= 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0072, code lost:
    
        r1 = new android.os.Bundle();
        r2 = com.google.android.gms.measurement.internal.zzjk.AD_STORAGE.zze;
        r6 = "denied";
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x008a, code lost:
    
        if (true == java.util.Objects.equals(r0.get("AuthorizePurpose1"), "1")) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x008c, code lost:
    
        r4 = "denied";
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x008f, code lost:
    
        r1.putString(r2, r4);
        r2 = com.google.android.gms.measurement.internal.zzjk.AD_PERSONALIZATION.zze;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a0, code lost:
    
        if (java.util.Objects.equals(r0.get("AuthorizePurpose3"), "1") == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00ac, code lost:
    
        if (java.util.Objects.equals(r0.get("AuthorizePurpose4"), "1") == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00ae, code lost:
    
        r4 = "granted";
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00b1, code lost:
    
        r1.putString(r2, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00b9, code lost:
    
        if (zzg() < 4) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00bb, code lost:
    
        r2 = com.google.android.gms.measurement.internal.zzjk.AD_USER_DATA.zze;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c7, code lost:
    
        if (java.util.Objects.equals(r0.get("AuthorizePurpose1"), "1") == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00d3, code lost:
    
        if (java.util.Objects.equals(r0.get("AuthorizePurpose7"), "1") == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d5, code lost:
    
        r6 = "granted";
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d8, code lost:
    
        r1.putString(r2, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00db, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b0, code lost:
    
        r4 = "denied";
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008e, code lost:
    
        r4 = "granted";
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00e0, code lost:
    
        return zzf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x004c, code lost:
    
        if ("1".equals(r2.get("EnableAdvertiserConsentMode")) != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0029, code lost:
    
        if ("1".equals(r2.get("EnableAdvertiserConsentMode")) != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0059, code lost:
    
        if (((java.lang.Boolean) r0.zzb(null)).booleanValue() == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x005b, code lost:
    
        r0 = r9.zza;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bundle zzb() {
        zzfx zzfxVar = zzfy.zzaZ;
        if (((Boolean) zzfxVar.zzb(null)).booleanValue()) {
            Map map = this.zza;
            if ("1".equals(map.get("gdprApplies"))) {
            }
            return Bundle.EMPTY;
        }
        Map map2 = this.zza;
        if ("1".equals(map2.get("GoogleConsent"))) {
            if ("1".equals(map2.get("gdprApplies"))) {
            }
        }
        return Bundle.EMPTY;
    }

    public final String zzc() {
        String str = (String) this.zza.get("PurposeDiagnostics");
        return TextUtils.isEmpty(str) ? "200000" : str;
    }

    public final String zzd(zzod zzodVar) {
        Map map = zzodVar.zza;
        String str = (map.isEmpty() || ((String) map.get("Version")) != null) ? "0" : "1";
        Bundle zzb = zzb();
        Bundle zzb2 = zzodVar.zzb();
        return str.concat(true != (zzb.size() != zzb2.size() ? true : !Objects.equals(zzb.getString("ad_storage"), zzb2.getString("ad_storage")) ? true : !Objects.equals(zzb.getString("ad_personalization"), zzb2.getString("ad_personalization")) ? true : !Objects.equals(zzb.getString("ad_user_data"), zzb2.getString("ad_user_data"))) ? "0" : "1");
    }

    public final String zze() {
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        int i = -1;
        try {
            String str = (String) this.zza.get("CmpSdkID");
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (NumberFormatException e) {
        }
        if (i < 0 || i > 4095) {
            sb.append("00");
        } else {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i >> 6));
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i & 63));
        }
        int zzg = zzg();
        if (zzg < 0 || zzg > 63) {
            sb.append("0");
        } else {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(zzg));
        }
        Preconditions.checkArgument(true);
        Map map = this.zza;
        int i2 = (true != "1".equals(map.get("gdprApplies")) ? 0 : 2) | 4;
        if ("1".equals(map.get("EnableAdvertiserConsentMode"))) {
            i2 |= 8;
        }
        sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i2));
        return sb.toString();
    }
}
