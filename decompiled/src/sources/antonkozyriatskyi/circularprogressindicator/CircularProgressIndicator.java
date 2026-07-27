package antonkozyriatskyi.circularprogressindicator;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.budiyev.android.codescanner.BarcodeUtils;
import com.github.mikephil.charting.utils.Utils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class CircularProgressIndicator extends View {
    private static final int ANGLE_END_PROGRESS_BACKGROUND = 360;
    private static final int ANGLE_START_PROGRESS_BACKGROUND = 0;
    public static final int CAP_BUTT = 1;
    public static final int CAP_ROUND = 0;
    private static final int DEFAULT_ANIMATION_DURATION = 1000;
    private static final String DEFAULT_PROGRESS_BACKGROUND_COLOR = "#e0e0e0";
    private static final String DEFAULT_PROGRESS_COLOR = "#3F51B5";
    private static final int DEFAULT_PROGRESS_START_ANGLE = 270;
    private static final int DEFAULT_STROKE_WIDTH_DP = 8;
    private static final int DEFAULT_TEXT_SIZE_SP = 24;
    private static final int DESIRED_WIDTH_DP = 150;
    public static final int DIRECTION_CLOCKWISE = 0;
    public static final int DIRECTION_COUNTERCLOCKWISE = 1;
    public static final int LINEAR_GRADIENT = 1;
    public static final int NO_GRADIENT = 0;
    private static final String PROPERTY_ANGLE = "angle";
    public static final int RADIAL_GRADIENT = 2;
    public static final int SWEEP_GRADIENT = 3;
    private Interpolator animationInterpolator;
    private RectF circleBounds;
    private int direction;
    private Paint dotPaint;
    private boolean isAnimationEnabled;
    private boolean isFillBackgroundEnabled;
    private double maxProgressValue;
    private OnProgressChangeListener onProgressChangeListener;
    private ValueAnimator progressAnimator;
    private Paint progressBackgroundPaint;
    private Paint progressPaint;
    private String progressText;
    private ProgressTextAdapter progressTextAdapter;
    private double progressValue;
    private float radius;
    private boolean shouldDrawDot;
    private int startAngle;
    private int sweepAngle;
    private Paint textPaint;
    private float textX;
    private float textY;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Cap {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GradientType {
    }

    public interface OnProgressChangeListener {
        void onProgressChanged(double d, double d2);
    }

    public interface ProgressTextAdapter {
        String formatText(double d);
    }

    public CircularProgressIndicator(Context context) {
        super(context);
        this.startAngle = 270;
        this.sweepAngle = 0;
        this.maxProgressValue = 100.0d;
        this.progressValue = Utils.DOUBLE_EPSILON;
        this.direction = 1;
        this.animationInterpolator = new AccelerateDecelerateInterpolator();
        init(context, null);
    }

    public CircularProgressIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.startAngle = 270;
        this.sweepAngle = 0;
        this.maxProgressValue = 100.0d;
        this.progressValue = Utils.DOUBLE_EPSILON;
        this.direction = 1;
        this.animationInterpolator = new AccelerateDecelerateInterpolator();
        init(context, attrs);
    }

    public CircularProgressIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.startAngle = 270;
        this.sweepAngle = 0;
        this.maxProgressValue = 100.0d;
        this.progressValue = Utils.DOUBLE_EPSILON;
        this.direction = 1;
        this.animationInterpolator = new AccelerateDecelerateInterpolator();
        init(context, attrs);
    }

    public CircularProgressIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.startAngle = 270;
        this.sweepAngle = 0;
        this.maxProgressValue = 100.0d;
        this.progressValue = Utils.DOUBLE_EPSILON;
        this.direction = 1;
        this.animationInterpolator = new AccelerateDecelerateInterpolator();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int progressColor;
        int progressColor2 = Color.parseColor(DEFAULT_PROGRESS_COLOR);
        int progressBackgroundColor = Color.parseColor(DEFAULT_PROGRESS_BACKGROUND_COLOR);
        int progressStrokeWidth = dp2px(8.0f);
        int progressBackgroundStrokeWidth = progressStrokeWidth;
        int textColor = progressColor2;
        int textSize = sp2px(24.0f);
        this.shouldDrawDot = true;
        int dotColor = progressColor2;
        int dotWidth = progressStrokeWidth;
        Paint.Cap progressStrokeCap = Paint.Cap.ROUND;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircularProgressIndicator);
            int progressColor3 = a.getColor(R.styleable.CircularProgressIndicator_progressColor, progressColor2);
            progressBackgroundColor = a.getColor(R.styleable.CircularProgressIndicator_progressBackgroundColor, progressBackgroundColor);
            progressStrokeWidth = a.getDimensionPixelSize(R.styleable.CircularProgressIndicator_progressStrokeWidth, progressStrokeWidth);
            progressBackgroundStrokeWidth = a.getDimensionPixelSize(R.styleable.CircularProgressIndicator_progressBackgroundStrokeWidth, progressStrokeWidth);
            textColor = a.getColor(R.styleable.CircularProgressIndicator_textColor, progressColor3);
            textSize = a.getDimensionPixelSize(R.styleable.CircularProgressIndicator_textSize, textSize);
            this.shouldDrawDot = a.getBoolean(R.styleable.CircularProgressIndicator_drawDot, true);
            dotColor = a.getColor(R.styleable.CircularProgressIndicator_dotColor, progressColor3);
            dotWidth = a.getDimensionPixelSize(R.styleable.CircularProgressIndicator_dotWidth, progressStrokeWidth);
            this.startAngle = a.getInt(R.styleable.CircularProgressIndicator_startAngle, 270);
            if (this.startAngle < 0 || this.startAngle > ANGLE_END_PROGRESS_BACKGROUND) {
                this.startAngle = 270;
            }
            this.isAnimationEnabled = a.getBoolean(R.styleable.CircularProgressIndicator_enableProgressAnimation, true);
            this.isFillBackgroundEnabled = a.getBoolean(R.styleable.CircularProgressIndicator_fillBackground, false);
            this.direction = a.getInt(R.styleable.CircularProgressIndicator_direction, 1);
            int cap = a.getInt(R.styleable.CircularProgressIndicator_progressCap, 0);
            progressStrokeCap = cap == 0 ? Paint.Cap.ROUND : Paint.Cap.BUTT;
            String formattingPattern = a.getString(R.styleable.CircularProgressIndicator_formattingPattern);
            if (formattingPattern != null) {
                this.progressTextAdapter = new PatternProgressTextAdapter(formattingPattern);
            } else {
                this.progressTextAdapter = new DefaultProgressTextAdapter();
            }
            reformatProgressText();
            final int gradientType = a.getColor(R.styleable.CircularProgressIndicator_gradientType, 0);
            if (gradientType != 0) {
                progressColor = progressColor3;
                final int gradientColorEnd = a.getColor(R.styleable.CircularProgressIndicator_gradientEndColor, -1);
                if (gradientColorEnd != -1) {
                    post(new Runnable() { // from class: antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator.1
                        @Override // java.lang.Runnable
                        public void run() {
                            CircularProgressIndicator.this.setGradient(gradientType, gradientColorEnd);
                        }
                    });
                } else {
                    throw new IllegalArgumentException("did you forget to specify gradientColorEnd?");
                }
            } else {
                progressColor = progressColor3;
            }
            a.recycle();
            progressColor2 = progressColor;
        }
        this.progressPaint = new Paint();
        this.progressPaint.setStrokeCap(progressStrokeCap);
        this.progressPaint.setStrokeWidth(progressStrokeWidth);
        this.progressPaint.setStyle(Paint.Style.STROKE);
        this.progressPaint.setColor(progressColor2);
        this.progressPaint.setAntiAlias(true);
        Paint.Style progressBackgroundStyle = this.isFillBackgroundEnabled ? Paint.Style.FILL_AND_STROKE : Paint.Style.STROKE;
        this.progressBackgroundPaint = new Paint();
        this.progressBackgroundPaint.setStyle(progressBackgroundStyle);
        this.progressBackgroundPaint.setStrokeWidth(progressBackgroundStrokeWidth);
        this.progressBackgroundPaint.setColor(progressBackgroundColor);
        this.progressBackgroundPaint.setAntiAlias(true);
        this.dotPaint = new Paint();
        this.dotPaint.setStrokeCap(Paint.Cap.ROUND);
        this.dotPaint.setStrokeWidth(dotWidth);
        this.dotPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.dotPaint.setColor(dotColor);
        this.dotPaint.setAntiAlias(true);
        this.textPaint = new TextPaint();
        this.textPaint.setStrokeCap(Paint.Cap.ROUND);
        this.textPaint.setColor(textColor);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextSize(textSize);
        this.circleBounds = new RectF();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int finalWidth;
        int finalHeight;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int measuredWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        Rect textBoundsRect = new Rect();
        this.textPaint.getTextBounds(this.progressText, 0, this.progressText.length(), textBoundsRect);
        float dotWidth = this.dotPaint.getStrokeWidth();
        float progressWidth = this.progressPaint.getStrokeWidth();
        float progressBackgroundWidth = this.progressBackgroundPaint.getStrokeWidth();
        float strokeSizeOffset = this.shouldDrawDot ? Math.max(dotWidth, Math.max(progressWidth, progressBackgroundWidth)) : Math.max(progressWidth, progressBackgroundWidth);
        int desiredSize = ((int) strokeSizeOffset) + dp2px(150.0f) + Math.max(paddingBottom + paddingTop, paddingLeft + paddingRight);
        int desiredSize2 = (int) (desiredSize + Math.max(textBoundsRect.width(), textBoundsRect.height()) + (desiredSize * 0.1f));
        switch (widthMode) {
            case Integer.MIN_VALUE:
                finalWidth = Math.min(desiredSize2, measuredWidth);
                break;
            case 1073741824:
                finalWidth = measuredWidth;
                break;
            default:
                finalWidth = desiredSize2;
                break;
        }
        switch (heightMode) {
            case Integer.MIN_VALUE:
                finalHeight = Math.min(desiredSize2, measuredHeight);
                break;
            case 1073741824:
                finalHeight = measuredHeight;
                break;
            default:
                finalHeight = desiredSize2;
                break;
        }
        int widthWithoutPadding = (finalWidth - paddingLeft) - paddingRight;
        int smallestSide = Math.min((finalHeight - paddingTop) - paddingBottom, widthWithoutPadding);
        setMeasuredDimension(smallestSide, smallestSide);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        calculateBounds(w, h);
        Shader shader = this.progressPaint.getShader();
        if (shader instanceof RadialGradient) {
        }
    }

    private void calculateBounds(int w, int h) {
        this.radius = w / 2.0f;
        float dotWidth = this.dotPaint.getStrokeWidth();
        float progressWidth = this.progressPaint.getStrokeWidth();
        float progressBackgroundWidth = this.progressBackgroundPaint.getStrokeWidth();
        float strokeSizeOffset = this.shouldDrawDot ? Math.max(dotWidth, Math.max(progressWidth, progressBackgroundWidth)) : Math.max(progressWidth, progressBackgroundWidth);
        float halfOffset = strokeSizeOffset / 2.0f;
        this.circleBounds.left = halfOffset;
        this.circleBounds.top = halfOffset;
        this.circleBounds.right = w - halfOffset;
        this.circleBounds.bottom = h - halfOffset;
        this.radius = this.circleBounds.width() / 2.0f;
        calculateTextBounds();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.progressAnimator != null) {
            this.progressAnimator.cancel();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawProgressBackground(canvas);
        drawProgress(canvas);
        if (this.shouldDrawDot) {
            drawDot(canvas);
        }
        drawText(canvas);
    }

    private void drawProgressBackground(Canvas canvas) {
        canvas.drawArc(this.circleBounds, 0.0f, 360.0f, false, this.progressBackgroundPaint);
    }

    private void drawProgress(Canvas canvas) {
        canvas.drawArc(this.circleBounds, this.startAngle, this.sweepAngle, false, this.progressPaint);
    }

    private void drawDot(Canvas canvas) {
        double angleRadians = Math.toRadians(this.startAngle + this.sweepAngle + BarcodeUtils.ROTATION_180);
        float cos = (float) Math.cos(angleRadians);
        float sin = (float) Math.sin(angleRadians);
        float x = this.circleBounds.centerX() - (this.radius * cos);
        float y = this.circleBounds.centerY() - (this.radius * sin);
        canvas.drawPoint(x, y, this.dotPaint);
    }

    private void drawText(Canvas canvas) {
        canvas.drawText(this.progressText, this.textX, this.textY, this.textPaint);
    }

    public void setMaxProgress(double maxProgress) {
        this.maxProgressValue = maxProgress;
        if (this.maxProgressValue < this.progressValue) {
            setCurrentProgress(maxProgress);
        }
        invalidate();
    }

    public void setCurrentProgress(double currentProgress) {
        if (currentProgress > this.maxProgressValue) {
            this.maxProgressValue = currentProgress;
        }
        setProgress(currentProgress, this.maxProgressValue);
    }

    public void setProgress(double current, double max) {
        double finalAngle;
        if (this.direction == 1) {
            finalAngle = -((current / max) * 360.0d);
        } else {
            double finalAngle2 = current / max;
            finalAngle = finalAngle2 * 360.0d;
        }
        double oldCurrentProgress = this.progressValue;
        this.maxProgressValue = max;
        this.progressValue = Math.min(current, max);
        if (this.onProgressChangeListener != null) {
            this.onProgressChangeListener.onProgressChanged(this.progressValue, this.maxProgressValue);
        }
        reformatProgressText();
        calculateTextBounds();
        stopProgressAnimation();
        if (this.isAnimationEnabled) {
            startProgressAnimation(oldCurrentProgress, finalAngle);
        } else {
            this.sweepAngle = (int) finalAngle;
            invalidate();
        }
    }

    private void startProgressAnimation(double oldCurrentProgress, final double finalAngle) {
        PropertyValuesHolder angleProperty = PropertyValuesHolder.ofInt(PROPERTY_ANGLE, this.sweepAngle, (int) finalAngle);
        this.progressAnimator = ValueAnimator.ofObject(new TypeEvaluator<Double>() { // from class: antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator.2
            @Override // android.animation.TypeEvaluator
            public Double evaluate(float fraction, Double startValue, Double endValue) {
                return Double.valueOf(startValue.doubleValue() + ((endValue.doubleValue() - startValue.doubleValue()) * fraction));
            }
        }, Double.valueOf(oldCurrentProgress), Double.valueOf(this.progressValue));
        this.progressAnimator.setDuration(1000L);
        this.progressAnimator.setValues(angleProperty);
        this.progressAnimator.setInterpolator(this.animationInterpolator);
        this.progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                CircularProgressIndicator.this.sweepAngle = ((Integer) animation.getAnimatedValue(CircularProgressIndicator.PROPERTY_ANGLE)).intValue();
                CircularProgressIndicator.this.invalidate();
            }
        });
        this.progressAnimator.addListener(new DefaultAnimatorListener() { // from class: antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator.4
            @Override // antonkozyriatskyi.circularprogressindicator.DefaultAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                CircularProgressIndicator.this.sweepAngle = (int) finalAngle;
                CircularProgressIndicator.this.invalidate();
                CircularProgressIndicator.this.progressAnimator = null;
            }
        });
        this.progressAnimator.start();
    }

    private void stopProgressAnimation() {
        if (this.progressAnimator != null) {
            this.progressAnimator.cancel();
        }
    }

    private void reformatProgressText() {
        this.progressText = this.progressTextAdapter.formatText(this.progressValue);
    }

    private Rect calculateTextBounds() {
        Rect textRect = new Rect();
        this.textPaint.getTextBounds(this.progressText, 0, this.progressText.length(), textRect);
        this.textX = this.circleBounds.centerX() - (textRect.width() / 2.0f);
        this.textY = this.circleBounds.centerY() + (textRect.height() / 2.0f);
        return textRect;
    }

    private int dp2px(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(1, dp, metrics);
    }

    private int sp2px(float sp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(2, sp, metrics);
    }

    private void invalidateEverything() {
        calculateBounds(getWidth(), getHeight());
        requestLayout();
        invalidate();
    }

    public void setProgressColor(int color) {
        this.progressPaint.setColor(color);
        invalidate();
    }

    public void setProgressBackgroundColor(int color) {
        this.progressBackgroundPaint.setColor(color);
        invalidate();
    }

    public void setProgressStrokeWidthDp(int strokeWidth) {
        setProgressStrokeWidthPx(dp2px(strokeWidth));
    }

    public void setProgressStrokeWidthPx(int strokeWidth) {
        this.progressPaint.setStrokeWidth(strokeWidth);
        invalidateEverything();
    }

    public void setProgressBackgroundStrokeWidthDp(int strokeWidth) {
        setProgressBackgroundStrokeWidthPx(dp2px(strokeWidth));
    }

    public void setProgressBackgroundStrokeWidthPx(int strokeWidth) {
        this.progressBackgroundPaint.setStrokeWidth(strokeWidth);
        invalidateEverything();
    }

    public void setTextColor(int color) {
        this.textPaint.setColor(color);
        Rect textRect = new Rect();
        this.textPaint.getTextBounds(this.progressText, 0, this.progressText.length(), textRect);
        invalidate(textRect);
    }

    public void setTextSizeSp(int size) {
        setTextSizePx(sp2px(size));
    }

    public void setTextSizePx(int size) {
        float currentSize = this.textPaint.getTextSize();
        float factor = this.textPaint.measureText(this.progressText) / currentSize;
        float offset = this.shouldDrawDot ? Math.max(this.dotPaint.getStrokeWidth(), this.progressPaint.getStrokeWidth()) : this.progressPaint.getStrokeWidth();
        float maximumAvailableTextWidth = this.circleBounds.width() - offset;
        if (size * factor >= maximumAvailableTextWidth) {
            size = (int) (maximumAvailableTextWidth / factor);
        }
        this.textPaint.setTextSize(size);
        Rect textBounds = calculateTextBounds();
        invalidate(textBounds);
    }

    public void setShouldDrawDot(boolean shouldDrawDot) {
        this.shouldDrawDot = shouldDrawDot;
        if (this.dotPaint.getStrokeWidth() > this.progressPaint.getStrokeWidth()) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    public void setDotColor(int color) {
        this.dotPaint.setColor(color);
        invalidate();
    }

    public void setDotWidthDp(int width) {
        setDotWidthPx(dp2px(width));
    }

    public void setDotWidthPx(int width) {
        this.dotPaint.setStrokeWidth(width);
        invalidateEverything();
    }

    public void setProgressTextAdapter(ProgressTextAdapter progressTextAdapter) {
        if (progressTextAdapter != null) {
            this.progressTextAdapter = progressTextAdapter;
        } else {
            this.progressTextAdapter = new DefaultProgressTextAdapter();
        }
        reformatProgressText();
        invalidateEverything();
    }

    public ProgressTextAdapter getProgressTextAdapter() {
        return this.progressTextAdapter;
    }

    public int getProgressColor() {
        return this.progressPaint.getColor();
    }

    public int getProgressBackgroundColor() {
        return this.progressBackgroundPaint.getColor();
    }

    public float getProgressStrokeWidth() {
        return this.progressPaint.getStrokeWidth();
    }

    public float getProgressBackgroundStrokeWidth() {
        return this.progressBackgroundPaint.getStrokeWidth();
    }

    public int getTextColor() {
        return this.textPaint.getColor();
    }

    public float getTextSize() {
        return this.textPaint.getTextSize();
    }

    public boolean isDotEnabled() {
        return this.shouldDrawDot;
    }

    public int getDotColor() {
        return this.dotPaint.getColor();
    }

    public float getDotWidth() {
        return this.dotPaint.getStrokeWidth();
    }

    public double getProgress() {
        return this.progressValue;
    }

    public double getMaxProgress() {
        return this.maxProgressValue;
    }

    public int getStartAngle() {
        return this.startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
        invalidate();
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        invalidate();
    }

    public int getProgressStrokeCap() {
        return this.progressPaint.getStrokeCap() == Paint.Cap.ROUND ? 0 : 1;
    }

    public void setProgressStrokeCap(int cap) {
        Paint.Cap paintCap = cap == 0 ? Paint.Cap.ROUND : Paint.Cap.BUTT;
        if (this.progressPaint.getStrokeCap() != paintCap) {
            this.progressPaint.setStrokeCap(paintCap);
            invalidate();
        }
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
    }

    public OnProgressChangeListener getOnProgressChangeListener() {
        return this.onProgressChangeListener;
    }

    public void setAnimationEnabled(boolean enableAnimation) {
        this.isAnimationEnabled = enableAnimation;
        if (!enableAnimation) {
            stopProgressAnimation();
        }
    }

    public boolean isAnimationEnabled() {
        return this.isAnimationEnabled;
    }

    public void setFillBackgroundEnabled(boolean fillBackgroundEnabled) {
        if (fillBackgroundEnabled == this.isFillBackgroundEnabled) {
            return;
        }
        this.isFillBackgroundEnabled = fillBackgroundEnabled;
        Paint.Style style = fillBackgroundEnabled ? Paint.Style.FILL_AND_STROKE : Paint.Style.STROKE;
        this.progressBackgroundPaint.setStyle(style);
        invalidate();
    }

    public boolean isFillBackgroundEnabled() {
        return this.isFillBackgroundEnabled;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.animationInterpolator = interpolator;
    }

    public Interpolator getInterpolator() {
        return this.animationInterpolator;
    }

    public void setGradient(int type, int endColor) {
        Shader gradient = null;
        float cx = getWidth() / 2.0f;
        float cy = getHeight() / 2.0f;
        int startColor = this.progressPaint.getColor();
        switch (type) {
            case 1:
                gradient = new LinearGradient(0.0f, 0.0f, getWidth(), getHeight(), startColor, endColor, Shader.TileMode.CLAMP);
                break;
            case 2:
                gradient = new RadialGradient(cx, cy, cx, startColor, endColor, Shader.TileMode.MIRROR);
                break;
            case 3:
                gradient = new SweepGradient(cx, cy, new int[]{startColor, endColor}, (float[]) null);
                break;
        }
        if (gradient != null) {
            Matrix matrix = new Matrix();
            matrix.postRotate(this.startAngle, cx, cy);
            gradient.setLocalMatrix(matrix);
        }
        this.progressPaint.setShader(gradient);
        invalidate();
    }

    public int getGradientType() {
        Shader shader = this.progressPaint.getShader();
        if (shader instanceof LinearGradient) {
            return 1;
        }
        if (shader instanceof RadialGradient) {
            return 2;
        }
        if (!(shader instanceof SweepGradient)) {
            return 0;
        }
        return 3;
    }
}
