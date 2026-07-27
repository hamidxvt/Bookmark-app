package com.airbnb.lottie;

import android.graphics.Bitmap;

/* loaded from: classes.dex */
public class LottieImageAsset {
    private Bitmap bitmap;
    private final String dirName;
    private final String fileName;
    private final int height;
    private final String id;
    private final int width;

    public LottieImageAsset(int width, int height, String id, String fileName, String dirName) {
        this.width = width;
        this.height = height;
        this.id = id;
        this.fileName = fileName;
        this.dirName = dirName;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getId() {
        return this.id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getDirName() {
        return this.dirName;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public LottieImageAsset copyWithScale(float scale) {
        LottieImageAsset newAsset = new LottieImageAsset((int) (this.width * scale), (int) (this.height * scale), this.id, this.fileName, this.dirName);
        if (this.bitmap != null) {
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(this.bitmap, newAsset.width, newAsset.height, true);
            newAsset.setBitmap(scaledBitmap);
        }
        return newAsset;
    }

    public boolean hasBitmap() {
        return this.bitmap != null || (this.fileName.startsWith("data:") && this.fileName.indexOf("base64,") > 0);
    }
}
