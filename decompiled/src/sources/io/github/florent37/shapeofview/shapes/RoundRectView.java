package io.github.florent37.shapeofview.shapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import io.github.florent37.shapeofview.R;
import io.github.florent37.shapeofview.ShapeOfView;
import io.github.florent37.shapeofview.manager.ClipPathManager;

/* loaded from: classes17.dex */
public class RoundRectView extends ShapeOfView {
    private int borderColor;
    private final Paint borderPaint;
    private final Path borderPath;
    private final RectF borderRectF;
    private float borderWidthPx;
    private float bottomLeftRadius;
    private float bottomRightRadius;
    private final RectF rectF;
    private float topLeftRadius;
    private float topRightRadius;

    public RoundRectView(Context context) {
        super(context);
        this.rectF = new RectF();
        this.borderPaint = new Paint(1);
        this.borderRectF = new RectF();
        this.borderPath = new Path();
        this.topLeftRadius = 0.0f;
        this.topRightRadius = 0.0f;
        this.bottomRightRadius = 0.0f;
        this.bottomLeftRadius = 0.0f;
        this.borderColor = -1;
        this.borderWidthPx = 0.0f;
        init(context, null);
    }

    public RoundRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.rectF = new RectF();
        this.borderPaint = new Paint(1);
        this.borderRectF = new RectF();
        this.borderPath = new Path();
        this.topLeftRadius = 0.0f;
        this.topRightRadius = 0.0f;
        this.bottomRightRadius = 0.0f;
        this.bottomLeftRadius = 0.0f;
        this.borderColor = -1;
        this.borderWidthPx = 0.0f;
        init(context, attrs);
    }

    public RoundRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.rectF = new RectF();
        this.borderPaint = new Paint(1);
        this.borderRectF = new RectF();
        this.borderPath = new Path();
        this.topLeftRadius = 0.0f;
        this.topRightRadius = 0.0f;
        this.bottomRightRadius = 0.0f;
        this.bottomLeftRadius = 0.0f;
        this.borderColor = -1;
        this.borderWidthPx = 0.0f;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundRectView);
            this.topLeftRadius = attributes.getDimensionPixelSize(R.styleable.RoundRectView_shape_roundRect_topLeftRadius, (int) this.topLeftRadius);
            this.topRightRadius = attributes.getDimensionPixelSize(R.styleable.RoundRectView_shape_roundRect_topRightRadius, (int) this.topRightRadius);
            this.bottomLeftRadius = attributes.getDimensionPixelSize(R.styleable.RoundRectView_shape_roundRect_bottomLeftRadius, (int) this.bottomLeftRadius);
            this.bottomRightRadius = attributes.getDimensionPixelSize(R.styleable.RoundRectView_shape_roundRect_bottomRightRadius, (int) this.bottomRightRadius);
            this.borderColor = attributes.getColor(R.styleable.RoundRectView_shape_roundRect_borderColor, this.borderColor);
            this.borderWidthPx = attributes.getDimensionPixelSize(R.styleable.RoundRectView_shape_roundRect_borderWidth, (int) this.borderWidthPx);
            attributes.recycle();
        }
        this.borderPaint.setStyle(Paint.Style.STROKE);
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.RoundRectView.1
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public Path createClipPath(int width, int height) {
                RoundRectView.this.rectF.set(0.0f, 0.0f, width, height);
                return RoundRectView.this.generatePath(RoundRectView.this.rectF, RoundRectView.this.limitSize(RoundRectView.this.topLeftRadius, width, height), RoundRectView.this.limitSize(RoundRectView.this.topRightRadius, width, height), RoundRectView.this.limitSize(RoundRectView.this.bottomRightRadius, width, height), RoundRectView.this.limitSize(RoundRectView.this.bottomLeftRadius, width, height));
            }

            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public boolean requiresBitmap() {
                return false;
            }
        });
    }

    protected float limitSize(float from, float width, float height) {
        return Math.min(from, Math.min(width, height));
    }

    @Override // io.github.florent37.shapeofview.ShapeOfView
    public void requiresShapeUpdate() {
        this.borderRectF.set(this.borderWidthPx / 2.0f, this.borderWidthPx / 2.0f, getWidth() - (this.borderWidthPx / 2.0f), getHeight() - (this.borderWidthPx / 2.0f));
        this.borderPath.set(generatePath(this.borderRectF, this.topLeftRadius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius));
        super.requiresShapeUpdate();
    }

    @Override // io.github.florent37.shapeofview.ShapeOfView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.borderWidthPx > 0.0f) {
            this.borderPaint.setStrokeWidth(this.borderWidthPx);
            this.borderPaint.setColor(this.borderColor);
            canvas.drawPath(this.borderPath, this.borderPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Path generatePath(RectF rect, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        return generatePath(false, rect, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
    }

    private Path generatePath(boolean useBezier, RectF rect, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        float f;
        Path path = new Path();
        float left = rect.left;
        float top = rect.top;
        float bottom = rect.bottom;
        float right = rect.right;
        float maxSize = Math.min(rect.width() / 2.0f, rect.height() / 2.0f);
        float topLeftRadiusAbs = Math.abs(topLeftRadius);
        float topRightRadiusAbs = Math.abs(topRightRadius);
        float bottomLeftRadiusAbs = Math.abs(bottomLeftRadius);
        float bottomRightRadiusAbs = Math.abs(bottomRightRadius);
        if (topLeftRadiusAbs > maxSize) {
            topLeftRadiusAbs = maxSize;
        }
        if (topRightRadiusAbs > maxSize) {
            topRightRadiusAbs = maxSize;
        }
        if (bottomLeftRadiusAbs > maxSize) {
            bottomLeftRadiusAbs = maxSize;
        }
        if (bottomRightRadiusAbs > maxSize) {
            bottomRightRadiusAbs = maxSize;
        }
        path.moveTo(left + topLeftRadiusAbs, top);
        path.lineTo(right - topRightRadiusAbs, top);
        if (useBezier) {
            path.quadTo(right, top, right, top + topRightRadiusAbs);
        } else {
            float arc = topRightRadius > 0.0f ? 90.0f : -270.0f;
            path.arcTo(new RectF(right - (topRightRadiusAbs * 2.0f), top, right, top + (topRightRadiusAbs * 2.0f)), -90.0f, arc);
        }
        path.lineTo(right, bottom - bottomRightRadiusAbs);
        if (!useBezier) {
            float arc2 = bottomRightRadiusAbs > 0.0f ? 90.0f : -270.0f;
            path.arcTo(new RectF(right - (bottomRightRadiusAbs * 2.0f), bottom - (bottomRightRadiusAbs * 2.0f), right, bottom), 0.0f, arc2);
        } else {
            path.quadTo(right, bottom, right - bottomRightRadiusAbs, bottom);
        }
        float arc3 = left + bottomLeftRadiusAbs;
        path.lineTo(arc3, bottom);
        if (useBezier) {
            path.quadTo(left, bottom, left, bottom - bottomLeftRadiusAbs);
            f = 90.0f;
        } else {
            float arc4 = bottomLeftRadiusAbs > 0.0f ? 90.0f : -270.0f;
            f = 90.0f;
            path.arcTo(new RectF(left, bottom - (bottomLeftRadiusAbs * 2.0f), left + (bottomLeftRadiusAbs * 2.0f), bottom), 90.0f, arc4);
        }
        float arc5 = top + topLeftRadiusAbs;
        path.lineTo(left, arc5);
        if (useBezier) {
            path.quadTo(left, top, left + topLeftRadiusAbs, top);
        } else {
            float arc6 = topLeftRadiusAbs > 0.0f ? f : -270.0f;
            path.arcTo(new RectF(left, top, (topLeftRadiusAbs * 2.0f) + left, (2.0f * topLeftRadiusAbs) + top), 180.0f, arc6);
        }
        path.close();
        return path;
    }

    public float getTopLeftRadius() {
        return this.topLeftRadius;
    }

    public void setTopLeftRadius(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        requiresShapeUpdate();
    }

    public float getTopLeftRadiusDp() {
        return pxToDp(getTopLeftRadius());
    }

    public void setTopLeftRadiusDp(float topLeftRadius) {
        setTopLeftRadius(dpToPx(topLeftRadius));
    }

    public float getTopRightRadius() {
        return this.topRightRadius;
    }

    public void setTopRightRadius(float topRightRadius) {
        this.topRightRadius = topRightRadius;
        requiresShapeUpdate();
    }

    public float getTopRightRadiusDp() {
        return pxToDp(getTopRightRadius());
    }

    public void setTopRightRadiusDp(float topRightRadius) {
        setTopRightRadius(dpToPx(topRightRadius));
    }

    public float getBottomRightRadius() {
        return this.bottomRightRadius;
    }

    public void setBottomRightRadius(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        requiresShapeUpdate();
    }

    public float getBottomRightRadiusDp() {
        return pxToDp(getBottomRightRadius());
    }

    public void setBottomRightRadiusDp(float bottomRightRadius) {
        setBottomRightRadius(dpToPx(bottomRightRadius));
    }

    public float getBottomLeftRadius() {
        return this.bottomLeftRadius;
    }

    public void setBottomLeftRadius(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        requiresShapeUpdate();
    }

    public float getBottomLeftRadiusDp() {
        return pxToDp(getBottomLeftRadius());
    }

    public void setBottomLeftRadiusDp(float bottomLeftRadius) {
        setBottomLeftRadius(dpToPx(bottomLeftRadius));
    }

    public float getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        requiresShapeUpdate();
    }

    public float getBorderWidth() {
        return this.borderWidthPx;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidthPx = borderWidth;
        requiresShapeUpdate();
    }

    public float getBorderWidthDp() {
        return pxToDp(getBorderWidth());
    }

    public void setBorderWidthDp(float borderWidth) {
        setBorderWidth(dpToPx(borderWidth));
    }
}
