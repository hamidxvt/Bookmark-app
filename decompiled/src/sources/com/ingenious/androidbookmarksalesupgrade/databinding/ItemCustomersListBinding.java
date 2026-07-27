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
public abstract class ItemCustomersListBinding extends ViewDataBinding {

    @Bindable
    protected CustomersData mItem;

    public abstract void setItem(CustomersData customersData);

    protected ItemCustomersListBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CustomersData getItem() {
        return this.mItem;
    }

    public static ItemCustomersListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomersListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCustomersListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_customers_list, root, attachToRoot, component);
    }

    public static ItemCustomersListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomersListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCustomersListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_customers_list, null, false, component);
    }

    public static ItemCustomersListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomersListBinding bind(View view, Object component) {
        return (ItemCustomersListBinding) bind(component, view, R.layout.item_customers_list);
    }
}
