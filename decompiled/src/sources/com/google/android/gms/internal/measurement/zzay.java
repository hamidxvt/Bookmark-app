package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzay extends zzav {
    protected zzay() {
        this.zza.add(zzbk.APPLY);
        this.zza.add(zzbk.BLOCK);
        this.zza.add(zzbk.BREAK);
        this.zza.add(zzbk.CASE);
        this.zza.add(zzbk.DEFAULT);
        this.zza.add(zzbk.CONTINUE);
        this.zza.add(zzbk.DEFINE_FUNCTION);
        this.zza.add(zzbk.FN);
        this.zza.add(zzbk.IF);
        this.zza.add(zzbk.QUOTE);
        this.zza.add(zzbk.RETURN);
        this.zza.add(zzbk.SWITCH);
        this.zza.add(zzbk.TERNARY);
    }

    private static zzao zzc(zzg zzgVar, List list) {
        zzh.zzb(zzbk.FN.name(), 2, list);
        zzao zza = zzgVar.zza((zzao) list.get(0));
        zzao zza2 = zzgVar.zza((zzao) list.get(1));
        if (!(zza2 instanceof zzae)) {
            throw new IllegalArgumentException(String.format("FN requires an ArrayValue of parameter names found %s", zza2.getClass().getCanonicalName()));
        }
        List zzb = ((zzae) zza2).zzb();
        List arrayList = new ArrayList();
        if (list.size() > 2) {
            arrayList = list.subList(2, list.size());
        }
        return new zzan(zza.zzc(), zzb, arrayList, zzgVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String str, zzg zzgVar, List list) {
        zzbk zzbkVar = zzbk.ADD;
        switch (zzh.zze(str).ordinal()) {
            case 2:
                zzh.zza(zzbk.APPLY.name(), 3, list);
                zzao zza = zzgVar.zza((zzao) list.get(0));
                String zzc = zzgVar.zza((zzao) list.get(1)).zzc();
                zzao zza2 = zzgVar.zza((zzao) list.get(2));
                if (!(zza2 instanceof zzae)) {
                    throw new IllegalArgumentException(String.format("Function arguments for Apply are not a list found %s", zza2.getClass().getCanonicalName()));
                }
                if (zzc.isEmpty()) {
                    throw new IllegalArgumentException("Function name for apply is undefined");
                }
                return zza.zzcA(zzc, zzgVar, ((zzae) zza2).zzb());
            case 11:
                return zzgVar.zzc().zzb(new zzae(list));
            case 12:
                zzh.zza(zzbk.BREAK.name(), 0, list);
                return zzao.zzi;
            case 13:
            case 19:
                if (list.isEmpty()) {
                    return zzao.zzf;
                }
                zzao zza3 = zzgVar.zza((zzao) list.get(0));
                return zza3 instanceof zzae ? zzgVar.zzb((zzae) zza3) : zzao.zzf;
            case 15:
                zzh.zza(zzbk.BREAK.name(), 0, list);
                return zzao.zzh;
            case 20:
                zzh.zzb(zzbk.DEFINE_FUNCTION.name(), 2, list);
                zzan zzanVar = (zzan) zzc(zzgVar, list);
                if (zzanVar.zzg() == null) {
                    zzgVar.zze("", zzanVar);
                    return zzanVar;
                }
                zzgVar.zze(zzanVar.zzg(), zzanVar);
                return zzanVar;
            case 25:
                return zzc(zzgVar, list);
            case 41:
                zzh.zzb(zzbk.IF.name(), 2, list);
                zzao zza4 = zzgVar.zza((zzao) list.get(0));
                zzao zza5 = zzgVar.zza((zzao) list.get(1));
                zzao zza6 = list.size() > 2 ? zzgVar.zza((zzao) list.get(2)) : null;
                zzao zzaoVar = zzao.zzf;
                zzao zzb = zza4.zze().booleanValue() ? zzgVar.zzb((zzae) zza5) : zza6 != null ? zzgVar.zzb((zzae) zza6) : zzaoVar;
                return true != (zzb instanceof zzag) ? zzaoVar : zzb;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                return new zzae(list);
            case 57:
                if (list.isEmpty()) {
                    return zzao.zzj;
                }
                zzh.zza(zzbk.RETURN.name(), 1, list);
                return new zzag("return", zzgVar.zza((zzao) list.get(0)));
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                zzh.zza(zzbk.SWITCH.name(), 3, list);
                zzao zza7 = zzgVar.zza((zzao) list.get(0));
                zzao zza8 = zzgVar.zza((zzao) list.get(1));
                zzao zza9 = zzgVar.zza((zzao) list.get(2));
                if (!(zza8 instanceof zzae)) {
                    throw new IllegalArgumentException("Malformed SWITCH statement, cases are not a list");
                }
                if (!(zza9 instanceof zzae)) {
                    throw new IllegalArgumentException("Malformed SWITCH statement, case statements are not a list");
                }
                zzae zzaeVar = (zzae) zza8;
                zzae zzaeVar2 = (zzae) zza9;
                boolean z = false;
                for (int i = 0; i < zzaeVar.zzh(); i++) {
                    if (z || zza7.equals(zzgVar.zza(zzaeVar.zzl(i)))) {
                        zzao zza10 = zzgVar.zza(zzaeVar2.zzl(i));
                        if (zza10 instanceof zzag) {
                            return ((zzag) zza10).zzg().equals("break") ? zzao.zzf : zza10;
                        }
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (zzaeVar.zzh() + 1 == zzaeVar2.zzh()) {
                    zzao zza11 = zzgVar.zza(zzaeVar2.zzl(zzaeVar.zzh()));
                    if (zza11 instanceof zzag) {
                        String zzg = ((zzag) zza11).zzg();
                        if (zzg.equals("return") || zzg.equals("continue")) {
                            return zza11;
                        }
                    }
                }
                return zzao.zzf;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                zzh.zza(zzbk.TERNARY.name(), 3, list);
                return zzgVar.zza((zzao) list.get(0)).zze().booleanValue() ? zzgVar.zza((zzao) list.get(1)) : zzgVar.zza((zzao) list.get(2));
            default:
                return super.zzb(str);
        }
    }
}
