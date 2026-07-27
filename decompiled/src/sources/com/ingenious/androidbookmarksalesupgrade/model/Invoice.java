package com.ingenious.androidbookmarksalesupgrade.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Invoice.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\t\u0010%\u001a\u00020\u0006HÆ\u0003J\t\u0010&\u001a\u00020\rHÆ\u0003J\t\u0010'\u001a\u00020\u000fHÆ\u0003Jo\u0010(\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006/"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/Invoice;", "", "products", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "notes", "", "imagePaths", "signaturePath", "invoiceType", "employeeId", "employeeName", "time", "", "totalAmount", "", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JD)V", "getProducts", "()Ljava/util/List;", "getNotes", "()Ljava/lang/String;", "getImagePaths", "getSignaturePath", "getInvoiceType", "getEmployeeId", "getEmployeeName", "getTime", "()J", "getTotalAmount", "()D", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class Invoice {
    private final String employeeId;
    private final String employeeName;
    private final List<String> imagePaths;
    private final String invoiceType;
    private final String notes;
    private final List<Products> products;
    private final String signaturePath;
    private final long time;
    private final double totalAmount;

    public final List<Products> component1() {
        return this.products;
    }

    /* renamed from: component2, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    public final List<String> component3() {
        return this.imagePaths;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSignaturePath() {
        return this.signaturePath;
    }

    /* renamed from: component5, reason: from getter */
    public final String getInvoiceType() {
        return this.invoiceType;
    }

    /* renamed from: component6, reason: from getter */
    public final String getEmployeeId() {
        return this.employeeId;
    }

    /* renamed from: component7, reason: from getter */
    public final String getEmployeeName() {
        return this.employeeName;
    }

    /* renamed from: component8, reason: from getter */
    public final long getTime() {
        return this.time;
    }

    /* renamed from: component9, reason: from getter */
    public final double getTotalAmount() {
        return this.totalAmount;
    }

    public final Invoice copy(List<Products> products, String notes, List<String> imagePaths, String signaturePath, String invoiceType, String employeeId, String employeeName, long time, double totalAmount) {
        Intrinsics.checkNotNullParameter(products, "products");
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(imagePaths, "imagePaths");
        Intrinsics.checkNotNullParameter(signaturePath, "signaturePath");
        Intrinsics.checkNotNullParameter(invoiceType, "invoiceType");
        Intrinsics.checkNotNullParameter(employeeId, "employeeId");
        Intrinsics.checkNotNullParameter(employeeName, "employeeName");
        return new Invoice(products, notes, imagePaths, signaturePath, invoiceType, employeeId, employeeName, time, totalAmount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Invoice)) {
            return false;
        }
        Invoice invoice = (Invoice) other;
        return Intrinsics.areEqual(this.products, invoice.products) && Intrinsics.areEqual(this.notes, invoice.notes) && Intrinsics.areEqual(this.imagePaths, invoice.imagePaths) && Intrinsics.areEqual(this.signaturePath, invoice.signaturePath) && Intrinsics.areEqual(this.invoiceType, invoice.invoiceType) && Intrinsics.areEqual(this.employeeId, invoice.employeeId) && Intrinsics.areEqual(this.employeeName, invoice.employeeName) && this.time == invoice.time && Double.compare(this.totalAmount, invoice.totalAmount) == 0;
    }

    public int hashCode() {
        return (((((((((((((((this.products.hashCode() * 31) + this.notes.hashCode()) * 31) + this.imagePaths.hashCode()) * 31) + this.signaturePath.hashCode()) * 31) + this.invoiceType.hashCode()) * 31) + this.employeeId.hashCode()) * 31) + this.employeeName.hashCode()) * 31) + Long.hashCode(this.time)) * 31) + Double.hashCode(this.totalAmount);
    }

    public String toString() {
        return "Invoice(products=" + this.products + ", notes=" + this.notes + ", imagePaths=" + this.imagePaths + ", signaturePath=" + this.signaturePath + ", invoiceType=" + this.invoiceType + ", employeeId=" + this.employeeId + ", employeeName=" + this.employeeName + ", time=" + this.time + ", totalAmount=" + this.totalAmount + ")";
    }

    public Invoice(List<Products> products, String notes, List<String> imagePaths, String signaturePath, String invoiceType, String employeeId, String employeeName, long time, double totalAmount) {
        Intrinsics.checkNotNullParameter(products, "products");
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(imagePaths, "imagePaths");
        Intrinsics.checkNotNullParameter(signaturePath, "signaturePath");
        Intrinsics.checkNotNullParameter(invoiceType, "invoiceType");
        Intrinsics.checkNotNullParameter(employeeId, "employeeId");
        Intrinsics.checkNotNullParameter(employeeName, "employeeName");
        this.products = products;
        this.notes = notes;
        this.imagePaths = imagePaths;
        this.signaturePath = signaturePath;
        this.invoiceType = invoiceType;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.time = time;
        this.totalAmount = totalAmount;
    }

    public final List<Products> getProducts() {
        return this.products;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final List<String> getImagePaths() {
        return this.imagePaths;
    }

    public final String getSignaturePath() {
        return this.signaturePath;
    }

    public final String getInvoiceType() {
        return this.invoiceType;
    }

    public final String getEmployeeId() {
        return this.employeeId;
    }

    public final String getEmployeeName() {
        return this.employeeName;
    }

    public final long getTime() {
        return this.time;
    }

    public final double getTotalAmount() {
        return this.totalAmount;
    }
}
