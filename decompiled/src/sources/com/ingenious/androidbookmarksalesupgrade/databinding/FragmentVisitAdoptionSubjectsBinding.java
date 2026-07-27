package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class FragmentVisitAdoptionSubjectsBinding extends ViewDataBinding {
    public final Button btnContinue;
    public final RecyclerView segmentsRv;
    public final TextView selectSegmentTv;

    protected FragmentVisitAdoptionSubjectsBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnContinue, RecyclerView segmentsRv, TextView selectSegmentTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnContinue = btnContinue;
        this.segmentsRv = segmentsRv;
        this.selectSegmentTv = selectSegmentTv;
    }

    public static FragmentVisitAdoptionSubjectsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionSubjectsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitAdoptionSubjectsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption_subjects, root, attachToRoot, component);
    }

    public static FragmentVisitAdoptionSubjectsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionSubjectsBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitAdoptionSubjectsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption_subjects, null, false, component);
    }

    public static FragmentVisitAdoptionSubjectsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionSubjectsBinding bind(View view, Object component) {
        return (FragmentVisitAdoptionSubjectsBinding) bind(component, view, R.layout.fragment_visit_adoption_subjects);
    }
}
