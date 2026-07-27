package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationRequest> CREATOR = new zzbo();

    @Deprecated
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;

    @Deprecated
    public static final int PRIORITY_HIGH_ACCURACY = 100;

    @Deprecated
    public static final int PRIORITY_LOW_POWER = 104;

    @Deprecated
    public static final int PRIORITY_NO_POWER = 105;
    int zza;
    long zzb;
    long zzc;
    boolean zzd;
    long zze;
    int zzf;
    float zzg;
    long zzh;
    boolean zzi;

    @Deprecated
    public LocationRequest() {
        this(102, DateUtils.MILLIS_PER_HOUR, 600000L, false, Long.MAX_VALUE, Integer.MAX_VALUE, 0.0f, 0L, false);
    }

    public static LocationRequest create() {
        return new LocationRequest(102, DateUtils.MILLIS_PER_HOUR, 600000L, false, Long.MAX_VALUE, Integer.MAX_VALUE, 0.0f, 0L, true);
    }

    public boolean equals(Object object) {
        if (object instanceof LocationRequest) {
            LocationRequest locationRequest = (LocationRequest) object;
            if (this.zza == locationRequest.zza && this.zzb == locationRequest.zzb && this.zzc == locationRequest.zzc && this.zzd == locationRequest.zzd && this.zze == locationRequest.zze && this.zzf == locationRequest.zzf && this.zzg == locationRequest.zzg && getMaxWaitTime() == locationRequest.getMaxWaitTime() && this.zzi == locationRequest.zzi) {
                return true;
            }
        }
        return false;
    }

    public long getExpirationTime() {
        return this.zze;
    }

    public long getFastestInterval() {
        return this.zzc;
    }

    public long getInterval() {
        return this.zzb;
    }

    public long getMaxWaitTime() {
        long j = this.zzh;
        long j2 = this.zzb;
        return j < j2 ? j2 : j;
    }

    public int getNumUpdates() {
        return this.zzf;
    }

    public int getPriority() {
        return this.zza;
    }

    public float getSmallestDisplacement() {
        return this.zzg;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Long.valueOf(this.zzb), Float.valueOf(this.zzg), Long.valueOf(this.zzh));
    }

    public boolean isFastestIntervalExplicitlySet() {
        return this.zzd;
    }

    public boolean isWaitForAccurateLocation() {
        return this.zzi;
    }

    public LocationRequest setExpirationDuration(long millis) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = millis <= Long.MAX_VALUE - elapsedRealtime ? millis + elapsedRealtime : Long.MAX_VALUE;
        this.zze = j;
        if (j < 0) {
            this.zze = 0L;
        }
        return this;
    }

    @Deprecated
    public LocationRequest setExpirationTime(long j) {
        this.zze = j;
        if (j < 0) {
            this.zze = 0L;
        }
        return this;
    }

    public LocationRequest setFastestInterval(long fastestIntervalMillis) {
        Preconditions.checkArgument(fastestIntervalMillis >= 0, "illegal fastest interval: %d", Long.valueOf(fastestIntervalMillis));
        this.zzd = true;
        this.zzc = fastestIntervalMillis;
        return this;
    }

    public LocationRequest setInterval(long intervalMillis) {
        Preconditions.checkArgument(intervalMillis >= 0, "illegal interval: %d", Long.valueOf(intervalMillis));
        this.zzb = intervalMillis;
        if (!this.zzd) {
            this.zzc = (long) (intervalMillis / 6.0d);
        }
        return this;
    }

    public LocationRequest setMaxWaitTime(long maxWaitTimeMillis) {
        Preconditions.checkArgument(maxWaitTimeMillis >= 0, "illegal max wait time: %d", Long.valueOf(maxWaitTimeMillis));
        this.zzh = maxWaitTimeMillis;
        return this;
    }

    public LocationRequest setNumUpdates(int numUpdates) {
        if (numUpdates > 0) {
            this.zzf = numUpdates;
            return this;
        }
        StringBuilder sb = new StringBuilder(31);
        sb.append("invalid numUpdates: ");
        sb.append(numUpdates);
        throw new IllegalArgumentException(sb.toString());
    }

    public LocationRequest setPriority(int priority) {
        boolean z = true;
        if (priority != 100 && priority != 102 && priority != 104) {
            if (priority == 105) {
                priority = 105;
            } else {
                z = false;
            }
        }
        Preconditions.checkArgument(z, "illegal priority: %d", Integer.valueOf(priority));
        this.zza = priority;
        return this;
    }

    public LocationRequest setSmallestDisplacement(float smallestDisplacementMeters) {
        if (smallestDisplacementMeters >= 0.0f) {
            this.zzg = smallestDisplacementMeters;
            return this;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("invalid displacement: ");
        sb.append(smallestDisplacementMeters);
        throw new IllegalArgumentException(sb.toString());
    }

    public LocationRequest setWaitForAccurateLocation(boolean z) {
        this.zzi = z;
        return this;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        switch (this.zza) {
            case 100:
                str = "PRIORITY_HIGH_ACCURACY";
                break;
            case TypedValues.TYPE_TARGET /* 101 */:
            case 103:
            default:
                str = "???";
                break;
            case 102:
                str = "PRIORITY_BALANCED_POWER_ACCURACY";
                break;
            case 104:
                str = "PRIORITY_LOW_POWER";
                break;
            case 105:
                str = "PRIORITY_NO_POWER";
                break;
        }
        sb.append(str);
        if (this.zza != 105) {
            sb.append(" requested=");
            sb.append(this.zzb);
            sb.append("ms");
        }
        sb.append(" fastest=");
        sb.append(this.zzc);
        sb.append("ms");
        if (this.zzh > this.zzb) {
            sb.append(" maxWait=");
            sb.append(this.zzh);
            sb.append("ms");
        }
        if (this.zzg > 0.0f) {
            sb.append(" smallestDisplacement=");
            sb.append(this.zzg);
            sb.append("m");
        }
        long j = this.zze;
        if (j != Long.MAX_VALUE) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(j - elapsedRealtime);
            sb.append("ms");
        }
        if (this.zzf != Integer.MAX_VALUE) {
            sb.append(" num=");
            sb.append(this.zzf);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeLong(parcel, 3, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzd);
        SafeParcelWriter.writeLong(parcel, 5, this.zze);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzg);
        SafeParcelWriter.writeLong(parcel, 8, this.zzh);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzi);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    LocationRequest(int i, long j, long j2, boolean z, long j3, int i2, float f, long j4, boolean z2) {
        this.zza = i;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = z;
        this.zze = j3;
        this.zzf = i2;
        this.zzg = f;
        this.zzh = j4;
        this.zzi = z2;
    }
}
