package eightbitlab.com.blurview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import eightbitlab.com.blurview.SizeScaler;

/* loaded from: classes17.dex */
public final class PreDrawBlurController implements BlurController {
    public static final int TRANSPARENT = 0;
    private final BlurAlgorithm blurAlgorithm;
    final View blurView;
    private Drawable frameClearDrawable;
    private boolean initialized;
    private Bitmap internalBitmap;
    private BlurViewCanvas internalCanvas;
    private int overlayColor;
    private final ViewGroup rootView;
    private float blurRadius = 16.0f;
    private final int[] rootLocation = new int[2];
    private final int[] blurViewLocation = new int[2];
    private final ViewTreeObserver.OnPreDrawListener drawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: eightbitlab.com.blurview.PreDrawBlurController.1
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            PreDrawBlurController.this.updateBlur();
            return true;
        }
    };
    private boolean blurEnabled = true;

    public PreDrawBlurController(View blurView, ViewGroup rootView, int overlayColor, BlurAlgorithm algorithm) {
        this.rootView = rootView;
        this.blurView = blurView;
        this.overlayColor = overlayColor;
        this.blurAlgorithm = algorithm;
        if (algorithm instanceof RenderEffectBlur) {
            ((RenderEffectBlur) algorithm).setContext(blurView.getContext());
        }
        int measuredWidth = blurView.getMeasuredWidth();
        int measuredHeight = blurView.getMeasuredHeight();
        init(measuredWidth, measuredHeight);
    }

    void init(int measuredWidth, int measuredHeight) {
        setBlurAutoUpdate(true);
        SizeScaler sizeScaler = new SizeScaler(this.blurAlgorithm.scaleFactor());
        if (sizeScaler.isZeroSized(measuredWidth, measuredHeight)) {
            this.blurView.setWillNotDraw(true);
            return;
        }
        this.blurView.setWillNotDraw(false);
        SizeScaler.Size bitmapSize = sizeScaler.scale(measuredWidth, measuredHeight);
        this.internalBitmap = Bitmap.createBitmap(bitmapSize.width, bitmapSize.height, this.blurAlgorithm.getSupportedBitmapConfig());
        this.internalCanvas = new BlurViewCanvas(this.internalBitmap);
        this.initialized = true;
        updateBlur();
    }

    void updateBlur() {
        if (!this.blurEnabled || !this.initialized) {
            return;
        }
        if (this.frameClearDrawable == null) {
            this.internalBitmap.eraseColor(0);
        } else {
            this.frameClearDrawable.draw(this.internalCanvas);
        }
        this.internalCanvas.save();
        setupInternalCanvasMatrix();
        this.rootView.draw(this.internalCanvas);
        this.internalCanvas.restore();
        blurAndSave();
    }

    private void setupInternalCanvasMatrix() {
        this.rootView.getLocationOnScreen(this.rootLocation);
        this.blurView.getLocationOnScreen(this.blurViewLocation);
        int left = this.blurViewLocation[0] - this.rootLocation[0];
        int top = this.blurViewLocation[1] - this.rootLocation[1];
        float scaleFactorH = this.blurView.getHeight() / this.internalBitmap.getHeight();
        float scaleFactorW = this.blurView.getWidth() / this.internalBitmap.getWidth();
        float scaledLeftPosition = (-left) / scaleFactorW;
        float scaledTopPosition = (-top) / scaleFactorH;
        this.internalCanvas.translate(scaledLeftPosition, scaledTopPosition);
        this.internalCanvas.scale(1.0f / scaleFactorW, 1.0f / scaleFactorH);
    }

    @Override // eightbitlab.com.blurview.BlurController
    public boolean draw(Canvas canvas) {
        if (!this.blurEnabled || !this.initialized) {
            return true;
        }
        if (canvas instanceof BlurViewCanvas) {
            return false;
        }
        float scaleFactorH = this.blurView.getHeight() / this.internalBitmap.getHeight();
        float scaleFactorW = this.blurView.getWidth() / this.internalBitmap.getWidth();
        canvas.save();
        canvas.scale(scaleFactorW, scaleFactorH);
        this.blurAlgorithm.render(canvas, this.internalBitmap);
        canvas.restore();
        if (this.overlayColor != 0) {
            canvas.drawColor(this.overlayColor);
        }
        return true;
    }

    private void blurAndSave() {
        this.internalBitmap = this.blurAlgorithm.blur(this.internalBitmap, this.blurRadius);
        if (!this.blurAlgorithm.canModifyBitmap()) {
            this.internalCanvas.setBitmap(this.internalBitmap);
        }
    }

    @Override // eightbitlab.com.blurview.BlurController
    public void updateBlurViewSize() {
        int measuredWidth = this.blurView.getMeasuredWidth();
        int measuredHeight = this.blurView.getMeasuredHeight();
        init(measuredWidth, measuredHeight);
    }

    @Override // eightbitlab.com.blurview.BlurController
    public void destroy() {
        setBlurAutoUpdate(false);
        this.blurAlgorithm.destroy();
        this.initialized = false;
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setBlurRadius(float radius) {
        this.blurRadius = radius;
        return this;
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setFrameClearDrawable(Drawable frameClearDrawable) {
        this.frameClearDrawable = frameClearDrawable;
        return this;
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setBlurEnabled(boolean enabled) {
        this.blurEnabled = enabled;
        setBlurAutoUpdate(enabled);
        this.blurView.invalidate();
        return this;
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setBlurAutoUpdate(boolean enabled) {
        this.rootView.getViewTreeObserver().removeOnPreDrawListener(this.drawListener);
        this.blurView.getViewTreeObserver().removeOnPreDrawListener(this.drawListener);
        if (enabled) {
            this.rootView.getViewTreeObserver().addOnPreDrawListener(this.drawListener);
            if (this.rootView.getWindowId() != this.blurView.getWindowId()) {
                this.blurView.getViewTreeObserver().addOnPreDrawListener(this.drawListener);
            }
        }
        return this;
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setOverlayColor(int overlayColor) {
        if (this.overlayColor != overlayColor) {
            this.overlayColor = overlayColor;
            this.blurView.invalidate();
        }
        return this;
    }
}
