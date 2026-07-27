package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLocationBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.AddressLocationModel;
import com.ingenious.androidbookmarksalesupgrade.model.LocationModel;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.utils.LoadDataCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.ReverseGeoCodeUtils;
import com.ingenious.bookmarkNew.utils.NullCheck;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationActivity.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u000101H\u0015J\u0010\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020\tH\u0016J\b\u00104\u001a\u00020/H\u0002J\b\u00105\u001a\u00020/H\u0002J!\u00106\u001a\u00020/2\b\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u000108H\u0002¢\u0006\u0002\u0010:J\b\u0010;\u001a\u00020/H\u0002J\b\u0010>\u001a\u00020/H\u0002J\b\u0010?\u001a\u00020/H\u0002J\b\u0010@\u001a\u00020/H\u0002J\b\u0010A\u001a\u00020/H\u0016J\b\u0010B\u001a\u00020/H\u0014J\b\u0010C\u001a\u00020/H\u0014J\b\u0010D\u001a\u00020/H\u0014J\b\u0010E\u001a\u00020/H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010(\u001a\u0004\u0018\u00010)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/LocationActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/GoogleMap$OnCameraIdleListener;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityLocationBinding;", "mGoogleMap", "Lcom/google/android/gms/maps/GoogleMap;", "getMGoogleMap", "()Lcom/google/android/gms/maps/GoogleMap;", "setMGoogleMap", "(Lcom/google/android/gms/maps/GoogleMap;)V", "fusedLocation", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "getFusedLocation", "()Lcom/google/android/gms/location/FusedLocationProviderClient;", "setFusedLocation", "(Lcom/google/android/gms/location/FusedLocationProviderClient;)V", "latLng", "Lcom/google/android/gms/maps/model/LatLng;", "getLatLng", "()Lcom/google/android/gms/maps/model/LatLng;", "setLatLng", "(Lcom/google/android/gms/maps/model/LatLng;)V", "address", "", "autocompleteFragment", "Lcom/google/android/libraries/places/widget/AutocompleteSupportFragment;", "getAutocompleteFragment", "()Lcom/google/android/libraries/places/widget/AutocompleteSupportFragment;", "setAutocompleteFragment", "(Lcom/google/android/libraries/places/widget/AutocompleteSupportFragment;)V", "checkGPSDialog", "Landroid/app/Dialog;", "getCheckGPSDialog", "()Landroid/app/Dialog;", "setCheckGPSDialog", "(Landroid/app/Dialog;)V", "model", "Lcom/ingenious/androidbookmarksalesupgrade/model/AddressLocationModel;", "getModel", "()Lcom/ingenious/androidbookmarksalesupgrade/model/AddressLocationModel;", "setModel", "(Lcom/ingenious/androidbookmarksalesupgrade/model/AddressLocationModel;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "map", "autoCompleteSetup", "getLastLocation", "animateCamera", "latitude", "", "longitude", "(Ljava/lang/Double;Ljava/lang/Double;)V", "fusedLocationInitialization", "changeForNetworkOrLocationProvider", "Landroid/content/BroadcastReceiver;", "checkAndAskForPermissions", "checkGps", "initializeGPSDialog", "onCameraIdle", "onResume", "onPause", "onDestroy", "onLowMemory", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class LocationActivity extends AppCompatActivity implements GoogleMap.OnCameraIdleListener, OnMapReadyCallback {
    private AutocompleteSupportFragment autocompleteFragment;
    private ActivityLocationBinding binding;
    public Dialog checkGPSDialog;
    public FusedLocationProviderClient fusedLocation;
    public LatLng latLng;
    public GoogleMap mGoogleMap;
    private AddressLocationModel model;
    private String address = "";
    private final BroadcastReceiver changeForNetworkOrLocationProvider = new BroadcastReceiver() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$changeForNetworkOrLocationProvider$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            String action = intent.getAction();
            if (!(action == null || action.length() == 0)) {
                LocationActivity.this.checkGps();
            }
        }
    };

    public final GoogleMap getMGoogleMap() {
        GoogleMap googleMap = this.mGoogleMap;
        if (googleMap != null) {
            return googleMap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
        return null;
    }

    public final void setMGoogleMap(GoogleMap googleMap) {
        Intrinsics.checkNotNullParameter(googleMap, "<set-?>");
        this.mGoogleMap = googleMap;
    }

    public final FusedLocationProviderClient getFusedLocation() {
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocation;
        if (fusedLocationProviderClient != null) {
            return fusedLocationProviderClient;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fusedLocation");
        return null;
    }

    public final void setFusedLocation(FusedLocationProviderClient fusedLocationProviderClient) {
        Intrinsics.checkNotNullParameter(fusedLocationProviderClient, "<set-?>");
        this.fusedLocation = fusedLocationProviderClient;
    }

    public final LatLng getLatLng() {
        LatLng latLng = this.latLng;
        if (latLng != null) {
            return latLng;
        }
        Intrinsics.throwUninitializedPropertyAccessException("latLng");
        return null;
    }

    public final void setLatLng(LatLng latLng) {
        Intrinsics.checkNotNullParameter(latLng, "<set-?>");
        this.latLng = latLng;
    }

    public final AutocompleteSupportFragment getAutocompleteFragment() {
        return this.autocompleteFragment;
    }

    public final void setAutocompleteFragment(AutocompleteSupportFragment autocompleteSupportFragment) {
        this.autocompleteFragment = autocompleteSupportFragment;
    }

    public final Dialog getCheckGPSDialog() {
        Dialog dialog = this.checkGPSDialog;
        if (dialog != null) {
            return dialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("checkGPSDialog");
        return null;
    }

    public final void setCheckGPSDialog(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "<set-?>");
        this.checkGPSDialog = dialog;
    }

    public final AddressLocationModel getModel() {
        return this.model;
    }

    public final void setModel(AddressLocationModel addressLocationModel) {
        this.model = addressLocationModel;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityLocationBinding.inflate(getLayoutInflater());
        ActivityLocationBinding activityLocationBinding = this.binding;
        ActivityLocationBinding activityLocationBinding2 = null;
        if (activityLocationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLocationBinding = null;
        }
        setContentView(activityLocationBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyB0MFVkxUkRNwaH6Ir84Nm01ykjsoYNryQ");
        }
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            if (extras.containsKey(Constant.IntentKeys.LOCATION_ADDRESS)) {
                this.model = (AddressLocationModel) getIntent().getParcelableExtra(Constant.IntentKeys.LOCATION_ADDRESS);
            }
        }
        ActivityLocationBinding activityLocationBinding3 = this.binding;
        if (activityLocationBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLocationBinding3 = null;
        }
        activityLocationBinding3.setHeaderName("Select Location");
        ActivityLocationBinding activityLocationBinding4 = this.binding;
        if (activityLocationBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLocationBinding4 = null;
        }
        activityLocationBinding4.mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(this);
        ActivityLocationBinding activityLocationBinding5 = this.binding;
        if (activityLocationBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLocationBinding5 = null;
        }
        activityLocationBinding5.mapView.getMapAsync(this);
        fusedLocationInitialization();
        IntentFilter intentFilterNetwork = new IntentFilter();
        intentFilterNetwork.addAction(Constant.BroadCastActions.INSTANCE.getON_GPS_ENABLED_CHANGE());
        intentFilterNetwork.addAction(Constant.BroadCastActions.INSTANCE.getON_LOCATION_CHANGED());
        registerReceiver(this.changeForNetworkOrLocationProvider, intentFilterNetwork, 4);
        getLastLocation();
        initializeGPSDialog();
        autoCompleteSetup();
        AutocompleteSupportFragment autocompleteSupportFragment = this.autocompleteFragment;
        if (autocompleteSupportFragment != null) {
            autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$onCreate$1
                @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
                public void onPlaceSelected(Place place) {
                    Intrinsics.checkNotNullParameter(place, "place");
                    LatLng it = place.getLatLng();
                    if (it != null) {
                        LocationActivity.this.animateCamera(Double.valueOf(it.latitude), Double.valueOf(it.longitude));
                    }
                }

                @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
                public void onError(Status status) {
                    Intrinsics.checkNotNullParameter(status, "status");
                    String statusMessage = status.getStatusMessage();
                    if (statusMessage == null) {
                        statusMessage = "Unknown error";
                    }
                    Log.e("PLACES_ERROR", statusMessage);
                }
            });
        }
        ActivityLocationBinding activityLocationBinding6 = this.binding;
        if (activityLocationBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityLocationBinding2 = activityLocationBinding6;
        }
        activityLocationBinding2.setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$onCreate$2
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
                LocationActivity.this.finish();
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLocationFab() {
                if (ActivityExtKt.isGpsEnable(LocationActivity.this)) {
                    LocationActivity.this.getLastLocation();
                } else {
                    LocationActivity.this.getCheckGPSDialog().show();
                }
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDone() {
                String str;
                if (LocationActivity.this.latLng == null) {
                    AppToast.INSTANCE.showToast("Location not selected yet");
                    return;
                }
                Intent returnIntent = new Intent();
                LocationActivity locationActivity = LocationActivity.this;
                str = locationActivity.address;
                returnIntent.putExtra(Constant.ADDRESS, str);
                returnIntent.putExtra(Constant.LATITUDE, String.valueOf(locationActivity.getLatLng().latitude));
                returnIntent.putExtra(Constant.LONGITUDE, String.valueOf(locationActivity.getLatLng().longitude));
                LocationActivity.this.setResult(-1, returnIntent);
                LocationActivity.this.finish();
                Log.d("lattt", String.valueOf(LocationActivity.this.getLatLng().latitude));
            }
        });
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap map) {
        Intrinsics.checkNotNullParameter(map, "map");
        setMGoogleMap(map);
        getMGoogleMap().setOnCameraIdleListener(this);
    }

    private final void autoCompleteSetup() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        this.autocompleteFragment = findFragmentById instanceof AutocompleteSupportFragment ? (AutocompleteSupportFragment) findFragmentById : null;
        AutocompleteSupportFragment autocompleteSupportFragment = this.autocompleteFragment;
        if (autocompleteSupportFragment != null) {
            autocompleteSupportFragment.setPlaceFields(CollectionsKt.listOf((Object[]) new Place.Field[]{Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS}));
        }
        AutocompleteSupportFragment autocompleteSupportFragment2 = this.autocompleteFragment;
        if (autocompleteSupportFragment2 != null) {
            autocompleteSupportFragment2.setCountries("PAK");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            checkAndAskForPermissions();
            return;
        }
        Task<Location> lastLocation = getFusedLocation().getLastLocation();
        final Function1 function1 = new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit lastLocation$lambda$1;
                lastLocation$lambda$1 = LocationActivity.getLastLocation$lambda$1(LocationActivity.this, (Location) obj);
                return lastLocation$lambda$1;
            }
        };
        lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                Function1.this.invoke(obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                Intrinsics.checkNotNullParameter(exc, "it");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getLastLocation$lambda$1(LocationActivity this$0, Location location) {
        if (location != null) {
            this$0.animateCamera(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()));
        } else {
            AppToast.INSTANCE.showToast("Unable to fetch location, please enable GPS");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void animateCamera(Double latitude, Double longitude) {
        NullCheck.INSTANCE.safeLet(latitude, longitude, new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit animateCamera$lambda$4;
                animateCamera$lambda$4 = LocationActivity.animateCamera$lambda$4(LocationActivity.this, ((Double) obj).doubleValue(), ((Double) obj2).doubleValue());
                return animateCamera$lambda$4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animateCamera$lambda$4(LocationActivity this$0, double lat, double lng) {
        this$0.setLatLng(new LatLng(lat, lng));
        this$0.getMGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(this$0.getLatLng(), 16.0f));
        return Unit.INSTANCE;
    }

    private final void fusedLocationInitialization() {
        setFusedLocation(LocationServices.getFusedLocationProviderClient((Activity) this));
    }

    private final void checkAndAskForPermissions() {
        Dexter.withContext(this).withPermission("android.permission.ACCESS_FINE_LOCATION").withListener(new LocationActivity$checkAndAskForPermissions$1(this)).withErrorListener(new PermissionRequestErrorListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$$ExternalSyntheticLambda5
            @Override // com.karumi.dexter.listener.PermissionRequestErrorListener
            public final void onError(DexterError dexterError) {
                LocationActivity.checkAndAskForPermissions$lambda$5(dexterError);
            }
        }).onSameThread().check();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkAndAskForPermissions$lambda$5(DexterError it) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkGps() {
        if (!ActivityExtKt.isGpsEnable(this)) {
            getCheckGPSDialog().show();
        } else {
            getCheckGPSDialog().dismiss();
        }
    }

    private final void initializeGPSDialog() {
        setCheckGPSDialog(new MaterialAlertDialogBuilder(this).setMessage((CharSequence) "You currently have all location services for this device or application disabled.Enabled them to know your current location!").setCancelable(false).setPositiveButton((CharSequence) "Ok", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LocationActivity.initializeGPSDialog$lambda$6(LocationActivity.this, dialogInterface, i);
            }
        }).create());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initializeGPSDialog$lambda$6(LocationActivity this$0, DialogInterface dialog, int which) {
        dialog.dismiss();
        this$0.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnCameraIdleListener
    public void onCameraIdle() {
        setLatLng(getMGoogleMap().getCameraPosition().target);
        ReverseGeoCodeUtils.INSTANCE.execute(this, String.valueOf(getLatLng().latitude), String.valueOf(getLatLng().longitude), new LoadDataCallback<LocationModel>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$onCameraIdle$1
            @Override // com.ingenious.androidbookmarksalesupgrade.utils.LoadDataCallback
            public void onDataNotAvailable(int errorCode, String reasonMsg) {
                LoadDataCallback.DefaultImpls.onDataNotAvailable(this, errorCode, reasonMsg);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.utils.LoadDataCallback
            public void onDataLoaded(LocationModel response) {
                Intrinsics.checkNotNullParameter(response, "response");
                AutocompleteSupportFragment autocompleteFragment = LocationActivity.this.getAutocompleteFragment();
                if (autocompleteFragment != null) {
                    autocompleteFragment.setText(response.getLocationAddress());
                }
                LocationActivity.this.address = response.getLocationAddress();
                Log.i("TAG", "onDataLoaded: " + response.getUserLatitude() + "--" + response.getUserLongitude());
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        if (this.binding != null) {
            ActivityLocationBinding activityLocationBinding = this.binding;
            if (activityLocationBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityLocationBinding = null;
            }
            activityLocationBinding.mapView.onResume();
        }
        super.onResume();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        if (this.binding != null) {
            ActivityLocationBinding activityLocationBinding = this.binding;
            if (activityLocationBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityLocationBinding = null;
            }
            activityLocationBinding.mapView.onPause();
        }
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (this.binding != null) {
            ActivityLocationBinding activityLocationBinding = this.binding;
            if (activityLocationBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityLocationBinding = null;
            }
            activityLocationBinding.mapView.onDestroy();
        }
        super.onDestroy();
        if (this.changeForNetworkOrLocationProvider != null) {
            unregisterReceiver(this.changeForNetworkOrLocationProvider);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        if (this.binding != null) {
            ActivityLocationBinding activityLocationBinding = this.binding;
            if (activityLocationBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityLocationBinding = null;
            }
            activityLocationBinding.mapView.onLowMemory();
        }
        super.onLowMemory();
    }
}
