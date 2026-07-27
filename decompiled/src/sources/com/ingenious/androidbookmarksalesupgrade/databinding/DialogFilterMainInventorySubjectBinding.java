package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogFilterMainInventorySubjectBinding extends ViewDataBinding {
    public final TextView all;
    public final TextView artsAndCraft;
    public final AppCompatButton btnClearAll;
    public final Button btnDone;
    public final TextView english;
    public final TextView generalKnowledge;
    public final TextView islamiat;
    public final ImageView ivClose;
    public final TextView mathematics;
    public final TextView urdu;

    protected DialogFilterMainInventorySubjectBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView all, TextView artsAndCraft, AppCompatButton btnClearAll, Button btnDone, TextView english, TextView generalKnowledge, TextView islamiat, ImageView ivClose, TextView mathematics, TextView urdu) {
        super(_bindingComponent, _root, _localFieldCount);
        this.all = all;
        this.artsAndCraft = artsAndCraft;
        this.btnClearAll = btnClearAll;
        this.btnDone = btnDone;
        this.english = english;
        this.generalKnowledge = generalKnowledge;
        this.islamiat = islamiat;
        this.ivClose = ivClose;
        this.mathematics = mathematics;
        this.urdu = urdu;
    }

    public static DialogFilterMainInventorySubjectBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventorySubjectBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogFilterMainInventorySubjectBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main_inventory_subject, root, attachToRoot, component);
    }

    public static DialogFilterMainInventorySubjectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventorySubjectBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogFilterMainInventorySubjectBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main_inventory_subject, null, false, component);
    }

    public static DialogFilterMainInventorySubjectBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventorySubjectBinding bind(View view, Object component) {
        return (DialogFilterMainInventorySubjectBinding) bind(component, view, R.layout.dialog_filter_main_inventory_subject);
    }
}
