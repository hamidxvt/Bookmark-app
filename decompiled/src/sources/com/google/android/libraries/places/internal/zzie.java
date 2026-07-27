package com.google.android.libraries.places.internal;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzie extends zzif implements Serializable {
    private static final zzie zzc;
    final zzhn zza;
    final zzhn zzb;

    static {
        zzhl zzhlVar;
        zzhj zzhjVar;
        zzhlVar = zzhl.zzb;
        zzhjVar = zzhj.zzb;
        zzc = new zzie(zzhlVar, zzhjVar);
    }

    private zzie(zzhn zzhnVar, zzhn zzhnVar2) {
        zzhj zzhjVar;
        zzhl zzhlVar;
        this.zza = zzhnVar;
        this.zzb = zzhnVar2;
        if (zzhnVar.compareTo(zzhnVar2) <= 0) {
            zzhjVar = zzhj.zzb;
            if (zzhnVar != zzhjVar) {
                zzhlVar = zzhl.zzb;
                if (zzhnVar2 != zzhlVar) {
                    return;
                }
            }
        }
        String zzf = zzf(zzhnVar, zzhnVar2);
        throw new IllegalArgumentException(zzf.length() != 0 ? "Invalid range: ".concat(zzf) : new String("Invalid range: "));
    }

    static int zza(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static zzie zzb(Comparable comparable) {
        zzhj zzhjVar;
        zzhm zzhmVar = new zzhm(comparable);
        zzhjVar = zzhj.zzb;
        return new zzie(zzhmVar, zzhjVar);
    }

    public static zzie zzc(Comparable comparable, Comparable comparable2) {
        return new zzie(new zzhm(comparable), new zzhk(comparable2));
    }

    public static zzie zzd(Comparable comparable, Comparable comparable2) {
        return new zzie(new zzhm(comparable), new zzhm(comparable2));
    }

    private static String zzf(zzhn zzhnVar, zzhn zzhnVar2) {
        StringBuilder sb = new StringBuilder(16);
        zzhnVar.zzc(sb);
        sb.append("..");
        zzhnVar2.zzd(sb);
        return sb.toString();
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzie) {
            zzie zzieVar = (zzie) obj;
            if (this.zza.equals(zzieVar.zza) && this.zzb.equals(zzieVar.zzb)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.zza.hashCode() * 31) + this.zzb.hashCode();
    }

    public final String toString() {
        return zzf(this.zza, this.zzb);
    }

    public final boolean zze(Comparable comparable) {
        if (comparable != null) {
            return this.zza.zze(comparable) && !this.zzb.zze(comparable);
        }
        throw null;
    }
}
