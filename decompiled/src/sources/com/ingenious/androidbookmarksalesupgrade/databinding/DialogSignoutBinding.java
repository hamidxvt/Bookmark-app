package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogSignoutBinding extends ViewDataBinding {
    public final AppCompatButton cancelBtn;
    public final Button startJobBtn;
    public final ImageView startJobCrossIv;

    protected DialogSignoutBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatButton cancelBtn, Button startJobBtn, ImageView startJobCrossIv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cancelBtn = cancelBtn;
        this.startJobBtn = startJobBtn;
        this.startJobCrossIv = startJobCrossIv;
    }

    public static DialogSignoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSignoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogSignoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_signout, root, attachToRoot, component);
    }

    public static DialogSignoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSignoutBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogSignoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_signout, null, false, component);
    }

    public static DialogSignoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSignoutBinding bind(View view, Object component) {
        return (DialogSignoutBinding) bind(component, view, R.layout.dialog_signout);
    }
}
