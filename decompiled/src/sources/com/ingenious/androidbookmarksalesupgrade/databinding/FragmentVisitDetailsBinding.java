package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.chip.Chip;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsResponse;

/* loaded from: classes13.dex */
public abstract class FragmentVisitDetailsBinding extends ViewDataBinding {

    @Bindable
    protected VisitDetailsResponse mItem;
    public final Chip requestToEdit;

    public abstract void setItem(VisitDetailsResponse visitDetailsResponse);

    protected FragmentVisitDetailsBinding(Object _bindingComponent, View _root, int _localFieldCount, Chip requestToEdit) {
        super(_bindingComponent, _root, _localFieldCount);
        this.requestToEdit = requestToEdit;
    }

    public VisitDetailsResponse getItem() {
        return this.mItem;
    }

    public static FragmentVisitDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitDetailsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_details, root, attachToRoot, component);
    }

    public static FragmentVisitDetailsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitDetailsBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitDetailsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_details, null, false, component);
    }

    public static FragmentVisitDetailsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitDetailsBinding bind(View view, Object component) {
        return (FragmentVisitDetailsBinding) bind(component, view, R.layout.fragment_visit_details);
    }
}
