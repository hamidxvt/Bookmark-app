package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class SegmentItemLowStockProductsBinding implements ViewBinding {
    public final ImageView addIv;
    public final TextView firstQuantity;
    public final TextView productName;
    public final TextView productPrice;
    public final TextView productQuantity;
    private final LinearLayout rootView;
    public final TextView secondQuantity;
    public final ImageView subtractIv;
    public final TextView thirdQuantity;

    private SegmentItemLowStockProductsBinding(LinearLayout rootView, ImageView addIv, TextView firstQuantity, TextView productName, TextView productPrice, TextView productQuantity, TextView secondQuantity, ImageView subtractIv, TextView thirdQuantity) {
        this.rootView = rootView;
        this.addIv = addIv;
        this.firstQuantity = firstQuantity;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.secondQuantity = secondQuantity;
        this.subtractIv = subtractIv;
        this.thirdQuantity = thirdQuantity;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SegmentItemLowStockProductsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SegmentItemLowStockProductsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.segment_item_low_stock_products, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static SegmentItemLowStockProductsBinding bind(View rootView) {
        int id = R.id.add_iv;
        ImageView addIv = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (addIv != null) {
            id = R.id.first_quantity;
            TextView firstQuantity = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (firstQuantity != null) {
                id = R.id.product_name;
                TextView productName = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (productName != null) {
                    id = R.id.product_price;
                    TextView productPrice = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (productPrice != null) {
                        id = R.id.productQuantity;
                        TextView productQuantity = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (productQuantity != null) {
                            id = R.id.second_quantity;
                            TextView secondQuantity = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (secondQuantity != null) {
                                id = R.id.subtract_iv;
                                ImageView subtractIv = (ImageView) ViewBindings.findChildViewById(rootView, id);
                                if (subtractIv != null) {
                                    id = R.id.third_quantity;
                                    TextView thirdQuantity = (TextView) ViewBindings.findChildViewById(rootView, id);
                                    if (thirdQuantity != null) {
                                        return new SegmentItemLowStockProductsBinding((LinearLayout) rootView, addIv, firstQuantity, productName, productPrice, productQuantity, secondQuantity, subtractIv, thirdQuantity);
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
