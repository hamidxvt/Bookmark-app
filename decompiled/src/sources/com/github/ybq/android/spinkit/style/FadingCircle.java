package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.CircleLayoutContainer;
import com.github.ybq.android.spinkit.sprite.CircleSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;

/* loaded from: classes16.dex */
public class FadingCircle extends CircleLayoutContainer {
    @Override // com.github.ybq.android.spinkit.sprite.SpriteContainer
    public Sprite[] onCreateChild() {
        Dot[] dots = new Dot[12];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot();
            dots[i].setAnimationDelay(i * 100);
        }
        return dots;
    }

    private class Dot extends CircleSprite {
        Dot() {
            setAlpha(0);
        }

        @Override // com.github.ybq.android.spinkit.sprite.CircleSprite, com.github.ybq.android.spinkit.sprite.Sprite
        public ValueAnimator onCreateAnimation() {
            float[] fractions = {0.0f, 0.39f, 0.4f, 1.0f};
            return new SpriteAnimatorBuilder(this).alpha(fractions, 0, 0, 255, 0).duration(1200L).easeInOut(fractions).build();
        }
    }
}
