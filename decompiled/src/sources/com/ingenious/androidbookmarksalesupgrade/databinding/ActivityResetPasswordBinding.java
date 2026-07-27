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
public abstract class ActivityResetPasswordBinding extends ViewDataBinding {
    public final Button btnLogin;
    public final TextInputLayout emailLayout;
    public final TextInputEditText etPassword;
    public final TextInputEditText etPasswordConfirmation;
    public final TextView forgetEmail;
    public final LayoutLoadingBinding layoutProgressIndicator;

    @Bindable
    protected GenericListeners mListener;
    public final TextView password;
    public final TextView passwordConfirmation;
    public final TextInputLayout passwordConfirmationLayout;

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityResetPasswordBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnLogin, TextInputLayout emailLayout, TextInputEditText etPassword, TextInputEditText etPasswordConfirmation, TextView forgetEmail, LayoutLoadingBinding layoutProgressIndicator, TextView password, TextView passwordConfirmation, TextInputLayout passwordConfirmationLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnLogin = btnLogin;
        this.emailLayout = emailLayout;
        this.etPassword = etPassword;
        this.etPasswordConfirmation = etPasswordConfirmation;
        this.forgetEmail = forgetEmail;
        this.layoutProgressIndicator = layoutProgressIndicator;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.passwordConfirmationLayout = passwordConfirmationLayout;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityResetPasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityResetPasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityResetPasswordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_reset_password, root, attachToRoot, component);
    }

    public static ActivityResetPasswordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityResetPasswordBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityResetPasswordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_reset_password, null, false, component);
    }

    public static ActivityResetPasswordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityResetPasswordBinding bind(View view, Object component) {
        return (ActivityResetPasswordBinding) bind(component, view, R.layout.activity_reset_password);
    }
}
