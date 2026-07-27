package com.google.android.libraries.places.internal;

import android.os.Build;
import android.util.Log;
import dalvik.system.VMStack;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzjv extends zzjq {
    private static final boolean zza = zza.zza();
    private static final boolean zzb;
    private static final zzjp zzc;

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    final class zza {
        zza() {
        }

        static boolean zza() {
            return zzjv.zzt();
        }
    }

    static {
        boolean z = true;
        if (Build.FINGERPRINT != null && !"robolectric".equals(Build.FINGERPRINT)) {
            z = false;
        }
        zzb = z;
        Log.class.getName();
        zzc = new zzjp() { // from class: com.google.android.libraries.places.internal.zzjv.1
            @Override // com.google.android.libraries.places.internal.zzjp
            public zziv zza(Class<?> cls, int i) {
                return zziv.zza;
            }

            @Override // com.google.android.libraries.places.internal.zzjp
            public String zzb(Class loggerClass) {
                StackTraceElement zza2;
                if (zzjv.zza) {
                    try {
                        if (loggerClass.equals(zzjv.zzp())) {
                            return VMStack.getStackClass2().getName();
                        }
                    } catch (Throwable th) {
                    }
                }
                if (!zzjv.zzb || (zza2 = zzks.zza(loggerClass, 1)) == null) {
                    return null;
                }
                return zza2.getClassName();
            }
        };
    }

    static Class<?> zzp() {
        return VMStack.getStackClass2();
    }

    static String zzq() {
        try {
            return VMStack.getStackClass2().getName();
        } catch (Throwable th) {
            return null;
        }
    }

    static boolean zzt() {
        try {
            Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", new Class[0]);
            return zza.class.getName().equals(zzq());
        } catch (Throwable th) {
            return false;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzjq
    protected zzja zze(String className) {
        return zzjy.zzb(className);
    }

    @Override // com.google.android.libraries.places.internal.zzjq
    protected zzjp zzh() {
        return zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzjq
    protected zzke zzj() {
        return zzjz.zzb();
    }

    @Override // com.google.android.libraries.places.internal.zzjq
    protected String zzm() {
        return "platform: Android";
    }
}
