package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class FragmentApprovedRefillRequestsBinding extends ViewDataBinding {
    public final RecyclerView refillApprovedRequestRv;

    protected FragmentApprovedRefillRequestsBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView refillApprovedRequestRv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.refillApprovedRequestRv = refillApprovedRequestRv;
    }

    public static FragmentApprovedRefillRequestsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentApprovedRefillRequestsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentApprovedRefillRequestsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_approved_refill_requests, root, attachToRoot, component);
    }

    public static FragmentApprovedRefillRequestsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentApprovedRefillRequestsBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentApprovedRefillRequestsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_approved_refill_requests, null, false, component);
    }

    public static FragmentApprovedRefillRequestsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentApprovedRefillRequestsBinding bind(View view, Object component) {
        return (FragmentApprovedRefillRequestsBinding) bind(component, view, R.layout.fragment_approved_refill_requests);
    }
}
