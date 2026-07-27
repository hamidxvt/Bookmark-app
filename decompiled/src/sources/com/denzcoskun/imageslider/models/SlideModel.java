package com.denzcoskun.imageslider.models;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: SlideModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\u0018\u00002\u00020\u0001B)\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B)\b\u0016\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nB\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u000bB\u001b\b\u0016\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\fR\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015¨\u0006\u001c"}, d2 = {"Lcom/denzcoskun/imageslider/models/SlideModel;", "", "imageUrl", "", "title", "scaleType", "Lcom/denzcoskun/imageslider/constants/ScaleTypes;", "(Ljava/lang/String;Ljava/lang/String;Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "imagePath", "", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "(Ljava/lang/String;Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "(Ljava/lang/Integer;Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "getImagePath", "()Ljava/lang/Integer;", "setImagePath", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getImageUrl", "()Ljava/lang/String;", "setImageUrl", "(Ljava/lang/String;)V", "getScaleType", "()Lcom/denzcoskun/imageslider/constants/ScaleTypes;", "setScaleType", "(Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "getTitle", "setTitle", "imageslider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes16.dex */
public final class SlideModel {
    private Integer imagePath;
    private String imageUrl;
    private ScaleTypes scaleType;
    private String title;

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public final Integer getImagePath() {
        return this.imagePath;
    }

    public final void setImagePath(Integer num) {
        this.imagePath = num;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final ScaleTypes getScaleType() {
        return this.scaleType;
    }

    public final void setScaleType(ScaleTypes scaleTypes) {
        this.scaleType = scaleTypes;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ SlideModel(String str, String str2, ScaleTypes scaleTypes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, scaleTypes);
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            scaleTypes = null;
        }
    }

    public SlideModel(String imageUrl, String title, ScaleTypes scaleType) {
        this.imagePath = 0;
        this.imageUrl = imageUrl;
        this.title = title;
        this.scaleType = scaleType;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ SlideModel(Integer num, String str, ScaleTypes scaleTypes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, scaleTypes);
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            scaleTypes = null;
        }
    }

    public SlideModel(Integer imagePath, String title, ScaleTypes scaleType) {
        this.imagePath = 0;
        this.imagePath = imagePath;
        this.title = title;
        this.scaleType = scaleType;
    }

    public SlideModel(String imageUrl, ScaleTypes scaleType) {
        this.imagePath = 0;
        this.imageUrl = imageUrl;
        this.scaleType = scaleType;
    }

    public SlideModel(Integer imagePath, ScaleTypes scaleType) {
        this.imagePath = 0;
        this.imagePath = imagePath;
        this.scaleType = scaleType;
    }
}
