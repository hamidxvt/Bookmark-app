package com.google.android.gms.measurement.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzgz extends zzos {
    public zzgz(zzpg zzpgVar) {
        super(zzpgVar);
    }

    public final boolean zzb() {
        zzaw();
        ConnectivityManager connectivityManager = (ConnectivityManager) this.zzu.zzaY().getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            try {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException e) {
            }
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    protected final boolean zzbb() {
        return false;
    }

    public final void zzc(String str, zzot zzotVar, com.google.android.gms.internal.measurement.zzib zzibVar, zzgw zzgwVar) {
        zzg();
        zzaw();
        try {
            URL url = new URI(zzotVar.zza()).toURL();
            this.zzg.zzp();
            this.zzu.zzaW().zzm(new zzgy(this, str, url, zzibVar.zzcc(), zzotVar.zzb(), zzgwVar));
        } catch (IllegalArgumentException | MalformedURLException | URISyntaxException e) {
            this.zzu.zzaV().zzb().zzc("Failed to parse URL. Not uploading MeasurementBatch. appId", zzgu.zzl(str), zzotVar.zza());
        }
    }

    public final void zzd(zzh zzhVar, Map map, zzgw zzgwVar) {
        zzg();
        zzaw();
        Preconditions.checkNotNull(zzhVar);
        Preconditions.checkNotNull(zzgwVar);
        zzou zzf = this.zzg.zzf();
        Uri.Builder builder = new Uri.Builder();
        String zzf2 = zzhVar.zzf();
        Uri.Builder encodedAuthority = builder.scheme((String) zzfy.zze.zzb(null)).encodedAuthority((String) zzfy.zzf.zzb(null));
        String.valueOf(zzf2);
        Uri.Builder appendQueryParameter = encodedAuthority.path("config/app/".concat(String.valueOf(zzf2))).appendQueryParameter("platform", "android");
        zzf.zzu.zzc().zzi();
        appendQueryParameter.appendQueryParameter("gmp_version", String.valueOf(133005L)).appendQueryParameter("runtime_version", "0");
        String uri = builder.build().toString();
        try {
            this.zzu.zzaW().zzm(new zzgy(this, zzhVar.zzc(), new URI(uri).toURL(), null, map, zzgwVar));
        } catch (IllegalArgumentException | MalformedURLException | URISyntaxException e) {
            this.zzu.zzaV().zzb().zzc("Failed to parse config URL. Not fetching. appId", zzgu.zzl(zzhVar.zzc()), uri);
        }
    }
}
