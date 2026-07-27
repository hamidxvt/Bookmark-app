package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;
import com.yalantis.ucrop.util.RectUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes17.dex */
public class OverlayView extends View {
    public static final boolean DEFAULT_CIRCLE_DIMMED_LAYER = false;
    public static final int DEFAULT_CROP_GRID_COLUMN_COUNT = 2;
    public static final int DEFAULT_CROP_GRID_ROW_COUNT = 2;
    public static final int DEFAULT_FREESTYLE_CROP_MODE = 0;
    public static final boolean DEFAULT_SHOW_CROP_FRAME = true;
    public static final boolean DEFAULT_SHOW_CROP_GRID = true;
    public static final int FREESTYLE_CROP_MODE_DISABLE = 0;
    public static final int FREESTYLE_CROP_MODE_ENABLE = 1;
    public static final int FREESTYLE_CROP_MODE_ENABLE_WITH_PASS_THROUGH = 2;
    private OverlayViewChangeListener mCallback;
    private boolean mCircleDimmedLayer;
    private Path mCircularPath;
    private Paint mCropFrameCornersPaint;
    private Paint mCropFramePaint;
    protected float[] mCropGridCenter;
    private int mCropGridColumnCount;
    protected float[] mCropGridCorners;
    private Paint mCropGridPaint;
    private int mCropGridRowCount;
    private int mCropRectCornerTouchAreaLineLength;
    private int mCropRectMinSize;
    private final RectF mCropViewRect;
    private int mCurrentTouchCornerIndex;
    private int mDimmedColor;
    private Paint mDimmedStrokePaint;
    private int mFreestyleCropMode;
    private float[] mGridPoints;
    private float mPreviousTouchX;
    private float mPreviousTouchY;
    private boolean mShouldSetupCropBounds;
    private boolean mShowCropFrame;
    private boolean mShowCropGrid;
    private float mTargetAspectRatio;
    private final RectF mTempRect;
    protected int mThisHeight;
    protected int mThisWidth;
    private int mTouchPointThreshold;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FreestyleMode {
    }

    public OverlayView(Context context) {
        this(context, null);
    }

    public OverlayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverlayView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCropViewRect = new RectF();
        this.mTempRect = new RectF();
        this.mGridPoints = null;
        this.mCircularPath = new Path();
        this.mDimmedStrokePaint = new Paint(1);
        this.mCropGridPaint = new Paint(1);
        this.mCropFramePaint = new Paint(1);
        this.mCropFrameCornersPaint = new Paint(1);
        this.mFreestyleCropMode = 0;
        this.mPreviousTouchX = -1.0f;
        this.mPreviousTouchY = -1.0f;
        this.mCurrentTouchCornerIndex = -1;
        this.mTouchPointThreshold = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_threshold);
        this.mCropRectMinSize = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_min_size);
        this.mCropRectCornerTouchAreaLineLength = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_area_line_length);
        init();
    }

    public OverlayViewChangeListener getOverlayViewChangeListener() {
        return this.mCallback;
    }

    public void setOverlayViewChangeListener(OverlayViewChangeListener callback) {
        this.mCallback = callback;
    }

    public RectF getCropViewRect() {
        return this.mCropViewRect;
    }

    @Deprecated
    public boolean isFreestyleCropEnabled() {
        return this.mFreestyleCropMode == 1;
    }

    @Deprecated
    public void setFreestyleCropEnabled(boolean z) {
        this.mFreestyleCropMode = z ? 1 : 0;
    }

    public int getFreestyleCropMode() {
        return this.mFreestyleCropMode;
    }

    public void setFreestyleCropMode(int mFreestyleCropMode) {
        this.mFreestyleCropMode = mFreestyleCropMode;
        postInvalidate();
    }

    public void setCircleDimmedLayer(boolean circleDimmedLayer) {
        this.mCircleDimmedLayer = circleDimmedLayer;
    }

    public void setCropGridRowCount(int cropGridRowCount) {
        this.mCropGridRowCount = cropGridRowCount;
        this.mGridPoints = null;
    }

    public void setCropGridColumnCount(int cropGridColumnCount) {
        this.mCropGridColumnCount = cropGridColumnCount;
        this.mGridPoints = null;
    }

    public void setShowCropFrame(boolean showCropFrame) {
        this.mShowCropFrame = showCropFrame;
    }

    public void setShowCropGrid(boolean showCropGrid) {
        this.mShowCropGrid = showCropGrid;
    }

    public void setDimmedColor(int dimmedColor) {
        this.mDimmedColor = dimmedColor;
    }

    public void setCropFrameStrokeWidth(int width) {
        this.mCropFramePaint.setStrokeWidth(width);
    }

    public void setCropGridStrokeWidth(int width) {
        this.mCropGridPaint.setStrokeWidth(width);
    }

    public void setCropFrameColor(int color) {
        this.mCropFramePaint.setColor(color);
    }

    public void setCropGridColor(int color) {
        this.mCropGridPaint.setColor(color);
    }

    public void setTargetAspectRatio(float targetAspectRatio) {
        this.mTargetAspectRatio = targetAspectRatio;
        if (this.mThisWidth > 0) {
            setupCropBounds();
            postInvalidate();
        } else {
            this.mShouldSetupCropBounds = true;
        }
    }

    public void setupCropBounds() {
        int height = (int) (this.mThisWidth / this.mTargetAspectRatio);
        if (height > this.mThisHeight) {
            int width = (int) (this.mThisHeight * this.mTargetAspectRatio);
            int halfDiff = (this.mThisWidth - width) / 2;
            this.mCropViewRect.set(getPaddingLeft() + halfDiff, getPaddingTop(), getPaddingLeft() + width + halfDiff, getPaddingTop() + this.mThisHeight);
        } else {
            int halfDiff2 = (this.mThisHeight - height) / 2;
            this.mCropViewRect.set(getPaddingLeft(), getPaddingTop() + halfDiff2, getPaddingLeft() + this.mThisWidth, getPaddingTop() + height + halfDiff2);
        }
        if (this.mCallback != null) {
            this.mCallback.onCropRectUpdated(this.mCropViewRect);
        }
        updateGridPoints();
    }

    private void updateGridPoints() {
        this.mCropGridCorners = RectUtils.getCornersFromRect(this.mCropViewRect);
        this.mCropGridCenter = RectUtils.getCenterFromRect(this.mCropViewRect);
        this.mGridPoints = null;
        this.mCircularPath.reset();
        this.mCircularPath.addCircle(this.mCropViewRect.centerX(), this.mCropViewRect.centerY(), Math.min(this.mCropViewRect.width(), this.mCropViewRect.height()) / 2.0f, Path.Direction.CW);
    }

    protected void init() {
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            int left2 = getPaddingLeft();
            int top2 = getPaddingTop();
            int right2 = getWidth() - getPaddingRight();
            int bottom2 = getHeight() - getPaddingBottom();
            this.mThisWidth = right2 - left2;
            this.mThisHeight = bottom2 - top2;
            if (this.mShouldSetupCropBounds) {
                this.mShouldSetupCropBounds = false;
                setTargetAspectRatio(this.mTargetAspectRatio);
            }
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDimmedLayer(canvas);
        drawCropGrid(canvas);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mCropViewRect.isEmpty() || this.mFreestyleCropMode == 0) {
            return false;
        }
        float x = event.getX();
        float y = event.getY();
        if ((event.getAction() & 255) == 0) {
            this.mCurrentTouchCornerIndex = getCurrentTouchIndex(x, y);
            boolean shouldHandle = this.mCurrentTouchCornerIndex != -1;
            if (!shouldHandle) {
                this.mPreviousTouchX = -1.0f;
                this.mPreviousTouchY = -1.0f;
            } else if (this.mPreviousTouchX < 0.0f) {
                this.mPreviousTouchX = x;
                this.mPreviousTouchY = y;
            }
            return shouldHandle;
        }
        if ((event.getAction() & 255) == 2 && event.getPointerCount() == 1 && this.mCurrentTouchCornerIndex != -1) {
            float x2 = Math.min(Math.max(x, getPaddingLeft()), getWidth() - getPaddingRight());
            float y2 = Math.min(Math.max(y, getPaddingTop()), getHeight() - getPaddingBottom());
            updateCropViewRect(x2, y2);
            this.mPreviousTouchX = x2;
            this.mPreviousTouchY = y2;
            return true;
        }
        if ((event.getAction() & 255) == 1) {
            this.mPreviousTouchX = -1.0f;
            this.mPreviousTouchY = -1.0f;
            this.mCurrentTouchCornerIndex = -1;
            if (this.mCallback != null) {
                this.mCallback.onCropRectUpdated(this.mCropViewRect);
            }
        }
        return false;
    }

    private void updateCropViewRect(float touchX, float touchY) {
        this.mTempRect.set(this.mCropViewRect);
        switch (this.mCurrentTouchCornerIndex) {
            case 0:
                this.mTempRect.set(touchX, touchY, this.mCropViewRect.right, this.mCropViewRect.bottom);
                break;
            case 1:
                this.mTempRect.set(this.mCropViewRect.left, touchY, touchX, this.mCropViewRect.bottom);
                break;
            case 2:
                this.mTempRect.set(this.mCropViewRect.left, this.mCropViewRect.top, touchX, touchY);
                break;
            case 3:
                this.mTempRect.set(touchX, this.mCropViewRect.top, this.mCropViewRect.right, touchY);
                break;
            case 4:
                this.mTempRect.offset(touchX - this.mPreviousTouchX, touchY - this.mPreviousTouchY);
                if (this.mTempRect.left > getLeft() && this.mTempRect.top > getTop() && this.mTempRect.right < getRight() && this.mTempRect.bottom < getBottom()) {
                    this.mCropViewRect.set(this.mTempRect);
                    updateGridPoints();
                    postInvalidate();
                    return;
                }
                return;
        }
        boolean changeHeight = this.mTempRect.height() >= ((float) this.mCropRectMinSize);
        boolean changeWidth = this.mTempRect.width() >= ((float) this.mCropRectMinSize);
        this.mCropViewRect.set((changeWidth ? this.mTempRect : this.mCropViewRect).left, (changeHeight ? this.mTempRect : this.mCropViewRect).top, (changeWidth ? this.mTempRect : this.mCropViewRect).right, (changeHeight ? this.mTempRect : this.mCropViewRect).bottom);
        if (changeHeight || changeWidth) {
            updateGridPoints();
            postInvalidate();
        }
    }

    private int getCurrentTouchIndex(float touchX, float touchY) {
        int closestPointIndex = -1;
        double closestPointDistance = this.mTouchPointThreshold;
        for (int i = 0; i < 8; i += 2) {
            double distanceToCorner = Math.sqrt(Math.pow(touchX - this.mCropGridCorners[i], 2.0d) + Math.pow(touchY - this.mCropGridCorners[i + 1], 2.0d));
            if (distanceToCorner < closestPointDistance) {
                closestPointDistance = distanceToCorner;
                closestPointIndex = i / 2;
            }
        }
        int i2 = this.mFreestyleCropMode;
        if (i2 == 1 && closestPointIndex < 0 && this.mCropViewRect.contains(touchX, touchY)) {
            return 4;
        }
        return closestPointIndex;
    }

    protected void drawDimmedLayer(Canvas canvas) {
        canvas.save();
        if (this.mCircleDimmedLayer) {
            canvas.clipPath(this.mCircularPath, Region.Op.DIFFERENCE);
        } else {
            canvas.clipRect(this.mCropViewRect, Region.Op.DIFFERENCE);
        }
        canvas.drawColor(this.mDimmedColor);
        canvas.restore();
        if (this.mCircleDimmedLayer) {
            canvas.drawCircle(this.mCropViewRect.centerX(), this.mCropViewRect.centerY(), Math.min(this.mCropViewRect.width(), this.mCropViewRect.height()) / 2.0f, this.mDimmedStrokePaint);
        }
    }

    protected void drawCropGrid(Canvas canvas) {
        if (this.mShowCropGrid) {
            if (this.mGridPoints == null && !this.mCropViewRect.isEmpty()) {
                this.mGridPoints = new float[(this.mCropGridRowCount * 4) + (this.mCropGridColumnCount * 4)];
                int index = 0;
                int i = 0;
                while (i < this.mCropGridRowCount) {
                    int index2 = index + 1;
                    this.mGridPoints[index] = this.mCropViewRect.left;
                    int index3 = index2 + 1;
                    this.mGridPoints[index2] = (this.mCropViewRect.height() * ((i + 1.0f) / (this.mCropGridRowCount + 1))) + this.mCropViewRect.top;
                    int index4 = index3 + 1;
                    this.mGridPoints[index3] = this.mCropViewRect.right;
                    this.mGridPoints[index4] = (this.mCropViewRect.height() * ((i + 1.0f) / (this.mCropGridRowCount + 1))) + this.mCropViewRect.top;
                    i++;
                    index = index4 + 1;
                }
                int i2 = 0;
                while (i2 < this.mCropGridColumnCount) {
                    int index5 = index + 1;
                    this.mGridPoints[index] = (this.mCropViewRect.width() * ((i2 + 1.0f) / (this.mCropGridColumnCount + 1))) + this.mCropViewRect.left;
                    int index6 = index5 + 1;
                    this.mGridPoints[index5] = this.mCropViewRect.top;
                    int index7 = index6 + 1;
                    this.mGridPoints[index6] = (this.mCropViewRect.width() * ((i2 + 1.0f) / (this.mCropGridColumnCount + 1))) + this.mCropViewRect.left;
                    this.mGridPoints[index7] = this.mCropViewRect.bottom;
                    i2++;
                    index = index7 + 1;
                }
            }
            if (this.mGridPoints != null) {
                canvas.drawLines(this.mGridPoints, this.mCropGridPaint);
            }
        }
        if (this.mShowCropFrame) {
            canvas.drawRect(this.mCropViewRect, this.mCropFramePaint);
        }
        if (this.mFreestyleCropMode != 0) {
            canvas.save();
            this.mTempRect.set(this.mCropViewRect);
            this.mTempRect.inset(this.mCropRectCornerTouchAreaLineLength, -this.mCropRectCornerTouchAreaLineLength);
            canvas.clipRect(this.mTempRect, Region.Op.DIFFERENCE);
            this.mTempRect.set(this.mCropViewRect);
            this.mTempRect.inset(-this.mCropRectCornerTouchAreaLineLength, this.mCropRectCornerTouchAreaLineLength);
            canvas.clipRect(this.mTempRect, Region.Op.DIFFERENCE);
            canvas.drawRect(this.mCropViewRect, this.mCropFrameCornersPaint);
            canvas.restore();
        }
    }

    protected void processStyledAttributes(TypedArray a) {
        this.mCircleDimmedLayer = a.getBoolean(R.styleable.ucrop_UCropView_ucrop_circle_dimmed_layer, false);
        this.mDimmedColor = a.getColor(R.styleable.ucrop_UCropView_ucrop_dimmed_color, getResources().getColor(R.color.ucrop_color_default_dimmed));
        this.mDimmedStrokePaint.setColor(this.mDimmedColor);
        this.mDimmedStrokePaint.setStyle(Paint.Style.STROKE);
        this.mDimmedStrokePaint.setStrokeWidth(1.0f);
        initCropFrameStyle(a);
        this.mShowCropFrame = a.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_frame, true);
        initCropGridStyle(a);
        this.mShowCropGrid = a.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_grid, true);
    }

    private void initCropFrameStyle(TypedArray a) {
        int cropFrameStrokeSize = a.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_frame_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_frame_stoke_width));
        int cropFrameColor = a.getColor(R.styleable.ucrop_UCropView_ucrop_frame_color, getResources().getColor(R.color.ucrop_color_default_crop_frame));
        this.mCropFramePaint.setStrokeWidth(cropFrameStrokeSize);
        this.mCropFramePaint.setColor(cropFrameColor);
        this.mCropFramePaint.setStyle(Paint.Style.STROKE);
        this.mCropFrameCornersPaint.setStrokeWidth(cropFrameStrokeSize * 3);
        this.mCropFrameCornersPaint.setColor(cropFrameColor);
        this.mCropFrameCornersPaint.setStyle(Paint.Style.STROKE);
    }

    private void initCropGridStyle(TypedArray a) {
        int cropGridStrokeSize = a.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_grid_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_grid_stoke_width));
        int cropGridColor = a.getColor(R.styleable.ucrop_UCropView_ucrop_grid_color, getResources().getColor(R.color.ucrop_color_default_crop_grid));
        this.mCropGridPaint.setStrokeWidth(cropGridStrokeSize);
        this.mCropGridPaint.setColor(cropGridColor);
        this.mCropGridRowCount = a.getInt(R.styleable.ucrop_UCropView_ucrop_grid_row_count, 2);
        this.mCropGridColumnCount = a.getInt(R.styleable.ucrop_UCropView_ucrop_grid_column_count, 2);
    }
}
