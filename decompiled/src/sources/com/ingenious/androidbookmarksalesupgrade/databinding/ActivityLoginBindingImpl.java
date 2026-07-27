package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;

/* loaded from: classes13.dex */
public class ActivityLoginBindingImpl extends ActivityLoginBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback41;
    private final View.OnClickListener mCallback42;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.layoutProgressIndicator, 3);
        sViewsWithIds.put(R.id.tvWelcome, 4);
        sViewsWithIds.put(R.id.tvSubtitle, 5);
        sViewsWithIds.put(R.id.tvEmployeeId, 6);
        sViewsWithIds.put(R.id.etEmployeeId, 7);
        sViewsWithIds.put(R.id.tvPassword, 8);
        sViewsWithIds.put(R.id.passwordLayout, 9);
        sViewsWithIds.put(R.id.etPassword, 10);
        sViewsWithIds.put(R.id.cbRemember, 11);
    }

    public ActivityLoginBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ActivityLoginBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[2], (CheckBox) bindings[11], (EditText) bindings[7], (TextInputEditText) bindings[10], bindings[3] != null ? LayoutLoadingBinding.bind((View) bindings[3]) : null, (LinearLayout) bindings[0], (TextInputLayout) bindings[9], (TextView) bindings[6], (TextView) bindings[1], (TextView) bindings[8], (TextView) bindings[5], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.btnLogin.setTag(null);
        this.main.setTag(null);
        this.tvForgotPassword.setTag(null);
        setRootTag(root);
        this.mCallback41 = new OnClickListener(this, 1);
        this.mCallback42 = new OnClickListener(this, 2);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        if (2 == variableId) {
            setItem((GlobalResponse) variable);
            return true;
        }
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLoginBinding
    public void setItem(GlobalResponse Item) {
        this.mItem = Item;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLoginBinding
    public void setListener(GenericListeners Listener) {
        this.mListener = Listener;
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        if ((4 & dirtyFlags) != 0) {
            this.btnLogin.setOnClickListener(this.mCallback42);
            this.tvForgotPassword.setOnClickListener(this.mCallback41);
        }
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean listenerJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                GenericListeners listener = this.mListener;
                listenerJavaLangObjectNull = listener != null;
                if (listenerJavaLangObjectNull) {
                    listener.onTapForgetPassword();
                    break;
                }
                break;
            case 2:
                GenericListeners listener2 = this.mListener;
                listenerJavaLangObjectNull = listener2 != null;
                if (listenerJavaLangObjectNull) {
                    listener2.onTapLogin();
                    break;
                }
                break;
        }
    }
}
