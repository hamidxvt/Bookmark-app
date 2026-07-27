package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAddCustomerBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutLoadingBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddCustomerRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: AddCustomerActivity.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0014J$\u0010(\u001a\u00020%*\u00020)2\u0006\u0010*\u001a\u00020\r2\u0010\b\u0002\u0010+\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010,J\u0006\u0010-\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u001a\u0010\u001b\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u000e\u0010\u001e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082.¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/AddCustomerActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityAddCustomerBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "customerType", "", "getCustomerType", "()Ljava/lang/String;", "setCustomerType", "(Ljava/lang/String;)V", "schoolNameText", "getSchoolNameText", "setSchoolNameText", "principalNameText", "getPrincipalNameText", "setPrincipalNameText", "locationText", "getLocationText", "setLocationText", "phoneText", "getPhoneText", "setPhoneText", "shopLat", "shopLng", "shopAddress", "resultLauncherForShopAddress", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showMaterialAlertDialog", "Landroid/content/Context;", "message", "positiveClick", "Lkotlin/Function0;", "generateRandomPhoneNumber", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class AddCustomerActivity extends BaseActivity {
    private ActivityAddCustomerBinding binding;
    private String customerType;
    private String locationText;
    private String phoneText;
    private String principalNameText;
    private ActivityResultLauncher<Intent> resultLauncherForShopAddress;
    private String schoolNameText;
    private String shopAddress;
    private String shopLat;
    private String shopLng;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public AddCustomerActivity() {
        final AddCustomerActivity $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$special$$inlined$viewModel$default$1
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
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$special$$inlined$viewModel$default$2
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
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(VisitViewModel.class), qualifier$iv2, null, parameters$iv2, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv2, viewModelParameters$iv);
            }
        };
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$special$$inlined$viewModel$default$3
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
        this.customerType = "";
        this.schoolNameText = "";
        this.principalNameText = "";
        this.locationText = "";
        this.phoneText = "";
        this.shopLat = "";
        this.shopLng = "";
        this.shopAddress = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final void setCustomerType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerType = str;
    }

    public final String getSchoolNameText() {
        return this.schoolNameText;
    }

    public final void setSchoolNameText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.schoolNameText = str;
    }

    public final String getPrincipalNameText() {
        return this.principalNameText;
    }

    public final void setPrincipalNameText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.principalNameText = str;
    }

    public final String getLocationText() {
        return this.locationText;
    }

    public final void setLocationText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.locationText = str;
    }

    public final String getPhoneText() {
        return this.phoneText;
    }

    public final void setPhoneText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.phoneText = str;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAddCustomerBinding.inflate(getLayoutInflater());
        ActivityAddCustomerBinding activityAddCustomerBinding = this.binding;
        ActivityAddCustomerBinding activityAddCustomerBinding2 = null;
        if (activityAddCustomerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding = null;
        }
        setContentView(activityAddCustomerBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        ActivityAddCustomerBinding activityAddCustomerBinding3 = this.binding;
        if (activityAddCustomerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding3 = null;
        }
        activityAddCustomerBinding3.setHeaderName("Create Location");
        ActivityAddCustomerBinding activityAddCustomerBinding4 = this.binding;
        if (activityAddCustomerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding4 = null;
        }
        this.customerType = activityAddCustomerBinding4.customerTypeSchool.getText().toString();
        getViewModel().getAddCustomerResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddCustomerActivity.onCreate$lambda$4(AddCustomerActivity.this, (ApiResponseCallback) obj);
            }
        });
        ActivityAddCustomerBinding activityAddCustomerBinding5 = this.binding;
        if (activityAddCustomerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding5 = null;
        }
        activityAddCustomerBinding5.schoolTypeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddCustomerActivity.onCreate$lambda$5(AddCustomerActivity.this, view);
            }
        });
        ActivityAddCustomerBinding activityAddCustomerBinding6 = this.binding;
        if (activityAddCustomerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding6 = null;
        }
        activityAddCustomerBinding6.shopTypeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddCustomerActivity.onCreate$lambda$6(AddCustomerActivity.this, view);
            }
        });
        ActivityAddCustomerBinding activityAddCustomerBinding7 = this.binding;
        if (activityAddCustomerBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddCustomerBinding2 = activityAddCustomerBinding7;
        }
        activityAddCustomerBinding2.setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$onCreate$4
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
            public void onTapAddHome() {
                GenericListeners.DefaultImpls.onTapAddHome(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddVisit() {
                GenericListeners.DefaultImpls.onTapAddVisit(this);
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
            public void onTapBack() {
                AddCustomerActivity.this.finish();
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLocation() {
                ActivityResultLauncher activityResultLauncher;
                AddCustomerActivity addCustomerActivity = AddCustomerActivity.this;
                activityResultLauncher = AddCustomerActivity.this.resultLauncherForShopAddress;
                if (activityResultLauncher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("resultLauncherForShopAddress");
                    activityResultLauncher = null;
                }
                ActivityExtKt.gotoActivityForResult(addCustomerActivity, LocationActivity.class, activityResultLauncher);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddCustomer() {
                VisitViewModel viewModel;
                ActivityAddCustomerBinding activityAddCustomerBinding8;
                String str;
                String str2;
                String str3;
                viewModel = AddCustomerActivity.this.getViewModel();
                activityAddCustomerBinding8 = AddCustomerActivity.this.binding;
                if (activityAddCustomerBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddCustomerBinding8 = null;
                }
                String obj = activityAddCustomerBinding8.customerNameEt.getText().toString();
                String customerType = AddCustomerActivity.this.getCustomerType();
                str = AddCustomerActivity.this.shopAddress;
                String generateRandomPhoneNumber = AddCustomerActivity.this.generateRandomPhoneNumber();
                str2 = AddCustomerActivity.this.shopLat;
                str3 = AddCustomerActivity.this.shopLng;
                viewModel.addCustomer(new AddCustomerRequest(obj, "Dumy Principle Name Add from Developer Side", generateRandomPhoneNumber, str, customerType, str2, str3));
            }
        });
        this.resultLauncherForShopAddress = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                AddCustomerActivity.onCreate$lambda$9(AddCustomerActivity.this, (ActivityResult) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$4(final AddCustomerActivity this$0, ApiResponseCallback it) {
        if (it != null) {
            ActivityAddCustomerBinding activityAddCustomerBinding = null;
            if (it instanceof ApiResponseCallback.Error) {
                ActivityAddCustomerBinding activityAddCustomerBinding2 = this$0.binding;
                if (activityAddCustomerBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAddCustomerBinding = activityAddCustomerBinding2;
                }
                LayoutLoadingBinding layoutProgressIndicator = activityAddCustomerBinding.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator, "layoutProgressIndicator");
                this$0.hideProgressIndicator(layoutProgressIndicator);
                return;
            }
            if (it instanceof ApiResponseCallback.Loading) {
                ActivityAddCustomerBinding activityAddCustomerBinding3 = this$0.binding;
                if (activityAddCustomerBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAddCustomerBinding = activityAddCustomerBinding3;
                }
                LayoutLoadingBinding layoutProgressIndicator2 = activityAddCustomerBinding.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator2, "layoutProgressIndicator");
                this$0.showProgressIndicator(layoutProgressIndicator2);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            ActivityAddCustomerBinding activityAddCustomerBinding4 = this$0.binding;
            if (activityAddCustomerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAddCustomerBinding = activityAddCustomerBinding4;
            }
            LayoutLoadingBinding layoutProgressIndicator3 = activityAddCustomerBinding.layoutProgressIndicator;
            Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator3, "layoutProgressIndicator");
            this$0.hideProgressIndicator(layoutProgressIndicator3);
            AddCustomerResponse data = (AddCustomerResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                if (Intrinsics.areEqual((Object) data.getSuccess(), (Object) true)) {
                    String message = data.getMessage();
                    Intrinsics.checkNotNull(message);
                    Toast.makeText(this$0, message, 0).show();
                    String message2 = data.getMessage();
                    Intrinsics.checkNotNull(message2);
                    this$0.showMaterialAlertDialog(this$0, message2, new Function0() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit onCreate$lambda$4$lambda$3$lambda$2$lambda$0;
                            onCreate$lambda$4$lambda$3$lambda$2$lambda$0 = AddCustomerActivity.onCreate$lambda$4$lambda$3$lambda$2$lambda$0(AddCustomerActivity.this);
                            return onCreate$lambda$4$lambda$3$lambda$2$lambda$0;
                        }
                    });
                    return;
                }
                AddCustomerActivity addCustomerActivity = this$0;
                String message3 = data.getMessage();
                if (message3 == null) {
                    message3 = "Failed to add customer";
                }
                Toast.makeText(addCustomerActivity, message3, 1).show();
                AddCustomerActivity addCustomerActivity2 = this$0;
                String message4 = data.getMessage();
                if (message4 == null) {
                    message4 = "Something went wrong!";
                }
                this$0.showMaterialAlertDialog(addCustomerActivity2, message4, new Function0() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$4$lambda$3$lambda$2$lambda$0(AddCustomerActivity this$0) {
        this$0.setResult(-1);
        this$0.finish();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(AddCustomerActivity this$0, View it) {
        ActivityAddCustomerBinding activityAddCustomerBinding = this$0.binding;
        ActivityAddCustomerBinding activityAddCustomerBinding2 = null;
        if (activityAddCustomerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding = null;
        }
        activityAddCustomerBinding.customerNameTv.setText("School Name");
        ActivityAddCustomerBinding activityAddCustomerBinding3 = this$0.binding;
        if (activityAddCustomerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding3 = null;
        }
        activityAddCustomerBinding3.customerNameEt.setHint("Name Of School");
        ActivityAddCustomerBinding activityAddCustomerBinding4 = this$0.binding;
        if (activityAddCustomerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding4 = null;
        }
        activityAddCustomerBinding4.customerOwnerNameTv.setText("Principal Name");
        ActivityAddCustomerBinding activityAddCustomerBinding5 = this$0.binding;
        if (activityAddCustomerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding5 = null;
        }
        activityAddCustomerBinding5.customerOwnerNameEt.setHint("Enter Principal Name");
        ActivityAddCustomerBinding activityAddCustomerBinding6 = this$0.binding;
        if (activityAddCustomerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding6 = null;
        }
        this$0.customerType = activityAddCustomerBinding6.customerTypeSchool.getText().toString();
        ActivityAddCustomerBinding activityAddCustomerBinding7 = this$0.binding;
        if (activityAddCustomerBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding7 = null;
        }
        activityAddCustomerBinding7.customerTypeSchool.setTextColor(this$0.getResources().getColor(R.color.app_color));
        ActivityAddCustomerBinding activityAddCustomerBinding8 = this$0.binding;
        if (activityAddCustomerBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding8 = null;
        }
        activityAddCustomerBinding8.customerTypeBookshop.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        ActivityAddCustomerBinding activityAddCustomerBinding9 = this$0.binding;
        if (activityAddCustomerBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding9 = null;
        }
        DrawableCompat.setTint(activityAddCustomerBinding9.customerTypeSchool.getCompoundDrawables()[0], ContextCompat.getColor(this$0, R.color.app_color));
        ActivityAddCustomerBinding activityAddCustomerBinding10 = this$0.binding;
        if (activityAddCustomerBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding10 = null;
        }
        DrawableCompat.setTint(activityAddCustomerBinding10.customerTypeBookshop.getCompoundDrawables()[0], ContextCompat.getColor(this$0, com.denzcoskun.imageslider.R.color.grey_font));
        ActivityAddCustomerBinding activityAddCustomerBinding11 = this$0.binding;
        if (activityAddCustomerBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding11 = null;
        }
        activityAddCustomerBinding11.schoolTypeLayout.setBackgroundResource(R.drawable.selected_button_background);
        ActivityAddCustomerBinding activityAddCustomerBinding12 = this$0.binding;
        if (activityAddCustomerBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddCustomerBinding2 = activityAddCustomerBinding12;
        }
        activityAddCustomerBinding2.shopTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(AddCustomerActivity this$0, View it) {
        ActivityAddCustomerBinding activityAddCustomerBinding = this$0.binding;
        ActivityAddCustomerBinding activityAddCustomerBinding2 = null;
        if (activityAddCustomerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding = null;
        }
        activityAddCustomerBinding.customerNameTv.setText("Bookshop Name");
        ActivityAddCustomerBinding activityAddCustomerBinding3 = this$0.binding;
        if (activityAddCustomerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding3 = null;
        }
        activityAddCustomerBinding3.customerNameEt.setHint("Name Of Bookshop");
        ActivityAddCustomerBinding activityAddCustomerBinding4 = this$0.binding;
        if (activityAddCustomerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding4 = null;
        }
        activityAddCustomerBinding4.customerOwnerNameTv.setText("Owner Name");
        ActivityAddCustomerBinding activityAddCustomerBinding5 = this$0.binding;
        if (activityAddCustomerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding5 = null;
        }
        activityAddCustomerBinding5.customerOwnerNameEt.setHint("Enter Owner Name");
        ActivityAddCustomerBinding activityAddCustomerBinding6 = this$0.binding;
        if (activityAddCustomerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding6 = null;
        }
        this$0.customerType = activityAddCustomerBinding6.customerTypeBookshop.getText().toString();
        ActivityAddCustomerBinding activityAddCustomerBinding7 = this$0.binding;
        if (activityAddCustomerBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding7 = null;
        }
        activityAddCustomerBinding7.customerTypeBookshop.setTextColor(this$0.getResources().getColor(R.color.app_color));
        ActivityAddCustomerBinding activityAddCustomerBinding8 = this$0.binding;
        if (activityAddCustomerBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding8 = null;
        }
        activityAddCustomerBinding8.customerTypeSchool.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        ActivityAddCustomerBinding activityAddCustomerBinding9 = this$0.binding;
        if (activityAddCustomerBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding9 = null;
        }
        DrawableCompat.setTint(activityAddCustomerBinding9.customerTypeSchool.getCompoundDrawables()[0], ContextCompat.getColor(this$0, com.denzcoskun.imageslider.R.color.grey_font));
        ActivityAddCustomerBinding activityAddCustomerBinding10 = this$0.binding;
        if (activityAddCustomerBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding10 = null;
        }
        DrawableCompat.setTint(activityAddCustomerBinding10.customerTypeBookshop.getCompoundDrawables()[0], ContextCompat.getColor(this$0, R.color.app_color));
        ActivityAddCustomerBinding activityAddCustomerBinding11 = this$0.binding;
        if (activityAddCustomerBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddCustomerBinding11 = null;
        }
        activityAddCustomerBinding11.shopTypeLayout.setBackgroundResource(R.drawable.selected_button_background);
        ActivityAddCustomerBinding activityAddCustomerBinding12 = this$0.binding;
        if (activityAddCustomerBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddCustomerBinding2 = activityAddCustomerBinding12;
        }
        activityAddCustomerBinding2.schoolTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(AddCustomerActivity this$0, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            Intent data = result.getData();
            if (data != null) {
                ActivityAddCustomerBinding activityAddCustomerBinding = this$0.binding;
                if (activityAddCustomerBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddCustomerBinding = null;
                }
                activityAddCustomerBinding.locationEt.setText(data.getStringExtra(Constant.ADDRESS));
                this$0.shopAddress = String.valueOf(data.getStringExtra(Constant.ADDRESS));
                this$0.shopLat = String.valueOf(data.getStringExtra(Constant.LATITUDE));
                this$0.shopLng = String.valueOf(data.getStringExtra(Constant.LONGITUDE));
                return;
            }
            showMaterialAlertDialog$default(this$0, this$0, Constant.ErrorMessage.no_data_found, null, 2, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showMaterialAlertDialog$default(AddCustomerActivity addCustomerActivity, Context context, String str, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        addCustomerActivity.showMaterialAlertDialog(context, str, function0);
    }

    public final void showMaterialAlertDialog(Context $this$showMaterialAlertDialog, String message, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter($this$showMaterialAlertDialog, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        View view = LayoutInflater.from($this$showMaterialAlertDialog).inflate(R.layout.dialog_message, (ViewGroup) null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        AlertDialog dialog = new MaterialAlertDialogBuilder($this$showMaterialAlertDialog).setView(view).setCancelable(false).setPositiveButton((CharSequence) "OK", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddCustomerActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AddCustomerActivity.showMaterialAlertDialog$lambda$10(Function0.this, dialogInterface, i);
            }
        }).show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.dialog_background_all);
        }
        Button positiveButton = dialog.getButton(-1);
        positiveButton.setTextColor(ContextCompat.getColor($this$showMaterialAlertDialog, R.color.white));
        positiveButton.setTextSize(20.0f);
        positiveButton.setTypeface(positiveButton.getTypeface(), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMaterialAlertDialog$lambda$10(Function0 $positiveClick, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if ($positiveClick != null) {
            $positiveClick.invoke();
        }
    }

    public final String generateRandomPhoneNumber() {
        int randomDigits = RangesKt.random(new IntRange(10000000, 99999999), Random.INSTANCE);
        return "03" + randomDigits;
    }
}
