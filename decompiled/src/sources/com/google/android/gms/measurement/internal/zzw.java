package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzqp;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzw extends BroadcastReceiver {
    private final zzic zza;

    public zzw(zzic zzicVar) {
        this.zza = zzicVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        char c;
        if (intent == null) {
            this.zza.zzaV().zze().zza("App receiver called with null intent");
        }
        String action = intent.getAction();
        if (action == null) {
            this.zza.zzaV().zze().zza("App receiver called with null action");
            return;
        }
        switch (action.hashCode()) {
            case -1928239649:
                if (action.equals("com.google.android.gms.measurement.TRIGGERS_AVAILABLE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1279883384:
                if (action.equals("com.google.android.gms.measurement.BATCHES_AVAILABLE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                final zzic zzicVar = this.zza;
                zzqp.zza();
                if (zzicVar.zzc().zzp(null, zzfy.zzaQ)) {
                    zzicVar.zzaV().zzk().zza("App receiver notified triggers are available");
                    zzicVar.zzaW().zzj(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzu
                        @Override // java.lang.Runnable
                        public final /* synthetic */ void run() {
                            zzic zzicVar2 = zzic.this;
                            if (!zzicVar2.zzk().zzS()) {
                                zzicVar2.zzaV().zze().zza("registerTrigger called but app not eligible");
                                return;
                            }
                            zzicVar2.zzj().zzv();
                            final zzlj zzj = zzicVar2.zzj();
                            Objects.requireNonNull(zzj);
                            new Thread(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzv
                                @Override // java.lang.Runnable
                                public final /* synthetic */ void run() {
                                    zzlj.this.zzw();
                                }
                            }).start();
                        }
                    });
                    break;
                }
                break;
            case 1:
                zzic zzicVar2 = this.zza;
                zzicVar2.zzaV().zzk().zza("[sgtm] App Receiver notified batches are available");
                zzicVar2.zzaW().zzj(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzt
                    @Override // java.lang.Runnable
                    public final /* synthetic */ void run() {
                        zzw.this.zza();
                    }
                });
                break;
            default:
                this.zza.zzaV().zze().zza("App receiver called with unknown action");
                break;
        }
    }

    final /* synthetic */ void zza() {
        this.zza.zzx().zzh(((Long) zzfy.zzC.zzb(null)).longValue());
    }
}
