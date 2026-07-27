package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzda extends zzdf {
    private final Location zza;
    private final zzhs zzb;

    zzda(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzhs zzhsVar, Locale locale, String str, boolean z, zzez zzezVar) {
        super(findCurrentPlaceRequest, locale, str, false, zzezVar);
        this.zza = location;
        this.zzb = zzhsVar;
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    protected final String zze() {
        return "findplacefromuserlocation/json";
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    public final Map zzf() {
        FindCurrentPlaceRequest findCurrentPlaceRequest = (FindCurrentPlaceRequest) zzb();
        HashMap hashMap = new HashMap();
        zzg(hashMap, FirebaseAnalytics.Param.LOCATION, zzdx.zzc(this.zza), null);
        zzg(hashMap, "wifiaccesspoints", zzdx.zzg(this.zzb, 4000), null);
        zzg(hashMap, "precision", zzdx.zza(this.zza), null);
        zzg(hashMap, "timestamp", Long.valueOf(this.zza.getTime()), null);
        zzg(hashMap, "fields", zzdy.zza(findCurrentPlaceRequest.getPlaceFields()), null);
        return hashMap;
    }
}
