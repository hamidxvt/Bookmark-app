package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.StockSummaryResponse;

/* loaded from: classes13.dex */
public class FragmentInventoryBindingImpl extends FragmentInventoryBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback25;
    private final View.OnClickListener mCallback26;
    private final View.OnClickListener mCallback27;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final LinearLayout mboundView2;
    private final LinearLayout mboundView3;
    private final TextView mboundView4;

    static {
        sViewsWithIds.put(R.id.performanceMenu, 5);
        sViewsWithIds.put(R.id.inventory_search_et, 6);
        sViewsWithIds.put(R.id.cancel_tv, 7);
        sViewsWithIds.put(R.id.lowStockList_rv, 8);
        sViewsWithIds.put(R.id.school_type_layout, 9);
        sViewsWithIds.put(R.id.customer_type_school, 10);
        sViewsWithIds.put(R.id.shop_type_layout, 11);
        sViewsWithIds.put(R.id.customer_type_bookshop, 12);
        sViewsWithIds.put(R.id.allBookMove, 13);
        sViewsWithIds.put(R.id.stock_summary_rv, 14);
        sViewsWithIds.put(R.id.progressBar, 15);
        sViewsWithIds.put(R.id.noDataText, 16);
        sViewsWithIds.put(R.id.segments_rv, 17);
    }

    public FragmentInventoryBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private FragmentInventoryBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[13], (TextView) bindings[7], (TextView) bindings[12], (TextView) bindings[10], (EditText) bindings[6], (RecyclerView) bindings[8], (TextView) bindings[16], (ImageView) bindings[5], (ProgressBar) bindings[15], (LinearLayout) bindings[9], (RecyclerView) bindings[17], (LinearLayout) bindings[11], (RecyclerView) bindings[14]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (LinearLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (LinearLayout) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (TextView) bindings[4];
        this.mboundView4.setTag(null);
        setRootTag(root);
        this.mCallback27 = new OnClickListener(this, 3);
        this.mCallback25 = new OnClickListener(this, 1);
        this.mCallback26 = new OnClickListener(this, 2);
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
            setItem((StockSummaryResponse) variable);
            return true;
        }
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.FragmentInventoryBinding
    public void setItem(StockSummaryResponse Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.FragmentInventoryBinding
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
        StockSummaryResponse item = this.mItem;
        String stringValueOfItemTotalBooksCount = null;
        GenericListeners genericListeners = this.mListener;
        Integer itemTotalBooksCount = null;
        if ((dirtyFlags & 5) != 0) {
            if (item != null) {
                itemTotalBooksCount = item.getTotalBooksCount();
            }
            int androidxDatabindingViewDataBindingSafeUnboxItemTotalBooksCount = ViewDataBinding.safeUnbox(itemTotalBooksCount);
            stringValueOfItemTotalBooksCount = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemTotalBooksCount);
        }
        if ((4 & dirtyFlags) != 0) {
            this.mboundView1.setOnClickListener(this.mCallback25);
            this.mboundView2.setOnClickListener(this.mCallback26);
            this.mboundView3.setOnClickListener(this.mCallback27);
        }
        if ((5 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, stringValueOfItemTotalBooksCount);
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
                    listener2.onTapLowStock();
                    break;
                }
                break;
            case 3:
                GenericListeners listener3 = this.mListener;
                listenerJavaLangObjectNull = listener3 != null;
                if (listenerJavaLangObjectNull) {
                    listener3.onTapRefillRequests();
                    break;
                }
                break;
        }
    }
}
