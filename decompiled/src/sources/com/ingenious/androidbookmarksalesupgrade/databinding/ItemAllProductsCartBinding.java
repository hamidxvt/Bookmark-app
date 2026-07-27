package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class ItemAllProductsCartBinding extends ViewDataBinding {
    public final ImageView btnMinus;
    public final ImageView btnPlus;
    public final LinearLayout deleteProductIv;
    public final TextView grade;
    public final ShapeableImageView productImage;
    public final TextView productName;
    public final TextView productPrice;
    public final TextView subject;
    public final TextView tvQuantity;

    protected ItemAllProductsCartBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView btnMinus, ImageView btnPlus, LinearLayout deleteProductIv, TextView grade, ShapeableImageView productImage, TextView productName, TextView productPrice, TextView subject, TextView tvQuantity) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnMinus = btnMinus;
        this.btnPlus = btnPlus;
        this.deleteProductIv = deleteProductIv;
        this.grade = grade;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.subject = subject;
        this.tvQuantity = tvQuantity;
    }

    public static ItemAllProductsCartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAllProductsCartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemAllProductsCartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_all_products_cart, root, attachToRoot, component);
    }

    public static ItemAllProductsCartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAllProductsCartBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemAllProductsCartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_all_products_cart, null, false, component);
    }

    public static ItemAllProductsCartBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAllProductsCartBinding bind(View view, Object component) {
        return (ItemAllProductsCartBinding) bind(component, view, R.layout.item_all_products_cart);
    }
}
