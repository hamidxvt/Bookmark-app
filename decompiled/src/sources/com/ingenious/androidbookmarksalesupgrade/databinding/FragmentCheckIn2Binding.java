package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class FragmentCheckIn2Binding extends ViewDataBinding {
    public final MaterialButton btnContinue2;
    public final ImageView btnOpenCamera;
    public final TextView cameraOpen;
    public final ImageView imageSet;

    @Bindable
    protected GenericListeners mListener;
    public final RelativeLayout photoBox;
    public final LinearLayout tvLinear;

    public abstract void setListener(GenericListeners genericListeners);

    protected FragmentCheckIn2Binding(Object _bindingComponent, View _root, int _localFieldCount, MaterialButton btnContinue2, ImageView btnOpenCamera, TextView cameraOpen, ImageView imageSet, RelativeLayout photoBox, LinearLayout tvLinear) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnContinue2 = btnContinue2;
        this.btnOpenCamera = btnOpenCamera;
        this.cameraOpen = cameraOpen;
        this.imageSet = imageSet;
        this.photoBox = photoBox;
        this.tvLinear = tvLinear;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static FragmentCheckIn2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCheckIn2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCheckIn2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_check_in2, root, attachToRoot, component);
    }

    public static FragmentCheckIn2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCheckIn2Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCheckIn2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_check_in2, null, false, component);
    }

    public static FragmentCheckIn2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCheckIn2Binding bind(View view, Object component) {
        return (FragmentCheckIn2Binding) bind(component, view, R.layout.fragment_check_in2);
    }
}
