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
public abstract class FragmentVisitAdoptionBinding extends ViewDataBinding {
    public final ImageView adoptionIvMain;
    public final LinearLayout adoptionLinear;
    public final RecyclerView adoptionListRv;
    public final TextView btnCreateAdoption;
    public final TextView noAdoptionSubtv;
    public final TextView noAdoptionTv;

    protected FragmentVisitAdoptionBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView adoptionIvMain, LinearLayout adoptionLinear, RecyclerView adoptionListRv, TextView btnCreateAdoption, TextView noAdoptionSubtv, TextView noAdoptionTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.adoptionIvMain = adoptionIvMain;
        this.adoptionLinear = adoptionLinear;
        this.adoptionListRv = adoptionListRv;
        this.btnCreateAdoption = btnCreateAdoption;
        this.noAdoptionSubtv = noAdoptionSubtv;
        this.noAdoptionTv = noAdoptionTv;
    }

    public static FragmentVisitAdoptionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitAdoptionBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption, root, attachToRoot, component);
    }

    public static FragmentVisitAdoptionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitAdoptionBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption, null, false, component);
    }

    public static FragmentVisitAdoptionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionBinding bind(View view, Object component) {
        return (FragmentVisitAdoptionBinding) bind(component, view, R.layout.fragment_visit_adoption);
    }
}
