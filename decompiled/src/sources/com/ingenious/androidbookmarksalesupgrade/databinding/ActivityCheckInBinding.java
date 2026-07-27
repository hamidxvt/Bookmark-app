package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class ActivityCheckInBinding extends ViewDataBinding {
    public final ImageView btnBack;
    public final FrameLayout fragmentContainer;
    public final LinearLayout header;
    public final ConstraintLayout main;
    public final TextView stepLabel;
    public final LinearProgressIndicator stepProgress;

    protected ActivityCheckInBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView btnBack, FrameLayout fragmentContainer, LinearLayout header, ConstraintLayout main, TextView stepLabel, LinearProgressIndicator stepProgress) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnBack = btnBack;
        this.fragmentContainer = fragmentContainer;
        this.header = header;
        this.main = main;
        this.stepLabel = stepLabel;
        this.stepProgress = stepProgress;
    }

    public static ActivityCheckInBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCheckInBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityCheckInBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_check_in, root, attachToRoot, component);
    }

    public static ActivityCheckInBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCheckInBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityCheckInBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_check_in, null, false, component);
    }

    public static ActivityCheckInBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCheckInBinding bind(View view, Object component) {
        return (ActivityCheckInBinding) bind(component, view, R.layout.activity_check_in);
    }
}
