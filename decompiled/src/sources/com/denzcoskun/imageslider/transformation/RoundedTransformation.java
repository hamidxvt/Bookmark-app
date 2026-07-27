package com.denzcoskun.imageslider.transformation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RoundedTransformation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001*B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\nH\u0002J(\u0010\"\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010#\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010$\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J\b\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(H\u0016R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/denzcoskun/imageslider/transformation/RoundedTransformation;", "Lcom/squareup/picasso/Transformation;", "radius", "", "margin", "cornerType", "Lcom/denzcoskun/imageslider/transformation/RoundedTransformation$CornerType;", "(IILcom/denzcoskun/imageslider/transformation/RoundedTransformation$CornerType;)V", "mCornerType", "mDiameter", "", "mMargin", "mRadius", "drawBottomLeftRoundRect", "", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", "right", "bottom", "drawBottomRightRoundRect", "drawBottomRoundRect", "drawDiagonalFromTopLeftRoundRect", "drawDiagonalFromTopRightRoundRect", "drawLeftRoundRect", "drawOtherBottomLeftRoundRect", "drawOtherBottomRightRoundRect", "drawOtherTopLeftRoundRect", "drawOtherTopRightRoundRect", "drawRightRoundRect", "drawRoundRect", "width", "height", "drawTopLeftRoundRect", "drawTopRightRoundRect", "drawTopRoundRect", "key", "", "transform", "Landroid/graphics/Bitmap;", "source", "CornerType", "imageslider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes16.dex */
public final class RoundedTransformation implements Transformation {
    private CornerType mCornerType;
    private float mDiameter;
    private float mMargin;
    private float mRadius;

    /* compiled from: RoundedTransformation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0011\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/denzcoskun/imageslider/transformation/RoundedTransformation$CornerType;", "", "(Ljava/lang/String;I)V", "ALL", "TOP_LEFT", "TOP_RIGHT", "BOTTOM_LEFT", "BOTTOM_RIGHT", "TOP", "BOTTOM", "LEFT", "RIGHT", "OTHER_TOP_LEFT", "OTHER_TOP_RIGHT", "OTHER_BOTTOM_LEFT", "OTHER_BOTTOM_RIGHT", "DIAGONAL_FROM_TOP_LEFT", "DIAGONAL_FROM_TOP_RIGHT", "imageslider_release"}, k = 1, mv = {1, 1, 16})
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

    public RoundedTransformation(int i, int i2) {
        this(i, i2, null, 4, null);
    }

    public RoundedTransformation(int radius, int margin, CornerType cornerType) {
        Intrinsics.checkParameterIsNotNull(cornerType, "cornerType");
        this.mRadius = radius;
        this.mDiameter = radius * 2;
        this.mMargin = margin;
        this.mCornerType = cornerType;
    }

    public /* synthetic */ RoundedTransformation(int i, int i2, CornerType cornerType, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? CornerType.ALL : cornerType);
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        drawRoundRect(canvas, paint, width, height);
        source.recycle();
        Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
        return bitmap;
    }

    private final void drawRoundRect(Canvas canvas, Paint paint, float width, float height) {
        float right = width - this.mMargin;
        float bottom = height - this.mMargin;
        switch (this.mCornerType) {
            case ALL:
                canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, right, bottom), this.mRadius, this.mRadius, paint);
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
                canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, right, bottom), this.mRadius, this.mRadius, paint);
                break;
        }
    }

    private final void drawTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.mRadius, this.mMargin + this.mRadius, bottom), paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin, right, bottom), paint);
    }

    private final void drawTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - this.mDiameter, this.mMargin, right, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, right - this.mRadius, bottom), paint);
        canvas.drawRect(new RectF(right - this.mRadius, this.mMargin + this.mRadius, right, bottom), paint);
    }

    private final void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, bottom - this.mDiameter, this.mMargin + this.mDiameter, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, bottom - this.mRadius), paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin, right, bottom), paint);
    }

    private final void drawBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - this.mDiameter, bottom - this.mDiameter, right, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, right - this.mRadius, bottom), paint);
        canvas.drawRect(new RectF(right - this.mRadius, this.mMargin, right, bottom - this.mRadius), paint);
    }

    private final void drawTopRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, right, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.mRadius, right, bottom), paint);
    }

    private final void drawBottomRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, bottom - this.mDiameter, right, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, right, bottom - this.mRadius), paint);
    }

    private final void drawLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin, right, bottom), paint);
    }

    private final void drawRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - this.mDiameter, this.mMargin, right, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, right - this.mRadius, bottom), paint);
    }

    private final void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, bottom - this.mDiameter, right, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(right - this.mDiameter, this.mMargin, right, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, right - this.mRadius, bottom - this.mRadius), paint);
    }

    private final void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(this.mMargin, bottom - this.mDiameter, right, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin, right, bottom - this.mRadius), paint);
    }

    private final void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, right, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(right - this.mDiameter, this.mMargin, right, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.mRadius, right - this.mRadius, bottom), paint);
    }

    private final void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, right, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin + this.mRadius, right, bottom), paint);
    }

    private final void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.mDiameter, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(right - this.mDiameter, bottom - this.mDiameter, right, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.mRadius, right - this.mDiameter, bottom), paint);
        canvas.drawRect(new RectF(this.mMargin + this.mDiameter, this.mMargin, right, bottom - this.mRadius), paint);
    }

    private final void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - this.mDiameter, this.mMargin, right, this.mMargin + this.mDiameter), this.mRadius, this.mRadius, paint);
        canvas.drawRoundRect(new RectF(this.mMargin, bottom - this.mDiameter, this.mMargin + this.mDiameter, bottom), this.mRadius, this.mRadius, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, right - this.mRadius, bottom - this.mRadius), paint);
        canvas.drawRect(new RectF(this.mMargin + this.mRadius, this.mMargin + this.mRadius, right, bottom), paint);
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "RoundedTransformation(radius=" + this.mRadius + ", margin=" + this.mMargin + ", diameter=" + this.mDiameter + ", cornerType=" + this.mCornerType.name() + ")";
    }
}
