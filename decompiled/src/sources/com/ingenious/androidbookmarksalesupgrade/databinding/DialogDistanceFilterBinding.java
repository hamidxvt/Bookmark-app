package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogDistanceFilterBinding extends ViewDataBinding {
    public final LinearLayout closestLinear;
    public final TextView closestTv;
    public final AppCompatButton distanceCancelBtn;
    public final ImageView distanceCrossIv;
    public final Button distanceDoneBtn;
    public final LinearLayout farthestLinear;
    public final TextView farthestTv;
    public final ImageView selectedCircle;
    public final ImageView selectedCircleFarthest;
    public final LinearLayout unselectedCircle;
    public final LinearLayout unselectedCircleFarthest;

    protected DialogDistanceFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout closestLinear, TextView closestTv, AppCompatButton distanceCancelBtn, ImageView distanceCrossIv, Button distanceDoneBtn, LinearLayout farthestLinear, TextView farthestTv, ImageView selectedCircle, ImageView selectedCircleFarthest, LinearLayout unselectedCircle, LinearLayout unselectedCircleFarthest) {
        super(_bindingComponent, _root, _localFieldCount);
        this.closestLinear = closestLinear;
        this.closestTv = closestTv;
        this.distanceCancelBtn = distanceCancelBtn;
        this.distanceCrossIv = distanceCrossIv;
        this.distanceDoneBtn = distanceDoneBtn;
        this.farthestLinear = farthestLinear;
        this.farthestTv = farthestTv;
        this.selectedCircle = selectedCircle;
        this.selectedCircleFarthest = selectedCircleFarthest;
        this.unselectedCircle = unselectedCircle;
        this.unselectedCircleFarthest = unselectedCircleFarthest;
    }

    public static DialogDistanceFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDistanceFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDistanceFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_distance_filter, root, attachToRoot, component);
    }

    public static DialogDistanceFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDistanceFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDistanceFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_distance_filter, null, false, component);
    }

    public static DialogDistanceFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDistanceFilterBinding bind(View view, Object component) {
        return (DialogDistanceFilterBinding) bind(component, view, R.layout.dialog_distance_filter);
    }
}
