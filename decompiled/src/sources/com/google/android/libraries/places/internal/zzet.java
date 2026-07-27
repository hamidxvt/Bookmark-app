package com.google.android.libraries.places.internal;

import android.content.Context;
import android.content.pm.PackageManager;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzet {
    public static zzes zzd(Context context) {
        String packageName = context.getPackageName();
        int i = 0;
        try {
            i = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        zzep zzepVar = new zzep();
        zzepVar.zza(packageName);
        zzepVar.zzb(i);
        zzepVar.zzd(1);
        return zzepVar;
    }

    public abstract int zza();

    public abstract String zzb();

    public abstract int zzc();
}
