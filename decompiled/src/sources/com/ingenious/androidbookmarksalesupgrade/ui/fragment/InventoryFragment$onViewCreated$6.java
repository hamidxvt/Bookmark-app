package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventoryGradeBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventorySegmentBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventorySubjectBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.LowStockActivity;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.RefillRequestsActivity;
import com.ingenious.androidbookmarksalesupgrade.viewModel.InventoryViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InventoryFragment.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016¨\u0006\u0006"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/fragment/InventoryFragment$onViewCreated$6", "Lcom/ingenious/androidbookmarksalesupgrade/listener/GenericListeners;", "onTapLowStock", "", "onTapRefillRequests", "onTapFilter", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class InventoryFragment$onViewCreated$6 implements GenericListeners {
    final /* synthetic */ InventoryFragment this$0;

    InventoryFragment$onViewCreated$6(InventoryFragment $receiver) {
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
    public void onTapLowStock() {
        ActivityExtKt.gotoActivityFromFragment(this.this$0, LowStockActivity.class);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapRefillRequests() {
        ActivityExtKt.gotoActivityFromFragment(this.this$0, RefillRequestsActivity.class);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapFilter() {
        View dialogView = this.this$0.getLayoutInflater().inflate(R.layout.dialog_filter_main_inventory, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(this.this$0.requireContext()).setView(dialogView).create();
        LinearLayout btnSegment = (LinearLayout) dialogView.findViewById(R.id.btnSegment);
        LinearLayout btnGrade = (LinearLayout) dialogView.findViewById(R.id.btnGrade);
        LinearLayout btnSubject = (LinearLayout) dialogView.findViewById(R.id.btnSubject);
        AppCompatButton btnClearAll = (AppCompatButton) dialogView.findViewById(R.id.btnClearAll);
        AppCompatButton btnDone = (AppCompatButton) dialogView.findViewById(R.id.btnDone);
        ImageView crossBtn = (ImageView) dialogView.findViewById(R.id.ivClose);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        final InventoryFragment inventoryFragment = this.this$0;
        btnSegment.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$8(InventoryFragment.this, dialog, view);
            }
        });
        final InventoryFragment inventoryFragment2 = this.this$0;
        btnGrade.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$17(InventoryFragment.this, dialog, view);
            }
        });
        final InventoryFragment inventoryFragment3 = this.this$0;
        btnSubject.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29(InventoryFragment.this, dialog, view);
            }
        });
        final InventoryFragment inventoryFragment4 = this.this$0;
        btnClearAll.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$30(InventoryFragment.this, dialog, view);
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$8(final InventoryFragment this$0, final AlertDialog $dialog, View it) {
        final DialogFilterMainInventorySegmentBinding segmentBinding = DialogFilterMainInventorySegmentBinding.inflate(this$0.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(segmentBinding, "inflate(...)");
        final AlertDialog segmentDialog = new AlertDialog.Builder(this$0.requireContext()).setView(segmentBinding.getRoot()).create();
        Window window = segmentDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        CollectionsKt.listOf((Object[]) new LinearLayout[]{segmentBinding.btnPrimary, segmentBinding.btnEarlyYears, segmentBinding.btnLower, segmentBinding.btnOLevel, segmentBinding.btnALevel});
        segmentBinding.btnPrimary.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$8$lambda$0(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnEarlyYears.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$8$lambda$1(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnLower.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$8$lambda$2(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnOLevel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$8$lambda$3(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnALevel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$8$lambda$4(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnClearAll.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$8$lambda$5(InventoryFragment.this, segmentDialog, view);
            }
        });
        segmentBinding.btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$8$lambda$6(InventoryFragment.this, segmentDialog, $dialog, view);
            }
        });
        segmentBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                segmentDialog.dismiss();
            }
        });
        segmentDialog.show();
    }

    private static final void onTapFilter$lambda$8$selectSegment(InventoryFragment this$0, View view, int segmentId) {
        this$0.selectedSegment = Integer.valueOf(segmentId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$8$lambda$0(DialogFilterMainInventorySegmentBinding $segmentBinding, InventoryFragment this$0, View it) {
        LinearLayout btnPrimary = $segmentBinding.btnPrimary;
        Intrinsics.checkNotNullExpressionValue(btnPrimary, "btnPrimary");
        onTapFilter$lambda$8$selectSegment(this$0, btnPrimary, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$8$lambda$1(DialogFilterMainInventorySegmentBinding $segmentBinding, InventoryFragment this$0, View it) {
        LinearLayout btnEarlyYears = $segmentBinding.btnEarlyYears;
        Intrinsics.checkNotNullExpressionValue(btnEarlyYears, "btnEarlyYears");
        onTapFilter$lambda$8$selectSegment(this$0, btnEarlyYears, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$8$lambda$2(DialogFilterMainInventorySegmentBinding $segmentBinding, InventoryFragment this$0, View it) {
        LinearLayout btnLower = $segmentBinding.btnLower;
        Intrinsics.checkNotNullExpressionValue(btnLower, "btnLower");
        onTapFilter$lambda$8$selectSegment(this$0, btnLower, 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$8$lambda$3(DialogFilterMainInventorySegmentBinding $segmentBinding, InventoryFragment this$0, View it) {
        LinearLayout btnOLevel = $segmentBinding.btnOLevel;
        Intrinsics.checkNotNullExpressionValue(btnOLevel, "btnOLevel");
        onTapFilter$lambda$8$selectSegment(this$0, btnOLevel, 6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$8$lambda$4(DialogFilterMainInventorySegmentBinding $segmentBinding, InventoryFragment this$0, View it) {
        LinearLayout btnALevel = $segmentBinding.btnALevel;
        Intrinsics.checkNotNullExpressionValue(btnALevel, "btnALevel");
        onTapFilter$lambda$8$selectSegment(this$0, btnALevel, 9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$8$lambda$5(InventoryFragment this$0, AlertDialog $segmentDialog, View it) {
        InventoryViewModel viewModel;
        InventoryViewModel viewModel2;
        this$0.selectedSegment = null;
        this$0.setupAdapter();
        this$0.setupStockAdapter();
        this$0.setupSegmentAdapter();
        viewModel = this$0.getViewModel();
        viewModel.lowStock();
        viewModel2 = this$0.getViewModel();
        viewModel2.stockSummary();
        this$0.fetchBooksBySegment();
        $segmentDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$8$lambda$6(InventoryFragment this$0, AlertDialog $segmentDialog, AlertDialog $dialog, View it) {
        this$0.fetchInventorySummaryWithFilter();
        $segmentDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$17(final InventoryFragment this$0, final AlertDialog $dialog, View it) {
        final DialogFilterMainInventoryGradeBinding gradeBinding = DialogFilterMainInventoryGradeBinding.inflate(this$0.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(gradeBinding, "inflate(...)");
        final AlertDialog dialogGrade = new AlertDialog.Builder(this$0.requireContext()).setView(gradeBinding.getRoot()).create();
        Window window = dialogGrade.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{gradeBinding.allGrade, gradeBinding.year1, gradeBinding.year2, gradeBinding.year3});
        gradeBinding.allGrade.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$17$lambda$10(DialogFilterMainInventoryGradeBinding.this, options, this$0, view);
            }
        });
        gradeBinding.year1.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$17$lambda$11(DialogFilterMainInventoryGradeBinding.this, options, this$0, view);
            }
        });
        gradeBinding.year2.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$17$lambda$12(DialogFilterMainInventoryGradeBinding.this, options, this$0, view);
            }
        });
        gradeBinding.year3.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$17$lambda$13(DialogFilterMainInventoryGradeBinding.this, options, this$0, view);
            }
        });
        gradeBinding.btnClearAll.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$17$lambda$14(InventoryFragment.this, dialogGrade, view);
            }
        });
        gradeBinding.btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$17$lambda$15(InventoryFragment.this, dialogGrade, $dialog, view);
            }
        });
        gradeBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogGrade.dismiss();
            }
        });
        dialogGrade.show();
    }

    private static final void onTapFilter$lambda$17$selectGrade(List<? extends TextView> list, InventoryFragment this$0, TextView view, Integer gradeId) {
        List<? extends TextView> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            TextView it = (TextView) element$iv;
            it.setBackgroundResource(R.drawable.edittext_background_white);
            it.setTextColor(ContextCompat.getColor(this$0.requireContext(), R.color.black));
        }
        view.setBackgroundResource(R.drawable.selected_bg);
        view.setTextColor(ContextCompat.getColor(this$0.requireContext(), R.color.app_color));
        this$0.selectedGrade = gradeId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$17$lambda$10(DialogFilterMainInventoryGradeBinding $gradeBinding, List $options, InventoryFragment this$0, View it) {
        TextView allGrade = $gradeBinding.allGrade;
        Intrinsics.checkNotNullExpressionValue(allGrade, "allGrade");
        onTapFilter$lambda$17$selectGrade($options, this$0, allGrade, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$17$lambda$11(DialogFilterMainInventoryGradeBinding $gradeBinding, List $options, InventoryFragment this$0, View it) {
        TextView year1 = $gradeBinding.year1;
        Intrinsics.checkNotNullExpressionValue(year1, "year1");
        onTapFilter$lambda$17$selectGrade($options, this$0, year1, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$17$lambda$12(DialogFilterMainInventoryGradeBinding $gradeBinding, List $options, InventoryFragment this$0, View it) {
        TextView year2 = $gradeBinding.year2;
        Intrinsics.checkNotNullExpressionValue(year2, "year2");
        onTapFilter$lambda$17$selectGrade($options, this$0, year2, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$17$lambda$13(DialogFilterMainInventoryGradeBinding $gradeBinding, List $options, InventoryFragment this$0, View it) {
        TextView year3 = $gradeBinding.year3;
        Intrinsics.checkNotNullExpressionValue(year3, "year3");
        onTapFilter$lambda$17$selectGrade($options, this$0, year3, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$17$lambda$14(InventoryFragment this$0, AlertDialog $dialogGrade, View it) {
        InventoryViewModel viewModel;
        InventoryViewModel viewModel2;
        this$0.selectedGrade = null;
        $dialogGrade.dismiss();
        this$0.setupAdapter();
        this$0.setupStockAdapter();
        this$0.setupSegmentAdapter();
        viewModel = this$0.getViewModel();
        viewModel.lowStock();
        viewModel2 = this$0.getViewModel();
        viewModel2.stockSummary();
        this$0.fetchBooksBySegment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$17$lambda$15(InventoryFragment this$0, AlertDialog $dialogGrade, AlertDialog $dialog, View it) {
        this$0.fetchInventorySummaryWithFilter();
        $dialogGrade.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29(final InventoryFragment this$0, final AlertDialog $dialog, View it) {
        final DialogFilterMainInventorySubjectBinding subjectBinding = DialogFilterMainInventorySubjectBinding.inflate(this$0.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(subjectBinding, "inflate(...)");
        final AlertDialog subjectDialog = new AlertDialog.Builder(this$0.requireContext()).setView(subjectBinding.getRoot()).create();
        Window window = subjectDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{subjectBinding.all, subjectBinding.artsAndCraft, subjectBinding.english, subjectBinding.generalKnowledge, subjectBinding.islamiat, subjectBinding.mathematics, subjectBinding.urdu});
        subjectBinding.all.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29$lambda$19(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.english.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29$lambda$20(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.urdu.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29$lambda$21(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.mathematics.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29$lambda$22(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.islamiat.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29$lambda$23(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.generalKnowledge.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29$lambda$24(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.artsAndCraft.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29$lambda$25(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.btnClearAll.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29$lambda$26(InventoryFragment.this, subjectDialog, view);
            }
        });
        subjectBinding.btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment$onViewCreated$6.onTapFilter$lambda$29$lambda$27(InventoryFragment.this, subjectDialog, $dialog, view);
            }
        });
        subjectBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$6$$ExternalSyntheticLambda30
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                subjectDialog.dismiss();
            }
        });
        subjectDialog.show();
    }

    private static final void onTapFilter$lambda$29$selectSubject(List<? extends TextView> list, InventoryFragment this$0, TextView view, Integer subjectId) {
        List<? extends TextView> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            TextView it = (TextView) element$iv;
            it.setBackgroundResource(R.drawable.edittext_background_white);
            it.setTextColor(ContextCompat.getColor(this$0.requireContext(), R.color.black));
        }
        view.setBackgroundResource(R.drawable.selected_bg);
        view.setTextColor(ContextCompat.getColor(this$0.requireContext(), R.color.app_color));
        this$0.selectedSubject = subjectId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29$lambda$19(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, InventoryFragment this$0, View it) {
        TextView all = $subjectBinding.all;
        Intrinsics.checkNotNullExpressionValue(all, "all");
        onTapFilter$lambda$29$selectSubject($options, this$0, all, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29$lambda$20(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, InventoryFragment this$0, View it) {
        TextView english = $subjectBinding.english;
        Intrinsics.checkNotNullExpressionValue(english, "english");
        onTapFilter$lambda$29$selectSubject($options, this$0, english, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29$lambda$21(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, InventoryFragment this$0, View it) {
        TextView urdu = $subjectBinding.urdu;
        Intrinsics.checkNotNullExpressionValue(urdu, "urdu");
        onTapFilter$lambda$29$selectSubject($options, this$0, urdu, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29$lambda$22(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, InventoryFragment this$0, View it) {
        TextView mathematics = $subjectBinding.mathematics;
        Intrinsics.checkNotNullExpressionValue(mathematics, "mathematics");
        onTapFilter$lambda$29$selectSubject($options, this$0, mathematics, 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29$lambda$23(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, InventoryFragment this$0, View it) {
        TextView islamiat = $subjectBinding.islamiat;
        Intrinsics.checkNotNullExpressionValue(islamiat, "islamiat");
        onTapFilter$lambda$29$selectSubject($options, this$0, islamiat, 6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29$lambda$24(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, InventoryFragment this$0, View it) {
        TextView generalKnowledge = $subjectBinding.generalKnowledge;
        Intrinsics.checkNotNullExpressionValue(generalKnowledge, "generalKnowledge");
        onTapFilter$lambda$29$selectSubject($options, this$0, generalKnowledge, 11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29$lambda$25(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, InventoryFragment this$0, View it) {
        TextView artsAndCraft = $subjectBinding.artsAndCraft;
        Intrinsics.checkNotNullExpressionValue(artsAndCraft, "artsAndCraft");
        onTapFilter$lambda$29$selectSubject($options, this$0, artsAndCraft, 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29$lambda$26(InventoryFragment this$0, AlertDialog $subjectDialog, View it) {
        InventoryViewModel viewModel;
        InventoryViewModel viewModel2;
        this$0.selectedSubject = null;
        this$0.setupAdapter();
        this$0.setupStockAdapter();
        this$0.setupSegmentAdapter();
        viewModel = this$0.getViewModel();
        viewModel.lowStock();
        viewModel2 = this$0.getViewModel();
        viewModel2.stockSummary();
        this$0.fetchBooksBySegment();
        $subjectDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$29$lambda$27(InventoryFragment this$0, AlertDialog $subjectDialog, AlertDialog $dialog, View it) {
        this$0.fetchInventorySummaryWithFilter();
        $subjectDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30(InventoryFragment this$0, AlertDialog $dialog, View it) {
        InventoryViewModel viewModel;
        InventoryViewModel viewModel2;
        this$0.selectedSegment = null;
        this$0.selectedGrade = null;
        this$0.selectedSubject = null;
        this$0.setupAdapter();
        this$0.setupStockAdapter();
        this$0.setupSegmentAdapter();
        viewModel = this$0.getViewModel();
        viewModel.lowStock();
        viewModel2 = this$0.getViewModel();
        viewModel2.stockSummary();
        this$0.fetchBooksBySegment();
        $dialog.dismiss();
    }
}
