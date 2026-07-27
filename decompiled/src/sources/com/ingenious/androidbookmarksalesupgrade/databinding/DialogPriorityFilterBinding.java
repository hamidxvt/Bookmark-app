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
public abstract class DialogPriorityFilterBinding extends ViewDataBinding {
    public final TextView highPriority;
    public final TextView lowPriority;
    public final TextView mediumPriority;
    public final AppCompatButton priorityCancelBtn;
    public final ImageView priorityCrossIv;
    public final Button priorityDoneBtn;

    protected DialogPriorityFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView highPriority, TextView lowPriority, TextView mediumPriority, AppCompatButton priorityCancelBtn, ImageView priorityCrossIv, Button priorityDoneBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.highPriority = highPriority;
        this.lowPriority = lowPriority;
        this.mediumPriority = mediumPriority;
        this.priorityCancelBtn = priorityCancelBtn;
        this.priorityCrossIv = priorityCrossIv;
        this.priorityDoneBtn = priorityDoneBtn;
    }

    public static DialogPriorityFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPriorityFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogPriorityFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_priority_filter, root, attachToRoot, component);
    }

    public static DialogPriorityFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPriorityFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogPriorityFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_priority_filter, null, false, component);
    }

    public static DialogPriorityFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPriorityFilterBinding bind(View view, Object component) {
        return (DialogPriorityFilterBinding) bind(component, view, R.layout.dialog_priority_filter);
    }
}
