package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.request.LocationCheckRequest;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: VisitDetailsActivity.kt */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/VisitDetailsActivity$onCreate$4", "Lcom/ingenious/androidbookmarksalesupgrade/listener/GenericListeners;", "onTapCheckIn", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitDetailsActivity$onCreate$4 implements GenericListeners {
    final /* synthetic */ VisitDetailsActivity this$0;

    VisitDetailsActivity$onCreate$4(VisitDetailsActivity $receiver) {
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
    public void onTapCheckIn() {
        VisitDetailsActivity visitDetailsActivity = this.this$0;
        final VisitDetailsActivity visitDetailsActivity2 = this.this$0;
        visitDetailsActivity.getCurrentLocation(new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$onCreate$4$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit onTapCheckIn$lambda$0;
                onTapCheckIn$lambda$0 = VisitDetailsActivity$onCreate$4.onTapCheckIn$lambda$0(VisitDetailsActivity.this, ((Double) obj).doubleValue(), ((Double) obj2).doubleValue());
                return onTapCheckIn$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onTapCheckIn$lambda$0(VisitDetailsActivity this$0, double latitude, double longitude) {
        VisitViewModel viewModel;
        String str;
        viewModel = this$0.getViewModel();
        str = this$0.visitId;
        viewModel.locationCheck(new LocationCheckRequest(str, String.valueOf(latitude), String.valueOf(longitude)));
        return Unit.INSTANCE;
    }
}
