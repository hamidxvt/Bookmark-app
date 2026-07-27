package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.Optimizer;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    private static final boolean DO_NOT_USE = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer mConstraintWidgetContainer;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();
    private Measure mMeasure = new Measure();

    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer layout) {
        this.mVariableDimensionsWidgets.clear();
        int childCount = layout.mChildren.size();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget widget = layout.mChildren.get(i);
            if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                this.mVariableDimensionsWidgets.add(widget);
            }
        }
        layout.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.mConstraintWidgetContainer = constraintWidgetContainer;
    }

    private void measureChildren(ConstraintWidgetContainer layout) {
        int childCount = layout.mChildren.size();
        boolean optimize = layout.optimizeFor(64);
        Measurer measurer = layout.getMeasurer();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget child = layout.mChildren.get(i);
            if (!(child instanceof Guideline) && !(child instanceof Barrier) && !child.isInVirtualLayout() && (!optimize || child.mHorizontalRun == null || child.mVerticalRun == null || !child.mHorizontalRun.mDimension.resolved || !child.mVerticalRun.mDimension.resolved)) {
                boolean skip = false;
                ConstraintWidget.DimensionBehaviour widthBehavior = child.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour heightBehavior = child.getDimensionBehaviour(1);
                if (widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultWidth != 1 && heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultHeight != 1) {
                    skip = true;
                }
                if (!skip && layout.optimizeFor(1) && !(child instanceof VirtualLayout)) {
                    if (widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultWidth == 0 && heightBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !child.isInHorizontalChain()) {
                        skip = true;
                    }
                    if (heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultHeight == 0 && widthBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !child.isInHorizontalChain()) {
                        skip = true;
                    }
                    if ((widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && child.mDimensionRatio > 0.0f) {
                        skip = true;
                    }
                }
                if (!skip) {
                    measure(measurer, child, Measure.SELF_DIMENSIONS);
                    if (layout.mMetrics != null) {
                        layout.mMetrics.measuredWidgets++;
                    }
                }
            }
        }
        measurer.didMeasures();
    }

    private void solveLinearSystem(ConstraintWidgetContainer layout, String reason, int pass, int w, int h) {
        long startLayout = layout.mMetrics != null ? System.nanoTime() : 0L;
        int minWidth = layout.getMinWidth();
        int minHeight = layout.getMinHeight();
        layout.setMinWidth(0);
        layout.setMinHeight(0);
        layout.setWidth(w);
        layout.setHeight(h);
        layout.setMinWidth(minWidth);
        layout.setMinHeight(minHeight);
        this.mConstraintWidgetContainer.setPass(pass);
        this.mConstraintWidgetContainer.layout();
        if (layout.mMetrics != null) {
            long endLayout = System.nanoTime();
            layout.mMetrics.mSolverPasses++;
            layout.mMetrics.measuresLayoutDuration += endLayout - startLayout;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:206:0x0097, code lost:
    
        r2 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long solverMeasure(ConstraintWidgetContainer layout, int optimizationLevel, int paddingX, int paddingY, int widthMode, int widthSize, int heightMode, int heightSize, int lastMeasureWidth, int lastMeasureHeight) {
        long layoutTime;
        boolean z;
        boolean allSolved;
        int heightSize2;
        int sizeDependentWidgetsCount;
        int optimizations;
        boolean z2;
        boolean z3;
        long layoutTime2;
        int sizeDependentWidgetsCount2;
        int measureStrategy;
        int maxIterations;
        Measurer measurer;
        int childCount;
        int startingHeight;
        boolean optimizeWrap;
        boolean allSolved2;
        boolean z4;
        BasicMeasure basicMeasure = this;
        Measurer measurer2 = layout.getMeasurer();
        long layoutTime3 = 0;
        int childCount2 = layout.mChildren.size();
        int startingWidth = layout.getWidth();
        int startingHeight2 = layout.getHeight();
        boolean optimizeWrap2 = Optimizer.enabled(optimizationLevel, 128);
        boolean optimize = optimizeWrap2 || Optimizer.enabled(optimizationLevel, 64);
        if (!optimize) {
            layoutTime = 0;
        } else {
            int i = 0;
            while (true) {
                if (i >= childCount2) {
                    layoutTime = layoutTime3;
                    break;
                }
                ConstraintWidget child = layout.mChildren.get(i);
                layoutTime = layoutTime3;
                boolean matchWidth = child.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean matchHeight = child.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean ratio = matchWidth && matchHeight && child.getDimensionRatio() > 0.0f;
                if (child.isInHorizontalChain() && ratio) {
                    optimize = false;
                    break;
                }
                if (child.isInVerticalChain() && ratio) {
                    optimize = false;
                    break;
                }
                boolean matchWidth2 = child instanceof VirtualLayout;
                if (matchWidth2) {
                    optimize = false;
                    break;
                }
                if (child.isInHorizontalChain() || child.isInVerticalChain()) {
                    break;
                }
                i++;
                layoutTime3 = layoutTime;
            }
        }
        if (optimize && LinearSystem.sMetrics != null) {
            LinearSystem.sMetrics.measures++;
        }
        boolean optimize2 = optimize & ((widthMode == 1073741824 && heightMode == 1073741824) || optimizeWrap2);
        int computations = 0;
        if (!optimize2) {
            z = false;
            allSolved = false;
            heightSize2 = 0;
        } else {
            int widthSize2 = Math.min(layout.getMaxWidth(), widthSize);
            int heightSize3 = Math.min(layout.getMaxHeight(), heightSize);
            if (widthMode == 1073741824 && layout.getWidth() != widthSize2) {
                layout.setWidth(widthSize2);
                layout.invalidateGraph();
            }
            if (heightMode == 1073741824 && layout.getHeight() != heightSize3) {
                layout.setHeight(heightSize3);
                layout.invalidateGraph();
            }
            if (widthMode == 1073741824 && heightMode == 1073741824) {
                allSolved2 = layout.directMeasure(optimizeWrap2);
                computations = 2;
                z4 = true;
            } else {
                allSolved2 = layout.directMeasureSetup(optimizeWrap2);
                if (widthMode == 1073741824) {
                    allSolved2 &= layout.directMeasureWithOrientation(optimizeWrap2, 0);
                    computations = 0 + 1;
                }
                if (heightMode != 1073741824) {
                    z4 = true;
                } else {
                    z4 = true;
                    allSolved2 &= layout.directMeasureWithOrientation(optimizeWrap2, 1);
                    computations++;
                }
            }
            if (allSolved2) {
                if (widthMode != 1073741824) {
                    z4 = false;
                }
                layout.updateFromRuns(z4, heightMode == 1073741824);
            }
            allSolved = allSolved2;
            z = false;
            heightSize2 = computations;
        }
        if (allSolved && heightSize2 == 2) {
            layoutTime2 = layoutTime;
        } else {
            int optimizations2 = layout.getOptimizationLevel();
            if (childCount2 > 0) {
                measureChildren(layout);
            }
            if (layout.mMetrics != null) {
                layoutTime = System.nanoTime();
            }
            updateHierarchy(layout);
            int sizeDependentWidgetsCount3 = basicMeasure.mVariableDimensionsWidgets.size();
            if (childCount2 <= 0) {
                sizeDependentWidgetsCount = sizeDependentWidgetsCount3;
                optimizations = optimizations2;
                z2 = z;
                z3 = true;
            } else {
                sizeDependentWidgetsCount = sizeDependentWidgetsCount3;
                optimizations = optimizations2;
                z3 = true;
                z2 = z;
                solveLinearSystem(layout, "First pass", 0, startingWidth, startingHeight2);
            }
            if (sizeDependentWidgetsCount > 0) {
                boolean needSolverPass = false;
                boolean containerWrapWidth = layout.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? z3 : z2;
                boolean containerWrapHeight = layout.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? z3 : z2;
                int minWidth = Math.max(layout.getWidth(), basicMeasure.mConstraintWidgetContainer.getMinWidth());
                int minHeight = Math.max(layout.getHeight(), basicMeasure.mConstraintWidgetContainer.getMinHeight());
                int i2 = 0;
                while (i2 < sizeDependentWidgetsCount) {
                    ConstraintWidget widget = basicMeasure.mVariableDimensionsWidgets.get(i2);
                    if (!(widget instanceof VirtualLayout)) {
                        childCount = childCount2;
                        startingHeight = startingHeight2;
                        optimizeWrap = optimizeWrap2;
                    } else {
                        int preWidth = widget.getWidth();
                        int preHeight = widget.getHeight();
                        childCount = childCount2;
                        boolean needSolverPass2 = needSolverPass | basicMeasure.measure(measurer2, widget, Measure.TRY_GIVEN_DIMENSIONS);
                        if (layout.mMetrics == null) {
                            startingHeight = startingHeight2;
                            optimizeWrap = optimizeWrap2;
                        } else {
                            startingHeight = startingHeight2;
                            optimizeWrap = optimizeWrap2;
                            layout.mMetrics.measuredMatchWidgets++;
                        }
                        int measuredWidth = widget.getWidth();
                        int measuredHeight = widget.getHeight();
                        if (measuredWidth != preWidth) {
                            widget.setWidth(measuredWidth);
                            if (containerWrapWidth && widget.getRight() > minWidth) {
                                int w = widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                minWidth = Math.max(minWidth, w);
                            }
                            needSolverPass2 = true;
                        }
                        if (measuredHeight != preHeight) {
                            widget.setHeight(measuredHeight);
                            if (containerWrapHeight && widget.getBottom() > minHeight) {
                                int h = widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                minHeight = Math.max(minHeight, h);
                            }
                            needSolverPass2 = true;
                        }
                        VirtualLayout virtualLayout = (VirtualLayout) widget;
                        needSolverPass = needSolverPass2 | virtualLayout.needSolverPass();
                    }
                    i2++;
                    childCount2 = childCount;
                    startingHeight2 = startingHeight;
                    optimizeWrap2 = optimizeWrap;
                }
                int startingHeight3 = startingHeight2;
                int maxIterations2 = 2;
                int j = 0;
                while (j < maxIterations2) {
                    int i3 = 0;
                    boolean needSolverPass3 = needSolverPass;
                    int minWidth2 = minWidth;
                    int minHeight2 = minHeight;
                    while (i3 < sizeDependentWidgetsCount) {
                        ConstraintWidget widget2 = basicMeasure.mVariableDimensionsWidgets.get(i3);
                        if (((widget2 instanceof Helper) && !(widget2 instanceof VirtualLayout)) || (widget2 instanceof Guideline) || widget2.getVisibility() == 8 || ((optimize2 && widget2.mHorizontalRun.mDimension.resolved && widget2.mVerticalRun.mDimension.resolved) || (widget2 instanceof VirtualLayout))) {
                            sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                            maxIterations = maxIterations2;
                            measurer = measurer2;
                        } else {
                            int preWidth2 = widget2.getWidth();
                            int preHeight2 = widget2.getHeight();
                            int preBaselineDistance = widget2.getBaselineDistance();
                            int measureStrategy2 = Measure.TRY_GIVEN_DIMENSIONS;
                            sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                            if (j != maxIterations2 - 1) {
                                measureStrategy = measureStrategy2;
                            } else {
                                int measureStrategy3 = Measure.USE_GIVEN_DIMENSIONS;
                                measureStrategy = measureStrategy3;
                            }
                            boolean hasMeasure = basicMeasure.measure(measurer2, widget2, measureStrategy);
                            needSolverPass3 |= hasMeasure;
                            if (layout.mMetrics == null) {
                                maxIterations = maxIterations2;
                                measurer = measurer2;
                            } else {
                                maxIterations = maxIterations2;
                                measurer = measurer2;
                                layout.mMetrics.measuredMatchWidgets++;
                            }
                            int measuredWidth2 = widget2.getWidth();
                            int measuredHeight2 = widget2.getHeight();
                            if (measuredWidth2 != preWidth2) {
                                widget2.setWidth(measuredWidth2);
                                if (containerWrapWidth && widget2.getRight() > minWidth2) {
                                    int w2 = widget2.getRight() + widget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                    minWidth2 = Math.max(minWidth2, w2);
                                }
                                needSolverPass3 = true;
                            }
                            if (measuredHeight2 != preHeight2) {
                                widget2.setHeight(measuredHeight2);
                                if (containerWrapHeight && widget2.getBottom() > minHeight2) {
                                    int h2 = widget2.getBottom() + widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                    minHeight2 = Math.max(minHeight2, h2);
                                }
                                needSolverPass3 = true;
                            }
                            if (widget2.hasBaseline() && preBaselineDistance != widget2.getBaselineDistance()) {
                                needSolverPass3 = true;
                            }
                        }
                        i3++;
                        basicMeasure = this;
                        sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                        maxIterations2 = maxIterations;
                        measurer2 = measurer;
                    }
                    int sizeDependentWidgetsCount4 = sizeDependentWidgetsCount;
                    int maxIterations3 = maxIterations2;
                    Measurer measurer3 = measurer2;
                    if (!needSolverPass3) {
                        break;
                    }
                    solveLinearSystem(layout, "intermediate pass", j + 1, startingWidth, startingHeight3);
                    needSolverPass = false;
                    j++;
                    sizeDependentWidgetsCount = sizeDependentWidgetsCount4;
                    maxIterations2 = maxIterations3;
                    minHeight = minHeight2;
                    minWidth = minWidth2;
                    measurer2 = measurer3;
                    basicMeasure = this;
                }
            }
            layout.setOptimizationLevel(optimizations);
            layoutTime2 = layoutTime;
        }
        if (layout.mMetrics != null) {
            return System.nanoTime() - layoutTime2;
        }
        return layoutTime2;
    }

    private boolean measure(Measurer measurer, ConstraintWidget widget, int measureStrategy) {
        this.mMeasure.horizontalBehavior = widget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = widget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = widget.getWidth();
        this.mMeasure.verticalDimension = widget.getHeight();
        this.mMeasure.measuredNeedsSolverPass = false;
        this.mMeasure.measureStrategy = measureStrategy;
        boolean horizontalMatchConstraints = this.mMeasure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalMatchConstraints = this.mMeasure.verticalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean horizontalUseRatio = horizontalMatchConstraints && widget.mDimensionRatio > 0.0f;
        boolean verticalUseRatio = verticalMatchConstraints && widget.mDimensionRatio > 0.0f;
        if (horizontalUseRatio && widget.mResolvedMatchConstraintDefault[0] == 4) {
            this.mMeasure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (verticalUseRatio && widget.mResolvedMatchConstraintDefault[1] == 4) {
            this.mMeasure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(widget, this.mMeasure);
        widget.setWidth(this.mMeasure.measuredWidth);
        widget.setHeight(this.mMeasure.measuredHeight);
        widget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        widget.setBaselineDistance(this.mMeasure.measuredBaseline);
        this.mMeasure.measureStrategy = Measure.SELF_DIMENSIONS;
        return this.mMeasure.measuredNeedsSolverPass;
    }
}
