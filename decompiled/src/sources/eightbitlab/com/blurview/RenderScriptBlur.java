package eightbitlab.com.blurview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

@Deprecated
/* loaded from: classes17.dex */
public class RenderScriptBlur implements BlurAlgorithm {
    private final ScriptIntrinsicBlur blurScript;
    private Allocation outAllocation;
    private final RenderScript renderScript;
    private final Paint paint = new Paint(2);
    private int lastBitmapWidth = -1;
    private int lastBitmapHeight = -1;

    public RenderScriptBlur(Context context) {
        this.renderScript = RenderScript.create(context);
        this.blurScript = ScriptIntrinsicBlur.create(this.renderScript, Element.U8_4(this.renderScript));
    }

    private boolean canReuseAllocation(Bitmap bitmap) {
        return bitmap.getHeight() == this.lastBitmapHeight && bitmap.getWidth() == this.lastBitmapWidth;
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public Bitmap blur(Bitmap bitmap, float blurRadius) {
        Allocation inAllocation = Allocation.createFromBitmap(this.renderScript, bitmap);
        if (!canReuseAllocation(bitmap)) {
            if (this.outAllocation != null) {
                this.outAllocation.destroy();
            }
            this.outAllocation = Allocation.createTyped(this.renderScript, inAllocation.getType());
            this.lastBitmapWidth = bitmap.getWidth();
            this.lastBitmapHeight = bitmap.getHeight();
        }
        this.blurScript.setRadius(blurRadius);
        this.blurScript.setInput(inAllocation);
        this.blurScript.forEach(this.outAllocation);
        this.outAllocation.copyTo(bitmap);
        inAllocation.destroy();
        return bitmap;
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public final void destroy() {
        this.blurScript.destroy();
        this.renderScript.destroy();
        if (this.outAllocation != null) {
            this.outAllocation.destroy();
        }
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public boolean canModifyBitmap() {
        return true;
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public Bitmap.Config getSupportedBitmapConfig() {
        return Bitmap.Config.ARGB_8888;
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public float scaleFactor() {
        return 6.0f;
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public void render(Canvas canvas, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.paint);
    }
}
