package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzhb extends zzhd {
    final /* synthetic */ zzhc zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzhb(zzhc zzhcVar, zzhe zzheVar, CharSequence charSequence) {
        super(zzheVar, "2.6.0");
        this.zza = zzhcVar;
    }

    @Override // com.google.android.libraries.places.internal.zzhd
    final int zzc(int i) {
        return i + 1;
    }

    @Override // com.google.android.libraries.places.internal.zzhd
    final int zzd(int i) {
        int length = "2.6.0".length();
        zzha.zzb(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if ("2.6.0".charAt(i) == '.') {
                return i;
            }
            i++;
        }
        return -1;
    }
}
