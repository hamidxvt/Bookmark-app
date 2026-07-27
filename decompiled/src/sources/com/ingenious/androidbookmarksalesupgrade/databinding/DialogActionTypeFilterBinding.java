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
public abstract class DialogActionTypeFilterBinding extends ViewDataBinding {
    public final TextView all;
    public final TextView completeVisit;
    public final TextView inventory;
    public final TextView newCustomer;
    public final AppCompatButton priorityCancelBtn;
    public final ImageView priorityCrossIv;
    public final Button priorityDoneBtn;

    protected DialogActionTypeFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView all, TextView completeVisit, TextView inventory, TextView newCustomer, AppCompatButton priorityCancelBtn, ImageView priorityCrossIv, Button priorityDoneBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.all = all;
        this.completeVisit = completeVisit;
        this.inventory = inventory;
        this.newCustomer = newCustomer;
        this.priorityCancelBtn = priorityCancelBtn;
        this.priorityCrossIv = priorityCrossIv;
        this.priorityDoneBtn = priorityDoneBtn;
    }

    public static DialogActionTypeFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogActionTypeFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogActionTypeFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_action_type_filter, root, attachToRoot, component);
    }

    public static DialogActionTypeFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogActionTypeFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogActionTypeFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_action_type_filter, null, false, component);
    }

    public static DialogActionTypeFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogActionTypeFilterBinding bind(View view, Object component) {
        return (DialogActionTypeFilterBinding) bind(component, view, R.layout.dialog_action_type_filter);
    }
}
