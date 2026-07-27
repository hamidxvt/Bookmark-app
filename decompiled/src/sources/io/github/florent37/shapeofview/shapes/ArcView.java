package io.github.florent37.shapeofview.shapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;
import io.github.florent37.shapeofview.R;
import io.github.florent37.shapeofview.ShapeOfView;
import io.github.florent37.shapeofview.manager.ClipPathManager;

/* loaded from: classes17.dex */
public class ArcView extends ShapeOfView {
    public static final int CROP_INSIDE = 1;
    public static final int CROP_OUTSIDE = 2;
    public static final int POSITION_BOTTOM = 1;
    public static final int POSITION_LEFT = 3;
    public static final int POSITION_RIGHT = 4;
    public static final int POSITION_TOP = 2;
    private float arcHeightPx;
    private int arcPosition;

    public @interface ArcPosition {
    }

    public @interface CropDirection {
    }

    public ArcView(Context context) {
        super(context);
        this.arcPosition = 2;
        this.arcHeightPx = 0.0f;
        init(context, null);
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.arcPosition = 2;
        this.arcHeightPx = 0.0f;
        init(context, attrs);
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.arcPosition = 2;
        this.arcHeightPx = 0.0f;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ArcView);
            this.arcHeightPx = attributes.getDimensionPixelSize(R.styleable.ArcView_shape_arc_height, (int) this.arcHeightPx);
            this.arcPosition = attributes.getInteger(R.styleable.ArcView_shape_arc_position, this.arcPosition);
            attributes.recycle();
        }
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.ArcView.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code restructure failed: missing block: B:18:0x00fe, code lost:
            
                return r0;
             */
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Path createClipPath(int width, int height) {
                Path path = new Path();
                boolean isCropInside = ArcView.this.getCropDirection() == 1;
                float arcHeightAbs = Math.abs(ArcView.this.arcHeightPx);
                switch (ArcView.this.arcPosition) {
                    case 1:
                        if (isCropInside) {
                            path.moveTo(0.0f, 0.0f);
                            path.lineTo(0.0f, height);
                            path.quadTo(width / 2, height - (2.0f * arcHeightAbs), width, height);
                            path.lineTo(width, 0.0f);
                            path.close();
                            break;
                        } else {
                            path.moveTo(0.0f, 0.0f);
                            path.lineTo(0.0f, height - arcHeightAbs);
                            path.quadTo(width / 2, height + arcHeightAbs, width, height - arcHeightAbs);
                            path.lineTo(width, 0.0f);
                            path.close();
                            break;
                        }
                    case 2:
                        if (isCropInside) {
                            path.moveTo(0.0f, height);
                            path.lineTo(0.0f, 0.0f);
                            path.quadTo(width / 2, 2.0f * arcHeightAbs, width, 0.0f);
                            path.lineTo(width, height);
                            path.close();
                            break;
                        } else {
                            path.moveTo(0.0f, arcHeightAbs);
                            path.quadTo(width / 2, -arcHeightAbs, width, arcHeightAbs);
                            path.lineTo(width, height);
                            path.lineTo(0.0f, height);
                            path.close();
                            break;
                        }
                    case 3:
                        if (isCropInside) {
                            path.moveTo(width, 0.0f);
                            path.lineTo(0.0f, 0.0f);
                            path.quadTo(2.0f * arcHeightAbs, height / 2, 0.0f, height);
                            path.lineTo(width, height);
                            path.close();
                            break;
                        } else {
                            path.moveTo(width, 0.0f);
                            path.lineTo(arcHeightAbs, 0.0f);
                            path.quadTo(-arcHeightAbs, height / 2, arcHeightAbs, height);
                            path.lineTo(width, height);
                            path.close();
                            break;
                        }
                    case 4:
                        if (isCropInside) {
                            path.moveTo(0.0f, 0.0f);
                            path.lineTo(width, 0.0f);
                            path.quadTo(width - (2.0f * arcHeightAbs), height / 2, width, height);
                            path.lineTo(0.0f, height);
                            path.close();
                            break;
                        } else {
                            path.moveTo(0.0f, 0.0f);
                            path.lineTo(width - arcHeightAbs, 0.0f);
                            path.quadTo(width + arcHeightAbs, height / 2, width - arcHeightAbs, height);
                            path.lineTo(0.0f, height);
                            path.close();
                            break;
                        }
                }
            }

            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            public boolean requiresBitmap() {
                return false;
            }
        });
    }

    public int getArcPosition() {
        return this.arcPosition;
    }

    public void setArcPosition(int arcPosition) {
        this.arcPosition = arcPosition;
        requiresShapeUpdate();
    }

    public int getCropDirection() {
        return this.arcHeightPx > 0.0f ? 2 : 1;
    }

    public float getArcHeight() {
        return this.arcHeightPx;
    }

    public void setArcHeight(float arcHeight) {
        this.arcHeightPx = arcHeight;
        requiresShapeUpdate();
    }

    public float getArcHeightDp() {
        return pxToDp(this.arcHeightPx);
    }

    public void setArcHeightDp(float arcHeight) {
        setArcHeight(dpToPx(arcHeight));
    }
}
