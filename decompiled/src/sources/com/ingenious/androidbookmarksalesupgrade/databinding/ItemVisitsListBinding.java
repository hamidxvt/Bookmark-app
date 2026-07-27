package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.model.response.TodayVisitsList;

/* loaded from: classes13.dex */
public abstract class ItemVisitsListBinding extends ViewDataBinding {
    public final ImageButton btnCall;
    public final Button btnCheckIn;
    public final ImageButton btnNavigate;
    public final TextView customerType;

    @Bindable
    protected TodayVisitsList mItem;

    @Bindable
    protected GenericAdapter.OnItemClickListener mListener;
    public final TextView priority;
    public final TextView tvDistance;
    public final TextView tvDuration;
    public final TextView tvSubtitle;
    public final TextView tvTitle;
    public final TextView visitType;

    public abstract void setItem(TodayVisitsList todayVisitsList);

    public abstract void setListener(GenericAdapter.OnItemClickListener onItemClickListener);

    protected ItemVisitsListBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageButton btnCall, Button btnCheckIn, ImageButton btnNavigate, TextView customerType, TextView priority, TextView tvDistance, TextView tvDuration, TextView tvSubtitle, TextView tvTitle, TextView visitType) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnCall = btnCall;
        this.btnCheckIn = btnCheckIn;
        this.btnNavigate = btnNavigate;
        this.customerType = customerType;
        this.priority = priority;
        this.tvDistance = tvDistance;
        this.tvDuration = tvDuration;
        this.tvSubtitle = tvSubtitle;
        this.tvTitle = tvTitle;
        this.visitType = visitType;
    }

    public GenericAdapter.OnItemClickListener getListener() {
        return this.mListener;
    }

    public TodayVisitsList getItem() {
        return this.mItem;
    }

    public static ItemVisitsListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVisitsListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemVisitsListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_visits_list, root, attachToRoot, component);
    }

    public static ItemVisitsListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVisitsListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemVisitsListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_visits_list, null, false, component);
    }

    public static ItemVisitsListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVisitsListBinding bind(View view, Object component) {
        return (ItemVisitsListBinding) bind(component, view, R.layout.item_visits_list);
    }
}
