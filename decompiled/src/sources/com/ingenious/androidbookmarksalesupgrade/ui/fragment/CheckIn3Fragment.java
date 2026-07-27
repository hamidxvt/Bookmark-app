package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCheckIn3Binding;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.model.request.LocationCheckRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.LocationCheckResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsCustomer;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.viewModel.CheckInViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: CheckIn3Fragment.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001fH\u0002J\u0018\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0007\u001a\u001c\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u00040\u00040\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/CheckIn3Fragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentCheckIn3Binding;", "visitId", "", "<init>", "(Ljava/lang/String;)V", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkInViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/CheckInViewModel;", "getCheckInViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/CheckInViewModel;", "checkInViewModel$delegate", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "getLocationAndCheck", "compressImage", "Ljava/io/File;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CheckIn3Fragment extends BaseFragment<FragmentCheckIn3Binding> {

    /* renamed from: checkInViewModel$delegate, reason: from kotlin metadata */
    private final Lazy checkInViewModel;
    private FusedLocationProviderClient fusedLocationClient;
    private final ActivityResultLauncher<String> requestPermissionLauncher;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private final String visitId;

    public CheckIn3Fragment(String visitId) {
        Intrinsics.checkNotNullParameter(visitId, "visitId");
        this.visitId = visitId;
        final CheckIn3Fragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$special$$inlined$viewModel$default$3
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$special$$inlined$viewModel$default$4
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
        final CheckIn3Fragment $this$activityViewModels_u24default$iv = this;
        final Function0 extrasProducer$iv = null;
        this.checkInViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$activityViewModels_u24default$iv, Reflection.getOrCreateKotlinClass(CheckInViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return Fragment.this.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CheckIn3Fragment.requestPermissionLauncher$lambda$0(CheckIn3Fragment.this, ((Boolean) obj).booleanValue());
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.requestPermissionLauncher = registerForActivityResult;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentCheckIn3Binding> getBindingInflater() {
        return CheckIn3Fragment$bindingInflater$1.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    private final CheckInViewModel getCheckInViewModel() {
        return (CheckInViewModel) this.checkInViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionLauncher$lambda$0(CheckIn3Fragment this$0, boolean isGranted) {
        if (isGranted) {
            this$0.getLocationAndCheck();
        } else {
            DialogExtKt.showMaterialDialog$default(this$0, "Permission denied to access location.", (DialogListeners) null, 2, (Object) null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        getBinding().btnStartVisit.setEnabled(false);
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) requireActivity());
        String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault()).format(new Date());
        getBinding().currentTimeTv.setText(currentDate);
        getBinding().btnStartVisit.setEnabled(true);
        getViewModel().getLocationCheckResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CheckIn3Fragment.onViewCreated$lambda$1(CheckIn3Fragment.this, (ApiResponseCallback) obj);
            }
        });
        getViewModel().getImageCheckResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CheckIn3Fragment.onViewCreated$lambda$2(CheckIn3Fragment.this, (ApiResponseCallback) obj);
            }
        });
        getBinding().btnStartVisit.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CheckIn3Fragment.onViewCreated$lambda$3(CheckIn3Fragment.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$1(CheckIn3Fragment this$0, ApiResponseCallback response) {
        String distance;
        VisitDetailsCustomer visitDetailsCustomer;
        if (response instanceof ApiResponseCallback.Success) {
            LocationCheckResponse locationCheckResponse = (LocationCheckResponse) ((ApiResponseCallback.Success) response).getData();
            if (locationCheckResponse == null || (visitDetailsCustomer = locationCheckResponse.getVisitDetailsCustomer()) == null || (distance = visitDetailsCustomer.getDistance()) == null) {
                distance = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            if ((distance.length() > 0) && !Intrinsics.areEqual(distance, EnvironmentCompat.MEDIA_UNKNOWN) && Integer.parseInt(distance) <= 1) {
                this$0.getBinding().btnStartVisit.setEnabled(true);
                AppToast.INSTANCE.showToast("Location verified. You can start the visit.");
                return;
            } else {
                DialogExtKt.showMaterialDialog$default(this$0, "You are not at the destination. Distance: " + distance + " km.", (DialogListeners) null, 2, (Object) null);
                return;
            }
        }
        if (response instanceof ApiResponseCallback.Error) {
            CheckIn3Fragment checkIn3Fragment = this$0;
            String message = ((ApiResponseCallback.Error) response).getMessage();
            if (message == null) {
                message = "An error occurred.";
            }
            DialogExtKt.showMaterialDialog$default(checkIn3Fragment, message, (DialogListeners) null, 2, (Object) null);
            return;
        }
        if (!(response instanceof ApiResponseCallback.Loading)) {
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(CheckIn3Fragment this$0, ApiResponseCallback it) {
        if (it instanceof ApiResponseCallback.Loading) {
            this$0.getBinding().btnStartVisit.setText("Uploading...");
            this$0.getBinding().btnStartVisit.setEnabled(false);
            return;
        }
        if (it instanceof ApiResponseCallback.Success) {
            AppToast.INSTANCE.showToast("Image uploaded successfully!");
            FragmentActivity activity = this$0.getActivity();
            if (activity != null) {
                activity.finish();
                return;
            }
            return;
        }
        if (!(it instanceof ApiResponseCallback.Error)) {
            throw new NoWhenBranchMatchedException();
        }
        CheckIn3Fragment checkIn3Fragment = this$0;
        String message = ((ApiResponseCallback.Error) it).getMessage();
        if (message == null) {
            message = "Image upload failed.";
        }
        DialogExtKt.showMaterialDialog$default(checkIn3Fragment, message, (DialogListeners) null, 2, (Object) null);
        this$0.getBinding().btnStartVisit.setText("Start Visit");
        this$0.getBinding().btnStartVisit.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(CheckIn3Fragment this$0, View it) {
        String photoPath = this$0.getCheckInViewModel().getPhotoPath().getValue();
        String str = photoPath;
        if (str == null || str.length() == 0) {
            AppToast.INSTANCE.showToast("No image to upload.");
            return;
        }
        this$0.getBinding().btnStartVisit.setText("Preparing...");
        this$0.getBinding().btnStartVisit.setEnabled(false);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new CheckIn3Fragment$onViewCreated$3$1(photoPath, this$0, null), 2, null);
    }

    private final void getLocationAndCheck() {
        try {
            FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
            if (fusedLocationProviderClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
                fusedLocationProviderClient = null;
            }
            Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
            final Function1 function1 = new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit locationAndCheck$lambda$4;
                    locationAndCheck$lambda$4 = CheckIn3Fragment.getLocationAndCheck$lambda$4(CheckIn3Fragment.this, (Location) obj);
                    return locationAndCheck$lambda$4;
                }
            };
            Intrinsics.checkNotNull(lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$$ExternalSyntheticLambda5
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    Function1.this.invoke(obj);
                }
            }));
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getLocationAndCheck$lambda$4(CheckIn3Fragment this$0, Location location) {
        if (location != null) {
            LocationCheckRequest locationCheckRequest = new LocationCheckRequest(this$0.visitId, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
            this$0.getViewModel().locationCheck(locationCheckRequest);
        } else {
            DialogExtKt.showMaterialDialog$default(this$0, "Could not get location.", (DialogListeners) null, 2, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File compressImage(Context context, Uri uri) {
        int fileSize;
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        File file = new File(context.getCacheDir(), "upload_" + System.currentTimeMillis() + ".jpg");
        int quality = 80;
        do {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                FileOutputStream it = fileOutputStream;
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, it);
                CloseableKt.closeFinally(fileOutputStream, null);
                fileSize = (int) (file.length() / 1024);
                quality -= 10;
                if (fileSize <= 400) {
                    break;
                }
            } finally {
            }
        } while (quality > 30);
        Log.d("FINAL_FILE_SIZE", fileSize + " KB");
        return file;
    }
}
