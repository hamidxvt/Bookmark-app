package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogCustomerTypeFilterBinding extends ViewDataBinding {
    public final AppCompatButton customerCancelBtn;
    public final Button customerDoneBtn;
    public final ImageView customerTypeCrossIv;
    public final TextView typeAllCustomer;
    public final TextView typeBookshop;
    public final TextView typeSchool;

    protected DialogCustomerTypeFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatButton customerCancelBtn, Button customerDoneBtn, ImageView customerTypeCrossIv, TextView typeAllCustomer, TextView typeBookshop, TextView typeSchool) {
        super(_bindingComponent, _root, _localFieldCount);
        this.customerCancelBtn = customerCancelBtn;
        this.customerDoneBtn = customerDoneBtn;
        this.customerTypeCrossIv = customerTypeCrossIv;
        this.typeAllCustomer = typeAllCustomer;
        this.typeBookshop = typeBookshop;
        this.typeSchool = typeSchool;
    }

    public static DialogCustomerTypeFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCustomerTypeFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogCustomerTypeFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_customer_type_filter, root, attachToRoot, component);
    }

    public static DialogCustomerTypeFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCustomerTypeFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogCustomerTypeFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_customer_type_filter, null, false, component);
    }

    public static DialogCustomerTypeFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCustomerTypeFilterBinding bind(View view, Object component) {
        return (DialogCustomerTypeFilterBinding) bind(component, view, R.layout.dialog_customer_type_filter);
    }
}
