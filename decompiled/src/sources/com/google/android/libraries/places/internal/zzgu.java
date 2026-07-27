package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzgu {
    public static final Appendable zza(Appendable appendable, Iterator it, zzgv zzgvVar, String str) throws IOException {
        String str2;
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            appendable.append(zzgvVar.zza(entry.getKey()));
            appendable.append("=");
            appendable.append(zzgvVar.zza(entry.getValue()));
            while (it.hasNext()) {
                str2 = zzgvVar.zza;
                appendable.append(str2);
                Map.Entry entry2 = (Map.Entry) it.next();
                appendable.append(zzgvVar.zza(entry2.getKey()));
                appendable.append("=");
                appendable.append(zzgvVar.zza(entry2.getValue()));
            }
        }
        return appendable;
    }
}
