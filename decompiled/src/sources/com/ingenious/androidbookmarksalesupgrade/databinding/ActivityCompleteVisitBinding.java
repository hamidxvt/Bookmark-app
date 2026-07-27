package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class ActivityCompleteVisitBinding extends ViewDataBinding {
    public final TextView addBooks;
    public final TextView agentId;
    public final TextView agentName;
    public final RecyclerView attachmentRv;
    public final MaterialButton btnGenerateInvoice;
    public final ImageView btnOpenCamera;
    public final MaterialButton btnVisitComplete;
    public final TextView cameraOpen;
    public final ImageView clearBtn;
    public final TextView completeVisitTime;
    public final MaterialAutoCompleteTextView invoiceType;
    public final LinearLayout linearCameraOpen;
    public final ImageView logout;

    @Bindable
    protected GenericListeners mListener;
    public final ConstraintLayout main;
    public final ProgressBar pbarFetchingBooks;
    public final LinearLayout photoBox;
    public final ProgressBar progressBar;
    public final RecyclerView recyclerView;
    public final TextView schoolName;
    public final TextView selectedProducts;
    public final SignaturePad signaturePad;
    public final EditText title;
    public final ImageView topBg;

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityCompleteVisitBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView addBooks, TextView agentId, TextView agentName, RecyclerView attachmentRv, MaterialButton btnGenerateInvoice, ImageView btnOpenCamera, MaterialButton btnVisitComplete, TextView cameraOpen, ImageView clearBtn, TextView completeVisitTime, MaterialAutoCompleteTextView invoiceType, LinearLayout linearCameraOpen, ImageView logout, ConstraintLayout main, ProgressBar pbarFetchingBooks, LinearLayout photoBox, ProgressBar progressBar, RecyclerView recyclerView, TextView schoolName, TextView selectedProducts, SignaturePad signaturePad, EditText title, ImageView topBg) {
        super(_bindingComponent, _root, _localFieldCount);
        this.addBooks = addBooks;
        this.agentId = agentId;
        this.agentName = agentName;
        this.attachmentRv = attachmentRv;
        this.btnGenerateInvoice = btnGenerateInvoice;
        this.btnOpenCamera = btnOpenCamera;
        this.btnVisitComplete = btnVisitComplete;
        this.cameraOpen = cameraOpen;
        this.clearBtn = clearBtn;
        this.completeVisitTime = completeVisitTime;
        this.invoiceType = invoiceType;
        this.linearCameraOpen = linearCameraOpen;
        this.logout = logout;
        this.main = main;
        this.pbarFetchingBooks = pbarFetchingBooks;
        this.photoBox = photoBox;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
        this.schoolName = schoolName;
        this.selectedProducts = selectedProducts;
        this.signaturePad = signaturePad;
        this.title = title;
        this.topBg = topBg;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityCompleteVisitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCompleteVisitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityCompleteVisitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_complete_visit, root, attachToRoot, component);
    }

    public static ActivityCompleteVisitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCompleteVisitBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityCompleteVisitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_complete_visit, null, false, component);
    }

    public static ActivityCompleteVisitBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCompleteVisitBinding bind(View view, Object component) {
        return (ActivityCompleteVisitBinding) bind(component, view, R.layout.activity_complete_visit);
    }
}
