package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public abstract class DialogInvoiceShareBinding extends ViewDataBinding {
    public final ImageView closeBtn;
    public final TextView date;
    public final TextView invoiceNumber;
    public final LinearLayout shareEmail;
    public final LinearLayout shareWhatsapp;
    public final TextView time;
    public final TextView timeTitle;
    public final TextView titleText;

    protected DialogInvoiceShareBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView closeBtn, TextView date, TextView invoiceNumber, LinearLayout shareEmail, LinearLayout shareWhatsapp, TextView time, TextView timeTitle, TextView titleText) {
        super(_bindingComponent, _root, _localFieldCount);
        this.closeBtn = closeBtn;
        this.date = date;
        this.invoiceNumber = invoiceNumber;
        this.shareEmail = shareEmail;
        this.shareWhatsapp = shareWhatsapp;
        this.time = time;
        this.timeTitle = timeTitle;
        this.titleText = titleText;
    }

    public static DialogInvoiceShareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInvoiceShareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogInvoiceShareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_invoice_share, root, attachToRoot, component);
    }

    public static DialogInvoiceShareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInvoiceShareBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogInvoiceShareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_invoice_share, null, false, component);
    }

    public static DialogInvoiceShareBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogInvoiceShareBinding bind(View view, Object component) {
        return (DialogInvoiceShareBinding) bind(component, view, R.layout.dialog_invoice_share);
    }
}
