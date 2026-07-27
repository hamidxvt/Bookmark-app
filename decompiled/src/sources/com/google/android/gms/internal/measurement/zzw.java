package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzw extends zzai {
    final Map zza;
    private final zzj zzb;

    public zzw(zzj zzjVar) {
        super("require");
        this.zza = new HashMap();
        this.zzb = zzjVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg zzgVar, List list) {
        zzao zzaoVar;
        zzh.zza("require", 1, list);
        String zzc = zzgVar.zza((zzao) list.get(0)).zzc();
        Map map = this.zza;
        if (map.containsKey(zzc)) {
            return (zzao) map.get(zzc);
        }
        Map map2 = this.zzb.zza;
        if (map2.containsKey(zzc)) {
            try {
                zzaoVar = (zzao) ((Callable) map2.get(zzc)).call();
            } catch (Exception e) {
                String.valueOf(zzc);
                throw new IllegalStateException("Failed to create API implementation: ".concat(String.valueOf(zzc)));
            }
        } else {
            zzaoVar = zzao.zzf;
        }
        if (zzaoVar instanceof zzai) {
            this.zza.put(zzc, (zzai) zzaoVar);
        }
        return zzaoVar;
    }
}
