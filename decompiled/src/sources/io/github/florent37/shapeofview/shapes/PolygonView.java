package io.github.florent37.shapeofview.shapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;
import com.github.mikephil.charting.utils.Utils;
import io.github.florent37.shapeofview.R;
import io.github.florent37.shapeofview.ShapeOfView;
import io.github.florent37.shapeofview.manager.ClipPathManager;

/* loaded from: classes17.dex */
public class PolygonView extends ShapeOfView {
    private int numberOfSides;

    public PolygonView(Context context) {
        super(context);
        this.numberOfSides = 4;
        init(context, null);
    }

    public PolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.numberOfSides = 4;
        init(context, attrs);
    }

    public PolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.numberOfSides = 4;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PolygonView);
            int sides = attributes.getInteger(R.styleable.PolygonView_shape_polygon_noOfSides, this.numberOfSides);
            this.numberOfSides = sides > 3 ? sides : this.numberOfSides;
            attributes.recycle();
        }
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.PolygonView.1
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public Path createClipPath(int width, int height) {
                float section = (float) (6.283185307179586d / PolygonView.this.numberOfSides);
                int polygonSize = Math.min(width, height);
                int radius = polygonSize / 2;
                int centerX = width / 2;
                int centerY = height / 2;
                Path polygonPath = new Path();
                polygonPath.moveTo(centerX + (radius * ((float) Math.cos(Utils.DOUBLE_EPSILON))), centerY + (radius * ((float) Math.sin(Utils.DOUBLE_EPSILON))));
                for (int i = 1; i < PolygonView.this.numberOfSides; i++) {
                    polygonPath.lineTo(centerX + (radius * ((float) Math.cos(i * section))), centerY + (radius * ((float) Math.sin(i * section))));
                }
                polygonPath.close();
                return polygonPath;
            }

            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public boolean requiresBitmap() {
                return true;
            }
        });
    }

    public int getNoOfSides() {
        return this.numberOfSides;
    }

    public void setNoOfSides(int numberOfSides) {
        this.numberOfSides = numberOfSides;
        requiresShapeUpdate();
    }
}
