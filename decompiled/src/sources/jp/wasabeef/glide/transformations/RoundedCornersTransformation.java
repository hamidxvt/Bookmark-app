package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* loaded from: classes17.dex */
public class RoundedCornersTransformation extends BitmapTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.RoundedCornersTransformation.1";
    private static final int VERSION = 1;
    private final CornerType cornerType;
    private final int diameter;
    private final int margin;
    private final int radius;

    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    public RoundedCornersTransformation(int radius, int margin) {
        this(radius, margin, CornerType.ALL);
    }

    public RoundedCornersTransformation(int radius, int margin, CornerType cornerType) {
        this.radius = radius;
        this.diameter = this.radius * 2;
        this.margin = margin;
        this.cornerType = cornerType;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation
    protected Bitmap transform(Context context, BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();
        Bitmap bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setHasAlpha(true);
        setCanvasBitmapDensity(toTransform, bitmap);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        drawRoundRect(canvas, paint, width, height);
        return bitmap;
    }

    private void drawRoundRect(Canvas canvas, Paint paint, float width, float height) {
        float right = width - this.margin;
        float bottom = height - this.margin;
        switch (this.cornerType) {
            case ALL:
                canvas.drawRoundRect(new RectF(this.margin, this.margin, right, bottom), this.radius, this.radius, paint);
                break;
            case TOP_LEFT:
                drawTopLeftRoundRect(canvas, paint, right, bottom);
                break;
            case TOP_RIGHT:
                drawTopRightRoundRect(canvas, paint, right, bottom);
                break;
            case BOTTOM_LEFT:
                drawBottomLeftRoundRect(canvas, paint, right, bottom);
                break;
            case BOTTOM_RIGHT:
                drawBottomRightRoundRect(canvas, paint, right, bottom);
                break;
            case TOP:
                drawTopRoundRect(canvas, paint, right, bottom);
                break;
            case BOTTOM:
                drawBottomRoundRect(canvas, paint, right, bottom);
                break;
            case LEFT:
                drawLeftRoundRect(canvas, paint, right, bottom);
                break;
            case RIGHT:
                drawRightRoundRect(canvas, paint, right, bottom);
                break;
            case OTHER_TOP_LEFT:
                drawOtherTopLeftRoundRect(canvas, paint, right, bottom);
                break;
            case OTHER_TOP_RIGHT:
                drawOtherTopRightRoundRect(canvas, paint, right, bottom);
                break;
            case OTHER_BOTTOM_LEFT:
                drawOtherBottomLeftRoundRect(canvas, paint, right, bottom);
                break;
            case OTHER_BOTTOM_RIGHT:
                drawOtherBottomRightRoundRect(canvas, paint, right, bottom);
                break;
            case DIAGONAL_FROM_TOP_LEFT:
                drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom);
                break;
            case DIAGONAL_FROM_TOP_RIGHT:
                drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom);
                break;
            default:
                canvas.drawRoundRect(new RectF(this.margin, this.margin, right, bottom), this.radius, this.radius, paint);
                break;
        }
    }

    private void drawTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, this.margin, this.margin + this.diameter, this.margin + this.diameter), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin + this.radius, this.margin + this.radius, bottom), paint);
        canvas.drawRect(new RectF(this.margin + this.radius, this.margin, right, bottom), paint);
    }

    private void drawTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - this.diameter, this.margin, right, this.margin + this.diameter), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin, right - this.radius, bottom), paint);
        canvas.drawRect(new RectF(right - this.radius, this.margin + this.radius, right, bottom), paint);
    }

    private void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, bottom - this.diameter, this.margin + this.diameter, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin, this.margin + this.diameter, bottom - this.radius), paint);
        canvas.drawRect(new RectF(this.margin + this.radius, this.margin, right, bottom), paint);
    }

    private void drawBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - this.diameter, bottom - this.diameter, right, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin, right - this.radius, bottom), paint);
        canvas.drawRect(new RectF(right - this.radius, this.margin, right, bottom - this.radius), paint);
    }

    private void drawTopRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, this.margin, right, this.margin + this.diameter), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin + this.radius, right, bottom), paint);
    }

    private void drawBottomRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, bottom - this.diameter, right, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin, right, bottom - this.radius), paint);
    }

    private void drawLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, this.margin, this.margin + this.diameter, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin + this.radius, this.margin, right, bottom), paint);
    }

    private void drawRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - this.diameter, this.margin, right, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin, right - this.radius, bottom), paint);
    }

    private void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, bottom - this.diameter, right, bottom), this.radius, this.radius, paint);
        canvas.drawRoundRect(new RectF(right - this.diameter, this.margin, right, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin, right - this.radius, bottom - this.radius), paint);
    }

    private void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, this.margin, this.margin + this.diameter, bottom), this.radius, this.radius, paint);
        canvas.drawRoundRect(new RectF(this.margin, bottom - this.diameter, right, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin + this.radius, this.margin, right, bottom - this.radius), paint);
    }

    private void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, this.margin, right, this.margin + this.diameter), this.radius, this.radius, paint);
        canvas.drawRoundRect(new RectF(right - this.diameter, this.margin, right, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin + this.radius, right - this.radius, bottom), paint);
    }

    private void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, this.margin, right, this.margin + this.diameter), this.radius, this.radius, paint);
        canvas.drawRoundRect(new RectF(this.margin, this.margin, this.margin + this.diameter, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin + this.radius, this.margin + this.radius, right, bottom), paint);
    }

    private void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.margin, this.margin, this.margin + this.diameter, this.margin + this.diameter), this.radius, this.radius, paint);
        canvas.drawRoundRect(new RectF(right - this.diameter, bottom - this.diameter, right, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin + this.radius, right - this.radius, bottom), paint);
        canvas.drawRect(new RectF(this.margin + this.radius, this.margin, right, bottom - this.radius), paint);
    }

    private void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - this.diameter, this.margin, right, this.margin + this.diameter), this.radius, this.radius, paint);
        canvas.drawRoundRect(new RectF(this.margin, bottom - this.diameter, this.margin + this.diameter, bottom), this.radius, this.radius, paint);
        canvas.drawRect(new RectF(this.margin, this.margin, right - this.radius, bottom - this.radius), paint);
        canvas.drawRect(new RectF(this.margin + this.radius, this.margin + this.radius, right, bottom), paint);
    }

    public String toString() {
        return "RoundedTransformation(radius=" + this.radius + ", margin=" + this.margin + ", diameter=" + this.diameter + ", cornerType=" + this.cornerType.name() + ")";
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object o) {
        return (o instanceof RoundedCornersTransformation) && ((RoundedCornersTransformation) o).radius == this.radius && ((RoundedCornersTransformation) o).diameter == this.diameter && ((RoundedCornersTransformation) o).margin == this.margin && ((RoundedCornersTransformation) o).cornerType == this.cornerType;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return ID.hashCode() + (this.radius * 10000) + (this.diameter * 1000) + (this.margin * 100) + (this.cornerType.ordinal() * 10);
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update((ID + this.radius + this.diameter + this.margin + this.cornerType).getBytes(CHARSET));
    }
}
