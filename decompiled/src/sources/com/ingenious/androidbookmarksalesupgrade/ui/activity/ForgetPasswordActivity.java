package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.savedstate.SavedStateRegistryOwner;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityForgetPasswordBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutLoadingBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.request.ForgetRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.ErrorHandler;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.viewModel.UserViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ForgetPasswordActivity.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/ForgetPasswordActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityForgetPasswordBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class ForgetPasswordActivity extends BaseActivity {
    private ActivityForgetPasswordBinding binding;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public ForgetPasswordActivity() {
        final ForgetPasswordActivity $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ForgetPasswordActivity$special$$inlined$viewModel$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                ComponentActivity componentActivity = ComponentActivity.this;
                ComponentActivity componentActivity2 = ComponentActivity.this;
                return companion.from(componentActivity, componentActivity2 instanceof SavedStateRegistryOwner ? componentActivity2 : null);
            }
        };
        final Function0 parameters$iv = null;
        final Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv);
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ForgetPasswordActivity$special$$inlined$viewModel$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                Function0 owner$iv2 = Function0.this;
                Qualifier qualifier$iv2 = qualifier$iv;
                Function0 parameters$iv2 = parameters$iv;
                Scope scope$iv2 = scope$iv;
                ViewModelOwner ownerValue$iv = (ViewModelOwner) owner$iv2.invoke();
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(UserViewModel.class), qualifier$iv2, null, parameters$iv2, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv2, viewModelParameters$iv);
            }
        };
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(UserViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ForgetPasswordActivity$special$$inlined$viewModel$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, factoryProducer$iv$iv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UserViewModel getViewModel() {
        return (UserViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        ActivityForgetPasswordBinding activityForgetPasswordBinding = this.binding;
        ActivityForgetPasswordBinding activityForgetPasswordBinding2 = null;
        if (activityForgetPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityForgetPasswordBinding = null;
        }
        setContentView(activityForgetPasswordBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        ActivityForgetPasswordBinding activityForgetPasswordBinding3 = this.binding;
        if (activityForgetPasswordBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityForgetPasswordBinding3 = null;
        }
        activityForgetPasswordBinding3.backIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ForgetPasswordActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ForgetPasswordActivity.this.finish();
            }
        });
        getViewModel().getForgetResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ForgetPasswordActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ForgetPasswordActivity.onCreate$lambda$4(ForgetPasswordActivity.this, (ApiResponseCallback) obj);
            }
        });
        ActivityForgetPasswordBinding activityForgetPasswordBinding4 = this.binding;
        if (activityForgetPasswordBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityForgetPasswordBinding2 = activityForgetPasswordBinding4;
        }
        activityForgetPasswordBinding2.setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ForgetPasswordActivity$onCreate$3
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
            public void onTapFilter() {
                GenericListeners.DefaultImpls.onTapFilter(this);
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
            public void onTapForgetPassword() {
                UserViewModel viewModel;
                ActivityForgetPasswordBinding activityForgetPasswordBinding5;
                viewModel = ForgetPasswordActivity.this.getViewModel();
                activityForgetPasswordBinding5 = ForgetPasswordActivity.this.binding;
                if (activityForgetPasswordBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityForgetPasswordBinding5 = null;
                }
                viewModel.forgetRequest(new ForgetRequest(String.valueOf(activityForgetPasswordBinding5.etEmail.getText())));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$4(final ForgetPasswordActivity this$0, ApiResponseCallback it) {
        if (it != null) {
            ActivityForgetPasswordBinding activityForgetPasswordBinding = null;
            if (it instanceof ApiResponseCallback.Error) {
                ActivityForgetPasswordBinding activityForgetPasswordBinding2 = this$0.binding;
                if (activityForgetPasswordBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityForgetPasswordBinding = activityForgetPasswordBinding2;
                }
                LayoutLoadingBinding layoutProgressIndicator = activityForgetPasswordBinding.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator, "layoutProgressIndicator");
                this$0.hideProgressIndicator(layoutProgressIndicator);
                this$0.genericNetworkErrorHandler(it, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ForgetPasswordActivity$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit onCreate$lambda$4$lambda$3$lambda$1;
                        onCreate$lambda$4$lambda$3$lambda$1 = ForgetPasswordActivity.onCreate$lambda$4$lambda$3$lambda$1((ErrorHandler) obj);
                        return onCreate$lambda$4$lambda$3$lambda$1;
                    }
                });
                return;
            }
            if (it instanceof ApiResponseCallback.Loading) {
                ActivityForgetPasswordBinding activityForgetPasswordBinding3 = this$0.binding;
                if (activityForgetPasswordBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityForgetPasswordBinding = activityForgetPasswordBinding3;
                }
                LayoutLoadingBinding layoutProgressIndicator2 = activityForgetPasswordBinding.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator2, "layoutProgressIndicator");
                this$0.showProgressIndicator(layoutProgressIndicator2);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            ActivityForgetPasswordBinding activityForgetPasswordBinding4 = this$0.binding;
            if (activityForgetPasswordBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityForgetPasswordBinding4 = null;
            }
            LayoutLoadingBinding layoutProgressIndicator3 = activityForgetPasswordBinding4.layoutProgressIndicator;
            Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator3, "layoutProgressIndicator");
            this$0.hideProgressIndicator(layoutProgressIndicator3);
            GlobalResponse data = (GlobalResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                Boolean success = data.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue()) {
                    String message = data.getMessage();
                    Intrinsics.checkNotNull(message);
                    ActivityExtKt.showMaterialAlertDialog(this$0, message, new DialogListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ForgetPasswordActivity$onCreate$2$1$2$1
                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onNegativeButtonTap(DialogInterface dialog) {
                            DialogListeners.DefaultImpls.onNegativeButtonTap(this, dialog);
                        }

                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onPositionButtonTap(DialogInterface dialog) {
                            ActivityForgetPasswordBinding activityForgetPasswordBinding5;
                            ForgetPasswordActivity forgetPasswordActivity = ForgetPasswordActivity.this;
                            activityForgetPasswordBinding5 = ForgetPasswordActivity.this.binding;
                            if (activityForgetPasswordBinding5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityForgetPasswordBinding5 = null;
                            }
                            ActivityExtKt.gotoActivity(forgetPasswordActivity, OTPVerificationActivity.class, "email", String.valueOf(activityForgetPasswordBinding5.etEmail.getText()));
                            ForgetPasswordActivity.this.finish();
                        }
                    });
                } else {
                    String message2 = data.getMessage();
                    Intrinsics.checkNotNull(message2);
                    ActivityExtKt.showMaterialAlertDialog$default(this$0, message2, null, 2, null);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$4$lambda$3$lambda$1(ErrorHandler it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }
}
