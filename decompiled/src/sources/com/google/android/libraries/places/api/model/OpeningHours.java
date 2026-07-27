package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzha;
import com.google.android.libraries.places.internal.zzhs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class OpeningHours implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    public static abstract class Builder {
        public OpeningHours build() {
            OpeningHours zza = zza();
            Iterator<String> it = zza.getWeekdayText().iterator();
            while (it.hasNext()) {
                zzha.zzi(!TextUtils.isEmpty(it.next()), "WeekdayText must not contain null or empty values.");
            }
            setPeriods(zzhs.zzk(zza.getPeriods()));
            setWeekdayText(zzhs.zzk(zza.getWeekdayText()));
            return zza();
        }

        public abstract List<Period> getPeriods();

        public abstract List<String> getWeekdayText();

        public abstract Builder setPeriods(List<Period> list);

        public abstract Builder setWeekdayText(List<String> list);

        abstract OpeningHours zza();
    }

    public static Builder builder() {
        zzk zzkVar = new zzk();
        zzkVar.setPeriods(new ArrayList());
        zzkVar.setWeekdayText(new ArrayList());
        return zzkVar;
    }

    public abstract List<Period> getPeriods();

    public abstract List<String> getWeekdayText();
}
