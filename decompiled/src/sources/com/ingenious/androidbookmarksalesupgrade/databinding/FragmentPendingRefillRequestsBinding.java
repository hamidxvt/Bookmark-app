package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class FragmentPendingRefillRequestsBinding extends ViewDataBinding {
    public final RecyclerView refillRequestRv;

    protected FragmentPendingRefillRequestsBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView refillRequestRv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.refillRequestRv = refillRequestRv;
    }

    public static FragmentPendingRefillRequestsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPendingRefillRequestsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentPendingRefillRequestsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_pending_refill_requests, root, attachToRoot, component);
    }

    public static FragmentPendingRefillRequestsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPendingRefillRequestsBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentPendingRefillRequestsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_pending_refill_requests, null, false, component);
    }

    public static FragmentPendingRefillRequestsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPendingRefillRequestsBinding bind(View view, Object component) {
        return (FragmentPendingRefillRequestsBinding) bind(component, view, R.layout.fragment_pending_refill_requests);
    }
}
