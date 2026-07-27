package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ItemNotificationBinding implements ViewBinding {
    public final ImageView ivIcon;
    private final CardView rootView;
    public final TextView tvDate;
    public final TextView tvMessage;
    public final TextView tvTitle;

    private ItemNotificationBinding(CardView rootView, ImageView ivIcon, TextView tvDate, TextView tvMessage, TextView tvTitle) {
        this.rootView = rootView;
        this.ivIcon = ivIcon;
        this.tvDate = tvDate;
        this.tvMessage = tvMessage;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemNotificationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemNotificationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_notification, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemNotificationBinding bind(View rootView) {
        int id = R.id.ivIcon;
        ImageView ivIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (ivIcon != null) {
            id = R.id.tvDate;
            TextView tvDate = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (tvDate != null) {
                id = R.id.tvMessage;
                TextView tvMessage = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (tvMessage != null) {
                    id = R.id.tvTitle;
                    TextView tvTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (tvTitle != null) {
                        return new ItemNotificationBinding((CardView) rootView, ivIcon, tvDate, tvMessage, tvTitle);
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
