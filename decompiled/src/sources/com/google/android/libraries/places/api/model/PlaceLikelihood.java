package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.libraries.places.internal.zzhf;
import com.google.android.libraries.places.internal.zzie;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class PlaceLikelihood implements Parcelable {
    public static final double LIKELIHOOD_MAX_VALUE = 1.0d;
    public static final double LIKELIHOOD_MIN_VALUE = 0.0d;

    public static PlaceLikelihood newInstance(Place place, double likelihood) {
        Double valueOf = Double.valueOf(Utils.DOUBLE_EPSILON);
        Double valueOf2 = Double.valueOf(1.0d);
        zzie zzc = zzie.zzc(valueOf, valueOf2);
        Double valueOf3 = Double.valueOf(likelihood);
        if (zzc.zze(valueOf3)) {
            return new zzat(place, likelihood);
        }
        throw new IllegalArgumentException(zzhf.zza("Likelihood must not be out-of-range: %s to %s, but was: %s.", valueOf, valueOf2, valueOf3));
    }

    public abstract double getLikelihood();

    public abstract Place getPlace();
}
