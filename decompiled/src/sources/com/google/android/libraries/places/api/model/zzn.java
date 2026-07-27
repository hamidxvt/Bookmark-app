package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
abstract class zzn extends Period {
    private final TimeOfWeek zza;
    private final TimeOfWeek zzb;

    zzn(TimeOfWeek timeOfWeek, TimeOfWeek timeOfWeek2) {
        this.zza = timeOfWeek;
        this.zzb = timeOfWeek2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Period)) {
            return false;
        }
        Period period = (Period) obj;
        TimeOfWeek timeOfWeek = this.zza;
        if (timeOfWeek != null ? timeOfWeek.equals(period.getOpen()) : period.getOpen() == null) {
            TimeOfWeek timeOfWeek2 = this.zzb;
            if (timeOfWeek2 != null ? timeOfWeek2.equals(period.getClose()) : period.getClose() == null) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.Period
    public final TimeOfWeek getClose() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.Period
    public final TimeOfWeek getOpen() {
        return this.zza;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(valueOf2).length());
        sb.append("Period{open=");
        sb.append(valueOf);
        sb.append(", close=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    public final int hashCode() {
        TimeOfWeek timeOfWeek = this.zza;
        int hashCode = ((timeOfWeek == null ? 0 : timeOfWeek.hashCode()) ^ 1000003) * 1000003;
        TimeOfWeek timeOfWeek2 = this.zzb;
        return hashCode ^ (timeOfWeek2 != null ? timeOfWeek2.hashCode() : 0);
    }
}
