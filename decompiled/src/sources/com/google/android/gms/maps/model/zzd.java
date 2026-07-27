package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzas;
import com.google.android.gms.maps.model.FeatureLayer;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
final class zzd extends zzas {
    final /* synthetic */ FeatureLayer.StyleFactory zza;

    zzd(FeatureLayer featureLayer, FeatureLayer.StyleFactory styleFactory) {
        this.zza = styleFactory;
    }

    @Override // com.google.android.gms.internal.maps.zzat
    public final FeatureStyle zzb(com.google.android.gms.internal.maps.zzr zzrVar) {
        Feature zza = Feature.zza(zzrVar);
        if (zza == null) {
            return null;
        }
        return this.zza.getStyle(zza);
    }
}
