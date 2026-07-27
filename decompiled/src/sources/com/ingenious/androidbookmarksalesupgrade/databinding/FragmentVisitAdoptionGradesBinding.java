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
public abstract class FragmentVisitAdoptionGradesBinding extends ViewDataBinding {
    public final Button btnContinue;
    public final RecyclerView segmentsRv;
    public final TextView selectSegmentTv;

    protected FragmentVisitAdoptionGradesBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnContinue, RecyclerView segmentsRv, TextView selectSegmentTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnContinue = btnContinue;
        this.segmentsRv = segmentsRv;
        this.selectSegmentTv = selectSegmentTv;
    }

    public static FragmentVisitAdoptionGradesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionGradesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitAdoptionGradesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption_grades, root, attachToRoot, component);
    }

    public static FragmentVisitAdoptionGradesBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionGradesBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitAdoptionGradesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption_grades, null, false, component);
    }

    public static FragmentVisitAdoptionGradesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionGradesBinding bind(View view, Object component) {
        return (FragmentVisitAdoptionGradesBinding) bind(component, view, R.layout.fragment_visit_adoption_grades);
    }
}
