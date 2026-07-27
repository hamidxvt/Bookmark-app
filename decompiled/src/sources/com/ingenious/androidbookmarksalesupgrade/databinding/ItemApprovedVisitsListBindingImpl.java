package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.ingenious.androidbookmarksalesupgrade.model.response.ApprovedVisitsLists;

/* loaded from: classes13.dex */
public class ItemApprovedVisitsListBindingImpl extends ItemApprovedVisitsListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final CardView mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;

    public ItemApprovedVisitsListBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ItemApprovedVisitsListBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (TextView) bindings[7];
        this.mboundView7.setTag(null);
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
            setItem((ApprovedVisitsLists) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemApprovedVisitsListBinding
    public void setItem(ApprovedVisitsLists Item) {
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
        String itemReason;
        String stringValueOfItemSample;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Integer itemImageCount = null;
        ApprovedVisitsLists item = this.mItem;
        String itemVisitDate = null;
        String stringValueOfItemVisitTotal = null;
        String itemType = null;
        Integer itemVisitTotal = null;
        String itemStatus = null;
        Integer itemSample = null;
        String stringValueOfItemImageCount = null;
        String itemReason2 = null;
        if ((dirtyFlags & 3) == 0) {
            itemReason = null;
            stringValueOfItemSample = null;
        } else {
            if (item != null) {
                itemImageCount = item.getImageCount();
                itemVisitDate = item.getVisitDate();
                itemType = item.getType();
                itemVisitTotal = item.getVisitTotal();
                itemStatus = item.getStatus();
                itemSample = item.getSample();
                itemReason2 = item.getReason();
            }
            int androidxDatabindingViewDataBindingSafeUnboxItemImageCount = ViewDataBinding.safeUnbox(itemImageCount);
            int androidxDatabindingViewDataBindingSafeUnboxItemVisitTotal = ViewDataBinding.safeUnbox(itemVisitTotal);
            int androidxDatabindingViewDataBindingSafeUnboxItemSample = ViewDataBinding.safeUnbox(itemSample);
            stringValueOfItemImageCount = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemImageCount);
            stringValueOfItemVisitTotal = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemVisitTotal);
            String stringValueOfItemSample2 = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemSample);
            itemReason = itemReason2;
            stringValueOfItemSample = stringValueOfItemSample2;
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, itemType);
            TextViewBindingAdapter.setText(this.mboundView2, itemVisitDate);
            TextViewBindingAdapter.setText(this.mboundView3, itemStatus);
            TextViewBindingAdapter.setText(this.mboundView4, itemReason);
            TextViewBindingAdapter.setText(this.mboundView5, stringValueOfItemSample);
            TextViewBindingAdapter.setText(this.mboundView6, stringValueOfItemImageCount);
            TextViewBindingAdapter.setText(this.mboundView7, stringValueOfItemVisitTotal);
        }
    }
}
