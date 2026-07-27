package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ItemProductBinding implements ViewBinding {
    public final ImageView ivBookImage;
    private final ConstraintLayout rootView;

    private ItemProductBinding(ConstraintLayout rootView, ImageView ivBookImage) {
        this.rootView = rootView;
        this.ivBookImage = ivBookImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemProductBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemProductBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_product, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemProductBinding bind(View rootView) {
        int id = R.id.ivBookImage;
        ImageView ivBookImage = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (ivBookImage != null) {
            return new ItemProductBinding((ConstraintLayout) rootView, ivBookImage);
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
