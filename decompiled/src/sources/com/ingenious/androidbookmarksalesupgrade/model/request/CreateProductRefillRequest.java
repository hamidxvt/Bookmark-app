package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CreateProductRefillRequest.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003J%\u0010\u0013\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001R$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefillRequest;", "", "products", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefill;", "notes", "", "<init>", "(Ljava/util/List;Ljava/lang/String;)V", "getProducts", "()Ljava/util/List;", "setProducts", "(Ljava/util/List;)V", "getNotes", "()Ljava/lang/String;", "setNotes", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class CreateProductRefillRequest {

    @SerializedName("notes")
    private String notes;

    @SerializedName("products")
    private List<CreateProductRefill> products;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CreateProductRefillRequest copy$default(CreateProductRefillRequest createProductRefillRequest, List list, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = createProductRefillRequest.products;
        }
        if ((i & 2) != 0) {
            str = createProductRefillRequest.notes;
        }
        return createProductRefillRequest.copy(list, str);
    }

    public final List<CreateProductRefill> component1() {
        return this.products;
    }

    /* renamed from: component2, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    public final CreateProductRefillRequest copy(List<CreateProductRefill> products, String notes) {
        Intrinsics.checkNotNullParameter(products, "products");
        return new CreateProductRefillRequest(products, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CreateProductRefillRequest)) {
            return false;
        }
        CreateProductRefillRequest createProductRefillRequest = (CreateProductRefillRequest) other;
        return Intrinsics.areEqual(this.products, createProductRefillRequest.products) && Intrinsics.areEqual(this.notes, createProductRefillRequest.notes);
    }

    public int hashCode() {
        return (this.products.hashCode() * 31) + (this.notes == null ? 0 : this.notes.hashCode());
    }

    public String toString() {
        return "CreateProductRefillRequest(products=" + this.products + ", notes=" + this.notes + ")";
    }

    public CreateProductRefillRequest(List<CreateProductRefill> products, String notes) {
        Intrinsics.checkNotNullParameter(products, "products");
        this.products = products;
        this.notes = notes;
    }

    public /* synthetic */ CreateProductRefillRequest(List list, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : str);
    }

    public final List<CreateProductRefill> getProducts() {
        return this.products;
    }

    public final void setProducts(List<CreateProductRefill> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.products = list;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final void setNotes(String str) {
        this.notes = str;
    }
}
