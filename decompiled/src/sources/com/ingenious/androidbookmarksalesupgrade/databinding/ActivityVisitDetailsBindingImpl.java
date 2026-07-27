package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomerDetails;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetails;

/* loaded from: classes13.dex */
public class ActivityVisitDetailsBindingImpl extends ActivityVisitDetailsBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback7;
    private long mDirtyFlags;
    private final TextView mboundView2;
    private final TextView mboundView3;

    static {
        sViewsWithIds.put(R.id.progressIndicator, 7);
        sViewsWithIds.put(R.id.back_arrow, 8);
        sViewsWithIds.put(R.id.customer_linear_type, 9);
        sViewsWithIds.put(R.id.visit_customer_type_iv, 10);
        sViewsWithIds.put(R.id.btnCall, 11);
        sViewsWithIds.put(R.id.btnNavigate, 12);
        sViewsWithIds.put(R.id.tabLayout, 13);
        sViewsWithIds.put(R.id.detailsTab, 14);
        sViewsWithIds.put(R.id.samplesTab, 15);
        sViewsWithIds.put(R.id.historyTab, 16);
        sViewsWithIds.put(R.id.adoptionTab, 17);
        sViewsWithIds.put(R.id.viewPager, 18);
    }

    public ActivityVisitDetailsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private ActivityVisitDetailsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TabItem) bindings[17], (ImageView) bindings[8], (ImageView) bindings[11], (ImageView) bindings[12], (MaterialButton) bindings[6], (LinearLayout) bindings[9], (TextView) bindings[1], (TabItem) bindings[14], (TabItem) bindings[16], (LinearLayout) bindings[0], bindings[7] != null ? LayoutLoadingBinding.bind((View) bindings[7]) : null, (TabItem) bindings[15], (TabLayout) bindings[13], (TextView) bindings[4], (TextView) bindings[5], (ViewPager2) bindings[18], (ImageView) bindings[10]);
        this.mDirtyFlags = -1L;
        this.checkInBtn.setTag(null);
        this.customerType.setTag(null);
        this.main.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.tvDistance.setTag(null);
        this.tvDuration.setTag(null);
        setRootTag(root);
        this.mCallback7 = new OnClickListener(this, 1);
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
            setItem((VisitDetails) variable);
            return true;
        }
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityVisitDetailsBinding
    public void setItem(VisitDetails Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityVisitDetailsBinding
    public void setListener(GenericListeners Listener) {
        this.mListener = Listener;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
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
        CustomerDetails itemCustomerDetails = null;
        VisitDetails item = this.mItem;
        GenericListeners genericListeners = this.mListener;
        Integer itemDistanceKm = null;
        String itemReason = null;
        String itemCustomerType = null;
        String itemEstTime = null;
        String itemCustomerDetailsName = null;
        String stringValueOfItemDistanceKm = null;
        if ((dirtyFlags & 5) != 0) {
            if (item != null) {
                itemCustomerDetails = item.getCustomerDetails();
                itemDistanceKm = item.getDistanceKm();
                itemReason = item.getReason();
                itemCustomerType = item.getCustomerType();
                itemEstTime = item.getEstTime();
            }
            if (itemCustomerDetails != null) {
                itemCustomerDetailsName = itemCustomerDetails.getName();
            }
            int androidxDatabindingViewDataBindingSafeUnboxItemDistanceKm = ViewDataBinding.safeUnbox(itemDistanceKm);
            stringValueOfItemDistanceKm = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemDistanceKm);
        }
        if ((dirtyFlags & 4) != 0) {
            this.checkInBtn.setOnClickListener(this.mCallback7);
        }
        if ((dirtyFlags & 5) != 0) {
            TextViewBindingAdapter.setText(this.customerType, itemCustomerType);
            TextViewBindingAdapter.setText(this.mboundView2, itemCustomerDetailsName);
            TextViewBindingAdapter.setText(this.mboundView3, itemReason);
            TextViewBindingAdapter.setText(this.tvDistance, stringValueOfItemDistanceKm);
            TextViewBindingAdapter.setText(this.tvDuration, itemEstTime);
        }
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        GenericListeners listener = this.mListener;
        boolean listenerJavaLangObjectNull = listener != null;
        if (listenerJavaLangObjectNull) {
            listener.onTapCheckIn();
        }
    }
}
