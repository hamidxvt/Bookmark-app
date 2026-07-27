package com.google.android.libraries.places.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzgf extends ViewModel {
    private final zzfs zza;
    private final zzgi zzb;
    private final zzgj zzc;
    private Runnable zze;
    private final Handler zzd = new Handler(Looper.getMainLooper());
    private final MutableLiveData zzf = new MutableLiveData();

    /* synthetic */ zzgf(zzfs zzfsVar, zzgi zzgiVar, zzgj zzgjVar, zzge zzgeVar) {
        this.zza = zzfsVar;
        this.zzb = zzgiVar;
        this.zzc = zzgjVar;
    }

    private static Status zzn(Exception exc) {
        return exc instanceof ApiException ? ((ApiException) exc).getStatus() : new Status(13, exc.getMessage());
    }

    private final void zzo(zzfn zzfnVar) {
        if (zzfnVar.equals(this.zzf.getValue())) {
            return;
        }
        this.zzf.setValue(zzfnVar);
    }

    private static boolean zzp(Status status) {
        return status.isCanceled() || status.getStatusCode() == 9012 || status.getStatusCode() == 9011;
    }

    public final LiveData zza() {
        return this.zzf;
    }

    final /* synthetic */ void zzb(String str, Task task) {
        if (task.isCanceled()) {
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            this.zzb.zzp();
            List<AutocompletePrediction> autocompletePredictions = ((FindAutocompletePredictionsResponse) task.getResult()).getAutocompletePredictions();
            if (autocompletePredictions.isEmpty()) {
                zzo(zzfn.zzh(str));
                return;
            } else {
                zzo(zzfn.zzj(autocompletePredictions));
                return;
            }
        }
        this.zzb.zzr();
        Status zzn = zzn(exception);
        if (zzp(zzn)) {
            zzo(zzfn.zzq(zzn));
        } else {
            zzo(zzfn.zzi(str, zzn));
        }
    }

    final /* synthetic */ void zzc(AutocompletePrediction autocompletePrediction, Task task) {
        if (task.isCanceled()) {
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            this.zzb.zzq();
            zzo(zzfn.zzn(((FetchPlaceResponse) task.getResult()).getPlace()));
            return;
        }
        this.zzb.zzs();
        Status zzn = zzn(exception);
        if (zzp(zzn)) {
            zzo(zzfn.zzq(zzn));
        } else {
            zzo(zzfn.zzm(autocompletePrediction, zzn));
        }
    }

    final /* synthetic */ void zzd(final String str) {
        this.zza.zzb(str).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.libraries.places.internal.zzgb
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zzgf.this.zzb(str, task);
            }
        });
    }

    public final void zze(Bundle bundle) {
        if (bundle == null) {
            this.zzf.setValue(zzfn.zzo());
        }
    }

    public final void zzf(final AutocompletePrediction autocompletePrediction, int i) {
        this.zzb.zzu(i);
        Task zza = this.zza.zza(autocompletePrediction);
        if (!zza.isComplete()) {
            zzo(zzfn.zzg());
        }
        zza.addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.libraries.places.internal.zzga
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zzgf.this.zzc(autocompletePrediction, task);
            }
        });
    }

    public final void zzg() {
        this.zzb.zzv();
    }

    public final void zzh() {
        this.zzb.zzl();
    }

    public final void zzi() {
        this.zzb.zzm();
    }

    public final void zzj() {
        this.zzb.zzn();
        zzo(zzfn.zzl());
    }

    public final void zzk() {
        this.zzb.zzw();
        zzm("");
    }

    public final void zzl(String str) {
        this.zza.zzc();
        zzm(str);
        zzo(zzfn.zzp());
    }

    public final void zzm(final String str) {
        this.zzb.zzt(str);
        this.zzd.removeCallbacks(this.zze);
        if (str.isEmpty()) {
            this.zza.zzc();
            zzo(zzfn.zzk());
        } else {
            this.zze = new Runnable() { // from class: com.google.android.libraries.places.internal.zzgc
                @Override // java.lang.Runnable
                public final void run() {
                    zzgf.this.zzd(str);
                }
            };
            this.zzd.postDelayed(this.zze, 100L);
            zzo(zzfn.zzg());
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected final void onCleared() {
        try {
            this.zza.zzc();
            this.zzd.removeCallbacks(this.zze);
            this.zzb.zzo();
            this.zzc.zza(this.zzb);
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }
}
