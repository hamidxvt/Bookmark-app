package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class FragmentCheckIn3Binding extends ViewDataBinding {
    public final MaterialButton btnStartVisit;
    public final TextView currentTimeTv;

    protected FragmentCheckIn3Binding(Object _bindingComponent, View _root, int _localFieldCount, MaterialButton btnStartVisit, TextView currentTimeTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnStartVisit = btnStartVisit;
        this.currentTimeTv = currentTimeTv;
    }

    public static FragmentCheckIn3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCheckIn3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCheckIn3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_check_in3, root, attachToRoot, component);
    }

    public static FragmentCheckIn3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCheckIn3Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCheckIn3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_check_in3, null, false, component);
    }

    public static FragmentCheckIn3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCheckIn3Binding bind(View view, Object component) {
        return (FragmentCheckIn3Binding) bind(component, view, R.layout.fragment_check_in3);
    }
}
