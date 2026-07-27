package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes16.dex */
public class HorizontalBarChartRenderer extends BarChartRenderer {
    private RectF mBarShadowRectBuffer;

    public HorizontalBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
        this.mBarShadowRectBuffer = new RectF();
        this.mValuePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new HorizontalBarBuffer(set.getEntryCount() * 4 * (set.isStacked() ? set.getStackSize() : 1), barData.getDataSetCount(), set.isStacked());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void drawDataSet(Canvas c, IBarDataSet dataSet, int index) {
        BarData barData;
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
        boolean drawBorder = dataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(dataSet.getBarShadowColor());
            BarData barData2 = this.mChart.getBarData();
            float barWidth = barData2.getBarWidth();
            float barWidthHalf = barWidth / 2.0f;
            int i = 0;
            int count = Math.min((int) Math.ceil(dataSet.getEntryCount() * phaseX), dataSet.getEntryCount());
            while (i < count) {
                BarEntry e = (BarEntry) dataSet.getEntryForIndex(i);
                float x = e.getX();
                this.mBarShadowRectBuffer.top = x - barWidthHalf;
                this.mBarShadowRectBuffer.bottom = x + barWidthHalf;
                trans.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
                    if (!this.mViewPortHandler.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.left = this.mViewPortHandler.contentLeft();
                    this.mBarShadowRectBuffer.right = this.mViewPortHandler.contentRight();
                    barData = barData2;
                    c.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
                } else {
                    barData = barData2;
                }
                i++;
                barData2 = barData;
            }
        }
        BarBuffer buffer = this.mBarBuffers[index];
        buffer.setPhases(phaseX, phaseY);
        buffer.setDataSet(index);
        buffer.setInverted(this.mChart.isInverted(dataSet.getAxisDependency()));
        buffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        buffer.feed(dataSet);
        trans.pointValuesToPixel(buffer.buffer);
        boolean isSingleColor = dataSet.getColors().size() == 1;
        if (isSingleColor) {
            this.mRenderPaint.setColor(dataSet.getColor());
        }
        for (int j = 0; j < buffer.size() && this.mViewPortHandler.isInBoundsTop(buffer.buffer[j + 3]); j += 4) {
            if (this.mViewPortHandler.isInBoundsBottom(buffer.buffer[j + 1])) {
                if (!isSingleColor) {
                    this.mRenderPaint.setColor(dataSet.getColor(j / 4));
                }
                c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mRenderPaint);
                if (drawBorder) {
                    c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mBarBorderPaint);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        List list;
        int i;
        MPPointF iconsOffset;
        float posOffset;
        float negOffset;
        float valueOffsetPlus;
        int index;
        float halfTextHeight;
        float[] vals;
        float valueOffsetPlus2;
        Transformer trans;
        float negOffset2;
        BarEntry entry;
        float negOffset3;
        float posOffset2;
        float negOffset4;
        float y;
        float x;
        int k;
        float[] transformed;
        float y2;
        float negOffset5;
        String formattedValue;
        float negOffset6;
        float[] vals2;
        float posOffset3;
        float negOffset7;
        BarEntry entry2;
        float posOffset4;
        float negOffset8;
        int j;
        List list2;
        int i2;
        MPPointF iconsOffset2;
        BarBuffer buffer;
        BarEntry entry3;
        float negOffset9;
        MPPointF iconsOffset3;
        float posOffset5;
        float negOffset10;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float valueOffsetPlus3 = Utils.convertDpToPixel(5.0f);
            float posOffset6 = 0.0f;
            float negOffset11 = 0.0f;
            boolean drawValueAboveBar = this.mChart.isDrawValueAboveBarEnabled();
            int i3 = 0;
            while (i3 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet dataSet = (IBarDataSet) dataSets.get(i3);
                if (!shouldDrawValues(dataSet)) {
                    list = dataSets;
                    valueOffsetPlus = valueOffsetPlus3;
                    i = i3;
                } else {
                    boolean isInverted = this.mChart.isInverted(dataSet.getAxisDependency());
                    applyValueTextStyle(dataSet);
                    float halfTextHeight2 = Utils.calcTextHeight(this.mValuePaint, "10") / 2.0f;
                    ValueFormatter formatter = dataSet.getValueFormatter();
                    BarBuffer buffer2 = this.mBarBuffers[i3];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF iconsOffset4 = MPPointF.getInstance(dataSet.getIconsOffset());
                    iconsOffset4.x = Utils.convertDpToPixel(iconsOffset4.x);
                    iconsOffset4.y = Utils.convertDpToPixel(iconsOffset4.y);
                    if (!dataSet.isStacked()) {
                        int j2 = 0;
                        while (true) {
                            posOffset4 = posOffset6;
                            if (j2 >= buffer2.buffer.length * this.mAnimator.getPhaseX()) {
                                negOffset8 = negOffset11;
                                list = dataSets;
                                i = i3;
                                iconsOffset = iconsOffset4;
                                break;
                            }
                            float y3 = (buffer2.buffer[j2 + 1] + buffer2.buffer[j2 + 3]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsTop(buffer2.buffer[j2 + 1])) {
                                negOffset8 = negOffset11;
                                list = dataSets;
                                i = i3;
                                iconsOffset = iconsOffset4;
                                break;
                            }
                            if (!this.mViewPortHandler.isInBoundsX(buffer2.buffer[j2]) || !this.mViewPortHandler.isInBoundsBottom(buffer2.buffer[j2 + 1])) {
                                j = j2;
                                list2 = dataSets;
                                i2 = i3;
                                posOffset6 = posOffset4;
                                iconsOffset2 = iconsOffset4;
                                buffer = buffer2;
                            } else {
                                BarEntry entry4 = (BarEntry) dataSet.getEntryForIndex(j2 / 4);
                                float val = entry4.getY();
                                String formattedValue2 = formatter.getBarLabel(entry4);
                                float valueTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue2);
                                float posOffset7 = drawValueAboveBar ? valueOffsetPlus3 : -(valueTextWidth + valueOffsetPlus3);
                                if (drawValueAboveBar) {
                                    entry3 = entry4;
                                    negOffset9 = -(valueTextWidth + valueOffsetPlus3);
                                } else {
                                    entry3 = entry4;
                                    negOffset9 = valueOffsetPlus3;
                                }
                                if (!isInverted) {
                                    iconsOffset3 = iconsOffset4;
                                    posOffset5 = posOffset7;
                                    negOffset10 = negOffset9;
                                } else {
                                    iconsOffset3 = iconsOffset4;
                                    posOffset5 = (-posOffset7) - valueTextWidth;
                                    negOffset10 = (-negOffset9) - valueTextWidth;
                                }
                                if (dataSet.isDrawValuesEnabled()) {
                                    j = j2;
                                    list2 = dataSets;
                                    iconsOffset2 = iconsOffset3;
                                    i2 = i3;
                                    buffer = buffer2;
                                    drawValue(c, formattedValue2, buffer2.buffer[j2 + 2] + (val >= 0.0f ? posOffset5 : negOffset10), y3 + halfTextHeight2, dataSet.getValueTextColor(j2 / 2));
                                } else {
                                    j = j2;
                                    list2 = dataSets;
                                    iconsOffset2 = iconsOffset3;
                                    i2 = i3;
                                    buffer = buffer2;
                                }
                                if (entry3.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                    Drawable icon = entry3.getIcon();
                                    float px = buffer.buffer[j + 2] + (val >= 0.0f ? posOffset5 : negOffset10);
                                    float px2 = px + iconsOffset2.x;
                                    float py = y3 + iconsOffset2.y;
                                    Utils.drawImage(c, icon, (int) px2, (int) py, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                                posOffset6 = posOffset5;
                                negOffset11 = negOffset10;
                            }
                            j2 = j + 4;
                            iconsOffset4 = iconsOffset2;
                            buffer2 = buffer;
                            i3 = i2;
                            dataSets = list2;
                        }
                        valueOffsetPlus = valueOffsetPlus3;
                        posOffset6 = posOffset4;
                        negOffset11 = negOffset8;
                    } else {
                        list = dataSets;
                        i = i3;
                        iconsOffset = iconsOffset4;
                        Transformer trans2 = this.mChart.getTransformer(dataSet.getAxisDependency());
                        int bufferIndex = 0;
                        int index2 = 0;
                        while (true) {
                            if (index2 >= dataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                                posOffset = posOffset6;
                                negOffset = negOffset11;
                                valueOffsetPlus = valueOffsetPlus3;
                                break;
                            }
                            BarEntry entry5 = (BarEntry) dataSet.getEntryForIndex(index2);
                            int color = dataSet.getValueTextColor(index2);
                            float[] vals3 = entry5.getYVals();
                            if (vals3 == null) {
                                posOffset = posOffset6;
                                if (!this.mViewPortHandler.isInBoundsTop(buffer2.buffer[bufferIndex + 1])) {
                                    negOffset = negOffset11;
                                    valueOffsetPlus = valueOffsetPlus3;
                                    break;
                                }
                                if (!this.mViewPortHandler.isInBoundsX(buffer2.buffer[bufferIndex]) || !this.mViewPortHandler.isInBoundsBottom(buffer2.buffer[bufferIndex + 1])) {
                                    posOffset6 = posOffset;
                                } else {
                                    String formattedValue3 = formatter.getBarLabel(entry5);
                                    float valueTextWidth2 = Utils.calcTextWidth(this.mValuePaint, formattedValue3);
                                    if (drawValueAboveBar) {
                                        negOffset5 = valueOffsetPlus3;
                                    } else {
                                        float negOffset12 = valueTextWidth2 + valueOffsetPlus3;
                                        negOffset5 = -negOffset12;
                                    }
                                    if (drawValueAboveBar) {
                                        formattedValue = formattedValue3;
                                        negOffset6 = -(valueTextWidth2 + valueOffsetPlus3);
                                    } else {
                                        formattedValue = formattedValue3;
                                        negOffset6 = valueOffsetPlus3;
                                    }
                                    if (!isInverted) {
                                        vals2 = vals3;
                                        posOffset3 = negOffset5;
                                        negOffset7 = negOffset6;
                                    } else {
                                        vals2 = vals3;
                                        float posOffset8 = (-negOffset5) - valueTextWidth2;
                                        posOffset3 = posOffset8;
                                        negOffset7 = (-negOffset6) - valueTextWidth2;
                                    }
                                    if (dataSet.isDrawValuesEnabled()) {
                                        float f = buffer2.buffer[bufferIndex + 2] + (entry5.getY() >= 0.0f ? posOffset3 : negOffset7);
                                        float f2 = buffer2.buffer[bufferIndex + 1] + halfTextHeight2;
                                        halfTextHeight = halfTextHeight2;
                                        vals = vals2;
                                        entry2 = entry5;
                                        index = index2;
                                        drawValue(c, formattedValue, f, f2, color);
                                    } else {
                                        index = index2;
                                        halfTextHeight = halfTextHeight2;
                                        vals = vals2;
                                        entry2 = entry5;
                                    }
                                    if (entry2.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                        Drawable icon2 = entry2.getIcon();
                                        float px3 = buffer2.buffer[bufferIndex + 2] + (entry2.getY() >= 0.0f ? posOffset3 : negOffset7);
                                        float py2 = buffer2.buffer[bufferIndex + 1];
                                        Utils.drawImage(c, icon2, (int) (px3 + iconsOffset.x), (int) (py2 + iconsOffset.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                    }
                                    valueOffsetPlus2 = valueOffsetPlus3;
                                    posOffset6 = posOffset3;
                                    negOffset11 = negOffset7;
                                    trans = trans2;
                                }
                            } else {
                                float posOffset9 = posOffset6;
                                float negOffset13 = negOffset11;
                                BarEntry entry6 = entry5;
                                index = index2;
                                halfTextHeight = halfTextHeight2;
                                vals = vals3;
                                float[] transformed2 = new float[vals.length * 2];
                                float negY = -entry6.getNegativeSum();
                                int k2 = 0;
                                int idx = 0;
                                float posY = 0.0f;
                                float negY2 = negY;
                                while (k2 < transformed2.length) {
                                    float value = vals[idx];
                                    if (value == 0.0f && (posY == 0.0f || negY2 == 0.0f)) {
                                        y2 = value;
                                    } else if (value >= 0.0f) {
                                        posY += value;
                                        y2 = posY;
                                    } else {
                                        y2 = negY2;
                                        negY2 -= value;
                                    }
                                    transformed2[k2] = y2 * phaseY;
                                    k2 += 2;
                                    idx++;
                                }
                                trans2.pointValuesToPixel(transformed2);
                                int k3 = 0;
                                posOffset6 = posOffset9;
                                negOffset11 = negOffset13;
                                while (true) {
                                    if (k3 >= transformed2.length) {
                                        valueOffsetPlus2 = valueOffsetPlus3;
                                        trans = trans2;
                                        break;
                                    }
                                    float val2 = vals[k3 / 2];
                                    BarEntry entry7 = entry6;
                                    trans = trans2;
                                    String formattedValue4 = formatter.getBarStackedLabel(val2, entry7);
                                    float valueTextWidth3 = Utils.calcTextWidth(this.mValuePaint, formattedValue4);
                                    if (drawValueAboveBar) {
                                        negOffset2 = valueOffsetPlus3;
                                    } else {
                                        float negOffset14 = valueTextWidth3 + valueOffsetPlus3;
                                        negOffset2 = -negOffset14;
                                    }
                                    if (drawValueAboveBar) {
                                        entry = entry7;
                                        negOffset3 = -(valueTextWidth3 + valueOffsetPlus3);
                                    } else {
                                        entry = entry7;
                                        negOffset3 = valueOffsetPlus3;
                                    }
                                    if (!isInverted) {
                                        valueOffsetPlus2 = valueOffsetPlus3;
                                        posOffset2 = negOffset2;
                                        negOffset4 = negOffset3;
                                    } else {
                                        valueOffsetPlus2 = valueOffsetPlus3;
                                        float valueOffsetPlus4 = -negOffset2;
                                        posOffset2 = valueOffsetPlus4 - valueTextWidth3;
                                        float posOffset10 = -negOffset3;
                                        negOffset4 = posOffset10 - valueTextWidth3;
                                    }
                                    boolean drawBelow = (val2 == 0.0f && negY2 == 0.0f && posY > 0.0f) || val2 < 0.0f;
                                    float x2 = (drawBelow ? negOffset4 : posOffset2) + transformed2[k3];
                                    float y4 = (buffer2.buffer[bufferIndex + 1] + buffer2.buffer[bufferIndex + 3]) / 2.0f;
                                    if (!this.mViewPortHandler.isInBoundsTop(y4)) {
                                        posOffset6 = posOffset2;
                                        negOffset11 = negOffset4;
                                        break;
                                    }
                                    if (!this.mViewPortHandler.isInBoundsX(x2)) {
                                        k = k3;
                                        transformed = transformed2;
                                    } else if (!this.mViewPortHandler.isInBoundsBottom(y4)) {
                                        k = k3;
                                        transformed = transformed2;
                                    } else {
                                        if (!dataSet.isDrawValuesEnabled()) {
                                            y = y4;
                                            x = x2;
                                            k = k3;
                                            transformed = transformed2;
                                        } else {
                                            y = y4;
                                            x = x2;
                                            k = k3;
                                            transformed = transformed2;
                                            drawValue(c, formattedValue4, x, y4 + halfTextHeight, color);
                                        }
                                        if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                            Drawable icon3 = entry.getIcon();
                                            Utils.drawImage(c, icon3, (int) (x + iconsOffset.x), (int) (y + iconsOffset.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                        }
                                    }
                                    k3 = k + 2;
                                    posOffset6 = posOffset2;
                                    negOffset11 = negOffset4;
                                    trans2 = trans;
                                    entry6 = entry;
                                    valueOffsetPlus3 = valueOffsetPlus2;
                                    transformed2 = transformed;
                                }
                            }
                            bufferIndex = vals == null ? bufferIndex + 4 : bufferIndex + (vals.length * 4);
                            index2 = index + 1;
                            trans2 = trans;
                            halfTextHeight2 = halfTextHeight;
                            valueOffsetPlus3 = valueOffsetPlus2;
                        }
                        posOffset6 = posOffset;
                        negOffset11 = negOffset;
                    }
                    MPPointF.recycleInstance(iconsOffset);
                }
                i3 = i + 1;
                dataSets = list;
                valueOffsetPlus3 = valueOffsetPlus;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValue(Canvas c, String valueText, float x, float y, int color) {
        this.mValuePaint.setColor(color);
        c.drawText(valueText, x, y, this.mValuePaint);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
        float top = x - barWidthHalf;
        float bottom = x + barWidthHalf;
        this.mBarRect.set(y1, top, y2, bottom);
        trans.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void setHighlightDrawPos(Highlight high, RectF bar) {
        high.setDraw(bar.centerY(), bar.right);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    protected boolean isDrawingValuesAllowed(ChartInterface chart) {
        return ((float) chart.getData().getEntryCount()) < ((float) chart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }
}
