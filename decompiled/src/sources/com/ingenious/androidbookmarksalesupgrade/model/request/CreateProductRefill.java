package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CreateProductRefill.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003J2\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefill;", "", "productId", "", FirebaseAnalytics.Param.QUANTITY, "image", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getProductId", "()Ljava/lang/Integer;", "setProductId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getQuantity", "setQuantity", "getImage", "()Ljava/lang/String;", "setImage", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefill;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class CreateProductRefill {

    @SerializedName("image")
    private String image;

    @SerializedName("product_id")
    private Integer productId;

    @SerializedName(FirebaseAnalytics.Param.QUANTITY)
    private Integer quantity;

    public CreateProductRefill() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ CreateProductRefill copy$default(CreateProductRefill createProductRefill, Integer num, Integer num2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = createProductRefill.productId;
        }
        if ((i & 2) != 0) {
            num2 = createProductRefill.quantity;
        }
        if ((i & 4) != 0) {
            str = createProductRefill.image;
        }
        return createProductRefill.copy(num, num2, str);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getProductId() {
        return this.productId;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getQuantity() {
        return this.quantity;
    }

    /* renamed from: component3, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    public final CreateProductRefill copy(Integer productId, Integer quantity, String image) {
        return new CreateProductRefill(productId, quantity, image);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CreateProductRefill)) {
            return false;
        }
        CreateProductRefill createProductRefill = (CreateProductRefill) other;
        return Intrinsics.areEqual(this.productId, createProductRefill.productId) && Intrinsics.areEqual(this.quantity, createProductRefill.quantity) && Intrinsics.areEqual(this.image, createProductRefill.image);
    }

    public int hashCode() {
        return ((((this.productId == null ? 0 : this.productId.hashCode()) * 31) + (this.quantity == null ? 0 : this.quantity.hashCode())) * 31) + (this.image != null ? this.image.hashCode() : 0);
    }

    public String toString() {
        return "CreateProductRefill(productId=" + this.productId + ", quantity=" + this.quantity + ", image=" + this.image + ")";
    }

    public CreateProductRefill(Integer productId, Integer quantity, String image) {
        this.productId = productId;
        this.quantity = quantity;
        this.image = image;
    }

    public /* synthetic */ CreateProductRefill(Integer num, Integer num2, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : str);
    }

    public final Integer getProductId() {
        return this.productId;
    }

    public final void setProductId(Integer num) {
        this.productId = num;
    }

    public final Integer getQuantity() {
        return this.quantity;
    }

    public final void setQuantity(Integer num) {
        this.quantity = num;
    }

    public final String getImage() {
        return this.image;
    }

    public final void setImage(String str) {
        this.image = str;
    }
}
