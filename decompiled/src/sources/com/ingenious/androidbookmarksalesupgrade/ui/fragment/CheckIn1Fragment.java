package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.EnvironmentCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCheckIn1Binding;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.model.request.LocationCheckRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.LocationCheckResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsCustomer;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.CheckInActivity;
import com.ingenious.androidbookmarksalesupgrade.utils.PermissionUtils;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: CheckIn1Fragment.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010&\u001a\u00020 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\r\u001a\u001c\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0010\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00040\u00040\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0011X\u0082D¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/CheckIn1Fragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentCheckIn1Binding;", "visitId", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "reason", "customerAddress", "priority", "customerType", "visitType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "isTestMode", "getLocationAndCheck", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CheckIn1Fragment extends BaseFragment<FragmentCheckIn1Binding> {
    private final String customerAddress;
    private final String customerType;
    private FusedLocationProviderClient fusedLocationClient;
    private final boolean isTestMode;
    private final String name;
    private final String priority;
    private final String reason;
    private final ActivityResultLauncher<String> requestPermissionLauncher;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private final String visitId;
    private final String visitType;

    public CheckIn1Fragment(String visitId, String name, String reason, String customerAddress, String priority, String customerType, String visitType) {
        Intrinsics.checkNotNullParameter(visitId, "visitId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(customerAddress, "customerAddress");
        Intrinsics.checkNotNullParameter(priority, "priority");
        Intrinsics.checkNotNullParameter(customerType, "customerType");
        Intrinsics.checkNotNullParameter(visitType, "visitType");
        this.visitId = visitId;
        this.name = name;
        this.reason = reason;
        this.customerAddress = customerAddress;
        this.priority = priority;
        this.customerType = customerType;
        this.visitType = visitType;
        final CheckIn1Fragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment$special$$inlined$viewModel$default$3
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment$special$$inlined$viewModel$default$4
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
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CheckIn1Fragment.requestPermissionLauncher$lambda$0(CheckIn1Fragment.this, ((Boolean) obj).booleanValue());
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.requestPermissionLauncher = registerForActivityResult;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentCheckIn1Binding> getBindingInflater() {
        return CheckIn1Fragment$bindingInflater$1.INSTANCE;
    }

    private final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionLauncher$lambda$0(CheckIn1Fragment this$0, boolean isGranted) {
        if (isGranted) {
            this$0.getLocationAndCheck();
        } else {
            DialogExtKt.showMaterialDialog$default(this$0, "Permission denied to access location.", (DialogListeners) null, 2, (Object) null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        getBinding().tvTitle.setText(this.name);
        getBinding().reasonTv.setText(this.reason);
        getBinding().locationTv.setText(this.customerAddress);
        getBinding().priorityTv.setText(this.priority);
        getBinding().customerType.setText(this.customerType);
        getBinding().visitType.setText(this.visitType);
        getBinding().btnContinue1.setEnabled(false);
        String str = this.priority;
        if (str == null || str.length() == 0) {
            getBinding().priorityTv.setVisibility(8);
        }
        String str2 = this.customerType;
        if (str2 == null || str2.length() == 0) {
            getBinding().customerType.setVisibility(8);
        }
        String str3 = this.visitType;
        if (str3 == null || str3.length() == 0) {
            getBinding().visitType.setVisibility(8);
        }
        getBinding().btnContinue1.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CheckIn1Fragment.onViewCreated$lambda$1(CheckIn1Fragment.this, view2);
            }
        });
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) requireActivity());
        PermissionUtils permissionUtils = PermissionUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        if (permissionUtils.isLocationPermissionGranted(requireContext)) {
            getLocationAndCheck();
        } else {
            this.requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION");
        }
        getViewModel().getLocationCheckResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CheckIn1Fragment.onViewCreated$lambda$2(CheckIn1Fragment.this, (ApiResponseCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(CheckIn1Fragment this$0, View it) {
        FragmentActivity activity = this$0.getActivity();
        CheckInActivity checkInActivity = activity instanceof CheckInActivity ? (CheckInActivity) activity : null;
        if (checkInActivity != null) {
            CheckInActivity.next$default(checkInActivity, null, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00bc -> B:19:0x0143). Please report as a decompilation issue!!! */
    public static final void onViewCreated$lambda$2(CheckIn1Fragment this$0, ApiResponseCallback response) {
        String distance;
        VisitDetailsCustomer visitDetailsCustomer;
        if (response instanceof ApiResponseCallback.Success) {
            LocationCheckResponse locationCheckResponse = (LocationCheckResponse) ((ApiResponseCallback.Success) response).getData();
            if (locationCheckResponse == null || (visitDetailsCustomer = locationCheckResponse.getVisitDetailsCustomer()) == null || (distance = visitDetailsCustomer.getDistance()) == null) {
                distance = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            try {
                if ((distance.length() > 0) && !Intrinsics.areEqual(distance, EnvironmentCompat.MEDIA_UNKNOWN) && Double.parseDouble(distance) <= 1.0d) {
                    this$0.getBinding().btnContinue1.setEnabled(true);
                    this$0.getBinding().locationChecking.setVisibility(8);
                    this$0.getBinding().locationNotVerified.setVisibility(8);
                    this$0.getBinding().locationVerified.setVisibility(0);
                } else {
                    this$0.getBinding().locationChecking.setVisibility(8);
                    this$0.getBinding().locationVerified.setVisibility(8);
                    this$0.getBinding().locationNotVerified.setVisibility(0);
                    this$0.getBinding().tvLocationNotVerifiedMessage.setText("You are not in the target location. Distance: " + distance + " km.");
                }
            } catch (Exception e) {
                DialogExtKt.showMaterialDialog$default(this$0, distance + e.getMessage(), (DialogListeners) null, 2, (Object) null);
            }
            return;
        }
        if (response instanceof ApiResponseCallback.Error) {
            CheckIn1Fragment checkIn1Fragment = this$0;
            String message = ((ApiResponseCallback.Error) response).getMessage();
            if (message == null) {
                message = "An error occurred.";
            }
            DialogExtKt.showMaterialDialog$default(checkIn1Fragment, message, (DialogListeners) null, 2, (Object) null);
            this$0.getBinding().locationChecking.setVisibility(8);
            this$0.getBinding().locationVerified.setVisibility(8);
            this$0.getBinding().locationNotVerified.setVisibility(0);
            return;
        }
        if (!(response instanceof ApiResponseCallback.Loading)) {
            throw new NoWhenBranchMatchedException();
        }
        this$0.getBinding().locationChecking.setVisibility(0);
        this$0.getBinding().locationVerified.setVisibility(8);
        this$0.getBinding().locationNotVerified.setVisibility(8);
        this$0.getBinding().tvLocationCheckingMessage.setText("Verifying Location...");
    }

    private final void getLocationAndCheck() {
        try {
            if (this.isTestMode) {
                LocationCheckRequest response = new LocationCheckRequest(this.visitId, "34.34524271406248", "73.19726058600797");
                getViewModel().locationCheck(response);
                return;
            }
            FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
            if (fusedLocationProviderClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
                fusedLocationProviderClient = null;
            }
            Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
            final Function1 function1 = new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit locationAndCheck$lambda$3;
                    locationAndCheck$lambda$3 = CheckIn1Fragment.getLocationAndCheck$lambda$3(CheckIn1Fragment.this, (Location) obj);
                    return locationAndCheck$lambda$3;
                }
            };
            Intrinsics.checkNotNull(lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment$$ExternalSyntheticLambda2
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    Function1.this.invoke(obj);
                }
            }));
        } catch (SecurityException e) {
            e.printStackTrace();
            getBinding().locationChecking.setVisibility(8);
            getBinding().locationVerified.setVisibility(8);
            getBinding().locationNotVerified.setVisibility(0);
            getBinding().tvLocationNotVerifiedMessage.setText("Location permission not granted.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getLocationAndCheck$lambda$3(CheckIn1Fragment this$0, Location location) {
        if (location != null) {
            LocationCheckRequest locationCheckRequest = new LocationCheckRequest(this$0.visitId, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
            this$0.getViewModel().locationCheck(locationCheckRequest);
        } else {
            DialogExtKt.showMaterialDialog$default(this$0, "Could not get location.", (DialogListeners) null, 2, (Object) null);
            this$0.getBinding().locationChecking.setVisibility(8);
            this$0.getBinding().locationVerified.setVisibility(8);
            this$0.getBinding().locationNotVerified.setVisibility(0);
            this$0.getBinding().tvLocationNotVerifiedMessage.setText("Could not get location.");
        }
        return Unit.INSTANCE;
    }
}
