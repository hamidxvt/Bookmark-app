package com.google.android.libraries.places.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzks {
    private static final String[] zza = {"com.google.common.flogger.util.StackWalkerStackGetter", "com.google.common.flogger.util.JavaLangAccessStackGetter"};
    private static final zzkw zzb;

    static {
        zzkw zzkxVar;
        String[] strArr = zza;
        int i = 0;
        while (true) {
            if (i >= 2) {
                zzkxVar = new zzkx();
                break;
            }
            try {
                zzkxVar = (zzkw) Class.forName(strArr[i]).asSubclass(zzkw.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable th) {
                zzkxVar = null;
            }
            if (zzkxVar != null) {
                break;
            } else {
                i++;
            }
        }
        zzb = zzkxVar;
    }

    @NullableDecl
    public static StackTraceElement zza(Class cls, int i) {
        zzkt.zza(cls, TypedValues.AttributesType.S_TARGET);
        return zzb.zza(cls, 2);
    }
}
