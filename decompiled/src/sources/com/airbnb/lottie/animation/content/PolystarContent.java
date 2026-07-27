package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

/* loaded from: classes.dex */
public class PolystarContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private static final float POLYGON_MAGIC_NUMBER = 0.25f;
    private static final float POLYSTAR_MAGIC_NUMBER = 0.47829f;
    private final boolean hidden;
    private final BaseKeyframeAnimation<?, Float> innerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> innerRoundednessAnimation;
    private boolean isPathValid;
    private final boolean isReversed;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<?, Float> outerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> outerRoundednessAnimation;
    private final BaseKeyframeAnimation<?, Float> pointsAnimation;
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final BaseKeyframeAnimation<?, Float> rotationAnimation;
    private final PolystarShape.Type type;
    private final Path path = new Path();
    private final Path lastSegmentPath = new Path();
    private final PathMeasure lastSegmentPathMeasure = new PathMeasure();
    private final float[] lastSegmentPosition = new float[2];
    private final CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();

    public PolystarContent(LottieDrawable lottieDrawable, BaseLayer layer, PolystarShape polystarShape) {
        this.lottieDrawable = lottieDrawable;
        this.name = polystarShape.getName();
        this.type = polystarShape.getType();
        this.hidden = polystarShape.isHidden();
        this.isReversed = polystarShape.isReversed();
        this.pointsAnimation = polystarShape.getPoints().createAnimation();
        this.positionAnimation = polystarShape.getPosition().createAnimation();
        this.rotationAnimation = polystarShape.getRotation().createAnimation();
        this.outerRadiusAnimation = polystarShape.getOuterRadius().createAnimation();
        this.outerRoundednessAnimation = polystarShape.getOuterRoundedness().createAnimation();
        if (this.type == PolystarShape.Type.STAR) {
            this.innerRadiusAnimation = polystarShape.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = polystarShape.getInnerRoundedness().createAnimation();
        } else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        layer.addAnimation(this.pointsAnimation);
        layer.addAnimation(this.positionAnimation);
        layer.addAnimation(this.rotationAnimation);
        layer.addAnimation(this.outerRadiusAnimation);
        layer.addAnimation(this.outerRoundednessAnimation);
        if (this.type == PolystarShape.Type.STAR) {
            layer.addAnimation(this.innerRadiusAnimation);
            layer.addAnimation(this.innerRoundednessAnimation);
        }
        this.pointsAnimation.addUpdateListener(this);
        this.positionAnimation.addUpdateListener(this);
        this.rotationAnimation.addUpdateListener(this);
        this.outerRadiusAnimation.addUpdateListener(this);
        this.outerRoundednessAnimation.addUpdateListener(this);
        if (this.type == PolystarShape.Type.STAR) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            Content content = contentsBefore.get(i);
            if ((content instanceof TrimPathContent) && ((TrimPathContent) content).getType() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                TrimPathContent trimPath = (TrimPathContent) content;
                this.trimPaths.addTrimPath(trimPath);
                trimPath.addListener(this);
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        switch (this.type) {
            case STAR:
                createStarPath();
                break;
            case POLYGON:
                createPolygonPath();
                break;
        }
        this.path.close();
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void createStarPath() {
        float outerRadius;
        float x;
        float halfAnglePerPoint;
        double currentAngle;
        float y;
        float radius;
        float dTheta;
        float dTheta2;
        float anglePerPoint;
        float outerRadius2;
        float radius2;
        PolystarContent polystarContent;
        float innerRadius;
        float innerRoundedness;
        float outerRoundedness;
        float dTheta3;
        float cp2Theta;
        float cp2Dx;
        float cp2x;
        float cp2y;
        float points = this.pointsAnimation.getValue().floatValue();
        double currentAngle2 = Math.toRadians((this.rotationAnimation == null ? Utils.DOUBLE_EPSILON : this.rotationAnimation.getValue().floatValue()) - 90.0d);
        float anglePerPoint2 = (float) (6.283185307179586d / points);
        if (this.isReversed) {
            anglePerPoint2 *= -1.0f;
        }
        float halfAnglePerPoint2 = anglePerPoint2 / 2.0f;
        float partialPointAmount = points - ((int) points);
        if (partialPointAmount != 0.0f) {
            currentAngle2 += (1.0f - partialPointAmount) * halfAnglePerPoint2;
        }
        float outerRadius3 = this.outerRadiusAnimation.getValue().floatValue();
        float innerRadius2 = this.innerRadiusAnimation.getValue().floatValue();
        float innerRoundedness2 = 0.0f;
        if (this.innerRoundednessAnimation != null) {
            innerRoundedness2 = this.innerRoundednessAnimation.getValue().floatValue() / 100.0f;
        }
        float outerRoundedness2 = 0.0f;
        if (this.outerRoundednessAnimation != null) {
            outerRoundedness2 = this.outerRoundednessAnimation.getValue().floatValue() / 100.0f;
        }
        float partialPointRadius = 0.0f;
        if (partialPointAmount != 0.0f) {
            partialPointRadius = innerRadius2 + ((outerRadius3 - innerRadius2) * partialPointAmount);
            x = (float) (partialPointRadius * Math.cos(currentAngle2));
            y = (float) (partialPointRadius * Math.sin(currentAngle2));
            this.path.moveTo(x, y);
            currentAngle = currentAngle2 + ((anglePerPoint2 * partialPointAmount) / 2.0f);
            outerRadius = outerRadius3;
            halfAnglePerPoint = halfAnglePerPoint2;
        } else {
            outerRadius = outerRadius3;
            x = (float) (outerRadius * Math.cos(currentAngle2));
            float y2 = (float) (outerRadius * Math.sin(currentAngle2));
            this.path.moveTo(x, y2);
            halfAnglePerPoint = halfAnglePerPoint2;
            currentAngle = currentAngle2 + halfAnglePerPoint;
            y = y2;
        }
        boolean longSegment = false;
        double currentAngle3 = currentAngle;
        double numPoints = Math.ceil(points) * 2.0d;
        int i = 0;
        while (true) {
            float points2 = points;
            if (i < numPoints) {
                float radius3 = longSegment ? outerRadius : innerRadius2;
                float dTheta4 = halfAnglePerPoint;
                if (partialPointRadius != 0.0f) {
                    radius = radius3;
                    dTheta = dTheta4;
                    if (i == numPoints - 2.0d) {
                        dTheta2 = (anglePerPoint2 * partialPointAmount) / 2.0f;
                        if (partialPointRadius == 0.0f) {
                            anglePerPoint = anglePerPoint2;
                            outerRadius2 = outerRadius;
                            if (i == numPoints - 1.0d) {
                                radius2 = partialPointRadius;
                                float previousX = x;
                                float previousY = y;
                                double numPoints2 = numPoints;
                                x = (float) (radius2 * Math.cos(currentAngle3));
                                y = (float) (radius2 * Math.sin(currentAngle3));
                                if (innerRoundedness2 == 0.0f || outerRoundedness2 != 0.0f) {
                                    polystarContent = this;
                                    innerRadius = innerRadius2;
                                    innerRoundedness = innerRoundedness2;
                                    float cp1Theta = (float) (Math.atan2(previousY, previousX) - 1.5707963267948966d);
                                    float cp1Dx = (float) Math.cos(cp1Theta);
                                    float cp1Dy = (float) Math.sin(cp1Theta);
                                    outerRoundedness = outerRoundedness2;
                                    dTheta3 = dTheta2;
                                    float cp2Theta2 = (float) (Math.atan2(y, x) - 1.5707963267948966d);
                                    float cp2Dx2 = (float) Math.cos(cp2Theta2);
                                    float cp2Dy = (float) Math.sin(cp2Theta2);
                                    float cp1Roundedness = longSegment ? innerRoundedness : outerRoundedness;
                                    float cp2Roundedness = longSegment ? outerRoundedness : innerRoundedness;
                                    float cp1Radius = longSegment ? innerRadius : outerRadius2;
                                    float cp2Radius = longSegment ? outerRadius2 : innerRadius;
                                    float cp1x = cp1Radius * cp1Roundedness * POLYSTAR_MAGIC_NUMBER * cp1Dx;
                                    float cp1y = cp1Radius * cp1Roundedness * POLYSTAR_MAGIC_NUMBER * cp1Dy;
                                    float cp2x2 = cp2Radius * cp2Roundedness * POLYSTAR_MAGIC_NUMBER * cp2Dx2;
                                    float cp2y2 = cp2Radius * cp2Roundedness * POLYSTAR_MAGIC_NUMBER * cp2Dy;
                                    if (partialPointAmount != 0.0f) {
                                        if (i == 0) {
                                            cp2Theta = cp1x * partialPointAmount;
                                            cp2Dx = cp1y * partialPointAmount;
                                            cp2x = cp2x2;
                                            cp2y = cp2y2;
                                        } else if (i == numPoints2 - 1.0d) {
                                            cp2Theta = cp1x;
                                            cp2Dx = cp1y;
                                            cp2x = cp2x2 * partialPointAmount;
                                            cp2y = cp2y2 * partialPointAmount;
                                        }
                                        polystarContent.path.cubicTo(previousX - cp2Theta, previousY - cp2Dx, x + cp2x, y + cp2y, x, y);
                                    }
                                    cp2Theta = cp1x;
                                    cp2Dx = cp1y;
                                    cp2x = cp2x2;
                                    cp2y = cp2y2;
                                    polystarContent.path.cubicTo(previousX - cp2Theta, previousY - cp2Dx, x + cp2x, y + cp2y, x, y);
                                } else {
                                    polystarContent = this;
                                    polystarContent.path.lineTo(x, y);
                                    dTheta3 = dTheta2;
                                    innerRadius = innerRadius2;
                                    innerRoundedness = innerRoundedness2;
                                    outerRoundedness = outerRoundedness2;
                                }
                                currentAngle3 += dTheta3;
                                longSegment = !longSegment;
                                i++;
                                anglePerPoint2 = anglePerPoint;
                                points = points2;
                                outerRadius = outerRadius2;
                                numPoints = numPoints2;
                                innerRadius2 = innerRadius;
                                innerRoundedness2 = innerRoundedness;
                                outerRoundedness2 = outerRoundedness;
                            }
                        } else {
                            anglePerPoint = anglePerPoint2;
                            outerRadius2 = outerRadius;
                        }
                        radius2 = radius;
                        float previousX2 = x;
                        float previousY2 = y;
                        double numPoints22 = numPoints;
                        x = (float) (radius2 * Math.cos(currentAngle3));
                        y = (float) (radius2 * Math.sin(currentAngle3));
                        if (innerRoundedness2 == 0.0f) {
                        }
                        polystarContent = this;
                        innerRadius = innerRadius2;
                        innerRoundedness = innerRoundedness2;
                        float cp1Theta2 = (float) (Math.atan2(previousY2, previousX2) - 1.5707963267948966d);
                        float cp1Dx2 = (float) Math.cos(cp1Theta2);
                        float cp1Dy2 = (float) Math.sin(cp1Theta2);
                        outerRoundedness = outerRoundedness2;
                        dTheta3 = dTheta2;
                        float cp2Theta22 = (float) (Math.atan2(y, x) - 1.5707963267948966d);
                        float cp2Dx22 = (float) Math.cos(cp2Theta22);
                        float cp2Dy2 = (float) Math.sin(cp2Theta22);
                        if (longSegment) {
                        }
                        float cp2Roundedness2 = longSegment ? outerRoundedness : innerRoundedness;
                        float cp1Radius2 = longSegment ? innerRadius : outerRadius2;
                        float cp2Radius2 = longSegment ? outerRadius2 : innerRadius;
                        float cp1x2 = cp1Radius2 * cp1Roundedness * POLYSTAR_MAGIC_NUMBER * cp1Dx2;
                        float cp1y2 = cp1Radius2 * cp1Roundedness * POLYSTAR_MAGIC_NUMBER * cp1Dy2;
                        float cp2x22 = cp2Radius2 * cp2Roundedness2 * POLYSTAR_MAGIC_NUMBER * cp2Dx22;
                        float cp2y22 = cp2Radius2 * cp2Roundedness2 * POLYSTAR_MAGIC_NUMBER * cp2Dy2;
                        if (partialPointAmount != 0.0f) {
                        }
                        cp2Theta = cp1x2;
                        cp2Dx = cp1y2;
                        cp2x = cp2x22;
                        cp2y = cp2y22;
                        polystarContent.path.cubicTo(previousX2 - cp2Theta, previousY2 - cp2Dx, x + cp2x, y + cp2y, x, y);
                        currentAngle3 += dTheta3;
                        longSegment = !longSegment;
                        i++;
                        anglePerPoint2 = anglePerPoint;
                        points = points2;
                        outerRadius = outerRadius2;
                        numPoints = numPoints22;
                        innerRadius2 = innerRadius;
                        innerRoundedness2 = innerRoundedness;
                        outerRoundedness2 = outerRoundedness;
                    }
                } else {
                    radius = radius3;
                    dTheta = dTheta4;
                }
                dTheta2 = dTheta;
                if (partialPointRadius == 0.0f) {
                }
                radius2 = radius;
                float previousX22 = x;
                float previousY22 = y;
                double numPoints222 = numPoints;
                x = (float) (radius2 * Math.cos(currentAngle3));
                y = (float) (radius2 * Math.sin(currentAngle3));
                if (innerRoundedness2 == 0.0f) {
                }
                polystarContent = this;
                innerRadius = innerRadius2;
                innerRoundedness = innerRoundedness2;
                float cp1Theta22 = (float) (Math.atan2(previousY22, previousX22) - 1.5707963267948966d);
                float cp1Dx22 = (float) Math.cos(cp1Theta22);
                float cp1Dy22 = (float) Math.sin(cp1Theta22);
                outerRoundedness = outerRoundedness2;
                dTheta3 = dTheta2;
                float cp2Theta222 = (float) (Math.atan2(y, x) - 1.5707963267948966d);
                float cp2Dx222 = (float) Math.cos(cp2Theta222);
                float cp2Dy22 = (float) Math.sin(cp2Theta222);
                if (longSegment) {
                }
                float cp2Roundedness22 = longSegment ? outerRoundedness : innerRoundedness;
                float cp1Radius22 = longSegment ? innerRadius : outerRadius2;
                float cp2Radius22 = longSegment ? outerRadius2 : innerRadius;
                float cp1x22 = cp1Radius22 * cp1Roundedness * POLYSTAR_MAGIC_NUMBER * cp1Dx22;
                float cp1y22 = cp1Radius22 * cp1Roundedness * POLYSTAR_MAGIC_NUMBER * cp1Dy22;
                float cp2x222 = cp2Radius22 * cp2Roundedness22 * POLYSTAR_MAGIC_NUMBER * cp2Dx222;
                float cp2y222 = cp2Radius22 * cp2Roundedness22 * POLYSTAR_MAGIC_NUMBER * cp2Dy22;
                if (partialPointAmount != 0.0f) {
                }
                cp2Theta = cp1x22;
                cp2Dx = cp1y22;
                cp2x = cp2x222;
                cp2y = cp2y222;
                polystarContent.path.cubicTo(previousX22 - cp2Theta, previousY22 - cp2Dx, x + cp2x, y + cp2y, x, y);
                currentAngle3 += dTheta3;
                longSegment = !longSegment;
                i++;
                anglePerPoint2 = anglePerPoint;
                points = points2;
                outerRadius = outerRadius2;
                numPoints = numPoints222;
                innerRadius2 = innerRadius;
                innerRoundedness2 = innerRoundedness;
                outerRoundedness2 = outerRoundedness;
            } else {
                PointF position = this.positionAnimation.getValue();
                this.path.offset(position.x, position.y);
                this.path.close();
                return;
            }
        }
    }

    private void createPolygonPath() {
        int points;
        double currentAngle;
        float anglePerPoint;
        float roundedness;
        float anglePerPoint2;
        float previousY;
        PolystarContent polystarContent = this;
        int points2 = (int) Math.floor(polystarContent.pointsAnimation.getValue().floatValue());
        double currentAngle2 = Math.toRadians((polystarContent.rotationAnimation == null ? Utils.DOUBLE_EPSILON : polystarContent.rotationAnimation.getValue().floatValue()) - 90.0d);
        float previousX = (float) (6.283185307179586d / points2);
        float roundedness2 = polystarContent.outerRoundednessAnimation.getValue().floatValue() / 100.0f;
        float radius = polystarContent.outerRadiusAnimation.getValue().floatValue();
        float x = (float) (radius * Math.cos(currentAngle2));
        float y = (float) (radius * Math.sin(currentAngle2));
        polystarContent.path.moveTo(x, y);
        double currentAngle3 = currentAngle2 + previousX;
        double numPoints = Math.ceil(points2);
        int i = 0;
        while (i < numPoints) {
            float previousX2 = x;
            float previousY2 = y;
            x = (float) (radius * Math.cos(currentAngle3));
            y = (float) (radius * Math.sin(currentAngle3));
            if (roundedness2 != 0.0f) {
                points = points2;
                currentAngle = currentAngle3;
                anglePerPoint = previousX;
                float cp1Theta = (float) (Math.atan2(previousY2, previousX2) - 1.5707963267948966d);
                float cp1Dx = (float) Math.cos(cp1Theta);
                float cp1Dy = (float) Math.sin(cp1Theta);
                float cp2Theta = (float) (Math.atan2(y, x) - 1.5707963267948966d);
                float cp2Dx = (float) Math.cos(cp2Theta);
                float cp2Dy = (float) Math.sin(cp2Theta);
                float cp1x = radius * roundedness2 * POLYGON_MAGIC_NUMBER * cp1Dx;
                float cp1y = radius * roundedness2 * POLYGON_MAGIC_NUMBER * cp1Dy;
                float cp2x = radius * roundedness2 * POLYGON_MAGIC_NUMBER * cp2Dx;
                float cp2y = radius * roundedness2 * POLYGON_MAGIC_NUMBER * cp2Dy;
                if (i == numPoints - 1.0d) {
                    polystarContent = this;
                    polystarContent.lastSegmentPath.reset();
                    previousY = previousY2;
                    polystarContent.lastSegmentPath.moveTo(previousX2, previousY);
                    polystarContent.lastSegmentPath.cubicTo(previousX2 - cp1x, previousY - cp1y, x + cp2x, y + cp2y, x, y);
                    polystarContent.lastSegmentPathMeasure.setPath(polystarContent.lastSegmentPath, false);
                    roundedness = roundedness2;
                    polystarContent.lastSegmentPathMeasure.getPosTan(polystarContent.lastSegmentPathMeasure.getLength() * 0.9999f, polystarContent.lastSegmentPosition, null);
                    polystarContent.path.cubicTo(previousX2 - cp1x, previousY - cp1y, x + cp2x, y + cp2y, polystarContent.lastSegmentPosition[0], polystarContent.lastSegmentPosition[1]);
                } else {
                    polystarContent = this;
                    roundedness = roundedness2;
                    previousY = previousY2;
                    polystarContent.path.cubicTo(previousX2 - cp1x, previousY - cp1y, x + cp2x, y + cp2y, x, y);
                }
            } else {
                points = points2;
                currentAngle = currentAngle3;
                anglePerPoint = previousX;
                roundedness = roundedness2;
                if (i == numPoints - 1.0d) {
                    anglePerPoint2 = anglePerPoint;
                    i++;
                    previousX = anglePerPoint2;
                    points2 = points;
                    currentAngle3 = currentAngle;
                    roundedness2 = roundedness;
                } else {
                    polystarContent.path.lineTo(x, y);
                }
            }
            anglePerPoint2 = anglePerPoint;
            currentAngle += anglePerPoint2;
            i++;
            previousX = anglePerPoint2;
            points2 = points;
            currentAngle3 = currentAngle;
            roundedness2 = roundedness;
        }
        PointF position = polystarContent.positionAnimation.getValue();
        polystarContent.path.offset(position.x, position.y);
        polystarContent.path.close();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        if (property == LottieProperty.POLYSTAR_POINTS) {
            this.pointsAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POLYSTAR_ROTATION) {
            this.rotationAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POLYSTAR_INNER_RADIUS && this.innerRadiusAnimation != null) {
            this.innerRadiusAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POLYSTAR_OUTER_RADIUS) {
            this.outerRadiusAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS && this.innerRoundednessAnimation != null) {
            this.innerRoundednessAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
            this.outerRoundednessAnimation.setValueCallback(callback);
        }
    }
}
