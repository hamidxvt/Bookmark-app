package com.airbnb.lottie.animation.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RoundedCornersContent implements ShapeModifierContent, BaseKeyframeAnimation.AnimationListener {
    private static final float ROUNDED_CORNER_MAGIC_NUMBER = 0.5519f;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Float, Float> roundedCorners;
    private ShapeData shapeData;

    public RoundedCornersContent(LottieDrawable lottieDrawable, BaseLayer layer, RoundedCorners roundedCorners) {
        this.lottieDrawable = lottieDrawable;
        this.name = roundedCorners.getName();
        this.roundedCorners = roundedCorners.getCornerRadius().createAnimation();
        layer.addAnimation(this.roundedCorners);
        this.roundedCorners.addUpdateListener(this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
    }

    public BaseKeyframeAnimation<Float, Float> getRoundedCorners() {
        return this.roundedCorners;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x009e, code lost:
    
        if (r7 == (r0.size() - 1)) goto L27;
     */
    @Override // com.airbnb.lottie.animation.content.ShapeModifierContent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ShapeData modifyShape(ShapeData startingShapeData) {
        List<CubicCurveData> startingCurves;
        boolean isEndOfCurve;
        float roundedness;
        boolean isClosed;
        List<CubicCurveData> startingCurves2 = startingShapeData.getCurves();
        if (startingCurves2.size() <= 2) {
            return startingShapeData;
        }
        float roundedness2 = this.roundedCorners.getValue().floatValue();
        if (roundedness2 == 0.0f) {
            return startingShapeData;
        }
        ShapeData modifiedShapeData = getShapeData(startingShapeData);
        modifiedShapeData.setInitialPoint(startingShapeData.getInitialPoint().x, startingShapeData.getInitialPoint().y);
        List<CubicCurveData> modifiedCurves = modifiedShapeData.getCurves();
        int modifiedCurvesIndex = 0;
        boolean isClosed2 = startingShapeData.isClosed();
        int i = 0;
        while (i < startingCurves2.size()) {
            CubicCurveData startingCurve = startingCurves2.get(i);
            CubicCurveData previousCurve = startingCurves2.get(floorMod(i - 1, startingCurves2.size()));
            CubicCurveData previousPreviousCurve = startingCurves2.get(floorMod(i - 2, startingCurves2.size()));
            PointF vertex = (i != 0 || isClosed2) ? previousCurve.getVertex() : startingShapeData.getInitialPoint();
            PointF inPoint = (i != 0 || isClosed2) ? previousCurve.getControlPoint2() : vertex;
            PointF outPoint = startingCurve.getControlPoint1();
            PointF previousVertex = previousPreviousCurve.getVertex();
            PointF nextVertex = startingCurve.getVertex();
            if (startingShapeData.isClosed()) {
                startingCurves = startingCurves2;
            } else {
                if (i != 0) {
                    startingCurves = startingCurves2;
                } else {
                    startingCurves = startingCurves2;
                }
                isEndOfCurve = true;
                if (!inPoint.equals(vertex) && outPoint.equals(vertex) && !isEndOfCurve) {
                    float dxToPreviousVertex = vertex.x - previousVertex.x;
                    isClosed = isClosed2;
                    float dyToPreviousVertex = vertex.y - previousVertex.y;
                    float dxToNextVertex = nextVertex.x - vertex.x;
                    float dyToNextVertex = nextVertex.y - vertex.y;
                    float dToPreviousVertex = (float) Math.hypot(dxToPreviousVertex, dyToPreviousVertex);
                    float dToNextVertex = (float) Math.hypot(dxToNextVertex, dyToNextVertex);
                    float previousVertexPercent = Math.min(roundedness2 / dToPreviousVertex, 0.5f);
                    float nextVertexPercent = Math.min(roundedness2 / dToNextVertex, 0.5f);
                    float f = vertex.x;
                    float dToNextVertex2 = previousVertex.x;
                    roundedness = roundedness2;
                    float newVertex1X = f + ((dToNextVertex2 - vertex.x) * previousVertexPercent);
                    float f2 = vertex.y;
                    float f3 = previousVertex.y;
                    float dxToNextVertex2 = vertex.y;
                    float newVertex1Y = f2 + ((f3 - dxToNextVertex2) * previousVertexPercent);
                    float newVertex2X = vertex.x + ((nextVertex.x - vertex.x) * nextVertexPercent);
                    float f4 = vertex.y;
                    float f5 = nextVertex.y;
                    float dToPreviousVertex2 = vertex.y;
                    float newVertex2Y = f4 + ((f5 - dToPreviousVertex2) * nextVertexPercent);
                    float newVertex1OutPointX = newVertex1X - ((newVertex1X - vertex.x) * ROUNDED_CORNER_MAGIC_NUMBER);
                    float dxToPreviousVertex2 = vertex.y;
                    float newVertex1OutPointY = newVertex1Y - ((newVertex1Y - dxToPreviousVertex2) * ROUNDED_CORNER_MAGIC_NUMBER);
                    float dyToNextVertex2 = vertex.x;
                    float newVertex2InPointX = newVertex2X - ((newVertex2X - dyToNextVertex2) * ROUNDED_CORNER_MAGIC_NUMBER);
                    float newVertex2InPointY = newVertex2Y - ((newVertex2Y - vertex.y) * ROUNDED_CORNER_MAGIC_NUMBER);
                    CubicCurveData previousCurveData = modifiedCurves.get(floorMod(modifiedCurvesIndex - 1, modifiedCurves.size()));
                    CubicCurveData currentCurveData = modifiedCurves.get(modifiedCurvesIndex);
                    previousCurveData.setControlPoint2(newVertex1X, newVertex1Y);
                    previousCurveData.setVertex(newVertex1X, newVertex1Y);
                    if (i == 0) {
                        modifiedShapeData.setInitialPoint(newVertex1X, newVertex1Y);
                    }
                    currentCurveData.setControlPoint1(newVertex1OutPointX, newVertex1OutPointY);
                    modifiedCurvesIndex++;
                    CubicCurveData currentCurveData2 = modifiedCurves.get(modifiedCurvesIndex);
                    currentCurveData.setControlPoint2(newVertex2InPointX, newVertex2InPointY);
                    currentCurveData.setVertex(newVertex2X, newVertex2Y);
                    currentCurveData2.setControlPoint1(newVertex2X, newVertex2Y);
                } else {
                    roundedness = roundedness2;
                    isClosed = isClosed2;
                    CubicCurveData previousCurveData2 = modifiedCurves.get(floorMod(modifiedCurvesIndex - 1, modifiedCurves.size()));
                    CubicCurveData currentCurveData3 = modifiedCurves.get(modifiedCurvesIndex);
                    previousCurveData2.setControlPoint2(previousCurve.getControlPoint2().x, previousCurve.getControlPoint2().y);
                    previousCurveData2.setVertex(previousCurve.getVertex().x, previousCurve.getVertex().y);
                    currentCurveData3.setControlPoint1(startingCurve.getControlPoint1().x, startingCurve.getControlPoint1().y);
                }
                modifiedCurvesIndex++;
                i++;
                startingCurves2 = startingCurves;
                isClosed2 = isClosed;
                roundedness2 = roundedness;
            }
            isEndOfCurve = false;
            if (!inPoint.equals(vertex)) {
            }
            roundedness = roundedness2;
            isClosed = isClosed2;
            CubicCurveData previousCurveData22 = modifiedCurves.get(floorMod(modifiedCurvesIndex - 1, modifiedCurves.size()));
            CubicCurveData currentCurveData32 = modifiedCurves.get(modifiedCurvesIndex);
            previousCurveData22.setControlPoint2(previousCurve.getControlPoint2().x, previousCurve.getControlPoint2().y);
            previousCurveData22.setVertex(previousCurve.getVertex().x, previousCurve.getVertex().y);
            currentCurveData32.setControlPoint1(startingCurve.getControlPoint1().x, startingCurve.getControlPoint1().y);
            modifiedCurvesIndex++;
            i++;
            startingCurves2 = startingCurves;
            isClosed2 = isClosed;
            roundedness2 = roundedness;
        }
        return modifiedShapeData;
    }

    private ShapeData getShapeData(ShapeData startingShapeData) {
        List<CubicCurveData> startingCurves = startingShapeData.getCurves();
        boolean isClosed = startingShapeData.isClosed();
        int vertices = 0;
        int i = startingCurves.size() - 1;
        while (true) {
            boolean isEndOfCurve = false;
            if (i < 0) {
                break;
            }
            CubicCurveData startingCurve = startingCurves.get(i);
            CubicCurveData previousCurve = startingCurves.get(floorMod(i - 1, startingCurves.size()));
            PointF vertex = (i != 0 || isClosed) ? previousCurve.getVertex() : startingShapeData.getInitialPoint();
            PointF inPoint = (i != 0 || isClosed) ? previousCurve.getControlPoint2() : vertex;
            PointF outPoint = startingCurve.getControlPoint1();
            if (!startingShapeData.isClosed() && (i == 0 || i == startingCurves.size() - 1)) {
                isEndOfCurve = true;
            }
            if (inPoint.equals(vertex) && outPoint.equals(vertex) && !isEndOfCurve) {
                vertices += 2;
            } else {
                vertices++;
            }
            i--;
        }
        if (this.shapeData == null || this.shapeData.getCurves().size() != vertices) {
            List<CubicCurveData> newCurves = new ArrayList<>(vertices);
            for (int i2 = 0; i2 < vertices; i2++) {
                newCurves.add(new CubicCurveData());
            }
            this.shapeData = new ShapeData(new PointF(0.0f, 0.0f), false, newCurves);
        }
        this.shapeData.setClosed(isClosed);
        return this.shapeData;
    }

    private static int floorMod(int x, int y) {
        return x - (floorDiv(x, y) * y);
    }

    private static int floorDiv(int x, int y) {
        int r = x / y;
        if ((x ^ y) < 0 && r * y != x) {
            return r - 1;
        }
        return r;
    }
}
