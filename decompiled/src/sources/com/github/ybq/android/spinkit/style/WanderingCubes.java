package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

/* loaded from: classes16.dex */
public class WanderingCubes extends SpriteContainer {
    @Override // com.github.ybq.android.spinkit.sprite.SpriteContainer
    public Sprite[] onCreateChild() {
        return new Sprite[]{new Cube(0), new Cube(3)};
    }

    @Override // com.github.ybq.android.spinkit.sprite.SpriteContainer
    public void onChildCreated(Sprite... sprites) {
        super.onChildCreated(sprites);
    }

    @Override // com.github.ybq.android.spinkit.sprite.SpriteContainer, com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        Rect bounds2 = clipSquare(bounds);
        super.onBoundsChange(bounds2);
        for (int i = 0; i < getChildCount(); i++) {
            Sprite sprite = getChildAt(i);
            sprite.setDrawBounds(bounds2.left, bounds2.top, bounds2.left + (bounds2.width() / 4), bounds2.top + (bounds2.height() / 4));
        }
    }

    private class Cube extends RectSprite {
        int startFrame;

        public Cube(int startFrame) {
            this.startFrame = startFrame;
        }

        @Override // com.github.ybq.android.spinkit.sprite.RectSprite, com.github.ybq.android.spinkit.sprite.Sprite
        public ValueAnimator onCreateAnimation() {
            float[] fractions = {0.0f, 0.25f, 0.5f, 0.51f, 0.75f, 1.0f};
            SpriteAnimatorBuilder rotate = new SpriteAnimatorBuilder(this).rotate(fractions, 0, -90, -179, -180, -270, -360);
            Float valueOf = Float.valueOf(0.0f);
            Float valueOf2 = Float.valueOf(0.75f);
            SpriteAnimatorBuilder translateYPercentage = rotate.translateXPercentage(fractions, valueOf, valueOf2, valueOf2, valueOf2, valueOf, valueOf).translateYPercentage(fractions, valueOf, valueOf, valueOf2, valueOf2, valueOf2, valueOf);
            Float valueOf3 = Float.valueOf(1.0f);
            Float valueOf4 = Float.valueOf(0.5f);
            SpriteAnimatorBuilder builder = translateYPercentage.scale(fractions, valueOf3, valueOf4, valueOf3, valueOf3, valueOf4, valueOf3).duration(1800L).easeInOut(fractions);
            builder.startFrame(this.startFrame);
            return builder.build();
        }
    }
}
