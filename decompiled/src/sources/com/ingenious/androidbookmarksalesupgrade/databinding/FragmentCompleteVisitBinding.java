package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.button.MaterialButton;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class FragmentCompleteVisitBinding extends ViewDataBinding {
    public final RecyclerView attachmentRv;
    public final ImageView btnOpenCamera;
    public final MaterialButton btnVisitComplete;
    public final TextView cameraOpen;
    public final ImageView clearBtn;
    public final ImageView imageSet;
    public final LinearLayout linearCameraOpen;

    @Bindable
    protected GenericListeners mListener;
    public final LinearLayout photoBox;
    public final ProgressBar progressBar;
    public final RecyclerView recyclerView;
    public final SignaturePad signaturePad;
    public final EditText title;

    public abstract void setListener(GenericListeners genericListeners);

    protected FragmentCompleteVisitBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView attachmentRv, ImageView btnOpenCamera, MaterialButton btnVisitComplete, TextView cameraOpen, ImageView clearBtn, ImageView imageSet, LinearLayout linearCameraOpen, LinearLayout photoBox, ProgressBar progressBar, RecyclerView recyclerView, SignaturePad signaturePad, EditText title) {
        super(_bindingComponent, _root, _localFieldCount);
        this.attachmentRv = attachmentRv;
        this.btnOpenCamera = btnOpenCamera;
        this.btnVisitComplete = btnVisitComplete;
        this.cameraOpen = cameraOpen;
        this.clearBtn = clearBtn;
        this.imageSet = imageSet;
        this.linearCameraOpen = linearCameraOpen;
        this.photoBox = photoBox;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
        this.signaturePad = signaturePad;
        this.title = title;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static FragmentCompleteVisitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCompleteVisitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCompleteVisitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_complete_visit, root, attachToRoot, component);
    }

    public static FragmentCompleteVisitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCompleteVisitBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCompleteVisitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_complete_visit, null, false, component);
    }

    public static FragmentCompleteVisitBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCompleteVisitBinding bind(View view, Object component) {
        return (FragmentCompleteVisitBinding) bind(component, view, R.layout.fragment_complete_visit);
    }
}
