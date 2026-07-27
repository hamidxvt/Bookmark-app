package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdoptionListResponse.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J/\u0010\u0014\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R.\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionListResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionsList;", "Lkotlin/collections/ArrayList;", "pagination", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Pagination;", "<init>", "(Ljava/util/ArrayList;Lcom/ingenious/androidbookmarksalesupgrade/model/response/Pagination;)V", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "getPagination", "()Lcom/ingenious/androidbookmarksalesupgrade/model/response/Pagination;", "setPagination", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/Pagination;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class AdoptionListResponse extends GlobalResponse {

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    private ArrayList<AdoptionsList> data;

    @SerializedName("pagination")
    private Pagination pagination;

    /* JADX WARN: Multi-variable type inference failed */
    public AdoptionListResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AdoptionListResponse copy$default(AdoptionListResponse adoptionListResponse, ArrayList arrayList, Pagination pagination, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = adoptionListResponse.data;
        }
        if ((i & 2) != 0) {
            pagination = adoptionListResponse.pagination;
        }
        return adoptionListResponse.copy(arrayList, pagination);
    }

    public final ArrayList<AdoptionsList> component1() {
        return this.data;
    }

    /* renamed from: component2, reason: from getter */
    public final Pagination getPagination() {
        return this.pagination;
    }

    public final AdoptionListResponse copy(ArrayList<AdoptionsList> data, Pagination pagination) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new AdoptionListResponse(data, pagination);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdoptionListResponse)) {
            return false;
        }
        AdoptionListResponse adoptionListResponse = (AdoptionListResponse) other;
        return Intrinsics.areEqual(this.data, adoptionListResponse.data) && Intrinsics.areEqual(this.pagination, adoptionListResponse.pagination);
    }

    public int hashCode() {
        return (this.data.hashCode() * 31) + (this.pagination == null ? 0 : this.pagination.hashCode());
    }

    public String toString() {
        return "AdoptionListResponse(data=" + this.data + ", pagination=" + this.pagination + ")";
    }

    public /* synthetic */ AdoptionListResponse(ArrayList arrayList, Pagination pagination, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList, (i & 2) != 0 ? new Pagination(null, null, null, null, 15, null) : pagination);
    }

    public final ArrayList<AdoptionsList> getData() {
        return this.data;
    }

    public final void setData(ArrayList<AdoptionsList> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.data = arrayList;
    }

    public final Pagination getPagination() {
        return this.pagination;
    }

    public final void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdoptionListResponse(ArrayList<AdoptionsList> data, Pagination pagination) {
        super(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.pagination = pagination;
    }
}
