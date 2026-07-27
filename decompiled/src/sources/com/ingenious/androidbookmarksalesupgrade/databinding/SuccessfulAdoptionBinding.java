package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class SuccessfulAdoptionBinding implements ViewBinding {
    public final AppCompatButton btnCheckAdoptions;
    private final RelativeLayout rootView;
    public final LinearLayout successContainer;
    public final TextView txtSuccessSubtitle;
    public final TextView txtSuccessTitle;

    private SuccessfulAdoptionBinding(RelativeLayout rootView, AppCompatButton btnCheckAdoptions, LinearLayout successContainer, TextView txtSuccessSubtitle, TextView txtSuccessTitle) {
        this.rootView = rootView;
        this.btnCheckAdoptions = btnCheckAdoptions;
        this.successContainer = successContainer;
        this.txtSuccessSubtitle = txtSuccessSubtitle;
        this.txtSuccessTitle = txtSuccessTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static SuccessfulAdoptionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SuccessfulAdoptionBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.successful_adoption, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static SuccessfulAdoptionBinding bind(View rootView) {
        int id = R.id.btnCheckAdoptions;
        AppCompatButton btnCheckAdoptions = (AppCompatButton) ViewBindings.findChildViewById(rootView, id);
        if (btnCheckAdoptions != null) {
            id = R.id.successContainer;
            LinearLayout successContainer = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
            if (successContainer != null) {
                id = R.id.txtSuccessSubtitle;
                TextView txtSuccessSubtitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (txtSuccessSubtitle != null) {
                    id = R.id.txtSuccessTitle;
                    TextView txtSuccessTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (txtSuccessTitle != null) {
                        return new SuccessfulAdoptionBinding((RelativeLayout) rootView, btnCheckAdoptions, successContainer, txtSuccessSubtitle, txtSuccessTitle);
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
