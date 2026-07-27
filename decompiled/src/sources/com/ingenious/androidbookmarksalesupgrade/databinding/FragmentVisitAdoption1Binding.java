package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class FragmentVisitAdoption1Binding extends ViewDataBinding {
    public final TextView adoptionListTv;
    public final EditText adoptionNameEt;
    public final TextView adoptionNameTv;
    public final TextView adoptionTv;
    public final Button btnContinue;
    public final EditText dateEt;
    public final TextView dateTv;
    public final EditText noteEt;
    public final TextView noteTv;

    protected FragmentVisitAdoption1Binding(Object _bindingComponent, View _root, int _localFieldCount, TextView adoptionListTv, EditText adoptionNameEt, TextView adoptionNameTv, TextView adoptionTv, Button btnContinue, EditText dateEt, TextView dateTv, EditText noteEt, TextView noteTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.adoptionListTv = adoptionListTv;
        this.adoptionNameEt = adoptionNameEt;
        this.adoptionNameTv = adoptionNameTv;
        this.adoptionTv = adoptionTv;
        this.btnContinue = btnContinue;
        this.dateEt = dateEt;
        this.dateTv = dateTv;
        this.noteEt = noteEt;
        this.noteTv = noteTv;
    }

    public static FragmentVisitAdoption1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoption1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVisitAdoption1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption1, root, attachToRoot, component);
    }

    public static FragmentVisitAdoption1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoption1Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVisitAdoption1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_visit_adoption1, null, false, component);
    }

    public static FragmentVisitAdoption1Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVisitAdoption1Binding bind(View view, Object component) {
        return (FragmentVisitAdoption1Binding) bind(component, view, R.layout.fragment_visit_adoption1);
    }
}
