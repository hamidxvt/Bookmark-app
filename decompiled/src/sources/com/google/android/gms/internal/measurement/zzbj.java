package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.text.HtmlCompat;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzbj extends zzav {
    protected zzbj() {
        this.zza.add(zzbk.ASSIGN);
        this.zza.add(zzbk.CONST);
        this.zza.add(zzbk.CREATE_ARRAY);
        this.zza.add(zzbk.CREATE_OBJECT);
        this.zza.add(zzbk.EXPRESSION_LIST);
        this.zza.add(zzbk.GET);
        this.zza.add(zzbk.GET_INDEX);
        this.zza.add(zzbk.GET_PROPERTY);
        this.zza.add(zzbk.NULL);
        this.zza.add(zzbk.SET_PROPERTY);
        this.zza.add(zzbk.TYPEOF);
        this.zza.add(zzbk.UNDEFINED);
        this.zza.add(zzbk.VAR);
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String str, zzg zzgVar, List list) {
        String str2;
        zzbk zzbkVar = zzbk.ADD;
        int i = 0;
        switch (zzh.zze(str).ordinal()) {
            case 3:
                zzh.zza(zzbk.ASSIGN.name(), 2, list);
                zzao zza = zzgVar.zza((zzao) list.get(0));
                if (!(zza instanceof zzas)) {
                    throw new IllegalArgumentException(String.format("Expected string for assign var. got %s", zza.getClass().getCanonicalName()));
                }
                if (!zzgVar.zzd(zza.zzc())) {
                    throw new IllegalArgumentException(String.format("Attempting to assign undefined value %s", zza.zzc()));
                }
                zzao zza2 = zzgVar.zza((zzao) list.get(1));
                zzgVar.zze(zza.zzc(), zza2);
                return zza2;
            case 14:
                zzh.zzb(zzbk.CONST.name(), 2, list);
                if (list.size() % 2 != 0) {
                    throw new IllegalArgumentException(String.format("CONST requires an even number of arguments, found %s", Integer.valueOf(list.size())));
                }
                while (i < list.size() - 1) {
                    zzao zza3 = zzgVar.zza((zzao) list.get(i));
                    if (!(zza3 instanceof zzas)) {
                        throw new IllegalArgumentException(String.format("Expected string for const name. got %s", zza3.getClass().getCanonicalName()));
                    }
                    zzgVar.zzg(zza3.zzc(), zzgVar.zza((zzao) list.get(i + 1)));
                    i += 2;
                }
                return zzao.zzf;
            case 17:
                if (list.isEmpty()) {
                    return new zzae();
                }
                zzae zzaeVar = new zzae();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    zzao zza4 = zzgVar.zza((zzao) it.next());
                    if (zza4 instanceof zzag) {
                        throw new IllegalStateException("Failed to evaluate array element");
                    }
                    zzaeVar.zzn(i, zza4);
                    i++;
                }
                return zzaeVar;
            case 18:
                if (list.isEmpty()) {
                    return new zzal();
                }
                if (list.size() % 2 != 0) {
                    throw new IllegalArgumentException(String.format("CREATE_OBJECT requires an even number of arguments, found %s", Integer.valueOf(list.size())));
                }
                zzal zzalVar = new zzal();
                while (i < list.size() - 1) {
                    zzao zza5 = zzgVar.zza((zzao) list.get(i));
                    zzao zza6 = zzgVar.zza((zzao) list.get(i + 1));
                    if ((zza5 instanceof zzag) || (zza6 instanceof zzag)) {
                        throw new IllegalStateException("Failed to evaluate map entry");
                    }
                    zzalVar.zzm(zza5.zzc(), zza6);
                    i += 2;
                }
                return zzalVar;
            case 24:
                zzh.zzb(zzbk.EXPRESSION_LIST.name(), 1, list);
                zzao zzaoVar = zzao.zzf;
                while (i < list.size()) {
                    zzaoVar = zzgVar.zza((zzao) list.get(i));
                    if (zzaoVar instanceof zzag) {
                        throw new IllegalStateException("ControlValue cannot be in an expression list");
                    }
                    i++;
                }
                return zzaoVar;
            case 33:
                zzh.zza(zzbk.GET.name(), 1, list);
                zzao zza7 = zzgVar.zza((zzao) list.get(0));
                if (zza7 instanceof zzas) {
                    return zzgVar.zzh(zza7.zzc());
                }
                throw new IllegalArgumentException(String.format("Expected string for get var. got %s", zza7.getClass().getCanonicalName()));
            case 35:
            case 36:
                zzh.zza(zzbk.GET_PROPERTY.name(), 2, list);
                zzao zza8 = zzgVar.zza((zzao) list.get(0));
                zzao zza9 = zzgVar.zza((zzao) list.get(1));
                if ((zza8 instanceof zzae) && zzh.zzd(zza9)) {
                    return ((zzae) zza8).zzl(zza9.zzd().intValue());
                }
                if (zza8 instanceof zzak) {
                    return ((zzak) zza8).zzk(zza9.zzc());
                }
                if (zza8 instanceof zzas) {
                    if ("length".equals(zza9.zzc())) {
                        return new zzah(Double.valueOf(zza8.zzc().length()));
                    }
                    if (zzh.zzd(zza9) && zza9.zzd().doubleValue() < zza8.zzc().length()) {
                        return new zzas(String.valueOf(zza8.zzc().charAt(zza9.zzd().intValue())));
                    }
                }
                return zzao.zzf;
            case 49:
                zzh.zza(zzbk.NULL.name(), 0, list);
                return zzao.zzg;
            case 58:
                zzh.zza(zzbk.SET_PROPERTY.name(), 3, list);
                zzao zza10 = zzgVar.zza((zzao) list.get(0));
                zzao zza11 = zzgVar.zza((zzao) list.get(1));
                zzao zza12 = zzgVar.zza((zzao) list.get(2));
                if (zza10 == zzao.zzf || zza10 == zzao.zzg) {
                    throw new IllegalStateException(String.format("Can't set property %s of %s", zza11.zzc(), zza10.zzc()));
                }
                if ((zza10 instanceof zzae) && (zza11 instanceof zzah)) {
                    ((zzae) zza10).zzn(zza11.zzd().intValue(), zza12);
                } else if (zza10 instanceof zzak) {
                    ((zzak) zza10).zzm(zza11.zzc(), zza12);
                    return zza12;
                }
                return zza12;
            case 62:
                zzh.zza(zzbk.TYPEOF.name(), 1, list);
                zzao zza13 = zzgVar.zza((zzao) list.get(0));
                if (zza13 instanceof zzat) {
                    str2 = "undefined";
                } else if (zza13 instanceof zzaf) {
                    str2 = TypedValues.Custom.S_BOOLEAN;
                } else if (zza13 instanceof zzah) {
                    str2 = "number";
                } else if (zza13 instanceof zzas) {
                    str2 = TypedValues.Custom.S_STRING;
                } else if (zza13 instanceof zzan) {
                    str2 = "function";
                } else {
                    if ((zza13 instanceof zzap) || (zza13 instanceof zzag)) {
                        throw new IllegalArgumentException(String.format("Unsupported value type %s in typeof", zza13));
                    }
                    str2 = "object";
                }
                return new zzas(str2);
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                zzh.zza(zzbk.UNDEFINED.name(), 0, list);
                return zzao.zzf;
            case 64:
                zzh.zzb(zzbk.VAR.name(), 1, list);
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    zzao zza14 = zzgVar.zza((zzao) it2.next());
                    if (!(zza14 instanceof zzas)) {
                        throw new IllegalArgumentException(String.format("Expected string for var name. got %s", zza14.getClass().getCanonicalName()));
                    }
                    zzgVar.zzf(zza14.zzc(), zzao.zzf);
                }
                return zzao.zzf;
            default:
                return super.zzb(str);
        }
    }
}
