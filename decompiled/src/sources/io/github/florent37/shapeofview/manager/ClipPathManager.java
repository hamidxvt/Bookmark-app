package io.github.florent37.shapeofview.manager;

import android.graphics.Paint;
import android.graphics.Path;
import androidx.core.view.ViewCompat;

/* loaded from: classes17.dex */
public class ClipPathManager implements ClipManager {
    protected final Path path = new Path();
    private final Paint paint = new Paint(1);
    private ClipPathCreator createClipPath = null;

    public interface ClipPathCreator {
        Path createClipPath(int i, int i2);

        boolean requiresBitmap();
    }

    public ClipPathManager() {
        this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(1.0f);
    }

    @Override // io.github.florent37.shapeofview.manager.ClipManager
    public Paint getPaint() {
        return this.paint;
    }

    @Override // io.github.florent37.shapeofview.manager.ClipManager
    public boolean requiresBitmap() {
        return this.createClipPath != null && this.createClipPath.requiresBitmap();
    }

    protected final Path createClipPath(int width, int height) {
        if (this.createClipPath != null) {
            return this.createClipPath.createClipPath(width, height);
        }
        return null;
    }

    public void setClipPathCreator(ClipPathCreator createClipPath) {
        this.createClipPath = createClipPath;
    }

    @Override // io.github.florent37.shapeofview.manager.ClipManager
    public Path createMask(int width, int height) {
        return this.path;
    }

    @Override // io.github.florent37.shapeofview.manager.ClipManager
    public Path getShadowConvexPath() {
        return this.path;
    }

    @Override // io.github.florent37.shapeofview.manager.ClipManager
    public void setupClipLayout(int width, int height) {
        this.path.reset();
        Path clipPath = createClipPath(width, height);
        if (clipPath != null) {
            this.path.set(clipPath);
        }
    }
}
