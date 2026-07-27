package com.google.android.libraries.places.api.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.Place;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzaq implements Parcelable.Creator {
    zzaq() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzar(parcel.readInt() == 0 ? parcel.readString() : null, (AddressComponents) parcel.readParcelable(Place.class.getClassLoader()), (Place.BusinessStatus) parcel.readParcelable(Place.class.getClassLoader()), parcel.readArrayList(Place.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, (LatLng) parcel.readParcelable(Place.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, (OpeningHours) parcel.readParcelable(Place.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readArrayList(Place.class.getClassLoader()), (PlusCode) parcel.readParcelable(Place.class.getClassLoader()), parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readInt() == 0 ? Double.valueOf(parcel.readDouble()) : null, parcel.readArrayList(Place.class.getClassLoader()), parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, (LatLngBounds) parcel.readParcelable(Place.class.getClassLoader()), (Uri) parcel.readParcelable(Place.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzar[i];
    }
}
