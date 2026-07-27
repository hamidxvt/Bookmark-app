package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class Legend extends ComponentBase {
    private List<Boolean> mCalculatedLabelBreakPoints;
    private List<FSize> mCalculatedLabelSizes;
    private List<FSize> mCalculatedLineSizes;
    private LegendDirection mDirection;
    private boolean mDrawInside;
    private LegendEntry[] mEntries;
    private LegendEntry[] mExtraEntries;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    private float mFormToTextSpace;
    private LegendHorizontalAlignment mHorizontalAlignment;
    private boolean mIsLegendCustom;
    private float mMaxSizePercent;
    public float mNeededHeight;
    public float mNeededWidth;
    private LegendOrientation mOrientation;
    private LegendForm mShape;
    private float mStackSpace;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private LegendVerticalAlignment mVerticalAlignment;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public enum LegendForm {
        NONE,
        EMPTY,
        DEFAULT,
        SQUARE,
        CIRCLE,
        LINE
    }

    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    public Legend() {
        this.mEntries = new LegendEntry[0];
        this.mIsLegendCustom = false;
        this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
        this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
        this.mOrientation = LegendOrientation.HORIZONTAL;
        this.mDrawInside = false;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mFormLineWidth = 3.0f;
        this.mFormLineDashEffect = null;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.mCalculatedLabelSizes = new ArrayList(16);
        this.mCalculatedLabelBreakPoints = new ArrayList(16);
        this.mCalculatedLineSizes = new ArrayList(16);
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(3.0f);
    }

    public Legend(LegendEntry[] entries) {
        this();
        if (entries == null) {
            throw new IllegalArgumentException("entries array is NULL");
        }
        this.mEntries = entries;
    }

    public void setEntries(List<LegendEntry> entries) {
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }

    public LegendEntry[] getEntries() {
        return this.mEntries;
    }

    public float getMaximumEntryWidth(Paint p) {
        float max = 0.0f;
        float maxFormSize = 0.0f;
        float formToTextSpace = Utils.convertDpToPixel(this.mFormToTextSpace);
        for (LegendEntry entry : this.mEntries) {
            float formSize = Utils.convertDpToPixel(Float.isNaN(entry.formSize) ? this.mFormSize : entry.formSize);
            if (formSize > maxFormSize) {
                maxFormSize = formSize;
            }
            String label = entry.label;
            if (label != null) {
                float length = Utils.calcTextWidth(p, label);
                if (length > max) {
                    max = length;
                }
            }
        }
        return max + maxFormSize + formToTextSpace;
    }

    public float getMaximumEntryHeight(Paint p) {
        float max = 0.0f;
        for (LegendEntry entry : this.mEntries) {
            String label = entry.label;
            if (label != null) {
                float length = Utils.calcTextHeight(p, label);
                if (length > max) {
                    max = length;
                }
            }
        }
        return max;
    }

    public LegendEntry[] getExtraEntries() {
        return this.mExtraEntries;
    }

    public void setExtra(List<LegendEntry> entries) {
        this.mExtraEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }

    public void setExtra(LegendEntry[] entries) {
        if (entries == null) {
            entries = new LegendEntry[0];
        }
        this.mExtraEntries = entries;
    }

    public void setExtra(int[] colors, String[] labels) {
        List<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < Math.min(colors.length, labels.length); i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors[i];
            entry.label = labels[i];
            if (entry.formColor == 1122868 || entry.formColor == 0) {
                entry.form = LegendForm.NONE;
            } else if (entry.formColor == 1122867) {
                entry.form = LegendForm.EMPTY;
            }
            entries.add(entry);
        }
        int i2 = entries.size();
        this.mExtraEntries = (LegendEntry[]) entries.toArray(new LegendEntry[i2]);
    }

    public void setCustom(LegendEntry[] entries) {
        this.mEntries = entries;
        this.mIsLegendCustom = true;
    }

    public void setCustom(List<LegendEntry> entries) {
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
        this.mIsLegendCustom = true;
    }

    public void resetCustom() {
        this.mIsLegendCustom = false;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }

    public void setHorizontalAlignment(LegendHorizontalAlignment value) {
        this.mHorizontalAlignment = value;
    }

    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }

    public void setVerticalAlignment(LegendVerticalAlignment value) {
        this.mVerticalAlignment = value;
    }

    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(LegendOrientation value) {
        this.mOrientation = value;
    }

    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }

    public void setDrawInside(boolean value) {
        this.mDrawInside = value;
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public void setDirection(LegendDirection pos) {
        this.mDirection = pos;
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public void setForm(LegendForm shape) {
        this.mShape = shape;
    }

    public void setFormSize(float size) {
        this.mFormSize = size;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public void setFormLineWidth(float size) {
        this.mFormLineWidth = size;
    }

    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public void setXEntrySpace(float space) {
        this.mXEntrySpace = space;
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public void setYEntrySpace(float space) {
        this.mYEntrySpace = space;
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public void setFormToTextSpace(float space) {
        this.mFormToTextSpace = space;
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public void setStackSpace(float space) {
        this.mStackSpace = space;
    }

    public void setWordWrapEnabled(boolean enabled) {
        this.mWordWrapEnabled = enabled;
    }

    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public void setMaxSizePercent(float maxSize) {
        this.mMaxSizePercent = maxSize;
    }

    public List<FSize> getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public List<Boolean> getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public List<FSize> getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0156  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void calculateDimensions(Paint labelpaint, ViewPortHandler viewPortHandler) {
        float formSize;
        float requiredWidth;
        float stackSpace;
        boolean wordWrapEnabled;
        float currentLineWidth;
        float currentLineWidth2 = Utils.convertDpToPixel(this.mFormSize);
        float stackSpace2 = Utils.convertDpToPixel(this.mStackSpace);
        float formToTextSpace = Utils.convertDpToPixel(this.mFormToTextSpace);
        float xEntrySpace = Utils.convertDpToPixel(this.mXEntrySpace);
        float yEntrySpace = Utils.convertDpToPixel(this.mYEntrySpace);
        boolean wordWrapEnabled2 = this.mWordWrapEnabled;
        LegendEntry[] entries = this.mEntries;
        int entryCount = entries.length;
        this.mTextWidthMax = getMaximumEntryWidth(labelpaint);
        this.mTextHeightMax = getMaximumEntryHeight(labelpaint);
        switch (this.mOrientation) {
            case VERTICAL:
                float maxWidth = 0.0f;
                float maxHeight = 0.0f;
                float width = 0.0f;
                float labelLineHeight = Utils.getLineHeight(labelpaint);
                boolean wasStacked = false;
                for (int i = 0; i < entryCount; i++) {
                    LegendEntry e = entries[i];
                    boolean drawingForm = e.form != LegendForm.NONE;
                    if (Float.isNaN(e.formSize)) {
                        formSize = currentLineWidth2;
                    } else {
                        formSize = Utils.convertDpToPixel(e.formSize);
                    }
                    String label = e.label;
                    if (!wasStacked) {
                        width = 0.0f;
                    }
                    if (drawingForm) {
                        if (wasStacked) {
                            width += stackSpace2;
                        }
                        width += formSize;
                    }
                    if (label != null) {
                        if (drawingForm && !wasStacked) {
                            width += formToTextSpace;
                        } else if (wasStacked) {
                            maxWidth = Math.max(maxWidth, width);
                            maxHeight += labelLineHeight + yEntrySpace;
                            width = 0.0f;
                            wasStacked = false;
                        }
                        width += Utils.calcTextWidth(labelpaint, label);
                        if (i < entryCount - 1) {
                            maxHeight += labelLineHeight + yEntrySpace;
                        }
                    } else {
                        wasStacked = true;
                        width += formSize;
                        if (i < entryCount - 1) {
                            width += stackSpace2;
                        }
                    }
                    maxWidth = Math.max(maxWidth, width);
                }
                this.mNeededWidth = maxWidth;
                this.mNeededHeight = maxHeight;
                break;
            case HORIZONTAL:
                float labelLineHeight2 = Utils.getLineHeight(labelpaint);
                float labelLineSpacing = Utils.getLineSpacing(labelpaint) + yEntrySpace;
                float contentWidth = viewPortHandler.contentWidth() * this.mMaxSizePercent;
                float maxLineWidth = 0.0f;
                float requiredWidth2 = 0.0f;
                this.mCalculatedLabelBreakPoints.clear();
                this.mCalculatedLabelSizes.clear();
                this.mCalculatedLineSizes.clear();
                int i2 = 0;
                float currentLineWidth3 = 0.0f;
                int stackedStartIndex = -1;
                while (i2 < entryCount) {
                    LegendEntry e2 = entries[i2];
                    float defaultFormSize = currentLineWidth2;
                    float xEntrySpace2 = xEntrySpace;
                    boolean drawingForm2 = e2.form != LegendForm.NONE;
                    float formSize2 = Float.isNaN(e2.formSize) ? defaultFormSize : Utils.convertDpToPixel(e2.formSize);
                    float yEntrySpace2 = yEntrySpace;
                    String label2 = e2.label;
                    LegendEntry[] entries2 = entries;
                    this.mCalculatedLabelBreakPoints.add(false);
                    int stackedStartIndex2 = stackedStartIndex;
                    if (stackedStartIndex2 == -1) {
                        requiredWidth = 0.0f;
                    } else {
                        requiredWidth = requiredWidth2 + stackSpace2;
                    }
                    if (label2 != null) {
                        stackSpace = stackSpace2;
                        this.mCalculatedLabelSizes.add(Utils.calcTextSize(labelpaint, label2));
                        requiredWidth2 = requiredWidth + (drawingForm2 ? formToTextSpace + formSize2 : 0.0f) + this.mCalculatedLabelSizes.get(i2).width;
                    } else {
                        stackSpace = stackSpace2;
                        float formSize3 = formSize2;
                        this.mCalculatedLabelSizes.add(FSize.getInstance(0.0f, 0.0f));
                        requiredWidth2 = requiredWidth + (drawingForm2 ? formSize3 : 0.0f);
                        if (stackedStartIndex2 == -1) {
                            stackedStartIndex2 = i2;
                        }
                    }
                    if (label2 != null || i2 == entryCount - 1) {
                        float currentLineWidth4 = currentLineWidth3;
                        float requiredSpacing = currentLineWidth4 == 0.0f ? 0.0f : xEntrySpace2;
                        if (!wordWrapEnabled2 || currentLineWidth4 == 0.0f) {
                            wordWrapEnabled = wordWrapEnabled2;
                        } else if (contentWidth - currentLineWidth4 >= requiredSpacing + requiredWidth2) {
                            wordWrapEnabled = wordWrapEnabled2;
                        } else {
                            this.mCalculatedLineSizes.add(FSize.getInstance(currentLineWidth4, labelLineHeight2));
                            maxLineWidth = Math.max(maxLineWidth, currentLineWidth4);
                            wordWrapEnabled = wordWrapEnabled2;
                            this.mCalculatedLabelBreakPoints.set(stackedStartIndex2 > -1 ? stackedStartIndex2 : i2, true);
                            currentLineWidth = requiredWidth2;
                            if (i2 == entryCount - 1) {
                                this.mCalculatedLineSizes.add(FSize.getInstance(currentLineWidth, labelLineHeight2));
                                maxLineWidth = Math.max(maxLineWidth, currentLineWidth);
                            }
                        }
                        currentLineWidth = requiredSpacing + requiredWidth2 + currentLineWidth4;
                        if (i2 == entryCount - 1) {
                        }
                    } else {
                        wordWrapEnabled = wordWrapEnabled2;
                        currentLineWidth = currentLineWidth3;
                    }
                    stackedStartIndex = label2 != null ? -1 : stackedStartIndex2;
                    i2++;
                    currentLineWidth3 = currentLineWidth;
                    currentLineWidth2 = defaultFormSize;
                    xEntrySpace = xEntrySpace2;
                    yEntrySpace = yEntrySpace2;
                    entries = entries2;
                    wordWrapEnabled2 = wordWrapEnabled;
                    stackSpace2 = stackSpace;
                }
                this.mNeededWidth = maxLineWidth;
                this.mNeededHeight = (this.mCalculatedLineSizes.size() * labelLineHeight2) + ((this.mCalculatedLineSizes.size() == 0 ? 0 : this.mCalculatedLineSizes.size() - 1) * labelLineSpacing);
                break;
        }
        float maxWidth2 = this.mNeededHeight;
        this.mNeededHeight = maxWidth2 + this.mYOffset;
        this.mNeededWidth += this.mXOffset;
    }
}
