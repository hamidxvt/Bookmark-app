package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.firebase.messaging.Constants;
import kotlinx.coroutines.DebugKt;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzx {
    private final zzic zza;

    public zzx(zzic zzicVar) {
        this.zza = zzicVar;
    }

    final void zza() {
        zzic zzicVar = this.zza;
        zzicVar.zzaW().zzg();
        if (zze()) {
            if (zzd()) {
                zzicVar.zzd().zzr.zzb(null);
                Bundle bundle = new Bundle();
                bundle.putString("source", "(not set)");
                bundle.putString("medium", "(not set)");
                bundle.putString("_cis", "intent");
                bundle.putLong("_cc", 1L);
                zzicVar.zzj().zzF(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_cmpx", bundle);
            } else {
                String zza = zzicVar.zzd().zzr.zza();
                if (TextUtils.isEmpty(zza)) {
                    zzicVar.zzaV().zzc().zza("Cache still valid but referrer not found");
                } else {
                    long zza2 = zzicVar.zzd().zzs.zza() / DateUtils.MILLIS_PER_HOUR;
                    Uri parse = Uri.parse(zza);
                    Bundle bundle2 = new Bundle();
                    Pair pair = new Pair(parse.getPath(), bundle2);
                    for (String str : parse.getQueryParameterNames()) {
                        bundle2.putString(str, parse.getQueryParameter(str));
                    }
                    ((Bundle) pair.second).putLong("_cc", (zza2 - 1) * DateUtils.MILLIS_PER_HOUR);
                    zzicVar.zzj().zzF(pair.first == null ? "app" : (String) pair.first, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, (Bundle) pair.second);
                }
                zzicVar.zzd().zzr.zzb(null);
            }
            zzicVar.zzd().zzs.zzb(0L);
        }
    }

    final void zzb(String str, Bundle bundle) {
        String uri;
        zzic zzicVar = this.zza;
        zzicVar.zzaW().zzg();
        if (zzicVar.zzB()) {
            return;
        }
        if (bundle.isEmpty()) {
            uri = null;
        } else {
            if (true == str.isEmpty()) {
                str = DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
            }
            Uri.Builder builder = new Uri.Builder();
            builder.path(str);
            for (String str2 : bundle.keySet()) {
                builder.appendQueryParameter(str2, bundle.getString(str2));
            }
            uri = builder.build().toString();
        }
        if (TextUtils.isEmpty(uri)) {
            return;
        }
        zzicVar.zzd().zzr.zzb(uri);
        zzicVar.zzd().zzs.zzb(zzicVar.zzaZ().currentTimeMillis());
    }

    final void zzc() {
        if (zze() && zzd()) {
            this.zza.zzd().zzr.zzb(null);
        }
    }

    final boolean zzd() {
        if (!zze()) {
            return false;
        }
        zzic zzicVar = this.zza;
        return zzicVar.zzaZ().currentTimeMillis() - zzicVar.zzd().zzs.zza() > zzicVar.zzc().zzl(null, zzfy.zzaj);
    }

    final boolean zze() {
        return this.zza.zzd().zzs.zza() > 0;
    }
}
