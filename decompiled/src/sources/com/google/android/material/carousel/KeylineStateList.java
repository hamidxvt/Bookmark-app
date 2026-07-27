package com.google.android.material.carousel;

import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.CarouselStrategy;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes16.dex */
public class KeylineStateList {
    private static final int NO_INDEX = -1;
    private final KeylineState defaultState;
    private final float endShiftRange;
    private final List<KeylineState> endStateSteps;
    private final float[] endStateStepsInterpolationPoints;
    private final float startShiftRange;
    private final List<KeylineState> startStateSteps;
    private final float[] startStateStepsInterpolationPoints;

    private KeylineStateList(KeylineState defaultState, List<KeylineState> startStateSteps, List<KeylineState> endStateSteps) {
        this.defaultState = defaultState;
        this.startStateSteps = Collections.unmodifiableList(startStateSteps);
        this.endStateSteps = Collections.unmodifiableList(endStateSteps);
        this.startShiftRange = startStateSteps.get(startStateSteps.size() - 1).getFirstKeyline().loc - defaultState.getFirstKeyline().loc;
        this.endShiftRange = defaultState.getLastKeyline().loc - endStateSteps.get(endStateSteps.size() - 1).getLastKeyline().loc;
        this.startStateStepsInterpolationPoints = getStateStepInterpolationPoints(this.startShiftRange, startStateSteps, true);
        this.endStateStepsInterpolationPoints = getStateStepInterpolationPoints(this.endShiftRange, endStateSteps, false);
    }

    static KeylineStateList from(Carousel carousel, KeylineState state, float itemMargins, float leftOrTopPadding, float rightOrBottomPadding, CarouselStrategy.StrategyType strategyType) {
        return new KeylineStateList(state, getStateStepsStart(carousel, state, itemMargins, leftOrTopPadding, strategyType), getStateStepsEnd(carousel, state, itemMargins, rightOrBottomPadding, strategyType));
    }

    KeylineState getDefaultState() {
        return this.defaultState;
    }

    KeylineState getStartState() {
        return this.startStateSteps.get(this.startStateSteps.size() - 1);
    }

    KeylineState getEndState() {
        return this.endStateSteps.get(this.endStateSteps.size() - 1);
    }

    public KeylineState getShiftedState(float scrollOffset, float minScrollOffset, float maxScrollOffset) {
        return getShiftedState(scrollOffset, minScrollOffset, maxScrollOffset, false);
    }

    KeylineState getShiftedState(float scrollOffset, float minScrollOffset, float maxScrollOffset, boolean roundToNearestStep) {
        float interpolation;
        List<KeylineState> steps;
        float[] interpolationPoints;
        float startShiftOffset = this.startShiftRange + minScrollOffset;
        float endShiftOffset = maxScrollOffset - this.endShiftRange;
        float startPaddingShift = getStartState().getFirstFocalKeyline().leftOrTopPaddingShift;
        float endPaddingShift = getEndState().getFirstFocalKeyline().rightOrBottomPaddingShift;
        if (this.startShiftRange == startPaddingShift) {
            startShiftOffset += startPaddingShift;
        }
        if (this.endShiftRange == endPaddingShift) {
            endShiftOffset -= endPaddingShift;
        }
        if (scrollOffset < startShiftOffset) {
            interpolation = AnimationUtils.lerp(1.0f, 0.0f, minScrollOffset, startShiftOffset, scrollOffset);
            steps = this.startStateSteps;
            interpolationPoints = this.startStateStepsInterpolationPoints;
        } else if (scrollOffset > endShiftOffset) {
            interpolation = AnimationUtils.lerp(0.0f, 1.0f, endShiftOffset, maxScrollOffset, scrollOffset);
            steps = this.endStateSteps;
            interpolationPoints = this.endStateStepsInterpolationPoints;
        } else {
            return this.defaultState;
        }
        if (roundToNearestStep) {
            return closestStateStepFromInterpolation(steps, interpolation, interpolationPoints);
        }
        return lerp(steps, interpolation, interpolationPoints);
    }

    private static KeylineState lerp(List<KeylineState> stateSteps, float interpolation, float[] stateStepsInterpolationPoints) {
        float[] stateStepsRange = getStateStepsRange(stateSteps, interpolation, stateStepsInterpolationPoints);
        return KeylineState.lerp(stateSteps.get((int) stateStepsRange[1]), stateSteps.get((int) stateStepsRange[2]), stateStepsRange[0]);
    }

    private static float[] getStateStepsRange(List<KeylineState> stateSteps, float interpolation, float[] stateStepsInterpolationPoints) {
        int numberOfSteps = stateSteps.size();
        float lowerBounds = stateStepsInterpolationPoints[0];
        for (int i = 1; i < numberOfSteps; i++) {
            float upperBounds = stateStepsInterpolationPoints[i];
            if (interpolation <= upperBounds) {
                int fromIndex = i - 1;
                int toIndex = i;
                float steppedProgress = AnimationUtils.lerp(0.0f, 1.0f, lowerBounds, upperBounds, interpolation);
                return new float[]{steppedProgress, fromIndex, toIndex};
            }
            lowerBounds = upperBounds;
        }
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    private KeylineState closestStateStepFromInterpolation(List<KeylineState> stateSteps, float interpolation, float[] stateStepsInterpolationPoints) {
        float[] stateStepsRange = getStateStepsRange(stateSteps, interpolation, stateStepsInterpolationPoints);
        if (stateStepsRange[0] >= 0.5f) {
            return stateSteps.get((int) stateStepsRange[2]);
        }
        return stateSteps.get((int) stateStepsRange[1]);
    }

    private static float[] getStateStepInterpolationPoints(float shiftRange, List<KeylineState> stateSteps, boolean isShiftingLeft) {
        float distanceShifted;
        int numberOfSteps = stateSteps.size();
        float[] stateStepsInterpolationPoints = new float[numberOfSteps];
        int i = 1;
        while (i < numberOfSteps) {
            KeylineState prevState = stateSteps.get(i - 1);
            KeylineState currState = stateSteps.get(i);
            if (isShiftingLeft) {
                distanceShifted = currState.getFirstKeyline().loc - prevState.getFirstKeyline().loc;
            } else {
                distanceShifted = prevState.getLastKeyline().loc - currState.getLastKeyline().loc;
            }
            float stepProgress = distanceShifted / shiftRange;
            stateStepsInterpolationPoints[i] = i == numberOfSteps + (-1) ? 1.0f : stateStepsInterpolationPoints[i - 1] + stepProgress;
            i++;
        }
        return stateStepsInterpolationPoints;
    }

    private static boolean isFirstFocalItemAtLeftOfContainer(KeylineState state) {
        float firstFocalItemLeft = state.getFirstFocalKeyline().locOffset - (state.getFirstFocalKeyline().maskedItemSize / 2.0f);
        return firstFocalItemLeft >= 0.0f && state.getFirstFocalKeyline() == state.getFirstNonAnchorKeyline();
    }

    private static boolean isLastFocalItemVisibleAtRightOfContainer(Carousel carousel, KeylineState state) {
        int containerSize = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerSize = carousel.getContainerWidth();
        }
        float lastFocalItemRight = state.getLastFocalKeyline().locOffset + (state.getLastFocalKeyline().maskedItemSize / 2.0f);
        return lastFocalItemRight <= ((float) containerSize) && state.getLastFocalKeyline() == state.getLastNonAnchorKeyline();
    }

    private static KeylineState shiftKeylineStateForPadding(KeylineState keylineState, float padding, int carouselSize, boolean leftShift, float childMargins, CarouselStrategy.StrategyType strategyType) {
        switch (strategyType) {
            case CONTAINED:
                return shiftKeylineStateForPaddingContained(keylineState, padding, carouselSize, leftShift, childMargins);
            default:
                return shiftKeylineStateForPaddingUncontained(keylineState, padding, carouselSize, leftShift);
        }
    }

    private static KeylineState shiftKeylineStateForPaddingUncontained(KeylineState keylineState, float padding, int carouselSize, boolean leftShift) {
        List<KeylineState.Keyline> tmpKeylines = new ArrayList<>(keylineState.getKeylines());
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.getItemSize(), carouselSize);
        int unchangingAnchorPosition = leftShift ? 0 : tmpKeylines.size() - 1;
        int j = 0;
        while (j < tmpKeylines.size()) {
            KeylineState.Keyline k = tmpKeylines.get(j);
            if (k.isAnchor && j == unchangingAnchorPosition) {
                builder.addKeyline(k.locOffset, k.mask, k.maskedItemSize, false, true, k.cutoff);
            } else {
                float f = k.locOffset;
                float newOffset = leftShift ? f + padding : f - padding;
                float leftOrTopPadding = leftShift ? padding : 0.0f;
                float rightOrBottomPadding = leftShift ? 0.0f : padding;
                boolean isFocal = j >= keylineState.getFirstFocalKeylineIndex() && j <= keylineState.getLastFocalKeylineIndex();
                builder.addKeyline(newOffset, k.mask, k.maskedItemSize, isFocal, k.isAnchor, Math.abs(leftShift ? Math.max(0.0f, ((k.maskedItemSize / 2.0f) + newOffset) - carouselSize) : Math.min(0.0f, newOffset - (k.maskedItemSize / 2.0f))), leftOrTopPadding, rightOrBottomPadding);
            }
            j++;
        }
        return builder.build();
    }

    private static KeylineState shiftKeylineStateForPaddingContained(KeylineState keylineState, float padding, int carouselSize, boolean leftShift, float childMargins) {
        List<KeylineState.Keyline> tmpKeylines = new ArrayList<>(keylineState.getKeylines());
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.getItemSize(), carouselSize);
        int numOfNonAnchorKeylines = keylineState.getNumberOfNonAnchorKeylines();
        float toDecreaseBy = padding / numOfNonAnchorKeylines;
        float nextOffset = 0.0f;
        if (leftShift) {
            nextOffset = padding;
        }
        float nextOffset2 = nextOffset;
        int j = 0;
        while (j < tmpKeylines.size()) {
            KeylineState.Keyline k = tmpKeylines.get(j);
            if (k.isAnchor) {
                builder.addKeyline(k.locOffset, k.mask, k.maskedItemSize, false, true, k.cutoff);
            } else {
                boolean isFocal = j >= keylineState.getFirstFocalKeylineIndex() && j <= keylineState.getLastFocalKeylineIndex();
                float maskedItemSize = k.maskedItemSize - toDecreaseBy;
                float mask = CarouselStrategy.getChildMaskPercentage(maskedItemSize, keylineState.getItemSize(), childMargins);
                float locOffset = (maskedItemSize / 2.0f) + nextOffset2;
                float actualPaddingShift = Math.abs(locOffset - k.locOffset);
                builder.addKeyline(locOffset, mask, maskedItemSize, isFocal, false, k.cutoff, leftShift ? actualPaddingShift : 0.0f, leftShift ? 0.0f : actualPaddingShift);
                nextOffset2 += maskedItemSize;
            }
            j++;
        }
        return builder.build();
    }

    private static List<KeylineState> getStateStepsStart(Carousel carousel, KeylineState defaultState, float itemMargins, float leftOrTopPaddingForKeylineShift, CarouselStrategy.StrategyType strategyType) {
        int carouselSize;
        int dstIndex;
        int i;
        int numberOfSteps;
        int carouselSize2;
        List<KeylineState> steps = new ArrayList<>();
        steps.add(defaultState);
        int firstNonAnchorKeylineIndex = findFirstNonAnchorKeylineIndex(defaultState);
        int carouselSize3 = carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight();
        if (isFirstFocalItemAtLeftOfContainer(defaultState)) {
            carouselSize = carouselSize3;
        } else {
            if (firstNonAnchorKeylineIndex != -1) {
                int end = defaultState.getFirstFocalKeylineIndex();
                int numberOfSteps2 = end - firstNonAnchorKeylineIndex;
                float originalStart = defaultState.getFirstKeyline().locOffset - (defaultState.getFirstKeyline().maskedItemSize / 2.0f);
                if (numberOfSteps2 <= 0 && defaultState.getFirstFocalKeyline().cutoff > 0.0f) {
                    float cutoffs = defaultState.getFirstFocalKeyline().cutoff;
                    steps.add(shiftKeylinesAndCreateKeylineState(defaultState, originalStart + cutoffs + leftOrTopPaddingForKeylineShift, carouselSize3));
                    return steps;
                }
                float cutoffs2 = 0.0f;
                int i2 = 0;
                while (i2 < numberOfSteps2) {
                    KeylineState prevStepState = steps.get(steps.size() - 1);
                    int itemOrigIndex = firstNonAnchorKeylineIndex + i2;
                    int dstIndex2 = defaultState.getKeylines().size() - 1;
                    float cutoffs3 = cutoffs2 + defaultState.getKeylines().get(itemOrigIndex).cutoff;
                    if (itemOrigIndex - 1 < 0) {
                        dstIndex = dstIndex2;
                    } else {
                        float originalAdjacentMaskLeft = defaultState.getKeylines().get(itemOrigIndex - 1).mask;
                        int dstIndex3 = findFirstIndexAfterLastFocalKeylineWithMask(prevStepState, originalAdjacentMaskLeft) - 1;
                        dstIndex = dstIndex3;
                    }
                    int newFirstFocalIndex = (defaultState.getFirstFocalKeylineIndex() - i2) - 1;
                    int newLastFocalIndex = (defaultState.getLastFocalKeylineIndex() - i2) - 1;
                    KeylineState shifted = moveKeylineAndCreateKeylineState(prevStepState, firstNonAnchorKeylineIndex, dstIndex, originalStart + cutoffs3, newFirstFocalIndex, newLastFocalIndex, carouselSize3);
                    if (i2 != numberOfSteps2 - 1 || leftOrTopPaddingForKeylineShift <= 0.0f) {
                        i = i2;
                        numberOfSteps = numberOfSteps2;
                        carouselSize2 = carouselSize3;
                    } else {
                        i = i2;
                        numberOfSteps = numberOfSteps2;
                        carouselSize2 = carouselSize3;
                        shifted = shiftKeylineStateForPadding(shifted, leftOrTopPaddingForKeylineShift, carouselSize3, true, itemMargins, strategyType);
                    }
                    steps.add(shifted);
                    i2 = i + 1;
                    numberOfSteps2 = numberOfSteps;
                    carouselSize3 = carouselSize2;
                    cutoffs2 = cutoffs3;
                }
                return steps;
            }
            carouselSize = carouselSize3;
        }
        if (leftOrTopPaddingForKeylineShift > 0.0f) {
            steps.add(shiftKeylineStateForPadding(defaultState, leftOrTopPaddingForKeylineShift, carouselSize, true, itemMargins, strategyType));
        }
        return steps;
    }

    private static List<KeylineState> getStateStepsEnd(Carousel carousel, KeylineState defaultState, float itemMargins, float rightOrBottomPaddingForKeylineShift, CarouselStrategy.StrategyType strategyType) {
        int carouselSize;
        int dstIndex;
        int i;
        int numberOfSteps;
        int carouselSize2;
        List<KeylineState> steps = new ArrayList<>();
        steps.add(defaultState);
        int lastNonAnchorKeylineIndex = findLastNonAnchorKeylineIndex(defaultState);
        int carouselSize3 = carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight();
        if (isLastFocalItemVisibleAtRightOfContainer(carousel, defaultState)) {
            carouselSize = carouselSize3;
        } else {
            if (lastNonAnchorKeylineIndex != -1) {
                int start = defaultState.getLastFocalKeylineIndex();
                int numberOfSteps2 = lastNonAnchorKeylineIndex - start;
                float originalStart = defaultState.getFirstKeyline().locOffset - (defaultState.getFirstKeyline().maskedItemSize / 2.0f);
                if (numberOfSteps2 <= 0 && defaultState.getLastFocalKeyline().cutoff > 0.0f) {
                    float cutoffs = defaultState.getLastFocalKeyline().cutoff;
                    steps.add(shiftKeylinesAndCreateKeylineState(defaultState, (originalStart - cutoffs) - rightOrBottomPaddingForKeylineShift, carouselSize3));
                    return steps;
                }
                float cutoffs2 = 0.0f;
                int i2 = 0;
                while (i2 < numberOfSteps2) {
                    KeylineState prevStepState = steps.get(steps.size() - 1);
                    int itemOrigIndex = lastNonAnchorKeylineIndex - i2;
                    float cutoffs3 = cutoffs2 + defaultState.getKeylines().get(itemOrigIndex).cutoff;
                    if (itemOrigIndex + 1 >= defaultState.getKeylines().size()) {
                        dstIndex = 0;
                    } else {
                        float originalAdjacentMaskRight = defaultState.getKeylines().get(itemOrigIndex + 1).mask;
                        int dstIndex2 = findLastIndexBeforeFirstFocalKeylineWithMask(prevStepState, originalAdjacentMaskRight) + 1;
                        dstIndex = dstIndex2;
                    }
                    int dstIndex3 = defaultState.getFirstFocalKeylineIndex();
                    int newFirstFocalIndex = dstIndex3 + i2 + 1;
                    int newLastFocalIndex = defaultState.getLastFocalKeylineIndex() + i2 + 1;
                    KeylineState shifted = moveKeylineAndCreateKeylineState(prevStepState, lastNonAnchorKeylineIndex, dstIndex, originalStart - cutoffs3, newFirstFocalIndex, newLastFocalIndex, carouselSize3);
                    if (i2 != numberOfSteps2 - 1 || rightOrBottomPaddingForKeylineShift <= 0.0f) {
                        i = i2;
                        numberOfSteps = numberOfSteps2;
                        carouselSize2 = carouselSize3;
                    } else {
                        i = i2;
                        numberOfSteps = numberOfSteps2;
                        carouselSize2 = carouselSize3;
                        shifted = shiftKeylineStateForPadding(shifted, rightOrBottomPaddingForKeylineShift, carouselSize3, false, itemMargins, strategyType);
                    }
                    steps.add(shifted);
                    i2 = i + 1;
                    numberOfSteps2 = numberOfSteps;
                    carouselSize3 = carouselSize2;
                    cutoffs2 = cutoffs3;
                }
                return steps;
            }
            carouselSize = carouselSize3;
        }
        if (rightOrBottomPaddingForKeylineShift > 0.0f) {
            steps.add(shiftKeylineStateForPadding(defaultState, rightOrBottomPaddingForKeylineShift, carouselSize, false, itemMargins, strategyType));
        }
        return steps;
    }

    private static KeylineState shiftKeylinesAndCreateKeylineState(KeylineState state, float startOffset, int carouselSize) {
        return moveKeylineAndCreateKeylineState(state, 0, 0, startOffset, state.getFirstFocalKeylineIndex(), state.getLastFocalKeylineIndex(), carouselSize);
    }

    private static KeylineState moveKeylineAndCreateKeylineState(KeylineState state, int keylineSrcIndex, int keylineDstIndex, float startOffset, int newFirstFocalIndex, int newLastFocalIndex, int carouselSize) {
        boolean z;
        List<KeylineState.Keyline> tmpKeylines = new ArrayList<>(state.getKeylines());
        KeylineState.Keyline item = tmpKeylines.remove(keylineSrcIndex);
        tmpKeylines.add(keylineDstIndex, item);
        KeylineState.Builder builder = new KeylineState.Builder(state.getItemSize(), carouselSize);
        float startOffset2 = startOffset;
        int j = 0;
        while (j < tmpKeylines.size()) {
            KeylineState.Keyline k = tmpKeylines.get(j);
            float offset = startOffset2 + (k.maskedItemSize / 2.0f);
            if (j >= newFirstFocalIndex && j <= newLastFocalIndex) {
                z = true;
                boolean isFocal = z;
                builder.addKeyline(offset, k.mask, k.maskedItemSize, isFocal, k.isAnchor, k.cutoff);
                startOffset2 += k.maskedItemSize;
                j++;
                tmpKeylines = tmpKeylines;
            }
            z = false;
            boolean isFocal2 = z;
            builder.addKeyline(offset, k.mask, k.maskedItemSize, isFocal2, k.isAnchor, k.cutoff);
            startOffset2 += k.maskedItemSize;
            j++;
            tmpKeylines = tmpKeylines;
        }
        return builder.build();
    }

    private static int findFirstIndexAfterLastFocalKeylineWithMask(KeylineState state, float mask) {
        int focalEndIndex = state.getLastFocalKeylineIndex();
        for (int i = focalEndIndex; i < state.getKeylines().size(); i++) {
            if (mask == state.getKeylines().get(i).mask) {
                return i;
            }
        }
        return state.getKeylines().size() - 1;
    }

    private static int findLastIndexBeforeFirstFocalKeylineWithMask(KeylineState state, float mask) {
        int focalStartIndex = state.getFirstFocalKeylineIndex() - 1;
        for (int i = focalStartIndex; i >= 0; i--) {
            if (mask == state.getKeylines().get(i).mask) {
                return i;
            }
        }
        return 0;
    }

    private static int findFirstNonAnchorKeylineIndex(KeylineState state) {
        for (int i = 0; i < state.getKeylines().size(); i++) {
            if (!state.getKeylines().get(i).isAnchor) {
                return i;
            }
        }
        return -1;
    }

    private static int findLastNonAnchorKeylineIndex(KeylineState state) {
        for (int i = state.getKeylines().size() - 1; i >= 0; i--) {
            if (!state.getKeylines().get(i).isAnchor) {
                return i;
            }
        }
        return -1;
    }

    Map<Integer, KeylineState> getKeylineStateForPositionMap(int itemCount, int minHorizontalScroll, int maxHorizontalScroll, boolean isRTL) {
        float itemSize = this.defaultState.getItemSize();
        Map<Integer, KeylineState> keylineStates = new HashMap<>();
        int endStepsIndex = 0;
        int startStepsIndex = 0;
        for (int i = 0; i < itemCount; i++) {
            int position = isRTL ? (itemCount - i) - 1 : i;
            float itemPosition = position * itemSize * (isRTL ? -1 : 1);
            if (itemPosition > maxHorizontalScroll - this.endShiftRange || i >= itemCount - this.endStateSteps.size()) {
                keylineStates.put(Integer.valueOf(position), this.endStateSteps.get(MathUtils.clamp(endStepsIndex, 0, this.endStateSteps.size() - 1)));
                endStepsIndex++;
            }
        }
        for (int i2 = itemCount - 1; i2 >= 0; i2--) {
            int position2 = isRTL ? (itemCount - i2) - 1 : i2;
            float itemPosition2 = position2 * itemSize * (isRTL ? -1 : 1);
            if (itemPosition2 < minHorizontalScroll + this.startShiftRange || i2 < this.startStateSteps.size()) {
                keylineStates.put(Integer.valueOf(position2), this.startStateSteps.get(MathUtils.clamp(startStepsIndex, 0, this.startStateSteps.size() - 1)));
                startStepsIndex++;
            }
        }
        return keylineStates;
    }
}
