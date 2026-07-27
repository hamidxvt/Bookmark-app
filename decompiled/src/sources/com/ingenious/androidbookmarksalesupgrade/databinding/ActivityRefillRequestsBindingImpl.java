package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public class ActivityRefillRequestsBindingImpl extends ActivityRefillRequestsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.back, 1);
        sViewsWithIds.put(R.id.tabLayout, 2);
        sViewsWithIds.put(R.id.detailsTab, 3);
        sViewsWithIds.put(R.id.samplesTab, 4);
        sViewsWithIds.put(R.id.historyTab, 5);
        sViewsWithIds.put(R.id.viewPager, 6);
    }

    public ActivityRefillRequestsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ActivityRefillRequestsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (AppCompatImageButton) bindings[1], (TabItem) bindings[3], (TabItem) bindings[5], (LinearLayout) bindings[0], (TabItem) bindings[4], (TabLayout) bindings[2], (ViewPager2) bindings[6]);
        this.mDirtyFlags = -1L;
        this.main.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        if (1 == variableId) {
            setHeaderName((String) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityRefillRequestsBinding
    public void setListener(GenericListeners Listener) {
        this.mListener = Listener;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityRefillRequestsBinding
    public void setHeaderName(String HeaderName) {
        this.mHeaderName = HeaderName;
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
