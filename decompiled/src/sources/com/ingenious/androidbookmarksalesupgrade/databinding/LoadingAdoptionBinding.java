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
public final class LoadingAdoptionBinding implements ViewBinding {
    public final ImageView imgLogo;
    private final ConstraintLayout rootView;
    public final TextView txtLoading;

    private LoadingAdoptionBinding(ConstraintLayout rootView, ImageView imgLogo, TextView txtLoading) {
        this.rootView = rootView;
        this.imgLogo = imgLogo;
        this.txtLoading = txtLoading;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LoadingAdoptionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LoadingAdoptionBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.loading_adoption, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static LoadingAdoptionBinding bind(View rootView) {
        int id = R.id.imgLogo;
        ImageView imgLogo = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (imgLogo != null) {
            id = R.id.txtLoading;
            TextView txtLoading = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (txtLoading != null) {
                return new LoadingAdoptionBinding((ConstraintLayout) rootView, imgLogo, txtLoading);
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
