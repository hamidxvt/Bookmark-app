package com.github.ybq.android.spinkit;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.MultiplePulse;
import com.github.ybq.android.spinkit.style.MultiplePulseRing;
import com.github.ybq.android.spinkit.style.Pulse;
import com.github.ybq.android.spinkit.style.PulseRing;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.github.ybq.android.spinkit.style.Wave;

/* loaded from: classes16.dex */
public class SpriteFactory {
    public static Sprite create(Style style) {
        switch (style) {
            case ROTATING_PLANE:
                Sprite sprite = new RotatingPlane();
                return sprite;
            case DOUBLE_BOUNCE:
                Sprite sprite2 = new DoubleBounce();
                return sprite2;
            case WAVE:
                Sprite sprite3 = new Wave();
                return sprite3;
            case WANDERING_CUBES:
                Sprite sprite4 = new WanderingCubes();
                return sprite4;
            case PULSE:
                Sprite sprite5 = new Pulse();
                return sprite5;
            case CHASING_DOTS:
                Sprite sprite6 = new ChasingDots();
                return sprite6;
            case THREE_BOUNCE:
                Sprite sprite7 = new ThreeBounce();
                return sprite7;
            case CIRCLE:
                Sprite sprite8 = new Circle();
                return sprite8;
            case CUBE_GRID:
                Sprite sprite9 = new CubeGrid();
                return sprite9;
            case FADING_CIRCLE:
                Sprite sprite10 = new FadingCircle();
                return sprite10;
            case FOLDING_CUBE:
                Sprite sprite11 = new FoldingCube();
                return sprite11;
            case ROTATING_CIRCLE:
                Sprite sprite12 = new RotatingCircle();
                return sprite12;
            case MULTIPLE_PULSE:
                Sprite sprite13 = new MultiplePulse();
                return sprite13;
            case PULSE_RING:
                Sprite sprite14 = new PulseRing();
                return sprite14;
            case MULTIPLE_PULSE_RING:
                Sprite sprite15 = new MultiplePulseRing();
                return sprite15;
            default:
                return null;
        }
    }
}
