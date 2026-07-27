package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdoptionDetailsData.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u00101\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0019\u00107\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010HÆ\u0003J\u0094\u0001\u00108\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010HÆ\u0001¢\u0006\u0002\u00109J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u0006HÖ\u0001J\t\u0010>\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0016R \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0014\"\u0004\b#\u0010\u0016R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0014\"\u0004\b'\u0010\u0016R \u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0014\"\u0004\b)\u0010\u0016R.\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006?"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionDetailsData;", "", "adoptionName", "", "date", "totalBooks", "", "totalQuantity", "addedBy", "notes", "selectionSummary", "grades", "subjects", "books", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooks;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;)V", "getAdoptionName", "()Ljava/lang/String;", "setAdoptionName", "(Ljava/lang/String;)V", "getDate", "setDate", "getTotalBooks", "()Ljava/lang/Integer;", "setTotalBooks", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTotalQuantity", "setTotalQuantity", "getAddedBy", "setAddedBy", "getNotes", "setNotes", "getSelectionSummary", "setSelectionSummary", "getGrades", "setGrades", "getSubjects", "setSubjects", "getBooks", "()Ljava/util/ArrayList;", "setBooks", "(Ljava/util/ArrayList;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionDetailsData;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class AdoptionDetailsData {

    @SerializedName("added_by")
    private String addedBy;

    @SerializedName("adoption_name")
    private String adoptionName;

    @SerializedName("books")
    private ArrayList<AdoptionBooks> books;

    @SerializedName("date")
    private String date;

    @SerializedName("grades")
    private String grades;

    @SerializedName("notes")
    private String notes;

    @SerializedName("selection_summary")
    private String selectionSummary;

    @SerializedName("subjects")
    private String subjects;

    @SerializedName("total_books")
    private Integer totalBooks;

    @SerializedName("total_quantity")
    private Integer totalQuantity;

    public AdoptionDetailsData() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAdoptionName() {
        return this.adoptionName;
    }

    public final ArrayList<AdoptionBooks> component10() {
        return this.books;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getTotalBooks() {
        return this.totalBooks;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getTotalQuantity() {
        return this.totalQuantity;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAddedBy() {
        return this.addedBy;
    }

    /* renamed from: component6, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    /* renamed from: component7, reason: from getter */
    public final String getSelectionSummary() {
        return this.selectionSummary;
    }

    /* renamed from: component8, reason: from getter */
    public final String getGrades() {
        return this.grades;
    }

    /* renamed from: component9, reason: from getter */
    public final String getSubjects() {
        return this.subjects;
    }

    public final AdoptionDetailsData copy(String adoptionName, String date, Integer totalBooks, Integer totalQuantity, String addedBy, String notes, String selectionSummary, String grades, String subjects, ArrayList<AdoptionBooks> books) {
        Intrinsics.checkNotNullParameter(books, "books");
        return new AdoptionDetailsData(adoptionName, date, totalBooks, totalQuantity, addedBy, notes, selectionSummary, grades, subjects, books);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdoptionDetailsData)) {
            return false;
        }
        AdoptionDetailsData adoptionDetailsData = (AdoptionDetailsData) other;
        return Intrinsics.areEqual(this.adoptionName, adoptionDetailsData.adoptionName) && Intrinsics.areEqual(this.date, adoptionDetailsData.date) && Intrinsics.areEqual(this.totalBooks, adoptionDetailsData.totalBooks) && Intrinsics.areEqual(this.totalQuantity, adoptionDetailsData.totalQuantity) && Intrinsics.areEqual(this.addedBy, adoptionDetailsData.addedBy) && Intrinsics.areEqual(this.notes, adoptionDetailsData.notes) && Intrinsics.areEqual(this.selectionSummary, adoptionDetailsData.selectionSummary) && Intrinsics.areEqual(this.grades, adoptionDetailsData.grades) && Intrinsics.areEqual(this.subjects, adoptionDetailsData.subjects) && Intrinsics.areEqual(this.books, adoptionDetailsData.books);
    }

    public int hashCode() {
        return ((((((((((((((((((this.adoptionName == null ? 0 : this.adoptionName.hashCode()) * 31) + (this.date == null ? 0 : this.date.hashCode())) * 31) + (this.totalBooks == null ? 0 : this.totalBooks.hashCode())) * 31) + (this.totalQuantity == null ? 0 : this.totalQuantity.hashCode())) * 31) + (this.addedBy == null ? 0 : this.addedBy.hashCode())) * 31) + (this.notes == null ? 0 : this.notes.hashCode())) * 31) + (this.selectionSummary == null ? 0 : this.selectionSummary.hashCode())) * 31) + (this.grades == null ? 0 : this.grades.hashCode())) * 31) + (this.subjects != null ? this.subjects.hashCode() : 0)) * 31) + this.books.hashCode();
    }

    public String toString() {
        return "AdoptionDetailsData(adoptionName=" + this.adoptionName + ", date=" + this.date + ", totalBooks=" + this.totalBooks + ", totalQuantity=" + this.totalQuantity + ", addedBy=" + this.addedBy + ", notes=" + this.notes + ", selectionSummary=" + this.selectionSummary + ", grades=" + this.grades + ", subjects=" + this.subjects + ", books=" + this.books + ")";
    }

    public AdoptionDetailsData(String adoptionName, String date, Integer totalBooks, Integer totalQuantity, String addedBy, String notes, String selectionSummary, String grades, String subjects, ArrayList<AdoptionBooks> books) {
        Intrinsics.checkNotNullParameter(books, "books");
        this.adoptionName = adoptionName;
        this.date = date;
        this.totalBooks = totalBooks;
        this.totalQuantity = totalQuantity;
        this.addedBy = addedBy;
        this.notes = notes;
        this.selectionSummary = selectionSummary;
        this.grades = grades;
        this.subjects = subjects;
        this.books = books;
    }

    public /* synthetic */ AdoptionDetailsData(String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, String str6, String str7, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) == 0 ? str7 : null, (i & 512) != 0 ? new ArrayList() : arrayList);
    }

    public final String getAdoptionName() {
        return this.adoptionName;
    }

    public final void setAdoptionName(String str) {
        this.adoptionName = str;
    }

    public final String getDate() {
        return this.date;
    }

    public final void setDate(String str) {
        this.date = str;
    }

    public final Integer getTotalBooks() {
        return this.totalBooks;
    }

    public final void setTotalBooks(Integer num) {
        this.totalBooks = num;
    }

    public final Integer getTotalQuantity() {
        return this.totalQuantity;
    }

    public final void setTotalQuantity(Integer num) {
        this.totalQuantity = num;
    }

    public final String getAddedBy() {
        return this.addedBy;
    }

    public final void setAddedBy(String str) {
        this.addedBy = str;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final void setNotes(String str) {
        this.notes = str;
    }

    public final String getSelectionSummary() {
        return this.selectionSummary;
    }

    public final void setSelectionSummary(String str) {
        this.selectionSummary = str;
    }

    public final String getGrades() {
        return this.grades;
    }

    public final void setGrades(String str) {
        this.grades = str;
    }

    public final String getSubjects() {
        return this.subjects;
    }

    public final void setSubjects(String str) {
        this.subjects = str;
    }

    public final ArrayList<AdoptionBooks> getBooks() {
        return this.books;
    }

    public final void setBooks(ArrayList<AdoptionBooks> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.books = arrayList;
    }
}
