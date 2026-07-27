package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class BottomSheetRefillRequestProductDetailsBinding extends ViewDataBinding {
    public final RecyclerView booksListRv;
    public final ImageView crossBtn;
    public final TextView dateTv;
    public final RecyclerView imageRv;
    public final TextView notesTv;
    public final TextView requestedId;
    public final TextView totalTv;

    protected BottomSheetRefillRequestProductDetailsBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView booksListRv, ImageView crossBtn, TextView dateTv, RecyclerView imageRv, TextView notesTv, TextView requestedId, TextView totalTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.booksListRv = booksListRv;
        this.crossBtn = crossBtn;
        this.dateTv = dateTv;
        this.imageRv = imageRv;
        this.notesTv = notesTv;
        this.requestedId = requestedId;
        this.totalTv = totalTv;
    }

    public static BottomSheetRefillRequestProductDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetRefillRequestProductDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BottomSheetRefillRequestProductDetailsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_refill_request_product_details, root, attachToRoot, component);
    }

    public static BottomSheetRefillRequestProductDetailsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetRefillRequestProductDetailsBinding inflate(LayoutInflater inflater, Object component) {
        return (BottomSheetRefillRequestProductDetailsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_refill_request_product_details, null, false, component);
    }

    public static BottomSheetRefillRequestProductDetailsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetRefillRequestProductDetailsBinding bind(View view, Object component) {
        return (BottomSheetRefillRequestProductDetailsBinding) bind(component, view, R.layout.bottom_sheet_refill_request_product_details);
    }
}
