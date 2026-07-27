package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class CreateRequestMultipleImgItemBinding implements ViewBinding {
    public final ShapeableImageView imagePreview;
    private final LinearLayout rootView;

    private CreateRequestMultipleImgItemBinding(LinearLayout rootView, ShapeableImageView imagePreview) {
        this.rootView = rootView;
        this.imagePreview = imagePreview;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CreateRequestMultipleImgItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CreateRequestMultipleImgItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.create_request_multiple_img_item, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static CreateRequestMultipleImgItemBinding bind(View rootView) {
        int id = R.id.imagePreview;
        ShapeableImageView imagePreview = (ShapeableImageView) ViewBindings.findChildViewById(rootView, id);
        if (imagePreview != null) {
            return new CreateRequestMultipleImgItemBinding((LinearLayout) rootView, imagePreview);
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
