package com.google.android.libraries.places.api.model;

import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class TimeOfWeek implements Parcelable {
    public static TimeOfWeek newInstance(DayOfWeek day, LocalTime localTime) {
        return new zzaz(day, localTime);
    }

    public abstract DayOfWeek getDay();

    public abstract LocalTime getTime();
}
