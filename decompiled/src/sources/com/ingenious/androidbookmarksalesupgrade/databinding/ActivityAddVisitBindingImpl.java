package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.LastVisitCustomerData;

/* loaded from: classes13.dex */
public class ActivityAddVisitBindingImpl extends ActivityAddVisitBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(20);
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback32;
    private final View.OnClickListener mCallback33;
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"layout_header"}, new int[]{4}, new int[]{R.layout.layout_header});
        sViewsWithIds = new SparseIntArray();
        sViewsWithIds.put(R.id.layoutProgressIndicator, 3);
        sViewsWithIds.put(R.id.content, 5);
        sViewsWithIds.put(R.id.school_type_layout, 6);
        sViewsWithIds.put(R.id.customer_type_school, 7);
        sViewsWithIds.put(R.id.shop_type_layout, 8);
        sViewsWithIds.put(R.id.customer_type_bookshop, 9);
        sViewsWithIds.put(R.id.customer_name_tv, 10);
        sViewsWithIds.put(R.id.customer_name_et, 11);
        sViewsWithIds.put(R.id.date_tv, 12);
        sViewsWithIds.put(R.id.location_tv, 13);
        sViewsWithIds.put(R.id.purpose_et, 14);
        sViewsWithIds.put(R.id.linear_priority, 15);
        sViewsWithIds.put(R.id.priority_low, 16);
        sViewsWithIds.put(R.id.priority_medium, 17);
        sViewsWithIds.put(R.id.priority_high, 18);
        sViewsWithIds.put(R.id.customerListRv, 19);
    }

    public ActivityAddVisitBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }

    private ActivityAddVisitBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (MaterialButton) bindings[2], (NestedScrollView) bindings[5], (RecyclerView) bindings[19], (EditText) bindings[11], (TextView) bindings[10], (TextView) bindings[9], (TextView) bindings[7], (EditText) bindings[1], (TextView) bindings[12], (LayoutHeaderBinding) bindings[4], bindings[3] != null ? LayoutLoadingBinding.bind((View) bindings[3]) : null, (LinearLayout) bindings[15], (TextView) bindings[13], (LinearLayout) bindings[0], (TextView) bindings[18], (TextView) bindings[16], (TextView) bindings[17], (EditText) bindings[14], (LinearLayout) bindings[6], (LinearLayout) bindings[8]);
        this.mDirtyFlags = -1L;
        this.btnAddCustomer.setTag(null);
        this.dateEt.setTag(null);
        setContainedBinding(this.layoutHeader);
        this.main.setTag(null);
        setRootTag(root);
        this.mCallback33 = new OnClickListener(this, 2);
        this.mCallback32 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        this.layoutHeader.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.layoutHeader.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (2 == variableId) {
            setItem((LastVisitCustomerData) variable);
            return true;
        }
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        if (1 == variableId) {
            setHeaderName((String) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAddVisitBinding
    public void setItem(LastVisitCustomerData Item) {
        this.mItem = Item;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAddVisitBinding
    public void setListener(GenericListeners Listener) {
        this.mListener = Listener;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAddVisitBinding
    public void setHeaderName(String HeaderName) {
        this.mHeaderName = HeaderName;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.layoutHeader.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLayoutHeader((LayoutHeaderBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLayoutHeader(LayoutHeaderBinding LayoutHeader, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GenericListeners listener = this.mListener;
        String headerName = this.mHeaderName;
        if ((16 & dirtyFlags) != 0) {
            this.btnAddCustomer.setOnClickListener(this.mCallback33);
            this.dateEt.setOnClickListener(this.mCallback32);
        }
        if ((24 & dirtyFlags) != 0) {
            this.layoutHeader.setHeaderName(headerName);
        }
        if ((20 & dirtyFlags) != 0) {
            this.layoutHeader.setListener(listener);
        }
        executeBindingsOn(this.layoutHeader);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean listenerJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                GenericListeners listener = this.mListener;
                listenerJavaLangObjectNull = listener != null;
                if (listenerJavaLangObjectNull) {
                    listener.onTapDate();
                    break;
                }
                break;
            case 2:
                GenericListeners listener2 = this.mListener;
                listenerJavaLangObjectNull = listener2 != null;
                if (listenerJavaLangObjectNull) {
                    listener2.onTapAddVisit();
                    break;
                }
                break;
        }
    }
}
