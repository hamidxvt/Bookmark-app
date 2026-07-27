package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class LayoutLoadingBinding implements ViewBinding {
    public final LinearProgressIndicator progressIndicator;
    private final LinearLayout rootView;

    private LayoutLoadingBinding(LinearLayout rootView, LinearProgressIndicator progressIndicator) {
        this.rootView = rootView;
        this.progressIndicator = progressIndicator;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutLoadingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutLoadingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.layout_loading, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static LayoutLoadingBinding bind(View rootView) {
        int id = R.id.progressIndicator;
        LinearProgressIndicator progressIndicator = (LinearProgressIndicator) ViewBindings.findChildViewById(rootView, id);
        if (progressIndicator != null) {
            return new LayoutLoadingBinding((LinearLayout) rootView, progressIndicator);
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
