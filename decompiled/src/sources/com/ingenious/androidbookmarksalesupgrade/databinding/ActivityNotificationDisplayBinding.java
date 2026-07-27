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
public final class ActivityNotificationDisplayBinding implements ViewBinding {
    public final ImageView backIcon;
    public final ConstraintLayout main;
    public final RecyclerView notificationList;
    public final ProgressBar progressBar;
    private final ConstraintLayout rootView;
    public final TextView settingTxt;
    public final TextView tvNoData;

    private ActivityNotificationDisplayBinding(ConstraintLayout rootView, ImageView backIcon, ConstraintLayout main, RecyclerView notificationList, ProgressBar progressBar, TextView settingTxt, TextView tvNoData) {
        this.rootView = rootView;
        this.backIcon = backIcon;
        this.main = main;
        this.notificationList = notificationList;
        this.progressBar = progressBar;
        this.settingTxt = settingTxt;
        this.tvNoData = tvNoData;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityNotificationDisplayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityNotificationDisplayBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_notification_display, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityNotificationDisplayBinding bind(View rootView) {
        int id = R.id.backIcon;
        ImageView backIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backIcon != null) {
            ConstraintLayout main = (ConstraintLayout) rootView;
            id = R.id.notificationList;
            RecyclerView notificationList = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
            if (notificationList != null) {
                id = R.id.progressBar;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, id);
                if (progressBar != null) {
                    id = R.id.settingTxt;
                    TextView settingTxt = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (settingTxt != null) {
                        id = R.id.tvNoData;
                        TextView tvNoData = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (tvNoData != null) {
                            return new ActivityNotificationDisplayBinding((ConstraintLayout) rootView, backIcon, main, notificationList, progressBar, settingTxt, tvNoData);
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
