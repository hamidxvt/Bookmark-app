package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionDetailsData;

/* loaded from: classes13.dex */
public class BottomSheetAdoptionDetailsFragmentBindingImpl extends BottomSheetAdoptionDetailsFragmentBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final ScrollView mboundView0;

    static {
        sViewsWithIds.put(R.id.tvAcademicYear, 9);
        sViewsWithIds.put(R.id.ivClose, 10);
        sViewsWithIds.put(R.id.layoutInfo, 11);
        sViewsWithIds.put(R.id.layoutInfo2, 12);
        sViewsWithIds.put(R.id.tvNotesLabel, 13);
        sViewsWithIds.put(R.id.tvSelectionLabel, 14);
        sViewsWithIds.put(R.id.tvGradesLabel, 15);
        sViewsWithIds.put(R.id.tvSubjectsLabel, 16);
        sViewsWithIds.put(R.id.tvBooksLabel, 17);
        sViewsWithIds.put(R.id.quantity_rv, 18);
        sViewsWithIds.put(R.id.rvBookImages, 19);
    }

    public BottomSheetAdoptionDetailsFragmentBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }

    private BottomSheetAdoptionDetailsFragmentBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[10], (LinearLayout) bindings[11], (LinearLayout) bindings[12], (RecyclerView) bindings[18], (RecyclerView) bindings[19], (TextView) bindings[9], (TextView) bindings[4], (TextView) bindings[17], (TextView) bindings[1], (TextView) bindings[7], (TextView) bindings[15], (TextView) bindings[5], (TextView) bindings[13], (TextView) bindings[6], (TextView) bindings[14], (TextView) bindings[8], (TextView) bindings[16], (TextView) bindings[2], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAddedBy.setTag(null);
        this.tvDate.setTag(null);
        this.tvGrades.setTag(null);
        this.tvNotes.setTag(null);
        this.tvSelection.setTag(null);
        this.tvSubjects.setTag(null);
        this.tvTotalBooks.setTag(null);
        this.tvTotalQty.setTag(null);
        setRootTag(root);
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
        if (2 == variableId) {
            setItem((AdoptionDetailsData) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetAdoptionDetailsFragmentBinding
    public void setItem(AdoptionDetailsData Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String itemGrades;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Integer itemTotalQuantity = null;
        AdoptionDetailsData item = this.mItem;
        String stringValueOfItemTotalBooks = null;
        String itemSubjects = null;
        String stringValueOfItemTotalQuantity = null;
        Integer itemTotalBooks = null;
        String itemNotes = null;
        String itemDate = null;
        String itemAddedBy = null;
        String itemSelectionSummary = null;
        String itemGrades2 = null;
        if ((dirtyFlags & 3) == 0) {
            itemGrades = null;
        } else {
            if (item != null) {
                itemTotalQuantity = item.getTotalQuantity();
                itemSubjects = item.getSubjects();
                itemTotalBooks = item.getTotalBooks();
                itemNotes = item.getNotes();
                itemDate = item.getDate();
                itemAddedBy = item.getAddedBy();
                itemSelectionSummary = item.getSelectionSummary();
                itemGrades2 = item.getGrades();
            }
            int androidxDatabindingViewDataBindingSafeUnboxItemTotalQuantity = ViewDataBinding.safeUnbox(itemTotalQuantity);
            int androidxDatabindingViewDataBindingSafeUnboxItemTotalBooks = ViewDataBinding.safeUnbox(itemTotalBooks);
            stringValueOfItemTotalQuantity = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemTotalQuantity);
            stringValueOfItemTotalBooks = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemTotalBooks);
            itemGrades = itemGrades2;
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.tvAddedBy, itemAddedBy);
            TextViewBindingAdapter.setText(this.tvDate, itemDate);
            TextViewBindingAdapter.setText(this.tvGrades, itemGrades);
            TextViewBindingAdapter.setText(this.tvNotes, itemNotes);
            TextViewBindingAdapter.setText(this.tvSelection, itemSelectionSummary);
            TextViewBindingAdapter.setText(this.tvSubjects, itemSubjects);
            TextViewBindingAdapter.setText(this.tvTotalBooks, stringValueOfItemTotalBooks);
            TextViewBindingAdapter.setText(this.tvTotalQty, stringValueOfItemTotalQuantity);
        }
    }
}
