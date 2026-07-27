package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VisitDetailsResponse.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ2\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006 "}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "visitDetails", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetails;", "visitsCount", "", "sampleVisitsCount", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetails;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getVisitDetails", "()Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetails;", "setVisitDetails", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetails;)V", "getVisitsCount", "()Ljava/lang/Integer;", "setVisitsCount", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSampleVisitsCount", "setSampleVisitsCount", "component1", "component2", "component3", "copy", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetails;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsResponse;", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class VisitDetailsResponse extends GlobalResponse {

    @SerializedName("sample_visits_count")
    private Integer sampleVisitsCount;

    @SerializedName("visit")
    private VisitDetails visitDetails;

    @SerializedName("visits_count")
    private Integer visitsCount;

    public VisitDetailsResponse() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ VisitDetailsResponse copy$default(VisitDetailsResponse visitDetailsResponse, VisitDetails visitDetails, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            visitDetails = visitDetailsResponse.visitDetails;
        }
        if ((i & 2) != 0) {
            num = visitDetailsResponse.visitsCount;
        }
        if ((i & 4) != 0) {
            num2 = visitDetailsResponse.sampleVisitsCount;
        }
        return visitDetailsResponse.copy(visitDetails, num, num2);
    }

    /* renamed from: component1, reason: from getter */
    public final VisitDetails getVisitDetails() {
        return this.visitDetails;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getVisitsCount() {
        return this.visitsCount;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getSampleVisitsCount() {
        return this.sampleVisitsCount;
    }

    public final VisitDetailsResponse copy(VisitDetails visitDetails, Integer visitsCount, Integer sampleVisitsCount) {
        return new VisitDetailsResponse(visitDetails, visitsCount, sampleVisitsCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VisitDetailsResponse)) {
            return false;
        }
        VisitDetailsResponse visitDetailsResponse = (VisitDetailsResponse) other;
        return Intrinsics.areEqual(this.visitDetails, visitDetailsResponse.visitDetails) && Intrinsics.areEqual(this.visitsCount, visitDetailsResponse.visitsCount) && Intrinsics.areEqual(this.sampleVisitsCount, visitDetailsResponse.sampleVisitsCount);
    }

    public int hashCode() {
        return ((((this.visitDetails == null ? 0 : this.visitDetails.hashCode()) * 31) + (this.visitsCount == null ? 0 : this.visitsCount.hashCode())) * 31) + (this.sampleVisitsCount != null ? this.sampleVisitsCount.hashCode() : 0);
    }

    public String toString() {
        return "VisitDetailsResponse(visitDetails=" + this.visitDetails + ", visitsCount=" + this.visitsCount + ", sampleVisitsCount=" + this.sampleVisitsCount + ")";
    }

    public /* synthetic */ VisitDetailsResponse(VisitDetails visitDetails, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new VisitDetails(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 67108863, null) : visitDetails, (i & 2) != 0 ? null : num, (i & 4) == 0 ? num2 : null);
    }

    public final VisitDetails getVisitDetails() {
        return this.visitDetails;
    }

    public final void setVisitDetails(VisitDetails visitDetails) {
        this.visitDetails = visitDetails;
    }

    public final Integer getVisitsCount() {
        return this.visitsCount;
    }

    public final void setVisitsCount(Integer num) {
        this.visitsCount = num;
    }

    public final Integer getSampleVisitsCount() {
        return this.sampleVisitsCount;
    }

    public final void setSampleVisitsCount(Integer num) {
        this.sampleVisitsCount = num;
    }

    public VisitDetailsResponse(VisitDetails visitDetails, Integer visitsCount, Integer sampleVisitsCount) {
        super(null, null, null, 7, null);
        this.visitDetails = visitDetails;
        this.visitsCount = visitsCount;
        this.sampleVisitsCount = sampleVisitsCount;
    }
}
