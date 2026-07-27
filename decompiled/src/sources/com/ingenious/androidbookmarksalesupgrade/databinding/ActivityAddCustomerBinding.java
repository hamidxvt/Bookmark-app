package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class ActivityAddCustomerBinding extends ViewDataBinding {
    public final MaterialButton btnAddCustomer;
    public final NestedScrollView content;
    public final EditText customerNameEt;
    public final TextView customerNameTv;
    public final EditText customerOwnerNameEt;
    public final TextView customerOwnerNameTv;
    public final TextView customerTypeBookshop;
    public final TextView customerTypeSchool;
    public final LayoutHeaderBinding layoutHeader;
    public final LayoutLoadingBinding layoutProgressIndicator;
    public final TextView locationEt;
    public final TextView locationTv;

    @Bindable
    protected String mHeaderName;

    @Bindable
    protected GenericListeners mListener;
    public final LinearLayout main;
    public final EditText phoneEt;
    public final TextView phoneTv;
    public final LinearLayout schoolTypeLayout;
    public final LinearLayout shopTypeLayout;

    public abstract void setHeaderName(String str);

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityAddCustomerBinding(Object _bindingComponent, View _root, int _localFieldCount, MaterialButton btnAddCustomer, NestedScrollView content, EditText customerNameEt, TextView customerNameTv, EditText customerOwnerNameEt, TextView customerOwnerNameTv, TextView customerTypeBookshop, TextView customerTypeSchool, LayoutHeaderBinding layoutHeader, LayoutLoadingBinding layoutProgressIndicator, TextView locationEt, TextView locationTv, LinearLayout main, EditText phoneEt, TextView phoneTv, LinearLayout schoolTypeLayout, LinearLayout shopTypeLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnAddCustomer = btnAddCustomer;
        this.content = content;
        this.customerNameEt = customerNameEt;
        this.customerNameTv = customerNameTv;
        this.customerOwnerNameEt = customerOwnerNameEt;
        this.customerOwnerNameTv = customerOwnerNameTv;
        this.customerTypeBookshop = customerTypeBookshop;
        this.customerTypeSchool = customerTypeSchool;
        this.layoutHeader = layoutHeader;
        this.layoutProgressIndicator = layoutProgressIndicator;
        this.locationEt = locationEt;
        this.locationTv = locationTv;
        this.main = main;
        this.phoneEt = phoneEt;
        this.phoneTv = phoneTv;
        this.schoolTypeLayout = schoolTypeLayout;
        this.shopTypeLayout = shopTypeLayout;
    }

    public String getHeaderName() {
        return this.mHeaderName;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityAddCustomerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddCustomerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAddCustomerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_add_customer, root, attachToRoot, component);
    }

    public static ActivityAddCustomerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddCustomerBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAddCustomerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_add_customer, null, false, component);
    }

    public static ActivityAddCustomerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddCustomerBinding bind(View view, Object component) {
        return (ActivityAddCustomerBinding) bind(component, view, R.layout.activity_add_customer);
    }
}
