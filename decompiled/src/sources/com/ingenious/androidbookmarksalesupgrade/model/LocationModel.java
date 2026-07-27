package com.ingenious.androidbookmarksalesupgrade.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationModel.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018¨\u0006\u001d"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/LocationModel;", "", "<init>", "()V", "locationAddress", "", "getLocationAddress", "()Ljava/lang/String;", "setLocationAddress", "(Ljava/lang/String;)V", "locationCityName", "getLocationCityName", "setLocationCityName", "locationAreaName", "getLocationAreaName", "setLocationAreaName", "locationCountryCode", "getLocationCountryCode", "setLocationCountryCode", "userLatitude", "", "getUserLatitude", "()Ljava/lang/Double;", "setUserLatitude", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "userLongitude", "getUserLongitude", "setUserLongitude", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final class LocationModel {
    private Double userLatitude;
    private Double userLongitude;
    private String locationAddress = "";
    private String locationCityName = "";
    private String locationAreaName = "";
    private String locationCountryCode = "";

    public final String getLocationAddress() {
        return this.locationAddress;
    }

    public final void setLocationAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.locationAddress = str;
    }

    public final String getLocationCityName() {
        return this.locationCityName;
    }

    public final void setLocationCityName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.locationCityName = str;
    }

    public final String getLocationAreaName() {
        return this.locationAreaName;
    }

    public final void setLocationAreaName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.locationAreaName = str;
    }

    public final String getLocationCountryCode() {
        return this.locationCountryCode;
    }

    public final void setLocationCountryCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.locationCountryCode = str;
    }

    public final Double getUserLatitude() {
        return this.userLatitude;
    }

    public final void setUserLatitude(Double d) {
        this.userLatitude = d;
    }

    public final Double getUserLongitude() {
        return this.userLongitude;
    }

    public final void setUserLongitude(Double d) {
        this.userLongitude = d;
    }
}
