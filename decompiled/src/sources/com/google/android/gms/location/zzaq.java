package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
final class zzaq extends zzaw {
    final /* synthetic */ ListenerHolder zza;
    final /* synthetic */ FusedLocationProviderClient zzb;

    zzaq(FusedLocationProviderClient fusedLocationProviderClient, ListenerHolder listenerHolder) {
        this.zzb = fusedLocationProviderClient;
        this.zza = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        com.google.android.gms.internal.location.zzbe zzbeVar = (com.google.android.gms.internal.location.zzbe) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        if (zzb()) {
            zzar zzarVar = new zzar(this.zzb, taskCompletionSource);
            ListenerHolder.ListenerKey listenerKey = this.zza.getListenerKey();
            if (listenerKey != null) {
                zzbeVar.zzy(listenerKey, zzarVar);
            }
        }
    }
}
