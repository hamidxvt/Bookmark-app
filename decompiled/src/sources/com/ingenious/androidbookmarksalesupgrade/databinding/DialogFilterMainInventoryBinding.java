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
public abstract class DialogFilterMainInventoryBinding extends ViewDataBinding {
    public final AppCompatButton btnClearAll;
    public final Button btnDone;
    public final LinearLayout btnGrade;
    public final LinearLayout btnSegment;
    public final LinearLayout btnSubject;
    public final ImageView ivClose;
    public final TextView tvFilterTitle;

    protected DialogFilterMainInventoryBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatButton btnClearAll, Button btnDone, LinearLayout btnGrade, LinearLayout btnSegment, LinearLayout btnSubject, ImageView ivClose, TextView tvFilterTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnClearAll = btnClearAll;
        this.btnDone = btnDone;
        this.btnGrade = btnGrade;
        this.btnSegment = btnSegment;
        this.btnSubject = btnSubject;
        this.ivClose = ivClose;
        this.tvFilterTitle = tvFilterTitle;
    }

    public static DialogFilterMainInventoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogFilterMainInventoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main_inventory, root, attachToRoot, component);
    }

    public static DialogFilterMainInventoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventoryBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogFilterMainInventoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main_inventory, null, false, component);
    }

    public static DialogFilterMainInventoryBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventoryBinding bind(View view, Object component) {
        return (DialogFilterMainInventoryBinding) bind(component, view, R.layout.dialog_filter_main_inventory);
    }
}
