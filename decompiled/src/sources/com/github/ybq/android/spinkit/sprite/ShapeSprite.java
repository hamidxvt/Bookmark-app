package com.github.ybq.android.spinkit.sprite;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;

/* loaded from: classes16.dex */
public abstract class ShapeSprite extends Sprite {
    private int mBaseColor;
    private Paint mPaint;
    private int mUseColor;

    public abstract void drawShape(Canvas canvas, Paint paint);

    public ShapeSprite() {
        setColor(-1);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mUseColor);
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite
    public void setColor(int color) {
        this.mBaseColor = color;
        updateUseColor();
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite
    public int getColor() {
        return this.mBaseColor;
    }

    public int getUseColor() {
        return this.mUseColor;
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        super.setAlpha(alpha);
        updateUseColor();
    }

    private void updateUseColor() {
        int alpha = getAlpha();
        int baseAlpha = this.mBaseColor >>> 24;
        int useAlpha = (baseAlpha * (alpha + (alpha >> 7))) >> 8;
        this.mUseColor = ((this.mBaseColor << 8) >>> 8) | (useAlpha << 24);
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // com.github.ybq.android.spinkit.sprite.Sprite
    protected final void drawSelf(Canvas canvas) {
        this.mPaint.setColor(this.mUseColor);
        drawShape(canvas, this.mPaint);
    }
}
