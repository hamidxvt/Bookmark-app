package com.github.mikephil.charting.renderer;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes16.dex */
public abstract class BarLineScatterCandleBubbleRenderer extends DataRenderer {
    protected XBounds mXBounds;

    public BarLineScatterCandleBubbleRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mXBounds = new XBounds();
    }

    protected boolean shouldDrawValues(IDataSet set) {
        return set.isVisible() && (set.isDrawValuesEnabled() || set.isDrawIconsEnabled());
    }

    protected boolean isInBoundsX(Entry e, IBarLineScatterCandleBubbleDataSet set) {
        if (e == null) {
            return false;
        }
        float entryIndex = set.getEntryIndex(e);
        if (e == null || entryIndex >= set.getEntryCount() * this.mAnimator.getPhaseX()) {
            return false;
        }
        return true;
    }

    protected class XBounds {
        public int max;
        public int min;
        public int range;

        protected XBounds() {
        }

        public void set(BarLineScatterCandleBubbleDataProvider chart, IBarLineScatterCandleBubbleDataSet dataSet) {
            float phaseX = Math.max(0.0f, Math.min(1.0f, BarLineScatterCandleBubbleRenderer.this.mAnimator.getPhaseX()));
            float low = chart.getLowestVisibleX();
            float high = chart.getHighestVisibleX();
            T entryForXValue = dataSet.getEntryForXValue(low, Float.NaN, DataSet.Rounding.DOWN);
            T entryForXValue2 = dataSet.getEntryForXValue(high, Float.NaN, DataSet.Rounding.UP);
            this.min = entryForXValue == 0 ? 0 : dataSet.getEntryIndex(entryForXValue);
            this.max = entryForXValue2 != 0 ? dataSet.getEntryIndex(entryForXValue2) : 0;
            this.range = (int) ((this.max - this.min) * phaseX);
        }
    }
}
