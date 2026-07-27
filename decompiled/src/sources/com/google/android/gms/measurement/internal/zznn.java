package com.google.android.gms.measurement.internal;

import android.content.pm.PackageManager;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zznn extends zzos {
    public final zzhe zza;
    public final zzhe zzb;
    public final zzhe zzc;
    public final zzhe zzd;
    public final zzhe zze;
    public final zzhe zzf;
    private final Map zzh;

    zznn(zzpg zzpgVar) {
        super(zzpgVar);
        this.zzh = new HashMap();
        zzhh zzd = this.zzu.zzd();
        Objects.requireNonNull(zzd);
        this.zza = new zzhe(zzd, "last_delete_stale", 0L);
        zzhh zzd2 = this.zzu.zzd();
        Objects.requireNonNull(zzd2);
        this.zzb = new zzhe(zzd2, "last_delete_stale_batch", 0L);
        zzhh zzd3 = this.zzu.zzd();
        Objects.requireNonNull(zzd3);
        this.zzc = new zzhe(zzd3, "backoff", 0L);
        zzhh zzd4 = this.zzu.zzd();
        Objects.requireNonNull(zzd4);
        this.zzd = new zzhe(zzd4, "last_upload", 0L);
        zzhh zzd5 = this.zzu.zzd();
        Objects.requireNonNull(zzd5);
        this.zze = new zzhe(zzd5, "last_upload_attempt", 0L);
        zzhh zzd6 = this.zzu.zzd();
        Objects.requireNonNull(zzd6);
        this.zzf = new zzhe(zzd6, "midnight_offset", 0L);
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    protected final boolean zzbb() {
        return false;
    }

    final Pair zzc(String str, zzjl zzjlVar) {
        return zzjlVar.zzo(zzjk.AD_STORAGE) ? zzd(str) : new Pair("", false);
    }

    @Deprecated
    final Pair zzd(String str) {
        zznm zznmVar;
        AdvertisingIdClient.Info info;
        zzg();
        zzic zzicVar = this.zzu;
        long elapsedRealtime = zzicVar.zzaZ().elapsedRealtime();
        zznm zznmVar2 = (zznm) this.zzh.get(str);
        if (zznmVar2 != null && elapsedRealtime < zznmVar2.zzc) {
            return new Pair(zznmVar2.zza, Boolean.valueOf(zznmVar2.zzb));
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        long zzl = zzicVar.zzc().zzl(str, zzfy.zza) + elapsedRealtime;
        try {
            try {
                info = AdvertisingIdClient.getAdvertisingIdInfo(zzicVar.zzaY());
            } catch (PackageManager.NameNotFoundException e) {
                info = null;
                if (zznmVar2 != null && elapsedRealtime < zznmVar2.zzc + this.zzu.zzc().zzl(str, zzfy.zzb)) {
                    return new Pair(zznmVar2.zza, Boolean.valueOf(zznmVar2.zzb));
                }
            }
        } catch (Exception e2) {
            this.zzu.zzaV().zzj().zzb("Unable to get advertising id", e2);
            zznmVar = new zznm("", false, zzl);
        }
        if (info == null) {
            return new Pair("00000000-0000-0000-0000-000000000000", false);
        }
        String id = info.getId();
        zznmVar = id != null ? new zznm(id, info.isLimitAdTrackingEnabled(), zzl) : new zznm("", info.isLimitAdTrackingEnabled(), zzl);
        this.zzh.put(str, zznmVar);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(zznmVar.zza, Boolean.valueOf(zznmVar.zzb));
    }

    @Deprecated
    final String zzf(String str, boolean z) {
        zzg();
        String str2 = z ? (String) zzd(str).first : "00000000-0000-0000-0000-000000000000";
        MessageDigest zzO = zzpp.zzO();
        if (zzO == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, zzO.digest(str2.getBytes())));
    }
}
