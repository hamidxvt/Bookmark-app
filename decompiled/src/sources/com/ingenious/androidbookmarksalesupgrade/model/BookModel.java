package com.ingenious.androidbookmarksalesupgrade.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BookModel.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0006\u0010\u0014\u001a\u00020\u0005J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006 "}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", "Landroid/os/Parcelable;", "productName", "", FirebaseAnalytics.Param.QUANTITY, "", FirebaseAnalytics.Param.PRICE, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getProductName", "()Ljava/lang/String;", "getQuantity", "()I", "setQuantity", "(I)V", "getPrice", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class BookModel implements Parcelable {
    public static final Parcelable.Creator<BookModel> CREATOR = new Creator();
    private final String price;
    private final String productName;
    private int quantity;

    /* compiled from: BookModel.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Creator implements Parcelable.Creator<BookModel> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final BookModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new BookModel(parcel.readString(), parcel.readInt(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final BookModel[] newArray(int i) {
            return new BookModel[i];
        }
    }

    public static /* synthetic */ BookModel copy$default(BookModel bookModel, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bookModel.productName;
        }
        if ((i2 & 2) != 0) {
            i = bookModel.quantity;
        }
        if ((i2 & 4) != 0) {
            str2 = bookModel.price;
        }
        return bookModel.copy(str, i, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* renamed from: component2, reason: from getter */
    public final int getQuantity() {
        return this.quantity;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    public final BookModel copy(String productName, int quantity, String price) {
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(price, "price");
        return new BookModel(productName, quantity, price);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BookModel)) {
            return false;
        }
        BookModel bookModel = (BookModel) other;
        return Intrinsics.areEqual(this.productName, bookModel.productName) && this.quantity == bookModel.quantity && Intrinsics.areEqual(this.price, bookModel.price);
    }

    public int hashCode() {
        return (((this.productName.hashCode() * 31) + Integer.hashCode(this.quantity)) * 31) + this.price.hashCode();
    }

    public String toString() {
        return "BookModel(productName=" + this.productName + ", quantity=" + this.quantity + ", price=" + this.price + ")";
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public BookModel(String productName, int quantity, String price) {
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(price, "price");
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final int getQuantity() {
        return this.quantity;
    }

    public final void setQuantity(int i) {
        this.quantity = i;
    }

    public final String getPrice() {
        return this.price;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.productName);
        dest.writeInt(this.quantity);
        dest.writeString(this.price);
    }
}
