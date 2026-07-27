package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooks;

/* loaded from: classes13.dex */
public abstract class ItemAdoptionQuantitiesBinding extends ViewDataBinding {

    @Bindable
    protected AdoptionBooks mItem;

    public abstract void setItem(AdoptionBooks adoptionBooks);

    protected ItemAdoptionQuantitiesBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public AdoptionBooks getItem() {
        return this.mItem;
    }

    public static ItemAdoptionQuantitiesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionQuantitiesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemAdoptionQuantitiesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_adoption_quantities, root, attachToRoot, component);
    }

    public static ItemAdoptionQuantitiesBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionQuantitiesBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemAdoptionQuantitiesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_adoption_quantities, null, false, component);
    }

    public static ItemAdoptionQuantitiesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionQuantitiesBinding bind(View view, Object component) {
        return (ItemAdoptionQuantitiesBinding) bind(component, view, R.layout.item_adoption_quantities);
    }
}
