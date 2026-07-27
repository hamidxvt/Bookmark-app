package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdoptionBooksList.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J4\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR.\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "totalResults", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksData;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/lang/Integer;Ljava/util/ArrayList;)V", "getTotalResults", "()Ljava/lang/Integer;", "setTotalResults", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/util/ArrayList;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksList;", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class AdoptionBooksList extends GlobalResponse {

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    private ArrayList<AdoptionBooksData> data;

    @SerializedName("total_results")
    private Integer totalResults;

    /* JADX WARN: Multi-variable type inference failed */
    public AdoptionBooksList() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AdoptionBooksList copy$default(AdoptionBooksList adoptionBooksList, Integer num, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            num = adoptionBooksList.totalResults;
        }
        if ((i & 2) != 0) {
            arrayList = adoptionBooksList.data;
        }
        return adoptionBooksList.copy(num, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getTotalResults() {
        return this.totalResults;
    }

    public final ArrayList<AdoptionBooksData> component2() {
        return this.data;
    }

    public final AdoptionBooksList copy(Integer totalResults, ArrayList<AdoptionBooksData> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new AdoptionBooksList(totalResults, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdoptionBooksList)) {
            return false;
        }
        AdoptionBooksList adoptionBooksList = (AdoptionBooksList) other;
        return Intrinsics.areEqual(this.totalResults, adoptionBooksList.totalResults) && Intrinsics.areEqual(this.data, adoptionBooksList.data);
    }

    public int hashCode() {
        return ((this.totalResults == null ? 0 : this.totalResults.hashCode()) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "AdoptionBooksList(totalResults=" + this.totalResults + ", data=" + this.data + ")";
    }

    public /* synthetic */ AdoptionBooksList(Integer num, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? new ArrayList() : arrayList);
    }

    public final Integer getTotalResults() {
        return this.totalResults;
    }

    public final void setTotalResults(Integer num) {
        this.totalResults = num;
    }

    public final ArrayList<AdoptionBooksData> getData() {
        return this.data;
    }

    public final void setData(ArrayList<AdoptionBooksData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.data = arrayList;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdoptionBooksList(Integer totalResults, ArrayList<AdoptionBooksData> data) {
        super(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(data, "data");
        this.totalResults = totalResults;
        this.data = data;
    }
}
