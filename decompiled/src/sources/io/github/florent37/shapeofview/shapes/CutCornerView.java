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
public class CutCornerView extends ShapeOfView {
    private float bottomLeftCutSizePx;
    private float bottomRightCutSizePx;
    private final RectF rectF;
    private float topLeftCutSizePx;
    private float topRightCutSizePx;

    public CutCornerView(Context context) {
        super(context);
        this.rectF = new RectF();
        this.topLeftCutSizePx = 0.0f;
        this.topRightCutSizePx = 0.0f;
        this.bottomRightCutSizePx = 0.0f;
        this.bottomLeftCutSizePx = 0.0f;
        init(context, null);
    }

    public CutCornerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.rectF = new RectF();
        this.topLeftCutSizePx = 0.0f;
        this.topRightCutSizePx = 0.0f;
        this.bottomRightCutSizePx = 0.0f;
        this.bottomLeftCutSizePx = 0.0f;
        init(context, attrs);
    }

    public CutCornerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.rectF = new RectF();
        this.topLeftCutSizePx = 0.0f;
        this.topRightCutSizePx = 0.0f;
        this.bottomRightCutSizePx = 0.0f;
        this.bottomLeftCutSizePx = 0.0f;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CutCornerView);
            this.topLeftCutSizePx = attributes.getDimensionPixelSize(R.styleable.CutCornerView_shape_cutCorner_topLeftSize, (int) this.topLeftCutSizePx);
            this.topRightCutSizePx = attributes.getDimensionPixelSize(R.styleable.CutCornerView_shape_cutCorner_topRightSize, (int) this.topRightCutSizePx);
            this.bottomLeftCutSizePx = attributes.getDimensionPixelSize(R.styleable.CutCornerView_shape_cutCorner_bottomLeftSize, (int) this.bottomLeftCutSizePx);
            this.bottomRightCutSizePx = attributes.getDimensionPixelSize(R.styleable.CutCornerView_shape_cutCorner_bottomRightSize, (int) this.bottomRightCutSizePx);
            attributes.recycle();
        }
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.CutCornerView.1
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public Path createClipPath(int width, int height) {
                CutCornerView.this.rectF.set(0.0f, 0.0f, width, height);
                return CutCornerView.this.generatePath(CutCornerView.this.rectF, CutCornerView.this.topLeftCutSizePx, CutCornerView.this.topRightCutSizePx, CutCornerView.this.bottomRightCutSizePx, CutCornerView.this.bottomLeftCutSizePx);
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
        path.lineTo(rect.right - topRightDiameter2, rect.top);
        path.lineTo(rect.right, rect.top + topRightDiameter2);
        path.lineTo(rect.right, rect.bottom - bottomRightDiameter2);
        path.lineTo(rect.right - bottomRightDiameter2, rect.bottom);
        path.lineTo(rect.left + bottomLeftDiameter2, rect.bottom);
        path.lineTo(rect.left, rect.bottom - bottomLeftDiameter2);
        path.lineTo(rect.left, rect.top + topLeftDiameter2);
        path.lineTo(rect.left + topLeftDiameter2, rect.top);
        path.close();
        return path;
    }

    public float getTopLeftCutSize() {
        return this.topLeftCutSizePx;
    }

    public void setTopLeftCutSize(float topLeftCutSize) {
        this.topLeftCutSizePx = topLeftCutSize;
        requiresShapeUpdate();
    }

    public float getTopLeftCutSizeDp() {
        return pxToDp(getTopLeftCutSize());
    }

    public void setTopLeftCutSizeDp(float topLeftCutSize) {
        setTopLeftCutSize(dpToPx(topLeftCutSize));
    }

    public float getTopRightCutSize() {
        return this.topRightCutSizePx;
    }

    public void setTopRightCutSize(float topRightCutSize) {
        this.topRightCutSizePx = topRightCutSize;
        requiresShapeUpdate();
    }

    public float getTopRightCutSizeDp() {
        return pxToDp(getTopRightCutSize());
    }

    public void setTopRightCutSizeDp(float topRightCutSize) {
        setTopRightCutSize(dpToPx(topRightCutSize));
    }

    public float getBottomRightCutSize() {
        return this.bottomRightCutSizePx;
    }

    public void setBottomRightCutSize(float bottomRightCutSize) {
        this.bottomRightCutSizePx = bottomRightCutSize;
        requiresShapeUpdate();
    }

    public float getBottomRightCutSizeDp() {
        return pxToDp(getBottomRightCutSize());
    }

    public void setBottomRightCutSizeDp(float bottomRightCutSize) {
        setBottomRightCutSize(dpToPx(bottomRightCutSize));
    }

    public float getBottomLeftCutSize() {
        return this.bottomLeftCutSizePx;
    }

    public void setBottomLeftCutSize(float bottomLeftCutSize) {
        this.bottomLeftCutSizePx = bottomLeftCutSize;
        requiresShapeUpdate();
    }

    public float getBottomLeftCutSizeDp() {
        return pxToDp(getBottomLeftCutSize());
    }

    public void setBottomLeftCutSizeDp(float bottomLeftCutSize) {
        setBottomLeftCutSize(dpToPx(bottomLeftCutSize));
    }
}
