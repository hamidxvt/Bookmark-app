package com.google.android.libraries.places.api.model;

import android.net.Uri;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzq extends Place.Builder {
    private String zza;
    private AddressComponents zzb;
    private Place.BusinessStatus zzc;
    private List zzd;
    private String zze;
    private LatLng zzf;
    private String zzg;
    private OpeningHours zzh;
    private String zzi;
    private List zzj;
    private PlusCode zzk;
    private Integer zzl;
    private Double zzm;
    private List zzn;
    private Integer zzo;
    private Integer zzp;
    private LatLngBounds zzq;
    private Uri zzr;
    private String zzs;
    private Integer zzt;

    zzq() {
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getAddress() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final AddressComponents getAddressComponents() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final List<String> getAttributions() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.BusinessStatus getBusinessStatus() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Integer getIconBackgroundColor() {
        return this.zzt;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getIconUrl() {
        return this.zzs;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getId() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final LatLng getLatLng() {
        return this.zzf;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getName() {
        return this.zzg;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final OpeningHours getOpeningHours() {
        return this.zzh;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final String getPhoneNumber() {
        return this.zzi;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final List<PhotoMetadata> getPhotoMetadatas() {
        return this.zzj;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final PlusCode getPlusCode() {
        return this.zzk;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Integer getPriceLevel() {
        return this.zzl;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Double getRating() {
        return this.zzm;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final List<Place.Type> getTypes() {
        return this.zzn;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Integer getUserRatingsTotal() {
        return this.zzo;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Integer getUtcOffsetMinutes() {
        return this.zzp;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final LatLngBounds getViewport() {
        return this.zzq;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Uri getWebsiteUri() {
        return this.zzr;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setAddress(String str) {
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setAddressComponents(AddressComponents addressComponents) {
        this.zzb = addressComponents;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setAttributions(List<String> list) {
        this.zzd = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setBusinessStatus(Place.BusinessStatus businessStatus) {
        this.zzc = businessStatus;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setIconBackgroundColor(Integer num) {
        this.zzt = num;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setIconUrl(String str) {
        this.zzs = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setId(String str) {
        this.zze = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setLatLng(LatLng latLng) {
        this.zzf = latLng;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setName(String str) {
        this.zzg = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setOpeningHours(OpeningHours openingHours) {
        this.zzh = openingHours;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setPhoneNumber(String str) {
        this.zzi = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setPhotoMetadatas(List<PhotoMetadata> list) {
        this.zzj = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setPlusCode(PlusCode plusCode) {
        this.zzk = plusCode;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setPriceLevel(Integer num) {
        this.zzl = num;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setRating(Double d) {
        this.zzm = d;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setTypes(List<Place.Type> list) {
        this.zzn = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setUserRatingsTotal(Integer num) {
        this.zzo = num;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setUtcOffsetMinutes(Integer num) {
        this.zzp = num;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setViewport(LatLngBounds latLngBounds) {
        this.zzq = latLngBounds;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    public final Place.Builder setWebsiteUri(Uri uri) {
        this.zzr = uri;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.Place.Builder
    final Place zza() {
        return new zzar(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo, this.zzp, this.zzq, this.zzr, this.zzs, this.zzt);
    }
}
