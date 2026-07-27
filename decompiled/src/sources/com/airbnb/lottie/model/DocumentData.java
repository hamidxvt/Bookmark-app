package com.airbnb.lottie.model;

import android.graphics.PointF;

/* loaded from: classes.dex */
public class DocumentData {
    public float baselineShift;
    public PointF boxPosition;
    public PointF boxSize;
    public int color;
    public String fontName;
    public Justification justification;
    public float lineHeight;
    public float size;
    public int strokeColor;
    public boolean strokeOverFill;
    public float strokeWidth;
    public String text;
    public int tracking;

    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String text, String fontName, float size, Justification justification, int tracking, float lineHeight, float baselineShift, int color, int strokeColor, float strokeWidth, boolean strokeOverFill, PointF boxPosition, PointF boxSize) {
        set(text, fontName, size, justification, tracking, lineHeight, baselineShift, color, strokeColor, strokeWidth, strokeOverFill, boxPosition, boxSize);
    }

    public DocumentData() {
    }

    public void set(String text, String fontName, float size, Justification justification, int tracking, float lineHeight, float baselineShift, int color, int strokeColor, float strokeWidth, boolean strokeOverFill, PointF boxPosition, PointF boxSize) {
        this.text = text;
        this.fontName = fontName;
        this.size = size;
        this.justification = justification;
        this.tracking = tracking;
        this.lineHeight = lineHeight;
        this.baselineShift = baselineShift;
        this.color = color;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.strokeOverFill = strokeOverFill;
        this.boxPosition = boxPosition;
        this.boxSize = boxSize;
    }

    public int hashCode() {
        int result = this.text.hashCode();
        int result2 = (((((int) ((((result * 31) + this.fontName.hashCode()) * 31) + this.size)) * 31) + this.justification.ordinal()) * 31) + this.tracking;
        long temp = Float.floatToRawIntBits(this.lineHeight);
        return (((result2 * 31) + ((int) ((temp >>> 32) ^ temp))) * 31) + this.color;
    }
}
