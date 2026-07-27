package com.google.android.libraries.places.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzjo {
    private static final zzjq zza = zzb(zzjq.zzd);

    private static zzjq zzb(String[] strArr) {
        zzjq zzjqVar;
        try {
            zzjqVar = zzjr.zza();
        } catch (NoClassDefFoundError e) {
            zzjqVar = null;
        }
        if (zzjqVar != null) {
            return zzjqVar;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            try {
                return (zzjq) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable th) {
                th = th;
                if (th instanceof InvocationTargetException) {
                    th = th.getCause();
                }
                sb.append('\n');
                sb.append(str);
                sb.append(": ");
                sb.append(th);
            }
        }
        throw new IllegalStateException(sb.insert(0, "No logging platforms found:").toString());
    }
}
