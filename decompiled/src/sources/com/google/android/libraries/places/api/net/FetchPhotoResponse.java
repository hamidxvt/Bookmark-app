package com.google.android.libraries.places.api.net;

import android.graphics.Bitmap;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class FetchPhotoResponse {
    public static FetchPhotoResponse newInstance(Bitmap bitmap) {
        return new zzd(bitmap);
    }

    public abstract Bitmap getBitmap();
}
