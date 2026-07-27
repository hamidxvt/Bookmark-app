package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ActivityPrivacyPolicyBinding implements ViewBinding {
    public final ImageView backIcon;
    public final ConstraintLayout main;
    public final ScrollView privacyScroll;
    public final TextView privacyText;
    private final ConstraintLayout rootView;
    public final TextView settingTxt;

    private ActivityPrivacyPolicyBinding(ConstraintLayout rootView, ImageView backIcon, ConstraintLayout main, ScrollView privacyScroll, TextView privacyText, TextView settingTxt) {
        this.rootView = rootView;
        this.backIcon = backIcon;
        this.main = main;
        this.privacyScroll = privacyScroll;
        this.privacyText = privacyText;
        this.settingTxt = settingTxt;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPrivacyPolicyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPrivacyPolicyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_privacy_policy, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityPrivacyPolicyBinding bind(View rootView) {
        int id = R.id.backIcon;
        ImageView backIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backIcon != null) {
            ConstraintLayout main = (ConstraintLayout) rootView;
            id = R.id.privacyScroll;
            ScrollView privacyScroll = (ScrollView) ViewBindings.findChildViewById(rootView, id);
            if (privacyScroll != null) {
                id = R.id.privacyText;
                TextView privacyText = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (privacyText != null) {
                    id = R.id.settingTxt;
                    TextView settingTxt = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (settingTxt != null) {
                        return new ActivityPrivacyPolicyBinding((ConstraintLayout) rootView, backIcon, main, privacyScroll, privacyText, settingTxt);
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
