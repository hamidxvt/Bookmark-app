package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public class FragmentVisitAdoptionQuantityBindingImpl extends FragmentVisitAdoptionQuantityBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final FrameLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.layoutMain, 1);
        sViewsWithIds.put(R.id.select_segment_tv, 2);
        sViewsWithIds.put(R.id.total_results_linear, 3);
        sViewsWithIds.put(R.id.total_results, 4);
        sViewsWithIds.put(R.id.segments_rv, 5);
        sViewsWithIds.put(R.id.btn_create_adoption, 6);
        sViewsWithIds.put(R.id.progress_bar, 7);
    }

    public FragmentVisitAdoptionQuantityBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private FragmentVisitAdoptionQuantityBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[6], (RelativeLayout) bindings[1], (ProgressBar) bindings[7], (RecyclerView) bindings[5], (TextView) bindings[2], (TextView) bindings[4], (LinearLayout) bindings[3]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (FrameLayout) bindings[0];
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
