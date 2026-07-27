package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class FragmentReceivedRefillRequestsBinding extends ViewDataBinding {
    public final RecyclerView refillReceivedRequestRv;

    protected FragmentReceivedRefillRequestsBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView refillReceivedRequestRv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.refillReceivedRequestRv = refillReceivedRequestRv;
    }

    public static FragmentReceivedRefillRequestsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentReceivedRefillRequestsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentReceivedRefillRequestsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_received_refill_requests, root, attachToRoot, component);
    }

    public static FragmentReceivedRefillRequestsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentReceivedRefillRequestsBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentReceivedRefillRequestsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_received_refill_requests, null, false, component);
    }

    public static FragmentReceivedRefillRequestsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentReceivedRefillRequestsBinding bind(View view, Object component) {
        return (FragmentReceivedRefillRequestsBinding) bind(component, view, R.layout.fragment_received_refill_requests);
    }
}
