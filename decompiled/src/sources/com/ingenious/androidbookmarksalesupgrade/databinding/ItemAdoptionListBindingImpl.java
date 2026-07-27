package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.ingenious.androidbookmarksalesupgrade.bindingAdapter.AppBindingAdapters;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionsList;

/* loaded from: classes13.dex */
public class ItemAdoptionListBindingImpl extends ItemAdoptionListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final LinearLayout mboundView1;
    private final ImageView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;

    public ItemAdoptionListBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemAdoptionListBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (ImageView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (TextView) bindings[6];
        this.mboundView6.setTag(null);
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
            setItem((AdoptionsList) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemAdoptionListBinding
    public void setItem(AdoptionsList Item) {
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
        String itemAdoptionName = null;
        AdoptionsList item = this.mItem;
        String itemGetProductCountf = null;
        String itemStatus = null;
        String itemGetTotalQuantityf = null;
        if ((dirtyFlags & 3) != 0 && item != null) {
            itemAdoptionName = item.getAdoptionName();
            itemGetProductCountf = item.getProductCountf();
            itemStatus = item.getStatus();
            itemGetTotalQuantityf = item.getTotalQuantityf();
        }
        if ((3 & dirtyFlags) != 0) {
            AppBindingAdapters.setStatusUI(this.mboundView1, itemStatus);
            AppBindingAdapters.setStatusUI(this.mboundView2, itemStatus);
            TextViewBindingAdapter.setText(this.mboundView3, itemStatus);
            AppBindingAdapters.setStatusUI(this.mboundView3, itemStatus);
            TextViewBindingAdapter.setText(this.mboundView4, itemAdoptionName);
            TextViewBindingAdapter.setText(this.mboundView5, itemGetProductCountf);
            TextViewBindingAdapter.setText(this.mboundView6, itemGetTotalQuantityf);
        }
    }
}
