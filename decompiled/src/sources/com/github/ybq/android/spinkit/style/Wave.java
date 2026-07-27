package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

/* loaded from: classes16.dex */
public class Wave extends SpriteContainer {
    @Override // com.github.ybq.android.spinkit.sprite.SpriteContainer
    public Sprite[] onCreateChild() {
        WaveItem[] waveItems = new WaveItem[5];
        for (int i = 0; i < waveItems.length; i++) {
            waveItems[i] = new WaveItem();
            waveItems[i].setAnimationDelay((i * 100) + 600);
        }
        return waveItems;
    }

    @Override // com.github.ybq.android.spinkit.sprite.SpriteContainer, com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        Rect bounds2 = clipSquare(bounds);
        int rw = bounds2.width() / getChildCount();
        int width = ((bounds2.width() / 5) * 3) / 5;
        for (int i = 0; i < getChildCount(); i++) {
            Sprite sprite = getChildAt(i);
            int l = bounds2.left + (i * rw) + (rw / 5);
            int r = l + width;
            sprite.setDrawBounds(l, bounds2.top, r, bounds2.bottom);
        }
    }

    private class WaveItem extends RectSprite {
        WaveItem() {
            setScaleY(0.4f);
        }

        @Override // com.github.ybq.android.spinkit.sprite.RectSprite, com.github.ybq.android.spinkit.sprite.Sprite
        public ValueAnimator onCreateAnimation() {
            float[] fractions = {0.0f, 0.2f, 0.4f, 1.0f};
            SpriteAnimatorBuilder spriteAnimatorBuilder = new SpriteAnimatorBuilder(this);
            Float valueOf = Float.valueOf(0.4f);
            return spriteAnimatorBuilder.scaleY(fractions, valueOf, Float.valueOf(1.0f), valueOf, valueOf).duration(1200L).easeInOut(fractions).build();
        }
    }
}
