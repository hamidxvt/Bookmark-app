package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.Summary;

/* loaded from: classes13.dex */
public abstract class FragmentCustomerBinding extends ViewDataBinding {
    public final LinearLayout allTypeLayout;
    public final TextView cancelTv;
    public final RecyclerView customerListRv;
    public final TextView customerTypeAll;
    public final TextView customerTypeBookshop;
    public final TextView customerTypeSchool;
    public final RecyclerView customersListFilterRv;
    public final ImageView filterIconIv;
    public final EditText inventorySearchEt;

    @Bindable
    protected Summary mItem;

    @Bindable
    protected GenericListeners mListener;
    public final ImageView performanceMenu;
    public final LinearLayout schoolTypeLayout;
    public final LinearLayout shopTypeLayout;

    public abstract void setItem(Summary summary);

    public abstract void setListener(GenericListeners genericListeners);

    protected FragmentCustomerBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout allTypeLayout, TextView cancelTv, RecyclerView customerListRv, TextView customerTypeAll, TextView customerTypeBookshop, TextView customerTypeSchool, RecyclerView customersListFilterRv, ImageView filterIconIv, EditText inventorySearchEt, ImageView performanceMenu, LinearLayout schoolTypeLayout, LinearLayout shopTypeLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.allTypeLayout = allTypeLayout;
        this.cancelTv = cancelTv;
        this.customerListRv = customerListRv;
        this.customerTypeAll = customerTypeAll;
        this.customerTypeBookshop = customerTypeBookshop;
        this.customerTypeSchool = customerTypeSchool;
        this.customersListFilterRv = customersListFilterRv;
        this.filterIconIv = filterIconIv;
        this.inventorySearchEt = inventorySearchEt;
        this.performanceMenu = performanceMenu;
        this.schoolTypeLayout = schoolTypeLayout;
        this.shopTypeLayout = shopTypeLayout;
    }

    public Summary getItem() {
        return this.mItem;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static FragmentCustomerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCustomerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCustomerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_customer, root, attachToRoot, component);
    }

    public static FragmentCustomerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCustomerBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCustomerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_customer, null, false, component);
    }

    public static FragmentCustomerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCustomerBinding bind(View view, Object component) {
        return (FragmentCustomerBinding) bind(component, view, R.layout.fragment_customer);
    }
}
