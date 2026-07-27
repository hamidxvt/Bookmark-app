package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ActivityRequestDashboardBinding implements ViewBinding {
    public final ImageView backIcon;
    public final ImageView createRequestButton;
    public final ConstraintLayout main;
    public final TextView noDataText;
    public final ProgressBar progressBar;
    public final RecyclerView recyclerRequests;
    private final ConstraintLayout rootView;
    public final TextView settingTxt;

    private ActivityRequestDashboardBinding(ConstraintLayout rootView, ImageView backIcon, ImageView createRequestButton, ConstraintLayout main, TextView noDataText, ProgressBar progressBar, RecyclerView recyclerRequests, TextView settingTxt) {
        this.rootView = rootView;
        this.backIcon = backIcon;
        this.createRequestButton = createRequestButton;
        this.main = main;
        this.noDataText = noDataText;
        this.progressBar = progressBar;
        this.recyclerRequests = recyclerRequests;
        this.settingTxt = settingTxt;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRequestDashboardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRequestDashboardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_request_dashboard, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityRequestDashboardBinding bind(View rootView) {
        int id = R.id.backIcon;
        ImageView backIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backIcon != null) {
            id = R.id.createRequestButton;
            ImageView createRequestButton = (ImageView) ViewBindings.findChildViewById(rootView, id);
            if (createRequestButton != null) {
                ConstraintLayout main = (ConstraintLayout) rootView;
                id = R.id.noDataText;
                TextView noDataText = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (noDataText != null) {
                    id = R.id.progressBar;
                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, id);
                    if (progressBar != null) {
                        id = R.id.recyclerRequests;
                        RecyclerView recyclerRequests = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
                        if (recyclerRequests != null) {
                            id = R.id.settingTxt;
                            TextView settingTxt = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (settingTxt != null) {
                                return new ActivityRequestDashboardBinding((ConstraintLayout) rootView, backIcon, createRequestButton, main, noDataText, progressBar, recyclerRequests, settingTxt);
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
