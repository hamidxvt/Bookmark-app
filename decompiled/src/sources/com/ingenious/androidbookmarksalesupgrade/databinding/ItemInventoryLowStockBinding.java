package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;

/* loaded from: classes13.dex */
public abstract class ItemInventoryLowStockBinding extends ViewDataBinding {
    public final ImageView addToCart;

    @Bindable
    protected Products mItem;
    public final ShapeableImageView productImage;
    public final TextView productName;
    public final TextView productPrice;
    public final TextView stockTv;

    public abstract void setItem(Products products);

    protected ItemInventoryLowStockBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView addToCart, ShapeableImageView productImage, TextView productName, TextView productPrice, TextView stockTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.addToCart = addToCart;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.stockTv = stockTv;
    }

    public Products getItem() {
        return this.mItem;
    }

    public static ItemInventoryLowStockBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInventoryLowStockBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemInventoryLowStockBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_inventory_low_stock, root, attachToRoot, component);
    }

    public static ItemInventoryLowStockBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInventoryLowStockBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemInventoryLowStockBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_inventory_low_stock, null, false, component);
    }

    public static ItemInventoryLowStockBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInventoryLowStockBinding bind(View view, Object component) {
        return (ItemInventoryLowStockBinding) bind(component, view, R.layout.item_inventory_low_stock);
    }
}
