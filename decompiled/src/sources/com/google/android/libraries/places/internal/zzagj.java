package com.google.android.libraries.places.internal;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public enum zzagj {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(Utils.DOUBLE_EPSILON)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzacp.zzb),
    ENUM(null),
    MESSAGE(null);

    private final Object zzk;

    zzagj(Object obj) {
        this.zzk = obj;
    }
}
