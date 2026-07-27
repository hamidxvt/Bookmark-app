package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
abstract class zzx extends TimeOfWeek {
    private final DayOfWeek zza;
    private final LocalTime zzb;

    zzx(DayOfWeek dayOfWeek, LocalTime localTime) {
        if (dayOfWeek == null) {
            throw new NullPointerException("Null day");
        }
        this.zza = dayOfWeek;
        if (localTime == null) {
            throw new NullPointerException("Null time");
        }
        this.zzb = localTime;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TimeOfWeek) {
            TimeOfWeek timeOfWeek = (TimeOfWeek) obj;
            if (this.zza.equals(timeOfWeek.getDay()) && this.zzb.equals(timeOfWeek.getTime())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.TimeOfWeek
    public final DayOfWeek getDay() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.TimeOfWeek
    public final LocalTime getTime() {
        return this.zzb;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        String obj2 = this.zzb.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 23 + obj2.length());
        sb.append("TimeOfWeek{day=");
        sb.append(obj);
        sb.append(", time=");
        sb.append(obj2);
        sb.append("}");
        return sb.toString();
    }
}
