package eightbitlab.com.blurview;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/* loaded from: classes17.dex */
public class NoOpController implements BlurController {
    @Override // eightbitlab.com.blurview.BlurController
    public boolean draw(Canvas canvas) {
        return true;
    }

    @Override // eightbitlab.com.blurview.BlurController
    public void updateBlurViewSize() {
    }

    @Override // eightbitlab.com.blurview.BlurController
    public void destroy() {
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setBlurRadius(float radius) {
        return this;
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setOverlayColor(int overlayColor) {
        return this;
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setFrameClearDrawable(Drawable windowBackground) {
        return this;
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setBlurEnabled(boolean enabled) {
        return this;
    }

    @Override // eightbitlab.com.blurview.BlurViewFacade
    public BlurViewFacade setBlurAutoUpdate(boolean enabled) {
        return this;
    }
}
