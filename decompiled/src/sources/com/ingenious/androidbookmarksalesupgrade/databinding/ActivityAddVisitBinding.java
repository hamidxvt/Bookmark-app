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
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.LastVisitCustomerData;

/* loaded from: classes13.dex */
public abstract class ActivityAddVisitBinding extends ViewDataBinding {
    public final MaterialButton btnAddCustomer;
    public final NestedScrollView content;
    public final RecyclerView customerListRv;
    public final EditText customerNameEt;
    public final TextView customerNameTv;
    public final TextView customerTypeBookshop;
    public final TextView customerTypeSchool;
    public final EditText dateEt;
    public final TextView dateTv;
    public final LayoutHeaderBinding layoutHeader;
    public final LayoutLoadingBinding layoutProgressIndicator;
    public final LinearLayout linearPriority;
    public final TextView locationTv;

    @Bindable
    protected String mHeaderName;

    @Bindable
    protected LastVisitCustomerData mItem;

    @Bindable
    protected GenericListeners mListener;
    public final LinearLayout main;
    public final TextView priorityHigh;
    public final TextView priorityLow;
    public final TextView priorityMedium;
    public final EditText purposeEt;
    public final LinearLayout schoolTypeLayout;
    public final LinearLayout shopTypeLayout;

    public abstract void setHeaderName(String str);

    public abstract void setItem(LastVisitCustomerData lastVisitCustomerData);

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityAddVisitBinding(Object _bindingComponent, View _root, int _localFieldCount, MaterialButton btnAddCustomer, NestedScrollView content, RecyclerView customerListRv, EditText customerNameEt, TextView customerNameTv, TextView customerTypeBookshop, TextView customerTypeSchool, EditText dateEt, TextView dateTv, LayoutHeaderBinding layoutHeader, LayoutLoadingBinding layoutProgressIndicator, LinearLayout linearPriority, TextView locationTv, LinearLayout main, TextView priorityHigh, TextView priorityLow, TextView priorityMedium, EditText purposeEt, LinearLayout schoolTypeLayout, LinearLayout shopTypeLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnAddCustomer = btnAddCustomer;
        this.content = content;
        this.customerListRv = customerListRv;
        this.customerNameEt = customerNameEt;
        this.customerNameTv = customerNameTv;
        this.customerTypeBookshop = customerTypeBookshop;
        this.customerTypeSchool = customerTypeSchool;
        this.dateEt = dateEt;
        this.dateTv = dateTv;
        this.layoutHeader = layoutHeader;
        this.layoutProgressIndicator = layoutProgressIndicator;
        this.linearPriority = linearPriority;
        this.locationTv = locationTv;
        this.main = main;
        this.priorityHigh = priorityHigh;
        this.priorityLow = priorityLow;
        this.priorityMedium = priorityMedium;
        this.purposeEt = purposeEt;
        this.schoolTypeLayout = schoolTypeLayout;
        this.shopTypeLayout = shopTypeLayout;
    }

    public String getHeaderName() {
        return this.mHeaderName;
    }

    public LastVisitCustomerData getItem() {
        return this.mItem;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityAddVisitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddVisitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAddVisitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_add_visit, root, attachToRoot, component);
    }

    public static ActivityAddVisitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddVisitBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAddVisitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_add_visit, null, false, component);
    }

    public static ActivityAddVisitBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddVisitBinding bind(View view, Object component) {
        return (ActivityAddVisitBinding) bind(component, view, R.layout.activity_add_visit);
    }
}
