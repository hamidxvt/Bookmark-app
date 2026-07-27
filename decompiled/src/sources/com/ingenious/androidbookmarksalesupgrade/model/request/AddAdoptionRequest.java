package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddAdoptionRequest.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JQ\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddAdoptionRequest;", "", "adoptionName", "", "adoptionDate", "customerId", "notes", NotificationCompat.CATEGORY_STATUS, "products", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AdoptionProductRequest;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getAdoptionName", "()Ljava/lang/String;", "getAdoptionDate", "getCustomerId", "getNotes", "getStatus", "getProducts", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class AddAdoptionRequest {

    @SerializedName("adoption_date")
    private final String adoptionDate;

    @SerializedName("adoption_name")
    private final String adoptionName;

    @SerializedName("customer_id")
    private final String customerId;

    @SerializedName("notes")
    private final String notes;

    @SerializedName("products")
    private final List<AdoptionProductRequest> products;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private final String status;

    public static /* synthetic */ AddAdoptionRequest copy$default(AddAdoptionRequest addAdoptionRequest, String str, String str2, String str3, String str4, String str5, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addAdoptionRequest.adoptionName;
        }
        if ((i & 2) != 0) {
            str2 = addAdoptionRequest.adoptionDate;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = addAdoptionRequest.customerId;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = addAdoptionRequest.notes;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = addAdoptionRequest.status;
        }
        String str9 = str5;
        if ((i & 32) != 0) {
            list = addAdoptionRequest.products;
        }
        return addAdoptionRequest.copy(str, str6, str7, str8, str9, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAdoptionName() {
        return this.adoptionName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAdoptionDate() {
        return this.adoptionDate;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    /* renamed from: component5, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    public final List<AdoptionProductRequest> component6() {
        return this.products;
    }

    public final AddAdoptionRequest copy(String adoptionName, String adoptionDate, String customerId, String notes, String status, List<AdoptionProductRequest> products) {
        Intrinsics.checkNotNullParameter(adoptionName, "adoptionName");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(products, "products");
        return new AddAdoptionRequest(adoptionName, adoptionDate, customerId, notes, status, products);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddAdoptionRequest)) {
            return false;
        }
        AddAdoptionRequest addAdoptionRequest = (AddAdoptionRequest) other;
        return Intrinsics.areEqual(this.adoptionName, addAdoptionRequest.adoptionName) && Intrinsics.areEqual(this.adoptionDate, addAdoptionRequest.adoptionDate) && Intrinsics.areEqual(this.customerId, addAdoptionRequest.customerId) && Intrinsics.areEqual(this.notes, addAdoptionRequest.notes) && Intrinsics.areEqual(this.status, addAdoptionRequest.status) && Intrinsics.areEqual(this.products, addAdoptionRequest.products);
    }

    public int hashCode() {
        return (((((((((this.adoptionName.hashCode() * 31) + (this.adoptionDate == null ? 0 : this.adoptionDate.hashCode())) * 31) + (this.customerId == null ? 0 : this.customerId.hashCode())) * 31) + (this.notes != null ? this.notes.hashCode() : 0)) * 31) + this.status.hashCode()) * 31) + this.products.hashCode();
    }

    public String toString() {
        return "AddAdoptionRequest(adoptionName=" + this.adoptionName + ", adoptionDate=" + this.adoptionDate + ", customerId=" + this.customerId + ", notes=" + this.notes + ", status=" + this.status + ", products=" + this.products + ")";
    }

    public AddAdoptionRequest(String adoptionName, String adoptionDate, String customerId, String notes, String status, List<AdoptionProductRequest> products) {
        Intrinsics.checkNotNullParameter(adoptionName, "adoptionName");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(products, "products");
        this.adoptionName = adoptionName;
        this.adoptionDate = adoptionDate;
        this.customerId = customerId;
        this.notes = notes;
        this.status = status;
        this.products = products;
    }

    public final String getAdoptionName() {
        return this.adoptionName;
    }

    public final String getAdoptionDate() {
        return this.adoptionDate;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getStatus() {
        return this.status;
    }

    public final List<AdoptionProductRequest> getProducts() {
        return this.products;
    }
}
