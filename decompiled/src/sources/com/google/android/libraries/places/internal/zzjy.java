package com.google.android.libraries.places.internal;

import android.os.Build;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzjy extends zzjs {
    private static final AtomicReference zza = new AtomicReference();
    private static final AtomicLong zzb = new AtomicLong();
    private static final ConcurrentLinkedQueue zzc = new ConcurrentLinkedQueue();
    private volatile zzja zzd;

    private zzjy(String str) {
        super(str);
        boolean z = true;
        boolean z2 = Build.FINGERPRINT != null ? "robolectric".equals(Build.FINGERPRINT) : true;
        boolean z3 = !"goldfish".equals(Build.HARDWARE) ? "ranchu".equals(Build.HARDWARE) : true;
        if (!"eng".equals(Build.TYPE) && !"userdebug".equals(Build.TYPE)) {
            z = false;
        }
        if (z2 || z3) {
            this.zzd = new zzjt().zza(zza());
        } else {
            this.zzd = z ? new zzka().zzb(false).zza(zza()) : null;
        }
    }

    public static zzja zzb(String str) {
        if (zza.get() != null) {
            return ((zzju) zza.get()).zza(str);
        }
        zzjy zzjyVar = new zzjy(str.replace('$', ClassUtils.PACKAGE_SEPARATOR_CHAR));
        zzjw.zza.offer(zzjyVar);
        if (zza.get() != null) {
            while (true) {
                zzjy zzjyVar2 = (zzjy) zzjw.zza.poll();
                if (zzjyVar2 == null) {
                    break;
                }
                zzjyVar2.zzd = ((zzju) zza.get()).zza(zzjyVar2.zza());
            }
            if (((zzjx) zzc.poll()) != null) {
                zzb.getAndDecrement();
                throw null;
            }
        }
        return zzjyVar;
    }
}
