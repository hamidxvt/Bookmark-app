package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;
import com.google.android.material.card.MaterialCardViewHelper;

/* loaded from: classes16.dex */
public class CubeGrid extends SpriteContainer {
    @Override // com.github.ybq.android.spinkit.sprite.SpriteContainer
    public Sprite[] onCreateChild() {
        int[] delays = {200, MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, 400, 100, 200, MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, 0, 100, 200};
        GridItem[] gridItems = new GridItem[9];
        for (int i = 0; i < gridItems.length; i++) {
            gridItems[i] = new GridItem();
            gridItems[i].setAnimationDelay(delays[i]);
        }
        return gridItems;
    }

    @Override // com.github.ybq.android.spinkit.sprite.SpriteContainer, com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        Rect bounds2 = clipSquare(bounds);
        int width = (int) (bounds2.width() * 0.33f);
        int height = (int) (bounds2.height() * 0.33f);
        for (int i = 0; i < getChildCount(); i++) {
            int x = i % 3;
            int y = i / 3;
            int l = bounds2.left + (x * width);
            int t = bounds2.top + (y * height);
            Sprite sprite = getChildAt(i);
            sprite.setDrawBounds(l, t, l + width, t + height);
        }
    }

    private class GridItem extends RectSprite {
        private GridItem() {
        }

        @Override // com.github.ybq.android.spinkit.sprite.RectSprite, com.github.ybq.android.spinkit.sprite.Sprite
        public ValueAnimator onCreateAnimation() {
            float[] fractions = {0.0f, 0.35f, 0.7f, 1.0f};
            SpriteAnimatorBuilder spriteAnimatorBuilder = new SpriteAnimatorBuilder(this);
            Float valueOf = Float.valueOf(1.0f);
            return spriteAnimatorBuilder.scale(fractions, valueOf, Float.valueOf(0.0f), valueOf, valueOf).duration(1300L).easeInOut(fractions).build();
        }
    }
}
