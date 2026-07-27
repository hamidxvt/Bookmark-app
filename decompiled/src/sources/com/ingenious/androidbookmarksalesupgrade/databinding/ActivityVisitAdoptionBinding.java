package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class ActivityVisitAdoptionBinding extends ViewDataBinding {
    public final ImageView btnBack;
    public final FrameLayout fragmentContainer;
    public final LinearLayout header;
    public final LinearLayout main;
    public final TextView stepLabel;
    public final LinearProgressIndicator stepProgress;

    protected ActivityVisitAdoptionBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView btnBack, FrameLayout fragmentContainer, LinearLayout header, LinearLayout main, TextView stepLabel, LinearProgressIndicator stepProgress) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnBack = btnBack;
        this.fragmentContainer = fragmentContainer;
        this.header = header;
        this.main = main;
        this.stepLabel = stepLabel;
        this.stepProgress = stepProgress;
    }

    public static ActivityVisitAdoptionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVisitAdoptionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityVisitAdoptionBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_visit_adoption, root, attachToRoot, component);
    }

    public static ActivityVisitAdoptionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVisitAdoptionBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityVisitAdoptionBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_visit_adoption, null, false, component);
    }

    public static ActivityVisitAdoptionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVisitAdoptionBinding bind(View view, Object component) {
        return (ActivityVisitAdoptionBinding) bind(component, view, R.layout.activity_visit_adoption);
    }
}
