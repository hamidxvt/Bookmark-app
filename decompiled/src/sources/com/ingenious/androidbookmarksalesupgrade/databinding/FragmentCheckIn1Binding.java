package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class FragmentCheckIn1Binding extends ViewDataBinding {
    public final MaterialButton btnContinue1;
    public final TextView customerType;
    public final LinearLayout locationChecking;
    public final LinearLayout locationNotVerified;
    public final TextView locationStatusTv;
    public final TextView locationTv;
    public final LinearLayout locationVerified;
    public final TextView priorityTv;
    public final TextView reasonTv;
    public final TextView tvLocationCheckingMessage;
    public final TextView tvLocationNotVerifiedMessage;
    public final TextView tvLocationVerifiedMessage;
    public final TextView tvTitle;
    public final TextView visitType;

    protected FragmentCheckIn1Binding(Object _bindingComponent, View _root, int _localFieldCount, MaterialButton btnContinue1, TextView customerType, LinearLayout locationChecking, LinearLayout locationNotVerified, TextView locationStatusTv, TextView locationTv, LinearLayout locationVerified, TextView priorityTv, TextView reasonTv, TextView tvLocationCheckingMessage, TextView tvLocationNotVerifiedMessage, TextView tvLocationVerifiedMessage, TextView tvTitle, TextView visitType) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnContinue1 = btnContinue1;
        this.customerType = customerType;
        this.locationChecking = locationChecking;
        this.locationNotVerified = locationNotVerified;
        this.locationStatusTv = locationStatusTv;
        this.locationTv = locationTv;
        this.locationVerified = locationVerified;
        this.priorityTv = priorityTv;
        this.reasonTv = reasonTv;
        this.tvLocationCheckingMessage = tvLocationCheckingMessage;
        this.tvLocationNotVerifiedMessage = tvLocationNotVerifiedMessage;
        this.tvLocationVerifiedMessage = tvLocationVerifiedMessage;
        this.tvTitle = tvTitle;
        this.visitType = visitType;
    }

    public static FragmentCheckIn1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCheckIn1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCheckIn1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_check_in1, root, attachToRoot, component);
    }

    public static FragmentCheckIn1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCheckIn1Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCheckIn1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_check_in1, null, false, component);
    }

    public static FragmentCheckIn1Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCheckIn1Binding bind(View view, Object component) {
        return (FragmentCheckIn1Binding) bind(component, view, R.layout.fragment_check_in1);
    }
}
