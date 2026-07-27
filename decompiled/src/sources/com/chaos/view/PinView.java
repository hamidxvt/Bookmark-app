package com.chaos.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

/* loaded from: classes16.dex */
public class PinView extends AppCompatEditText {
    private static final int BLINK = 500;
    private static final boolean DBG = false;
    private static final int DEFAULT_COUNT = 4;
    private static final String TAG = "PinView";
    private static final int VIEW_TYPE_LINE = 1;
    private static final int VIEW_TYPE_NONE = 2;
    private static final int VIEW_TYPE_RECTANGLE = 0;
    private boolean drawCursor;
    private boolean isAnimationEnable;
    private boolean isCursorVisible;
    private boolean isPasswordHidden;
    private final TextPaint mAnimatorTextPaint;
    private Blink mBlink;
    private int mCurLineColor;
    private int mCursorColor;
    private float mCursorHeight;
    private int mCursorWidth;
    private ValueAnimator mDefaultAddAnimator;
    private boolean mHideLineWhenFilled;
    private Drawable mItemBackground;
    private int mItemBackgroundResource;
    private final RectF mItemBorderRect;
    private final PointF mItemCenterPoint;
    private final RectF mItemLineRect;
    private ColorStateList mLineColor;
    private int mLineWidth;
    private final Paint mPaint;
    private final Path mPath;
    private int mPinItemCount;
    private int mPinItemHeight;
    private int mPinItemRadius;
    private int mPinItemSpacing;
    private int mPinItemWidth;
    private final Rect mTextRect;
    private String mTransformed;
    private int mViewType;
    private static final InputFilter[] NO_FILTERS = new InputFilter[0];
    private static final int[] HIGHLIGHT_STATES = {android.R.attr.state_selected};

    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.pinViewStyle);
    }

    public PinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mAnimatorTextPaint = new TextPaint();
        this.mCurLineColor = ViewCompat.MEASURED_STATE_MASK;
        this.mTextRect = new Rect();
        this.mItemBorderRect = new RectF();
        this.mItemLineRect = new RectF();
        this.mPath = new Path();
        this.mItemCenterPoint = new PointF();
        this.isAnimationEnable = false;
        Resources res = getResources();
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mAnimatorTextPaint.set(getPaint());
        Resources.Theme theme = context.getTheme();
        TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.PinView, defStyleAttr, 0);
        this.mViewType = a.getInt(R.styleable.PinView_viewType, 0);
        this.mPinItemCount = a.getInt(R.styleable.PinView_itemCount, 4);
        this.mPinItemHeight = (int) a.getDimension(R.styleable.PinView_itemHeight, res.getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
        this.mPinItemWidth = (int) a.getDimension(R.styleable.PinView_itemWidth, res.getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
        this.mPinItemSpacing = a.getDimensionPixelSize(R.styleable.PinView_itemSpacing, res.getDimensionPixelSize(R.dimen.pv_pin_view_item_spacing));
        this.mPinItemRadius = (int) a.getDimension(R.styleable.PinView_itemRadius, 0.0f);
        this.mLineWidth = (int) a.getDimension(R.styleable.PinView_lineWidth, res.getDimensionPixelSize(R.dimen.pv_pin_view_item_line_width));
        this.mLineColor = a.getColorStateList(R.styleable.PinView_lineColor);
        this.isCursorVisible = a.getBoolean(R.styleable.PinView_android_cursorVisible, true);
        this.mCursorColor = a.getColor(R.styleable.PinView_cursorColor, getCurrentTextColor());
        this.mCursorWidth = a.getDimensionPixelSize(R.styleable.PinView_cursorWidth, res.getDimensionPixelSize(R.dimen.pv_pin_view_cursor_width));
        this.mItemBackground = a.getDrawable(R.styleable.PinView_android_itemBackground);
        this.mHideLineWhenFilled = a.getBoolean(R.styleable.PinView_hideLineWhenFilled, false);
        a.recycle();
        if (this.mLineColor != null) {
            this.mCurLineColor = this.mLineColor.getDefaultColor();
        }
        updateCursorHeight();
        checkItemRadius();
        setMaxLength(this.mPinItemCount);
        this.mPaint.setStrokeWidth(this.mLineWidth);
        setupAnimator();
        setTransformationMethod(null);
        disableSelectionMenu();
        this.isPasswordHidden = isPasswordInputType(getInputType());
    }

    @Override // android.widget.TextView
    public void setInputType(int type) {
        super.setInputType(type);
        this.isPasswordHidden = isPasswordInputType(getInputType());
    }

    public void setPasswordHidden(boolean hidden) {
        this.isPasswordHidden = hidden;
        requestLayout();
    }

    public boolean isPasswordHidden() {
        return this.isPasswordHidden;
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
        if (this.mAnimatorTextPaint != null) {
            this.mAnimatorTextPaint.set(getPaint());
        }
    }

    private void setMaxLength(int maxLength) {
        if (maxLength >= 0) {
            setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        } else {
            setFilters(NO_FILTERS);
        }
    }

    private void setupAnimator() {
        this.mDefaultAddAnimator = ValueAnimator.ofFloat(0.5f, 1.0f);
        this.mDefaultAddAnimator.setDuration(150L);
        this.mDefaultAddAnimator.setInterpolator(new DecelerateInterpolator());
        this.mDefaultAddAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.chaos.view.PinView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = ((Float) animation.getAnimatedValue()).floatValue();
                int alpha = (int) (255.0f * scale);
                PinView.this.mAnimatorTextPaint.setTextSize(PinView.this.getTextSize() * scale);
                PinView.this.mAnimatorTextPaint.setAlpha(alpha);
                PinView.this.postInvalidate();
            }
        });
    }

    private void checkItemRadius() {
        if (this.mViewType == 1) {
            float halfOfLineWidth = this.mLineWidth / 2.0f;
            if (this.mPinItemRadius > halfOfLineWidth) {
                throw new IllegalArgumentException("The itemRadius can not be greater than lineWidth when viewType is line");
            }
        } else if (this.mViewType == 0) {
            float halfOfItemWidth = this.mPinItemWidth / 2.0f;
            if (this.mPinItemRadius > halfOfItemWidth) {
                throw new IllegalArgumentException("The itemRadius can not be greater than itemWidth");
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int boxesWidth;
        int height;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int boxHeight = this.mPinItemHeight;
        if (widthMode == 1073741824) {
            boxesWidth = widthSize;
        } else {
            int boxesWidth2 = ((this.mPinItemCount - 1) * this.mPinItemSpacing) + (this.mPinItemCount * this.mPinItemWidth);
            int width = ViewCompat.getPaddingEnd(this) + boxesWidth2 + ViewCompat.getPaddingStart(this);
            if (this.mPinItemSpacing != 0) {
                boxesWidth = width;
            } else {
                boxesWidth = width - ((this.mPinItemCount - 1) * this.mLineWidth);
            }
        }
        if (heightMode == 1073741824) {
            height = heightSize;
        } else {
            int height2 = getPaddingTop();
            height = height2 + boxHeight + getPaddingBottom();
        }
        setMeasuredDimension(boxesWidth, height);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (start != text.length()) {
            moveSelectionToEnd();
        }
        makeBlink();
        if (this.isAnimationEnable) {
            boolean isAdd = lengthAfter - lengthBefore > 0;
            if (isAdd && this.mDefaultAddAnimator != null) {
                this.mDefaultAddAnimator.end();
                this.mDefaultAddAnimator.start();
            }
        }
        TransformationMethod transformation = getTransformationMethod();
        if (transformation == null) {
            this.mTransformed = getText().toString();
        } else {
            this.mTransformed = transformation.getTransformation(getText(), this).toString();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            moveSelectionToEnd();
            makeBlink();
        }
    }

    @Override // android.widget.TextView
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (selEnd != getText().length()) {
            moveSelectionToEnd();
        }
    }

    private void moveSelectionToEnd() {
        setSelection(getText().length());
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mLineColor == null || this.mLineColor.isStateful()) {
            updateColors();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.save();
        updatePaints();
        drawPinView(canvas);
        canvas.restore();
    }

    private void updatePaints() {
        this.mPaint.setColor(this.mCurLineColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mLineWidth);
        getPaint().setColor(getCurrentTextColor());
    }

    private void drawPinView(Canvas canvas) {
        int highlightIdx = getText().length();
        int i = 0;
        while (i < this.mPinItemCount) {
            boolean highlight = isFocused() && highlightIdx == i;
            this.mPaint.setColor(highlight ? getLineColorForState(HIGHLIGHT_STATES) : this.mCurLineColor);
            updateItemRectF(i);
            updateCenterPoint();
            canvas.save();
            if (this.mViewType == 0) {
                updatePinBoxPath(i);
                canvas.clipPath(this.mPath);
            }
            drawItemBackground(canvas, highlight);
            canvas.restore();
            if (highlight) {
                drawCursor(canvas);
            }
            if (this.mViewType == 0) {
                drawPinBox(canvas, i);
            } else if (this.mViewType == 1) {
                drawPinLine(canvas, i);
            }
            if (this.mTransformed.length() > i) {
                if (getTransformationMethod() == null && this.isPasswordHidden) {
                    drawCircle(canvas, i);
                } else {
                    drawText(canvas, i);
                }
            } else if (!TextUtils.isEmpty(getHint()) && getHint().length() == this.mPinItemCount) {
                drawHint(canvas, i);
            }
            i++;
        }
        if (isFocused() && getText().length() != this.mPinItemCount && this.mViewType == 0) {
            int index = getText().length();
            updateItemRectF(index);
            updateCenterPoint();
            updatePinBoxPath(index);
            this.mPaint.setColor(getLineColorForState(HIGHLIGHT_STATES));
            drawPinBox(canvas, index);
        }
    }

    private int getLineColorForState(int... states) {
        return this.mLineColor != null ? this.mLineColor.getColorForState(states, this.mCurLineColor) : this.mCurLineColor;
    }

    private void drawItemBackground(Canvas canvas, boolean highlight) {
        if (this.mItemBackground == null) {
            return;
        }
        float delta = this.mLineWidth / 2.0f;
        int left = Math.round(this.mItemBorderRect.left - delta);
        int top = Math.round(this.mItemBorderRect.top - delta);
        int right = Math.round(this.mItemBorderRect.right + delta);
        int bottom = Math.round(this.mItemBorderRect.bottom + delta);
        this.mItemBackground.setBounds(left, top, right, bottom);
        this.mItemBackground.setState(highlight ? HIGHLIGHT_STATES : getDrawableState());
        this.mItemBackground.draw(canvas);
    }

    private void updatePinBoxPath(int i) {
        boolean drawRightCorner = false;
        boolean drawLeftCorner = false;
        if (this.mPinItemSpacing != 0) {
            drawRightCorner = true;
            drawLeftCorner = true;
        } else {
            if (i == 0 && i != this.mPinItemCount - 1) {
                drawLeftCorner = true;
            }
            if (i == this.mPinItemCount - 1 && i != 0) {
                drawRightCorner = true;
            }
        }
        updateRoundRectPath(this.mItemBorderRect, this.mPinItemRadius, this.mPinItemRadius, drawLeftCorner, drawRightCorner);
    }

    private void drawPinBox(Canvas canvas, int i) {
        if (this.mHideLineWhenFilled && i < getText().length()) {
            return;
        }
        canvas.drawPath(this.mPath, this.mPaint);
    }

    private void drawPinLine(Canvas canvas, int i) {
        if (this.mHideLineWhenFilled && i < getText().length()) {
            return;
        }
        boolean r = true;
        boolean l = true;
        if (this.mPinItemSpacing == 0 && this.mPinItemCount > 1) {
            if (i != 0) {
                if (i == this.mPinItemCount - 1) {
                    l = false;
                } else {
                    r = false;
                    l = false;
                }
            } else {
                r = false;
            }
        }
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(this.mLineWidth / 10.0f);
        float halfLineWidth = this.mLineWidth / 2.0f;
        this.mItemLineRect.set(this.mItemBorderRect.left - halfLineWidth, this.mItemBorderRect.bottom - halfLineWidth, this.mItemBorderRect.right + halfLineWidth, this.mItemBorderRect.bottom + halfLineWidth);
        updateRoundRectPath(this.mItemLineRect, this.mPinItemRadius, this.mPinItemRadius, l, r);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    private void drawCursor(Canvas canvas) {
        if (this.drawCursor) {
            float cx = this.mItemCenterPoint.x;
            float cy = this.mItemCenterPoint.y;
            float y = cy - (this.mCursorHeight / 2.0f);
            int color = this.mPaint.getColor();
            float width = this.mPaint.getStrokeWidth();
            this.mPaint.setColor(this.mCursorColor);
            this.mPaint.setStrokeWidth(this.mCursorWidth);
            canvas.drawLine(cx, y, cx, y + this.mCursorHeight, this.mPaint);
            this.mPaint.setColor(color);
            this.mPaint.setStrokeWidth(width);
        }
    }

    private void updateRoundRectPath(RectF rectF, float rx, float ry, boolean l, boolean r) {
        updateRoundRectPath(rectF, rx, ry, l, r, r, l);
    }

    private void updateRoundRectPath(RectF rectF, float rx, float ry, boolean tl, boolean tr, boolean br, boolean bl) {
        this.mPath.reset();
        float l = rectF.left;
        float t = rectF.top;
        float r = rectF.right;
        float b = rectF.bottom;
        float w = r - l;
        float h = b - t;
        float lw = w - (rx * 2.0f);
        float lh = h - (2.0f * ry);
        this.mPath.moveTo(l, t + ry);
        if (tl) {
            this.mPath.rQuadTo(0.0f, -ry, rx, -ry);
        } else {
            this.mPath.rLineTo(0.0f, -ry);
            this.mPath.rLineTo(rx, 0.0f);
        }
        this.mPath.rLineTo(lw, 0.0f);
        if (tr) {
            this.mPath.rQuadTo(rx, 0.0f, rx, ry);
        } else {
            this.mPath.rLineTo(rx, 0.0f);
            this.mPath.rLineTo(0.0f, ry);
        }
        this.mPath.rLineTo(0.0f, lh);
        if (br) {
            this.mPath.rQuadTo(0.0f, ry, -rx, ry);
        } else {
            this.mPath.rLineTo(0.0f, ry);
            this.mPath.rLineTo(-rx, 0.0f);
        }
        this.mPath.rLineTo(-lw, 0.0f);
        if (bl) {
            this.mPath.rQuadTo(-rx, 0.0f, -rx, -ry);
        } else {
            this.mPath.rLineTo(-rx, 0.0f);
            this.mPath.rLineTo(0.0f, -ry);
        }
        this.mPath.rLineTo(0.0f, -lh);
        this.mPath.close();
    }

    private void updateItemRectF(int i) {
        float halfLineWidth = this.mLineWidth / 2.0f;
        float left = getScrollX() + ViewCompat.getPaddingStart(this) + ((this.mPinItemSpacing + this.mPinItemWidth) * i) + halfLineWidth;
        if (this.mPinItemSpacing == 0 && i > 0) {
            left -= this.mLineWidth * i;
        }
        float right = (this.mPinItemWidth + left) - this.mLineWidth;
        float top = getScrollY() + getPaddingTop() + halfLineWidth;
        float bottom = (this.mPinItemHeight + top) - this.mLineWidth;
        this.mItemBorderRect.set(left, top, right, bottom);
    }

    private void drawText(Canvas canvas, int i) {
        Paint paint = getPaintByIndex(i);
        drawTextAtBox(canvas, paint, this.mTransformed, i);
    }

    private void drawHint(Canvas canvas, int i) {
        Paint paint = getPaintByIndex(i);
        paint.setColor(getCurrentHintTextColor());
        drawTextAtBox(canvas, paint, getHint(), i);
    }

    private void drawTextAtBox(Canvas canvas, Paint paint, CharSequence text, int charAt) {
        paint.getTextBounds(text.toString(), charAt, charAt + 1, this.mTextRect);
        float cx = this.mItemCenterPoint.x;
        float cy = this.mItemCenterPoint.y;
        float x = (cx - (Math.abs(this.mTextRect.width()) / 2.0f)) - this.mTextRect.left;
        float y = ((Math.abs(this.mTextRect.height()) / 2.0f) + cy) - this.mTextRect.bottom;
        canvas.drawText(text, charAt, charAt + 1, x, y, paint);
    }

    private void drawCircle(Canvas canvas, int i) {
        Paint paint = getPaintByIndex(i);
        float cx = this.mItemCenterPoint.x;
        float cy = this.mItemCenterPoint.y;
        canvas.drawCircle(cx, cy, paint.getTextSize() / 2.0f, paint);
    }

    private Paint getPaintByIndex(int i) {
        if (this.isAnimationEnable && i == getText().length() - 1) {
            this.mAnimatorTextPaint.setColor(getPaint().getColor());
            return this.mAnimatorTextPaint;
        }
        return getPaint();
    }

    private void drawAnchorLine(Canvas canvas) {
        float cx = this.mItemCenterPoint.x;
        float cy = this.mItemCenterPoint.y;
        this.mPaint.setStrokeWidth(1.0f);
        float cx2 = cx - (this.mPaint.getStrokeWidth() / 2.0f);
        float cy2 = cy - (this.mPaint.getStrokeWidth() / 2.0f);
        this.mPath.reset();
        this.mPath.moveTo(cx2, this.mItemBorderRect.top);
        this.mPath.lineTo(cx2, this.mItemBorderRect.top + Math.abs(this.mItemBorderRect.height()));
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPath.reset();
        this.mPath.moveTo(this.mItemBorderRect.left, cy2);
        this.mPath.lineTo(this.mItemBorderRect.left + Math.abs(this.mItemBorderRect.width()), cy2);
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPath.reset();
        this.mPaint.setStrokeWidth(this.mLineWidth);
    }

    private void updateColors() {
        int color;
        boolean inval = false;
        if (this.mLineColor != null) {
            color = this.mLineColor.getColorForState(getDrawableState(), 0);
        } else {
            color = getCurrentTextColor();
        }
        if (color != this.mCurLineColor) {
            this.mCurLineColor = color;
            inval = true;
        }
        if (inval) {
            invalidate();
        }
    }

    private void updateCenterPoint() {
        float cx = this.mItemBorderRect.left + (Math.abs(this.mItemBorderRect.width()) / 2.0f);
        float cy = this.mItemBorderRect.top + (Math.abs(this.mItemBorderRect.height()) / 2.0f);
        this.mItemCenterPoint.set(cx, cy);
    }

    private static boolean isPasswordInputType(int inputType) {
        int variation = inputType & 4095;
        return variation == 129 || variation == 225 || variation == 18;
    }

    @Override // android.widget.EditText, android.widget.TextView
    protected MovementMethod getDefaultMovementMethod() {
        return DefaultMovementMethod.getInstance();
    }

    public void setLineColor(int color) {
        this.mLineColor = ColorStateList.valueOf(color);
        updateColors();
    }

    public void setLineColor(ColorStateList colors) {
        if (colors == null) {
            throw new NullPointerException();
        }
        this.mLineColor = colors;
        updateColors();
    }

    public ColorStateList getLineColors() {
        return this.mLineColor;
    }

    public int getCurrentLineColor() {
        return this.mCurLineColor;
    }

    public void setLineWidth(int borderWidth) {
        this.mLineWidth = borderWidth;
        checkItemRadius();
        requestLayout();
    }

    public int getLineWidth() {
        return this.mLineWidth;
    }

    public void setItemCount(int count) {
        this.mPinItemCount = count;
        setMaxLength(count);
        requestLayout();
    }

    public int getItemCount() {
        return this.mPinItemCount;
    }

    public void setItemRadius(int itemRadius) {
        this.mPinItemRadius = itemRadius;
        checkItemRadius();
        requestLayout();
    }

    public int getItemRadius() {
        return this.mPinItemRadius;
    }

    public void setItemSpacing(int itemSpacing) {
        this.mPinItemSpacing = itemSpacing;
        requestLayout();
    }

    public int getItemSpacing() {
        return this.mPinItemSpacing;
    }

    public void setItemHeight(int itemHeight) {
        this.mPinItemHeight = itemHeight;
        updateCursorHeight();
        requestLayout();
    }

    public int getItemHeight() {
        return this.mPinItemHeight;
    }

    public void setItemWidth(int itemWidth) {
        this.mPinItemWidth = itemWidth;
        checkItemRadius();
        requestLayout();
    }

    public int getItemWidth() {
        return this.mPinItemWidth;
    }

    public void setAnimationEnable(boolean enable) {
        this.isAnimationEnable = enable;
    }

    public void setHideLineWhenFilled(boolean hideLineWhenFilled) {
        this.mHideLineWhenFilled = hideLineWhenFilled;
    }

    @Override // android.widget.TextView
    public void setTextSize(float size) {
        super.setTextSize(size);
        updateCursorHeight();
    }

    @Override // android.widget.TextView
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
        updateCursorHeight();
    }

    public void setItemBackgroundResources(int resId) {
        if (resId != 0 && this.mItemBackgroundResource != resId) {
            return;
        }
        this.mItemBackground = ResourcesCompat.getDrawable(getResources(), resId, getContext().getTheme());
        setItemBackground(this.mItemBackground);
        this.mItemBackgroundResource = resId;
    }

    public void setItemBackgroundColor(int color) {
        if (this.mItemBackground instanceof ColorDrawable) {
            ((ColorDrawable) this.mItemBackground.mutate()).setColor(color);
            this.mItemBackgroundResource = 0;
        } else {
            setItemBackground(new ColorDrawable(color));
        }
    }

    public void setItemBackground(Drawable background) {
        this.mItemBackgroundResource = 0;
        this.mItemBackground = background;
        invalidate();
    }

    public void setCursorWidth(int width) {
        this.mCursorWidth = width;
        if (isCursorVisible()) {
            invalidateCursor(true);
        }
    }

    public int getCursorWidth() {
        return this.mCursorWidth;
    }

    public void setCursorColor(int color) {
        this.mCursorColor = color;
        if (isCursorVisible()) {
            invalidateCursor(true);
        }
    }

    public int getCursorColor() {
        return this.mCursorColor;
    }

    @Override // android.widget.TextView
    public void setCursorVisible(boolean visible) {
        if (this.isCursorVisible != visible) {
            this.isCursorVisible = visible;
            invalidateCursor(this.isCursorVisible);
            makeBlink();
        }
    }

    @Override // android.widget.TextView
    public boolean isCursorVisible() {
        return this.isCursorVisible;
    }

    @Override // android.widget.TextView, android.view.View
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        switch (screenState) {
            case 0:
                suspendBlink();
                break;
            case 1:
                resumeBlink();
                break;
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        resumeBlink();
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        suspendBlink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldBlink() {
        return isCursorVisible() && isFocused();
    }

    private void makeBlink() {
        if (shouldBlink()) {
            if (this.mBlink == null) {
                this.mBlink = new Blink();
            }
            removeCallbacks(this.mBlink);
            this.drawCursor = false;
            postDelayed(this.mBlink, 500L);
            return;
        }
        if (this.mBlink != null) {
            removeCallbacks(this.mBlink);
        }
    }

    private void suspendBlink() {
        if (this.mBlink == null) {
            return;
        }
        this.mBlink.cancel();
        invalidateCursor(false);
    }

    private void resumeBlink() {
        if (this.mBlink != null) {
            this.mBlink.uncancel();
            makeBlink();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateCursor(boolean showCursor) {
        if (this.drawCursor != showCursor) {
            this.drawCursor = showCursor;
            invalidate();
        }
    }

    private void updateCursorHeight() {
        int delta = dpToPx(2.0f) * 2;
        this.mCursorHeight = ((float) this.mPinItemHeight) - getTextSize() > ((float) delta) ? getTextSize() + delta : getTextSize();
    }

    private class Blink implements Runnable {
        private boolean mCancelled;

        private Blink() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mCancelled) {
                return;
            }
            PinView.this.removeCallbacks(this);
            if (PinView.this.shouldBlink()) {
                PinView.this.invalidateCursor(!PinView.this.drawCursor);
                PinView.this.postDelayed(this, 500L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel() {
            if (!this.mCancelled) {
                PinView.this.removeCallbacks(this);
                this.mCancelled = true;
            }
        }

        void uncancel() {
            this.mCancelled = false;
        }
    }

    private void disableSelectionMenu() {
        setCustomSelectionActionModeCallback(new DefaultActionModeCallback());
        setCustomInsertionActionModeCallback(new DefaultActionModeCallback() { // from class: com.chaos.view.PinView.2
            @Override // com.chaos.view.PinView.DefaultActionModeCallback, android.view.ActionMode.Callback
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                menu.removeItem(android.R.id.autofill);
                return true;
            }
        });
    }

    @Override // android.widget.TextView
    public boolean isSuggestionsEnabled() {
        return false;
    }

    private int dpToPx(float dp) {
        return (int) ((getResources().getDisplayMetrics().density * dp) + 0.5f);
    }

    private static class DefaultActionModeCallback implements ActionMode.Callback {
        private DefaultActionModeCallback() {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode mode) {
        }
    }
}
