package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddAdoptionRequest.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JH\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000f\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/AdoptionProductRequest;", "", "segmentId", "", "gradeId", "subjectId", "productId", FirebaseAnalytics.Param.QUANTITY, "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;I)V", "getSegmentId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getGradeId", "getSubjectId", "getProductId", "getQuantity", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;I)Lcom/ingenious/androidbookmarksalesupgrade/model/request/AdoptionProductRequest;", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class AdoptionProductRequest {

    @SerializedName("grade_id")
    private final Integer gradeId;

    @SerializedName("product_id")
    private final Integer productId;

    @SerializedName(FirebaseAnalytics.Param.QUANTITY)
    private final int quantity;

    @SerializedName("segment_id")
    private final Integer segmentId;

    @SerializedName("subject_id")
    private final Integer subjectId;

    public static /* synthetic */ AdoptionProductRequest copy$default(AdoptionProductRequest adoptionProductRequest, Integer num, Integer num2, Integer num3, Integer num4, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = adoptionProductRequest.segmentId;
        }
        if ((i2 & 2) != 0) {
            num2 = adoptionProductRequest.gradeId;
        }
        Integer num5 = num2;
        if ((i2 & 4) != 0) {
            num3 = adoptionProductRequest.subjectId;
        }
        Integer num6 = num3;
        if ((i2 & 8) != 0) {
            num4 = adoptionProductRequest.productId;
        }
        Integer num7 = num4;
        if ((i2 & 16) != 0) {
            i = adoptionProductRequest.quantity;
        }
        return adoptionProductRequest.copy(num, num5, num6, num7, i);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getSegmentId() {
        return this.segmentId;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getGradeId() {
        return this.gradeId;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getSubjectId() {
        return this.subjectId;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getProductId() {
        return this.productId;
    }

    /* renamed from: component5, reason: from getter */
    public final int getQuantity() {
        return this.quantity;
    }

    public final AdoptionProductRequest copy(Integer segmentId, Integer gradeId, Integer subjectId, Integer productId, int quantity) {
        return new AdoptionProductRequest(segmentId, gradeId, subjectId, productId, quantity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdoptionProductRequest)) {
            return false;
        }
        AdoptionProductRequest adoptionProductRequest = (AdoptionProductRequest) other;
        return Intrinsics.areEqual(this.segmentId, adoptionProductRequest.segmentId) && Intrinsics.areEqual(this.gradeId, adoptionProductRequest.gradeId) && Intrinsics.areEqual(this.subjectId, adoptionProductRequest.subjectId) && Intrinsics.areEqual(this.productId, adoptionProductRequest.productId) && this.quantity == adoptionProductRequest.quantity;
    }

    public int hashCode() {
        return ((((((((this.segmentId == null ? 0 : this.segmentId.hashCode()) * 31) + (this.gradeId == null ? 0 : this.gradeId.hashCode())) * 31) + (this.subjectId == null ? 0 : this.subjectId.hashCode())) * 31) + (this.productId != null ? this.productId.hashCode() : 0)) * 31) + Integer.hashCode(this.quantity);
    }

    public String toString() {
        return "AdoptionProductRequest(segmentId=" + this.segmentId + ", gradeId=" + this.gradeId + ", subjectId=" + this.subjectId + ", productId=" + this.productId + ", quantity=" + this.quantity + ")";
    }

    public AdoptionProductRequest(Integer segmentId, Integer gradeId, Integer subjectId, Integer productId, int quantity) {
        this.segmentId = segmentId;
        this.gradeId = gradeId;
        this.subjectId = subjectId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public final Integer getSegmentId() {
        return this.segmentId;
    }

    public final Integer getGradeId() {
        return this.gradeId;
    }

    public final Integer getSubjectId() {
        return this.subjectId;
    }

    public final Integer getProductId() {
        return this.productId;
    }

    public final int getQuantity() {
        return this.quantity;
    }
}
