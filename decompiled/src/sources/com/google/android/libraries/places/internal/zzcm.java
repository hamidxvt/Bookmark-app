package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzcm extends zzdf {
    zzcm(FetchPhotoRequest fetchPhotoRequest, String str, boolean z, zzez zzezVar) {
        super(fetchPhotoRequest, null, str, false, zzezVar);
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    protected final String zze() {
        return "photo";
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    public final Map zzf() {
        FetchPhotoRequest fetchPhotoRequest = (FetchPhotoRequest) zzb();
        PhotoMetadata photoMetadata = fetchPhotoRequest.getPhotoMetadata();
        HashMap hashMap = new HashMap();
        zzg(hashMap, "maxheight", fetchPhotoRequest.getMaxHeight(), null);
        zzg(hashMap, "maxwidth", fetchPhotoRequest.getMaxWidth(), null);
        hashMap.put("photoreference", photoMetadata.zza());
        return hashMap;
    }
}
