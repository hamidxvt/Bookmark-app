package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes16.dex */
public class LegendRenderer extends Renderer {
    protected List<LegendEntry> computedEntries;
    protected Paint.FontMetrics legendFontMetrics;
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;
    private Path mLineFormPath;

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.computedEntries = new ArrayList(16);
        this.legendFontMetrics = new Paint.FontMetrics();
        this.mLineFormPath = new Path();
        this.mLegend = legend;
        this.mLegendLabelPaint = new Paint(1);
        this.mLegendLabelPaint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
        this.mLegendFormPaint = new Paint(1);
        this.mLegendFormPaint.setStyle(Paint.Style.FILL);
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    /* JADX WARN: Type inference failed for: r3v6, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    public void computeLegend(ChartData<?> chartData) {
        ChartData<?> chartData2;
        String label;
        ChartData<?> chartData3 = chartData;
        if (!this.mLegend.isLegendCustom()) {
            this.computedEntries.clear();
            int i = 0;
            while (i < chartData.getDataSetCount()) {
                ?? dataSetByIndex = chartData3.getDataSetByIndex(i);
                List<Integer> colors = dataSetByIndex.getColors();
                int entryCount = dataSetByIndex.getEntryCount();
                if ((dataSetByIndex instanceof IBarDataSet) && ((IBarDataSet) dataSetByIndex).isStacked()) {
                    IBarDataSet iBarDataSet = (IBarDataSet) dataSetByIndex;
                    String[] stackLabels = iBarDataSet.getStackLabels();
                    int i2 = 0;
                    while (i2 < colors.size() && i2 < iBarDataSet.getStackSize()) {
                        this.computedEntries.add(new LegendEntry(stackLabels[i2 % stackLabels.length], dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), colors.get(i2).intValue()));
                        i2++;
                        stackLabels = stackLabels;
                    }
                    if (iBarDataSet.getLabel() != null) {
                        this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), Legend.LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                    }
                    chartData2 = chartData3;
                } else if (dataSetByIndex instanceof IPieDataSet) {
                    IPieDataSet iPieDataSet = (IPieDataSet) dataSetByIndex;
                    for (int i3 = 0; i3 < colors.size() && i3 < entryCount; i3++) {
                        this.computedEntries.add(new LegendEntry(iPieDataSet.getEntryForIndex(i3).getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), colors.get(i3).intValue()));
                    }
                    if (iPieDataSet.getLabel() != null) {
                        this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), Legend.LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                    }
                    chartData2 = chartData;
                } else if ((dataSetByIndex instanceof ICandleDataSet) && ((ICandleDataSet) dataSetByIndex).getDecreasingColor() != 1122867) {
                    int decreasingColor = ((ICandleDataSet) dataSetByIndex).getDecreasingColor();
                    int increasingColor = ((ICandleDataSet) dataSetByIndex).getIncreasingColor();
                    this.computedEntries.add(new LegendEntry(null, dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), decreasingColor));
                    this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), increasingColor));
                    chartData2 = chartData;
                } else {
                    int i4 = 0;
                    IDataSet iDataSet = dataSetByIndex;
                    while (i4 < colors.size() && i4 < entryCount) {
                        if (i4 < colors.size() - 1 && i4 < entryCount - 1) {
                            label = null;
                        } else {
                            label = chartData.getDataSetByIndex(i).getLabel();
                        }
                        this.computedEntries.add(new LegendEntry(label, iDataSet.getForm(), iDataSet.getFormSize(), iDataSet.getFormLineWidth(), iDataSet.getFormLineDashEffect(), colors.get(i4).intValue()));
                        i4++;
                        iDataSet = iDataSet;
                    }
                    chartData2 = chartData;
                }
                i++;
                chartData3 = chartData2;
            }
            if (this.mLegend.getExtraEntries() != null) {
                Collections.addAll(this.computedEntries, this.mLegend.getExtraEntries());
            }
            this.mLegend.setEntries(this.computedEntries);
        }
        Typeface typeface = this.mLegend.getTypeface();
        if (typeface != null) {
            this.mLegendLabelPaint.setTypeface(typeface);
        }
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
        this.mLegend.calculateDimensions(this.mLegendLabelPaint, this.mViewPortHandler);
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0438  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void renderLegend(Canvas c) {
        float labelLineSpacing;
        float formToTextSpace;
        float xEntrySpace;
        float originPosX;
        float originPosX2;
        float originPosX3;
        float originPosX4;
        double d;
        float posX;
        float posX2;
        Legend.LegendHorizontalAlignment horizontalAlignment;
        int lineIndex;
        boolean isStacked;
        LegendEntry e;
        Legend.LegendHorizontalAlignment horizontalAlignment2;
        List<FSize> calculatedLineSizes;
        List<Boolean> calculatedLabelBreakPoints;
        Canvas canvas;
        float formToTextSpace2;
        float xEntrySpace2;
        float f;
        float posX3;
        float f2;
        float xoffset;
        Legend.LegendHorizontalAlignment horizontalAlignment3;
        Legend.LegendOrientation orientation;
        float formYOffset;
        LegendEntry[] entries;
        float formYOffset2;
        Legend.LegendDirection direction;
        float formToTextSpace3;
        float f3;
        float posX4;
        float posY;
        if (!this.mLegend.isEnabled()) {
        }
        Typeface tf = this.mLegend.getTypeface();
        if (tf != null) {
            this.mLegendLabelPaint.setTypeface(tf);
        }
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
        float labelLineHeight = Utils.getLineHeight(this.mLegendLabelPaint, this.legendFontMetrics);
        float labelLineSpacing2 = Utils.getLineSpacing(this.mLegendLabelPaint, this.legendFontMetrics) + Utils.convertDpToPixel(this.mLegend.getYEntrySpace());
        float stackSpace = labelLineHeight - (Utils.calcTextHeight(this.mLegendLabelPaint, "ABC") / 2.0f);
        LegendEntry[] entries2 = this.mLegend.getEntries();
        float formToTextSpace4 = Utils.convertDpToPixel(this.mLegend.getFormToTextSpace());
        float xEntrySpace3 = Utils.convertDpToPixel(this.mLegend.getXEntrySpace());
        Legend.LegendOrientation orientation2 = this.mLegend.getOrientation();
        Legend.LegendHorizontalAlignment horizontalAlignment4 = this.mLegend.getHorizontalAlignment();
        Legend.LegendVerticalAlignment verticalAlignment = this.mLegend.getVerticalAlignment();
        Legend.LegendDirection direction2 = this.mLegend.getDirection();
        float defaultFormSize = Utils.convertDpToPixel(this.mLegend.getFormSize());
        float stackSpace2 = Utils.convertDpToPixel(this.mLegend.getStackSpace());
        float yoffset = this.mLegend.getYOffset();
        float xoffset2 = this.mLegend.getXOffset();
        switch (horizontalAlignment4) {
            case LEFT:
                labelLineSpacing = labelLineSpacing2;
                formToTextSpace = formToTextSpace4;
                xEntrySpace = xEntrySpace3;
                if (orientation2 == Legend.LegendOrientation.VERTICAL) {
                    originPosX = xoffset2;
                } else {
                    originPosX = this.mViewPortHandler.contentLeft() + xoffset2;
                }
                if (direction2 != Legend.LegendDirection.RIGHT_TO_LEFT) {
                    originPosX2 = originPosX;
                    break;
                } else {
                    originPosX2 = originPosX + this.mLegend.mNeededWidth;
                    break;
                }
            case RIGHT:
                labelLineSpacing = labelLineSpacing2;
                formToTextSpace = formToTextSpace4;
                xEntrySpace = xEntrySpace3;
                if (orientation2 == Legend.LegendOrientation.VERTICAL) {
                    originPosX3 = this.mViewPortHandler.getChartWidth() - xoffset2;
                } else {
                    originPosX3 = this.mViewPortHandler.contentRight() - xoffset2;
                }
                if (direction2 != Legend.LegendDirection.LEFT_TO_RIGHT) {
                    originPosX2 = originPosX3;
                    break;
                } else {
                    originPosX2 = originPosX3 - this.mLegend.mNeededWidth;
                    break;
                }
            case CENTER:
                if (orientation2 == Legend.LegendOrientation.VERTICAL) {
                    originPosX4 = this.mViewPortHandler.getChartWidth() / 2.0f;
                } else {
                    originPosX4 = this.mViewPortHandler.contentLeft() + (this.mViewPortHandler.contentWidth() / 2.0f);
                }
                float originPosX5 = (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT ? xoffset2 : -xoffset2) + originPosX4;
                if (orientation2 != Legend.LegendOrientation.VERTICAL) {
                    originPosX2 = originPosX5;
                    labelLineSpacing = labelLineSpacing2;
                    formToTextSpace = formToTextSpace4;
                    xEntrySpace = xEntrySpace3;
                    break;
                } else {
                    labelLineSpacing = labelLineSpacing2;
                    double d2 = originPosX5;
                    if (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT) {
                        formToTextSpace = formToTextSpace4;
                        xEntrySpace = xEntrySpace3;
                        d = ((-this.mLegend.mNeededWidth) / 2.0d) + xoffset2;
                    } else {
                        formToTextSpace = formToTextSpace4;
                        xEntrySpace = xEntrySpace3;
                        d = (this.mLegend.mNeededWidth / 2.0d) - xoffset2;
                    }
                    originPosX2 = (float) (d2 + d);
                    break;
                }
            default:
                originPosX2 = 0.0f;
                labelLineSpacing = labelLineSpacing2;
                formToTextSpace = formToTextSpace4;
                xEntrySpace = xEntrySpace3;
                break;
        }
        switch (orientation2) {
            case HORIZONTAL:
                Legend.LegendHorizontalAlignment horizontalAlignment5 = horizontalAlignment4;
                float formToTextSpace5 = formToTextSpace;
                Canvas canvas2 = c;
                List<FSize> calculatedLineSizes2 = this.mLegend.getCalculatedLineSizes();
                List<FSize> calculatedLabelSizes = this.mLegend.getCalculatedLabelSizes();
                List<Boolean> calculatedLabelBreakPoints2 = this.mLegend.getCalculatedLabelBreakPoints();
                float posX5 = originPosX2;
                float posY2 = 0.0f;
                switch (verticalAlignment) {
                    case TOP:
                        posY2 = yoffset;
                        break;
                    case BOTTOM:
                        posY2 = (this.mViewPortHandler.getChartHeight() - yoffset) - this.mLegend.mNeededHeight;
                        break;
                    case CENTER:
                        posY2 = ((this.mViewPortHandler.getChartHeight() - this.mLegend.mNeededHeight) / 2.0f) + yoffset;
                        break;
                }
                int lineIndex2 = 0;
                int count = entries2.length;
                int i = 0;
                while (i < count) {
                    LegendEntry e2 = entries2[i];
                    float posX6 = posX5;
                    boolean drawingForm = e2.form != Legend.LegendForm.NONE;
                    float formSize = Float.isNaN(e2.formSize) ? defaultFormSize : Utils.convertDpToPixel(e2.formSize);
                    if (i < calculatedLabelBreakPoints2.size() && calculatedLabelBreakPoints2.get(i).booleanValue()) {
                        posX = originPosX2;
                        posX2 = posY2 + labelLineHeight + labelLineSpacing;
                    } else {
                        posX = posX6;
                        posX2 = posY2;
                    }
                    if (posX == originPosX2) {
                        horizontalAlignment = horizontalAlignment5;
                        if (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER && lineIndex2 < calculatedLineSizes2.size()) {
                            if (direction2 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                f2 = calculatedLineSizes2.get(lineIndex2).width;
                            } else {
                                f2 = -calculatedLineSizes2.get(lineIndex2).width;
                            }
                            posX += f2 / 2.0f;
                            lineIndex = lineIndex2 + 1;
                            isStacked = e2.label != null;
                            if (drawingForm) {
                                e = e2;
                                horizontalAlignment2 = horizontalAlignment;
                                calculatedLineSizes = calculatedLineSizes2;
                                calculatedLabelBreakPoints = calculatedLabelBreakPoints2;
                                canvas = c;
                                formToTextSpace2 = formToTextSpace5;
                            } else {
                                if (direction2 != Legend.LegendDirection.RIGHT_TO_LEFT) {
                                    posX3 = posX;
                                } else {
                                    posX3 = posX - formSize;
                                }
                                e = e2;
                                horizontalAlignment2 = horizontalAlignment;
                                calculatedLineSizes = calculatedLineSizes2;
                                canvas = c;
                                calculatedLabelBreakPoints = calculatedLabelBreakPoints2;
                                formToTextSpace2 = formToTextSpace5;
                                drawForm(c, posX3, posX2 + stackSpace, e, this.mLegend);
                                if (direction2 != Legend.LegendDirection.LEFT_TO_RIGHT) {
                                    posX = posX3;
                                } else {
                                    posX = posX3 + formSize;
                                }
                            }
                            if (isStacked) {
                                if (drawingForm) {
                                    posX += direction2 == Legend.LegendDirection.RIGHT_TO_LEFT ? -formToTextSpace2 : formToTextSpace2;
                                }
                                if (direction2 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                    posX -= calculatedLabelSizes.get(i).width;
                                }
                                drawLabel(canvas, posX, posX2 + labelLineHeight, e.label);
                                if (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                    posX += calculatedLabelSizes.get(i).width;
                                }
                                if (direction2 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                    xEntrySpace2 = xEntrySpace;
                                    f = -xEntrySpace2;
                                } else {
                                    xEntrySpace2 = xEntrySpace;
                                    f = xEntrySpace2;
                                }
                            } else {
                                xEntrySpace2 = xEntrySpace;
                                f = direction2 == Legend.LegendDirection.RIGHT_TO_LEFT ? -stackSpace2 : stackSpace2;
                            }
                            posX5 = posX + f;
                            i++;
                            xEntrySpace = xEntrySpace2;
                            canvas2 = canvas;
                            formToTextSpace5 = formToTextSpace2;
                            posY2 = posX2;
                            lineIndex2 = lineIndex;
                            calculatedLineSizes2 = calculatedLineSizes;
                            horizontalAlignment5 = horizontalAlignment2;
                            calculatedLabelBreakPoints2 = calculatedLabelBreakPoints;
                        }
                    } else {
                        horizontalAlignment = horizontalAlignment5;
                    }
                    lineIndex = lineIndex2;
                    isStacked = e2.label != null;
                    if (drawingForm) {
                    }
                    if (isStacked) {
                    }
                    posX5 = posX + f;
                    i++;
                    xEntrySpace = xEntrySpace2;
                    canvas2 = canvas;
                    formToTextSpace5 = formToTextSpace2;
                    posY2 = posX2;
                    lineIndex2 = lineIndex;
                    calculatedLineSizes2 = calculatedLineSizes;
                    horizontalAlignment5 = horizontalAlignment2;
                    calculatedLabelBreakPoints2 = calculatedLabelBreakPoints;
                }
                break;
            case VERTICAL:
                float posY3 = 0.0f;
                switch (verticalAlignment) {
                    case TOP:
                        float posY4 = horizontalAlignment4 == Legend.LegendHorizontalAlignment.CENTER ? 0.0f : this.mViewPortHandler.contentTop();
                        posY3 = posY4 + yoffset;
                        break;
                    case BOTTOM:
                        if (horizontalAlignment4 == Legend.LegendHorizontalAlignment.CENTER) {
                            posY = this.mViewPortHandler.getChartHeight();
                        } else {
                            posY = this.mViewPortHandler.contentBottom();
                        }
                        posY3 = posY - (this.mLegend.mNeededHeight + yoffset);
                        break;
                    case CENTER:
                        posY3 = ((this.mViewPortHandler.getChartHeight() / 2.0f) - (this.mLegend.mNeededHeight / 2.0f)) + this.mLegend.getYOffset();
                        break;
                }
                float posY5 = posY3;
                boolean wasStacked = false;
                int i2 = 0;
                float stack = 0.0f;
                while (i2 < entries2.length) {
                    LegendEntry e3 = entries2[i2];
                    boolean drawingForm2 = e3.form != Legend.LegendForm.NONE;
                    float formSize2 = Float.isNaN(e3.formSize) ? defaultFormSize : Utils.convertDpToPixel(e3.formSize);
                    float posX7 = originPosX2;
                    if (!drawingForm2) {
                        xoffset = xoffset2;
                        horizontalAlignment3 = horizontalAlignment4;
                        orientation = orientation2;
                        formYOffset = stackSpace;
                        entries = entries2;
                        formYOffset2 = stackSpace2;
                        direction = direction2;
                    } else {
                        xoffset = xoffset2;
                        if (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT) {
                            posX4 = posX7 + stack;
                        } else {
                            posX4 = posX7 - (formSize2 - stack);
                        }
                        formYOffset = stackSpace;
                        formYOffset2 = stackSpace2;
                        entries = entries2;
                        direction = direction2;
                        horizontalAlignment3 = horizontalAlignment4;
                        orientation = orientation2;
                        drawForm(c, posX4, posY5 + stackSpace, e3, this.mLegend);
                        if (direction != Legend.LegendDirection.LEFT_TO_RIGHT) {
                            posX7 = posX4;
                        } else {
                            posX7 = posX4 + formSize2;
                        }
                    }
                    if (e3.label != null) {
                        if (!drawingForm2 || wasStacked) {
                            formToTextSpace3 = formToTextSpace;
                            if (wasStacked) {
                                posX7 = originPosX2;
                            }
                        } else {
                            if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                f3 = formToTextSpace;
                                formToTextSpace3 = f3;
                            } else {
                                formToTextSpace3 = formToTextSpace;
                                f3 = -formToTextSpace3;
                            }
                            posX7 += f3;
                        }
                        if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            posX7 -= Utils.calcTextWidth(this.mLegendLabelPaint, e3.label);
                        }
                        if (!wasStacked) {
                            drawLabel(c, posX7, posY5 + labelLineHeight, e3.label);
                        } else {
                            posY5 += labelLineHeight + labelLineSpacing;
                            drawLabel(c, posX7, posY5 + labelLineHeight, e3.label);
                        }
                        posY5 += labelLineHeight + labelLineSpacing;
                        stack = 0.0f;
                    } else {
                        formToTextSpace3 = formToTextSpace;
                        stack += formSize2 + formYOffset2;
                        wasStacked = true;
                    }
                    i2++;
                    formToTextSpace = formToTextSpace3;
                    stackSpace2 = formYOffset2;
                    direction2 = direction;
                    xoffset2 = xoffset;
                    orientation2 = orientation;
                    stackSpace = formYOffset;
                    entries2 = entries;
                    horizontalAlignment4 = horizontalAlignment3;
                }
                break;
        }
    }

    protected void drawForm(Canvas c, float x, float y, LegendEntry entry, Legend legend) {
        Legend.LegendForm form;
        if (entry.formColor == 1122868 || entry.formColor == 1122867 || entry.formColor == 0) {
            return;
        }
        int restoreCount = c.save();
        Legend.LegendForm form2 = entry.form;
        if (form2 != Legend.LegendForm.DEFAULT) {
            form = form2;
        } else {
            form = legend.getForm();
        }
        this.mLegendFormPaint.setColor(entry.formColor);
        float formSize = Utils.convertDpToPixel(Float.isNaN(entry.formSize) ? legend.getFormSize() : entry.formSize);
        float half = formSize / 2.0f;
        switch (form) {
            case DEFAULT:
            case CIRCLE:
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                c.drawCircle(x + half, y, half, this.mLegendFormPaint);
                break;
            case SQUARE:
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                c.drawRect(x, y - half, x + formSize, y + half, this.mLegendFormPaint);
                break;
            case LINE:
                float formLineWidth = Utils.convertDpToPixel(Float.isNaN(entry.formLineWidth) ? legend.getFormLineWidth() : entry.formLineWidth);
                DashPathEffect formLineDashEffect = entry.formLineDashEffect == null ? legend.getFormLineDashEffect() : entry.formLineDashEffect;
                this.mLegendFormPaint.setStyle(Paint.Style.STROKE);
                this.mLegendFormPaint.setStrokeWidth(formLineWidth);
                this.mLegendFormPaint.setPathEffect(formLineDashEffect);
                this.mLineFormPath.reset();
                this.mLineFormPath.moveTo(x, y);
                this.mLineFormPath.lineTo(x + formSize, y);
                c.drawPath(this.mLineFormPath, this.mLegendFormPaint);
                break;
        }
        c.restoreToCount(restoreCount);
    }

    protected void drawLabel(Canvas c, float x, float y, String label) {
        c.drawText(label, x, y, this.mLegendLabelPaint);
    }
}
