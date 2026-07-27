package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class SampleItemBookCardBinding implements ViewBinding {
    public final ShapeableImageView imgBook;
    private final CardView rootView;
    public final TextView tvPrice;
    public final TextView tvSubtitle;
    public final TextView tvTitle;

    private SampleItemBookCardBinding(CardView rootView, ShapeableImageView imgBook, TextView tvPrice, TextView tvSubtitle, TextView tvTitle) {
        this.rootView = rootView;
        this.imgBook = imgBook;
        this.tvPrice = tvPrice;
        this.tvSubtitle = tvSubtitle;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static SampleItemBookCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SampleItemBookCardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.sample_item_book_card, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static SampleItemBookCardBinding bind(View rootView) {
        int id = R.id.imgBook;
        ShapeableImageView imgBook = (ShapeableImageView) ViewBindings.findChildViewById(rootView, id);
        if (imgBook != null) {
            id = R.id.tvPrice;
            TextView tvPrice = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (tvPrice != null) {
                id = R.id.tvSubtitle;
                TextView tvSubtitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (tvSubtitle != null) {
                    id = R.id.tvTitle;
                    TextView tvTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (tvTitle != null) {
                        return new SampleItemBookCardBinding((CardView) rootView, imgBook, tvPrice, tvSubtitle, tvTitle);
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
