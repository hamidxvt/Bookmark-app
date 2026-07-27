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
import com.ingenious.androidbookmarksalesupgrade.model.response.PastVisitsList;

/* loaded from: classes13.dex */
public abstract class ItemPastVisitsListBinding extends ViewDataBinding {
    public final ImageButton btnCall;
    public final Button btnCheckIn;
    public final ImageButton btnNavigate;
    public final TextView customerType;

    @Bindable
    protected PastVisitsList mItem;

    @Bindable
    protected GenericAdapter.OnItemClickListener mListener;
    public final TextView priority;
    public final TextView tvDistance;
    public final TextView tvDuration;
    public final TextView tvSubtitle;
    public final TextView tvTitle;
    public final TextView visitType;

    public abstract void setItem(PastVisitsList pastVisitsList);

    public abstract void setListener(GenericAdapter.OnItemClickListener onItemClickListener);

    protected ItemPastVisitsListBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageButton btnCall, Button btnCheckIn, ImageButton btnNavigate, TextView customerType, TextView priority, TextView tvDistance, TextView tvDuration, TextView tvSubtitle, TextView tvTitle, TextView visitType) {
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

    public PastVisitsList getItem() {
        return this.mItem;
    }

    public static ItemPastVisitsListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPastVisitsListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemPastVisitsListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_past_visits_list, root, attachToRoot, component);
    }

    public static ItemPastVisitsListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPastVisitsListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemPastVisitsListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_past_visits_list, null, false, component);
    }

    public static ItemPastVisitsListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPastVisitsListBinding bind(View view, Object component) {
        return (ItemPastVisitsListBinding) bind(component, view, R.layout.item_past_visits_list);
    }
}
