package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooks;

/* loaded from: classes13.dex */
public class ItemAdoptionQuantitiesBindingImpl extends ItemAdoptionQuantitiesBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;

    public ItemAdoptionQuantitiesBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemAdoptionQuantitiesBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
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
            setItem((AdoptionBooks) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemAdoptionQuantitiesBinding
    public void setItem(AdoptionBooks Item) {
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
        AdoptionBooks item = this.mItem;
        String stringValueOfItemQuantity = null;
        String itemBookName = null;
        Integer itemQuantity = null;
        if ((dirtyFlags & 3) != 0) {
            if (item != null) {
                itemBookName = item.getBookName();
                itemQuantity = item.getQuantity();
            }
            int androidxDatabindingViewDataBindingSafeUnboxItemQuantity = ViewDataBinding.safeUnbox(itemQuantity);
            stringValueOfItemQuantity = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemQuantity);
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, itemBookName);
            TextViewBindingAdapter.setText(this.mboundView2, stringValueOfItemQuantity);
        }
    }
}
