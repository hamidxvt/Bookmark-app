package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.app.BroadcastOptions;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzqp;
import com.google.firebase.messaging.Constants;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.coroutines.DebugKt;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzic implements zzjg {
    private static volatile zzic zzb;
    private volatile Boolean zzA;
    private volatile boolean zzB;
    private int zzC;
    private int zzD;
    final long zza;
    private final Context zzc;
    private final boolean zzd;
    private final zzae zze;
    private final zzal zzf;
    private final zzhh zzg;
    private final zzgu zzh;
    private final zzhz zzi;
    private final zzoc zzj;
    private final zzpp zzk;
    private final zzgn zzl;
    private final Clock zzm;
    private final zzmb zzn;
    private final zzlj zzo;
    private final zzd zzp;
    private final zzlo zzq;
    private final String zzr;
    private zzgl zzs;
    private zznl zzt;
    private zzba zzu;
    private zzgi zzv;
    private zzlq zzw;
    private Boolean zzy;
    private long zzz;
    private boolean zzx = false;
    private final AtomicInteger zzE = new AtomicInteger(0);

    zzic(zzjs zzjsVar) {
        Preconditions.checkNotNull(zzjsVar);
        Context context = zzjsVar.zza;
        this.zze = new zzae(context);
        zzfr.zza = this.zze;
        this.zzc = context;
        this.zzd = zzjsVar.zze;
        this.zzA = zzjsVar.zzb;
        this.zzr = zzjsVar.zzg;
        this.zzB = true;
        com.google.android.gms.internal.measurement.zzkm.zzb(this.zzc);
        this.zzm = DefaultClock.getInstance();
        Long l = zzjsVar.zzf;
        this.zza = l != null ? l.longValue() : this.zzm.currentTimeMillis();
        this.zzf = new zzal(this);
        zzhh zzhhVar = new zzhh(this);
        zzhhVar.zzx();
        this.zzg = zzhhVar;
        zzgu zzguVar = new zzgu(this);
        zzguVar.zzx();
        this.zzh = zzguVar;
        zzpp zzppVar = new zzpp(this);
        zzppVar.zzx();
        this.zzk = zzppVar;
        this.zzl = new zzgn(new zzjr(zzjsVar, this));
        this.zzp = new zzd(this);
        zzmb zzmbVar = new zzmb(this);
        zzmbVar.zzc();
        this.zzn = zzmbVar;
        zzlj zzljVar = new zzlj(this);
        zzljVar.zzc();
        this.zzo = zzljVar;
        zzoc zzocVar = new zzoc(this);
        zzocVar.zzc();
        this.zzj = zzocVar;
        zzlo zzloVar = new zzlo(this);
        zzloVar.zzx();
        this.zzq = zzloVar;
        zzhz zzhzVar = new zzhz(this);
        zzhzVar.zzx();
        this.zzi = zzhzVar;
        com.google.android.gms.internal.measurement.zzdd zzddVar = zzjsVar.zzd;
        boolean z = zzddVar == null || zzddVar.zzb == 0;
        if (this.zzc.getApplicationContext() instanceof Application) {
            zzlj zzljVar2 = this.zzo;
            zzO(zzljVar2);
            if (zzljVar2.zzu.zzc.getApplicationContext() instanceof Application) {
                Application application = (Application) zzljVar2.zzu.zzc.getApplicationContext();
                if (zzljVar2.zza == null) {
                    zzljVar2.zza = new zzky(zzljVar2);
                }
                if (z) {
                    application.unregisterActivityLifecycleCallbacks(zzljVar2.zza);
                    application.registerActivityLifecycleCallbacks(zzljVar2.zza);
                    zzgu zzguVar2 = zzljVar2.zzu.zzh;
                    zzP(zzguVar2);
                    zzguVar2.zzk().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzgu zzguVar3 = this.zzh;
            zzP(zzguVar3);
            zzguVar3.zze().zza("Application context is not an Application");
        }
        this.zzi.zzj(new zzia(this, zzjsVar));
    }

    static final void zzL() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private static final void zzM(zzf zzfVar) {
        if (zzfVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static final void zzN(zzje zzjeVar) {
        if (zzjeVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static final void zzO(zzg zzgVar) {
        if (zzgVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzgVar.zza()) {
            return;
        }
        String valueOf = String.valueOf(zzgVar.getClass());
        String.valueOf(valueOf);
        throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(valueOf)));
    }

    private static final void zzP(zzjf zzjfVar) {
        if (zzjfVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzjfVar.zzv()) {
            return;
        }
        String valueOf = String.valueOf(zzjfVar.getClass());
        String.valueOf(valueOf);
        throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(valueOf)));
    }

    public static zzic zzy(Context context, com.google.android.gms.internal.measurement.zzdd zzddVar, Long l) {
        Bundle bundle;
        if (zzddVar != null) {
            Bundle bundle2 = zzddVar.zzd;
            zzddVar = new com.google.android.gms.internal.measurement.zzdd(zzddVar.zza, zzddVar.zzb, zzddVar.zzc, bundle2, null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzic.class) {
                if (zzb == null) {
                    zzb = new zzic(new zzjs(context, zzddVar, l));
                }
            }
        } else if (zzddVar != null && (bundle = zzddVar.zzd) != null && bundle.containsKey("dataCollectionDefaultEnabled")) {
            Preconditions.checkNotNull(zzb);
            zzb.zzA = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzb);
        return zzb;
    }

    public final boolean zzA() {
        return this.zzA != null && this.zzA.booleanValue();
    }

    public final boolean zzB() {
        return zzC() == 0;
    }

    public final int zzC() {
        zzhz zzhzVar = this.zzi;
        zzP(zzhzVar);
        zzhzVar.zzg();
        zzal zzalVar = this.zzf;
        if (zzalVar.zzt()) {
            return 1;
        }
        zzP(zzhzVar);
        zzhzVar.zzg();
        if (!this.zzB) {
            return 8;
        }
        zzhh zzhhVar = this.zzg;
        zzN(zzhhVar);
        Boolean zzi = zzhhVar.zzi();
        if (zzi != null) {
            return zzi.booleanValue() ? 0 : 3;
        }
        zzae zzaeVar = zzalVar.zzu.zze;
        Boolean zzr = zzalVar.zzr("firebase_analytics_collection_enabled");
        return zzr != null ? zzr.booleanValue() ? 0 : 4 : (this.zzA == null || this.zzA.booleanValue()) ? 0 : 7;
    }

    public final void zzD(boolean z) {
        zzhz zzhzVar = this.zzi;
        zzP(zzhzVar);
        zzhzVar.zzg();
        this.zzB = z;
    }

    public final boolean zzE() {
        zzhz zzhzVar = this.zzi;
        zzP(zzhzVar);
        zzhzVar.zzg();
        return this.zzB;
    }

    final void zzF() {
        this.zzC++;
    }

    final void zzG() {
        this.zzE.incrementAndGet();
    }

    protected final boolean zzH() {
        if (!this.zzx) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
        zzhz zzhzVar = this.zzi;
        zzP(zzhzVar);
        zzhzVar.zzg();
        Boolean bool = this.zzy;
        if (bool == null || this.zzz == 0 || (!bool.booleanValue() && Math.abs(this.zzm.elapsedRealtime() - this.zzz) > 1000)) {
            this.zzz = this.zzm.elapsedRealtime();
            zzpp zzppVar = this.zzk;
            zzN(zzppVar);
            boolean z = false;
            if (zzppVar.zzY("android.permission.INTERNET")) {
                zzN(zzppVar);
                if (zzppVar.zzY("android.permission.ACCESS_NETWORK_STATE")) {
                    Context context = this.zzc;
                    if (Wrappers.packageManager(context).isCallerInstantApp() || this.zzf.zzE()) {
                        z = true;
                    } else if (zzpp.zzau(context) && zzpp.zzQ(context, false)) {
                        z = true;
                    }
                }
            }
            this.zzy = Boolean.valueOf(z);
            if (this.zzy.booleanValue()) {
                zzN(zzppVar);
                this.zzy = Boolean.valueOf(zzppVar.zzA(zzv().zzk()));
            }
        }
        return this.zzy.booleanValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x008a, code lost:
    
        if (r4.zzah() >= 234200) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzI() {
        NetworkInfo networkInfo;
        zzhz zzhzVar = this.zzi;
        zzP(zzhzVar);
        zzhzVar.zzg();
        zzlo zzloVar = this.zzq;
        zzP(zzloVar);
        zzP(zzloVar);
        String zzj = zzv().zzj();
        if (!this.zzf.zzu()) {
            zzgu zzguVar = this.zzh;
            zzP(zzguVar);
            zzguVar.zzk().zza("ADID collection is disabled from Manifest. Skipping");
            return false;
        }
        zzhh zzhhVar = this.zzg;
        zzN(zzhhVar);
        Pair zzb2 = zzhhVar.zzb(zzj);
        if (((Boolean) zzb2.second).booleanValue() || TextUtils.isEmpty((CharSequence) zzb2.first)) {
            zzgu zzguVar2 = this.zzh;
            zzP(zzguVar2);
            zzguVar2.zzk().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return false;
        }
        zzP(zzloVar);
        zzloVar.zzw();
        ConnectivityManager connectivityManager = (ConnectivityManager) zzloVar.zzu.zzc.getSystemService("connectivity");
        if (connectivityManager != null) {
            try {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException e) {
                networkInfo = null;
            }
        } else {
            networkInfo = null;
        }
        if (networkInfo == null || !networkInfo.isConnected()) {
            zzgu zzguVar3 = this.zzh;
            zzP(zzguVar3);
            zzguVar3.zze().zza("Network is not available for Deferred Deep Link request. Skipping");
            return false;
        }
        StringBuilder sb = new StringBuilder();
        zznl zzt = zzt();
        zzt.zzg();
        zzt.zzb();
        if (zzt.zzK()) {
            zzpp zzppVar = zzt.zzu.zzk;
            zzN(zzppVar);
        }
        zzlj zzljVar = this.zzo;
        zzO(zzljVar);
        zzic zzicVar = zzljVar.zzu;
        zzljVar.zzg();
        zzao zzz = zzicVar.zzt().zzz();
        Bundle bundle = zzz != null ? zzz.zza : null;
        if (bundle == null) {
            int i = this.zzD;
            this.zzD = i + 1;
            boolean z = i < 10;
            zzgu zzguVar4 = this.zzh;
            zzP(zzguVar4);
            String str = i < 10 ? "Retrying." : "Skipping.";
            zzgs zzj2 = zzguVar4.zzj();
            StringBuilder sb2 = new StringBuilder(str.length() + 60);
            sb2.append("Failed to retrieve DMA consent from the service, ");
            sb2.append(str);
            sb2.append(" retryCount");
            zzj2.zzb(sb2.toString(), Integer.valueOf(this.zzD));
            return z;
        }
        zzjl zze = zzjl.zze(bundle, 100);
        sb.append("&gcs=");
        sb.append(zze.zzk());
        zzaz zzh = zzaz.zzh(bundle, 100);
        sb.append("&dma=");
        sb.append(!Objects.equals(zzh.zzj(), false) ? 1 : 0);
        if (!TextUtils.isEmpty(zzh.zzk())) {
            sb.append("&dma_cps=");
            sb.append(zzh.zzk());
        }
        int i2 = !Objects.equals(zzaz.zzi(bundle), true) ? 1 : 0;
        sb.append("&npa=");
        sb.append(i2);
        zzgu zzguVar5 = this.zzh;
        zzP(zzguVar5);
        zzguVar5.zzk().zzb("Consent query parameters to Bow", sb);
        zzpp zzppVar2 = this.zzk;
        zzN(zzppVar2);
        zzv().zzu.zzf.zzi();
        String str2 = (String) zzb2.first;
        zzhh zzhhVar2 = this.zzg;
        zzN(zzhhVar2);
        URL zzat = zzppVar2.zzat(133005L, zzj, str2, (-1) + zzhhVar2.zzp.zza(), sb.toString());
        if (zzat != null) {
            zzlo zzloVar2 = this.zzq;
            zzP(zzloVar2);
            zzll zzllVar = new zzll() { // from class: com.google.android.gms.measurement.internal.zzib
                @Override // com.google.android.gms.measurement.internal.zzll
                public final /* synthetic */ void zza(String str3, int i3, Throwable th, byte[] bArr, Map map) {
                    zzic.this.zzJ(str3, i3, th, bArr, map);
                }
            };
            zzloVar2.zzw();
            Preconditions.checkNotNull(zzat);
            Preconditions.checkNotNull(zzllVar);
            zzhz zzhzVar2 = zzloVar2.zzu.zzi;
            zzP(zzhzVar2);
            zzhzVar2.zzm(new zzln(zzloVar2, zzj, zzat, null, null, zzllVar));
        }
        return false;
    }

    final /* synthetic */ void zzJ(String str, int i, Throwable th, byte[] bArr, Map map) {
        int i2;
        if (i == 200 || i == 204) {
            i2 = i;
        } else {
            i2 = 304;
            if (i != 304) {
                i2 = i;
                zzgu zzguVar = this.zzh;
                zzP(zzguVar);
                zzguVar.zze().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i2), th);
            }
        }
        if (th == null) {
            zzhh zzhhVar = this.zzg;
            zzN(zzhhVar);
            zzhhVar.zzo.zzb(true);
            if (bArr == null || bArr.length == 0) {
                zzgu zzguVar2 = this.zzh;
                zzP(zzguVar2);
                zzguVar2.zzj().zza("Deferred Deep Link response empty.");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr));
                String optString = jSONObject.optString("deeplink", "");
                if (TextUtils.isEmpty(optString)) {
                    zzgu zzguVar3 = this.zzh;
                    zzP(zzguVar3);
                    zzguVar3.zzj().zza("Deferred Deep Link is empty.");
                    return;
                }
                String optString2 = jSONObject.optString("gclid", "");
                String optString3 = jSONObject.optString("gbraid", "");
                String optString4 = jSONObject.optString("gad_source", "");
                double optDouble = jSONObject.optDouble("timestamp", Utils.DOUBLE_EPSILON);
                Bundle bundle = new Bundle();
                zzpp zzppVar = this.zzk;
                zzN(zzppVar);
                zzic zzicVar = zzppVar.zzu;
                if (!TextUtils.isEmpty(optString)) {
                    Context context = zzicVar.zzc;
                    List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0);
                    if (queryIntentActivities != null && !queryIntentActivities.isEmpty()) {
                        if (!TextUtils.isEmpty(optString3)) {
                            bundle.putString("gbraid", optString3);
                        }
                        if (!TextUtils.isEmpty(optString4)) {
                            bundle.putString("gad_source", optString4);
                        }
                        bundle.putString("gclid", optString2);
                        bundle.putString("_cis", "ddp");
                        this.zzo.zzF(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
                        zzN(zzppVar);
                        if (TextUtils.isEmpty(optString)) {
                            return;
                        }
                        try {
                            SharedPreferences.Editor edit = context.getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
                            edit.putString("deeplink", optString);
                            edit.putLong("timestamp", Double.doubleToRawLongBits(optDouble));
                            if (edit.commit()) {
                                Intent intent = new Intent("android.google.analytics.action.DEEPLINK_ACTION");
                                Context context2 = zzppVar.zzu.zzc;
                                if (Build.VERSION.SDK_INT < 34) {
                                    context2.sendBroadcast(intent);
                                    return;
                                } else {
                                    context2.sendBroadcast(intent, null, BroadcastOptions.makeBasic().setShareIdentityEnabled(true).toBundle());
                                    return;
                                }
                            }
                            return;
                        } catch (RuntimeException e) {
                            zzgu zzguVar4 = zzppVar.zzu.zzh;
                            zzP(zzguVar4);
                            zzguVar4.zzb().zzb("Failed to persist Deferred Deep Link. exception", e);
                            return;
                        }
                    }
                }
                zzgu zzguVar5 = this.zzh;
                zzP(zzguVar5);
                zzguVar5.zze().zzd("Deferred Deep Link validation failed. gclid, gbraid, deep link", optString2, optString3, optString);
                return;
            } catch (JSONException e2) {
                zzgu zzguVar6 = this.zzh;
                zzP(zzguVar6);
                zzguVar6.zzb().zzb("Failed to parse the Deferred Deep Link response. exception", e2);
                return;
            }
        }
        zzgu zzguVar7 = this.zzh;
        zzP(zzguVar7);
        zzguVar7.zze().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i2), th);
    }

    final /* synthetic */ void zzK(zzjs zzjsVar) {
        zzhz zzhzVar = this.zzi;
        zzP(zzhzVar);
        zzhzVar.zzg();
        zzal zzalVar = this.zzf;
        zzalVar.zzb();
        zzba zzbaVar = new zzba(this);
        zzbaVar.zzx();
        this.zzu = zzbaVar;
        com.google.android.gms.internal.measurement.zzdd zzddVar = zzjsVar.zzd;
        zzgi zzgiVar = new zzgi(this, zzjsVar.zzc, zzddVar == null ? 0L : zzddVar.zza);
        zzgiVar.zzc();
        this.zzv = zzgiVar;
        zzgl zzglVar = new zzgl(this);
        zzglVar.zzc();
        this.zzs = zzglVar;
        zznl zznlVar = new zznl(this);
        zznlVar.zzc();
        this.zzt = zznlVar;
        zzpp zzppVar = this.zzk;
        zzppVar.zzy();
        this.zzg.zzy();
        this.zzv.zzd();
        zzlq zzlqVar = new zzlq(this);
        zzlqVar.zzc();
        this.zzw = zzlqVar;
        this.zzw.zzd();
        zzgu zzguVar = this.zzh;
        zzP(zzguVar);
        zzgs zzi = zzguVar.zzi();
        zzalVar.zzi();
        zzi.zzb("App measurement initialized, version", 133005L);
        zzP(zzguVar);
        zzguVar.zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzj = zzgiVar.zzj();
        zzN(zzppVar);
        if (zzppVar.zzaa(zzj, zzalVar.zzz())) {
            zzP(zzguVar);
            zzguVar.zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
        } else {
            zzP(zzguVar);
            zzgs zzi2 = zzguVar.zzi();
            String.valueOf(zzj);
            zzi2.zza("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(String.valueOf(zzj)));
        }
        zzP(zzguVar);
        zzguVar.zzj().zza("Debug-level message logging enabled");
        int i = this.zzC;
        AtomicInteger atomicInteger = this.zzE;
        if (i != atomicInteger.get()) {
            zzP(zzguVar);
            zzguVar.zzb().zzc("Not all components initialized", Integer.valueOf(this.zzC), Integer.valueOf(atomicInteger.get()));
        }
        this.zzx = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0030, code lost:
    
        if (r1.zzS() == false) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:117:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0259  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void zza(com.google.android.gms.internal.measurement.zzdd zzddVar) {
        zzjl zza;
        zzlj zzljVar;
        zzji zzw;
        zzji zzw2;
        Bundle bundle;
        zzaz zzh;
        Boolean zzr;
        zzhe zzheVar;
        zzhz zzhzVar = this.zzi;
        zzP(zzhzVar);
        zzhzVar.zzg();
        com.google.android.gms.internal.measurement.zzin zzj = zzx().zzj();
        com.google.android.gms.internal.measurement.zzin zzinVar = com.google.android.gms.internal.measurement.zzin.CLIENT_UPLOAD_ELIGIBLE;
        zzqp.zza();
        zzfx zzfxVar = zzfy.zzaQ;
        zzal zzalVar = this.zzf;
        boolean zzp = zzalVar.zzp(null, zzfxVar);
        boolean z = zzj == zzinVar;
        if (zzp) {
            zzpp zzppVar = this.zzk;
            zzN(zzppVar);
        }
        if (z) {
            z = true;
            zzpp zzppVar2 = this.zzk;
            zzN(zzppVar2);
            zzppVar2.zzg();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.google.android.gms.measurement.TRIGGERS_AVAILABLE");
            intentFilter.addAction("com.google.android.gms.measurement.BATCHES_AVAILABLE");
            zzw zzwVar = new zzw(zzppVar2.zzu);
            zzic zzicVar = zzppVar2.zzu;
            ContextCompat.registerReceiver(zzicVar.zzc, zzwVar, intentFilter, 2);
            zzgu zzguVar = zzicVar.zzh;
            zzP(zzguVar);
            zzguVar.zzj().zza("Registered app receiver");
            if (z) {
                zzx().zzh(((Long) zzfy.zzB.zzb(null)).longValue());
            }
        }
        zzhh zzhhVar = this.zzg;
        zzN(zzhhVar);
        zzjl zzl = zzhhVar.zzl();
        int zzb2 = zzl.zzb();
        zzji zzw3 = zzalVar.zzw("google_analytics_default_allow_ad_storage", false);
        zzji zzw4 = zzalVar.zzw("google_analytics_default_allow_analytics_storage", false);
        zzji zzjiVar = zzji.UNINITIALIZED;
        if (zzw3 != zzjiVar || zzw4 != zzjiVar) {
            zzN(zzhhVar);
            if (zzhhVar.zzk(-10)) {
                zza = zzjl.zza(zzw3, zzw4, -10);
                if (zza != null) {
                    zzlj zzljVar2 = this.zzo;
                    zzO(zzljVar2);
                    zzljVar2.zzs(zza, true);
                    zzl = zza;
                }
                zzljVar = this.zzo;
                zzO(zzljVar);
                zzljVar.zzA(zzl);
                zzN(zzhhVar);
                int zzb3 = zzhhVar.zzj().zzb();
                zzw = zzalVar.zzw("google_analytics_default_allow_ad_personalization_signals", true);
                if (zzw != zzjiVar) {
                    zzgu zzguVar2 = this.zzh;
                    zzP(zzguVar2);
                    zzguVar2.zzk().zzb("Default ad personalization consent from Manifest", zzw);
                }
                zzw2 = zzalVar.zzw("google_analytics_default_allow_ad_user_data", true);
                if (zzw2 == zzjiVar && zzjl.zzu(-10, zzb3)) {
                    zzO(zzljVar);
                    zzljVar.zzq(zzaz.zza(zzw2, -10), true);
                } else if (TextUtils.isEmpty(zzv().zzk()) && (zzb3 == 0 || zzb3 == 30)) {
                    zzO(zzljVar);
                    zzljVar.zzq(new zzaz((Boolean) null, -10, (Boolean) null, (String) null), true);
                } else if (TextUtils.isEmpty(zzv().zzk()) && zzddVar != null && (bundle = zzddVar.zzd) != null && zzjl.zzu(30, zzb3)) {
                    zzh = zzaz.zzh(bundle, 30);
                    if (zzh.zzd()) {
                        zzO(zzljVar);
                        zzljVar.zzq(zzh, true);
                    }
                }
                zzic zzicVar2 = zzalVar.zzu;
                zzr = zzalVar.zzr("google_analytics_tcf_data_enabled");
                if (zzr != null || zzr.booleanValue()) {
                    zzgu zzguVar3 = this.zzh;
                    zzP(zzguVar3);
                    zzguVar3.zzj().zza("TCF client enabled.");
                    zzO(zzljVar);
                    zzljVar.zzE();
                    zzO(zzljVar);
                    zzljVar.zzD();
                }
                zzN(zzhhVar);
                zzheVar = zzhhVar.zzc;
                if (zzheVar.zza() == 0) {
                    zzgu zzguVar4 = this.zzh;
                    zzP(zzguVar4);
                    long j = this.zza;
                    zzguVar4.zzk().zzb("Persisting first open", Long.valueOf(j));
                    zzN(zzhhVar);
                    zzheVar.zzb(j);
                }
                zzO(zzljVar);
                zzljVar.zzb.zzc();
                if (!zzH()) {
                    if (!TextUtils.isEmpty(zzv().zzk())) {
                        zzpp zzppVar3 = this.zzk;
                        zzN(zzppVar3);
                        String zzk = zzv().zzk();
                        zzN(zzhhVar);
                        zzhhVar.zzg();
                        if (zzppVar3.zzB(zzk, zzhhVar.zzd().getString("gmp_app_id", null))) {
                            zzgu zzguVar5 = this.zzh;
                            zzP(zzguVar5);
                            zzguVar5.zzi().zza("Rechecking which service to use due to a GMP App Id change");
                            zzN(zzhhVar);
                            zzhhVar.zzg();
                            Boolean zzi = zzhhVar.zzi();
                            SharedPreferences.Editor edit = zzhhVar.zzd().edit();
                            edit.clear();
                            edit.apply();
                            if (zzi != null) {
                                zzhhVar.zzh(zzi);
                            }
                            zzm().zzh();
                            this.zzt.zzM();
                            this.zzt.zzI();
                            zzN(zzhhVar);
                            zzheVar.zzb(this.zza);
                            zzN(zzhhVar);
                            zzhhVar.zze.zzb(null);
                        }
                        zzN(zzhhVar);
                        String zzk2 = zzv().zzk();
                        zzhhVar.zzg();
                        SharedPreferences.Editor edit2 = zzhhVar.zzd().edit();
                        edit2.putString("gmp_app_id", zzk2);
                        edit2.apply();
                    }
                    zzN(zzhhVar);
                    if (!zzhhVar.zzl().zzo(zzjk.ANALYTICS_STORAGE)) {
                        zzN(zzhhVar);
                        zzhhVar.zze.zzb(null);
                    }
                    zzO(zzljVar);
                    zzN(zzhhVar);
                    zzljVar.zzR(zzhhVar.zze.zza());
                    zzpp zzppVar4 = this.zzk;
                    zzN(zzppVar4);
                    try {
                        zzppVar4.zzu.zzc.getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
                    } catch (ClassNotFoundException e) {
                        zzhh zzhhVar2 = this.zzg;
                        zzN(zzhhVar2);
                        zzhg zzhgVar = zzhhVar2.zzq;
                        if (!TextUtils.isEmpty(zzhgVar.zza())) {
                            zzgu zzguVar6 = this.zzh;
                            zzP(zzguVar6);
                            zzguVar6.zze().zza("Remote config removed with active feature rollouts");
                            zzN(zzhhVar2);
                            zzhgVar.zzb(null);
                        }
                    }
                    if (!TextUtils.isEmpty(zzv().zzk())) {
                        boolean zzB = zzB();
                        zzhh zzhhVar3 = this.zzg;
                        zzN(zzhhVar3);
                        if (!zzhhVar3.zzo() && !this.zzf.zzt()) {
                            zzN(zzhhVar3);
                            zzhhVar3.zzn(!zzB);
                        }
                        if (zzB) {
                            zzlj zzljVar3 = this.zzo;
                            zzO(zzljVar3);
                            zzljVar3.zzU();
                        }
                        zzoc zzocVar = this.zzj;
                        zzO(zzocVar);
                        zzocVar.zza.zza();
                        zzt().zzC(new AtomicReference());
                        zznl zzt = zzt();
                        zzN(zzhhVar3);
                        zzt.zzH(zzhhVar3.zzt.zza());
                    }
                } else if (zzB()) {
                    zzpp zzppVar5 = this.zzk;
                    zzN(zzppVar5);
                    if (!zzppVar5.zzY("android.permission.INTERNET")) {
                        zzgu zzguVar7 = this.zzh;
                        zzP(zzguVar7);
                        zzguVar7.zzb().zza("App is missing INTERNET permission");
                    }
                    zzN(zzppVar5);
                    if (!zzppVar5.zzY("android.permission.ACCESS_NETWORK_STATE")) {
                        zzgu zzguVar8 = this.zzh;
                        zzP(zzguVar8);
                        zzguVar8.zzb().zza("App is missing ACCESS_NETWORK_STATE permission");
                    }
                    Context context = this.zzc;
                    if (!Wrappers.packageManager(context).isCallerInstantApp() && !this.zzf.zzE()) {
                        if (!zzpp.zzau(context)) {
                            zzgu zzguVar9 = this.zzh;
                            zzP(zzguVar9);
                            zzguVar9.zzb().zza("AppMeasurementReceiver not registered/enabled");
                        }
                        if (!zzpp.zzQ(context, false)) {
                            zzgu zzguVar10 = this.zzh;
                            zzP(zzguVar10);
                            zzguVar10.zzb().zza("AppMeasurementService not registered/enabled");
                        }
                    }
                    zzgu zzguVar11 = this.zzh;
                    zzP(zzguVar11);
                    zzguVar11.zzb().zza("Uploading is not possible. App measurement disabled");
                }
                zzqp.zza();
                if (this.zzf.zzp(null, zzfy.zzaQ)) {
                    zzpp zzppVar6 = this.zzk;
                    zzN(zzppVar6);
                    if (zzppVar6.zzS()) {
                        long max = Math.max(500L, ((((Integer) zzfy.zzax.zzb(null)).intValue() * 1000) + new Random().nextInt(5000)) - this.zzm.elapsedRealtime());
                        if (max > 500) {
                            zzgu zzguVar12 = this.zzh;
                            zzP(zzguVar12);
                            zzguVar12.zzk().zzb("Waiting to fetch trigger URIs until some time after boot. Delay in millis", Long.valueOf(max));
                        }
                        zzlj zzljVar4 = this.zzo;
                        zzO(zzljVar4);
                        zzljVar4.zzu(max);
                    }
                }
                zzhh zzhhVar4 = this.zzg;
                zzN(zzhhVar4);
                zzhhVar4.zzj.zzb(true);
            }
        }
        if (!TextUtils.isEmpty(zzv().zzk()) && (zzb2 == 0 || zzb2 == 30 || zzb2 == 10 || zzb2 == 40)) {
            zzlj zzljVar5 = this.zzo;
            zzO(zzljVar5);
            zzljVar5.zzs(new zzjl(null, null, -10), false);
        }
        zza = null;
        if (zza != null) {
        }
        zzljVar = this.zzo;
        zzO(zzljVar);
        zzljVar.zzA(zzl);
        zzN(zzhhVar);
        int zzb32 = zzhhVar.zzj().zzb();
        zzw = zzalVar.zzw("google_analytics_default_allow_ad_personalization_signals", true);
        if (zzw != zzjiVar) {
        }
        zzw2 = zzalVar.zzw("google_analytics_default_allow_ad_user_data", true);
        if (zzw2 == zzjiVar) {
        }
        if (TextUtils.isEmpty(zzv().zzk())) {
        }
        if (TextUtils.isEmpty(zzv().zzk())) {
            zzh = zzaz.zzh(bundle, 30);
            if (zzh.zzd()) {
            }
        }
        zzic zzicVar22 = zzalVar.zzu;
        zzr = zzalVar.zzr("google_analytics_tcf_data_enabled");
        if (zzr != null) {
        }
        zzgu zzguVar32 = this.zzh;
        zzP(zzguVar32);
        zzguVar32.zzj().zza("TCF client enabled.");
        zzO(zzljVar);
        zzljVar.zzE();
        zzO(zzljVar);
        zzljVar.zzD();
        zzN(zzhhVar);
        zzheVar = zzhhVar.zzc;
        if (zzheVar.zza() == 0) {
        }
        zzO(zzljVar);
        zzljVar.zzb.zzc();
        if (!zzH()) {
        }
        zzqp.zza();
        if (this.zzf.zzp(null, zzfy.zzaQ)) {
        }
        zzhh zzhhVar42 = this.zzg;
        zzN(zzhhVar42);
        zzhhVar42.zzj.zzb(true);
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final zzae zzaU() {
        return this.zze;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final zzgu zzaV() {
        zzgu zzguVar = this.zzh;
        zzP(zzguVar);
        return zzguVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final zzhz zzaW() {
        zzhz zzhzVar = this.zzi;
        zzP(zzhzVar);
        return zzhzVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final Context zzaY() {
        return this.zzc;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    @Pure
    public final Clock zzaZ() {
        return this.zzm;
    }

    @Pure
    public final zzal zzc() {
        return this.zzf;
    }

    @Pure
    public final zzhh zzd() {
        zzhh zzhhVar = this.zzg;
        zzN(zzhhVar);
        return zzhhVar;
    }

    public final zzgu zzf() {
        zzgu zzguVar = this.zzh;
        if (zzguVar == null || !zzguVar.zzv()) {
            return null;
        }
        return zzguVar;
    }

    @Pure
    public final zzoc zzh() {
        zzoc zzocVar = this.zzj;
        zzO(zzocVar);
        return zzocVar;
    }

    @SideEffectFree
    final zzhz zzi() {
        return this.zzi;
    }

    @Pure
    public final zzlj zzj() {
        zzlj zzljVar = this.zzo;
        zzO(zzljVar);
        return zzljVar;
    }

    @Pure
    public final zzpp zzk() {
        zzpp zzppVar = this.zzk;
        zzN(zzppVar);
        return zzppVar;
    }

    @Pure
    public final zzgn zzl() {
        return this.zzl;
    }

    @Pure
    public final zzgl zzm() {
        zzO(this.zzs);
        return this.zzs;
    }

    @Pure
    public final zzlo zzn() {
        zzlo zzloVar = this.zzq;
        zzP(zzloVar);
        return zzloVar;
    }

    @Pure
    public final boolean zzp() {
        return this.zzd;
    }

    @Pure
    public final String zzq() {
        return this.zzr;
    }

    @Pure
    public final zzmb zzs() {
        zzmb zzmbVar = this.zzn;
        zzO(zzmbVar);
        return zzmbVar;
    }

    @Pure
    public final zznl zzt() {
        zzO(this.zzt);
        return this.zzt;
    }

    @Pure
    public final zzba zzu() {
        zzP(this.zzu);
        return this.zzu;
    }

    @Pure
    public final zzgi zzv() {
        zzO(this.zzv);
        return this.zzv;
    }

    @Pure
    public final zzd zzw() {
        zzd zzdVar = this.zzp;
        zzM(zzdVar);
        return zzdVar;
    }

    @Pure
    public final zzlq zzx() {
        zzM(this.zzw);
        return this.zzw;
    }

    final void zzz(boolean z) {
        this.zzA = Boolean.valueOf(z);
    }
}
