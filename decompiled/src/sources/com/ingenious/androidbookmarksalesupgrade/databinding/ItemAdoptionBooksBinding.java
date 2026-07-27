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
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;

/* loaded from: classes13.dex */
public abstract class ItemAdoptionBooksBinding extends ViewDataBinding {
    public final ShapeableImageView adoptionBookIv;
    public final ImageView ivSelection;
    public final LinearLayout linearSegments;

    @Bindable
    protected AdoptionBooksData mItem;
    public final TextView productName;

    public abstract void setItem(AdoptionBooksData adoptionBooksData);

    protected ItemAdoptionBooksBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeableImageView adoptionBookIv, ImageView ivSelection, LinearLayout linearSegments, TextView productName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.adoptionBookIv = adoptionBookIv;
        this.ivSelection = ivSelection;
        this.linearSegments = linearSegments;
        this.productName = productName;
    }

    public AdoptionBooksData getItem() {
        return this.mItem;
    }

    public static ItemAdoptionBooksBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionBooksBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemAdoptionBooksBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_adoption_books, root, attachToRoot, component);
    }

    public static ItemAdoptionBooksBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionBooksBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemAdoptionBooksBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_adoption_books, null, false, component);
    }

    public static ItemAdoptionBooksBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAdoptionBooksBinding bind(View view, Object component) {
        return (ItemAdoptionBooksBinding) bind(component, view, R.layout.item_adoption_books);
    }
}
