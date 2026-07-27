package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionDetailsData;

/* loaded from: classes13.dex */
public abstract class BottomSheetAdoptionDetailsFragmentBinding extends ViewDataBinding {
    public final ImageView ivClose;
    public final LinearLayout layoutInfo;
    public final LinearLayout layoutInfo2;

    @Bindable
    protected AdoptionDetailsData mItem;
    public final RecyclerView quantityRv;
    public final RecyclerView rvBookImages;
    public final TextView tvAcademicYear;
    public final TextView tvAddedBy;
    public final TextView tvBooksLabel;
    public final TextView tvDate;
    public final TextView tvGrades;
    public final TextView tvGradesLabel;
    public final TextView tvNotes;
    public final TextView tvNotesLabel;
    public final TextView tvSelection;
    public final TextView tvSelectionLabel;
    public final TextView tvSubjects;
    public final TextView tvSubjectsLabel;
    public final TextView tvTotalBooks;
    public final TextView tvTotalQty;

    public abstract void setItem(AdoptionDetailsData adoptionDetailsData);

    protected BottomSheetAdoptionDetailsFragmentBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivClose, LinearLayout layoutInfo, LinearLayout layoutInfo2, RecyclerView quantityRv, RecyclerView rvBookImages, TextView tvAcademicYear, TextView tvAddedBy, TextView tvBooksLabel, TextView tvDate, TextView tvGrades, TextView tvGradesLabel, TextView tvNotes, TextView tvNotesLabel, TextView tvSelection, TextView tvSelectionLabel, TextView tvSubjects, TextView tvSubjectsLabel, TextView tvTotalBooks, TextView tvTotalQty) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivClose = ivClose;
        this.layoutInfo = layoutInfo;
        this.layoutInfo2 = layoutInfo2;
        this.quantityRv = quantityRv;
        this.rvBookImages = rvBookImages;
        this.tvAcademicYear = tvAcademicYear;
        this.tvAddedBy = tvAddedBy;
        this.tvBooksLabel = tvBooksLabel;
        this.tvDate = tvDate;
        this.tvGrades = tvGrades;
        this.tvGradesLabel = tvGradesLabel;
        this.tvNotes = tvNotes;
        this.tvNotesLabel = tvNotesLabel;
        this.tvSelection = tvSelection;
        this.tvSelectionLabel = tvSelectionLabel;
        this.tvSubjects = tvSubjects;
        this.tvSubjectsLabel = tvSubjectsLabel;
        this.tvTotalBooks = tvTotalBooks;
        this.tvTotalQty = tvTotalQty;
    }

    public AdoptionDetailsData getItem() {
        return this.mItem;
    }

    public static BottomSheetAdoptionDetailsFragmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetAdoptionDetailsFragmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BottomSheetAdoptionDetailsFragmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_adoption_details_fragment, root, attachToRoot, component);
    }

    public static BottomSheetAdoptionDetailsFragmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetAdoptionDetailsFragmentBinding inflate(LayoutInflater inflater, Object component) {
        return (BottomSheetAdoptionDetailsFragmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_adoption_details_fragment, null, false, component);
    }

    public static BottomSheetAdoptionDetailsFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetAdoptionDetailsFragmentBinding bind(View view, Object component) {
        return (BottomSheetAdoptionDetailsFragmentBinding) bind(component, view, R.layout.bottom_sheet_adoption_details_fragment);
    }
}
