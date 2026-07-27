package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public class DialogDistanceFilterBindingImpl extends DialogDistanceFilterBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final CardView mboundView0;

    static {
        sViewsWithIds.put(R.id.distance_cross_iv, 1);
        sViewsWithIds.put(R.id.closest_linear, 2);
        sViewsWithIds.put(R.id.closest_tv, 3);
        sViewsWithIds.put(R.id.unselected_circle, 4);
        sViewsWithIds.put(R.id.selected_circle, 5);
        sViewsWithIds.put(R.id.farthest_linear, 6);
        sViewsWithIds.put(R.id.farthest_tv, 7);
        sViewsWithIds.put(R.id.unselected_circle_farthest, 8);
        sViewsWithIds.put(R.id.selected_circle_farthest, 9);
        sViewsWithIds.put(R.id.distance_cancel_btn, 10);
        sViewsWithIds.put(R.id.distance_done_btn, 11);
    }

    public DialogDistanceFilterBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private DialogDistanceFilterBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[2], (TextView) bindings[3], (AppCompatButton) bindings[10], (ImageView) bindings[1], (Button) bindings[11], (LinearLayout) bindings[6], (TextView) bindings[7], (ImageView) bindings[5], (ImageView) bindings[9], (LinearLayout) bindings[4], (LinearLayout) bindings[8]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (CardView) bindings[0];
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
