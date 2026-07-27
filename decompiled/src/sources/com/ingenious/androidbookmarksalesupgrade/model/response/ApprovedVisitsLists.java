package com.ingenious.androidbookmarksalesupgrade.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApprovedVisitsLists.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\bO\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0095\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010R\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010S\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010_\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010`\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u0010f\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aHÆ\u0003J\u009c\u0002\u0010g\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aHÆ\u0001¢\u0006\u0002\u0010hJ\u0006\u0010i\u001a\u00020\u0003J\u0013\u0010j\u001a\u00020k2\b\u0010l\u001a\u0004\u0018\u00010mHÖ\u0003J\t\u0010n\u001a\u00020\u0003HÖ\u0001J\t\u0010o\u001a\u00020\u0006HÖ\u0001J\u0016\u0010p\u001a\u00020q2\u0006\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020\u0003R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R \u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(R \u0010\b\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010&\"\u0004\b,\u0010(R \u0010\t\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010&\"\u0004\b.\u0010(R \u0010\n\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010&\"\u0004\b0\u0010(R \u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010&\"\u0004\b2\u0010(R \u0010\f\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010&\"\u0004\b4\u0010(R \u0010\r\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010&\"\u0004\b6\u0010(R \u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010&\"\u0004\b8\u0010(R \u0010\u000f\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010&\"\u0004\b:\u0010(R \u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010&\"\u0004\b<\u0010(R \u0010\u0011\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010&\"\u0004\b>\u0010(R\"\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b?\u0010\u001f\"\u0004\b@\u0010!R\"\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\bA\u0010\u001f\"\u0004\bB\u0010!R\"\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\bC\u0010\u001f\"\u0004\bD\u0010!R\"\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\bE\u0010\u001f\"\u0004\bF\u0010!R \u0010\u0016\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010&\"\u0004\bH\u0010(R \u0010\u0017\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010&\"\u0004\bJ\u0010(R \u0010\u0018\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010&\"\u0004\bL\u0010(R&\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010P¨\u0006u"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsLists;", "Landroid/os/Parcelable;", Constant.VISIT_ID, "", "bookerId", "customerId", "", "type", "customerType", "remark", "reason", NotificationCompat.CATEGORY_STATUS, "priority", "visitType", "invoiceType", "visitDate", "createdAt", "updatedAt", "visitTotal", "visitDuration", "sample", "imageCount", "imageUrls", "invoiceImageUrls", "signatureUrl", "products", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBookerId", "setBookerId", "getCustomerId", "()Ljava/lang/String;", "setCustomerId", "(Ljava/lang/String;)V", "getType", "setType", "getCustomerType", "setCustomerType", "getRemark", "setRemark", "getReason", "setReason", "getStatus", "setStatus", "getPriority", "setPriority", "getVisitType", "setVisitType", "getInvoiceType", "setInvoiceType", "getVisitDate", "setVisitDate", "getCreatedAt", "setCreatedAt", "getUpdatedAt", "setUpdatedAt", "getVisitTotal", "setVisitTotal", "getVisitDuration", "setVisitDuration", "getSample", "setSample", "getImageCount", "setImageCount", "getImageUrls", "setImageUrls", "getInvoiceImageUrls", "setInvoiceImageUrls", "getSignatureUrl", "setSignatureUrl", "getProducts", "()Ljava/util/List;", "setProducts", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsLists;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class ApprovedVisitsLists implements Parcelable {
    public static final Parcelable.Creator<ApprovedVisitsLists> CREATOR = new Creator();

    @SerializedName("booker_id")
    private Integer bookerId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("customer_id")
    private String customerId;

    @SerializedName("customer_type")
    private String customerType;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("image_count")
    private Integer imageCount;

    @SerializedName("image_urls")
    private String imageUrls;

    @SerializedName("invoice")
    private String invoiceImageUrls;

    @SerializedName("invoice_type")
    private String invoiceType;

    @SerializedName("priority")
    private String priority;

    @SerializedName("products")
    private List<Products> products;

    @SerializedName("reason")
    private String reason;

    @SerializedName("remark")
    private String remark;

    @SerializedName("sample")
    private Integer sample;

    @SerializedName("signature_url")
    private String signatureUrl;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    @SerializedName("type")
    private String type;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("visit_date")
    private String visitDate;

    @SerializedName("VisitDuration")
    private Integer visitDuration;

    @SerializedName("visit_total")
    private Integer visitTotal;

    @SerializedName("visit_type")
    private String visitType;

    /* compiled from: ApprovedVisitsLists.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Creator implements Parcelable.Creator<ApprovedVisitsLists> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ApprovedVisitsLists createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            ArrayList arrayList = null;
            Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer valueOf2 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            String readString8 = parcel.readString();
            String readString9 = parcel.readString();
            String readString10 = parcel.readString();
            String readString11 = parcel.readString();
            String readString12 = parcel.readString();
            Integer valueOf3 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer valueOf4 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer valueOf5 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer valueOf6 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            String readString13 = parcel.readString();
            String readString14 = parcel.readString();
            String readString15 = parcel.readString();
            if (parcel.readInt() != 0) {
                int readInt = parcel.readInt();
                arrayList = new ArrayList(readInt);
                int i = 0;
                while (i != readInt) {
                    arrayList.add(Products.CREATOR.createFromParcel(parcel));
                    i++;
                    readInt = readInt;
                }
            }
            return new ApprovedVisitsLists(valueOf, valueOf2, readString, readString2, readString3, readString4, readString5, readString6, readString7, readString8, readString9, readString10, readString11, readString12, valueOf3, valueOf4, valueOf5, valueOf6, readString13, readString14, readString15, arrayList);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ApprovedVisitsLists[] newArray(int i) {
            return new ApprovedVisitsLists[i];
        }
    }

    public ApprovedVisitsLists() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 4194303, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getVisitType() {
        return this.visitType;
    }

    /* renamed from: component11, reason: from getter */
    public final String getInvoiceType() {
        return this.invoiceType;
    }

    /* renamed from: component12, reason: from getter */
    public final String getVisitDate() {
        return this.visitDate;
    }

    /* renamed from: component13, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component14, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component15, reason: from getter */
    public final Integer getVisitTotal() {
        return this.visitTotal;
    }

    /* renamed from: component16, reason: from getter */
    public final Integer getVisitDuration() {
        return this.visitDuration;
    }

    /* renamed from: component17, reason: from getter */
    public final Integer getSample() {
        return this.sample;
    }

    /* renamed from: component18, reason: from getter */
    public final Integer getImageCount() {
        return this.imageCount;
    }

    /* renamed from: component19, reason: from getter */
    public final String getImageUrls() {
        return this.imageUrls;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getBookerId() {
        return this.bookerId;
    }

    /* renamed from: component20, reason: from getter */
    public final String getInvoiceImageUrls() {
        return this.invoiceImageUrls;
    }

    /* renamed from: component21, reason: from getter */
    public final String getSignatureUrl() {
        return this.signatureUrl;
    }

    public final List<Products> component22() {
        return this.products;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCustomerType() {
        return this.customerType;
    }

    /* renamed from: component6, reason: from getter */
    public final String getRemark() {
        return this.remark;
    }

    /* renamed from: component7, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* renamed from: component8, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component9, reason: from getter */
    public final String getPriority() {
        return this.priority;
    }

    public final ApprovedVisitsLists copy(Integer id, Integer bookerId, String customerId, String type, String customerType, String remark, String reason, String status, String priority, String visitType, String invoiceType, String visitDate, String createdAt, String updatedAt, Integer visitTotal, Integer visitDuration, Integer sample, Integer imageCount, String imageUrls, String invoiceImageUrls, String signatureUrl, List<Products> products) {
        return new ApprovedVisitsLists(id, bookerId, customerId, type, customerType, remark, reason, status, priority, visitType, invoiceType, visitDate, createdAt, updatedAt, visitTotal, visitDuration, sample, imageCount, imageUrls, invoiceImageUrls, signatureUrl, products);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ApprovedVisitsLists)) {
            return false;
        }
        ApprovedVisitsLists approvedVisitsLists = (ApprovedVisitsLists) other;
        return Intrinsics.areEqual(this.id, approvedVisitsLists.id) && Intrinsics.areEqual(this.bookerId, approvedVisitsLists.bookerId) && Intrinsics.areEqual(this.customerId, approvedVisitsLists.customerId) && Intrinsics.areEqual(this.type, approvedVisitsLists.type) && Intrinsics.areEqual(this.customerType, approvedVisitsLists.customerType) && Intrinsics.areEqual(this.remark, approvedVisitsLists.remark) && Intrinsics.areEqual(this.reason, approvedVisitsLists.reason) && Intrinsics.areEqual(this.status, approvedVisitsLists.status) && Intrinsics.areEqual(this.priority, approvedVisitsLists.priority) && Intrinsics.areEqual(this.visitType, approvedVisitsLists.visitType) && Intrinsics.areEqual(this.invoiceType, approvedVisitsLists.invoiceType) && Intrinsics.areEqual(this.visitDate, approvedVisitsLists.visitDate) && Intrinsics.areEqual(this.createdAt, approvedVisitsLists.createdAt) && Intrinsics.areEqual(this.updatedAt, approvedVisitsLists.updatedAt) && Intrinsics.areEqual(this.visitTotal, approvedVisitsLists.visitTotal) && Intrinsics.areEqual(this.visitDuration, approvedVisitsLists.visitDuration) && Intrinsics.areEqual(this.sample, approvedVisitsLists.sample) && Intrinsics.areEqual(this.imageCount, approvedVisitsLists.imageCount) && Intrinsics.areEqual(this.imageUrls, approvedVisitsLists.imageUrls) && Intrinsics.areEqual(this.invoiceImageUrls, approvedVisitsLists.invoiceImageUrls) && Intrinsics.areEqual(this.signatureUrl, approvedVisitsLists.signatureUrl) && Intrinsics.areEqual(this.products, approvedVisitsLists.products);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.bookerId == null ? 0 : this.bookerId.hashCode())) * 31) + (this.customerId == null ? 0 : this.customerId.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.customerType == null ? 0 : this.customerType.hashCode())) * 31) + (this.remark == null ? 0 : this.remark.hashCode())) * 31) + (this.reason == null ? 0 : this.reason.hashCode())) * 31) + (this.status == null ? 0 : this.status.hashCode())) * 31) + (this.priority == null ? 0 : this.priority.hashCode())) * 31) + (this.visitType == null ? 0 : this.visitType.hashCode())) * 31) + (this.invoiceType == null ? 0 : this.invoiceType.hashCode())) * 31) + (this.visitDate == null ? 0 : this.visitDate.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.updatedAt == null ? 0 : this.updatedAt.hashCode())) * 31) + (this.visitTotal == null ? 0 : this.visitTotal.hashCode())) * 31) + (this.visitDuration == null ? 0 : this.visitDuration.hashCode())) * 31) + (this.sample == null ? 0 : this.sample.hashCode())) * 31) + (this.imageCount == null ? 0 : this.imageCount.hashCode())) * 31) + (this.imageUrls == null ? 0 : this.imageUrls.hashCode())) * 31) + (this.invoiceImageUrls == null ? 0 : this.invoiceImageUrls.hashCode())) * 31) + (this.signatureUrl == null ? 0 : this.signatureUrl.hashCode())) * 31) + (this.products != null ? this.products.hashCode() : 0);
    }

    public String toString() {
        return "ApprovedVisitsLists(id=" + this.id + ", bookerId=" + this.bookerId + ", customerId=" + this.customerId + ", type=" + this.type + ", customerType=" + this.customerType + ", remark=" + this.remark + ", reason=" + this.reason + ", status=" + this.status + ", priority=" + this.priority + ", visitType=" + this.visitType + ", invoiceType=" + this.invoiceType + ", visitDate=" + this.visitDate + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", visitTotal=" + this.visitTotal + ", visitDuration=" + this.visitDuration + ", sample=" + this.sample + ", imageCount=" + this.imageCount + ", imageUrls=" + this.imageUrls + ", invoiceImageUrls=" + this.invoiceImageUrls + ", signatureUrl=" + this.signatureUrl + ", products=" + this.products + ")";
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public ApprovedVisitsLists(Integer id, Integer bookerId, String customerId, String type, String customerType, String remark, String reason, String status, String priority, String visitType, String invoiceType, String visitDate, String createdAt, String updatedAt, Integer visitTotal, Integer visitDuration, Integer sample, Integer imageCount, String imageUrls, String invoiceImageUrls, String signatureUrl, List<Products> list) {
        this.id = id;
        this.bookerId = bookerId;
        this.customerId = customerId;
        this.type = type;
        this.customerType = customerType;
        this.remark = remark;
        this.reason = reason;
        this.status = status;
        this.priority = priority;
        this.visitType = visitType;
        this.invoiceType = invoiceType;
        this.visitDate = visitDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.visitTotal = visitTotal;
        this.visitDuration = visitDuration;
        this.sample = sample;
        this.imageCount = imageCount;
        this.imageUrls = imageUrls;
        this.invoiceImageUrls = invoiceImageUrls;
        this.signatureUrl = signatureUrl;
        this.products = list;
    }

    public /* synthetic */ ApprovedVisitsLists(Integer num, Integer num2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, Integer num3, Integer num4, Integer num5, Integer num6, String str13, String str14, String str15, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : str8, (i & 1024) != 0 ? null : str9, (i & 2048) != 0 ? null : str10, (i & 4096) != 0 ? null : str11, (i & 8192) != 0 ? null : str12, (i & 16384) != 0 ? null : num3, (i & 32768) != 0 ? null : num4, (i & 65536) != 0 ? null : num5, (i & 131072) != 0 ? null : num6, (i & 262144) != 0 ? null : str13, (i & 524288) != 0 ? null : str14, (i & 1048576) != 0 ? null : str15, (i & 2097152) != 0 ? null : list);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final Integer getBookerId() {
        return this.bookerId;
    }

    public final void setBookerId(Integer num) {
        this.bookerId = num;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(String str) {
        this.customerId = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final void setCustomerType(String str) {
        this.customerType = str;
    }

    public final String getRemark() {
        return this.remark;
    }

    public final void setRemark(String str) {
        this.remark = str;
    }

    public final String getReason() {
        return this.reason;
    }

    public final void setReason(String str) {
        this.reason = str;
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(String str) {
        this.status = str;
    }

    public final String getPriority() {
        return this.priority;
    }

    public final void setPriority(String str) {
        this.priority = str;
    }

    public final String getVisitType() {
        return this.visitType;
    }

    public final void setVisitType(String str) {
        this.visitType = str;
    }

    public final String getInvoiceType() {
        return this.invoiceType;
    }

    public final void setInvoiceType(String str) {
        this.invoiceType = str;
    }

    public final String getVisitDate() {
        return this.visitDate;
    }

    public final void setVisitDate(String str) {
        this.visitDate = str;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public final Integer getVisitTotal() {
        return this.visitTotal;
    }

    public final void setVisitTotal(Integer num) {
        this.visitTotal = num;
    }

    public final Integer getVisitDuration() {
        return this.visitDuration;
    }

    public final void setVisitDuration(Integer num) {
        this.visitDuration = num;
    }

    public final Integer getSample() {
        return this.sample;
    }

    public final void setSample(Integer num) {
        this.sample = num;
    }

    public final Integer getImageCount() {
        return this.imageCount;
    }

    public final void setImageCount(Integer num) {
        this.imageCount = num;
    }

    public final String getImageUrls() {
        return this.imageUrls;
    }

    public final void setImageUrls(String str) {
        this.imageUrls = str;
    }

    public final String getInvoiceImageUrls() {
        return this.invoiceImageUrls;
    }

    public final void setInvoiceImageUrls(String str) {
        this.invoiceImageUrls = str;
    }

    public final String getSignatureUrl() {
        return this.signatureUrl;
    }

    public final void setSignatureUrl(String str) {
        this.signatureUrl = str;
    }

    public final List<Products> getProducts() {
        return this.products;
    }

    public final void setProducts(List<Products> list) {
        this.products = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        Integer num = this.id;
        if (num == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num.intValue());
        }
        Integer num2 = this.bookerId;
        if (num2 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num2.intValue());
        }
        dest.writeString(this.customerId);
        dest.writeString(this.type);
        dest.writeString(this.customerType);
        dest.writeString(this.remark);
        dest.writeString(this.reason);
        dest.writeString(this.status);
        dest.writeString(this.priority);
        dest.writeString(this.visitType);
        dest.writeString(this.invoiceType);
        dest.writeString(this.visitDate);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        Integer num3 = this.visitTotal;
        if (num3 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num3.intValue());
        }
        Integer num4 = this.visitDuration;
        if (num4 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num4.intValue());
        }
        Integer num5 = this.sample;
        if (num5 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num5.intValue());
        }
        Integer num6 = this.imageCount;
        if (num6 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num6.intValue());
        }
        dest.writeString(this.imageUrls);
        dest.writeString(this.invoiceImageUrls);
        dest.writeString(this.signatureUrl);
        List<Products> list = this.products;
        if (list == null) {
            dest.writeInt(0);
            return;
        }
        dest.writeInt(1);
        dest.writeInt(list.size());
        Iterator<Products> it = list.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(dest, flags);
        }
    }
}
