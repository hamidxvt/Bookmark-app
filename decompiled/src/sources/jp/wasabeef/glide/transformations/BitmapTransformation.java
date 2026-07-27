package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import java.security.MessageDigest;

/* loaded from: classes17.dex */
public abstract class BitmapTransformation implements Transformation<Bitmap> {
    @Override // com.bumptech.glide.load.Key
    public abstract boolean equals(Object o);

    @Override // com.bumptech.glide.load.Key
    public abstract int hashCode();

    protected abstract Bitmap transform(Context context, BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight);

    @Override // com.bumptech.glide.load.Key
    public abstract void updateDiskCacheKey(MessageDigest messageDigest);

    @Override // com.bumptech.glide.load.Transformation
    public final Resource<Bitmap> transform(Context context, Resource<Bitmap> resource, int outWidth, int outHeight) {
        if (!Util.isValidDimensions(outWidth, outHeight)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + outWidth + " or height: " + outHeight + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
        Bitmap toTransform = resource.get();
        int targetWidth = outWidth == Integer.MIN_VALUE ? toTransform.getWidth() : outWidth;
        int targetHeight = outHeight == Integer.MIN_VALUE ? toTransform.getHeight() : outHeight;
        Bitmap transformed = transform(context.getApplicationContext(), bitmapPool, toTransform, targetWidth, targetHeight);
        if (toTransform.equals(transformed)) {
            return resource;
        }
        Resource<Bitmap> result = BitmapResource.obtain(transformed, bitmapPool);
        return result;
    }

    void setCanvasBitmapDensity(Bitmap toTransform, Bitmap canvasBitmap) {
        canvasBitmap.setDensity(toTransform.getDensity());
    }
}
