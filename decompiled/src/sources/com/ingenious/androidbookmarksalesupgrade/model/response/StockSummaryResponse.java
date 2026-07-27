package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StockSummaryResponse.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007\u0012\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u001d\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0003J\u001d\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0003JV\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00072\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR2\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R2\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013¨\u0006\""}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/StockSummaryResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "totalBooksCount", "", "todayRecommendedBooks", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "Lkotlin/collections/ArrayList;", "allBookerProducts", "<init>", "(Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getTotalBooksCount", "()Ljava/lang/Integer;", "setTotalBooksCount", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTodayRecommendedBooks", "()Ljava/util/ArrayList;", "setTodayRecommendedBooks", "(Ljava/util/ArrayList;)V", "getAllBookerProducts", "setAllBookerProducts", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/util/ArrayList;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/StockSummaryResponse;", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class StockSummaryResponse extends GlobalResponse {

    @SerializedName("all_booker_products")
    private ArrayList<Products> allBookerProducts;

    @SerializedName("todays_recommended_books")
    private ArrayList<Products> todayRecommendedBooks;

    @SerializedName("total_books_count")
    private Integer totalBooksCount;

    public StockSummaryResponse() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StockSummaryResponse copy$default(StockSummaryResponse stockSummaryResponse, Integer num, ArrayList arrayList, ArrayList arrayList2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = stockSummaryResponse.totalBooksCount;
        }
        if ((i & 2) != 0) {
            arrayList = stockSummaryResponse.todayRecommendedBooks;
        }
        if ((i & 4) != 0) {
            arrayList2 = stockSummaryResponse.allBookerProducts;
        }
        return stockSummaryResponse.copy(num, arrayList, arrayList2);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getTotalBooksCount() {
        return this.totalBooksCount;
    }

    public final ArrayList<Products> component2() {
        return this.todayRecommendedBooks;
    }

    public final ArrayList<Products> component3() {
        return this.allBookerProducts;
    }

    public final StockSummaryResponse copy(Integer totalBooksCount, ArrayList<Products> todayRecommendedBooks, ArrayList<Products> allBookerProducts) {
        return new StockSummaryResponse(totalBooksCount, todayRecommendedBooks, allBookerProducts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StockSummaryResponse)) {
            return false;
        }
        StockSummaryResponse stockSummaryResponse = (StockSummaryResponse) other;
        return Intrinsics.areEqual(this.totalBooksCount, stockSummaryResponse.totalBooksCount) && Intrinsics.areEqual(this.todayRecommendedBooks, stockSummaryResponse.todayRecommendedBooks) && Intrinsics.areEqual(this.allBookerProducts, stockSummaryResponse.allBookerProducts);
    }

    public int hashCode() {
        return ((((this.totalBooksCount == null ? 0 : this.totalBooksCount.hashCode()) * 31) + (this.todayRecommendedBooks == null ? 0 : this.todayRecommendedBooks.hashCode())) * 31) + (this.allBookerProducts != null ? this.allBookerProducts.hashCode() : 0);
    }

    public String toString() {
        return "StockSummaryResponse(totalBooksCount=" + this.totalBooksCount + ", todayRecommendedBooks=" + this.todayRecommendedBooks + ", allBookerProducts=" + this.allBookerProducts + ")";
    }

    public /* synthetic */ StockSummaryResponse(Integer num, ArrayList arrayList, ArrayList arrayList2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? new ArrayList() : arrayList, (i & 4) != 0 ? new ArrayList() : arrayList2);
    }

    public final Integer getTotalBooksCount() {
        return this.totalBooksCount;
    }

    public final void setTotalBooksCount(Integer num) {
        this.totalBooksCount = num;
    }

    public final ArrayList<Products> getTodayRecommendedBooks() {
        return this.todayRecommendedBooks;
    }

    public final void setTodayRecommendedBooks(ArrayList<Products> arrayList) {
        this.todayRecommendedBooks = arrayList;
    }

    public final ArrayList<Products> getAllBookerProducts() {
        return this.allBookerProducts;
    }

    public final void setAllBookerProducts(ArrayList<Products> arrayList) {
        this.allBookerProducts = arrayList;
    }

    public StockSummaryResponse(Integer totalBooksCount, ArrayList<Products> arrayList, ArrayList<Products> arrayList2) {
        super(null, null, null, 7, null);
        this.totalBooksCount = totalBooksCount;
        this.todayRecommendedBooks = arrayList;
        this.allBookerProducts = arrayList2;
    }
}
