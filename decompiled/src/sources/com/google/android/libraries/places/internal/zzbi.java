package com.google.android.libraries.places.internal;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzbi {
    public static final /* synthetic */ int zza = 0;
    private static final long zzb = TimeUnit.MINUTES.toMicros(1);
    private final zzas zzc;
    private final Context zzd;

    zzbi(Context context, zzas zzasVar) {
        this.zzd = context;
        this.zzc = zzasVar;
    }

    public final zzhs zza(String str) {
        boolean z;
        WifiManager wifiManager = (WifiManager) this.zzd.getSystemService("wifi");
        if (wifiManager == null || !wifiManager.isWifiEnabled()) {
            return zzhs.zzm();
        }
        List<ScanResult> scanResults = wifiManager.getScanResults();
        if (scanResults == null || scanResults.isEmpty()) {
            return zzhs.zzm();
        }
        zzhs zzp = zzhs.zzp(zzid.zza(new Comparator() { // from class: com.google.android.libraries.places.internal.zzbh
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int i = zzbi.zza;
                return ((ScanResult) obj2).level - ((ScanResult) obj).level;
            }
        }), scanResults);
        ArrayList arrayList = new ArrayList();
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        int size = zzp.size();
        for (int i = 0; i < size; i++) {
            ScanResult scanResult = (ScanResult) zzp.get(i);
            if (scanResult != null && !TextUtils.isEmpty(scanResult.SSID)) {
                long zza2 = (this.zzc.zza() * 1000) - scanResult.timestamp;
                long j = zzb;
                String str2 = scanResult.SSID;
                if (str2 == null) {
                    throw new IllegalArgumentException("Null SSID.");
                }
                if (str2.indexOf(95) < 0) {
                    z = false;
                } else {
                    String lowerCase = str2.toLowerCase(Locale.ENGLISH);
                    z = true;
                    if (!lowerCase.contains("_nomap") && !lowerCase.contains("_optout")) {
                        z = false;
                    }
                }
                if (zza2 <= j && !z) {
                    arrayList.add(new zzbg(connectionInfo, scanResult));
                }
            }
        }
        return zzhs.zzk(arrayList);
    }
}
