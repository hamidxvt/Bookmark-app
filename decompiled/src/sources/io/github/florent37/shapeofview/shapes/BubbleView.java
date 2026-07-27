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
public class BubbleView extends ShapeOfView {
    public static final int POSITION_BOTTOM = 1;
    public static final int POSITION_LEFT = 3;
    public static final int POSITION_RIGHT = 4;
    public static final int POSITION_TOP = 2;
    private float arrowHeightPx;
    private float arrowWidthPx;
    private float borderRadiusPx;
    private float defPositionPer;
    private int position;
    private float positionPer;

    public @interface Position {
    }

    public BubbleView(Context context) {
        super(context);
        this.position = 1;
        this.defPositionPer = 0.5f;
        init(context, null);
    }

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.position = 1;
        this.defPositionPer = 0.5f;
        init(context, attrs);
    }

    public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.position = 1;
        this.defPositionPer = 0.5f;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.BubbleView);
            this.borderRadiusPx = attributes.getDimensionPixelSize(R.styleable.BubbleView_shape_bubble_borderRadius, (int) dpToPx(10.0f));
            this.position = attributes.getInteger(R.styleable.BubbleView_shape_bubble_arrowPosition, this.position);
            this.arrowHeightPx = attributes.getDimensionPixelSize(R.styleable.BubbleView_shape_bubble_arrowHeight, (int) dpToPx(10.0f));
            this.arrowWidthPx = attributes.getDimensionPixelSize(R.styleable.BubbleView_shape_bubble_arrowWidth, (int) dpToPx(10.0f));
            this.positionPer = attributes.getFloat(R.styleable.BubbleView_arrow_posititon_percent, this.defPositionPer);
            attributes.recycle();
        }
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.BubbleView.1
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public Path createClipPath(int width, int height) {
                RectF myRect = new RectF(0.0f, 0.0f, width, height);
                return BubbleView.this.drawBubble(myRect, BubbleView.this.borderRadiusPx, BubbleView.this.borderRadiusPx, BubbleView.this.borderRadiusPx, BubbleView.this.borderRadiusPx);
            }

            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public boolean requiresBitmap() {
                return false;
            }
        });
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
        requiresShapeUpdate();
    }

    public float getBorderRadius() {
        return this.borderRadiusPx;
    }

    public float getBorderRadiusDp() {
        return pxToDp(getBorderRadius());
    }

    public void setBorderRadius(float borderRadius) {
        this.borderRadiusPx = borderRadius;
        requiresShapeUpdate();
    }

    public void setBorderRadiusDp(float borderRadius) {
        this.borderRadiusPx = dpToPx(borderRadius);
        requiresShapeUpdate();
    }

    public float getArrowHeight() {
        return this.arrowHeightPx;
    }

    public float getArrowHeightDp() {
        return pxToDp(getArrowHeight());
    }

    public void setArrowHeight(float arrowHeight) {
        this.arrowHeightPx = arrowHeight;
        requiresShapeUpdate();
    }

    public void setArrowHeightDp(float arrowHeight) {
        setArrowHeight(dpToPx(arrowHeight));
    }

    public float getArrowWidth() {
        return this.arrowWidthPx;
    }

    public void setArrowWidth(float arrowWidth) {
        this.arrowWidthPx = arrowWidth;
        requiresShapeUpdate();
    }

    public void setArrowWidthDp(float arrowWidth) {
        setArrowWidth(dpToPx(arrowWidth));
    }

    public void setPositionPer(float positionPer) {
        this.positionPer = positionPer;
        requiresShapeUpdate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Path drawBubble(RectF myRect, float topLeftDiameter, float topRightDiameter, float bottomRightDiameter, float bottomLeftDiameter) {
        float spacingLeft;
        float spacingTop;
        float spacingRight;
        Path path = new Path();
        float spacingBottom = 0.0f;
        float topLeftDiameter2 = topLeftDiameter < 0.0f ? 0.0f : topLeftDiameter;
        float topRightDiameter2 = topRightDiameter < 0.0f ? 0.0f : topRightDiameter;
        float bottomLeftDiameter2 = bottomLeftDiameter < 0.0f ? 0.0f : bottomLeftDiameter;
        float bottomRightDiameter2 = bottomRightDiameter < 0.0f ? 0.0f : bottomRightDiameter;
        if (this.position == 3) {
            spacingLeft = this.arrowHeightPx;
        } else {
            spacingLeft = 0.0f;
        }
        if (this.position == 2) {
            spacingTop = this.arrowHeightPx;
        } else {
            spacingTop = 0.0f;
        }
        if (this.position == 4) {
            spacingRight = this.arrowHeightPx;
        } else {
            spacingRight = 0.0f;
        }
        if (this.position == 1) {
            spacingBottom = this.arrowHeightPx;
        }
        float left = myRect.left + spacingLeft;
        float top = myRect.top + spacingTop;
        float right = myRect.right - spacingRight;
        float bottom = myRect.bottom - spacingBottom;
        float f = myRect.left;
        float spacingBottom2 = myRect.right;
        float centerX = (f + spacingBottom2) * this.positionPer;
        path.moveTo(left + (topLeftDiameter2 / 2.0f), top);
        if (this.position == 2) {
            path.lineTo(centerX - this.arrowWidthPx, top);
            path.lineTo(centerX, myRect.top);
            path.lineTo(this.arrowWidthPx + centerX, top);
        }
        path.lineTo(right - (topRightDiameter2 / 2.0f), top);
        path.quadTo(right, top, right, (topRightDiameter2 / 2.0f) + top);
        if (this.position == 4) {
            path.lineTo(right, (bottom - ((1.0f - this.positionPer) * bottom)) - this.arrowWidthPx);
            path.lineTo(myRect.right, bottom - ((1.0f - this.positionPer) * bottom));
            path.lineTo(right, (bottom - ((1.0f - this.positionPer) * bottom)) + this.arrowWidthPx);
        }
        path.lineTo(right, bottom - (bottomRightDiameter2 / 2.0f));
        path.quadTo(right, bottom, right - (bottomRightDiameter2 / 2.0f), bottom);
        if (this.position == 1) {
            path.lineTo(this.arrowWidthPx + centerX, bottom);
            path.lineTo(centerX, myRect.bottom);
            path.lineTo(centerX - this.arrowWidthPx, bottom);
        }
        path.lineTo((bottomLeftDiameter2 / 2.0f) + left, bottom);
        path.quadTo(left, bottom, left, bottom - (bottomLeftDiameter2 / 2.0f));
        if (this.position == 3) {
            path.lineTo(left, (bottom - ((1.0f - this.positionPer) * bottom)) + this.arrowWidthPx);
            path.lineTo(myRect.left, bottom - ((1.0f - this.positionPer) * bottom));
            path.lineTo(left, (bottom - ((1.0f - this.positionPer) * bottom)) - this.arrowWidthPx);
        }
        path.lineTo(left, (topLeftDiameter2 / 2.0f) + top);
        path.quadTo(left, top, (topLeftDiameter2 / 2.0f) + left, top);
        path.close();
        return path;
    }
}
