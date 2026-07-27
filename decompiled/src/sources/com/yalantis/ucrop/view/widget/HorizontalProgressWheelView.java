package com.yalantis.ucrop.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.yalantis.ucrop.R;

/* loaded from: classes17.dex */
public class HorizontalProgressWheelView extends View {
    private final Rect mCanvasClipBounds;
    private float mLastTouchedPosition;
    private int mMiddleLineColor;
    private int mProgressLineHeight;
    private int mProgressLineMargin;
    private Paint mProgressLinePaint;
    private int mProgressLineWidth;
    private Paint mProgressMiddleLinePaint;
    private boolean mScrollStarted;
    private ScrollingListener mScrollingListener;
    private float mTotalScrollDistance;

    public interface ScrollingListener {
        void onScroll(float f, float f2);

        void onScrollEnd();

        void onScrollStart();
    }

    public HorizontalProgressWheelView(Context context) {
        this(context, null);
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCanvasClipBounds = new Rect();
        init();
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mCanvasClipBounds = new Rect();
    }

    public void setScrollingListener(ScrollingListener scrollingListener) {
        this.mScrollingListener = scrollingListener;
    }

    public void setMiddleLineColor(int middleLineColor) {
        this.mMiddleLineColor = middleLineColor;
        this.mProgressMiddleLinePaint.setColor(this.mMiddleLineColor);
        invalidate();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
    
        return true;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.mLastTouchedPosition = event.getX();
                break;
            case 1:
                if (this.mScrollingListener != null) {
                    this.mScrollStarted = false;
                    this.mScrollingListener.onScrollEnd();
                    break;
                }
                break;
            case 2:
                float distance = event.getX() - this.mLastTouchedPosition;
                if (distance != 0.0f) {
                    if (!this.mScrollStarted) {
                        this.mScrollStarted = true;
                        if (this.mScrollingListener != null) {
                            this.mScrollingListener.onScrollStart();
                        }
                    }
                    onScrollEvent(event, distance);
                    break;
                }
                break;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.getClipBounds(this.mCanvasClipBounds);
        int linesCount = this.mCanvasClipBounds.width() / (this.mProgressLineWidth + this.mProgressLineMargin);
        float deltaX = this.mTotalScrollDistance % (this.mProgressLineMargin + this.mProgressLineWidth);
        for (int i = 0; i < linesCount; i++) {
            if (i < linesCount / 4) {
                this.mProgressLinePaint.setAlpha((int) ((i / (linesCount / 4)) * 255.0f));
            } else if (i > (linesCount * 3) / 4) {
                this.mProgressLinePaint.setAlpha((int) (((linesCount - i) / (linesCount / 4)) * 255.0f));
            } else {
                this.mProgressLinePaint.setAlpha(255);
            }
            canvas.drawLine((-deltaX) + this.mCanvasClipBounds.left + ((this.mProgressLineWidth + this.mProgressLineMargin) * i), this.mCanvasClipBounds.centerY() - (this.mProgressLineHeight / 4.0f), (-deltaX) + this.mCanvasClipBounds.left + ((this.mProgressLineWidth + this.mProgressLineMargin) * i), this.mCanvasClipBounds.centerY() + (this.mProgressLineHeight / 4.0f), this.mProgressLinePaint);
        }
        canvas.drawLine(this.mCanvasClipBounds.centerX(), this.mCanvasClipBounds.centerY() - (this.mProgressLineHeight / 2.0f), this.mCanvasClipBounds.centerX(), (this.mProgressLineHeight / 2.0f) + this.mCanvasClipBounds.centerY(), this.mProgressMiddleLinePaint);
    }

    private void onScrollEvent(MotionEvent event, float distance) {
        this.mTotalScrollDistance -= distance;
        postInvalidate();
        this.mLastTouchedPosition = event.getX();
        if (this.mScrollingListener != null) {
            this.mScrollingListener.onScroll(-distance, this.mTotalScrollDistance);
        }
    }

    private void init() {
        this.mMiddleLineColor = ContextCompat.getColor(getContext(), R.color.ucrop_color_widget_rotate_mid_line);
        this.mProgressLineWidth = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_width_horizontal_wheel_progress_line);
        this.mProgressLineHeight = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_height_horizontal_wheel_progress_line);
        this.mProgressLineMargin = getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_margin_horizontal_wheel_progress_line);
        this.mProgressLinePaint = new Paint(1);
        this.mProgressLinePaint.setStyle(Paint.Style.STROKE);
        this.mProgressLinePaint.setStrokeWidth(this.mProgressLineWidth);
        this.mProgressLinePaint.setColor(getResources().getColor(R.color.ucrop_color_progress_wheel_line));
        this.mProgressMiddleLinePaint = new Paint(this.mProgressLinePaint);
        this.mProgressMiddleLinePaint.setColor(this.mMiddleLineColor);
        this.mProgressMiddleLinePaint.setStrokeCap(Paint.Cap.ROUND);
        this.mProgressMiddleLinePaint.setStrokeWidth(getContext().getResources().getDimensionPixelSize(R.dimen.ucrop_width_middle_wheel_progress_line));
    }
}
