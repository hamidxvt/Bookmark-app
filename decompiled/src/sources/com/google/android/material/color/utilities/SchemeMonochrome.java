package com.google.android.material.color.utilities;

import com.github.mikephil.charting.utils.Utils;

/* loaded from: classes16.dex */
public class SchemeMonochrome extends DynamicScheme {
    public SchemeMonochrome(Hct sourceColorHct, boolean isDark, double contrastLevel) {
        super(sourceColorHct, Variant.MONOCHROME, isDark, contrastLevel, TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), Utils.DOUBLE_EPSILON), TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), Utils.DOUBLE_EPSILON), TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), Utils.DOUBLE_EPSILON), TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), Utils.DOUBLE_EPSILON), TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), Utils.DOUBLE_EPSILON));
    }
}
