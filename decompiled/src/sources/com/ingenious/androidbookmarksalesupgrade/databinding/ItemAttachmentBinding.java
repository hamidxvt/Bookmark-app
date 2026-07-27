package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import io.github.florent37.shapeofview.shapes.RoundRectView;

/* loaded from: classes13.dex */
public abstract class ItemAttachmentBinding extends ViewDataBinding {
    public final AppCompatImageView deleteImageView;
    public final RoundRectView imgCircleProfile;
    public final ImageView imgProfile;

    @Bindable
    protected Uri mItem;

    public abstract void setItem(Uri uri);

    protected ItemAttachmentBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatImageView deleteImageView, RoundRectView imgCircleProfile, ImageView imgProfile) {
        super(_bindingComponent, _root, _localFieldCount);
        this.deleteImageView = deleteImageView;
        this.imgCircleProfile = imgCircleProfile;
        this.imgProfile = imgProfile;
    }

    public Uri getItem() {
        return this.mItem;
    }

    public static ItemAttachmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAttachmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemAttachmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_attachment, root, attachToRoot, component);
    }

    public static ItemAttachmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAttachmentBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemAttachmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_attachment, null, false, component);
    }

    public static ItemAttachmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAttachmentBinding bind(View view, Object component) {
        return (ItemAttachmentBinding) bind(component, view, R.layout.item_attachment);
    }
}
