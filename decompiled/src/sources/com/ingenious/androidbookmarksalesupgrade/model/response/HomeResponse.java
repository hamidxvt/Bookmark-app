package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeResponse.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b=\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BÇ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010C\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010D\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010E\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010F\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010G\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010H\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010I\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010J\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010K\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010L\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0011\u0010M\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011HÆ\u0003J\u0011\u0010N\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0011HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0016HÆ\u0003JÎ\u0001\u0010P\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00112\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÆ\u0001¢\u0006\u0002\u0010QJ\u0013\u0010R\u001a\u00020\u00032\b\u0010S\u001a\u0004\u0018\u00010THÖ\u0003J\t\u0010U\u001a\u00020\u0005HÖ\u0001J\t\u0010V\u001a\u00020\u0016HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b%\u0010\u001f\"\u0004\b&\u0010!R\"\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b'\u0010\u001f\"\u0004\b(\u0010!R\"\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!R\"\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b+\u0010\u001f\"\u0004\b,\u0010!R\"\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b-\u0010\u001f\"\u0004\b.\u0010!R\"\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b/\u0010\u001f\"\u0004\b0\u0010!R\"\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b1\u0010\u001f\"\u0004\b2\u0010!R\"\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b3\u0010\u001f\"\u0004\b4\u0010!R\"\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b5\u0010\u001f\"\u0004\b6\u0010!R&\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R&\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00108\"\u0004\b<\u0010:R \u0010\u0015\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@¨\u0006W"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/HomeResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "jobStarted", "", "totalVisitsToday", "", "visitsLeftToday", "visitsCompletedToday", "visitsLastWeek", "visitsThisWeek", "schoolTotalCount", "schoolCompletedCount", "shopTotalCount", "shopCompletedCount", "totalsVisitsCount", "completedVisitsCount", "todayVisits", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/TodayVisitsList;", "pastVisits", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/PastVisitsList;", "startTime", "", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/String;)V", "getJobStarted", "()Ljava/lang/Boolean;", "setJobStarted", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getTotalVisitsToday", "()Ljava/lang/Integer;", "setTotalVisitsToday", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getVisitsLeftToday", "setVisitsLeftToday", "getVisitsCompletedToday", "setVisitsCompletedToday", "getVisitsLastWeek", "setVisitsLastWeek", "getVisitsThisWeek", "setVisitsThisWeek", "getSchoolTotalCount", "setSchoolTotalCount", "getSchoolCompletedCount", "setSchoolCompletedCount", "getShopTotalCount", "setShopTotalCount", "getShopCompletedCount", "setShopCompletedCount", "getTotalsVisitsCount", "setTotalsVisitsCount", "getCompletedVisitsCount", "setCompletedVisitsCount", "getTodayVisits", "()Ljava/util/ArrayList;", "setTodayVisits", "(Ljava/util/ArrayList;)V", "getPastVisits", "setPastVisits", "getStartTime", "()Ljava/lang/String;", "setStartTime", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/HomeResponse;", "equals", "other", "", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class HomeResponse extends GlobalResponse {

    @SerializedName("CompletedVisitsCount")
    private Integer completedVisitsCount;

    @SerializedName("jobStarted")
    private Boolean jobStarted;

    @SerializedName("pastVisits")
    private ArrayList<PastVisitsList> pastVisits;

    @SerializedName("schoolCompletedCount")
    private Integer schoolCompletedCount;

    @SerializedName("schooltotalCount")
    private Integer schoolTotalCount;

    @SerializedName("shopCompletedCount")
    private Integer shopCompletedCount;

    @SerializedName("shoptotalCount")
    private Integer shopTotalCount;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("todayVisits")
    private ArrayList<TodayVisitsList> todayVisits;

    @SerializedName("totalvisitsToday")
    private Integer totalVisitsToday;

    @SerializedName("totalsVisitsCount")
    private Integer totalsVisitsCount;

    @SerializedName("visitsCompletedToday")
    private Integer visitsCompletedToday;

    @SerializedName("visitsLastWeek")
    private Integer visitsLastWeek;

    @SerializedName("visitsLeftToday")
    private Integer visitsLeftToday;

    @SerializedName("visitsThisWeek")
    private Integer visitsThisWeek;

    public HomeResponse() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Boolean getJobStarted() {
        return this.jobStarted;
    }

    /* renamed from: component10, reason: from getter */
    public final Integer getShopCompletedCount() {
        return this.shopCompletedCount;
    }

    /* renamed from: component11, reason: from getter */
    public final Integer getTotalsVisitsCount() {
        return this.totalsVisitsCount;
    }

    /* renamed from: component12, reason: from getter */
    public final Integer getCompletedVisitsCount() {
        return this.completedVisitsCount;
    }

    public final ArrayList<TodayVisitsList> component13() {
        return this.todayVisits;
    }

    public final ArrayList<PastVisitsList> component14() {
        return this.pastVisits;
    }

    /* renamed from: component15, reason: from getter */
    public final String getStartTime() {
        return this.startTime;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getTotalVisitsToday() {
        return this.totalVisitsToday;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getVisitsLeftToday() {
        return this.visitsLeftToday;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getVisitsCompletedToday() {
        return this.visitsCompletedToday;
    }

    /* renamed from: component5, reason: from getter */
    public final Integer getVisitsLastWeek() {
        return this.visitsLastWeek;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getVisitsThisWeek() {
        return this.visitsThisWeek;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getSchoolTotalCount() {
        return this.schoolTotalCount;
    }

    /* renamed from: component8, reason: from getter */
    public final Integer getSchoolCompletedCount() {
        return this.schoolCompletedCount;
    }

    /* renamed from: component9, reason: from getter */
    public final Integer getShopTotalCount() {
        return this.shopTotalCount;
    }

    public final HomeResponse copy(Boolean jobStarted, Integer totalVisitsToday, Integer visitsLeftToday, Integer visitsCompletedToday, Integer visitsLastWeek, Integer visitsThisWeek, Integer schoolTotalCount, Integer schoolCompletedCount, Integer shopTotalCount, Integer shopCompletedCount, Integer totalsVisitsCount, Integer completedVisitsCount, ArrayList<TodayVisitsList> todayVisits, ArrayList<PastVisitsList> pastVisits, String startTime) {
        return new HomeResponse(jobStarted, totalVisitsToday, visitsLeftToday, visitsCompletedToday, visitsLastWeek, visitsThisWeek, schoolTotalCount, schoolCompletedCount, shopTotalCount, shopCompletedCount, totalsVisitsCount, completedVisitsCount, todayVisits, pastVisits, startTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeResponse)) {
            return false;
        }
        HomeResponse homeResponse = (HomeResponse) other;
        return Intrinsics.areEqual(this.jobStarted, homeResponse.jobStarted) && Intrinsics.areEqual(this.totalVisitsToday, homeResponse.totalVisitsToday) && Intrinsics.areEqual(this.visitsLeftToday, homeResponse.visitsLeftToday) && Intrinsics.areEqual(this.visitsCompletedToday, homeResponse.visitsCompletedToday) && Intrinsics.areEqual(this.visitsLastWeek, homeResponse.visitsLastWeek) && Intrinsics.areEqual(this.visitsThisWeek, homeResponse.visitsThisWeek) && Intrinsics.areEqual(this.schoolTotalCount, homeResponse.schoolTotalCount) && Intrinsics.areEqual(this.schoolCompletedCount, homeResponse.schoolCompletedCount) && Intrinsics.areEqual(this.shopTotalCount, homeResponse.shopTotalCount) && Intrinsics.areEqual(this.shopCompletedCount, homeResponse.shopCompletedCount) && Intrinsics.areEqual(this.totalsVisitsCount, homeResponse.totalsVisitsCount) && Intrinsics.areEqual(this.completedVisitsCount, homeResponse.completedVisitsCount) && Intrinsics.areEqual(this.todayVisits, homeResponse.todayVisits) && Intrinsics.areEqual(this.pastVisits, homeResponse.pastVisits) && Intrinsics.areEqual(this.startTime, homeResponse.startTime);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((this.jobStarted == null ? 0 : this.jobStarted.hashCode()) * 31) + (this.totalVisitsToday == null ? 0 : this.totalVisitsToday.hashCode())) * 31) + (this.visitsLeftToday == null ? 0 : this.visitsLeftToday.hashCode())) * 31) + (this.visitsCompletedToday == null ? 0 : this.visitsCompletedToday.hashCode())) * 31) + (this.visitsLastWeek == null ? 0 : this.visitsLastWeek.hashCode())) * 31) + (this.visitsThisWeek == null ? 0 : this.visitsThisWeek.hashCode())) * 31) + (this.schoolTotalCount == null ? 0 : this.schoolTotalCount.hashCode())) * 31) + (this.schoolCompletedCount == null ? 0 : this.schoolCompletedCount.hashCode())) * 31) + (this.shopTotalCount == null ? 0 : this.shopTotalCount.hashCode())) * 31) + (this.shopCompletedCount == null ? 0 : this.shopCompletedCount.hashCode())) * 31) + (this.totalsVisitsCount == null ? 0 : this.totalsVisitsCount.hashCode())) * 31) + (this.completedVisitsCount == null ? 0 : this.completedVisitsCount.hashCode())) * 31) + (this.todayVisits == null ? 0 : this.todayVisits.hashCode())) * 31) + (this.pastVisits == null ? 0 : this.pastVisits.hashCode())) * 31) + (this.startTime != null ? this.startTime.hashCode() : 0);
    }

    public String toString() {
        return "HomeResponse(jobStarted=" + this.jobStarted + ", totalVisitsToday=" + this.totalVisitsToday + ", visitsLeftToday=" + this.visitsLeftToday + ", visitsCompletedToday=" + this.visitsCompletedToday + ", visitsLastWeek=" + this.visitsLastWeek + ", visitsThisWeek=" + this.visitsThisWeek + ", schoolTotalCount=" + this.schoolTotalCount + ", schoolCompletedCount=" + this.schoolCompletedCount + ", shopTotalCount=" + this.shopTotalCount + ", shopCompletedCount=" + this.shopCompletedCount + ", totalsVisitsCount=" + this.totalsVisitsCount + ", completedVisitsCount=" + this.completedVisitsCount + ", todayVisits=" + this.todayVisits + ", pastVisits=" + this.pastVisits + ", startTime=" + this.startTime + ")";
    }

    public /* synthetic */ HomeResponse(Boolean bool, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, ArrayList arrayList, ArrayList arrayList2, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : num4, (i & 32) != 0 ? null : num5, (i & 64) != 0 ? null : num6, (i & 128) != 0 ? null : num7, (i & 256) != 0 ? null : num8, (i & 512) != 0 ? null : num9, (i & 1024) != 0 ? null : num10, (i & 2048) != 0 ? null : num11, (i & 4096) != 0 ? null : arrayList, (i & 8192) != 0 ? null : arrayList2, (i & 16384) == 0 ? str : null);
    }

    public final Boolean getJobStarted() {
        return this.jobStarted;
    }

    public final void setJobStarted(Boolean bool) {
        this.jobStarted = bool;
    }

    public final Integer getTotalVisitsToday() {
        return this.totalVisitsToday;
    }

    public final void setTotalVisitsToday(Integer num) {
        this.totalVisitsToday = num;
    }

    public final Integer getVisitsLeftToday() {
        return this.visitsLeftToday;
    }

    public final void setVisitsLeftToday(Integer num) {
        this.visitsLeftToday = num;
    }

    public final Integer getVisitsCompletedToday() {
        return this.visitsCompletedToday;
    }

    public final void setVisitsCompletedToday(Integer num) {
        this.visitsCompletedToday = num;
    }

    public final Integer getVisitsLastWeek() {
        return this.visitsLastWeek;
    }

    public final void setVisitsLastWeek(Integer num) {
        this.visitsLastWeek = num;
    }

    public final Integer getVisitsThisWeek() {
        return this.visitsThisWeek;
    }

    public final void setVisitsThisWeek(Integer num) {
        this.visitsThisWeek = num;
    }

    public final Integer getSchoolTotalCount() {
        return this.schoolTotalCount;
    }

    public final void setSchoolTotalCount(Integer num) {
        this.schoolTotalCount = num;
    }

    public final Integer getSchoolCompletedCount() {
        return this.schoolCompletedCount;
    }

    public final void setSchoolCompletedCount(Integer num) {
        this.schoolCompletedCount = num;
    }

    public final Integer getShopTotalCount() {
        return this.shopTotalCount;
    }

    public final void setShopTotalCount(Integer num) {
        this.shopTotalCount = num;
    }

    public final Integer getShopCompletedCount() {
        return this.shopCompletedCount;
    }

    public final void setShopCompletedCount(Integer num) {
        this.shopCompletedCount = num;
    }

    public final Integer getTotalsVisitsCount() {
        return this.totalsVisitsCount;
    }

    public final void setTotalsVisitsCount(Integer num) {
        this.totalsVisitsCount = num;
    }

    public final Integer getCompletedVisitsCount() {
        return this.completedVisitsCount;
    }

    public final void setCompletedVisitsCount(Integer num) {
        this.completedVisitsCount = num;
    }

    public final ArrayList<TodayVisitsList> getTodayVisits() {
        return this.todayVisits;
    }

    public final void setTodayVisits(ArrayList<TodayVisitsList> arrayList) {
        this.todayVisits = arrayList;
    }

    public final ArrayList<PastVisitsList> getPastVisits() {
        return this.pastVisits;
    }

    public final void setPastVisits(ArrayList<PastVisitsList> arrayList) {
        this.pastVisits = arrayList;
    }

    public final String getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(String str) {
        this.startTime = str;
    }

    public HomeResponse(Boolean jobStarted, Integer totalVisitsToday, Integer visitsLeftToday, Integer visitsCompletedToday, Integer visitsLastWeek, Integer visitsThisWeek, Integer schoolTotalCount, Integer schoolCompletedCount, Integer shopTotalCount, Integer shopCompletedCount, Integer totalsVisitsCount, Integer completedVisitsCount, ArrayList<TodayVisitsList> arrayList, ArrayList<PastVisitsList> arrayList2, String startTime) {
        super(null, null, null, 7, null);
        this.jobStarted = jobStarted;
        this.totalVisitsToday = totalVisitsToday;
        this.visitsLeftToday = visitsLeftToday;
        this.visitsCompletedToday = visitsCompletedToday;
        this.visitsLastWeek = visitsLastWeek;
        this.visitsThisWeek = visitsThisWeek;
        this.schoolTotalCount = schoolTotalCount;
        this.schoolCompletedCount = schoolCompletedCount;
        this.shopTotalCount = shopTotalCount;
        this.shopCompletedCount = shopCompletedCount;
        this.totalsVisitsCount = totalsVisitsCount;
        this.completedVisitsCount = completedVisitsCount;
        this.todayVisits = arrayList;
        this.pastVisits = arrayList2;
        this.startTime = startTime;
    }
}
