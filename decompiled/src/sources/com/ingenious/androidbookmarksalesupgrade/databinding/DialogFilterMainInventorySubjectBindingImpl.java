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
public class DialogFilterMainInventorySubjectBindingImpl extends DialogFilterMainInventorySubjectBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final CardView mboundView0;

    static {
        sViewsWithIds.put(R.id.ivClose, 1);
        sViewsWithIds.put(R.id.all, 2);
        sViewsWithIds.put(R.id.artsAndCraft, 3);
        sViewsWithIds.put(R.id.english, 4);
        sViewsWithIds.put(R.id.generalKnowledge, 5);
        sViewsWithIds.put(R.id.islamiat, 6);
        sViewsWithIds.put(R.id.mathematics, 7);
        sViewsWithIds.put(R.id.urdu, 8);
        sViewsWithIds.put(R.id.btnClearAll, 9);
        sViewsWithIds.put(R.id.btnDone, 10);
    }

    public DialogFilterMainInventorySubjectBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private DialogFilterMainInventorySubjectBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[2], (TextView) bindings[3], (AppCompatButton) bindings[9], (Button) bindings[10], (TextView) bindings[4], (TextView) bindings[5], (TextView) bindings[6], (ImageView) bindings[1], (TextView) bindings[7], (TextView) bindings[8]);
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
