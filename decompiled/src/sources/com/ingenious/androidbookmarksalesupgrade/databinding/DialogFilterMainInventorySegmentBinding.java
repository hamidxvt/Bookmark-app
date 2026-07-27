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
public abstract class DialogFilterMainInventorySegmentBinding extends ViewDataBinding {
    public final LinearLayout btnALevel;
    public final AppCompatButton btnClearAll;
    public final Button btnDone;
    public final LinearLayout btnEarlyYears;
    public final LinearLayout btnLower;
    public final LinearLayout btnOLevel;
    public final LinearLayout btnPrimary;
    public final ImageView ivClose;
    public final TextView tvFilterTitle;

    protected DialogFilterMainInventorySegmentBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout btnALevel, AppCompatButton btnClearAll, Button btnDone, LinearLayout btnEarlyYears, LinearLayout btnLower, LinearLayout btnOLevel, LinearLayout btnPrimary, ImageView ivClose, TextView tvFilterTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnALevel = btnALevel;
        this.btnClearAll = btnClearAll;
        this.btnDone = btnDone;
        this.btnEarlyYears = btnEarlyYears;
        this.btnLower = btnLower;
        this.btnOLevel = btnOLevel;
        this.btnPrimary = btnPrimary;
        this.ivClose = ivClose;
        this.tvFilterTitle = tvFilterTitle;
    }

    public static DialogFilterMainInventorySegmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventorySegmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogFilterMainInventorySegmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main_inventory_segment, root, attachToRoot, component);
    }

    public static DialogFilterMainInventorySegmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventorySegmentBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogFilterMainInventorySegmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main_inventory_segment, null, false, component);
    }

    public static DialogFilterMainInventorySegmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventorySegmentBinding bind(View view, Object component) {
        return (DialogFilterMainInventorySegmentBinding) bind(component, view, R.layout.dialog_filter_main_inventory_segment);
    }
}
