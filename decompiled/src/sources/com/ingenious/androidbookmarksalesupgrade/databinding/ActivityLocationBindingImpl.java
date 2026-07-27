package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.gms.maps.MapView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public class ActivityLocationBindingImpl extends ActivityLocationBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(6);
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback39;
    private final View.OnClickListener mCallback40;
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"layout_header"}, new int[]{3}, new int[]{R.layout.layout_header});
        sViewsWithIds = new SparseIntArray();
        sViewsWithIds.put(R.id.mapView, 4);
        sViewsWithIds.put(R.id.address, 5);
    }

    public ActivityLocationBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ActivityLocationBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (MaterialCardView) bindings[5], (Button) bindings[2], (FloatingActionButton) bindings[1], (LayoutHeaderBinding) bindings[3], (LinearLayout) bindings[0], (MapView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.continueButton.setTag(null);
        this.currentLocationButton.setTag(null);
        setContainedBinding(this.layoutHeader);
        this.main.setTag(null);
        setRootTag(root);
        this.mCallback39 = new OnClickListener(this, 1);
        this.mCallback40 = new OnClickListener(this, 2);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLocationBinding
    public void setListener(GenericListeners Listener) {
        this.mListener = Listener;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLocationBinding
    public void setHeaderName(String HeaderName) {
        this.mHeaderName = HeaderName;
        synchronized (this) {
            this.mDirtyFlags |= 4;
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
        if ((8 & dirtyFlags) != 0) {
            this.continueButton.setOnClickListener(this.mCallback40);
            this.currentLocationButton.setOnClickListener(this.mCallback39);
        }
        if ((12 & dirtyFlags) != 0) {
            this.layoutHeader.setHeaderName(headerName);
        }
        if ((10 & dirtyFlags) != 0) {
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
                    listener.onTapLocationFab();
                    break;
                }
                break;
            case 2:
                GenericListeners listener2 = this.mListener;
                listenerJavaLangObjectNull = listener2 != null;
                if (listenerJavaLangObjectNull) {
                    listener2.onTapDone();
                    break;
                }
                break;
        }
    }
}
