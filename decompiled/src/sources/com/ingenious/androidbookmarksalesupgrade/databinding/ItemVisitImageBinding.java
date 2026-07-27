package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ItemVisitImageBinding implements ViewBinding {
    public final ImageView imgVisit;
    private final CardView rootView;

    private ItemVisitImageBinding(CardView rootView, ImageView imgVisit) {
        this.rootView = rootView;
        this.imgVisit = imgVisit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemVisitImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemVisitImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_visit_image, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemVisitImageBinding bind(View rootView) {
        int id = R.id.imgVisit;
        ImageView imgVisit = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (imgVisit != null) {
            return new ItemVisitImageBinding((CardView) rootView, imgVisit);
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
