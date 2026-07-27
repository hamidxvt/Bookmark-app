package com.github.gcacace.signaturepad.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.view.ViewCompat;
import com.github.gcacace.signaturepad.R;
import com.github.gcacace.signaturepad.utils.Bezier;
import com.github.gcacace.signaturepad.utils.ControlTimedPoints;
import com.github.gcacace.signaturepad.utils.SvgBuilder;
import com.github.gcacace.signaturepad.utils.TimedPoint;
import com.github.gcacace.signaturepad.view.ViewTreeObserverCompat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class SignaturePad extends View {
    private static final int DOUBLE_CLICK_DELAY_MS = 200;
    private final boolean DEFAULT_ATTR_CLEAR_ON_DOUBLE_CLICK;
    private final int DEFAULT_ATTR_PEN_COLOR;
    private final int DEFAULT_ATTR_PEN_MAX_WIDTH_PX;
    private final int DEFAULT_ATTR_PEN_MIN_WIDTH_PX;
    private final float DEFAULT_ATTR_VELOCITY_FILTER_WEIGHT;
    private Bezier mBezierCached;
    private Bitmap mBitmapSavedState;
    private boolean mClearOnDoubleClick;
    private ControlTimedPoints mControlTimedPointsCached;
    private int mCountClick;
    private RectF mDirtyRect;
    private long mFirstClick;
    private Boolean mHasEditState;
    private boolean mIsEmpty;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mLastVelocity;
    private float mLastWidth;
    private int mMaxWidth;
    private int mMinWidth;
    private OnSignedListener mOnSignedListener;
    private Paint mPaint;
    private List<TimedPoint> mPoints;
    private List<TimedPoint> mPointsCache;
    private Bitmap mSignatureBitmap;
    private Canvas mSignatureBitmapCanvas;
    private final SvgBuilder mSvgBuilder;
    private float mVelocityFilterWeight;

    public interface OnSignedListener {
        void onClear();

        void onSigned();

        void onStartSigning();
    }

    public SignaturePad(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSvgBuilder = new SvgBuilder();
        this.mPointsCache = new ArrayList();
        this.mControlTimedPointsCached = new ControlTimedPoints();
        this.mBezierCached = new Bezier();
        this.DEFAULT_ATTR_PEN_MIN_WIDTH_PX = 3;
        this.DEFAULT_ATTR_PEN_MAX_WIDTH_PX = 7;
        this.DEFAULT_ATTR_PEN_COLOR = ViewCompat.MEASURED_STATE_MASK;
        this.DEFAULT_ATTR_VELOCITY_FILTER_WEIGHT = 0.9f;
        this.DEFAULT_ATTR_CLEAR_ON_DOUBLE_CLICK = false;
        this.mPaint = new Paint();
        this.mSignatureBitmap = null;
        this.mSignatureBitmapCanvas = null;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SignaturePad, 0, 0);
        try {
            this.mMinWidth = a.getDimensionPixelSize(R.styleable.SignaturePad_penMinWidth, convertDpToPx(3.0f));
            this.mMaxWidth = a.getDimensionPixelSize(R.styleable.SignaturePad_penMaxWidth, convertDpToPx(7.0f));
            this.mPaint.setColor(a.getColor(R.styleable.SignaturePad_penColor, ViewCompat.MEASURED_STATE_MASK));
            this.mVelocityFilterWeight = a.getFloat(R.styleable.SignaturePad_velocityFilterWeight, 0.9f);
            this.mClearOnDoubleClick = a.getBoolean(R.styleable.SignaturePad_clearOnDoubleClick, false);
            a.recycle();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeCap(Paint.Cap.ROUND);
            this.mPaint.setStrokeJoin(Paint.Join.ROUND);
            this.mDirtyRect = new RectF();
            clearView();
        } catch (Throwable th) {
            a.recycle();
            throw th;
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        if (this.mHasEditState == null || this.mHasEditState.booleanValue()) {
            this.mBitmapSavedState = getTransparentSignatureBitmap();
        }
        bundle.putParcelable("signatureBitmap", this.mBitmapSavedState);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            setSignatureBitmap((Bitmap) bundle.getParcelable("signatureBitmap"));
            this.mBitmapSavedState = (Bitmap) bundle.getParcelable("signatureBitmap");
            state = bundle.getParcelable("superState");
        }
        this.mHasEditState = false;
        super.onRestoreInstanceState(state);
    }

    public void setPenColorRes(int colorRes) {
        try {
            setPenColor(getResources().getColor(colorRes));
        } catch (Resources.NotFoundException e) {
            setPenColor(Color.parseColor("#000000"));
        }
    }

    public void setPenColor(int color) {
        this.mPaint.setColor(color);
    }

    public void setMinWidth(float minWidth) {
        this.mMinWidth = convertDpToPx(minWidth);
    }

    public void setMaxWidth(float maxWidth) {
        this.mMaxWidth = convertDpToPx(maxWidth);
    }

    public void setVelocityFilterWeight(float velocityFilterWeight) {
        this.mVelocityFilterWeight = velocityFilterWeight;
    }

    public void clearView() {
        this.mSvgBuilder.clear();
        this.mPoints = new ArrayList();
        this.mLastVelocity = 0.0f;
        this.mLastWidth = (this.mMinWidth + this.mMaxWidth) / 2;
        if (this.mSignatureBitmap != null) {
            this.mSignatureBitmap = null;
            ensureSignatureBitmap();
        }
        setIsEmpty(true);
        invalidate();
    }

    public void clear() {
        clearView();
        this.mHasEditState = true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case 0:
                getParent().requestDisallowInterceptTouchEvent(true);
                this.mPoints.clear();
                if (!isDoubleClick()) {
                    this.mLastTouchX = eventX;
                    this.mLastTouchY = eventY;
                    addPoint(getNewPoint(eventX, eventY));
                    if (this.mOnSignedListener != null) {
                        this.mOnSignedListener.onStartSigning();
                    }
                    resetDirtyRect(eventX, eventY);
                    addPoint(getNewPoint(eventX, eventY));
                    setIsEmpty(false);
                }
                invalidate((int) (this.mDirtyRect.left - this.mMaxWidth), (int) (this.mDirtyRect.top - this.mMaxWidth), (int) (this.mDirtyRect.right + this.mMaxWidth), (int) (this.mDirtyRect.bottom + this.mMaxWidth));
                break;
            case 1:
                resetDirtyRect(eventX, eventY);
                addPoint(getNewPoint(eventX, eventY));
                getParent().requestDisallowInterceptTouchEvent(true);
                invalidate((int) (this.mDirtyRect.left - this.mMaxWidth), (int) (this.mDirtyRect.top - this.mMaxWidth), (int) (this.mDirtyRect.right + this.mMaxWidth), (int) (this.mDirtyRect.bottom + this.mMaxWidth));
                break;
            case 2:
                resetDirtyRect(eventX, eventY);
                addPoint(getNewPoint(eventX, eventY));
                setIsEmpty(false);
                invalidate((int) (this.mDirtyRect.left - this.mMaxWidth), (int) (this.mDirtyRect.top - this.mMaxWidth), (int) (this.mDirtyRect.right + this.mMaxWidth), (int) (this.mDirtyRect.bottom + this.mMaxWidth));
                break;
        }
        return false;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mSignatureBitmap != null) {
            canvas.drawBitmap(this.mSignatureBitmap, 0.0f, 0.0f, this.mPaint);
        }
    }

    public void setOnSignedListener(OnSignedListener listener) {
        this.mOnSignedListener = listener;
    }

    public boolean isEmpty() {
        return this.mIsEmpty;
    }

    public String getSignatureSvg() {
        int width = getTransparentSignatureBitmap().getWidth();
        int height = getTransparentSignatureBitmap().getHeight();
        return this.mSvgBuilder.build(width, height);
    }

    public Bitmap getSignatureBitmap() {
        Bitmap originalBitmap = getTransparentSignatureBitmap();
        Bitmap whiteBgBitmap = Bitmap.createBitmap(originalBitmap.getWidth(), originalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(whiteBgBitmap);
        canvas.drawColor(-1);
        canvas.drawBitmap(originalBitmap, 0.0f, 0.0f, (Paint) null);
        return whiteBgBitmap;
    }

    public void setSignatureBitmap(final Bitmap signature) {
        if (com.github.gcacace.signaturepad.view.ViewCompat.isLaidOut(this)) {
            clearView();
            ensureSignatureBitmap();
            RectF tempSrc = new RectF();
            RectF tempDst = new RectF();
            int dWidth = signature.getWidth();
            int dHeight = signature.getHeight();
            int vWidth = getWidth();
            int vHeight = getHeight();
            tempSrc.set(0.0f, 0.0f, dWidth, dHeight);
            tempDst.set(0.0f, 0.0f, vWidth, vHeight);
            Matrix drawMatrix = new Matrix();
            drawMatrix.setRectToRect(tempSrc, tempDst, Matrix.ScaleToFit.CENTER);
            Canvas canvas = new Canvas(this.mSignatureBitmap);
            canvas.drawBitmap(signature, drawMatrix, null);
            setIsEmpty(false);
            invalidate();
            return;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.github.gcacace.signaturepad.views.SignaturePad.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ViewTreeObserverCompat.removeOnGlobalLayoutListener(SignaturePad.this.getViewTreeObserver(), this);
                SignaturePad.this.setSignatureBitmap(signature);
            }
        });
    }

    public Bitmap getTransparentSignatureBitmap() {
        ensureSignatureBitmap();
        return this.mSignatureBitmap;
    }

    public Bitmap getTransparentSignatureBitmap(boolean trimBlankSpace) {
        if (!trimBlankSpace) {
            return getTransparentSignatureBitmap();
        }
        ensureSignatureBitmap();
        int imgHeight = this.mSignatureBitmap.getHeight();
        int imgWidth = this.mSignatureBitmap.getWidth();
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        boolean foundPixel = false;
        for (int x = 0; x < imgWidth; x++) {
            boolean stop = false;
            int y = 0;
            while (true) {
                if (y >= imgHeight) {
                    break;
                }
                if (this.mSignatureBitmap.getPixel(x, y) == 0) {
                    y++;
                } else {
                    xMin = x;
                    stop = true;
                    foundPixel = true;
                    break;
                }
            }
            if (stop) {
                break;
            }
        }
        if (!foundPixel) {
            return null;
        }
        for (int y2 = 0; y2 < imgHeight; y2++) {
            boolean stop2 = false;
            int x2 = xMin;
            while (true) {
                if (x2 >= imgWidth) {
                    break;
                }
                if (this.mSignatureBitmap.getPixel(x2, y2) == 0) {
                    x2++;
                } else {
                    yMin = y2;
                    stop2 = true;
                    break;
                }
            }
            if (stop2) {
                break;
            }
        }
        for (int x3 = imgWidth - 1; x3 >= xMin; x3--) {
            boolean stop3 = false;
            int y3 = yMin;
            while (true) {
                if (y3 >= imgHeight) {
                    break;
                }
                if (this.mSignatureBitmap.getPixel(x3, y3) == 0) {
                    y3++;
                } else {
                    xMax = x3;
                    stop3 = true;
                    break;
                }
            }
            if (stop3) {
                break;
            }
        }
        for (int y4 = imgHeight - 1; y4 >= yMin; y4--) {
            boolean stop4 = false;
            int x4 = xMin;
            while (true) {
                if (x4 > xMax) {
                    break;
                }
                if (this.mSignatureBitmap.getPixel(x4, y4) == 0) {
                    x4++;
                } else {
                    yMax = y4;
                    stop4 = true;
                    break;
                }
            }
            if (stop4) {
                break;
            }
        }
        return Bitmap.createBitmap(this.mSignatureBitmap, xMin, yMin, xMax - xMin, yMax - yMin);
    }

    private boolean isDoubleClick() {
        if (this.mClearOnDoubleClick) {
            if (this.mFirstClick != 0 && System.currentTimeMillis() - this.mFirstClick > 200) {
                this.mCountClick = 0;
            }
            this.mCountClick++;
            if (this.mCountClick == 1) {
                this.mFirstClick = System.currentTimeMillis();
            } else if (this.mCountClick == 2) {
                long lastClick = System.currentTimeMillis();
                if (lastClick - this.mFirstClick < 200) {
                    clearView();
                    return true;
                }
            }
        }
        return false;
    }

    private TimedPoint getNewPoint(float x, float y) {
        TimedPoint timedPoint;
        int mCacheSize = this.mPointsCache.size();
        if (mCacheSize == 0) {
            timedPoint = new TimedPoint();
        } else {
            timedPoint = this.mPointsCache.remove(mCacheSize - 1);
        }
        return timedPoint.set(x, y);
    }

    private void recyclePoint(TimedPoint point) {
        this.mPointsCache.add(point);
    }

    private void addPoint(TimedPoint newPoint) {
        this.mPoints.add(newPoint);
        int pointsCount = this.mPoints.size();
        if (pointsCount > 3) {
            ControlTimedPoints tmp = calculateCurveControlPoints(this.mPoints.get(0), this.mPoints.get(1), this.mPoints.get(2));
            TimedPoint c2 = tmp.c2;
            recyclePoint(tmp.c1);
            ControlTimedPoints tmp2 = calculateCurveControlPoints(this.mPoints.get(1), this.mPoints.get(2), this.mPoints.get(3));
            TimedPoint c3 = tmp2.c1;
            recyclePoint(tmp2.c2);
            Bezier curve = this.mBezierCached.set(this.mPoints.get(1), c2, c3, this.mPoints.get(2));
            TimedPoint startPoint = curve.startPoint;
            TimedPoint endPoint = curve.endPoint;
            float velocity = endPoint.velocityFrom(startPoint);
            float velocity2 = (this.mVelocityFilterWeight * (Float.isNaN(velocity) ? 0.0f : velocity)) + ((1.0f - this.mVelocityFilterWeight) * this.mLastVelocity);
            float newWidth = strokeWidth(velocity2);
            addBezier(curve, this.mLastWidth, newWidth);
            this.mLastVelocity = velocity2;
            this.mLastWidth = newWidth;
            recyclePoint(this.mPoints.remove(0));
            recyclePoint(c2);
            recyclePoint(c3);
        } else if (pointsCount == 1) {
            TimedPoint firstPoint = this.mPoints.get(0);
            this.mPoints.add(getNewPoint(firstPoint.x, firstPoint.y));
        }
        this.mHasEditState = true;
    }

    private void addBezier(Bezier curve, float startWidth, float endWidth) {
        this.mSvgBuilder.append(curve, (startWidth + endWidth) / 2.0f);
        ensureSignatureBitmap();
        float originalWidth = this.mPaint.getStrokeWidth();
        float widthDelta = endWidth - startWidth;
        float drawSteps = (float) Math.ceil(curve.length());
        for (int i = 0; i < drawSteps; i++) {
            float t = i / drawSteps;
            float tt = t * t;
            float ttt = tt * t;
            float u = 1.0f - t;
            float uu = u * u;
            float uuu = uu * u;
            float x = (curve.startPoint.x * uuu) + (uu * 3.0f * t * curve.control1.x) + (u * 3.0f * tt * curve.control2.x) + (curve.endPoint.x * ttt);
            float y = (curve.startPoint.y * uuu) + (uu * 3.0f * t * curve.control1.y) + (3.0f * u * tt * curve.control2.y) + (curve.endPoint.y * ttt);
            this.mPaint.setStrokeWidth(startWidth + (ttt * widthDelta));
            this.mSignatureBitmapCanvas.drawPoint(x, y, this.mPaint);
            expandDirtyRect(x, y);
        }
        this.mPaint.setStrokeWidth(originalWidth);
    }

    private ControlTimedPoints calculateCurveControlPoints(TimedPoint s1, TimedPoint s2, TimedPoint s3) {
        float dx1 = s1.x - s2.x;
        float dy1 = s1.y - s2.y;
        float dx2 = s2.x - s3.x;
        float dy2 = s2.y - s3.y;
        float m1X = (s1.x + s2.x) / 2.0f;
        float m1Y = (s1.y + s2.y) / 2.0f;
        float m2X = (s2.x + s3.x) / 2.0f;
        float m2Y = (s2.y + s3.y) / 2.0f;
        float l1 = (float) Math.sqrt((dx1 * dx1) + (dy1 * dy1));
        float l2 = (float) Math.sqrt((dx2 * dx2) + (dy2 * dy2));
        float dxm = m1X - m2X;
        float dym = m1Y - m2Y;
        float k = l2 / (l1 + l2);
        if (Float.isNaN(k)) {
            k = 0.0f;
        }
        float cmX = m2X + (dxm * k);
        float cmY = m2Y + (dym * k);
        float tx = s2.x - cmX;
        float ty = s2.y - cmY;
        return this.mControlTimedPointsCached.set(getNewPoint(m1X + tx, m1Y + ty), getNewPoint(m2X + tx, m2Y + ty));
    }

    private float strokeWidth(float velocity) {
        return Math.max(this.mMaxWidth / (1.0f + velocity), this.mMinWidth);
    }

    private void expandDirtyRect(float historicalX, float historicalY) {
        if (historicalX < this.mDirtyRect.left) {
            this.mDirtyRect.left = historicalX;
        } else if (historicalX > this.mDirtyRect.right) {
            this.mDirtyRect.right = historicalX;
        }
        if (historicalY < this.mDirtyRect.top) {
            this.mDirtyRect.top = historicalY;
        } else if (historicalY > this.mDirtyRect.bottom) {
            this.mDirtyRect.bottom = historicalY;
        }
    }

    private void resetDirtyRect(float eventX, float eventY) {
        this.mDirtyRect.left = Math.min(this.mLastTouchX, eventX);
        this.mDirtyRect.right = Math.max(this.mLastTouchX, eventX);
        this.mDirtyRect.top = Math.min(this.mLastTouchY, eventY);
        this.mDirtyRect.bottom = Math.max(this.mLastTouchY, eventY);
    }

    private void setIsEmpty(boolean newValue) {
        this.mIsEmpty = newValue;
        if (this.mOnSignedListener != null) {
            if (this.mIsEmpty) {
                this.mOnSignedListener.onClear();
            } else {
                this.mOnSignedListener.onSigned();
            }
        }
    }

    private void ensureSignatureBitmap() {
        if (this.mSignatureBitmap == null) {
            this.mSignatureBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            this.mSignatureBitmapCanvas = new Canvas(this.mSignatureBitmap);
        }
    }

    private int convertDpToPx(float dp) {
        return Math.round(getContext().getResources().getDisplayMetrics().density * dp);
    }

    public List<TimedPoint> getPoints() {
        return this.mPoints;
    }
}
