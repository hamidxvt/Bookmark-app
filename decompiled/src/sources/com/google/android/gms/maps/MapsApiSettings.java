package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.zzcc;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public final class MapsApiSettings {
    private static final String zza = MapsApiSettings.class.getSimpleName();

    private MapsApiSettings() {
    }

    public static void addInternalUsageAttributionId(Context context, String internalUsageAttributionId) {
        try {
            zzcc.zza(context, null).zzk(ObjectWrapper.wrap(context), internalUsageAttributionId);
        } catch (RemoteException | GooglePlayServicesNotAvailableException e) {
            Log.e(zza, "Failed to add internal usage attribution id.", e);
        }
    }
}
