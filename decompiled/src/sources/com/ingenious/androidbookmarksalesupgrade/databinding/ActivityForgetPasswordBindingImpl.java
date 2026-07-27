package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public class ActivityForgetPasswordBindingImpl extends ActivityForgetPasswordBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback28;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.layoutProgressIndicator, 2);
        sViewsWithIds.put(R.id.backIcon, 3);
        sViewsWithIds.put(R.id.tvWelcome, 4);
        sViewsWithIds.put(R.id.subText, 5);
        sViewsWithIds.put(R.id.forget_email, 6);
        sViewsWithIds.put(R.id.emailLayout, 7);
        sViewsWithIds.put(R.id.etEmail, 8);
    }

    public ActivityForgetPasswordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityForgetPasswordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[3], (Button) bindings[1], (TextInputLayout) bindings[7], (TextInputEditText) bindings[8], (TextView) bindings[6], bindings[2] != null ? LayoutLoadingBinding.bind((View) bindings[2]) : null, (ConstraintLayout) bindings[0], (TextView) bindings[5], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.btnLogin.setTag(null);
        this.main.setTag(null);
        setRootTag(root);
        this.mCallback28 = new OnClickListener(this, 1);
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
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityForgetPasswordBinding
    public void setListener(GenericListeners Listener) {
        this.mListener = Listener;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(3);
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
        GenericListeners genericListeners = this.mListener;
        if ((2 & dirtyFlags) != 0) {
            this.btnLogin.setOnClickListener(this.mCallback28);
        }
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        GenericListeners listener = this.mListener;
        boolean listenerJavaLangObjectNull = listener != null;
        if (listenerJavaLangObjectNull) {
            listener.onTapForgetPassword();
        }
    }
}
