package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.google.android.flexbox.FlexboxHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class FlexboxLayout extends ViewGroup implements FlexContainer {
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    private int mAlignContent;
    private int mAlignItems;
    private Drawable mDividerDrawableHorizontal;
    private Drawable mDividerDrawableVertical;
    private int mDividerHorizontalHeight;
    private int mDividerVerticalWidth;
    private int mFlexDirection;
    private List<FlexLine> mFlexLines;
    private FlexboxHelper.FlexLinesResult mFlexLinesResult;
    private int mFlexWrap;
    private FlexboxHelper mFlexboxHelper;
    private int mJustifyContent;
    private int mMaxLine;
    private SparseIntArray mOrderCache;
    private int[] mReorderedIndices;
    private int mShowDividerHorizontal;
    private int mShowDividerVertical;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMaxLine = -1;
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mFlexLines = new ArrayList();
        this.mFlexLinesResult = new FlexboxHelper.FlexLinesResult();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlexboxLayout, defStyleAttr, 0);
        this.mFlexDirection = a.getInt(R.styleable.FlexboxLayout_flexDirection, 0);
        this.mFlexWrap = a.getInt(R.styleable.FlexboxLayout_flexWrap, 0);
        this.mJustifyContent = a.getInt(R.styleable.FlexboxLayout_justifyContent, 0);
        this.mAlignItems = a.getInt(R.styleable.FlexboxLayout_alignItems, 0);
        this.mAlignContent = a.getInt(R.styleable.FlexboxLayout_alignContent, 0);
        this.mMaxLine = a.getInt(R.styleable.FlexboxLayout_maxLine, -1);
        Drawable drawable = a.getDrawable(R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawableHorizontal = a.getDrawable(R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawableHorizontal != null) {
            setDividerDrawableHorizontal(drawableHorizontal);
        }
        Drawable drawableVertical = a.getDrawable(R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawableVertical != null) {
            setDividerDrawableVertical(drawableVertical);
        }
        int dividerMode = a.getInt(R.styleable.FlexboxLayout_showDivider, 0);
        if (dividerMode != 0) {
            this.mShowDividerVertical = dividerMode;
            this.mShowDividerHorizontal = dividerMode;
        }
        int dividerModeVertical = a.getInt(R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (dividerModeVertical != 0) {
            this.mShowDividerVertical = dividerModeVertical;
        }
        int dividerModeHorizontal = a.getInt(R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (dividerModeHorizontal != 0) {
            this.mShowDividerHorizontal = dividerModeHorizontal;
        }
        a.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        if (this.mFlexboxHelper.isOrderChangedFromLastMeasurement(this.mOrderCache)) {
            this.mReorderedIndices = this.mFlexboxHelper.createReorderedIndices(this.mOrderCache);
        }
        switch (this.mFlexDirection) {
            case 0:
            case 1:
                measureHorizontal(widthMeasureSpec, heightMeasureSpec);
                return;
            case 2:
            case 3:
                measureVertical(widthMeasureSpec, heightMeasureSpec);
                return;
            default:
                throw new IllegalStateException("Invalid value for the flex direction is set: " + this.mFlexDirection);
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexItemCount() {
        return getChildCount();
    }

    @Override // com.google.android.flexbox.FlexContainer
    public View getFlexItemAt(int index) {
        return getChildAt(index);
    }

    public View getReorderedChildAt(int index) {
        if (index < 0 || index >= this.mReorderedIndices.length) {
            return null;
        }
        return getChildAt(this.mReorderedIndices[index]);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public View getReorderedFlexItemAt(int index) {
        return getReorderedChildAt(index);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        this.mReorderedIndices = this.mFlexboxHelper.createReorderedIndices(child, index, params, this.mOrderCache);
        super.addView(child, index, params);
    }

    private void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {
        this.mFlexLines.clear();
        this.mFlexLinesResult.reset();
        this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec);
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.determineMainSize(widthMeasureSpec, heightMeasureSpec);
        if (this.mAlignItems == 3) {
            for (FlexLine flexLine : this.mFlexLines) {
                int largestHeightInLine = Integer.MIN_VALUE;
                for (int i = 0; i < flexLine.mItemCount; i++) {
                    int viewIndex = flexLine.mFirstIndex + i;
                    View child = getReorderedChildAt(viewIndex);
                    if (child != null && child.getVisibility() != 8) {
                        LayoutParams lp = (LayoutParams) child.getLayoutParams();
                        if (this.mFlexWrap != 2) {
                            int marginTop = flexLine.mMaxBaseline - child.getBaseline();
                            largestHeightInLine = Math.max(largestHeightInLine, child.getMeasuredHeight() + Math.max(marginTop, lp.topMargin) + lp.bottomMargin);
                        } else {
                            int marginBottom = (flexLine.mMaxBaseline - child.getMeasuredHeight()) + child.getBaseline();
                            largestHeightInLine = Math.max(largestHeightInLine, child.getMeasuredHeight() + lp.topMargin + Math.max(marginBottom, lp.bottomMargin));
                        }
                    }
                }
                flexLine.mCrossSize = largestHeightInLine;
            }
        }
        this.mFlexboxHelper.determineCrossSize(widthMeasureSpec, heightMeasureSpec, getPaddingTop() + getPaddingBottom());
        this.mFlexboxHelper.stretchViews();
        setMeasuredDimensionForFlex(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, this.mFlexLinesResult.mChildState);
    }

    private void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
        this.mFlexLines.clear();
        this.mFlexLinesResult.reset();
        this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec);
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.determineMainSize(widthMeasureSpec, heightMeasureSpec);
        this.mFlexboxHelper.determineCrossSize(widthMeasureSpec, heightMeasureSpec, getPaddingLeft() + getPaddingRight());
        this.mFlexboxHelper.stretchViews();
        setMeasuredDimensionForFlex(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, this.mFlexLinesResult.mChildState);
    }

    private void setMeasuredDimensionForFlex(int flexDirection, int widthMeasureSpec, int heightMeasureSpec, int childState) {
        int calculatedMaxHeight;
        int calculatedMaxWidth;
        int widthSizeAndState;
        int heightSizeAndState;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        switch (flexDirection) {
            case 0:
            case 1:
                int calculatedMaxHeight2 = getSumOfCrossSize();
                calculatedMaxHeight = calculatedMaxHeight2 + getPaddingTop() + getPaddingBottom();
                calculatedMaxWidth = getLargestMainSize();
                break;
            case 2:
            case 3:
                calculatedMaxHeight = getLargestMainSize();
                calculatedMaxWidth = getSumOfCrossSize() + getPaddingLeft() + getPaddingRight();
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        switch (widthMode) {
            case Integer.MIN_VALUE:
                if (widthSize < calculatedMaxWidth) {
                    childState = View.combineMeasuredStates(childState, 16777216);
                } else {
                    widthSize = calculatedMaxWidth;
                }
                widthSizeAndState = View.resolveSizeAndState(widthSize, widthMeasureSpec, childState);
                break;
            case 0:
                widthSizeAndState = View.resolveSizeAndState(calculatedMaxWidth, widthMeasureSpec, childState);
                break;
            case 1073741824:
                if (widthSize < calculatedMaxWidth) {
                    childState = View.combineMeasuredStates(childState, 16777216);
                }
                widthSizeAndState = View.resolveSizeAndState(widthSize, widthMeasureSpec, childState);
                break;
            default:
                throw new IllegalStateException("Unknown width mode is set: " + widthMode);
        }
        switch (heightMode) {
            case Integer.MIN_VALUE:
                if (heightSize < calculatedMaxHeight) {
                    childState = View.combineMeasuredStates(childState, 256);
                } else {
                    heightSize = calculatedMaxHeight;
                }
                heightSizeAndState = View.resolveSizeAndState(heightSize, heightMeasureSpec, childState);
                break;
            case 0:
                heightSizeAndState = View.resolveSizeAndState(calculatedMaxHeight, heightMeasureSpec, childState);
                break;
            case 1073741824:
                if (heightSize < calculatedMaxHeight) {
                    childState = View.combineMeasuredStates(childState, 256);
                }
                heightSizeAndState = View.resolveSizeAndState(heightSize, heightMeasureSpec, childState);
                break;
            default:
                throw new IllegalStateException("Unknown height mode is set: " + heightMode);
        }
        setMeasuredDimension(widthSizeAndState, heightSizeAndState);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getLargestMainSize() {
        int largestSize = Integer.MIN_VALUE;
        for (FlexLine flexLine : this.mFlexLines) {
            largestSize = Math.max(largestSize, flexLine.mMainSize);
        }
        return largestSize;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getSumOfCrossSize() {
        int sum = 0;
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                if (isMainAxisDirectionHorizontal()) {
                    sum += this.mDividerHorizontalHeight;
                } else {
                    sum += this.mDividerVerticalWidth;
                }
            }
            if (hasEndDividerAfterFlexLine(i)) {
                if (isMainAxisDirectionHorizontal()) {
                    sum += this.mDividerHorizontalHeight;
                } else {
                    sum += this.mDividerVerticalWidth;
                }
            }
            sum += flexLine.mCrossSize;
        }
        return sum;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public boolean isMainAxisDirectionHorizontal() {
        return this.mFlexDirection == 0 || this.mFlexDirection == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        boolean isRtl;
        boolean isRtl2;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        switch (this.mFlexDirection) {
            case 0:
                boolean isRtl3 = layoutDirection == 1;
                layoutHorizontal(isRtl3, left, top, right, bottom);
                return;
            case 1:
                boolean isRtl4 = layoutDirection != 1;
                layoutHorizontal(isRtl4, left, top, right, bottom);
                return;
            case 2:
                boolean isRtl5 = layoutDirection == 1;
                if (this.mFlexWrap != 2) {
                    isRtl = isRtl5;
                } else {
                    boolean isRtl6 = isRtl5 ? false : true;
                    isRtl = isRtl6;
                }
                layoutVertical(isRtl, false, left, top, right, bottom);
                return;
            case 3:
                boolean isRtl7 = layoutDirection == 1;
                if (this.mFlexWrap != 2) {
                    isRtl2 = isRtl7;
                } else {
                    boolean isRtl8 = isRtl7 ? false : true;
                    isRtl2 = isRtl8;
                }
                layoutVertical(isRtl2, true, left, top, right, bottom);
                return;
            default:
                throw new IllegalStateException("Invalid flex direction is set: " + this.mFlexDirection);
        }
    }

    private void layoutHorizontal(boolean isRtl, int left, int top, int right, int bottom) {
        float childLeft;
        float childRight;
        int paddingLeft;
        int paddingRight;
        int j;
        boolean z;
        int beforeDividerLength;
        float childLeft2;
        float childRight2;
        LayoutParams lp;
        int paddingLeft2 = getPaddingLeft();
        int paddingRight2 = getPaddingRight();
        int height = bottom - top;
        int width = right - left;
        int childBottom = height - getPaddingBottom();
        int childTop = getPaddingTop();
        int i = 0;
        int size = this.mFlexLines.size();
        while (i < size) {
            FlexLine flexLine = this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                childBottom -= this.mDividerHorizontalHeight;
                childTop += this.mDividerHorizontalHeight;
            }
            float spaceBetweenItem = 0.0f;
            boolean z2 = true;
            switch (this.mJustifyContent) {
                case 0:
                    childLeft = paddingLeft2;
                    childRight = width - paddingRight2;
                    break;
                case 1:
                    childLeft = (width - flexLine.mMainSize) + paddingRight2;
                    childRight = flexLine.mMainSize - paddingLeft2;
                    break;
                case 2:
                    float childLeft3 = paddingLeft2 + ((width - flexLine.mMainSize) / 2.0f);
                    childRight = (width - paddingRight2) - ((width - flexLine.mMainSize) / 2.0f);
                    childLeft = childLeft3;
                    break;
                case 3:
                    childLeft = paddingLeft2;
                    int visibleCount = flexLine.getItemCountNotGone();
                    float denominator = visibleCount != 1 ? visibleCount - 1 : 1.0f;
                    spaceBetweenItem = (width - flexLine.mMainSize) / denominator;
                    float childRight3 = width - paddingRight2;
                    childRight = childRight3;
                    break;
                case 4:
                    int visibleCount2 = flexLine.getItemCountNotGone();
                    if (visibleCount2 != 0) {
                        spaceBetweenItem = (width - flexLine.mMainSize) / visibleCount2;
                    }
                    float childLeft4 = paddingLeft2 + (spaceBetweenItem / 2.0f);
                    float childRight4 = (width - paddingRight2) - (spaceBetweenItem / 2.0f);
                    childRight = childRight4;
                    childLeft = childLeft4;
                    break;
                case 5:
                    int visibleCount3 = flexLine.getItemCountNotGone();
                    if (visibleCount3 != 0) {
                        spaceBetweenItem = (width - flexLine.mMainSize) / (visibleCount3 + 1);
                    }
                    childLeft = paddingLeft2 + spaceBetweenItem;
                    childRight = (width - paddingRight2) - spaceBetweenItem;
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
            }
            float spaceBetweenItem2 = Math.max(spaceBetweenItem, 0.0f);
            int j2 = 0;
            while (j2 < flexLine.mItemCount) {
                int index = flexLine.mFirstIndex + j2;
                View child = getReorderedChildAt(index);
                if (child == null) {
                    paddingLeft = paddingLeft2;
                    paddingRight = paddingRight2;
                    j = j2;
                    z = z2;
                } else if (child.getVisibility() == 8) {
                    paddingLeft = paddingLeft2;
                    paddingRight = paddingRight2;
                    j = j2;
                    z = true;
                } else {
                    LayoutParams lp2 = (LayoutParams) child.getLayoutParams();
                    float childLeft5 = childLeft + lp2.leftMargin;
                    float childRight5 = childRight - lp2.rightMargin;
                    int endDividerLength = 0;
                    if (!hasDividerBeforeChildAtAlongMainAxis(index, j2)) {
                        paddingLeft = paddingLeft2;
                        beforeDividerLength = 0;
                        childLeft2 = childLeft5;
                        childRight2 = childRight5;
                    } else {
                        int beforeDividerLength2 = this.mDividerVerticalWidth;
                        paddingLeft = paddingLeft2;
                        beforeDividerLength = beforeDividerLength2;
                        childLeft2 = childLeft5 + beforeDividerLength2;
                        childRight2 = childRight5 - beforeDividerLength2;
                    }
                    int beforeDividerLength3 = flexLine.mItemCount;
                    if (j2 == beforeDividerLength3 - 1 && (this.mShowDividerVertical & 4) > 0) {
                        endDividerLength = this.mDividerVerticalWidth;
                    }
                    if (this.mFlexWrap == 2) {
                        if (isRtl) {
                            FlexboxHelper flexboxHelper = this.mFlexboxHelper;
                            int round = Math.round(childRight2) - child.getMeasuredWidth();
                            int paddingRight3 = childBottom - child.getMeasuredHeight();
                            j = j2;
                            paddingRight = paddingRight2;
                            lp = lp2;
                            z = true;
                            flexboxHelper.layoutSingleChildHorizontal(child, flexLine, round, paddingRight3, Math.round(childRight2), childBottom);
                        } else {
                            paddingRight = paddingRight2;
                            j = j2;
                            lp = lp2;
                            z = true;
                            this.mFlexboxHelper.layoutSingleChildHorizontal(child, flexLine, Math.round(childLeft2), childBottom - child.getMeasuredHeight(), Math.round(childLeft2) + child.getMeasuredWidth(), childBottom);
                        }
                    } else {
                        paddingRight = paddingRight2;
                        j = j2;
                        lp = lp2;
                        z = true;
                        if (isRtl) {
                            this.mFlexboxHelper.layoutSingleChildHorizontal(child, flexLine, Math.round(childRight2) - child.getMeasuredWidth(), childTop, Math.round(childRight2), childTop + child.getMeasuredHeight());
                        } else {
                            this.mFlexboxHelper.layoutSingleChildHorizontal(child, flexLine, Math.round(childLeft2), childTop, Math.round(childLeft2) + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
                        }
                    }
                    float childLeft6 = childLeft2 + child.getMeasuredWidth() + spaceBetweenItem2 + lp.rightMargin;
                    float childRight6 = childRight2 - ((child.getMeasuredWidth() + spaceBetweenItem2) + lp.leftMargin);
                    if (isRtl) {
                        flexLine.updatePositionFromView(child, endDividerLength, 0, beforeDividerLength, 0);
                    } else {
                        flexLine.updatePositionFromView(child, beforeDividerLength, 0, endDividerLength, 0);
                    }
                    childLeft = childLeft6;
                    childRight = childRight6;
                }
                j2 = j + 1;
                paddingLeft2 = paddingLeft;
                paddingRight2 = paddingRight;
                z2 = z;
            }
            int paddingLeft3 = paddingLeft2;
            int paddingLeft4 = flexLine.mCrossSize;
            childTop += paddingLeft4;
            childBottom -= flexLine.mCrossSize;
            i++;
            paddingLeft2 = paddingLeft3;
        }
    }

    private void layoutVertical(boolean isRtl, boolean fromBottomToTop, int left, int top, int right, int bottom) {
        float childTop;
        float childBottom;
        int paddingTop;
        int paddingBottom;
        int j;
        int beforeDividerLength;
        float childTop2;
        float childBottom2;
        int endDividerLength;
        int paddingTop2 = getPaddingTop();
        int paddingBottom2 = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int childLeft = getPaddingLeft();
        int width = right - left;
        int height = bottom - top;
        int childRight = width - paddingRight;
        int i = 0;
        int size = this.mFlexLines.size();
        while (i < size) {
            FlexLine flexLine = this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                childLeft += this.mDividerVerticalWidth;
                childRight -= this.mDividerVerticalWidth;
            }
            float spaceBetweenItem = 0.0f;
            switch (this.mJustifyContent) {
                case 0:
                    childTop = paddingTop2;
                    childBottom = height - paddingBottom2;
                    break;
                case 1:
                    childTop = (height - flexLine.mMainSize) + paddingBottom2;
                    childBottom = flexLine.mMainSize - paddingTop2;
                    break;
                case 2:
                    float childTop3 = paddingTop2 + ((height - flexLine.mMainSize) / 2.0f);
                    childBottom = (height - paddingBottom2) - ((height - flexLine.mMainSize) / 2.0f);
                    childTop = childTop3;
                    break;
                case 3:
                    childTop = paddingTop2;
                    int visibleCount = flexLine.getItemCountNotGone();
                    float denominator = visibleCount != 1 ? visibleCount - 1 : 1.0f;
                    spaceBetweenItem = (height - flexLine.mMainSize) / denominator;
                    float childBottom3 = height - paddingBottom2;
                    childBottom = childBottom3;
                    break;
                case 4:
                    int visibleCount2 = flexLine.getItemCountNotGone();
                    if (visibleCount2 != 0) {
                        spaceBetweenItem = (height - flexLine.mMainSize) / visibleCount2;
                    }
                    float childTop4 = paddingTop2 + (spaceBetweenItem / 2.0f);
                    float childBottom4 = (height - paddingBottom2) - (spaceBetweenItem / 2.0f);
                    childBottom = childBottom4;
                    childTop = childTop4;
                    break;
                case 5:
                    int visibleCount3 = flexLine.getItemCountNotGone();
                    if (visibleCount3 != 0) {
                        spaceBetweenItem = (height - flexLine.mMainSize) / (visibleCount3 + 1);
                    }
                    childTop = paddingTop2 + spaceBetweenItem;
                    childBottom = (height - paddingBottom2) - spaceBetweenItem;
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
            }
            float spaceBetweenItem2 = Math.max(spaceBetweenItem, 0.0f);
            int j2 = 0;
            while (j2 < flexLine.mItemCount) {
                int index = flexLine.mFirstIndex + j2;
                View child = getReorderedChildAt(index);
                if (child != null) {
                    paddingTop = paddingTop2;
                    if (child.getVisibility() == 8) {
                        paddingBottom = paddingBottom2;
                        j = j2;
                    } else {
                        LayoutParams lp = (LayoutParams) child.getLayoutParams();
                        float childTop5 = childTop + lp.topMargin;
                        float childBottom5 = childBottom - lp.bottomMargin;
                        if (!hasDividerBeforeChildAtAlongMainAxis(index, j2)) {
                            paddingBottom = paddingBottom2;
                            beforeDividerLength = 0;
                            childTop2 = childTop5;
                            childBottom2 = childBottom5;
                        } else {
                            int beforeDividerLength2 = this.mDividerHorizontalHeight;
                            paddingBottom = paddingBottom2;
                            beforeDividerLength = beforeDividerLength2;
                            childTop2 = childTop5 + beforeDividerLength2;
                            childBottom2 = childBottom5 - beforeDividerLength2;
                        }
                        int beforeDividerLength3 = flexLine.mItemCount;
                        if (j2 == beforeDividerLength3 - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                            endDividerLength = this.mDividerHorizontalHeight;
                        } else {
                            endDividerLength = 0;
                        }
                        if (!isRtl) {
                            j = j2;
                            if (fromBottomToTop) {
                                this.mFlexboxHelper.layoutSingleChildVertical(child, flexLine, false, childLeft, Math.round(childBottom2) - child.getMeasuredHeight(), childLeft + child.getMeasuredWidth(), Math.round(childBottom2));
                            } else {
                                this.mFlexboxHelper.layoutSingleChildVertical(child, flexLine, false, childLeft, Math.round(childTop2), childLeft + child.getMeasuredWidth(), Math.round(childTop2) + child.getMeasuredHeight());
                            }
                        } else if (fromBottomToTop) {
                            j = j2;
                            this.mFlexboxHelper.layoutSingleChildVertical(child, flexLine, true, childRight - child.getMeasuredWidth(), Math.round(childBottom2) - child.getMeasuredHeight(), childRight, Math.round(childBottom2));
                        } else {
                            j = j2;
                            this.mFlexboxHelper.layoutSingleChildVertical(child, flexLine, true, childRight - child.getMeasuredWidth(), Math.round(childTop2), childRight, Math.round(childTop2) + child.getMeasuredHeight());
                        }
                        float childTop6 = childTop2 + child.getMeasuredHeight() + spaceBetweenItem2 + lp.bottomMargin;
                        float childBottom6 = childBottom2 - ((child.getMeasuredHeight() + spaceBetweenItem2) + lp.topMargin);
                        if (fromBottomToTop) {
                            flexLine.updatePositionFromView(child, 0, endDividerLength, 0, beforeDividerLength);
                        } else {
                            flexLine.updatePositionFromView(child, 0, beforeDividerLength, 0, endDividerLength);
                        }
                        childTop = childTop6;
                        childBottom = childBottom6;
                    }
                } else {
                    paddingTop = paddingTop2;
                    paddingBottom = paddingBottom2;
                    j = j2;
                }
                j2 = j + 1;
                paddingTop2 = paddingTop;
                paddingBottom2 = paddingBottom;
            }
            int paddingTop3 = paddingTop2;
            int paddingTop4 = flexLine.mCrossSize;
            childLeft += paddingTop4;
            childRight -= flexLine.mCrossSize;
            i++;
            paddingTop2 = paddingTop3;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDividerDrawableVertical == null && this.mDividerDrawableHorizontal == null) {
        }
        if (this.mShowDividerHorizontal == 0 && this.mShowDividerVertical == 0) {
            return;
        }
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        boolean fromBottomToTop = false;
        switch (this.mFlexDirection) {
            case 0:
                boolean isRtl = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    fromBottomToTop = true;
                }
                drawDividersHorizontal(canvas, isRtl, fromBottomToTop);
                break;
            case 1:
                boolean isRtl2 = layoutDirection != 1;
                if (this.mFlexWrap == 2) {
                    fromBottomToTop = true;
                }
                drawDividersHorizontal(canvas, isRtl2, fromBottomToTop);
                break;
            case 2:
                boolean isRtl3 = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    isRtl3 = isRtl3 ? false : true;
                }
                drawDividersVertical(canvas, isRtl3, false);
                break;
            case 3:
                boolean isRtl4 = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    isRtl4 = isRtl4 ? false : true;
                }
                drawDividersVertical(canvas, isRtl4, true);
                break;
        }
    }

    private void drawDividersHorizontal(Canvas canvas, boolean isRtl, boolean fromBottomToTop) {
        int horizontalDividerTop;
        int horizontalDividerTop2;
        int dividerLeft;
        int dividerLeft2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int horizontalDividerLength = Math.max(0, (getWidth() - paddingRight) - paddingLeft);
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.mFlexLines.get(i);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                int viewIndex = flexLine.mFirstIndex + j;
                View view = getReorderedChildAt(viewIndex);
                if (view != null && view.getVisibility() != 8) {
                    LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(viewIndex, j)) {
                        if (isRtl) {
                            dividerLeft2 = view.getRight() + lp.rightMargin;
                        } else {
                            int dividerLeft3 = view.getLeft();
                            dividerLeft2 = (dividerLeft3 - lp.leftMargin) - this.mDividerVerticalWidth;
                        }
                        drawVerticalDivider(canvas, dividerLeft2, flexLine.mTop, flexLine.mCrossSize);
                    }
                    int dividerLeft4 = flexLine.mItemCount;
                    if (j == dividerLeft4 - 1 && (this.mShowDividerVertical & 4) > 0) {
                        if (isRtl) {
                            dividerLeft = (view.getLeft() - lp.leftMargin) - this.mDividerVerticalWidth;
                        } else {
                            int dividerLeft5 = view.getRight();
                            dividerLeft = dividerLeft5 + lp.rightMargin;
                        }
                        drawVerticalDivider(canvas, dividerLeft, flexLine.mTop, flexLine.mCrossSize);
                    }
                }
            }
            if (hasDividerBeforeFlexLine(i)) {
                if (fromBottomToTop) {
                    horizontalDividerTop2 = flexLine.mBottom;
                } else {
                    int horizontalDividerTop3 = flexLine.mTop;
                    horizontalDividerTop2 = horizontalDividerTop3 - this.mDividerHorizontalHeight;
                }
                drawHorizontalDivider(canvas, paddingLeft, horizontalDividerTop2, horizontalDividerLength);
            }
            if (hasEndDividerAfterFlexLine(i) && (this.mShowDividerHorizontal & 4) > 0) {
                if (fromBottomToTop) {
                    horizontalDividerTop = flexLine.mTop - this.mDividerHorizontalHeight;
                } else {
                    horizontalDividerTop = flexLine.mBottom;
                }
                drawHorizontalDivider(canvas, paddingLeft, horizontalDividerTop, horizontalDividerLength);
            }
        }
    }

    private void drawDividersVertical(Canvas canvas, boolean isRtl, boolean fromBottomToTop) {
        int verticalDividerLeft;
        int verticalDividerLeft2;
        int dividerTop;
        int dividerTop2;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int verticalDividerLength = Math.max(0, (getHeight() - paddingBottom) - paddingTop);
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.mFlexLines.get(i);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                int viewIndex = flexLine.mFirstIndex + j;
                View view = getReorderedChildAt(viewIndex);
                if (view != null && view.getVisibility() != 8) {
                    LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(viewIndex, j)) {
                        if (fromBottomToTop) {
                            dividerTop2 = view.getBottom() + lp.bottomMargin;
                        } else {
                            int dividerTop3 = view.getTop();
                            dividerTop2 = (dividerTop3 - lp.topMargin) - this.mDividerHorizontalHeight;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, dividerTop2, flexLine.mCrossSize);
                    }
                    int dividerTop4 = flexLine.mItemCount;
                    if (j == dividerTop4 - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                        if (fromBottomToTop) {
                            dividerTop = (view.getTop() - lp.topMargin) - this.mDividerHorizontalHeight;
                        } else {
                            int dividerTop5 = view.getBottom();
                            dividerTop = dividerTop5 + lp.bottomMargin;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, dividerTop, flexLine.mCrossSize);
                    }
                }
            }
            if (hasDividerBeforeFlexLine(i)) {
                if (isRtl) {
                    verticalDividerLeft2 = flexLine.mRight;
                } else {
                    int verticalDividerLeft3 = flexLine.mLeft;
                    verticalDividerLeft2 = verticalDividerLeft3 - this.mDividerVerticalWidth;
                }
                drawVerticalDivider(canvas, verticalDividerLeft2, paddingTop, verticalDividerLength);
            }
            if (hasEndDividerAfterFlexLine(i) && (this.mShowDividerVertical & 4) > 0) {
                if (isRtl) {
                    verticalDividerLeft = flexLine.mLeft - this.mDividerVerticalWidth;
                } else {
                    verticalDividerLeft = flexLine.mRight;
                }
                drawVerticalDivider(canvas, verticalDividerLeft, paddingTop, verticalDividerLength);
            }
        }
    }

    private void drawVerticalDivider(Canvas canvas, int left, int top, int length) {
        if (this.mDividerDrawableVertical == null) {
            return;
        }
        this.mDividerDrawableVertical.setBounds(left, top, this.mDividerVerticalWidth + left, top + length);
        this.mDividerDrawableVertical.draw(canvas);
    }

    private void drawHorizontalDivider(Canvas canvas, int left, int top, int length) {
        if (this.mDividerDrawableHorizontal == null) {
            return;
        }
        this.mDividerDrawableHorizontal.setBounds(left, top, left + length, this.mDividerHorizontalHeight + top);
        this.mDividerDrawableHorizontal.draw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        if (lp instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) lp);
        }
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) lp);
        }
        return new LayoutParams(lp);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexDirection(int flexDirection) {
        if (this.mFlexDirection != flexDirection) {
            this.mFlexDirection = flexDirection;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexWrap(int flexWrap) {
        if (this.mFlexWrap != flexWrap) {
            this.mFlexWrap = flexWrap;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setJustifyContent(int justifyContent) {
        if (this.mJustifyContent != justifyContent) {
            this.mJustifyContent = justifyContent;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getAlignItems() {
        return this.mAlignItems;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setAlignItems(int alignItems) {
        if (this.mAlignItems != alignItems) {
            this.mAlignItems = alignItems;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getAlignContent() {
        return this.mAlignContent;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setAlignContent(int alignContent) {
        if (this.mAlignContent != alignContent) {
            this.mAlignContent = alignContent;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getMaxLine() {
        return this.mMaxLine;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setMaxLine(int maxLine) {
        if (this.mMaxLine != maxLine) {
            this.mMaxLine = maxLine;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public List<FlexLine> getFlexLines() {
        List<FlexLine> result = new ArrayList<>(this.mFlexLines.size());
        for (FlexLine flexLine : this.mFlexLines) {
            if (flexLine.getItemCountNotGone() != 0) {
                result.add(flexLine);
            }
        }
        return result;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getDecorationLengthMainAxis(View view, int index, int indexInFlexLine) {
        int decorationLength;
        if (isMainAxisDirectionHorizontal()) {
            decorationLength = hasDividerBeforeChildAtAlongMainAxis(index, indexInFlexLine) ? 0 + this.mDividerVerticalWidth : 0;
            if ((this.mShowDividerVertical & 4) > 0) {
                return decorationLength + this.mDividerVerticalWidth;
            }
            return decorationLength;
        }
        decorationLength = hasDividerBeforeChildAtAlongMainAxis(index, indexInFlexLine) ? 0 + this.mDividerHorizontalHeight : 0;
        if ((this.mShowDividerHorizontal & 4) > 0) {
            return decorationLength + this.mDividerHorizontalHeight;
        }
        return decorationLength;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getDecorationLengthCrossAxis(View view) {
        return 0;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void onNewFlexLineAdded(FlexLine flexLine) {
        if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerVertical & 4) > 0) {
                flexLine.mMainSize += this.mDividerVerticalWidth;
                flexLine.mDividerLengthInMainSize += this.mDividerVerticalWidth;
                return;
            }
            return;
        }
        if ((this.mShowDividerHorizontal & 4) > 0) {
            flexLine.mMainSize += this.mDividerHorizontalHeight;
            flexLine.mDividerLengthInMainSize += this.mDividerHorizontalHeight;
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getChildWidthMeasureSpec(int widthSpec, int padding, int childDimension) {
        return getChildMeasureSpec(widthSpec, padding, childDimension);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getChildHeightMeasureSpec(int heightSpec, int padding, int childDimension) {
        return getChildMeasureSpec(heightSpec, padding, childDimension);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void onNewFlexItemAdded(View view, int index, int indexInFlexLine, FlexLine flexLine) {
        if (hasDividerBeforeChildAtAlongMainAxis(index, indexInFlexLine)) {
            if (isMainAxisDirectionHorizontal()) {
                flexLine.mMainSize += this.mDividerVerticalWidth;
                flexLine.mDividerLengthInMainSize += this.mDividerVerticalWidth;
            } else {
                flexLine.mMainSize += this.mDividerHorizontalHeight;
                flexLine.mDividerLengthInMainSize += this.mDividerHorizontalHeight;
            }
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexLines(List<FlexLine> flexLines) {
        this.mFlexLines = flexLines;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public List<FlexLine> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void updateViewCache(int position, View view) {
    }

    public Drawable getDividerDrawableHorizontal() {
        return this.mDividerDrawableHorizontal;
    }

    public Drawable getDividerDrawableVertical() {
        return this.mDividerDrawableVertical;
    }

    public void setDividerDrawable(Drawable divider) {
        setDividerDrawableHorizontal(divider);
        setDividerDrawableVertical(divider);
    }

    public void setDividerDrawableHorizontal(Drawable divider) {
        if (divider == this.mDividerDrawableHorizontal) {
            return;
        }
        this.mDividerDrawableHorizontal = divider;
        if (divider != null) {
            this.mDividerHorizontalHeight = divider.getIntrinsicHeight();
        } else {
            this.mDividerHorizontalHeight = 0;
        }
        setWillNotDrawFlag();
        requestLayout();
    }

    public void setDividerDrawableVertical(Drawable divider) {
        if (divider == this.mDividerDrawableVertical) {
            return;
        }
        this.mDividerDrawableVertical = divider;
        if (divider != null) {
            this.mDividerVerticalWidth = divider.getIntrinsicWidth();
        } else {
            this.mDividerVerticalWidth = 0;
        }
        setWillNotDrawFlag();
        requestLayout();
    }

    public int getShowDividerVertical() {
        return this.mShowDividerVertical;
    }

    public int getShowDividerHorizontal() {
        return this.mShowDividerHorizontal;
    }

    public void setShowDivider(int dividerMode) {
        setShowDividerVertical(dividerMode);
        setShowDividerHorizontal(dividerMode);
    }

    public void setShowDividerVertical(int dividerMode) {
        if (dividerMode != this.mShowDividerVertical) {
            this.mShowDividerVertical = dividerMode;
            requestLayout();
        }
    }

    public void setShowDividerHorizontal(int dividerMode) {
        if (dividerMode != this.mShowDividerHorizontal) {
            this.mShowDividerHorizontal = dividerMode;
            requestLayout();
        }
    }

    private void setWillNotDrawFlag() {
        if (this.mDividerDrawableHorizontal == null && this.mDividerDrawableVertical == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private boolean hasDividerBeforeChildAtAlongMainAxis(int index, int indexInFlexLine) {
        return allViewsAreGoneBefore(index, indexInFlexLine) ? isMainAxisDirectionHorizontal() ? (this.mShowDividerVertical & 1) != 0 : (this.mShowDividerHorizontal & 1) != 0 : isMainAxisDirectionHorizontal() ? (this.mShowDividerVertical & 2) != 0 : (this.mShowDividerHorizontal & 2) != 0;
    }

    private boolean allViewsAreGoneBefore(int index, int indexInFlexLine) {
        for (int i = 1; i <= indexInFlexLine; i++) {
            View view = getReorderedChildAt(index - i);
            if (view != null && view.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDividerBeforeFlexLine(int flexLineIndex) {
        if (flexLineIndex < 0 || flexLineIndex >= this.mFlexLines.size()) {
            return false;
        }
        return allFlexLinesAreDummyBefore(flexLineIndex) ? isMainAxisDirectionHorizontal() ? (this.mShowDividerHorizontal & 1) != 0 : (this.mShowDividerVertical & 1) != 0 : isMainAxisDirectionHorizontal() ? (this.mShowDividerHorizontal & 2) != 0 : (this.mShowDividerVertical & 2) != 0;
    }

    private boolean allFlexLinesAreDummyBefore(int flexLineIndex) {
        for (int i = 0; i < flexLineIndex; i++) {
            if (this.mFlexLines.get(i).getItemCountNotGone() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean hasEndDividerAfterFlexLine(int flexLineIndex) {
        if (flexLineIndex < 0 || flexLineIndex >= this.mFlexLines.size()) {
            return false;
        }
        for (int i = flexLineIndex + 1; i < this.mFlexLines.size(); i++) {
            if (this.mFlexLines.get(i).getItemCountNotGone() > 0) {
                return false;
            }
        }
        return isMainAxisDirectionHorizontal() ? (this.mShowDividerHorizontal & 4) != 0 : (this.mShowDividerVertical & 4) != 0;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new Parcelable.Creator<LayoutParams>() { // from class: com.google.android.flexbox.FlexboxLayout.LayoutParams.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LayoutParams createFromParcel(Parcel source) {
                return new LayoutParams(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LayoutParams[] newArray(int size) {
                return new LayoutParams[size];
            }
        };
        private int mAlignSelf;
        private float mFlexBasisPercent;
        private float mFlexGrow;
        private float mFlexShrink;
        private int mMaxHeight;
        private int mMaxWidth;
        private int mMinHeight;
        private int mMinWidth;
        private int mOrder;
        private boolean mWrapBefore;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.mOrder = 1;
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMinWidth = -1;
            this.mMinHeight = -1;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlexboxLayout_Layout);
            this.mOrder = a.getInt(R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.mFlexGrow = a.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.mFlexShrink = a.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.mAlignSelf = a.getInt(R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.mFlexBasisPercent = a.getFraction(R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.mMinWidth = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minWidth, -1);
            this.mMinHeight = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minHeight, -1);
            this.mMaxWidth = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.mMaxHeight = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.mWrapBefore = a.getBoolean(R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            a.recycle();
        }

        public LayoutParams(LayoutParams source) {
            super((ViewGroup.MarginLayoutParams) source);
            this.mOrder = 1;
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMinWidth = -1;
            this.mMinHeight = -1;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
            this.mOrder = source.mOrder;
            this.mFlexGrow = source.mFlexGrow;
            this.mFlexShrink = source.mFlexShrink;
            this.mAlignSelf = source.mAlignSelf;
            this.mFlexBasisPercent = source.mFlexBasisPercent;
            this.mMinWidth = source.mMinWidth;
            this.mMinHeight = source.mMinHeight;
            this.mMaxWidth = source.mMaxWidth;
            this.mMaxHeight = source.mMaxHeight;
            this.mWrapBefore = source.mWrapBefore;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.mOrder = 1;
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMinWidth = -1;
            this.mMinHeight = -1;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(int width, int height) {
            super(new ViewGroup.LayoutParams(width, height));
            this.mOrder = 1;
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMinWidth = -1;
            this.mMinHeight = -1;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
            this.mOrder = 1;
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMinWidth = -1;
            this.mMinHeight = -1;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getWidth() {
            return this.width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setWidth(int width) {
            this.width = width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getHeight() {
            return this.height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setHeight(int height) {
            this.height = height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return this.mOrder;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setOrder(int order) {
            this.mOrder = order;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexGrow() {
            return this.mFlexGrow;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setFlexGrow(float flexGrow) {
            this.mFlexGrow = flexGrow;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexShrink() {
            return this.mFlexShrink;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setFlexShrink(float flexShrink) {
            this.mFlexShrink = flexShrink;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getAlignSelf() {
            return this.mAlignSelf;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setAlignSelf(int alignSelf) {
            this.mAlignSelf = alignSelf;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinWidth() {
            return this.mMinWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinWidth(int minWidth) {
            this.mMinWidth = minWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinHeight() {
            return this.mMinHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinHeight(int minHeight) {
            this.mMinHeight = minHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxWidth() {
            return this.mMaxWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMaxWidth(int maxWidth) {
            this.mMaxWidth = maxWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxHeight() {
            return this.mMaxHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMaxHeight(int maxHeight) {
            this.mMaxHeight = maxHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean isWrapBefore() {
            return this.mWrapBefore;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setWrapBefore(boolean wrapBefore) {
            this.mWrapBefore = wrapBefore;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexBasisPercent() {
            return this.mFlexBasisPercent;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setFlexBasisPercent(float flexBasisPercent) {
            this.mFlexBasisPercent = flexBasisPercent;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginLeft() {
            return this.leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginTop() {
            return this.topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginRight() {
            return this.rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginBottom() {
            return this.bottomMargin;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mOrder);
            parcel.writeFloat(this.mFlexGrow);
            parcel.writeFloat(this.mFlexShrink);
            parcel.writeInt(this.mAlignSelf);
            parcel.writeFloat(this.mFlexBasisPercent);
            parcel.writeInt(this.mMinWidth);
            parcel.writeInt(this.mMinHeight);
            parcel.writeInt(this.mMaxWidth);
            parcel.writeInt(this.mMaxHeight);
            parcel.writeByte(this.mWrapBefore ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        protected LayoutParams(Parcel in) {
            super(0, 0);
            this.mOrder = 1;
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMinWidth = -1;
            this.mMinHeight = -1;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
            this.mOrder = in.readInt();
            this.mFlexGrow = in.readFloat();
            this.mFlexShrink = in.readFloat();
            this.mAlignSelf = in.readInt();
            this.mFlexBasisPercent = in.readFloat();
            this.mMinWidth = in.readInt();
            this.mMinHeight = in.readInt();
            this.mMaxWidth = in.readInt();
            this.mMaxHeight = in.readInt();
            this.mWrapBefore = in.readByte() != 0;
            this.bottomMargin = in.readInt();
            this.leftMargin = in.readInt();
            this.rightMargin = in.readInt();
            this.topMargin = in.readInt();
            this.height = in.readInt();
            this.width = in.readInt();
        }
    }
}
