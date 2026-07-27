package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes16.dex */
public abstract class AxisRenderer extends Renderer {
    protected AxisBase mAxis;
    protected Paint mAxisLabelPaint;
    protected Paint mAxisLinePaint;
    protected Paint mGridPaint;
    protected Paint mLimitLinePaint;
    protected Transformer mTrans;

    public abstract void renderAxisLabels(Canvas canvas);

    public abstract void renderAxisLine(Canvas canvas);

    public abstract void renderGridLines(Canvas canvas);

    public abstract void renderLimitLines(Canvas canvas);

    public AxisRenderer(ViewPortHandler viewPortHandler, Transformer trans, AxisBase axis) {
        super(viewPortHandler);
        this.mTrans = trans;
        this.mAxis = axis;
        if (this.mViewPortHandler != null) {
            this.mAxisLabelPaint = new Paint(1);
            this.mGridPaint = new Paint();
            this.mGridPaint.setColor(-7829368);
            this.mGridPaint.setStrokeWidth(1.0f);
            this.mGridPaint.setStyle(Paint.Style.STROKE);
            this.mGridPaint.setAlpha(90);
            this.mAxisLinePaint = new Paint();
            this.mAxisLinePaint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mAxisLinePaint.setStrokeWidth(1.0f);
            this.mAxisLinePaint.setStyle(Paint.Style.STROKE);
            this.mLimitLinePaint = new Paint(1);
            this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
        }
    }

    public Paint getPaintAxisLabels() {
        return this.mAxisLabelPaint;
    }

    public Paint getPaintGrid() {
        return this.mGridPaint;
    }

    public Paint getPaintAxisLine() {
        return this.mAxisLinePaint;
    }

    public Transformer getTransformer() {
        return this.mTrans;
    }

    public void computeAxis(float min, float max, boolean inverted) {
        if (this.mViewPortHandler != null && this.mViewPortHandler.contentWidth() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutY()) {
            MPPointD p1 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD p2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom());
            if (!inverted) {
                min = (float) p2.y;
                max = (float) p1.y;
            } else {
                min = (float) p1.y;
                max = (float) p2.y;
            }
            MPPointD.recycleInstance(p1);
            MPPointD.recycleInstance(p2);
        }
        computeAxisValues(min, max);
    }

    protected void computeAxisValues(float f, float f2) {
        int i;
        double nextUp;
        int i2;
        int labelCount = this.mAxis.getLabelCount();
        double abs = Math.abs(f2 - f);
        if (labelCount != 0 && abs > Utils.DOUBLE_EPSILON) {
            if (!Double.isInfinite(abs)) {
                double roundToNextSignificant = Utils.roundToNextSignificant(abs / labelCount);
                if (this.mAxis.isGranularityEnabled()) {
                    roundToNextSignificant = roundToNextSignificant < ((double) this.mAxis.getGranularity()) ? this.mAxis.getGranularity() : roundToNextSignificant;
                }
                double roundToNextSignificant2 = Utils.roundToNextSignificant(Math.pow(10.0d, (int) Math.log10(roundToNextSignificant)));
                if (((int) (roundToNextSignificant / roundToNextSignificant2)) > 5) {
                    roundToNextSignificant = Math.floor(10.0d * roundToNextSignificant2);
                }
                boolean isCenterAxisLabelsEnabled = this.mAxis.isCenterAxisLabelsEnabled();
                if (this.mAxis.isForceLabelsEnabled()) {
                    roundToNextSignificant = ((float) abs) / (labelCount - 1);
                    this.mAxis.mEntryCount = labelCount;
                    if (this.mAxis.mEntries.length < labelCount) {
                        this.mAxis.mEntries = new float[labelCount];
                    }
                    float f3 = f;
                    int i3 = 0;
                    while (i3 < labelCount) {
                        this.mAxis.mEntries[i3] = f3;
                        f3 = (float) (f3 + roundToNextSignificant);
                        i3++;
                        abs = abs;
                    }
                    i2 = labelCount;
                } else {
                    double ceil = roundToNextSignificant == Utils.DOUBLE_EPSILON ? Utils.DOUBLE_EPSILON : Math.ceil(f / roundToNextSignificant) * roundToNextSignificant;
                    if (this.mAxis.isCenterAxisLabelsEnabled()) {
                        ceil -= roundToNextSignificant;
                    }
                    if (roundToNextSignificant == Utils.DOUBLE_EPSILON) {
                        i = isCenterAxisLabelsEnabled ? 1 : 0;
                        nextUp = Utils.DOUBLE_EPSILON;
                    } else {
                        i = isCenterAxisLabelsEnabled ? 1 : 0;
                        nextUp = Utils.nextUp(Math.floor(f2 / roundToNextSignificant) * roundToNextSignificant);
                    }
                    if (roundToNextSignificant != Utils.DOUBLE_EPSILON) {
                        for (double d = ceil; d <= nextUp; d += roundToNextSignificant) {
                            i++;
                        }
                    }
                    this.mAxis.mEntryCount = i;
                    if (this.mAxis.mEntries.length < i) {
                        this.mAxis.mEntries = new float[i];
                    }
                    double d2 = ceil;
                    int i4 = 0;
                    while (i4 < i) {
                        if (d2 == Utils.DOUBLE_EPSILON) {
                            d2 = Utils.DOUBLE_EPSILON;
                        }
                        this.mAxis.mEntries[i4] = (float) d2;
                        d2 += roundToNextSignificant;
                        i4++;
                        ceil = ceil;
                    }
                    i2 = i;
                }
                if (roundToNextSignificant < 1.0d) {
                    this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(roundToNextSignificant));
                } else {
                    this.mAxis.mDecimals = 0;
                }
                if (this.mAxis.isCenterAxisLabelsEnabled()) {
                    if (this.mAxis.mCenteredEntries.length < i2) {
                        this.mAxis.mCenteredEntries = new float[i2];
                    }
                    float f4 = ((float) roundToNextSignificant) / 2.0f;
                    for (int i5 = 0; i5 < i2; i5++) {
                        this.mAxis.mCenteredEntries[i5] = this.mAxis.mEntries[i5] + f4;
                    }
                    return;
                }
                return;
            }
        }
        this.mAxis.mEntries = new float[0];
        this.mAxis.mCenteredEntries = new float[0];
        this.mAxis.mEntryCount = 0;
    }
}
