package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogAddHomeBinding extends ViewDataBinding {
    public final LinearLayout addCustomer;
    public final LinearLayout addVisit;
    public final ImageView btnClose;

    protected DialogAddHomeBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout addCustomer, LinearLayout addVisit, ImageView btnClose) {
        super(_bindingComponent, _root, _localFieldCount);
        this.addCustomer = addCustomer;
        this.addVisit = addVisit;
        this.btnClose = btnClose;
    }

    public static DialogAddHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogAddHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_add_home, root, attachToRoot, component);
    }

    public static DialogAddHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddHomeBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogAddHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_add_home, null, false, component);
    }

    public static DialogAddHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddHomeBinding bind(View view, Object component) {
        return (DialogAddHomeBinding) bind(component, view, R.layout.dialog_add_home);
    }
}
