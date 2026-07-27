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
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public class DialogFilterMainBindingImpl extends DialogFilterMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final CardView mboundView0;

    static {
        sViewsWithIds.put(R.id.tvFilterTitle, 1);
        sViewsWithIds.put(R.id.ivClose, 2);
        sViewsWithIds.put(R.id.btnPriority, 3);
        sViewsWithIds.put(R.id.btnDistance, 4);
        sViewsWithIds.put(R.id.btnCustomerType, 5);
        sViewsWithIds.put(R.id.btnAddedBy, 6);
        sViewsWithIds.put(R.id.areaLinear, 7);
        sViewsWithIds.put(R.id.lastVisitLinear, 8);
        sViewsWithIds.put(R.id.adoptions_switch, 9);
        sViewsWithIds.put(R.id.switch_btn, 10);
        sViewsWithIds.put(R.id.btnClearAll, 11);
        sViewsWithIds.put(R.id.btnDone, 12);
    }

    public DialogFilterMainBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private DialogFilterMainBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[9], (LinearLayout) bindings[7], (LinearLayout) bindings[6], (AppCompatButton) bindings[11], (LinearLayout) bindings[5], (LinearLayout) bindings[4], (Button) bindings[12], (LinearLayout) bindings[3], (ImageView) bindings[2], (LinearLayout) bindings[8], (SwitchMaterial) bindings[10], (TextView) bindings[1]);
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
