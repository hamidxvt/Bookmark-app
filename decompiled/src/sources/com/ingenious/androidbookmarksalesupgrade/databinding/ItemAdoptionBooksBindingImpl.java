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
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;

/* loaded from: classes13.dex */
public class ItemAdoptionBooksBindingImpl extends ItemAdoptionBooksBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.linearSegments, 3);
        sViewsWithIds.put(R.id.iv_selection, 4);
    }

    public ItemAdoptionBooksBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemAdoptionBooksBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeableImageView) bindings[1], (ImageView) bindings[4], (LinearLayout) bindings[3], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.adoptionBookIv.setTag(null);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.productName.setTag(null);
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
            setItem((AdoptionBooksData) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemAdoptionBooksBinding
    public void setItem(AdoptionBooksData Item) {
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
        String itemImage = null;
        String itemName = null;
        AdoptionBooksData item = this.mItem;
        if ((dirtyFlags & 3) != 0 && item != null) {
            itemImage = item.getImage();
            itemName = item.getName();
        }
        if ((3 & dirtyFlags) != 0) {
            AppBindingAdapters.setImageUsingGlide(this.adoptionBookIv, itemImage);
            TextViewBindingAdapter.setText(this.productName, itemName);
        }
    }
}
