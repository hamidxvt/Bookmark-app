package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class FragmentPerformanceBinding implements ViewBinding {
    public final TextView achievementText;
    public final TextView avgDaily;
    public final BarChart customersBarChart;
    public final TextView missedVisits;
    public final ImageView performanceMenu;
    public final TextView plusCustomer;
    private final NestedScrollView rootView;
    public final NestedScrollView scrollView;
    public final LinearLayout targetSection;
    public final TextView totalCustomer;
    public final TextView totalVisits;
    public final LineChart visitsLineChart;
    public final TextView weeklyPercentText;
    public final ProgressBar weeklyProgress;
    public final TextView weeklyTargetDetail;
    public final TextView weeklyTargetTitle;
    public final TextView yearlyPercentText;
    public final ProgressBar yearlyProgress;
    public final TextView yearlyTargetDetail;
    public final TextView yearlyTargetTitle;

    private FragmentPerformanceBinding(NestedScrollView rootView, TextView achievementText, TextView avgDaily, BarChart customersBarChart, TextView missedVisits, ImageView performanceMenu, TextView plusCustomer, NestedScrollView scrollView, LinearLayout targetSection, TextView totalCustomer, TextView totalVisits, LineChart visitsLineChart, TextView weeklyPercentText, ProgressBar weeklyProgress, TextView weeklyTargetDetail, TextView weeklyTargetTitle, TextView yearlyPercentText, ProgressBar yearlyProgress, TextView yearlyTargetDetail, TextView yearlyTargetTitle) {
        this.rootView = rootView;
        this.achievementText = achievementText;
        this.avgDaily = avgDaily;
        this.customersBarChart = customersBarChart;
        this.missedVisits = missedVisits;
        this.performanceMenu = performanceMenu;
        this.plusCustomer = plusCustomer;
        this.scrollView = scrollView;
        this.targetSection = targetSection;
        this.totalCustomer = totalCustomer;
        this.totalVisits = totalVisits;
        this.visitsLineChart = visitsLineChart;
        this.weeklyPercentText = weeklyPercentText;
        this.weeklyProgress = weeklyProgress;
        this.weeklyTargetDetail = weeklyTargetDetail;
        this.weeklyTargetTitle = weeklyTargetTitle;
        this.yearlyPercentText = yearlyPercentText;
        this.yearlyProgress = yearlyProgress;
        this.yearlyTargetDetail = yearlyTargetDetail;
        this.yearlyTargetTitle = yearlyTargetTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentPerformanceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentPerformanceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.fragment_performance, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static FragmentPerformanceBinding bind(View rootView) {
        int id = R.id.achievementText;
        TextView achievementText = (TextView) ViewBindings.findChildViewById(rootView, id);
        if (achievementText != null) {
            id = R.id.avgDaily;
            TextView avgDaily = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (avgDaily != null) {
                id = R.id.customersBarChart;
                BarChart customersBarChart = (BarChart) ViewBindings.findChildViewById(rootView, id);
                if (customersBarChart != null) {
                    id = R.id.missedVisits;
                    TextView missedVisits = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (missedVisits != null) {
                        id = R.id.performanceMenu;
                        ImageView performanceMenu = (ImageView) ViewBindings.findChildViewById(rootView, id);
                        if (performanceMenu != null) {
                            id = R.id.plusCustomer;
                            TextView plusCustomer = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (plusCustomer != null) {
                                NestedScrollView scrollView = (NestedScrollView) rootView;
                                id = R.id.targetSection;
                                LinearLayout targetSection = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                                if (targetSection != null) {
                                    id = R.id.totalCustomer;
                                    TextView totalCustomer = (TextView) ViewBindings.findChildViewById(rootView, id);
                                    if (totalCustomer != null) {
                                        id = R.id.totalVisits;
                                        TextView totalVisits = (TextView) ViewBindings.findChildViewById(rootView, id);
                                        if (totalVisits != null) {
                                            id = R.id.visitsLineChart;
                                            LineChart visitsLineChart = (LineChart) ViewBindings.findChildViewById(rootView, id);
                                            if (visitsLineChart != null) {
                                                id = R.id.weeklyPercentText;
                                                TextView weeklyPercentText = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                if (weeklyPercentText != null) {
                                                    id = R.id.weeklyProgress;
                                                    ProgressBar weeklyProgress = (ProgressBar) ViewBindings.findChildViewById(rootView, id);
                                                    if (weeklyProgress != null) {
                                                        id = R.id.weeklyTargetDetail;
                                                        TextView weeklyTargetDetail = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                        if (weeklyTargetDetail != null) {
                                                            id = R.id.weeklyTargetTitle;
                                                            TextView weeklyTargetTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                            if (weeklyTargetTitle != null) {
                                                                id = R.id.yearlyPercentText;
                                                                TextView yearlyPercentText = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                if (yearlyPercentText != null) {
                                                                    id = R.id.yearlyProgress;
                                                                    ProgressBar yearlyProgress = (ProgressBar) ViewBindings.findChildViewById(rootView, id);
                                                                    if (yearlyProgress != null) {
                                                                        id = R.id.yearlyTargetDetail;
                                                                        TextView yearlyTargetDetail = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                        if (yearlyTargetDetail != null) {
                                                                            id = R.id.yearlyTargetTitle;
                                                                            TextView yearlyTargetTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                            if (yearlyTargetTitle != null) {
                                                                                return new FragmentPerformanceBinding((NestedScrollView) rootView, achievementText, avgDaily, customersBarChart, missedVisits, performanceMenu, plusCustomer, scrollView, targetSection, totalCustomer, totalVisits, visitsLineChart, weeklyPercentText, weeklyProgress, weeklyTargetDetail, weeklyTargetTitle, yearlyPercentText, yearlyProgress, yearlyTargetDetail, yearlyTargetTitle);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
