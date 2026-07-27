package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.ingenious.androidbookmarksalesupgrade.bottomsheet.AllProductsCartBottomSheet;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventoryGradeBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventorySegmentBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogFilterMainInventorySubjectBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AllProductsActivity.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/AllProductsActivity$onCreate$2", "Lcom/ingenious/androidbookmarksalesupgrade/listener/GenericListeners;", "onTapViewSelection", "", "onTapFilter", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class AllProductsActivity$onCreate$2 implements GenericListeners {
    final /* synthetic */ AllProductsActivity this$0;

    AllProductsActivity$onCreate$2(AllProductsActivity $receiver) {
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
        List list;
        Bundle bundle = new Bundle();
        list = this.this$0.selectedProductsList;
        bundle.putParcelableArrayList("selectedProducts", new ArrayList<>(list));
        AllProductsCartBottomSheet bottomSheet = new AllProductsCartBottomSheet();
        bottomSheet.setArguments(bundle);
        bottomSheet.show(this.this$0.getSupportFragmentManager(), "allProductsBottomSheet");
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapFilter() {
        View dialogView = this.this$0.getLayoutInflater().inflate(R.layout.dialog_filter_main_inventory, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(this.this$0).setView(dialogView).create();
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
        final AllProductsActivity allProductsActivity = this.this$0;
        btnSegment.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$9(AllProductsActivity.this, dialog, view);
            }
        });
        final AllProductsActivity allProductsActivity2 = this.this$0;
        btnGrade.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$18(AllProductsActivity.this, dialog, view);
            }
        });
        final AllProductsActivity allProductsActivity3 = this.this$0;
        btnSubject.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30(AllProductsActivity.this, dialog, view);
            }
        });
        final AllProductsActivity allProductsActivity4 = this.this$0;
        btnClearAll.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$31(AllProductsActivity.this, dialog, view);
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$9(final AllProductsActivity this$0, final AlertDialog $dialog, View it) {
        final DialogFilterMainInventorySegmentBinding segmentBinding = DialogFilterMainInventorySegmentBinding.inflate(this$0.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(segmentBinding, "inflate(...)");
        final AlertDialog segmentDialog = new AlertDialog.Builder(this$0).setView(segmentBinding.getRoot()).create();
        Window window = segmentDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        CollectionsKt.listOf((Object[]) new LinearLayout[]{segmentBinding.btnPrimary, segmentBinding.btnEarlyYears, segmentBinding.btnLower, segmentBinding.btnOLevel, segmentBinding.btnALevel});
        segmentBinding.btnPrimary.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$9$lambda$1(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnEarlyYears.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$9$lambda$2(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnLower.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$9$lambda$3(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnOLevel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$9$lambda$4(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnALevel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$9$lambda$5(DialogFilterMainInventorySegmentBinding.this, this$0, view);
            }
        });
        segmentBinding.btnClearAll.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$9$lambda$6(AllProductsActivity.this, segmentDialog, view);
            }
        });
        segmentBinding.btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$9$lambda$7(AllProductsActivity.this, segmentDialog, $dialog, view);
            }
        });
        segmentBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                segmentDialog.dismiss();
            }
        });
        segmentDialog.show();
    }

    private static final void onTapFilter$lambda$9$selectSegment(AllProductsActivity this$0, View view, int segmentId) {
        this$0.selectedSegment = Integer.valueOf(segmentId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$9$lambda$1(DialogFilterMainInventorySegmentBinding $segmentBinding, AllProductsActivity this$0, View it) {
        LinearLayout btnPrimary = $segmentBinding.btnPrimary;
        Intrinsics.checkNotNullExpressionValue(btnPrimary, "btnPrimary");
        onTapFilter$lambda$9$selectSegment(this$0, btnPrimary, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$9$lambda$2(DialogFilterMainInventorySegmentBinding $segmentBinding, AllProductsActivity this$0, View it) {
        LinearLayout btnEarlyYears = $segmentBinding.btnEarlyYears;
        Intrinsics.checkNotNullExpressionValue(btnEarlyYears, "btnEarlyYears");
        onTapFilter$lambda$9$selectSegment(this$0, btnEarlyYears, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$9$lambda$3(DialogFilterMainInventorySegmentBinding $segmentBinding, AllProductsActivity this$0, View it) {
        LinearLayout btnLower = $segmentBinding.btnLower;
        Intrinsics.checkNotNullExpressionValue(btnLower, "btnLower");
        onTapFilter$lambda$9$selectSegment(this$0, btnLower, 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$9$lambda$4(DialogFilterMainInventorySegmentBinding $segmentBinding, AllProductsActivity this$0, View it) {
        LinearLayout btnOLevel = $segmentBinding.btnOLevel;
        Intrinsics.checkNotNullExpressionValue(btnOLevel, "btnOLevel");
        onTapFilter$lambda$9$selectSegment(this$0, btnOLevel, 6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$9$lambda$5(DialogFilterMainInventorySegmentBinding $segmentBinding, AllProductsActivity this$0, View it) {
        LinearLayout btnALevel = $segmentBinding.btnALevel;
        Intrinsics.checkNotNullExpressionValue(btnALevel, "btnALevel");
        onTapFilter$lambda$9$selectSegment(this$0, btnALevel, 9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$9$lambda$6(AllProductsActivity this$0, AlertDialog $segmentDialog, View it) {
        this$0.selectedSegment = null;
        this$0.pageNo = 1;
        this$0.fetchProductsWithFilter();
        $segmentDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$9$lambda$7(AllProductsActivity this$0, AlertDialog $segmentDialog, AlertDialog $dialog, View it) {
        this$0.fetchProductsWithFilter();
        $segmentDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$18(final AllProductsActivity this$0, final AlertDialog $dialog, View it) {
        final DialogFilterMainInventoryGradeBinding gradeBinding = DialogFilterMainInventoryGradeBinding.inflate(this$0.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(gradeBinding, "inflate(...)");
        final AlertDialog dialogGrade = new AlertDialog.Builder(this$0).setView(gradeBinding.getRoot()).create();
        Window window = dialogGrade.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{gradeBinding.allGrade, gradeBinding.year1, gradeBinding.year2, gradeBinding.year3});
        gradeBinding.allGrade.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$18$lambda$11(DialogFilterMainInventoryGradeBinding.this, options, this$0, view);
            }
        });
        gradeBinding.year1.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$18$lambda$12(DialogFilterMainInventoryGradeBinding.this, options, this$0, view);
            }
        });
        gradeBinding.year2.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$18$lambda$13(DialogFilterMainInventoryGradeBinding.this, options, this$0, view);
            }
        });
        gradeBinding.year3.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$18$lambda$14(DialogFilterMainInventoryGradeBinding.this, options, this$0, view);
            }
        });
        gradeBinding.btnClearAll.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$18$lambda$15(AllProductsActivity.this, dialogGrade, view);
            }
        });
        gradeBinding.btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$18$lambda$16(AllProductsActivity.this, dialogGrade, $dialog, view);
            }
        });
        gradeBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogGrade.dismiss();
            }
        });
        dialogGrade.show();
    }

    private static final void onTapFilter$lambda$18$selectGrade(List<? extends TextView> list, AllProductsActivity this$0, TextView view, Integer gradeId) {
        List<? extends TextView> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            TextView it = (TextView) element$iv;
            it.setBackgroundResource(R.drawable.edittext_background_white);
            it.setTextColor(ContextCompat.getColor(this$0, R.color.black));
        }
        view.setBackgroundResource(R.drawable.selected_bg);
        view.setTextColor(ContextCompat.getColor(this$0, R.color.app_color));
        this$0.selectedGrade = gradeId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$18$lambda$11(DialogFilterMainInventoryGradeBinding $gradeBinding, List $options, AllProductsActivity this$0, View it) {
        TextView allGrade = $gradeBinding.allGrade;
        Intrinsics.checkNotNullExpressionValue(allGrade, "allGrade");
        onTapFilter$lambda$18$selectGrade($options, this$0, allGrade, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$18$lambda$12(DialogFilterMainInventoryGradeBinding $gradeBinding, List $options, AllProductsActivity this$0, View it) {
        TextView year1 = $gradeBinding.year1;
        Intrinsics.checkNotNullExpressionValue(year1, "year1");
        onTapFilter$lambda$18$selectGrade($options, this$0, year1, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$18$lambda$13(DialogFilterMainInventoryGradeBinding $gradeBinding, List $options, AllProductsActivity this$0, View it) {
        TextView year2 = $gradeBinding.year2;
        Intrinsics.checkNotNullExpressionValue(year2, "year2");
        onTapFilter$lambda$18$selectGrade($options, this$0, year2, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$18$lambda$14(DialogFilterMainInventoryGradeBinding $gradeBinding, List $options, AllProductsActivity this$0, View it) {
        TextView year3 = $gradeBinding.year3;
        Intrinsics.checkNotNullExpressionValue(year3, "year3");
        onTapFilter$lambda$18$selectGrade($options, this$0, year3, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$18$lambda$15(AllProductsActivity this$0, AlertDialog $dialogGrade, View it) {
        this$0.selectedGrade = null;
        $dialogGrade.dismiss();
        this$0.pageNo = 1;
        this$0.fetchProductsWithFilter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$18$lambda$16(AllProductsActivity this$0, AlertDialog $dialogGrade, AlertDialog $dialog, View it) {
        this$0.fetchProductsWithFilter();
        $dialogGrade.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30(final AllProductsActivity this$0, final AlertDialog $dialog, View it) {
        final DialogFilterMainInventorySubjectBinding subjectBinding = DialogFilterMainInventorySubjectBinding.inflate(this$0.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(subjectBinding, "inflate(...)");
        final AlertDialog subjectDialog = new AlertDialog.Builder(this$0).setView(subjectBinding.getRoot()).create();
        Window window = subjectDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{subjectBinding.all, subjectBinding.artsAndCraft, subjectBinding.english, subjectBinding.generalKnowledge, subjectBinding.islamiat, subjectBinding.mathematics, subjectBinding.urdu});
        subjectBinding.all.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30$lambda$20(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.english.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30$lambda$21(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.urdu.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda30
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30$lambda$22(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.mathematics.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30$lambda$23(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.islamiat.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30$lambda$24(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.generalKnowledge.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30$lambda$25(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.artsAndCraft.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30$lambda$26(DialogFilterMainInventorySubjectBinding.this, options, this$0, view);
            }
        });
        subjectBinding.btnClearAll.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30$lambda$27(AllProductsActivity.this, subjectDialog, view);
            }
        });
        subjectBinding.btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity$onCreate$2.onTapFilter$lambda$30$lambda$28(AllProductsActivity.this, subjectDialog, $dialog, view);
            }
        });
        subjectBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$2$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                subjectDialog.dismiss();
            }
        });
        subjectDialog.show();
    }

    private static final void onTapFilter$lambda$30$selectSubject(List<? extends TextView> list, AllProductsActivity this$0, TextView view, Integer subjectId) {
        List<? extends TextView> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            TextView it = (TextView) element$iv;
            it.setBackgroundResource(R.drawable.edittext_background_white);
            it.setTextColor(ContextCompat.getColor(this$0, R.color.black));
        }
        view.setBackgroundResource(R.drawable.selected_bg);
        view.setTextColor(ContextCompat.getColor(this$0, R.color.app_color));
        this$0.selectedSubject = subjectId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30$lambda$20(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, AllProductsActivity this$0, View it) {
        TextView all = $subjectBinding.all;
        Intrinsics.checkNotNullExpressionValue(all, "all");
        onTapFilter$lambda$30$selectSubject($options, this$0, all, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30$lambda$21(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, AllProductsActivity this$0, View it) {
        TextView english = $subjectBinding.english;
        Intrinsics.checkNotNullExpressionValue(english, "english");
        onTapFilter$lambda$30$selectSubject($options, this$0, english, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30$lambda$22(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, AllProductsActivity this$0, View it) {
        TextView urdu = $subjectBinding.urdu;
        Intrinsics.checkNotNullExpressionValue(urdu, "urdu");
        onTapFilter$lambda$30$selectSubject($options, this$0, urdu, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30$lambda$23(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, AllProductsActivity this$0, View it) {
        TextView mathematics = $subjectBinding.mathematics;
        Intrinsics.checkNotNullExpressionValue(mathematics, "mathematics");
        onTapFilter$lambda$30$selectSubject($options, this$0, mathematics, 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30$lambda$24(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, AllProductsActivity this$0, View it) {
        TextView islamiat = $subjectBinding.islamiat;
        Intrinsics.checkNotNullExpressionValue(islamiat, "islamiat");
        onTapFilter$lambda$30$selectSubject($options, this$0, islamiat, 6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30$lambda$25(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, AllProductsActivity this$0, View it) {
        TextView generalKnowledge = $subjectBinding.generalKnowledge;
        Intrinsics.checkNotNullExpressionValue(generalKnowledge, "generalKnowledge");
        onTapFilter$lambda$30$selectSubject($options, this$0, generalKnowledge, 11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30$lambda$26(DialogFilterMainInventorySubjectBinding $subjectBinding, List $options, AllProductsActivity this$0, View it) {
        TextView artsAndCraft = $subjectBinding.artsAndCraft;
        Intrinsics.checkNotNullExpressionValue(artsAndCraft, "artsAndCraft");
        onTapFilter$lambda$30$selectSubject($options, this$0, artsAndCraft, 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30$lambda$27(AllProductsActivity this$0, AlertDialog $subjectDialog, View it) {
        this$0.selectedSubject = null;
        this$0.pageNo = 1;
        this$0.fetchProductsWithFilter();
        $subjectDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$30$lambda$28(AllProductsActivity this$0, AlertDialog $subjectDialog, AlertDialog $dialog, View it) {
        this$0.fetchProductsWithFilter();
        $subjectDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$31(AllProductsActivity this$0, AlertDialog $dialog, View it) {
        this$0.selectedSegment = null;
        this$0.selectedGrade = null;
        this$0.selectedSubject = null;
        this$0.pageNo = 1;
        this$0.fetchProductsWithFilter();
        $dialog.dismiss();
    }
}
