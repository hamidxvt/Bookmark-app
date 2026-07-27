package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.Summary;

/* loaded from: classes13.dex */
public class FragmentCustomerBindingImpl extends FragmentCustomerBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback5;
    private final View.OnClickListener mCallback6;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    static {
        sViewsWithIds.put(R.id.performanceMenu, 6);
        sViewsWithIds.put(R.id.inventory_search_et, 7);
        sViewsWithIds.put(R.id.cancel_tv, 8);
        sViewsWithIds.put(R.id.customers_list_filter_rv, 9);
        sViewsWithIds.put(R.id.all_type_layout, 10);
        sViewsWithIds.put(R.id.customer_type_all, 11);
        sViewsWithIds.put(R.id.school_type_layout, 12);
        sViewsWithIds.put(R.id.customer_type_school, 13);
        sViewsWithIds.put(R.id.shop_type_layout, 14);
        sViewsWithIds.put(R.id.customer_type_bookshop, 15);
        sViewsWithIds.put(R.id.customerListRv, 16);
    }

    public FragmentCustomerBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private FragmentCustomerBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[10], (TextView) bindings[8], (RecyclerView) bindings[16], (TextView) bindings[11], (TextView) bindings[15], (TextView) bindings[13], (RecyclerView) bindings[9], (ImageView) bindings[5], (EditText) bindings[7], (ImageView) bindings[6], (LinearLayout) bindings[12], (LinearLayout) bindings[14]);
        this.mDirtyFlags = -1L;
        this.filterIconIv.setTag(null);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (TextView) bindings[4];
        this.mboundView4.setTag(null);
        setRootTag(root);
        this.mCallback6 = new OnClickListener(this, 2);
        this.mCallback5 = new OnClickListener(this, 1);
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
            setItem((Summary) variable);
            return true;
        }
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCustomerBinding
    public void setItem(Summary Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCustomerBinding
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
        Summary item = this.mItem;
        String stringValueOfItemTotalBookshops = null;
        Integer itemTotalSchools = null;
        GenericListeners genericListeners = this.mListener;
        Integer itemTotalBookshops = null;
        Integer itemTotalCustomers = null;
        String stringValueOfItemTotalCustomers = null;
        String stringValueOfItemTotalSchools = null;
        if ((dirtyFlags & 5) != 0) {
            if (item != null) {
                itemTotalSchools = item.getTotalSchools();
                itemTotalBookshops = item.getTotalBookshops();
                itemTotalCustomers = item.getTotalCustomers();
            }
            int androidxDatabindingViewDataBindingSafeUnboxItemTotalSchools = ViewDataBinding.safeUnbox(itemTotalSchools);
            int androidxDatabindingViewDataBindingSafeUnboxItemTotalBookshops = ViewDataBinding.safeUnbox(itemTotalBookshops);
            int androidxDatabindingViewDataBindingSafeUnboxItemTotalCustomers = ViewDataBinding.safeUnbox(itemTotalCustomers);
            stringValueOfItemTotalSchools = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemTotalSchools);
            stringValueOfItemTotalBookshops = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemTotalBookshops);
            stringValueOfItemTotalCustomers = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemTotalCustomers);
        }
        if ((dirtyFlags & 4) != 0) {
            this.filterIconIv.setOnClickListener(this.mCallback6);
            this.mboundView1.setOnClickListener(this.mCallback5);
        }
        if ((dirtyFlags & 5) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, stringValueOfItemTotalCustomers);
            TextViewBindingAdapter.setText(this.mboundView3, stringValueOfItemTotalSchools);
            TextViewBindingAdapter.setText(this.mboundView4, stringValueOfItemTotalBookshops);
        }
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean listenerJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                GenericListeners listener = this.mListener;
                listenerJavaLangObjectNull = listener != null;
                if (listenerJavaLangObjectNull) {
                    listener.onTapFilter();
                    break;
                }
                break;
            case 2:
                GenericListeners listener2 = this.mListener;
                listenerJavaLangObjectNull = listener2 != null;
                if (listenerJavaLangObjectNull) {
                    listener2.onTapFilter();
                    break;
                }
                break;
        }
    }
}
