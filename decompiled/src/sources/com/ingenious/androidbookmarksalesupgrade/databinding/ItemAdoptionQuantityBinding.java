package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ItemAdoptionQuantityBinding implements ViewBinding {
    public final MaterialCardView cardView;
    public final ShapeableImageView ivBookCover;
    public final ImageView ivMinus;
    public final ImageView ivPlus;
    public final LinearLayout quantityStepper;
    private final ConstraintLayout rootView;
    public final TextView tvBookDetails;
    public final TextView tvBookTitle;
    public final TextView tvQuantity;

    private ItemAdoptionQuantityBinding(ConstraintLayout rootView, MaterialCardView cardView, ShapeableImageView ivBookCover, ImageView ivMinus, ImageView ivPlus, LinearLayout quantityStepper, TextView tvBookDetails, TextView tvBookTitle, TextView tvQuantity) {
        this.rootView = rootView;
        this.cardView = cardView;
        this.ivBookCover = ivBookCover;
        this.ivMinus = ivMinus;
        this.ivPlus = ivPlus;
        this.quantityStepper = quantityStepper;
        this.tvBookDetails = tvBookDetails;
        this.tvBookTitle = tvBookTitle;
        this.tvQuantity = tvQuantity;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemAdoptionQuantityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemAdoptionQuantityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_adoption_quantity, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemAdoptionQuantityBinding bind(View rootView) {
        int id = R.id.cardView;
        MaterialCardView cardView = (MaterialCardView) ViewBindings.findChildViewById(rootView, id);
        if (cardView != null) {
            id = R.id.ivBookCover;
            ShapeableImageView ivBookCover = (ShapeableImageView) ViewBindings.findChildViewById(rootView, id);
            if (ivBookCover != null) {
                id = R.id.ivMinus;
                ImageView ivMinus = (ImageView) ViewBindings.findChildViewById(rootView, id);
                if (ivMinus != null) {
                    id = R.id.ivPlus;
                    ImageView ivPlus = (ImageView) ViewBindings.findChildViewById(rootView, id);
                    if (ivPlus != null) {
                        id = R.id.quantityStepper;
                        LinearLayout quantityStepper = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                        if (quantityStepper != null) {
                            id = R.id.tvBookDetails;
                            TextView tvBookDetails = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (tvBookDetails != null) {
                                id = R.id.tvBookTitle;
                                TextView tvBookTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                                if (tvBookTitle != null) {
                                    id = R.id.tvQuantity;
                                    TextView tvQuantity = (TextView) ViewBindings.findChildViewById(rootView, id);
                                    if (tvQuantity != null) {
                                        return new ItemAdoptionQuantityBinding((ConstraintLayout) rootView, cardView, ivBookCover, ivMinus, ivPlus, quantityStepper, tvBookDetails, tvBookTitle, tvQuantity);
                                    }
                                }
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
