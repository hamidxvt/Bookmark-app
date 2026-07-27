package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.bindingAdapter.AppBindingAdapters;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;

/* loaded from: classes13.dex */
public class ItemLowStockProductsBindingImpl extends ItemLowStockProductsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ShapeableImageView mboundView1;
    private final TextView mboundView2;

    static {
        sViewsWithIds.put(R.id.subtract_iv, 4);
        sViewsWithIds.put(R.id.productQuantity, 5);
        sViewsWithIds.put(R.id.add_iv, 6);
        sViewsWithIds.put(R.id.first_quantity, 7);
        sViewsWithIds.put(R.id.second_quantity, 8);
        sViewsWithIds.put(R.id.third_quantity, 9);
    }

    public ItemLowStockProductsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemLowStockProductsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[6], (TextView) bindings[7], (TextView) bindings[3], (TextView) bindings[5], (TextView) bindings[8], (ImageView) bindings[4], (TextView) bindings[9]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (ShapeableImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.productPrice.setTag(null);
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

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemLowStockProductsBinding
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
        String itemImage = null;
        Products item = this.mItem;
        String itemPrice = null;
        if ((dirtyFlags & 3) != 0 && item != null) {
            itemProductName = item.getProductName();
            itemImage = item.getImage();
            itemPrice = item.getPrice();
        }
        if ((3 & dirtyFlags) != 0) {
            AppBindingAdapters.setImageUsingGlide(this.mboundView1, itemImage);
            TextViewBindingAdapter.setText(this.mboundView2, itemProductName);
            TextViewBindingAdapter.setText(this.productPrice, itemPrice);
        }
    }
}
