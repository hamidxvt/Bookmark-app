package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
final class zzs extends zzai {
    final boolean zza;
    final boolean zzb;
    final /* synthetic */ zzt zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzs(zzt zztVar, boolean z, boolean z2) {
        super("log");
        Objects.requireNonNull(zztVar);
        this.zzc = zztVar;
        this.zza = z;
        this.zzb = z2;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg zzgVar, List list) {
        int i;
        zzh.zzb("log", 1, list);
        if (list.size() == 1) {
            zzt zztVar = this.zzc;
            zztVar.zzb().zza(3, zzgVar.zza((zzao) list.get(0)).zzc(), Collections.emptyList(), this.zza, this.zzb);
            return zzf;
        }
        switch (zzh.zzg(zzgVar.zza((zzao) list.get(0)).zzd().doubleValue())) {
            case 2:
                i = 4;
                break;
            case 3:
                i = 1;
                break;
            case 4:
            default:
                i = 3;
                break;
            case 5:
                i = 5;
                break;
            case 6:
                i = 2;
                break;
        }
        String zzc = zzgVar.zza((zzao) list.get(1)).zzc();
        if (list.size() == 2) {
            zzt zztVar2 = this.zzc;
            zztVar2.zzb().zza(i, zzc, Collections.emptyList(), this.zza, this.zzb);
            return zzf;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 2; i2 < Math.min(list.size(), 5); i2++) {
            arrayList.add(zzgVar.zza((zzao) list.get(i2)).zzc());
        }
        this.zzc.zzb().zza(i, zzc, arrayList, this.zza, this.zzb);
        return zzf;
    }
}
