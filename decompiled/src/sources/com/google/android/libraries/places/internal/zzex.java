package com.google.android.libraries.places.internal;

import android.content.Context;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.TransportRuntime;
import java.io.IOException;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzex {
    private final Transport zza;

    public zzex(Context context) {
        TransportRuntime.initialize(context.getApplicationContext());
        this.zza = TransportRuntime.getInstance().newFactory("cct").getTransport("LE", zzlg.class, new Transformer() { // from class: com.google.android.libraries.places.internal.zzew
            @Override // com.google.android.datatransport.Transformer
            public final Object apply(Object obj) {
                zzlg zzlgVar = (zzlg) obj;
                try {
                    byte[] bArr = new byte[zzlgVar.zzv()];
                    zzacx zzC = zzacx.zzC(bArr);
                    zzlgVar.zzH(zzC);
                    zzC.zzD();
                    return bArr;
                } catch (IOException e) {
                    String name = zzlgVar.getClass().getName();
                    StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
                    sb.append("Serializing ");
                    sb.append(name);
                    sb.append(" to a byte array threw an IOException (should never happen).");
                    throw new RuntimeException(sb.toString(), e);
                }
            }
        });
    }

    public final void zza(zzlg zzlgVar) {
        this.zza.send(Event.ofData(zzlgVar));
    }
}
