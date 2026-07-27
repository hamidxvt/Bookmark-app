package com.ingenious.androidbookmarksalesupgrade.utils;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.OkHttpClient;

/* compiled from: LocationService.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 (2\u00020\u0001:\u0001(B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\"\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\b\u0010\u0019\u001a\u00020\u000fH\u0002J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0014\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010&\u001a\u00020\u000fH\u0016J\n\u0010'\u001a\u0004\u0018\u00010\"H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/LocationService;", "Landroid/app/Service;", "<init>", "()V", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "locationCallback", "Lcom/google/android/gms/location/LocationCallback;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "httpClient", "Lokhttp3/OkHttpClient;", "lastSentTime", "", "onCreate", "", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "startTracking", "stopTracking", "setupLocationCallback", "startLocationUpdates", "sendLocationToApi", "lat", "", "lng", "createNotificationChannel", "buildNotification", "Landroid/app/Notification;", "text", "", "updateNotification", "onBind", "Landroid/os/IBinder;", "onDestroy", "getToken", "Companion", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final class LocationService extends Service {
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_STOP = "ACTION_STOP";
    private static final String API_URL = "https://staging.bookmark.services/api/updatelocation";
    public static final String CHANNEL_ID = "LocationServiceChannel";
    private static final long INTERVAL_MS = 3000;
    public static final int NOTIFICATION_ID = 1;
    private static final String TAG = "LocationService";
    private FusedLocationProviderClient fusedLocationClient;
    private long lastSentTime;
    private LocationCallback locationCallback;
    private final CoroutineScope serviceScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
    private final OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        createNotificationChannel();
        setupLocationCallback();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            switch (action.hashCode()) {
                case -528730005:
                    if (action.equals(ACTION_STOP)) {
                        stopTracking();
                        break;
                    }
                    break;
                case 789225721:
                    if (action.equals(ACTION_START)) {
                        startTracking();
                        break;
                    }
                    break;
            }
            return 1;
        }
        return 1;
    }

    private final void startTracking() {
        Notification notification = buildNotification("Acquiring location...");
        startForeground(1, notification);
        startLocationUpdates();
        Log.d(TAG, "Location tracking started");
    }

    private final void stopTracking() {
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        LocationCallback locationCallback = null;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        LocationCallback locationCallback2 = this.locationCallback;
        if (locationCallback2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationCallback");
        } else {
            locationCallback = locationCallback2;
        }
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        stopForeground(1);
        stopSelf();
        Log.d(TAG, "Location tracking stopped");
    }

    private final void setupLocationCallback() {
        this.locationCallback = new LocationCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.utils.LocationService$setupLocationCallback$1
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult result) {
                long j;
                Intrinsics.checkNotNullParameter(result, "result");
                Location location = result.getLastLocation();
                if (location == null) {
                    return;
                }
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                Log.d("LocationService", "Location updated: " + lat + ", " + lng);
                LocationService locationService = LocationService.this;
                String format = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(lat)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                String format2 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(lng)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                locationService.updateNotification("Lat: " + format + ", Lng: " + format2);
                long now = System.currentTimeMillis();
                j = LocationService.this.lastSentTime;
                if (now - j >= Constant.SPLASH_TIME) {
                    LocationService.this.lastSentTime = now;
                    LocationService.this.sendLocationToApi(lat, lng);
                }
            }
        };
    }

    private final void startLocationUpdates() {
        boolean hasPermission = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0;
        if (!hasPermission) {
            Log.e(TAG, "Location permission not granted");
            stopSelf();
            return;
        }
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(3000L);
        locationRequest.setFastestInterval(3000L);
        locationRequest.setPriority(100);
        Intrinsics.checkNotNullExpressionValue(locationRequest, "apply(...)");
        HandlerThread handlerThread = new HandlerThread("LocationThread");
        handlerThread.start();
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        LocationCallback locationCallback = null;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        LocationCallback locationCallback2 = this.locationCallback;
        if (locationCallback2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationCallback");
        } else {
            locationCallback = locationCallback2;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendLocationToApi(double lat, double lng) {
        String token = getToken();
        String str = token;
        if (!(str == null || str.length() == 0)) {
            BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new LocationService$sendLocationToApi$1(token, this, lat, lng, null), 3, null);
        } else {
            Log.e(TAG, "No auth token found, skipping API call");
        }
    }

    private final void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Location Tracking", 2);
        channel.setDescription("Shows location tracking status");
        channel.setShowBadge(false);
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(channel);
    }

    private final Notification buildNotification(String text) {
        Intent stopIntent = new Intent(this, (Class<?>) LocationService.class);
        stopIntent.setAction(ACTION_STOP);
        PendingIntent.getService(this, 0, stopIntent, 201326592);
        PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) HomeActivity.class), 201326592);
        Notification build = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Location Tracker Active").setContentText(text).setSmallIcon(R.drawable.ic_menu_mylocation).setOngoing(true).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNotification(String text) {
        ((NotificationManager) getSystemService(NotificationManager.class)).notify(1, buildNotification(text));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        LocationCallback locationCallback = this.locationCallback;
        if (locationCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationCallback");
            locationCallback = null;
        }
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        CoroutineScopeKt.cancel$default(this.serviceScope, null, 1, null);
        Log.d(TAG, "Service destroyed");
    }

    private final String getToken() {
        return getSharedPreferences("BookmarkApp", 0).getString("AUTH_TOKEN", null);
    }
}
