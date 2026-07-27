package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
/* loaded from: classes16.dex */
public class Strings {
    private static final Pattern zza = Pattern.compile("\\$\\{(.*?)\\}");

    private Strings() {
    }

    public static String emptyToNull(String string) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    public static boolean isEmptyOrWhitespace(String string) {
        return string == null || string.trim().isEmpty();
    }
}
