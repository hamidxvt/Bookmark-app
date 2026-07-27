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
public abstract class BottomSheetCustomerDetailsBinding extends ViewDataBinding {
    public final ImageView crossBtn;
    public final TextView detail;
    public final TextView self;
    public final TextView test;
    public final TextView time;

    protected BottomSheetCustomerDetailsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView crossBtn, TextView detail, TextView self, TextView test, TextView time) {
        super(_bindingComponent, _root, _localFieldCount);
        this.crossBtn = crossBtn;
        this.detail = detail;
        this.self = self;
        this.test = test;
        this.time = time;
    }

    public static BottomSheetCustomerDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetCustomerDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BottomSheetCustomerDetailsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_customer_details, root, attachToRoot, component);
    }

    public static BottomSheetCustomerDetailsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetCustomerDetailsBinding inflate(LayoutInflater inflater, Object component) {
        return (BottomSheetCustomerDetailsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_customer_details, null, false, component);
    }

    public static BottomSheetCustomerDetailsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetCustomerDetailsBinding bind(View view, Object component) {
        return (BottomSheetCustomerDetailsBinding) bind(component, view, R.layout.bottom_sheet_customer_details);
    }
}
