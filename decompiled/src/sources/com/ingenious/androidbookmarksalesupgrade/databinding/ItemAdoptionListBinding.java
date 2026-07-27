package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionsList;

/* loaded from: classes13.dex */
public abstract class ItemAdoptionListBinding extends ViewDataBinding {

    @Bindable
    protected AdoptionsList mItem;

    public abstract void setItem(AdoptionsList adoptionsList);

    protected ItemAdoptionListBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public AdoptionsList getItem() {
        return this.mItem;
    }

    public static ItemAdoptionListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemAdoptionListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_adoption_list, root, attachToRoot, component);
    }

    public static ItemAdoptionListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionListBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemAdoptionListBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_adoption_list, null, false, component);
    }

    public static ItemAdoptionListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionListBinding bind(View view, Object component) {
        return (ItemAdoptionListBinding) bind(component, view, R.layout.item_adoption_list);
    }
}
