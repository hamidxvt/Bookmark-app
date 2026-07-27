package com.ingenious.androidbookmarksalesupgrade.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectedProducts.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0011J\t\u0010\u001a\u001a\u00020\tHÆ\u0003JD\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/SelectedProducts;", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "", FirebaseAnalytics.Param.PRICE, FirebaseAnalytics.Param.QUANTITY, "image", "", "isSelected", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Z)V", "getName", "()Ljava/lang/String;", "getPrice", "getQuantity", "getImage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "setSelected", "(Z)V", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Z)Lcom/ingenious/androidbookmarksalesupgrade/model/SelectedProducts;", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class SelectedProducts {
    private final Integer image;
    private boolean isSelected;
    private final String name;
    private final String price;
    private final String quantity;

    public static /* synthetic */ SelectedProducts copy$default(SelectedProducts selectedProducts, String str, String str2, String str3, Integer num, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = selectedProducts.name;
        }
        if ((i & 2) != 0) {
            str2 = selectedProducts.price;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = selectedProducts.quantity;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            num = selectedProducts.image;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            z = selectedProducts.isSelected;
        }
        return selectedProducts.copy(str, str4, str5, num2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* renamed from: component3, reason: from getter */
    public final String getQuantity() {
        return this.quantity;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getImage() {
        return this.image;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsSelected() {
        return this.isSelected;
    }

    public final SelectedProducts copy(String name, String price, String quantity, Integer image, boolean isSelected) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(price, "price");
        return new SelectedProducts(name, price, quantity, image, isSelected);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectedProducts)) {
            return false;
        }
        SelectedProducts selectedProducts = (SelectedProducts) other;
        return Intrinsics.areEqual(this.name, selectedProducts.name) && Intrinsics.areEqual(this.price, selectedProducts.price) && Intrinsics.areEqual(this.quantity, selectedProducts.quantity) && Intrinsics.areEqual(this.image, selectedProducts.image) && this.isSelected == selectedProducts.isSelected;
    }

    public int hashCode() {
        return (((((((this.name.hashCode() * 31) + this.price.hashCode()) * 31) + (this.quantity == null ? 0 : this.quantity.hashCode())) * 31) + (this.image != null ? this.image.hashCode() : 0)) * 31) + Boolean.hashCode(this.isSelected);
    }

    public String toString() {
        return "SelectedProducts(name=" + this.name + ", price=" + this.price + ", quantity=" + this.quantity + ", image=" + this.image + ", isSelected=" + this.isSelected + ")";
    }

    public SelectedProducts(String name, String price, String quantity, Integer image, boolean isSelected) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(price, "price");
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.isSelected = isSelected;
    }

    public /* synthetic */ SelectedProducts(String str, String str2, String str3, Integer num, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : num, (i & 16) != 0 ? false : z);
    }

    public final String getName() {
        return this.name;
    }

    public final String getPrice() {
        return this.price;
    }

    public final String getQuantity() {
        return this.quantity;
    }

    public final Integer getImage() {
        return this.image;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }
}
