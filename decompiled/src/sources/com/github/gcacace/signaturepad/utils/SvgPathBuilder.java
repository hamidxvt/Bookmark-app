package com.github.gcacace.signaturepad.utils;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes16.dex */
public class SvgPathBuilder {
    private SvgPoint mLastPoint;
    private final SvgPoint mStartPoint;
    private final StringBuilder mStringBuilder = new StringBuilder();
    private final Integer mStrokeWidth;
    public static final Character SVG_RELATIVE_CUBIC_BEZIER_CURVE = 'c';
    public static final Character SVG_MOVE = 'M';

    public SvgPathBuilder(SvgPoint startPoint, Integer strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        this.mStartPoint = startPoint;
        this.mLastPoint = startPoint;
        this.mStringBuilder.append(SVG_RELATIVE_CUBIC_BEZIER_CURVE);
    }

    public final Integer getStrokeWidth() {
        return this.mStrokeWidth;
    }

    public final SvgPoint getLastPoint() {
        return this.mLastPoint;
    }

    public SvgPathBuilder append(SvgPoint controlPoint1, SvgPoint controlPoint2, SvgPoint endPoint) {
        this.mStringBuilder.append(makeRelativeCubicBezierCurve(controlPoint1, controlPoint2, endPoint));
        this.mLastPoint = endPoint;
        return this;
    }

    public String toString() {
        return "<path stroke-width=\"" + this.mStrokeWidth + "\" d=\"" + SVG_MOVE + this.mStartPoint + ((CharSequence) this.mStringBuilder) + "\"/>";
    }

    private String makeRelativeCubicBezierCurve(SvgPoint controlPoint1, SvgPoint controlPoint2, SvgPoint endPoint) {
        String sControlPoint1 = controlPoint1.toRelativeCoordinates(this.mLastPoint);
        String sControlPoint2 = controlPoint2.toRelativeCoordinates(this.mLastPoint);
        String sEndPoint = endPoint.toRelativeCoordinates(this.mLastPoint);
        String svg = sControlPoint1 + StringUtils.SPACE + sControlPoint2 + StringUtils.SPACE + sEndPoint + StringUtils.SPACE;
        if ("c0 0 0 0 0 0".equals(svg)) {
            return "";
        }
        return svg;
    }
}
