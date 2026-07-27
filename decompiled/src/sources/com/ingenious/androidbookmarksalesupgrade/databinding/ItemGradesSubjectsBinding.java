package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;

/* loaded from: classes13.dex */
public abstract class ItemGradesSubjectsBinding extends ViewDataBinding {
    public final MaterialCardView card;
    public final ImageView icTickToggleIv;
    public final LinearLayout linearSegments;

    @Bindable
    protected GradesSubjectsData mItem;

    public abstract void setItem(GradesSubjectsData gradesSubjectsData);

    protected ItemGradesSubjectsBinding(Object _bindingComponent, View _root, int _localFieldCount, MaterialCardView card, ImageView icTickToggleIv, LinearLayout linearSegments) {
        super(_bindingComponent, _root, _localFieldCount);
        this.card = card;
        this.icTickToggleIv = icTickToggleIv;
        this.linearSegments = linearSegments;
    }

    public GradesSubjectsData getItem() {
        return this.mItem;
    }

    public static ItemGradesSubjectsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGradesSubjectsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGradesSubjectsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_grades_subjects, root, attachToRoot, component);
    }

    public static ItemGradesSubjectsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGradesSubjectsBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGradesSubjectsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_grades_subjects, null, false, component);
    }

    public static ItemGradesSubjectsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGradesSubjectsBinding bind(View view, Object component) {
        return (ItemGradesSubjectsBinding) bind(component, view, R.layout.item_grades_subjects);
    }
}
