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
public abstract class DialogJobStartBinding extends ViewDataBinding {
    public final AppCompatButton cancelBtn;
    public final Button startJobBtn;
    public final ImageView startJobCrossIv;

    protected DialogJobStartBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatButton cancelBtn, Button startJobBtn, ImageView startJobCrossIv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cancelBtn = cancelBtn;
        this.startJobBtn = startJobBtn;
        this.startJobCrossIv = startJobCrossIv;
    }

    public static DialogJobStartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogJobStartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogJobStartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_job_start, root, attachToRoot, component);
    }

    public static DialogJobStartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogJobStartBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogJobStartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_job_start, null, false, component);
    }

    public static DialogJobStartBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogJobStartBinding bind(View view, Object component) {
        return (DialogJobStartBinding) bind(component, view, R.layout.dialog_job_start);
    }
}
