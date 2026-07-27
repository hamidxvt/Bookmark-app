package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationCheckRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/LocationCheckRequest;", "", "visitId", "", "latitude", "longitude", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getVisitId", "()Ljava/lang/String;", "getLatitude", "getLongitude", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class LocationCheckRequest {

    @SerializedName("latitude")
    private final String latitude;

    @SerializedName("longitude")
    private final String longitude;

    @SerializedName("visitId")
    private final String visitId;

    public LocationCheckRequest() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ LocationCheckRequest copy$default(LocationCheckRequest locationCheckRequest, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = locationCheckRequest.visitId;
        }
        if ((i & 2) != 0) {
            str2 = locationCheckRequest.latitude;
        }
        if ((i & 4) != 0) {
            str3 = locationCheckRequest.longitude;
        }
        return locationCheckRequest.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getVisitId() {
        return this.visitId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLatitude() {
        return this.latitude;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLongitude() {
        return this.longitude;
    }

    public final LocationCheckRequest copy(String visitId, String latitude, String longitude) {
        return new LocationCheckRequest(visitId, latitude, longitude);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocationCheckRequest)) {
            return false;
        }
        LocationCheckRequest locationCheckRequest = (LocationCheckRequest) other;
        return Intrinsics.areEqual(this.visitId, locationCheckRequest.visitId) && Intrinsics.areEqual(this.latitude, locationCheckRequest.latitude) && Intrinsics.areEqual(this.longitude, locationCheckRequest.longitude);
    }

    public int hashCode() {
        return ((((this.visitId == null ? 0 : this.visitId.hashCode()) * 31) + (this.latitude == null ? 0 : this.latitude.hashCode())) * 31) + (this.longitude != null ? this.longitude.hashCode() : 0);
    }

    public String toString() {
        return "LocationCheckRequest(visitId=" + this.visitId + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ")";
    }

    public LocationCheckRequest(String visitId, String latitude, String longitude) {
        this.visitId = visitId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public /* synthetic */ LocationCheckRequest(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getVisitId() {
        return this.visitId;
    }

    public final String getLatitude() {
        return this.latitude;
    }

    public final String getLongitude() {
        return this.longitude;
    }
}
