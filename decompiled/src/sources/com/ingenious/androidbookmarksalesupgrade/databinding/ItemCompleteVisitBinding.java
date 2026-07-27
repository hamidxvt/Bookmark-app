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
public abstract class ItemCompleteVisitBinding extends ViewDataBinding {
    public final ImageView decreaseBtn;
    public final ImageView increaseBtn;
    public final TextView productGrade;
    public final ShapeableImageView productImage;
    public final TextView productName;
    public final TextView productPrice;
    public final TextView productQuantity;

    protected ItemCompleteVisitBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView decreaseBtn, ImageView increaseBtn, TextView productGrade, ShapeableImageView productImage, TextView productName, TextView productPrice, TextView productQuantity) {
        super(_bindingComponent, _root, _localFieldCount);
        this.decreaseBtn = decreaseBtn;
        this.increaseBtn = increaseBtn;
        this.productGrade = productGrade;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public static ItemCompleteVisitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCompleteVisitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCompleteVisitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_complete_visit, root, attachToRoot, component);
    }

    public static ItemCompleteVisitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCompleteVisitBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCompleteVisitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_complete_visit, null, false, component);
    }

    public static ItemCompleteVisitBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCompleteVisitBinding bind(View view, Object component) {
        return (ItemCompleteVisitBinding) bind(component, view, R.layout.item_complete_visit);
    }
}
