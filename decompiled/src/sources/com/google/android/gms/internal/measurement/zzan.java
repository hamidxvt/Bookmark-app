package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzan extends zzai implements zzak {
    protected final List zza;
    protected final List zzb;
    protected zzg zzc;

    private zzan(zzan zzanVar) {
        super(zzanVar.zzd);
        this.zza = new ArrayList(zzanVar.zza.size());
        this.zza.addAll(zzanVar.zza);
        this.zzb = new ArrayList(zzanVar.zzb.size());
        this.zzb.addAll(zzanVar.zzb);
        this.zzc = zzanVar.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg zzgVar, List list) {
        zzg zzc = this.zzc.zzc();
        int i = 0;
        while (true) {
            List list2 = this.zza;
            if (i >= list2.size()) {
                break;
            }
            if (i < list.size()) {
                zzc.zzf((String) list2.get(i), zzgVar.zza((zzao) list.get(i)));
            } else {
                zzc.zzf((String) list2.get(i), zzf);
            }
            i++;
        }
        for (zzao zzaoVar : this.zzb) {
            zzao zza = zzc.zza(zzaoVar);
            if (zza instanceof zzap) {
                zza = zzc.zza(zzaoVar);
            }
            if (zza instanceof zzag) {
                return ((zzag) zza).zzb();
            }
        }
        return zzao.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzai, com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        return new zzan(this);
    }

    public zzan(String str, List list, List list2, zzg zzgVar) {
        super(str);
        this.zza = new ArrayList();
        this.zzc = zzgVar;
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.zza.add(((zzao) it.next()).zzc());
            }
        }
        this.zzb = new ArrayList(list2);
    }
}
