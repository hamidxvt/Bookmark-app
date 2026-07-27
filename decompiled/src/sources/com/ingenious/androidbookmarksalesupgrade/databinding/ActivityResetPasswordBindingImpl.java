package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public class ActivityResetPasswordBindingImpl extends ActivityResetPasswordBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback37;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.layoutProgressIndicator, 2);
        sViewsWithIds.put(R.id.password, 3);
        sViewsWithIds.put(R.id.forget_email, 4);
        sViewsWithIds.put(R.id.emailLayout, 5);
        sViewsWithIds.put(R.id.etPassword, 6);
        sViewsWithIds.put(R.id.password_confirmation, 7);
        sViewsWithIds.put(R.id.password_confirmation_Layout, 8);
        sViewsWithIds.put(R.id.etPasswordConfirmation, 9);
    }

    public ActivityResetPasswordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivityResetPasswordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[1], (TextInputLayout) bindings[5], (TextInputEditText) bindings[6], (TextInputEditText) bindings[9], (TextView) bindings[4], bindings[2] != null ? LayoutLoadingBinding.bind((View) bindings[2]) : null, (TextView) bindings[3], (TextView) bindings[7], (TextInputLayout) bindings[8]);
        this.mDirtyFlags = -1L;
        this.btnLogin.setTag(null);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        this.mCallback37 = new OnClickListener(this, 1);
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

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityResetPasswordBinding
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
            this.btnLogin.setOnClickListener(this.mCallback37);
        }
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        GenericListeners listener = this.mListener;
        boolean listenerJavaLangObjectNull = listener != null;
        if (listenerJavaLangObjectNull) {
            listener.onTapResetPassword();
        }
    }
}
