package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProductsList;

/* loaded from: classes13.dex */
public abstract class ItemSampleListBinding extends ViewDataBinding {

    @Bindable
    protected ProductsList mItem;

    public abstract void setItem(ProductsList productsList);

    protected ItemSampleListBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public ProductsList getItem() {
        return this.mItem;
    }

    public static ItemSampleListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSampleListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSampleListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sample_list, root, attachToRoot, component);
    }

    public static ItemSampleListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSampleListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSampleListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sample_list, null, false, component);
    }

    public static ItemSampleListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSampleListBinding bind(View view, Object component) {
        return (ItemSampleListBinding) bind(component, view, R.layout.item_sample_list);
    }
}
