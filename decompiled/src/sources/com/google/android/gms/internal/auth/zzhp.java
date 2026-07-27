package com.google.android.gms.internal.auth;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
public enum zzhp {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(Utils.DOUBLE_EPSILON)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzef.zzb),
    ENUM(null),
    MESSAGE(null);

    private final Object zzk;

    zzhp(Object obj) {
        this.zzk = obj;
    }
}
