package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ItemActivityLogBinding implements ViewBinding {
    public final TextView VisitNoteTitle;
    public final LinearLayout booksDelivered;
    public final Button btnViewDetails;
    public final LinearLayout detailsContain;
    public final View divider;
    public final LinearLayout duration;
    public final LinearLayout expandableSection;
    public final ImageView ivArrow;
    public final ImageView ivCall;
    public final LinearLayout leftContainer;
    public final LinearLayout productsContainer;
    public final LinearLayout productsList;
    public final LinearLayout rightContainer;
    private final CardView rootView;
    public final RecyclerView rvProductName;
    public final RecyclerView rvProducts;
    public final TextView tvBadge;
    public final TextView tvCreatedAt;
    public final TextView tvNotes;
    public final TextView tvOrderValue;
    public final TextView tvSchoolInfo;
    public final TextView tvSubject;
    public final TextView tvVisitDate;
    public final TextView tvVisitDuration;
    public final LinearLayout visitNoteContainer;

    private ItemActivityLogBinding(CardView rootView, TextView VisitNoteTitle, LinearLayout booksDelivered, Button btnViewDetails, LinearLayout detailsContain, View divider, LinearLayout duration, LinearLayout expandableSection, ImageView ivArrow, ImageView ivCall, LinearLayout leftContainer, LinearLayout productsContainer, LinearLayout productsList, LinearLayout rightContainer, RecyclerView rvProductName, RecyclerView rvProducts, TextView tvBadge, TextView tvCreatedAt, TextView tvNotes, TextView tvOrderValue, TextView tvSchoolInfo, TextView tvSubject, TextView tvVisitDate, TextView tvVisitDuration, LinearLayout visitNoteContainer) {
        this.rootView = rootView;
        this.VisitNoteTitle = VisitNoteTitle;
        this.booksDelivered = booksDelivered;
        this.btnViewDetails = btnViewDetails;
        this.detailsContain = detailsContain;
        this.divider = divider;
        this.duration = duration;
        this.expandableSection = expandableSection;
        this.ivArrow = ivArrow;
        this.ivCall = ivCall;
        this.leftContainer = leftContainer;
        this.productsContainer = productsContainer;
        this.productsList = productsList;
        this.rightContainer = rightContainer;
        this.rvProductName = rvProductName;
        this.rvProducts = rvProducts;
        this.tvBadge = tvBadge;
        this.tvCreatedAt = tvCreatedAt;
        this.tvNotes = tvNotes;
        this.tvOrderValue = tvOrderValue;
        this.tvSchoolInfo = tvSchoolInfo;
        this.tvSubject = tvSubject;
        this.tvVisitDate = tvVisitDate;
        this.tvVisitDuration = tvVisitDuration;
        this.visitNoteContainer = visitNoteContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemActivityLogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemActivityLogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_activity_log, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemActivityLogBinding bind(View rootView) {
        View divider;
        int id = R.id.VisitNoteTitle;
        TextView VisitNoteTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
        if (VisitNoteTitle != null) {
            id = R.id.booksDelivered;
            LinearLayout booksDelivered = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
            if (booksDelivered != null) {
                id = R.id.btnViewDetails;
                Button btnViewDetails = (Button) ViewBindings.findChildViewById(rootView, id);
                if (btnViewDetails != null) {
                    id = R.id.detailsContain;
                    LinearLayout detailsContain = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                    if (detailsContain != null && (divider = ViewBindings.findChildViewById(rootView, (id = R.id.divider))) != null) {
                        id = R.id.duration;
                        LinearLayout duration = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                        if (duration != null) {
                            id = R.id.expandableSection;
                            LinearLayout expandableSection = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                            if (expandableSection != null) {
                                id = R.id.ivArrow;
                                ImageView ivArrow = (ImageView) ViewBindings.findChildViewById(rootView, id);
                                if (ivArrow != null) {
                                    id = R.id.ivCall;
                                    ImageView ivCall = (ImageView) ViewBindings.findChildViewById(rootView, id);
                                    if (ivCall != null) {
                                        id = R.id.leftContainer;
                                        LinearLayout leftContainer = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                                        if (leftContainer != null) {
                                            id = R.id.productsContainer;
                                            LinearLayout productsContainer = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                                            if (productsContainer != null) {
                                                id = R.id.productsList;
                                                LinearLayout productsList = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                                                if (productsList != null) {
                                                    id = R.id.rightContainer;
                                                    LinearLayout rightContainer = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                                                    if (rightContainer != null) {
                                                        id = R.id.rvProductName;
                                                        RecyclerView rvProductName = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
                                                        if (rvProductName != null) {
                                                            id = R.id.rvProducts;
                                                            RecyclerView rvProducts = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
                                                            if (rvProducts != null) {
                                                                id = R.id.tvBadge;
                                                                TextView tvBadge = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                if (tvBadge != null) {
                                                                    id = R.id.tvCreatedAt;
                                                                    TextView tvCreatedAt = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                    if (tvCreatedAt != null) {
                                                                        id = R.id.tvNotes;
                                                                        TextView tvNotes = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                        if (tvNotes != null) {
                                                                            id = R.id.tvOrderValue;
                                                                            TextView tvOrderValue = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                            if (tvOrderValue != null) {
                                                                                id = R.id.tvSchoolInfo;
                                                                                TextView tvSchoolInfo = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                                if (tvSchoolInfo != null) {
                                                                                    id = R.id.tvSubject;
                                                                                    TextView tvSubject = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                                    if (tvSubject != null) {
                                                                                        id = R.id.tvVisitDate;
                                                                                        TextView tvVisitDate = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                                        if (tvVisitDate != null) {
                                                                                            id = R.id.tvVisitDuration;
                                                                                            TextView tvVisitDuration = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                                                            if (tvVisitDuration != null) {
                                                                                                id = R.id.visitNoteContainer;
                                                                                                LinearLayout visitNoteContainer = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                                                                                                if (visitNoteContainer != null) {
                                                                                                    return new ItemActivityLogBinding((CardView) rootView, VisitNoteTitle, booksDelivered, btnViewDetails, detailsContain, divider, duration, expandableSection, ivArrow, ivCall, leftContainer, productsContainer, productsList, rightContainer, rvProductName, rvProducts, tvBadge, tvCreatedAt, tvNotes, tvOrderValue, tvSchoolInfo, tvSubject, tvVisitDate, tvVisitDuration, visitNoteContainer);
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
