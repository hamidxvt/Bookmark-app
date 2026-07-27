package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.firebase.messaging.Constants;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
final class zzhi implements Runnable {
    final /* synthetic */ com.google.android.gms.internal.measurement.zzbq zza;
    final /* synthetic */ ServiceConnection zzb;
    final /* synthetic */ zzhj zzc;

    zzhi(zzhj zzhjVar, com.google.android.gms.internal.measurement.zzbq zzbqVar, ServiceConnection serviceConnection) {
        this.zza = zzbqVar;
        this.zzb = serviceConnection;
        Objects.requireNonNull(zzhjVar);
        this.zzc = zzhjVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle;
        zzhj zzhjVar = this.zzc;
        zzhk zzhkVar = zzhjVar.zza;
        zzic zzicVar = zzhkVar.zza;
        zzicVar.zzaW().zzg();
        Bundle bundle2 = new Bundle();
        String zza = zzhjVar.zza();
        bundle2.putString("package_name", zza);
        try {
            bundle = this.zza.zze(bundle2);
            if (bundle == null) {
                zzicVar.zzaV().zzb().zza("Install Referrer Service returned a null response");
                bundle = null;
            }
        } catch (Exception e) {
            zzhkVar.zza.zzaV().zzb().zzb("Exception occurred while retrieving the Install Referrer", e.getMessage());
            bundle = null;
        }
        zzic zzicVar2 = zzhkVar.zza;
        zzicVar2.zzaW().zzg();
        zzic.zzL();
        if (bundle != null) {
            long j = bundle.getLong("install_begin_timestamp_seconds", 0L) * 1000;
            if (j == 0) {
                zzicVar2.zzaV().zze().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = bundle.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzicVar2.zzaV().zzb().zza("No referrer defined in Install Referrer response");
                } else {
                    zzicVar2.zzaV().zzk().zzb("InstallReferrer API result", string);
                    Bundle zzi = zzicVar2.zzk().zzi(Uri.parse("?".concat(string)));
                    if (zzi == null) {
                        zzicVar2.zzaV().zzb().zza("No campaign params defined in Install Referrer result");
                    } else {
                        List asList = Arrays.asList(((String) zzfy.zzbg.zzb(null)).split(","));
                        Iterator<String> it = zzi.keySet().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (asList.contains(it.next())) {
                                long j2 = bundle.getLong("referrer_click_timestamp_server_seconds", 0L) * 1000;
                                if (j2 > 0) {
                                    zzi.putLong("click_timestamp", j2);
                                }
                            }
                        }
                        if (j == zzicVar2.zzd().zzd.zza()) {
                            zzicVar2.zzaV().zzk().zza("Logging Install Referrer campaign from module while it may have already been logged.");
                        }
                        if (zzicVar2.zzB()) {
                            zzicVar2.zzd().zzd.zzb(j);
                            zzicVar2.zzaV().zzk().zzb("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                            zzi.putString("_cis", "referrer API v2");
                            zzicVar2.zzj().zzI(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, zzi, zza);
                        }
                    }
                }
            }
        }
        ConnectionTracker.getInstance().unbindService(zzicVar2.zzaY(), this.zzb);
    }
}
