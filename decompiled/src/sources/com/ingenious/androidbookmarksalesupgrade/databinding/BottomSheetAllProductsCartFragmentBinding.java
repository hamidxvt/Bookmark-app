package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class BottomSheetAllProductsCartFragmentBinding extends ViewDataBinding {
    public final AppCompatButton addToVisitBtn;
    public final ProgressBar addingProductsBar;
    public final ImageView crossBtn;
    public final RecyclerView selectedProductsRv;

    protected BottomSheetAllProductsCartFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatButton addToVisitBtn, ProgressBar addingProductsBar, ImageView crossBtn, RecyclerView selectedProductsRv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.addToVisitBtn = addToVisitBtn;
        this.addingProductsBar = addingProductsBar;
        this.crossBtn = crossBtn;
        this.selectedProductsRv = selectedProductsRv;
    }

    public static BottomSheetAllProductsCartFragmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetAllProductsCartFragmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BottomSheetAllProductsCartFragmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_all_products_cart_fragment, root, attachToRoot, component);
    }

    public static BottomSheetAllProductsCartFragmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetAllProductsCartFragmentBinding inflate(LayoutInflater inflater, Object component) {
        return (BottomSheetAllProductsCartFragmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_all_products_cart_fragment, null, false, component);
    }

    public static BottomSheetAllProductsCartFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetAllProductsCartFragmentBinding bind(View view, Object component) {
        return (BottomSheetAllProductsCartFragmentBinding) bind(component, view, R.layout.bottom_sheet_all_products_cart_fragment);
    }
}
