package eightbitlab.com.blurview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* loaded from: classes17.dex */
public class BlurView extends FrameLayout {
    private static final String TAG = BlurView.class.getSimpleName();
    BlurController blurController;
    private int overlayColor;

    public BlurView(Context context) {
        super(context);
        this.blurController = new NoOpController();
        init(null, 0);
    }

    public BlurView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.blurController = new NoOpController();
        init(attrs, 0);
    }

    public BlurView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.blurController = new NoOpController();
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BlurView, defStyleAttr, 0);
        this.overlayColor = a.getColor(R.styleable.BlurView_blurOverlayColor, 0);
        a.recycle();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean shouldDraw = this.blurController.draw(canvas);
        if (shouldDraw) {
            super.draw(canvas);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.blurController.updateBlurViewSize();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.blurController.setBlurAutoUpdate(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isHardwareAccelerated()) {
            Log.e(TAG, "BlurView can't be used in not hardware-accelerated window!");
        } else {
            this.blurController.setBlurAutoUpdate(true);
        }
    }

    public BlurViewFacade setupWith(ViewGroup rootView, BlurAlgorithm algorithm) {
        this.blurController.destroy();
        BlurController blurController = new PreDrawBlurController(this, rootView, this.overlayColor, algorithm);
        this.blurController = blurController;
        return blurController;
    }

    public BlurViewFacade setupWith(ViewGroup rootView) {
        return setupWith(rootView, getBlurAlgorithm());
    }

    public BlurViewFacade setBlurRadius(float radius) {
        return this.blurController.setBlurRadius(radius);
    }

    public BlurViewFacade setOverlayColor(int overlayColor) {
        this.overlayColor = overlayColor;
        return this.blurController.setOverlayColor(overlayColor);
    }

    public BlurViewFacade setBlurAutoUpdate(boolean enabled) {
        return this.blurController.setBlurAutoUpdate(enabled);
    }

    public BlurViewFacade setBlurEnabled(boolean enabled) {
        return this.blurController.setBlurEnabled(enabled);
    }

    private BlurAlgorithm getBlurAlgorithm() {
        if (Build.VERSION.SDK_INT >= 31) {
            BlurAlgorithm algorithm = new RenderEffectBlur();
            return algorithm;
        }
        BlurAlgorithm algorithm2 = new RenderScriptBlur(getContext());
        return algorithm2;
    }
}
