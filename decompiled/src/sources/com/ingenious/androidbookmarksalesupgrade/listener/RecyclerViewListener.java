package com.ingenious.androidbookmarksalesupgrade.listener;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentData;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecyclerViewListener.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\u001f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;", "", "addQuantityToCart", "", "quantityNo", "", "productId", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/OnResultListener;", "onSelectProduct", "products", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "onSelectSegment", "segments", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegmentData;", "onSelectGradesSubjects", "gradesSubjects", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GradesSubjectsData;", "onSelectBooksList", "books", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksData;", "onTapAddPrice", "onTotalUpdated", "onItemClick", "onTapAddToCart", "onTapSelectQuantity", "onTapDelete", "onTapSelectReturnProduct", "onSegmentTotalUpdated", "Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", "onTapUpdateQuantity", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes12.dex */
public interface RecyclerViewListener {
    void addQuantityToCart(String quantityNo, String productId, OnResultListener listener);

    void onItemClick(Products products);

    void onSegmentTotalUpdated(BookModel products);

    void onSelectBooksList(AdoptionBooksData books);

    void onSelectGradesSubjects(GradesSubjectsData gradesSubjects);

    void onSelectProduct(Products products);

    void onSelectSegment(BooksBySegmentData segments);

    void onTapAddPrice(Products products);

    void onTapAddToCart(Products products);

    void onTapDelete(Products products);

    void onTapSelectQuantity(Products products);

    void onTapSelectReturnProduct(Products products);

    void onTapUpdateQuantity(Products products);

    void onTotalUpdated(Products products);

    /* compiled from: RecyclerViewListener.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static void addQuantityToCart(RecyclerViewListener $this, String quantityNo, String productId, OnResultListener listener) {
            Intrinsics.checkNotNullParameter(quantityNo, "quantityNo");
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(listener, "listener");
        }

        public static void onSelectProduct(RecyclerViewListener $this, Products products) {
            Intrinsics.checkNotNullParameter(products, "products");
        }

        public static void onSelectSegment(RecyclerViewListener $this, BooksBySegmentData segments) {
            Intrinsics.checkNotNullParameter(segments, "segments");
        }

        public static void onSelectGradesSubjects(RecyclerViewListener $this, GradesSubjectsData gradesSubjects) {
            Intrinsics.checkNotNullParameter(gradesSubjects, "gradesSubjects");
        }

        public static void onSelectBooksList(RecyclerViewListener $this, AdoptionBooksData books) {
            Intrinsics.checkNotNullParameter(books, "books");
        }

        public static void onTapAddPrice(RecyclerViewListener $this, Products products) {
            Intrinsics.checkNotNullParameter(products, "products");
        }

        public static void onTotalUpdated(RecyclerViewListener $this, Products products) {
            Intrinsics.checkNotNullParameter(products, "products");
        }

        public static void onItemClick(RecyclerViewListener $this, Products products) {
            Intrinsics.checkNotNullParameter(products, "products");
        }

        public static void onTapAddToCart(RecyclerViewListener $this, Products products) {
            Intrinsics.checkNotNullParameter(products, "products");
        }

        public static void onTapSelectQuantity(RecyclerViewListener $this, Products products) {
            Intrinsics.checkNotNullParameter(products, "products");
        }

        public static void onTapDelete(RecyclerViewListener $this, Products products) {
            Intrinsics.checkNotNullParameter(products, "products");
        }

        public static void onTapSelectReturnProduct(RecyclerViewListener $this, Products products) {
            Intrinsics.checkNotNullParameter(products, "products");
        }

        public static void onSegmentTotalUpdated(RecyclerViewListener $this, BookModel products) {
            Intrinsics.checkNotNullParameter(products, "products");
        }
    }
}
