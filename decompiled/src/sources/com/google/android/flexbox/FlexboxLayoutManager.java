package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexboxHelper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements FlexContainer, RecyclerView.SmoothScroller.ScrollVectorProvider {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DEBUG = false;
    private static final String TAG = "FlexboxLayoutManager";
    private static final Rect TEMP_RECT = new Rect();
    private int mAlignItems;
    private AnchorInfo mAnchorInfo;
    private final Context mContext;
    private int mDirtyPosition;
    private int mFlexDirection;
    private List<FlexLine> mFlexLines;
    private FlexboxHelper.FlexLinesResult mFlexLinesResult;
    private int mFlexWrap;
    private final FlexboxHelper mFlexboxHelper;
    private boolean mFromBottomToTop;
    private boolean mIsRtl;
    private int mJustifyContent;
    private int mLastHeight;
    private int mLastWidth;
    private LayoutState mLayoutState;
    private int mMaxLine;
    private OrientationHelper mOrientationHelper;
    private View mParent;
    private SavedState mPendingSavedState;
    private int mPendingScrollPosition;
    private int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private RecyclerView.Recycler mRecycler;
    private RecyclerView.State mState;
    private OrientationHelper mSubOrientationHelper;
    private SparseArray<View> mViewCache;

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    public FlexboxLayoutManager(Context context, int flexDirection) {
        this(context, flexDirection, 1);
    }

    public FlexboxLayoutManager(Context context, int flexDirection, int flexWrap) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexboxHelper.FlexLinesResult();
        setFlexDirection(flexDirection);
        setFlexWrap(flexWrap);
        setAlignItems(4);
        this.mContext = context;
    }

    public FlexboxLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexboxHelper.FlexLinesResult();
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attrs, defStyleAttr, defStyleRes);
        switch (properties.orientation) {
            case 0:
                if (properties.reverseLayout) {
                    setFlexDirection(1);
                    break;
                } else {
                    setFlexDirection(0);
                    break;
                }
            case 1:
                if (properties.reverseLayout) {
                    setFlexDirection(3);
                    break;
                } else {
                    setFlexDirection(2);
                    break;
                }
        }
        setFlexWrap(1);
        setAlignItems(4);
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexDirection(int flexDirection) {
        if (this.mFlexDirection != flexDirection) {
            removeAllViews();
            this.mFlexDirection = flexDirection;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            clearFlexLines();
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexWrap(int flexWrap) {
        if (flexWrap == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        }
        if (this.mFlexWrap != flexWrap) {
            if (this.mFlexWrap == 0 || flexWrap == 0) {
                removeAllViews();
                clearFlexLines();
            }
            this.mFlexWrap = flexWrap;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
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
            if (this.mAlignItems == 4 || alignItems == 4) {
                removeAllViews();
                clearFlexLines();
            }
            this.mAlignItems = alignItems;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getAlignContent() {
        return 5;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setAlignContent(int alignContent) {
        throw new UnsupportedOperationException("Setting the alignContent in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to use this attribute.");
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
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.mFlexLines.get(i);
            if (flexLine.getItemCount() != 0) {
                result.add(flexLine);
            }
        }
        return result;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getDecorationLengthMainAxis(View view, int index, int indexInFlexLine) {
        if (isMainAxisDirectionHorizontal()) {
            return getLeftDecorationWidth(view) + getRightDecorationWidth(view);
        }
        return getTopDecorationHeight(view) + getBottomDecorationHeight(view);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getDecorationLengthCrossAxis(View view) {
        if (isMainAxisDirectionHorizontal()) {
            return getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        }
        return getLeftDecorationWidth(view) + getRightDecorationWidth(view);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void onNewFlexItemAdded(View view, int index, int indexInFlexLine, FlexLine flexLine) {
        calculateItemDecorationsForChild(view, TEMP_RECT);
        if (isMainAxisDirectionHorizontal()) {
            int decorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            flexLine.mMainSize += decorationWidth;
            flexLine.mDividerLengthInMainSize += decorationWidth;
        } else {
            int decorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
            flexLine.mMainSize += decorationHeight;
            flexLine.mDividerLengthInMainSize += decorationHeight;
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexItemCount() {
        return this.mState.getItemCount();
    }

    @Override // com.google.android.flexbox.FlexContainer
    public View getFlexItemAt(int index) {
        View cachedView = this.mViewCache.get(index);
        if (cachedView != null) {
            return cachedView;
        }
        return this.mRecycler.getViewForPosition(index);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public View getReorderedFlexItemAt(int index) {
        return getFlexItemAt(index);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void onNewFlexLineAdded(FlexLine flexLine) {
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getChildWidthMeasureSpec(int widthSpec, int padding, int childDimension) {
        return getChildMeasureSpec(getWidth(), getWidthMode(), padding, childDimension, canScrollHorizontally());
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getChildHeightMeasureSpec(int heightSpec, int padding, int childDimension) {
        return getChildMeasureSpec(getHeight(), getHeightMode(), padding, childDimension, canScrollVertically());
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getLargestMainSize() {
        if (this.mFlexLines.size() == 0) {
            return 0;
        }
        int largestSize = Integer.MIN_VALUE;
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.mFlexLines.get(i);
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
            sum += flexLine.mCrossSize;
        }
        return sum;
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
        this.mViewCache.put(position, view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int targetPosition) {
        View view;
        if (getChildCount() == 0 || (view = getChildAt(0)) == null) {
            return null;
        }
        int firstChildPos = getPosition(view);
        int direction = targetPosition < firstChildPos ? -1 : 1;
        if (isMainAxisDirectionHorizontal()) {
            return new PointF(0.0f, direction);
        }
        return new PointF(direction, 0.0f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context c, AttributeSet attrs) {
        return new LayoutParams(c, attrs);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
        return lp instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
        removeAllViews();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            View firstView = getChildClosestToStart();
            savedState.mAnchorPosition = getPosition(firstView);
            savedState.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(firstView) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            savedState.invalidateAnchor();
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            this.mPendingSavedState = (SavedState) state;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int positionStart, int itemCount) {
        super.onItemsAdded(recyclerView, positionStart, itemCount);
        updateDirtyPosition(positionStart);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int positionStart, int itemCount, Object payload) {
        super.onItemsUpdated(recyclerView, positionStart, itemCount, payload);
        updateDirtyPosition(positionStart);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int positionStart, int itemCount) {
        super.onItemsUpdated(recyclerView, positionStart, itemCount);
        updateDirtyPosition(positionStart);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int positionStart, int itemCount) {
        super.onItemsRemoved(recyclerView, positionStart, itemCount);
        updateDirtyPosition(positionStart);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int from, int to, int itemCount) {
        super.onItemsMoved(recyclerView, from, to, itemCount);
        updateDirtyPosition(Math.min(from, to));
    }

    private void updateDirtyPosition(int positionStart) {
        int lastVisiblePosition = findLastVisibleItemPosition();
        if (positionStart >= lastVisiblePosition) {
            return;
        }
        int childCount = getChildCount();
        this.mFlexboxHelper.ensureMeasureSpecCache(childCount);
        this.mFlexboxHelper.ensureMeasuredSizeCache(childCount);
        this.mFlexboxHelper.ensureIndexToFlexLine(childCount);
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        if (positionStart >= this.mFlexboxHelper.mIndexToFlexLine.length) {
            return;
        }
        this.mDirtyPosition = positionStart;
        View firstView = getChildClosestToStart();
        if (firstView == null) {
            return;
        }
        this.mPendingScrollPosition = getPosition(firstView);
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedEnd(firstView) + this.mOrientationHelper.getEndPadding();
        } else {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedStart(firstView) - this.mOrientationHelper.getStartAfterPadding();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int filledToStart;
        int filledToStart2;
        this.mRecycler = recycler;
        this.mState = state;
        int childCount = state.getItemCount();
        if (childCount == 0 && state.isPreLayout()) {
            return;
        }
        resolveLayoutDirection();
        ensureOrientationHelper();
        ensureLayoutState();
        this.mFlexboxHelper.ensureMeasureSpecCache(childCount);
        this.mFlexboxHelper.ensureMeasuredSizeCache(childCount);
        this.mFlexboxHelper.ensureIndexToFlexLine(childCount);
        this.mLayoutState.mShouldRecycle = false;
        if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor(childCount)) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
        }
        if (!this.mAnchorInfo.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            this.mAnchorInfo.reset();
            updateAnchorInfoForLayout(state, this.mAnchorInfo);
            this.mAnchorInfo.mValid = true;
        }
        detachAndScrapAttachedViews(recycler);
        if (this.mAnchorInfo.mLayoutFromEnd) {
            updateLayoutStateToFillStart(this.mAnchorInfo, false, true);
        } else {
            updateLayoutStateToFillEnd(this.mAnchorInfo, false, true);
        }
        updateFlexLines(childCount);
        fill(recycler, state, this.mLayoutState);
        if (this.mAnchorInfo.mLayoutFromEnd) {
            filledToStart2 = this.mLayoutState.mOffset;
            updateLayoutStateToFillEnd(this.mAnchorInfo, true, false);
            fill(recycler, state, this.mLayoutState);
            filledToStart = this.mLayoutState.mOffset;
        } else {
            filledToStart = this.mLayoutState.mOffset;
            updateLayoutStateToFillStart(this.mAnchorInfo, true, false);
            fill(recycler, state, this.mLayoutState);
            filledToStart2 = this.mLayoutState.mOffset;
        }
        if (getChildCount() <= 0) {
            return;
        }
        if (this.mAnchorInfo.mLayoutFromEnd) {
            int fixOffset = fixLayoutEndGap(filledToStart, recycler, state, true);
            int startOffset = filledToStart2 + fixOffset;
            fixLayoutStartGap(startOffset, recycler, state, false);
        } else {
            int fixOffset2 = fixLayoutStartGap(filledToStart2, recycler, state, true);
            int endOffset = filledToStart + fixOffset2;
            fixLayoutEndGap(endOffset, recycler, state, false);
        }
    }

    private int fixLayoutStartGap(int startOffset, RecyclerView.Recycler recycler, RecyclerView.State state, boolean canOffsetChildren) {
        int fixOffset;
        int gap;
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            int gap2 = this.mOrientationHelper.getEndAfterPadding() - startOffset;
            if (gap2 <= 0) {
                return 0;
            }
            fixOffset = handleScrollingMainOrientation(-gap2, recycler, state);
        } else {
            int gap3 = startOffset - this.mOrientationHelper.getStartAfterPadding();
            if (gap3 <= 0) {
                return 0;
            }
            fixOffset = -handleScrollingMainOrientation(gap3, recycler, state);
        }
        int startOffset2 = startOffset + fixOffset;
        if (canOffsetChildren && (gap = startOffset2 - this.mOrientationHelper.getStartAfterPadding()) > 0) {
            this.mOrientationHelper.offsetChildren(-gap);
            return fixOffset - gap;
        }
        return fixOffset;
    }

    private int fixLayoutEndGap(int endOffset, RecyclerView.Recycler recycler, RecyclerView.State state, boolean canOffsetChildren) {
        int fixOffset;
        int gap;
        boolean columnAndRtl = !isMainAxisDirectionHorizontal() && this.mIsRtl;
        if (columnAndRtl) {
            int gap2 = endOffset - this.mOrientationHelper.getStartAfterPadding();
            if (gap2 <= 0) {
                return 0;
            }
            fixOffset = handleScrollingMainOrientation(gap2, recycler, state);
        } else {
            int gap3 = this.mOrientationHelper.getEndAfterPadding() - endOffset;
            if (gap3 <= 0) {
                return 0;
            }
            fixOffset = -handleScrollingMainOrientation(-gap3, recycler, state);
        }
        int endOffset2 = endOffset + fixOffset;
        if (canOffsetChildren && (gap = this.mOrientationHelper.getEndAfterPadding() - endOffset2) > 0) {
            this.mOrientationHelper.offsetChildren(gap);
            return gap + fixOffset;
        }
        return fixOffset;
    }

    private void updateFlexLines(int childCount) {
        int i;
        boolean isMainSizeChanged;
        int needsToFill;
        int width;
        int i2;
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width2 = getWidth();
        int height = getHeight();
        if (isMainAxisDirectionHorizontal()) {
            boolean isMainSizeChanged2 = (this.mLastWidth == Integer.MIN_VALUE || this.mLastWidth == width2) ? false : true;
            if (this.mLayoutState.mInfinite) {
                i2 = this.mContext.getResources().getDisplayMetrics().heightPixels;
            } else {
                i2 = this.mLayoutState.mAvailable;
            }
            isMainSizeChanged = isMainSizeChanged2;
            needsToFill = i2;
        } else {
            boolean isMainSizeChanged3 = (this.mLastHeight == Integer.MIN_VALUE || this.mLastHeight == height) ? false : true;
            if (this.mLayoutState.mInfinite) {
                i = this.mContext.getResources().getDisplayMetrics().widthPixels;
            } else {
                i = this.mLayoutState.mAvailable;
            }
            isMainSizeChanged = isMainSizeChanged3;
            needsToFill = i;
        }
        this.mLastWidth = width2;
        this.mLastHeight = height;
        if (this.mDirtyPosition != -1 || (this.mPendingScrollPosition == -1 && !isMainSizeChanged)) {
            int fromIndex = this.mDirtyPosition != -1 ? Math.min(this.mDirtyPosition, this.mAnchorInfo.mPosition) : this.mAnchorInfo.mPosition;
            this.mFlexLinesResult.reset();
            if (!isMainAxisDirectionHorizontal()) {
                width = fromIndex;
                if (this.mFlexLines.size() > 0) {
                    this.mFlexboxHelper.clearFlexLines(this.mFlexLines, width);
                    this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, heightMeasureSpec, widthMeasureSpec, needsToFill, width, this.mAnchorInfo.mPosition, this.mFlexLines);
                } else {
                    this.mFlexboxHelper.ensureIndexToFlexLine(childCount);
                    this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec, needsToFill, 0, this.mFlexLines);
                }
            } else if (this.mFlexLines.size() > 0) {
                this.mFlexboxHelper.clearFlexLines(this.mFlexLines, fromIndex);
                width = fromIndex;
                this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec, needsToFill, fromIndex, this.mAnchorInfo.mPosition, this.mFlexLines);
            } else {
                width = fromIndex;
                this.mFlexboxHelper.ensureIndexToFlexLine(childCount);
                this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec, needsToFill, 0, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.mFlexLines;
            this.mFlexboxHelper.determineMainSize(widthMeasureSpec, heightMeasureSpec, width);
            this.mFlexboxHelper.stretchViews(width);
            return;
        }
        if (this.mAnchorInfo.mLayoutFromEnd) {
            return;
        }
        this.mFlexLines.clear();
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        this.mFlexLinesResult.reset();
        if (isMainAxisDirectionHorizontal()) {
            this.mFlexboxHelper.calculateHorizontalFlexLinesToIndex(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec, needsToFill, this.mAnchorInfo.mPosition, this.mFlexLines);
        } else {
            this.mFlexboxHelper.calculateVerticalFlexLinesToIndex(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec, needsToFill, this.mAnchorInfo.mPosition, this.mFlexLines);
        }
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.determineMainSize(widthMeasureSpec, heightMeasureSpec);
        this.mFlexboxHelper.stretchViews();
        this.mAnchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[this.mAnchorInfo.mPosition];
        this.mLayoutState.mFlexLinePosition = this.mAnchorInfo.mFlexLinePosition;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mDirtyPosition = -1;
        this.mAnchorInfo.reset();
        this.mViewCache.clear();
    }

    boolean isLayoutRtl() {
        return this.mIsRtl;
    }

    private void resolveLayoutDirection() {
        int layoutDirection = getLayoutDirection();
        switch (this.mFlexDirection) {
            case 0:
                this.mIsRtl = layoutDirection == 1;
                this.mFromBottomToTop = this.mFlexWrap == 2;
                break;
            case 1:
                this.mIsRtl = layoutDirection != 1;
                this.mFromBottomToTop = this.mFlexWrap == 2;
                break;
            case 2:
                this.mIsRtl = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    this.mIsRtl = !this.mIsRtl;
                }
                this.mFromBottomToTop = false;
                break;
            case 3:
                this.mIsRtl = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    this.mIsRtl = !this.mIsRtl;
                }
                this.mFromBottomToTop = true;
                break;
            default:
                this.mIsRtl = false;
                this.mFromBottomToTop = false;
                break;
        }
    }

    private void updateAnchorInfoForLayout(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (updateAnchorFromPendingState(state, anchorInfo, this.mPendingSavedState) || updateAnchorFromChildren(state, anchorInfo)) {
            return;
        }
        anchorInfo.assignCoordinateFromPadding();
        anchorInfo.mPosition = 0;
        anchorInfo.mFlexLinePosition = 0;
    }

    private boolean updateAnchorFromPendingState(RecyclerView.State state, AnchorInfo anchorInfo, SavedState savedState) {
        View view;
        int decoratedStart;
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        if (state.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        anchorInfo.mPosition = this.mPendingScrollPosition;
        anchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[anchorInfo.mPosition];
        if (this.mPendingSavedState == null || !this.mPendingSavedState.hasValidAnchor(state.getItemCount())) {
            if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                View anchorView = findViewByPosition(this.mPendingScrollPosition);
                if (anchorView != null) {
                    if (this.mOrientationHelper.getDecoratedMeasurement(anchorView) <= this.mOrientationHelper.getTotalSpace()) {
                        int startGap = this.mOrientationHelper.getDecoratedStart(anchorView) - this.mOrientationHelper.getStartAfterPadding();
                        if (startGap >= 0) {
                            int endGap = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(anchorView);
                            if (endGap < 0) {
                                anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                                anchorInfo.mLayoutFromEnd = true;
                                return true;
                            }
                            if (anchorInfo.mLayoutFromEnd) {
                                decoratedStart = this.mOrientationHelper.getDecoratedEnd(anchorView) + this.mOrientationHelper.getTotalSpaceChange();
                            } else {
                                decoratedStart = this.mOrientationHelper.getDecoratedStart(anchorView);
                            }
                            anchorInfo.mCoordinate = decoratedStart;
                        } else {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                            anchorInfo.mLayoutFromEnd = false;
                            return true;
                        }
                    } else {
                        anchorInfo.assignCoordinateFromPadding();
                        return true;
                    }
                } else {
                    if (getChildCount() > 0 && (view = getChildAt(0)) != null) {
                        int position = getPosition(view);
                        anchorInfo.mLayoutFromEnd = this.mPendingScrollPosition < position;
                    }
                    anchorInfo.assignCoordinateFromPadding();
                }
                return true;
            }
            if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
            } else {
                anchorInfo.mCoordinate = this.mPendingScrollPositionOffset - this.mOrientationHelper.getEndPadding();
            }
            return true;
        }
        anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + savedState.mAnchorOffset;
        anchorInfo.mAssignedFromSavedState = true;
        anchorInfo.mFlexLinePosition = -1;
        return true;
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, AnchorInfo anchorInfo) {
        View referenceChild;
        int startAfterPadding;
        if (getChildCount() == 0) {
            return false;
        }
        if (anchorInfo.mLayoutFromEnd) {
            referenceChild = findLastReferenceChild(state.getItemCount());
        } else {
            referenceChild = findFirstReferenceChild(state.getItemCount());
        }
        if (referenceChild == null) {
            return false;
        }
        anchorInfo.assignFromView(referenceChild);
        if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
            boolean notVisible = this.mOrientationHelper.getDecoratedStart(referenceChild) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(referenceChild) < this.mOrientationHelper.getStartAfterPadding();
            if (notVisible) {
                if (anchorInfo.mLayoutFromEnd) {
                    startAfterPadding = this.mOrientationHelper.getEndAfterPadding();
                } else {
                    startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                }
                anchorInfo.mCoordinate = startAfterPadding;
            }
        }
        return true;
    }

    private View findFirstReferenceChild(int itemCount) {
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        View firstFound = findReferenceChild(0, getChildCount(), itemCount);
        if (firstFound == null) {
            return null;
        }
        int firstFoundPosition = getPosition(firstFound);
        int firstFoundLinePosition = this.mFlexboxHelper.mIndexToFlexLine[firstFoundPosition];
        if (firstFoundLinePosition == -1) {
            return null;
        }
        FlexLine firstFoundLine = this.mFlexLines.get(firstFoundLinePosition);
        return findFirstReferenceViewInLine(firstFound, firstFoundLine);
    }

    private View findLastReferenceChild(int itemCount) {
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        View lastFound = findReferenceChild(getChildCount() - 1, -1, itemCount);
        if (lastFound == null) {
            return null;
        }
        int lastFoundPosition = getPosition(lastFound);
        int lastFoundLinePosition = this.mFlexboxHelper.mIndexToFlexLine[lastFoundPosition];
        FlexLine lastFoundLine = this.mFlexLines.get(lastFoundLinePosition);
        return findLastReferenceViewInLine(lastFound, lastFoundLine);
    }

    private View findReferenceChild(int start, int end, int itemCount) {
        int position;
        ensureOrientationHelper();
        ensureLayoutState();
        View invalidMatch = null;
        View outOfBoundsMatch = null;
        int boundStart = this.mOrientationHelper.getStartAfterPadding();
        int boundEnd = this.mOrientationHelper.getEndAfterPadding();
        int diff = end > start ? 1 : -1;
        for (int i = start; i != end; i += diff) {
            View view = getChildAt(i);
            if (view != null && (position = getPosition(view)) >= 0 && position < itemCount) {
                if (((RecyclerView.LayoutParams) view.getLayoutParams()).isItemRemoved()) {
                    if (invalidMatch == null) {
                        invalidMatch = view;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(view) < boundStart || this.mOrientationHelper.getDecoratedEnd(view) > boundEnd) {
                    if (outOfBoundsMatch == null) {
                        outOfBoundsMatch = view;
                    }
                } else {
                    return view;
                }
            }
        }
        return outOfBoundsMatch != null ? outOfBoundsMatch : invalidMatch;
    }

    private View getChildClosestToStart() {
        return getChildAt(0);
    }

    private int fill(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState) {
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            if (layoutState.mAvailable < 0) {
                LayoutState.access$2012(layoutState, layoutState.mAvailable);
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int start = layoutState.mAvailable;
        int remainingSpace = layoutState.mAvailable;
        int consumed = 0;
        boolean mainAxisHorizontal = isMainAxisDirectionHorizontal();
        while (true) {
            if ((remainingSpace > 0 || this.mLayoutState.mInfinite) && layoutState.hasMore(state, this.mFlexLines)) {
                FlexLine flexLine = this.mFlexLines.get(layoutState.mFlexLinePosition);
                layoutState.mPosition = flexLine.mFirstIndex;
                consumed += layoutFlexLine(flexLine, layoutState);
                if (mainAxisHorizontal || !this.mIsRtl) {
                    LayoutState.access$1012(layoutState, flexLine.getCrossSize() * layoutState.mLayoutDirection);
                } else {
                    LayoutState.access$1020(layoutState, flexLine.getCrossSize() * layoutState.mLayoutDirection);
                }
                remainingSpace -= flexLine.getCrossSize();
            }
        }
        LayoutState.access$1220(layoutState, consumed);
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            LayoutState.access$2012(layoutState, consumed);
            if (layoutState.mAvailable < 0) {
                LayoutState.access$2012(layoutState, layoutState.mAvailable);
            }
            recycleByLayoutState(recycler, layoutState);
        }
        return start - layoutState.mAvailable;
    }

    private void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (!layoutState.mShouldRecycle) {
            return;
        }
        if (layoutState.mLayoutDirection == -1) {
            recycleFlexLinesFromEnd(recycler, layoutState);
        } else {
            recycleFlexLinesFromStart(recycler, layoutState);
        }
    }

    private void recycleFlexLinesFromStart(RecyclerView.Recycler recycler, LayoutState layoutState) {
        View firstView;
        int currentLineIndex;
        if (layoutState.mScrollingOffset < 0) {
            return;
        }
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        int childCount = getChildCount();
        if (childCount == 0 || (firstView = getChildAt(0)) == null || (currentLineIndex = this.mFlexboxHelper.mIndexToFlexLine[getPosition(firstView)]) == -1) {
            return;
        }
        FlexLine flexLine = this.mFlexLines.get(currentLineIndex);
        int recycleTo = -1;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view != null) {
                if (!canViewBeRecycledFromStart(view, layoutState.mScrollingOffset)) {
                    break;
                }
                if (flexLine.mLastIndex == getPosition(view)) {
                    recycleTo = i;
                    if (currentLineIndex >= this.mFlexLines.size() - 1) {
                        break;
                    }
                    currentLineIndex += layoutState.mLayoutDirection;
                    FlexLine flexLine2 = this.mFlexLines.get(currentLineIndex);
                    flexLine = flexLine2;
                } else {
                    continue;
                }
            }
        }
        recycleChildren(recycler, 0, recycleTo);
    }

    private boolean canViewBeRecycledFromStart(View view, int scrollingOffset) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.getDecoratedEnd(view) <= scrollingOffset : this.mOrientationHelper.getEnd() - this.mOrientationHelper.getDecoratedStart(view) <= scrollingOffset;
    }

    private void recycleFlexLinesFromEnd(RecyclerView.Recycler recycler, LayoutState layoutState) {
        View lastView;
        int currentLineIndex;
        if (layoutState.mScrollingOffset < 0) {
            return;
        }
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        int childCount = getChildCount();
        if (childCount == 0 || (lastView = getChildAt(childCount - 1)) == null || (currentLineIndex = this.mFlexboxHelper.mIndexToFlexLine[getPosition(lastView)]) == -1) {
            return;
        }
        int recycleTo = childCount - 1;
        int recycleFrom = childCount;
        FlexLine flexLine = this.mFlexLines.get(currentLineIndex);
        for (int i = childCount - 1; i >= 0; i--) {
            View view = getChildAt(i);
            if (view != null) {
                if (!canViewBeRecycledFromEnd(view, layoutState.mScrollingOffset)) {
                    break;
                }
                if (flexLine.mFirstIndex == getPosition(view)) {
                    recycleFrom = i;
                    if (currentLineIndex <= 0) {
                        break;
                    }
                    currentLineIndex += layoutState.mLayoutDirection;
                    FlexLine flexLine2 = this.mFlexLines.get(currentLineIndex);
                    flexLine = flexLine2;
                } else {
                    continue;
                }
            }
        }
        recycleChildren(recycler, recycleFrom, recycleTo);
    }

    private boolean canViewBeRecycledFromEnd(View view, int scrollingOffset) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEnd() - scrollingOffset : this.mOrientationHelper.getDecoratedEnd(view) <= scrollingOffset;
    }

    private void recycleChildren(RecyclerView.Recycler recycler, int startIndex, int endIndex) {
        for (int i = endIndex; i >= startIndex; i--) {
            removeAndRecycleViewAt(i, recycler);
        }
    }

    private int layoutFlexLine(FlexLine flexLine, LayoutState layoutState) {
        if (isMainAxisDirectionHorizontal()) {
            return layoutFlexLineMainAxisHorizontal(flexLine, layoutState);
        }
        return layoutFlexLineMainAxisVertical(flexLine, layoutState);
    }

    /* JADX WARN: Incorrect condition in loop: B:17:0x00db */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int layoutFlexLineMainAxisHorizontal(FlexLine flexLine, LayoutState layoutState) {
        int childTop;
        float childLeft;
        float childRight;
        int indexInFlexLine;
        View view;
        int i;
        if (this.mFlexboxHelper.mMeasureSpecCache == null) {
            throw new AssertionError();
        }
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int parentWidth = getWidth();
        int childTop2 = layoutState.mOffset;
        if (layoutState.mLayoutDirection != -1) {
            childTop = childTop2;
        } else {
            childTop = childTop2 - flexLine.mCrossSize;
        }
        int startPosition = layoutState.mPosition;
        float spaceBetweenItem = 0.0f;
        int i2 = 1;
        switch (this.mJustifyContent) {
            case 0:
                childLeft = paddingLeft;
                childRight = parentWidth - paddingRight;
                break;
            case 1:
                childLeft = (parentWidth - flexLine.mMainSize) + paddingRight;
                childRight = flexLine.mMainSize - paddingLeft;
                break;
            case 2:
                float childLeft2 = paddingLeft;
                childLeft = childLeft2 + ((parentWidth - flexLine.mMainSize) / 2.0f);
                childRight = (parentWidth - paddingRight) - ((parentWidth - flexLine.mMainSize) / 2.0f);
                break;
            case 3:
                childLeft = paddingLeft;
                float denominator = flexLine.mItemCount != 1 ? flexLine.mItemCount - 1 : 1.0f;
                spaceBetweenItem = (parentWidth - flexLine.mMainSize) / denominator;
                float childRight2 = parentWidth - paddingRight;
                childRight = childRight2;
                break;
            case 4:
                if (flexLine.mItemCount != 0) {
                    spaceBetweenItem = (parentWidth - flexLine.mMainSize) / flexLine.mItemCount;
                }
                childLeft = paddingLeft + (spaceBetweenItem / 2.0f);
                childRight = (parentWidth - paddingRight) - (spaceBetweenItem / 2.0f);
                break;
            case 5:
                if (flexLine.mItemCount != 0) {
                    spaceBetweenItem = (parentWidth - flexLine.mMainSize) / (flexLine.mItemCount + 1);
                }
                childLeft = paddingLeft + spaceBetweenItem;
                childRight = (parentWidth - paddingRight) - spaceBetweenItem;
                break;
            default:
                throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
        }
        float childLeft3 = childLeft - this.mAnchorInfo.mPerpendicularCoordinate;
        float childRight3 = childRight - this.mAnchorInfo.mPerpendicularCoordinate;
        float spaceBetweenItem2 = Math.max(spaceBetweenItem, 0.0f);
        int indexInFlexLine2 = 0;
        int itemCount = flexLine.getItemCount();
        int i3 = startPosition;
        while (i3 < i) {
            View view2 = getFlexItemAt(i3);
            if (view2 == null) {
                i = i3;
            } else {
                if (layoutState.mLayoutDirection == i2) {
                    calculateItemDecorationsForChild(view2, TEMP_RECT);
                    addView(view2);
                    indexInFlexLine = indexInFlexLine2;
                } else {
                    calculateItemDecorationsForChild(view2, TEMP_RECT);
                    addView(view2, indexInFlexLine2);
                    indexInFlexLine = indexInFlexLine2 + 1;
                }
                long measureSpec = this.mFlexboxHelper.mMeasureSpecCache[i3];
                int widthSpec = this.mFlexboxHelper.extractLowerInt(measureSpec);
                int heightSpec = this.mFlexboxHelper.extractHigherInt(measureSpec);
                LayoutParams lp = (LayoutParams) view2.getLayoutParams();
                if (shouldMeasureChild(view2, widthSpec, heightSpec, lp)) {
                    view2.measure(widthSpec, heightSpec);
                }
                float childLeft4 = childLeft3 + lp.leftMargin + getLeftDecorationWidth(view2);
                float childRight4 = childRight3 - (lp.rightMargin + getRightDecorationWidth(view2));
                int topWithDecoration = childTop + getTopDecorationHeight(view2);
                if (this.mIsRtl) {
                    FlexboxHelper flexboxHelper = this.mFlexboxHelper;
                    int round = Math.round(childRight4) - view2.getMeasuredWidth();
                    int i4 = Math.round(childRight4);
                    view = view2;
                    i = i3;
                    flexboxHelper.layoutSingleChildHorizontal(view2, flexLine, round, topWithDecoration, i4, topWithDecoration + view2.getMeasuredHeight());
                } else {
                    view = view2;
                    i = i3;
                    this.mFlexboxHelper.layoutSingleChildHorizontal(view, flexLine, Math.round(childLeft4), topWithDecoration, Math.round(childLeft4) + view.getMeasuredWidth(), topWithDecoration + view.getMeasuredHeight());
                }
                View view3 = view;
                float childRight5 = childRight4 - (((view3.getMeasuredWidth() + lp.leftMargin) + getLeftDecorationWidth(view3)) + spaceBetweenItem2);
                indexInFlexLine2 = indexInFlexLine;
                childLeft3 = childLeft4 + view.getMeasuredWidth() + lp.rightMargin + getRightDecorationWidth(view3) + spaceBetweenItem2;
                childRight3 = childRight5;
            }
            i3 = i + 1;
            i2 = 1;
        }
        LayoutState.access$1512(layoutState, this.mLayoutState.mLayoutDirection);
        return flexLine.getCrossSize();
    }

    /* JADX WARN: Incorrect condition in loop: B:17:0x00e4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int layoutFlexLineMainAxisVertical(FlexLine flexLine, LayoutState layoutState) {
        int childLeft;
        int childRight;
        float childTop;
        float childBottom;
        int indexInFlexLine;
        View view;
        int i;
        boolean z;
        if (this.mFlexboxHelper.mMeasureSpecCache == null) {
            throw new AssertionError();
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int parentHeight = getHeight();
        int childLeft2 = layoutState.mOffset;
        int childRight2 = layoutState.mOffset;
        if (layoutState.mLayoutDirection != -1) {
            childLeft = childLeft2;
            childRight = childRight2;
        } else {
            childLeft = childLeft2 - flexLine.mCrossSize;
            childRight = childRight2 + flexLine.mCrossSize;
        }
        int startPosition = layoutState.mPosition;
        float spaceBetweenItem = 0.0f;
        boolean z2 = true;
        switch (this.mJustifyContent) {
            case 0:
                childTop = paddingTop;
                childBottom = parentHeight - paddingBottom;
                break;
            case 1:
                childTop = (parentHeight - flexLine.mMainSize) + paddingBottom;
                childBottom = flexLine.mMainSize - paddingTop;
                break;
            case 2:
                float childTop2 = paddingTop;
                childTop = childTop2 + ((parentHeight - flexLine.mMainSize) / 2.0f);
                childBottom = (parentHeight - paddingBottom) - ((parentHeight - flexLine.mMainSize) / 2.0f);
                break;
            case 3:
                childTop = paddingTop;
                float denominator = flexLine.mItemCount != 1 ? flexLine.mItemCount - 1 : 1.0f;
                spaceBetweenItem = (parentHeight - flexLine.mMainSize) / denominator;
                float childBottom2 = parentHeight - paddingBottom;
                childBottom = childBottom2;
                break;
            case 4:
                if (flexLine.mItemCount != 0) {
                    spaceBetweenItem = (parentHeight - flexLine.mMainSize) / flexLine.mItemCount;
                }
                childTop = paddingTop + (spaceBetweenItem / 2.0f);
                childBottom = (parentHeight - paddingBottom) - (spaceBetweenItem / 2.0f);
                break;
            case 5:
                if (flexLine.mItemCount != 0) {
                    spaceBetweenItem = (parentHeight - flexLine.mMainSize) / (flexLine.mItemCount + 1);
                }
                childTop = paddingTop + spaceBetweenItem;
                childBottom = (parentHeight - paddingBottom) - spaceBetweenItem;
                break;
            default:
                throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
        }
        float childTop3 = childTop - this.mAnchorInfo.mPerpendicularCoordinate;
        float childBottom3 = childBottom - this.mAnchorInfo.mPerpendicularCoordinate;
        float spaceBetweenItem2 = Math.max(spaceBetweenItem, 0.0f);
        int indexInFlexLine2 = 0;
        int itemCount = flexLine.getItemCount();
        int i2 = startPosition;
        while (i2 < i) {
            View view2 = getFlexItemAt(i2);
            if (view2 == null) {
                i = i2;
                z = z2;
            } else {
                long measureSpec = this.mFlexboxHelper.mMeasureSpecCache[i2];
                int widthSpec = this.mFlexboxHelper.extractLowerInt(measureSpec);
                int heightSpec = this.mFlexboxHelper.extractHigherInt(measureSpec);
                LayoutParams lp = (LayoutParams) view2.getLayoutParams();
                if (shouldMeasureChild(view2, widthSpec, heightSpec, lp)) {
                    view2.measure(widthSpec, heightSpec);
                }
                float childTop4 = childTop3 + lp.topMargin + getTopDecorationHeight(view2);
                float childBottom4 = childBottom3 - (lp.rightMargin + getBottomDecorationHeight(view2));
                if (layoutState.mLayoutDirection == 1) {
                    calculateItemDecorationsForChild(view2, TEMP_RECT);
                    addView(view2);
                    indexInFlexLine = indexInFlexLine2;
                } else {
                    calculateItemDecorationsForChild(view2, TEMP_RECT);
                    addView(view2, indexInFlexLine2);
                    indexInFlexLine = indexInFlexLine2 + 1;
                }
                int indexInFlexLine3 = getLeftDecorationWidth(view2);
                int leftWithDecoration = childLeft + indexInFlexLine3;
                int rightWithDecoration = childRight - getRightDecorationWidth(view2);
                if (!this.mIsRtl) {
                    view = view2;
                    i = i2;
                    z = true;
                    if (this.mFromBottomToTop) {
                        this.mFlexboxHelper.layoutSingleChildVertical(view, flexLine, this.mIsRtl, leftWithDecoration, Math.round(childBottom4) - view.getMeasuredHeight(), leftWithDecoration + view.getMeasuredWidth(), Math.round(childBottom4));
                    } else {
                        this.mFlexboxHelper.layoutSingleChildVertical(view, flexLine, this.mIsRtl, leftWithDecoration, Math.round(childTop4), leftWithDecoration + view.getMeasuredWidth(), Math.round(childTop4) + view.getMeasuredHeight());
                    }
                } else if (this.mFromBottomToTop) {
                    FlexboxHelper flexboxHelper = this.mFlexboxHelper;
                    boolean z3 = this.mIsRtl;
                    int measuredWidth = rightWithDecoration - view2.getMeasuredWidth();
                    int i3 = Math.round(childBottom4) - view2.getMeasuredHeight();
                    view = view2;
                    i = i2;
                    z = true;
                    flexboxHelper.layoutSingleChildVertical(view2, flexLine, z3, measuredWidth, i3, rightWithDecoration, Math.round(childBottom4));
                } else {
                    view = view2;
                    i = i2;
                    z = true;
                    this.mFlexboxHelper.layoutSingleChildVertical(view, flexLine, this.mIsRtl, rightWithDecoration - view.getMeasuredWidth(), Math.round(childTop4), rightWithDecoration, Math.round(childTop4) + view.getMeasuredHeight());
                }
                View view3 = view;
                float childBottom5 = childBottom4 - (((view3.getMeasuredHeight() + lp.bottomMargin) + getTopDecorationHeight(view3)) + spaceBetweenItem2);
                indexInFlexLine2 = indexInFlexLine;
                childTop3 = childTop4 + view.getMeasuredHeight() + lp.topMargin + getBottomDecorationHeight(view3) + spaceBetweenItem2;
                childBottom3 = childBottom5;
            }
            i2 = i + 1;
            z2 = z;
        }
        LayoutState.access$1512(layoutState, this.mLayoutState.mLayoutDirection);
        return flexLine.getCrossSize();
    }

    @Override // com.google.android.flexbox.FlexContainer
    public boolean isMainAxisDirectionHorizontal() {
        return this.mFlexDirection == 0 || this.mFlexDirection == 1;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo anchorInfo, boolean fromNextLine, boolean considerInfinite) {
        if (considerInfinite) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - getPaddingRight();
        } else {
            this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - anchorInfo.mCoordinate;
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (!fromNextLine || this.mFlexLines.size() <= 1 || anchorInfo.mFlexLinePosition < 0 || anchorInfo.mFlexLinePosition >= this.mFlexLines.size() - 1) {
            return;
        }
        FlexLine currentLine = this.mFlexLines.get(anchorInfo.mFlexLinePosition);
        LayoutState.access$1508(this.mLayoutState);
        LayoutState.access$2212(this.mLayoutState, currentLine.getItemCount());
    }

    private void updateLayoutStateToFillStart(AnchorInfo anchorInfo, boolean fromPreviousLine, boolean considerInfinite) {
        if (considerInfinite) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.mLayoutState.mAvailable = (this.mParent.getWidth() - anchorInfo.mCoordinate) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (!fromPreviousLine || anchorInfo.mFlexLinePosition <= 0 || this.mFlexLines.size() <= anchorInfo.mFlexLinePosition) {
            return;
        }
        FlexLine currentLine = this.mFlexLines.get(anchorInfo.mFlexLinePosition);
        LayoutState.access$1510(this.mLayoutState);
        LayoutState.access$2220(this.mLayoutState, currentLine.getItemCount());
    }

    private void resolveInfiniteAmount() {
        int crossMode;
        if (isMainAxisDirectionHorizontal()) {
            crossMode = getHeightMode();
        } else {
            crossMode = getWidthMode();
        }
        this.mLayoutState.mInfinite = crossMode == 0 || crossMode == Integer.MIN_VALUE;
    }

    private void ensureOrientationHelper() {
        if (this.mOrientationHelper != null) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            if (this.mFlexWrap == 0) {
                this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
                return;
            } else {
                this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                return;
            }
        }
        if (this.mFlexWrap == 0) {
            this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
            this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
        } else {
            this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
            this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
        }
    }

    private void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new LayoutState();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int position) {
        this.mPendingScrollPosition = position;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public void setRecycleChildrenOnDetach(boolean recycleChildrenOnDetach) {
        this.mRecycleChildrenOnDetach = recycleChildrenOnDetach;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mParent = (View) recyclerView.getParent();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        if (this.mFlexWrap == 0) {
            return isMainAxisDirectionHorizontal();
        }
        if (isMainAxisDirectionHorizontal()) {
            if (getWidth() <= (this.mParent != null ? this.mParent.getWidth() : 0)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        if (this.mFlexWrap == 0) {
            return !isMainAxisDirectionHorizontal();
        }
        if (isMainAxisDirectionHorizontal()) {
            return true;
        }
        return getHeight() > (this.mParent != null ? this.mParent.getHeight() : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!isMainAxisDirectionHorizontal() || this.mFlexWrap == 0) {
            int scrolled = handleScrollingMainOrientation(dx, recycler, state);
            this.mViewCache.clear();
            return scrolled;
        }
        int scrolled2 = handleScrollingSubOrientation(dx);
        AnchorInfo.access$2412(this.mAnchorInfo, scrolled2);
        this.mSubOrientationHelper.offsetChildren(-scrolled2);
        return scrolled2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (isMainAxisDirectionHorizontal() || (this.mFlexWrap == 0 && !isMainAxisDirectionHorizontal())) {
            int scrolled = handleScrollingMainOrientation(dy, recycler, state);
            this.mViewCache.clear();
            return scrolled;
        }
        int scrolled2 = handleScrollingSubOrientation(dy);
        AnchorInfo.access$2412(this.mAnchorInfo, scrolled2);
        this.mSubOrientationHelper.offsetChildren(-scrolled2);
        return scrolled2;
    }

    private int handleScrollingMainOrientation(int delta, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrolled;
        if (getChildCount() == 0 || delta == 0) {
            return 0;
        }
        ensureOrientationHelper();
        int layoutDirection = 1;
        this.mLayoutState.mShouldRecycle = true;
        boolean columnAndRtl = !isMainAxisDirectionHorizontal() && this.mIsRtl;
        if (columnAndRtl) {
            if (delta >= 0) {
                layoutDirection = -1;
            }
        } else if (delta <= 0) {
            layoutDirection = -1;
        }
        int absDelta = Math.abs(delta);
        updateLayoutState(layoutDirection, absDelta);
        int freeScroll = this.mLayoutState.mScrollingOffset;
        int consumed = fill(recycler, state, this.mLayoutState) + freeScroll;
        if (consumed < 0) {
            return 0;
        }
        if (columnAndRtl) {
            scrolled = absDelta > consumed ? (-layoutDirection) * consumed : delta;
        } else {
            scrolled = absDelta > consumed ? layoutDirection * consumed : delta;
        }
        this.mOrientationHelper.offsetChildren(-scrolled);
        this.mLayoutState.mLastScrollDelta = scrolled;
        return scrolled;
    }

    private int handleScrollingSubOrientation(int delta) {
        int delta2;
        if (getChildCount() == 0 || delta == 0) {
            return 0;
        }
        ensureOrientationHelper();
        boolean isMainAxisHorizontal = isMainAxisDirectionHorizontal();
        View view = this.mParent;
        int parentLength = isMainAxisHorizontal ? view.getWidth() : view.getHeight();
        int mainAxisLength = isMainAxisHorizontal ? getWidth() : getHeight();
        boolean layoutRtl = getLayoutDirection() == 1;
        if (layoutRtl) {
            int absDelta = Math.abs(delta);
            if (delta < 0) {
                return -Math.min((this.mAnchorInfo.mPerpendicularCoordinate + mainAxisLength) - parentLength, absDelta);
            }
            if (this.mAnchorInfo.mPerpendicularCoordinate + delta <= 0) {
                delta2 = delta;
            } else {
                delta2 = -this.mAnchorInfo.mPerpendicularCoordinate;
            }
            return delta2;
        }
        if (delta > 0) {
            return Math.min((mainAxisLength - this.mAnchorInfo.mPerpendicularCoordinate) - parentLength, delta);
        }
        return this.mAnchorInfo.mPerpendicularCoordinate + delta >= 0 ? delta : -this.mAnchorInfo.mPerpendicularCoordinate;
    }

    private void updateLayoutState(int layoutDirection, int absDelta) {
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        this.mLayoutState.mLayoutDirection = layoutDirection;
        boolean mainAxisHorizontal = isMainAxisDirectionHorizontal();
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        boolean columnAndRtl = !mainAxisHorizontal && this.mIsRtl;
        if (layoutDirection == 1) {
            View lastVisible = getChildAt(getChildCount() - 1);
            if (lastVisible == null) {
                return;
            }
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(lastVisible);
            int lastVisiblePosition = getPosition(lastVisible);
            int lastVisibleLinePosition = this.mFlexboxHelper.mIndexToFlexLine[lastVisiblePosition];
            FlexLine lastVisibleLine = this.mFlexLines.get(lastVisibleLinePosition);
            View referenceView = findLastReferenceViewInLine(lastVisible, lastVisibleLine);
            this.mLayoutState.mItemDirection = 1;
            this.mLayoutState.mPosition = this.mLayoutState.mItemDirection + lastVisiblePosition;
            if (this.mFlexboxHelper.mIndexToFlexLine.length <= this.mLayoutState.mPosition) {
                this.mLayoutState.mFlexLinePosition = -1;
            } else {
                this.mLayoutState.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[this.mLayoutState.mPosition];
            }
            if (columnAndRtl) {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(referenceView);
                this.mLayoutState.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(referenceView)) + this.mOrientationHelper.getStartAfterPadding();
                this.mLayoutState.mScrollingOffset = Math.max(this.mLayoutState.mScrollingOffset, 0);
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(referenceView);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(referenceView) - this.mOrientationHelper.getEndAfterPadding();
            }
            if ((this.mLayoutState.mFlexLinePosition == -1 || this.mLayoutState.mFlexLinePosition > this.mFlexLines.size() - 1) && this.mLayoutState.mPosition <= getFlexItemCount()) {
                int needsToFill = absDelta - this.mLayoutState.mScrollingOffset;
                this.mFlexLinesResult.reset();
                if (needsToFill > 0) {
                    if (mainAxisHorizontal) {
                        this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec, needsToFill, this.mLayoutState.mPosition, this.mFlexLines);
                    } else {
                        this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec, needsToFill, this.mLayoutState.mPosition, this.mFlexLines);
                    }
                    this.mFlexboxHelper.determineMainSize(widthMeasureSpec, heightMeasureSpec, this.mLayoutState.mPosition);
                    this.mFlexboxHelper.stretchViews(this.mLayoutState.mPosition);
                }
            }
        } else {
            View firstVisible = getChildAt(0);
            if (firstVisible == null) {
                return;
            }
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(firstVisible);
            int firstVisiblePosition = getPosition(firstVisible);
            int firstVisibleLinePosition = this.mFlexboxHelper.mIndexToFlexLine[firstVisiblePosition];
            FlexLine firstVisibleLine = this.mFlexLines.get(firstVisibleLinePosition);
            View referenceView2 = findFirstReferenceViewInLine(firstVisible, firstVisibleLine);
            this.mLayoutState.mItemDirection = 1;
            int flexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[firstVisiblePosition];
            if (flexLinePosition == -1) {
                flexLinePosition = 0;
            }
            if (flexLinePosition > 0) {
                FlexLine previousLine = this.mFlexLines.get(flexLinePosition - 1);
                this.mLayoutState.mPosition = firstVisiblePosition - previousLine.getItemCount();
            } else {
                this.mLayoutState.mPosition = -1;
            }
            this.mLayoutState.mFlexLinePosition = flexLinePosition > 0 ? flexLinePosition - 1 : 0;
            if (columnAndRtl) {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(referenceView2);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(referenceView2) - this.mOrientationHelper.getEndAfterPadding();
                this.mLayoutState.mScrollingOffset = Math.max(this.mLayoutState.mScrollingOffset, 0);
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(referenceView2);
                this.mLayoutState.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(referenceView2)) + this.mOrientationHelper.getStartAfterPadding();
            }
        }
        this.mLayoutState.mAvailable = absDelta - this.mLayoutState.mScrollingOffset;
    }

    private View findFirstReferenceViewInLine(View firstView, FlexLine firstVisibleLine) {
        boolean mainAxisHorizontal = isMainAxisDirectionHorizontal();
        View referenceView = firstView;
        int to = firstVisibleLine.mItemCount;
        for (int i = 1; i < to; i++) {
            View viewInSameLine = getChildAt(i);
            if (viewInSameLine != null && viewInSameLine.getVisibility() != 8) {
                if (this.mIsRtl && !mainAxisHorizontal) {
                    if (this.mOrientationHelper.getDecoratedEnd(referenceView) < this.mOrientationHelper.getDecoratedEnd(viewInSameLine)) {
                        referenceView = viewInSameLine;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(referenceView) > this.mOrientationHelper.getDecoratedStart(viewInSameLine)) {
                    referenceView = viewInSameLine;
                }
            }
        }
        return referenceView;
    }

    private View findLastReferenceViewInLine(View lastView, FlexLine lastVisibleLine) {
        boolean mainAxisHorizontal = isMainAxisDirectionHorizontal();
        View referenceView = lastView;
        int to = (getChildCount() - lastVisibleLine.mItemCount) - 1;
        for (int i = getChildCount() - 2; i > to; i--) {
            View viewInSameLine = getChildAt(i);
            if (viewInSameLine != null && viewInSameLine.getVisibility() != 8) {
                if (this.mIsRtl && !mainAxisHorizontal) {
                    if (this.mOrientationHelper.getDecoratedStart(referenceView) > this.mOrientationHelper.getDecoratedStart(viewInSameLine)) {
                        referenceView = viewInSameLine;
                    }
                } else if (this.mOrientationHelper.getDecoratedEnd(referenceView) < this.mOrientationHelper.getDecoratedEnd(viewInSameLine)) {
                    referenceView = viewInSameLine;
                }
            }
        }
        return referenceView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        int scrollExtent = computeScrollExtent(state);
        return scrollExtent;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        int scrollExtent = computeScrollExtent(state);
        return scrollExtent;
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int allChildrenCount = state.getItemCount();
        ensureOrientationHelper();
        View firstReferenceView = findFirstReferenceChild(allChildrenCount);
        View lastReferenceView = findLastReferenceChild(allChildrenCount);
        if (state.getItemCount() == 0 || firstReferenceView == null || lastReferenceView == null) {
            return 0;
        }
        int extend = this.mOrientationHelper.getDecoratedEnd(lastReferenceView) - this.mOrientationHelper.getDecoratedStart(firstReferenceView);
        return Math.min(this.mOrientationHelper.getTotalSpace(), extend);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        int scrollOffset = computeScrollOffset(state);
        return scrollOffset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        int scrollOffset = computeScrollOffset(state);
        return scrollOffset;
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int allChildrenCount = state.getItemCount();
        View firstReferenceView = findFirstReferenceChild(allChildrenCount);
        View lastReferenceView = findLastReferenceChild(allChildrenCount);
        if (state.getItemCount() == 0 || firstReferenceView == null || lastReferenceView == null) {
            return 0;
        }
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        int minPosition = getPosition(firstReferenceView);
        int maxPosition = getPosition(lastReferenceView);
        int laidOutArea = Math.abs(this.mOrientationHelper.getDecoratedEnd(lastReferenceView) - this.mOrientationHelper.getDecoratedStart(firstReferenceView));
        int firstLinePosition = this.mFlexboxHelper.mIndexToFlexLine[minPosition];
        if (firstLinePosition == 0 || firstLinePosition == -1) {
            return 0;
        }
        int lastLinePosition = this.mFlexboxHelper.mIndexToFlexLine[maxPosition];
        int lineRange = (lastLinePosition - firstLinePosition) + 1;
        float averageSizePerLine = laidOutArea / lineRange;
        return Math.round((firstLinePosition * averageSizePerLine) + (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(firstReferenceView)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        int scrollRange = computeScrollRange(state);
        return scrollRange;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        int scrollRange = computeScrollRange(state);
        return scrollRange;
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int allItemCount = state.getItemCount();
        View firstReferenceView = findFirstReferenceChild(allItemCount);
        View lastReferenceView = findLastReferenceChild(allItemCount);
        if (state.getItemCount() == 0 || firstReferenceView == null || lastReferenceView == null) {
            return 0;
        }
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        int firstVisiblePosition = findFirstVisibleItemPosition();
        int lastVisiblePosition = findLastVisibleItemPosition();
        int laidOutArea = Math.abs(this.mOrientationHelper.getDecoratedEnd(lastReferenceView) - this.mOrientationHelper.getDecoratedStart(firstReferenceView));
        int laidOutRange = (lastVisiblePosition - firstVisiblePosition) + 1;
        return (int) ((laidOutArea / laidOutRange) * state.getItemCount());
    }

    private boolean shouldMeasureChild(View child, int widthSpec, int heightSpec, RecyclerView.LayoutParams lp) {
        return (!child.isLayoutRequested() && isMeasurementCacheEnabled() && isMeasurementUpToDate(child.getWidth(), widthSpec, lp.width) && isMeasurementUpToDate(child.getHeight(), heightSpec, lp.height)) ? false : true;
    }

    private static boolean isMeasurementUpToDate(int childSize, int spec, int dimension) {
        int specMode = View.MeasureSpec.getMode(spec);
        int specSize = View.MeasureSpec.getSize(spec);
        if (dimension > 0 && childSize != dimension) {
            return false;
        }
        switch (specMode) {
            case Integer.MIN_VALUE:
                if (specSize >= childSize) {
                    break;
                }
                break;
            case 0:
                break;
            case 1073741824:
                if (specSize == childSize) {
                    break;
                }
                break;
        }
        return false;
    }

    private void clearFlexLines() {
        this.mFlexLines.clear();
        this.mAnchorInfo.reset();
        this.mAnchorInfo.mPerpendicularCoordinate = 0;
    }

    private int getChildLeft(View view) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        return getDecoratedLeft(view) - params.leftMargin;
    }

    private int getChildRight(View view) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        return getDecoratedRight(view) + params.rightMargin;
    }

    private int getChildTop(View view) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        return getDecoratedTop(view) - params.topMargin;
    }

    private int getChildBottom(View view) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        return getDecoratedBottom(view) + params.bottomMargin;
    }

    private boolean isViewVisible(View view, boolean completelyVisible) {
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getWidth() - getPaddingRight();
        int bottom = getHeight() - getPaddingBottom();
        int childLeft = getChildLeft(view);
        int childTop = getChildTop(view);
        int childRight = getChildRight(view);
        int childBottom = getChildBottom(view);
        boolean horizontalCompletelyVisible = false;
        boolean horizontalPartiallyVisible = false;
        boolean verticalCompletelyVisible = false;
        boolean verticalPartiallyVisible = false;
        if (left <= childLeft && right >= childRight) {
            horizontalCompletelyVisible = true;
        }
        if (childLeft >= right || childRight >= left) {
            horizontalPartiallyVisible = true;
        }
        if (top <= childTop && bottom >= childBottom) {
            verticalCompletelyVisible = true;
        }
        if (childTop >= bottom || childBottom >= top) {
            verticalPartiallyVisible = true;
        }
        return completelyVisible ? horizontalCompletelyVisible && verticalCompletelyVisible : horizontalPartiallyVisible && verticalPartiallyVisible;
    }

    public int findFirstVisibleItemPosition() {
        View child = findOneVisibleChild(0, getChildCount(), false);
        if (child == null) {
            return -1;
        }
        return getPosition(child);
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View child = findOneVisibleChild(0, getChildCount(), true);
        if (child == null) {
            return -1;
        }
        return getPosition(child);
    }

    public int findLastVisibleItemPosition() {
        View child = findOneVisibleChild(getChildCount() - 1, -1, false);
        if (child == null) {
            return -1;
        }
        return getPosition(child);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View child = findOneVisibleChild(getChildCount() - 1, -1, true);
        if (child == null) {
            return -1;
        }
        return getPosition(child);
    }

    private View findOneVisibleChild(int fromIndex, int toIndex, boolean completelyVisible) {
        int next = toIndex > fromIndex ? 1 : -1;
        for (int i = fromIndex; i != toIndex; i += next) {
            View view = getChildAt(i);
            if (isViewVisible(view, completelyVisible)) {
                return view;
            }
        }
        return null;
    }

    int getPositionToFlexLineIndex(int position) {
        if (this.mFlexboxHelper.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        return this.mFlexboxHelper.mIndexToFlexLine[position];
    }

    public static class LayoutParams extends RecyclerView.LayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new Parcelable.Creator<LayoutParams>() { // from class: com.google.android.flexbox.FlexboxLayoutManager.LayoutParams.1
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
        private boolean mWrapBefore;

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

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(RecyclerView.LayoutParams source) {
            super(source);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(LayoutParams source) {
            super((RecyclerView.LayoutParams) source);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
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

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return 1;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setOrder(int order) {
            throw new UnsupportedOperationException("Setting the order in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to reorder using the attribute.");
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
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
            super(-2, -2);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
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

    private class AnchorInfo {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean mAssignedFromSavedState;
        private int mCoordinate;
        private int mFlexLinePosition;
        private boolean mLayoutFromEnd;
        private int mPerpendicularCoordinate;
        private int mPosition;
        private boolean mValid;

        private AnchorInfo() {
            this.mPerpendicularCoordinate = 0;
        }

        static /* synthetic */ int access$2412(AnchorInfo x0, int x1) {
            int i = x0.mPerpendicularCoordinate + x1;
            x0.mPerpendicularCoordinate = i;
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.mPosition = -1;
            this.mFlexLinePosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mValid = false;
            this.mAssignedFromSavedState = false;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal()) {
                if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                    this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexDirection == 1;
                    return;
                } else {
                    this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexWrap == 2;
                    return;
                }
            }
            if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexDirection == 3;
            } else {
                this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexWrap == 2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assignCoordinateFromPadding() {
            if (!FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() && FlexboxLayoutManager.this.mIsRtl) {
                this.mCoordinate = this.mLayoutFromEnd ? FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding() : FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            } else {
                this.mCoordinate = this.mLayoutFromEnd ? FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding() : FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assignFromView(View anchor) {
            OrientationHelper orientationHelper = FlexboxLayoutManager.this.mFlexWrap == 0 ? FlexboxLayoutManager.this.mSubOrientationHelper : FlexboxLayoutManager.this.mOrientationHelper;
            if (!FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() && FlexboxLayoutManager.this.mIsRtl) {
                if (this.mLayoutFromEnd) {
                    this.mCoordinate = orientationHelper.getDecoratedStart(anchor) + orientationHelper.getTotalSpaceChange();
                } else {
                    this.mCoordinate = orientationHelper.getDecoratedEnd(anchor);
                }
            } else if (this.mLayoutFromEnd) {
                this.mCoordinate = orientationHelper.getDecoratedEnd(anchor) + orientationHelper.getTotalSpaceChange();
            } else {
                this.mCoordinate = orientationHelper.getDecoratedStart(anchor);
            }
            this.mPosition = FlexboxLayoutManager.this.getPosition(anchor);
            this.mAssignedFromSavedState = false;
            if (FlexboxLayoutManager.this.mFlexboxHelper.mIndexToFlexLine != null) {
                int flexLinePosition = FlexboxLayoutManager.this.mFlexboxHelper.mIndexToFlexLine[this.mPosition != -1 ? this.mPosition : 0];
                this.mFlexLinePosition = flexLinePosition != -1 ? flexLinePosition : 0;
                if (FlexboxLayoutManager.this.mFlexLines.size() > this.mFlexLinePosition) {
                    this.mPosition = ((FlexLine) FlexboxLayoutManager.this.mFlexLines.get(this.mFlexLinePosition)).mFirstIndex;
                    return;
                }
                return;
            }
            throw new AssertionError();
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mFlexLinePosition=" + this.mFlexLinePosition + ", mCoordinate=" + this.mCoordinate + ", mPerpendicularCoordinate=" + this.mPerpendicularCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + ", mAssignedFromSavedState=" + this.mAssignedFromSavedState + '}';
        }
    }

    private static class LayoutState {
        private static final int ITEM_DIRECTION_TAIL = 1;
        private static final int LAYOUT_END = 1;
        private static final int LAYOUT_START = -1;
        private static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
        private int mAvailable;
        private int mFlexLinePosition;
        private boolean mInfinite;
        private int mItemDirection;
        private int mLastScrollDelta;
        private int mLayoutDirection;
        private int mOffset;
        private int mPosition;
        private int mScrollingOffset;
        private boolean mShouldRecycle;

        private LayoutState() {
            this.mItemDirection = 1;
            this.mLayoutDirection = 1;
        }

        static /* synthetic */ int access$1012(LayoutState x0, int x1) {
            int i = x0.mOffset + x1;
            x0.mOffset = i;
            return i;
        }

        static /* synthetic */ int access$1020(LayoutState x0, int x1) {
            int i = x0.mOffset - x1;
            x0.mOffset = i;
            return i;
        }

        static /* synthetic */ int access$1220(LayoutState x0, int x1) {
            int i = x0.mAvailable - x1;
            x0.mAvailable = i;
            return i;
        }

        static /* synthetic */ int access$1508(LayoutState x0) {
            int i = x0.mFlexLinePosition;
            x0.mFlexLinePosition = i + 1;
            return i;
        }

        static /* synthetic */ int access$1510(LayoutState x0) {
            int i = x0.mFlexLinePosition;
            x0.mFlexLinePosition = i - 1;
            return i;
        }

        static /* synthetic */ int access$1512(LayoutState x0, int x1) {
            int i = x0.mFlexLinePosition + x1;
            x0.mFlexLinePosition = i;
            return i;
        }

        static /* synthetic */ int access$2012(LayoutState x0, int x1) {
            int i = x0.mScrollingOffset + x1;
            x0.mScrollingOffset = i;
            return i;
        }

        static /* synthetic */ int access$2212(LayoutState x0, int x1) {
            int i = x0.mPosition + x1;
            x0.mPosition = i;
            return i;
        }

        static /* synthetic */ int access$2220(LayoutState x0, int x1) {
            int i = x0.mPosition - x1;
            x0.mPosition = i;
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasMore(RecyclerView.State state, List<FlexLine> flexLines) {
            return this.mPosition >= 0 && this.mPosition < state.getItemCount() && this.mFlexLinePosition >= 0 && this.mFlexLinePosition < flexLines.size();
        }

        public String toString() {
            return "LayoutState{mAvailable=" + this.mAvailable + ", mFlexLinePosition=" + this.mFlexLinePosition + ", mPosition=" + this.mPosition + ", mOffset=" + this.mOffset + ", mScrollingOffset=" + this.mScrollingOffset + ", mLastScrollDelta=" + this.mLastScrollDelta + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + '}';
        }
    }

    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.flexbox.FlexboxLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        private int mAnchorOffset;
        private int mAnchorPosition;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mAnchorPosition);
            dest.writeInt(this.mAnchorOffset);
        }

        SavedState() {
        }

        private SavedState(Parcel in) {
            this.mAnchorPosition = in.readInt();
            this.mAnchorOffset = in.readInt();
        }

        private SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasValidAnchor(int itemCount) {
            return this.mAnchorPosition >= 0 && this.mAnchorPosition < itemCount;
        }

        public String toString() {
            return "SavedState{mAnchorPosition=" + this.mAnchorPosition + ", mAnchorOffset=" + this.mAnchorOffset + '}';
        }
    }
}
