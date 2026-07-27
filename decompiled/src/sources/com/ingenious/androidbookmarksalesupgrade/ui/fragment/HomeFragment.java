package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.google.gson.Gson;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.adapter.PastVisitsAdapter;
import com.ingenious.androidbookmarksalesupgrade.adapter.TodayVisitsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentHomeBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.model.LocationModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomerDetails;
import com.ingenious.androidbookmarksalesupgrade.model.response.HomeResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LoginResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.OnlineStatusResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.PastVisitsList;
import com.ingenious.androidbookmarksalesupgrade.model.response.TodayVisitsList;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetails;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.CheckInActivity;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.utils.LocationService;
import com.ingenious.androidbookmarksalesupgrade.viewModel.JobViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.UserViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: HomeFragment.kt */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020O2\b\u0010P\u001a\u0004\u0018\u00010QH\u0016J\b\u0010R\u001a\u00020MH\u0016J\u0010\u0010S\u001a\u00020M2\u0006\u0010T\u001a\u00020\tH\u0002J\b\u0010U\u001a\u00020MH\u0002J\b\u0010[\u001a\u00020MH\u0002J\b\u0010]\u001a\u00020\tH\u0002J\b\u0010^\u001a\u00020MH\u0002J\b\u0010_\u001a\u00020MH\u0002J\b\u0010`\u001a\u00020MH\u0002J\b\u0010a\u001a\u00020MH\u0003J\u0010\u0010b\u001a\u00020\u00172\u0006\u0010c\u001a\u00020.H\u0002J\u0010\u0010d\u001a\u00020\u00172\u0006\u0010e\u001a\u00020.H\u0002J\u0010\u0010f\u001a\u00020M2\u0006\u0010g\u001a\u00020hH\u0002J\b\u0010i\u001a\u00020MH\u0002J\u000e\u0010j\u001a\u00020M2\u0006\u0010k\u001a\u00020.J\u0006\u0010l\u001a\u00020.J\u0006\u0010m\u001a\u00020\tJ\b\u0010n\u001a\u00020MH\u0002J\u0010\u0010o\u001a\u00020M2\u0006\u0010p\u001a\u00020\u0017H\u0002J\u0010\u0010q\u001a\u00020M2\u0006\u0010r\u001a\u00020\tH\u0002J\n\u0010s\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010t\u001a\u00020M2\u0006\u0010u\u001a\u00020\u0013H\u0002J\b\u0010v\u001a\u00020MH\u0002J\b\u0010w\u001a\u00020MH\u0002J\b\u0010x\u001a\u00020\tH\u0002R.\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u000e\u0010 \u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u00101\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u00102R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\u0011\u001a\u0004\b9\u0010:R\u001b\u0010<\u001a\u00020=8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010\u0011\u001a\u0004\b>\u0010?R\u001b\u0010A\u001a\u00020B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\u0011\u001a\u0004\bC\u0010DR\u001a\u0010F\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u001a\"\u0004\bH\u0010\u001cR\u001c\u0010I\u001a\u0010\u0012\f\u0012\n K*\u0004\u0018\u00010\u00170\u00170JX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010V\u001a\u00020W8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bZ\u0010\u0011\u001a\u0004\bX\u0010YR\u001c\u0010\\\u001a\u0010\u0012\f\u0012\n K*\u0004\u0018\u00010\u00170\u00170JX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006y"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/HomeFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentHomeBinding;", "<init>", "()V", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "LOCATION_PERMISSION_REQUEST", "", "calendar", "Ljava/util/Calendar;", "currentDate", "", NotificationCompat.CATEGORY_STATUS, "getStatus", "()Z", "setStatus", "(Z)V", "jobStatus", "getJobStatus", "setJobStatus", "toadyVisitsAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/TodayVisitsAdapter;", "pastVisitsAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/PastVisitsAdapter;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "latitude", "", "longitude", "selectedPriority", "selectedDistance", "selectedCustomerType", "selectedAddedBy", "startTime", "", "endTime", "ongoingVisit", "ongoingVisitId", "Ljava/lang/Integer;", "timerHandler", "Landroid/os/Handler;", "timerRunnable", "Ljava/lang/Runnable;", "jobViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/JobViewModel;", "getJobViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/JobViewModel;", "jobViewModel$delegate", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "client$delegate", "messageViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "getMessageViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "messageViewModel$delegate", "foundOngoing", "getFoundOngoing", "setFoundOngoing", "notificationPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "updateButtonUI", "isStarted", "updateDate", "visitViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getVisitViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "visitViewModel$delegate", "setupAdapter", "requestPermissionLauncher", "isGPSEnabled", "showEnableGPSDialog", "showPermissionDeniedDialog", "checkLocationPermission", "getCurrentLocation", "formatTime", "timeInMillis", "formatDuration", "durationInMillis", "startTimer", "statusText", "Landroid/widget/TextView;", "stopTimer", "saveJobStartTime", "time", "getJobStartTime", "isJobRunning", "clearJobStartTime", "checkNotificationsAndMessages", "token", "updateNotificationDot", "hasNotification", "getToken", "todayAndPastVisitCallingFun", "visitId", "startLocationService", "stopLocationService", "checkNotificationPermissionAndProceed", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private final int LOCATION_PERMISSION_REQUEST;
    private Calendar calendar;

    /* renamed from: client$delegate, reason: from kotlin metadata */
    private final Lazy client;
    private String currentDate;
    private long endTime;
    private boolean foundOngoing;
    private FusedLocationProviderClient fusedLocationClient;
    private boolean jobStatus;

    /* renamed from: jobViewModel$delegate, reason: from kotlin metadata */
    private final Lazy jobViewModel;
    private double latitude;
    private double longitude;

    /* renamed from: messageViewModel$delegate, reason: from kotlin metadata */
    private final Lazy messageViewModel;
    private final ActivityResultLauncher<String> notificationPermissionLauncher;
    private boolean ongoingVisit;
    private Integer ongoingVisitId;
    private PastVisitsAdapter pastVisitsAdapter;
    private final ActivityResultLauncher<String> requestPermissionLauncher;
    private String selectedAddedBy;
    private String selectedCustomerType;
    private String selectedDistance;
    private String selectedPriority;
    private long startTime;
    private boolean status;
    private Handler timerHandler;
    private Runnable timerRunnable;
    private TodayVisitsAdapter toadyVisitsAdapter;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* renamed from: visitViewModel$delegate, reason: from kotlin metadata */
    private final Lazy visitViewModel;

    public HomeFragment() {
        final HomeFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$3
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
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(MainViewModel.class), qualifier$iv2, null, parameters$iv2, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv2, viewModelParameters$iv);
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$4
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
        this.LOCATION_PERMISSION_REQUEST = 1001;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance(...)");
        this.calendar = calendar;
        this.jobStatus = true;
        this.ongoingVisitId = -1;
        final HomeFragment $this$activityViewModels_u24default$iv = this;
        final Function0 extrasProducer$iv = null;
        this.jobViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$activityViewModels_u24default$iv, Reflection.getOrCreateKotlinClass(JobViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return Fragment.this.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        this.client = LazyKt.lazy(new Function0() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                OkHttpClient client_delegate$lambda$0;
                client_delegate$lambda$0 = HomeFragment.client_delegate$lambda$0();
                return client_delegate$lambda$0;
            }
        });
        final HomeFragment $this$viewModel_u24default$iv2 = this;
        final Qualifier qualifier$iv2 = null;
        final Function0 owner$iv2 = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$5
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
        final Function0 parameters$iv2 = null;
        final Scope scope$iv2 = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv2);
        final Function0 ownerProducer$iv$iv2 = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv2 = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                Function0 owner$iv3 = Function0.this;
                Qualifier qualifier$iv3 = qualifier$iv2;
                Function0 parameters$iv3 = parameters$iv2;
                Scope scope$iv3 = scope$iv2;
                ViewModelOwner ownerValue$iv = (ViewModelOwner) owner$iv3.invoke();
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(UserViewModel.class), qualifier$iv3, null, parameters$iv3, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv3, viewModelParameters$iv);
            }
        };
        this.messageViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv2, Reflection.getOrCreateKotlinClass(UserViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$8
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
        }, factoryProducer$iv$iv2);
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                Intrinsics.checkNotNullParameter((Boolean) obj, "isGranted");
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.notificationPermissionLauncher = registerForActivityResult;
        final HomeFragment $this$viewModel_u24default$iv3 = this;
        final Qualifier qualifier$iv3 = null;
        final Function0 owner$iv3 = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$9
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
        final Function0 parameters$iv3 = null;
        final Scope scope$iv3 = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv3);
        final Function0 ownerProducer$iv$iv3 = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$10
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv3 = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                Function0 owner$iv4 = Function0.this;
                Qualifier qualifier$iv4 = qualifier$iv3;
                Function0 parameters$iv4 = parameters$iv3;
                Scope scope$iv4 = scope$iv3;
                ViewModelOwner ownerValue$iv = (ViewModelOwner) owner$iv4.invoke();
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(VisitViewModel.class), qualifier$iv4, null, parameters$iv4, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv4, viewModelParameters$iv);
            }
        };
        this.visitViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv3, Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$special$$inlined$viewModel$default$12
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
        }, factoryProducer$iv$iv3);
        ActivityResultLauncher<String> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                HomeFragment.requestPermissionLauncher$lambda$22(HomeFragment.this, ((Boolean) obj).booleanValue());
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.requestPermissionLauncher = registerForActivityResult2;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentHomeBinding> getBindingInflater() {
        return HomeFragment$bindingInflater$1.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel.getValue();
    }

    public final boolean getStatus() {
        return this.status;
    }

    public final void setStatus(boolean z) {
        this.status = z;
    }

    public final boolean getJobStatus() {
        return this.jobStatus;
    }

    public final void setJobStatus(boolean z) {
        this.jobStatus = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JobViewModel getJobViewModel() {
        return (JobViewModel) this.jobViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getClient() {
        return (OkHttpClient) this.client.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpClient client_delegate$lambda$0() {
        return new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).build();
    }

    private final UserViewModel getMessageViewModel() {
        return (UserViewModel) this.messageViewModel.getValue();
    }

    public final boolean getFoundOngoing() {
        return this.foundOngoing;
    }

    public final void setFoundOngoing(boolean z) {
        this.foundOngoing = z;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMMM, yyyy", Locale.getDefault());
        SimpleDateFormat apiFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        getBinding().tvDate.setText(format.format(this.calendar.getTime()));
        this.currentDate = apiFormat.format(this.calendar.getTime());
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
        if (isJobRunning()) {
            this.startTime = getJobStartTime();
            getJobViewModel().setStartTime(this.startTime);
            getJobViewModel().setStatus(true);
            TextView statusText = getBinding().statusText;
            Intrinsics.checkNotNullExpressionValue(statusText, "statusText");
            startTimer(statusText);
            updateButtonUI(true);
        }
        requireActivity().getIntent().getStringExtra("visitId");
        setupAdapter();
        getViewModel().getHomeResponse().observe(getViewLifecycleOwner(), new HomeFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$10;
                onViewCreated$lambda$10 = HomeFragment.onViewCreated$lambda$10(HomeFragment.this, (ApiResponseCallback) obj);
                return onViewCreated$lambda$10;
            }
        }));
        String it = getToken();
        if (it != null) {
            checkNotificationsAndMessages(it);
        } else {
            getViewModel().getJobStatusResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda4
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    HomeFragment.onViewCreated$lambda$14(HomeFragment.this, (ApiResponseCallback) obj);
                }
            });
        }
        final Ref.BooleanRef showingStats = new Ref.BooleanRef();
        showingStats.element = true;
        final FragmentHomeBinding $this$onViewCreated_u24lambda_u2417 = getBinding();
        $this$onViewCreated_u24lambda_u2417.inbox.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HomeFragment.onViewCreated$lambda$17$lambda$15(HomeFragment.this, view2);
            }
        });
        RequestManager with = Glide.with(requireContext());
        LoginResponse loginData = AppPreferences.INSTANCE.getLoginData();
        with.load(loginData != null ? loginData.getImage() : null).placeholder(R.drawable.photo).into($this$onViewCreated_u24lambda_u2417.roundedImage);
        $this$onViewCreated_u24lambda_u2417.seeStats.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HomeFragment.onViewCreated$lambda$17$lambda$16(Ref.BooleanRef.this, $this$onViewCreated_u24lambda_u2417, view2);
            }
        });
        $this$onViewCreated_u24lambda_u2417.setListener(new HomeFragment$onViewCreated$4$3(this, $this$onViewCreated_u24lambda_u2417));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0469  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0463  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Unit onViewCreated$lambda$10(HomeFragment this$0, ApiResponseCallback response) {
        int count$iv;
        boolean isNewUser;
        Iterable<TodayVisitsList> $this$forEach$iv;
        Iterable pastVisits;
        int total;
        Integer totalVisitsToday;
        Object obj;
        PastVisitsList copy;
        TodayVisitsList copy2;
        if (response != null) {
            ApiResponseCallback it = response;
            int i = 0;
            if (it instanceof ApiResponseCallback.Error) {
                Log.e("API_ERROR", "Error: " + ((ApiResponseCallback.Error) it).getMessage());
            } else if (!(it instanceof ApiResponseCallback.Loading)) {
                if (!(it instanceof ApiResponseCallback.Success)) {
                    throw new NoWhenBranchMatchedException();
                }
                HomeResponse data = (HomeResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null && Intrinsics.areEqual((Object) data.getSuccess(), (Object) true)) {
                    Log.d("API_RESPONSE", "Success: " + data);
                    this$0.getBinding().setItem(data);
                    ArrayList<TodayVisitsList> todayVisits = data.getTodayVisits();
                    if (todayVisits != null) {
                        todayVisits.size();
                    }
                    Iterable todayVisits2 = data.getTodayVisits();
                    if (todayVisits2 != null) {
                        Iterable $this$count$iv = todayVisits2;
                        if (($this$count$iv instanceof Collection) && ((Collection) $this$count$iv).isEmpty()) {
                            count$iv = 0;
                        } else {
                            count$iv = 0;
                            Iterator it2 = $this$count$iv.iterator();
                            while (it2.hasNext()) {
                                if (StringsKt.equals(((TodayVisitsList) it2.next()).getStatus(), "Completed", true) && (count$iv = count$iv + 1) < 0) {
                                    CollectionsKt.throwCountOverflow();
                                }
                            }
                        }
                    } else {
                        count$iv = 0;
                    }
                    int todayCompleted = count$iv;
                    Integer visitsThisWeek = data.getVisitsThisWeek();
                    int weeklyTotal = visitsThisWeek != null ? visitsThisWeek.intValue() : 0;
                    int pending = RangesKt.coerceAtLeast(weeklyTotal - todayCompleted, 0);
                    this$0.getBinding().visitsThisWeek.setText(String.valueOf(weeklyTotal));
                    this$0.getBinding().totalVisit.setText(String.valueOf(weeklyTotal));
                    this$0.getBinding().visitsValue.setText(String.valueOf(todayCompleted));
                    this$0.getBinding().pendingVisit.setText(String.valueOf(pending));
                    Log.i("FULL_RESPONSE", new Gson().toJson(data));
                    Boolean jobStarted = data.getJobStarted();
                    this$0.status = jobStarted != null ? jobStarted.booleanValue() : false;
                    this$0.updateButtonUI(this$0.status);
                    Log.i("TAG", "onViewCreated: " + this$0.status);
                    ArrayList<PastVisitsList> pastVisits2 = data.getPastVisits();
                    if (pastVisits2 == null || pastVisits2.isEmpty()) {
                        ArrayList<TodayVisitsList> todayVisits3 = data.getTodayVisits();
                        if (todayVisits3 == null || todayVisits3.isEmpty()) {
                            isNewUser = true;
                            List fixedTodayVisits = new ArrayList();
                            $this$forEach$iv = data.getTodayVisits();
                            if ($this$forEach$iv == null) {
                                for (TodayVisitsList visit : $this$forEach$iv) {
                                    ApiResponseCallback it3 = it;
                                    int i2 = i;
                                    Log.i("STATUS", "onViewCreated: " + visit.getStatus());
                                    if (!Intrinsics.areEqual(visit.getStatus(), "on-going")) {
                                        fixedTodayVisits.add(visit);
                                    } else if (this$0.foundOngoing) {
                                        copy2 = visit.copy((r42 & 1) != 0 ? visit.id : null, (r42 & 2) != 0 ? visit.bookerId : null, (r42 & 4) != 0 ? visit.customerId : null, (r42 & 8) != 0 ? visit.customerName : null, (r42 & 16) != 0 ? visit.distanceKm : null, (r42 & 32) != 0 ? visit.estTime : null, (r42 & 64) != 0 ? visit.type : null, (r42 & 128) != 0 ? visit.customerType : null, (r42 & 256) != 0 ? visit.previousLatitude : null, (r42 & 512) != 0 ? visit.previousLongitude : null, (r42 & 1024) != 0 ? visit.currentLatitude : null, (r42 & 2048) != 0 ? visit.currentLongitude : null, (r42 & 4096) != 0 ? visit.remark : null, (r42 & 8192) != 0 ? visit.reason : null, (r42 & 16384) != 0 ? visit.status : "Pending", (r42 & 32768) != 0 ? visit.createdAt : null, (r42 & 65536) != 0 ? visit.updatedAt : null, (r42 & 131072) != 0 ? visit.visittype : null, (r42 & 262144) != 0 ? visit.priority : null, (r42 & 524288) != 0 ? visit.purpose : null, (r42 & 1048576) != 0 ? visit.customerAddress : null, (r42 & 2097152) != 0 ? visit.visitStartTime : null, (r42 & 4194304) != 0 ? visit.visitDate : null, (r42 & 8388608) != 0 ? visit.visitDetails : null);
                                        fixedTodayVisits.add(copy2);
                                    } else {
                                        fixedTodayVisits.add(visit);
                                        this$0.foundOngoing = true;
                                    }
                                    it = it3;
                                    i = i2;
                                }
                            }
                            List fixedPastVisits = new ArrayList();
                            pastVisits = data.getPastVisits();
                            if (pastVisits != null) {
                                Iterable<PastVisitsList> $this$forEach$iv2 = pastVisits;
                                for (PastVisitsList visit2 : $this$forEach$iv2) {
                                    Iterable $this$forEach$iv3 = $this$forEach$iv2;
                                    if (!Intrinsics.areEqual(visit2.getStatus(), "on-going")) {
                                        fixedPastVisits.add(visit2);
                                    } else if (this$0.foundOngoing) {
                                        copy = visit2.copy((r42 & 1) != 0 ? visit2.id : null, (r42 & 2) != 0 ? visit2.bookerId : null, (r42 & 4) != 0 ? visit2.customerId : null, (r42 & 8) != 0 ? visit2.type : null, (r42 & 16) != 0 ? visit2.customerType : null, (r42 & 32) != 0 ? visit2.previousLatitude : null, (r42 & 64) != 0 ? visit2.previousLongitude : null, (r42 & 128) != 0 ? visit2.currentLatitude : null, (r42 & 256) != 0 ? visit2.currentLongitude : null, (r42 & 512) != 0 ? visit2.view : null, (r42 & 1024) != 0 ? visit2.remark : null, (r42 & 2048) != 0 ? visit2.reason : null, (r42 & 4096) != 0 ? visit2.status : "Pending", (r42 & 8192) != 0 ? visit2.createdAt : null, (r42 & 16384) != 0 ? visit2.updatedAt : null, (r42 & 32768) != 0 ? visit2.customerName : null, (r42 & 65536) != 0 ? visit2.distanceKm : null, (r42 & 131072) != 0 ? visit2.estTime : null, (r42 & 262144) != 0 ? visit2.visittype : null, (r42 & 524288) != 0 ? visit2.priority : null, (r42 & 1048576) != 0 ? visit2.purpose : null, (r42 & 2097152) != 0 ? visit2.customerAddress : null, (r42 & 4194304) != 0 ? visit2.visitStartTime : null, (r42 & 8388608) != 0 ? visit2.visitDate : null);
                                        fixedPastVisits.add(copy);
                                    } else {
                                        fixedPastVisits.add(visit2);
                                        this$0.foundOngoing = true;
                                    }
                                    $this$forEach$iv2 = $this$forEach$iv3;
                                }
                            }
                            if (Intrinsics.areEqual((Object) data.getJobStarted(), (Object) true)) {
                                this$0.getBinding().seeStatsLinear.setVisibility(8);
                                this$0.getBinding().jobStartLinear.setVisibility(0);
                                this$0.getBinding().todayVisitsRv.setVisibility(8);
                                this$0.getBinding().pastVisitsRv.setVisibility(8);
                                this$0.getBinding().pastVisitsText.setVisibility(8);
                                this$0.getBinding().filterIconIv.setVisibility(8);
                                this$0.getBinding().pastFilterIconIv.setVisibility(8);
                                total = 0;
                                this$0.getBinding().dateFilterLinearLayout.setVisibility(0);
                            } else {
                                if (isNewUser) {
                                    this$0.getBinding().seeStatsLinear.setVisibility(8);
                                    this$0.getBinding().jobStartLinear.setVisibility(0);
                                    this$0.getBinding().todayVisitsRv.setVisibility(8);
                                    this$0.getBinding().pastVisitsRv.setVisibility(8);
                                    this$0.getBinding().filterIconIv.setVisibility(8);
                                    this$0.getBinding().pastFilterIconIv.setVisibility(8);
                                    this$0.getBinding().dateFilterLinearLayout.setVisibility(8);
                                } else {
                                    this$0.getBinding().seeStatsLinear.setVisibility(0);
                                    this$0.getBinding().jobStartLinear.setVisibility(8);
                                    this$0.getBinding().filterIconIv.setVisibility(0);
                                    this$0.getBinding().pastFilterIconIv.setVisibility(0);
                                    this$0.getBinding().dateFilterLinearLayout.setVisibility(0);
                                    this$0.getBinding().pastVisitsText.setVisibility(0);
                                    if (fixedPastVisits.isEmpty()) {
                                        this$0.getBinding().pastVisitsRv.setVisibility(8);
                                    } else {
                                        this$0.getBinding().pastVisitsRv.setVisibility(0);
                                        PastVisitsAdapter pastVisitsAdapter = this$0.pastVisitsAdapter;
                                        if (pastVisitsAdapter == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("pastVisitsAdapter");
                                            pastVisitsAdapter = null;
                                        }
                                        pastVisitsAdapter.addList(fixedPastVisits);
                                    }
                                    if (fixedTodayVisits.isEmpty()) {
                                        this$0.getBinding().todayVisitsRv.setVisibility(8);
                                    } else {
                                        this$0.getBinding().todayVisitsRv.setVisibility(0);
                                        TodayVisitsAdapter todayVisitsAdapter = this$0.toadyVisitsAdapter;
                                        if (todayVisitsAdapter == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("toadyVisitsAdapter");
                                            todayVisitsAdapter = null;
                                        }
                                        todayVisitsAdapter.addList(fixedTodayVisits);
                                    }
                                }
                                List $this$firstOrNull$iv = fixedTodayVisits;
                                Iterator it4 = $this$firstOrNull$iv.iterator();
                                while (true) {
                                    if (!it4.hasNext()) {
                                        obj = null;
                                        break;
                                    }
                                    Object element$iv = it4.next();
                                    if (Intrinsics.areEqual(((TodayVisitsList) element$iv).getStatus(), "on-going")) {
                                        obj = element$iv;
                                        break;
                                    }
                                }
                                TodayVisitsList it5 = (TodayVisitsList) obj;
                                if (it5 != null) {
                                    this$0.ongoingVisit = true;
                                    this$0.ongoingVisitId = it5.getId();
                                    total = 0;
                                } else {
                                    this$0.ongoingVisit = false;
                                    this$0.ongoingVisitId = -1;
                                    total = 0;
                                }
                            }
                            Integer visitsCompletedToday = data.getVisitsCompletedToday();
                            int completed = visitsCompletedToday == null ? visitsCompletedToday.intValue() : total;
                            totalVisitsToday = data.getTotalVisitsToday();
                            if (totalVisitsToday != null) {
                                total = totalVisitsToday.intValue();
                            }
                            if (total > 0) {
                                int percentage = (completed * 100) / total;
                                this$0.getBinding().circleProgress.setProgressCompat(percentage, true);
                            }
                        }
                    }
                    isNewUser = false;
                    List fixedTodayVisits2 = new ArrayList();
                    $this$forEach$iv = data.getTodayVisits();
                    if ($this$forEach$iv == null) {
                    }
                    List fixedPastVisits2 = new ArrayList();
                    pastVisits = data.getPastVisits();
                    if (pastVisits != null) {
                    }
                    if (Intrinsics.areEqual((Object) data.getJobStarted(), (Object) true)) {
                    }
                    Integer visitsCompletedToday2 = data.getVisitsCompletedToday();
                    if (visitsCompletedToday2 == null) {
                    }
                    totalVisitsToday = data.getTotalVisitsToday();
                    if (totalVisitsToday != null) {
                    }
                    if (total > 0) {
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$14(HomeFragment this$0, ApiResponseCallback it) {
        String str;
        if (it != null) {
            if (it instanceof ApiResponseCallback.Error) {
                String message = ((ApiResponseCallback.Error) it).getMessage();
                Intrinsics.checkNotNull(message);
                DialogExtKt.showMaterialDialog$default(this$0, message, (DialogListeners) null, 2, (Object) null);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Loading)) {
                if (!(it instanceof ApiResponseCallback.Success)) {
                    throw new NoWhenBranchMatchedException();
                }
                OnlineStatusResponse data = (OnlineStatusResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null) {
                    Boolean success = data.getSuccess();
                    Intrinsics.checkNotNull(success);
                    if (success.booleanValue()) {
                        MainViewModel viewModel = this$0.getViewModel();
                        String str2 = this$0.currentDate;
                        if (str2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("currentDate");
                            str = null;
                        } else {
                            str = str2;
                        }
                        MainViewModel.homeRequest$default(viewModel, str, String.valueOf(this$0.latitude), String.valueOf(this$0.longitude), null, null, null, null, 120, null);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$17$lambda$15(HomeFragment this$0, View it) {
        ActivityExtKt.gotoActivityFromFragment(this$0, ChatActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$17$lambda$16(Ref.BooleanRef $showingStats, FragmentHomeBinding $this_apply, View it) {
        if ($showingStats.element) {
            $this_apply.seeStatsLinear.setVisibility(0);
            $this_apply.jobStartLinear.setVisibility(8);
        } else {
            $this_apply.seeStatsLinear.setVisibility(8);
            $this_apply.jobStartLinear.setVisibility(0);
        }
        $showingStats.element = !$showingStats.element;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        checkLocationPermission();
        if (getJobViewModel().getStatus()) {
            this.startTime = getJobViewModel().getStartTime();
            TextView statusText = getBinding().statusText;
            Intrinsics.checkNotNullExpressionValue(statusText, "statusText");
            startTimer(statusText);
        }
        this.foundOngoing = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateButtonUI(boolean isStarted) {
        Log.i("TAG", "updateButtonUI: " + isStarted);
        if (isStarted) {
            getBinding().startJobBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.app_color)));
            getBinding().startJobBtn.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
            getBinding().startJobBtn.setText("End Job");
        } else {
            getBinding().startJobBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.alto)));
            getBinding().startJobBtn.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
            getBinding().startJobBtn.setText("Start Job");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateDate() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMMM, yyyy", Locale.getDefault());
        getBinding().tvDate.setText(format.format(this.calendar.getTime()));
        SimpleDateFormat apiFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        this.currentDate = apiFormat.format(this.calendar.getTime());
        checkLocationPermission();
    }

    private final VisitViewModel getVisitViewModel() {
        return (VisitViewModel) this.visitViewModel.getValue();
    }

    private final void setupAdapter() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.toadyVisitsAdapter = new TodayVisitsAdapter(requireContext, CollectionsKt.emptyList(), new GenericAdapter.OnItemClickListener<TodayVisitsList>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$setupAdapter$1
            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onSelectionChanged(List<? extends TodayVisitsList> list) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onSelectionChanged(this, list);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClick(TodayVisitsList item) {
                Intrinsics.checkNotNullParameter(item, "item");
                Integer id = item.getId();
                if (id != null) {
                    HomeFragment homeFragment = HomeFragment.this;
                    id.intValue();
                    ActivityExtKt.gotoActivityFromFragment(homeFragment, VisitDetailsActivity.class, Constant.VISIT_ID, String.valueOf(item.getId()));
                    Log.i("TAG", "onViewCreated: " + item.getId() + "---id");
                }
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClickTwo(TodayVisitsList item) {
                boolean z;
                Integer num;
                TodayVisitsAdapter todayVisitsAdapter;
                TodayVisitsAdapter todayVisitsAdapter2;
                TodayVisitsAdapter todayVisitsAdapter3;
                TodayVisitsAdapter todayVisitsAdapter4;
                Intrinsics.checkNotNullParameter(item, "item");
                Log.i("TAG", "onItemClickTwo: " + item.getStatus());
                if (Intrinsics.areEqual(item.getStatus(), "on-going")) {
                    Context requireContext2 = HomeFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
                    ExtensionKt.setVisitId(requireContext2, String.valueOf(item.getId()));
                    Intent i = new Intent(HomeFragment.this.requireActivity(), (Class<?>) CompleteVisitActivity.class);
                    i.putExtra("visitId", item.getId());
                    i.putExtra("visitstarttime", item.getVisitStartTime());
                    i.putExtra("customer_name", item.getCustomerName());
                    i.putExtra("visitDate", item.getVisitDate());
                    HomeFragment.this.startActivity(i);
                    return;
                }
                Log.i("TAG", "onItemClickTwo: " + item.getStatus());
                z = HomeFragment.this.ongoingVisit;
                if (z) {
                    num = HomeFragment.this.ongoingVisitId;
                    if (Intrinsics.areEqual(num, item.getId())) {
                        Log.i("TAG", "onItemClickTwo: " + item.getStatus());
                        Context requireContext3 = HomeFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext(...)");
                        ExtensionKt.setVisitId(requireContext3, String.valueOf(item.getId()));
                        Intent intent = new Intent(HomeFragment.this.requireContext(), (Class<?>) CheckInActivity.class);
                        intent.putExtra("visitId", item.getId());
                        intent.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, item.getCustomerName());
                        intent.putExtra("reason", item.getPurpose());
                        intent.putExtra("type", item.getCustomerType());
                        intent.putExtra("customerType", item.getCustomerType());
                        intent.putExtra("visitType", item.getVisittype());
                        intent.putExtra("priority", item.getPriority());
                        intent.putExtra(FirebaseAnalytics.Param.LOCATION, item.getCustomerAddress());
                        HomeFragment.this.startActivity(intent);
                        todayVisitsAdapter = HomeFragment.this.toadyVisitsAdapter;
                        if (todayVisitsAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("toadyVisitsAdapter");
                            todayVisitsAdapter2 = null;
                        } else {
                            todayVisitsAdapter2 = todayVisitsAdapter;
                        }
                        todayVisitsAdapter2.notifyDataSetChanged();
                        return;
                    }
                    AppToast.INSTANCE.showToast("Visit Going-on");
                    return;
                }
                Log.i("TAG", "onViewCreated: " + item);
                Context requireContext4 = HomeFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext(...)");
                ExtensionKt.setVisitId(requireContext4, String.valueOf(item.getId()));
                Intent intent2 = new Intent(HomeFragment.this.requireContext(), (Class<?>) CheckInActivity.class);
                intent2.putExtra("visitId", item.getId());
                intent2.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, item.getCustomerName());
                intent2.putExtra("reason", item.getPurpose());
                intent2.putExtra("type", item.getCustomerType());
                intent2.putExtra("customerType", item.getCustomerType());
                intent2.putExtra("visitType", item.getVisittype());
                intent2.putExtra("priority", item.getPriority());
                intent2.putExtra(FirebaseAnalytics.Param.LOCATION, item.getCustomerAddress());
                HomeFragment.this.startActivity(intent2);
                todayVisitsAdapter3 = HomeFragment.this.toadyVisitsAdapter;
                if (todayVisitsAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toadyVisitsAdapter");
                    todayVisitsAdapter4 = null;
                } else {
                    todayVisitsAdapter4 = todayVisitsAdapter3;
                }
                todayVisitsAdapter4.notifyDataSetChanged();
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onCall(int visitId) {
                HomeFragment.this.todayAndPastVisitCallingFun(visitId);
                GenericAdapter.OnItemClickListener.DefaultImpls.onCall(this, visitId);
            }
        });
        RecyclerView $this$setupAdapter_u24lambda_u2419 = getBinding().todayVisitsRv;
        TodayVisitsAdapter todayVisitsAdapter = this.toadyVisitsAdapter;
        PastVisitsAdapter pastVisitsAdapter = null;
        if (todayVisitsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toadyVisitsAdapter");
            todayVisitsAdapter = null;
        }
        $this$setupAdapter_u24lambda_u2419.setAdapter(todayVisitsAdapter);
        LinearLayoutManager $this$setupAdapter_u24lambda_u2419_u24lambda_u2418 = new LinearLayoutManager(requireContext(), 1, false);
        $this$setupAdapter_u24lambda_u2419_u24lambda_u2418.setStackFromEnd(true);
        $this$setupAdapter_u24lambda_u2419.setLayoutManager($this$setupAdapter_u24lambda_u2419_u24lambda_u2418);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
        this.pastVisitsAdapter = new PastVisitsAdapter(requireContext2, CollectionsKt.emptyList(), new GenericAdapter.OnItemClickListener<PastVisitsList>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$setupAdapter$3
            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onSelectionChanged(List<? extends PastVisitsList> list) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onSelectionChanged(this, list);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClick(PastVisitsList item) {
                Intrinsics.checkNotNullParameter(item, "item");
                Integer id = item.getId();
                if (id != null) {
                    HomeFragment homeFragment = HomeFragment.this;
                    id.intValue();
                    ActivityExtKt.gotoActivityFromFragment(homeFragment, VisitDetailsActivity.class, Constant.VISIT_ID, String.valueOf(item.getId()));
                    Log.i("TAG", "Past Vist: " + item.getId() + "---id");
                }
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClickTwo(PastVisitsList item) {
                boolean z;
                Integer num;
                PastVisitsAdapter pastVisitsAdapter2;
                PastVisitsAdapter pastVisitsAdapter3;
                PastVisitsAdapter pastVisitsAdapter4;
                PastVisitsAdapter pastVisitsAdapter5;
                Intrinsics.checkNotNullParameter(item, "item");
                Log.i("TAG", "Past: " + item.getStatus());
                if (Intrinsics.areEqual(item.getStatus(), "on-going")) {
                    Context requireContext3 = HomeFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext(...)");
                    ExtensionKt.setVisitId(requireContext3, String.valueOf(item.getId()));
                    Intent i = new Intent(HomeFragment.this.requireActivity(), (Class<?>) CompleteVisitActivity.class);
                    i.putExtra("visitId", item.getId());
                    i.putExtra("visitstarttime", item.getVisitStartTime());
                    i.putExtra("customer_name", item.getCustomerName());
                    i.putExtra("visitDate", item.getVisitDate());
                    HomeFragment.this.startActivity(i);
                    return;
                }
                Log.i("TAG", "past 2: " + item.getStatus());
                z = HomeFragment.this.ongoingVisit;
                if (!z) {
                    Log.i("TAG", "onViewCreated: " + item);
                    Context requireContext4 = HomeFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext(...)");
                    ExtensionKt.setVisitId(requireContext4, String.valueOf(item.getId()));
                    Intent intent = new Intent(HomeFragment.this.requireContext(), (Class<?>) CheckInActivity.class);
                    intent.putExtra("visitId", item.getId());
                    intent.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, item.getCustomerName());
                    intent.putExtra("reason", item.getReason());
                    intent.putExtra("type", item.getCustomerType());
                    intent.putExtra("customerType", item.getCustomerType());
                    intent.putExtra("visitType", item.getVisittype());
                    intent.putExtra("priority", item.getPriority());
                    intent.putExtra(FirebaseAnalytics.Param.LOCATION, item.getCustomerAddress());
                    HomeFragment.this.startActivity(intent);
                    pastVisitsAdapter4 = HomeFragment.this.pastVisitsAdapter;
                    if (pastVisitsAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("pastVisitsAdapter");
                        pastVisitsAdapter5 = null;
                    } else {
                        pastVisitsAdapter5 = pastVisitsAdapter4;
                    }
                    pastVisitsAdapter5.notifyDataSetChanged();
                    return;
                }
                Integer id = item.getId();
                num = HomeFragment.this.ongoingVisitId;
                if (Intrinsics.areEqual(id, num)) {
                    Log.i("TAG", "Past 3: " + item.getStatus());
                    Log.i("TAG", "onViewCreated: " + item);
                    Context requireContext5 = HomeFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext(...)");
                    ExtensionKt.setVisitId(requireContext5, String.valueOf(item.getId()));
                    Intent intent2 = new Intent(HomeFragment.this.requireContext(), (Class<?>) CheckInActivity.class);
                    intent2.putExtra("visitId", item.getId());
                    intent2.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, item.getCustomerName());
                    intent2.putExtra("reason", item.getReason());
                    intent2.putExtra("type", item.getCustomerType());
                    intent2.putExtra("customerType", item.getCustomerType());
                    intent2.putExtra("visitType", item.getVisittype());
                    intent2.putExtra("priority", item.getPriority());
                    intent2.putExtra(FirebaseAnalytics.Param.LOCATION, item.getCustomerAddress());
                    HomeFragment.this.startActivity(intent2);
                    pastVisitsAdapter2 = HomeFragment.this.pastVisitsAdapter;
                    if (pastVisitsAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("pastVisitsAdapter");
                        pastVisitsAdapter3 = null;
                    } else {
                        pastVisitsAdapter3 = pastVisitsAdapter2;
                    }
                    pastVisitsAdapter3.notifyDataSetChanged();
                    return;
                }
                AppToast.INSTANCE.showToast("Visit Going-on");
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onCall(int visitId) {
                HomeFragment.this.todayAndPastVisitCallingFun(visitId);
                GenericAdapter.OnItemClickListener.DefaultImpls.onCall(this, visitId);
            }
        });
        RecyclerView $this$setupAdapter_u24lambda_u2421 = getBinding().pastVisitsRv;
        PastVisitsAdapter pastVisitsAdapter2 = this.pastVisitsAdapter;
        if (pastVisitsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pastVisitsAdapter");
        } else {
            pastVisitsAdapter = pastVisitsAdapter2;
        }
        $this$setupAdapter_u24lambda_u2421.setAdapter(pastVisitsAdapter);
        LinearLayoutManager $this$setupAdapter_u24lambda_u2421_u24lambda_u2420 = new LinearLayoutManager(requireContext(), 1, false);
        $this$setupAdapter_u24lambda_u2421_u24lambda_u2420.setStackFromEnd(true);
        $this$setupAdapter_u24lambda_u2421.setLayoutManager($this$setupAdapter_u24lambda_u2421_u24lambda_u2420);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionLauncher$lambda$22(HomeFragment this$0, boolean isGranted) {
        if (isGranted) {
            if (this$0.isGPSEnabled()) {
                this$0.getCurrentLocation();
                return;
            } else {
                this$0.showEnableGPSDialog();
                return;
            }
        }
        this$0.showPermissionDeniedDialog();
    }

    private final boolean isGPSEnabled() {
        Object systemService = requireContext().getSystemService(FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        LocationManager locationManager = (LocationManager) systemService;
        return locationManager.isProviderEnabled("gps");
    }

    private final void showEnableGPSDialog() {
        new AlertDialog.Builder(requireContext()).setTitle("Location is disabled").setMessage("Please enable Location to use this feature.").setPositiveButton("Enable Location", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HomeFragment.showEnableGPSDialog$lambda$23(HomeFragment.this, dialogInterface, i);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HomeFragment.showEnableGPSDialog$lambda$24(dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEnableGPSDialog$lambda$23(HomeFragment this$0, DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEnableGPSDialog$lambda$24(DialogInterface dialog, int i) {
        dialog.dismiss();
        AppToast.INSTANCE.showToast("Location features are disabled.");
    }

    private final void showPermissionDeniedDialog() {
        new AlertDialog.Builder(requireContext()).setTitle("Permission Denied").setMessage("Location permission is required to get your current location. Please enable it in app settings.").setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda11
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HomeFragment.showPermissionDeniedDialog$lambda$25(HomeFragment.this, dialogInterface, i);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda12
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HomeFragment.showPermissionDeniedDialog$lambda$26(dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPermissionDeniedDialog$lambda$25(HomeFragment this$0, DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        Uri uri = Uri.fromParts("package", this$0.requireActivity().getPackageName(), null);
        intent.setData(uri);
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPermissionDeniedDialog$lambda$26(DialogInterface dialog, int i) {
        dialog.dismiss();
        AppToast.INSTANCE.showToast("Location permission denied.");
    }

    private final void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            if (isGPSEnabled()) {
                getCurrentLocation();
                return;
            } else {
                showEnableGPSDialog();
                return;
            }
        }
        if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
            this.requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION");
        } else {
            new AlertDialog.Builder(requireContext()).setTitle("Location Permission Needed").setMessage("This app needs the Location permission to get your current location.").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    HomeFragment.checkLocationPermission$lambda$27(HomeFragment.this, dialogInterface, i);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda7
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    HomeFragment.checkLocationPermission$lambda$28(dialogInterface, i);
                }
            }).create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkLocationPermission$lambda$27(HomeFragment this$0, DialogInterface dialogInterface, int i) {
        this$0.requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkLocationPermission$lambda$28(DialogInterface dialog, int i) {
        dialog.dismiss();
        AppToast.INSTANCE.showToast("Location permission was not granted.");
    }

    private final void getCurrentLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) requireActivity());
        Intrinsics.checkNotNullExpressionValue(fusedLocationClient, "getFusedLocationProviderClient(...)");
        CurrentLocationRequest locationRequest = new CurrentLocationRequest.Builder().setPriority(100).setMaxUpdateAgeMillis(0L).build();
        Intrinsics.checkNotNullExpressionValue(locationRequest, "build(...)");
        Task<Location> currentLocation = fusedLocationClient.getCurrentLocation(locationRequest, (CancellationToken) null);
        final Function1 function1 = new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit currentLocation$lambda$29;
                currentLocation$lambda$29 = HomeFragment.getCurrentLocation$lambda$29(HomeFragment.this, (Location) obj);
                return currentLocation$lambda$29;
            }
        };
        currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda14
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                Function1.this.invoke(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getCurrentLocation$lambda$29(HomeFragment this$0, Location location) {
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            LocationModel userLocation = AppPreferences.INSTANCE.getUserLocation();
            if (userLocation != null) {
                userLocation.setUserLatitude(Double.valueOf(lat));
            }
            LocationModel userLocation2 = AppPreferences.INSTANCE.getUserLocation();
            if (userLocation2 != null) {
                userLocation2.setUserLongitude(Double.valueOf(lng));
            }
            Log.d(HttpHeaders.LOCATION, "Lat: " + lat + ", Lng: " + lng);
            MainViewModel viewModel = this$0.getViewModel();
            String str = this$0.currentDate;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentDate");
                str = null;
            }
            MainViewModel.homeRequest$default(viewModel, str, String.valueOf(lng), String.valueOf(lat), null, null, null, null, 120, null);
        } else {
            AppToast.INSTANCE.showToast("Unable to get current location.");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String format = sdf.format(new Date(timeInMillis));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatDuration(long durationInMillis) {
        long j = 60;
        long seconds = (durationInMillis / 1000) % j;
        long minutes = (durationInMillis / 60000) % j;
        long hours = durationInMillis / 3600000;
        return hours + "h " + minutes + "m " + seconds + "s";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTimer(final TextView statusText) {
        this.timerHandler = new Handler(Looper.getMainLooper());
        this.timerRunnable = new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$startTimer$1
            @Override // java.lang.Runnable
            public void run() {
                long j;
                String formatDuration;
                Handler handler;
                long currentTimeMillis = System.currentTimeMillis();
                j = HomeFragment.this.startTime;
                long elapsed = currentTimeMillis - j;
                TextView textView = statusText;
                formatDuration = HomeFragment.this.formatDuration(elapsed);
                textView.setText(formatDuration);
                handler = HomeFragment.this.timerHandler;
                if (handler != null) {
                    handler.postDelayed(this, 1000L);
                }
            }
        };
        Handler handler = this.timerHandler;
        if (handler != null) {
            Runnable runnable = this.timerRunnable;
            Intrinsics.checkNotNull(runnable);
            handler.post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopTimer() {
        Handler handler = this.timerHandler;
        if (handler != null) {
            Runnable runnable = this.timerRunnable;
            Intrinsics.checkNotNull(runnable);
            handler.removeCallbacks(runnable);
        }
        this.timerRunnable = null;
        this.timerHandler = null;
        this.startTime = 0L;
        getJobViewModel().setStatus(false);
        SharedPreferences prefs = requireContext().getSharedPreferences("JOB_PREFS", 0);
        prefs.edit().putBoolean("job_started", false).apply();
        prefs.edit().putLong("job_start_time", 0L).apply();
    }

    public final void saveJobStartTime(long time) {
        SharedPreferences prefs = requireContext().getSharedPreferences("JOB_PREFS", 0);
        prefs.edit().putLong("job_start_time", time).apply();
        prefs.edit().putBoolean("job_started", true).apply();
    }

    public final long getJobStartTime() {
        SharedPreferences prefs = requireContext().getSharedPreferences("JOB_PREFS", 0);
        return prefs.getLong("job_start_time", 0L);
    }

    public final boolean isJobRunning() {
        SharedPreferences prefs = requireContext().getSharedPreferences("JOB_PREFS", 0);
        return prefs.getBoolean("job_started", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearJobStartTime() {
        SharedPreferences prefs = requireContext().getSharedPreferences("JOB_PREFS", 0);
        prefs.edit().putBoolean("job_started", false).apply();
        prefs.edit().putLong("job_start_time", 0L).apply();
    }

    private final void checkNotificationsAndMessages(String token) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new HomeFragment$checkNotificationsAndMessages$1(token, this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNotificationDot(boolean hasNotification) {
        getBinding().redDot.setVisibility(hasNotification ? 0 : 8);
        getBinding().redDotInbox.setVisibility(hasNotification ? 0 : 8);
    }

    private final String getToken() {
        SharedPreferences pref = requireActivity().getSharedPreferences("BookmarkApp", 0);
        return pref.getString("AUTH_TOKEN", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void todayAndPastVisitCallingFun(int visitId) {
        Log.i("TAG", "onCall: " + visitId);
        getVisitViewModel().visitDetails(String.valueOf(visitId));
        getVisitViewModel().getVisitResponse().observe(requireActivity(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HomeFragment.todayAndPastVisitCallingFun$lambda$33(HomeFragment.this, (ApiResponseCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void todayAndPastVisitCallingFun$lambda$33(HomeFragment this$0, ApiResponseCallback it) {
        String phoneNumber;
        CustomerDetails customerDetails;
        CustomerDetails customerDetails2;
        if (it != null) {
            if (!(it instanceof ApiResponseCallback.Error)) {
                if (!(it instanceof ApiResponseCallback.Loading)) {
                    if (!(it instanceof ApiResponseCallback.Success)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    VisitDetailsResponse item = (VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData();
                    if (item != null) {
                        Boolean success = item.getSuccess();
                        Intrinsics.checkNotNull(success);
                        if (success.booleanValue()) {
                            VisitDetails visitDetails = item.getVisitDetails();
                            Log.i("TAG", "onCall: " + ((visitDetails == null || (customerDetails2 = visitDetails.getCustomerDetails()) == null) ? null : customerDetails2.getContact()));
                            VisitDetails visitDetails2 = item.getVisitDetails();
                            if (visitDetails2 == null || (customerDetails = visitDetails2.getCustomerDetails()) == null || (phoneNumber = customerDetails.getContact()) == null) {
                                phoneNumber = "N/A";
                            }
                            if (!Intrinsics.areEqual(phoneNumber, "N/A")) {
                                Intent intent = new Intent("android.intent.action.DIAL");
                                String $this$toUri$iv = "tel:" + phoneNumber;
                                intent.setData(Uri.parse($this$toUri$iv));
                                this$0.requireActivity().startActivity(intent);
                                return;
                            }
                            AppToast.INSTANCE.showToast("Phone number not available");
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            Log.e(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Error: " + ((ApiResponseCallback.Error) it).getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startLocationService() {
        Intent intent = new Intent(requireActivity(), (Class<?>) LocationService.class);
        intent.setAction(LocationService.ACTION_START);
        ContextCompat.startForegroundService(requireActivity(), intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopLocationService() {
        Intent intent = new Intent(requireActivity(), (Class<?>) LocationService.class);
        intent.setAction(LocationService.ACTION_STOP);
        requireActivity().startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkNotificationPermissionAndProceed() {
        if (Build.VERSION.SDK_INT < 33) {
            return true;
        }
        boolean granted = ContextCompat.checkSelfPermission(requireActivity(), "android.permission.POST_NOTIFICATIONS") == 0;
        if (granted) {
            return true;
        }
        this.notificationPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS");
        return false;
    }
}
