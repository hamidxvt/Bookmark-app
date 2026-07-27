package com.google.android.gms.internal.auth;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
public final class zzcq {
    static volatile zzdh zza = zzdh.zzc();
    private static final Object zzb = new Object();

    /* JADX WARN: Can't wrap try/catch for region: R(12:18|(9:20|(1:22)(1:32)|23|(1:25)|27|28|29|30|31)|33|34|35|36|(5:38|28|29|30|31)|27|28|29|30|31) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0075, code lost:
    
        if ("com.google.android.gms".equals(r0.packageName) != false) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean zza(Context context, Uri uri) {
        String authority = uri.getAuthority();
        boolean z = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            Log.e("PhenotypeClientHelper", String.valueOf(authority).concat(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported."));
            return false;
        }
        if (zza.zzb()) {
            return ((Boolean) zza.zza()).booleanValue();
        }
        synchronized (zzb) {
            if (zza.zzb()) {
                return ((Boolean) zza.zza()).booleanValue();
            }
            if (!"com.google.android.gms".equals(context.getPackageName())) {
                ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", Build.VERSION.SDK_INT < 29 ? 0 : 268435456);
                if (resolveContentProvider != null) {
                }
                zza = zzdh.zzd(Boolean.valueOf(z));
                return ((Boolean) zza.zza()).booleanValue();
            }
            if ((context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0) {
                z = true;
                zza = zzdh.zzd(Boolean.valueOf(z));
                return ((Boolean) zza.zza()).booleanValue();
            }
            zza = zzdh.zzd(Boolean.valueOf(z));
            return ((Boolean) zza.zza()).booleanValue();
        }
    }
}
