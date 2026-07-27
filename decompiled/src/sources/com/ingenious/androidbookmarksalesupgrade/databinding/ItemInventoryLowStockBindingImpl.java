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
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;

/* loaded from: classes13.dex */
public class ItemInventoryLowStockBindingImpl extends ItemInventoryLowStockBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final CardView mboundView0;

    static {
        sViewsWithIds.put(R.id.productImage, 3);
        sViewsWithIds.put(R.id.addToCart, 4);
        sViewsWithIds.put(R.id.productPrice, 5);
    }

    public ItemInventoryLowStockBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemInventoryLowStockBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[4], (ShapeableImageView) bindings[3], (TextView) bindings[2], (TextView) bindings[5], (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.productName.setTag(null);
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

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemInventoryLowStockBinding
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
        String itemProductName = null;
        Products item = this.mItem;
        String stringValueOfItemQuantity = null;
        String itemQuantity = null;
        if ((dirtyFlags & 3) != 0) {
            if (item != null) {
                itemProductName = item.getProductName();
                itemQuantity = item.getQuantity();
            }
            stringValueOfItemQuantity = String.valueOf(itemQuantity);
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.productName, itemProductName);
            TextViewBindingAdapter.setText(this.stockTv, stringValueOfItemQuantity);
        }
    }
}
