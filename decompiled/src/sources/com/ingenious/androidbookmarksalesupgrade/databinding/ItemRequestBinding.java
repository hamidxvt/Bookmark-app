package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ItemRequestBinding implements ViewBinding {
    public final TextView requestDate;
    public final TextView requestId;
    public final TextView requestTitle;
    private final LinearLayout rootView;
    public final MaterialCardView statusCard;
    public final TextView statusDot;
    public final TextView statusText;

    private ItemRequestBinding(LinearLayout rootView, TextView requestDate, TextView requestId, TextView requestTitle, MaterialCardView statusCard, TextView statusDot, TextView statusText) {
        this.rootView = rootView;
        this.requestDate = requestDate;
        this.requestId = requestId;
        this.requestTitle = requestTitle;
        this.statusCard = statusCard;
        this.statusDot = statusDot;
        this.statusText = statusText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemRequestBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemRequestBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_request, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemRequestBinding bind(View rootView) {
        int id = R.id.requestDate;
        TextView requestDate = (TextView) ViewBindings.findChildViewById(rootView, id);
        if (requestDate != null) {
            id = R.id.requestId;
            TextView requestId = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (requestId != null) {
                id = R.id.requestTitle;
                TextView requestTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (requestTitle != null) {
                    id = R.id.statusCard;
                    MaterialCardView statusCard = (MaterialCardView) ViewBindings.findChildViewById(rootView, id);
                    if (statusCard != null) {
                        id = R.id.statusDot;
                        TextView statusDot = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (statusDot != null) {
                            id = R.id.statusText;
                            TextView statusText = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (statusText != null) {
                                return new ItemRequestBinding((LinearLayout) rootView, requestDate, requestId, requestTitle, statusCard, statusDot, statusText);
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
