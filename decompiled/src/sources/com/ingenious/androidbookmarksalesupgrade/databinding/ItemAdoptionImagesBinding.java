package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooks;

/* loaded from: classes13.dex */
public abstract class ItemAdoptionImagesBinding extends ViewDataBinding {

    @Bindable
    protected AdoptionBooks mItem;

    public abstract void setItem(AdoptionBooks adoptionBooks);

    protected ItemAdoptionImagesBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public AdoptionBooks getItem() {
        return this.mItem;
    }

    public static ItemAdoptionImagesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionImagesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemAdoptionImagesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_adoption_images, root, attachToRoot, component);
    }

    public static ItemAdoptionImagesBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionImagesBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemAdoptionImagesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_adoption_images, null, false, component);
    }

    public static ItemAdoptionImagesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionImagesBinding bind(View view, Object component) {
        return (ItemAdoptionImagesBinding) bind(component, view, R.layout.item_adoption_images);
    }
}
