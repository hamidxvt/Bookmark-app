package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.RefillByStatusData;

/* loaded from: classes13.dex */
public abstract class ItemRequestListBinding extends ViewDataBinding {

    @Bindable
    protected RefillByStatusData mItem;

    public abstract void setItem(RefillByStatusData refillByStatusData);

    protected ItemRequestListBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public RefillByStatusData getItem() {
        return this.mItem;
    }

    public static ItemRequestListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRequestListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemRequestListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_request_list, root, attachToRoot, component);
    }

    public static ItemRequestListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRequestListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemRequestListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_request_list, null, false, component);
    }

    public static ItemRequestListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRequestListBinding bind(View view, Object component) {
        return (ItemRequestListBinding) bind(component, view, R.layout.item_request_list);
    }
}
