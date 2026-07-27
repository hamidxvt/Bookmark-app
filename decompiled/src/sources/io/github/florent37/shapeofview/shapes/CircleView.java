package io.github.florent37.shapeofview.shapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import io.github.florent37.shapeofview.R;
import io.github.florent37.shapeofview.ShapeOfView;
import io.github.florent37.shapeofview.manager.ClipPathManager;

/* loaded from: classes17.dex */
public class CircleView extends ShapeOfView {
    private int borderColor;
    private final Paint borderPaint;
    private float borderWidthPx;

    public CircleView(Context context) {
        super(context);
        this.borderWidthPx = 0.0f;
        this.borderColor = -1;
        this.borderPaint = new Paint(1);
        init(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.borderWidthPx = 0.0f;
        this.borderColor = -1;
        this.borderPaint = new Paint(1);
        init(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.borderWidthPx = 0.0f;
        this.borderColor = -1;
        this.borderPaint = new Paint(1);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
            this.borderWidthPx = attributes.getDimensionPixelSize(R.styleable.CircleView_shape_circle_borderWidth, (int) this.borderWidthPx);
            this.borderColor = attributes.getColor(R.styleable.CircleView_shape_circle_borderColor, this.borderColor);
            attributes.recycle();
        }
        this.borderPaint.setAntiAlias(true);
        this.borderPaint.setStyle(Paint.Style.STROKE);
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.CircleView.1
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public Path createClipPath(int width, int height) {
                Path path = new Path();
                path.addCircle(width / 2.0f, height / 2.0f, Math.min(width / 2.0f, height / 2.0f), Path.Direction.CW);
                return path;
            }

            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public boolean requiresBitmap() {
                return false;
            }
        });
    }

    @Override // io.github.florent37.shapeofview.ShapeOfView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.borderWidthPx > 0.0f) {
            this.borderPaint.setStrokeWidth(this.borderWidthPx);
            this.borderPaint.setColor(this.borderColor);
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, Math.min((getWidth() - this.borderWidthPx) / 2.0f, (getHeight() - this.borderWidthPx) / 2.0f), this.borderPaint);
        }
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidthPx = borderWidth;
        requiresShapeUpdate();
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        requiresShapeUpdate();
    }

    public void setBorderWidthDp(float borderWidth) {
        setBorderWidth(dpToPx(borderWidth));
    }

    public float getBorderWidth() {
        return this.borderWidthPx;
    }

    public float getBorderWidthDp() {
        return pxToDp(getBorderWidth());
    }

    public int getBorderColor() {
        return this.borderColor;
    }
}
