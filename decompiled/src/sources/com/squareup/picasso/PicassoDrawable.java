package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/* loaded from: classes17.dex */
final class PicassoDrawable extends BitmapDrawable {
    private static final Paint DEBUG_PAINT = new Paint();
    private static final float FADE_DURATION = 200.0f;
    int alpha;
    boolean animating;
    private final boolean debugging;
    private final float density;
    private final Picasso.LoadedFrom loadedFrom;
    Drawable placeholder;
    long startTimeMillis;

    /* JADX WARN: Multi-variable type inference failed */
    static void setBitmap(ImageView target, Context context, Bitmap bitmap, Picasso.LoadedFrom loadedFrom, boolean noFade, boolean debugging) {
        Drawable drawable = target.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).stop();
        }
        PicassoDrawable drawable2 = new PicassoDrawable(context, bitmap, drawable, loadedFrom, noFade, debugging);
        target.setImageDrawable(drawable2);
    }

    static void setPlaceholder(ImageView target, Drawable placeholderDrawable) {
        target.setImageDrawable(placeholderDrawable);
        if (target.getDrawable() instanceof Animatable) {
            ((Animatable) target.getDrawable()).start();
        }
    }

    PicassoDrawable(Context context, Bitmap bitmap, Drawable placeholder, Picasso.LoadedFrom loadedFrom, boolean noFade, boolean debugging) {
        super(context.getResources(), bitmap);
        this.alpha = 255;
        this.debugging = debugging;
        this.density = context.getResources().getDisplayMetrics().density;
        this.loadedFrom = loadedFrom;
        boolean fade = (loadedFrom == Picasso.LoadedFrom.MEMORY || noFade) ? false : true;
        if (fade) {
            this.placeholder = placeholder;
            this.animating = true;
            this.startTimeMillis = SystemClock.uptimeMillis();
        }
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!this.animating) {
            super.draw(canvas);
        } else {
            float normalized = (SystemClock.uptimeMillis() - this.startTimeMillis) / 200.0f;
            if (normalized >= 1.0f) {
                this.animating = false;
                this.placeholder = null;
                super.draw(canvas);
            } else {
                if (this.placeholder != null) {
                    this.placeholder.draw(canvas);
                }
                int partialAlpha = (int) (this.alpha * normalized);
                super.setAlpha(partialAlpha);
                super.draw(canvas);
                super.setAlpha(this.alpha);
            }
        }
        if (this.debugging) {
            drawDebugIndicator(canvas);
        }
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        if (this.placeholder != null) {
            this.placeholder.setAlpha(alpha);
        }
        super.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
        if (this.placeholder != null) {
            this.placeholder.setColorFilter(cf);
        }
        super.setColorFilter(cf);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        if (this.placeholder != null) {
            this.placeholder.setBounds(bounds);
        }
        super.onBoundsChange(bounds);
    }

    private void drawDebugIndicator(Canvas canvas) {
        DEBUG_PAINT.setColor(-1);
        Path path = getTrianglePath(0, 0, (int) (this.density * 16.0f));
        canvas.drawPath(path, DEBUG_PAINT);
        DEBUG_PAINT.setColor(this.loadedFrom.debugColor);
        Path path2 = getTrianglePath(0, 0, (int) (this.density * 15.0f));
        canvas.drawPath(path2, DEBUG_PAINT);
    }

    private static Path getTrianglePath(int x1, int y1, int width) {
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x1 + width, y1);
        path.lineTo(x1, y1 + width);
        return path;
    }
}
