package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.FrameMetricsAggregator;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdoptionProducts.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010-\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u000bHÆ\u0003Jz\u0010/\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u00100J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u00020\u0003HÖ\u0001J\t\u00105\u001a\u00020\u000bHÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0016\u0010\u0010\"\u0004\b\u0017\u0010\u0012R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0018\u0010\u0010\"\u0004\b\u0019\u0010\u0012R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\"\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u001c\u0010\u0010\"\u0004\b\u001d\u0010\u0012R\"\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u0012R \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R \u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#¨\u00066"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionProducts;", "", Constant.VISIT_ID, "", "adoptionId", "segmentId", "gradeId", "subjectId", "productId", FirebaseAnalytics.Param.QUANTITY, "createdAt", "", "updatedAt", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getAdoptionId", "setAdoptionId", "getSegmentId", "setSegmentId", "getGradeId", "setGradeId", "getSubjectId", "setSubjectId", "getProductId", "setProductId", "getQuantity", "setQuantity", "getCreatedAt", "()Ljava/lang/String;", "setCreatedAt", "(Ljava/lang/String;)V", "getUpdatedAt", "setUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionProducts;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class AdoptionProducts {

    @SerializedName("adoption_id")
    private Integer adoptionId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("grade_id")
    private Integer gradeId;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("product_id")
    private Integer productId;

    @SerializedName(FirebaseAnalytics.Param.QUANTITY)
    private Integer quantity;

    @SerializedName("segment_id")
    private Integer segmentId;

    @SerializedName("subject_id")
    private Integer subjectId;

    @SerializedName("updated_at")
    private String updatedAt;

    public AdoptionProducts() {
        this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getAdoptionId() {
        return this.adoptionId;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getSegmentId() {
        return this.segmentId;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getGradeId() {
        return this.gradeId;
    }

    /* renamed from: component5, reason: from getter */
    public final Integer getSubjectId() {
        return this.subjectId;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getProductId() {
        return this.productId;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getQuantity() {
        return this.quantity;
    }

    /* renamed from: component8, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component9, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final AdoptionProducts copy(Integer id, Integer adoptionId, Integer segmentId, Integer gradeId, Integer subjectId, Integer productId, Integer quantity, String createdAt, String updatedAt) {
        return new AdoptionProducts(id, adoptionId, segmentId, gradeId, subjectId, productId, quantity, createdAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdoptionProducts)) {
            return false;
        }
        AdoptionProducts adoptionProducts = (AdoptionProducts) other;
        return Intrinsics.areEqual(this.id, adoptionProducts.id) && Intrinsics.areEqual(this.adoptionId, adoptionProducts.adoptionId) && Intrinsics.areEqual(this.segmentId, adoptionProducts.segmentId) && Intrinsics.areEqual(this.gradeId, adoptionProducts.gradeId) && Intrinsics.areEqual(this.subjectId, adoptionProducts.subjectId) && Intrinsics.areEqual(this.productId, adoptionProducts.productId) && Intrinsics.areEqual(this.quantity, adoptionProducts.quantity) && Intrinsics.areEqual(this.createdAt, adoptionProducts.createdAt) && Intrinsics.areEqual(this.updatedAt, adoptionProducts.updatedAt);
    }

    public int hashCode() {
        return ((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.adoptionId == null ? 0 : this.adoptionId.hashCode())) * 31) + (this.segmentId == null ? 0 : this.segmentId.hashCode())) * 31) + (this.gradeId == null ? 0 : this.gradeId.hashCode())) * 31) + (this.subjectId == null ? 0 : this.subjectId.hashCode())) * 31) + (this.productId == null ? 0 : this.productId.hashCode())) * 31) + (this.quantity == null ? 0 : this.quantity.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.updatedAt != null ? this.updatedAt.hashCode() : 0);
    }

    public String toString() {
        return "AdoptionProducts(id=" + this.id + ", adoptionId=" + this.adoptionId + ", segmentId=" + this.segmentId + ", gradeId=" + this.gradeId + ", subjectId=" + this.subjectId + ", productId=" + this.productId + ", quantity=" + this.quantity + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
    }

    public AdoptionProducts(Integer id, Integer adoptionId, Integer segmentId, Integer gradeId, Integer subjectId, Integer productId, Integer quantity, String createdAt, String updatedAt) {
        this.id = id;
        this.adoptionId = adoptionId;
        this.segmentId = segmentId;
        this.gradeId = gradeId;
        this.subjectId = subjectId;
        this.productId = productId;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public /* synthetic */ AdoptionProducts(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3, (i & 8) != 0 ? null : num4, (i & 16) != 0 ? null : num5, (i & 32) != 0 ? null : num6, (i & 64) != 0 ? null : num7, (i & 128) != 0 ? null : str, (i & 256) == 0 ? str2 : null);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final Integer getAdoptionId() {
        return this.adoptionId;
    }

    public final void setAdoptionId(Integer num) {
        this.adoptionId = num;
    }

    public final Integer getSegmentId() {
        return this.segmentId;
    }

    public final void setSegmentId(Integer num) {
        this.segmentId = num;
    }

    public final Integer getGradeId() {
        return this.gradeId;
    }

    public final void setGradeId(Integer num) {
        this.gradeId = num;
    }

    public final Integer getSubjectId() {
        return this.subjectId;
    }

    public final void setSubjectId(Integer num) {
        this.subjectId = num;
    }

    public final Integer getProductId() {
        return this.productId;
    }

    public final void setProductId(Integer num) {
        this.productId = num;
    }

    public final Integer getQuantity() {
        return this.quantity;
    }

    public final void setQuantity(Integer num) {
        this.quantity = num;
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
}
