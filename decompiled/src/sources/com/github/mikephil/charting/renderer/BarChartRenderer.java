package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes16.dex */
public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect;
    private RectF mBarShadowRectBuffer;
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    public BarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mBarRect = new RectF();
        this.mBarShadowRectBuffer = new RectF();
        this.mChart = chart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        this.mShadowPaint = new Paint(1);
        this.mShadowPaint.setStyle(Paint.Style.FILL);
        this.mBarBorderPaint = new Paint(1);
        this.mBarBorderPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new BarBuffer(set.getEntryCount() * 4 * (set.isStacked() ? set.getStackSize() : 1), barData.getDataSetCount(), set.isStacked());
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas c) {
        BarData barData = this.mChart.getBarData();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(i);
            if (set.isVisible()) {
                drawDataSet(c, set, i);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawDataSet(Canvas c, IBarDataSet dataSet, int index) {
        BarData barData;
        IBarDataSet iBarDataSet = dataSet;
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
                BarEntry e = (BarEntry) iBarDataSet.getEntryForIndex(i);
                float x = e.getX();
                this.mBarShadowRectBuffer.left = x - barWidthHalf;
                this.mBarShadowRectBuffer.right = x + barWidthHalf;
                trans.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
                    if (!this.mViewPortHandler.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.top = this.mViewPortHandler.contentTop();
                    this.mBarShadowRectBuffer.bottom = this.mViewPortHandler.contentBottom();
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
        buffer.feed(iBarDataSet);
        trans.pointValuesToPixel(buffer.buffer);
        boolean isSingleColor = dataSet.getColors().size() == 1;
        if (isSingleColor) {
            this.mRenderPaint.setColor(dataSet.getColor());
        }
        int j = 0;
        while (j < buffer.size()) {
            if (this.mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                if (this.mViewPortHandler.isInBoundsRight(buffer.buffer[j])) {
                    if (!isSingleColor) {
                        this.mRenderPaint.setColor(iBarDataSet.getColor(j / 4));
                    }
                    if (dataSet.getGradientColor() != null) {
                        GradientColor gradientColor = dataSet.getGradientColor();
                        this.mRenderPaint.setShader(new LinearGradient(buffer.buffer[j], buffer.buffer[j + 3], buffer.buffer[j], buffer.buffer[j + 1], gradientColor.getStartColor(), gradientColor.getEndColor(), Shader.TileMode.MIRROR));
                    }
                    if (dataSet.getGradientColors() != null) {
                        this.mRenderPaint.setShader(new LinearGradient(buffer.buffer[j], buffer.buffer[j + 3], buffer.buffer[j], buffer.buffer[j + 1], iBarDataSet.getGradientColor(j / 4).getStartColor(), iBarDataSet.getGradientColor(j / 4).getEndColor(), Shader.TileMode.MIRROR));
                    }
                    c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mRenderPaint);
                    if (drawBorder) {
                        c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mBarBorderPaint);
                    }
                } else {
                    return;
                }
            }
            j += 4;
            iBarDataSet = dataSet;
        }
    }

    protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
        float left = x - barWidthHalf;
        float right = x + barWidthHalf;
        this.mBarRect.set(left, y1, right, y2);
        trans.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        float posOffset;
        float negOffset;
        List list;
        float valueOffsetPlus;
        boolean drawValueAboveBar;
        MPPointF iconsOffset;
        int index;
        boolean isInverted;
        float valueTextHeight;
        float[] vals;
        Transformer trans;
        float x;
        int k;
        float[] transformed;
        BarEntry entry;
        float y;
        float y2;
        int index2;
        boolean isInverted2;
        float valueTextHeight2;
        float x2;
        BarEntry entry2;
        int j;
        List list2;
        float valueOffsetPlus2;
        boolean drawValueAboveBar2;
        MPPointF iconsOffset2;
        ValueFormatter formatter;
        BarBuffer buffer;
        BarEntry entry3;
        float x3;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float valueOffsetPlus3 = Utils.convertDpToPixel(4.5f);
            boolean drawValueAboveBar3 = this.mChart.isDrawValueAboveBarEnabled();
            int i = 0;
            while (i < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet dataSet = (IBarDataSet) dataSets.get(i);
                if (!shouldDrawValues(dataSet)) {
                    list = dataSets;
                    valueOffsetPlus = valueOffsetPlus3;
                    drawValueAboveBar = drawValueAboveBar3;
                } else {
                    applyValueTextStyle(dataSet);
                    boolean isInverted3 = this.mChart.isInverted(dataSet.getAxisDependency());
                    float valueTextHeight3 = Utils.calcTextHeight(this.mValuePaint, "8");
                    float posOffset2 = drawValueAboveBar3 ? -valueOffsetPlus3 : valueTextHeight3 + valueOffsetPlus3;
                    float negOffset2 = drawValueAboveBar3 ? valueTextHeight3 + valueOffsetPlus3 : -valueOffsetPlus3;
                    if (!isInverted3) {
                        posOffset = posOffset2;
                        negOffset = negOffset2;
                    } else {
                        posOffset = (-posOffset2) - valueTextHeight3;
                        negOffset = (-negOffset2) - valueTextHeight3;
                    }
                    BarBuffer buffer2 = this.mBarBuffers[i];
                    float phaseY = this.mAnimator.getPhaseY();
                    ValueFormatter formatter2 = dataSet.getValueFormatter();
                    MPPointF iconsOffset3 = MPPointF.getInstance(dataSet.getIconsOffset());
                    iconsOffset3.x = Utils.convertDpToPixel(iconsOffset3.x);
                    iconsOffset3.y = Utils.convertDpToPixel(iconsOffset3.y);
                    if (!dataSet.isStacked()) {
                        int j2 = 0;
                        while (true) {
                            MPPointF iconsOffset4 = iconsOffset3;
                            if (j2 >= buffer2.buffer.length * this.mAnimator.getPhaseX()) {
                                list = dataSets;
                                valueOffsetPlus = valueOffsetPlus3;
                                drawValueAboveBar = drawValueAboveBar3;
                                iconsOffset = iconsOffset4;
                                break;
                            }
                            float x4 = (buffer2.buffer[j2] + buffer2.buffer[j2 + 2]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsRight(x4)) {
                                list = dataSets;
                                valueOffsetPlus = valueOffsetPlus3;
                                drawValueAboveBar = drawValueAboveBar3;
                                iconsOffset = iconsOffset4;
                                break;
                            }
                            if (!this.mViewPortHandler.isInBoundsY(buffer2.buffer[j2 + 1])) {
                                j = j2;
                                list2 = dataSets;
                                valueOffsetPlus2 = valueOffsetPlus3;
                                drawValueAboveBar2 = drawValueAboveBar3;
                                iconsOffset2 = iconsOffset4;
                                formatter = formatter2;
                                buffer = buffer2;
                            } else if (!this.mViewPortHandler.isInBoundsLeft(x4)) {
                                j = j2;
                                list2 = dataSets;
                                valueOffsetPlus2 = valueOffsetPlus3;
                                drawValueAboveBar2 = drawValueAboveBar3;
                                iconsOffset2 = iconsOffset4;
                                formatter = formatter2;
                                buffer = buffer2;
                            } else {
                                BarEntry entry4 = (BarEntry) dataSet.getEntryForIndex(j2 / 4);
                                float val = entry4.getY();
                                if (!dataSet.isDrawValuesEnabled()) {
                                    entry3 = entry4;
                                    j = j2;
                                    list2 = dataSets;
                                    valueOffsetPlus2 = valueOffsetPlus3;
                                    drawValueAboveBar2 = drawValueAboveBar3;
                                    iconsOffset2 = iconsOffset4;
                                    x3 = x4;
                                    formatter = formatter2;
                                    buffer = buffer2;
                                } else {
                                    entry3 = entry4;
                                    j = j2;
                                    list2 = dataSets;
                                    iconsOffset2 = iconsOffset4;
                                    x3 = x4;
                                    valueOffsetPlus2 = valueOffsetPlus3;
                                    formatter = formatter2;
                                    drawValueAboveBar2 = drawValueAboveBar3;
                                    buffer = buffer2;
                                    drawValue(c, formatter2.getBarLabel(entry4), x4, val >= 0.0f ? buffer2.buffer[j2 + 1] + posOffset : buffer2.buffer[j2 + 3] + negOffset, dataSet.getValueTextColor(j2 / 4));
                                }
                                if (entry3.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                    Drawable icon = entry3.getIcon();
                                    float px = x3;
                                    float py = val >= 0.0f ? buffer.buffer[j + 1] + posOffset : buffer.buffer[j + 3] + negOffset;
                                    Utils.drawImage(c, icon, (int) (px + iconsOffset2.x), (int) (py + iconsOffset2.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                            }
                            j2 = j + 4;
                            iconsOffset3 = iconsOffset2;
                            formatter2 = formatter;
                            buffer2 = buffer;
                            dataSets = list2;
                            drawValueAboveBar3 = drawValueAboveBar2;
                            valueOffsetPlus3 = valueOffsetPlus2;
                        }
                    } else {
                        list = dataSets;
                        valueOffsetPlus = valueOffsetPlus3;
                        drawValueAboveBar = drawValueAboveBar3;
                        iconsOffset = iconsOffset3;
                        Transformer trans2 = this.mChart.getTransformer(dataSet.getAxisDependency());
                        int bufferIndex = 0;
                        int index3 = 0;
                        while (index3 < dataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry entry5 = (BarEntry) dataSet.getEntryForIndex(index3);
                            float[] vals2 = entry5.getYVals();
                            float x5 = (buffer2.buffer[bufferIndex] + buffer2.buffer[bufferIndex + 2]) / 2.0f;
                            int color = dataSet.getValueTextColor(index3);
                            if (vals2 == null) {
                                if (!this.mViewPortHandler.isInBoundsRight(x5)) {
                                    break;
                                }
                                if (!this.mViewPortHandler.isInBoundsY(buffer2.buffer[bufferIndex + 1])) {
                                    index2 = index3;
                                    isInverted2 = isInverted3;
                                    valueTextHeight2 = valueTextHeight3;
                                } else if (!this.mViewPortHandler.isInBoundsLeft(x5)) {
                                    index2 = index3;
                                    isInverted2 = isInverted3;
                                    valueTextHeight2 = valueTextHeight3;
                                } else {
                                    if (!dataSet.isDrawValuesEnabled()) {
                                        x2 = x5;
                                        index = index3;
                                        isInverted = isInverted3;
                                        valueTextHeight = valueTextHeight3;
                                        vals = vals2;
                                        entry2 = entry5;
                                        trans = trans2;
                                    } else {
                                        x2 = x5;
                                        isInverted = isInverted3;
                                        vals = vals2;
                                        entry2 = entry5;
                                        index = index3;
                                        valueTextHeight = valueTextHeight3;
                                        trans = trans2;
                                        drawValue(c, formatter2.getBarLabel(entry5), x2, buffer2.buffer[bufferIndex + 1] + (entry5.getY() >= 0.0f ? posOffset : negOffset), color);
                                    }
                                    if (entry2.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                        Drawable icon2 = entry2.getIcon();
                                        float px2 = x2;
                                        float py2 = buffer2.buffer[bufferIndex + 1] + (entry2.getY() >= 0.0f ? posOffset : negOffset);
                                        Utils.drawImage(c, icon2, (int) (px2 + iconsOffset.x), (int) (py2 + iconsOffset.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                    }
                                }
                                trans2 = trans2;
                                valueTextHeight3 = valueTextHeight2;
                                isInverted3 = isInverted2;
                                index3 = index2;
                            } else {
                                float x6 = x5;
                                BarEntry entry6 = entry5;
                                index = index3;
                                isInverted = isInverted3;
                                valueTextHeight = valueTextHeight3;
                                vals = vals2;
                                trans = trans2;
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
                                    transformed2[k2 + 1] = y2 * phaseY;
                                    k2 += 2;
                                    idx++;
                                }
                                trans.pointValuesToPixel(transformed2);
                                int k3 = 0;
                                while (k3 < transformed2.length) {
                                    float val2 = vals[k3 / 2];
                                    boolean drawBelow = (val2 == 0.0f && negY2 == 0.0f && posY > 0.0f) || val2 < 0.0f;
                                    float y3 = transformed2[k3 + 1] + (drawBelow ? negOffset : posOffset);
                                    float x7 = x6;
                                    if (!this.mViewPortHandler.isInBoundsRight(x7)) {
                                        break;
                                    }
                                    if (!this.mViewPortHandler.isInBoundsY(y3)) {
                                        x = x7;
                                        k = k3;
                                        transformed = transformed2;
                                        entry = entry6;
                                    } else if (!this.mViewPortHandler.isInBoundsLeft(x7)) {
                                        x = x7;
                                        k = k3;
                                        transformed = transformed2;
                                        entry = entry6;
                                    } else {
                                        if (!dataSet.isDrawValuesEnabled()) {
                                            x = x7;
                                            y = y3;
                                            k = k3;
                                            transformed = transformed2;
                                            entry = entry6;
                                        } else {
                                            BarEntry entry7 = entry6;
                                            entry = entry7;
                                            x = x7;
                                            y = y3;
                                            k = k3;
                                            transformed = transformed2;
                                            drawValue(c, formatter2.getBarStackedLabel(val2, entry7), x, y, color);
                                        }
                                        if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                            Drawable icon3 = entry.getIcon();
                                            Utils.drawImage(c, icon3, (int) (x + iconsOffset.x), (int) (y + iconsOffset.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                        }
                                    }
                                    k3 = k + 2;
                                    entry6 = entry;
                                    x6 = x;
                                    transformed2 = transformed;
                                }
                            }
                            bufferIndex = vals == null ? bufferIndex + 4 : bufferIndex + (vals.length * 4);
                            index3 = index + 1;
                            trans2 = trans;
                            valueTextHeight3 = valueTextHeight;
                            isInverted3 = isInverted;
                        }
                    }
                    MPPointF.recycleInstance(iconsOffset);
                }
                i++;
                dataSets = list;
                drawValueAboveBar3 = drawValueAboveBar;
                valueOffsetPlus3 = valueOffsetPlus;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValue(Canvas c, String valueText, float x, float y, int color) {
        this.mValuePaint.setColor(color);
        c.drawText(valueText, x, y, this.mValuePaint);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas c, Highlight[] indices) {
        float y1;
        float y2;
        BarData barData = this.mChart.getBarData();
        for (Highlight high : indices) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(high.getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                BarEntry e = (BarEntry) set.getEntryForXValue(high.getX(), high.getY());
                if (isInBoundsX(e, set)) {
                    Transformer trans = this.mChart.getTransformer(set.getAxisDependency());
                    this.mHighlightPaint.setColor(set.getHighLightColor());
                    this.mHighlightPaint.setAlpha(set.getHighLightAlpha());
                    boolean isStack = high.getStackIndex() >= 0 && e.isStacked();
                    if (isStack) {
                        if (this.mChart.isHighlightFullBarEnabled()) {
                            float y12 = e.getPositiveSum();
                            y1 = y12;
                            y2 = -e.getNegativeSum();
                        } else {
                            Range range = e.getRanges()[high.getStackIndex()];
                            float y13 = range.from;
                            float y22 = range.to;
                            y2 = y22;
                            y1 = y13;
                        }
                    } else {
                        float y14 = e.getY();
                        y1 = y14;
                        y2 = 0.0f;
                    }
                    prepareBarHighlight(e.getX(), y1, y2, barData.getBarWidth() / 2.0f, trans);
                    setHighlightDrawPos(high, this.mBarRect);
                    c.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    protected void setHighlightDrawPos(Highlight high, RectF bar) {
        high.setDraw(bar.centerX(), bar.top);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas c) {
    }
}
