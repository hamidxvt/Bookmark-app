package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.StockSummaryResponse;

/* loaded from: classes13.dex */
public abstract class FragmentInventoryBinding extends ViewDataBinding {
    public final TextView allBookMove;
    public final TextView cancelTv;
    public final TextView customerTypeBookshop;
    public final TextView customerTypeSchool;
    public final EditText inventorySearchEt;
    public final RecyclerView lowStockListRv;

    @Bindable
    protected StockSummaryResponse mItem;

    @Bindable
    protected GenericListeners mListener;
    public final TextView noDataText;
    public final ImageView performanceMenu;
    public final ProgressBar progressBar;
    public final LinearLayout schoolTypeLayout;
    public final RecyclerView segmentsRv;
    public final LinearLayout shopTypeLayout;
    public final RecyclerView stockSummaryRv;

    public abstract void setItem(StockSummaryResponse stockSummaryResponse);

    public abstract void setListener(GenericListeners genericListeners);

    protected FragmentInventoryBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView allBookMove, TextView cancelTv, TextView customerTypeBookshop, TextView customerTypeSchool, EditText inventorySearchEt, RecyclerView lowStockListRv, TextView noDataText, ImageView performanceMenu, ProgressBar progressBar, LinearLayout schoolTypeLayout, RecyclerView segmentsRv, LinearLayout shopTypeLayout, RecyclerView stockSummaryRv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.allBookMove = allBookMove;
        this.cancelTv = cancelTv;
        this.customerTypeBookshop = customerTypeBookshop;
        this.customerTypeSchool = customerTypeSchool;
        this.inventorySearchEt = inventorySearchEt;
        this.lowStockListRv = lowStockListRv;
        this.noDataText = noDataText;
        this.performanceMenu = performanceMenu;
        this.progressBar = progressBar;
        this.schoolTypeLayout = schoolTypeLayout;
        this.segmentsRv = segmentsRv;
        this.shopTypeLayout = shopTypeLayout;
        this.stockSummaryRv = stockSummaryRv;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public StockSummaryResponse getItem() {
        return this.mItem;
    }

    public static FragmentInventoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentInventoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentInventoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_inventory, root, attachToRoot, component);
    }

    public static FragmentInventoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentInventoryBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentInventoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_inventory, null, false, component);
    }

    public static FragmentInventoryBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentInventoryBinding bind(View view, Object component) {
        return (FragmentInventoryBinding) bind(component, view, R.layout.fragment_inventory);
    }
}
