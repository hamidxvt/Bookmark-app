package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzbk implements zzel {
    private final zzet zza;
    private final zzex zzb;
    private final zzem zzc;

    zzbk(zzex zzexVar, zzet zzetVar, zzem zzemVar, byte[] bArr) {
        this.zzb = zzexVar;
        this.zza = zzetVar;
        this.zzc = zzemVar;
    }

    static final int zzi(Task task) {
        if (task.isSuccessful()) {
            return 2;
        }
        Exception exception = task.getException();
        if (exception == null) {
            throw null;
        }
        switch ((exception instanceof ApiException ? (ApiException) exception : new ApiException(new Status(13, exception.getMessage()))).getStatusCode()) {
            case 7:
                return 4;
            case 15:
                return 3;
            default:
                return 1;
        }
    }

    private final zzzk zzj() {
        Locale zzb = this.zzc.zzb();
        Locale locale = Locale.getDefault();
        zzzk zza = zzzm.zza();
        zza.zzd(zzb.toString());
        if (!zzb.equals(locale)) {
            zza.zzb(locale.toString());
        }
        return zza;
    }

    private final void zzk(zzwt zzwtVar) {
        zzxv zzb = zzey.zzb(this.zza);
        zzb.zzl(16);
        zzb.zze(zzwtVar);
        zzb.zza(this.zzc.zza());
        zzl((zzya) zzb.zzt());
    }

    private final void zzl(zzya zzyaVar) {
        this.zzb.zza(zzey.zza(zzyaVar));
    }

    @Override // com.google.android.libraries.places.internal.zzel
    public final void zza(FetchPhotoRequest fetchPhotoRequest) {
        zzzc zza = zzze.zza();
        zza.zza(2);
        zzze zzzeVar = (zzze) zza.zzt();
        zzxv zzb = zzey.zzb(this.zza);
        zzb.zzl(5);
        zzb.zzg(zzzeVar);
        zzb.zza(this.zzc.zza());
        zzl((zzya) zzb.zzt());
    }

    @Override // com.google.android.libraries.places.internal.zzel
    public final void zzb(Task task, long j, long j2) {
        zzwo zza = zzwt.zza();
        zza.zzf(15);
        zza.zze(zzi(task));
        zza.zzd((int) (j2 - j));
        zzk((zzwt) zza.zzt());
    }

    @Override // com.google.android.libraries.places.internal.zzel
    public final void zzc(FetchPlaceRequest fetchPlaceRequest) {
        zzyg zza = zzyh.zza();
        zza.zza(1);
        zzzf zza2 = zzzg.zza();
        zza2.zza(zzdy.zzb(fetchPlaceRequest.getPlaceFields()));
        zza.zzb((zzzg) zza2.zzt());
        zzyh zzyhVar = (zzyh) zza.zzt();
        zzzk zzj = zzj();
        zzj.zze(5);
        zzj.zzc(zzyhVar);
        zzzm zzzmVar = (zzzm) zzj.zzt();
        zzxv zzb = zzey.zzb(this.zza);
        zzb.zzl(1);
        zzb.zzh(zzzmVar);
        zzb.zza(this.zzc.zza());
        AutocompleteSessionToken sessionToken = fetchPlaceRequest.getSessionToken();
        if (sessionToken != null) {
            zzb.zzj(sessionToken.toString());
        }
        zzl((zzya) zzb.zzt());
    }

    @Override // com.google.android.libraries.places.internal.zzel
    public final void zzd(Task task, long j, long j2) {
        boolean isSuccessful = task.isSuccessful();
        zzwg zza = zzwh.zza();
        zza.zza(1);
        zza.zzb(isSuccessful ? 1 : 0);
        zzwh zzwhVar = (zzwh) zza.zzt();
        zzwo zza2 = zzwt.zza();
        zza2.zzf(8);
        zza2.zzc(zzwhVar);
        zza2.zze(zzi(task));
        zza2.zzd((int) (j2 - j));
        zzk((zzwt) zza2.zzt());
    }

    @Override // com.google.android.libraries.places.internal.zzel
    public final void zze(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        zzwx zza = zzwy.zza();
        TypeFilter typeFilter = findAutocompletePredictionsRequest.getTypeFilter();
        if (typeFilter != null) {
            zza.zza(zzdz.zza(typeFilter));
        }
        zzwy zzwyVar = (zzwy) zza.zzt();
        zzxj zza2 = zzxk.zza();
        if (zzwyVar != null) {
            zza2.zza(zzwyVar);
        }
        zzxk zzxkVar = (zzxk) zza2.zzt();
        zzzk zzj = zzj();
        zzj.zze(6);
        zzj.zza(zzxkVar);
        zzzm zzzmVar = (zzzm) zzj.zzt();
        zzxv zzb = zzey.zzb(this.zza);
        zzb.zzl(1);
        zzb.zzh(zzzmVar);
        zzb.zza(this.zzc.zza());
        AutocompleteSessionToken sessionToken = findAutocompletePredictionsRequest.getSessionToken();
        if (sessionToken != null) {
            zzb.zzj(sessionToken.toString());
        }
        zzl((zzya) zzb.zzt());
    }

    @Override // com.google.android.libraries.places.internal.zzel
    public final void zzf(Task task, long j, long j2) {
        int size = task.isSuccessful() ? ((FindAutocompletePredictionsResponse) task.getResult()).getAutocompletePredictions().size() : 0;
        zzwb zza = zzwc.zza();
        zza.zza(size);
        zzwc zzwcVar = (zzwc) zza.zzt();
        zzwo zza2 = zzwt.zza();
        zza2.zzf(6);
        zza2.zzb(zzwcVar);
        zza2.zze(zzi(task));
        zza2.zzd((int) (j2 - j));
        zzk((zzwt) zza2.zzt());
    }

    @Override // com.google.android.libraries.places.internal.zzel
    public final void zzg(FindCurrentPlaceRequest findCurrentPlaceRequest, Task task, long j, long j2) {
        int i = true == task.isSuccessful() ? 2 : 1;
        zzym zza = zzyo.zza();
        zzzf zza2 = zzzg.zza();
        zza2.zza(zzdy.zzb(findCurrentPlaceRequest.getPlaceFields()));
        zza.zzb((zzzg) zza2.zzt());
        zza.zza((int) (j2 - j));
        zza.zzc(i);
        zzyo zzyoVar = (zzyo) zza.zzt();
        zzxv zzb = zzey.zzb(this.zza);
        zzb.zzl(6);
        zzb.zzd(zzyoVar);
        zzb.zza(this.zzc.zza());
        zzl((zzya) zzb.zzt());
    }

    @Override // com.google.android.libraries.places.internal.zzel
    public final void zzh(Task task, long j, long j2) {
        int size = task.isSuccessful() ? ((FindCurrentPlaceResponse) task.getResult()).getPlaceLikelihoods().size() : 0;
        zzvu zza = zzvv.zza();
        zza.zza(size);
        zzvv zzvvVar = (zzvv) zza.zzt();
        zzwo zza2 = zzwt.zza();
        zza2.zzf(4);
        zza2.zza(zzvvVar);
        zza2.zze(zzi(task));
        zza2.zzd((int) (j2 - j));
        zzk((zzwt) zza2.zzt());
    }
}
