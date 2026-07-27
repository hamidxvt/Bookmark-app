package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzrb;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
final class zzit implements Callable {
    final /* synthetic */ zzbg zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzjd zzc;

    zzit(zzjd zzjdVar, zzbg zzbgVar, String str) {
        this.zza = zzbgVar;
        this.zzb = str;
        Objects.requireNonNull(zzjdVar);
        this.zzc = zzjdVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.google.android.gms.measurement.internal.zzbg, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.google.android.gms.measurement.internal.zzol] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        byte[] bArr;
        zzpg zzpgVar;
        zzpn zzpnVar;
        zzpg zzpgVar2;
        zzpg zzpgVar3;
        zzh zzhVar;
        com.google.android.gms.internal.measurement.zzhz zzhzVar;
        Bundle bundle;
        String str;
        zzic zzicVar;
        com.google.android.gms.internal.measurement.zzic zzicVar2;
        String str2;
        boolean z;
        Object obj;
        zzbc zza;
        long j;
        zzav zzj;
        zzjd zzjdVar = this.zzc;
        zzjdVar.zzL().zzZ();
        zzlp zzn = zzjdVar.zzL().zzn();
        zzn.zzg();
        zzic zzicVar3 = zzn.zzu;
        zzic.zzL();
        ?? r4 = this.zza;
        Preconditions.checkNotNull(r4);
        String str3 = this.zzb;
        Preconditions.checkNotEmpty(str3);
        String str4 = r4.zza;
        if (!"_iap".equals(str4) && !"_iapx".equals(str4)) {
            zzn.zzu.zzaV().zzj().zzc("Generating a payload for this event is not available. package_name, event_name", str3, str4);
            return null;
        }
        zzpg zzpgVar4 = zzn.zzg;
        com.google.android.gms.internal.measurement.zzhz zzh = com.google.android.gms.internal.measurement.zzib.zzh();
        zzpgVar4.zzj().zzb();
        try {
            zzh zzu = zzpgVar4.zzj().zzu(str3);
            if (zzu == null) {
                zzn.zzu.zzaV().zzj().zzb("Log and bundle not available. package_name", str3);
                bArr = new byte[0];
            } else if (zzu.zzD()) {
                com.google.android.gms.internal.measurement.zzic zzaE = com.google.android.gms.internal.measurement.zzid.zzaE();
                zzaE.zza(1);
                zzaE.zzC("android");
                if (!TextUtils.isEmpty(zzu.zzc())) {
                    zzaE.zzL(zzu.zzc());
                }
                if (!TextUtils.isEmpty(zzu.zzv())) {
                    zzaE.zzJ((String) Preconditions.checkNotNull(zzu.zzv()));
                }
                if (!TextUtils.isEmpty(zzu.zzr())) {
                    zzaE.zzM((String) Preconditions.checkNotNull(zzu.zzr()));
                }
                if (zzu.zzt() != -2147483648L) {
                    zzaE.zzaj((int) zzu.zzt());
                }
                zzaE.zzN(zzu.zzx());
                zzaE.zzar(zzu.zzB());
                String zzf = zzu.zzf();
                if (!TextUtils.isEmpty(zzf)) {
                    zzaE.zzad(zzf);
                }
                zzaE.zzay(zzu.zzak());
                zzjl zzB = zzn.zzg.zzB(str3);
                zzaE.zzY(zzu.zzz());
                if (zzicVar3.zzB() && zzn.zzu.zzc().zzC(zzaE.zzK()) && zzB.zzo(zzjk.AD_STORAGE) && !TextUtils.isEmpty(null)) {
                    zzaE.zzam(null);
                }
                zzaE.zzat(zzB.zzk());
                if (zzB.zzo(zzjk.AD_STORAGE) && zzu.zzac()) {
                    Pair zzc = zzpgVar4.zzq().zzc(zzu.zzc(), zzB);
                    if (zzu.zzac() && !TextUtils.isEmpty((CharSequence) zzc.first)) {
                        try {
                            zzaE.zzQ(zzlp.zzc((String) zzc.first, Long.toString(r4.zzd)));
                            if (zzc.second != null) {
                                zzaE.zzT(((Boolean) zzc.second).booleanValue());
                            }
                        } catch (SecurityException e) {
                            zzn.zzu.zzaV().zzj().zzb("Resettable device id encryption failed", e.getMessage());
                            bArr = new byte[0];
                            zzpgVar = zzn.zzg;
                            zzj = zzpgVar.zzj();
                            zzj.zzd();
                            return bArr;
                        }
                    }
                }
                zzic zzicVar4 = zzn.zzu;
                zzicVar4.zzu().zzw();
                zzaE.zzF(Build.MODEL);
                zzicVar4.zzu().zzw();
                zzaE.zzE(Build.VERSION.RELEASE);
                zzaE.zzI((int) zzicVar4.zzu().zzb());
                zzaE.zzH(zzicVar4.zzu().zzc());
                try {
                    try {
                        if (zzB.zzo(zzjk.ANALYTICS_STORAGE) && zzu.zzd() != null) {
                            zzaE.zzW(zzlp.zzc((String) Preconditions.checkNotNull(zzu.zzd()), Long.toString(r4.zzd)));
                        }
                        if (!TextUtils.isEmpty(zzu.zzl())) {
                            zzaE.zzah((String) Preconditions.checkNotNull(zzu.zzl()));
                        }
                        String zzc2 = zzu.zzc();
                        zzpg zzpgVar5 = zzn.zzg;
                        List zzn2 = zzpgVar5.zzj().zzn(zzc2);
                        Iterator it = zzn2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                zzpnVar = null;
                                break;
                            }
                            zzpnVar = (zzpn) it.next();
                            if ("_lte".equals(zzpnVar.zzc)) {
                                break;
                            }
                        }
                        if (zzpnVar == null || zzpnVar.zze == null) {
                            zzpn zzpnVar2 = new zzpn(zzc2, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_lte", zzn.zzu.zzaZ().currentTimeMillis(), 0L);
                            zzn2.add(zzpnVar2);
                            zzpgVar5.zzj().zzl(zzpnVar2);
                        }
                        com.google.android.gms.internal.measurement.zziu[] zziuVarArr = new com.google.android.gms.internal.measurement.zziu[zzn2.size()];
                        for (int i = 0; i < zzn2.size(); i++) {
                            com.google.android.gms.internal.measurement.zzit zzm = com.google.android.gms.internal.measurement.zziu.zzm();
                            zzm.zzb(((zzpn) zzn2.get(i)).zzc);
                            zzm.zza(((zzpn) zzn2.get(i)).zzd);
                            zzpgVar5.zzp().zzc(zzm, ((zzpn) zzn2.get(i)).zze);
                            zziuVarArr[i] = (com.google.android.gms.internal.measurement.zziu) zzm.zzbc();
                        }
                        zzaE.zzq(Arrays.asList(zziuVarArr));
                        zzpg zzpgVar6 = zzn.zzg;
                        zzpgVar6.zzI(zzu, zzaE);
                        zzpgVar6.zzJ(zzu, zzaE);
                        zzgv zza2 = zzgv.zza(r4);
                        zzic zzicVar5 = zzn.zzu;
                        zzpp zzk = zzicVar5.zzk();
                        Bundle bundle2 = zza2.zzd;
                        zzk.zzI(bundle2, zzpgVar5.zzj().zzU(str3));
                        zzicVar5.zzk().zzG(zza2, zzicVar5.zzc().zzd(str3));
                        try {
                            bundle2.putLong("_c", 1L);
                            zzicVar5.zzaV().zzj().zza("Marking in-app purchase as real-time");
                            bundle2.putLong("_r", 1L);
                            String str5 = r4.zzc;
                            bundle2.putString("_o", str5);
                            if (zzicVar5.zzk().zzaa(zzaE.zzK(), zzu.zzay())) {
                                zzicVar5.zzk().zzM(bundle2, "_dbg", 1L);
                                zzicVar5.zzk().zzM(bundle2, "_r", 1L);
                            }
                            zzav zzj2 = zzpgVar5.zzj();
                            String str6 = r4.zza;
                            zzbc zzf2 = zzj2.zzf(str3, str6);
                            if (zzf2 == null) {
                                bundle = bundle2;
                                str = str5;
                                zzicVar = zzicVar5;
                                zzicVar2 = zzaE;
                                zzpgVar3 = zzpgVar5;
                                zzhVar = zzu;
                                zzhzVar = zzh;
                                zzpgVar2 = zzpgVar6;
                                str2 = str3;
                                z = true;
                                obj = null;
                                zza = new zzbc(str3, str6, 0L, 0L, 0L, r4.zzd, 0L, null, null, null, null);
                                j = 0;
                            } else {
                                zzpgVar2 = zzpgVar6;
                                zzpgVar3 = zzpgVar5;
                                zzhVar = zzu;
                                zzhzVar = zzh;
                                bundle = bundle2;
                                str = str5;
                                zzicVar = zzicVar5;
                                zzicVar2 = zzaE;
                                str2 = str3;
                                z = true;
                                obj = null;
                                long j2 = zzf2.zzf;
                                zza = zzf2.zza(r4.zzd);
                                j = j2;
                            }
                            zzpgVar3.zzj().zzh(zza);
                            try {
                                zzbb zzbbVar = new zzbb(zzn.zzu, str, str2, str6, r4.zzd, j, bundle);
                                com.google.android.gms.internal.measurement.zzhr zzk2 = com.google.android.gms.internal.measurement.zzhs.zzk();
                                zzk2.zzo(zzbbVar.zzd);
                                zzk2.zzl(zzbbVar.zzb);
                                zzk2.zzq(zzbbVar.zze);
                                zzbe zzbeVar = zzbbVar.zzf;
                                zzbd zzbdVar = new zzbd(zzbeVar);
                                while (zzbdVar.hasNext()) {
                                    String next = zzbdVar.next();
                                    String str7 = next;
                                    com.google.android.gms.internal.measurement.zzhv zzn3 = com.google.android.gms.internal.measurement.zzhw.zzn();
                                    zzn3.zzb(next);
                                    Object zza3 = zzbeVar.zza(next);
                                    if (zza3 != null) {
                                        zzpgVar3.zzp().zzd(zzn3, zza3);
                                        zzk2.zzg(zzn3);
                                    }
                                }
                                com.google.android.gms.internal.measurement.zzic zzicVar6 = zzicVar2;
                                zzicVar6.zzg(zzk2);
                                com.google.android.gms.internal.measurement.zzie zza4 = com.google.android.gms.internal.measurement.zzig.zza();
                                com.google.android.gms.internal.measurement.zzht zza5 = com.google.android.gms.internal.measurement.zzhu.zza();
                                zza5.zzb(zza.zzc);
                                zza5.zza(str6);
                                zza4.zza(zza5);
                                zzicVar6.zzap(zza4);
                                zzicVar6.zzaf(zzpgVar3.zzm().zzb(zzhVar.zzc(), Collections.emptyList(), zzicVar6.zzk(), Long.valueOf(zzk2.zzn()), Long.valueOf(zzk2.zzn()), false));
                                if (zzk2.zzm()) {
                                    zzicVar6.zzv(zzk2.zzn());
                                    zzicVar6.zzx(zzk2.zzn());
                                }
                                long zzp = zzhVar.zzp();
                                if (zzp != 0) {
                                    zzicVar6.zzA(zzp);
                                }
                                long zzn4 = zzhVar.zzn();
                                if (zzn4 != 0) {
                                    zzicVar6.zzy(zzn4);
                                } else if (zzp != 0) {
                                    zzicVar6.zzy(zzp);
                                }
                                String zzh2 = zzhVar.zzh();
                                zzrb.zza();
                                String str8 = str2;
                                if (zzicVar.zzc().zzp(str8, zzfy.zzaM) && zzh2 != null) {
                                    zzicVar6.zzau(zzh2);
                                }
                                zzhVar.zzL();
                                zzicVar6.zzZ((int) zzhVar.zzG());
                                zzicVar.zzc().zzi();
                                zzicVar6.zzO(133005L);
                                zzicVar6.zzs(zzicVar.zzaZ().currentTimeMillis());
                                zzicVar6.zzae(z);
                                zzpgVar2.zzS(zzicVar6.zzK(), zzicVar6);
                                com.google.android.gms.internal.measurement.zzhz zzhzVar2 = zzhzVar;
                                zzhzVar2.zze(zzicVar6);
                                zzh zzhVar2 = zzhVar;
                                zzhVar2.zzo(zzicVar6.zzu());
                                zzhVar2.zzq(zzicVar6.zzw());
                                zzpgVar3.zzj().zzv(zzhVar2, false, false);
                                zzpgVar3.zzj().zzc();
                                zzpgVar3.zzj().zzd();
                                try {
                                    return zzpgVar3.zzp().zzv(((com.google.android.gms.internal.measurement.zzib) zzhzVar2.zzbc()).zzcc());
                                } catch (IOException e2) {
                                    zzn.zzu.zzaV().zzb().zzc("Data loss. Failed to bundle and serialize. appId", zzgu.zzl(str8), e2);
                                    return obj;
                                }
                            } catch (Throwable th) {
                                th = th;
                                r4 = zzn;
                                r4.zzg.zzj().zzd();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            r4 = zzn;
                        }
                    } catch (SecurityException e3) {
                        zzn.zzu.zzaV().zzj().zzb("app instance id encryption failed", e3.getMessage());
                        bArr = new byte[0];
                        zzpgVar = zzn.zzg;
                        zzj = zzpgVar.zzj();
                        zzj.zzd();
                        return bArr;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } else {
                zzn.zzu.zzaV().zzj().zzb("Log and bundle disabled. package_name", str3);
                bArr = new byte[0];
            }
            zzj = zzpgVar4.zzj();
            zzj.zzd();
            return bArr;
        } catch (Throwable th4) {
            th = th4;
            r4 = zzn;
            r4.zzg.zzj().zzd();
            throw th;
        }
    }
}
