package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ActivitySettingScreenBinding implements ViewBinding {
    public final ImageView backIcon;
    public final View contactCard;
    public final ImageView contactIcon;
    public final View helpCard;
    public final ImageView helpIcon;
    public final ConstraintLayout main;
    public final ImageView notIcon;
    public final View notificationCard;
    public final ImageView priIcon;
    public final View privacyPolicyCard;
    private final ConstraintLayout rootView;
    public final TextView settingTxt;

    private ActivitySettingScreenBinding(ConstraintLayout rootView, ImageView backIcon, View contactCard, ImageView contactIcon, View helpCard, ImageView helpIcon, ConstraintLayout main, ImageView notIcon, View notificationCard, ImageView priIcon, View privacyPolicyCard, TextView settingTxt) {
        this.rootView = rootView;
        this.backIcon = backIcon;
        this.contactCard = contactCard;
        this.contactIcon = contactIcon;
        this.helpCard = helpCard;
        this.helpIcon = helpIcon;
        this.main = main;
        this.notIcon = notIcon;
        this.notificationCard = notificationCard;
        this.priIcon = priIcon;
        this.privacyPolicyCard = privacyPolicyCard;
        this.settingTxt = settingTxt;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySettingScreenBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySettingScreenBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_setting_screen, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivitySettingScreenBinding bind(View rootView) {
        View contactCard;
        View helpCard;
        View notificationCard;
        View privacyPolicyCard;
        int id = R.id.backIcon;
        ImageView backIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backIcon != null && (contactCard = ViewBindings.findChildViewById(rootView, (id = R.id.contactCard))) != null) {
            id = R.id.contactIcon;
            ImageView contactIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
            if (contactIcon != null && (helpCard = ViewBindings.findChildViewById(rootView, (id = R.id.helpCard))) != null) {
                id = R.id.helpIcon;
                ImageView helpIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
                if (helpIcon != null) {
                    ConstraintLayout main = (ConstraintLayout) rootView;
                    id = R.id.notIcon;
                    ImageView notIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
                    if (notIcon != null && (notificationCard = ViewBindings.findChildViewById(rootView, (id = R.id.notificationCard))) != null) {
                        id = R.id.priIcon;
                        ImageView priIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
                        if (priIcon != null && (privacyPolicyCard = ViewBindings.findChildViewById(rootView, (id = R.id.privacyPolicyCard))) != null) {
                            id = R.id.settingTxt;
                            TextView settingTxt = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (settingTxt != null) {
                                return new ActivitySettingScreenBinding((ConstraintLayout) rootView, backIcon, contactCard, contactIcon, helpCard, helpIcon, main, notIcon, notificationCard, priIcon, privacyPolicyCard, settingTxt);
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
