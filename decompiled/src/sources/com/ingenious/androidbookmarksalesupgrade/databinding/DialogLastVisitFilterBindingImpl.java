package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public class DialogLastVisitFilterBindingImpl extends DialogLastVisitFilterBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final CardView mboundView0;

    static {
        sViewsWithIds.put(R.id.area_cross_iv, 1);
        sViewsWithIds.put(R.id.today_tv, 2);
        sViewsWithIds.put(R.id.this_week_tv, 3);
        sViewsWithIds.put(R.id.last_30_days_tv, 4);
        sViewsWithIds.put(R.id.plus_30_days_ago_tv, 5);
        sViewsWithIds.put(R.id.never_visited_tv, 6);
        sViewsWithIds.put(R.id.last_visit_cancel_btn, 7);
        sViewsWithIds.put(R.id.last_visit_done_btn, 8);
    }

    public DialogLastVisitFilterBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private DialogLastVisitFilterBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1], (TextView) bindings[4], (AppCompatButton) bindings[7], (Button) bindings[8], (TextView) bindings[6], (TextView) bindings[5], (TextView) bindings[3], (TextView) bindings[2]);
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
