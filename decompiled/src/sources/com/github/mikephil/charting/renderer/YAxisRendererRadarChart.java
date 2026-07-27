package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes16.dex */
public class YAxisRendererRadarChart extends YAxisRenderer {
    private RadarChart mChart;
    private Path mRenderLimitLinesPathBuffer;

    public YAxisRendererRadarChart(ViewPortHandler viewPortHandler, YAxis yAxis, RadarChart chart) {
        super(viewPortHandler, yAxis, null);
        this.mRenderLimitLinesPathBuffer = new Path();
        this.mChart = chart;
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    protected void computeAxisValues(float f, float f2) {
        int i;
        double nextUp;
        int i2;
        float f3 = f;
        float f4 = f2;
        int labelCount = this.mAxis.getLabelCount();
        double abs = Math.abs(f4 - f3);
        if (labelCount != 0 && abs > Utils.DOUBLE_EPSILON) {
            if (!Double.isInfinite(abs)) {
                double roundToNextSignificant = Utils.roundToNextSignificant(abs / labelCount);
                if (this.mAxis.isGranularityEnabled()) {
                    roundToNextSignificant = roundToNextSignificant < ((double) this.mAxis.getGranularity()) ? this.mAxis.getGranularity() : roundToNextSignificant;
                }
                double roundToNextSignificant2 = Utils.roundToNextSignificant(Math.pow(10.0d, (int) Math.log10(roundToNextSignificant)));
                int i3 = (int) (roundToNextSignificant / roundToNextSignificant2);
                if (i3 > 5) {
                    roundToNextSignificant = Math.floor(10.0d * roundToNextSignificant2);
                }
                boolean isCenterAxisLabelsEnabled = this.mAxis.isCenterAxisLabelsEnabled();
                if (this.mAxis.isForceLabelsEnabled()) {
                    float f5 = ((float) abs) / (labelCount - 1);
                    this.mAxis.mEntryCount = labelCount;
                    if (this.mAxis.mEntries.length < labelCount) {
                        this.mAxis.mEntries = new float[labelCount];
                    }
                    float f6 = f;
                    int i4 = 0;
                    while (i4 < labelCount) {
                        this.mAxis.mEntries[i4] = f6;
                        f6 += f5;
                        i4++;
                        i3 = i3;
                    }
                    i2 = labelCount;
                } else {
                    double ceil = roundToNextSignificant == Utils.DOUBLE_EPSILON ? Utils.DOUBLE_EPSILON : Math.ceil(f3 / roundToNextSignificant) * roundToNextSignificant;
                    if (isCenterAxisLabelsEnabled) {
                        ceil -= roundToNextSignificant;
                    }
                    if (roundToNextSignificant == Utils.DOUBLE_EPSILON) {
                        i = isCenterAxisLabelsEnabled ? 1 : 0;
                        nextUp = Utils.DOUBLE_EPSILON;
                    } else {
                        i = isCenterAxisLabelsEnabled ? 1 : 0;
                        nextUp = Utils.nextUp(Math.floor(f4 / roundToNextSignificant) * roundToNextSignificant);
                    }
                    if (roundToNextSignificant != Utils.DOUBLE_EPSILON) {
                        for (double d = ceil; d <= nextUp; d += roundToNextSignificant) {
                            i++;
                        }
                    }
                    int i5 = i + 1;
                    this.mAxis.mEntryCount = i5;
                    if (this.mAxis.mEntries.length < i5) {
                        this.mAxis.mEntries = new float[i5];
                    }
                    double d2 = ceil;
                    int i6 = 0;
                    while (i6 < i5) {
                        if (d2 == Utils.DOUBLE_EPSILON) {
                            d2 = Utils.DOUBLE_EPSILON;
                        }
                        float f7 = f3;
                        double d3 = d2;
                        this.mAxis.mEntries[i6] = (float) d3;
                        d2 = d3 + roundToNextSignificant;
                        i6++;
                        labelCount = labelCount;
                        f3 = f7;
                        f4 = f4;
                    }
                    i2 = i5;
                }
                if (roundToNextSignificant < 1.0d) {
                    this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(roundToNextSignificant));
                } else {
                    this.mAxis.mDecimals = 0;
                }
                if (isCenterAxisLabelsEnabled) {
                    if (this.mAxis.mCenteredEntries.length < i2) {
                        this.mAxis.mCenteredEntries = new float[i2];
                    }
                    float f8 = (this.mAxis.mEntries[1] - this.mAxis.mEntries[0]) / 2.0f;
                    for (int i7 = 0; i7 < i2; i7++) {
                        this.mAxis.mCenteredEntries[i7] = this.mAxis.mEntries[i7] + f8;
                    }
                }
                this.mAxis.mAxisMinimum = this.mAxis.mEntries[0];
                this.mAxis.mAxisMaximum = this.mAxis.mEntries[i2 - 1];
                this.mAxis.mAxisRange = Math.abs(this.mAxis.mAxisMaximum - this.mAxis.mAxisMinimum);
                return;
            }
        }
        this.mAxis.mEntries = new float[0];
        this.mAxis.mCenteredEntries = new float[0];
        this.mAxis.mEntryCount = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [int] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [com.github.mikephil.charting.components.YAxis] */
    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        if (!this.mYAxis.isEnabled() || !this.mYAxis.isDrawLabelsEnabled()) {
            return;
        }
        this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        float factor = this.mChart.getFactor();
        boolean z = !this.mYAxis.isDrawBottomYLabelEntryEnabled();
        int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        for (?? r5 = z; r5 < i; r5++) {
            Utils.getPosition(centerOffsets, (this.mYAxis.mEntries[r5] - this.mYAxis.mAxisMinimum) * factor, this.mChart.getRotationAngle(), mPPointF);
            canvas.drawText(this.mYAxis.getFormattedLabel(r5), mPPointF.x + 10.0f, mPPointF.y, this.mAxisLabelPaint);
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(mPPointF);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas c) {
        List<LimitLine> limitLines = this.mYAxis.getLimitLines();
        if (limitLines == null) {
            return;
        }
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        for (int i = 0; i < limitLines.size(); i++) {
            LimitLine l = limitLines.get(i);
            if (l.isEnabled()) {
                this.mLimitLinePaint.setColor(l.getLineColor());
                this.mLimitLinePaint.setPathEffect(l.getDashPathEffect());
                this.mLimitLinePaint.setStrokeWidth(l.getLineWidth());
                float r = (l.getLimit() - this.mChart.getYChartMin()) * factor;
                Path limitPath = this.mRenderLimitLinesPathBuffer;
                limitPath.reset();
                for (int j = 0; j < ((RadarData) this.mChart.getData()).getMaxEntryCountSet().getEntryCount(); j++) {
                    Utils.getPosition(center, r, (j * sliceangle) + this.mChart.getRotationAngle(), pOut);
                    if (j == 0) {
                        limitPath.moveTo(pOut.x, pOut.y);
                    } else {
                        limitPath.lineTo(pOut.x, pOut.y);
                    }
                }
                limitPath.close();
                c.drawPath(limitPath, this.mLimitLinePaint);
            }
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut);
    }
}
