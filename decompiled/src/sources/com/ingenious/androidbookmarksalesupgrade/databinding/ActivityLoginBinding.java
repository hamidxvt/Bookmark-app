package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;

/* loaded from: classes13.dex */
public abstract class ActivityLoginBinding extends ViewDataBinding {
    public final Button btnLogin;
    public final CheckBox cbRemember;
    public final EditText etEmployeeId;
    public final TextInputEditText etPassword;
    public final LayoutLoadingBinding layoutProgressIndicator;

    @Bindable
    protected GlobalResponse mItem;

    @Bindable
    protected GenericListeners mListener;
    public final LinearLayout main;
    public final TextInputLayout passwordLayout;
    public final TextView tvEmployeeId;
    public final TextView tvForgotPassword;
    public final TextView tvPassword;
    public final TextView tvSubtitle;
    public final TextView tvWelcome;

    public abstract void setItem(GlobalResponse globalResponse);

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityLoginBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnLogin, CheckBox cbRemember, EditText etEmployeeId, TextInputEditText etPassword, LayoutLoadingBinding layoutProgressIndicator, LinearLayout main, TextInputLayout passwordLayout, TextView tvEmployeeId, TextView tvForgotPassword, TextView tvPassword, TextView tvSubtitle, TextView tvWelcome) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnLogin = btnLogin;
        this.cbRemember = cbRemember;
        this.etEmployeeId = etEmployeeId;
        this.etPassword = etPassword;
        this.layoutProgressIndicator = layoutProgressIndicator;
        this.main = main;
        this.passwordLayout = passwordLayout;
        this.tvEmployeeId = tvEmployeeId;
        this.tvForgotPassword = tvForgotPassword;
        this.tvPassword = tvPassword;
        this.tvSubtitle = tvSubtitle;
        this.tvWelcome = tvWelcome;
    }

    public GlobalResponse getItem() {
        return this.mItem;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_login, root, attachToRoot, component);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_login, null, false, component);
    }

    public static ActivityLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding bind(View view, Object component) {
        return (ActivityLoginBinding) bind(component, view, R.layout.activity_login);
    }
}
