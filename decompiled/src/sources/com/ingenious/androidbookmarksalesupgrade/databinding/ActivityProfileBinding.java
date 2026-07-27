package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.chip.Chip;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileData;
import io.github.florent37.shapeofview.shapes.CircleView;

/* loaded from: classes13.dex */
public abstract class ActivityProfileBinding extends ViewDataBinding {
    public final ImageView back;
    public final CircleView imgCircleProfile;
    public final ImageView imgProfile;

    @Bindable
    protected ProfileData mItem;

    @Bindable
    protected GenericListeners mListener;
    public final NestedScrollView main;
    public final Chip requestToEdit;

    public abstract void setItem(ProfileData profileData);

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityProfileBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView back, CircleView imgCircleProfile, ImageView imgProfile, NestedScrollView main, Chip requestToEdit) {
        super(_bindingComponent, _root, _localFieldCount);
        this.back = back;
        this.imgCircleProfile = imgCircleProfile;
        this.imgProfile = imgProfile;
        this.main = main;
        this.requestToEdit = requestToEdit;
    }

    public ProfileData getItem() {
        return this.mItem;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityProfileBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityProfileBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityProfileBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_profile, root, attachToRoot, component);
    }

    public static ActivityProfileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityProfileBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityProfileBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_profile, null, false, component);
    }

    public static ActivityProfileBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityProfileBinding bind(View view, Object component) {
        return (ActivityProfileBinding) bind(component, view, R.layout.activity_profile);
    }
}
