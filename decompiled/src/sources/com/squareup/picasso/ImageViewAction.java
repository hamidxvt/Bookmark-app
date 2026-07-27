package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/* loaded from: classes17.dex */
class ImageViewAction extends Action<ImageView> {
    Callback callback;

    ImageViewAction(Picasso picasso, ImageView imageView, Request data, int memoryPolicy, int networkPolicy, int errorResId, Drawable errorDrawable, String key, Object tag, Callback callback, boolean noFade) {
        super(picasso, imageView, data, memoryPolicy, networkPolicy, errorResId, errorDrawable, key, tag, noFade);
        this.callback = callback;
    }

    @Override // com.squareup.picasso.Action
    public void complete(Bitmap result, Picasso.LoadedFrom from) {
        if (result == null) {
            throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", this));
        }
        ImageView target = (ImageView) this.target.get();
        if (target == null) {
            return;
        }
        Context context = this.picasso.context;
        boolean indicatorsEnabled = this.picasso.indicatorsEnabled;
        PicassoDrawable.setBitmap(target, context, result, from, this.noFade, indicatorsEnabled);
        if (this.callback != null) {
            this.callback.onSuccess();
        }
    }

    @Override // com.squareup.picasso.Action
    public void error(Exception e) {
        ImageView target = (ImageView) this.target.get();
        if (target == null) {
            return;
        }
        Object drawable = target.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).stop();
        }
        if (this.errorResId != 0) {
            target.setImageResource(this.errorResId);
        } else if (this.errorDrawable != null) {
            target.setImageDrawable(this.errorDrawable);
        }
        if (this.callback != null) {
            this.callback.onError(e);
        }
    }

    @Override // com.squareup.picasso.Action
    void cancel() {
        super.cancel();
        if (this.callback != null) {
            this.callback = null;
        }
    }
}
