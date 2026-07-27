package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public class ActivityHomeBindingImpl extends ActivityHomeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.navHostFragment, 1);
        sViewsWithIds.put(R.id.bottom_nav, 2);
        sViewsWithIds.put(R.id.nav_home, 3);
        sViewsWithIds.put(R.id.icon_home, 4);
        sViewsWithIds.put(R.id.homeTxt, 5);
        sViewsWithIds.put(R.id.nav_inventory, 6);
        sViewsWithIds.put(R.id.icon_inventory, 7);
        sViewsWithIds.put(R.id.inventoryTxt, 8);
        sViewsWithIds.put(R.id.nav_customers, 9);
        sViewsWithIds.put(R.id.icon_customers, 10);
        sViewsWithIds.put(R.id.customerTxt, 11);
        sViewsWithIds.put(R.id.nav_activity, 12);
        sViewsWithIds.put(R.id.activity_icon, 13);
        sViewsWithIds.put(R.id.activityTxt, 14);
        sViewsWithIds.put(R.id.nav_performance, 15);
        sViewsWithIds.put(R.id.performance, 16);
        sViewsWithIds.put(R.id.performanceTxt, 17);
    }

    public ActivityHomeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private ActivityHomeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[13], (TextView) bindings[14], (LinearLayout) bindings[2], (TextView) bindings[11], (TextView) bindings[5], (ImageView) bindings[10], (ImageView) bindings[4], (ImageView) bindings[7], (TextView) bindings[8], (ConstraintLayout) bindings[0], (LinearLayout) bindings[12], (LinearLayout) bindings[9], (LinearLayout) bindings[3], (FragmentContainerView) bindings[1], (LinearLayout) bindings[6], (LinearLayout) bindings[15], (ImageView) bindings[16], (TextView) bindings[17]);
        this.mDirtyFlags = -1L;
        this.main.setTag(null);
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
