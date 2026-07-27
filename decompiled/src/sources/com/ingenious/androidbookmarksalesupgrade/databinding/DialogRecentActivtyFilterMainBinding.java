package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogRecentActivtyFilterMainBinding extends ViewDataBinding {
    public final LinearLayout btnActionType;
    public final LinearLayout btnArea;
    public final AppCompatButton btnClearAll;
    public final LinearLayout btnDateRange;
    public final Button btnDone;
    public final ImageView ivClose;
    public final TextView tvFilterTitle;

    protected DialogRecentActivtyFilterMainBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout btnActionType, LinearLayout btnArea, AppCompatButton btnClearAll, LinearLayout btnDateRange, Button btnDone, ImageView ivClose, TextView tvFilterTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnActionType = btnActionType;
        this.btnArea = btnArea;
        this.btnClearAll = btnClearAll;
        this.btnDateRange = btnDateRange;
        this.btnDone = btnDone;
        this.ivClose = ivClose;
        this.tvFilterTitle = tvFilterTitle;
    }

    public static DialogRecentActivtyFilterMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRecentActivtyFilterMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogRecentActivtyFilterMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_recent_activty_filter_main, root, attachToRoot, component);
    }

    public static DialogRecentActivtyFilterMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRecentActivtyFilterMainBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogRecentActivtyFilterMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_recent_activty_filter_main, null, false, component);
    }

    public static DialogRecentActivtyFilterMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRecentActivtyFilterMainBinding bind(View view, Object component) {
        return (DialogRecentActivtyFilterMainBinding) bind(component, view, R.layout.dialog_recent_activty_filter_main);
    }
}
