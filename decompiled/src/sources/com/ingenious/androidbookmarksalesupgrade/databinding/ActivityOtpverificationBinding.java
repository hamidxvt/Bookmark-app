package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class ActivityOtpverificationBinding extends ViewDataBinding {
    public final Button btnLogin;
    public final TextInputEditText etOtp;
    public final TextView forgetEmail;
    public final LayoutLoadingBinding layoutProgressIndicator;

    @Bindable
    protected GenericListeners mListener;
    public final TextInputLayout otpLayout;
    public final TextView tvWelcome;

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityOtpverificationBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnLogin, TextInputEditText etOtp, TextView forgetEmail, LayoutLoadingBinding layoutProgressIndicator, TextInputLayout otpLayout, TextView tvWelcome) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnLogin = btnLogin;
        this.etOtp = etOtp;
        this.forgetEmail = forgetEmail;
        this.layoutProgressIndicator = layoutProgressIndicator;
        this.otpLayout = otpLayout;
        this.tvWelcome = tvWelcome;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityOtpverificationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityOtpverificationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityOtpverificationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_otpverification, root, attachToRoot, component);
    }

    public static ActivityOtpverificationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityOtpverificationBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityOtpverificationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_otpverification, null, false, component);
    }

    public static ActivityOtpverificationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityOtpverificationBinding bind(View view, Object component) {
        return (ActivityOtpverificationBinding) bind(component, view, R.layout.activity_otpverification);
    }
}
