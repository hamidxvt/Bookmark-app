package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.gms.maps.MapView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class ActivityLocationBinding extends ViewDataBinding {
    public final MaterialCardView address;
    public final Button continueButton;
    public final FloatingActionButton currentLocationButton;
    public final LayoutHeaderBinding layoutHeader;

    @Bindable
    protected String mHeaderName;

    @Bindable
    protected GenericListeners mListener;
    public final LinearLayout main;
    public final MapView mapView;

    public abstract void setHeaderName(String str);

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityLocationBinding(Object _bindingComponent, View _root, int _localFieldCount, MaterialCardView address, Button continueButton, FloatingActionButton currentLocationButton, LayoutHeaderBinding layoutHeader, LinearLayout main, MapView mapView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.address = address;
        this.continueButton = continueButton;
        this.currentLocationButton = currentLocationButton;
        this.layoutHeader = layoutHeader;
        this.main = main;
        this.mapView = mapView;
    }

    public String getHeaderName() {
        return this.mHeaderName;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityLocationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLocationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_location, root, attachToRoot, component);
    }

    public static ActivityLocationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocationBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLocationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_location, null, false, component);
    }

    public static ActivityLocationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocationBinding bind(View view, Object component) {
        return (ActivityLocationBinding) bind(component, view, R.layout.activity_location);
    }
}
