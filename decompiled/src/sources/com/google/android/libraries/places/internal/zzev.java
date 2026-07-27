package com.google.android.libraries.places.internal;

import android.content.Context;
import android.os.Build;
import android.os.DropBoxManager;
import android.util.Log;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzev {
    private static DropBoxManager zza;
    private static final LinkedHashMap zzb = new zzeu(16, 0.75f, true);
    private static String zzc;

    public static synchronized void zza(Context context, boolean z) {
        synchronized (zzev.class) {
            if (zza == null) {
                zza = (DropBoxManager) context.getApplicationContext().getSystemService("dropbox");
                zzc = "com.google.android.libraries.places";
            }
        }
    }

    public static synchronized void zzb(Throwable th) {
        DropBoxManager dropBoxManager;
        synchronized (zzev.class) {
            long id = Thread.currentThread().getId();
            int hashCode = th.hashCode();
            Integer num = (Integer) zzb.get(Long.valueOf(id));
            if ((num == null || num.intValue() != hashCode) && (dropBoxManager = zza) != null && dropBoxManager.isTagEnabled("system_app_crash")) {
                DropBoxManager dropBoxManager2 = zza;
                StringBuilder sb = new StringBuilder();
                String str = zzc;
                List zzc2 = zzhe.zzb(ClassUtils.PACKAGE_SEPARATOR_CHAR).zzc("2.6.0");
                long j = -1;
                if (zzc2.size() == 3) {
                    long j2 = 0;
                    for (int i = 0; i < zzc2.size(); i++) {
                        try {
                            j2 = (j2 * 100) + Integer.parseInt((String) zzc2.get(i));
                        } catch (NumberFormatException e) {
                        }
                    }
                    j = j2;
                }
                sb.append(String.format("Package: %s v%d (%s)\n", str, Long.valueOf(j), "2.6.0"));
                sb.append(String.format("Build: %s\n", Build.FINGERPRINT));
                sb.append(StringUtils.LF);
                sb.append(Log.getStackTraceString(th));
                dropBoxManager2.addText("system_app_crash", sb.toString());
                zzb.put(Long.valueOf(id), Integer.valueOf(hashCode));
            }
        }
    }
}
