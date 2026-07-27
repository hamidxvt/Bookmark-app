package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import com.google.android.libraries.places.internal.zzha;
import com.google.android.libraries.places.internal.zzie;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class LocalTime implements Parcelable, Comparable<LocalTime> {
    public static LocalTime newInstance(int hours, int minutes) {
        try {
            zzi zziVar = new zzi();
            zziVar.zza(hours);
            zziVar.zzb(minutes);
            LocalTime zzc = zziVar.zzc();
            int hours2 = zzc.getHours();
            zzha.zzj(zzie.zzc(0, 23).zze(Integer.valueOf(hours2)), "Hours must not be out-of-range: 0 to 23, but was: %s.", hours2);
            int minutes2 = zzc.getMinutes();
            zzha.zzj(zzie.zzc(0, 59).zze(Integer.valueOf(minutes2)), "Minutes must not be out-of-range: 0 to 59, but was: %s.", minutes2);
            return zzc;
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalTime compare) {
        zzha.zzc(compare, "compare must not be null.");
        if (this == compare) {
            return 0;
        }
        return getHours() == compare.getHours() ? getMinutes() - compare.getMinutes() : getHours() - compare.getHours();
    }

    public abstract int getHours();

    public abstract int getMinutes();
}
