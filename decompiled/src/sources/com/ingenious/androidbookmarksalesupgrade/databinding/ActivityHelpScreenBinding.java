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
public final class ActivityHelpScreenBinding implements ViewBinding {
    public final ImageView backIcon;
    public final ConstraintLayout main;
    public final LinearLayout quickScreen;
    public final LinearLayout requestScreen;
    private final ConstraintLayout rootView;
    public final TextView settingTxt;
    public final LinearLayout whatsAppScreen;

    private ActivityHelpScreenBinding(ConstraintLayout rootView, ImageView backIcon, ConstraintLayout main, LinearLayout quickScreen, LinearLayout requestScreen, TextView settingTxt, LinearLayout whatsAppScreen) {
        this.rootView = rootView;
        this.backIcon = backIcon;
        this.main = main;
        this.quickScreen = quickScreen;
        this.requestScreen = requestScreen;
        this.settingTxt = settingTxt;
        this.whatsAppScreen = whatsAppScreen;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityHelpScreenBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityHelpScreenBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_help_screen, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityHelpScreenBinding bind(View rootView) {
        int id = R.id.backIcon;
        ImageView backIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backIcon != null) {
            ConstraintLayout main = (ConstraintLayout) rootView;
            id = R.id.quickScreen;
            LinearLayout quickScreen = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
            if (quickScreen != null) {
                id = R.id.requestScreen;
                LinearLayout requestScreen = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                if (requestScreen != null) {
                    id = R.id.settingTxt;
                    TextView settingTxt = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (settingTxt != null) {
                        id = R.id.whatsAppScreen;
                        LinearLayout whatsAppScreen = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                        if (whatsAppScreen != null) {
                            return new ActivityHelpScreenBinding((ConstraintLayout) rootView, backIcon, main, quickScreen, requestScreen, settingTxt, whatsAppScreen);
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
