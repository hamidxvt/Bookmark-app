package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VisitCompletionDetails.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\tHÆ\u0003JG\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitCompletionDetails;", "", "visitId", "", "remarks", "", "invoiceType", "signatureUrl", "imageUrls", "", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getVisitId", "()I", "getRemarks", "()Ljava/lang/String;", "getInvoiceType", "getSignatureUrl", "getImageUrls", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class VisitCompletionDetails {

    @SerializedName("imageUrls")
    private final List<String> imageUrls;

    @SerializedName("invoice_type")
    private final String invoiceType;

    @SerializedName("remarks")
    private final String remarks;

    @SerializedName("signatureUrl")
    private final String signatureUrl;

    @SerializedName("visitId")
    private final int visitId;

    public static /* synthetic */ VisitCompletionDetails copy$default(VisitCompletionDetails visitCompletionDetails, int i, String str, String str2, String str3, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = visitCompletionDetails.visitId;
        }
        if ((i2 & 2) != 0) {
            str = visitCompletionDetails.remarks;
        }
        String str4 = str;
        if ((i2 & 4) != 0) {
            str2 = visitCompletionDetails.invoiceType;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            str3 = visitCompletionDetails.signatureUrl;
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            list = visitCompletionDetails.imageUrls;
        }
        return visitCompletionDetails.copy(i, str4, str5, str6, list);
    }

    /* renamed from: component1, reason: from getter */
    public final int getVisitId() {
        return this.visitId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getRemarks() {
        return this.remarks;
    }

    /* renamed from: component3, reason: from getter */
    public final String getInvoiceType() {
        return this.invoiceType;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSignatureUrl() {
        return this.signatureUrl;
    }

    public final List<String> component5() {
        return this.imageUrls;
    }

    public final VisitCompletionDetails copy(int visitId, String remarks, String invoiceType, String signatureUrl, List<String> imageUrls) {
        Intrinsics.checkNotNullParameter(imageUrls, "imageUrls");
        return new VisitCompletionDetails(visitId, remarks, invoiceType, signatureUrl, imageUrls);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VisitCompletionDetails)) {
            return false;
        }
        VisitCompletionDetails visitCompletionDetails = (VisitCompletionDetails) other;
        return this.visitId == visitCompletionDetails.visitId && Intrinsics.areEqual(this.remarks, visitCompletionDetails.remarks) && Intrinsics.areEqual(this.invoiceType, visitCompletionDetails.invoiceType) && Intrinsics.areEqual(this.signatureUrl, visitCompletionDetails.signatureUrl) && Intrinsics.areEqual(this.imageUrls, visitCompletionDetails.imageUrls);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.visitId) * 31) + (this.remarks == null ? 0 : this.remarks.hashCode())) * 31) + (this.invoiceType == null ? 0 : this.invoiceType.hashCode())) * 31) + (this.signatureUrl != null ? this.signatureUrl.hashCode() : 0)) * 31) + this.imageUrls.hashCode();
    }

    public String toString() {
        return "VisitCompletionDetails(visitId=" + this.visitId + ", remarks=" + this.remarks + ", invoiceType=" + this.invoiceType + ", signatureUrl=" + this.signatureUrl + ", imageUrls=" + this.imageUrls + ")";
    }

    public VisitCompletionDetails(int visitId, String remarks, String invoiceType, String signatureUrl, List<String> imageUrls) {
        Intrinsics.checkNotNullParameter(imageUrls, "imageUrls");
        this.visitId = visitId;
        this.remarks = remarks;
        this.invoiceType = invoiceType;
        this.signatureUrl = signatureUrl;
        this.imageUrls = imageUrls;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ VisitCompletionDetails(int i, String str, String str2, String str3, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, str3, r5);
        List list2;
        if ((i2 & 16) == 0) {
            list2 = list;
        } else {
            list2 = CollectionsKt.emptyList();
        }
    }

    public final int getVisitId() {
        return this.visitId;
    }

    public final String getRemarks() {
        return this.remarks;
    }

    public final String getInvoiceType() {
        return this.invoiceType;
    }

    public final String getSignatureUrl() {
        return this.signatureUrl;
    }

    public final List<String> getImageUrls() {
        return this.imageUrls;
    }
}
