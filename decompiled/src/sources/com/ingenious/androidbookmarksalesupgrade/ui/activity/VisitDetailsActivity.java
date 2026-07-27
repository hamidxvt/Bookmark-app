package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.viewpager2.widget.ViewPager2;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.VisitDetailsViewPagerAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityVisitDetailsBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutLoadingBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomerDetails;
import com.ingenious.androidbookmarksalesupgrade.model.response.LocationCheckResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetails;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsCustomer;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.apache.commons.lang3.StringUtils;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: VisitDetailsActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001aH\u0002J\u0012\u0010\u001e\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010!\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010\"\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015¨\u0006#"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/VisitDetailsActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityVisitDetailsBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "visitId", "", "customerId", "currentLat", "", "getCurrentLat", "()D", "setCurrentLat", "(D)V", "currentLong", "getCurrentLong", "setCurrentLong", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViewPagerAdapter", "onTabSelected", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabUnselected", "onTabReselected", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitDetailsActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    private ActivityVisitDetailsBinding binding;
    private double currentLat;
    private double currentLong;
    private String customerId;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private String visitId;

    public VisitDetailsActivity() {
        final VisitDetailsActivity $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$special$$inlined$viewModel$default$1
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
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$special$$inlined$viewModel$default$2
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
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$special$$inlined$viewModel$default$3
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
        this.visitId = "";
        this.customerId = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    public final double getCurrentLat() {
        return this.currentLat;
    }

    public final void setCurrentLat(double d) {
        this.currentLat = d;
    }

    public final double getCurrentLong() {
        return this.currentLong;
    }

    public final void setCurrentLong(double d) {
        this.currentLong = d;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityVisitDetailsBinding.inflate(getLayoutInflater());
        ActivityVisitDetailsBinding activityVisitDetailsBinding = this.binding;
        ActivityVisitDetailsBinding activityVisitDetailsBinding2 = null;
        if (activityVisitDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitDetailsBinding = null;
        }
        setContentView(activityVisitDetailsBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        ActivityVisitDetailsBinding activityVisitDetailsBinding3 = this.binding;
        if (activityVisitDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitDetailsBinding3 = null;
        }
        activityVisitDetailsBinding3.backArrow.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VisitDetailsActivity.this.finish();
            }
        });
        String stringExtra = getIntent().getStringExtra(Constant.VISIT_ID);
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.visitId = stringExtra;
        Log.i("TAG", "onCreate: " + this.visitId);
        getViewModel().getLocationCheckResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VisitDetailsActivity.onCreate$lambda$3(VisitDetailsActivity.this, (ApiResponseCallback) obj);
            }
        });
        getViewModel().visitDetails(this.visitId);
        getViewModel().getVisitResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VisitDetailsActivity.onCreate$lambda$6(VisitDetailsActivity.this, (ApiResponseCallback) obj);
            }
        });
        ActivityVisitDetailsBinding activityVisitDetailsBinding4 = this.binding;
        if (activityVisitDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitDetailsBinding4 = null;
        }
        activityVisitDetailsBinding4.setListener(new VisitDetailsActivity$onCreate$4(this));
        ActivityVisitDetailsBinding activityVisitDetailsBinding5 = this.binding;
        if (activityVisitDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitDetailsBinding5 = null;
        }
        activityVisitDetailsBinding5.btnCall.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VisitDetailsActivity.onCreate$lambda$7(VisitDetailsActivity.this, view);
            }
        });
        ActivityVisitDetailsBinding activityVisitDetailsBinding6 = this.binding;
        if (activityVisitDetailsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityVisitDetailsBinding2 = activityVisitDetailsBinding6;
        }
        activityVisitDetailsBinding2.btnNavigate.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VisitDetailsActivity.onCreate$lambda$8(VisitDetailsActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$3(VisitDetailsActivity visitDetailsActivity, ApiResponseCallback apiResponseCallback) {
        if (apiResponseCallback != null) {
            ActivityVisitDetailsBinding activityVisitDetailsBinding = null;
            if (apiResponseCallback instanceof ApiResponseCallback.Error) {
                String message = ((ApiResponseCallback.Error) apiResponseCallback).getMessage();
                Intrinsics.checkNotNull(message);
                ActivityExtKt.showMaterialAlertDialog$default(visitDetailsActivity, message, null, 2, null);
                ActivityVisitDetailsBinding activityVisitDetailsBinding2 = visitDetailsActivity.binding;
                if (activityVisitDetailsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityVisitDetailsBinding = activityVisitDetailsBinding2;
                }
                LayoutLoadingBinding progressIndicator = activityVisitDetailsBinding.progressIndicator;
                Intrinsics.checkNotNullExpressionValue(progressIndicator, "progressIndicator");
                visitDetailsActivity.hideProgressIndicator(progressIndicator);
                return;
            }
            if (!(apiResponseCallback instanceof ApiResponseCallback.Loading)) {
                if (!(apiResponseCallback instanceof ApiResponseCallback.Success)) {
                    throw new NoWhenBranchMatchedException();
                }
                ActivityVisitDetailsBinding activityVisitDetailsBinding3 = visitDetailsActivity.binding;
                if (activityVisitDetailsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityVisitDetailsBinding3 = null;
                }
                LayoutLoadingBinding progressIndicator2 = activityVisitDetailsBinding3.progressIndicator;
                Intrinsics.checkNotNullExpressionValue(progressIndicator2, "progressIndicator");
                visitDetailsActivity.hideProgressIndicator(progressIndicator2);
                LocationCheckResponse locationCheckResponse = (LocationCheckResponse) ((ApiResponseCallback.Success) apiResponseCallback).getData();
                if (locationCheckResponse != null) {
                    Boolean success = locationCheckResponse.getSuccess();
                    Intrinsics.checkNotNull(success);
                    if (success.booleanValue()) {
                        String message2 = locationCheckResponse.getMessage();
                        Intrinsics.checkNotNull(message2);
                        DialogExtKt.showMaterialDialog$default(visitDetailsActivity, message2, (DialogListeners) null, 2, (Object) null);
                        Intent intent = new Intent(visitDetailsActivity, (Class<?>) CheckInActivity.class);
                        intent.putExtra("visitId", visitDetailsActivity.visitId);
                        VisitDetailsCustomer visitDetailsCustomer = locationCheckResponse.getVisitDetailsCustomer();
                        intent.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, visitDetailsCustomer != null ? visitDetailsCustomer.getCustomerName() : null);
                        VisitDetailsCustomer visitDetailsCustomer2 = locationCheckResponse.getVisitDetailsCustomer();
                        intent.putExtra("reason", visitDetailsCustomer2 != null ? visitDetailsCustomer2.getReason() : null);
                        VisitDetailsCustomer visitDetailsCustomer3 = locationCheckResponse.getVisitDetailsCustomer();
                        intent.putExtra(FirebaseAnalytics.Param.LOCATION, visitDetailsCustomer3 != null ? visitDetailsCustomer3.getCustomerAddress() : null);
                        VisitDetailsCustomer visitDetailsCustomer4 = locationCheckResponse.getVisitDetailsCustomer();
                        intent.putExtra("priority", visitDetailsCustomer4 != null ? visitDetailsCustomer4.getPriority() : null);
                        VisitDetailsCustomer visitDetailsCustomer5 = locationCheckResponse.getVisitDetailsCustomer();
                        intent.putExtra("customerType", visitDetailsCustomer5 != null ? visitDetailsCustomer5.getCustomerType() : null);
                        VisitDetailsCustomer visitDetailsCustomer6 = locationCheckResponse.getVisitDetailsCustomer();
                        intent.putExtra("visitType", visitDetailsCustomer6 != null ? visitDetailsCustomer6.getVisitType() : null);
                        visitDetailsActivity.startActivity(intent);
                        VisitDetailsCustomer visitDetailsCustomer7 = locationCheckResponse.getVisitDetailsCustomer();
                        Intrinsics.checkNotNull(visitDetailsCustomer7);
                        Double currentLatitude = visitDetailsCustomer7.getCurrentLatitude();
                        Intrinsics.checkNotNull(currentLatitude);
                        visitDetailsActivity.currentLat = currentLatitude.doubleValue();
                        VisitDetailsCustomer visitDetailsCustomer8 = locationCheckResponse.getVisitDetailsCustomer();
                        Intrinsics.checkNotNull(visitDetailsCustomer8);
                        Double currentLongitude = visitDetailsCustomer8.getCurrentLongitude();
                        Intrinsics.checkNotNull(currentLongitude);
                        visitDetailsActivity.currentLong = currentLongitude.doubleValue();
                        Log.i("TAG", "Location Check Response Current Lat and Lon: " + visitDetailsActivity.currentLat + StringUtils.SPACE + visitDetailsActivity.currentLong);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$6(VisitDetailsActivity this$0, ApiResponseCallback it) {
        Double currentLongitude;
        Double currentLatitude;
        Double currentLongitude2;
        Double currentLatitude2;
        CustomerDetails customerDetails;
        if (it != null) {
            ActivityVisitDetailsBinding activityVisitDetailsBinding = null;
            if (it instanceof ApiResponseCallback.Error) {
                Log.e(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Error: " + ((ApiResponseCallback.Error) it).getMessage());
                Log.i("TAG", "onCreate: " + ((ApiResponseCallback.Error) it).getMessage());
                String message = ((ApiResponseCallback.Error) it).getMessage();
                Intrinsics.checkNotNull(message);
                ActivityExtKt.showMaterialAlertDialog$default(this$0, message, null, 2, null);
                ActivityVisitDetailsBinding activityVisitDetailsBinding2 = this$0.binding;
                if (activityVisitDetailsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityVisitDetailsBinding = activityVisitDetailsBinding2;
                }
                LayoutLoadingBinding progressIndicator = activityVisitDetailsBinding.progressIndicator;
                Intrinsics.checkNotNullExpressionValue(progressIndicator, "progressIndicator");
                this$0.hideProgressIndicator(progressIndicator);
                return;
            }
            if (it instanceof ApiResponseCallback.Loading) {
                ActivityVisitDetailsBinding activityVisitDetailsBinding3 = this$0.binding;
                if (activityVisitDetailsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityVisitDetailsBinding = activityVisitDetailsBinding3;
                }
                LayoutLoadingBinding progressIndicator2 = activityVisitDetailsBinding.progressIndicator;
                Intrinsics.checkNotNullExpressionValue(progressIndicator2, "progressIndicator");
                this$0.showProgressIndicator(progressIndicator2);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            ActivityVisitDetailsBinding activityVisitDetailsBinding4 = this$0.binding;
            if (activityVisitDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityVisitDetailsBinding4 = null;
            }
            LayoutLoadingBinding progressIndicator3 = activityVisitDetailsBinding4.progressIndicator;
            Intrinsics.checkNotNullExpressionValue(progressIndicator3, "progressIndicator");
            this$0.hideProgressIndicator(progressIndicator3);
            VisitDetailsResponse data = (VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                Boolean success = data.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue()) {
                    ActivityVisitDetailsBinding activityVisitDetailsBinding5 = this$0.binding;
                    if (activityVisitDetailsBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityVisitDetailsBinding5 = null;
                    }
                    activityVisitDetailsBinding5.setItem(((VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData()).getVisitDetails());
                    VisitDetails visitDetails = ((VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData()).getVisitDetails();
                    String valueOf = String.valueOf((visitDetails == null || (customerDetails = visitDetails.getCustomerDetails()) == null) ? null : customerDetails.getCustomerId());
                    if (valueOf == null) {
                        valueOf = "";
                    }
                    this$0.customerId = valueOf;
                    String str = this$0.customerId;
                    VisitDetails visitDetails2 = ((VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData()).getVisitDetails();
                    Intrinsics.checkNotNull(visitDetails2);
                    Log.d("customerId", "onCreate: " + str + StringUtils.SPACE + visitDetails2.getCustomerType());
                    VisitDetails visitDetails3 = ((VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData()).getVisitDetails();
                    boolean areEqual = Intrinsics.areEqual(visitDetails3 != null ? visitDetails3.getCustomerType() : null, "Schools");
                    double d = Utils.DOUBLE_EPSILON;
                    if (areEqual) {
                        VisitDetails visitDetails4 = ((VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData()).getVisitDetails();
                        this$0.currentLat = (visitDetails4 == null || (currentLatitude2 = visitDetails4.getCurrentLatitude()) == null) ? 0.0d : currentLatitude2.doubleValue();
                        VisitDetails visitDetails5 = ((VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData()).getVisitDetails();
                        if (visitDetails5 != null && (currentLongitude2 = visitDetails5.getCurrentLongitude()) != null) {
                            d = currentLongitude2.doubleValue();
                        }
                        this$0.currentLong = d;
                        Log.i("TAG", "Visit School Response Current Lat and Lon: " + this$0.currentLat + StringUtils.SPACE + this$0.currentLong);
                        ActivityVisitDetailsBinding activityVisitDetailsBinding6 = this$0.binding;
                        if (activityVisitDetailsBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityVisitDetailsBinding6 = null;
                        }
                        activityVisitDetailsBinding6.customerLinearType.setBackgroundResource(R.drawable.visit_customer_type_bg);
                        ActivityVisitDetailsBinding activityVisitDetailsBinding7 = this$0.binding;
                        if (activityVisitDetailsBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityVisitDetailsBinding7 = null;
                        }
                        activityVisitDetailsBinding7.customerLinearType.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this$0, R.color.tag_color1)));
                        ActivityVisitDetailsBinding activityVisitDetailsBinding8 = this$0.binding;
                        if (activityVisitDetailsBinding8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityVisitDetailsBinding8 = null;
                        }
                        activityVisitDetailsBinding8.customerType.setTextColor(ContextCompat.getColor(this$0, R.color.tag_text_color1));
                        ActivityVisitDetailsBinding activityVisitDetailsBinding9 = this$0.binding;
                        if (activityVisitDetailsBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityVisitDetailsBinding = activityVisitDetailsBinding9;
                        }
                        activityVisitDetailsBinding.visitCustomerTypeIv.setImageResource(R.drawable.school_icon);
                    } else {
                        ActivityVisitDetailsBinding activityVisitDetailsBinding10 = this$0.binding;
                        if (activityVisitDetailsBinding10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityVisitDetailsBinding10 = null;
                        }
                        activityVisitDetailsBinding10.customerLinearType.setBackgroundResource(R.drawable.visit_customer_type_bg);
                        ActivityVisitDetailsBinding activityVisitDetailsBinding11 = this$0.binding;
                        if (activityVisitDetailsBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityVisitDetailsBinding11 = null;
                        }
                        activityVisitDetailsBinding11.customerLinearType.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this$0, R.color.orange_bookshop)));
                        VisitDetails visitDetails6 = ((VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData()).getVisitDetails();
                        this$0.currentLat = (visitDetails6 == null || (currentLatitude = visitDetails6.getCurrentLatitude()) == null) ? 0.0d : currentLatitude.doubleValue();
                        VisitDetails visitDetails7 = ((VisitDetailsResponse) ((ApiResponseCallback.Success) it).getData()).getVisitDetails();
                        if (visitDetails7 != null && (currentLongitude = visitDetails7.getCurrentLongitude()) != null) {
                            d = currentLongitude.doubleValue();
                        }
                        this$0.currentLong = d;
                        Log.i("TAG", "Visit Bookmark Response Current Lat and Lon: " + this$0.currentLat + StringUtils.SPACE + this$0.currentLong);
                        ActivityVisitDetailsBinding activityVisitDetailsBinding12 = this$0.binding;
                        if (activityVisitDetailsBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityVisitDetailsBinding12 = null;
                        }
                        activityVisitDetailsBinding12.customerType.setTextColor(ContextCompat.getColor(this$0, R.color.orange_text));
                        ActivityVisitDetailsBinding activityVisitDetailsBinding13 = this$0.binding;
                        if (activityVisitDetailsBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityVisitDetailsBinding = activityVisitDetailsBinding13;
                        }
                        activityVisitDetailsBinding.visitCustomerTypeIv.setImageResource(R.drawable.shop_icon);
                    }
                    this$0.setupViewPagerAdapter();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(VisitDetailsActivity this$0, View it) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:03118875054"));
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(VisitDetailsActivity this$0, View it) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + this$0.currentLat + "," + this$0.currentLong + "&mode=d");
        Intent mapIntent = new Intent("android.intent.action.VIEW", gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        this$0.startActivity(mapIntent);
    }

    private final void setupViewPagerAdapter() {
        VisitDetailsViewPagerAdapter viewPagerAdapter = new VisitDetailsViewPagerAdapter(this, 4, this.visitId, this.customerId.toString());
        ActivityVisitDetailsBinding activityVisitDetailsBinding = this.binding;
        ActivityVisitDetailsBinding activityVisitDetailsBinding2 = null;
        if (activityVisitDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitDetailsBinding = null;
        }
        activityVisitDetailsBinding.viewPager.setAdapter(viewPagerAdapter);
        ActivityVisitDetailsBinding activityVisitDetailsBinding3 = this.binding;
        if (activityVisitDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitDetailsBinding3 = null;
        }
        TabLayout tabLayout = activityVisitDetailsBinding3.tabLayout;
        ActivityVisitDetailsBinding activityVisitDetailsBinding4 = this.binding;
        if (activityVisitDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityVisitDetailsBinding2 = activityVisitDetailsBinding4;
        }
        new TabLayoutMediator(tabLayout, activityVisitDetailsBinding2.viewPager, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitDetailsActivity$$ExternalSyntheticLambda5
            @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
            public final void onConfigureTab(TabLayout.Tab tab, int i) {
                VisitDetailsActivity.setupViewPagerAdapter$lambda$9(tab, i);
            }
        }).attach();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViewPagerAdapter$lambda$9(TabLayout.Tab tab, int position) {
        String str;
        Intrinsics.checkNotNullParameter(tab, "tab");
        switch (position) {
            case 0:
                str = "Details";
                break;
            case 1:
                str = "Samples";
                break;
            case 2:
                str = "History";
                break;
            default:
                str = "Adoption";
                break;
        }
        tab.setText(str);
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabSelected(TabLayout.Tab tab) {
        ActivityVisitDetailsBinding activityVisitDetailsBinding = this.binding;
        if (activityVisitDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitDetailsBinding = null;
        }
        ViewPager2 viewPager2 = activityVisitDetailsBinding.viewPager;
        Intrinsics.checkNotNull(tab);
        viewPager2.setCurrentItem(tab.getPosition());
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabReselected(TabLayout.Tab tab) {
        ActivityVisitDetailsBinding activityVisitDetailsBinding = this.binding;
        if (activityVisitDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitDetailsBinding = null;
        }
        activityVisitDetailsBinding.viewPager.offsetLeftAndRight(1);
    }
}
