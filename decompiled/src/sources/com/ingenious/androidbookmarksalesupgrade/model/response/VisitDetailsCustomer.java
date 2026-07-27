package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VisitDetailsCustomer.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b8\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B£\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u00107\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010>\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010*J\u0010\u0010?\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010@\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jª\u0001\u0010C\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010DJ\u0013\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020\u0003HÖ\u0001J\t\u0010I\u001a\u00020\u0005HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR \u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001a\"\u0004\b$\u0010\u001cR \u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001a\"\u0004\b&\u0010\u001cR \u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001a\"\u0004\b(\u0010\u001cR\"\u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\"\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b.\u0010*\"\u0004\b/\u0010,R \u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001a\"\u0004\b1\u0010\u001cR \u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001a\"\u0004\b3\u0010\u001cR \u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001a\"\u0004\b5\u0010\u001c¨\u0006J"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsCustomer;", "", "visitId", "", "customerId", "", "customerName", "customerAddress", "customerType", "priority", "reason", "visitType", "currentLatitude", "", "currentLongitude", "providedLatitude", "providedLongitude", "distance", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getVisitId", "()Ljava/lang/Integer;", "setVisitId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCustomerId", "()Ljava/lang/String;", "setCustomerId", "(Ljava/lang/String;)V", "getCustomerName", "setCustomerName", "getCustomerAddress", "setCustomerAddress", "getCustomerType", "setCustomerType", "getPriority", "setPriority", "getReason", "setReason", "getVisitType", "setVisitType", "getCurrentLatitude", "()Ljava/lang/Double;", "setCurrentLatitude", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getCurrentLongitude", "setCurrentLongitude", "getProvidedLatitude", "setProvidedLatitude", "getProvidedLongitude", "setProvidedLongitude", "getDistance", "setDistance", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsCustomer;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class VisitDetailsCustomer {

    @SerializedName("currentLatitude")
    private Double currentLatitude;

    @SerializedName("currentLongitude")
    private Double currentLongitude;

    @SerializedName("customerAddress")
    private String customerAddress;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("customerName")
    private String customerName;

    @SerializedName("customerType")
    private String customerType;

    @SerializedName("distance")
    private String distance;

    @SerializedName("priority")
    private String priority;

    @SerializedName("providedLatitude")
    private String providedLatitude;

    @SerializedName("providedLongitude")
    private String providedLongitude;

    @SerializedName("reason")
    private String reason;

    @SerializedName("visitId")
    private Integer visitId;

    @SerializedName("visitType")
    private String visitType;

    public VisitDetailsCustomer() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getVisitId() {
        return this.visitId;
    }

    /* renamed from: component10, reason: from getter */
    public final Double getCurrentLongitude() {
        return this.currentLongitude;
    }

    /* renamed from: component11, reason: from getter */
    public final String getProvidedLatitude() {
        return this.providedLatitude;
    }

    /* renamed from: component12, reason: from getter */
    public final String getProvidedLongitude() {
        return this.providedLongitude;
    }

    /* renamed from: component13, reason: from getter */
    public final String getDistance() {
        return this.distance;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCustomerName() {
        return this.customerName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getCustomerAddress() {
        return this.customerAddress;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCustomerType() {
        return this.customerType;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPriority() {
        return this.priority;
    }

    /* renamed from: component7, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* renamed from: component8, reason: from getter */
    public final String getVisitType() {
        return this.visitType;
    }

    /* renamed from: component9, reason: from getter */
    public final Double getCurrentLatitude() {
        return this.currentLatitude;
    }

    public final VisitDetailsCustomer copy(Integer visitId, String customerId, String customerName, String customerAddress, String customerType, String priority, String reason, String visitType, Double currentLatitude, Double currentLongitude, String providedLatitude, String providedLongitude, String distance) {
        return new VisitDetailsCustomer(visitId, customerId, customerName, customerAddress, customerType, priority, reason, visitType, currentLatitude, currentLongitude, providedLatitude, providedLongitude, distance);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VisitDetailsCustomer)) {
            return false;
        }
        VisitDetailsCustomer visitDetailsCustomer = (VisitDetailsCustomer) other;
        return Intrinsics.areEqual(this.visitId, visitDetailsCustomer.visitId) && Intrinsics.areEqual(this.customerId, visitDetailsCustomer.customerId) && Intrinsics.areEqual(this.customerName, visitDetailsCustomer.customerName) && Intrinsics.areEqual(this.customerAddress, visitDetailsCustomer.customerAddress) && Intrinsics.areEqual(this.customerType, visitDetailsCustomer.customerType) && Intrinsics.areEqual(this.priority, visitDetailsCustomer.priority) && Intrinsics.areEqual(this.reason, visitDetailsCustomer.reason) && Intrinsics.areEqual(this.visitType, visitDetailsCustomer.visitType) && Intrinsics.areEqual((Object) this.currentLatitude, (Object) visitDetailsCustomer.currentLatitude) && Intrinsics.areEqual((Object) this.currentLongitude, (Object) visitDetailsCustomer.currentLongitude) && Intrinsics.areEqual(this.providedLatitude, visitDetailsCustomer.providedLatitude) && Intrinsics.areEqual(this.providedLongitude, visitDetailsCustomer.providedLongitude) && Intrinsics.areEqual(this.distance, visitDetailsCustomer.distance);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((this.visitId == null ? 0 : this.visitId.hashCode()) * 31) + (this.customerId == null ? 0 : this.customerId.hashCode())) * 31) + (this.customerName == null ? 0 : this.customerName.hashCode())) * 31) + (this.customerAddress == null ? 0 : this.customerAddress.hashCode())) * 31) + (this.customerType == null ? 0 : this.customerType.hashCode())) * 31) + (this.priority == null ? 0 : this.priority.hashCode())) * 31) + (this.reason == null ? 0 : this.reason.hashCode())) * 31) + (this.visitType == null ? 0 : this.visitType.hashCode())) * 31) + (this.currentLatitude == null ? 0 : this.currentLatitude.hashCode())) * 31) + (this.currentLongitude == null ? 0 : this.currentLongitude.hashCode())) * 31) + (this.providedLatitude == null ? 0 : this.providedLatitude.hashCode())) * 31) + (this.providedLongitude == null ? 0 : this.providedLongitude.hashCode())) * 31) + (this.distance != null ? this.distance.hashCode() : 0);
    }

    public String toString() {
        return "VisitDetailsCustomer(visitId=" + this.visitId + ", customerId=" + this.customerId + ", customerName=" + this.customerName + ", customerAddress=" + this.customerAddress + ", customerType=" + this.customerType + ", priority=" + this.priority + ", reason=" + this.reason + ", visitType=" + this.visitType + ", currentLatitude=" + this.currentLatitude + ", currentLongitude=" + this.currentLongitude + ", providedLatitude=" + this.providedLatitude + ", providedLongitude=" + this.providedLongitude + ", distance=" + this.distance + ")";
    }

    public VisitDetailsCustomer(Integer visitId, String customerId, String customerName, String customerAddress, String customerType, String priority, String reason, String visitType, Double currentLatitude, Double currentLongitude, String providedLatitude, String providedLongitude, String distance) {
        this.visitId = visitId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerType = customerType;
        this.priority = priority;
        this.reason = reason;
        this.visitType = visitType;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.providedLatitude = providedLatitude;
        this.providedLongitude = providedLongitude;
        this.distance = distance;
    }

    public /* synthetic */ VisitDetailsCustomer(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, Double d, Double d2, String str8, String str9, String str10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? null : str7, (i & 256) != 0 ? null : d, (i & 512) != 0 ? null : d2, (i & 1024) != 0 ? null : str8, (i & 2048) != 0 ? null : str9, (i & 4096) == 0 ? str10 : null);
    }

    public final Integer getVisitId() {
        return this.visitId;
    }

    public final void setVisitId(Integer num) {
        this.visitId = num;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(String str) {
        this.customerId = str;
    }

    public final String getCustomerName() {
        return this.customerName;
    }

    public final void setCustomerName(String str) {
        this.customerName = str;
    }

    public final String getCustomerAddress() {
        return this.customerAddress;
    }

    public final void setCustomerAddress(String str) {
        this.customerAddress = str;
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final void setCustomerType(String str) {
        this.customerType = str;
    }

    public final String getPriority() {
        return this.priority;
    }

    public final void setPriority(String str) {
        this.priority = str;
    }

    public final String getReason() {
        return this.reason;
    }

    public final void setReason(String str) {
        this.reason = str;
    }

    public final String getVisitType() {
        return this.visitType;
    }

    public final void setVisitType(String str) {
        this.visitType = str;
    }

    public final Double getCurrentLatitude() {
        return this.currentLatitude;
    }

    public final void setCurrentLatitude(Double d) {
        this.currentLatitude = d;
    }

    public final Double getCurrentLongitude() {
        return this.currentLongitude;
    }

    public final void setCurrentLongitude(Double d) {
        this.currentLongitude = d;
    }

    public final String getProvidedLatitude() {
        return this.providedLatitude;
    }

    public final void setProvidedLatitude(String str) {
        this.providedLatitude = str;
    }

    public final String getProvidedLongitude() {
        return this.providedLongitude;
    }

    public final void setProvidedLongitude(String str) {
        this.providedLongitude = str;
    }

    public final String getDistance() {
        return this.distance;
    }

    public final void setDistance(String str) {
        this.distance = str;
    }
}
