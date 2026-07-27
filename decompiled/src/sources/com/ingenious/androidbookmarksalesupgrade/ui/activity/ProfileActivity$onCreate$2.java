package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import kotlin.Metadata;

/* compiled from: ProfileActivity.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/ProfileActivity$onCreate$2", "Lcom/ingenious/androidbookmarksalesupgrade/listener/GenericListeners;", "onTapLogout", "", "onSettingClick", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class ProfileActivity$onCreate$2 implements GenericListeners {
    final /* synthetic */ ProfileActivity this$0;

    ProfileActivity$onCreate$2(ProfileActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onNotificationClick() {
        GenericListeners.DefaultImpls.onNotificationClick(this);
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
    public void onTapFilter() {
        GenericListeners.DefaultImpls.onTapFilter(this);
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
    public void onTapLogout() {
        View dialogView = this.this$0.getLayoutInflater().inflate(R.layout.dialog_signout, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(this.this$0).setView(dialogView).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button cancel = (Button) dialogView.findViewById(R.id.cancel_btn);
        Button startJob = (Button) dialogView.findViewById(R.id.start_job_btn);
        ImageView cross = (ImageView) dialogView.findViewById(R.id.start_job_cross_iv);
        cross.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$onCreate$2$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        final ProfileActivity profileActivity = this.this$0;
        startJob.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$onCreate$2$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileActivity$onCreate$2.onTapLogout$lambda$1(ProfileActivity.this, dialog, view);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$onCreate$2$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapLogout$lambda$1(ProfileActivity this$0, AlertDialog $dialog, View it) {
        AppPreferences.INSTANCE.setLoginData(null);
        ActivityExtKt.gotoActivity(this$0, LoginActivity.class);
        $dialog.dismiss();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
    public void onSettingClick() {
        Intent intent = new Intent(this.this$0, (Class<?>) SettingScreenAct.class);
        this.this$0.startActivity(intent);
        GenericListeners.DefaultImpls.onSettingClick(this);
    }
}
