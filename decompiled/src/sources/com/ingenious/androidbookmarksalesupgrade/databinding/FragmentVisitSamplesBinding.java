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
public abstract class FragmentVisitSamplesBinding extends ViewDataBinding {
    public final ImageView adoptionIvMain;
    public final LinearLayout adoptionLinear;
    public final TextView noAdoptionSubtv;
    public final TextView noAdoptionTv;
    public final RecyclerView sampleListRv;

    protected FragmentVisitSamplesBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView adoptionIvMain, LinearLayout adoptionLinear, TextView noAdoptionSubtv, TextView noAdoptionTv, RecyclerView sampleListRv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.adoptionIvMain = adoptionIvMain;
        this.adoptionLinear = adoptionLinear;
        this.noAdoptionSubtv = noAdoptionSubtv;
        this.noAdoptionTv = noAdoptionTv;
        this.sampleListRv = sampleListRv;
    }

    public static FragmentVisitSamplesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitSamplesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitSamplesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_samples, root, attachToRoot, component);
    }

    public static FragmentVisitSamplesBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitSamplesBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitSamplesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_samples, null, false, component);
    }

    public static FragmentVisitSamplesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitSamplesBinding bind(View view, Object component) {
        return (FragmentVisitSamplesBinding) bind(component, view, R.layout.fragment_visit_samples);
    }
}
