package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class ItemAllProductsCart2Binding extends ViewDataBinding {
    public final ImageView deleteProductIv;
    public final TextView grade;
    public final ShapeableImageView productImage;
    public final TextView productName;
    public final TextView productPrice;
    public final TextView subject;

    protected ItemAllProductsCart2Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView deleteProductIv, TextView grade, ShapeableImageView productImage, TextView productName, TextView productPrice, TextView subject) {
        super(_bindingComponent, _root, _localFieldCount);
        this.deleteProductIv = deleteProductIv;
        this.grade = grade;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.subject = subject;
    }

    public static ItemAllProductsCart2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAllProductsCart2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemAllProductsCart2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_all_products_cart_2, root, attachToRoot, component);
    }

    public static ItemAllProductsCart2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAllProductsCart2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemAllProductsCart2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_all_products_cart_2, null, false, component);
    }

    public static ItemAllProductsCart2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAllProductsCart2Binding bind(View view, Object component) {
        return (ItemAllProductsCart2Binding) bind(component, view, R.layout.item_all_products_cart_2);
    }
}
