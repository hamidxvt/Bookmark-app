package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;

/* loaded from: classes13.dex */
public abstract class ItemStockListBinding extends ViewDataBinding {

    @Bindable
    protected Products mItem;

    public abstract void setItem(Products products);

    protected ItemStockListBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public Products getItem() {
        return this.mItem;
    }

    public static ItemStockListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemStockListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemStockListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_stock_list, root, attachToRoot, component);
    }

    public static ItemStockListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemStockListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemStockListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_stock_list, null, false, component);
    }

    public static ItemStockListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemStockListBinding bind(View view, Object component) {
        return (ItemStockListBinding) bind(component, view, R.layout.item_stock_list);
    }
}
