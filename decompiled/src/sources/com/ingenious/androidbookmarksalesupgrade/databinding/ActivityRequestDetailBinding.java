package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ActivityRequestDetailBinding implements ViewBinding {
    public final ImageView backButton;
    public final ScrollView main;
    public final LinearLayout photoContainer;
    private final ScrollView rootView;
    public final TextView tvCategory;
    public final TextView tvCreatedAt;
    public final TextView tvDetails;
    public final TextView tvRequestId;
    public final TextView tvReviewMessage;
    public final TextView tvStatus;
    public final TextView tvTitle;

    private ActivityRequestDetailBinding(ScrollView rootView, ImageView backButton, ScrollView main, LinearLayout photoContainer, TextView tvCategory, TextView tvCreatedAt, TextView tvDetails, TextView tvRequestId, TextView tvReviewMessage, TextView tvStatus, TextView tvTitle) {
        this.rootView = rootView;
        this.backButton = backButton;
        this.main = main;
        this.photoContainer = photoContainer;
        this.tvCategory = tvCategory;
        this.tvCreatedAt = tvCreatedAt;
        this.tvDetails = tvDetails;
        this.tvRequestId = tvRequestId;
        this.tvReviewMessage = tvReviewMessage;
        this.tvStatus = tvStatus;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static ActivityRequestDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRequestDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_request_detail, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityRequestDetailBinding bind(View rootView) {
        int id = R.id.backButton;
        ImageView backButton = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backButton != null) {
            ScrollView main = (ScrollView) rootView;
            id = R.id.photoContainer;
            LinearLayout photoContainer = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
            if (photoContainer != null) {
                id = R.id.tvCategory;
                TextView tvCategory = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (tvCategory != null) {
                    id = R.id.tvCreatedAt;
                    TextView tvCreatedAt = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (tvCreatedAt != null) {
                        id = R.id.tvDetails;
                        TextView tvDetails = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (tvDetails != null) {
                            id = R.id.tvRequestId;
                            TextView tvRequestId = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (tvRequestId != null) {
                                id = R.id.tvReviewMessage;
                                TextView tvReviewMessage = (TextView) ViewBindings.findChildViewById(rootView, id);
                                if (tvReviewMessage != null) {
                                    id = R.id.tvStatus;
                                    TextView tvStatus = (TextView) ViewBindings.findChildViewById(rootView, id);
                                    if (tvStatus != null) {
                                        id = R.id.tvTitle;
                                        TextView tvTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                                        if (tvTitle != null) {
                                            return new ActivityRequestDetailBinding((ScrollView) rootView, backButton, main, photoContainer, tvCategory, tvCreatedAt, tvDetails, tvRequestId, tvReviewMessage, tvStatus, tvTitle);
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
