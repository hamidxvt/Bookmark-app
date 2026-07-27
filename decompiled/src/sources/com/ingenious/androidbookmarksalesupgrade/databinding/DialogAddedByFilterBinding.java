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
public abstract class DialogAddedByFilterBinding extends ViewDataBinding {
    public final TextView addedByAdmin;
    public final TextView addedByAll;
    public final AppCompatButton addedByCancelBtn;
    public final ImageView addedByCrossIv;
    public final Button addedByDoneBtn;
    public final TextView addedByUser;

    protected DialogAddedByFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView addedByAdmin, TextView addedByAll, AppCompatButton addedByCancelBtn, ImageView addedByCrossIv, Button addedByDoneBtn, TextView addedByUser) {
        super(_bindingComponent, _root, _localFieldCount);
        this.addedByAdmin = addedByAdmin;
        this.addedByAll = addedByAll;
        this.addedByCancelBtn = addedByCancelBtn;
        this.addedByCrossIv = addedByCrossIv;
        this.addedByDoneBtn = addedByDoneBtn;
        this.addedByUser = addedByUser;
    }

    public static DialogAddedByFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddedByFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogAddedByFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_added_by_filter, root, attachToRoot, component);
    }

    public static DialogAddedByFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddedByFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogAddedByFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_added_by_filter, null, false, component);
    }

    public static DialogAddedByFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddedByFilterBinding bind(View view, Object component) {
        return (DialogAddedByFilterBinding) bind(component, view, R.layout.dialog_added_by_filter);
    }
}
