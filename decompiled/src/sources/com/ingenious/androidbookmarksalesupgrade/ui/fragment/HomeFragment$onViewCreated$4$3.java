package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentHomeBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.LocationModel;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationDisplayAct;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.viewModel.JobViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeFragment.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/fragment/HomeFragment$onViewCreated$4$3", "Lcom/ingenious/androidbookmarksalesupgrade/listener/GenericListeners;", "onTapDateNext", "", "onTapDatePrevious", "onTapProfile", "onTapSwitch", "onTapAddHome", "onTapFilter", "onSettingClick", "onNotificationClick", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class HomeFragment$onViewCreated$4$3 implements GenericListeners {
    final /* synthetic */ FragmentHomeBinding $this_apply;
    final /* synthetic */ HomeFragment this$0;

    HomeFragment$onViewCreated$4$3(HomeFragment $receiver, FragmentHomeBinding $receiver2) {
        this.this$0 = $receiver;
        this.$this_apply = $receiver2;
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
    public void onTapViewSelection() {
        GenericListeners.DefaultImpls.onTapViewSelection(this);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapDateNext() {
        Calendar calendar;
        calendar = this.this$0.calendar;
        calendar.add(5, 1);
        this.this$0.updateDate();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapDatePrevious() {
        Calendar calendar;
        calendar = this.this$0.calendar;
        calendar.add(5, -1);
        this.this$0.updateDate();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapProfile() {
        ActivityExtKt.gotoActivityFromFragment(this.this$0, ProfileActivity.class);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapSwitch() {
        View dialogView = this.this$0.getLayoutInflater().inflate(R.layout.dialog_job_start, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(this.this$0.requireContext()).setView(dialogView).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button cancel = (Button) dialogView.findViewById(R.id.cancel_btn);
        final Button startJob = (Button) dialogView.findViewById(R.id.start_job_btn);
        ImageView cross = (ImageView) dialogView.findViewById(R.id.start_job_cross_iv);
        startJob.setText(this.this$0.getStatus() ? "End Job" : "Start Job");
        final HomeFragment homeFragment = this.this$0;
        final FragmentHomeBinding fragmentHomeBinding = this.$this_apply;
        startJob.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapSwitch$lambda$2(HomeFragment.this, startJob, fragmentHomeBinding, dialog, view);
            }
        });
        final HomeFragment homeFragment2 = this.this$0;
        cancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapSwitch$lambda$3(dialog, homeFragment2, view);
            }
        });
        final HomeFragment homeFragment3 = this.this$0;
        cross.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapSwitch$lambda$4(dialog, homeFragment3, view);
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapSwitch$lambda$2(final HomeFragment this$0, final Button $startJob, final FragmentHomeBinding $this_apply, final AlertDialog $dialog, View it) {
        FusedLocationProviderClient fusedLocationProviderClient;
        if (ActivityCompat.checkSelfPermission(this$0.requireContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this$0.requireContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            fusedLocationProviderClient = this$0.fusedLocationClient;
            if (fusedLocationProviderClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
                fusedLocationProviderClient = null;
            }
            Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
            final Function1 function1 = new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit onTapSwitch$lambda$2$lambda$0;
                    onTapSwitch$lambda$2$lambda$0 = HomeFragment$onViewCreated$4$3.onTapSwitch$lambda$2$lambda$0(HomeFragment.this, $startJob, $this_apply, $dialog, (Location) obj);
                    return onTapSwitch$lambda$2$lambda$0;
                }
            };
            lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda11
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onTapSwitch$lambda$2$lambda$0(HomeFragment this$0, Button $startJob, FragmentHomeBinding $this_apply, AlertDialog $dialog, Location location) {
        boolean checkNotificationPermissionAndProceed;
        MainViewModel viewModel;
        double d;
        double d2;
        long j;
        long j2;
        long j3;
        String formatTime;
        JobViewModel jobViewModel;
        JobViewModel jobViewModel2;
        long j4;
        double d3;
        double d4;
        if (location != null) {
            this$0.latitude = location.getLatitude();
            this$0.longitude = location.getLongitude();
            LocationModel userLocation = AppPreferences.INSTANCE.getUserLocation();
            if (userLocation != null) {
                d4 = this$0.latitude;
                userLocation.setUserLatitude(Double.valueOf(d4));
            }
            LocationModel userLocation2 = AppPreferences.INSTANCE.getUserLocation();
            if (userLocation2 != null) {
                d3 = this$0.longitude;
                userLocation2.setUserLongitude(Double.valueOf(d3));
            }
            checkNotificationPermissionAndProceed = this$0.checkNotificationPermissionAndProceed();
            if (checkNotificationPermissionAndProceed) {
                boolean newStatus = !this$0.getStatus();
                viewModel = this$0.getViewModel();
                d = this$0.latitude;
                String valueOf = String.valueOf(d);
                d2 = this$0.longitude;
                viewModel.jobStatusRequest(newStatus, valueOf, String.valueOf(d2));
                if (newStatus) {
                    this$0.startTime = System.currentTimeMillis();
                    jobViewModel = this$0.getJobViewModel();
                    jobViewModel.setStartTime(System.currentTimeMillis());
                    jobViewModel2 = this$0.getJobViewModel();
                    jobViewModel2.setStatus(true);
                    TextView statusText = this$0.getBinding().statusText;
                    Intrinsics.checkNotNullExpressionValue(statusText, "statusText");
                    this$0.startTimer(statusText);
                    j4 = this$0.startTime;
                    this$0.saveJobStartTime(j4);
                    $startJob.setText("End Job");
                    $this_apply.seeStatsLinear.setVisibility(0);
                    $this_apply.jobStartLinear.setVisibility(8);
                    this$0.startLocationService();
                    $this_apply.materialSwitch.setChecked(true);
                } else {
                    this$0.endTime = System.currentTimeMillis();
                    this$0.stopTimer();
                    this$0.clearJobStartTime();
                    j = this$0.endTime;
                    j2 = this$0.startTime;
                    long j5 = j - j2;
                    TextView textView = $this_apply.statusText;
                    j3 = this$0.endTime;
                    formatTime = this$0.formatTime(j3);
                    textView.setText("Ended at: " + formatTime);
                    $startJob.setText("Start Job");
                    $this_apply.seeStatsLinear.setVisibility(8);
                    $this_apply.jobStartLinear.setVisibility(0);
                    this$0.stopLocationService();
                    $this_apply.materialSwitch.setChecked(false);
                }
                this$0.setStatus(newStatus);
                this$0.updateButtonUI(newStatus);
            } else {
                Toast.makeText(this$0.requireContext(), "Notification permission is required", 0).show();
            }
            $dialog.dismiss();
        } else {
            AppToast.INSTANCE.showToast("Unable to get location");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapSwitch$lambda$3(AlertDialog $dialog, HomeFragment this$0, View it) {
        $dialog.dismiss();
        this$0.getBinding().materialSwitch.setChecked(this$0.getStatus());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapSwitch$lambda$4(AlertDialog $dialog, HomeFragment this$0, View it) {
        $dialog.dismiss();
        this$0.getBinding().materialSwitch.setChecked(this$0.getStatus());
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapAddHome() {
        View dialogView = this.this$0.getLayoutInflater().inflate(R.layout.dialog_add_home, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(this.this$0.requireContext()).setView(dialogView).create();
        Window $this$onTapAddHome_u24lambda_u245 = dialog.getWindow();
        if ($this$onTapAddHome_u24lambda_u245 != null) {
            $this$onTapAddHome_u24lambda_u245.setBackgroundDrawable(new ColorDrawable(0));
            $this$onTapAddHome_u24lambda_u245.setGravity(80);
            WindowManager.LayoutParams params = $this$onTapAddHome_u24lambda_u245.getAttributes();
            params.y = 210;
            $this$onTapAddHome_u24lambda_u245.setAttributes(params);
        }
        dialog.show();
        LinearLayout addVisit = (LinearLayout) dialogView.findViewById(R.id.add_visit);
        LinearLayout addCustomer = (LinearLayout) dialogView.findViewById(R.id.add_customer);
        ImageView closeBtn = (ImageView) dialogView.findViewById(R.id.btn_close);
        final HomeFragment homeFragment = this.this$0;
        addVisit.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapAddHome$lambda$6(HomeFragment.this, dialog, view);
            }
        });
        final HomeFragment homeFragment2 = this.this$0;
        addCustomer.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapAddHome$lambda$7(HomeFragment.this, view);
            }
        });
        closeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapAddHome$lambda$6(HomeFragment this$0, AlertDialog $dialog, View it) {
        ActivityExtKt.gotoActivityFromFragment(this$0, AddVisitActivity.class);
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapAddHome$lambda$7(HomeFragment this$0, View it) {
        ActivityExtKt.gotoActivityFromFragment(this$0, AddCustomerActivity.class);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onTapFilter() {
        View dialogView = this.this$0.getLayoutInflater().inflate(R.layout.dialog_filter_main, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(this.this$0.requireContext()).setView(dialogView).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button cancel = (Button) dialogView.findViewById(R.id.btnClearAll);
        ImageView crossBtn = (ImageView) dialogView.findViewById(R.id.ivClose);
        LinearLayout priority = (LinearLayout) dialogView.findViewById(R.id.btnPriority);
        LinearLayout distance = (LinearLayout) dialogView.findViewById(R.id.btnDistance);
        LinearLayout customerType = (LinearLayout) dialogView.findViewById(R.id.btnCustomerType);
        LinearLayout addedBy = (LinearLayout) dialogView.findViewById(R.id.btnAddedBy);
        final HomeFragment homeFragment = this.this$0;
        priority.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$15(HomeFragment.this, dialog, view);
            }
        });
        final HomeFragment homeFragment2 = this.this$0;
        distance.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$21(HomeFragment.this, dialog, view);
            }
        });
        final HomeFragment homeFragment3 = this.this$0;
        customerType.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$28(HomeFragment.this, dialog, view);
            }
        });
        final HomeFragment homeFragment4 = this.this$0;
        addedBy.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$35(HomeFragment.this, dialog, view);
            }
        });
        final HomeFragment homeFragment5 = this.this$0;
        cancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$36(HomeFragment.this, dialog, view);
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$15(final HomeFragment this$0, final AlertDialog $dialog, View it) {
        View priorityDialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_priority_filter, (ViewGroup) null);
        final AlertDialog priorityDialog = new AlertDialog.Builder(this$0.requireContext()).setView(priorityDialogView).create();
        Window window = priorityDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button priorityCancel = (Button) priorityDialogView.findViewById(R.id.priority_cancel_btn);
        ImageView priorityCrossBtn = (ImageView) priorityDialogView.findViewById(R.id.priority_cross_iv);
        TextView high = (TextView) priorityDialogView.findViewById(R.id.high_priority);
        TextView medium = (TextView) priorityDialogView.findViewById(R.id.medium_priority);
        TextView low = (TextView) priorityDialogView.findViewById(R.id.low_priority);
        Button priorityDone = (Button) priorityDialogView.findViewById(R.id.priority_done_btn);
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{high, medium, low});
        List $this$forEach$iv = options;
        for (Object element$iv : $this$forEach$iv) {
            final TextView tv = (TextView) element$iv;
            tv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda19
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment$onViewCreated$4$3.onTapFilter$lambda$15$lambda$11$lambda$10(options, tv, this$0, view);
                }
            });
            priorityDialogView = priorityDialogView;
        }
        priorityCrossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$15$lambda$12(HomeFragment.this, priorityDialog, view);
            }
        });
        priorityDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$15$lambda$13(HomeFragment.this, priorityDialog, $dialog, view);
            }
        });
        priorityCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$15$lambda$14(HomeFragment.this, priorityDialog, view);
            }
        });
        priorityDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$15$lambda$11$lambda$10(List $options, TextView $tv, HomeFragment this$0, View it) {
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
        this$0.selectedPriority = $tv.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$15$lambda$12(HomeFragment this$0, AlertDialog $priorityDialog, View it) {
        this$0.selectedPriority = "";
        $priorityDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$15$lambda$13(HomeFragment this$0, AlertDialog $priorityDialog, AlertDialog $dialog, View it) {
        MainViewModel viewModel;
        String str;
        viewModel = this$0.getViewModel();
        str = this$0.selectedPriority;
        MainViewModel.homeRequest$default(viewModel, null, null, null, str, null, null, null, 119, null);
        $priorityDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$15$lambda$14(HomeFragment this$0, AlertDialog $priorityDialog, View it) {
        this$0.selectedPriority = "";
        $priorityDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$21(final HomeFragment this$0, final AlertDialog $dialog, View it) {
        View distanceDialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_distance_filter, (ViewGroup) null);
        final AlertDialog distanceDialog = new AlertDialog.Builder(this$0.requireContext()).setView(distanceDialogView).create();
        Window window = distanceDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button distanceCancel = (Button) distanceDialogView.findViewById(R.id.distance_cancel_btn);
        ImageView crossBtnDistance = (ImageView) distanceDialogView.findViewById(R.id.distance_cross_iv);
        Button distanceDoneBtn = (Button) distanceDialogView.findViewById(R.id.distance_done_btn);
        LinearLayout closestLinear = (LinearLayout) distanceDialogView.findViewById(R.id.closest_linear);
        final TextView closest = (TextView) distanceDialogView.findViewById(R.id.closest_tv);
        LinearLayout farthestLinear = (LinearLayout) distanceDialogView.findViewById(R.id.farthest_linear);
        final TextView farthest = (TextView) distanceDialogView.findViewById(R.id.farthest_tv);
        final ImageView selectedCircleClosest = (ImageView) distanceDialogView.findViewById(R.id.selected_circle);
        final LinearLayout unSelectedCircleClosest = (LinearLayout) distanceDialogView.findViewById(R.id.unselected_circle);
        final ImageView selectedCircleFarthest = (ImageView) distanceDialogView.findViewById(R.id.selected_circle_farthest);
        final LinearLayout unSelectedCircleFarthest = (LinearLayout) distanceDialogView.findViewById(R.id.unselected_circle_farthest);
        selectedCircleClosest.setVisibility(8);
        unSelectedCircleClosest.setVisibility(0);
        selectedCircleFarthest.setVisibility(8);
        unSelectedCircleFarthest.setVisibility(0);
        closestLinear.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda30
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$21$lambda$16(selectedCircleClosest, unSelectedCircleClosest, selectedCircleFarthest, unSelectedCircleFarthest, this$0, closest, view);
            }
        });
        farthestLinear.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$21$lambda$17(selectedCircleClosest, unSelectedCircleClosest, selectedCircleFarthest, unSelectedCircleFarthest, this$0, farthest, view);
            }
        });
        crossBtnDistance.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$21$lambda$18(HomeFragment.this, distanceDialog, view);
            }
        });
        distanceDoneBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$21$lambda$19(HomeFragment.this, distanceDialog, $dialog, view);
            }
        });
        distanceCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$21$lambda$20(HomeFragment.this, distanceDialog, view);
            }
        });
        distanceDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$21$lambda$16(ImageView $selectedCircleClosest, LinearLayout $unSelectedCircleClosest, ImageView $selectedCircleFarthest, LinearLayout $unSelectedCircleFarthest, HomeFragment this$0, TextView $closest, View it) {
        $selectedCircleClosest.setVisibility(0);
        $unSelectedCircleClosest.setVisibility(8);
        $selectedCircleFarthest.setVisibility(8);
        $unSelectedCircleFarthest.setVisibility(0);
        this$0.selectedDistance = $closest.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$21$lambda$17(ImageView $selectedCircleClosest, LinearLayout $unSelectedCircleClosest, ImageView $selectedCircleFarthest, LinearLayout $unSelectedCircleFarthest, HomeFragment this$0, TextView $farthest, View it) {
        $selectedCircleClosest.setVisibility(8);
        $unSelectedCircleClosest.setVisibility(0);
        $selectedCircleFarthest.setVisibility(0);
        $unSelectedCircleFarthest.setVisibility(8);
        this$0.selectedDistance = $farthest.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$21$lambda$18(HomeFragment this$0, AlertDialog $distanceDialog, View it) {
        this$0.selectedDistance = "";
        $distanceDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$21$lambda$19(HomeFragment this$0, AlertDialog $distanceDialog, AlertDialog $dialog, View it) {
        MainViewModel viewModel;
        String str;
        viewModel = this$0.getViewModel();
        str = this$0.selectedDistance;
        MainViewModel.homeRequest$default(viewModel, null, null, null, null, str, null, null, 111, null);
        $distanceDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$21$lambda$20(HomeFragment this$0, AlertDialog $distanceDialog, View it) {
        this$0.selectedDistance = "";
        $distanceDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$28(final HomeFragment this$0, final AlertDialog $dialog, View it) {
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
        TextView shop = (TextView) customerTypeDialogView.findViewById(R.id.type_bookshop);
        Button customerTypeDone = (Button) customerTypeDialogView.findViewById(R.id.customer_done_btn);
        final List options = CollectionsKt.listOf((Object[]) new TextView[]{all, school, shop});
        List $this$forEach$iv = options;
        for (Object element$iv : $this$forEach$iv) {
            final TextView tv = (TextView) element$iv;
            tv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda22
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment$onViewCreated$4$3.onTapFilter$lambda$28$lambda$24$lambda$23(options, tv, this$0, view);
                }
            });
            customerTypeDialogView = customerTypeDialogView;
        }
        customerTypeDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$28$lambda$25(HomeFragment.this, customerTypeDialog, $dialog, view);
            }
        });
        customerTypeCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$28$lambda$26(HomeFragment.this, customerTypeDialog, view);
            }
        });
        crossBtnCustomerType.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment$onViewCreated$4$3.onTapFilter$lambda$28$lambda$27(HomeFragment.this, customerTypeDialog, view);
            }
        });
        customerTypeDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$28$lambda$24$lambda$23(List $options, TextView $tv, HomeFragment this$0, View it) {
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
    public static final void onTapFilter$lambda$28$lambda$25(HomeFragment this$0, AlertDialog $customerTypeDialog, AlertDialog $dialog, View it) {
        MainViewModel viewModel;
        String str;
        viewModel = this$0.getViewModel();
        str = this$0.selectedCustomerType;
        MainViewModel.homeRequest$default(viewModel, null, null, null, null, null, str, null, 95, null);
        $customerTypeDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$28$lambda$26(HomeFragment this$0, AlertDialog $customerTypeDialog, View it) {
        this$0.selectedCustomerType = "";
        $customerTypeDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$28$lambda$27(HomeFragment this$0, AlertDialog $customerTypeDialog, View it) {
        this$0.selectedCustomerType = "";
        $customerTypeDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$35(final HomeFragment this$0, final AlertDialog $dialog, View it) {
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
            tv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment$onViewCreated$4$3.onTapFilter$lambda$35$lambda$34$lambda$30(options, tv, this$0, view);
                }
            });
            crossBtnAddedBy.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment$onViewCreated$4$3.onTapFilter$lambda$35$lambda$34$lambda$31(HomeFragment.this, addedByDialog, view);
                }
            });
            addedByDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment$onViewCreated$4$3.onTapFilter$lambda$35$lambda$34$lambda$32(HomeFragment.this, addedByDialog, $dialog, view);
                }
            });
            addedByCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$onViewCreated$4$3$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment$onViewCreated$4$3.onTapFilter$lambda$35$lambda$34$lambda$33(HomeFragment.this, addedByDialog, view);
                }
            });
            addedByDialogView = addedByDialogView;
            options = options;
        }
        addedByDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$35$lambda$34$lambda$30(List $options, TextView $tv, HomeFragment this$0, View it) {
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
    public static final void onTapFilter$lambda$35$lambda$34$lambda$31(HomeFragment this$0, AlertDialog $addedByDialog, View it) {
        this$0.selectedAddedBy = "";
        $addedByDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$35$lambda$34$lambda$32(HomeFragment this$0, AlertDialog $addedByDialog, AlertDialog $dialog, View it) {
        MainViewModel viewModel;
        String str;
        viewModel = this$0.getViewModel();
        str = this$0.selectedAddedBy;
        MainViewModel.homeRequest$default(viewModel, null, null, null, null, null, null, str, 63, null);
        $addedByDialog.dismiss();
        $dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$35$lambda$34$lambda$33(HomeFragment this$0, AlertDialog $addedByDialog, View it) {
        this$0.selectedAddedBy = "";
        $addedByDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapFilter$lambda$36(HomeFragment this$0, AlertDialog $dialog, View it) {
        this$0.selectedPriority = "";
        this$0.selectedDistance = "";
        this$0.selectedAddedBy = "";
        this$0.selectedCustomerType = "";
        $dialog.dismiss();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onSettingClick() {
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onNotificationClick() {
        Intent intent = new Intent(this.this$0.requireContext(), (Class<?>) NotificationDisplayAct.class);
        this.this$0.startActivity(intent);
        GenericListeners.DefaultImpls.onNotificationClick(this);
    }
}
