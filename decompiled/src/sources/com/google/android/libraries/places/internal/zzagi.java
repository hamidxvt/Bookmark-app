package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public enum zzagi {
    DOUBLE(zzagj.DOUBLE, 1),
    FLOAT(zzagj.FLOAT, 5),
    INT64(zzagj.LONG, 0),
    UINT64(zzagj.LONG, 0),
    INT32(zzagj.INT, 0),
    FIXED64(zzagj.LONG, 1),
    FIXED32(zzagj.INT, 5),
    BOOL(zzagj.BOOLEAN, 0),
    STRING(zzagj.STRING, 2),
    GROUP(zzagj.MESSAGE, 3),
    MESSAGE(zzagj.MESSAGE, 2),
    BYTES(zzagj.BYTE_STRING, 2),
    UINT32(zzagj.INT, 0),
    ENUM(zzagj.ENUM, 0),
    SFIXED32(zzagj.INT, 5),
    SFIXED64(zzagj.LONG, 1),
    SINT32(zzagj.INT, 0),
    SINT64(zzagj.LONG, 0);

    private final zzagj zzt;

    zzagi(zzagj zzagjVar, int i) {
        this.zzt = zzagjVar;
    }

    public final zzagj zza() {
        return this.zzt;
    }
}
