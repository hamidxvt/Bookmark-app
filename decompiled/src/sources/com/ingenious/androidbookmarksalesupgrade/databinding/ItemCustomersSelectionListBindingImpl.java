package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.ingenious.androidbookmarksalesupgrade.bindingAdapter.AppBindingAdapters;
import com.ingenious.androidbookmarksalesupgrade.model.response.SearchData;

/* loaded from: classes13.dex */
public class ItemCustomersSelectionListBindingImpl extends ItemCustomersSelectionListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    public ItemCustomersSelectionListBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemCustomersSelectionListBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (CardView) bindings[0], (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.lastVisitLayout.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.tvCustomer.setTag(null);
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
            setItem((SearchData) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemCustomersSelectionListBinding
    public void setItem(SearchData Item) {
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
        String itemCustomerTypeJavaLangObjectNullItemCustomerTypeJavaLangStringBackendNameNull = null;
        String itemCustomerType = null;
        String itemCreatedAt = null;
        SearchData item = this.mItem;
        boolean itemCustomerTypeJavaLangObjectNull = false;
        String itemBusinessName = null;
        String itemShopAddress = null;
        if ((dirtyFlags & 3) != 0) {
            if (item != null) {
                itemCustomerType = item.getCustomerType();
                itemCreatedAt = item.getCreatedAt();
                itemBusinessName = item.getBusinessName();
                itemShopAddress = item.getShopAddress();
            }
            itemCustomerTypeJavaLangObjectNull = itemCustomerType != null;
            if ((dirtyFlags & 3) != 0) {
                dirtyFlags = itemCustomerTypeJavaLangObjectNull ? dirtyFlags | 8 : dirtyFlags | 4;
            }
        }
        if ((dirtyFlags & 3) != 0) {
            itemCustomerTypeJavaLangObjectNullItemCustomerTypeJavaLangStringBackendNameNull = itemCustomerTypeJavaLangObjectNull ? itemCustomerType : "Backend Name Null";
        }
        if ((3 & dirtyFlags) != 0) {
            AppBindingAdapters.setRelativeTime(this.mboundView2, itemCreatedAt);
            TextViewBindingAdapter.setText(this.mboundView3, itemBusinessName);
            TextViewBindingAdapter.setText(this.mboundView4, itemShopAddress);
            TextViewBindingAdapter.setText(this.tvCustomer, itemCustomerTypeJavaLangObjectNullItemCustomerTypeJavaLangStringBackendNameNull);
            AppBindingAdapters.setCustomerStyle(this.tvCustomer, itemCustomerType);
        }
    }
}
