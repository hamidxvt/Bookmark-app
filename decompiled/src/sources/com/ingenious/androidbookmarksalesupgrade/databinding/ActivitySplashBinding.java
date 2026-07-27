package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ActivitySplashBinding implements ViewBinding {
    public final LottieAnimationView logoAnimation;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView titleTv;

    private ActivitySplashBinding(ConstraintLayout rootView, LottieAnimationView logoAnimation, ConstraintLayout main, TextView titleTv) {
        this.rootView = rootView;
        this.logoAnimation = logoAnimation;
        this.main = main;
        this.titleTv = titleTv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySplashBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySplashBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_splash, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivitySplashBinding bind(View rootView) {
        int id = R.id.logo_animation;
        LottieAnimationView logoAnimation = (LottieAnimationView) ViewBindings.findChildViewById(rootView, id);
        if (logoAnimation != null) {
            ConstraintLayout main = (ConstraintLayout) rootView;
            id = R.id.title_tv;
            TextView titleTv = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (titleTv != null) {
                return new ActivitySplashBinding((ConstraintLayout) rootView, logoAnimation, main, titleTv);
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
