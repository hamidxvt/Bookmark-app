package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.card.MaterialCardView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;

/* loaded from: classes13.dex */
public class ItemGradesSubjectsBindingImpl extends ItemGradesSubjectsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final TextView mboundView1;

    static {
        sViewsWithIds.put(R.id.linear_segments, 2);
        sViewsWithIds.put(R.id.ic_tick_toggle_iv, 3);
    }

    public ItemGradesSubjectsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemGradesSubjectsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (MaterialCardView) bindings[0], (ImageView) bindings[3], (LinearLayout) bindings[2]);
        this.mDirtyFlags = -1L;
        this.card.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
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
            setItem((GradesSubjectsData) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemGradesSubjectsBinding
    public void setItem(GradesSubjectsData Item) {
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
        String itemTitle = null;
        String itemName = null;
        GradesSubjectsData item = this.mItem;
        boolean itemTitleJavaLangObjectNull = false;
        String itemTitleJavaLangObjectNullItemTitleItemName = null;
        if ((dirtyFlags & 3) != 0) {
            if (item != null) {
                itemTitle = item.getTitle();
            }
            itemTitleJavaLangObjectNull = itemTitle != null;
            if ((dirtyFlags & 3) != 0) {
                dirtyFlags = itemTitleJavaLangObjectNull ? dirtyFlags | 8 : dirtyFlags | 4;
            }
        }
        if ((dirtyFlags & 4) != 0 && item != null) {
            itemName = item.getName();
        }
        if ((dirtyFlags & 3) != 0) {
            itemTitleJavaLangObjectNullItemTitleItemName = itemTitleJavaLangObjectNull ? itemTitle : itemName;
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, itemTitleJavaLangObjectNullItemTitleItemName);
        }
    }
}
