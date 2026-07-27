package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public abstract class zzkm {
    public static final /* synthetic */ int zzc = 0;
    private static final Object zzd = new Object();

    @Nullable
    private static volatile zzkh zze = null;
    private static volatile boolean zzf = false;
    private static final AtomicInteger zzh;
    final zzkg zza;
    final String zzb;
    private Object zzg;
    private volatile int zzi = -1;
    private volatile Object zzj;
    private volatile boolean zzk;

    static {
        new AtomicReference();
        Preconditions.checkNotNull(zzkk.zza, "BuildInfo must be non-null");
        zzh = new AtomicInteger();
    }

    /* synthetic */ zzkm(zzkg zzkgVar, String str, Object obj, boolean z, byte[] bArr) {
        if (zzkgVar.zza == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zza = zzkgVar;
        this.zzb = str;
        this.zzg = obj;
        this.zzk = false;
    }

    public static void zzb(final Context context) {
        if (zze != null || context == null) {
            return;
        }
        Object obj = zzd;
        synchronized (obj) {
            if (zze == null) {
                synchronized (obj) {
                    zzkh zzkhVar = zze;
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    if (zzkhVar == null || zzkhVar.zza() != context) {
                        if (zzkhVar != null) {
                            zzjr.zzd();
                            zzko.zzb();
                            zzjy.zzc();
                        }
                        zze = new zzjn(context, Suppliers.memoize(new Supplier() { // from class: com.google.android.gms.internal.measurement.zzkl
                            @Override // com.google.common.base.Supplier
                            public final /* synthetic */ Object get() {
                                int i = zzkm.zzc;
                                return zzjz.zza(context);
                            }
                        }));
                        zzh.incrementAndGet();
                    }
                }
            }
        }
    }

    public static void zzc() {
        zzh.incrementAndGet();
    }

    @Nullable
    abstract Object zza(Object obj);

    /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0058 A[Catch: all -> 0x00ca, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0018, B:11:0x001e, B:13:0x0034, B:16:0x004d, B:18:0x0058, B:20:0x0062, B:22:0x0085, B:24:0x008d, B:27:0x00b4, B:30:0x00bc, B:31:0x00bf, B:32:0x00c4, B:33:0x0096, B:35:0x009a, B:37:0x00aa, B:39:0x00b0, B:43:0x0073, B:46:0x00c8), top: B:4:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0096 A[Catch: all -> 0x00ca, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0018, B:11:0x001e, B:13:0x0034, B:16:0x004d, B:18:0x0058, B:20:0x0062, B:22:0x0085, B:24:0x008d, B:27:0x00b4, B:30:0x00bc, B:31:0x00bf, B:32:0x00c4, B:33:0x0096, B:35:0x009a, B:37:0x00aa, B:39:0x00b0, B:43:0x0073, B:46:0x00c8), top: B:4:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0073 A[Catch: all -> 0x00ca, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0018, B:11:0x001e, B:13:0x0034, B:16:0x004d, B:18:0x0058, B:20:0x0062, B:22:0x0085, B:24:0x008d, B:27:0x00b4, B:30:0x00bc, B:31:0x00bf, B:32:0x00c4, B:33:0x0096, B:35:0x009a, B:37:0x00aa, B:39:0x00b0, B:43:0x0073, B:46:0x00c8), top: B:4:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzd() {
        String str;
        Object zza;
        String zze2;
        Object zze3;
        int i = zzh.get();
        if (this.zzi < i) {
            synchronized (this) {
                if (this.zzi < i) {
                    zzkh zzkhVar = zze;
                    Optional absent = Optional.absent();
                    Object obj = null;
                    if (zzkhVar != null && zzkhVar.zzb() != null) {
                        absent = (Optional) ((Supplier) Preconditions.checkNotNull(zzkhVar.zzb())).get();
                        if (absent.isPresent()) {
                            zzjt zzjtVar = (zzjt) absent.get();
                            zzkg zzkgVar = this.zza;
                            str = zzjtVar.zza(zzkgVar.zza, null, zzkgVar.zzc, this.zzb);
                            Preconditions.checkState(zzkhVar == null, "Must call PhenotypeFlagInitializer.maybeInit() first");
                            zzkg zzkgVar2 = this.zza;
                            Uri uri = zzkgVar2.zza;
                            zzjv zza2 = uri == null ? zzka.zza(zzkhVar.zza(), uri) ? zzjr.zza(zzkhVar.zza().getContentResolver(), uri, zzkj.zza) : null : zzko.zza(zzkhVar.zza(), (String) Preconditions.checkNotNull(null), zzki.zza);
                            zza = (zza2 != null || (zze3 = zza2.zze(this.zzb)) == null) ? null : zza(zze3);
                            if (zza == null) {
                                if (!zzkgVar2.zzd && (zze2 = zzjy.zza(zzkhVar.zza()).zze(this.zzb)) != null) {
                                    obj = zza(zze2);
                                }
                                zza = obj == null ? this.zzg : obj;
                            }
                            if (absent.isPresent()) {
                                zza = str == null ? this.zzg : zza(str);
                            }
                            this.zzj = zza;
                            this.zzi = i;
                        }
                    }
                    str = null;
                    Preconditions.checkState(zzkhVar == null, "Must call PhenotypeFlagInitializer.maybeInit() first");
                    zzkg zzkgVar22 = this.zza;
                    Uri uri2 = zzkgVar22.zza;
                    if (uri2 == null) {
                    }
                    if (zza2 != null) {
                    }
                    if (zza == null) {
                    }
                    if (absent.isPresent()) {
                    }
                    this.zzj = zza;
                    this.zzi = i;
                }
            }
        }
        return this.zzj;
    }
}
