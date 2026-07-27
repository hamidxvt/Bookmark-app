package com.google.android.libraries.places.internal;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzbg {
    private final String zza;
    private final int zzb;
    private final zzbf zzc;
    private final boolean zzd;
    private final int zze;

    public zzbg(WifiInfo wifiInfo, ScanResult scanResult) {
        zzbf zzbfVar;
        String str = scanResult.BSSID;
        String str2 = scanResult.capabilities;
        int i = scanResult.level;
        int i2 = scanResult.frequency;
        if (TextUtils.isEmpty(str2)) {
            zzbfVar = zzbf.OTHER;
        } else {
            String upperCase = str2.toUpperCase();
            zzbfVar = (upperCase.equals("[ESS]") || upperCase.equals("[IBSS]")) ? zzbf.NONE : upperCase.matches(".*WPA[0-9]*-PSK.*") ? zzbf.PSK : upperCase.matches(".*WPA[0-9]*-EAP.*") ? zzbf.EAP : zzbf.OTHER;
        }
        boolean z = false;
        if (wifiInfo != null && !TextUtils.isEmpty(str) && str.equalsIgnoreCase(wifiInfo.getBSSID())) {
            z = true;
        }
        this.zza = str;
        this.zzb = i;
        this.zzc = zzbfVar;
        this.zzd = z;
        this.zze = i2;
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final zzbf zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zza;
    }

    public final boolean zze() {
        return this.zzd;
    }
}
