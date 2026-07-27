package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
abstract class zze extends AutocompletePrediction {
    private final String zza;
    private final Integer zzb;
    private final List zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final List zzg;
    private final List zzh;
    private final List zzi;

    zze(String str, Integer num, List list, String str2, String str3, String str4, List list2, List list3, List list4) {
        if (str == null) {
            throw new NullPointerException("Null placeId");
        }
        this.zza = str;
        this.zzb = num;
        if (list == null) {
            throw new NullPointerException("Null placeTypes");
        }
        this.zzc = list;
        if (str2 == null) {
            throw new NullPointerException("Null fullText");
        }
        this.zzd = str2;
        if (str3 == null) {
            throw new NullPointerException("Null primaryText");
        }
        this.zze = str3;
        if (str4 == null) {
            throw new NullPointerException("Null secondaryText");
        }
        this.zzf = str4;
        this.zzg = list2;
        this.zzh = list3;
        this.zzi = list4;
    }

    public final boolean equals(Object obj) {
        Integer num;
        List list;
        List list2;
        List list3;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AutocompletePrediction)) {
            return false;
        }
        AutocompletePrediction autocompletePrediction = (AutocompletePrediction) obj;
        return this.zza.equals(autocompletePrediction.getPlaceId()) && ((num = this.zzb) != null ? num.equals(autocompletePrediction.getDistanceMeters()) : autocompletePrediction.getDistanceMeters() == null) && this.zzc.equals(autocompletePrediction.getPlaceTypes()) && this.zzd.equals(autocompletePrediction.zza()) && this.zze.equals(autocompletePrediction.zzb()) && this.zzf.equals(autocompletePrediction.zzc()) && ((list = this.zzg) != null ? list.equals(autocompletePrediction.zzd()) : autocompletePrediction.zzd() == null) && ((list2 = this.zzh) != null ? list2.equals(autocompletePrediction.zze()) : autocompletePrediction.zze() == null) && ((list3 = this.zzi) != null ? list3.equals(autocompletePrediction.zzf()) : autocompletePrediction.zzf() == null);
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    public final Integer getDistanceMeters() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    public final String getPlaceId() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    public final List<Place.Type> getPlaceTypes() {
        return this.zzc;
    }

    public final int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        Integer num = this.zzb;
        int hashCode2 = (((((((((hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003;
        List list = this.zzg;
        int hashCode3 = (hashCode2 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        List list2 = this.zzh;
        int hashCode4 = (hashCode3 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        List list3 = this.zzi;
        return hashCode4 ^ (list3 != null ? list3.hashCode() : 0);
    }

    public final String toString() {
        String str = this.zza;
        String valueOf = String.valueOf(this.zzb);
        String obj = this.zzc.toString();
        String str2 = this.zzd;
        String str3 = this.zze;
        String str4 = this.zzf;
        String valueOf2 = String.valueOf(this.zzg);
        String valueOf3 = String.valueOf(this.zzh);
        String valueOf4 = String.valueOf(this.zzi);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        int length3 = String.valueOf(valueOf3).length();
        StringBuilder sb = new StringBuilder(str.length() + 195 + length + obj.length() + str2.length() + str3.length() + str4.length() + length2 + length3 + String.valueOf(valueOf4).length());
        sb.append("AutocompletePrediction{placeId=");
        sb.append(str);
        sb.append(", distanceMeters=");
        sb.append(valueOf);
        sb.append(", placeTypes=");
        sb.append(obj);
        sb.append(", fullText=");
        sb.append(str2);
        sb.append(", primaryText=");
        sb.append(str3);
        sb.append(", secondaryText=");
        sb.append(str4);
        sb.append(", fullTextMatchedSubstrings=");
        sb.append(valueOf2);
        sb.append(", primaryTextMatchedSubstrings=");
        sb.append(valueOf3);
        sb.append(", secondaryTextMatchedSubstrings=");
        sb.append(valueOf4);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    final String zza() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    final String zzb() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    final String zzc() {
        return this.zzf;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    final List zzd() {
        return this.zzg;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    final List zze() {
        return this.zzh;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    final List zzf() {
        return this.zzi;
    }
}
