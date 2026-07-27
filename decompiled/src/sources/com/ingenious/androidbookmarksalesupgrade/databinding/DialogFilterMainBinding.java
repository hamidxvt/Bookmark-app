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
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogFilterMainBinding extends ViewDataBinding {
    public final LinearLayout adoptionsSwitch;
    public final LinearLayout areaLinear;
    public final LinearLayout btnAddedBy;
    public final AppCompatButton btnClearAll;
    public final LinearLayout btnCustomerType;
    public final LinearLayout btnDistance;
    public final Button btnDone;
    public final LinearLayout btnPriority;
    public final ImageView ivClose;
    public final LinearLayout lastVisitLinear;
    public final SwitchMaterial switchBtn;
    public final TextView tvFilterTitle;

    protected DialogFilterMainBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout adoptionsSwitch, LinearLayout areaLinear, LinearLayout btnAddedBy, AppCompatButton btnClearAll, LinearLayout btnCustomerType, LinearLayout btnDistance, Button btnDone, LinearLayout btnPriority, ImageView ivClose, LinearLayout lastVisitLinear, SwitchMaterial switchBtn, TextView tvFilterTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.adoptionsSwitch = adoptionsSwitch;
        this.areaLinear = areaLinear;
        this.btnAddedBy = btnAddedBy;
        this.btnClearAll = btnClearAll;
        this.btnCustomerType = btnCustomerType;
        this.btnDistance = btnDistance;
        this.btnDone = btnDone;
        this.btnPriority = btnPriority;
        this.ivClose = ivClose;
        this.lastVisitLinear = lastVisitLinear;
        this.switchBtn = switchBtn;
        this.tvFilterTitle = tvFilterTitle;
    }

    public static DialogFilterMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogFilterMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main, root, attachToRoot, component);
    }

    public static DialogFilterMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogFilterMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main, null, false, component);
    }

    public static DialogFilterMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainBinding bind(View view, Object component) {
        return (DialogFilterMainBinding) bind(component, view, R.layout.dialog_filter_main);
    }
}
