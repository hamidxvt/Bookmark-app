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
public abstract class DialogLastVisitFilterBinding extends ViewDataBinding {
    public final ImageView areaCrossIv;
    public final TextView last30DaysTv;
    public final AppCompatButton lastVisitCancelBtn;
    public final Button lastVisitDoneBtn;
    public final TextView neverVisitedTv;
    public final TextView plus30DaysAgoTv;
    public final TextView thisWeekTv;
    public final TextView todayTv;

    protected DialogLastVisitFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView areaCrossIv, TextView last30DaysTv, AppCompatButton lastVisitCancelBtn, Button lastVisitDoneBtn, TextView neverVisitedTv, TextView plus30DaysAgoTv, TextView thisWeekTv, TextView todayTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.areaCrossIv = areaCrossIv;
        this.last30DaysTv = last30DaysTv;
        this.lastVisitCancelBtn = lastVisitCancelBtn;
        this.lastVisitDoneBtn = lastVisitDoneBtn;
        this.neverVisitedTv = neverVisitedTv;
        this.plus30DaysAgoTv = plus30DaysAgoTv;
        this.thisWeekTv = thisWeekTv;
        this.todayTv = todayTv;
    }

    public static DialogLastVisitFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLastVisitFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogLastVisitFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_last_visit_filter, root, attachToRoot, component);
    }

    public static DialogLastVisitFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLastVisitFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogLastVisitFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_last_visit_filter, null, false, component);
    }

    public static DialogLastVisitFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogLastVisitFilterBinding bind(View view, Object component) {
        return (DialogLastVisitFilterBinding) bind(component, view, R.layout.dialog_last_visit_filter);
    }
}
