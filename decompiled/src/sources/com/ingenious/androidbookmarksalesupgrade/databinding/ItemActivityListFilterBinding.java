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
public final class ItemActivityListFilterBinding implements ViewBinding {
    public final TextView name;
    private final LinearLayout rootView;

    private ItemActivityListFilterBinding(LinearLayout rootView, TextView name) {
        this.rootView = rootView;
        this.name = name;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemActivityListFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemActivityListFilterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_activity_list_filter, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemActivityListFilterBinding bind(View rootView) {
        int id = R.id.name;
        TextView name = (TextView) ViewBindings.findChildViewById(rootView, id);
        if (name != null) {
            return new ItemActivityListFilterBinding((LinearLayout) rootView, name);
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
