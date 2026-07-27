package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PastVisitsList.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\bg\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B§\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010W\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010X\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\\\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010]\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010^\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010_\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010`\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010h\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0006HÆ\u0003J®\u0002\u0010o\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010pJ\u0013\u0010q\u001a\u00020r2\b\u0010s\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010t\u001a\u00020\u0003HÖ\u0001J\t\u0010u\u001a\u00020\u0006HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R \u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010'\"\u0004\b+\u0010)R \u0010\b\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010'\"\u0004\b-\u0010)R\"\u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\"\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b3\u0010/\"\u0004\b4\u00101R\"\u0010\f\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b5\u0010/\"\u0004\b6\u00101R\"\u0010\r\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b7\u0010/\"\u0004\b8\u00101R\"\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b9\u0010 \"\u0004\b:\u0010\"R \u0010\u000f\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010'\"\u0004\b<\u0010)R \u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010'\"\u0004\b>\u0010)R \u0010\u0011\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010'\"\u0004\b@\u0010)R \u0010\u0012\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010'\"\u0004\bB\u0010)R \u0010\u0013\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010'\"\u0004\bD\u0010)R \u0010\u0014\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010'\"\u0004\bF\u0010)R\"\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\bG\u0010 \"\u0004\bH\u0010\"R \u0010\u0016\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010'\"\u0004\bJ\u0010)R \u0010\u0017\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010'\"\u0004\bL\u0010)R \u0010\u0018\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010'\"\u0004\bN\u0010)R \u0010\u0019\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010'\"\u0004\bP\u0010)R \u0010\u001a\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010'\"\u0004\bR\u0010)R \u0010\u001b\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010'\"\u0004\bT\u0010)R \u0010\u001c\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010'\"\u0004\bV\u0010)¨\u0006v"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/PastVisitsList;", "", Constant.VISIT_ID, "", "bookerId", "customerId", "", "type", "customerType", "previousLatitude", "", "previousLongitude", "currentLatitude", "currentLongitude", "view", "remark", "reason", NotificationCompat.CATEGORY_STATUS, "createdAt", "updatedAt", "customerName", "distanceKm", "estTime", "visittype", "priority", "purpose", "customerAddress", "visitStartTime", "visitDate", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBookerId", "setBookerId", "getCustomerId", "()Ljava/lang/String;", "setCustomerId", "(Ljava/lang/String;)V", "getType", "setType", "getCustomerType", "setCustomerType", "getPreviousLatitude", "()Ljava/lang/Double;", "setPreviousLatitude", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getPreviousLongitude", "setPreviousLongitude", "getCurrentLatitude", "setCurrentLatitude", "getCurrentLongitude", "setCurrentLongitude", "getView", "setView", "getRemark", "setRemark", "getReason", "setReason", "getStatus", "setStatus", "getCreatedAt", "setCreatedAt", "getUpdatedAt", "setUpdatedAt", "getCustomerName", "setCustomerName", "getDistanceKm", "setDistanceKm", "getEstTime", "setEstTime", "getVisittype", "setVisittype", "getPriority", "setPriority", "getPurpose", "setPurpose", "getCustomerAddress", "setCustomerAddress", "getVisitStartTime", "setVisitStartTime", "getVisitDate", "setVisitDate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/PastVisitsList;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class PastVisitsList {

    @SerializedName("booker_id")
    private Integer bookerId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("current_latitude")
    private Double currentLatitude;

    @SerializedName("current_longitude")
    private Double currentLongitude;

    @SerializedName("customer_address")
    private String customerAddress;

    @SerializedName("customer_id")
    private String customerId;

    @SerializedName("customer_name")
    private String customerName;

    @SerializedName("customer_type")
    private String customerType;

    @SerializedName("distance_km")
    private Integer distanceKm;

    @SerializedName("est_time")
    private String estTime;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("previous_latitude")
    private Double previousLatitude;

    @SerializedName("previous_longitude")
    private Double previousLongitude;

    @SerializedName("priority")
    private String priority;

    @SerializedName("purpose")
    private String purpose;

    @SerializedName("reason")
    private String reason;

    @SerializedName("remark")
    private String remark;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    @SerializedName("type")
    private String type;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("view")
    private Integer view;

    @SerializedName("visit_date")
    private String visitDate;

    @SerializedName("visitstarttime")
    private String visitStartTime;

    @SerializedName("visit_type")
    private String visittype;

    public PastVisitsList() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16777215, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final Integer getView() {
        return this.view;
    }

    /* renamed from: component11, reason: from getter */
    public final String getRemark() {
        return this.remark;
    }

    /* renamed from: component12, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* renamed from: component13, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component14, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component15, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component16, reason: from getter */
    public final String getCustomerName() {
        return this.customerName;
    }

    /* renamed from: component17, reason: from getter */
    public final Integer getDistanceKm() {
        return this.distanceKm;
    }

    /* renamed from: component18, reason: from getter */
    public final String getEstTime() {
        return this.estTime;
    }

    /* renamed from: component19, reason: from getter */
    public final String getVisittype() {
        return this.visittype;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getBookerId() {
        return this.bookerId;
    }

    /* renamed from: component20, reason: from getter */
    public final String getPriority() {
        return this.priority;
    }

    /* renamed from: component21, reason: from getter */
    public final String getPurpose() {
        return this.purpose;
    }

    /* renamed from: component22, reason: from getter */
    public final String getCustomerAddress() {
        return this.customerAddress;
    }

    /* renamed from: component23, reason: from getter */
    public final String getVisitStartTime() {
        return this.visitStartTime;
    }

    /* renamed from: component24, reason: from getter */
    public final String getVisitDate() {
        return this.visitDate;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCustomerType() {
        return this.customerType;
    }

    /* renamed from: component6, reason: from getter */
    public final Double getPreviousLatitude() {
        return this.previousLatitude;
    }

    /* renamed from: component7, reason: from getter */
    public final Double getPreviousLongitude() {
        return this.previousLongitude;
    }

    /* renamed from: component8, reason: from getter */
    public final Double getCurrentLatitude() {
        return this.currentLatitude;
    }

    /* renamed from: component9, reason: from getter */
    public final Double getCurrentLongitude() {
        return this.currentLongitude;
    }

    public final PastVisitsList copy(Integer id, Integer bookerId, String customerId, String type, String customerType, Double previousLatitude, Double previousLongitude, Double currentLatitude, Double currentLongitude, Integer view, String remark, String reason, String status, String createdAt, String updatedAt, String customerName, Integer distanceKm, String estTime, String visittype, String priority, String purpose, String customerAddress, String visitStartTime, String visitDate) {
        return new PastVisitsList(id, bookerId, customerId, type, customerType, previousLatitude, previousLongitude, currentLatitude, currentLongitude, view, remark, reason, status, createdAt, updatedAt, customerName, distanceKm, estTime, visittype, priority, purpose, customerAddress, visitStartTime, visitDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PastVisitsList)) {
            return false;
        }
        PastVisitsList pastVisitsList = (PastVisitsList) other;
        return Intrinsics.areEqual(this.id, pastVisitsList.id) && Intrinsics.areEqual(this.bookerId, pastVisitsList.bookerId) && Intrinsics.areEqual(this.customerId, pastVisitsList.customerId) && Intrinsics.areEqual(this.type, pastVisitsList.type) && Intrinsics.areEqual(this.customerType, pastVisitsList.customerType) && Intrinsics.areEqual((Object) this.previousLatitude, (Object) pastVisitsList.previousLatitude) && Intrinsics.areEqual((Object) this.previousLongitude, (Object) pastVisitsList.previousLongitude) && Intrinsics.areEqual((Object) this.currentLatitude, (Object) pastVisitsList.currentLatitude) && Intrinsics.areEqual((Object) this.currentLongitude, (Object) pastVisitsList.currentLongitude) && Intrinsics.areEqual(this.view, pastVisitsList.view) && Intrinsics.areEqual(this.remark, pastVisitsList.remark) && Intrinsics.areEqual(this.reason, pastVisitsList.reason) && Intrinsics.areEqual(this.status, pastVisitsList.status) && Intrinsics.areEqual(this.createdAt, pastVisitsList.createdAt) && Intrinsics.areEqual(this.updatedAt, pastVisitsList.updatedAt) && Intrinsics.areEqual(this.customerName, pastVisitsList.customerName) && Intrinsics.areEqual(this.distanceKm, pastVisitsList.distanceKm) && Intrinsics.areEqual(this.estTime, pastVisitsList.estTime) && Intrinsics.areEqual(this.visittype, pastVisitsList.visittype) && Intrinsics.areEqual(this.priority, pastVisitsList.priority) && Intrinsics.areEqual(this.purpose, pastVisitsList.purpose) && Intrinsics.areEqual(this.customerAddress, pastVisitsList.customerAddress) && Intrinsics.areEqual(this.visitStartTime, pastVisitsList.visitStartTime) && Intrinsics.areEqual(this.visitDate, pastVisitsList.visitDate);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.bookerId == null ? 0 : this.bookerId.hashCode())) * 31) + (this.customerId == null ? 0 : this.customerId.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.customerType == null ? 0 : this.customerType.hashCode())) * 31) + (this.previousLatitude == null ? 0 : this.previousLatitude.hashCode())) * 31) + (this.previousLongitude == null ? 0 : this.previousLongitude.hashCode())) * 31) + (this.currentLatitude == null ? 0 : this.currentLatitude.hashCode())) * 31) + (this.currentLongitude == null ? 0 : this.currentLongitude.hashCode())) * 31) + (this.view == null ? 0 : this.view.hashCode())) * 31) + (this.remark == null ? 0 : this.remark.hashCode())) * 31) + (this.reason == null ? 0 : this.reason.hashCode())) * 31) + (this.status == null ? 0 : this.status.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.updatedAt == null ? 0 : this.updatedAt.hashCode())) * 31) + (this.customerName == null ? 0 : this.customerName.hashCode())) * 31) + (this.distanceKm == null ? 0 : this.distanceKm.hashCode())) * 31) + (this.estTime == null ? 0 : this.estTime.hashCode())) * 31) + (this.visittype == null ? 0 : this.visittype.hashCode())) * 31) + (this.priority == null ? 0 : this.priority.hashCode())) * 31) + (this.purpose == null ? 0 : this.purpose.hashCode())) * 31) + (this.customerAddress == null ? 0 : this.customerAddress.hashCode())) * 31) + (this.visitStartTime == null ? 0 : this.visitStartTime.hashCode())) * 31) + (this.visitDate != null ? this.visitDate.hashCode() : 0);
    }

    public String toString() {
        return "PastVisitsList(id=" + this.id + ", bookerId=" + this.bookerId + ", customerId=" + this.customerId + ", type=" + this.type + ", customerType=" + this.customerType + ", previousLatitude=" + this.previousLatitude + ", previousLongitude=" + this.previousLongitude + ", currentLatitude=" + this.currentLatitude + ", currentLongitude=" + this.currentLongitude + ", view=" + this.view + ", remark=" + this.remark + ", reason=" + this.reason + ", status=" + this.status + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", customerName=" + this.customerName + ", distanceKm=" + this.distanceKm + ", estTime=" + this.estTime + ", visittype=" + this.visittype + ", priority=" + this.priority + ", purpose=" + this.purpose + ", customerAddress=" + this.customerAddress + ", visitStartTime=" + this.visitStartTime + ", visitDate=" + this.visitDate + ")";
    }

    public PastVisitsList(Integer id, Integer bookerId, String customerId, String type, String customerType, Double previousLatitude, Double previousLongitude, Double currentLatitude, Double currentLongitude, Integer view, String remark, String reason, String status, String createdAt, String updatedAt, String customerName, Integer distanceKm, String estTime, String visittype, String priority, String purpose, String customerAddress, String visitStartTime, String visitDate) {
        this.id = id;
        this.bookerId = bookerId;
        this.customerId = customerId;
        this.type = type;
        this.customerType = customerType;
        this.previousLatitude = previousLatitude;
        this.previousLongitude = previousLongitude;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.view = view;
        this.remark = remark;
        this.reason = reason;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.estTime = estTime;
        this.visittype = visittype;
        this.priority = priority;
        this.purpose = purpose;
        this.customerAddress = customerAddress;
        this.visitStartTime = visitStartTime;
        this.visitDate = visitDate;
    }

    public /* synthetic */ PastVisitsList(Integer num, Integer num2, String str, String str2, String str3, Double d, Double d2, Double d3, Double d4, Integer num3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num4, String str10, String str11, String str12, String str13, String str14, String str15, String str16, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : d, (i & 64) != 0 ? null : d2, (i & 128) != 0 ? null : d3, (i & 256) != 0 ? null : d4, (i & 512) != 0 ? null : num3, (i & 1024) != 0 ? null : str4, (i & 2048) != 0 ? null : str5, (i & 4096) != 0 ? null : str6, (i & 8192) != 0 ? null : str7, (i & 16384) != 0 ? null : str8, (i & 32768) != 0 ? null : str9, (i & 65536) != 0 ? null : num4, (i & 131072) != 0 ? null : str10, (i & 262144) != 0 ? null : str11, (i & 524288) != 0 ? null : str12, (i & 1048576) != 0 ? null : str13, (i & 2097152) != 0 ? null : str14, (i & 4194304) != 0 ? null : str15, (i & 8388608) != 0 ? null : str16);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final Integer getBookerId() {
        return this.bookerId;
    }

    public final void setBookerId(Integer num) {
        this.bookerId = num;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(String str) {
        this.customerId = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final void setCustomerType(String str) {
        this.customerType = str;
    }

    public final Double getPreviousLatitude() {
        return this.previousLatitude;
    }

    public final void setPreviousLatitude(Double d) {
        this.previousLatitude = d;
    }

    public final Double getPreviousLongitude() {
        return this.previousLongitude;
    }

    public final void setPreviousLongitude(Double d) {
        this.previousLongitude = d;
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

    public final Integer getView() {
        return this.view;
    }

    public final void setView(Integer num) {
        this.view = num;
    }

    public final String getRemark() {
        return this.remark;
    }

    public final void setRemark(String str) {
        this.remark = str;
    }

    public final String getReason() {
        return this.reason;
    }

    public final void setReason(String str) {
        this.reason = str;
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(String str) {
        this.status = str;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public final String getCustomerName() {
        return this.customerName;
    }

    public final void setCustomerName(String str) {
        this.customerName = str;
    }

    public final Integer getDistanceKm() {
        return this.distanceKm;
    }

    public final void setDistanceKm(Integer num) {
        this.distanceKm = num;
    }

    public final String getEstTime() {
        return this.estTime;
    }

    public final void setEstTime(String str) {
        this.estTime = str;
    }

    public final String getVisittype() {
        return this.visittype;
    }

    public final void setVisittype(String str) {
        this.visittype = str;
    }

    public final String getPriority() {
        return this.priority;
    }

    public final void setPriority(String str) {
        this.priority = str;
    }

    public final String getPurpose() {
        return this.purpose;
    }

    public final void setPurpose(String str) {
        this.purpose = str;
    }

    public final String getCustomerAddress() {
        return this.customerAddress;
    }

    public final void setCustomerAddress(String str) {
        this.customerAddress = str;
    }

    public final String getVisitStartTime() {
        return this.visitStartTime;
    }

    public final void setVisitStartTime(String str) {
        this.visitStartTime = str;
    }

    public final String getVisitDate() {
        return this.visitDate;
    }

    public final void setVisitDate(String str) {
        this.visitDate = str;
    }
}
