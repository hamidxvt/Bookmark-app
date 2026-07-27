package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAddVisitBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutLoadingBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddVisitRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddVisitResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SearchCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SearchData;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.util.ArrayList;
import java.util.Calendar;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: AddVisitActivity.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020 H\u0002J\b\u0010\u0018\u001a\u00020 H\u0002J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020\u0007H\u0002J$\u0010)\u001a\u00020 *\u00020*2\u0006\u0010+\u001a\u00020\u00072\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010-R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/AddVisitActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityAddVisitBinding;", "customerType", "", "getCustomerType", "()Ljava/lang/String;", "setCustomerType", "(Ljava/lang/String;)V", "priorityText", "getPriorityText", "setPriorityText", "customerId", "getCustomerId", "setCustomerId", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "searchAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SearchData;", "handler", "Landroid/os/Handler;", "runnable", "Ljava/lang/Runnable;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showDatePickerDialog", "displaySelectedDate", "timeInMillis", "", "setPriorityDrawable", "selectedPriority", "showMaterialAlertDialog", "Landroid/content/Context;", "message", "positiveClick", "Lkotlin/Function0;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class AddVisitActivity extends BaseActivity {
    private ActivityAddVisitBinding binding;
    private final Handler handler;
    private Runnable runnable;
    private GenericAdapter<SearchData> searchAdapter;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private String customerType = "";
    private String priorityText = "low";
    private String customerId = "";

    public AddVisitActivity() {
        final AddVisitActivity $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$special$$inlined$viewModel$default$1
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
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$special$$inlined$viewModel$default$2
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
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$special$$inlined$viewModel$default$3
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
        this.handler = new Handler(Looper.getMainLooper());
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final void setCustomerType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerType = str;
    }

    public final String getPriorityText() {
        return this.priorityText;
    }

    public final void setPriorityText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.priorityText = str;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAddVisitBinding.inflate(getLayoutInflater());
        ActivityAddVisitBinding activityAddVisitBinding = this.binding;
        ActivityAddVisitBinding activityAddVisitBinding2 = null;
        if (activityAddVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding = null;
        }
        setContentView(activityAddVisitBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        ActivityAddVisitBinding activityAddVisitBinding3 = this.binding;
        if (activityAddVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding3 = null;
        }
        activityAddVisitBinding3.setHeaderName("Add Visit");
        ActivityAddVisitBinding activityAddVisitBinding4 = this.binding;
        if (activityAddVisitBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding4 = null;
        }
        this.customerType = activityAddVisitBinding4.customerTypeSchool.getText().toString();
        ActivityAddVisitBinding activityAddVisitBinding5 = this.binding;
        if (activityAddVisitBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding5 = null;
        }
        activityAddVisitBinding5.schoolTypeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddVisitActivity.onCreate$lambda$0(AddVisitActivity.this, view);
            }
        });
        ActivityAddVisitBinding activityAddVisitBinding6 = this.binding;
        if (activityAddVisitBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding6 = null;
        }
        activityAddVisitBinding6.shopTypeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddVisitActivity.onCreate$lambda$1(AddVisitActivity.this, view);
            }
        });
        ActivityAddVisitBinding activityAddVisitBinding7 = this.binding;
        if (activityAddVisitBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding7 = null;
        }
        activityAddVisitBinding7.priorityHigh.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddVisitActivity.this.setPriorityDrawable("high");
            }
        });
        ActivityAddVisitBinding activityAddVisitBinding8 = this.binding;
        if (activityAddVisitBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding8 = null;
        }
        activityAddVisitBinding8.priorityLow.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddVisitActivity.this.setPriorityDrawable("low");
            }
        });
        ActivityAddVisitBinding activityAddVisitBinding9 = this.binding;
        if (activityAddVisitBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding9 = null;
        }
        activityAddVisitBinding9.priorityMedium.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddVisitActivity.this.setPriorityDrawable("medium");
            }
        });
        ActivityAddVisitBinding activityAddVisitBinding10 = this.binding;
        if (activityAddVisitBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding10 = null;
        }
        activityAddVisitBinding10.customerNameEt.addTextChangedListener(new AddVisitActivity$onCreate$6(this));
        searchAdapter();
        getViewModel().getSearchCustomerResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddVisitActivity.onCreate$lambda$7(AddVisitActivity.this, (ApiResponseCallback) obj);
            }
        });
        getViewModel().getAddVisitResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddVisitActivity.onCreate$lambda$12(AddVisitActivity.this, (ApiResponseCallback) obj);
            }
        });
        ActivityAddVisitBinding activityAddVisitBinding11 = this.binding;
        if (activityAddVisitBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddVisitBinding2 = activityAddVisitBinding11;
        }
        activityAddVisitBinding2.setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$onCreate$9
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
            public void onTapBack() {
                AddVisitActivity.this.finish();
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDate() {
                AddVisitActivity.this.showDatePickerDialog();
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddVisit() {
                VisitViewModel viewModel;
                ActivityAddVisitBinding activityAddVisitBinding12;
                ActivityAddVisitBinding activityAddVisitBinding13;
                Log.d("Button", "CustomerType: " + AddVisitActivity.this.getCustomerType() + ", Priority: " + AddVisitActivity.this.getPriorityText() + ", CustomerId: " + AddVisitActivity.this.getCustomerId());
                if (AddVisitActivity.this.getCustomerType().length() > 0) {
                    if (AddVisitActivity.this.getPriorityText().length() > 0) {
                        if (AddVisitActivity.this.getCustomerId().length() > 0) {
                            viewModel = AddVisitActivity.this.getViewModel();
                            String customerType = AddVisitActivity.this.getCustomerType();
                            String customerId = AddVisitActivity.this.getCustomerId();
                            activityAddVisitBinding12 = AddVisitActivity.this.binding;
                            ActivityAddVisitBinding activityAddVisitBinding14 = null;
                            if (activityAddVisitBinding12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityAddVisitBinding12 = null;
                            }
                            String obj = activityAddVisitBinding12.dateEt.getText().toString();
                            activityAddVisitBinding13 = AddVisitActivity.this.binding;
                            if (activityAddVisitBinding13 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityAddVisitBinding14 = activityAddVisitBinding13;
                            }
                            viewModel.addVisit(new AddVisitRequest(customerType, customerId, obj, activityAddVisitBinding14.purposeEt.getText().toString(), AddVisitActivity.this.getPriorityText()));
                            return;
                        }
                    }
                }
                AppToast.INSTANCE.showToast("Please fill all the fields");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(AddVisitActivity this$0, View it) {
        ActivityAddVisitBinding activityAddVisitBinding = this$0.binding;
        ActivityAddVisitBinding activityAddVisitBinding2 = null;
        if (activityAddVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding = null;
        }
        activityAddVisitBinding.customerNameTv.setText("School Name");
        ActivityAddVisitBinding activityAddVisitBinding3 = this$0.binding;
        if (activityAddVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding3 = null;
        }
        activityAddVisitBinding3.customerNameEt.setHint("Name Of School");
        ActivityAddVisitBinding activityAddVisitBinding4 = this$0.binding;
        if (activityAddVisitBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding4 = null;
        }
        this$0.customerType = activityAddVisitBinding4.customerTypeSchool.getText().toString();
        ActivityAddVisitBinding activityAddVisitBinding5 = this$0.binding;
        if (activityAddVisitBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding5 = null;
        }
        activityAddVisitBinding5.customerTypeSchool.setTextColor(this$0.getResources().getColor(R.color.app_color));
        ActivityAddVisitBinding activityAddVisitBinding6 = this$0.binding;
        if (activityAddVisitBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding6 = null;
        }
        activityAddVisitBinding6.customerTypeBookshop.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        ActivityAddVisitBinding activityAddVisitBinding7 = this$0.binding;
        if (activityAddVisitBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding7 = null;
        }
        DrawableCompat.setTint(activityAddVisitBinding7.customerTypeSchool.getCompoundDrawables()[0], ContextCompat.getColor(this$0, R.color.app_color));
        ActivityAddVisitBinding activityAddVisitBinding8 = this$0.binding;
        if (activityAddVisitBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding8 = null;
        }
        DrawableCompat.setTint(activityAddVisitBinding8.customerTypeBookshop.getCompoundDrawables()[0], ContextCompat.getColor(this$0, com.denzcoskun.imageslider.R.color.grey_font));
        ActivityAddVisitBinding activityAddVisitBinding9 = this$0.binding;
        if (activityAddVisitBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding9 = null;
        }
        activityAddVisitBinding9.schoolTypeLayout.setBackgroundResource(R.drawable.selected_button_background);
        ActivityAddVisitBinding activityAddVisitBinding10 = this$0.binding;
        if (activityAddVisitBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddVisitBinding2 = activityAddVisitBinding10;
        }
        activityAddVisitBinding2.shopTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(AddVisitActivity this$0, View it) {
        ActivityAddVisitBinding activityAddVisitBinding = this$0.binding;
        ActivityAddVisitBinding activityAddVisitBinding2 = null;
        if (activityAddVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding = null;
        }
        activityAddVisitBinding.customerNameTv.setText("Bookshop Name");
        ActivityAddVisitBinding activityAddVisitBinding3 = this$0.binding;
        if (activityAddVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding3 = null;
        }
        activityAddVisitBinding3.customerNameEt.setHint("Name Of Bookshop");
        ActivityAddVisitBinding activityAddVisitBinding4 = this$0.binding;
        if (activityAddVisitBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding4 = null;
        }
        this$0.customerType = activityAddVisitBinding4.customerTypeBookshop.getText().toString();
        ActivityAddVisitBinding activityAddVisitBinding5 = this$0.binding;
        if (activityAddVisitBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding5 = null;
        }
        activityAddVisitBinding5.customerTypeBookshop.setTextColor(this$0.getResources().getColor(R.color.app_color));
        ActivityAddVisitBinding activityAddVisitBinding6 = this$0.binding;
        if (activityAddVisitBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding6 = null;
        }
        activityAddVisitBinding6.customerTypeSchool.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        ActivityAddVisitBinding activityAddVisitBinding7 = this$0.binding;
        if (activityAddVisitBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding7 = null;
        }
        DrawableCompat.setTint(activityAddVisitBinding7.customerTypeSchool.getCompoundDrawables()[0], ContextCompat.getColor(this$0, com.denzcoskun.imageslider.R.color.grey_font));
        ActivityAddVisitBinding activityAddVisitBinding8 = this$0.binding;
        if (activityAddVisitBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding8 = null;
        }
        DrawableCompat.setTint(activityAddVisitBinding8.customerTypeBookshop.getCompoundDrawables()[0], ContextCompat.getColor(this$0, R.color.app_color));
        ActivityAddVisitBinding activityAddVisitBinding9 = this$0.binding;
        if (activityAddVisitBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding9 = null;
        }
        activityAddVisitBinding9.shopTypeLayout.setBackgroundResource(R.drawable.selected_button_background);
        ActivityAddVisitBinding activityAddVisitBinding10 = this$0.binding;
        if (activityAddVisitBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddVisitBinding2 = activityAddVisitBinding10;
        }
        activityAddVisitBinding2.schoolTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$7(AddVisitActivity this$0, ApiResponseCallback it) {
        if (it != null && !(it instanceof ApiResponseCallback.Error) && !(it instanceof ApiResponseCallback.Loading)) {
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            SearchCustomerResponse data = (SearchCustomerResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                Boolean success = data.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue()) {
                    GenericAdapter<SearchData> genericAdapter = this$0.searchAdapter;
                    if (genericAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("searchAdapter");
                        genericAdapter = null;
                    }
                    ArrayList<SearchData> data2 = data.getData();
                    genericAdapter.addList(data2 != null ? data2 : CollectionsKt.emptyList());
                    Log.i("TAG", "onCreate: " + data.getData());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$12(final AddVisitActivity this$0, ApiResponseCallback it) {
        if (it != null) {
            ActivityAddVisitBinding activityAddVisitBinding = null;
            if (it instanceof ApiResponseCallback.Error) {
                ActivityAddVisitBinding activityAddVisitBinding2 = this$0.binding;
                if (activityAddVisitBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAddVisitBinding = activityAddVisitBinding2;
                }
                LayoutLoadingBinding layoutProgressIndicator = activityAddVisitBinding.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator, "layoutProgressIndicator");
                this$0.hideProgressIndicator(layoutProgressIndicator);
                return;
            }
            if (it instanceof ApiResponseCallback.Loading) {
                ActivityAddVisitBinding activityAddVisitBinding3 = this$0.binding;
                if (activityAddVisitBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAddVisitBinding = activityAddVisitBinding3;
                }
                LayoutLoadingBinding layoutProgressIndicator2 = activityAddVisitBinding.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator2, "layoutProgressIndicator");
                this$0.showProgressIndicator(layoutProgressIndicator2);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            ActivityAddVisitBinding activityAddVisitBinding4 = this$0.binding;
            if (activityAddVisitBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAddVisitBinding = activityAddVisitBinding4;
            }
            LayoutLoadingBinding layoutProgressIndicator3 = activityAddVisitBinding.layoutProgressIndicator;
            Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator3, "layoutProgressIndicator");
            this$0.hideProgressIndicator(layoutProgressIndicator3);
            AddVisitResponse data = (AddVisitResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                if (Intrinsics.areEqual((Object) data.getSuccess(), (Object) true)) {
                    String message = data.getMessage();
                    Intrinsics.checkNotNull(message);
                    Toast.makeText(this$0, message, 0).show();
                    String message2 = data.getMessage();
                    Intrinsics.checkNotNull(message2);
                    this$0.showMaterialAlertDialog(this$0, message2, new Function0() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit onCreate$lambda$12$lambda$11$lambda$10$lambda$8;
                            onCreate$lambda$12$lambda$11$lambda$10$lambda$8 = AddVisitActivity.onCreate$lambda$12$lambda$11$lambda$10$lambda$8(AddVisitActivity.this);
                            return onCreate$lambda$12$lambda$11$lambda$10$lambda$8;
                        }
                    });
                    return;
                }
                AddVisitActivity addVisitActivity = this$0;
                String message3 = data.getMessage();
                if (message3 == null) {
                    message3 = "Failed to add customer";
                }
                Toast.makeText(addVisitActivity, message3, 1).show();
                AddVisitActivity addVisitActivity2 = this$0;
                String message4 = data.getMessage();
                if (message4 == null) {
                    message4 = "Something went wrong!";
                }
                this$0.showMaterialAlertDialog(addVisitActivity2, message4, new Function0() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda6
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
    public static final Unit onCreate$lambda$12$lambda$11$lambda$10$lambda$8(AddVisitActivity this$0) {
        this$0.setResult(-1);
        this$0.finish();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda3
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                AddVisitActivity.showDatePickerDialog$lambda$13(calendar, this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
        View findViewById = datePickerDialog.findViewById(android.R.id.content);
        ViewParent parent = findViewById != null ? findViewById.getParent() : null;
        ViewGroup root = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (root != null) {
            root.setPadding(0, 0, 0, 0);
        }
        Window window = datePickerDialog.getWindow();
        if (window != null) {
            window.setLayout(-1, -2);
        }
        datePickerDialog.getDatePicker().post(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AddVisitActivity.showDatePickerDialog$lambda$14(datePickerDialog, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDatePickerDialog$lambda$13(Calendar $calendar, AddVisitActivity this$0, DatePicker datePicker, int year, int month, int dayOfMonth) {
        $calendar.set(year, month, dayOfMonth);
        this$0.displaySelectedDate($calendar.getTimeInMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDatePickerDialog$lambda$14(DatePickerDialog $datePickerDialog, AddVisitActivity this$0) {
        Button button = $datePickerDialog.getButton(-1);
        if (button != null) {
            button.setTextColor(ContextCompat.getColor(this$0, R.color.black));
        }
        Button button2 = $datePickerDialog.getButton(-2);
        if (button2 != null) {
            button2.setTextColor(ContextCompat.getColor(this$0, R.color.black));
        }
    }

    private final void searchAdapter() {
        this.searchAdapter = new GenericAdapter<>(R.layout.item_customers_selection_list, new AddVisitActivity$searchAdapter$1(this));
        ActivityAddVisitBinding activityAddVisitBinding = this.binding;
        ActivityAddVisitBinding activityAddVisitBinding2 = null;
        if (activityAddVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding = null;
        }
        RecyclerView recyclerView = activityAddVisitBinding.customerListRv;
        GenericAdapter<SearchData> genericAdapter = this.searchAdapter;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchAdapter");
            genericAdapter = null;
        }
        recyclerView.setAdapter(genericAdapter);
        ActivityAddVisitBinding activityAddVisitBinding3 = this.binding;
        if (activityAddVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddVisitBinding2 = activityAddVisitBinding3;
        }
        activityAddVisitBinding2.customerListRv.setLayoutManager(new LinearLayoutManager(this, 1, false));
    }

    private final void displaySelectedDate(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        String dateString = DateFormat.format("yyyy-MM-dd", calendar.getTime()).toString();
        ActivityAddVisitBinding activityAddVisitBinding = this.binding;
        if (activityAddVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding = null;
        }
        activityAddVisitBinding.dateEt.setText(dateString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void setPriorityDrawable(String selectedPriority) {
        ContextCompat.getDrawable(this, R.drawable.edittext_background);
        int selectedTextColor = ContextCompat.getColor(this, R.color.white);
        int defaultTextColor = ContextCompat.getColor(this, R.color.black);
        ActivityAddVisitBinding activityAddVisitBinding = this.binding;
        ActivityAddVisitBinding activityAddVisitBinding2 = null;
        if (activityAddVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding = null;
        }
        activityAddVisitBinding.priorityHigh.setTextColor(defaultTextColor);
        ActivityAddVisitBinding activityAddVisitBinding3 = this.binding;
        if (activityAddVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding3 = null;
        }
        activityAddVisitBinding3.priorityLow.setTextColor(defaultTextColor);
        ActivityAddVisitBinding activityAddVisitBinding4 = this.binding;
        if (activityAddVisitBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding4 = null;
        }
        activityAddVisitBinding4.priorityMedium.setTextColor(defaultTextColor);
        switch (selectedPriority.hashCode()) {
            case -1078030475:
                if (selectedPriority.equals("medium")) {
                    ActivityAddVisitBinding activityAddVisitBinding5 = this.binding;
                    if (activityAddVisitBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding5 = null;
                    }
                    activityAddVisitBinding5.priorityMedium.setBackground(ContextCompat.getDrawable(this, R.drawable.medium));
                    ActivityAddVisitBinding activityAddVisitBinding6 = this.binding;
                    if (activityAddVisitBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding6 = null;
                    }
                    activityAddVisitBinding6.priorityMedium.setTextColor(selectedTextColor);
                    ActivityAddVisitBinding activityAddVisitBinding7 = this.binding;
                    if (activityAddVisitBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding7 = null;
                    }
                    activityAddVisitBinding7.priorityHigh.setBackground(ContextCompat.getDrawable(this, R.drawable.hight_white));
                    ActivityAddVisitBinding activityAddVisitBinding8 = this.binding;
                    if (activityAddVisitBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding8 = null;
                    }
                    activityAddVisitBinding8.priorityHigh.setTextColor(defaultTextColor);
                    ActivityAddVisitBinding activityAddVisitBinding9 = this.binding;
                    if (activityAddVisitBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding9 = null;
                    }
                    activityAddVisitBinding9.priorityLow.setBackground(ContextCompat.getDrawable(this, R.drawable.low_white));
                    ActivityAddVisitBinding activityAddVisitBinding10 = this.binding;
                    if (activityAddVisitBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityAddVisitBinding2 = activityAddVisitBinding10;
                    }
                    activityAddVisitBinding2.priorityLow.setTextColor(defaultTextColor);
                    this.priorityText = "medium";
                    break;
                }
                break;
            case 107348:
                if (selectedPriority.equals("low")) {
                    ActivityAddVisitBinding activityAddVisitBinding11 = this.binding;
                    if (activityAddVisitBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding11 = null;
                    }
                    activityAddVisitBinding11.priorityLow.setBackground(ContextCompat.getDrawable(this, R.drawable.low));
                    ActivityAddVisitBinding activityAddVisitBinding12 = this.binding;
                    if (activityAddVisitBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding12 = null;
                    }
                    activityAddVisitBinding12.priorityLow.setTextColor(selectedTextColor);
                    ActivityAddVisitBinding activityAddVisitBinding13 = this.binding;
                    if (activityAddVisitBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding13 = null;
                    }
                    activityAddVisitBinding13.priorityHigh.setBackground(ContextCompat.getDrawable(this, R.drawable.hight_white));
                    ActivityAddVisitBinding activityAddVisitBinding14 = this.binding;
                    if (activityAddVisitBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding14 = null;
                    }
                    activityAddVisitBinding14.priorityHigh.setTextColor(defaultTextColor);
                    ActivityAddVisitBinding activityAddVisitBinding15 = this.binding;
                    if (activityAddVisitBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding15 = null;
                    }
                    activityAddVisitBinding15.priorityMedium.setBackground(ContextCompat.getDrawable(this, R.drawable.medium_white));
                    ActivityAddVisitBinding activityAddVisitBinding16 = this.binding;
                    if (activityAddVisitBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityAddVisitBinding2 = activityAddVisitBinding16;
                    }
                    activityAddVisitBinding2.priorityMedium.setTextColor(defaultTextColor);
                    this.priorityText = "low";
                    break;
                }
                break;
            case 3202466:
                if (selectedPriority.equals("high")) {
                    ActivityAddVisitBinding activityAddVisitBinding17 = this.binding;
                    if (activityAddVisitBinding17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding17 = null;
                    }
                    activityAddVisitBinding17.priorityHigh.setBackground(ContextCompat.getDrawable(this, R.drawable.high));
                    ActivityAddVisitBinding activityAddVisitBinding18 = this.binding;
                    if (activityAddVisitBinding18 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding18 = null;
                    }
                    activityAddVisitBinding18.priorityHigh.setTextColor(selectedTextColor);
                    ActivityAddVisitBinding activityAddVisitBinding19 = this.binding;
                    if (activityAddVisitBinding19 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding19 = null;
                    }
                    activityAddVisitBinding19.priorityMedium.setBackground(ContextCompat.getDrawable(this, R.drawable.medium_white));
                    ActivityAddVisitBinding activityAddVisitBinding20 = this.binding;
                    if (activityAddVisitBinding20 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding20 = null;
                    }
                    activityAddVisitBinding20.priorityMedium.setTextColor(defaultTextColor);
                    ActivityAddVisitBinding activityAddVisitBinding21 = this.binding;
                    if (activityAddVisitBinding21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding21 = null;
                    }
                    activityAddVisitBinding21.priorityLow.setBackground(ContextCompat.getDrawable(this, R.drawable.low_white));
                    ActivityAddVisitBinding activityAddVisitBinding22 = this.binding;
                    if (activityAddVisitBinding22 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityAddVisitBinding2 = activityAddVisitBinding22;
                    }
                    activityAddVisitBinding2.priorityLow.setTextColor(defaultTextColor);
                    this.priorityText = "high";
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showMaterialAlertDialog$default(AddVisitActivity addVisitActivity, Context context, String str, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        addVisitActivity.showMaterialAlertDialog(context, str, function0);
    }

    public final void showMaterialAlertDialog(Context $this$showMaterialAlertDialog, String message, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter($this$showMaterialAlertDialog, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        View view = LayoutInflater.from($this$showMaterialAlertDialog).inflate(R.layout.dialog_message, (ViewGroup) null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        AlertDialog dialog = new MaterialAlertDialogBuilder($this$showMaterialAlertDialog).setView(view).setCancelable(false).setPositiveButton((CharSequence) "OK", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AddVisitActivity.showMaterialAlertDialog$lambda$15(Function0.this, dialogInterface, i);
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
    public static final void showMaterialAlertDialog$lambda$15(Function0 $positiveClick, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if ($positiveClick != null) {
            $positiveClick.invoke();
        }
    }
}
