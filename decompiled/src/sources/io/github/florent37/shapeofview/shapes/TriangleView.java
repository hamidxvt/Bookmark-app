package io.github.florent37.shapeofview.shapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;
import io.github.florent37.shapeofview.R;
import io.github.florent37.shapeofview.ShapeOfView;
import io.github.florent37.shapeofview.manager.ClipPathManager;

/* loaded from: classes17.dex */
public class TriangleView extends ShapeOfView {
    private float percentBottom;
    private float percentLeft;
    private float percentRight;

    public TriangleView(Context context) {
        super(context);
        this.percentBottom = 0.5f;
        this.percentLeft = 0.0f;
        this.percentRight = 0.0f;
        init(context, null);
    }

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.percentBottom = 0.5f;
        this.percentLeft = 0.0f;
        this.percentRight = 0.0f;
        init(context, attrs);
    }

    public TriangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.percentBottom = 0.5f;
        this.percentLeft = 0.0f;
        this.percentRight = 0.0f;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TriangleView);
            this.percentBottom = attributes.getFloat(R.styleable.TriangleView_shape_triangle_percentBottom, this.percentBottom);
            this.percentLeft = attributes.getFloat(R.styleable.TriangleView_shape_triangle_percentLeft, this.percentLeft);
            this.percentRight = attributes.getFloat(R.styleable.TriangleView_shape_triangle_percentRight, this.percentRight);
            attributes.recycle();
        }
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.TriangleView.1
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public Path createClipPath(int width, int height) {
                Path path = new Path();
                path.moveTo(0.0f, TriangleView.this.percentLeft * height);
                path.lineTo(TriangleView.this.percentBottom * width, height);
                path.lineTo(width, TriangleView.this.percentRight * height);
                path.close();
                return path;
            }

            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public boolean requiresBitmap() {
                return false;
            }
        });
    }

    public float getPercentBottom() {
        return this.percentBottom;
    }

    public void setPercentBottom(float percentBottom) {
        this.percentBottom = percentBottom;
        requiresShapeUpdate();
    }

    public float getPercentLeft() {
        return this.percentLeft;
    }

    public void setPercentLeft(float percentLeft) {
        this.percentLeft = percentLeft;
        requiresShapeUpdate();
    }

    public float getPercentRight() {
        return this.percentRight;
    }

    public void setPercentRight(float percentRight) {
        this.percentRight = percentRight;
        requiresShapeUpdate();
    }
}
