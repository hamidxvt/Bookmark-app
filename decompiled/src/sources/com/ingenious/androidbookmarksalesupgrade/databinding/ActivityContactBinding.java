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
public final class ActivityContactBinding implements ViewBinding {
    public final ImageView backIcon;
    public final LinearLayout callScreen;
    public final LinearLayout emailScreen;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView settingTxt;
    public final LinearLayout whatsAppScreen;

    private ActivityContactBinding(ConstraintLayout rootView, ImageView backIcon, LinearLayout callScreen, LinearLayout emailScreen, ConstraintLayout main, TextView settingTxt, LinearLayout whatsAppScreen) {
        this.rootView = rootView;
        this.backIcon = backIcon;
        this.callScreen = callScreen;
        this.emailScreen = emailScreen;
        this.main = main;
        this.settingTxt = settingTxt;
        this.whatsAppScreen = whatsAppScreen;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityContactBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityContactBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_contact, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityContactBinding bind(View rootView) {
        int id = R.id.backIcon;
        ImageView backIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backIcon != null) {
            id = R.id.callScreen;
            LinearLayout callScreen = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
            if (callScreen != null) {
                id = R.id.emailScreen;
                LinearLayout emailScreen = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                if (emailScreen != null) {
                    ConstraintLayout main = (ConstraintLayout) rootView;
                    id = R.id.settingTxt;
                    TextView settingTxt = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (settingTxt != null) {
                        id = R.id.whatsAppScreen;
                        LinearLayout whatsAppScreen = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                        if (whatsAppScreen != null) {
                            return new ActivityContactBinding((ConstraintLayout) rootView, backIcon, callScreen, emailScreen, main, settingTxt, whatsAppScreen);
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
