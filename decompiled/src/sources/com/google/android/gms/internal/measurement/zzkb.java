package com.google.android.gms.internal.measurement;

import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzkb {
    public static final /* synthetic */ int zza = 0;
    private static final ArrayMap zzb = new ArrayMap();

    public static synchronized Uri zza(String str) {
        Uri uri;
        synchronized (zzkb.class) {
            ArrayMap arrayMap = zzb;
            uri = (Uri) arrayMap.get("com.google.android.gms.measurement");
            if (uri == null) {
                String encode = Uri.encode("com.google.android.gms.measurement");
                String.valueOf(encode);
                uri = Uri.parse("content://com.google.android.gms.phenotype/".concat(String.valueOf(encode)));
                arrayMap.put("com.google.android.gms.measurement", uri);
            }
        }
        return uri;
    }
}
