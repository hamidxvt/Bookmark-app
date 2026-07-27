package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* loaded from: classes17.dex */
public class CropTransformation extends BitmapTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.CropTransformation.1";
    private static final int VERSION = 1;
    private CropType cropType;
    private int height;
    private int width;

    public enum CropType {
        TOP,
        CENTER,
        BOTTOM
    }

    public CropTransformation(int width, int height) {
        this(width, height, CropType.CENTER);
    }

    public CropTransformation(int width, int height, CropType cropType) {
        this.cropType = CropType.CENTER;
        this.width = width;
        this.height = height;
        this.cropType = cropType;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation
    protected Bitmap transform(Context context, BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        this.width = this.width == 0 ? toTransform.getWidth() : this.width;
        this.height = this.height == 0 ? toTransform.getHeight() : this.height;
        Bitmap.Config config = toTransform.getConfig() != null ? toTransform.getConfig() : Bitmap.Config.ARGB_8888;
        Bitmap bitmap = pool.get(this.width, this.height, config);
        bitmap.setHasAlpha(true);
        float scaleX = this.width / toTransform.getWidth();
        float scaleY = this.height / toTransform.getHeight();
        float scale = Math.max(scaleX, scaleY);
        float scaledWidth = toTransform.getWidth() * scale;
        float scaledHeight = toTransform.getHeight() * scale;
        float left = (this.width - scaledWidth) / 2.0f;
        float top = getTop(scaledHeight);
        RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);
        setCanvasBitmapDensity(toTransform, bitmap);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(toTransform, (Rect) null, targetRect, (Paint) null);
        return bitmap;
    }

    private float getTop(float scaledHeight) {
        switch (this.cropType) {
            case TOP:
                return 0.0f;
            case CENTER:
                return (this.height - scaledHeight) / 2.0f;
            case BOTTOM:
                return this.height - scaledHeight;
            default:
                return 0.0f;
        }
    }

    public String toString() {
        return "CropTransformation(width=" + this.width + ", height=" + this.height + ", cropType=" + this.cropType + ")";
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object o) {
        return (o instanceof CropTransformation) && ((CropTransformation) o).width == this.width && ((CropTransformation) o).height == this.height && ((CropTransformation) o).cropType == this.cropType;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return ID.hashCode() + (this.width * 100000) + (this.height * 1000) + (this.cropType.ordinal() * 10);
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update((ID + this.width + this.height + this.cropType).getBytes(CHARSET));
    }
}
