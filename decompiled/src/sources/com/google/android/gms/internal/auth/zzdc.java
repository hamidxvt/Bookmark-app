package com.google.android.gms.internal.auth;

import android.content.Context;
import android.net.Uri;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
public abstract class zzdc {
    public static final /* synthetic */ int zzd = 0;
    final zzcz zzb;
    final String zzc;
    private final Object zzj;
    private volatile int zzk = -1;
    private volatile Object zzl;
    private static final Object zza = new Object();

    @Nullable
    private static volatile zzda zze = null;
    private static volatile boolean zzf = false;
    private static final AtomicReference zzg = new AtomicReference();
    private static final zzde zzh = new zzde(new Object() { // from class: com.google.android.gms.internal.auth.zzcu
    });
    private static final AtomicInteger zzi = new AtomicInteger();

    /* synthetic */ zzdc(zzcz zzczVar, String str, Object obj, boolean z, zzdb zzdbVar) {
        if (zzczVar.zza == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zzb = zzczVar;
        this.zzc = str;
        this.zzj = obj;
    }

    public static void zzc() {
        zzi.incrementAndGet();
    }

    public static void zzd(final Context context) {
        if (zze != null || context == null) {
            return;
        }
        synchronized (zza) {
            if (zze == null) {
                synchronized (zza) {
                    zzda zzdaVar = zze;
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    if (zzdaVar == null || zzdaVar.zza() != context) {
                        zzcg.zzd();
                        zzdd.zzc();
                        zzco.zze();
                        zze = new zzcd(context, zzdo.zza(new zzdj() { // from class: com.google.android.gms.internal.auth.zzct
                            @Override // com.google.android.gms.internal.auth.zzdj
                            public final Object zza() {
                                Context context2 = context;
                                int i = zzdc.zzd;
                                return zzcp.zza(context2);
                            }
                        }));
                        zzi.incrementAndGet();
                    }
                }
            }
        }
    }

    abstract Object zza(Object obj);

    /* JADX WARN: Removed duplicated region for block: B:14:0x0040 A[Catch: all -> 0x00c8, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0018, B:11:0x0028, B:14:0x0040, B:16:0x0046, B:18:0x0050, B:20:0x0071, B:22:0x0079, B:24:0x0081, B:26:0x0087, B:29:0x0099, B:31:0x009f, B:32:0x0097, B:34:0x00a7, B:36:0x00ab, B:39:0x00b3, B:40:0x00b6, B:41:0x00bb, B:44:0x0065, B:45:0x00c0, B:46:0x00c5, B:49:0x00c6), top: B:4:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c0 A[Catch: all -> 0x00c8, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0018, B:11:0x0028, B:14:0x0040, B:16:0x0046, B:18:0x0050, B:20:0x0071, B:22:0x0079, B:24:0x0081, B:26:0x0087, B:29:0x0099, B:31:0x009f, B:32:0x0097, B:34:0x00a7, B:36:0x00ab, B:39:0x00b3, B:40:0x00b6, B:41:0x00bb, B:44:0x0065, B:45:0x00c0, B:46:0x00c5, B:49:0x00c6), top: B:4:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzb() {
        String str;
        Object zzb;
        int i = zzi.get();
        if (this.zzk < i) {
            synchronized (this) {
                if (this.zzk < i) {
                    zzda zzdaVar = zze;
                    zzdh zzc = zzdh.zzc();
                    Object obj = null;
                    if (zzdaVar != null) {
                        zzc = (zzdh) zzdaVar.zzb().zza();
                        if (zzc.zzb()) {
                            zzci zzciVar = (zzci) zzc.zza();
                            zzcz zzczVar = this.zzb;
                            str = zzciVar.zza(zzczVar.zza, null, zzczVar.zzc, this.zzc);
                            if (zzdaVar != null) {
                                throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                            }
                            Uri uri = this.zzb.zza;
                            zzcl zza2 = uri != null ? zzcq.zza(zzdaVar.zza(), uri) ? zzcg.zza(zzdaVar.zza().getContentResolver(), this.zzb.zza, new Runnable() { // from class: com.google.android.gms.internal.auth.zzcs
                                @Override // java.lang.Runnable
                                public final void run() {
                                    zzdc.zzc();
                                }
                            }) : null : zzdd.zza(zzdaVar.zza(), null, new Runnable() { // from class: com.google.android.gms.internal.auth.zzcs
                                @Override // java.lang.Runnable
                                public final void run() {
                                    zzdc.zzc();
                                }
                            });
                            Object zza3 = (zza2 == null || (zzb = zza2.zzb(this.zzc)) == null) ? null : zza(zzb);
                            if (zza3 == null) {
                                if (!this.zzb.zzd) {
                                    String zzb2 = zzco.zza(zzdaVar.zza()).zzb(this.zzb.zzd ? null : this.zzc);
                                    if (zzb2 != null) {
                                        obj = zza(zzb2);
                                    }
                                }
                                zza3 = obj == null ? this.zzj : obj;
                            }
                            if (zzc.zzb()) {
                                zza3 = str == null ? this.zzj : zza(str);
                            }
                            this.zzl = zza3;
                            this.zzk = i;
                        }
                    }
                    str = null;
                    if (zzdaVar != null) {
                    }
                }
            }
        }
        return this.zzl;
    }
}
