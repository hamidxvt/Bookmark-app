package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class SegmentItemInventoryLowStockBinding implements ViewBinding {
    public final ImageView addToCart;
    public final ShapeableImageView productImage;
    public final TextView productName;
    public final TextView productPrice;
    private final CardView rootView;
    public final TextView stockTv;

    private SegmentItemInventoryLowStockBinding(CardView rootView, ImageView addToCart, ShapeableImageView productImage, TextView productName, TextView productPrice, TextView stockTv) {
        this.rootView = rootView;
        this.addToCart = addToCart;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.stockTv = stockTv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static SegmentItemInventoryLowStockBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SegmentItemInventoryLowStockBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.segment_item_inventory_low_stock, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static SegmentItemInventoryLowStockBinding bind(View rootView) {
        int id = R.id.addToCart;
        ImageView addToCart = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (addToCart != null) {
            id = R.id.productImage;
            ShapeableImageView productImage = (ShapeableImageView) ViewBindings.findChildViewById(rootView, id);
            if (productImage != null) {
                id = R.id.productName;
                TextView productName = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (productName != null) {
                    id = R.id.productPrice;
                    TextView productPrice = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (productPrice != null) {
                        id = R.id.stock_tv;
                        TextView stockTv = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (stockTv != null) {
                            return new SegmentItemInventoryLowStockBinding((CardView) rootView, addToCart, productImage, productName, productPrice, stockTv);
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
