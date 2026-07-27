package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BooksBySegmentData.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b*\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0019\u0010-\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tHÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0017J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0017J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010'J|\u00103\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u00104J\u0013\u00105\u001a\u00020\u000f2\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u00020\u0005HÖ\u0001J\t\u00108\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R.\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u001f\u0010\u0017\"\u0004\b \u0010\u0019R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0013\"\u0004\b\"\u0010\u0015R\"\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b#\u0010\u0017\"\u0004\b$\u0010\u0019R \u0010\r\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0013\"\u0004\b&\u0010\u0015R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010*\u001a\u0004\b\u000e\u0010'\"\u0004\b(\u0010)¨\u00069"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegmentData;", "", "segment", "", "totalBooksCount", "", "books", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegment;", "Lkotlin/collections/ArrayList;", Constant.VISIT_ID, AppMeasurementSdk.ConditionalUserProperty.NAME, "bookCount", NotificationCompat.CATEGORY_STATUS, "isSelected", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)V", "getSegment", "()Ljava/lang/String;", "setSegment", "(Ljava/lang/String;)V", "getTotalBooksCount", "()Ljava/lang/Integer;", "setTotalBooksCount", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBooks", "()Ljava/util/ArrayList;", "setBooks", "(Ljava/util/ArrayList;)V", "getId", "setId", "getName", "setName", "getBookCount", "setBookCount", "getStatus", "setStatus", "()Ljava/lang/Boolean;", "setSelected", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegmentData;", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class BooksBySegmentData {

    @SerializedName("book_count")
    private Integer bookCount;

    @SerializedName("books")
    private ArrayList<BooksBySegment> books;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;
    private Boolean isSelected;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;

    @SerializedName("segment")
    private String segment;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    @SerializedName("total_books_count")
    private Integer totalBooksCount;

    public BooksBySegmentData() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSegment() {
        return this.segment;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getTotalBooksCount() {
        return this.totalBooksCount;
    }

    public final ArrayList<BooksBySegment> component3() {
        return this.books;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getBookCount() {
        return this.bookCount;
    }

    /* renamed from: component7, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component8, reason: from getter */
    public final Boolean getIsSelected() {
        return this.isSelected;
    }

    public final BooksBySegmentData copy(String segment, Integer totalBooksCount, ArrayList<BooksBySegment> books, Integer id, String name, Integer bookCount, String status, Boolean isSelected) {
        Intrinsics.checkNotNullParameter(books, "books");
        return new BooksBySegmentData(segment, totalBooksCount, books, id, name, bookCount, status, isSelected);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BooksBySegmentData)) {
            return false;
        }
        BooksBySegmentData booksBySegmentData = (BooksBySegmentData) other;
        return Intrinsics.areEqual(this.segment, booksBySegmentData.segment) && Intrinsics.areEqual(this.totalBooksCount, booksBySegmentData.totalBooksCount) && Intrinsics.areEqual(this.books, booksBySegmentData.books) && Intrinsics.areEqual(this.id, booksBySegmentData.id) && Intrinsics.areEqual(this.name, booksBySegmentData.name) && Intrinsics.areEqual(this.bookCount, booksBySegmentData.bookCount) && Intrinsics.areEqual(this.status, booksBySegmentData.status) && Intrinsics.areEqual(this.isSelected, booksBySegmentData.isSelected);
    }

    public int hashCode() {
        return ((((((((((((((this.segment == null ? 0 : this.segment.hashCode()) * 31) + (this.totalBooksCount == null ? 0 : this.totalBooksCount.hashCode())) * 31) + this.books.hashCode()) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.bookCount == null ? 0 : this.bookCount.hashCode())) * 31) + (this.status == null ? 0 : this.status.hashCode())) * 31) + (this.isSelected != null ? this.isSelected.hashCode() : 0);
    }

    public String toString() {
        return "BooksBySegmentData(segment=" + this.segment + ", totalBooksCount=" + this.totalBooksCount + ", books=" + this.books + ", id=" + this.id + ", name=" + this.name + ", bookCount=" + this.bookCount + ", status=" + this.status + ", isSelected=" + this.isSelected + ")";
    }

    public BooksBySegmentData(String segment, Integer totalBooksCount, ArrayList<BooksBySegment> books, Integer id, String name, Integer bookCount, String status, Boolean isSelected) {
        Intrinsics.checkNotNullParameter(books, "books");
        this.segment = segment;
        this.totalBooksCount = totalBooksCount;
        this.books = books;
        this.id = id;
        this.name = name;
        this.bookCount = bookCount;
        this.status = status;
        this.isSelected = isSelected;
    }

    public /* synthetic */ BooksBySegmentData(String str, Integer num, ArrayList arrayList, Integer num2, String str2, Integer num3, String str3, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? new ArrayList() : arrayList, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : num3, (i & 64) == 0 ? str3 : null, (i & 128) != 0 ? false : bool);
    }

    public final String getSegment() {
        return this.segment;
    }

    public final void setSegment(String str) {
        this.segment = str;
    }

    public final Integer getTotalBooksCount() {
        return this.totalBooksCount;
    }

    public final void setTotalBooksCount(Integer num) {
        this.totalBooksCount = num;
    }

    public final ArrayList<BooksBySegment> getBooks() {
        return this.books;
    }

    public final void setBooks(ArrayList<BooksBySegment> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.books = arrayList;
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final Integer getBookCount() {
        return this.bookCount;
    }

    public final void setBookCount(Integer num) {
        this.bookCount = num;
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(String str) {
        this.status = str;
    }

    public final Boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(Boolean bool) {
        this.isSelected = bool;
    }
}
