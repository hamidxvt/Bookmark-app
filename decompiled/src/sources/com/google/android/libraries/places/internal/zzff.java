package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzff extends zzfm {
    private String zza;
    private zzhs zzb;
    private Place zzc;
    private AutocompletePrediction zzd;
    private Status zze;
    private int zzf;

    zzff() {
    }

    @Override // com.google.android.libraries.places.internal.zzfm
    public final zzfm zza(Place place) {
        this.zzc = place;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfm
    public final zzfm zzb(AutocompletePrediction autocompletePrediction) {
        this.zzd = autocompletePrediction;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfm
    public final zzfm zzc(List list) {
        this.zzb = zzhs.zzk(list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfm
    public final zzfm zzd(String str) {
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfm
    public final zzfm zze(Status status) {
        this.zze = status;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfm
    public final zzfn zzf() {
        int i = this.zzf;
        if (i != 0) {
            return new zzfh(i, this.zza, this.zzb, this.zzc, this.zzd, this.zze, null);
        }
        throw new IllegalStateException("Missing required properties: type");
    }

    public final zzfm zzg(int i) {
        this.zzf = i;
        return this;
    }
}
