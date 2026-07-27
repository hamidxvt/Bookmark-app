package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class FragmentVisitAdoptionQuantityBinding extends ViewDataBinding {
    public final Button btnCreateAdoption;
    public final RelativeLayout layoutMain;
    public final ProgressBar progressBar;
    public final RecyclerView segmentsRv;
    public final TextView selectSegmentTv;
    public final TextView totalResults;
    public final LinearLayout totalResultsLinear;

    protected FragmentVisitAdoptionQuantityBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnCreateAdoption, RelativeLayout layoutMain, ProgressBar progressBar, RecyclerView segmentsRv, TextView selectSegmentTv, TextView totalResults, LinearLayout totalResultsLinear) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnCreateAdoption = btnCreateAdoption;
        this.layoutMain = layoutMain;
        this.progressBar = progressBar;
        this.segmentsRv = segmentsRv;
        this.selectSegmentTv = selectSegmentTv;
        this.totalResults = totalResults;
        this.totalResultsLinear = totalResultsLinear;
    }

    public static FragmentVisitAdoptionQuantityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionQuantityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitAdoptionQuantityBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption_quantity, root, attachToRoot, component);
    }

    public static FragmentVisitAdoptionQuantityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionQuantityBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitAdoptionQuantityBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption_quantity, null, false, component);
    }

    public static FragmentVisitAdoptionQuantityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionQuantityBinding bind(View view, Object component) {
        return (FragmentVisitAdoptionQuantityBinding) bind(component, view, R.layout.fragment_visit_adoption_quantity);
    }
}
