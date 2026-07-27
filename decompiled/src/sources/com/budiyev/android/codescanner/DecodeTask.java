package com.budiyev.android.codescanner;

import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;

/* loaded from: classes.dex */
final class DecodeTask {
    private final byte[] mImage;
    private final Point mImageSize;
    private final int mOrientation;
    private final Point mPreviewSize;
    private final boolean mReverseHorizontal;
    private final Rect mViewFrameRect;
    private final Point mViewSize;

    public DecodeTask(byte[] image, Point imageSize, Point previewSize, Point viewSize, Rect viewFrameRect, int orientation, boolean reverseHorizontal) {
        this.mImage = image;
        this.mImageSize = imageSize;
        this.mPreviewSize = previewSize;
        this.mViewSize = viewSize;
        this.mViewFrameRect = viewFrameRect;
        this.mOrientation = orientation;
        this.mReverseHorizontal = reverseHorizontal;
    }

    public Result decode(MultiFormatReader reader) throws ReaderException {
        int imageWidth = this.mImageSize.getX();
        int imageHeight = this.mImageSize.getY();
        int orientation = this.mOrientation;
        byte[] image = Utils.rotateYuv(this.mImage, imageWidth, imageHeight, orientation);
        if (orientation == 90 || orientation == 270) {
            imageWidth = imageHeight;
            imageHeight = imageWidth;
        }
        Rect frameRect = Utils.getImageFrameRect(imageWidth, imageHeight, this.mViewFrameRect, this.mPreviewSize, this.mViewSize);
        int frameWidth = frameRect.getWidth();
        int frameHeight = frameRect.getHeight();
        if (frameWidth >= 1 && frameHeight >= 1) {
            return Utils.decodeLuminanceSource(reader, new PlanarYUVLuminanceSource(image, imageWidth, imageHeight, frameRect.getLeft(), frameRect.getTop(), frameWidth, frameHeight, this.mReverseHorizontal));
        }
        return null;
    }
}
