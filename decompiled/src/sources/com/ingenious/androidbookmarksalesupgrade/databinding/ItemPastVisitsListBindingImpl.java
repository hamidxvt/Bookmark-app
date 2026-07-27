package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.model.response.PastVisitsList;

/* loaded from: classes13.dex */
public class ItemPastVisitsListBindingImpl extends ItemPastVisitsListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final CardView mboundView0;

    static {
        sViewsWithIds.put(R.id.btnCheckIn, 8);
        sViewsWithIds.put(R.id.btnCall, 9);
        sViewsWithIds.put(R.id.btnNavigate, 10);
    }

    public ItemPastVisitsListBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private ItemPastVisitsListBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageButton) bindings[9], (Button) bindings[8], (ImageButton) bindings[10], (TextView) bindings[1], (TextView) bindings[3], (TextView) bindings[6], (TextView) bindings[7], (TextView) bindings[5], (TextView) bindings[4], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.customerType.setTag(null);
        this.mboundView0 = (CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.priority.setTag(null);
        this.tvDistance.setTag(null);
        this.tvDuration.setTag(null);
        this.tvSubtitle.setTag(null);
        this.tvTitle.setTag(null);
        this.visitType.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
            setItem((PastVisitsList) variable);
            return true;
        }
        if (3 == variableId) {
            setListener((GenericAdapter.OnItemClickListener) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemPastVisitsListBinding
    public void setItem(PastVisitsList Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ItemPastVisitsListBinding
    public void setListener(GenericAdapter.OnItemClickListener Listener) {
        this.mListener = Listener;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String itemCustomerType = null;
        String itemCustomerName = null;
        String itemEstTime = null;
        PastVisitsList item = this.mItem;
        String stringValueOfItemDistanceKm = null;
        String itemPriority = null;
        String itemPurpose = null;
        String itemVisittype = null;
        Integer itemDistanceKm = null;
        if ((dirtyFlags & 5) != 0) {
            if (item != null) {
                itemCustomerType = item.getCustomerType();
                itemCustomerName = item.getCustomerName();
                itemEstTime = item.getEstTime();
                itemPriority = item.getPriority();
                itemPurpose = item.getPurpose();
                itemVisittype = item.getVisittype();
                itemDistanceKm = item.getDistanceKm();
            }
            int androidxDatabindingViewDataBindingSafeUnboxItemDistanceKm = ViewDataBinding.safeUnbox(itemDistanceKm);
            stringValueOfItemDistanceKm = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemDistanceKm);
        }
        if ((5 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.customerType, itemCustomerType);
            TextViewBindingAdapter.setText(this.priority, itemPriority);
            TextViewBindingAdapter.setText(this.tvDistance, stringValueOfItemDistanceKm);
            TextViewBindingAdapter.setText(this.tvDuration, itemEstTime);
            TextViewBindingAdapter.setText(this.tvSubtitle, itemPurpose);
            TextViewBindingAdapter.setText(this.tvTitle, itemCustomerName);
            TextViewBindingAdapter.setText(this.visitType, itemVisittype);
        }
    }
}
