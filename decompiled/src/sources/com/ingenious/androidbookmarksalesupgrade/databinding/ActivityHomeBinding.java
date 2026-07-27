package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class ActivityHomeBinding extends ViewDataBinding {
    public final ImageView activityIcon;
    public final TextView activityTxt;
    public final LinearLayout bottomNav;
    public final TextView customerTxt;
    public final TextView homeTxt;
    public final ImageView iconCustomers;
    public final ImageView iconHome;
    public final ImageView iconInventory;
    public final TextView inventoryTxt;
    public final ConstraintLayout main;
    public final LinearLayout navActivity;
    public final LinearLayout navCustomers;
    public final LinearLayout navHome;
    public final FragmentContainerView navHostFragment;
    public final LinearLayout navInventory;
    public final LinearLayout navPerformance;
    public final ImageView performance;
    public final TextView performanceTxt;

    protected ActivityHomeBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView activityIcon, TextView activityTxt, LinearLayout bottomNav, TextView customerTxt, TextView homeTxt, ImageView iconCustomers, ImageView iconHome, ImageView iconInventory, TextView inventoryTxt, ConstraintLayout main, LinearLayout navActivity, LinearLayout navCustomers, LinearLayout navHome, FragmentContainerView navHostFragment, LinearLayout navInventory, LinearLayout navPerformance, ImageView performance, TextView performanceTxt) {
        super(_bindingComponent, _root, _localFieldCount);
        this.activityIcon = activityIcon;
        this.activityTxt = activityTxt;
        this.bottomNav = bottomNav;
        this.customerTxt = customerTxt;
        this.homeTxt = homeTxt;
        this.iconCustomers = iconCustomers;
        this.iconHome = iconHome;
        this.iconInventory = iconInventory;
        this.inventoryTxt = inventoryTxt;
        this.main = main;
        this.navActivity = navActivity;
        this.navCustomers = navCustomers;
        this.navHome = navHome;
        this.navHostFragment = navHostFragment;
        this.navInventory = navInventory;
        this.navPerformance = navPerformance;
        this.performance = performance;
        this.performanceTxt = performanceTxt;
    }

    public static ActivityHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_home, root, attachToRoot, component);
    }

    public static ActivityHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHomeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_home, null, false, component);
    }

    public static ActivityHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHomeBinding bind(View view, Object component) {
        return (ActivityHomeBinding) bind(component, view, R.layout.activity_home);
    }
}
