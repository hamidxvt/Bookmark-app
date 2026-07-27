package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzax extends zzav {
    public zzax() {
        this.zza.add(zzbk.EQUALS);
        this.zza.add(zzbk.GREATER_THAN);
        this.zza.add(zzbk.GREATER_THAN_EQUALS);
        this.zza.add(zzbk.IDENTITY_EQUALS);
        this.zza.add(zzbk.IDENTITY_NOT_EQUALS);
        this.zza.add(zzbk.LESS_THAN);
        this.zza.add(zzbk.LESS_THAN_EQUALS);
        this.zza.add(zzbk.NOT_EQUALS);
    }

    private static boolean zzc(zzao zzaoVar, zzao zzaoVar2) {
        if (zzaoVar instanceof zzak) {
            zzaoVar = new zzas(zzaoVar.zzc());
        }
        if (zzaoVar2 instanceof zzak) {
            zzaoVar2 = new zzas(zzaoVar2.zzc());
        }
        if ((zzaoVar instanceof zzas) && (zzaoVar2 instanceof zzas)) {
            return zzaoVar.zzc().compareTo(zzaoVar2.zzc()) < 0;
        }
        double doubleValue = zzaoVar.zzd().doubleValue();
        double doubleValue2 = zzaoVar2.zzd().doubleValue();
        if (Double.isNaN(doubleValue) || Double.isNaN(doubleValue2)) {
            return false;
        }
        return !(doubleValue == Utils.DOUBLE_EPSILON && doubleValue2 == Utils.DOUBLE_EPSILON) && !(doubleValue == Utils.DOUBLE_EPSILON && doubleValue2 == Utils.DOUBLE_EPSILON) && Double.compare(doubleValue, doubleValue2) < 0;
    }

    private static boolean zzd(zzao zzaoVar, zzao zzaoVar2) {
        if (zzaoVar.getClass().equals(zzaoVar2.getClass())) {
            if ((zzaoVar instanceof zzat) || (zzaoVar instanceof zzam)) {
                return true;
            }
            return zzaoVar instanceof zzah ? (Double.isNaN(zzaoVar.zzd().doubleValue()) || Double.isNaN(zzaoVar2.zzd().doubleValue()) || zzaoVar.zzd().doubleValue() != zzaoVar2.zzd().doubleValue()) ? false : true : zzaoVar instanceof zzas ? zzaoVar.zzc().equals(zzaoVar2.zzc()) : zzaoVar instanceof zzaf ? zzaoVar.zze().equals(zzaoVar2.zze()) : zzaoVar == zzaoVar2;
        }
        if (((zzaoVar instanceof zzat) || (zzaoVar instanceof zzam)) && ((zzaoVar2 instanceof zzat) || (zzaoVar2 instanceof zzam))) {
            return true;
        }
        boolean z = zzaoVar instanceof zzah;
        if (z && (zzaoVar2 instanceof zzas)) {
            return zzd(zzaoVar, new zzah(zzaoVar2.zzd()));
        }
        boolean z2 = zzaoVar instanceof zzas;
        if (z2 && (zzaoVar2 instanceof zzah)) {
            return zzd(new zzah(zzaoVar.zzd()), zzaoVar2);
        }
        if (zzaoVar instanceof zzaf) {
            return zzd(new zzah(zzaoVar.zzd()), zzaoVar2);
        }
        if (zzaoVar2 instanceof zzaf) {
            return zzd(zzaoVar, new zzah(zzaoVar2.zzd()));
        }
        if ((z2 || z) && (zzaoVar2 instanceof zzak)) {
            return zzd(zzaoVar, new zzas(zzaoVar2.zzc()));
        }
        if ((zzaoVar instanceof zzak) && ((zzaoVar2 instanceof zzas) || (zzaoVar2 instanceof zzah))) {
            return zzd(new zzas(zzaoVar.zzc()), zzaoVar2);
        }
        return false;
    }

    private static boolean zze(zzao zzaoVar, zzao zzaoVar2) {
        if (zzaoVar instanceof zzak) {
            zzaoVar = new zzas(zzaoVar.zzc());
        }
        if (zzaoVar2 instanceof zzak) {
            zzaoVar2 = new zzas(zzaoVar2.zzc());
        }
        return (((zzaoVar instanceof zzas) && (zzaoVar2 instanceof zzas)) || !(Double.isNaN(zzaoVar.zzd().doubleValue()) || Double.isNaN(zzaoVar2.zzd().doubleValue()))) && !zzc(zzaoVar2, zzaoVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String str, zzg zzgVar, List list) {
        boolean zzd;
        zzh.zza(zzh.zze(str).name(), 2, list);
        zzao zza = zzgVar.zza((zzao) list.get(0));
        zzao zza2 = zzgVar.zza((zzao) list.get(1));
        switch (zzh.zze(str).ordinal()) {
            case 23:
                zzd = zzd(zza, zza2);
                break;
            case 37:
                zzd = zzc(zza2, zza);
                break;
            case 38:
                zzd = zze(zza2, zza);
                break;
            case 39:
                zzd = zzh.zzf(zza, zza2);
                break;
            case 40:
                zzd = !zzh.zzf(zza, zza2);
                break;
            case 42:
                zzd = zzc(zza, zza2);
                break;
            case 43:
                zzd = zze(zza, zza2);
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                zzd = !zzd(zza, zza2);
                break;
            default:
                return super.zzb(str);
        }
        return zzd ? zzao.zzk : zzao.zzl;
    }
}
