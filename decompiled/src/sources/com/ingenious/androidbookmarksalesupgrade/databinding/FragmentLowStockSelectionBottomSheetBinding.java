package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;

/* loaded from: classes13.dex */
public abstract class FragmentLowStockSelectionBottomSheetBinding extends ViewDataBinding {
    public final ImageView arrowBack;
    public final TextView cancelButton;
    public final TextView confirmButton;

    @Bindable
    protected GenericListeners mListener;
    public final EditText notesEditText;
    public final RecyclerView productRecyclerView;
    public final ProgressBar progressBar;
    public final TextView totalPrice;

    public abstract void setListener(GenericListeners genericListeners);

    protected FragmentLowStockSelectionBottomSheetBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView arrowBack, TextView cancelButton, TextView confirmButton, EditText notesEditText, RecyclerView productRecyclerView, ProgressBar progressBar, TextView totalPrice) {
        super(_bindingComponent, _root, _localFieldCount);
        this.arrowBack = arrowBack;
        this.cancelButton = cancelButton;
        this.confirmButton = confirmButton;
        this.notesEditText = notesEditText;
        this.productRecyclerView = productRecyclerView;
        this.progressBar = progressBar;
        this.totalPrice = totalPrice;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static FragmentLowStockSelectionBottomSheetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLowStockSelectionBottomSheetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentLowStockSelectionBottomSheetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_low_stock_selection_bottom_sheet, root, attachToRoot, component);
    }

    public static FragmentLowStockSelectionBottomSheetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLowStockSelectionBottomSheetBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentLowStockSelectionBottomSheetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_low_stock_selection_bottom_sheet, null, false, component);
    }

    public static FragmentLowStockSelectionBottomSheetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLowStockSelectionBottomSheetBinding bind(View view, Object component) {
        return (FragmentLowStockSelectionBottomSheetBinding) bind(component, view, R.layout.fragment_low_stock_selection_bottom_sheet);
    }
}
