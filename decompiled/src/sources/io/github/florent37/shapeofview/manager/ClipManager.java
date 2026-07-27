package io.github.florent37.shapeofview.manager;

import android.graphics.Paint;
import android.graphics.Path;

/* loaded from: classes17.dex */
public interface ClipManager {
    Path createMask(int i, int i2);

    Paint getPaint();

    Path getShadowConvexPath();

    boolean requiresBitmap();

    void setupClipLayout(int i, int i2);
}
