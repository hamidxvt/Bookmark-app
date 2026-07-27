package com.google.android.flexbox;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.widget.CompoundButtonCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes16.dex */
class FlexboxHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INITIAL_CAPACITY = 10;
    private static final long MEASURE_SPEC_WIDTH_MASK = 4294967295L;
    private boolean[] mChildrenFrozen;
    private final FlexContainer mFlexContainer;
    int[] mIndexToFlexLine;
    long[] mMeasureSpecCache;
    private long[] mMeasuredSizeCache;

    FlexboxHelper(FlexContainer flexContainer) {
        this.mFlexContainer = flexContainer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    int[] createReorderedIndices(View viewBeforeAdded, int indexForViewBeforeAdded, ViewGroup.LayoutParams paramsForViewBeforeAdded, SparseIntArray orderCache) {
        int childCount = this.mFlexContainer.getFlexItemCount();
        List<Order> orders = createOrders(childCount);
        Order orderForViewToBeAdded = new Order();
        if (viewBeforeAdded != null && (paramsForViewBeforeAdded instanceof FlexItem)) {
            orderForViewToBeAdded.order = ((FlexItem) paramsForViewBeforeAdded).getOrder();
        } else {
            orderForViewToBeAdded.order = 1;
        }
        if (indexForViewBeforeAdded == -1 || indexForViewBeforeAdded == childCount) {
            orderForViewToBeAdded.index = childCount;
        } else if (indexForViewBeforeAdded < this.mFlexContainer.getFlexItemCount()) {
            orderForViewToBeAdded.index = indexForViewBeforeAdded;
            for (int i = indexForViewBeforeAdded; i < childCount; i++) {
                orders.get(i).index++;
            }
        } else {
            orderForViewToBeAdded.index = childCount;
        }
        orders.add(orderForViewToBeAdded);
        return sortOrdersIntoReorderedIndices(childCount + 1, orders, orderCache);
    }

    int[] createReorderedIndices(SparseIntArray orderCache) {
        int childCount = this.mFlexContainer.getFlexItemCount();
        List<Order> orders = createOrders(childCount);
        return sortOrdersIntoReorderedIndices(childCount, orders, orderCache);
    }

    private List<Order> createOrders(int childCount) {
        List<Order> orders = new ArrayList<>(childCount);
        for (int i = 0; i < childCount; i++) {
            View child = this.mFlexContainer.getFlexItemAt(i);
            FlexItem flexItem = (FlexItem) child.getLayoutParams();
            Order order = new Order();
            order.order = flexItem.getOrder();
            order.index = i;
            orders.add(order);
        }
        return orders;
    }

    boolean isOrderChangedFromLastMeasurement(SparseIntArray orderCache) {
        int childCount = this.mFlexContainer.getFlexItemCount();
        if (orderCache.size() != childCount) {
            return true;
        }
        for (int i = 0; i < childCount; i++) {
            View view = this.mFlexContainer.getFlexItemAt(i);
            if (view != null) {
                FlexItem flexItem = (FlexItem) view.getLayoutParams();
                if (flexItem.getOrder() != orderCache.get(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] sortOrdersIntoReorderedIndices(int childCount, List<Order> orders, SparseIntArray orderCache) {
        Collections.sort(orders);
        orderCache.clear();
        int[] reorderedIndices = new int[childCount];
        int i = 0;
        for (Order order : orders) {
            reorderedIndices[i] = order.index;
            orderCache.append(order.index, order.order);
            i++;
        }
        return reorderedIndices;
    }

    void calculateHorizontalFlexLines(FlexLinesResult result, int widthMeasureSpec, int heightMeasureSpec) {
        calculateFlexLines(result, widthMeasureSpec, heightMeasureSpec, Integer.MAX_VALUE, 0, -1, null);
    }

    void calculateHorizontalFlexLines(FlexLinesResult result, int widthMeasureSpec, int heightMeasureSpec, int needsCalcAmount, int fromIndex, List<FlexLine> existingLines) {
        calculateFlexLines(result, widthMeasureSpec, heightMeasureSpec, needsCalcAmount, fromIndex, -1, existingLines);
    }

    void calculateHorizontalFlexLinesToIndex(FlexLinesResult result, int widthMeasureSpec, int heightMeasureSpec, int needsCalcAmount, int toIndex, List<FlexLine> existingLines) {
        calculateFlexLines(result, widthMeasureSpec, heightMeasureSpec, needsCalcAmount, 0, toIndex, existingLines);
    }

    void calculateVerticalFlexLines(FlexLinesResult result, int widthMeasureSpec, int heightMeasureSpec) {
        calculateFlexLines(result, heightMeasureSpec, widthMeasureSpec, Integer.MAX_VALUE, 0, -1, null);
    }

    void calculateVerticalFlexLines(FlexLinesResult result, int widthMeasureSpec, int heightMeasureSpec, int needsCalcAmount, int fromIndex, List<FlexLine> existingLines) {
        calculateFlexLines(result, heightMeasureSpec, widthMeasureSpec, needsCalcAmount, fromIndex, -1, existingLines);
    }

    void calculateVerticalFlexLinesToIndex(FlexLinesResult result, int widthMeasureSpec, int heightMeasureSpec, int needsCalcAmount, int toIndex, List<FlexLine> existingLines) {
        calculateFlexLines(result, heightMeasureSpec, widthMeasureSpec, needsCalcAmount, 0, toIndex, existingLines);
    }

    /* JADX WARN: Multi-variable type inference failed */
    void calculateFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, int i5, List<FlexLine> list) {
        List<FlexLine> list2;
        int i6;
        List<FlexLine> list3;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        List<FlexLine> list4;
        View view;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17 = i;
        int i18 = i5;
        boolean isMainAxisDirectionHorizontal = this.mFlexContainer.isMainAxisDirectionHorizontal();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int i19 = 0;
        if (list == null) {
            list2 = new ArrayList();
        } else {
            list2 = list;
        }
        flexLinesResult.mFlexLines = list2;
        boolean z = i18 == -1;
        int paddingStartMain = getPaddingStartMain(isMainAxisDirectionHorizontal);
        int paddingEndMain = getPaddingEndMain(isMainAxisDirectionHorizontal);
        int paddingStartCross = getPaddingStartCross(isMainAxisDirectionHorizontal);
        int paddingEndCross = getPaddingEndCross(isMainAxisDirectionHorizontal);
        FlexLine flexLine = new FlexLine();
        flexLine.mFirstIndex = i4;
        flexLine.mMainSize = paddingStartMain + paddingEndMain;
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        int i20 = 0;
        boolean z2 = z;
        int i21 = Integer.MIN_VALUE;
        int i22 = i4;
        int i23 = 0;
        FlexLine flexLine2 = flexLine;
        while (true) {
            if (i22 >= flexItemCount) {
                break;
            }
            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i22);
            if (reorderedFlexItemAt == null) {
                if (isLastFlexItem(i22, flexItemCount, flexLine2)) {
                    addFlexLine(list2, flexLine2, i22, i23);
                }
            } else if (reorderedFlexItemAt.getVisibility() == 8) {
                flexLine2.mGoneItemCount++;
                flexLine2.mItemCount++;
                if (isLastFlexItem(i22, flexItemCount, flexLine2)) {
                    addFlexLine(list2, flexLine2, i22, i23);
                }
            } else {
                if (reorderedFlexItemAt instanceof CompoundButton) {
                    evaluateMinimumSizeForCompoundButton((CompoundButton) reorderedFlexItemAt);
                }
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int i24 = flexItemCount;
                if (flexItem.getAlignSelf() == 4) {
                    flexLine2.mIndicesAlignSelfStretch.add(Integer.valueOf(i22));
                }
                int flexItemSizeMain = getFlexItemSizeMain(flexItem, isMainAxisDirectionHorizontal);
                if (flexItem.getFlexBasisPercent() != -1.0f && mode == 1073741824) {
                    i6 = Math.round(size * flexItem.getFlexBasisPercent());
                } else {
                    i6 = flexItemSizeMain;
                }
                if (isMainAxisDirectionHorizontal) {
                    int childWidthMeasureSpec = this.mFlexContainer.getChildWidthMeasureSpec(i17, paddingStartMain + paddingEndMain + getFlexItemMarginStartMain(flexItem, true) + getFlexItemMarginEndMain(flexItem, true), i6);
                    i7 = size;
                    i9 = 1;
                    i8 = mode;
                    list3 = list2;
                    int childHeightMeasureSpec = this.mFlexContainer.getChildHeightMeasureSpec(i2, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, true) + getFlexItemMarginEndCross(flexItem, true) + i23, getFlexItemSizeCross(flexItem, true));
                    reorderedFlexItemAt.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                    updateMeasureCache(i22, childWidthMeasureSpec, childHeightMeasureSpec, reorderedFlexItemAt);
                    i11 = childWidthMeasureSpec;
                    i10 = 0;
                } else {
                    list3 = list2;
                    i7 = size;
                    i8 = mode;
                    i9 = 1;
                    i10 = 0;
                    int childWidthMeasureSpec2 = this.mFlexContainer.getChildWidthMeasureSpec(i2, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, false) + getFlexItemMarginEndCross(flexItem, false) + i23, getFlexItemSizeCross(flexItem, false));
                    int childHeightMeasureSpec2 = this.mFlexContainer.getChildHeightMeasureSpec(i17, paddingStartMain + paddingEndMain + getFlexItemMarginStartMain(flexItem, false) + getFlexItemMarginEndMain(flexItem, false), i6);
                    reorderedFlexItemAt.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                    updateMeasureCache(i22, childWidthMeasureSpec2, childHeightMeasureSpec2, reorderedFlexItemAt);
                    i11 = childHeightMeasureSpec2;
                }
                this.mFlexContainer.updateViewCache(i22, reorderedFlexItemAt);
                checkSizeConstraints(reorderedFlexItemAt, i22);
                int combineMeasuredStates = View.combineMeasuredStates(i19, reorderedFlexItemAt.getMeasuredState());
                int i25 = flexLine2.mMainSize;
                int flexItemMarginEndMain = getFlexItemMarginEndMain(flexItem, isMainAxisDirectionHorizontal) + getViewMeasuredSizeMain(reorderedFlexItemAt, isMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, isMainAxisDirectionHorizontal);
                int size2 = list3.size();
                int i26 = i22;
                FlexLine flexLine3 = flexLine2;
                int i27 = i10;
                list4 = list3;
                int i28 = i11;
                int i29 = i23;
                if (isWrapRequired(reorderedFlexItemAt, i8, i7, i25, flexItemMarginEndMain, flexItem, i26, i20, size2)) {
                    if (flexLine3.getItemCountNotGone() <= 0) {
                        i12 = i26;
                    } else {
                        i12 = i26;
                        addFlexLine(list4, flexLine3, i12 > 0 ? i12 - 1 : i27, i29);
                        i29 = flexLine3.mCrossSize + i29;
                    }
                    if (!isMainAxisDirectionHorizontal) {
                        view = reorderedFlexItemAt;
                        if (flexItem.getWidth() == -1) {
                            view.measure(this.mFlexContainer.getChildWidthMeasureSpec(i2, this.mFlexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i29, flexItem.getWidth()), i28);
                            checkSizeConstraints(view, i12);
                        }
                    } else if (flexItem.getHeight() == -1) {
                        view = reorderedFlexItemAt;
                        view.measure(i28, this.mFlexContainer.getChildHeightMeasureSpec(i2, this.mFlexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i29, flexItem.getHeight()));
                        checkSizeConstraints(view, i12);
                    } else {
                        view = reorderedFlexItemAt;
                    }
                    flexLine2 = new FlexLine();
                    flexLine2.mItemCount = 1;
                    flexLine2.mMainSize = paddingStartMain + paddingEndMain;
                    flexLine2.mFirstIndex = i12;
                    i14 = 0;
                    i23 = i29;
                    i13 = Integer.MIN_VALUE;
                } else {
                    view = reorderedFlexItemAt;
                    i12 = i26;
                    flexLine3.mItemCount++;
                    flexLine2 = flexLine3;
                    i23 = i29;
                    i13 = i21;
                    i14 = i20 + 1;
                }
                flexLine2.mAnyItemsHaveFlexGrow = (flexLine2.mAnyItemsHaveFlexGrow ? 1 : 0) | (flexItem.getFlexGrow() != 0.0f ? 1 : i27);
                flexLine2.mAnyItemsHaveFlexShrink = (flexLine2.mAnyItemsHaveFlexShrink ? 1 : 0) | (flexItem.getFlexShrink() != 0.0f ? 1 : i27);
                if (this.mIndexToFlexLine != null) {
                    this.mIndexToFlexLine[i12] = list4.size();
                }
                flexLine2.mMainSize += getViewMeasuredSizeMain(view, isMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, isMainAxisDirectionHorizontal) + getFlexItemMarginEndMain(flexItem, isMainAxisDirectionHorizontal);
                flexLine2.mTotalFlexGrow += flexItem.getFlexGrow();
                flexLine2.mTotalFlexShrink += flexItem.getFlexShrink();
                this.mFlexContainer.onNewFlexItemAdded(view, i12, i14, flexLine2);
                int max = Math.max(i13, getViewMeasuredSizeCross(view, isMainAxisDirectionHorizontal) + getFlexItemMarginStartCross(flexItem, isMainAxisDirectionHorizontal) + getFlexItemMarginEndCross(flexItem, isMainAxisDirectionHorizontal) + this.mFlexContainer.getDecorationLengthCrossAxis(view));
                flexLine2.mCrossSize = Math.max(flexLine2.mCrossSize, max);
                if (isMainAxisDirectionHorizontal) {
                    if (this.mFlexContainer.getFlexWrap() != 2) {
                        flexLine2.mMaxBaseline = Math.max(flexLine2.mMaxBaseline, view.getBaseline() + flexItem.getMarginTop());
                    } else {
                        flexLine2.mMaxBaseline = Math.max(flexLine2.mMaxBaseline, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.getMarginBottom());
                    }
                }
                i15 = i24;
                if (isLastFlexItem(i12, i15, flexLine2)) {
                    addFlexLine(list4, flexLine2, i12, i23);
                    i23 += flexLine2.mCrossSize;
                }
                i16 = i5;
                if (i16 != -1 && list4.size() > 0 && list4.get(list4.size() - 1).mLastIndex >= i16 && i12 >= i16 && !z2) {
                    i23 = -flexLine2.getCrossSize();
                    z2 = true;
                }
                if (i23 > i3 && z2) {
                    i19 = combineMeasuredStates;
                    break;
                }
                i21 = max;
                i20 = i14;
                i19 = combineMeasuredStates;
                i22 = i12 + 1;
                flexItemCount = i15;
                i18 = i16;
                list2 = list4;
                size = i7;
                mode = i8;
                i17 = i;
            }
            i12 = i22;
            list4 = list2;
            i7 = size;
            i8 = mode;
            i16 = i18;
            i15 = flexItemCount;
            i22 = i12 + 1;
            flexItemCount = i15;
            i18 = i16;
            list2 = list4;
            size = i7;
            mode = i8;
            i17 = i;
        }
        flexLinesResult.mChildState = i19;
    }

    private void evaluateMinimumSizeForCompoundButton(CompoundButton compoundButton) {
        FlexItem flexItem = (FlexItem) compoundButton.getLayoutParams();
        int minWidth = flexItem.getMinWidth();
        int minHeight = flexItem.getMinHeight();
        Drawable drawable = CompoundButtonCompat.getButtonDrawable(compoundButton);
        int drawableMinWidth = drawable == null ? 0 : drawable.getMinimumWidth();
        int drawableMinHeight = drawable != null ? drawable.getMinimumHeight() : 0;
        flexItem.setMinWidth(minWidth == -1 ? drawableMinWidth : minWidth);
        flexItem.setMinHeight(minHeight == -1 ? drawableMinHeight : minHeight);
    }

    private int getPaddingStartMain(boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return this.mFlexContainer.getPaddingStart();
        }
        return this.mFlexContainer.getPaddingTop();
    }

    private int getPaddingEndMain(boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return this.mFlexContainer.getPaddingEnd();
        }
        return this.mFlexContainer.getPaddingBottom();
    }

    private int getPaddingStartCross(boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return this.mFlexContainer.getPaddingTop();
        }
        return this.mFlexContainer.getPaddingStart();
    }

    private int getPaddingEndCross(boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return this.mFlexContainer.getPaddingBottom();
        }
        return this.mFlexContainer.getPaddingEnd();
    }

    private int getViewMeasuredSizeMain(View view, boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    private int getViewMeasuredSizeCross(View view, boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return view.getMeasuredHeight();
        }
        return view.getMeasuredWidth();
    }

    private int getFlexItemSizeMain(FlexItem flexItem, boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return flexItem.getWidth();
        }
        return flexItem.getHeight();
    }

    private int getFlexItemSizeCross(FlexItem flexItem, boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return flexItem.getHeight();
        }
        return flexItem.getWidth();
    }

    private int getFlexItemMarginStartMain(FlexItem flexItem, boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return flexItem.getMarginLeft();
        }
        return flexItem.getMarginTop();
    }

    private int getFlexItemMarginEndMain(FlexItem flexItem, boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return flexItem.getMarginRight();
        }
        return flexItem.getMarginBottom();
    }

    private int getFlexItemMarginStartCross(FlexItem flexItem, boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return flexItem.getMarginTop();
        }
        return flexItem.getMarginLeft();
    }

    private int getFlexItemMarginEndCross(FlexItem flexItem, boolean isMainHorizontal) {
        if (isMainHorizontal) {
            return flexItem.getMarginBottom();
        }
        return flexItem.getMarginRight();
    }

    private boolean isWrapRequired(View view, int mode, int maxSize, int currentLength, int childLength, FlexItem flexItem, int index, int indexInFlexLine, int flexLinesSize) {
        if (this.mFlexContainer.getFlexWrap() == 0) {
            return false;
        }
        if (flexItem.isWrapBefore()) {
            return true;
        }
        if (mode == 0) {
            return false;
        }
        int maxLine = this.mFlexContainer.getMaxLine();
        if (maxLine != -1 && maxLine <= flexLinesSize + 1) {
            return false;
        }
        int decorationLength = this.mFlexContainer.getDecorationLengthMainAxis(view, index, indexInFlexLine);
        if (decorationLength > 0) {
            childLength += decorationLength;
        }
        return maxSize < currentLength + childLength;
    }

    private boolean isLastFlexItem(int childIndex, int childCount, FlexLine flexLine) {
        return childIndex == childCount + (-1) && flexLine.getItemCountNotGone() != 0;
    }

    private void addFlexLine(List<FlexLine> flexLines, FlexLine flexLine, int viewIndex, int usedCrossSizeSoFar) {
        flexLine.mSumCrossSizeBefore = usedCrossSizeSoFar;
        this.mFlexContainer.onNewFlexLineAdded(flexLine);
        flexLine.mLastIndex = viewIndex;
        flexLines.add(flexLine);
    }

    private void checkSizeConstraints(View view, int index) {
        boolean needsMeasure = false;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int childWidth = view.getMeasuredWidth();
        int childHeight = view.getMeasuredHeight();
        if (childWidth < flexItem.getMinWidth()) {
            needsMeasure = true;
            childWidth = flexItem.getMinWidth();
        } else if (childWidth > flexItem.getMaxWidth()) {
            needsMeasure = true;
            childWidth = flexItem.getMaxWidth();
        }
        if (childHeight < flexItem.getMinHeight()) {
            needsMeasure = true;
            childHeight = flexItem.getMinHeight();
        } else if (childHeight > flexItem.getMaxHeight()) {
            needsMeasure = true;
            childHeight = flexItem.getMaxHeight();
        }
        if (needsMeasure) {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(childWidth, 1073741824);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(childHeight, 1073741824);
            view.measure(widthSpec, heightSpec);
            updateMeasureCache(index, widthSpec, heightSpec, view);
            this.mFlexContainer.updateViewCache(index, view);
        }
    }

    void determineMainSize(int widthMeasureSpec, int heightMeasureSpec) {
        determineMainSize(widthMeasureSpec, heightMeasureSpec, 0);
    }

    void determineMainSize(int widthMeasureSpec, int heightMeasureSpec, int fromIndex) {
        int mainSize;
        int mainSize2;
        int paddingAlongMainAxis;
        int flexLineIndex;
        int mainSize3;
        ensureChildrenFrozen(this.mFlexContainer.getFlexItemCount());
        if (fromIndex >= this.mFlexContainer.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.mFlexContainer.getFlexDirection();
        switch (this.mFlexContainer.getFlexDirection()) {
            case 0:
            case 1:
                int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
                int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
                int largestMainSize = this.mFlexContainer.getLargestMainSize();
                if (widthMode == 1073741824) {
                    mainSize = widthSize;
                } else {
                    mainSize = Math.min(largestMainSize, widthSize);
                }
                int paddingAlongMainAxis2 = this.mFlexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight();
                mainSize2 = mainSize;
                paddingAlongMainAxis = paddingAlongMainAxis2;
                break;
            case 2:
            case 3:
                int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
                int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
                if (heightMode == 1073741824) {
                    mainSize3 = heightSize;
                } else {
                    mainSize3 = this.mFlexContainer.getLargestMainSize();
                }
                int paddingAlongMainAxis3 = this.mFlexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom();
                mainSize2 = mainSize3;
                paddingAlongMainAxis = paddingAlongMainAxis3;
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        if (this.mIndexToFlexLine == null) {
            flexLineIndex = 0;
        } else {
            int flexLineIndex2 = this.mIndexToFlexLine[fromIndex];
            flexLineIndex = flexLineIndex2;
        }
        List<FlexLine> flexLines = this.mFlexContainer.getFlexLinesInternal();
        int i = flexLineIndex;
        int size = flexLines.size();
        for (int i2 = i; i2 < size; i2++) {
            FlexLine flexLine = flexLines.get(i2);
            if (flexLine.mMainSize < mainSize2 && flexLine.mAnyItemsHaveFlexGrow) {
                expandFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, mainSize2, paddingAlongMainAxis, false);
            } else if (flexLine.mMainSize > mainSize2 && flexLine.mAnyItemsHaveFlexShrink) {
                shrinkFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, mainSize2, paddingAlongMainAxis, false);
            }
        }
    }

    private void ensureChildrenFrozen(int size) {
        if (this.mChildrenFrozen == null) {
            this.mChildrenFrozen = new boolean[Math.max(size, 10)];
        } else if (this.mChildrenFrozen.length < size) {
            int newCapacity = this.mChildrenFrozen.length * 2;
            this.mChildrenFrozen = new boolean[Math.max(newCapacity, size)];
        } else {
            Arrays.fill(this.mChildrenFrozen, false);
        }
    }

    private void expandFlexItems(int widthMeasureSpec, int heightMeasureSpec, FlexLine flexLine, int maxMainSize, int paddingAlongMainAxis, boolean calledRecursively) {
        int sizeBeforeExpand;
        int sizeBeforeExpand2;
        boolean needsReexpand;
        int flexDirection;
        boolean needsReexpand2;
        int largestCrossSize;
        boolean needsReexpand3;
        int childMeasuredWidth;
        View child;
        int childMeasuredHeight;
        View child2;
        int childMeasuredHeight2;
        int childMeasuredWidth2;
        if (flexLine.mTotalFlexGrow <= 0.0f || maxMainSize < flexLine.mMainSize) {
            return;
        }
        int sizeBeforeExpand3 = flexLine.mMainSize;
        float unitSpace = (maxMainSize - flexLine.mMainSize) / flexLine.mTotalFlexGrow;
        flexLine.mMainSize = paddingAlongMainAxis + flexLine.mDividerLengthInMainSize;
        if (!calledRecursively) {
            flexLine.mCrossSize = Integer.MIN_VALUE;
        }
        int i = 0;
        boolean needsReexpand4 = false;
        int largestCrossSize2 = 0;
        float accumulatedRoundError = 0.0f;
        while (i < flexLine.mItemCount) {
            int index = flexLine.mFirstIndex + i;
            View child3 = this.mFlexContainer.getReorderedFlexItemAt(index);
            if (child3 == null) {
                sizeBeforeExpand = sizeBeforeExpand3;
                sizeBeforeExpand2 = largestCrossSize2;
                needsReexpand = needsReexpand4;
            } else if (child3.getVisibility() == 8) {
                sizeBeforeExpand = sizeBeforeExpand3;
                sizeBeforeExpand2 = largestCrossSize2;
                needsReexpand = needsReexpand4;
            } else {
                FlexItem flexItem = (FlexItem) child3.getLayoutParams();
                int flexDirection2 = this.mFlexContainer.getFlexDirection();
                if (flexDirection2 == 0) {
                    sizeBeforeExpand = sizeBeforeExpand3;
                } else if (flexDirection2 == 1) {
                    sizeBeforeExpand = sizeBeforeExpand3;
                } else {
                    int childMeasuredHeight3 = child3.getMeasuredHeight();
                    if (this.mMeasuredSizeCache == null) {
                        child = child3;
                        childMeasuredHeight = childMeasuredHeight3;
                    } else {
                        child = child3;
                        int childMeasuredHeight4 = extractHigherInt(this.mMeasuredSizeCache[index]);
                        childMeasuredHeight = childMeasuredHeight4;
                    }
                    int childMeasuredWidth3 = child.getMeasuredWidth();
                    if (this.mMeasuredSizeCache == null) {
                        sizeBeforeExpand = sizeBeforeExpand3;
                    } else {
                        sizeBeforeExpand = sizeBeforeExpand3;
                        childMeasuredWidth3 = extractLowerInt(this.mMeasuredSizeCache[index]);
                    }
                    if (!this.mChildrenFrozen[index] && flexItem.getFlexGrow() > 0.0f) {
                        float rawCalculatedHeight = childMeasuredHeight + (flexItem.getFlexGrow() * unitSpace);
                        if (i == flexLine.mItemCount - 1) {
                            rawCalculatedHeight += accumulatedRoundError;
                            accumulatedRoundError = 0.0f;
                        }
                        int newHeight = Math.round(rawCalculatedHeight);
                        if (newHeight > flexItem.getMaxHeight()) {
                            needsReexpand4 = true;
                            newHeight = flexItem.getMaxHeight();
                            this.mChildrenFrozen[index] = true;
                            flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                        } else {
                            accumulatedRoundError += rawCalculatedHeight - newHeight;
                            if (accumulatedRoundError > 1.0d) {
                                newHeight++;
                                accumulatedRoundError = (float) (accumulatedRoundError - 1.0d);
                            } else if (accumulatedRoundError < -1.0d) {
                                newHeight--;
                                accumulatedRoundError = (float) (accumulatedRoundError + 1.0d);
                            }
                        }
                        int childWidthMeasureSpec = getChildWidthMeasureSpecInternal(widthMeasureSpec, flexItem, flexLine.mSumCrossSizeBefore);
                        int childHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(newHeight, 1073741824);
                        child2 = child;
                        child2.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                        childMeasuredWidth2 = child2.getMeasuredWidth();
                        int childMeasuredHeight5 = child2.getMeasuredHeight();
                        updateMeasureCache(index, childWidthMeasureSpec, childHeightMeasureSpec, child2);
                        this.mFlexContainer.updateViewCache(index, child2);
                        childMeasuredHeight2 = childMeasuredHeight5;
                    } else {
                        int childMeasuredWidth4 = childMeasuredWidth3;
                        child2 = child;
                        childMeasuredHeight2 = childMeasuredHeight;
                        childMeasuredWidth2 = childMeasuredWidth4;
                    }
                    int largestCrossSize3 = Math.max(largestCrossSize2, childMeasuredWidth2 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(child2));
                    flexLine.mMainSize += flexItem.getMarginTop() + childMeasuredHeight2 + flexItem.getMarginBottom();
                    flexDirection = largestCrossSize3;
                    flexLine.mCrossSize = Math.max(flexLine.mCrossSize, flexDirection);
                    largestCrossSize2 = flexDirection;
                    i++;
                    sizeBeforeExpand3 = sizeBeforeExpand;
                }
                int childMeasuredWidth5 = child3.getMeasuredWidth();
                if (this.mMeasuredSizeCache != null) {
                    needsReexpand2 = needsReexpand4;
                    largestCrossSize = largestCrossSize2;
                    childMeasuredWidth5 = extractLowerInt(this.mMeasuredSizeCache[index]);
                } else {
                    needsReexpand2 = needsReexpand4;
                    largestCrossSize = largestCrossSize2;
                }
                int childMeasuredHeight6 = child3.getMeasuredHeight();
                if (this.mMeasuredSizeCache != null) {
                    needsReexpand3 = needsReexpand2;
                    childMeasuredHeight6 = extractHigherInt(this.mMeasuredSizeCache[index]);
                } else {
                    needsReexpand3 = needsReexpand2;
                }
                if (!this.mChildrenFrozen[index] && flexItem.getFlexGrow() > 0.0f) {
                    float rawCalculatedWidth = childMeasuredWidth5 + (flexItem.getFlexGrow() * unitSpace);
                    if (i == flexLine.mItemCount - 1) {
                        rawCalculatedWidth += accumulatedRoundError;
                        accumulatedRoundError = 0.0f;
                    }
                    int newWidth = Math.round(rawCalculatedWidth);
                    if (newWidth > flexItem.getMaxWidth()) {
                        newWidth = flexItem.getMaxWidth();
                        this.mChildrenFrozen[index] = true;
                        flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                        needsReexpand3 = true;
                    } else {
                        accumulatedRoundError += rawCalculatedWidth - newWidth;
                        if (accumulatedRoundError > 1.0d) {
                            newWidth++;
                            accumulatedRoundError = (float) (accumulatedRoundError - 1.0d);
                        } else if (accumulatedRoundError < -1.0d) {
                            newWidth--;
                            accumulatedRoundError = (float) (accumulatedRoundError + 1.0d);
                        }
                    }
                    int childHeightMeasureSpec2 = getChildHeightMeasureSpecInternal(heightMeasureSpec, flexItem, flexLine.mSumCrossSizeBefore);
                    int childWidthMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(newWidth, 1073741824);
                    child3.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                    int childMeasuredWidth6 = child3.getMeasuredWidth();
                    childMeasuredHeight6 = child3.getMeasuredHeight();
                    updateMeasureCache(index, childWidthMeasureSpec2, childHeightMeasureSpec2, child3);
                    this.mFlexContainer.updateViewCache(index, child3);
                    needsReexpand4 = needsReexpand3;
                    childMeasuredWidth = childMeasuredWidth6;
                } else {
                    needsReexpand4 = needsReexpand3;
                    childMeasuredWidth = childMeasuredWidth5;
                }
                flexDirection = Math.max(largestCrossSize, flexItem.getMarginTop() + childMeasuredHeight6 + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(child3));
                int largestCrossSize4 = flexLine.mMainSize;
                flexLine.mMainSize = largestCrossSize4 + flexItem.getMarginLeft() + childMeasuredWidth + flexItem.getMarginRight();
                flexLine.mCrossSize = Math.max(flexLine.mCrossSize, flexDirection);
                largestCrossSize2 = flexDirection;
                i++;
                sizeBeforeExpand3 = sizeBeforeExpand;
            }
            needsReexpand4 = needsReexpand;
            largestCrossSize2 = sizeBeforeExpand2;
            i++;
            sizeBeforeExpand3 = sizeBeforeExpand;
        }
        int sizeBeforeExpand4 = sizeBeforeExpand3;
        if (needsReexpand4 && sizeBeforeExpand4 != flexLine.mMainSize) {
            expandFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, maxMainSize, paddingAlongMainAxis, true);
        }
    }

    private void shrinkFlexItems(int widthMeasureSpec, int heightMeasureSpec, FlexLine flexLine, int maxMainSize, int paddingAlongMainAxis, boolean calledRecursively) {
        int sizeBeforeShrink;
        int sizeBeforeShrink2;
        int flexDirection;
        int largestCrossSize;
        int largestCrossSize2;
        int childMeasuredWidth;
        View child;
        int childMeasuredHeight;
        int childMeasuredWidth2;
        View child2;
        int childMeasuredHeight2;
        int sizeBeforeShrink3 = flexLine.mMainSize;
        if (flexLine.mTotalFlexShrink > 0.0f && maxMainSize <= flexLine.mMainSize) {
            float unitShrink = (flexLine.mMainSize - maxMainSize) / flexLine.mTotalFlexShrink;
            flexLine.mMainSize = paddingAlongMainAxis + flexLine.mDividerLengthInMainSize;
            if (!calledRecursively) {
                flexLine.mCrossSize = Integer.MIN_VALUE;
            }
            int i = 0;
            boolean needsReshrink = false;
            float accumulatedRoundError = 0.0f;
            int largestCrossSize3 = 0;
            while (i < flexLine.mItemCount) {
                int index = flexLine.mFirstIndex + i;
                View child3 = this.mFlexContainer.getReorderedFlexItemAt(index);
                if (child3 == null) {
                    sizeBeforeShrink = sizeBeforeShrink3;
                    sizeBeforeShrink2 = largestCrossSize3;
                } else if (child3.getVisibility() == 8) {
                    sizeBeforeShrink = sizeBeforeShrink3;
                    sizeBeforeShrink2 = largestCrossSize3;
                } else {
                    FlexItem flexItem = (FlexItem) child3.getLayoutParams();
                    int flexDirection2 = this.mFlexContainer.getFlexDirection();
                    if (flexDirection2 == 0) {
                        sizeBeforeShrink = sizeBeforeShrink3;
                    } else if (flexDirection2 == 1) {
                        sizeBeforeShrink = sizeBeforeShrink3;
                    } else {
                        int childMeasuredHeight3 = child3.getMeasuredHeight();
                        if (this.mMeasuredSizeCache == null) {
                            child = child3;
                            childMeasuredHeight = childMeasuredHeight3;
                        } else {
                            child = child3;
                            int childMeasuredHeight4 = extractHigherInt(this.mMeasuredSizeCache[index]);
                            childMeasuredHeight = childMeasuredHeight4;
                        }
                        int childMeasuredWidth3 = child.getMeasuredWidth();
                        if (this.mMeasuredSizeCache == null) {
                            sizeBeforeShrink = sizeBeforeShrink3;
                        } else {
                            sizeBeforeShrink = sizeBeforeShrink3;
                            childMeasuredWidth3 = extractLowerInt(this.mMeasuredSizeCache[index]);
                        }
                        if (!this.mChildrenFrozen[index] && flexItem.getFlexShrink() > 0.0f) {
                            float rawCalculatedHeight = childMeasuredHeight - (flexItem.getFlexShrink() * unitShrink);
                            if (i == flexLine.mItemCount - 1) {
                                rawCalculatedHeight += accumulatedRoundError;
                                accumulatedRoundError = 0.0f;
                            }
                            int newHeight = Math.round(rawCalculatedHeight);
                            if (newHeight < flexItem.getMinHeight()) {
                                needsReshrink = true;
                                newHeight = flexItem.getMinHeight();
                                this.mChildrenFrozen[index] = true;
                                flexLine.mTotalFlexShrink -= flexItem.getFlexShrink();
                            } else {
                                accumulatedRoundError += rawCalculatedHeight - newHeight;
                                if (accumulatedRoundError > 1.0d) {
                                    newHeight++;
                                    accumulatedRoundError -= 1.0f;
                                } else if (accumulatedRoundError < -1.0d) {
                                    newHeight--;
                                    accumulatedRoundError += 1.0f;
                                }
                            }
                            int childWidthMeasureSpec = getChildWidthMeasureSpecInternal(widthMeasureSpec, flexItem, flexLine.mSumCrossSizeBefore);
                            int childHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(newHeight, 1073741824);
                            child2 = child;
                            child2.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                            int childMeasuredWidth4 = child2.getMeasuredWidth();
                            int childMeasuredHeight5 = child2.getMeasuredHeight();
                            updateMeasureCache(index, childWidthMeasureSpec, childHeightMeasureSpec, child2);
                            this.mFlexContainer.updateViewCache(index, child2);
                            childMeasuredHeight2 = childMeasuredHeight5;
                            childMeasuredWidth2 = childMeasuredWidth4;
                        } else {
                            childMeasuredWidth2 = childMeasuredWidth3;
                            child2 = child;
                            childMeasuredHeight2 = childMeasuredHeight;
                        }
                        int largestCrossSize4 = Math.max(largestCrossSize3, childMeasuredWidth2 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(child2));
                        flexLine.mMainSize += flexItem.getMarginTop() + childMeasuredHeight2 + flexItem.getMarginBottom();
                        flexDirection = largestCrossSize4;
                        flexLine.mCrossSize = Math.max(flexLine.mCrossSize, flexDirection);
                        largestCrossSize3 = flexDirection;
                        i++;
                        sizeBeforeShrink3 = sizeBeforeShrink;
                    }
                    int childMeasuredWidth5 = child3.getMeasuredWidth();
                    if (this.mMeasuredSizeCache != null) {
                        largestCrossSize = largestCrossSize3;
                        childMeasuredWidth5 = extractLowerInt(this.mMeasuredSizeCache[index]);
                    } else {
                        largestCrossSize = largestCrossSize3;
                    }
                    int childMeasuredHeight6 = child3.getMeasuredHeight();
                    if (this.mMeasuredSizeCache != null) {
                        largestCrossSize2 = largestCrossSize;
                        childMeasuredHeight6 = extractHigherInt(this.mMeasuredSizeCache[index]);
                    } else {
                        largestCrossSize2 = largestCrossSize;
                    }
                    if (!this.mChildrenFrozen[index] && flexItem.getFlexShrink() > 0.0f) {
                        float rawCalculatedWidth = childMeasuredWidth5 - (flexItem.getFlexShrink() * unitShrink);
                        if (i == flexLine.mItemCount - 1) {
                            rawCalculatedWidth += accumulatedRoundError;
                            accumulatedRoundError = 0.0f;
                        }
                        int newWidth = Math.round(rawCalculatedWidth);
                        if (newWidth < flexItem.getMinWidth()) {
                            newWidth = flexItem.getMinWidth();
                            this.mChildrenFrozen[index] = true;
                            flexLine.mTotalFlexShrink -= flexItem.getFlexShrink();
                            needsReshrink = true;
                        } else {
                            accumulatedRoundError += rawCalculatedWidth - newWidth;
                            if (accumulatedRoundError > 1.0d) {
                                newWidth++;
                                accumulatedRoundError -= 1.0f;
                            } else if (accumulatedRoundError < -1.0d) {
                                newWidth--;
                                accumulatedRoundError += 1.0f;
                            }
                        }
                        int childHeightMeasureSpec2 = getChildHeightMeasureSpecInternal(heightMeasureSpec, flexItem, flexLine.mSumCrossSizeBefore);
                        int childWidthMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(newWidth, 1073741824);
                        child3.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                        int childMeasuredWidth6 = child3.getMeasuredWidth();
                        childMeasuredHeight6 = child3.getMeasuredHeight();
                        updateMeasureCache(index, childWidthMeasureSpec2, childHeightMeasureSpec2, child3);
                        this.mFlexContainer.updateViewCache(index, child3);
                        childMeasuredWidth = childMeasuredWidth6;
                    } else {
                        childMeasuredWidth = childMeasuredWidth5;
                    }
                    flexDirection = Math.max(largestCrossSize2, flexItem.getMarginTop() + childMeasuredHeight6 + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(child3));
                    int largestCrossSize5 = flexLine.mMainSize;
                    flexLine.mMainSize = largestCrossSize5 + flexItem.getMarginLeft() + childMeasuredWidth + flexItem.getMarginRight();
                    flexLine.mCrossSize = Math.max(flexLine.mCrossSize, flexDirection);
                    largestCrossSize3 = flexDirection;
                    i++;
                    sizeBeforeShrink3 = sizeBeforeShrink;
                }
                largestCrossSize3 = sizeBeforeShrink2;
                i++;
                sizeBeforeShrink3 = sizeBeforeShrink;
            }
            int sizeBeforeShrink4 = sizeBeforeShrink3;
            if (needsReshrink && sizeBeforeShrink4 != flexLine.mMainSize) {
                shrinkFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, maxMainSize, paddingAlongMainAxis, true);
            }
        }
    }

    private int getChildWidthMeasureSpecInternal(int widthMeasureSpec, FlexItem flexItem, int padding) {
        int childWidthMeasureSpec = this.mFlexContainer.getChildWidthMeasureSpec(widthMeasureSpec, this.mFlexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + padding, flexItem.getWidth());
        int childWidth = View.MeasureSpec.getSize(childWidthMeasureSpec);
        if (childWidth > flexItem.getMaxWidth()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        if (childWidth < flexItem.getMinWidth()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMinWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return childWidthMeasureSpec;
    }

    private int getChildHeightMeasureSpecInternal(int heightMeasureSpec, FlexItem flexItem, int padding) {
        int childHeightMeasureSpec = this.mFlexContainer.getChildHeightMeasureSpec(heightMeasureSpec, this.mFlexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + padding, flexItem.getHeight());
        int childHeight = View.MeasureSpec.getSize(childHeightMeasureSpec);
        if (childHeight > flexItem.getMaxHeight()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        if (childHeight < flexItem.getMinHeight()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMinHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return childHeightMeasureSpec;
    }

    void determineCrossSize(int widthMeasureSpec, int heightMeasureSpec, int paddingAlongCrossAxis) {
        int mode;
        int size;
        int flexDirection = this.mFlexContainer.getFlexDirection();
        switch (flexDirection) {
            case 0:
            case 1:
                mode = View.MeasureSpec.getMode(heightMeasureSpec);
                size = View.MeasureSpec.getSize(heightMeasureSpec);
                break;
            case 2:
            case 3:
                mode = View.MeasureSpec.getMode(widthMeasureSpec);
                size = View.MeasureSpec.getSize(widthMeasureSpec);
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        List<FlexLine> flexLines = this.mFlexContainer.getFlexLinesInternal();
        if (mode == 1073741824) {
            int totalCrossSize = this.mFlexContainer.getSumOfCrossSize() + paddingAlongCrossAxis;
            int i = 1;
            if (flexLines.size() == 1) {
                flexLines.get(0).mCrossSize = size - paddingAlongCrossAxis;
                return;
            }
            if (flexLines.size() >= 2) {
                switch (this.mFlexContainer.getAlignContent()) {
                    case 1:
                        int spaceTop = size - totalCrossSize;
                        FlexLine dummySpaceFlexLine = new FlexLine();
                        dummySpaceFlexLine.mCrossSize = spaceTop;
                        flexLines.add(0, dummySpaceFlexLine);
                        return;
                    case 2:
                        this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLines, size, totalCrossSize));
                        return;
                    case 3:
                        if (totalCrossSize < size) {
                            int numberOfSpaces = flexLines.size() - 1;
                            float spaceBetweenFlexLine = (size - totalCrossSize) / numberOfSpaces;
                            float accumulatedError = 0.0f;
                            List<FlexLine> newFlexLines = new ArrayList<>();
                            int flexLineSize = flexLines.size();
                            for (int i2 = 0; i2 < flexLineSize; i2++) {
                                FlexLine flexLine = flexLines.get(i2);
                                newFlexLines.add(flexLine);
                                if (i2 != flexLines.size() - 1) {
                                    FlexLine dummySpaceFlexLine2 = new FlexLine();
                                    if (i2 == flexLines.size() - 2) {
                                        dummySpaceFlexLine2.mCrossSize = Math.round(spaceBetweenFlexLine + accumulatedError);
                                        accumulatedError = 0.0f;
                                    } else {
                                        dummySpaceFlexLine2.mCrossSize = Math.round(spaceBetweenFlexLine);
                                    }
                                    accumulatedError += spaceBetweenFlexLine - dummySpaceFlexLine2.mCrossSize;
                                    if (accumulatedError > 1.0f) {
                                        dummySpaceFlexLine2.mCrossSize++;
                                        accumulatedError -= 1.0f;
                                    } else if (accumulatedError < -1.0f) {
                                        dummySpaceFlexLine2.mCrossSize--;
                                        accumulatedError += 1.0f;
                                    }
                                    newFlexLines.add(dummySpaceFlexLine2);
                                }
                            }
                            this.mFlexContainer.setFlexLines(newFlexLines);
                            return;
                        }
                        return;
                    case 4:
                        if (totalCrossSize >= size) {
                            this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLines, size, totalCrossSize));
                            return;
                        }
                        int spaceTopAndBottom = size - totalCrossSize;
                        int numberOfSpaces2 = flexLines.size() * 2;
                        List<FlexLine> newFlexLines2 = new ArrayList<>();
                        FlexLine dummySpaceFlexLine3 = new FlexLine();
                        dummySpaceFlexLine3.mCrossSize = spaceTopAndBottom / numberOfSpaces2;
                        for (FlexLine flexLine2 : flexLines) {
                            newFlexLines2.add(dummySpaceFlexLine3);
                            newFlexLines2.add(flexLine2);
                            newFlexLines2.add(dummySpaceFlexLine3);
                        }
                        this.mFlexContainer.setFlexLines(newFlexLines2);
                        return;
                    case 5:
                        if (totalCrossSize < size) {
                            float freeSpaceUnit = (size - totalCrossSize) / flexLines.size();
                            float accumulatedError2 = 0.0f;
                            int i3 = 0;
                            int flexLinesSize = flexLines.size();
                            while (i3 < flexLinesSize) {
                                FlexLine flexLine3 = flexLines.get(i3);
                                float newCrossSizeAsFloat = flexLine3.mCrossSize + freeSpaceUnit;
                                if (i3 == flexLines.size() - i) {
                                    newCrossSizeAsFloat += accumulatedError2;
                                    accumulatedError2 = 0.0f;
                                }
                                int newCrossSize = Math.round(newCrossSizeAsFloat);
                                accumulatedError2 += newCrossSizeAsFloat - newCrossSize;
                                if (accumulatedError2 > 1.0f) {
                                    newCrossSize++;
                                    accumulatedError2 -= 1.0f;
                                } else if (accumulatedError2 < -1.0f) {
                                    newCrossSize--;
                                    accumulatedError2 += 1.0f;
                                }
                                flexLine3.mCrossSize = newCrossSize;
                                i3++;
                                i = 1;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private List<FlexLine> constructFlexLinesForAlignContentCenter(List<FlexLine> flexLines, int size, int totalCrossSize) {
        int spaceAboveAndBottom = size - totalCrossSize;
        List<FlexLine> newFlexLines = new ArrayList<>();
        FlexLine dummySpaceFlexLine = new FlexLine();
        dummySpaceFlexLine.mCrossSize = spaceAboveAndBottom / 2;
        int flexLineSize = flexLines.size();
        for (int i = 0; i < flexLineSize; i++) {
            if (i == 0) {
                newFlexLines.add(dummySpaceFlexLine);
            }
            FlexLine flexLine = flexLines.get(i);
            newFlexLines.add(flexLine);
            if (i == flexLines.size() - 1) {
                newFlexLines.add(dummySpaceFlexLine);
            }
        }
        return newFlexLines;
    }

    void stretchViews() {
        stretchViews(0);
    }

    void stretchViews(int fromIndex) {
        char c;
        if (fromIndex >= this.mFlexContainer.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.mFlexContainer.getFlexDirection();
        char c2 = 4;
        if (this.mFlexContainer.getAlignItems() == 4) {
            int flexLineIndex = 0;
            if (this.mIndexToFlexLine != null) {
                flexLineIndex = this.mIndexToFlexLine[fromIndex];
            }
            List<FlexLine> flexLines = this.mFlexContainer.getFlexLinesInternal();
            int size = flexLines.size();
            for (int i = flexLineIndex; i < size; i++) {
                FlexLine flexLine = flexLines.get(i);
                int j = 0;
                int itemCount = flexLine.mItemCount;
                while (j < itemCount) {
                    int viewIndex = flexLine.mFirstIndex + j;
                    if (j >= this.mFlexContainer.getFlexItemCount()) {
                        c = c2;
                    } else {
                        View view = this.mFlexContainer.getReorderedFlexItemAt(viewIndex);
                        if (view == null) {
                            c = c2;
                        } else if (view.getVisibility() == 8) {
                            c = c2;
                        } else {
                            FlexItem flexItem = (FlexItem) view.getLayoutParams();
                            if (flexItem.getAlignSelf() == -1) {
                                c = 4;
                            } else {
                                c = 4;
                                if (flexItem.getAlignSelf() != 4) {
                                    continue;
                                }
                            }
                            switch (flexDirection) {
                                case 0:
                                case 1:
                                    stretchViewVertically(view, flexLine.mCrossSize, viewIndex);
                                    break;
                                case 2:
                                case 3:
                                    stretchViewHorizontally(view, flexLine.mCrossSize, viewIndex);
                                    break;
                                default:
                                    throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                            }
                        }
                    }
                    j++;
                    c2 = c;
                }
            }
            return;
        }
        for (FlexLine flexLine2 : this.mFlexContainer.getFlexLinesInternal()) {
            for (Integer index : flexLine2.mIndicesAlignSelfStretch) {
                View view2 = this.mFlexContainer.getReorderedFlexItemAt(index.intValue());
                switch (flexDirection) {
                    case 0:
                    case 1:
                        stretchViewVertically(view2, flexLine2.mCrossSize, index.intValue());
                        break;
                    case 2:
                    case 3:
                        stretchViewHorizontally(view2, flexLine2.mCrossSize, index.intValue());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                }
            }
        }
    }

    private void stretchViewVertically(View view, int crossSize, int index) {
        int measuredWidth;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int newHeight = ((crossSize - flexItem.getMarginTop()) - flexItem.getMarginBottom()) - this.mFlexContainer.getDecorationLengthCrossAxis(view);
        int newHeight2 = Math.min(Math.max(newHeight, flexItem.getMinHeight()), flexItem.getMaxHeight());
        if (this.mMeasuredSizeCache != null) {
            measuredWidth = extractLowerInt(this.mMeasuredSizeCache[index]);
        } else {
            measuredWidth = view.getMeasuredWidth();
        }
        int childWidthSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int childHeightSpec = View.MeasureSpec.makeMeasureSpec(newHeight2, 1073741824);
        view.measure(childWidthSpec, childHeightSpec);
        updateMeasureCache(index, childWidthSpec, childHeightSpec, view);
        this.mFlexContainer.updateViewCache(index, view);
    }

    private void stretchViewHorizontally(View view, int crossSize, int index) {
        int measuredHeight;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int newWidth = ((crossSize - flexItem.getMarginLeft()) - flexItem.getMarginRight()) - this.mFlexContainer.getDecorationLengthCrossAxis(view);
        int newWidth2 = Math.min(Math.max(newWidth, flexItem.getMinWidth()), flexItem.getMaxWidth());
        if (this.mMeasuredSizeCache != null) {
            measuredHeight = extractHigherInt(this.mMeasuredSizeCache[index]);
        } else {
            measuredHeight = view.getMeasuredHeight();
        }
        int childHeightSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        int childWidthSpec = View.MeasureSpec.makeMeasureSpec(newWidth2, 1073741824);
        view.measure(childWidthSpec, childHeightSpec);
        updateMeasureCache(index, childWidthSpec, childHeightSpec, view);
        this.mFlexContainer.updateViewCache(index, view);
    }

    void layoutSingleChildHorizontal(View view, FlexLine flexLine, int left, int top, int right, int bottom) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int crossSize = flexLine.mCrossSize;
        switch (alignItems) {
            case 0:
            case 4:
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    view.layout(left, flexItem.getMarginTop() + top, right, flexItem.getMarginTop() + bottom);
                    break;
                } else {
                    view.layout(left, top - flexItem.getMarginBottom(), right, bottom - flexItem.getMarginBottom());
                    break;
                }
            case 1:
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    view.layout(left, ((top + crossSize) - view.getMeasuredHeight()) - flexItem.getMarginBottom(), right, (top + crossSize) - flexItem.getMarginBottom());
                    break;
                } else {
                    view.layout(left, (top - crossSize) + view.getMeasuredHeight() + flexItem.getMarginTop(), right, (bottom - crossSize) + view.getMeasuredHeight() + flexItem.getMarginTop());
                    break;
                }
            case 2:
                int topFromCrossAxis = (((crossSize - view.getMeasuredHeight()) + flexItem.getMarginTop()) - flexItem.getMarginBottom()) / 2;
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    view.layout(left, top + topFromCrossAxis, right, top + topFromCrossAxis + view.getMeasuredHeight());
                    break;
                } else {
                    view.layout(left, top - topFromCrossAxis, right, (top - topFromCrossAxis) + view.getMeasuredHeight());
                    break;
                }
            case 3:
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int marginTop = Math.max(flexLine.mMaxBaseline - view.getBaseline(), flexItem.getMarginTop());
                    view.layout(left, top + marginTop, right, bottom + marginTop);
                    break;
                } else {
                    int marginBottom = Math.max((flexLine.mMaxBaseline - view.getMeasuredHeight()) + view.getBaseline(), flexItem.getMarginBottom());
                    view.layout(left, top - marginBottom, right, bottom - marginBottom);
                    break;
                }
        }
    }

    void layoutSingleChildVertical(View view, FlexLine flexLine, boolean isRtl, int left, int top, int right, int bottom) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int crossSize = flexLine.mCrossSize;
        switch (alignItems) {
            case 0:
            case 3:
            case 4:
                if (!isRtl) {
                    view.layout(flexItem.getMarginLeft() + left, top, flexItem.getMarginLeft() + right, bottom);
                    break;
                } else {
                    view.layout(left - flexItem.getMarginRight(), top, right - flexItem.getMarginRight(), bottom);
                    break;
                }
            case 1:
                if (!isRtl) {
                    view.layout(((left + crossSize) - view.getMeasuredWidth()) - flexItem.getMarginRight(), top, ((right + crossSize) - view.getMeasuredWidth()) - flexItem.getMarginRight(), bottom);
                    break;
                } else {
                    view.layout((left - crossSize) + view.getMeasuredWidth() + flexItem.getMarginLeft(), top, (right - crossSize) + view.getMeasuredWidth() + flexItem.getMarginLeft(), bottom);
                    break;
                }
            case 2:
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int leftFromCrossAxis = (((crossSize - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(lp)) - MarginLayoutParamsCompat.getMarginEnd(lp)) / 2;
                if (!isRtl) {
                    view.layout(left + leftFromCrossAxis, top, right + leftFromCrossAxis, bottom);
                    break;
                } else {
                    view.layout(left - leftFromCrossAxis, top, right - leftFromCrossAxis, bottom);
                    break;
                }
        }
    }

    void ensureMeasuredSizeCache(int size) {
        if (this.mMeasuredSizeCache == null) {
            this.mMeasuredSizeCache = new long[Math.max(size, 10)];
        } else if (this.mMeasuredSizeCache.length < size) {
            int newCapacity = this.mMeasuredSizeCache.length * 2;
            this.mMeasuredSizeCache = Arrays.copyOf(this.mMeasuredSizeCache, Math.max(newCapacity, size));
        }
    }

    void ensureMeasureSpecCache(int size) {
        if (this.mMeasureSpecCache == null) {
            this.mMeasureSpecCache = new long[Math.max(size, 10)];
        } else if (this.mMeasureSpecCache.length < size) {
            int newCapacity = this.mMeasureSpecCache.length * 2;
            this.mMeasureSpecCache = Arrays.copyOf(this.mMeasureSpecCache, Math.max(newCapacity, size));
        }
    }

    int extractLowerInt(long longValue) {
        return (int) longValue;
    }

    int extractHigherInt(long longValue) {
        return (int) (longValue >> 32);
    }

    long makeCombinedLong(int widthMeasureSpec, int heightMeasureSpec) {
        return (heightMeasureSpec << 32) | (widthMeasureSpec & MEASURE_SPEC_WIDTH_MASK);
    }

    private void updateMeasureCache(int index, int widthMeasureSpec, int heightMeasureSpec, View view) {
        if (this.mMeasureSpecCache != null) {
            this.mMeasureSpecCache[index] = makeCombinedLong(widthMeasureSpec, heightMeasureSpec);
        }
        if (this.mMeasuredSizeCache != null) {
            this.mMeasuredSizeCache[index] = makeCombinedLong(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    void ensureIndexToFlexLine(int size) {
        if (this.mIndexToFlexLine == null) {
            this.mIndexToFlexLine = new int[Math.max(size, 10)];
        } else if (this.mIndexToFlexLine.length < size) {
            int newCapacity = this.mIndexToFlexLine.length * 2;
            this.mIndexToFlexLine = Arrays.copyOf(this.mIndexToFlexLine, Math.max(newCapacity, size));
        }
    }

    void clearFlexLines(List<FlexLine> flexLines, int fromFlexItem) {
        if (this.mIndexToFlexLine == null) {
            throw new AssertionError();
        }
        if (this.mMeasureSpecCache == null) {
            throw new AssertionError();
        }
        int fromFlexLine = this.mIndexToFlexLine[fromFlexItem];
        if (fromFlexLine == -1) {
            fromFlexLine = 0;
        }
        if (flexLines.size() > fromFlexLine) {
            flexLines.subList(fromFlexLine, flexLines.size()).clear();
        }
        int fillTo = this.mIndexToFlexLine.length - 1;
        if (fromFlexItem > fillTo) {
            Arrays.fill(this.mIndexToFlexLine, -1);
        } else {
            Arrays.fill(this.mIndexToFlexLine, fromFlexItem, fillTo, -1);
        }
        int fillTo2 = this.mMeasureSpecCache.length - 1;
        if (fromFlexItem > fillTo2) {
            Arrays.fill(this.mMeasureSpecCache, 0L);
        } else {
            Arrays.fill(this.mMeasureSpecCache, fromFlexItem, fillTo2, 0L);
        }
    }

    private static class Order implements Comparable<Order> {
        int index;
        int order;

        private Order() {
        }

        @Override // java.lang.Comparable
        public int compareTo(Order another) {
            if (this.order != another.order) {
                return this.order - another.order;
            }
            return this.index - another.index;
        }

        public String toString() {
            return "Order{order=" + this.order + ", index=" + this.index + '}';
        }
    }

    static class FlexLinesResult {
        int mChildState;
        List<FlexLine> mFlexLines;

        FlexLinesResult() {
        }

        void reset() {
            this.mFlexLines = null;
            this.mChildState = 0;
        }
    }
}
