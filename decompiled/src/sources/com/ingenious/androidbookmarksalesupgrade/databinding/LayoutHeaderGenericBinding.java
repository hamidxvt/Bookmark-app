package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class LayoutHeaderGenericBinding extends ViewDataBinding {

    @Bindable
    protected String mHeaderName;

    @Bindable
    protected GenericListeners mListener;

    public abstract void setHeaderName(String str);

    public abstract void setListener(GenericListeners genericListeners);

    protected LayoutHeaderGenericBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public String getHeaderName() {
        return this.mHeaderName;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static LayoutHeaderGenericBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutHeaderGenericBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutHeaderGenericBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_header_generic, root, attachToRoot, component);
    }

    public static LayoutHeaderGenericBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutHeaderGenericBinding inflate(LayoutInflater inflater, Object component) {
        return (LayoutHeaderGenericBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_header_generic, null, false, component);
    }

    public static LayoutHeaderGenericBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutHeaderGenericBinding bind(View view, Object component) {
        return (LayoutHeaderGenericBinding) bind(component, view, R.layout.layout_header_generic);
    }
}
