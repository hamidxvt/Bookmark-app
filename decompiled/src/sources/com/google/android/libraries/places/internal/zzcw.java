package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzcw extends zzdf {
    zzcw(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, Locale locale, String str, boolean z, zzez zzezVar) {
        super(findAutocompletePredictionsRequest, locale, str, false, zzezVar);
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    protected final String zze() {
        return "autocomplete/json";
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    public final Map zzf() {
        HashMap hashMap = new HashMap();
        FindAutocompletePredictionsRequest findAutocompletePredictionsRequest = (FindAutocompletePredictionsRequest) zzb();
        TypeFilter typeFilter = findAutocompletePredictionsRequest.getTypeFilter();
        String query = findAutocompletePredictionsRequest.getQuery();
        zzg(hashMap, "input", query == null ? null : query.replaceFirst("^\\s+", "").replaceFirst("\\s+$", StringUtils.SPACE), null);
        zzg(hashMap, "types", typeFilter != null ? zzdz.zza(typeFilter) : null, null);
        zzg(hashMap, "sessiontoken", findAutocompletePredictionsRequest.getSessionToken(), null);
        zzg(hashMap, "origin", zzdx.zzd(findAutocompletePredictionsRequest.getOrigin()), null);
        zzg(hashMap, "locationbias", zzdx.zze(findAutocompletePredictionsRequest.getLocationBias()), null);
        zzg(hashMap, "locationrestriction", zzdx.zzf(findAutocompletePredictionsRequest.getLocationRestriction()), null);
        zzg(hashMap, "components", zzdx.zzb(findAutocompletePredictionsRequest.getCountries()), null);
        return hashMap;
    }
}
