package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.bindingAdapter.AppBindingAdapters;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;

/* loaded from: classes13.dex */
public class ItemInventoryProductsBindingImpl extends ItemInventoryProductsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final CardView mboundView0;

    static {
        sViewsWithIds.put(R.id.addToCart, 5);
    }

    public ItemInventoryProductsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemInventoryProductsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[5], (ShapeableImageView) bindings[1], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.productImage.setTag(null);
        this.productName.setTag(null);
        this.productPrice.setTag(null);
        this.stockTv.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (2 == variableId) {
            setItem((Products) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemInventoryProductsBinding
    public void setItem(Products Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Products item = this.mItem;
        String stringValueOfItemStockAvailablee = null;
        String itemStockAvailablee = null;
        String itemProductName = null;
        String itemImage = null;
        String itemPrice = null;
        if ((dirtyFlags & 3) != 0) {
            if (item != null) {
                itemStockAvailablee = item.getStockAvailablee();
                itemProductName = item.getProductName();
                itemImage = item.getImage();
                itemPrice = item.getPrice();
            }
            stringValueOfItemStockAvailablee = String.valueOf(itemStockAvailablee);
        }
        if ((3 & dirtyFlags) != 0) {
            AppBindingAdapters.setImageUsingGlide(this.productImage, itemImage);
            TextViewBindingAdapter.setText(this.productName, itemProductName);
            TextViewBindingAdapter.setText(this.productPrice, itemPrice);
            TextViewBindingAdapter.setText(this.stockTv, stringValueOfItemStockAvailablee);
        }
    }
}
