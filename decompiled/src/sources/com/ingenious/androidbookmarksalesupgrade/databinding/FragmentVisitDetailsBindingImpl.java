package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.chip.Chip;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomerDetails;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetails;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsResponse;

/* loaded from: classes13.dex */
public class FragmentVisitDetailsBindingImpl extends FragmentVisitDetailsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;

    static {
        sViewsWithIds.put(R.id.requestToEdit, 8);
    }

    public FragmentVisitDetailsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private FragmentVisitDetailsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Chip) bindings[8]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (TextView) bindings[7];
        this.mboundView7.setTag(null);
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
            setItem((VisitDetailsResponse) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitDetailsBinding
    public void setItem(VisitDetailsResponse Item) {
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
        String itemVisitDetailsReason;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Integer itemVisitsCount = null;
        VisitDetailsResponse item = this.mItem;
        String stringValueOfItemVisitsCount = null;
        CustomerDetails itemVisitDetailsCustomerDetails = null;
        String itemVisitDetailsSpecialInstruction = null;
        String itemVisitDetailsCustomerDetailsAddress = null;
        String stringValueOfItemSampleVisitsCount = null;
        String itemVisitDetailsCustomerDetailsAssignBy = null;
        String itemVisitDetailsCustomerDetailsContact = null;
        Integer itemSampleVisitsCount = null;
        String itemVisitDetailsReason2 = null;
        VisitDetails itemVisitDetails = null;
        if ((dirtyFlags & 3) == 0) {
            itemVisitDetailsReason = null;
        } else {
            if (item != null) {
                itemVisitsCount = item.getVisitsCount();
                itemSampleVisitsCount = item.getSampleVisitsCount();
                itemVisitDetails = item.getVisitDetails();
            }
            int androidxDatabindingViewDataBindingSafeUnboxItemVisitsCount = ViewDataBinding.safeUnbox(itemVisitsCount);
            int androidxDatabindingViewDataBindingSafeUnboxItemSampleVisitsCount = ViewDataBinding.safeUnbox(itemSampleVisitsCount);
            if (itemVisitDetails != null) {
                itemVisitDetailsCustomerDetails = itemVisitDetails.getCustomerDetails();
                itemVisitDetailsSpecialInstruction = itemVisitDetails.getSpecial_instruction();
                itemVisitDetailsReason2 = itemVisitDetails.getReason();
            }
            stringValueOfItemVisitsCount = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemVisitsCount);
            stringValueOfItemSampleVisitsCount = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemSampleVisitsCount);
            if (itemVisitDetailsCustomerDetails == null) {
                itemVisitDetailsReason = itemVisitDetailsReason2;
            } else {
                itemVisitDetailsCustomerDetailsAddress = itemVisitDetailsCustomerDetails.getAddress();
                itemVisitDetailsCustomerDetailsAssignBy = itemVisitDetailsCustomerDetails.getAssignBy();
                itemVisitDetailsCustomerDetailsContact = itemVisitDetailsCustomerDetails.getContact();
                itemVisitDetailsReason = itemVisitDetailsReason2;
            }
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, itemVisitDetailsCustomerDetailsAddress);
            TextViewBindingAdapter.setText(this.mboundView2, itemVisitDetailsCustomerDetailsContact);
            TextViewBindingAdapter.setText(this.mboundView3, itemVisitDetailsReason);
            TextViewBindingAdapter.setText(this.mboundView4, itemVisitDetailsCustomerDetailsAssignBy);
            TextViewBindingAdapter.setText(this.mboundView5, itemVisitDetailsSpecialInstruction);
            TextViewBindingAdapter.setText(this.mboundView6, stringValueOfItemVisitsCount);
            TextViewBindingAdapter.setText(this.mboundView7, stringValueOfItemSampleVisitsCount);
        }
    }
}
