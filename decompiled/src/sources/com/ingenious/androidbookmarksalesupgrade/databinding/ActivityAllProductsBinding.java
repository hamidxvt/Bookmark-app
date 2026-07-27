package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class ActivityAllProductsBinding extends ViewDataBinding {
    public final LinearLayout allBookText;
    public final TextView booksCount;
    public final EditText inventorySearchEt;
    public final ImageView ivBack;
    public final LayoutLoadingBinding layoutProgressIndicator;

    @Bindable
    protected GenericListeners mListener;
    public final RelativeLayout main;
    public final TextView productCount;
    public final RecyclerView productsRv;
    public final MaterialButton productsViewSelection;
    public final ImageView searchIcon;
    public final LinearLayout topBar;

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityAllProductsBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout allBookText, TextView booksCount, EditText inventorySearchEt, ImageView ivBack, LayoutLoadingBinding layoutProgressIndicator, RelativeLayout main, TextView productCount, RecyclerView productsRv, MaterialButton productsViewSelection, ImageView searchIcon, LinearLayout topBar) {
        super(_bindingComponent, _root, _localFieldCount);
        this.allBookText = allBookText;
        this.booksCount = booksCount;
        this.inventorySearchEt = inventorySearchEt;
        this.ivBack = ivBack;
        this.layoutProgressIndicator = layoutProgressIndicator;
        this.main = main;
        this.productCount = productCount;
        this.productsRv = productsRv;
        this.productsViewSelection = productsViewSelection;
        this.searchIcon = searchIcon;
        this.topBar = topBar;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityAllProductsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAllProductsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAllProductsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_all_products, root, attachToRoot, component);
    }

    public static ActivityAllProductsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAllProductsBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAllProductsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_all_products, null, false, component);
    }

    public static ActivityAllProductsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAllProductsBinding bind(View view, Object component) {
        return (ActivityAllProductsBinding) bind(component, view, R.layout.activity_all_products);
    }
}
