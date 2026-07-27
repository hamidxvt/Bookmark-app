package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class ActivityForgetPasswordBinding extends ViewDataBinding {
    public final ImageView backIcon;
    public final Button btnLogin;
    public final TextInputLayout emailLayout;
    public final TextInputEditText etEmail;
    public final TextView forgetEmail;
    public final LayoutLoadingBinding layoutProgressIndicator;

    @Bindable
    protected GenericListeners mListener;
    public final ConstraintLayout main;
    public final TextView subText;
    public final TextView tvWelcome;

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityForgetPasswordBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView backIcon, Button btnLogin, TextInputLayout emailLayout, TextInputEditText etEmail, TextView forgetEmail, LayoutLoadingBinding layoutProgressIndicator, ConstraintLayout main, TextView subText, TextView tvWelcome) {
        super(_bindingComponent, _root, _localFieldCount);
        this.backIcon = backIcon;
        this.btnLogin = btnLogin;
        this.emailLayout = emailLayout;
        this.etEmail = etEmail;
        this.forgetEmail = forgetEmail;
        this.layoutProgressIndicator = layoutProgressIndicator;
        this.main = main;
        this.subText = subText;
        this.tvWelcome = tvWelcome;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityForgetPasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityForgetPasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityForgetPasswordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_forget_password, root, attachToRoot, component);
    }

    public static ActivityForgetPasswordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityForgetPasswordBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityForgetPasswordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_forget_password, null, false, component);
    }

    public static ActivityForgetPasswordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityForgetPasswordBinding bind(View view, Object component) {
        return (ActivityForgetPasswordBinding) bind(component, view, R.layout.activity_forget_password);
    }
}
