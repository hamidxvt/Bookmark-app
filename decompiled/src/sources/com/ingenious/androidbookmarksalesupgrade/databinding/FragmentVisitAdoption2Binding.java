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
public abstract class FragmentVisitAdoption2Binding extends ViewDataBinding {
    public final Button btnContinue;
    public final RecyclerView segmentsRv;
    public final TextView selectSegmentTv;

    protected FragmentVisitAdoption2Binding(Object _bindingComponent, View _root, int _localFieldCount, Button btnContinue, RecyclerView segmentsRv, TextView selectSegmentTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnContinue = btnContinue;
        this.segmentsRv = segmentsRv;
        this.selectSegmentTv = selectSegmentTv;
    }

    public static FragmentVisitAdoption2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoption2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitAdoption2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption2, root, attachToRoot, component);
    }

    public static FragmentVisitAdoption2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoption2Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitAdoption2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption2, null, false, component);
    }

    public static FragmentVisitAdoption2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoption2Binding bind(View view, Object component) {
        return (FragmentVisitAdoption2Binding) bind(component, view, R.layout.fragment_visit_adoption2);
    }
}
