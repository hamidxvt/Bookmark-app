package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
public class LocationServices {
    private static final Api.ClientKey zza = new Api.ClientKey();
    private static final Api.AbstractClientBuilder zzb = new zzbq();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("LocationServices.API", zzb, zza);

    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi = new com.google.android.gms.internal.location.zzz();

    @Deprecated
    public static final GeofencingApi GeofencingApi = new com.google.android.gms.internal.location.zzaf();

    @Deprecated
    public static final SettingsApi SettingsApi = new com.google.android.gms.internal.location.zzbm();

    private LocationServices() {
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Activity activity) {
        return new FusedLocationProviderClient(activity);
    }

    public static GeofencingClient getGeofencingClient(Activity activity) {
        return new GeofencingClient(activity);
    }

    public static SettingsClient getSettingsClient(Activity activity) {
        return new SettingsClient(activity);
    }

    public static com.google.android.gms.internal.location.zzbe zza(GoogleApiClient googleApiClient) {
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        com.google.android.gms.internal.location.zzbe zzbeVar = (com.google.android.gms.internal.location.zzbe) googleApiClient.getClient(zza);
        Preconditions.checkState(zzbeVar != null, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzbeVar;
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Context context) {
        return new FusedLocationProviderClient(context);
    }

    public static GeofencingClient getGeofencingClient(Context context) {
        return new GeofencingClient(context);
    }

    public static SettingsClient getSettingsClient(Context context) {
        return new SettingsClient(context);
    }
}
