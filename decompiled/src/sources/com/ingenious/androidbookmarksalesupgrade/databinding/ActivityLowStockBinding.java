package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class ActivityLowStockBinding extends ViewDataBinding {
    public final ImageView backArrow;
    public final LinearLayout backLayout;
    public final RecyclerView lowStockRv;

    @Bindable
    protected GenericListeners mListener;
    public final RelativeLayout main;
    public final Button refillBtn;

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityLowStockBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView backArrow, LinearLayout backLayout, RecyclerView lowStockRv, RelativeLayout main, Button refillBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.backArrow = backArrow;
        this.backLayout = backLayout;
        this.lowStockRv = lowStockRv;
        this.main = main;
        this.refillBtn = refillBtn;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityLowStockBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLowStockBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLowStockBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_low_stock, root, attachToRoot, component);
    }

    public static ActivityLowStockBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLowStockBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLowStockBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_low_stock, null, false, component);
    }

    public static ActivityLowStockBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLowStockBinding bind(View view, Object component) {
        return (ActivityLowStockBinding) bind(component, view, R.layout.activity_low_stock);
    }
}
