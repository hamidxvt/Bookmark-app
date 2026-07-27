package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes16.dex */
public class RadarChartRenderer extends LineRadarRenderer {
    protected RadarChart mChart;
    protected Path mDrawDataSetSurfacePathBuffer;
    protected Path mDrawHighlightCirclePathBuffer;
    protected Paint mHighlightCirclePaint;
    protected Paint mWebPaint;

    public RadarChartRenderer(RadarChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mDrawDataSetSurfacePathBuffer = new Path();
        this.mDrawHighlightCirclePathBuffer = new Path();
        this.mChart = chart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(2.0f);
        this.mHighlightPaint.setColor(Color.rgb(255, 187, 115));
        this.mWebPaint = new Paint(1);
        this.mWebPaint.setStyle(Paint.Style.STROKE);
        this.mHighlightCirclePaint = new Paint(1);
    }

    public Paint getWebPaint() {
        return this.mWebPaint;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas c) {
        RadarData radarData = (RadarData) this.mChart.getData();
        int mostEntries = radarData.getMaxEntryCountSet().getEntryCount();
        for (IRadarDataSet set : radarData.getDataSets()) {
            if (set.isVisible()) {
                drawDataSet(c, set, mostEntries);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawDataSet(Canvas c, IRadarDataSet dataSet, int mostEntries) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        Path surface = this.mDrawDataSetSurfacePathBuffer;
        surface.reset();
        boolean hasMovedToPoint = false;
        for (int j = 0; j < dataSet.getEntryCount(); j++) {
            this.mRenderPaint.setColor(dataSet.getColor(j));
            RadarEntry e = (RadarEntry) dataSet.getEntryForIndex(j);
            Utils.getPosition(center, (e.getY() - this.mChart.getYChartMin()) * factor * phaseY, (j * sliceangle * phaseX) + this.mChart.getRotationAngle(), pOut);
            if (!Float.isNaN(pOut.x)) {
                if (!hasMovedToPoint) {
                    surface.moveTo(pOut.x, pOut.y);
                    hasMovedToPoint = true;
                } else {
                    surface.lineTo(pOut.x, pOut.y);
                }
            }
        }
        int j2 = dataSet.getEntryCount();
        if (j2 > mostEntries) {
            surface.lineTo(center.x, center.y);
        }
        surface.close();
        if (dataSet.isDrawFilledEnabled()) {
            Drawable drawable = dataSet.getFillDrawable();
            if (drawable == null) {
                drawFilledPath(c, surface, dataSet.getFillColor(), dataSet.getFillAlpha());
            } else {
                drawFilledPath(c, surface, drawable);
            }
        }
        this.mRenderPaint.setStrokeWidth(dataSet.getLineWidth());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        if (!dataSet.isDrawFilledEnabled() || dataSet.getFillAlpha() < 255) {
            c.drawPath(surface, this.mRenderPaint);
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        MPPointF pOut;
        float yoffset;
        int i;
        RadarEntry entry;
        ValueFormatter formatter;
        MPPointF pOut2;
        float yoffset2;
        MPPointF pOut3;
        int j;
        IRadarDataSet dataSet;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut4 = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF pIcon = MPPointF.getInstance(0.0f, 0.0f);
        float yoffset3 = Utils.convertDpToPixel(5.0f);
        int i2 = 0;
        while (i2 < ((RadarData) this.mChart.getData()).getDataSetCount()) {
            IRadarDataSet dataSet2 = ((RadarData) this.mChart.getData()).getDataSetByIndex(i2);
            if (!shouldDrawValues(dataSet2)) {
                pOut = pOut4;
                yoffset = yoffset3;
                i = i2;
            } else {
                applyValueTextStyle(dataSet2);
                ValueFormatter formatter2 = dataSet2.getValueFormatter();
                MPPointF iconsOffset = MPPointF.getInstance(dataSet2.getIconsOffset());
                iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
                iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);
                int j2 = 0;
                while (j2 < dataSet2.getEntryCount()) {
                    RadarEntry entry2 = (RadarEntry) dataSet2.getEntryForIndex(j2);
                    MPPointF iconsOffset2 = iconsOffset;
                    int i3 = i2;
                    Utils.getPosition(center, (entry2.getY() - this.mChart.getYChartMin()) * factor * phaseY, (j2 * sliceangle * phaseX) + this.mChart.getRotationAngle(), pOut4);
                    if (!dataSet2.isDrawValuesEnabled()) {
                        entry = entry2;
                        formatter = formatter2;
                        pOut2 = pOut4;
                        yoffset2 = yoffset3;
                        pOut3 = iconsOffset2;
                        j = j2;
                        dataSet = dataSet2;
                    } else {
                        entry = entry2;
                        yoffset2 = yoffset3;
                        j = j2;
                        pOut2 = pOut4;
                        pOut3 = iconsOffset2;
                        formatter = formatter2;
                        dataSet = dataSet2;
                        drawValue(c, formatter2.getRadarLabel(entry2), pOut4.x, pOut4.y - yoffset3, dataSet2.getValueTextColor(j2));
                    }
                    if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                        Drawable icon = entry.getIcon();
                        Utils.getPosition(center, (entry.getY() * factor * phaseY) + pOut3.y, (j * sliceangle * phaseX) + this.mChart.getRotationAngle(), pIcon);
                        pIcon.y += pOut3.x;
                        Utils.drawImage(c, icon, (int) pIcon.x, (int) pIcon.y, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    j2 = j + 1;
                    iconsOffset = pOut3;
                    formatter2 = formatter;
                    dataSet2 = dataSet;
                    i2 = i3;
                    yoffset3 = yoffset2;
                    pOut4 = pOut2;
                }
                pOut = pOut4;
                yoffset = yoffset3;
                i = i2;
                MPPointF pOut5 = iconsOffset;
                MPPointF.recycleInstance(pOut5);
            }
            i2 = i + 1;
            yoffset3 = yoffset;
            pOut4 = pOut;
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut4);
        MPPointF.recycleInstance(pIcon);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValue(Canvas c, String valueText, float x, float y, int color) {
        this.mValuePaint.setColor(color);
        c.drawText(valueText, x, y, this.mValuePaint);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas c) {
        drawWeb(c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawWeb(Canvas c) {
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        float rotationangle = this.mChart.getRotationAngle();
        MPPointF center = this.mChart.getCenterOffsets();
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidth());
        this.mWebPaint.setColor(this.mChart.getWebColor());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int xIncrements = this.mChart.getSkipWebLineCount() + 1;
        int maxEntryCount = ((RadarData) this.mChart.getData()).getMaxEntryCountSet().getEntryCount();
        MPPointF p = MPPointF.getInstance(0.0f, 0.0f);
        for (int i = 0; i < maxEntryCount; i += xIncrements) {
            Utils.getPosition(center, this.mChart.getYRange() * factor, (i * sliceangle) + rotationangle, p);
            c.drawLine(center.x, center.y, p.x, p.y, this.mWebPaint);
        }
        MPPointF.recycleInstance(p);
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidthInner());
        this.mWebPaint.setColor(this.mChart.getWebColorInner());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int labelCount = this.mChart.getYAxis().mEntryCount;
        MPPointF p1out = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF p2out = MPPointF.getInstance(0.0f, 0.0f);
        for (int j = 0; j < labelCount; j++) {
            int i2 = 0;
            while (i2 < ((RadarData) this.mChart.getData()).getEntryCount()) {
                float r = (this.mChart.getYAxis().mEntries[j] - this.mChart.getYChartMin()) * factor;
                Utils.getPosition(center, r, (i2 * sliceangle) + rotationangle, p1out);
                Utils.getPosition(center, r, ((i2 + 1) * sliceangle) + rotationangle, p2out);
                c.drawLine(p1out.x, p1out.y, p2out.x, p2out.y, this.mWebPaint);
                i2++;
                sliceangle = sliceangle;
                factor = factor;
                rotationangle = rotationangle;
            }
        }
        MPPointF.recycleInstance(p1out);
        MPPointF.recycleInstance(p2out);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas c, Highlight[] indices) {
        int i;
        int i2;
        int strokeColor;
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        RadarData radarData = (RadarData) this.mChart.getData();
        int length = indices.length;
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            Highlight high = indices[i4];
            IRadarDataSet set = radarData.getDataSetByIndex(high.getDataSetIndex());
            if (set == null) {
                i = i4;
                i2 = i3;
            } else if (!set.isHighlightEnabled()) {
                i = i4;
                i2 = i3;
            } else {
                Entry e = (RadarEntry) set.getEntryForIndex((int) high.getX());
                if (isInBoundsX(e, set)) {
                    float y = e.getY() - this.mChart.getYChartMin();
                    Utils.getPosition(center, y * factor * this.mAnimator.getPhaseY(), (high.getX() * sliceangle * this.mAnimator.getPhaseX()) + this.mChart.getRotationAngle(), pOut);
                    high.setDraw(pOut.x, pOut.y);
                    drawHighlightLines(c, pOut.x, pOut.y, set);
                    if (!set.isDrawHighlightCircleEnabled()) {
                        i = i4;
                        i2 = i3;
                    } else if (Float.isNaN(pOut.x) || Float.isNaN(pOut.y)) {
                        i = i4;
                        i2 = i3;
                    } else {
                        int strokeColor2 = set.getHighlightCircleStrokeColor();
                        if (strokeColor2 == 1122867) {
                            strokeColor2 = set.getColor(i3);
                        }
                        if (set.getHighlightCircleStrokeAlpha() >= 255) {
                            strokeColor = strokeColor2;
                        } else {
                            strokeColor = ColorTemplate.colorWithAlpha(strokeColor2, set.getHighlightCircleStrokeAlpha());
                        }
                        i = i4;
                        i2 = 0;
                        drawHighlightCircle(c, pOut, set.getHighlightCircleInnerRadius(), set.getHighlightCircleOuterRadius(), set.getHighlightCircleFillColor(), strokeColor, set.getHighlightCircleStrokeWidth());
                    }
                } else {
                    i = i4;
                    i2 = i3;
                }
            }
            i4 = i + 1;
            i3 = i2;
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut);
    }

    public void drawHighlightCircle(Canvas c, MPPointF point, float innerRadius, float outerRadius, int fillColor, int strokeColor, float strokeWidth) {
        c.save();
        float outerRadius2 = Utils.convertDpToPixel(outerRadius);
        float innerRadius2 = Utils.convertDpToPixel(innerRadius);
        if (fillColor != 1122867) {
            Path p = this.mDrawHighlightCirclePathBuffer;
            p.reset();
            p.addCircle(point.x, point.y, outerRadius2, Path.Direction.CW);
            if (innerRadius2 > 0.0f) {
                p.addCircle(point.x, point.y, innerRadius2, Path.Direction.CCW);
            }
            this.mHighlightCirclePaint.setColor(fillColor);
            this.mHighlightCirclePaint.setStyle(Paint.Style.FILL);
            c.drawPath(p, this.mHighlightCirclePaint);
        }
        if (strokeColor != 1122867) {
            this.mHighlightCirclePaint.setColor(strokeColor);
            this.mHighlightCirclePaint.setStyle(Paint.Style.STROKE);
            this.mHighlightCirclePaint.setStrokeWidth(Utils.convertDpToPixel(strokeWidth));
            c.drawCircle(point.x, point.y, outerRadius2, this.mHighlightCirclePaint);
        }
        c.restore();
    }
}
