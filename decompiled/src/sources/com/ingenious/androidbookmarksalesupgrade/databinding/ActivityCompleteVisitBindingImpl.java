package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public class ActivityCompleteVisitBindingImpl extends ActivityCompleteVisitBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback22;
    private final View.OnClickListener mCallback23;
    private final View.OnClickListener mCallback24;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.topBg, 4);
        sViewsWithIds.put(R.id.schoolName, 5);
        sViewsWithIds.put(R.id.logout, 6);
        sViewsWithIds.put(R.id.title, 7);
        sViewsWithIds.put(R.id.linear_camera_open, 8);
        sViewsWithIds.put(R.id.btnOpenCamera, 9);
        sViewsWithIds.put(R.id.cameraOpen, 10);
        sViewsWithIds.put(R.id.attachment_rv, 11);
        sViewsWithIds.put(R.id.selectedProducts, 12);
        sViewsWithIds.put(R.id.pbar_fetchingBooks, 13);
        sViewsWithIds.put(R.id.recyclerView, 14);
        sViewsWithIds.put(R.id.clear_btn, 15);
        sViewsWithIds.put(R.id.signaturePad, 16);
        sViewsWithIds.put(R.id.invoice_type, 17);
        sViewsWithIds.put(R.id.agentName, 18);
        sViewsWithIds.put(R.id.agentId, 19);
        sViewsWithIds.put(R.id.completeVisitTime, 20);
        sViewsWithIds.put(R.id.btnGenerateInvoice, 21);
        sViewsWithIds.put(R.id.progressBar, 22);
    }

    public ActivityCompleteVisitBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private ActivityCompleteVisitBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[2], (TextView) bindings[19], (TextView) bindings[18], (RecyclerView) bindings[11], (MaterialButton) bindings[21], (ImageView) bindings[9], (MaterialButton) bindings[3], (TextView) bindings[10], (ImageView) bindings[15], (TextView) bindings[20], (MaterialAutoCompleteTextView) bindings[17], (LinearLayout) bindings[8], (ImageView) bindings[6], (ConstraintLayout) bindings[0], (ProgressBar) bindings[13], (LinearLayout) bindings[1], (ProgressBar) bindings[22], (RecyclerView) bindings[14], (TextView) bindings[5], (TextView) bindings[12], (SignaturePad) bindings[16], (EditText) bindings[7], (ImageView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.addBooks.setTag(null);
        this.btnVisitComplete.setTag(null);
        this.main.setTag(null);
        this.photoBox.setTag(null);
        setRootTag(root);
        this.mCallback23 = new OnClickListener(this, 2);
        this.mCallback24 = new OnClickListener(this, 3);
        this.mCallback22 = new OnClickListener(this, 1);
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

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityCompleteVisitBinding
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
            this.addBooks.setOnClickListener(this.mCallback23);
            this.btnVisitComplete.setOnClickListener(this.mCallback24);
            this.photoBox.setOnClickListener(this.mCallback22);
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
                    listener.onTapCaptureImage();
                    break;
                }
                break;
            case 2:
                GenericListeners listener2 = this.mListener;
                listenerJavaLangObjectNull = listener2 != null;
                if (listenerJavaLangObjectNull) {
                    listener2.onTapAddBooks();
                    break;
                }
                break;
            case 3:
                GenericListeners listener3 = this.mListener;
                listenerJavaLangObjectNull = listener3 != null;
                if (listenerJavaLangObjectNull) {
                    listener3.onTapCompleteVisit();
                    break;
                }
                break;
        }
    }
}
