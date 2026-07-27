package io.github.florent37.shapeofview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import io.github.florent37.shapeofview.manager.ClipManager;
import io.github.florent37.shapeofview.manager.ClipPathManager;

/* loaded from: classes17.dex */
public class ShapeOfView extends FrameLayout {
    private Bitmap clipBitmap;
    private ClipManager clipManager;
    private final Paint clipPaint;
    private final Path clipPath;
    protected Drawable drawable;
    protected PorterDuffXfermode pdMode;
    final Path rectView;
    private boolean requiersShapeUpdate;

    public ShapeOfView(Context context) {
        super(context);
        this.clipPaint = new Paint(1);
        this.clipPath = new Path();
        this.pdMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.drawable = null;
        this.clipManager = new ClipPathManager();
        this.requiersShapeUpdate = true;
        this.rectView = new Path();
        init(context, null);
    }

    public ShapeOfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.clipPaint = new Paint(1);
        this.clipPath = new Path();
        this.pdMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.drawable = null;
        this.clipManager = new ClipPathManager();
        this.requiersShapeUpdate = true;
        this.rectView = new Path();
        init(context, attrs);
    }

    public ShapeOfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.clipPaint = new Paint(1);
        this.clipPath = new Path();
        this.pdMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.drawable = null;
        this.clipManager = new ClipPathManager();
        this.requiersShapeUpdate = true;
        this.rectView = new Path();
        init(context, attrs);
    }

    @Override // android.view.View
    public void setBackground(Drawable background) {
    }

    @Override // android.view.View
    public void setBackgroundResource(int resid) {
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
    }

    private void init(Context context, AttributeSet attrs) {
        int resourceId;
        this.clipPaint.setAntiAlias(true);
        setDrawingCacheEnabled(true);
        setWillNotDraw(false);
        this.clipPaint.setColor(-16776961);
        this.clipPaint.setStyle(Paint.Style.FILL);
        this.clipPaint.setStrokeWidth(1.0f);
        if (Build.VERSION.SDK_INT <= 27) {
            this.clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            setLayerType(1, this.clipPaint);
        } else {
            this.clipPaint.setXfermode(this.pdMode);
            setLayerType(1, null);
        }
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ShapeOfView);
            if (attributes.hasValue(R.styleable.ShapeOfView_shape_clip_drawable) && -1 != (resourceId = attributes.getResourceId(R.styleable.ShapeOfView_shape_clip_drawable, -1))) {
                setDrawable(resourceId);
            }
            attributes.recycle();
        }
    }

    protected float dpToPx(float dp) {
        return getContext().getResources().getDisplayMetrics().density * dp;
    }

    protected float pxToDp(float px) {
        return px / getContext().getResources().getDisplayMetrics().density;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            requiresShapeUpdate();
        }
    }

    private boolean requiresBitmap() {
        return isInEditMode() || (this.clipManager != null && this.clipManager.requiresBitmap()) || this.drawable != null;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        requiresShapeUpdate();
    }

    public void setDrawable(int redId) {
        setDrawable(AppCompatResources.getDrawable(getContext(), redId));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.requiersShapeUpdate) {
            calculateLayout(canvas.getWidth(), canvas.getHeight());
            this.requiersShapeUpdate = false;
        }
        if (requiresBitmap()) {
            this.clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawBitmap(this.clipBitmap, 0.0f, 0.0f, this.clipPaint);
        } else if (Build.VERSION.SDK_INT <= 27) {
            canvas.drawPath(this.clipPath, this.clipPaint);
        } else {
            canvas.drawPath(this.rectView, this.clipPaint);
        }
        if (Build.VERSION.SDK_INT <= 27) {
            setLayerType(2, null);
        }
    }

    private void calculateLayout(int width, int height) {
        this.rectView.reset();
        this.rectView.addRect(0.0f, 0.0f, getWidth() * 1.0f, getHeight() * 1.0f, Path.Direction.CW);
        if (this.clipManager != null && width > 0 && height > 0) {
            this.clipManager.setupClipLayout(width, height);
            this.clipPath.reset();
            this.clipPath.set(this.clipManager.createMask(width, height));
            if (requiresBitmap()) {
                if (this.clipBitmap != null) {
                    this.clipBitmap.recycle();
                }
                this.clipBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(this.clipBitmap);
                if (this.drawable != null) {
                    this.drawable.setBounds(0, 0, width, height);
                    this.drawable.draw(canvas);
                } else {
                    canvas.drawPath(this.clipPath, this.clipManager.getPaint());
                }
            }
            if (Build.VERSION.SDK_INT > 27) {
                this.rectView.op(this.clipPath, Path.Op.DIFFERENCE);
            }
            if (ViewCompat.getElevation(this) > 0.0f) {
                try {
                    setOutlineProvider(getOutlineProvider());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        postInvalidate();
    }

    @Override // android.view.View
    public ViewOutlineProvider getOutlineProvider() {
        return new ViewOutlineProvider() { // from class: io.github.florent37.shapeofview.ShapeOfView.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                Path shadowConvexPath;
                if (ShapeOfView.this.clipManager != null && !ShapeOfView.this.isInEditMode() && (shadowConvexPath = ShapeOfView.this.clipManager.getShadowConvexPath()) != null) {
                    try {
                        outline.setConvexPath(shadowConvexPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public void setClipPathCreator(ClipPathManager.ClipPathCreator createClipPath) {
        ((ClipPathManager) this.clipManager).setClipPathCreator(createClipPath);
        requiresShapeUpdate();
    }

    public void requiresShapeUpdate() {
        this.requiersShapeUpdate = true;
        postInvalidate();
    }
}
