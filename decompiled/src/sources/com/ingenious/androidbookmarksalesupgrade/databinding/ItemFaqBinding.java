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
public final class ItemFaqBinding implements ViewBinding {
    public final ImageView ivToggle;
    public final LinearLayout layoutQuestion;
    private final LinearLayout rootView;
    public final TextView tvAnswer;
    public final TextView tvQuestion;

    private ItemFaqBinding(LinearLayout rootView, ImageView ivToggle, LinearLayout layoutQuestion, TextView tvAnswer, TextView tvQuestion) {
        this.rootView = rootView;
        this.ivToggle = ivToggle;
        this.layoutQuestion = layoutQuestion;
        this.tvAnswer = tvAnswer;
        this.tvQuestion = tvQuestion;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemFaqBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemFaqBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_faq, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemFaqBinding bind(View rootView) {
        int id = R.id.ivToggle;
        ImageView ivToggle = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (ivToggle != null) {
            id = R.id.layoutQuestion;
            LinearLayout layoutQuestion = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
            if (layoutQuestion != null) {
                id = R.id.tvAnswer;
                TextView tvAnswer = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (tvAnswer != null) {
                    id = R.id.tvQuestion;
                    TextView tvQuestion = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (tvQuestion != null) {
                        return new ItemFaqBinding((LinearLayout) rootView, ivToggle, layoutQuestion, tvAnswer, tvQuestion);
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
