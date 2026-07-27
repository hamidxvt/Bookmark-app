package com.google.android.libraries.places.api.model;

import android.util.Log;
import com.google.android.libraries.places.api.model.Period;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzhu;
import com.google.android.libraries.places.internal.zzhv;
import com.google.android.libraries.places.internal.zzie;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzbe {
    private static final zzhv zza;
    private static final LocalTime zzb;

    static {
        zzhu zzhuVar = new zzhu();
        zzhuVar.zza(1, DayOfWeek.SUNDAY);
        zzhuVar.zza(2, DayOfWeek.MONDAY);
        zzhuVar.zza(3, DayOfWeek.TUESDAY);
        zzhuVar.zza(4, DayOfWeek.WEDNESDAY);
        zzhuVar.zza(5, DayOfWeek.THURSDAY);
        zzhuVar.zza(6, DayOfWeek.FRIDAY);
        zzhuVar.zza(7, DayOfWeek.SATURDAY);
        zza = zzhuVar.zzb();
        zzb = LocalTime.newInstance(23, 59);
    }

    static Boolean zza(Place place, long j) {
        TimeZone timeZone;
        Place.BusinessStatus businessStatus = place.getBusinessStatus();
        OpeningHours openingHours = place.getOpeningHours();
        Integer utcOffsetMinutes = place.getUtcOffsetMinutes();
        if (businessStatus != null && businessStatus != Place.BusinessStatus.OPERATIONAL) {
            return false;
        }
        if (openingHours == null || utcOffsetMinutes == null) {
            return null;
        }
        List<Period> periods = openingHours.getPeriods();
        if (periods.isEmpty()) {
            return false;
        }
        if (periods.size() == 1) {
            Period period = periods.get(0);
            TimeOfWeek open = period.getOpen();
            if (period.getClose() == null && open != null && open.getDay() == DayOfWeek.SUNDAY && open.getTime().getHours() == 0 && open.getTime().getMinutes() == 0) {
                return true;
            }
        }
        for (Period period2 : periods) {
            if (period2.getOpen() == null || period2.getClose() == null) {
                return null;
            }
        }
        int intValue = utcOffsetMinutes.intValue();
        String[] availableIDs = TimeZone.getAvailableIDs((int) TimeUnit.MINUTES.toMillis(intValue));
        if (availableIDs == null || availableIDs.length <= 0) {
            Log.w("Places", String.format("Cannot find timezone that associates with utcOffsetMinutes %d from Place object.", Integer.valueOf(intValue)));
            timeZone = null;
        } else {
            timeZone = TimeZone.getTimeZone(availableIDs[0]);
        }
        if (timeZone == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(j);
        DayOfWeek dayOfWeek = (DayOfWeek) zza.get(Integer.valueOf(calendar.get(7)));
        LocalTime newInstance = LocalTime.newInstance(calendar.get(11), calendar.get(12));
        EnumMap enumMap = new EnumMap(DayOfWeek.class);
        if (!periods.isEmpty()) {
            Period period3 = periods.get(0);
            int i = 0;
            while (period3 != null) {
                TimeOfWeek open2 = period3.getOpen();
                TimeOfWeek close = period3.getClose();
                if (open2 == null || close == null) {
                    i++;
                    period3 = i >= periods.size() ? null : periods.get(i);
                } else {
                    DayOfWeek day = open2.getDay();
                    LocalTime time = open2.getTime();
                    if (open2.getDay() != close.getDay()) {
                        LocalTime localTime = zzb;
                        List list = (List) zzb(enumMap, day, new ArrayList());
                        list.add(zzie.zzc(time, localTime));
                        enumMap.put((EnumMap) day, (DayOfWeek) list);
                        TimeOfWeek newInstance2 = TimeOfWeek.newInstance(DayOfWeek.values()[(day.ordinal() + 1) % 7], LocalTime.newInstance(0, 0));
                        TimeOfWeek close2 = period3.getClose();
                        Period.Builder builder = Period.builder();
                        builder.setOpen(newInstance2);
                        builder.setClose(close2);
                        period3 = builder.build();
                    } else {
                        LocalTime time2 = close.getTime();
                        List list2 = (List) zzb(enumMap, day, new ArrayList());
                        list2.add(zzie.zzd(time, time2));
                        enumMap.put((EnumMap) day, (DayOfWeek) list2);
                        i++;
                        period3 = i >= periods.size() ? null : periods.get(i);
                    }
                }
            }
        }
        List list3 = (List) enumMap.get(dayOfWeek);
        if (list3 == null) {
            return false;
        }
        Iterator it = list3.iterator();
        while (it.hasNext()) {
            if (((zzie) it.next()).zze(newInstance)) {
                return true;
            }
        }
        return false;
    }

    private static Object zzb(Map map, Object obj, Object obj2) {
        return map.containsKey(obj) ? map.get(obj) : obj2;
    }
}
