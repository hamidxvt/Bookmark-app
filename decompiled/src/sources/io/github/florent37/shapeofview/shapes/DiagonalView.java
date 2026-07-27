package io.github.florent37.shapeofview.shapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;
import io.github.florent37.shapeofview.R;
import io.github.florent37.shapeofview.ShapeOfView;
import io.github.florent37.shapeofview.manager.ClipPathManager;

/* loaded from: classes17.dex */
public class DiagonalView extends ShapeOfView {
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int POSITION_BOTTOM = 1;
    public static final int POSITION_LEFT = 3;
    public static final int POSITION_RIGHT = 4;
    public static final int POSITION_TOP = 2;
    private float diagonalAngle;
    private int diagonalPosition;

    public @interface DiagonalDirection {
    }

    public @interface DiagonalPosition {
    }

    public DiagonalView(Context context) {
        super(context);
        this.diagonalPosition = 2;
        this.diagonalAngle = 0.0f;
        init(context, null);
    }

    public DiagonalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.diagonalPosition = 2;
        this.diagonalAngle = 0.0f;
        init(context, attrs);
    }

    public DiagonalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.diagonalPosition = 2;
        this.diagonalAngle = 0.0f;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DiagonalView);
            this.diagonalAngle = attributes.getFloat(R.styleable.DiagonalView_shape_diagonal_angle, this.diagonalAngle);
            this.diagonalPosition = attributes.getInteger(R.styleable.DiagonalView_shape_diagonal_position, this.diagonalPosition);
            attributes.recycle();
        }
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() { // from class: io.github.florent37.shapeofview.shapes.DiagonalView.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code restructure failed: missing block: B:18:0x02c8, code lost:
            
                return r0;
             */
            @Override // io.github.florent37.shapeofview.manager.ClipPathManager.ClipPathCreator
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Path createClipPath(int width, int height) {
                Path path = new Path();
                float diagonalAngleAbs = Math.abs(DiagonalView.this.diagonalAngle);
                boolean isDirectionLeft = DiagonalView.this.getDiagonalDirection() == 1;
                float perpendicularHeight = (float) (width * Math.tan(Math.toRadians(diagonalAngleAbs)));
                switch (DiagonalView.this.diagonalPosition) {
                    case 1:
                        if (isDirectionLeft) {
                            path.moveTo(DiagonalView.this.getPaddingLeft(), DiagonalView.this.getPaddingRight());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), DiagonalView.this.getPaddingTop());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), (height - perpendicularHeight) - DiagonalView.this.getPaddingBottom());
                            path.lineTo(DiagonalView.this.getPaddingLeft(), height - DiagonalView.this.getPaddingBottom());
                            path.close();
                            break;
                        } else {
                            path.moveTo(width - DiagonalView.this.getPaddingRight(), height - DiagonalView.this.getPaddingBottom());
                            path.lineTo(DiagonalView.this.getPaddingLeft(), (height - perpendicularHeight) - DiagonalView.this.getPaddingBottom());
                            path.lineTo(DiagonalView.this.getPaddingLeft(), DiagonalView.this.getPaddingTop());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), DiagonalView.this.getPaddingTop());
                            path.close();
                            break;
                        }
                    case 2:
                        if (isDirectionLeft) {
                            path.moveTo(width - DiagonalView.this.getPaddingRight(), height - DiagonalView.this.getPaddingBottom());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), DiagonalView.this.getPaddingTop() + perpendicularHeight);
                            path.lineTo(DiagonalView.this.getPaddingLeft(), DiagonalView.this.getPaddingTop());
                            path.lineTo(DiagonalView.this.getPaddingLeft(), height - DiagonalView.this.getPaddingBottom());
                            path.close();
                            break;
                        } else {
                            path.moveTo(width - DiagonalView.this.getPaddingRight(), height - DiagonalView.this.getPaddingBottom());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), DiagonalView.this.getPaddingTop());
                            path.lineTo(DiagonalView.this.getPaddingLeft(), DiagonalView.this.getPaddingTop() + perpendicularHeight);
                            path.lineTo(DiagonalView.this.getPaddingLeft(), height - DiagonalView.this.getPaddingBottom());
                            path.close();
                            break;
                        }
                    case 3:
                        if (isDirectionLeft) {
                            path.moveTo(DiagonalView.this.getPaddingLeft() + perpendicularHeight, DiagonalView.this.getPaddingTop());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), DiagonalView.this.getPaddingTop());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), height - DiagonalView.this.getPaddingBottom());
                            path.lineTo(DiagonalView.this.getPaddingLeft(), height - DiagonalView.this.getPaddingBottom());
                            path.close();
                            break;
                        } else {
                            path.moveTo(DiagonalView.this.getPaddingLeft(), DiagonalView.this.getPaddingTop());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), DiagonalView.this.getPaddingTop());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), height - DiagonalView.this.getPaddingBottom());
                            path.lineTo(DiagonalView.this.getPaddingLeft() + perpendicularHeight, height - DiagonalView.this.getPaddingBottom());
                            path.close();
                            break;
                        }
                    case 4:
                        if (isDirectionLeft) {
                            path.moveTo(DiagonalView.this.getPaddingLeft(), DiagonalView.this.getPaddingTop());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), DiagonalView.this.getPaddingTop());
                            path.lineTo((width - DiagonalView.this.getPaddingRight()) - perpendicularHeight, height - DiagonalView.this.getPaddingBottom());
                            path.lineTo(DiagonalView.this.getPaddingLeft(), height - DiagonalView.this.getPaddingBottom());
                            path.close();
                            break;
                        } else {
                            path.moveTo(DiagonalView.this.getPaddingLeft(), DiagonalView.this.getPaddingTop());
                            path.lineTo((width - DiagonalView.this.getPaddingRight()) - perpendicularHeight, DiagonalView.this.getPaddingTop());
                            path.lineTo(width - DiagonalView.this.getPaddingRight(), height - DiagonalView.this.getPaddingBottom());
                            path.lineTo(DiagonalView.this.getPaddingLeft(), height - DiagonalView.this.getPaddingBottom());
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

    public void setDiagonalPosition(int diagonalPosition) {
        this.diagonalPosition = diagonalPosition;
        requiresShapeUpdate();
    }

    public int getDiagonalPosition() {
        return this.diagonalPosition;
    }

    public int getDiagonalDirection() {
        return this.diagonalAngle > 0.0f ? 1 : 2;
    }

    public float getDiagonalAngle() {
        return this.diagonalAngle;
    }

    public void setDiagonalAngle(float diagonalAngle) {
        this.diagonalAngle = diagonalAngle;
        requiresShapeUpdate();
    }
}
