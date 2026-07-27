package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzcs extends zzdf {
    zzcs(FetchPlaceRequest fetchPlaceRequest, Locale locale, String str, boolean z, zzez zzezVar) {
        super(fetchPlaceRequest, locale, str, false, zzezVar);
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    protected final String zze() {
        return "details/json";
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    public final Map zzf() {
        FetchPlaceRequest fetchPlaceRequest = (FetchPlaceRequest) zzb();
        HashMap hashMap = new HashMap();
        zzg(hashMap, "placeid", fetchPlaceRequest.getPlaceId(), null);
        zzg(hashMap, "sessiontoken", fetchPlaceRequest.getSessionToken(), null);
        zzg(hashMap, "fields", zzdy.zza(fetchPlaceRequest.getPlaceFields()), null);
        return hashMap;
    }
}
