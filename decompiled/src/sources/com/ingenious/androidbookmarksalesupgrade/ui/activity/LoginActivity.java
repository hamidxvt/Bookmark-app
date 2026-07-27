package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.savedstate.SavedStateRegistryOwner;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLoginBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutLoadingBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.request.LoginRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.LoginResponse;
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

/* compiled from: LoginActivity.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010\u0014\u001a\u00020\u00112\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\b\u0010\u0016\u001a\u00020\u0011H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/LoginActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "token", "", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityLoginBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "notificationPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "saveToken", "checkNotificationPermissionAndProceed", "navigateToHome", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private final ActivityResultLauncher<String> notificationPermissionLauncher;
    private String token;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public LoginActivity() {
        final LoginActivity $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LoginActivity$special$$inlined$viewModel$default$1
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
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LoginActivity$special$$inlined$viewModel$default$2
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
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(UserViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LoginActivity$special$$inlined$viewModel$default$3
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
        this.notificationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LoginActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                LoginActivity loginActivity = LoginActivity.this;
                ((Boolean) obj).booleanValue();
                loginActivity.navigateToHome();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UserViewModel getViewModel() {
        return (UserViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityLoginBinding.inflate(getLayoutInflater());
        ActivityLoginBinding activityLoginBinding = this.binding;
        ActivityLoginBinding activityLoginBinding2 = null;
        if (activityLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding = null;
        }
        setContentView(activityLoginBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        getViewModel().getLoginResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LoginActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LoginActivity.onCreate$lambda$4(LoginActivity.this, (ApiResponseCallback) obj);
            }
        });
        ActivityLoginBinding activityLoginBinding3 = this.binding;
        if (activityLoginBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityLoginBinding2 = activityLoginBinding3;
        }
        activityLoginBinding2.setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LoginActivity$onCreate$2
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
            public void onTapLogin() {
                UserViewModel viewModel;
                ActivityLoginBinding activityLoginBinding4;
                ActivityLoginBinding activityLoginBinding5;
                viewModel = LoginActivity.this.getViewModel();
                activityLoginBinding4 = LoginActivity.this.binding;
                ActivityLoginBinding activityLoginBinding6 = null;
                if (activityLoginBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityLoginBinding4 = null;
                }
                String obj = activityLoginBinding4.etEmployeeId.getText().toString();
                activityLoginBinding5 = LoginActivity.this.binding;
                if (activityLoginBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityLoginBinding6 = activityLoginBinding5;
                }
                viewModel.loginRequest(new LoginRequest(obj, String.valueOf(activityLoginBinding6.etPassword.getText())));
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapForgetPassword() {
                ActivityExtKt.gotoActivity(LoginActivity.this, ForgetPasswordActivity.class);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$4(final LoginActivity this$0, ApiResponseCallback it) {
        if (it != null) {
            ActivityLoginBinding activityLoginBinding = null;
            if (it instanceof ApiResponseCallback.Loading) {
                ActivityLoginBinding activityLoginBinding2 = this$0.binding;
                if (activityLoginBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityLoginBinding = activityLoginBinding2;
                }
                LayoutLoadingBinding layoutProgressIndicator = activityLoginBinding.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator, "layoutProgressIndicator");
                this$0.showProgressIndicator(layoutProgressIndicator);
                return;
            }
            if (it instanceof ApiResponseCallback.Success) {
                ActivityLoginBinding activityLoginBinding3 = this$0.binding;
                if (activityLoginBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityLoginBinding3 = null;
                }
                LayoutLoadingBinding layoutProgressIndicator2 = activityLoginBinding3.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator2, "layoutProgressIndicator");
                this$0.hideProgressIndicator(layoutProgressIndicator2);
                LoginResponse data = (LoginResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null) {
                    Boolean success = data.getSuccess();
                    Intrinsics.checkNotNull(success);
                    if (success.booleanValue()) {
                        String token = data.getToken();
                        Log.i("TAG", "onCreate: " + token);
                        this$0.saveToken(token);
                        ActivityExtKt.showMaterialAlertDialog(this$0, "Logged in Successfully", new DialogListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LoginActivity$onCreate$1$1$1$1
                            @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                            public void onNegativeButtonTap(DialogInterface dialog) {
                                DialogListeners.DefaultImpls.onNegativeButtonTap(this, dialog);
                            }

                            @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                            public void onPositionButtonTap(DialogInterface dialog) {
                                LoginActivity.this.checkNotificationPermissionAndProceed();
                            }
                        });
                        return;
                    }
                    String message = data.getMessage();
                    Intrinsics.checkNotNull(message);
                    ActivityExtKt.showMaterialAlertDialog$default(this$0, message, null, 2, null);
                    return;
                }
                return;
            }
            if (!(it instanceof ApiResponseCallback.Error)) {
                throw new NoWhenBranchMatchedException();
            }
            ActivityLoginBinding activityLoginBinding4 = this$0.binding;
            if (activityLoginBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityLoginBinding = activityLoginBinding4;
            }
            LayoutLoadingBinding layoutProgressIndicator3 = activityLoginBinding.layoutProgressIndicator;
            Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator3, "layoutProgressIndicator");
            this$0.hideProgressIndicator(layoutProgressIndicator3);
            this$0.genericNetworkErrorHandler(it, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LoginActivity$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit onCreate$lambda$4$lambda$3$lambda$2;
                    onCreate$lambda$4$lambda$3$lambda$2 = LoginActivity.onCreate$lambda$4$lambda$3$lambda$2((ErrorHandler) obj);
                    return onCreate$lambda$4$lambda$3$lambda$2;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$4$lambda$3$lambda$2(ErrorHandler it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    private final void saveToken(String token) {
        String str = token;
        if (!(str == null || str.length() == 0)) {
            SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
            Intrinsics.checkNotNull(sharedPref);
            SharedPreferences.Editor editor$iv = sharedPref.edit();
            editor$iv.putString("AUTH_TOKEN", token);
            editor$iv.apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkNotificationPermissionAndProceed() {
        if (Build.VERSION.SDK_INT >= 33) {
            boolean granted = ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == 0;
            if (!granted) {
                this.notificationPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS");
                return;
            } else {
                navigateToHome();
                return;
            }
        }
        navigateToHome();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void navigateToHome() {
        ActivityExtKt.gotoActivity(this, HomeActivity.class);
    }
}
