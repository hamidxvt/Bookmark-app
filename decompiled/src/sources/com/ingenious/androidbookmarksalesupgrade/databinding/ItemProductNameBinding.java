package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ItemProductNameBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvBookTitle;

    private ItemProductNameBinding(ConstraintLayout rootView, TextView tvBookTitle) {
        this.rootView = rootView;
        this.tvBookTitle = tvBookTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemProductNameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemProductNameBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_product_name, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemProductNameBinding bind(View rootView) {
        int id = R.id.tvBookTitle;
        TextView tvBookTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
        if (tvBookTitle != null) {
            return new ItemProductNameBinding((ConstraintLayout) rootView, tvBookTitle);
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
