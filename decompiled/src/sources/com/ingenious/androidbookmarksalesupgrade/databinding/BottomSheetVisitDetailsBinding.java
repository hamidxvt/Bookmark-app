package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class BottomSheetVisitDetailsBinding implements ViewBinding {
    public final LinearLayout bottomSheetRoot;
    public final ImageView cancel;
    public final ShapeableImageView imgBook;
    public final ImageView invoiceImage;
    public final FrameLayout invoicePdf;
    public final RecyclerView recyclerVisitImages;
    private final ScrollView rootView;
    public final TextView tvBookTitle;
    public final TextView tvInvoiceNo;
    public final TextView tvOrderValue;
    public final TextView tvVisitDate;
    public final TextView tvVisitDuration;
    public final TextView tvVisitNotes;

    private BottomSheetVisitDetailsBinding(ScrollView rootView, LinearLayout bottomSheetRoot, ImageView cancel, ShapeableImageView imgBook, ImageView invoiceImage, FrameLayout invoicePdf, RecyclerView recyclerVisitImages, TextView tvBookTitle, TextView tvInvoiceNo, TextView tvOrderValue, TextView tvVisitDate, TextView tvVisitDuration, TextView tvVisitNotes) {
        this.rootView = rootView;
        this.bottomSheetRoot = bottomSheetRoot;
        this.cancel = cancel;
        this.imgBook = imgBook;
        this.invoiceImage = invoiceImage;
        this.invoicePdf = invoicePdf;
        this.recyclerVisitImages = recyclerVisitImages;
        this.tvBookTitle = tvBookTitle;
        this.tvInvoiceNo = tvInvoiceNo;
        this.tvOrderValue = tvOrderValue;
        this.tvVisitDate = tvVisitDate;
        this.tvVisitDuration = tvVisitDuration;
        this.tvVisitNotes = tvVisitNotes;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static BottomSheetVisitDetailsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetVisitDetailsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.bottom_sheet_visit_details, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static BottomSheetVisitDetailsBinding bind(View rootView) {
        int id = R.id.bottomSheetRoot;
        LinearLayout bottomSheetRoot = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
        if (bottomSheetRoot != null) {
            id = R.id.cancel;
            ImageView cancel = (ImageView) ViewBindings.findChildViewById(rootView, id);
            if (cancel != null) {
                id = R.id.imgBook;
                ShapeableImageView imgBook = (ShapeableImageView) ViewBindings.findChildViewById(rootView, id);
                if (imgBook != null) {
                    id = R.id.invoiceImage;
                    ImageView invoiceImage = (ImageView) ViewBindings.findChildViewById(rootView, id);
                    if (invoiceImage != null) {
                        id = R.id.invoicePdf;
                        FrameLayout invoicePdf = (FrameLayout) ViewBindings.findChildViewById(rootView, id);
                        if (invoicePdf != null) {
                            id = R.id.recyclerVisitImages;
                            RecyclerView recyclerVisitImages = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
                            if (recyclerVisitImages != null) {
                                id = R.id.tvBookTitle;
                                TextView tvBookTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                                if (tvBookTitle != null) {
                                    id = R.id.tvInvoiceNo;
                                    TextView tvInvoiceNo = (TextView) ViewBindings.findChildViewById(rootView, id);
                                    if (tvInvoiceNo != null) {
                                        id = R.id.tvOrderValue;
                                        TextView tvOrderValue = (TextView) ViewBindings.findChildViewById(rootView, id);
                                        if (tvOrderValue != null) {
                                            id = R.id.tvVisitDate;
                                            TextView tvVisitDate = (TextView) ViewBindings.findChildViewById(rootView, id);
                                            if (tvVisitDate != null) {
                                                id = R.id.tvVisitDuration;
                                                TextView tvVisitDuration = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                if (tvVisitDuration != null) {
                                                    id = R.id.tvVisitNotes;
                                                    TextView tvVisitNotes = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                    if (tvVisitNotes != null) {
                                                        return new BottomSheetVisitDetailsBinding((ScrollView) rootView, bottomSheetRoot, cancel, imgBook, invoiceImage, invoicePdf, recyclerVisitImages, tvBookTitle, tvInvoiceNo, tvOrderValue, tvVisitDate, tvVisitDuration, tvVisitNotes);
                                                    }
                                                }
                                            }
                                        }
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
