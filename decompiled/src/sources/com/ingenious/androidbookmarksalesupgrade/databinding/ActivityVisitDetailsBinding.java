package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetails;

/* loaded from: classes13.dex */
public abstract class ActivityVisitDetailsBinding extends ViewDataBinding {
    public final TabItem adoptionTab;
    public final ImageView backArrow;
    public final ImageView btnCall;
    public final ImageView btnNavigate;
    public final MaterialButton checkInBtn;
    public final LinearLayout customerLinearType;
    public final TextView customerType;
    public final TabItem detailsTab;
    public final TabItem historyTab;

    @Bindable
    protected VisitDetails mItem;

    @Bindable
    protected GenericListeners mListener;
    public final LinearLayout main;
    public final LayoutLoadingBinding progressIndicator;
    public final TabItem samplesTab;
    public final TabLayout tabLayout;
    public final TextView tvDistance;
    public final TextView tvDuration;
    public final ViewPager2 viewPager;
    public final ImageView visitCustomerTypeIv;

    public abstract void setItem(VisitDetails visitDetails);

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityVisitDetailsBinding(Object _bindingComponent, View _root, int _localFieldCount, TabItem adoptionTab, ImageView backArrow, ImageView btnCall, ImageView btnNavigate, MaterialButton checkInBtn, LinearLayout customerLinearType, TextView customerType, TabItem detailsTab, TabItem historyTab, LinearLayout main, LayoutLoadingBinding progressIndicator, TabItem samplesTab, TabLayout tabLayout, TextView tvDistance, TextView tvDuration, ViewPager2 viewPager, ImageView visitCustomerTypeIv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.adoptionTab = adoptionTab;
        this.backArrow = backArrow;
        this.btnCall = btnCall;
        this.btnNavigate = btnNavigate;
        this.checkInBtn = checkInBtn;
        this.customerLinearType = customerLinearType;
        this.customerType = customerType;
        this.detailsTab = detailsTab;
        this.historyTab = historyTab;
        this.main = main;
        this.progressIndicator = progressIndicator;
        this.samplesTab = samplesTab;
        this.tabLayout = tabLayout;
        this.tvDistance = tvDistance;
        this.tvDuration = tvDuration;
        this.viewPager = viewPager;
        this.visitCustomerTypeIv = visitCustomerTypeIv;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public VisitDetails getItem() {
        return this.mItem;
    }

    public static ActivityVisitDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVisitDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityVisitDetailsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_visit_details, root, attachToRoot, component);
    }

    public static ActivityVisitDetailsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVisitDetailsBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityVisitDetailsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_visit_details, null, false, component);
    }

    public static ActivityVisitDetailsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVisitDetailsBinding bind(View view, Object component) {
        return (ActivityVisitDetailsBinding) bind(component, view, R.layout.activity_visit_details);
    }
}
