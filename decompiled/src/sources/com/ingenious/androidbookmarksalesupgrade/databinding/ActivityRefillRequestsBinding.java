package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class ActivityRefillRequestsBinding extends ViewDataBinding {
    public final AppCompatImageButton back;
    public final TabItem detailsTab;
    public final TabItem historyTab;

    @Bindable
    protected String mHeaderName;

    @Bindable
    protected GenericListeners mListener;
    public final LinearLayout main;
    public final TabItem samplesTab;
    public final TabLayout tabLayout;
    public final ViewPager2 viewPager;

    public abstract void setHeaderName(String str);

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityRefillRequestsBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatImageButton back, TabItem detailsTab, TabItem historyTab, LinearLayout main, TabItem samplesTab, TabLayout tabLayout, ViewPager2 viewPager) {
        super(_bindingComponent, _root, _localFieldCount);
        this.back = back;
        this.detailsTab = detailsTab;
        this.historyTab = historyTab;
        this.main = main;
        this.samplesTab = samplesTab;
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
    }

    public String getHeaderName() {
        return this.mHeaderName;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityRefillRequestsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRefillRequestsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRefillRequestsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_refill_requests, root, attachToRoot, component);
    }

    public static ActivityRefillRequestsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRefillRequestsBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRefillRequestsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_refill_requests, null, false, component);
    }

    public static ActivityRefillRequestsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRefillRequestsBinding bind(View view, Object component) {
        return (ActivityRefillRequestsBinding) bind(component, view, R.layout.activity_refill_requests);
    }
}
