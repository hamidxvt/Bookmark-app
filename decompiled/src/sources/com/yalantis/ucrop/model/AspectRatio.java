package com.yalantis.ucrop.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes17.dex */
public class AspectRatio implements Parcelable {
    public static final Parcelable.Creator<AspectRatio> CREATOR = new Parcelable.Creator<AspectRatio>() { // from class: com.yalantis.ucrop.model.AspectRatio.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AspectRatio createFromParcel(Parcel in) {
            return new AspectRatio(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AspectRatio[] newArray(int size) {
            return new AspectRatio[size];
        }
    };
    private final String mAspectRatioTitle;
    private final float mAspectRatioX;
    private final float mAspectRatioY;

    public AspectRatio(String aspectRatioTitle, float aspectRatioX, float aspectRatioY) {
        this.mAspectRatioTitle = aspectRatioTitle;
        this.mAspectRatioX = aspectRatioX;
        this.mAspectRatioY = aspectRatioY;
    }

    protected AspectRatio(Parcel in) {
        this.mAspectRatioTitle = in.readString();
        this.mAspectRatioX = in.readFloat();
        this.mAspectRatioY = in.readFloat();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mAspectRatioTitle);
        dest.writeFloat(this.mAspectRatioX);
        dest.writeFloat(this.mAspectRatioY);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAspectRatioTitle() {
        return this.mAspectRatioTitle;
    }

    public float getAspectRatioX() {
        return this.mAspectRatioX;
    }

    public float getAspectRatioY() {
        return this.mAspectRatioY;
    }
}
