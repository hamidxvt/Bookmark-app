package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomerFragment.kt */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/fragment/CustomerFragment$onViewCreated$6", "Lcom/ingenious/androidbookmarksalesupgrade/listener/GenericListeners;", "onTapFilter", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CustomerFragment$onViewCreated$6 implements GenericListeners {
    final /* synthetic */ CustomerFragment this$0;

    CustomerFragment$onViewCreated$6(CustomerFragment $receiver) {
        this.this$0 = $receiver;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onNotificationClick() {
        GenericListeners.DefaultImpls.onNotificationClick(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onSettingClick() {
        GenericListeners.DefaultImpls.onSettingClick(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapAddBooks() {
        GenericListeners.DefaultImpls.onTapAddBooks(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapAddCustomer() {
        GenericListeners.DefaultImpls.onTapAddCustomer(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapAddHome() {
        GenericListeners.DefaultImpls.onTapAddHome(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapAddVisit() {
        GenericListeners.DefaultImpls.onTapAddVisit(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapBack() {
        GenericListeners.DefaultImpls.onTapBack(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapCaptureImage() {
        GenericListeners.DefaultImpls.onTapCaptureImage(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapCheckIn() {
        GenericListeners.DefaultImpls.onTapCheckIn(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapCompleteVisit() {
        GenericListeners.DefaultImpls.onTapCompleteVisit(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapDate() {
        GenericListeners.DefaultImpls.onTapDate(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapDateNext() {
        GenericListeners.DefaultImpls.onTapDateNext(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapDatePrevious() {
        GenericListeners.DefaultImpls.onTapDatePrevious(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapDismiss() {
        GenericListeners.DefaultImpls.onTapDismiss(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapDone() {
        GenericListeners.DefaultImpls.onTapDone(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapForgetPassword() {
        GenericListeners.DefaultImpls.onTapForgetPassword(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapLocation() {
        GenericListeners.DefaultImpls.onTapLocation(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapLocationFab() {
        GenericListeners.DefaultImpls.onTapLocationFab(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapLogin() {
        GenericListeners.DefaultImpls.onTapLogin(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapLogout() {
        GenericListeners.DefaultImpls.onTapLogout(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapLowStock() {
        GenericListeners.DefaultImpls.onTapLowStock(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapNewAccount() {
        GenericListeners.DefaultImpls.onTapNewAccount(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapOTP() {
        GenericListeners.DefaultImpls.onTapOTP(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapProfile() {
        GenericListeners.DefaultImpls.onTapProfile(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapRefill() {
        GenericListeners.DefaultImpls.onTapRefill(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapRefillRequests() {
        GenericListeners.DefaultImpls.onTapRefillRequests(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapResetPassword() {
        GenericListeners.DefaultImpls.onTapResetPassword(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapSendMessage() {
        GenericListeners.DefaultImpls.onTapSendMessage(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapSettings() {
        GenericListeners.DefaultImpls.onTapSettings(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapSwitch() {
        GenericListeners.DefaultImpls.onTapSwitch(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapViewSelection() {
        GenericListeners.DefaultImpls.onTapViewSelection(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapFilter() {
        View dialogView = this.this$0.getLayoutInflater().inflate(R.layout.dialog_filter_main, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(this.this$0.requireContext()).setView(dialogView).create();
        LinearLayout customerType = (LinearLayout) dialogView.findViewById(R.id.btnCustomerType);
        LinearLayout addedBy = (LinearLayout) dialogView.findViewById(R.id.btnAddedBy);
        Button cancel = (Button) dialogView.findViewById(R.id.btnClearAll);
        ImageView crossBtn = (ImageView) dialogView.findViewById(R.id.ivClose);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        LinearLayout adoptionLinear = (LinearLayout) dialogView.findViewById(R.id.adoptions_switch);
        LinearLayout distanceLinear = (LinearLayout) dialogView.findViewById(R.id.btnDistance);
        LinearLayout priorityLinear = (LinearLayout) dialogView.findViewById(R.id.btnPriority);
        LinearLayout lastVisitLinear = (LinearLayout) dialogView.findViewById(R.id.lastVisitLinear);
        LinearLayout areaLinear = (LinearLayout) dialogView.findViewById(R.id.areaLinear);
        priorityLinear.setVisibility(8);
        distanceLinear.setVisibility(8);
        adoptionLinear.setVisibility(0);
        lastVisitLinear.setVisibility(0);
        areaLinear.setVisibility(0);
        final CustomerFragment customerFragment = this.this$0;
        customerType.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerFragment$onViewCreated$6.onTapFilter$lambda$6(CustomerFragment.this, dialog, view);
            }
        });
        final CustomerFragment customerFragment2 = this.this$0;
        addedBy.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerFragment$onViewCreated$6.onTapFilter$lambda$13(CustomerFragment.this, dialog, view);
            }
        });
        final CustomerFragment customerFragment3 = this.this$0;
        areaLinear.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerFragment$onViewCreated$6.onTapFilter$lambda$20(CustomerFragment.this, dialog, view);
            }
        });
        final CustomerFragment customerFragment4 = this.this$0;
        lastVisitLinear.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerFragment$onViewCreated$6.onTapFilter$lambda$27(CustomerFragment.this, dialog, view);
            }
        });
        final CustomerFragment customerFragment5 = this.this$0;
        cancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerFragment$onViewCreated$6.onTapFilter$lambda$28(CustomerFragment.this, dialog, view);
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$6(final CustomerFragment this$0, final AlertDialog $dialog, View it) {
        View customerTypeDialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_customer_type_filter, (ViewGroup) null);
        final AlertDialog customerTypeDialog = new AlertDialog.Builder(this$0.requireContext()).setView(customerTypeDialogView).create();
        Window window = customerTypeDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button customerTypeCancel = (Button) customerTypeDialogView.findViewById(R.id.customer_cancel_btn);
        ImageView crossBtnCustomerType = (ImageView) customerTypeDialogView.findViewById(R.id.customer_type_cross_iv);
        TextView all = (TextView) customerTypeDialogView.findViewById(R.id.type_all_customer);
        TextView school = (TextView) customerTypeDialogView.findViewById(R.id.type_school);
        TextView bookshop = (TextView) customerTypeDialogView.findViewById(R.id.type_bookshop);
        Button customerTypeDone = (Button) customerTypeDialogView.findViewById(R.id.customer_done_btn);
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{all, school, bookshop});
        List $this$forEach$iv = options;
        for (Object element$iv : $this$forEach$iv) {
            final TextView tv = (TextView) element$iv;
            tv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$6$lambda$2$lambda$1(options, tv, this$0, view);
                }
            });
            customerTypeDialogView = customerTypeDialogView;
        }
        customerTypeDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerFragment$onViewCreated$6.onTapFilter$lambda$6$lambda$3(CustomerFragment.this, customerTypeDialog, $dialog, view);
            }
        });
        customerTypeCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerFragment$onViewCreated$6.onTapFilter$lambda$6$lambda$4(CustomerFragment.this, customerTypeDialog, view);
            }
        });
        crossBtnCustomerType.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerFragment$onViewCreated$6.onTapFilter$lambda$6$lambda$5(CustomerFragment.this, customerTypeDialog, view);
            }
        });
        customerTypeDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$6$lambda$2$lambda$1(List $options, TextView $tv, CustomerFragment this$0, View it) {
        List $this$forEach$iv = $options;
        for (Object element$iv : $this$forEach$iv) {
            TextView it2 = (TextView) element$iv;
            it2.setBackgroundResource(R.drawable.edittext_background_white);
            it2.setTextColor(ContextCompat.getColor(this$0.requireContext(), R.color.black));
        }
        $tv.setBackgroundResource(R.drawable.selected_bg);
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        $tv.setTextColor(ContextCompat.getColor(context, R.color.app_color));
        this$0.selectedCustomerType = $tv.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$6$lambda$3(CustomerFragment this$0, AlertDialog $customerTypeDialog, AlertDialog $dialog, View it) {
        VisitViewModel viewModel;
        String str;
        String str2;
        viewModel = this$0.getViewModel();
        str = this$0.selectedCustomerType;
        if (str != null) {
            str2 = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(str2, "toLowerCase(...)");
        } else {
            str2 = null;
        }
        VisitViewModel.customersList$default(viewModel, str2, null, null, null, null, 30, null);
        $customerTypeDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$6$lambda$4(CustomerFragment this$0, AlertDialog $customerTypeDialog, View it) {
        this$0.selectedCustomerType = "";
        $customerTypeDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$6$lambda$5(CustomerFragment this$0, AlertDialog $customerTypeDialog, View it) {
        this$0.selectedCustomerType = "";
        $customerTypeDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$13(final CustomerFragment this$0, final AlertDialog $dialog, View it) {
        View addedByDialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_added_by_filter, (ViewGroup) null);
        final AlertDialog addedByDialog = new AlertDialog.Builder(this$0.requireContext()).setView(addedByDialogView).create();
        Window window = addedByDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button addedByCancel = (Button) addedByDialogView.findViewById(R.id.addedBy_cancel_btn);
        ImageView crossBtnAddedBy = (ImageView) addedByDialogView.findViewById(R.id.added_by_cross_iv);
        Button addedByDone = (Button) addedByDialogView.findViewById(R.id.addedBy_done_btn);
        TextView all = (TextView) addedByDialogView.findViewById(R.id.added_by_all);
        TextView admin = (TextView) addedByDialogView.findViewById(R.id.added_by_admin);
        TextView user = (TextView) addedByDialogView.findViewById(R.id.added_by_user);
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{all, admin, user});
        List $this$forEach$iv = options;
        for (Object element$iv : $this$forEach$iv) {
            final TextView tv = (TextView) element$iv;
            tv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda20
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$13$lambda$12$lambda$8(options, tv, this$0, view);
                }
            });
            crossBtnAddedBy.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda21
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$13$lambda$12$lambda$9(CustomerFragment.this, addedByDialog, view);
                }
            });
            addedByDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$13$lambda$12$lambda$10(CustomerFragment.this, addedByDialog, $dialog, view);
                }
            });
            addedByCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$13$lambda$12$lambda$11(CustomerFragment.this, addedByDialog, view);
                }
            });
            addedByDialogView = addedByDialogView;
            options = options;
        }
        addedByDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$13$lambda$12$lambda$8(List $options, TextView $tv, CustomerFragment this$0, View it) {
        List $this$forEach$iv = $options;
        for (Object element$iv : $this$forEach$iv) {
            TextView it2 = (TextView) element$iv;
            it2.setBackgroundResource(R.drawable.edittext_background_white);
            it2.setTextColor(ContextCompat.getColor(this$0.requireContext(), R.color.black));
        }
        $tv.setBackgroundResource(R.drawable.selected_bg);
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        $tv.setTextColor(ContextCompat.getColor(context, R.color.app_color));
        this$0.selectedAddedBy = $tv.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$13$lambda$12$lambda$9(CustomerFragment this$0, AlertDialog $addedByDialog, View it) {
        this$0.selectedAddedBy = "";
        $addedByDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$13$lambda$12$lambda$10(CustomerFragment this$0, AlertDialog $addedByDialog, AlertDialog $dialog, View it) {
        VisitViewModel viewModel;
        String str;
        viewModel = this$0.getViewModel();
        str = this$0.selectedAddedBy;
        VisitViewModel.customersList$default(viewModel, null, null, null, null, str, 15, null);
        $addedByDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$13$lambda$12$lambda$11(CustomerFragment this$0, AlertDialog $addedByDialog, View it) {
        this$0.selectedAddedBy = "";
        $addedByDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$20(final CustomerFragment this$0, final AlertDialog $dialog, View it) {
        View areaDialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_area_filter, (ViewGroup) null);
        final AlertDialog areaDialog = new AlertDialog.Builder(this$0.requireContext()).setView(areaDialogView).create();
        Window window = areaDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button areaCancel = (Button) areaDialogView.findViewById(R.id.area_cancel_btn);
        ImageView crossBtnArea = (ImageView) areaDialogView.findViewById(R.id.area_cross_iv);
        Button areaDone = (Button) areaDialogView.findViewById(R.id.area_done_btn);
        TextView areaAll = (TextView) areaDialogView.findViewById(R.id.area_all);
        TextView areaGulshan = (TextView) areaDialogView.findViewById(R.id.area_gulshan);
        TextView areaKarimabad = (TextView) areaDialogView.findViewById(R.id.area_karimabad);
        TextView areaDha = (TextView) areaDialogView.findViewById(R.id.area_dha);
        TextView areaFb = (TextView) areaDialogView.findViewById(R.id.area_fb);
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{areaAll, areaGulshan, areaKarimabad, areaDha, areaFb});
        List $this$forEach$iv = options;
        for (Object element$iv : $this$forEach$iv) {
            View areaDialogView2 = areaDialogView;
            final TextView tv = (TextView) element$iv;
            tv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda16
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$20$lambda$19$lambda$15(options, tv, this$0, view);
                }
            });
            crossBtnArea.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda17
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$20$lambda$19$lambda$16(CustomerFragment.this, areaDialog, view);
                }
            });
            areaDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda18
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$20$lambda$19$lambda$17(CustomerFragment.this, areaDialog, $dialog, view);
                }
            });
            areaCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda19
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$20$lambda$19$lambda$18(CustomerFragment.this, areaDialog, view);
                }
            });
            areaDialogView = areaDialogView2;
            areaAll = areaAll;
        }
        areaDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$20$lambda$19$lambda$15(List $options, TextView $tv, CustomerFragment this$0, View it) {
        List $this$forEach$iv = $options;
        for (Object element$iv : $this$forEach$iv) {
            TextView it2 = (TextView) element$iv;
            it2.setBackgroundResource(R.drawable.edittext_background_white);
            it2.setTextColor(ContextCompat.getColor(this$0.requireContext(), R.color.black));
        }
        $tv.setBackgroundResource(R.drawable.selected_bg);
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        $tv.setTextColor(ContextCompat.getColor(context, R.color.app_color));
        this$0.selectedArea = $tv.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$20$lambda$19$lambda$16(CustomerFragment this$0, AlertDialog $areaDialog, View it) {
        this$0.selectedArea = "";
        $areaDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$20$lambda$19$lambda$17(CustomerFragment this$0, AlertDialog $areaDialog, AlertDialog $dialog, View it) {
        VisitViewModel viewModel;
        String str;
        viewModel = this$0.getViewModel();
        str = this$0.selectedArea;
        VisitViewModel.customersList$default(viewModel, null, null, str, null, null, 27, null);
        $areaDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$20$lambda$19$lambda$18(CustomerFragment this$0, AlertDialog $areaDialog, View it) {
        this$0.selectedAddedBy = "";
        $areaDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$27(final CustomerFragment this$0, final AlertDialog $dialog, View it) {
        View lastVisitDialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_last_visit_filter, (ViewGroup) null);
        final AlertDialog lastVisitDialog = new AlertDialog.Builder(this$0.requireContext()).setView(lastVisitDialogView).create();
        Window window = lastVisitDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button areaCancel = (Button) lastVisitDialogView.findViewById(R.id.last_visit_cancel_btn);
        ImageView crossBtnArea = (ImageView) lastVisitDialogView.findViewById(R.id.area_cross_iv);
        Button lastVisitDone = (Button) lastVisitDialogView.findViewById(R.id.last_visit_done_btn);
        TextView today = (TextView) lastVisitDialogView.findViewById(R.id.today_tv);
        TextView thisWeek = (TextView) lastVisitDialogView.findViewById(R.id.this_week_tv);
        TextView last30Days = (TextView) lastVisitDialogView.findViewById(R.id.last_30_days_tv);
        TextView plus30DaysAgo = (TextView) lastVisitDialogView.findViewById(R.id.plus_30_days_ago_tv);
        TextView neverVisited = (TextView) lastVisitDialogView.findViewById(R.id.never_visited_tv);
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{today, thisWeek, last30Days, plus30DaysAgo, neverVisited});
        List $this$forEach$iv = options;
        for (Object element$iv : $this$forEach$iv) {
            View lastVisitDialogView2 = lastVisitDialogView;
            final TextView tv = (TextView) element$iv;
            tv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$27$lambda$26$lambda$22(options, tv, this$0, view);
                }
            });
            crossBtnArea.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$27$lambda$26$lambda$23(CustomerFragment.this, lastVisitDialog, view);
                }
            });
            lastVisitDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$27$lambda$26$lambda$24(CustomerFragment.this, lastVisitDialog, $dialog, view);
                }
            });
            areaCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$6$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerFragment$onViewCreated$6.onTapFilter$lambda$27$lambda$26$lambda$25(CustomerFragment.this, lastVisitDialog, view);
                }
            });
            lastVisitDialogView = lastVisitDialogView2;
            today = today;
        }
        lastVisitDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$27$lambda$26$lambda$22(List $options, TextView $tv, CustomerFragment this$0, View it) {
        List $this$forEach$iv = $options;
        for (Object element$iv : $this$forEach$iv) {
            TextView it2 = (TextView) element$iv;
            it2.setBackgroundResource(R.drawable.edittext_background_white);
            it2.setTextColor(ContextCompat.getColor(this$0.requireContext(), R.color.black));
        }
        $tv.setBackgroundResource(R.drawable.selected_bg);
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        $tv.setTextColor(ContextCompat.getColor(context, R.color.app_color));
        String lowerCase = $tv.getText().toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        this$0.selectedLastVisit = lowerCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$27$lambda$26$lambda$23(CustomerFragment this$0, AlertDialog $lastVisitDialog, View it) {
        this$0.selectedLastVisit = "";
        $lastVisitDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$27$lambda$26$lambda$24(CustomerFragment this$0, AlertDialog $lastVisitDialog, AlertDialog $dialog, View it) {
        VisitViewModel viewModel;
        String str;
        viewModel = this$0.getViewModel();
        str = this$0.selectedLastVisit;
        VisitViewModel.customersList$default(viewModel, null, null, null, str, null, 23, null);
        $lastVisitDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$27$lambda$26$lambda$25(CustomerFragment this$0, AlertDialog $lastVisitDialog, View it) {
        this$0.selectedAddedBy = "";
        $lastVisitDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$28(CustomerFragment this$0, AlertDialog $dialog, View it) {
        this$0.selectedPriority = "";
        this$0.selectedDistance = "";
        this$0.selectedAddedBy = "";
        this$0.selectedCustomerType = "";
        $dialog.dismiss();
    }
}
