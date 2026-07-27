package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;

/* loaded from: classes13.dex */
public abstract class ItemLowStockProductsBinding extends ViewDataBinding {
    public final ImageView addIv;
    public final TextView firstQuantity;

    @Bindable
    protected Products mItem;
    public final TextView productPrice;
    public final TextView productQuantity;
    public final TextView secondQuantity;
    public final ImageView subtractIv;
    public final TextView thirdQuantity;

    public abstract void setItem(Products products);

    protected ItemLowStockProductsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView addIv, TextView firstQuantity, TextView productPrice, TextView productQuantity, TextView secondQuantity, ImageView subtractIv, TextView thirdQuantity) {
        super(_bindingComponent, _root, _localFieldCount);
        this.addIv = addIv;
        this.firstQuantity = firstQuantity;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.secondQuantity = secondQuantity;
        this.subtractIv = subtractIv;
        this.thirdQuantity = thirdQuantity;
    }

    public Products getItem() {
        return this.mItem;
    }

    public static ItemLowStockProductsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLowStockProductsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemLowStockProductsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_low_stock_products, root, attachToRoot, component);
    }

    public static ItemLowStockProductsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLowStockProductsBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemLowStockProductsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_low_stock_products, null, false, component);
    }

    public static ItemLowStockProductsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLowStockProductsBinding bind(View view, Object component) {
        return (ItemLowStockProductsBinding) bind(component, view, R.layout.item_low_stock_products);
    }
}
