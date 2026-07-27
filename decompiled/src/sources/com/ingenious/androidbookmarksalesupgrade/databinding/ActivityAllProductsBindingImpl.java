package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public class ActivityAllProductsBindingImpl extends ActivityAllProductsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback3;
    private final View.OnClickListener mCallback4;
    private long mDirtyFlags;
    private final ImageView mboundView1;

    static {
        sViewsWithIds.put(R.id.layoutProgressIndicator, 3);
        sViewsWithIds.put(R.id.topBar, 4);
        sViewsWithIds.put(R.id.iv_back, 5);
        sViewsWithIds.put(R.id.inventory_search_et, 6);
        sViewsWithIds.put(R.id.all_book_text, 7);
        sViewsWithIds.put(R.id.books_count, 8);
        sViewsWithIds.put(R.id.search_icon, 9);
        sViewsWithIds.put(R.id.products_rv, 10);
        sViewsWithIds.put(R.id.product_count, 11);
    }

    public ActivityAllProductsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ActivityAllProductsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[7], (TextView) bindings[8], (EditText) bindings[6], (ImageView) bindings[5], bindings[3] != null ? LayoutLoadingBinding.bind((View) bindings[3]) : null, (RelativeLayout) bindings[0], (TextView) bindings[11], (RecyclerView) bindings[10], (MaterialButton) bindings[2], (ImageView) bindings[9], (LinearLayout) bindings[4]);
        this.mDirtyFlags = -1L;
        this.main.setTag(null);
        this.mboundView1 = (ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.productsViewSelection.setTag(null);
        setRootTag(root);
        this.mCallback3 = new OnClickListener(this, 1);
        this.mCallback4 = new OnClickListener(this, 2);
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

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAllProductsBinding
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
            this.mboundView1.setOnClickListener(this.mCallback3);
            this.productsViewSelection.setOnClickListener(this.mCallback4);
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
                    listener.onTapFilter();
                    break;
                }
                break;
            case 2:
                GenericListeners listener2 = this.mListener;
                listenerJavaLangObjectNull = listener2 != null;
                if (listenerJavaLangObjectNull) {
                    listener2.onTapViewSelection();
                    break;
                }
                break;
        }
    }
}
