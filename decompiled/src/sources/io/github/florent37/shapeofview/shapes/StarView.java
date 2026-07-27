package io.github.florent37.shapeofview.shapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;
import io.github.florent37.shapeofview.R;
import io.github.florent37.shapeofview.ShapeOfView;
import io.github.florent37.shapeofview.manager.ClipPathManager;

/* loaded from: classes17.dex */
public class StarView extends ShapeOfView {
    private int noOfPoints;

    public StarView(Context context) {
        super(context);
        this.noOfPoints = 5;
        init(context, null);
    }

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.noOfPoints = 5;
        init(context, attrs);
    }

    public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.noOfPoints = 5;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.StarView);
            int points = attributes.getInteger(R.styleable.StarView_shape_star_noOfPoints, this.noOfPoints);
            this.noOfPoints = points > 2 ? points : this.noOfPoints;
            attributes.recycle();
        }
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.StarView.1
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public Path createClipPath(int width, int height) {
                int vertices = StarView.this.noOfPoints * 2;
                float alpha = 6.2831855f / vertices;
                int radius = (height <= width ? height : width) / 2;
                float centerX = width / 2;
                float centerY = height / 2;
                Path path = new Path();
                for (int i = vertices + 1; i != 0; i--) {
                    float r = (((i % 2) + 1) * radius) / 2;
                    double omega = i * alpha;
                    path.lineTo(((float) (r * Math.sin(omega))) + centerX, ((float) (r * Math.cos(omega))) + centerY);
                }
                path.close();
                return path;
            }

            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public boolean requiresBitmap() {
                return true;
            }
        });
    }

    public void setNoOfPoints(int noOfPoints) {
        this.noOfPoints = noOfPoints;
        requiresShapeUpdate();
    }

    public int getNoOfPoints() {
        return this.noOfPoints;
    }
}
