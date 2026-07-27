package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdoptionBooks.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003JJ\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0007HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\"\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000e¨\u0006&"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooks;", "", "bookName", "", "grade", "subject", FirebaseAnalytics.Param.QUANTITY, "", "image", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getBookName", "()Ljava/lang/String;", "setBookName", "(Ljava/lang/String;)V", "getGrade", "setGrade", "getSubject", "setSubject", "getQuantity", "()Ljava/lang/Integer;", "setQuantity", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getImage", "setImage", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooks;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class AdoptionBooks {

    @SerializedName("book_name")
    private String bookName;

    @SerializedName("grade")
    private String grade;

    @SerializedName("image")
    private String image;

    @SerializedName(FirebaseAnalytics.Param.QUANTITY)
    private Integer quantity;

    @SerializedName("subject")
    private String subject;

    public AdoptionBooks() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ AdoptionBooks copy$default(AdoptionBooks adoptionBooks, String str, String str2, String str3, Integer num, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = adoptionBooks.bookName;
        }
        if ((i & 2) != 0) {
            str2 = adoptionBooks.grade;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = adoptionBooks.subject;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            num = adoptionBooks.quantity;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            str4 = adoptionBooks.image;
        }
        return adoptionBooks.copy(str, str5, str6, num2, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getBookName() {
        return this.bookName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getGrade() {
        return this.grade;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSubject() {
        return this.subject;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getQuantity() {
        return this.quantity;
    }

    /* renamed from: component5, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    public final AdoptionBooks copy(String bookName, String grade, String subject, Integer quantity, String image) {
        return new AdoptionBooks(bookName, grade, subject, quantity, image);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdoptionBooks)) {
            return false;
        }
        AdoptionBooks adoptionBooks = (AdoptionBooks) other;
        return Intrinsics.areEqual(this.bookName, adoptionBooks.bookName) && Intrinsics.areEqual(this.grade, adoptionBooks.grade) && Intrinsics.areEqual(this.subject, adoptionBooks.subject) && Intrinsics.areEqual(this.quantity, adoptionBooks.quantity) && Intrinsics.areEqual(this.image, adoptionBooks.image);
    }

    public int hashCode() {
        return ((((((((this.bookName == null ? 0 : this.bookName.hashCode()) * 31) + (this.grade == null ? 0 : this.grade.hashCode())) * 31) + (this.subject == null ? 0 : this.subject.hashCode())) * 31) + (this.quantity == null ? 0 : this.quantity.hashCode())) * 31) + (this.image != null ? this.image.hashCode() : 0);
    }

    public String toString() {
        return "AdoptionBooks(bookName=" + this.bookName + ", grade=" + this.grade + ", subject=" + this.subject + ", quantity=" + this.quantity + ", image=" + this.image + ")";
    }

    public AdoptionBooks(String bookName, String grade, String subject, Integer quantity, String image) {
        this.bookName = bookName;
        this.grade = grade;
        this.subject = subject;
        this.quantity = quantity;
        this.image = image;
    }

    public /* synthetic */ AdoptionBooks(String str, String str2, String str3, Integer num, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : str4);
    }

    public final String getBookName() {
        return this.bookName;
    }

    public final void setBookName(String str) {
        this.bookName = str;
    }

    public final String getGrade() {
        return this.grade;
    }

    public final void setGrade(String str) {
        this.grade = str;
    }

    public final String getSubject() {
        return this.subject;
    }

    public final void setSubject(String str) {
        this.subject = str;
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
