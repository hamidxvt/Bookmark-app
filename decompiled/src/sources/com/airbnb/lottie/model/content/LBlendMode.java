package com.airbnb.lottie.model.content;

import androidx.core.graphics.BlendModeCompat;

/* loaded from: classes.dex */
public enum LBlendMode {
    NORMAL,
    MULTIPLY,
    SCREEN,
    OVERLAY,
    DARKEN,
    LIGHTEN,
    COLOR_DODGE,
    COLOR_BURN,
    HARD_LIGHT,
    SOFT_LIGHT,
    DIFFERENCE,
    EXCLUSION,
    HUE,
    SATURATION,
    COLOR,
    LUMINOSITY,
    ADD,
    HARD_MIX;

    public BlendModeCompat toNativeBlendMode() {
        switch (this) {
            case NORMAL:
                return null;
            case SCREEN:
                return BlendModeCompat.SCREEN;
            case OVERLAY:
                return BlendModeCompat.OVERLAY;
            case DARKEN:
                return BlendModeCompat.DARKEN;
            case LIGHTEN:
                return BlendModeCompat.LIGHTEN;
            case ADD:
                return BlendModeCompat.PLUS;
            default:
                return null;
        }
    }
}
