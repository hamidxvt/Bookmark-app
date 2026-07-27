package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zznf implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zznl zza;
    private volatile boolean zzb;
    private volatile zzgo zzc;

    protected zznf(zznl zznlVar) {
        Objects.requireNonNull(zznlVar);
        this.zza = zznlVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.zza.zzu.zzaW().zzd();
        synchronized (this) {
            try {
                Preconditions.checkNotNull(this.zzc);
                this.zza.zzu.zzaW().zzj(new zzna(this, (zzgb) this.zzc.getService()));
            } catch (DeadObjectException | IllegalStateException e) {
                this.zzc = null;
                this.zzb = false;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zznl zznlVar = this.zza;
        zznlVar.zzu.zzaW().zzd();
        zzgu zzf = zznlVar.zzu.zzf();
        if (zzf != null) {
            zzf.zzk().zzb("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzb = false;
            this.zzc = null;
        }
        this.zza.zzu.zzaW().zzj(new zzne(this, connectionResult));
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        zzic zzicVar = this.zza.zzu;
        zzicVar.zzaW().zzd();
        zzicVar.zzaV().zzj().zza("Service connection suspended");
        zzicVar.zzaW().zzj(new zznb(this));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0081 A[Catch: all -> 0x006b, TRY_LEAVE, TryCatch #1 {, blocks: (B:6:0x000f, B:7:0x0022, B:12:0x0025, B:14:0x0031, B:16:0x003b, B:18:0x0045, B:20:0x0081, B:22:0x0083, B:23:0x00a9, B:27:0x0099, B:30:0x006e, B:31:0x003f, B:33:0x0059), top: B:4:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0099 A[Catch: all -> 0x006b, TRY_ENTER, TryCatch #1 {, blocks: (B:6:0x000f, B:7:0x0022, B:12:0x0025, B:14:0x0031, B:16:0x003b, B:18:0x0045, B:20:0x0081, B:22:0x0083, B:23:0x00a9, B:27:0x0099, B:30:0x006e, B:31:0x003f, B:33:0x0059), top: B:4:0x000d }] */
    @Override // android.content.ServiceConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zza.zzu.zzaW().zzd();
        synchronized (this) {
            if (iBinder == null) {
                this.zzb = false;
                this.zza.zzu.zzaV().zzb().zza("Service connected with null binder");
                return;
            }
            zzgb zzgbVar = null;
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zzgbVar = queryLocalInterface instanceof zzgb ? (zzgb) queryLocalInterface : new zzfz(iBinder);
                    try {
                        this.zza.zzu.zzaV().zzk().zza("Bound to IMeasurementService interface");
                    } catch (RemoteException e) {
                        this.zza.zzu.zzaV().zzb().zza("Service connect failed to get IMeasurementService");
                        if (zzgbVar != null) {
                        }
                    }
                } else {
                    this.zza.zzu.zzaV().zzb().zzb("Got binder with a wrong descriptor", interfaceDescriptor);
                }
            } catch (RemoteException e2) {
            }
            if (zzgbVar != null) {
                this.zzb = false;
                try {
                    ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
                    zznl zznlVar = this.zza;
                    connectionTracker.unbindService(zznlVar.zzu.zzaY(), zznlVar.zzY());
                } catch (IllegalArgumentException e3) {
                }
            } else {
                this.zza.zzu.zzaW().zzj(new zzmy(this, zzgbVar));
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzic zzicVar = this.zza.zzu;
        zzicVar.zzaW().zzd();
        zzicVar.zzaV().zzj().zza("Service disconnected");
        zzicVar.zzaW().zzj(new zzmz(this, componentName));
    }

    public final void zza(Intent intent) {
        zznl zznlVar = this.zza;
        zznlVar.zzg();
        Context zzaY = zznlVar.zzu.zzaY();
        ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzb) {
                this.zza.zzu.zzaV().zzk().zza("Connection attempt already in progress");
                return;
            }
            zznl zznlVar2 = this.zza;
            zznlVar2.zzu.zzaV().zzk().zza("Using local app measurement service");
            this.zzb = true;
            connectionTracker.bindService(zzaY, intent, zznlVar2.zzY(), 129);
        }
    }

    public final void zzb() {
        if (this.zzc != null && (this.zzc.isConnected() || this.zzc.isConnecting())) {
            this.zzc.disconnect();
        }
        this.zzc = null;
    }

    public final void zzc() {
        zznl zznlVar = this.zza;
        zznlVar.zzg();
        Context zzaY = zznlVar.zzu.zzaY();
        synchronized (this) {
            if (this.zzb) {
                this.zza.zzu.zzaV().zzk().zza("Connection attempt already in progress");
                return;
            }
            if (this.zzc != null && (this.zzc.isConnecting() || this.zzc.isConnected())) {
                this.zza.zzu.zzaV().zzk().zza("Already awaiting connection attempt");
                return;
            }
            this.zzc = new zzgo(zzaY, Looper.getMainLooper(), this, this);
            this.zza.zzu.zzaV().zzk().zza("Connecting to remote service");
            this.zzb = true;
            Preconditions.checkNotNull(this.zzc);
            this.zzc.checkAvailabilityAndConnect();
        }
    }

    final /* synthetic */ void zzd(boolean z) {
        this.zzb = false;
    }
}
