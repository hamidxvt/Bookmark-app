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
public final class NotificationLayoutBinding implements ViewBinding {
    public final ImageView notificationLogo;
    public final TextView notificationMessage;
    public final TextView notificationTitle;
    private final ConstraintLayout rootView;

    private NotificationLayoutBinding(ConstraintLayout rootView, ImageView notificationLogo, TextView notificationMessage, TextView notificationTitle) {
        this.rootView = rootView;
        this.notificationLogo = notificationLogo;
        this.notificationMessage = notificationMessage;
        this.notificationTitle = notificationTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static NotificationLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static NotificationLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.notification_layout, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static NotificationLayoutBinding bind(View rootView) {
        int id = R.id.notification_logo;
        ImageView notificationLogo = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (notificationLogo != null) {
            id = R.id.notification_message;
            TextView notificationMessage = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (notificationMessage != null) {
                id = R.id.notification_title;
                TextView notificationTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (notificationTitle != null) {
                    return new NotificationLayoutBinding((ConstraintLayout) rootView, notificationLogo, notificationMessage, notificationTitle);
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
