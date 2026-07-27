package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class FragmentVisitHistoryBinding extends ViewDataBinding {
    public final ImageView adoptionIvMain;
    public final LinearLayout adoptionLinear;
    public final RecyclerView approvedVisitsRv;
    public final TextView noAdoptionSubtv;
    public final TextView noAdoptionTv;

    protected FragmentVisitHistoryBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView adoptionIvMain, LinearLayout adoptionLinear, RecyclerView approvedVisitsRv, TextView noAdoptionSubtv, TextView noAdoptionTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.adoptionIvMain = adoptionIvMain;
        this.adoptionLinear = adoptionLinear;
        this.approvedVisitsRv = approvedVisitsRv;
        this.noAdoptionSubtv = noAdoptionSubtv;
        this.noAdoptionTv = noAdoptionTv;
    }

    public static FragmentVisitHistoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitHistoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitHistoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_history, root, attachToRoot, component);
    }

    public static FragmentVisitHistoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitHistoryBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitHistoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_history, null, false, component);
    }

    public static FragmentVisitHistoryBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitHistoryBinding bind(View view, Object component) {
        return (FragmentVisitHistoryBinding) bind(component, view, R.layout.fragment_visit_history);
    }
}
