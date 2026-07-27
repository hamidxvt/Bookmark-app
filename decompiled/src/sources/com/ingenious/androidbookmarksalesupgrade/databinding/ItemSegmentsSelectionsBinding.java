package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class ItemSegmentsSelectionsBinding extends ViewDataBinding {
    protected ItemSegmentsSelectionsBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public static ItemSegmentsSelectionsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSegmentsSelectionsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSegmentsSelectionsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_segments_selections, root, attachToRoot, component);
    }

    public static ItemSegmentsSelectionsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSegmentsSelectionsBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSegmentsSelectionsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_segments_selections, null, false, component);
    }

    public static ItemSegmentsSelectionsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSegmentsSelectionsBinding bind(View view, Object component) {
        return (ItemSegmentsSelectionsBinding) bind(component, view, R.layout.item_segments_selections);
    }
}
