package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogRequestToEditBinding extends ViewDataBinding {
    public final AppCompatButton cancelBtn;
    public final ProgressBar progressBar;
    public final Button startJobBtn;
    public final ImageView startJobCrossIv;

    protected DialogRequestToEditBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatButton cancelBtn, ProgressBar progressBar, Button startJobBtn, ImageView startJobCrossIv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cancelBtn = cancelBtn;
        this.progressBar = progressBar;
        this.startJobBtn = startJobBtn;
        this.startJobCrossIv = startJobCrossIv;
    }

    public static DialogRequestToEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRequestToEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogRequestToEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_request_to_edit, root, attachToRoot, component);
    }

    public static DialogRequestToEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRequestToEditBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogRequestToEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_request_to_edit, null, false, component);
    }

    public static DialogRequestToEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRequestToEditBinding bind(View view, Object component) {
        return (DialogRequestToEditBinding) bind(component, view, R.layout.dialog_request_to_edit);
    }
}
