package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCheckIn2Binding;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.CheckInActivity;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.viewModel.CheckInViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: CheckIn2Fragment.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\"\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0007\u001a\u001c\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006 "}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/CheckIn2Fragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentCheckIn2Binding;", "visitId", "", "<init>", "(Ljava/lang/String;)V", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "checkInViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/CheckInViewModel;", "getCheckInViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/CheckInViewModel;", "checkInViewModel$delegate", "Lkotlin/Lazy;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CheckIn2Fragment extends BaseFragment<FragmentCheckIn2Binding> {

    /* renamed from: checkInViewModel$delegate, reason: from kotlin metadata */
    private final Lazy checkInViewModel;
    private final String visitId;

    public CheckIn2Fragment(String visitId) {
        Intrinsics.checkNotNullParameter(visitId, "visitId");
        this.visitId = visitId;
        final CheckIn2Fragment $this$activityViewModels_u24default$iv = this;
        final Function0 extrasProducer$iv = null;
        this.checkInViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$activityViewModels_u24default$iv, Reflection.getOrCreateKotlinClass(CheckInViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn2Fragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return Fragment.this.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn2Fragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function0 = Function0.this;
                return (function0 == null || (creationExtras = (CreationExtras) function0.invoke()) == null) ? $this$activityViewModels_u24default$iv.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn2Fragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentCheckIn2Binding> getBindingInflater() {
        return CheckIn2Fragment$bindingInflater$1.INSTANCE;
    }

    private final CheckInViewModel getCheckInViewModel() {
        return (CheckInViewModel) this.checkInViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        getBinding().btnOpenCamera.setVisibility(0);
        getBinding().cameraOpen.setVisibility(0);
        getBinding().imageSet.setVisibility(8);
        getBinding().setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn2Fragment$onViewCreated$1
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
            public void onTapCaptureImage() {
                ImagePicker.INSTANCE.with(CheckIn2Fragment.this).cameraOnly().start();
            }
        });
        getBinding().btnContinue2.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn2Fragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CheckIn2Fragment.onViewCreated$lambda$0(CheckIn2Fragment.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(CheckIn2Fragment this$0, View it) {
        String value = this$0.getCheckInViewModel().getPhotoPath().getValue();
        if (value == null || value.length() == 0) {
            AppToast.INSTANCE.showToast("Please select an image first.");
            return;
        }
        FragmentActivity activity = this$0.getActivity();
        CheckInActivity checkInActivity = activity instanceof CheckInActivity ? (CheckInActivity) activity : null;
        if (checkInActivity != null) {
            CheckInActivity.next$default(checkInActivity, null, 1, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case -1:
                Uri uri = data != null ? data.getData() : null;
                Intrinsics.checkNotNull(uri);
                getCheckInViewModel().getPhotoPath().setValue(uri.toString());
                getBinding().imageSet.setVisibility(0);
                Glide.with(this).load(uri).thumbnail(0.25f).centerCrop().into(getBinding().imageSet);
                getBinding().btnOpenCamera.setVisibility(8);
                getBinding().cameraOpen.setVisibility(8);
                break;
            case 64:
                AppToast.INSTANCE.showToast(ImagePicker.INSTANCE.getError(data));
                break;
            default:
                AppToast.INSTANCE.showToast("Cancelled!");
                break;
        }
    }
}
