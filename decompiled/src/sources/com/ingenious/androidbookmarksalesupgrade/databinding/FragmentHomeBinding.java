package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.HomeResponse;
import io.github.florent37.shapeofview.shapes.CircleView;

/* loaded from: classes13.dex */
public abstract class FragmentHomeBinding extends ViewDataBinding {
    public final ImageButton btnNext;
    public final ImageButton btnPrev;
    public final CircularProgressIndicator circleProgress;
    public final LinearLayout dateFilterLinearLayout;
    public final TextView fabAddVisitCustomer;
    public final ImageView filterIconIv;
    public final ImageButton icCalendar;
    public final CircleView imgCircleProfile;
    public final ImageView inbox;
    public final MaterialCardView jobStartLinear;

    @Bindable
    protected HomeResponse mItem;

    @Bindable
    protected GenericListeners mListener;
    public final SwitchMaterial materialSwitch;
    public final ImageView notificationIcon;
    public final ImageView pastFilterIconIv;
    public final RecyclerView pastVisitsRv;
    public final TextView pastVisitsText;
    public final TextView pendingVisit;
    public final View redDot;
    public final View redDotInbox;
    public final ImageView roundedImage;
    public final TextView seeStats;
    public final MaterialCardView seeStatsLinear;
    public final AppCompatButton startJobBtn;
    public final TextView statusText;
    public final RecyclerView todayVisitsRv;
    public final TextView totalVisit;
    public final TextView tvDate;
    public final TextView visitsLabel;
    public final TextView visitsThisWeek;
    public final TextView visitsValue;

    public abstract void setItem(HomeResponse homeResponse);

    public abstract void setListener(GenericListeners genericListeners);

    protected FragmentHomeBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageButton btnNext, ImageButton btnPrev, CircularProgressIndicator circleProgress, LinearLayout dateFilterLinearLayout, TextView fabAddVisitCustomer, ImageView filterIconIv, ImageButton icCalendar, CircleView imgCircleProfile, ImageView inbox, MaterialCardView jobStartLinear, SwitchMaterial materialSwitch, ImageView notificationIcon, ImageView pastFilterIconIv, RecyclerView pastVisitsRv, TextView pastVisitsText, TextView pendingVisit, View redDot, View redDotInbox, ImageView roundedImage, TextView seeStats, MaterialCardView seeStatsLinear, AppCompatButton startJobBtn, TextView statusText, RecyclerView todayVisitsRv, TextView totalVisit, TextView tvDate, TextView visitsLabel, TextView visitsThisWeek, TextView visitsValue) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnNext = btnNext;
        this.btnPrev = btnPrev;
        this.circleProgress = circleProgress;
        this.dateFilterLinearLayout = dateFilterLinearLayout;
        this.fabAddVisitCustomer = fabAddVisitCustomer;
        this.filterIconIv = filterIconIv;
        this.icCalendar = icCalendar;
        this.imgCircleProfile = imgCircleProfile;
        this.inbox = inbox;
        this.jobStartLinear = jobStartLinear;
        this.materialSwitch = materialSwitch;
        this.notificationIcon = notificationIcon;
        this.pastFilterIconIv = pastFilterIconIv;
        this.pastVisitsRv = pastVisitsRv;
        this.pastVisitsText = pastVisitsText;
        this.pendingVisit = pendingVisit;
        this.redDot = redDot;
        this.redDotInbox = redDotInbox;
        this.roundedImage = roundedImage;
        this.seeStats = seeStats;
        this.seeStatsLinear = seeStatsLinear;
        this.startJobBtn = startJobBtn;
        this.statusText = statusText;
        this.todayVisitsRv = todayVisitsRv;
        this.totalVisit = totalVisit;
        this.tvDate = tvDate;
        this.visitsLabel = visitsLabel;
        this.visitsThisWeek = visitsThisWeek;
        this.visitsValue = visitsValue;
    }

    public HomeResponse getItem() {
        return this.mItem;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home, root, attachToRoot, component);
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home, null, false, component);
    }

    public static FragmentHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeBinding bind(View view, Object component) {
        return (FragmentHomeBinding) bind(component, view, R.layout.fragment_home);
    }
}
