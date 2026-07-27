package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
/* loaded from: classes16.dex */
public abstract class GmsClientSupervisor {
    static HandlerThread zza;
    private static zzs zzd;
    private static Executor zze;
    private static final Object zzb = new Object();
    private static int zzc = 9;
    private static boolean zzf = false;

    public static int getDefaultBindFlags() {
        return 4225;
    }

    public static GmsClientSupervisor getInstance(Context context) {
        synchronized (zzb) {
            if (zzd == null) {
                zzd = new zzs(context.getApplicationContext(), zzf ? getOrStartHandlerThread().getLooper() : context.getMainLooper(), zze);
            }
        }
        return zzd;
    }

    public static HandlerThread getOrStartHandlerThread() {
        synchronized (zzb) {
            HandlerThread handlerThread = zza;
            if (handlerThread != null) {
                return handlerThread;
            }
            zza = new HandlerThread("GoogleApiHandler", zzc);
            zza.start();
            return zza;
        }
    }

    public static void setDefaultBindExecutor(Executor executor) {
        synchronized (zzb) {
            zzs zzsVar = zzd;
            if (zzsVar != null) {
                zzsVar.zzi(executor);
            }
            zze = executor;
        }
    }

    public static boolean setGamHandlerThreadPriorityIfNotInitialized(int priority) {
        synchronized (zzb) {
            if (zza != null) {
                return false;
            }
            zzc = priority;
            return true;
        }
    }

    public static void setUseHandlerThreadForCallbacks() {
        synchronized (zzb) {
            zzs zzsVar = zzd;
            if (zzsVar != null && !zzf) {
                zzsVar.zzj(getOrStartHandlerThread().getLooper());
            }
            zzf = true;
        }
    }

    public boolean bindService(ComponentName componentName, ServiceConnection connection, String realClientName) {
        return zza(new zzo(componentName, 4225), connection, realClientName, null).isSuccess();
    }

    public void unbindService(ComponentName componentName, ServiceConnection connection, String realClientName) {
        zzb(new zzo(componentName, 4225), connection, realClientName);
    }

    protected abstract ConnectionResult zza(zzo zzoVar, ServiceConnection serviceConnection, String str, Executor executor);

    protected abstract void zzb(zzo zzoVar, ServiceConnection serviceConnection, String str);

    public final void zzc(String str, String str2, int i, ServiceConnection serviceConnection, String str3, boolean z) {
        zzb(new zzo(str, str2, 4225, z), serviceConnection, str3);
    }

    public void unbindService(String startServiceAction, ServiceConnection connection, String realClientName) {
        zzb(new zzo(startServiceAction, 4225, false), connection, realClientName);
    }

    public boolean bindService(ComponentName componentName, ServiceConnection connection, String realClientName, Executor executor) {
        return zza(new zzo(componentName, 4225), connection, realClientName, executor).isSuccess();
    }

    public static HandlerThread getOrStartHandlerThread(int priority) {
        synchronized (zzb) {
            HandlerThread handlerThread = zza;
            if (handlerThread != null) {
                return handlerThread;
            }
            zza = new HandlerThread("GoogleApiHandler", priority);
            zza.start();
            return zza;
        }
    }

    public boolean bindService(String startServiceAction, ServiceConnection connection, String realClientName) {
        return zza(new zzo(startServiceAction, 4225, false), connection, realClientName, null).isSuccess();
    }
}
