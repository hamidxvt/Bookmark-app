package com.github.ybq.android.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.animation.AnimationUtils;

/* loaded from: classes16.dex */
public abstract class SpriteContainer extends Sprite {
    private int color;
    private Sprite[] sprites = onCreateChild();

    public abstract Sprite[] onCreateChild();

    public SpriteContainer() {
        initCallBack();
        onChildCreated(this.sprites);
    }

    private void initCallBack() {
        if (this.sprites != null) {
            for (Sprite sprite : this.sprites) {
                sprite.setCallback(this);
            }
        }
    }

    public void onChildCreated(Sprite... sprites) {
    }

    public int getChildCount() {
        if (this.sprites == null) {
            return 0;
        }
        return this.sprites.length;
    }

    public Sprite getChildAt(int index) {
        if (this.sprites == null) {
            return null;
        }
        return this.sprites[index];
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite
    public void setColor(int color) {
        this.color = color;
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setColor(color);
        }
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite
    public int getColor() {
        return this.color;
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawChild(canvas);
    }

    public void drawChild(Canvas canvas) {
        if (this.sprites != null) {
            for (Sprite sprite : this.sprites) {
                int count = canvas.save();
                sprite.draw(canvas);
                canvas.restoreToCount(count);
            }
        }
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite
    protected void drawSelf(Canvas canvas) {
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        for (Sprite sprite : this.sprites) {
            sprite.setBounds(bounds);
        }
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Animatable
    public void start() {
        super.start();
        AnimationUtils.start(this.sprites);
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Animatable
    public void stop() {
        super.stop();
        AnimationUtils.stop(this.sprites);
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Animatable
    public boolean isRunning() {
        return AnimationUtils.isRunning(this.sprites) || super.isRunning();
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite
    public ValueAnimator onCreateAnimation() {
        return null;
    }
}
