package com.google.android.libraries.places.internal;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzbm {
    static ApiException zza(VolleyError volleyError) {
        return new ApiException(new Status(volleyError instanceof NetworkError ? 7 : volleyError instanceof TimeoutError ? 15 : !(volleyError instanceof ServerError) ? volleyError instanceof ParseError ? 8 : volleyError instanceof AuthFailureError ? PlacesStatusCodes.REQUEST_DENIED : 13 : 8, String.format("Unexpected server error (HTTP Code: %s. Message: %s.)", volleyError.networkResponse == null ? "N/A" : String.valueOf(volleyError.networkResponse.statusCode), volleyError)));
    }
}
