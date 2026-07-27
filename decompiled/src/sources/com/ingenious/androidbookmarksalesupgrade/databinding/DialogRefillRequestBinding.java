package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogRefillRequestBinding extends ViewDataBinding {
    public final ImageView closeBtn;
    public final TextView date;
    public final TextView datetrackIdTv;
    public final TextView descriptionText;
    public final TextView titleText;
    public final TextView trackId;
    public final TextView trackIdTv;
    public final TextView viewRequestsButton;

    protected DialogRefillRequestBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView closeBtn, TextView date, TextView datetrackIdTv, TextView descriptionText, TextView titleText, TextView trackId, TextView trackIdTv, TextView viewRequestsButton) {
        super(_bindingComponent, _root, _localFieldCount);
        this.closeBtn = closeBtn;
        this.date = date;
        this.datetrackIdTv = datetrackIdTv;
        this.descriptionText = descriptionText;
        this.titleText = titleText;
        this.trackId = trackId;
        this.trackIdTv = trackIdTv;
        this.viewRequestsButton = viewRequestsButton;
    }

    public static DialogRefillRequestBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRefillRequestBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogRefillRequestBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_refill_request, root, attachToRoot, component);
    }

    public static DialogRefillRequestBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRefillRequestBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogRefillRequestBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_refill_request, null, false, component);
    }

    public static DialogRefillRequestBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRefillRequestBinding bind(View view, Object component) {
        return (DialogRefillRequestBinding) bind(component, view, R.layout.dialog_refill_request);
    }
}
