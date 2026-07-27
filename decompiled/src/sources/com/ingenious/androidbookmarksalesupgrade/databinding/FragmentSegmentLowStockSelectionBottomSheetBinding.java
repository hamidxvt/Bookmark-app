package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class FragmentSegmentLowStockSelectionBottomSheetBinding implements ViewBinding {
    public final ImageView arrowBack;
    public final TextView cancelButton;
    public final TextView confirmButton;
    public final EditText notesEditText;
    public final RecyclerView productRecyclerView;
    private final NestedScrollView rootView;
    public final TextView totalPrice;

    private FragmentSegmentLowStockSelectionBottomSheetBinding(NestedScrollView rootView, ImageView arrowBack, TextView cancelButton, TextView confirmButton, EditText notesEditText, RecyclerView productRecyclerView, TextView totalPrice) {
        this.rootView = rootView;
        this.arrowBack = arrowBack;
        this.cancelButton = cancelButton;
        this.confirmButton = confirmButton;
        this.notesEditText = notesEditText;
        this.productRecyclerView = productRecyclerView;
        this.totalPrice = totalPrice;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentSegmentLowStockSelectionBottomSheetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSegmentLowStockSelectionBottomSheetBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.fragment_segment_low_stock_selection_bottom_sheet, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static FragmentSegmentLowStockSelectionBottomSheetBinding bind(View rootView) {
        int id = R.id.arrow_back;
        ImageView arrowBack = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (arrowBack != null) {
            id = R.id.cancelButton;
            TextView cancelButton = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (cancelButton != null) {
                id = R.id.confirmButton;
                TextView confirmButton = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (confirmButton != null) {
                    id = R.id.notesEditText;
                    EditText notesEditText = (EditText) ViewBindings.findChildViewById(rootView, id);
                    if (notesEditText != null) {
                        id = R.id.productRecyclerView;
                        RecyclerView productRecyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
                        if (productRecyclerView != null) {
                            id = R.id.totalPrice;
                            TextView totalPrice = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (totalPrice != null) {
                                return new FragmentSegmentLowStockSelectionBottomSheetBinding((NestedScrollView) rootView, arrowBack, cancelButton, confirmButton, notesEditText, productRecyclerView, totalPrice);
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
