package com.github.ybq.android.spinkit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.github.ybq.android.spinkit.sprite.Sprite;

/* loaded from: classes16.dex */
public class SpinKitView extends ProgressBar {
    private int mColor;
    private Sprite mSprite;
    private Style mStyle;

    public SpinKitView(Context context) {
        this(context, null);
    }

    public SpinKitView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.SpinKitViewStyle);
    }

    public SpinKitView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.SpinKitView);
    }

    public SpinKitView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpinKitView, defStyleAttr, defStyleRes);
        this.mStyle = Style.values()[a.getInt(R.styleable.SpinKitView_SpinKit_Style, 0)];
        this.mColor = a.getColor(R.styleable.SpinKitView_SpinKit_Color, -1);
        a.recycle();
        init();
        setIndeterminate(true);
    }

    private void init() {
        Sprite sprite = SpriteFactory.create(this.mStyle);
        sprite.setColor(this.mColor);
        setIndeterminateDrawable(sprite);
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable d) {
        if (!(d instanceof Sprite)) {
            throw new IllegalArgumentException("this d must be instanceof Sprite");
        }
        setIndeterminateDrawable((Sprite) d);
    }

    public void setIndeterminateDrawable(Sprite d) {
        super.setIndeterminateDrawable((Drawable) d);
        this.mSprite = d;
        if (this.mSprite.getColor() == 0) {
            this.mSprite.setColor(this.mColor);
        }
        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
        if (getVisibility() == 0) {
            this.mSprite.start();
        }
    }

    @Override // android.widget.ProgressBar
    public Sprite getIndeterminateDrawable() {
        return this.mSprite;
    }

    public void setColor(int color) {
        this.mColor = color;
        if (this.mSprite != null) {
            this.mSprite.setColor(color);
        }
        invalidate();
    }

    @Override // android.view.View
    public void unscheduleDrawable(Drawable who) {
        super.unscheduleDrawable(who);
        if (who instanceof Sprite) {
            ((Sprite) who).stop();
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && this.mSprite != null && getVisibility() == 0) {
            this.mSprite.start();
        }
    }

    @Override // android.view.View
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        if (screenState == 0 && this.mSprite != null) {
            this.mSprite.stop();
        }
    }
}
