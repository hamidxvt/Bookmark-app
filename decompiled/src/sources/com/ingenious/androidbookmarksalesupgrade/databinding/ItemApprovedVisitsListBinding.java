package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.ApprovedVisitsLists;

/* loaded from: classes13.dex */
public abstract class ItemApprovedVisitsListBinding extends ViewDataBinding {

    @Bindable
    protected ApprovedVisitsLists mItem;

    public abstract void setItem(ApprovedVisitsLists approvedVisitsLists);

    protected ItemApprovedVisitsListBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public ApprovedVisitsLists getItem() {
        return this.mItem;
    }

    public static ItemApprovedVisitsListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemApprovedVisitsListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemApprovedVisitsListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_approved_visits_list, root, attachToRoot, component);
    }

    public static ItemApprovedVisitsListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemApprovedVisitsListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemApprovedVisitsListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_approved_visits_list, null, false, component);
    }

    public static ItemApprovedVisitsListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemApprovedVisitsListBinding bind(View view, Object component) {
        return (ItemApprovedVisitsListBinding) bind(component, view, R.layout.item_approved_visits_list);
    }
}
