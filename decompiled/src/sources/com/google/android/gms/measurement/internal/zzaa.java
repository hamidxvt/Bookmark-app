package com.google.android.gms.measurement.internal;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzpu;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
final class zzaa extends zzab {
    final /* synthetic */ zzad zza;
    private final com.google.android.gms.internal.measurement.zzff zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaa(zzad zzadVar, String str, int i, com.google.android.gms.internal.measurement.zzff zzffVar) {
        super(str, i);
        Objects.requireNonNull(zzadVar);
        this.zza = zzadVar;
        this.zzh = zzffVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    final int zza() {
        return this.zzh.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    final boolean zzb() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzab
    final boolean zzc() {
        return this.zzh.zzg();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0371 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0369  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final boolean zzd(Long l, Long l2, com.google.android.gms.internal.measurement.zzhs zzhsVar, long j, zzbc zzbcVar, boolean z) {
        Boolean zzi;
        zzpu.zza();
        zzad zzadVar = this.zza;
        zzic zzicVar = zzadVar.zzu;
        zzal zzc = zzicVar.zzc();
        String str = this.zzb;
        boolean zzp = zzc.zzp(str, zzfy.zzaF);
        com.google.android.gms.internal.measurement.zzff zzffVar = this.zzh;
        long j2 = zzffVar.zzm() ? zzbcVar.zze : j;
        r9 = null;
        r9 = null;
        r9 = null;
        r9 = null;
        r9 = null;
        r9 = null;
        r9 = null;
        r9 = null;
        r9 = null;
        r9 = null;
        r9 = null;
        Boolean bool = null;
        if (Log.isLoggable(zzicVar.zzaV().zzn(), 2)) {
            zzicVar.zzaV().zzk().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(this.zzc), zzffVar.zza() ? Integer.valueOf(zzffVar.zzb()) : null, zzicVar.zzl().zza(zzffVar.zzc()));
            zzicVar.zzaV().zzk().zzb("Filter definition", zzadVar.zzg.zzp().zzj(zzffVar));
        }
        if (!zzffVar.zza() || zzffVar.zzb() > 256) {
            zzicVar.zzaV().zze().zzc("Invalid event filter ID. appId, id", zzgu.zzl(str), String.valueOf(zzffVar.zza() ? Integer.valueOf(zzffVar.zzb()) : null));
            return false;
        }
        Object[] objArr = (zzffVar.zzi() || zzffVar.zzj()) ? true : zzffVar.zzm();
        if (z && objArr != true) {
            zzicVar.zzaV().zzk().zzc("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzc), zzffVar.zza() ? Integer.valueOf(zzffVar.zzb()) : null);
            return true;
        }
        String zzd = zzhsVar.zzd();
        if (zzffVar.zzg()) {
            Boolean zzg = zzg(j2, zzffVar.zzh());
            if (zzg != null) {
                if (!zzg.booleanValue()) {
                    bool = false;
                }
            }
            zzicVar.zzaV().zzk().zzb("Event filter result", bool != null ? "null" : bool);
            if (bool != null) {
                return false;
            }
            this.zzd = true;
            if (!bool.booleanValue()) {
                return true;
            }
            this.zze = true;
            if (objArr != false && zzhsVar.zze()) {
                Long valueOf = Long.valueOf(zzhsVar.zzf());
                if (zzffVar.zzj()) {
                    if (zzp && zzffVar.zzg()) {
                        valueOf = l;
                    }
                    this.zzg = valueOf;
                } else {
                    if (zzp && zzffVar.zzg()) {
                        valueOf = l2;
                    }
                    this.zzf = valueOf;
                }
            }
            return true;
        }
        HashSet hashSet = new HashSet();
        Iterator it = zzffVar.zzd().iterator();
        while (true) {
            if (!it.hasNext()) {
                ArrayMap arrayMap = new ArrayMap();
                Iterator it2 = zzhsVar.zza().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        Iterator it3 = zzffVar.zzd().iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                bool = true;
                                break;
                            }
                            com.google.android.gms.internal.measurement.zzfh zzfhVar = (com.google.android.gms.internal.measurement.zzfh) it3.next();
                            boolean z2 = zzfhVar.zze() && zzfhVar.zzf();
                            String zzh = zzfhVar.zzh();
                            if (zzh.isEmpty()) {
                                zzicVar.zzaV().zze().zzb("Event has empty param name. event", zzicVar.zzl().zza(zzd));
                                break;
                            }
                            V v = arrayMap.get(zzh);
                            if (v instanceof Long) {
                                if (!zzfhVar.zzc()) {
                                    zzicVar.zzaV().zze().zzc("No number filter for long param. event, param", zzicVar.zzl().zza(zzd), zzicVar.zzl().zzb(zzh));
                                    break;
                                }
                                Boolean zzg2 = zzg(((Long) v).longValue(), zzfhVar.zzd());
                                if (zzg2 == null) {
                                    break;
                                }
                                if (zzg2.booleanValue() == z2) {
                                    bool = false;
                                    break;
                                }
                            } else if (v instanceof Double) {
                                if (!zzfhVar.zzc()) {
                                    zzicVar.zzaV().zze().zzc("No number filter for double param. event, param", zzicVar.zzl().zza(zzd), zzicVar.zzl().zzb(zzh));
                                    break;
                                }
                                Boolean zzh2 = zzh(((Double) v).doubleValue(), zzfhVar.zzd());
                                if (zzh2 == null) {
                                    break;
                                }
                                if (zzh2.booleanValue() == z2) {
                                    bool = false;
                                    break;
                                }
                            } else if (v instanceof String) {
                                if (!zzfhVar.zza()) {
                                    if (!zzfhVar.zzc()) {
                                        zzicVar.zzaV().zze().zzc("No filter for String param. event, param", zzicVar.zzl().zza(zzd), zzicVar.zzl().zzb(zzh));
                                        break;
                                    }
                                    String str2 = (String) v;
                                    if (!zzpk.zzm(str2)) {
                                        zzicVar.zzaV().zze().zzc("Invalid param value for number filter. event, param", zzicVar.zzl().zza(zzd), zzicVar.zzl().zzb(zzh));
                                        break;
                                    }
                                    zzi = zzi(str2, zzfhVar.zzd());
                                } else {
                                    zzi = zzf((String) v, zzfhVar.zzb(), zzicVar.zzaV());
                                }
                                if (zzi == null) {
                                    break;
                                }
                                if (zzi.booleanValue() == z2) {
                                    bool = false;
                                    break;
                                }
                            } else if (v == 0) {
                                zzicVar.zzaV().zzk().zzc("Missing param for filter. event, param", zzicVar.zzl().zza(zzd), zzicVar.zzl().zzb(zzh));
                                bool = false;
                            } else {
                                zzicVar.zzaV().zze().zzc("Unknown param type. event, param", zzicVar.zzl().zza(zzd), zzicVar.zzl().zzb(zzh));
                            }
                        }
                    } else {
                        com.google.android.gms.internal.measurement.zzhw zzhwVar = (com.google.android.gms.internal.measurement.zzhw) it2.next();
                        if (hashSet.contains(zzhwVar.zzb())) {
                            if (!zzhwVar.zze()) {
                                if (!zzhwVar.zzi()) {
                                    if (!zzhwVar.zzc()) {
                                        zzicVar.zzaV().zze().zzc("Unknown value for param. event, param", zzicVar.zzl().zza(zzd), zzicVar.zzl().zzb(zzhwVar.zzb()));
                                        break;
                                    }
                                    arrayMap.put(zzhwVar.zzb(), zzhwVar.zzd());
                                } else {
                                    arrayMap.put(zzhwVar.zzb(), zzhwVar.zzi() ? Double.valueOf(zzhwVar.zzj()) : null);
                                }
                            } else {
                                arrayMap.put(zzhwVar.zzb(), zzhwVar.zze() ? Long.valueOf(zzhwVar.zzf()) : null);
                            }
                        }
                    }
                }
            } else {
                com.google.android.gms.internal.measurement.zzfh zzfhVar2 = (com.google.android.gms.internal.measurement.zzfh) it.next();
                if (zzfhVar2.zzh().isEmpty()) {
                    zzicVar.zzaV().zze().zzb("null or empty param name in filter. event", zzicVar.zzl().zza(zzd));
                    break;
                }
                hashSet.add(zzfhVar2.zzh());
            }
        }
        zzicVar.zzaV().zzk().zzb("Event filter result", bool != null ? "null" : bool);
        if (bool != null) {
        }
    }
}
