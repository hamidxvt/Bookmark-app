package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ItemMonthHeaderBinding implements ViewBinding {
    private final TextView rootView;
    public final TextView tvMonthHeader;

    private ItemMonthHeaderBinding(TextView rootView, TextView tvMonthHeader) {
        this.rootView = rootView;
        this.tvMonthHeader = tvMonthHeader;
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextView getRoot() {
        return this.rootView;
    }

    public static ItemMonthHeaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemMonthHeaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_month_header, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemMonthHeaderBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        TextView tvMonthHeader = (TextView) rootView;
        return new ItemMonthHeaderBinding((TextView) rootView, tvMonthHeader);
    }
}
