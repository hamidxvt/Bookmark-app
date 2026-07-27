package com.ingenious.androidbookmarksalesupgrade.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SampleBookModel.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/SampleBookModel;", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "image", "gradeTitle", "subjectName", "totalQuantity", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getImage", "getGradeTitle", "getSubjectName", "getTotalQuantity", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class SampleBookModel {
    private final String gradeTitle;
    private final String image;
    private final String name;
    private final String subjectName;
    private final String totalQuantity;

    public static /* synthetic */ SampleBookModel copy$default(SampleBookModel sampleBookModel, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sampleBookModel.name;
        }
        if ((i & 2) != 0) {
            str2 = sampleBookModel.image;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = sampleBookModel.gradeTitle;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = sampleBookModel.subjectName;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = sampleBookModel.totalQuantity;
        }
        return sampleBookModel.copy(str, str6, str7, str8, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    /* renamed from: component3, reason: from getter */
    public final String getGradeTitle() {
        return this.gradeTitle;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSubjectName() {
        return this.subjectName;
    }

    /* renamed from: component5, reason: from getter */
    public final String getTotalQuantity() {
        return this.totalQuantity;
    }

    public final SampleBookModel copy(String name, String image, String gradeTitle, String subjectName, String totalQuantity) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(gradeTitle, "gradeTitle");
        Intrinsics.checkNotNullParameter(subjectName, "subjectName");
        Intrinsics.checkNotNullParameter(totalQuantity, "totalQuantity");
        return new SampleBookModel(name, image, gradeTitle, subjectName, totalQuantity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SampleBookModel)) {
            return false;
        }
        SampleBookModel sampleBookModel = (SampleBookModel) other;
        return Intrinsics.areEqual(this.name, sampleBookModel.name) && Intrinsics.areEqual(this.image, sampleBookModel.image) && Intrinsics.areEqual(this.gradeTitle, sampleBookModel.gradeTitle) && Intrinsics.areEqual(this.subjectName, sampleBookModel.subjectName) && Intrinsics.areEqual(this.totalQuantity, sampleBookModel.totalQuantity);
    }

    public int hashCode() {
        return (((((((this.name.hashCode() * 31) + this.image.hashCode()) * 31) + this.gradeTitle.hashCode()) * 31) + this.subjectName.hashCode()) * 31) + this.totalQuantity.hashCode();
    }

    public String toString() {
        return "SampleBookModel(name=" + this.name + ", image=" + this.image + ", gradeTitle=" + this.gradeTitle + ", subjectName=" + this.subjectName + ", totalQuantity=" + this.totalQuantity + ")";
    }

    public SampleBookModel(String name, String image, String gradeTitle, String subjectName, String totalQuantity) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(gradeTitle, "gradeTitle");
        Intrinsics.checkNotNullParameter(subjectName, "subjectName");
        Intrinsics.checkNotNullParameter(totalQuantity, "totalQuantity");
        this.name = name;
        this.image = image;
        this.gradeTitle = gradeTitle;
        this.subjectName = subjectName;
        this.totalQuantity = totalQuantity;
    }

    public final String getName() {
        return this.name;
    }

    public final String getImage() {
        return this.image;
    }

    public final String getGradeTitle() {
        return this.gradeTitle;
    }

    public final String getSubjectName() {
        return this.subjectName;
    }

    public final String getTotalQuantity() {
        return this.totalQuantity;
    }
}
