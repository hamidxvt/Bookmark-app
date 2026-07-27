package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
final class zzq extends zzan {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ CountDownLatch zzb;

    zzq(zzz zzzVar, AtomicReference atomicReference, CountDownLatch countDownLatch) {
        this.zza = atomicReference;
        this.zzb = countDownLatch;
    }

    @Override // com.google.android.gms.internal.location.zzao
    public final void zzb(Status status, Location location) {
        if (status.isSuccess()) {
            this.zza.set(location);
        }
        this.zzb.countDown();
    }
}
