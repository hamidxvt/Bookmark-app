package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Customers.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b9\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B¿\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t\u0012\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u0007\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0019\u00109\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010=\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0019\u0010>\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u0007HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010B\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018JÆ\u0001\u0010E\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010FJ\u0013\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010J\u001a\u00020\u0003HÖ\u0001J\t\u0010K\u001a\u00020\tHÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR.\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR \u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b$\u0010\u0018\"\u0004\b%\u0010\u001aR\"\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b&\u0010\u0018\"\u0004\b'\u0010\u001aR \u0010\f\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R.\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001d\"\u0004\b+\u0010\u001fR \u0010\u000f\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010!\"\u0004\b-\u0010#R \u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010!\"\u0004\b/\u0010#R\"\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b0\u0010\u0018\"\u0004\b1\u0010\u001aR \u0010\u0012\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010!\"\u0004\b3\u0010#R\"\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b4\u0010\u0018\"\u0004\b5\u0010\u001aR\"\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b6\u0010\u0018\"\u0004\b7\u0010\u001a¨\u0006L"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/Customers;", "", "currentPage", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersData;", "Lkotlin/collections/ArrayList;", "firstPageUrl", "", "from", "lastPage", "lastPageUrl", "links", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Links;", "nextPageUrl", "path", "perPage", "prevPageUrl", TypedValues.TransitionType.S_TO, "total", "<init>", "(Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getCurrentPage", "()Ljava/lang/Integer;", "setCurrentPage", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "getFirstPageUrl", "()Ljava/lang/String;", "setFirstPageUrl", "(Ljava/lang/String;)V", "getFrom", "setFrom", "getLastPage", "setLastPage", "getLastPageUrl", "setLastPageUrl", "getLinks", "setLinks", "getNextPageUrl", "setNextPageUrl", "getPath", "setPath", "getPerPage", "setPerPage", "getPrevPageUrl", "setPrevPageUrl", "getTo", "setTo", "getTotal", "setTotal", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/Customers;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class Customers {

    @SerializedName("current_page")
    private Integer currentPage;

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    private ArrayList<CustomersData> data;

    @SerializedName("first_page_url")
    private String firstPageUrl;

    @SerializedName("from")
    private Integer from;

    @SerializedName("last_page")
    private Integer lastPage;

    @SerializedName("last_page_url")
    private String lastPageUrl;

    @SerializedName("links")
    private ArrayList<Links> links;

    @SerializedName("next_page_url")
    private String nextPageUrl;

    @SerializedName("path")
    private String path;

    @SerializedName("per_page")
    private Integer perPage;

    @SerializedName("prev_page_url")
    private String prevPageUrl;

    @SerializedName(TypedValues.TransitionType.S_TO)
    private Integer to;

    @SerializedName("total")
    private Integer total;

    public Customers() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getCurrentPage() {
        return this.currentPage;
    }

    /* renamed from: component10, reason: from getter */
    public final Integer getPerPage() {
        return this.perPage;
    }

    /* renamed from: component11, reason: from getter */
    public final String getPrevPageUrl() {
        return this.prevPageUrl;
    }

    /* renamed from: component12, reason: from getter */
    public final Integer getTo() {
        return this.to;
    }

    /* renamed from: component13, reason: from getter */
    public final Integer getTotal() {
        return this.total;
    }

    public final ArrayList<CustomersData> component2() {
        return this.data;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFirstPageUrl() {
        return this.firstPageUrl;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getFrom() {
        return this.from;
    }

    /* renamed from: component5, reason: from getter */
    public final Integer getLastPage() {
        return this.lastPage;
    }

    /* renamed from: component6, reason: from getter */
    public final String getLastPageUrl() {
        return this.lastPageUrl;
    }

    public final ArrayList<Links> component7() {
        return this.links;
    }

    /* renamed from: component8, reason: from getter */
    public final String getNextPageUrl() {
        return this.nextPageUrl;
    }

    /* renamed from: component9, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    public final Customers copy(Integer currentPage, ArrayList<CustomersData> data, String firstPageUrl, Integer from, Integer lastPage, String lastPageUrl, ArrayList<Links> links, String nextPageUrl, String path, Integer perPage, String prevPageUrl, Integer to, Integer total) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(links, "links");
        return new Customers(currentPage, data, firstPageUrl, from, lastPage, lastPageUrl, links, nextPageUrl, path, perPage, prevPageUrl, to, total);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Customers)) {
            return false;
        }
        Customers customers = (Customers) other;
        return Intrinsics.areEqual(this.currentPage, customers.currentPage) && Intrinsics.areEqual(this.data, customers.data) && Intrinsics.areEqual(this.firstPageUrl, customers.firstPageUrl) && Intrinsics.areEqual(this.from, customers.from) && Intrinsics.areEqual(this.lastPage, customers.lastPage) && Intrinsics.areEqual(this.lastPageUrl, customers.lastPageUrl) && Intrinsics.areEqual(this.links, customers.links) && Intrinsics.areEqual(this.nextPageUrl, customers.nextPageUrl) && Intrinsics.areEqual(this.path, customers.path) && Intrinsics.areEqual(this.perPage, customers.perPage) && Intrinsics.areEqual(this.prevPageUrl, customers.prevPageUrl) && Intrinsics.areEqual(this.to, customers.to) && Intrinsics.areEqual(this.total, customers.total);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((this.currentPage == null ? 0 : this.currentPage.hashCode()) * 31) + this.data.hashCode()) * 31) + (this.firstPageUrl == null ? 0 : this.firstPageUrl.hashCode())) * 31) + (this.from == null ? 0 : this.from.hashCode())) * 31) + (this.lastPage == null ? 0 : this.lastPage.hashCode())) * 31) + (this.lastPageUrl == null ? 0 : this.lastPageUrl.hashCode())) * 31) + this.links.hashCode()) * 31) + (this.nextPageUrl == null ? 0 : this.nextPageUrl.hashCode())) * 31) + (this.path == null ? 0 : this.path.hashCode())) * 31) + (this.perPage == null ? 0 : this.perPage.hashCode())) * 31) + (this.prevPageUrl == null ? 0 : this.prevPageUrl.hashCode())) * 31) + (this.to == null ? 0 : this.to.hashCode())) * 31) + (this.total != null ? this.total.hashCode() : 0);
    }

    public String toString() {
        return "Customers(currentPage=" + this.currentPage + ", data=" + this.data + ", firstPageUrl=" + this.firstPageUrl + ", from=" + this.from + ", lastPage=" + this.lastPage + ", lastPageUrl=" + this.lastPageUrl + ", links=" + this.links + ", nextPageUrl=" + this.nextPageUrl + ", path=" + this.path + ", perPage=" + this.perPage + ", prevPageUrl=" + this.prevPageUrl + ", to=" + this.to + ", total=" + this.total + ")";
    }

    public Customers(Integer currentPage, ArrayList<CustomersData> data, String firstPageUrl, Integer from, Integer lastPage, String lastPageUrl, ArrayList<Links> links, String nextPageUrl, String path, Integer perPage, String prevPageUrl, Integer to, Integer total) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(links, "links");
        this.currentPage = currentPage;
        this.data = data;
        this.firstPageUrl = firstPageUrl;
        this.from = from;
        this.lastPage = lastPage;
        this.lastPageUrl = lastPageUrl;
        this.links = links;
        this.nextPageUrl = nextPageUrl;
        this.path = path;
        this.perPage = perPage;
        this.prevPageUrl = prevPageUrl;
        this.to = to;
        this.total = total;
    }

    public /* synthetic */ Customers(Integer num, ArrayList arrayList, String str, Integer num2, Integer num3, String str2, ArrayList arrayList2, String str3, String str4, Integer num4, String str5, Integer num5, Integer num6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? new ArrayList() : arrayList, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : num3, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? new ArrayList() : arrayList2, (i & 128) != 0 ? null : str3, (i & 256) != 0 ? null : str4, (i & 512) != 0 ? null : num4, (i & 1024) != 0 ? null : str5, (i & 2048) != 0 ? null : num5, (i & 4096) == 0 ? num6 : null);
    }

    public final Integer getCurrentPage() {
        return this.currentPage;
    }

    public final void setCurrentPage(Integer num) {
        this.currentPage = num;
    }

    public final ArrayList<CustomersData> getData() {
        return this.data;
    }

    public final void setData(ArrayList<CustomersData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.data = arrayList;
    }

    public final String getFirstPageUrl() {
        return this.firstPageUrl;
    }

    public final void setFirstPageUrl(String str) {
        this.firstPageUrl = str;
    }

    public final Integer getFrom() {
        return this.from;
    }

    public final void setFrom(Integer num) {
        this.from = num;
    }

    public final Integer getLastPage() {
        return this.lastPage;
    }

    public final void setLastPage(Integer num) {
        this.lastPage = num;
    }

    public final String getLastPageUrl() {
        return this.lastPageUrl;
    }

    public final void setLastPageUrl(String str) {
        this.lastPageUrl = str;
    }

    public final ArrayList<Links> getLinks() {
        return this.links;
    }

    public final void setLinks(ArrayList<Links> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.links = arrayList;
    }

    public final String getNextPageUrl() {
        return this.nextPageUrl;
    }

    public final void setNextPageUrl(String str) {
        this.nextPageUrl = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        this.path = str;
    }

    public final Integer getPerPage() {
        return this.perPage;
    }

    public final void setPerPage(Integer num) {
        this.perPage = num;
    }

    public final String getPrevPageUrl() {
        return this.prevPageUrl;
    }

    public final void setPrevPageUrl(String str) {
        this.prevPageUrl = str;
    }

    public final Integer getTo() {
        return this.to;
    }

    public final void setTo(Integer num) {
        this.to = num;
    }

    public final Integer getTotal() {
        return this.total;
    }

    public final void setTotal(Integer num) {
        this.total = num;
    }
}
