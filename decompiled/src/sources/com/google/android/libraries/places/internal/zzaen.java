package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzaen {
    private static final zzaem zza;
    private static final zzaem zzb;

    static {
        zzaem zzaemVar;
        try {
            zzaemVar = (zzaem) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzaemVar = null;
        }
        zza = zzaemVar;
        zzb = new zzaem();
    }

    static zzaem zza() {
        return zza;
    }

    static zzaem zzb() {
        return zzb;
    }
}
