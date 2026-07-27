package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class DialogMessageBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvMessage;

    private DialogMessageBinding(LinearLayout rootView, TextView tvMessage) {
        this.rootView = rootView;
        this.tvMessage = tvMessage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogMessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogMessageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.dialog_message, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static DialogMessageBinding bind(View rootView) {
        int id = R.id.tvMessage;
        TextView tvMessage = (TextView) ViewBindings.findChildViewById(rootView, id);
        if (tvMessage != null) {
            return new DialogMessageBinding((LinearLayout) rootView, tvMessage);
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
