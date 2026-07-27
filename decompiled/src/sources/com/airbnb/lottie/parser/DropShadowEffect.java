package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;

/* loaded from: classes.dex */
public class DropShadowEffect {
    private final AnimatableColorValue color;
    private final AnimatableFloatValue direction;
    private final AnimatableFloatValue distance;
    private final AnimatableFloatValue opacity;
    private final AnimatableFloatValue radius;

    DropShadowEffect(AnimatableColorValue color, AnimatableFloatValue opacity, AnimatableFloatValue direction, AnimatableFloatValue distance, AnimatableFloatValue radius) {
        this.color = color;
        this.opacity = opacity;
        this.direction = direction;
        this.distance = distance;
        this.radius = radius;
    }

    public AnimatableColorValue getColor() {
        return this.color;
    }

    public AnimatableFloatValue getOpacity() {
        return this.opacity;
    }

    public AnimatableFloatValue getDirection() {
        return this.direction;
    }

    public AnimatableFloatValue getDistance() {
        return this.distance;
    }

    public AnimatableFloatValue getRadius() {
        return this.radius;
    }
}
