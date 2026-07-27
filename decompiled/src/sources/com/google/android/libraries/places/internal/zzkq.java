package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
enum zzkq {
    BOOLEAN,
    STRING,
    LONG,
    DOUBLE;

    static /* synthetic */ zzkq zza(Object obj) {
        if (obj instanceof String) {
            return STRING;
        }
        if (obj instanceof Boolean) {
            return BOOLEAN;
        }
        if (obj instanceof Long) {
            return LONG;
        }
        if (obj instanceof Double) {
            return DOUBLE;
        }
        String valueOf = String.valueOf(obj.getClass());
        String.valueOf(valueOf).length();
        throw new AssertionError("invalid tag type: ".concat(String.valueOf(valueOf)));
    }
}
