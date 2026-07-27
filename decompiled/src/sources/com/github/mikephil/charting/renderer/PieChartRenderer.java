package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes16.dex */
public class PieChartRenderer extends DataRenderer {
    protected Canvas mBitmapCanvas;
    private RectF mCenterTextLastBounds;
    private CharSequence mCenterTextLastValue;
    private StaticLayout mCenterTextLayout;
    private TextPaint mCenterTextPaint;
    protected PieChart mChart;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mDrawCenterTextPathBuffer;
    protected RectF mDrawHighlightedRectF;
    private Paint mEntryLabelsPaint;
    private Path mHoleCirclePath;
    protected Paint mHolePaint;
    private RectF mInnerRectBuffer;
    private Path mPathBuffer;
    private RectF[] mRectBuffer;
    protected Paint mTransparentCirclePaint;
    protected Paint mValueLinePaint;

    public PieChartRenderer(PieChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mCenterTextLastBounds = new RectF();
        this.mRectBuffer = new RectF[]{new RectF(), new RectF(), new RectF()};
        this.mPathBuffer = new Path();
        this.mInnerRectBuffer = new RectF();
        this.mHoleCirclePath = new Path();
        this.mDrawCenterTextPathBuffer = new Path();
        this.mDrawHighlightedRectF = new RectF();
        this.mChart = chart;
        this.mHolePaint = new Paint(1);
        this.mHolePaint.setColor(-1);
        this.mHolePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint = new Paint(1);
        this.mTransparentCirclePaint.setColor(-1);
        this.mTransparentCirclePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint.setAlpha(105);
        this.mCenterTextPaint = new TextPaint(1);
        this.mCenterTextPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValuePaint.setColor(-1);
        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint = new Paint(1);
        this.mEntryLabelsPaint.setColor(-1);
        this.mEntryLabelsPaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValueLinePaint = new Paint(1);
        this.mValueLinePaint.setStyle(Paint.Style.STROKE);
    }

    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    public Paint getPaintEntryLabels() {
        return this.mEntryLabelsPaint;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas c) {
        int width = (int) this.mViewPortHandler.getChartWidth();
        int height = (int) this.mViewPortHandler.getChartHeight();
        Bitmap drawBitmap = this.mDrawBitmap == null ? null : this.mDrawBitmap.get();
        if (drawBitmap == null || drawBitmap.getWidth() != width || drawBitmap.getHeight() != height) {
            if (width > 0 && height > 0) {
                drawBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
                this.mDrawBitmap = new WeakReference<>(drawBitmap);
                this.mBitmapCanvas = new Canvas(drawBitmap);
            } else {
                return;
            }
        }
        drawBitmap.eraseColor(0);
        PieData pieData = (PieData) this.mChart.getData();
        for (IPieDataSet set : pieData.getDataSets()) {
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set);
            }
        }
    }

    protected float calculateMinimumRadiusForSpacedSlice(MPPointF center, float radius, float angle, float arcStartPointX, float arcStartPointY, float startAngle, float sweepAngle) {
        float angleMiddle = startAngle + (sweepAngle / 2.0f);
        float arcEndPointX = center.x + (((float) Math.cos((startAngle + sweepAngle) * 0.017453292f)) * radius);
        float arcEndPointY = center.y + (((float) Math.sin((startAngle + sweepAngle) * 0.017453292f)) * radius);
        float arcMidPointX = center.x + (((float) Math.cos(angleMiddle * 0.017453292f)) * radius);
        float arcMidPointY = center.y + (((float) Math.sin(0.017453292f * angleMiddle)) * radius);
        double basePointsDistance = Math.sqrt(Math.pow(arcEndPointX - arcStartPointX, 2.0d) + Math.pow(arcEndPointY - arcStartPointY, 2.0d));
        float containedTriangleHeight = (float) ((basePointsDistance / 2.0d) * Math.tan(((180.0d - angle) / 2.0d) * 0.017453292519943295d));
        float spacedRadius = radius - containedTriangleHeight;
        return (float) (spacedRadius - Math.sqrt(Math.pow(arcMidPointX - ((arcEndPointX + arcStartPointX) / 2.0f), 2.0d) + Math.pow(arcMidPointY - ((arcEndPointY + arcStartPointY) / 2.0f), 2.0d)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected float getSliceSpace(IPieDataSet dataSet) {
        if (!dataSet.isAutomaticallyDisableSliceSpacingEnabled()) {
            return dataSet.getSliceSpace();
        }
        float spaceSizeRatio = dataSet.getSliceSpace() / this.mViewPortHandler.getSmallestContentExtension();
        float minValueRatio = (dataSet.getYMin() / ((PieData) this.mChart.getData()).getYValueSum()) * 2.0f;
        if (spaceSizeRatio > minValueRatio) {
            return 0.0f;
        }
        float sliceSpace = dataSet.getSliceSpace();
        return sliceSpace;
    }

    protected void drawDataSet(Canvas c, IPieDataSet dataSet) {
        float sweepAngleOuter;
        int j;
        int visibleAngleCount;
        int entryCount;
        float[] drawAngles;
        float phaseX;
        float radius;
        float rotationAngle;
        int visibleAngleCount2;
        float startAngleOuter;
        int i;
        RectF roundedCircleBox;
        RectF circleBox;
        float arcStartPointX;
        float arcStartPointX2;
        MPPointF center;
        RectF roundedCircleBox2;
        MPPointF center2;
        int i2;
        int visibleAngleCount3;
        MPPointF center3;
        PieChartRenderer pieChartRenderer = this;
        IPieDataSet iPieDataSet = dataSet;
        float rotationAngle2 = pieChartRenderer.mChart.getRotationAngle();
        float phaseX2 = pieChartRenderer.mAnimator.getPhaseX();
        float phaseY = pieChartRenderer.mAnimator.getPhaseY();
        RectF circleBox2 = pieChartRenderer.mChart.getCircleBox();
        int entryCount2 = dataSet.getEntryCount();
        float[] drawAngles2 = pieChartRenderer.mChart.getDrawAngles();
        MPPointF center4 = pieChartRenderer.mChart.getCenterCircleBox();
        float radius2 = pieChartRenderer.mChart.getRadius();
        int i3 = 1;
        boolean drawInnerArc = pieChartRenderer.mChart.isDrawHoleEnabled() && !pieChartRenderer.mChart.isDrawSlicesUnderHoleEnabled();
        float userInnerRadius = drawInnerArc ? (pieChartRenderer.mChart.getHoleRadius() / 100.0f) * radius2 : 0.0f;
        float roundedRadius = (radius2 - ((pieChartRenderer.mChart.getHoleRadius() * radius2) / 100.0f)) / 2.0f;
        RectF roundedCircleBox3 = new RectF();
        boolean drawRoundedSlices = drawInnerArc && pieChartRenderer.mChart.isDrawRoundedSlicesEnabled();
        int visibleAngleCount4 = 0;
        for (int j2 = 0; j2 < entryCount2; j2++) {
            if (Math.abs(iPieDataSet.getEntryForIndex(j2).getY()) > Utils.FLOAT_EPSILON) {
                visibleAngleCount4++;
            }
        }
        float sliceSpace = visibleAngleCount4 <= 1 ? 0.0f : pieChartRenderer.getSliceSpace(iPieDataSet);
        float angle = 0.0f;
        int j3 = 0;
        while (j3 < entryCount2) {
            float sliceAngle = drawAngles2[j3];
            float innerRadius = userInnerRadius;
            Entry e = iPieDataSet.getEntryForIndex(j3);
            if (Math.abs(e.getY()) <= Utils.FLOAT_EPSILON) {
                angle += sliceAngle * phaseX2;
                j = j3;
                visibleAngleCount2 = visibleAngleCount4;
                i = i3;
                radius = radius2;
                rotationAngle = rotationAngle2;
                phaseX = phaseX2;
                circleBox = circleBox2;
                entryCount = entryCount2;
                drawAngles = drawAngles2;
                roundedCircleBox = roundedCircleBox3;
                center = center4;
            } else if (pieChartRenderer.mChart.needsHighlight(j3) && !drawRoundedSlices) {
                angle += sliceAngle * phaseX2;
                j = j3;
                visibleAngleCount2 = visibleAngleCount4;
                i = i3;
                radius = radius2;
                rotationAngle = rotationAngle2;
                phaseX = phaseX2;
                circleBox = circleBox2;
                entryCount = entryCount2;
                drawAngles = drawAngles2;
                roundedCircleBox = roundedCircleBox3;
                center = center4;
            } else {
                boolean accountForSliceSpacing = sliceSpace > 0.0f && sliceAngle <= 180.0f;
                pieChartRenderer.mRenderPaint.setColor(iPieDataSet.getColor(j3));
                float sliceSpaceAngleOuter = visibleAngleCount4 == 1 ? 0.0f : sliceSpace / (radius2 * 0.017453292f);
                float startAngleOuter2 = ((angle + (sliceSpaceAngleOuter / 2.0f)) * phaseY) + rotationAngle2;
                float sweepAngleOuter2 = (sliceAngle - sliceSpaceAngleOuter) * phaseY;
                if (sweepAngleOuter2 >= 0.0f) {
                    sweepAngleOuter = sweepAngleOuter2;
                } else {
                    sweepAngleOuter = 0.0f;
                }
                pieChartRenderer.mPathBuffer.reset();
                if (drawRoundedSlices) {
                    j = j3;
                    visibleAngleCount = visibleAngleCount4;
                    float x = center4.x + ((radius2 - roundedRadius) * ((float) Math.cos(startAngleOuter2 * 0.017453292f)));
                    entryCount = entryCount2;
                    drawAngles = drawAngles2;
                    float y = center4.y + ((radius2 - roundedRadius) * ((float) Math.sin(startAngleOuter2 * 0.017453292f)));
                    roundedCircleBox3.set(x - roundedRadius, y - roundedRadius, x + roundedRadius, y + roundedRadius);
                } else {
                    j = j3;
                    visibleAngleCount = visibleAngleCount4;
                    entryCount = entryCount2;
                    drawAngles = drawAngles2;
                }
                float arcStartPointX3 = center4.x + (((float) Math.cos(startAngleOuter2 * 0.017453292f)) * radius2);
                float rotationAngle3 = rotationAngle2;
                phaseX = phaseX2;
                float arcStartPointY = center4.y + (((float) Math.sin(startAngleOuter2 * 0.017453292f)) * radius2);
                if (sweepAngleOuter >= 360.0f && sweepAngleOuter % 360.0f <= Utils.FLOAT_EPSILON) {
                    pieChartRenderer.mPathBuffer.addCircle(center4.x, center4.y, radius2, Path.Direction.CW);
                } else {
                    if (drawRoundedSlices) {
                        pieChartRenderer.mPathBuffer.arcTo(roundedCircleBox3, startAngleOuter2 + 180.0f, -180.0f);
                    }
                    pieChartRenderer.mPathBuffer.arcTo(circleBox2, startAngleOuter2, sweepAngleOuter);
                }
                pieChartRenderer.mInnerRectBuffer.set(center4.x - innerRadius, center4.y - innerRadius, center4.x + innerRadius, center4.y + innerRadius);
                if (!drawInnerArc) {
                    radius = radius2;
                    rotationAngle = rotationAngle3;
                    visibleAngleCount2 = visibleAngleCount;
                    startAngleOuter = startAngleOuter2;
                    i = 1;
                    roundedCircleBox = roundedCircleBox3;
                    circleBox = circleBox2;
                    arcStartPointX = arcStartPointX3;
                    arcStartPointX2 = innerRadius;
                } else if (innerRadius > 0.0f || accountForSliceSpacing) {
                    if (!accountForSliceSpacing) {
                        roundedCircleBox2 = roundedCircleBox3;
                        radius = radius2;
                        center2 = center4;
                        i2 = 1;
                        circleBox = circleBox2;
                        visibleAngleCount3 = visibleAngleCount;
                    } else {
                        circleBox = circleBox2;
                        visibleAngleCount3 = visibleAngleCount;
                        roundedCircleBox2 = roundedCircleBox3;
                        i2 = 1;
                        radius = radius2;
                        center2 = center4;
                        float minSpacedRadius = calculateMinimumRadiusForSpacedSlice(center4, radius2, sliceAngle * phaseY, arcStartPointX3, arcStartPointY, startAngleOuter2, sweepAngleOuter);
                        if (minSpacedRadius < 0.0f) {
                            minSpacedRadius = -minSpacedRadius;
                        }
                        innerRadius = Math.max(innerRadius, minSpacedRadius);
                    }
                    float sliceSpaceAngleInner = (visibleAngleCount3 == i2 || innerRadius == 0.0f) ? 0.0f : sliceSpace / (innerRadius * 0.017453292f);
                    float startAngleInner = ((angle + (sliceSpaceAngleInner / 2.0f)) * phaseY) + rotationAngle3;
                    float sweepAngleInner = (sliceAngle - sliceSpaceAngleInner) * phaseY;
                    if (sweepAngleInner < 0.0f) {
                        sweepAngleInner = 0.0f;
                    }
                    float endAngleInner = startAngleInner + sweepAngleInner;
                    if (sweepAngleOuter >= 360.0f && sweepAngleOuter % 360.0f <= Utils.FLOAT_EPSILON) {
                        i = i2;
                        pieChartRenderer = this;
                        center3 = center2;
                        pieChartRenderer.mPathBuffer.addCircle(center3.x, center3.y, innerRadius, Path.Direction.CCW);
                        visibleAngleCount2 = visibleAngleCount3;
                        rotationAngle = rotationAngle3;
                        roundedCircleBox = roundedCircleBox2;
                    } else {
                        i = i2;
                        center3 = center2;
                        pieChartRenderer = this;
                        if (drawRoundedSlices) {
                            visibleAngleCount2 = visibleAngleCount3;
                            float x2 = center3.x + ((radius - roundedRadius) * ((float) Math.cos(endAngleInner * 0.017453292f)));
                            float y2 = center3.y + ((radius - roundedRadius) * ((float) Math.sin(endAngleInner * 0.017453292f)));
                            rotationAngle = rotationAngle3;
                            roundedCircleBox = roundedCircleBox2;
                            roundedCircleBox.set(x2 - roundedRadius, y2 - roundedRadius, x2 + roundedRadius, y2 + roundedRadius);
                            pieChartRenderer.mPathBuffer.arcTo(roundedCircleBox, endAngleInner, 180.0f);
                        } else {
                            visibleAngleCount2 = visibleAngleCount3;
                            rotationAngle = rotationAngle3;
                            roundedCircleBox = roundedCircleBox2;
                            pieChartRenderer.mPathBuffer.lineTo(center3.x + (((float) Math.cos(endAngleInner * 0.017453292f)) * innerRadius), center3.y + (((float) Math.sin(endAngleInner * 0.017453292f)) * innerRadius));
                        }
                        pieChartRenderer.mPathBuffer.arcTo(pieChartRenderer.mInnerRectBuffer, endAngleInner, -sweepAngleInner);
                    }
                    center = center3;
                    pieChartRenderer.mPathBuffer.close();
                    pieChartRenderer.mBitmapCanvas.drawPath(pieChartRenderer.mPathBuffer, pieChartRenderer.mRenderPaint);
                    angle += sliceAngle * phaseX;
                } else {
                    radius = radius2;
                    rotationAngle = rotationAngle3;
                    visibleAngleCount2 = visibleAngleCount;
                    startAngleOuter = startAngleOuter2;
                    i = 1;
                    roundedCircleBox = roundedCircleBox3;
                    circleBox = circleBox2;
                    arcStartPointX = arcStartPointX3;
                    arcStartPointX2 = innerRadius;
                }
                if (sweepAngleOuter % 360.0f <= Utils.FLOAT_EPSILON) {
                    center = center4;
                } else if (accountForSliceSpacing) {
                    float angleMiddle = startAngleOuter + (sweepAngleOuter / 2.0f);
                    center = center4;
                    float sliceSpaceOffset = calculateMinimumRadiusForSpacedSlice(center4, radius, sliceAngle * phaseY, arcStartPointX, arcStartPointY, startAngleOuter, sweepAngleOuter);
                    float arcEndPointX = center.x + (((float) Math.cos(angleMiddle * 0.017453292f)) * sliceSpaceOffset);
                    float arcEndPointY = center.y + (((float) Math.sin(angleMiddle * 0.017453292f)) * sliceSpaceOffset);
                    pieChartRenderer.mPathBuffer.lineTo(arcEndPointX, arcEndPointY);
                } else {
                    center = center4;
                    pieChartRenderer.mPathBuffer.lineTo(center.x, center.y);
                }
                pieChartRenderer.mPathBuffer.close();
                pieChartRenderer.mBitmapCanvas.drawPath(pieChartRenderer.mPathBuffer, pieChartRenderer.mRenderPaint);
                angle += sliceAngle * phaseX;
            }
            j3 = j + 1;
            iPieDataSet = dataSet;
            center4 = center;
            roundedCircleBox3 = roundedCircleBox;
            radius2 = radius;
            i3 = i;
            entryCount2 = entryCount;
            drawAngles2 = drawAngles;
            phaseX2 = phaseX;
            circleBox2 = circleBox;
            visibleAngleCount4 = visibleAngleCount2;
            rotationAngle2 = rotationAngle;
        }
        MPPointF.recycleInstance(center4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        float rotationAngle;
        float labelRadiusOffset;
        int i;
        List<IPieDataSet> dataSets;
        float labelRadiusOffset2;
        float radius;
        float[] drawAngles;
        float[] absoluteAngles;
        float phaseX;
        float phaseY;
        float rotationAngle2;
        Canvas canvas;
        float angle;
        float line1Radius;
        PieDataSet.ValuePosition yValuePosition;
        PieDataSet.ValuePosition xValuePosition;
        float f;
        float pt2x;
        float pt2y;
        float labelPty;
        float labelPtx;
        float labelPtx2;
        float sliceXBase;
        float radius2;
        IPieDataSet dataSet;
        MPPointF iconsOffset;
        Canvas canvas2;
        float rotationAngle3;
        Canvas canvas3 = c;
        MPPointF center = this.mChart.getCenterCircleBox();
        float radius3 = this.mChart.getRadius();
        float rotationAngle4 = this.mChart.getRotationAngle();
        float[] drawAngles2 = this.mChart.getDrawAngles();
        float[] absoluteAngles2 = this.mChart.getAbsoluteAngles();
        float phaseX2 = this.mAnimator.getPhaseX();
        float phaseY2 = this.mAnimator.getPhaseY();
        float roundedRadius = (radius3 - ((this.mChart.getHoleRadius() * radius3) / 100.0f)) / 2.0f;
        float holeRadiusPercent = this.mChart.getHoleRadius() / 100.0f;
        float labelRadiusOffset3 = (radius3 / 10.0f) * 3.6f;
        if (!this.mChart.isDrawHoleEnabled()) {
            rotationAngle = rotationAngle4;
            labelRadiusOffset = labelRadiusOffset3;
        } else {
            float labelRadiusOffset4 = (radius3 - (radius3 * holeRadiusPercent)) / 2.0f;
            if (!this.mChart.isDrawSlicesUnderHoleEnabled() && this.mChart.isDrawRoundedSlicesEnabled()) {
                rotationAngle = (float) (rotationAngle4 + ((360.0f * roundedRadius) / (radius3 * 6.283185307179586d)));
                labelRadiusOffset = labelRadiusOffset4;
            } else {
                rotationAngle = rotationAngle4;
                labelRadiusOffset = labelRadiusOffset4;
            }
        }
        float labelRadius = radius3 - labelRadiusOffset;
        PieData data = (PieData) this.mChart.getData();
        List<IPieDataSet> dataSets2 = data.getDataSets();
        float yValueSum = data.getYValueSum();
        boolean drawEntryLabels = this.mChart.isDrawEntryLabelsEnabled();
        int xIndex = 0;
        c.save();
        float offset = Utils.convertDpToPixel(5.0f);
        int i2 = 0;
        while (i2 < dataSets2.size()) {
            IPieDataSet dataSet2 = dataSets2.get(i2);
            boolean drawValues = dataSet2.isDrawValuesEnabled();
            if (!drawValues && !drawEntryLabels) {
                i = i2;
                dataSets = dataSets2;
                radius = radius3;
                drawAngles = drawAngles2;
                absoluteAngles = absoluteAngles2;
                phaseX = phaseX2;
                phaseY = phaseY2;
                rotationAngle2 = rotationAngle;
                labelRadiusOffset2 = labelRadiusOffset;
                canvas = canvas3;
            } else {
                PieDataSet.ValuePosition xValuePosition2 = dataSet2.getXValuePosition();
                PieDataSet.ValuePosition yValuePosition2 = dataSet2.getYValuePosition();
                applyValueTextStyle(dataSet2);
                int xIndex2 = xIndex;
                i = i2;
                float lineHeight = Utils.calcTextHeight(this.mValuePaint, "Q") + Utils.convertDpToPixel(4.0f);
                ValueFormatter formatter = dataSet2.getValueFormatter();
                int entryCount = dataSet2.getEntryCount();
                dataSets = dataSets2;
                labelRadiusOffset2 = labelRadiusOffset;
                this.mValueLinePaint.setColor(dataSet2.getValueLineColor());
                this.mValueLinePaint.setStrokeWidth(Utils.convertDpToPixel(dataSet2.getValueLineWidth()));
                float sliceSpace = getSliceSpace(dataSet2);
                MPPointF iconsOffset2 = MPPointF.getInstance(dataSet2.getIconsOffset());
                iconsOffset2.x = Utils.convertDpToPixel(iconsOffset2.x);
                iconsOffset2.y = Utils.convertDpToPixel(iconsOffset2.y);
                int j = 0;
                while (j < entryCount) {
                    MPPointF iconsOffset3 = iconsOffset2;
                    PieEntry entry = dataSet2.getEntryForIndex(j);
                    if (xIndex2 == 0) {
                        angle = 0.0f;
                    } else {
                        angle = absoluteAngles2[xIndex2 - 1] * phaseX2;
                    }
                    float sliceAngle = drawAngles2[xIndex2];
                    float sliceSpaceMiddleAngle = sliceSpace / (labelRadius * 0.017453292f);
                    float angleOffset = (sliceAngle - (sliceSpaceMiddleAngle / 2.0f)) / 2.0f;
                    float[] drawAngles3 = drawAngles2;
                    float transformedAngle = rotationAngle + ((angle + angleOffset) * phaseY2);
                    int entryCount2 = entryCount;
                    float value = this.mChart.isUsePercentValuesEnabled() ? (entry.getY() / yValueSum) * 100.0f : entry.getY();
                    String formattedValue = formatter.getPieLabel(value, entry);
                    float[] absoluteAngles3 = absoluteAngles2;
                    String entryLabel = entry.getLabel();
                    float value2 = transformedAngle * 0.017453292f;
                    ValueFormatter formatter2 = formatter;
                    float sliceXBase2 = (float) Math.cos(value2);
                    float phaseX3 = phaseX2;
                    float phaseY3 = phaseY2;
                    float sliceYBase = (float) Math.sin(transformedAngle * 0.017453292f);
                    boolean drawXOutside = drawEntryLabels && xValuePosition2 == PieDataSet.ValuePosition.OUTSIDE_SLICE;
                    boolean drawYOutside = drawValues && yValuePosition2 == PieDataSet.ValuePosition.OUTSIDE_SLICE;
                    boolean drawXInside = drawEntryLabels && xValuePosition2 == PieDataSet.ValuePosition.INSIDE_SLICE;
                    boolean drawYInside = drawValues && yValuePosition2 == PieDataSet.ValuePosition.INSIDE_SLICE;
                    if (drawXOutside || drawYOutside) {
                        float valueLineLength1 = dataSet2.getValueLinePart1Length();
                        float valueLineLength2 = dataSet2.getValueLinePart2Length();
                        float valueLinePart1OffsetPercentage = dataSet2.getValueLinePart1OffsetPercentage() / 100.0f;
                        if (this.mChart.isDrawHoleEnabled()) {
                            line1Radius = ((radius3 - (radius3 * holeRadiusPercent)) * valueLinePart1OffsetPercentage) + (radius3 * holeRadiusPercent);
                        } else {
                            float line1Radius2 = radius3 * valueLinePart1OffsetPercentage;
                            line1Radius = line1Radius2;
                        }
                        if (dataSet2.isValueLineVariableLength()) {
                            yValuePosition = yValuePosition2;
                            xValuePosition = xValuePosition2;
                            f = labelRadius * valueLineLength2 * ((float) Math.abs(Math.sin(transformedAngle * 0.017453292f)));
                        } else {
                            yValuePosition = yValuePosition2;
                            xValuePosition = xValuePosition2;
                            f = labelRadius * valueLineLength2;
                        }
                        float polyline2Width = f;
                        float pt0x = (line1Radius * sliceXBase2) + center.x;
                        float pt0y = (line1Radius * sliceYBase) + center.y;
                        float pt1x = ((valueLineLength1 + 1.0f) * labelRadius * sliceXBase2) + center.x;
                        float pt1y = ((valueLineLength1 + 1.0f) * labelRadius * sliceYBase) + center.y;
                        if (transformedAngle % 360.0d >= 90.0d && transformedAngle % 360.0d <= 270.0d) {
                            float pt2x2 = pt1x - polyline2Width;
                            this.mValuePaint.setTextAlign(Paint.Align.RIGHT);
                            if (drawXOutside) {
                                this.mEntryLabelsPaint.setTextAlign(Paint.Align.RIGHT);
                            }
                            float labelPtx3 = pt2x2 - offset;
                            pt2x = pt2x2;
                            pt2y = pt1y;
                            labelPty = pt1y;
                            labelPtx = labelPtx3;
                        } else {
                            float pt2x3 = pt1x + polyline2Width;
                            this.mValuePaint.setTextAlign(Paint.Align.LEFT);
                            if (drawXOutside) {
                                this.mEntryLabelsPaint.setTextAlign(Paint.Align.LEFT);
                            }
                            float labelPtx4 = pt2x3 + offset;
                            pt2x = pt2x3;
                            pt2y = pt1y;
                            labelPty = pt1y;
                            labelPtx = labelPtx4;
                        }
                        if (dataSet2.getValueLineColor() == 1122867) {
                            labelPtx2 = labelPtx;
                            sliceXBase = sliceXBase2;
                            radius2 = radius3;
                            dataSet = dataSet2;
                            iconsOffset = iconsOffset3;
                        } else {
                            if (dataSet2.isUsingSliceColorAsValueLineColor()) {
                                this.mValueLinePaint.setColor(dataSet2.getColor(j));
                            }
                            radius2 = radius3;
                            dataSet = dataSet2;
                            labelPtx2 = labelPtx;
                            sliceXBase = sliceXBase2;
                            iconsOffset = iconsOffset3;
                            c.drawLine(pt0x, pt0y, pt1x, pt1y, this.mValueLinePaint);
                            c.drawLine(pt1x, pt1y, pt2x, pt2y, this.mValueLinePaint);
                        }
                        if (drawXOutside && drawYOutside) {
                            drawValue(c, formattedValue, labelPtx2, labelPty, dataSet.getValueTextColor(j));
                            if (j < data.getEntryCount() && entryLabel != null) {
                                drawEntryLabel(c, entryLabel, labelPtx2, labelPty + lineHeight);
                                canvas2 = c;
                            } else {
                                canvas2 = c;
                            }
                        } else {
                            float labelPtx5 = labelPtx2;
                            if (drawXOutside) {
                                if (j < data.getEntryCount() && entryLabel != null) {
                                    drawEntryLabel(c, entryLabel, labelPtx5, labelPty + (lineHeight / 2.0f));
                                    canvas2 = c;
                                } else {
                                    canvas2 = c;
                                }
                            } else if (drawYOutside) {
                                canvas2 = c;
                                drawValue(c, formattedValue, labelPtx5, labelPty + (lineHeight / 2.0f), dataSet.getValueTextColor(j));
                            } else {
                                canvas2 = c;
                            }
                        }
                    } else {
                        yValuePosition = yValuePosition2;
                        xValuePosition = xValuePosition2;
                        sliceXBase = sliceXBase2;
                        radius2 = radius3;
                        canvas2 = c;
                        dataSet = dataSet2;
                        iconsOffset = iconsOffset3;
                    }
                    if (drawXInside || drawYInside) {
                        float x = (labelRadius * sliceXBase) + center.x;
                        float y = (labelRadius * sliceYBase) + center.y;
                        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
                        if (!drawXInside || !drawYInside) {
                            rotationAngle3 = rotationAngle;
                            if (drawXInside) {
                                if (j < data.getEntryCount() && entryLabel != null) {
                                    drawEntryLabel(canvas2, entryLabel, x, y + (lineHeight / 2.0f));
                                }
                            } else if (drawYInside) {
                                drawValue(c, formattedValue, x, y + (lineHeight / 2.0f), dataSet.getValueTextColor(j));
                            }
                        } else {
                            rotationAngle3 = rotationAngle;
                            drawValue(c, formattedValue, x, y, dataSet.getValueTextColor(j));
                            if (j < data.getEntryCount() && entryLabel != null) {
                                drawEntryLabel(canvas2, entryLabel, x, y + lineHeight);
                            }
                        }
                    } else {
                        rotationAngle3 = rotationAngle;
                    }
                    if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                        Drawable icon = entry.getIcon();
                        Utils.drawImage(c, icon, (int) (((labelRadius + iconsOffset.y) * sliceXBase) + center.x), (int) (((labelRadius + iconsOffset.y) * sliceYBase) + center.y + iconsOffset.x), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    xIndex2++;
                    j++;
                    dataSet2 = dataSet;
                    iconsOffset2 = iconsOffset;
                    xValuePosition2 = xValuePosition;
                    entryCount = entryCount2;
                    drawAngles2 = drawAngles3;
                    absoluteAngles2 = absoluteAngles3;
                    formatter = formatter2;
                    phaseX2 = phaseX3;
                    phaseY2 = phaseY3;
                    rotationAngle = rotationAngle3;
                    yValuePosition2 = yValuePosition;
                    radius3 = radius2;
                }
                radius = radius3;
                drawAngles = drawAngles2;
                absoluteAngles = absoluteAngles2;
                phaseX = phaseX2;
                phaseY = phaseY2;
                rotationAngle2 = rotationAngle;
                canvas = c;
                MPPointF.recycleInstance(iconsOffset2);
                xIndex = xIndex2;
            }
            i2 = i + 1;
            canvas3 = canvas;
            dataSets2 = dataSets;
            labelRadiusOffset = labelRadiusOffset2;
            drawAngles2 = drawAngles;
            absoluteAngles2 = absoluteAngles;
            phaseX2 = phaseX;
            phaseY2 = phaseY;
            rotationAngle = rotationAngle2;
            radius3 = radius;
        }
        MPPointF.recycleInstance(center);
        c.restore();
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValue(Canvas c, String valueText, float x, float y, int color) {
        this.mValuePaint.setColor(color);
        c.drawText(valueText, x, y, this.mValuePaint);
    }

    protected void drawEntryLabel(Canvas c, String label, float x, float y) {
        c.drawText(label, x, y, this.mEntryLabelsPaint);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas c) {
        drawHole(c);
        c.drawBitmap(this.mDrawBitmap.get(), 0.0f, 0.0f, (Paint) null);
        drawCenterText(c);
    }

    protected void drawHole(Canvas c) {
        if (this.mChart.isDrawHoleEnabled() && this.mBitmapCanvas != null) {
            float radius = this.mChart.getRadius();
            float holeRadius = (this.mChart.getHoleRadius() / 100.0f) * radius;
            MPPointF center = this.mChart.getCenterCircleBox();
            if (Color.alpha(this.mHolePaint.getColor()) > 0) {
                this.mBitmapCanvas.drawCircle(center.x, center.y, holeRadius, this.mHolePaint);
            }
            if (Color.alpha(this.mTransparentCirclePaint.getColor()) > 0 && this.mChart.getTransparentCircleRadius() > this.mChart.getHoleRadius()) {
                int alpha = this.mTransparentCirclePaint.getAlpha();
                float secondHoleRadius = (this.mChart.getTransparentCircleRadius() / 100.0f) * radius;
                this.mTransparentCirclePaint.setAlpha((int) (alpha * this.mAnimator.getPhaseX() * this.mAnimator.getPhaseY()));
                this.mHoleCirclePath.reset();
                this.mHoleCirclePath.addCircle(center.x, center.y, secondHoleRadius, Path.Direction.CW);
                this.mHoleCirclePath.addCircle(center.x, center.y, holeRadius, Path.Direction.CCW);
                this.mBitmapCanvas.drawPath(this.mHoleCirclePath, this.mTransparentCirclePaint);
                this.mTransparentCirclePaint.setAlpha(alpha);
            }
            MPPointF.recycleInstance(center);
        }
    }

    protected void drawCenterText(Canvas c) {
        float radius;
        RectF boundingRect;
        RectF holeRect;
        CharSequence centerText = this.mChart.getCenterText();
        if (this.mChart.isDrawCenterTextEnabled() && centerText != null) {
            MPPointF center = this.mChart.getCenterCircleBox();
            MPPointF offset = this.mChart.getCenterTextOffset();
            float x = center.x + offset.x;
            float y = center.y + offset.y;
            if (this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled()) {
                radius = this.mChart.getRadius() * (this.mChart.getHoleRadius() / 100.0f);
            } else {
                radius = this.mChart.getRadius();
            }
            float innerRadius = radius;
            RectF holeRect2 = this.mRectBuffer[0];
            holeRect2.left = x - innerRadius;
            holeRect2.top = y - innerRadius;
            holeRect2.right = x + innerRadius;
            holeRect2.bottom = y + innerRadius;
            RectF boundingRect2 = this.mRectBuffer[1];
            boundingRect2.set(holeRect2);
            float radiusPercent = this.mChart.getCenterTextRadiusPercent() / 100.0f;
            if (radiusPercent > Utils.DOUBLE_EPSILON) {
                boundingRect2.inset((boundingRect2.width() - (boundingRect2.width() * radiusPercent)) / 2.0f, (boundingRect2.height() - (boundingRect2.height() * radiusPercent)) / 2.0f);
            }
            if (centerText.equals(this.mCenterTextLastValue) && boundingRect2.equals(this.mCenterTextLastBounds)) {
                boundingRect = boundingRect2;
                holeRect = holeRect2;
            } else {
                this.mCenterTextLastBounds.set(boundingRect2);
                this.mCenterTextLastValue = centerText;
                float width = this.mCenterTextLastBounds.width();
                boundingRect = boundingRect2;
                holeRect = holeRect2;
                this.mCenterTextLayout = new StaticLayout(centerText, 0, centerText.length(), this.mCenterTextPaint, (int) Math.max(Math.ceil(width), 1.0d), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            }
            float layoutHeight = this.mCenterTextLayout.getHeight();
            c.save();
            Path path = this.mDrawCenterTextPathBuffer;
            path.reset();
            path.addOval(holeRect, Path.Direction.CW);
            c.clipPath(path);
            RectF boundingRect3 = boundingRect;
            c.translate(boundingRect3.left, boundingRect3.top + ((boundingRect3.height() - layoutHeight) / 2.0f));
            this.mCenterTextLayout.draw(c);
            c.restore();
            MPPointF.recycleInstance(center);
            MPPointF.recycleInstance(offset);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas c, Highlight[] indices) {
        float f;
        int i;
        RectF highlightedCircleBox;
        boolean z;
        MPPointF center;
        float[] drawAngles;
        boolean drawInnerArc;
        float angle;
        float sweepAngleOuter;
        float innerRadius;
        float[] drawAngles2;
        int visibleAngleCount;
        float innerRadius2;
        float innerRadius3;
        float sliceSpaceAngleInner;
        Highlight[] highlightArr = indices;
        int i2 = 1;
        boolean drawInnerArc2 = this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled();
        if (drawInnerArc2 && this.mChart.isDrawRoundedSlicesEnabled()) {
            return;
        }
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles3 = this.mChart.getDrawAngles();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        MPPointF center2 = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        boolean z2 = false;
        if (!drawInnerArc2) {
            f = 0.0f;
        } else {
            f = (this.mChart.getHoleRadius() / 100.0f) * radius;
        }
        float userInnerRadius = f;
        RectF highlightedCircleBox2 = this.mDrawHighlightedRectF;
        highlightedCircleBox2.set(0.0f, 0.0f, 0.0f, 0.0f);
        int i3 = 0;
        while (i3 < highlightArr.length) {
            int index = (int) highlightArr[i3].getX();
            if (index >= drawAngles3.length) {
                i = i3;
                highlightedCircleBox = highlightedCircleBox2;
                z = z2;
                center = center2;
                drawAngles = drawAngles3;
                drawInnerArc = drawInnerArc2;
            } else {
                IPieDataSet set = ((PieData) this.mChart.getData()).getDataSetByIndex(highlightArr[i3].getDataSetIndex());
                if (set == null) {
                    i = i3;
                    highlightedCircleBox = highlightedCircleBox2;
                    z = z2;
                    center = center2;
                    drawAngles = drawAngles3;
                    drawInnerArc = drawInnerArc2;
                } else if (!set.isHighlightEnabled()) {
                    i = i3;
                    highlightedCircleBox = highlightedCircleBox2;
                    z = z2;
                    center = center2;
                    drawAngles = drawAngles3;
                    drawInnerArc = drawInnerArc2;
                } else {
                    int entryCount = set.getEntryCount();
                    int visibleAngleCount2 = 0;
                    for (int j = 0; j < entryCount; j++) {
                        if (Math.abs(set.getEntryForIndex(j).getY()) > Utils.FLOAT_EPSILON) {
                            visibleAngleCount2++;
                        }
                    }
                    if (index == 0) {
                        angle = 0.0f;
                    } else {
                        angle = absoluteAngles[index - 1] * phaseX;
                    }
                    float sliceSpace = visibleAngleCount2 <= i2 ? 0.0f : set.getSliceSpace();
                    float sliceAngle = drawAngles3[index];
                    float shift = set.getSelectionShift();
                    float highlightedRadius = radius + shift;
                    highlightedCircleBox2.set(this.mChart.getCircleBox());
                    i = i3;
                    highlightedCircleBox2.inset(-shift, -shift);
                    boolean accountForSliceSpacing = sliceSpace > 0.0f && sliceAngle <= 180.0f;
                    this.mRenderPaint.setColor(set.getColor(index));
                    float sliceSpaceAngleOuter = visibleAngleCount2 == 1 ? 0.0f : sliceSpace / (radius * 0.017453292f);
                    float sliceSpaceAngleShifted = visibleAngleCount2 == 1 ? 0.0f : sliceSpace / (highlightedRadius * 0.017453292f);
                    float startAngleOuter = rotationAngle + ((angle + (sliceSpaceAngleOuter / 2.0f)) * phaseY);
                    float sweepAngleOuter2 = (sliceAngle - sliceSpaceAngleOuter) * phaseY;
                    if (sweepAngleOuter2 >= 0.0f) {
                        sweepAngleOuter = sweepAngleOuter2;
                    } else {
                        sweepAngleOuter = 0.0f;
                    }
                    float startAngleShifted = rotationAngle + ((angle + (sliceSpaceAngleShifted / 2.0f)) * phaseY);
                    float sweepAngleShifted = (sliceAngle - sliceSpaceAngleShifted) * phaseY;
                    z = false;
                    if (sweepAngleShifted < 0.0f) {
                        sweepAngleShifted = 0.0f;
                    }
                    this.mPathBuffer.reset();
                    if (sweepAngleOuter < 360.0f || sweepAngleOuter % 360.0f > Utils.FLOAT_EPSILON) {
                        innerRadius = userInnerRadius;
                        drawAngles2 = drawAngles3;
                        visibleAngleCount = visibleAngleCount2;
                        this.mPathBuffer.moveTo(center2.x + (((float) Math.cos(startAngleShifted * 0.017453292f)) * highlightedRadius), center2.y + (((float) Math.sin(startAngleShifted * 0.017453292f)) * highlightedRadius));
                        this.mPathBuffer.arcTo(highlightedCircleBox2, startAngleShifted, sweepAngleShifted);
                    } else {
                        Path path = this.mPathBuffer;
                        float f2 = center2.x;
                        innerRadius = userInnerRadius;
                        float innerRadius4 = center2.y;
                        drawAngles2 = drawAngles3;
                        path.addCircle(f2, innerRadius4, highlightedRadius, Path.Direction.CW);
                        visibleAngleCount = visibleAngleCount2;
                    }
                    float sliceSpaceRadius = 0.0f;
                    if (accountForSliceSpacing) {
                        float innerRadius5 = center2.x + (((float) Math.cos(startAngleOuter * 0.017453292f)) * radius);
                        highlightedCircleBox = highlightedCircleBox2;
                        innerRadius2 = innerRadius;
                        drawAngles = drawAngles2;
                        center = center2;
                        sliceSpaceRadius = calculateMinimumRadiusForSpacedSlice(center2, radius, sliceAngle * phaseY, innerRadius5, center2.y + (((float) Math.sin(startAngleOuter * 0.017453292f)) * radius), startAngleOuter, sweepAngleOuter);
                    } else {
                        highlightedCircleBox = highlightedCircleBox2;
                        innerRadius2 = innerRadius;
                        drawAngles = drawAngles2;
                        center = center2;
                    }
                    this.mInnerRectBuffer.set(center.x - innerRadius2, center.y - innerRadius2, center.x + innerRadius2, center.y + innerRadius2);
                    if (!drawInnerArc2) {
                        drawInnerArc = drawInnerArc2;
                    } else if (innerRadius2 > 0.0f || accountForSliceSpacing) {
                        if (!accountForSliceSpacing) {
                            innerRadius3 = innerRadius2;
                        } else {
                            float minSpacedRadius = sliceSpaceRadius;
                            if (minSpacedRadius < 0.0f) {
                                minSpacedRadius = -minSpacedRadius;
                            }
                            innerRadius3 = Math.max(innerRadius2, minSpacedRadius);
                        }
                        if (visibleAngleCount != 1 && innerRadius3 != 0.0f) {
                            sliceSpaceAngleInner = sliceSpace / (innerRadius3 * 0.017453292f);
                        } else {
                            sliceSpaceAngleInner = 0.0f;
                        }
                        float startAngleInner = ((angle + (sliceSpaceAngleInner / 2.0f)) * phaseY) + rotationAngle;
                        float sweepAngleInner = (sliceAngle - sliceSpaceAngleInner) * phaseY;
                        if (sweepAngleInner < 0.0f) {
                            sweepAngleInner = 0.0f;
                        }
                        float endAngleInner = startAngleInner + sweepAngleInner;
                        if (sweepAngleOuter >= 360.0f && sweepAngleOuter % 360.0f <= Utils.FLOAT_EPSILON) {
                            this.mPathBuffer.addCircle(center.x, center.y, innerRadius3, Path.Direction.CCW);
                            drawInnerArc = drawInnerArc2;
                        } else {
                            drawInnerArc = drawInnerArc2;
                            this.mPathBuffer.lineTo(center.x + (((float) Math.cos(endAngleInner * 0.017453292f)) * innerRadius3), center.y + (((float) Math.sin(endAngleInner * 0.017453292f)) * innerRadius3));
                            this.mPathBuffer.arcTo(this.mInnerRectBuffer, endAngleInner, -sweepAngleInner);
                        }
                        this.mPathBuffer.close();
                        this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                    } else {
                        drawInnerArc = drawInnerArc2;
                    }
                    if (sweepAngleOuter % 360.0f > Utils.FLOAT_EPSILON) {
                        if (accountForSliceSpacing) {
                            float angleMiddle = startAngleOuter + (sweepAngleOuter / 2.0f);
                            float arcEndPointX = center.x + (((float) Math.cos(angleMiddle * 0.017453292f)) * sliceSpaceRadius);
                            float arcEndPointY = center.y + (((float) Math.sin(angleMiddle * 0.017453292f)) * sliceSpaceRadius);
                            this.mPathBuffer.lineTo(arcEndPointX, arcEndPointY);
                        } else {
                            this.mPathBuffer.lineTo(center.x, center.y);
                        }
                    }
                    this.mPathBuffer.close();
                    this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                }
            }
            i3 = i + 1;
            drawInnerArc2 = drawInnerArc;
            center2 = center;
            z2 = z;
            drawAngles3 = drawAngles;
            highlightedCircleBox2 = highlightedCircleBox;
            i2 = 1;
            highlightArr = indices;
        }
        MPPointF.recycleInstance(center2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawRoundedSlices(Canvas c) {
        float[] drawAngles;
        float angle;
        if (!this.mChart.isDrawRoundedSlicesEnabled()) {
            return;
        }
        IPieDataSet dataSet = ((PieData) this.mChart.getData()).getDataSet();
        if (!dataSet.isVisible()) {
            return;
        }
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        MPPointF center = this.mChart.getCenterCircleBox();
        float r = this.mChart.getRadius();
        float circleRadius = (r - ((this.mChart.getHoleRadius() * r) / 100.0f)) / 2.0f;
        float[] drawAngles2 = this.mChart.getDrawAngles();
        float angle2 = this.mChart.getRotationAngle();
        int j = 0;
        while (j < dataSet.getEntryCount()) {
            float sliceAngle = drawAngles2[j];
            Entry e = dataSet.getEntryForIndex(j);
            if (Math.abs(e.getY()) <= Utils.FLOAT_EPSILON) {
                drawAngles = drawAngles2;
                angle = angle2;
            } else {
                float x = (float) (((r - circleRadius) * Math.cos(Math.toRadians((angle2 + sliceAngle) * phaseY))) + center.x);
                drawAngles = drawAngles2;
                angle = angle2;
                float y = (float) (((r - circleRadius) * Math.sin(Math.toRadians((angle2 + sliceAngle) * phaseY))) + center.y);
                this.mRenderPaint.setColor(dataSet.getColor(j));
                this.mBitmapCanvas.drawCircle(x, y, circleRadius, this.mRenderPaint);
            }
            angle2 = angle + (sliceAngle * phaseX);
            j++;
            drawAngles2 = drawAngles;
        }
        MPPointF.recycleInstance(center);
    }

    public void releaseBitmap() {
        if (this.mBitmapCanvas != null) {
            this.mBitmapCanvas.setBitmap(null);
            this.mBitmapCanvas = null;
        }
        if (this.mDrawBitmap != null) {
            Bitmap drawBitmap = this.mDrawBitmap.get();
            if (drawBitmap != null) {
                drawBitmap.recycle();
            }
            this.mDrawBitmap.clear();
            this.mDrawBitmap = null;
        }
    }
}
