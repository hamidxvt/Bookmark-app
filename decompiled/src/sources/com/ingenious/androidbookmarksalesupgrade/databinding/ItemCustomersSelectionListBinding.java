package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.SearchData;

/* loaded from: classes13.dex */
public abstract class ItemCustomersSelectionListBinding extends ViewDataBinding {
    public final CardView lastVisitLayout;

    @Bindable
    protected SearchData mItem;
    public final TextView tvCustomer;

    public abstract void setItem(SearchData searchData);

    protected ItemCustomersSelectionListBinding(Object _bindingComponent, View _root, int _localFieldCount, CardView lastVisitLayout, TextView tvCustomer) {
        super(_bindingComponent, _root, _localFieldCount);
        this.lastVisitLayout = lastVisitLayout;
        this.tvCustomer = tvCustomer;
    }

    public SearchData getItem() {
        return this.mItem;
    }

    public static ItemCustomersSelectionListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomersSelectionListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCustomersSelectionListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_customers_selection_list, root, attachToRoot, component);
    }

    public static ItemCustomersSelectionListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomersSelectionListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCustomersSelectionListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_customers_selection_list, null, false, component);
    }

    public static ItemCustomersSelectionListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomersSelectionListBinding bind(View view, Object component) {
        return (ItemCustomersSelectionListBinding) bind(component, view, R.layout.item_customers_selection_list);
    }
}
