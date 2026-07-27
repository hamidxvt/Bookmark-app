package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.net.HttpHeaders;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitDetailsBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
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

/* compiled from: VisitDetailsFragment.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J(\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\n\u0010!\u001a\u0004\u0018\u00010\u0013H\u0002J\u0018\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR.\u0010\u000b\u001a\u001c\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitDetailsFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentVisitDetailsBinding;", "<init>", "()V", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "visitId", "", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "updateProfileMultipart", AppMeasurementSdk.ConditionalUserProperty.NAME, "email", "progressBar", "Landroid/widget/ProgressBar;", "dialog", "Landroid/app/AlertDialog;", "getToken", "showLoading", "show", "Companion", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitDetailsFragment extends BaseFragment<FragmentVisitDetailsBinding> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private String visitId;

    public VisitDetailsFragment() {
        final VisitDetailsFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$special$$inlined$viewModel$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                Fragment fragment = Fragment.this;
                Fragment fragment2 = Fragment.this;
                return companion.from(fragment, fragment2 instanceof SavedStateRegistryOwner ? fragment2 : null);
            }
        };
        final Function0 parameters$iv = null;
        final Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv);
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$special$$inlined$viewModel$default$3
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$special$$inlined$viewModel$default$4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) Function0.this.invoke()).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, factoryProducer$iv$iv);
    }

    private final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentVisitDetailsBinding> getBindingInflater() {
        return VisitDetailsFragment$bindingInflater$1.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        this.visitId = arguments != null ? arguments.getString(Constant.VISIT_ID) : null;
        getViewModel().visitDetails(String.valueOf(this.visitId));
        getViewModel().getVisitResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VisitDetailsFragment.onViewCreated$lambda$2(VisitDetailsFragment.this, (ApiResponseCallback) obj);
            }
        });
        getBinding().requestToEdit.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisitDetailsFragment.onViewCreated$lambda$6(VisitDetailsFragment.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$2(VisitDetailsFragment this$0, ApiResponseCallback it) {
        if (it != null) {
            if (it instanceof ApiResponseCallback.Error) {
                Log.e(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Error: " + ((ApiResponseCallback.Error) it).getMessage());
                String message = ((ApiResponseCallback.Error) it).getMessage();
                Intrinsics.checkNotNull(message);
                DialogExtKt.showMaterialAlertDialog$default(this$0, message, null, 2, null);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Loading)) {
                if (!(it instanceof ApiResponseCallback.Success)) {
                    throw new NoWhenBranchMatchedException();
                }
                VisitDetailsResponse data = (VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null) {
                    Boolean success = data.getSuccess();
                    Intrinsics.checkNotNull(success);
                    if (success.booleanValue()) {
                        this$0.getBinding().setItem(data);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$6(final VisitDetailsFragment this$0, View it) {
        View dialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_request_to_edit, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(this$0.requireActivity()).setView(dialogView).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button cancel = (Button) dialogView.findViewById(R.id.cancel_btn);
        Button startJob = (Button) dialogView.findViewById(R.id.start_job_btn);
        ImageView cross = (ImageView) dialogView.findViewById(R.id.start_job_cross_iv);
        final ProgressBar progress = (ProgressBar) dialogView.findViewById(R.id.progressBar);
        cross.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        startJob.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VisitDetailsFragment.onViewCreated$lambda$6$lambda$4(VisitDetailsFragment.this, progress, dialog, view);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$6$lambda$4(VisitDetailsFragment this$0, ProgressBar $progress, AlertDialog $dialog, View it) {
        Intrinsics.checkNotNull($progress);
        Intrinsics.checkNotNull($dialog);
        this$0.updateProfileMultipart("demo", "demo", $progress, $dialog);
    }

    /* compiled from: VisitDetailsFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitDetailsFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitDetailsFragment;", "visitId", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VisitDetailsFragment newInstance(String visitId) {
            Intrinsics.checkNotNullParameter(visitId, "visitId");
            VisitDetailsFragment fragment = new VisitDetailsFragment();
            Bundle args = new Bundle();
            args.putString(Constant.VISIT_ID, visitId);
            fragment.setArguments(args);
            return fragment;
        }
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
        client.newCall(request).enqueue(new VisitDetailsFragment$updateProfileMultipart$1(this, progressBar, dialog));
    }

    private final String getToken() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading(boolean show, ProgressBar progressBar) {
        progressBar.setVisibility(show ? 0 : 8);
    }
}
