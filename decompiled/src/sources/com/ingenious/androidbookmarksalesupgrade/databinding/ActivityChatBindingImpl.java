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
import com.google.android.material.card.MaterialCardView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.MessageListResponse;

/* loaded from: classes13.dex */
public class ActivityChatBindingImpl extends ActivityChatBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback8;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.layoutHeader, 2);
        sViewsWithIds.put(R.id.cancel, 3);
        sViewsWithIds.put(R.id.tvName, 4);
        sViewsWithIds.put(R.id.call, 5);
        sViewsWithIds.put(R.id.recyclerView, 6);
        sViewsWithIds.put(R.id.layoutFooter, 7);
        sViewsWithIds.put(R.id.etMsg, 8);
        sViewsWithIds.put(R.id.mic, 9);
    }

    public ActivityChatBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivityChatBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (MaterialCardView) bindings[5], (ImageView) bindings[3], (EditText) bindings[8], (ImageView) bindings[1], (LinearLayout) bindings[7], (LinearLayout) bindings[2], (RelativeLayout) bindings[0], (ImageView) bindings[9], (RecyclerView) bindings[6], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.imgMsgSend.setTag(null);
        this.main.setTag(null);
        setRootTag(root);
        this.mCallback8 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
            setItem((MessageListResponse) variable);
            return true;
        }
        if (1 == variableId) {
            setHeaderName((String) variable);
            return true;
        }
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityChatBinding
    public void setItem(MessageListResponse Item) {
        this.mItem = Item;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityChatBinding
    public void setHeaderName(String HeaderName) {
        this.mHeaderName = HeaderName;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityChatBinding
    public void setListener(GenericListeners Listener) {
        this.mListener = Listener;
        synchronized (this) {
            this.mDirtyFlags |= 4;
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
        if ((8 & dirtyFlags) != 0) {
            this.imgMsgSend.setOnClickListener(this.mCallback8);
        }
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        GenericListeners listener = this.mListener;
        boolean listenerJavaLangObjectNull = listener != null;
        if (listenerJavaLangObjectNull) {
            listener.onTapSendMessage();
        }
    }
}
