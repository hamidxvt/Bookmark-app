package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ingenious.androidbookmarksalesupgrade.R;

/* loaded from: classes13.dex */
public final class ActivityNotificationScreenBinding implements ViewBinding {
    public final ImageView backIcon;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView settingTxt;
    public final SwitchCompat switchLowStock;
    public final SwitchCompat switchNewProduct;
    public final SwitchCompat switchPush;
    public final SwitchCompat switchSms;
    public final SwitchCompat switchStockRefill;

    private ActivityNotificationScreenBinding(ConstraintLayout rootView, ImageView backIcon, ConstraintLayout main, TextView settingTxt, SwitchCompat switchLowStock, SwitchCompat switchNewProduct, SwitchCompat switchPush, SwitchCompat switchSms, SwitchCompat switchStockRefill) {
        this.rootView = rootView;
        this.backIcon = backIcon;
        this.main = main;
        this.settingTxt = settingTxt;
        this.switchLowStock = switchLowStock;
        this.switchNewProduct = switchNewProduct;
        this.switchPush = switchPush;
        this.switchSms = switchSms;
        this.switchStockRefill = switchStockRefill;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityNotificationScreenBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityNotificationScreenBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_notification_screen, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityNotificationScreenBinding bind(View rootView) {
        int id = R.id.backIcon;
        ImageView backIcon = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (backIcon != null) {
            ConstraintLayout main = (ConstraintLayout) rootView;
            id = R.id.settingTxt;
            TextView settingTxt = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (settingTxt != null) {
                id = R.id.switch_low_stock;
                SwitchCompat switchLowStock = (SwitchCompat) ViewBindings.findChildViewById(rootView, id);
                if (switchLowStock != null) {
                    id = R.id.switch_new_product;
                    SwitchCompat switchNewProduct = (SwitchCompat) ViewBindings.findChildViewById(rootView, id);
                    if (switchNewProduct != null) {
                        id = R.id.switch_push;
                        SwitchCompat switchPush = (SwitchCompat) ViewBindings.findChildViewById(rootView, id);
                        if (switchPush != null) {
                            id = R.id.switch_sms;
                            SwitchCompat switchSms = (SwitchCompat) ViewBindings.findChildViewById(rootView, id);
                            if (switchSms != null) {
                                id = R.id.switch_stock_refill;
                                SwitchCompat switchStockRefill = (SwitchCompat) ViewBindings.findChildViewById(rootView, id);
                                if (switchStockRefill != null) {
                                    return new ActivityNotificationScreenBinding((ConstraintLayout) rootView, backIcon, main, settingTxt, switchLowStock, switchNewProduct, switchPush, switchSms, switchStockRefill);
                                }
                            }
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
