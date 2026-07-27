package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class NewItemSegmentBinding implements ViewBinding {
    public final TextView bookCount;
    public final TextView booksCountTv;
    public final LinearLayout linearSegments;
    private final CardView rootView;
    public final TextView segment;

    private NewItemSegmentBinding(CardView rootView, TextView bookCount, TextView booksCountTv, LinearLayout linearSegments, TextView segment) {
        this.rootView = rootView;
        this.bookCount = bookCount;
        this.booksCountTv = booksCountTv;
        this.linearSegments = linearSegments;
        this.segment = segment;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static NewItemSegmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static NewItemSegmentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.new_item_segment, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static NewItemSegmentBinding bind(View rootView) {
        int id = R.id.bookCount;
        TextView bookCount = (TextView) ViewBindings.findChildViewById(rootView, id);
        if (bookCount != null) {
            id = R.id.books_count_tv;
            TextView booksCountTv = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (booksCountTv != null) {
                id = R.id.linear_segments;
                LinearLayout linearSegments = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
                if (linearSegments != null) {
                    id = R.id.segment;
                    TextView segment = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (segment != null) {
                        return new NewItemSegmentBinding((CardView) rootView, bookCount, booksCountTv, linearSegments, segment);
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
