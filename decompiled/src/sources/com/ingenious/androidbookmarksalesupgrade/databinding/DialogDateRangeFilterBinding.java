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
public abstract class DialogDateRangeFilterBinding extends ViewDataBinding {
    public final TextView last7Days;
    public final AppCompatButton priorityCancelBtn;
    public final ImageView priorityCrossIv;
    public final Button priorityDoneBtn;
    public final TextView today;
    public final TextView yesterday;

    protected DialogDateRangeFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView last7Days, AppCompatButton priorityCancelBtn, ImageView priorityCrossIv, Button priorityDoneBtn, TextView today, TextView yesterday) {
        super(_bindingComponent, _root, _localFieldCount);
        this.last7Days = last7Days;
        this.priorityCancelBtn = priorityCancelBtn;
        this.priorityCrossIv = priorityCrossIv;
        this.priorityDoneBtn = priorityDoneBtn;
        this.today = today;
        this.yesterday = yesterday;
    }

    public static DialogDateRangeFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDateRangeFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDateRangeFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_date_range_filter, root, attachToRoot, component);
    }

    public static DialogDateRangeFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDateRangeFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDateRangeFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_date_range_filter, null, false, component);
    }

    public static DialogDateRangeFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDateRangeFilterBinding bind(View view, Object component) {
        return (DialogDateRangeFilterBinding) bind(component, view, R.layout.dialog_date_range_filter);
    }
}
