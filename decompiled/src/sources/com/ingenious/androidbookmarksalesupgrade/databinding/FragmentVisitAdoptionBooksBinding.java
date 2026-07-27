package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksResponse;

/* loaded from: classes13.dex */
public abstract class FragmentVisitAdoptionBooksBinding extends ViewDataBinding {
    public final Button btnContinue;

    @Bindable
    protected AdoptionBooksResponse mItem;
    public final RecyclerView segmentsRv;
    public final TextView selectSegmentTv;
    public final TextView totalResults;
    public final LinearLayout totalResultsLinear;

    public abstract void setItem(AdoptionBooksResponse adoptionBooksResponse);

    protected FragmentVisitAdoptionBooksBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnContinue, RecyclerView segmentsRv, TextView selectSegmentTv, TextView totalResults, LinearLayout totalResultsLinear) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnContinue = btnContinue;
        this.segmentsRv = segmentsRv;
        this.selectSegmentTv = selectSegmentTv;
        this.totalResults = totalResults;
        this.totalResultsLinear = totalResultsLinear;
    }

    public AdoptionBooksResponse getItem() {
        return this.mItem;
    }

    public static FragmentVisitAdoptionBooksBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionBooksBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitAdoptionBooksBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption_books, root, attachToRoot, component);
    }

    public static FragmentVisitAdoptionBooksBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionBooksBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitAdoptionBooksBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption_books, null, false, component);
    }

    public static FragmentVisitAdoptionBooksBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoptionBooksBinding bind(View view, Object component) {
        return (FragmentVisitAdoptionBooksBinding) bind(component, view, R.layout.fragment_visit_adoption_books);
    }
}
