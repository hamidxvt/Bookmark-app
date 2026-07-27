package io.github.florent37.shapeofview.shapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import io.github.florent37.shapeofview.R;
import io.github.florent37.shapeofview.ShapeOfView;
import io.github.florent37.shapeofview.manager.ClipPathManager;

/* loaded from: classes17.dex */
public class DottedEdgesCutCornerView extends ShapeOfView {
    public static final int POSITION_BOTTOM = 1;
    public static final int POSITION_LEFT = 4;
    public static final int POSITION_NONE = 0;
    public static final int POSITION_RIGHT = 8;
    public static final int POSITION_TOP = 2;
    private float bottomLeftCutSize;
    private float bottomRightCutSize;
    private int dotEdgePosition;
    private float dotRadius;
    private float dotSpacing;
    private final RectF rectF;
    private float topLeftCutSize;
    private float topRightCutSize;

    public DottedEdgesCutCornerView(Context context) {
        super(context);
        this.rectF = new RectF();
        this.topLeftCutSize = 0.0f;
        this.topRightCutSize = 0.0f;
        this.bottomRightCutSize = 0.0f;
        this.bottomLeftCutSize = 0.0f;
        this.dotRadius = 0.0f;
        this.dotSpacing = 0.0f;
        init(context, null);
    }

    public DottedEdgesCutCornerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.rectF = new RectF();
        this.topLeftCutSize = 0.0f;
        this.topRightCutSize = 0.0f;
        this.bottomRightCutSize = 0.0f;
        this.bottomLeftCutSize = 0.0f;
        this.dotRadius = 0.0f;
        this.dotSpacing = 0.0f;
        init(context, attrs);
    }

    public DottedEdgesCutCornerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.rectF = new RectF();
        this.topLeftCutSize = 0.0f;
        this.topRightCutSize = 0.0f;
        this.bottomRightCutSize = 0.0f;
        this.bottomLeftCutSize = 0.0f;
        this.dotRadius = 0.0f;
        this.dotSpacing = 0.0f;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DottedEdgesCutCornerView);
            this.topLeftCutSize = attributes.getDimensionPixelSize(R.styleable.DottedEdgesCutCornerView_shape_dottedEdgesCutCorner_topLeftSize, (int) this.topLeftCutSize);
            this.topRightCutSize = attributes.getDimensionPixelSize(R.styleable.DottedEdgesCutCornerView_shape_dottedEdgesCutCorner_topRightSize, (int) this.topRightCutSize);
            this.bottomLeftCutSize = attributes.getDimensionPixelSize(R.styleable.DottedEdgesCutCornerView_shape_dottedEdgesCutCorner_bottomLeftSize, (int) this.bottomLeftCutSize);
            this.bottomRightCutSize = attributes.getDimensionPixelSize(R.styleable.DottedEdgesCutCornerView_shape_dottedEdgesCutCorner_bottomRightSize, (int) this.bottomRightCutSize);
            this.dotEdgePosition = attributes.getInteger(R.styleable.DottedEdgesCutCornerView_shape_edge_position, 0);
            this.dotRadius = attributes.getDimensionPixelSize(R.styleable.DottedEdgesCutCornerView_shape_dot_radius, (int) this.dotRadius);
            this.dotSpacing = attributes.getDimensionPixelSize(R.styleable.DottedEdgesCutCornerView_shape_dot_spacing, (int) this.dotSpacing);
            attributes.recycle();
        }
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.DottedEdgesCutCornerView.1
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public Path createClipPath(int width, int height) {
                DottedEdgesCutCornerView.this.rectF.set(0.0f, 0.0f, width, height);
                return DottedEdgesCutCornerView.this.generatePath(DottedEdgesCutCornerView.this.rectF, DottedEdgesCutCornerView.this.topLeftCutSize, DottedEdgesCutCornerView.this.topRightCutSize, DottedEdgesCutCornerView.this.bottomRightCutSize, DottedEdgesCutCornerView.this.bottomLeftCutSize);
            }

            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public boolean requiresBitmap() {
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Path generatePath(RectF rect, float topLeftDiameter, float topRightDiameter, float bottomRightDiameter, float bottomLeftDiameter) {
        Path path = new Path();
        float topLeftDiameter2 = topLeftDiameter < 0.0f ? 0.0f : topLeftDiameter;
        float topRightDiameter2 = topRightDiameter < 0.0f ? 0.0f : topRightDiameter;
        float bottomLeftDiameter2 = bottomLeftDiameter < 0.0f ? 0.0f : bottomLeftDiameter;
        float bottomRightDiameter2 = bottomRightDiameter >= 0.0f ? bottomRightDiameter : 0.0f;
        path.moveTo(rect.left + topLeftDiameter2, rect.top);
        if (containsFlag(2)) {
            int count = 1;
            int x = (int) (rect.left + topLeftDiameter2 + (this.dotSpacing * 1) + (this.dotRadius * 2.0f * (1 - 1)));
            while (x + this.dotSpacing + (this.dotRadius * 2.0f) <= rect.right - topRightDiameter2) {
                x = (int) (rect.left + topLeftDiameter2 + (this.dotSpacing * count) + (this.dotRadius * 2.0f * (count - 1)));
                path.lineTo(x, rect.top);
                path.quadTo(x + this.dotRadius, rect.top + this.dotRadius, x + (this.dotRadius * 2.0f), rect.top);
                count++;
            }
            path.lineTo(rect.right - topRightDiameter2, rect.top);
        } else {
            path.lineTo(rect.right - topRightDiameter2, rect.top);
        }
        path.lineTo(rect.right, rect.top + topRightDiameter2);
        if (containsFlag(8)) {
            path.lineTo(rect.right - this.dotRadius, rect.top + topRightDiameter2);
            path.lineTo(rect.right - this.dotRadius, rect.bottom - bottomRightDiameter2);
            path.lineTo(rect.right, rect.bottom - bottomRightDiameter2);
            int count2 = 1;
            int y = (int) (((rect.bottom - bottomRightDiameter2) - (this.dotSpacing * 1)) - ((this.dotRadius * 2.0f) * (1 - 1)));
            while ((y - this.dotSpacing) - (this.dotRadius * 2.0f) >= rect.top + topRightDiameter2) {
                y = (int) (((rect.bottom - bottomRightDiameter2) - (this.dotSpacing * count2)) - ((this.dotRadius * 2.0f) * (count2 - 1)));
                path.lineTo(rect.right, y);
                path.quadTo(rect.right - this.dotRadius, y - this.dotRadius, rect.right, y - (this.dotRadius * 2.0f));
                count2++;
            }
            path.lineTo(rect.right, rect.top + topRightDiameter2);
            path.lineTo(rect.right - this.dotRadius, rect.top + topRightDiameter2);
            path.lineTo(rect.right - this.dotRadius, rect.bottom - bottomRightDiameter2);
            path.lineTo(rect.right, rect.bottom - bottomRightDiameter2);
        } else {
            path.lineTo(rect.right, rect.bottom - bottomRightDiameter2);
        }
        path.lineTo(rect.right - bottomRightDiameter2, rect.bottom);
        if (containsFlag(1)) {
            int count3 = 1;
            int x2 = (int) (((rect.right - bottomRightDiameter2) - (this.dotSpacing * 1)) - ((this.dotRadius * 2.0f) * (1 - 1)));
            while ((x2 - this.dotSpacing) - (this.dotRadius * 2.0f) >= rect.left + bottomLeftDiameter2) {
                x2 = (int) (((rect.right - bottomRightDiameter2) - (this.dotSpacing * count3)) - ((this.dotRadius * 2.0f) * (count3 - 1)));
                path.lineTo(x2, rect.bottom);
                path.quadTo(x2 - this.dotRadius, rect.bottom - this.dotRadius, x2 - (this.dotRadius * 2.0f), rect.bottom);
                count3++;
            }
            path.lineTo(rect.left + bottomLeftDiameter2, rect.bottom);
        } else {
            path.lineTo(rect.left + bottomLeftDiameter2, rect.bottom);
        }
        path.lineTo(rect.left, rect.bottom - bottomLeftDiameter2);
        if (containsFlag(4)) {
            int count4 = 1;
            int y2 = (int) (((rect.bottom - bottomLeftDiameter2) - (this.dotSpacing * 1)) - ((this.dotRadius * 2.0f) * (1 - 1)));
            while ((y2 - this.dotSpacing) - (this.dotRadius * 2.0f) >= rect.top + topLeftDiameter2) {
                y2 = (int) (((rect.bottom - bottomLeftDiameter2) - (this.dotSpacing * count4)) - ((this.dotRadius * 2.0f) * (count4 - 1)));
                path.lineTo(rect.left, y2);
                path.quadTo(rect.left + this.dotRadius, y2 - this.dotRadius, rect.left, y2 - (this.dotRadius * 2.0f));
                count4++;
            }
            path.lineTo(rect.left, rect.top + topLeftDiameter2);
        } else {
            path.lineTo(rect.left, rect.top + topLeftDiameter2);
        }
        path.lineTo(rect.left + topLeftDiameter2, rect.top);
        path.close();
        return path;
    }

    private boolean containsFlag(int positionFlag) {
        return (this.dotEdgePosition | positionFlag) == this.dotEdgePosition;
    }

    public float getTopLeftCutSize() {
        return this.topLeftCutSize;
    }

    public void setTopLeftCutSize(float topLeftCutSize) {
        this.topLeftCutSize = topLeftCutSize;
        requiresShapeUpdate();
    }

    public float getTopLeftCutSizeDp() {
        return pxToDp(getTopLeftCutSize());
    }

    public void setTopLeftCutSizeDp(float topLeftCutSize) {
        setTopLeftCutSize(dpToPx(topLeftCutSize));
    }

    public float getTopRightCutSize() {
        return this.topRightCutSize;
    }

    public void setTopRightCutSize(float topRightCutSize) {
        this.topRightCutSize = topRightCutSize;
        requiresShapeUpdate();
    }

    public float getTopRightCutSizeDp() {
        return pxToDp(getTopRightCutSize());
    }

    public void setTopRightCutSizeDp(float topRightCutSize) {
        setTopRightCutSize(dpToPx(topRightCutSize));
    }

    public float getBottomRightCutSize() {
        return this.bottomRightCutSize;
    }

    public void setBottomRightCutSize(float bottomRightCutSize) {
        this.bottomRightCutSize = bottomRightCutSize;
        requiresShapeUpdate();
    }

    public float getBottomRightCutSizeDp() {
        return pxToDp(getBottomRightCutSize());
    }

    public void setBottomRightCutSizeDp(float bottomRightCutSize) {
        setBottomRightCutSize(dpToPx(bottomRightCutSize));
    }

    public float getBottomLeftCutSize() {
        return this.bottomLeftCutSize;
    }

    public void setBottomLeftCutSize(float bottomLeftCutSize) {
        this.bottomLeftCutSize = bottomLeftCutSize;
        requiresShapeUpdate();
    }

    public float getBottomLeftCutSizeDp() {
        return pxToDp(getBottomLeftCutSize());
    }

    public void setBottomLeftCutSizeDp(float bottomLeftCutSize) {
        setBottomLeftCutSize(dpToPx(bottomLeftCutSize));
    }

    public int getDotEdgePosition() {
        return this.dotEdgePosition;
    }

    public void addDotEdgePosition(int dotEdgePosition) {
        this.dotEdgePosition |= dotEdgePosition;
        requiresShapeUpdate();
    }

    public float getDotRadius() {
        return this.dotRadius;
    }

    public void setDotRadius(float dotRadius) {
        this.dotRadius = dotRadius;
        requiresShapeUpdate();
    }

    public float getDotRadiusDp() {
        return pxToDp(getDotRadius());
    }

    public void setDotRadiusDp(float dotRadius) {
        setDotRadius(dpToPx(dotRadius));
    }

    public float getDotSpacing() {
        return this.dotSpacing;
    }

    public void setDotSpacing(float dotSpacing) {
        this.dotSpacing = dotSpacing;
        requiresShapeUpdate();
    }

    public float getDotSpacingDp() {
        return pxToDp(this.dotSpacing);
    }

    public void setDotSpacingDp(float dotSpacing) {
        setDotRadius(dpToPx(dotSpacing));
    }
}
