package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationCheckResponse.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0013"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/LocationCheckResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "visitDetailsCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsCustomer;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsCustomer;)V", "getVisitDetailsCustomer", "()Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsCustomer;", "setVisitDetailsCustomer", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class LocationCheckResponse extends GlobalResponse {

    @SerializedName("visitDetails")
    private VisitDetailsCustomer visitDetailsCustomer;

    /* JADX WARN: Multi-variable type inference failed */
    public LocationCheckResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ LocationCheckResponse copy$default(LocationCheckResponse locationCheckResponse, VisitDetailsCustomer visitDetailsCustomer, int i, Object obj) {
        if ((i & 1) != 0) {
            visitDetailsCustomer = locationCheckResponse.visitDetailsCustomer;
        }
        return locationCheckResponse.copy(visitDetailsCustomer);
    }

    /* renamed from: component1, reason: from getter */
    public final VisitDetailsCustomer getVisitDetailsCustomer() {
        return this.visitDetailsCustomer;
    }

    public final LocationCheckResponse copy(VisitDetailsCustomer visitDetailsCustomer) {
        return new LocationCheckResponse(visitDetailsCustomer);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LocationCheckResponse) && Intrinsics.areEqual(this.visitDetailsCustomer, ((LocationCheckResponse) other).visitDetailsCustomer);
    }

    public int hashCode() {
        if (this.visitDetailsCustomer == null) {
            return 0;
        }
        return this.visitDetailsCustomer.hashCode();
    }

    public String toString() {
        return "LocationCheckResponse(visitDetailsCustomer=" + this.visitDetailsCustomer + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ LocationCheckResponse(VisitDetailsCustomer visitDetailsCustomer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(r0);
        VisitDetailsCustomer visitDetailsCustomer2;
        if ((i & 1) == 0) {
            visitDetailsCustomer2 = visitDetailsCustomer;
        } else {
            visitDetailsCustomer2 = new VisitDetailsCustomer(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
        }
    }

    public final VisitDetailsCustomer getVisitDetailsCustomer() {
        return this.visitDetailsCustomer;
    }

    public final void setVisitDetailsCustomer(VisitDetailsCustomer visitDetailsCustomer) {
        this.visitDetailsCustomer = visitDetailsCustomer;
    }

    public LocationCheckResponse(VisitDetailsCustomer visitDetailsCustomer) {
        super(null, null, null, 7, null);
        this.visitDetailsCustomer = visitDetailsCustomer;
    }
}
