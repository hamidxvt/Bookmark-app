package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LastVisitCustomerResponse.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0013"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerData;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerData;)V", "getData", "()Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerData;", "setData", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class LastVisitCustomerResponse extends GlobalResponse {

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    private LastVisitCustomerData data;

    /* JADX WARN: Multi-variable type inference failed */
    public LastVisitCustomerResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ LastVisitCustomerResponse copy$default(LastVisitCustomerResponse lastVisitCustomerResponse, LastVisitCustomerData lastVisitCustomerData, int i, Object obj) {
        if ((i & 1) != 0) {
            lastVisitCustomerData = lastVisitCustomerResponse.data;
        }
        return lastVisitCustomerResponse.copy(lastVisitCustomerData);
    }

    /* renamed from: component1, reason: from getter */
    public final LastVisitCustomerData getData() {
        return this.data;
    }

    public final LastVisitCustomerResponse copy(LastVisitCustomerData data) {
        return new LastVisitCustomerResponse(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LastVisitCustomerResponse) && Intrinsics.areEqual(this.data, ((LastVisitCustomerResponse) other).data);
    }

    public int hashCode() {
        if (this.data == null) {
            return 0;
        }
        return this.data.hashCode();
    }

    public String toString() {
        return "LastVisitCustomerResponse(data=" + this.data + ")";
    }

    public /* synthetic */ LastVisitCustomerResponse(LastVisitCustomerData lastVisitCustomerData, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LastVisitCustomerData(null, null, null, null, null, 31, null) : lastVisitCustomerData);
    }

    public final LastVisitCustomerData getData() {
        return this.data;
    }

    public final void setData(LastVisitCustomerData lastVisitCustomerData) {
        this.data = lastVisitCustomerData;
    }

    public LastVisitCustomerResponse(LastVisitCustomerData data) {
        super(null, null, null, 7, null);
        this.data = data;
    }
}
