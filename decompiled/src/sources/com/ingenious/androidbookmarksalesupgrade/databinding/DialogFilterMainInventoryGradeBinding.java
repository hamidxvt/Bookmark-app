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
public abstract class DialogFilterMainInventoryGradeBinding extends ViewDataBinding {
    public final TextView allGrade;
    public final AppCompatButton btnClearAll;
    public final Button btnDone;
    public final ImageView ivClose;
    public final TextView year1;
    public final TextView year2;
    public final TextView year3;

    protected DialogFilterMainInventoryGradeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView allGrade, AppCompatButton btnClearAll, Button btnDone, ImageView ivClose, TextView year1, TextView year2, TextView year3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.allGrade = allGrade;
        this.btnClearAll = btnClearAll;
        this.btnDone = btnDone;
        this.ivClose = ivClose;
        this.year1 = year1;
        this.year2 = year2;
        this.year3 = year3;
    }

    public static DialogFilterMainInventoryGradeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventoryGradeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogFilterMainInventoryGradeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main_inventory_grade, root, attachToRoot, component);
    }

    public static DialogFilterMainInventoryGradeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventoryGradeBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogFilterMainInventoryGradeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_filter_main_inventory_grade, null, false, component);
    }

    public static DialogFilterMainInventoryGradeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogFilterMainInventoryGradeBinding bind(View view, Object component) {
        return (DialogFilterMainInventoryGradeBinding) bind(component, view, R.layout.dialog_filter_main_inventory_grade);
    }
}
