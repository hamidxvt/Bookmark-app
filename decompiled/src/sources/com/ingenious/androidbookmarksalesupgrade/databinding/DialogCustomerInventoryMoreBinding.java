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
public abstract class DialogCustomerInventoryMoreBinding extends ViewDataBinding {
    public final LinearLayout btnDateRange;
    public final ImageView ivClose;
    public final TextView tvFilterTitle;

    protected DialogCustomerInventoryMoreBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout btnDateRange, ImageView ivClose, TextView tvFilterTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnDateRange = btnDateRange;
        this.ivClose = ivClose;
        this.tvFilterTitle = tvFilterTitle;
    }

    public static DialogCustomerInventoryMoreBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCustomerInventoryMoreBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogCustomerInventoryMoreBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_customer_inventory_more, root, attachToRoot, component);
    }

    public static DialogCustomerInventoryMoreBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCustomerInventoryMoreBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogCustomerInventoryMoreBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_customer_inventory_more, null, false, component);
    }

    public static DialogCustomerInventoryMoreBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCustomerInventoryMoreBinding bind(View view, Object component) {
        return (DialogCustomerInventoryMoreBinding) bind(component, view, R.layout.dialog_customer_inventory_more);
    }
}
