package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import androidx.work.WorkRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.measurement.zzqp;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import kotlinx.coroutines.DebugKt;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzlj extends zzg {
    protected zzky zza;
    final zzx zzb;
    protected boolean zzc;
    private zzjp zzd;
    private final Set zze;
    private boolean zzf;
    private final AtomicReference zzg;
    private final Object zzh;
    private boolean zzi;
    private int zzj;
    private zzay zzk;
    private zzay zzl;
    private PriorityQueue zzm;
    private boolean zzn;
    private zzjl zzo;
    private final AtomicLong zzp;
    private long zzq;
    private zzay zzr;
    private SharedPreferences.OnSharedPreferenceChangeListener zzs;
    private zzay zzt;
    private final zzpo zzv;

    protected zzlj(zzic zzicVar) {
        super(zzicVar);
        this.zze = new CopyOnWriteArraySet();
        this.zzh = new Object();
        this.zzi = false;
        this.zzj = 1;
        this.zzc = true;
        this.zzv = new zzkn(this);
        this.zzg = new AtomicReference();
        this.zzo = zzjl.zza;
        this.zzq = -1L;
        this.zzp = new AtomicLong(0L);
        this.zzb = new zzx(zzicVar);
    }

    private final zzlr zzar(final zzom zzomVar) {
        try {
            URL url = new URI(zzomVar.zzc).toURL();
            final AtomicReference atomicReference = new AtomicReference();
            String zzl = this.zzu.zzv().zzl();
            zzic zzicVar = this.zzu;
            zzgs zzk = zzicVar.zzaV().zzk();
            Long valueOf = Long.valueOf(zzomVar.zza);
            zzk.zzd("[sgtm] Uploading data from app. row_id, url, uncompressed size", valueOf, zzomVar.zzc, Integer.valueOf(zzomVar.zzb.length));
            if (!TextUtils.isEmpty(zzomVar.zzg)) {
                zzicVar.zzaV().zzk().zzc("[sgtm] Uploading data from app. row_id", valueOf, zzomVar.zzg);
            }
            HashMap hashMap = new HashMap();
            Bundle bundle = zzomVar.zzd;
            for (String str : bundle.keySet()) {
                String string = bundle.getString(str);
                if (!TextUtils.isEmpty(string)) {
                    hashMap.put(str, string);
                }
            }
            zzlo zzn = zzicVar.zzn();
            byte[] bArr = zzomVar.zzb;
            zzll zzllVar = new zzll() { // from class: com.google.android.gms.measurement.internal.zzkz
                /* JADX WARN: Removed duplicated region for block: B:10:0x0064  */
                /* JADX WARN: Removed duplicated region for block: B:13:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:22:0x0067  */
                @Override // com.google.android.gms.measurement.internal.zzll
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final /* synthetic */ void zza(String str2, int i, Throwable th, byte[] bArr2, Map map) {
                    zzlr zzlrVar;
                    AtomicReference atomicReference2;
                    zzlj zzljVar = zzlj.this;
                    zzljVar.zzg();
                    zzom zzomVar2 = zzomVar;
                    if (i != 200 && i != 204) {
                        if (i == 304) {
                            i = 304;
                        }
                        zzljVar.zzu.zzaV().zze().zzd("[sgtm] Upload failed for row_id. response, exception", Long.valueOf(zzomVar2.zza), Integer.valueOf(i), th);
                        zzlrVar = !Arrays.asList(((String) zzfy.zzt.zzb(null)).split(",")).contains(String.valueOf(i)) ? zzlr.BACKOFF : zzlr.FAILURE;
                        atomicReference2 = atomicReference;
                        zznl zzt = zzljVar.zzu.zzt();
                        long j = zzomVar2.zza;
                        zzt.zzy(new zzaf(j, zzlrVar.zza(), zzomVar2.zzf));
                        zzljVar.zzu.zzaV().zzk().zzc("[sgtm] Updated status for row_id", Long.valueOf(j), zzlrVar);
                        synchronized (atomicReference2) {
                            atomicReference2.set(zzlrVar);
                            atomicReference2.notifyAll();
                        }
                        return;
                    }
                    if (th == null) {
                        zzljVar.zzu.zzaV().zzk().zzb("[sgtm] Upload succeeded for row_id", Long.valueOf(zzomVar2.zza));
                        zzlrVar = zzlr.SUCCESS;
                        atomicReference2 = atomicReference;
                        zznl zzt2 = zzljVar.zzu.zzt();
                        long j2 = zzomVar2.zza;
                        zzt2.zzy(new zzaf(j2, zzlrVar.zza(), zzomVar2.zzf));
                        zzljVar.zzu.zzaV().zzk().zzc("[sgtm] Updated status for row_id", Long.valueOf(j2), zzlrVar);
                        synchronized (atomicReference2) {
                        }
                    }
                    zzljVar.zzu.zzaV().zze().zzd("[sgtm] Upload failed for row_id. response, exception", Long.valueOf(zzomVar2.zza), Integer.valueOf(i), th);
                    if (!Arrays.asList(((String) zzfy.zzt.zzb(null)).split(",")).contains(String.valueOf(i))) {
                    }
                    atomicReference2 = atomicReference;
                    zznl zzt22 = zzljVar.zzu.zzt();
                    long j22 = zzomVar2.zza;
                    zzt22.zzy(new zzaf(j22, zzlrVar.zza(), zzomVar2.zzf));
                    zzljVar.zzu.zzaV().zzk().zzc("[sgtm] Updated status for row_id", Long.valueOf(j22), zzlrVar);
                    synchronized (atomicReference2) {
                    }
                }
            };
            zzn.zzw();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(bArr);
            Preconditions.checkNotNull(zzllVar);
            zzn.zzu.zzaW().zzm(new zzln(zzn, zzl, url, bArr, hashMap, zzllVar));
            try {
                zzic zzicVar2 = zzicVar.zzk().zzu;
                long currentTimeMillis = zzicVar2.zzaZ().currentTimeMillis();
                long j = currentTimeMillis + DateUtils.MILLIS_PER_MINUTE;
                synchronized (atomicReference) {
                    for (long j2 = DateUtils.MILLIS_PER_MINUTE; atomicReference.get() == null && j2 > 0; j2 = j - zzicVar2.zzaZ().currentTimeMillis()) {
                        atomicReference.wait(j2);
                    }
                }
            } catch (InterruptedException e) {
                this.zzu.zzaV().zze().zza("[sgtm] Interrupted waiting for uploading batch");
            }
            return atomicReference.get() == null ? zzlr.UNKNOWN : (zzlr) atomicReference.get();
        } catch (MalformedURLException | URISyntaxException e2) {
            this.zzu.zzaV().zzb().zzd("[sgtm] Bad upload url for row_id", zzomVar.zzc, Long.valueOf(zzomVar.zza), e2);
            return zzlr.FAILURE;
        }
    }

    private final void zzas(Boolean bool, boolean z) {
        zzg();
        zzb();
        zzic zzicVar = this.zzu;
        zzicVar.zzaV().zzj().zzb("Setting app measurement enabled (FE)", bool);
        zzicVar.zzd().zzh(bool);
        if (z) {
            zzhh zzd = zzicVar.zzd();
            zzic zzicVar2 = zzd.zzu;
            zzd.zzg();
            SharedPreferences.Editor edit = zzd.zzd().edit();
            if (bool != null) {
                edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
            } else {
                edit.remove("measurement_enabled_from_api");
            }
            edit.apply();
        }
        if (this.zzu.zzE() || !(bool == null || bool.booleanValue())) {
            zzal();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzat, reason: merged with bridge method [inline-methods] */
    public final void zzal() {
        zzg();
        zzic zzicVar = this.zzu;
        String zza = zzicVar.zzd().zzh.zza();
        if (zza != null) {
            if ("unset".equals(zza)) {
                zzN("app", "_npa", null, zzicVar.zzaZ().currentTimeMillis());
            } else {
                zzN("app", "_npa", Long.valueOf(true != BooleanUtils.TRUE.equals(zza) ? 0L : 1L), zzicVar.zzaZ().currentTimeMillis());
            }
        }
        if (!this.zzu.zzB() || !this.zzc) {
            zzicVar.zzaV().zzj().zza("Updating Scion state (FE)");
            this.zzu.zzt().zzi();
        } else {
            zzicVar.zzaV().zzj().zza("Recording app launch after enabling measurement for the first time (FE)");
            zzU();
            this.zzu.zzh().zza.zza();
            zzicVar.zzaW().zzj(new zzjz(this));
        }
    }

    final void zzA(zzjl zzjlVar) {
        zzg();
        boolean z = (zzjlVar.zzo(zzjk.ANALYTICS_STORAGE) && zzjlVar.zzo(zzjk.AD_STORAGE)) ? true : this.zzu.zzt().zzO();
        zzic zzicVar = this.zzu;
        if (z != zzicVar.zzE()) {
            zzicVar.zzD(z);
            zzhh zzd = this.zzu.zzd();
            zzic zzicVar2 = zzd.zzu;
            zzd.zzg();
            Boolean valueOf = zzd.zzd().contains("measurement_enabled_from_api") ? Boolean.valueOf(zzd.zzd().getBoolean("measurement_enabled_from_api", true)) : null;
            if (!z || valueOf == null || valueOf.booleanValue()) {
                zzas(Boolean.valueOf(z), false);
            }
        }
    }

    public final void zzB(String str, String str2, Bundle bundle) {
        zzC(str, str2, bundle, true, true, this.zzu.zzaZ().currentTimeMillis());
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzC(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        boolean z3;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        if (Objects.equals(str2, FirebaseAnalytics.Event.SCREEN_VIEW)) {
            this.zzu.zzs().zzj(bundle2, j);
            return;
        }
        boolean z4 = true;
        if (z2 && this.zzd != null) {
            if (zzpp.zzZ(str2)) {
                z3 = true;
                zzJ(str != null ? "app" : str, str2, j, bundle2, z2, z3, z, null);
            }
            z4 = false;
        }
        z3 = z4;
        zzJ(str != null ? "app" : str, str2, j, bundle2, z2, z3, z, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzD() {
        zzod zzodVar;
        zzod zzodVar2;
        com.google.android.gms.internal.measurement.zzkq zzkqVar;
        zzg();
        zzic zzicVar = this.zzu;
        zzicVar.zzaV().zzj().zza("Handle tcf update.");
        SharedPreferences zze = zzicVar.zzd().zze();
        HashMap hashMap = new HashMap();
        zzfx zzfxVar = zzfy.zzaZ;
        if (((Boolean) zzfxVar.zzb(null)).booleanValue()) {
            int i = zzof.zzb;
            com.google.android.gms.internal.measurement.zzkp zzkpVar = com.google.android.gms.internal.measurement.zzkp.IAB_TCF_PURPOSE_STORE_AND_ACCESS_INFORMATION_ON_A_DEVICE;
            zzoe zzoeVar = zzoe.CONSENT;
            com.google.android.gms.internal.measurement.zzkp zzkpVar2 = com.google.android.gms.internal.measurement.zzkp.IAB_TCF_PURPOSE_SELECT_BASIC_ADS;
            zzoe zzoeVar2 = zzoe.FLEXIBLE_LEGITIMATE_INTEREST;
            ImmutableMap ofEntries = ImmutableMap.ofEntries(zzlj$$ExternalSyntheticBackport0.m(zzkpVar, zzoeVar), zzlj$$ExternalSyntheticBackport0.m(zzkpVar2, zzoeVar2), zzlj$$ExternalSyntheticBackport0.m(com.google.android.gms.internal.measurement.zzkp.IAB_TCF_PURPOSE_CREATE_A_PERSONALISED_ADS_PROFILE, zzoeVar), zzlj$$ExternalSyntheticBackport0.m(com.google.android.gms.internal.measurement.zzkp.IAB_TCF_PURPOSE_SELECT_PERSONALISED_ADS, zzoeVar), zzlj$$ExternalSyntheticBackport0.m(com.google.android.gms.internal.measurement.zzkp.IAB_TCF_PURPOSE_MEASURE_AD_PERFORMANCE, zzoeVar2), zzlj$$ExternalSyntheticBackport0.m(com.google.android.gms.internal.measurement.zzkp.IAB_TCF_PURPOSE_APPLY_MARKET_RESEARCH_TO_GENERATE_AUDIENCE_INSIGHTS, zzoeVar2), zzlj$$ExternalSyntheticBackport0.m(com.google.android.gms.internal.measurement.zzkp.IAB_TCF_PURPOSE_DEVELOP_AND_IMPROVE_PRODUCTS, zzoeVar2));
            ImmutableSet of = ImmutableSet.of("CH");
            char[] cArr = new char[5];
            boolean contains = zze.contains("IABTCF_TCString");
            int zzb = zzof.zzb(zze, "IABTCF_CmpSdkID");
            int zzb2 = zzof.zzb(zze, "IABTCF_PolicyVersion");
            int zzb3 = zzof.zzb(zze, "IABTCF_gdprApplies");
            int zzb4 = zzof.zzb(zze, "IABTCF_PurposeOneTreatment");
            int zzb5 = zzof.zzb(zze, "IABTCF_EnableAdvertiserConsentMode");
            String zza = zzof.zza(zze, "IABTCF_PublisherCC");
            ImmutableMap.Builder builder = ImmutableMap.builder();
            UnmodifiableIterator it = ofEntries.keySet().iterator();
            while (it.hasNext()) {
                com.google.android.gms.internal.measurement.zzkp zzkpVar3 = (com.google.android.gms.internal.measurement.zzkp) it.next();
                int zza2 = zzkpVar3.zza();
                StringBuilder sb = new StringBuilder(String.valueOf(zza2).length() + 28);
                sb.append("IABTCF_PublisherRestrictions");
                sb.append(zza2);
                String zza3 = zzof.zza(zze, sb.toString());
                if (TextUtils.isEmpty(zza3) || zza3.length() < 755) {
                    zzkqVar = com.google.android.gms.internal.measurement.zzkq.PURPOSE_RESTRICTION_UNDEFINED;
                } else {
                    int digit = Character.digit(zza3.charAt(754), 10);
                    if (digit >= 0 && digit <= com.google.android.gms.internal.measurement.zzkq.values().length) {
                        switch (digit) {
                            case 0:
                                break;
                            case 1:
                                zzkqVar = com.google.android.gms.internal.measurement.zzkq.PURPOSE_RESTRICTION_REQUIRE_CONSENT;
                                break;
                            case 2:
                                zzkqVar = com.google.android.gms.internal.measurement.zzkq.PURPOSE_RESTRICTION_REQUIRE_LEGITIMATE_INTEREST;
                                break;
                            default:
                                zzkqVar = com.google.android.gms.internal.measurement.zzkq.PURPOSE_RESTRICTION_UNDEFINED;
                                break;
                        }
                    }
                    zzkqVar = com.google.android.gms.internal.measurement.zzkq.PURPOSE_RESTRICTION_NOT_ALLOWED;
                }
                builder.put(zzkpVar3, zzkqVar);
            }
            ImmutableMap buildOrThrow = builder.buildOrThrow();
            String zza4 = zzof.zza(zze, "IABTCF_PurposeConsents");
            String zza5 = zzof.zza(zze, "IABTCF_VendorConsents");
            boolean z = !TextUtils.isEmpty(zza5) && zza5.length() >= 755 && zza5.charAt(754) == '1';
            String zza6 = zzof.zza(zze, "IABTCF_PurposeLegitimateInterests");
            String zza7 = zzof.zza(zze, "IABTCF_VendorLegitimateInterests");
            boolean z2 = !TextUtils.isEmpty(zza7) && zza7.length() >= 755 && zza7.charAt(754) == '1';
            cArr[0] = '2';
            zzodVar = new zzod(zzof.zzd(ofEntries, buildOrThrow, of, cArr, zzb, zzb5, zzb3, zzb2, zzb4, zza, zza4, zza6, z, z2, contains));
        } else {
            String zza8 = zzof.zza(zze, "IABTCF_VendorConsents");
            if (!"".equals(zza8) && zza8.length() > 754) {
                hashMap.put("GoogleConsent", String.valueOf(zza8.charAt(754)));
            }
            int zzb6 = zzof.zzb(zze, "IABTCF_gdprApplies");
            if (zzb6 != -1) {
                hashMap.put("gdprApplies", String.valueOf(zzb6));
            }
            int zzb7 = zzof.zzb(zze, "IABTCF_EnableAdvertiserConsentMode");
            if (zzb7 != -1) {
                hashMap.put("EnableAdvertiserConsentMode", String.valueOf(zzb7));
            }
            int zzb8 = zzof.zzb(zze, "IABTCF_PolicyVersion");
            if (zzb8 != -1) {
                hashMap.put("PolicyVersion", String.valueOf(zzb8));
            }
            String zza9 = zzof.zza(zze, "IABTCF_PurposeConsents");
            if (!"".equals(zza9)) {
                hashMap.put("PurposeConsents", zza9);
            }
            int zzb9 = zzof.zzb(zze, "IABTCF_CmpSdkID");
            if (zzb9 != -1) {
                hashMap.put("CmpSdkID", String.valueOf(zzb9));
            }
            zzodVar = new zzod(hashMap);
        }
        zzicVar.zzaV().zzk().zzb("Tcf preferences read", zzodVar);
        if (!zzicVar.zzc().zzp(null, zzfxVar)) {
            if (zzicVar.zzd().zzm(zzodVar)) {
                Bundle zzb10 = zzodVar.zzb();
                zzicVar.zzaV().zzk().zzb("Consent generated from Tcf", zzb10);
                if (zzb10 != Bundle.EMPTY) {
                    zzp(zzb10, -30, zzicVar.zzaZ().currentTimeMillis());
                }
                Bundle bundle = new Bundle();
                bundle.putString("_tcfd", zzodVar.zze());
                zzF(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_tcf", bundle);
                return;
            }
            return;
        }
        zzhh zzd = zzicVar.zzd();
        zzd.zzg();
        String string = zzd.zzd().getString("stored_tcf_param", "");
        HashMap hashMap2 = new HashMap();
        if (TextUtils.isEmpty(string)) {
            zzodVar2 = new zzod(hashMap2);
        } else {
            for (String str : string.split(";")) {
                String[] split = str.split("=");
                if (split.length >= 2 && zzof.zza.contains(split[0])) {
                    hashMap2.put(split[0], split[1]);
                }
            }
            zzodVar2 = new zzod(hashMap2);
        }
        if (zzicVar.zzd().zzm(zzodVar)) {
            Bundle zzb11 = zzodVar.zzb();
            zzicVar.zzaV().zzk().zzb("Consent generated from Tcf", zzb11);
            if (zzb11 != Bundle.EMPTY) {
                zzp(zzb11, -30, zzicVar.zzaZ().currentTimeMillis());
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("_tcfm", zzodVar.zzd(zzodVar2));
            bundle2.putString("_tcfd2", zzodVar.zzc());
            bundle2.putString("_tcfd", zzodVar.zze());
            zzF(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_tcf", bundle2);
        }
    }

    public final void zzE() {
        zzg();
        zzic zzicVar = this.zzu;
        zzicVar.zzaV().zzj().zza("Register tcfPrefChangeListener.");
        if (this.zzs == null) {
            this.zzt = new zzkb(this, this.zzu);
            this.zzs = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.gms.measurement.internal.zzle
                @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
                public final /* synthetic */ void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                    zzlj.this.zzag(sharedPreferences, str);
                }
            };
        }
        zzicVar.zzd().zze().registerOnSharedPreferenceChangeListener(this.zzs);
    }

    final void zzF(String str, String str2, Bundle bundle) {
        zzg();
        zzG(str, str2, this.zzu.zzaZ().currentTimeMillis(), bundle);
    }

    final void zzG(String str, String str2, long j, Bundle bundle) {
        zzg();
        zzH(str, str2, j, bundle, true, this.zzd != null ? zzpp.zzZ(str2) : true, true, null);
    }

    protected final void zzH(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        boolean z4;
        Bundle bundle2;
        String str4;
        long j2;
        Bundle[] bundleArr;
        int i;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzg();
        zzb();
        zzic zzicVar = this.zzu;
        if (!zzicVar.zzB()) {
            this.zzu.zzaV().zzj().zza("Event not sent since app measurement is disabled");
            return;
        }
        List zzp = this.zzu.zzv().zzp();
        if (zzp != null && !zzp.contains(str2)) {
            this.zzu.zzaV().zzj().zzc("Dropping non-safelisted event. event name, origin", str2, str);
            return;
        }
        if (!this.zzf) {
            this.zzf = true;
            try {
                try {
                    (!zzicVar.zzp() ? Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, this.zzu.zzaY().getClassLoader()) : Class.forName("com.google.android.gms.tagmanager.TagManagerService")).getDeclaredMethod("initialize", Context.class).invoke(null, this.zzu.zzaY());
                } catch (Exception e) {
                    this.zzu.zzaV().zze().zzb("Failed to invoke Tag Manager's initialize() method", e);
                }
            } catch (ClassNotFoundException e2) {
                this.zzu.zzaV().zzi().zza("Tag Manager is not found and thus will not be used");
            }
        }
        zzic zzicVar2 = this.zzu;
        if (!zzicVar2.zzc().zzp(null, zzfy.zzbf) && Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(str2) && bundle.containsKey("gclid")) {
            zzicVar2.zzaU();
            zzN(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_lgclid", bundle.getString("gclid"), zzicVar2.zzaZ().currentTimeMillis());
        }
        zzicVar2.zzaU();
        if (z && zzpp.zzaf(str2)) {
            zzicVar2.zzk().zzI(bundle, zzicVar2.zzd().zzt.zza());
        }
        if (!z3) {
            zzicVar2.zzaU();
            if (!"_iap".equals(str2)) {
                zzic zzicVar3 = this.zzu;
                zzpp zzk = zzicVar3.zzk();
                if (!zzk.zzj(NotificationCompat.CATEGORY_EVENT, str2)) {
                    i = 2;
                } else if (zzk.zzl(NotificationCompat.CATEGORY_EVENT, zzjm.zza, zzjm.zzb, str2)) {
                    zzk.zzu.zzc();
                    i = !zzk.zzm(NotificationCompat.CATEGORY_EVENT, 40, str2) ? 2 : 0;
                } else {
                    i = 13;
                }
                if (i != 0) {
                    zzicVar2.zzaV().zzd().zzb("Invalid public event name. Event will not be logged (FE)", zzicVar2.zzl().zza(str2));
                    zzpp zzk2 = zzicVar3.zzk();
                    zzicVar3.zzc();
                    zzicVar3.zzk().zzN(this.zzv, null, i, "_ev", zzk2.zzC(str2, 40, true), str2 != null ? str2.length() : 0);
                    return;
                }
            }
        }
        zzicVar2.zzaU();
        zzic zzicVar4 = this.zzu;
        zzlu zzh = zzicVar4.zzs().zzh(false);
        if (zzh != null && !bundle.containsKey("_sc")) {
            zzh.zzd = true;
        }
        zzpp.zzav(zzh, bundle, z && !z3);
        boolean equals = "am".equals(str);
        boolean zzZ = zzpp.zzZ(str2);
        if (!z || this.zzd == null || zzZ) {
            z4 = equals;
        } else {
            if (!equals) {
                zzicVar2.zzaV().zzj().zzc("Passing event to registered event handler (FE)", zzicVar2.zzl().zza(str2), zzicVar2.zzl().zze(bundle));
                Preconditions.checkNotNull(this.zzd);
                this.zzd.interceptEvent(str, str2, bundle, j);
                return;
            }
            z4 = true;
        }
        zzic zzicVar5 = this.zzu;
        if (zzicVar5.zzH()) {
            int zzn = zzicVar2.zzk().zzn(str2);
            if (zzn != 0) {
                zzicVar2.zzaV().zzd().zzb("Invalid event name. Event will not be logged (FE)", zzicVar2.zzl().zza(str2));
                zzpp zzk3 = zzicVar2.zzk();
                zzicVar2.zzc();
                zzicVar5.zzk().zzN(this.zzv, str3, zzn, "_ev", zzk3.zzC(str2, 40, true), str2 != null ? str2.length() : 0);
                return;
            }
            Bundle zzF = zzicVar2.zzk().zzF(str3, str2, bundle, CollectionUtils.listOf((Object[]) new String[]{"_o", "_sn", "_sc", "_si"}), z3);
            Preconditions.checkNotNull(zzF);
            zzicVar2.zzaU();
            if (zzicVar4.zzs().zzh(false) != null && "_ae".equals(str2)) {
                zzoa zzoaVar = zzicVar4.zzh().zzb;
                long elapsedRealtime = zzoaVar.zzc.zzu.zzaZ().elapsedRealtime();
                long j3 = elapsedRealtime - zzoaVar.zzb;
                zzoaVar.zzb = elapsedRealtime;
                if (j3 > 0) {
                    zzicVar2.zzk().zzak(zzF, j3);
                }
            }
            if (!DebugKt.DEBUG_PROPERTY_VALUE_AUTO.equals(str) && "_ssr".equals(str2)) {
                zzpp zzk4 = zzicVar2.zzk();
                String string = zzF.getString("_ffr");
                if (Strings.isEmptyOrWhitespace(string)) {
                    string = null;
                } else if (string != null) {
                    string = string.trim();
                }
                zzic zzicVar6 = zzk4.zzu;
                if (Objects.equals(string, zzicVar6.zzd().zzq.zza())) {
                    zzicVar6.zzaV().zzj().zza("Not logging duplicate session_start_with_rollout event");
                    return;
                }
                zzicVar6.zzd().zzq.zzb(string);
            } else if ("_ae".equals(str2)) {
                String zza = zzicVar2.zzk().zzu.zzd().zzq.zza();
                if (!TextUtils.isEmpty(zza)) {
                    zzF.putString("_ffr", zza);
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzF);
            boolean zzi = zzicVar2.zzc().zzp(null, zzfy.zzaU) ? zzicVar4.zzh().zzi() : zzicVar2.zzd().zzn.zza();
            if (zzicVar2.zzd().zzk.zza() <= 0) {
                bundle2 = zzF;
                str4 = "_ae";
                j2 = 0;
            } else if (zzicVar2.zzd().zzp(j) && zzi) {
                zzicVar2.zzaV().zzk().zza("Current session is expired, remove the session number, ID, and engagement time");
                j2 = 0;
                bundle2 = zzF;
                str4 = "_ae";
                zzN(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_sid", null, zzicVar2.zzaZ().currentTimeMillis());
                zzN(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_sno", null, zzicVar2.zzaZ().currentTimeMillis());
                zzN(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_se", null, zzicVar2.zzaZ().currentTimeMillis());
                zzicVar2.zzd().zzl.zzb(0L);
            } else {
                bundle2 = zzF;
                str4 = "_ae";
                j2 = 0;
            }
            if (bundle2.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, j2) == 1) {
                zzicVar2.zzaV().zzk().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                zzicVar5.zzh().zza.zzb(j, true);
            }
            ArrayList arrayList2 = new ArrayList(bundle2.keySet());
            Collections.sort(arrayList2);
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                String str5 = (String) arrayList2.get(i2);
                if (str5 != null) {
                    zzicVar2.zzk();
                    Object obj = bundle2.get(str5);
                    if (obj instanceof Bundle) {
                        bundleArr = new Bundle[]{(Bundle) obj};
                    } else if (obj instanceof Parcelable[]) {
                        Parcelable[] parcelableArr = (Parcelable[]) obj;
                        bundleArr = (Bundle[]) Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList3 = (ArrayList) obj;
                        bundleArr = (Bundle[]) arrayList3.toArray(new Bundle[arrayList3.size()]);
                    } else {
                        bundleArr = null;
                    }
                    if (bundleArr != null) {
                        bundle2.putParcelableArray(str5, bundleArr);
                    }
                }
            }
            int i3 = 0;
            while (i3 < arrayList.size()) {
                Bundle bundle3 = (Bundle) arrayList.get(i3);
                String str6 = i3 != 0 ? "_ep" : str2;
                bundle3.putString("_o", str);
                Bundle zzab = z2 ? zzicVar2.zzk().zzab(bundle3, null) : bundle3;
                Bundle bundle4 = zzab;
                zzicVar4.zzt().zzn(new zzbg(str6, new zzbe(zzab), str, j), str3);
                if (!z4) {
                    Iterator it = this.zze.iterator();
                    while (it.hasNext()) {
                        ((zzjq) it.next()).onEvent(str, str2, new Bundle(bundle4), j);
                    }
                }
                i3++;
            }
            zzicVar2.zzaU();
            if (zzicVar4.zzs().zzh(false) == null || !str4.equals(str2)) {
                return;
            }
            zzicVar4.zzh().zzb.zzd(true, true, zzicVar2.zzaZ().elapsedRealtime());
        }
    }

    public final void zzI(String str, String str2, Bundle bundle, String str3) {
        zzic.zzL();
        zzJ(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str2, this.zzu.zzaZ().currentTimeMillis(), bundle, false, true, true, str3);
    }

    protected final void zzJ(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        int i = zzpp.zza;
        Bundle bundle2 = new Bundle(bundle);
        for (String str4 : bundle2.keySet()) {
            Object obj = bundle2.get(str4);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str4, new Bundle((Bundle) obj));
            } else {
                int i2 = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i2 < parcelableArr.length) {
                        Parcelable parcelable = parcelableArr[i2];
                        if (parcelable instanceof Bundle) {
                            parcelableArr[i2] = new Bundle((Bundle) parcelable);
                        }
                        i2++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i2 < list.size()) {
                        Object obj2 = list.get(i2);
                        if (obj2 instanceof Bundle) {
                            list.set(i2, new Bundle((Bundle) obj2));
                        }
                        i2++;
                    }
                }
            }
        }
        this.zzu.zzaW().zzj(new zzkc(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    public final void zzK(String str, String str2, Object obj, boolean z) {
        zzL(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ldl", obj, true, this.zzu.zzaZ().currentTimeMillis());
    }

    final void zzM(String str, String str2, long j, Object obj) {
        this.zzu.zzaW().zzj(new zzkd(this, str, str2, obj, j));
    }

    final void zzN(String str, String str2, Object obj, long j) {
        String str3;
        Object obj2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzb();
        if (FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS.equals(str2)) {
            if (obj instanceof String) {
                String str4 = (String) obj;
                if (!TextUtils.isEmpty(str4)) {
                    String lowerCase = str4.toLowerCase(Locale.ENGLISH);
                    String str5 = BooleanUtils.FALSE;
                    long j2 = true != BooleanUtils.FALSE.equals(lowerCase) ? 0L : 1L;
                    zzic zzicVar = this.zzu;
                    Long valueOf = Long.valueOf(j2);
                    zzhg zzhgVar = zzicVar.zzd().zzh;
                    if (valueOf.longValue() == 1) {
                        str5 = BooleanUtils.TRUE;
                    }
                    zzhgVar.zzb(str5);
                    obj = valueOf;
                    str2 = "_npa";
                    this.zzu.zzaV().zzk().zzc("Setting user property(FE)", "non_personalized_ads(_npa)", obj);
                    str3 = str2;
                    obj2 = obj;
                }
            }
            if (obj == null) {
                this.zzu.zzd().zzh.zzb("unset");
                str2 = "_npa";
            }
            this.zzu.zzaV().zzk().zzc("Setting user property(FE)", "non_personalized_ads(_npa)", obj);
            str3 = str2;
            obj2 = obj;
        } else {
            str3 = str2;
            obj2 = obj;
        }
        zzic zzicVar2 = this.zzu;
        if (!zzicVar2.zzB()) {
            this.zzu.zzaV().zzk().zza("User property not set since app measurement is disabled");
        } else if (zzicVar2.zzH()) {
            this.zzu.zzt().zzA(new zzpl(str3, j, obj2, str));
        }
    }

    public final List zzO(boolean z) {
        zzb();
        zzic zzicVar = this.zzu;
        zzicVar.zzaV().zzk().zza("Getting user properties (FE)");
        if (zzicVar.zzaW().zze()) {
            zzicVar.zzaV().zzb().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        }
        zzicVar.zzaU();
        if (zzae.zza()) {
            zzicVar.zzaV().zzb().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzu.zzaW().zzk(atomicReference, CoroutineLiveDataKt.DEFAULT_TIMEOUT, "get user properties", new zzkf(this, atomicReference, z));
        List list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzicVar.zzaV().zzb().zzb("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
        return Collections.emptyList();
    }

    public final Map zzP(String str, String str2, boolean z) {
        zzic zzicVar = this.zzu;
        if (zzicVar.zzaW().zze()) {
            zzicVar.zzaV().zzb().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        zzicVar.zzaU();
        if (zzae.zza()) {
            zzicVar.zzaV().zzb().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzu.zzaW().zzk(atomicReference, CoroutineLiveDataKt.DEFAULT_TIMEOUT, "get user properties", new zzkl(this, atomicReference, null, str, str2, z));
        List<zzpl> list = (List) atomicReference.get();
        if (list == null) {
            zzicVar.zzaV().zzb().zzb("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (zzpl zzplVar : list) {
            Object zza = zzplVar.zza();
            if (zza != null) {
                arrayMap.put(zzplVar.zzb, zza);
            }
        }
        return arrayMap;
    }

    public final String zzQ() {
        return (String) this.zzg.get();
    }

    final void zzR(String str) {
        this.zzg.set(str);
    }

    public final void zzS() {
        zzg();
        zzic zzicVar = this.zzu;
        if (zzicVar.zzd().zzo.zza()) {
            zzicVar.zzaV().zzj().zza("Deferred Deep Link already retrieved. Not fetching again.");
            return;
        }
        long zza = zzicVar.zzd().zzp.zza();
        zzicVar.zzd().zzp.zzb(1 + zza);
        zzicVar.zzc();
        if (zza >= 5) {
            zzicVar.zzaV().zze().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
            zzicVar.zzd().zzo.zzb(true);
        } else {
            if (this.zzr == null) {
                this.zzr = new zzkg(this, this.zzu);
            }
            this.zzr.zzb(0L);
        }
    }

    public final void zzT(long j) {
        this.zzg.set(null);
        this.zzu.zzaW().zzj(new zzkh(this, j));
    }

    public final void zzU() {
        zzg();
        zzb();
        if (this.zzu.zzH()) {
            zzic zzicVar = this.zzu;
            zzal zzc = zzicVar.zzc();
            zzc.zzu.zzaU();
            Boolean zzr = zzc.zzr("google_analytics_deferred_deep_link_enabled");
            if (zzr != null && zzr.booleanValue()) {
                zzicVar.zzaV().zzj().zza("Deferred Deep Link feature enabled.");
                zzicVar.zzaW().zzj(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzlh
                    @Override // java.lang.Runnable
                    public final /* synthetic */ void run() {
                        zzlj.this.zzS();
                    }
                });
            }
            this.zzu.zzt().zzE();
            this.zzc = false;
            zzhh zzd = zzicVar.zzd();
            zzd.zzg();
            String string = zzd.zzd().getString("previous_os_version", null);
            zzd.zzu.zzu().zzw();
            String str = Build.VERSION.RELEASE;
            if (!TextUtils.isEmpty(str) && !str.equals(string)) {
                SharedPreferences.Editor edit = zzd.zzd().edit();
                edit.putString("previous_os_version", str);
                edit.apply();
            }
            if (TextUtils.isEmpty(string)) {
                return;
            }
            zzicVar.zzu().zzw();
            if (string.equals(Build.VERSION.RELEASE)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("_po", string);
            zzF(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ou", bundle);
        }
    }

    public final void zzV(zzjp zzjpVar) {
        zzjp zzjpVar2;
        zzg();
        zzb();
        if (zzjpVar != null && zzjpVar != (zzjpVar2 = this.zzd)) {
            Preconditions.checkState(zzjpVar2 == null, "EventInterceptor already set.");
        }
        this.zzd = zzjpVar;
    }

    public final void zzW(zzjq zzjqVar) {
        zzb();
        Preconditions.checkNotNull(zzjqVar);
        if (this.zze.add(zzjqVar)) {
            return;
        }
        this.zzu.zzaV().zze().zza("OnEventListener already registered");
    }

    public final void zzX(zzjq zzjqVar) {
        zzb();
        Preconditions.checkNotNull(zzjqVar);
        if (this.zze.remove(zzjqVar)) {
            return;
        }
        this.zzu.zzaV().zze().zza("OnEventListener had not been registered");
    }

    public final int zzY(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzu.zzc();
        return 25;
    }

    public final void zzZ(Bundle bundle) {
        zzaa(bundle, this.zzu.zzaZ().currentTimeMillis());
    }

    public final void zzaa(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            this.zzu.zzaV().zze().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        zzjh.zzb(bundle2, "app_id", String.class, null);
        zzjh.zzb(bundle2, "origin", String.class, null);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
        zzjh.zzb(bundle2, "value", Object.class, null);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzjh.zzb(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object obj = bundle2.get("value");
        zzic zzicVar = this.zzu;
        if (zzicVar.zzk().zzp(string) != 0) {
            zzicVar.zzaV().zzb().zzb("Invalid conditional user property name", zzicVar.zzl().zzc(string));
            return;
        }
        if (zzicVar.zzk().zzK(string, obj) != 0) {
            zzicVar.zzaV().zzb().zzc("Invalid conditional user property value", zzicVar.zzl().zzc(string), obj);
            return;
        }
        Object zzL = zzicVar.zzk().zzL(string, obj);
        if (zzL == null) {
            zzicVar.zzaV().zzb().zzc("Unable to normalize conditional user property value", zzicVar.zzl().zzc(string), obj);
            return;
        }
        zzjh.zza(bundle2, zzL);
        long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
        if (!TextUtils.isEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME))) {
            zzicVar.zzc();
            if (j2 > 15552000000L || j2 < 1) {
                zzicVar.zzaV().zzb().zzc("Invalid conditional user property timeout", zzicVar.zzl().zzc(string), Long.valueOf(j2));
                return;
            }
        }
        long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
        zzicVar.zzc();
        if (j3 > 15552000000L || j3 < 1) {
            zzicVar.zzaV().zzb().zzc("Invalid conditional user property time to live", zzicVar.zzl().zzc(string), Long.valueOf(j3));
        } else {
            zzicVar.zzaW().zzj(new zzki(this, bundle2));
        }
    }

    public final void zzab(String str, String str2, Bundle bundle) {
        zzic zzicVar = this.zzu;
        long currentTimeMillis = zzicVar.zzaZ().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str2 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str2);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzicVar.zzaW().zzj(new zzkj(this, bundle2));
    }

    public final ArrayList zzac(String str, String str2) {
        zzic zzicVar = this.zzu;
        if (zzicVar.zzaW().zze()) {
            zzicVar.zzaV().zzb().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList(0);
        }
        zzicVar.zzaU();
        if (zzae.zza()) {
            zzicVar.zzaV().zzb().zza("Cannot get conditional user properties from main thread");
            return new ArrayList(0);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzu.zzaW().zzk(atomicReference, CoroutineLiveDataKt.DEFAULT_TIMEOUT, "get conditional user properties", new zzkk(this, atomicReference, null, str, str2));
        List list = (List) atomicReference.get();
        if (list != null) {
            return zzpp.zzas(list);
        }
        zzicVar.zzaV().zzb().zzb("Timed out waiting for get conditional user properties", null);
        return new ArrayList();
    }

    public final String zzad() {
        zzlu zzl = this.zzu.zzs().zzl();
        if (zzl != null) {
            return zzl.zza;
        }
        return null;
    }

    public final String zzae() {
        zzlu zzl = this.zzu.zzs().zzl();
        if (zzl != null) {
            return zzl.zzb;
        }
        return null;
    }

    public final String zzaf() {
        try {
            return zzlt.zza(this.zzu.zzaY(), "google_app_id", this.zzu.zzq());
        } catch (IllegalStateException e) {
            this.zzu.zzaV().zzb().zzb("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    final /* synthetic */ void zzag(SharedPreferences sharedPreferences, String str) {
        zzic zzicVar = this.zzu;
        if (!zzicVar.zzc().zzp(null, zzfy.zzaZ)) {
            if (Objects.equals(str, "IABTCF_TCString")) {
                zzicVar.zzaV().zzk().zza("IABTCF_TCString change picked up in listener.");
                ((zzay) Preconditions.checkNotNull(this.zzt)).zzb(500L);
                return;
            }
            return;
        }
        if (Objects.equals(str, "IABTCF_TCString") || Objects.equals(str, "IABTCF_gdprApplies") || Objects.equals(str, "IABTCF_EnableAdvertiserConsentMode")) {
            zzicVar.zzaV().zzk().zza("IABTCF_TCString change picked up in listener.");
            ((zzay) Preconditions.checkNotNull(this.zzt)).zzb(500L);
        }
    }

    final /* synthetic */ void zzah(Bundle bundle) {
        Bundle bundle2;
        int i;
        if (bundle.isEmpty()) {
            bundle2 = bundle;
        } else {
            zzic zzicVar = this.zzu;
            bundle2 = new Bundle(zzicVar.zzd().zzt.zza());
            Iterator<String> it = bundle.keySet().iterator();
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                Object obj = bundle.get(next);
                if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                    if (zzicVar.zzk().zzt(obj)) {
                        zzicVar.zzk().zzN(this.zzv, null, 27, null, null, 0);
                    }
                    zzicVar.zzaV().zzh().zzc("Invalid default event parameter type. Name, value", next, obj);
                } else if (zzpp.zzZ(next)) {
                    zzicVar.zzaV().zzh().zzb("Invalid default event parameter name. Name", next);
                } else if (obj == null) {
                    bundle2.remove(next);
                } else if (zzicVar.zzk().zzu("param", next, zzicVar.zzc().zze(null, false), obj)) {
                    zzicVar.zzk().zzM(bundle2, next, obj);
                }
            }
            zzicVar.zzk();
            int zzc = zzicVar.zzc().zzc();
            if (bundle2.size() > zzc) {
                for (String str : new TreeSet(bundle2.keySet())) {
                    i++;
                    if (i > zzc) {
                        bundle2.remove(str);
                    }
                }
                zzicVar.zzk().zzN(this.zzv, null, 26, null, null, 0);
                zzicVar.zzaV().zzh().zza("Too many default event parameters set. Discarding beyond event parameter limit");
            }
        }
        zzic zzicVar2 = this.zzu;
        zzicVar2.zzd().zzt.zzb(bundle2);
        if (!bundle.isEmpty() || zzicVar2.zzc().zzp(null, zzfy.zzaW)) {
            this.zzu.zzt().zzH(bundle2);
        }
    }

    final /* synthetic */ void zzai(int i) {
        if (this.zzk == null) {
            this.zzk = new zzjx(this, this.zzu);
        }
        this.zzk.zzb(i * 1000);
    }

    final /* synthetic */ void zzaj(Boolean bool, boolean z) {
        zzas(bool, true);
    }

    final /* synthetic */ void zzak(zzjl zzjlVar, long j, boolean z, boolean z2) {
        zzg();
        zzb();
        zzic zzicVar = this.zzu;
        zzjl zzl = zzicVar.zzd().zzl();
        if (j <= this.zzq && zzjl.zzu(zzl.zzb(), zzjlVar.zzb())) {
            zzicVar.zzaV().zzi().zzb("Dropped out-of-date consent setting, proposed settings", zzjlVar);
            return;
        }
        zzhh zzd = zzicVar.zzd();
        zzic zzicVar2 = zzd.zzu;
        zzd.zzg();
        int zzb = zzjlVar.zzb();
        if (!zzd.zzk(zzb)) {
            zzicVar.zzaV().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(zzjlVar.zzb()));
            return;
        }
        zzic zzicVar3 = this.zzu;
        SharedPreferences.Editor edit = zzd.zzd().edit();
        edit.putString("consent_settings", zzjlVar.zzl());
        edit.putInt("consent_source", zzb);
        edit.apply();
        zzicVar.zzaV().zzk().zzb("Setting storage consent(FE)", zzjlVar);
        this.zzq = j;
        if (zzicVar3.zzt().zzP()) {
            zzicVar3.zzt().zzk(z);
        } else {
            zzicVar3.zzt().zzj(z);
        }
        if (z2) {
            zzicVar3.zzt().zzC(new AtomicReference());
        }
    }

    final /* synthetic */ void zzam(boolean z) {
        this.zzi = false;
    }

    final /* synthetic */ int zzan() {
        return this.zzj;
    }

    final /* synthetic */ void zzao(int i) {
        this.zzj = i;
    }

    final /* synthetic */ zzay zzap() {
        return this.zzr;
    }

    final /* synthetic */ int zzaq(Throwable th) {
        String message = th.getMessage();
        this.zzn = false;
        int i = 2;
        if (message != null) {
            if ((th instanceof IllegalStateException) || message.contains("garbage collected") || th.getClass().getSimpleName().equals("ServiceUnavailableException")) {
                i = 1;
                if (message.contains("Background")) {
                    this.zzn = true;
                    return 1;
                }
            } else if ((th instanceof SecurityException) && !message.endsWith("READ_DEVICE_CONFIG")) {
                return 3;
            }
        }
        return i;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    protected final boolean zze() {
        return false;
    }

    public final void zzh() {
        zzic zzicVar = this.zzu;
        if (!(zzicVar.zzaY().getApplicationContext() instanceof Application) || this.zza == null) {
            return;
        }
        ((Application) zzicVar.zzaY().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
    }

    public final Boolean zzi() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) this.zzu.zzaW().zzk(atomicReference, 15000L, "boolean test flag value", new zzke(this, atomicReference));
    }

    public final String zzj() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) this.zzu.zzaW().zzk(atomicReference, 15000L, "String test flag value", new zzko(this, atomicReference));
    }

    public final Long zzk() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) this.zzu.zzaW().zzk(atomicReference, 15000L, "long test flag value", new zzkp(this, atomicReference));
    }

    public final Integer zzl() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) this.zzu.zzaW().zzk(atomicReference, 15000L, "int test flag value", new zzkq(this, atomicReference));
    }

    public final Double zzm() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) this.zzu.zzaW().zzk(atomicReference, 15000L, "double test flag value", new zzkr(this, atomicReference));
    }

    public final void zzn(Boolean bool) {
        zzb();
        this.zzu.zzaW().zzj(new zzks(this, bool));
    }

    final void zzp(Bundle bundle, int i, long j) {
        Object obj;
        String string;
        zzb();
        zzjl zzjlVar = zzjl.zza;
        zzjk[] zzb = zzjj.STORAGE.zzb();
        int length = zzb.length;
        int i2 = 0;
        while (true) {
            obj = null;
            if (i2 >= length) {
                break;
            }
            String str = zzb[i2].zze;
            if (bundle.containsKey(str) && (string = bundle.getString(str)) != null) {
                if (string.equals("granted")) {
                    obj = true;
                } else if (string.equals("denied")) {
                    obj = false;
                }
                if (obj == null) {
                    obj = string;
                    break;
                }
            }
            i2++;
        }
        if (obj != null) {
            zzic zzicVar = this.zzu;
            zzicVar.zzaV().zzh().zzb("Ignoring invalid consent setting", obj);
            zzicVar.zzaV().zzh().zza("Valid consent values are 'granted', 'denied'");
        }
        boolean zze = this.zzu.zzaW().zze();
        zzjl zze2 = zzjl.zze(bundle, i);
        if (zze2.zzc()) {
            zzs(zze2, zze);
        }
        zzaz zzh = zzaz.zzh(bundle, i);
        if (zzh.zzd()) {
            zzq(zzh, zze);
        }
        Boolean zzi = zzaz.zzi(bundle);
        if (zzi != null) {
            String str2 = i == -30 ? "tcf" : "app";
            if (zze) {
                zzN(str2, FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS, zzi.toString(), j);
            } else {
                zzL(str2, FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS, zzi.toString(), false, j);
            }
        }
    }

    final void zzq(zzaz zzazVar, boolean z) {
        zzkt zzktVar = new zzkt(this, zzazVar);
        if (!z) {
            this.zzu.zzaW().zzj(zzktVar);
        } else {
            zzg();
            zzktVar.run();
        }
    }

    public final void zzs(zzjl zzjlVar, boolean z) {
        boolean z2;
        zzjl zzjlVar2;
        boolean z3;
        boolean z4;
        zzb();
        int zzb = zzjlVar.zzb();
        if (zzb != -10) {
            zzji zzp = zzjlVar.zzp();
            zzji zzjiVar = zzji.UNINITIALIZED;
            if (zzp == zzjiVar && zzjlVar.zzq() == zzjiVar) {
                this.zzu.zzaV().zzh().zza("Ignoring empty consent settings");
                return;
            }
        }
        synchronized (this.zzh) {
            z2 = false;
            if (zzjl.zzu(zzb, this.zzo.zzb())) {
                z3 = zzjlVar.zzr(this.zzo);
                zzjk zzjkVar = zzjk.ANALYTICS_STORAGE;
                if (zzjlVar.zzo(zzjkVar) && !this.zzo.zzo(zzjkVar)) {
                    z2 = true;
                }
                zzjl zzt = zzjlVar.zzt(this.zzo);
                this.zzo = zzt;
                zzjlVar2 = zzt;
                z4 = z2;
                z2 = true;
            } else {
                zzjlVar2 = zzjlVar;
                z3 = false;
                z4 = false;
            }
        }
        if (!z2) {
            this.zzu.zzaV().zzi().zzb("Ignoring lower-priority consent settings, proposed settings", zzjlVar2);
            return;
        }
        long andIncrement = this.zzp.getAndIncrement();
        if (z3) {
            this.zzg.set(null);
            zzku zzkuVar = new zzku(this, zzjlVar2, andIncrement, z4);
            if (!z) {
                this.zzu.zzaW().zzl(zzkuVar);
                return;
            } else {
                zzg();
                zzkuVar.run();
                return;
            }
        }
        zzkv zzkvVar = new zzkv(this, zzjlVar2, andIncrement, z4);
        if (z) {
            zzg();
            zzkvVar.run();
        } else if (zzb == 30 || zzb == -10) {
            this.zzu.zzaW().zzl(zzkvVar);
        } else {
            this.zzu.zzaW().zzj(zzkvVar);
        }
    }

    final void zzt(Runnable runnable) {
        zzb();
        zzic zzicVar = this.zzu;
        if (zzicVar.zzaW().zze()) {
            zzicVar.zzaV().zzb().zza("Cannot retrieve and upload batches from analytics worker thread");
            return;
        }
        if (zzicVar.zzaW().zzf()) {
            zzicVar.zzaV().zzb().zza("Cannot retrieve and upload batches from analytics network thread");
            return;
        }
        zzicVar.zzaU();
        if (zzae.zza()) {
            zzicVar.zzaV().zzb().zza("Cannot retrieve and upload batches from main thread");
            return;
        }
        zzicVar.zzaV().zzk().zza("[sgtm] Started client-side batch upload work.");
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (!z) {
            zzicVar.zzaV().zzk().zza("[sgtm] Getting upload batches from service (FE)");
            final AtomicReference atomicReference = new AtomicReference();
            zzicVar.zzaW().zzk(atomicReference, WorkRequest.MIN_BACKOFF_MILLIS, "[sgtm] Getting upload batches", new Runnable() { // from class: com.google.android.gms.measurement.internal.zzli
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    zzlj.this.zzu.zzt().zzx(atomicReference, zzoo.zza(zzls.SGTM_CLIENT));
                }
            });
            zzoq zzoqVar = (zzoq) atomicReference.get();
            if (zzoqVar == null) {
                break;
            }
            List list = zzoqVar.zza;
            if (!list.isEmpty()) {
                zzicVar.zzaV().zzk().zzb("[sgtm] Retrieved upload batches. count", Integer.valueOf(list.size()));
                i += list.size();
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    zzlr zzar = zzar((zzom) it.next());
                    if (zzar == zzlr.SUCCESS) {
                        i2++;
                    } else if (zzar == zzlr.BACKOFF) {
                        z = true;
                        break;
                    }
                }
            } else {
                break;
            }
        }
        zzicVar.zzaV().zzk().zzc("[sgtm] Completed client-side batch upload work. total, success", Integer.valueOf(i), Integer.valueOf(i2));
        runnable.run();
    }

    final void zzu(long j) {
        zzg();
        if (this.zzl == null) {
            this.zzl = new zzju(this, this.zzu);
        }
        this.zzl.zzb(j);
    }

    final void zzv() {
        zzg();
        zzay zzayVar = this.zzl;
        if (zzayVar != null) {
            zzayVar.zzd();
        }
    }

    final void zzw() {
        zzqp.zza();
        zzic zzicVar = this.zzu;
        if (zzicVar.zzc().zzp(null, zzfy.zzaQ)) {
            if (zzicVar.zzaW().zze()) {
                zzicVar.zzaV().zzb().zza("Cannot get trigger URIs from analytics worker thread");
                return;
            }
            zzicVar.zzaU();
            if (zzae.zza()) {
                zzicVar.zzaV().zzb().zza("Cannot get trigger URIs from main thread");
                return;
            }
            zzb();
            zzicVar.zzaV().zzk().zza("Getting trigger URIs (FE)");
            final AtomicReference atomicReference = new AtomicReference();
            zzicVar.zzaW().zzk(atomicReference, WorkRequest.MIN_BACKOFF_MILLIS, "get trigger URIs", new Runnable() { // from class: com.google.android.gms.measurement.internal.zzla
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    zzlj zzljVar = zzlj.this;
                    zzljVar.zzu.zzt().zzw(atomicReference, zzljVar.zzu.zzd().zzi.zza());
                }
            });
            final List list = (List) atomicReference.get();
            if (list == null) {
                zzicVar.zzaV().zzd().zza("Timed out waiting for get trigger URIs");
            } else {
                zzicVar.zzaW().zzj(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzlb
                    @Override // java.lang.Runnable
                    public final /* synthetic */ void run() {
                        zzlj zzljVar = zzlj.this;
                        zzljVar.zzg();
                        if (Build.VERSION.SDK_INT < 30) {
                            return;
                        }
                        List<zzoh> list2 = list;
                        SparseArray zzf = zzljVar.zzu.zzd().zzf();
                        for (zzoh zzohVar : list2) {
                            int i = zzohVar.zzc;
                            if (!zzf.contains(i) || ((Long) zzf.get(i)).longValue() < zzohVar.zzb) {
                                zzljVar.zzy().add(zzohVar);
                            }
                        }
                        zzljVar.zzz();
                    }
                });
            }
        }
    }

    final boolean zzx() {
        return this.zzn;
    }

    final PriorityQueue zzy() {
        if (this.zzm == null) {
            this.zzm = new PriorityQueue(Comparator.comparing(zzlc.zza, zzld.zza));
        }
        return this.zzm;
    }

    final void zzz() {
        zzoh zzohVar;
        zzg();
        this.zzn = false;
        if (zzy().isEmpty() || this.zzi || (zzohVar = (zzoh) zzy().poll()) == null) {
            return;
        }
        zzic zzicVar = this.zzu;
        MeasurementManagerFutures zzT = zzicVar.zzk().zzT();
        if (zzT != null) {
            this.zzi = true;
            zzgs zzk = zzicVar.zzaV().zzk();
            String str = zzohVar.zza;
            zzk.zzb("Registering trigger URI", str);
            ListenableFuture<Unit> registerTriggerAsync = zzT.registerTriggerAsync(Uri.parse(str));
            if (registerTriggerAsync != null) {
                Futures.addCallback(registerTriggerAsync, new zzjw(this, zzohVar), new zzjv(this));
            } else {
                this.zzi = false;
                zzy().add(zzohVar);
            }
        }
    }

    public final void zzL(String str, String str2, Object obj, boolean z, long j) {
        int i;
        if (z) {
            i = this.zzu.zzk().zzp(str2);
        } else {
            zzpp zzk = this.zzu.zzk();
            if (!zzk.zzj("user property", str2)) {
                i = 6;
            } else if (zzk.zzl("user property", zzjo.zza, null, str2)) {
                zzk.zzu.zzc();
                i = !zzk.zzm("user property", 24, str2) ? 6 : 0;
            } else {
                i = 15;
            }
        }
        if (i != 0) {
            zzic zzicVar = this.zzu;
            zzpp zzk2 = zzicVar.zzk();
            zzicVar.zzc();
            this.zzu.zzk().zzN(this.zzv, null, i, "_ev", zzk2.zzC(str2, 24, true), str2 != null ? str2.length() : 0);
            return;
        }
        String str3 = str == null ? "app" : str;
        if (obj == null) {
            zzM(str3, str2, j, null);
            return;
        }
        zzic zzicVar2 = this.zzu;
        int zzK = zzicVar2.zzk().zzK(str2, obj);
        if (zzK == 0) {
            Object zzL = zzicVar2.zzk().zzL(str2, obj);
            if (zzL != null) {
                zzM(str3, str2, j, zzL);
                return;
            }
            return;
        }
        zzpp zzk3 = zzicVar2.zzk();
        zzicVar2.zzc();
        this.zzu.zzk().zzN(this.zzv, null, zzK, "_ev", zzk3.zzC(str2, 24, true), ((obj instanceof String) || (obj instanceof CharSequence)) ? obj.toString().length() : 0);
    }
}
