package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ActivityCreateRequestBinding implements ViewBinding {
    public final ImageView backButton;
    public final MaterialButton btnAdoption;
    public final MaterialButton btnAppIssue;
    public final MaterialButton btnInventory;
    public final MaterialButton btnInvoice;
    public final MaterialButton btnOther;
    public final Button btnSubmit;
    public final FlexboxLayout categoryContainer;
    public final RecyclerView imageRecycler;
    public final EditText inputDetails;
    public final EditText inputTitle;
    public final ScrollView main;
    public final ProgressBar progressBar;
    private final ScrollView rootView;
    public final TextView tvUploadPhoto;
    public final LinearLayout uploadContainer;

    private ActivityCreateRequestBinding(ScrollView rootView, ImageView backButton, MaterialButton btnAdoption, MaterialButton btnAppIssue, MaterialButton btnInventory, MaterialButton btnInvoice, MaterialButton btnOther, Button btnSubmit, FlexboxLayout categoryContainer, RecyclerView imageRecycler, EditText inputDetails, EditText inputTitle, ScrollView main, ProgressBar progressBar, TextView tvUploadPhoto, LinearLayout uploadContainer) {
        this.rootView = rootView;
        this.backButton = backButton;
        this.btnAdoption = btnAdoption;
        this.btnAppIssue = btnAppIssue;
        this.btnInventory = btnInventory;
        this.btnInvoice = btnInvoice;
        this.btnOther = btnOther;
        this.btnSubmit = btnSubmit;
        this.categoryContainer = categoryContainer;
        this.imageRecycler = imageRecycler;
        this.inputDetails = inputDetails;
        this.inputTitle = inputTitle;
        this.main = main;
        this.progressBar = progressBar;
        this.tvUploadPhoto = tvUploadPhoto;
        this.uploadContainer = uploadContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static ActivityCreateRequestBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCreateRequestBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_create_request, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityCreateRequestBinding bind(View rootView) {
        int id = R.id.backButton;
        ImageView backButton = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backButton != null) {
            id = R.id.btnAdoption;
            MaterialButton btnAdoption = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
            if (btnAdoption != null) {
                id = R.id.btnAppIssue;
                MaterialButton btnAppIssue = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
                if (btnAppIssue != null) {
                    id = R.id.btnInventory;
                    MaterialButton btnInventory = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
                    if (btnInventory != null) {
                        id = R.id.btnInvoice;
                        MaterialButton btnInvoice = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
                        if (btnInvoice != null) {
                            id = R.id.btnOther;
                            MaterialButton btnOther = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
                            if (btnOther != null) {
                                id = R.id.btnSubmit;
                                Button btnSubmit = (Button) ViewBindings.findChildViewById(rootView, id);
                                if (btnSubmit != null) {
                                    id = R.id.categoryContainer;
                                    FlexboxLayout categoryContainer = (FlexboxLayout) ViewBindings.findChildViewById(rootView, id);
                                    if (categoryContainer != null) {
                                        id = R.id.imageRecycler;
                                        RecyclerView imageRecycler = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
                                        if (imageRecycler != null) {
                                            id = R.id.inputDetails;
                                            EditText inputDetails = (EditText) ViewBindings.findChildViewById(rootView, id);
                                            if (inputDetails != null) {
                                                id = R.id.inputTitle;
                                                EditText inputTitle = (EditText) ViewBindings.findChildViewById(rootView, id);
                                                if (inputTitle != null) {
                                                    ScrollView main = (ScrollView) rootView;
                                                    id = R.id.progressBar;
                                                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, id);
                                                    if (progressBar != null) {
                                                        id = R.id.tvUploadPhoto;
                                                        TextView tvUploadPhoto = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                        if (tvUploadPhoto != null) {
                                                            id = R.id.uploadContainer;
                                                            LinearLayout uploadContainer = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                                                            if (uploadContainer != null) {
                                                                return new ActivityCreateRequestBinding((ScrollView) rootView, backButton, btnAdoption, btnAppIssue, btnInventory, btnInvoice, btnOther, btnSubmit, categoryContainer, imageRecycler, inputDetails, inputTitle, main, progressBar, tvUploadPhoto, uploadContainer);
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
