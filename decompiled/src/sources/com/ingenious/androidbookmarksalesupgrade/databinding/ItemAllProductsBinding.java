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
public abstract class ItemAllProductsBinding extends ViewDataBinding {
    public final ImageView addToCart;

    @Bindable
    protected Products mItem;
    public final ShapeableImageView productImage;
    public final TextView productName;
    public final TextView productPrice;
    public final TextView stockTv;

    public abstract void setItem(Products products);

    protected ItemAllProductsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView addToCart, ShapeableImageView productImage, TextView productName, TextView productPrice, TextView stockTv) {
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

    public static ItemAllProductsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAllProductsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemAllProductsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_all_products, root, attachToRoot, component);
    }

    public static ItemAllProductsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAllProductsBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemAllProductsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_all_products, null, false, component);
    }

    public static ItemAllProductsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAllProductsBinding bind(View view, Object component) {
        return (ItemAllProductsBinding) bind(component, view, R.layout.item_all_products);
    }
}
