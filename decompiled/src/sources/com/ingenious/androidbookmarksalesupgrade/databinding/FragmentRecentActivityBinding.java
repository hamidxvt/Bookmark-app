package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class FragmentRecentActivityBinding implements ViewBinding {
    public final RecyclerView activityListRv;
    public final RecyclerView activityResultRv;
    public final TextView cancelTv;
    public final LinearLayout filterLayout;
    public final EditText filterSearch;
    public final ImageView mainFiler;
    public final ImageView performanceMenu;
    public final ProgressBar progressBar;
    private final ConstraintLayout rootView;
    public final LinearLayout searchLayout;
    public final LinearLayout settingTxt;
    public final TextView tvNoData;

    private FragmentRecentActivityBinding(ConstraintLayout rootView, RecyclerView activityListRv, RecyclerView activityResultRv, TextView cancelTv, LinearLayout filterLayout, EditText filterSearch, ImageView mainFiler, ImageView performanceMenu, ProgressBar progressBar, LinearLayout searchLayout, LinearLayout settingTxt, TextView tvNoData) {
        this.rootView = rootView;
        this.activityListRv = activityListRv;
        this.activityResultRv = activityResultRv;
        this.cancelTv = cancelTv;
        this.filterLayout = filterLayout;
        this.filterSearch = filterSearch;
        this.mainFiler = mainFiler;
        this.performanceMenu = performanceMenu;
        this.progressBar = progressBar;
        this.searchLayout = searchLayout;
        this.settingTxt = settingTxt;
        this.tvNoData = tvNoData;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentRecentActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentRecentActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.fragment_recent_activity, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static FragmentRecentActivityBinding bind(View rootView) {
        int id = R.id.activityListRv;
        RecyclerView activityListRv = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
        if (activityListRv != null) {
            id = R.id.activityResultRv;
            RecyclerView activityResultRv = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
            if (activityResultRv != null) {
                id = R.id.cancel_tv;
                TextView cancelTv = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (cancelTv != null) {
                    id = R.id.filterLayout;
                    LinearLayout filterLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                    if (filterLayout != null) {
                        id = R.id.filterSearch;
                        EditText filterSearch = (EditText) ViewBindings.findChildViewById(rootView, id);
                        if (filterSearch != null) {
                            id = R.id.mainFiler;
                            ImageView mainFiler = (ImageView) ViewBindings.findChildViewById(rootView, id);
                            if (mainFiler != null) {
                                id = R.id.performanceMenu;
                                ImageView performanceMenu = (ImageView) ViewBindings.findChildViewById(rootView, id);
                                if (performanceMenu != null) {
                                    id = R.id.progressBar;
                                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, id);
                                    if (progressBar != null) {
                                        id = R.id.searchLayout;
                                        LinearLayout searchLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                                        if (searchLayout != null) {
                                            id = R.id.settingTxt;
                                            LinearLayout settingTxt = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                                            if (settingTxt != null) {
                                                id = R.id.tvNoData;
                                                TextView tvNoData = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                if (tvNoData != null) {
                                                    return new FragmentRecentActivityBinding((ConstraintLayout) rootView, activityListRv, activityResultRv, cancelTv, filterLayout, filterSearch, mainFiler, performanceMenu, progressBar, searchLayout, settingTxt, tvNoData);
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
