package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public class FragmentVisitAdoption1BindingImpl extends FragmentVisitAdoption1Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.adoption_tv, 1);
        sViewsWithIds.put(R.id.adoption_list_tv, 2);
        sViewsWithIds.put(R.id.adoption_name_tv, 3);
        sViewsWithIds.put(R.id.adoption_name_et, 4);
        sViewsWithIds.put(R.id.date_tv, 5);
        sViewsWithIds.put(R.id.date_et, 6);
        sViewsWithIds.put(R.id.note_tv, 7);
        sViewsWithIds.put(R.id.note_et, 8);
        sViewsWithIds.put(R.id.btn_continue, 9);
    }

    public FragmentVisitAdoption1BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FragmentVisitAdoption1BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[2], (EditText) bindings[4], (TextView) bindings[3], (TextView) bindings[1], (Button) bindings[9], (EditText) bindings[6], (TextView) bindings[5], (EditText) bindings[8], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (RelativeLayout) bindings[0];
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
