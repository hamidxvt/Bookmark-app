package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ActivityQuickScreenBinding implements ViewBinding {
    public final ImageView backIcon;
    public final LinearLayout faqContainer;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView settingTxt;

    private ActivityQuickScreenBinding(ConstraintLayout rootView, ImageView backIcon, LinearLayout faqContainer, ConstraintLayout main, TextView settingTxt) {
        this.rootView = rootView;
        this.backIcon = backIcon;
        this.faqContainer = faqContainer;
        this.main = main;
        this.settingTxt = settingTxt;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityQuickScreenBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityQuickScreenBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_quick_screen, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityQuickScreenBinding bind(View rootView) {
        int id = R.id.backIcon;
        ImageView backIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backIcon != null) {
            id = R.id.faqContainer;
            LinearLayout faqContainer = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
            if (faqContainer != null) {
                ConstraintLayout main = (ConstraintLayout) rootView;
                id = R.id.settingTxt;
                TextView settingTxt = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (settingTxt != null) {
                    return new ActivityQuickScreenBinding((ConstraintLayout) rootView, backIcon, faqContainer, main, settingTxt);
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
