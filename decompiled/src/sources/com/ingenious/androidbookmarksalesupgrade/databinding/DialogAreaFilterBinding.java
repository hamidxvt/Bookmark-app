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
public abstract class DialogAreaFilterBinding extends ViewDataBinding {
    public final TextView areaAll;
    public final AppCompatButton areaCancelBtn;
    public final ImageView areaCrossIv;
    public final TextView areaDha;
    public final Button areaDoneBtn;
    public final TextView areaFb;
    public final TextView areaGulshan;
    public final TextView areaKarimabad;

    protected DialogAreaFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView areaAll, AppCompatButton areaCancelBtn, ImageView areaCrossIv, TextView areaDha, Button areaDoneBtn, TextView areaFb, TextView areaGulshan, TextView areaKarimabad) {
        super(_bindingComponent, _root, _localFieldCount);
        this.areaAll = areaAll;
        this.areaCancelBtn = areaCancelBtn;
        this.areaCrossIv = areaCrossIv;
        this.areaDha = areaDha;
        this.areaDoneBtn = areaDoneBtn;
        this.areaFb = areaFb;
        this.areaGulshan = areaGulshan;
        this.areaKarimabad = areaKarimabad;
    }

    public static DialogAreaFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAreaFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogAreaFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_area_filter, root, attachToRoot, component);
    }

    public static DialogAreaFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAreaFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogAreaFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_area_filter, null, false, component);
    }

    public static DialogAreaFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAreaFilterBinding bind(View view, Object component) {
        return (DialogAreaFilterBinding) bind(component, view, R.layout.dialog_area_filter);
    }
}
