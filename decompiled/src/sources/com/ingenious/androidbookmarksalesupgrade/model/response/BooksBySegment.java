package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BooksBySegment.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J2\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\n\"\u0004\b\u0013\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegment;", "", "productName", "", FirebaseAnalytics.Param.QUANTITY, "", FirebaseAnalytics.Param.PRICE, "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getProductName", "()Ljava/lang/String;", "setProductName", "(Ljava/lang/String;)V", "getQuantity", "()Ljava/lang/Integer;", "setQuantity", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPrice", "setPrice", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegment;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class BooksBySegment {

    @SerializedName(FirebaseAnalytics.Param.PRICE)
    private String price;

    @SerializedName("product_name")
    private String productName;

    @SerializedName(FirebaseAnalytics.Param.QUANTITY)
    private Integer quantity;

    public BooksBySegment() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ BooksBySegment copy$default(BooksBySegment booksBySegment, String str, Integer num, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = booksBySegment.productName;
        }
        if ((i & 2) != 0) {
            num = booksBySegment.quantity;
        }
        if ((i & 4) != 0) {
            str2 = booksBySegment.price;
        }
        return booksBySegment.copy(str, num, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getQuantity() {
        return this.quantity;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    public final BooksBySegment copy(String productName, Integer quantity, String price) {
        return new BooksBySegment(productName, quantity, price);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BooksBySegment)) {
            return false;
        }
        BooksBySegment booksBySegment = (BooksBySegment) other;
        return Intrinsics.areEqual(this.productName, booksBySegment.productName) && Intrinsics.areEqual(this.quantity, booksBySegment.quantity) && Intrinsics.areEqual(this.price, booksBySegment.price);
    }

    public int hashCode() {
        return ((((this.productName == null ? 0 : this.productName.hashCode()) * 31) + (this.quantity == null ? 0 : this.quantity.hashCode())) * 31) + (this.price != null ? this.price.hashCode() : 0);
    }

    public String toString() {
        return "BooksBySegment(productName=" + this.productName + ", quantity=" + this.quantity + ", price=" + this.price + ")";
    }

    public BooksBySegment(String productName, Integer quantity, String price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public /* synthetic */ BooksBySegment(String str, Integer num, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str2);
    }

    public final String getProductName() {
        return this.productName;
    }

    public final void setProductName(String str) {
        this.productName = str;
    }

    public final Integer getQuantity() {
        return this.quantity;
    }

    public final void setQuantity(Integer num) {
        this.quantity = num;
    }

    public final String getPrice() {
        return this.price;
    }

    public final void setPrice(String str) {
        this.price = str;
    }
}
