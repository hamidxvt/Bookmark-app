package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.net.HttpHeaders;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityProfileBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.viewModel.UserViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ProfileActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J(\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0012H\u0002J\u0018\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u001c"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/ProfileActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityProfileBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "updateProfileMultipart", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "email", "progressBar", "Landroid/widget/ProgressBar;", "dialog", "Landroid/app/AlertDialog;", "getToken", "showLoading", "show", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class ProfileActivity extends BaseActivity {
    private ActivityProfileBinding binding;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public ProfileActivity() {
        final ProfileActivity $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$special$$inlined$viewModel$default$1
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
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$special$$inlined$viewModel$default$2
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
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(UserViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$special$$inlined$viewModel$default$3
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

    private final UserViewModel getViewModel() {
        return (UserViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityProfileBinding.inflate(getLayoutInflater());
        ActivityProfileBinding activityProfileBinding = this.binding;
        ActivityProfileBinding activityProfileBinding2 = null;
        if (activityProfileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileBinding = null;
        }
        setContentView(activityProfileBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        getViewModel().profile();
        getViewModel().getProfileResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ProfileActivity.onCreate$lambda$2(ProfileActivity.this, (ApiResponseCallback) obj);
            }
        });
        ActivityProfileBinding activityProfileBinding3 = this.binding;
        if (activityProfileBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileBinding3 = null;
        }
        activityProfileBinding3.setListener(new ProfileActivity$onCreate$2(this));
        ActivityProfileBinding activityProfileBinding4 = this.binding;
        if (activityProfileBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileBinding4 = null;
        }
        activityProfileBinding4.back.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileActivity.this.finish();
            }
        });
        ActivityProfileBinding activityProfileBinding5 = this.binding;
        if (activityProfileBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileBinding2 = activityProfileBinding5;
        }
        activityProfileBinding2.requestToEdit.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileActivity.onCreate$lambda$7(ProfileActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$2(ProfileActivity this$0, ApiResponseCallback it) {
        if (it != null) {
            ActivityProfileBinding activityProfileBinding = null;
            if (it instanceof ApiResponseCallback.Error) {
                ActivityExtKt.showMaterialAlertDialog$default(this$0, "Error Found!", null, 2, null);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Loading)) {
                if (!(it instanceof ApiResponseCallback.Success)) {
                    throw new NoWhenBranchMatchedException();
                }
                ProfileResponse data = (ProfileResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null) {
                    Boolean success = data.getSuccess();
                    Intrinsics.checkNotNull(success);
                    if (success.booleanValue()) {
                        ActivityProfileBinding activityProfileBinding2 = this$0.binding;
                        if (activityProfileBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityProfileBinding = activityProfileBinding2;
                        }
                        activityProfileBinding.setItem(data.getData());
                        Log.i("TAG", "onCreate: " + data.getData());
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(final ProfileActivity this$0, View it) {
        View dialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_request_to_edit, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(this$0).setView(dialogView).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button cancel = (Button) dialogView.findViewById(R.id.cancel_btn);
        Button startJob = (Button) dialogView.findViewById(R.id.start_job_btn);
        ImageView cross = (ImageView) dialogView.findViewById(R.id.start_job_cross_iv);
        final ProgressBar progress = (ProgressBar) dialogView.findViewById(R.id.progressBar);
        cross.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        startJob.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileActivity.onCreate$lambda$7$lambda$5(ProfileActivity.this, progress, dialog, view);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ProfileActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7$lambda$5(ProfileActivity this$0, ProgressBar $progress, AlertDialog $dialog, View it) {
        Intrinsics.checkNotNull($progress);
        Intrinsics.checkNotNull($dialog);
        this$0.updateProfileMultipart("demo", "demo", $progress, $dialog);
    }

    private final void updateProfileMultipart(String name, String email, ProgressBar progressBar, AlertDialog dialog) {
        showLoading(true, progressBar);
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM).addFormDataPart(AppMeasurementSdk.ConditionalUserProperty.NAME, name).addFormDataPart("email", email);
        MultipartBody requestBody = builder.build();
        Request.Builder addHeader = new Request.Builder().url("https://staging.bookmark.services/api/request/update/profile").post(requestBody).addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        client.newCall(request).enqueue(new ProfileActivity$updateProfileMultipart$1(this, progressBar, dialog));
    }

    private final String getToken() {
        SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading(boolean show, ProgressBar progressBar) {
        progressBar.setVisibility(show ? 0 : 8);
    }
}
