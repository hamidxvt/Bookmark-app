package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogByAreaFilterBinding extends ViewDataBinding {
    public final TextView all;
    public final TextView dha;
    public final TextView gulshan;
    public final TextView karimabad;
    public final AppCompatButton priorityCancelBtn;
    public final ImageView priorityCrossIv;
    public final Button priorityDoneBtn;

    protected DialogByAreaFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView all, TextView dha, TextView gulshan, TextView karimabad, AppCompatButton priorityCancelBtn, ImageView priorityCrossIv, Button priorityDoneBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.all = all;
        this.dha = dha;
        this.gulshan = gulshan;
        this.karimabad = karimabad;
        this.priorityCancelBtn = priorityCancelBtn;
        this.priorityCrossIv = priorityCrossIv;
        this.priorityDoneBtn = priorityDoneBtn;
    }

    public static DialogByAreaFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogByAreaFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogByAreaFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_by_area_filter, root, attachToRoot, component);
    }

    public static DialogByAreaFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogByAreaFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogByAreaFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_by_area_filter, null, false, component);
    }

    public static DialogByAreaFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogByAreaFilterBinding bind(View view, Object component) {
        return (DialogByAreaFilterBinding) bind(component, view, R.layout.dialog_by_area_filter);
    }
}
