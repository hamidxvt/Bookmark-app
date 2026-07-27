package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzd extends AutocompletePrediction.Builder {
    private String zza;
    private Integer zzb;
    private List zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private List zzg;
    private List zzh;
    private List zzi;

    zzd() {
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final Integer getDistanceMeters() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final String getFullText() {
        String str = this.zzd;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Property \"fullText\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final List<Place.Type> getPlaceTypes() {
        List<Place.Type> list = this.zzc;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("Property \"placeTypes\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final String getPrimaryText() {
        String str = this.zze;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Property \"primaryText\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final String getSecondaryText() {
        String str = this.zzf;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Property \"secondaryText\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final AutocompletePrediction.Builder setDistanceMeters(Integer num) {
        this.zzb = num;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final AutocompletePrediction.Builder setFullText(String str) {
        if (str == null) {
            throw new NullPointerException("Null fullText");
        }
        this.zzd = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final AutocompletePrediction.Builder setPlaceTypes(List<Place.Type> list) {
        if (list == null) {
            throw new NullPointerException("Null placeTypes");
        }
        this.zzc = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final AutocompletePrediction.Builder setPrimaryText(String str) {
        if (str == null) {
            throw new NullPointerException("Null primaryText");
        }
        this.zze = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final AutocompletePrediction.Builder setSecondaryText(String str) {
        if (str == null) {
            throw new NullPointerException("Null secondaryText");
        }
        this.zzf = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final AutocompletePrediction.Builder zza(List list) {
        this.zzg = list;
        return this;
    }

    final AutocompletePrediction.Builder zzb(String str) {
        if (str == null) {
            throw new NullPointerException("Null placeId");
        }
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final AutocompletePrediction.Builder zzc(List list) {
        this.zzh = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    public final AutocompletePrediction.Builder zzd(List list) {
        this.zzi = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.Builder
    final AutocompletePrediction zze() {
        List list;
        String str;
        String str2;
        String str3;
        String str4 = this.zza;
        if (str4 != null && (list = this.zzc) != null && (str = this.zzd) != null && (str2 = this.zze) != null && (str3 = this.zzf) != null) {
            return new zzad(str4, this.zzb, list, str, str2, str3, this.zzg, this.zzh, this.zzi);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" placeId");
        }
        if (this.zzc == null) {
            sb.append(" placeTypes");
        }
        if (this.zzd == null) {
            sb.append(" fullText");
        }
        if (this.zze == null) {
            sb.append(" primaryText");
        }
        if (this.zzf == null) {
            sb.append(" secondaryText");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
