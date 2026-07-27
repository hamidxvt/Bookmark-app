package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogPerformanceFilterMainBinding extends ViewDataBinding {
    public final LinearLayout btnActionType;
    public final LinearLayout btnDateRange;
    public final ImageView ivClose;
    public final TextView tvFilterTitle;

    protected DialogPerformanceFilterMainBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout btnActionType, LinearLayout btnDateRange, ImageView ivClose, TextView tvFilterTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnActionType = btnActionType;
        this.btnDateRange = btnDateRange;
        this.ivClose = ivClose;
        this.tvFilterTitle = tvFilterTitle;
    }

    public static DialogPerformanceFilterMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPerformanceFilterMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogPerformanceFilterMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_performance_filter_main, root, attachToRoot, component);
    }

    public static DialogPerformanceFilterMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPerformanceFilterMainBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogPerformanceFilterMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_performance_filter_main, null, false, component);
    }

    public static DialogPerformanceFilterMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPerformanceFilterMainBinding bind(View view, Object component) {
        return (DialogPerformanceFilterMainBinding) bind(component, view, R.layout.dialog_performance_filter_main);
    }
}
