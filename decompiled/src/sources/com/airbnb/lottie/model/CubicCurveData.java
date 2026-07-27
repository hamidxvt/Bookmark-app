package com.airbnb.lottie.model;

import android.graphics.PointF;

/* loaded from: classes.dex */
public class CubicCurveData {
    private final PointF controlPoint1;
    private final PointF controlPoint2;
    private final PointF vertex;

    public CubicCurveData() {
        this.controlPoint1 = new PointF();
        this.controlPoint2 = new PointF();
        this.vertex = new PointF();
    }

    public CubicCurveData(PointF controlPoint1, PointF controlPoint2, PointF vertex) {
        this.controlPoint1 = controlPoint1;
        this.controlPoint2 = controlPoint2;
        this.vertex = vertex;
    }

    public void setControlPoint1(float x, float y) {
        this.controlPoint1.set(x, y);
    }

    public PointF getControlPoint1() {
        return this.controlPoint1;
    }

    public void setControlPoint2(float x, float y) {
        this.controlPoint2.set(x, y);
    }

    public PointF getControlPoint2() {
        return this.controlPoint2;
    }

    public void setVertex(float x, float y) {
        this.vertex.set(x, y);
    }

    public void setFrom(CubicCurveData curveData) {
        setVertex(curveData.vertex.x, curveData.vertex.y);
        setControlPoint1(curveData.controlPoint1.x, curveData.controlPoint1.y);
        setControlPoint2(curveData.controlPoint2.x, curveData.controlPoint2.y);
    }

    public PointF getVertex() {
        return this.vertex;
    }

    public String toString() {
        return String.format("v=%.2f,%.2f cp1=%.2f,%.2f cp2=%.2f,%.2f", Float.valueOf(this.vertex.x), Float.valueOf(this.vertex.y), Float.valueOf(this.controlPoint1.x), Float.valueOf(this.controlPoint1.y), Float.valueOf(this.controlPoint2.x), Float.valueOf(this.controlPoint2.y));
    }
}
