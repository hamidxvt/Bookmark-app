package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.imageview.ShapeableImageView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public class ItemAllProductsCartBindingImpl extends ItemAllProductsCartBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.product_image, 1);
        sViewsWithIds.put(R.id.product_name, 2);
        sViewsWithIds.put(R.id.grade, 3);
        sViewsWithIds.put(R.id.subject, 4);
        sViewsWithIds.put(R.id.product_price, 5);
        sViewsWithIds.put(R.id.btn_minus, 6);
        sViewsWithIds.put(R.id.tv_quantity, 7);
        sViewsWithIds.put(R.id.btn_plus, 8);
        sViewsWithIds.put(R.id.delete_product_iv, 9);
    }

    public ItemAllProductsCartBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemAllProductsCartBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[6], (ImageView) bindings[8], (LinearLayout) bindings[9], (TextView) bindings[3], (ShapeableImageView) bindings[1], (TextView) bindings[2], (TextView) bindings[5], (TextView) bindings[4], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
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
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
    }
}
