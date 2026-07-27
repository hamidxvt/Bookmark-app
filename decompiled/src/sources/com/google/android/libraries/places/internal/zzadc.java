package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzadc {
    private static final zzada zza = new zzadb();
    private static final zzada zzb;

    static {
        zzada zzadaVar;
        try {
            zzadaVar = (zzada) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzadaVar = null;
        }
        zzb = zzadaVar;
    }

    static zzada zza() {
        zzada zzadaVar = zzb;
        if (zzadaVar != null) {
            return zzadaVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzada zzb() {
        return zza;
    }
}
