package com.ingenious.androidbookmarksalesupgrade.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BookModel.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0006\u0010\u0015\u001a\u00020\u0005J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006!"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/SegmentModel;", "Landroid/os/Parcelable;", "segment", "", "totalBooksCount", "", "books", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", "<init>", "(Ljava/lang/String;ILjava/util/List;)V", "getSegment", "()Ljava/lang/String;", "getTotalBooksCount", "()I", "getBooks", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class SegmentModel implements Parcelable {
    public static final Parcelable.Creator<SegmentModel> CREATOR = new Creator();
    private final List<BookModel> books;
    private final String segment;
    private final int totalBooksCount;

    /* compiled from: BookModel.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Creator implements Parcelable.Creator<SegmentModel> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SegmentModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt2);
            for (int i = 0; i != readInt2; i++) {
                arrayList.add(BookModel.CREATOR.createFromParcel(parcel));
            }
            return new SegmentModel(readString, readInt, arrayList);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SegmentModel[] newArray(int i) {
            return new SegmentModel[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SegmentModel copy$default(SegmentModel segmentModel, String str, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = segmentModel.segment;
        }
        if ((i2 & 2) != 0) {
            i = segmentModel.totalBooksCount;
        }
        if ((i2 & 4) != 0) {
            list = segmentModel.books;
        }
        return segmentModel.copy(str, i, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSegment() {
        return this.segment;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTotalBooksCount() {
        return this.totalBooksCount;
    }

    public final List<BookModel> component3() {
        return this.books;
    }

    public final SegmentModel copy(String segment, int totalBooksCount, List<BookModel> books) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        Intrinsics.checkNotNullParameter(books, "books");
        return new SegmentModel(segment, totalBooksCount, books);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SegmentModel)) {
            return false;
        }
        SegmentModel segmentModel = (SegmentModel) other;
        return Intrinsics.areEqual(this.segment, segmentModel.segment) && this.totalBooksCount == segmentModel.totalBooksCount && Intrinsics.areEqual(this.books, segmentModel.books);
    }

    public int hashCode() {
        return (((this.segment.hashCode() * 31) + Integer.hashCode(this.totalBooksCount)) * 31) + this.books.hashCode();
    }

    public String toString() {
        return "SegmentModel(segment=" + this.segment + ", totalBooksCount=" + this.totalBooksCount + ", books=" + this.books + ")";
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public SegmentModel(String segment, int totalBooksCount, List<BookModel> books) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        Intrinsics.checkNotNullParameter(books, "books");
        this.segment = segment;
        this.totalBooksCount = totalBooksCount;
        this.books = books;
    }

    public final String getSegment() {
        return this.segment;
    }

    public final int getTotalBooksCount() {
        return this.totalBooksCount;
    }

    public final List<BookModel> getBooks() {
        return this.books;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.segment);
        dest.writeInt(this.totalBooksCount);
        List<BookModel> list = this.books;
        dest.writeInt(list.size());
        Iterator<BookModel> it = list.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(dest, flags);
        }
    }
}
