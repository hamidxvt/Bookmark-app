package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzha;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class PhotoMetadata implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    public static abstract class Builder {
        public PhotoMetadata build() {
            PhotoMetadata zzb = zzb();
            int width = zzb.getWidth();
            zzha.zzj(width >= 0, "Width must not be < 0, but was: %s.", width);
            int height = zzb.getHeight();
            zzha.zzj(height >= 0, "Height must not be < 0, but was: %s.", height);
            zzha.zzi(!TextUtils.isEmpty(zzb.zza()), "PhotoReference must not be null or empty.");
            return zzb;
        }

        public abstract String getAttributions();

        public abstract int getHeight();

        public abstract int getWidth();

        public abstract Builder setAttributions(String str);

        public abstract Builder setHeight(int i);

        public abstract Builder setWidth(int i);

        abstract PhotoMetadata zzb();
    }

    public static Builder builder(String photoReference) {
        zzo zzoVar = new zzo();
        zzoVar.zza(photoReference);
        zzoVar.setWidth(0);
        zzoVar.setHeight(0);
        zzoVar.setAttributions("");
        return zzoVar;
    }

    public abstract String getAttributions();

    public abstract int getHeight();

    public abstract int getWidth();

    public abstract String zza();
}
