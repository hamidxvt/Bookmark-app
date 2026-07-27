package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public class FragmentCheckIn1BindingImpl extends FragmentCheckIn1Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final ScrollView mboundView0;

    static {
        sViewsWithIds.put(R.id.customer_type, 1);
        sViewsWithIds.put(R.id.visit_type, 2);
        sViewsWithIds.put(R.id.priority_tv, 3);
        sViewsWithIds.put(R.id.tvTitle, 4);
        sViewsWithIds.put(R.id.reason_tv, 5);
        sViewsWithIds.put(R.id.location_tv, 6);
        sViewsWithIds.put(R.id.locationVerified, 7);
        sViewsWithIds.put(R.id.tvLocationVerifiedMessage, 8);
        sViewsWithIds.put(R.id.locationNotVerified, 9);
        sViewsWithIds.put(R.id.tvLocationNotVerifiedMessage, 10);
        sViewsWithIds.put(R.id.locationChecking, 11);
        sViewsWithIds.put(R.id.tvLocationCheckingMessage, 12);
        sViewsWithIds.put(R.id.locationStatusTv, 13);
        sViewsWithIds.put(R.id.btnContinue1, 14);
    }

    public FragmentCheckIn1BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }

    private FragmentCheckIn1BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (MaterialButton) bindings[14], (TextView) bindings[1], (LinearLayout) bindings[11], (LinearLayout) bindings[9], (TextView) bindings[13], (TextView) bindings[6], (LinearLayout) bindings[7], (TextView) bindings[3], (TextView) bindings[5], (TextView) bindings[12], (TextView) bindings[10], (TextView) bindings[8], (TextView) bindings[4], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (ScrollView) bindings[0];
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
