package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentData;

/* loaded from: classes13.dex */
public abstract class ItemSegmentsBinding extends ViewDataBinding {
    public final TextView booksCountTv;
    public final MaterialCardView card;
    public final ImageView icTickToggleIv;
    public final LinearLayout linearSegments;

    @Bindable
    protected BooksBySegmentData mItem;

    public abstract void setItem(BooksBySegmentData booksBySegmentData);

    protected ItemSegmentsBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView booksCountTv, MaterialCardView card, ImageView icTickToggleIv, LinearLayout linearSegments) {
        super(_bindingComponent, _root, _localFieldCount);
        this.booksCountTv = booksCountTv;
        this.card = card;
        this.icTickToggleIv = icTickToggleIv;
        this.linearSegments = linearSegments;
    }

    public BooksBySegmentData getItem() {
        return this.mItem;
    }

    public static ItemSegmentsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSegmentsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSegmentsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_segments, root, attachToRoot, component);
    }

    public static ItemSegmentsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSegmentsBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSegmentsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_segments, null, false, component);
    }

    public static ItemSegmentsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSegmentsBinding bind(View view, Object component) {
        return (ItemSegmentsBinding) bind(component, view, R.layout.item_segments);
    }
}
