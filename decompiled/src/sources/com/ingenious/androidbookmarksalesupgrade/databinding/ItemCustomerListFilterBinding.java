package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersData;

/* loaded from: classes13.dex */
public abstract class ItemCustomerListFilterBinding extends ViewDataBinding {

    @Bindable
    protected CustomersData mItem;

    public abstract void setItem(CustomersData customersData);

    protected ItemCustomerListFilterBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CustomersData getItem() {
        return this.mItem;
    }

    public static ItemCustomerListFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomerListFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCustomerListFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_customer_list_filter, root, attachToRoot, component);
    }

    public static ItemCustomerListFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomerListFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCustomerListFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_customer_list_filter, null, false, component);
    }

    public static ItemCustomerListFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomerListFilterBinding bind(View view, Object component) {
        return (ItemCustomerListFilterBinding) bind(component, view, R.layout.item_customer_list_filter);
    }
}
