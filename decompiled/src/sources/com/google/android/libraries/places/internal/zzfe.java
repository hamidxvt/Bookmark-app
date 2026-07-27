package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzfe extends zzfc {
    public static final Parcelable.Creator<zzfe> CREATOR = new zzfd();

    zzfe(AutocompleteActivityMode autocompleteActivityMode, zzhs zzhsVar, zzfj zzfjVar, String str, String str2, LocationBias locationBias, LocationRestriction locationRestriction, zzhs zzhsVar2, TypeFilter typeFilter, int i, int i2) {
        super(autocompleteActivityMode, zzhsVar, zzfjVar, str, str2, locationBias, locationRestriction, zzhsVar2, typeFilter, i, i2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(zzh(), i);
        parcel.writeList(zzj());
        parcel.writeParcelable(zzf(), i);
        if (zzl() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(zzl());
        }
        if (zzk() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(zzk());
        }
        parcel.writeParcelable(zzc(), i);
        parcel.writeParcelable(zzd(), i);
        parcel.writeList(zzi());
        parcel.writeParcelable(zze(), i);
        parcel.writeInt(zza());
        parcel.writeInt(zzb());
    }
}
