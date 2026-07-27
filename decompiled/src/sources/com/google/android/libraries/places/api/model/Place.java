package com.google.android.libraries.places.api.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.internal.zzha;
import com.google.android.libraries.places.internal.zzhf;
import com.google.android.libraries.places.internal.zzhs;
import com.google.android.libraries.places.internal.zzie;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class Place implements Parcelable {
    public static final int PRICE_LEVEL_MAX_VALUE = 4;
    public static final int PRICE_LEVEL_MIN_VALUE = 0;
    public static final double RATING_MAX_VALUE = 5.0d;
    public static final double RATING_MIN_VALUE = 1.0d;

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    public static abstract class Builder {
        public Place build() {
            Place zza = zza();
            List<String> attributions = zza.getAttributions();
            if (attributions != null) {
                Iterator<String> it = attributions.iterator();
                while (it.hasNext()) {
                    zzha.zzi(!TextUtils.isEmpty(it.next()), "Attributions must not contain null or empty values.");
                }
            }
            Integer priceLevel = zza.getPriceLevel();
            if (priceLevel != null) {
                zzha.zzk(zzie.zzc(0, 4).zze(priceLevel), "Price Level must not be out-of-range: %s to %s, but was: %s.", 0, 4, priceLevel);
            }
            Double rating = zza.getRating();
            if (rating != null) {
                Double valueOf = Double.valueOf(1.0d);
                Double valueOf2 = Double.valueOf(5.0d);
                zzha.zzk(zzie.zzc(valueOf, valueOf2).zze(rating), "Rating must not be out-of-range: %s to %s, but was: %s.", valueOf, valueOf2, rating);
            }
            Integer userRatingsTotal = zza.getUserRatingsTotal();
            if (userRatingsTotal != null && !zzie.zzb(0).zze(userRatingsTotal)) {
                throw new IllegalStateException(zzhf.zza("User Ratings Total must not be < 0, but was: %s.", userRatingsTotal));
            }
            if (attributions != null) {
                setAttributions(zzhs.zzk(attributions));
            }
            List<PhotoMetadata> photoMetadatas = zza.getPhotoMetadatas();
            if (photoMetadatas != null) {
                setPhotoMetadatas(zzhs.zzk(photoMetadatas));
            }
            List<Type> types = zza.getTypes();
            if (types != null) {
                setTypes(zzhs.zzk(types));
            }
            return zza();
        }

        public abstract String getAddress();

        public abstract AddressComponents getAddressComponents();

        public abstract List<String> getAttributions();

        public abstract BusinessStatus getBusinessStatus();

        public abstract Integer getIconBackgroundColor();

        public abstract String getIconUrl();

        public abstract String getId();

        public abstract LatLng getLatLng();

        public abstract String getName();

        public abstract OpeningHours getOpeningHours();

        public abstract String getPhoneNumber();

        public abstract List<PhotoMetadata> getPhotoMetadatas();

        public abstract PlusCode getPlusCode();

        public abstract Integer getPriceLevel();

        public abstract Double getRating();

        public abstract List<Type> getTypes();

        public abstract Integer getUserRatingsTotal();

        public abstract Integer getUtcOffsetMinutes();

        public abstract LatLngBounds getViewport();

        public abstract Uri getWebsiteUri();

        public abstract Builder setAddress(String str);

        public abstract Builder setAddressComponents(AddressComponents addressComponents);

        public abstract Builder setAttributions(List<String> list);

        public abstract Builder setBusinessStatus(BusinessStatus businessStatus);

        public abstract Builder setIconBackgroundColor(Integer num);

        public abstract Builder setIconUrl(String str);

        public abstract Builder setId(String str);

        public abstract Builder setLatLng(LatLng latLng);

        public abstract Builder setName(String str);

        public abstract Builder setOpeningHours(OpeningHours openingHours);

        public abstract Builder setPhoneNumber(String str);

        public abstract Builder setPhotoMetadatas(List<PhotoMetadata> list);

        public abstract Builder setPlusCode(PlusCode plusCode);

        public abstract Builder setPriceLevel(Integer num);

        public abstract Builder setRating(Double d);

        public abstract Builder setTypes(List<Type> list);

        public abstract Builder setUserRatingsTotal(Integer num);

        public abstract Builder setUtcOffsetMinutes(Integer num);

        public abstract Builder setViewport(LatLngBounds latLngBounds);

        public abstract Builder setWebsiteUri(Uri uri);

        abstract Place zza();
    }

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    public enum BusinessStatus implements Parcelable {
        OPERATIONAL,
        CLOSED_TEMPORARILY,
        CLOSED_PERMANENTLY;

        public static final Parcelable.Creator<BusinessStatus> CREATOR = new zzbf();

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int i) {
            dest.writeString(name());
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    public enum Field implements Parcelable {
        ADDRESS,
        ADDRESS_COMPONENTS,
        BUSINESS_STATUS,
        ID,
        LAT_LNG,
        NAME,
        OPENING_HOURS,
        PHONE_NUMBER,
        PHOTO_METADATAS,
        PLUS_CODE,
        PRICE_LEVEL,
        RATING,
        TYPES,
        USER_RATINGS_TOTAL,
        UTC_OFFSET,
        VIEWPORT,
        WEBSITE_URI,
        ICON_BACKGROUND_COLOR,
        ICON_URL;

        public static final Parcelable.Creator<Field> CREATOR = new zzbg();

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int i) {
            dest.writeString(name());
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    public enum Type implements Parcelable {
        OTHER,
        ACCOUNTING,
        ADMINISTRATIVE_AREA_LEVEL_1,
        ADMINISTRATIVE_AREA_LEVEL_2,
        ADMINISTRATIVE_AREA_LEVEL_3,
        ADMINISTRATIVE_AREA_LEVEL_4,
        ADMINISTRATIVE_AREA_LEVEL_5,
        AIRPORT,
        AMUSEMENT_PARK,
        AQUARIUM,
        ARCHIPELAGO,
        ART_GALLERY,
        ATM,
        BAKERY,
        BANK,
        BAR,
        BEAUTY_SALON,
        BICYCLE_STORE,
        BOOK_STORE,
        BOWLING_ALLEY,
        BUS_STATION,
        CAFE,
        CAMPGROUND,
        CAR_DEALER,
        CAR_RENTAL,
        CAR_REPAIR,
        CAR_WASH,
        CASINO,
        CEMETERY,
        CHURCH,
        CITY_HALL,
        CLOTHING_STORE,
        COLLOQUIAL_AREA,
        CONTINENT,
        CONVENIENCE_STORE,
        COUNTRY,
        COURTHOUSE,
        DENTIST,
        DEPARTMENT_STORE,
        DOCTOR,
        DRUGSTORE,
        ELECTRICIAN,
        ELECTRONICS_STORE,
        EMBASSY,
        ESTABLISHMENT,
        FINANCE,
        FIRE_STATION,
        FLOOR,
        FLORIST,
        FOOD,
        FUNERAL_HOME,
        FURNITURE_STORE,
        GAS_STATION,
        GENERAL_CONTRACTOR,
        GEOCODE,
        GROCERY_OR_SUPERMARKET,
        GYM,
        HAIR_CARE,
        HARDWARE_STORE,
        HEALTH,
        HINDU_TEMPLE,
        HOME_GOODS_STORE,
        HOSPITAL,
        INSURANCE_AGENCY,
        INTERSECTION,
        JEWELRY_STORE,
        LAUNDRY,
        LAWYER,
        LIBRARY,
        LIGHT_RAIL_STATION,
        LIQUOR_STORE,
        LOCAL_GOVERNMENT_OFFICE,
        LOCALITY,
        LOCKSMITH,
        LODGING,
        MEAL_DELIVERY,
        MEAL_TAKEAWAY,
        MOSQUE,
        MOVIE_RENTAL,
        MOVIE_THEATER,
        MOVING_COMPANY,
        MUSEUM,
        NATURAL_FEATURE,
        NEIGHBORHOOD,
        NIGHT_CLUB,
        PAINTER,
        PARK,
        PARKING,
        PET_STORE,
        PHARMACY,
        PHYSIOTHERAPIST,
        PLACE_OF_WORSHIP,
        PLUMBER,
        PLUS_CODE,
        POINT_OF_INTEREST,
        POLICE,
        POLITICAL,
        POST_BOX,
        POST_OFFICE,
        POSTAL_CODE_PREFIX,
        POSTAL_CODE_SUFFIX,
        POSTAL_CODE,
        POSTAL_TOWN,
        PREMISE,
        PRIMARY_SCHOOL,
        REAL_ESTATE_AGENCY,
        RESTAURANT,
        ROOFING_CONTRACTOR,
        ROOM,
        ROUTE,
        RV_PARK,
        SCHOOL,
        SECONDARY_SCHOOL,
        SHOE_STORE,
        SHOPPING_MALL,
        SPA,
        STADIUM,
        STORAGE,
        STORE,
        STREET_ADDRESS,
        STREET_NUMBER,
        SUBLOCALITY_LEVEL_1,
        SUBLOCALITY_LEVEL_2,
        SUBLOCALITY_LEVEL_3,
        SUBLOCALITY_LEVEL_4,
        SUBLOCALITY_LEVEL_5,
        SUBLOCALITY,
        SUBPREMISE,
        SUBWAY_STATION,
        SUPERMARKET,
        SYNAGOGUE,
        TAXI_STAND,
        TOURIST_ATTRACTION,
        TOWN_SQUARE,
        TRAIN_STATION,
        TRANSIT_STATION,
        TRAVEL_AGENCY,
        UNIVERSITY,
        VETERINARY_CARE,
        ZOO;

        public static final Parcelable.Creator<Type> CREATOR = new zzbh();

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int i) {
            dest.writeString(name());
        }
    }

    public static Builder builder() {
        return new zzq();
    }

    public abstract String getAddress();

    public abstract AddressComponents getAddressComponents();

    public abstract List<String> getAttributions();

    public abstract BusinessStatus getBusinessStatus();

    public abstract Integer getIconBackgroundColor();

    public abstract String getIconUrl();

    public abstract String getId();

    public abstract LatLng getLatLng();

    public abstract String getName();

    public abstract OpeningHours getOpeningHours();

    public abstract String getPhoneNumber();

    public abstract List<PhotoMetadata> getPhotoMetadatas();

    public abstract PlusCode getPlusCode();

    public abstract Integer getPriceLevel();

    public abstract Double getRating();

    public abstract List<Type> getTypes();

    public abstract Integer getUserRatingsTotal();

    public abstract Integer getUtcOffsetMinutes();

    public abstract LatLngBounds getViewport();

    public abstract Uri getWebsiteUri();

    public Boolean isOpen() {
        return isOpen(System.currentTimeMillis());
    }

    public Boolean isOpen(long utcTimeMillis) {
        return zzbe.zza(this, utcTimeMillis);
    }
}
