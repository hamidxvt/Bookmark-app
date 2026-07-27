package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzqp;
import com.google.android.gms.internal.measurement.zzrn;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzgi extends zzg {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private final long zzh;
    private List zzi;
    private String zzj;
    private int zzk;
    private String zzl;
    private String zzm;
    private long zzn;
    private String zzo;

    zzgi(zzic zzicVar, long j, long j2) {
        super(zzicVar);
        this.zzn = 0L;
        this.zzo = null;
        this.zzg = j;
        this.zzh = j2;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    protected final boolean zze() {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x017b A[Catch: IllegalStateException -> 0x018d, TRY_LEAVE, TryCatch #3 {IllegalStateException -> 0x018d, blocks: (B:9:0x0161, B:12:0x0177, B:14:0x017b), top: B:8:0x0161 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00ce  */
    @Override // com.google.android.gms.measurement.internal.zzg
    @EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void zzf() {
        String str;
        String str2;
        int zzC;
        zzic zzicVar;
        List zzs;
        String zza;
        zzic zzicVar2 = this.zzu;
        zzicVar2.zzaV().zzk().zzc("sdkVersion bundled with app, dynamiteVersion", Long.valueOf(this.zzh), Long.valueOf(this.zzg));
        String packageName = zzicVar2.zzaY().getPackageName();
        PackageManager packageManager = zzicVar2.zzaY().getPackageManager();
        String str3 = "";
        int i = Integer.MIN_VALUE;
        String str4 = "Unknown";
        String str5 = EnvironmentCompat.MEDIA_UNKNOWN;
        if (packageManager == null) {
            zzicVar2.zzaV().zzb().zzb("PackageManager is null, app identity information might be inaccurate. appId", zzgu.zzl(packageName));
            str2 = "Unknown";
        } else {
            try {
                str5 = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException e) {
                this.zzu.zzaV().zzb().zzb("Error retrieving app installer package name. appId", zzgu.zzl(packageName));
            }
            if (str5 == null) {
                str5 = "manual_install";
            } else if ("com.android.vending".equals(str5)) {
                str5 = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(this.zzu.zzaY().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    str2 = !TextUtils.isEmpty(applicationLabel) ? applicationLabel.toString() : "Unknown";
                    try {
                        str4 = packageInfo.versionName;
                        i = packageInfo.versionCode;
                    } catch (PackageManager.NameNotFoundException e2) {
                        str = str4;
                        str4 = str2;
                        this.zzu.zzaV().zzb().zzc("Error retrieving package info. appId, appName", zzgu.zzl(packageName), str4);
                        str2 = str4;
                        str4 = str;
                        this.zza = packageName;
                        this.zzd = str5;
                        this.zzb = str4;
                        this.zzc = i;
                        this.zze = str2;
                        this.zzf = 0L;
                        zzic zzicVar3 = this.zzu;
                        zzC = zzicVar3.zzC();
                        switch (zzC) {
                        }
                        this.zzl = "";
                        zzicVar = this.zzu;
                        zzicVar.zzaU();
                        zza = zzlt.zza(zzicVar.zzaY(), "google_app_id", zzicVar3.zzq());
                        if (TextUtils.isEmpty(zza)) {
                        }
                        this.zzl = str3;
                        if (zzC == 0) {
                        }
                        this.zzi = null;
                        zzic zzicVar4 = this.zzu;
                        zzicVar4.zzaU();
                        zzs = zzicVar4.zzc().zzs("analytics.safelisted_events");
                        if (zzs != null) {
                        }
                        this.zzi = zzs;
                        if (packageManager != null) {
                        }
                    }
                } else {
                    str2 = "Unknown";
                }
            } catch (PackageManager.NameNotFoundException e3) {
                str = "Unknown";
            }
        }
        this.zza = packageName;
        this.zzd = str5;
        this.zzb = str4;
        this.zzc = i;
        this.zze = str2;
        this.zzf = 0L;
        zzic zzicVar32 = this.zzu;
        zzC = zzicVar32.zzC();
        switch (zzC) {
            case 0:
                this.zzu.zzaV().zzk().zza("App measurement collection enabled");
                break;
            case 1:
                this.zzu.zzaV().zzi().zza("App measurement deactivated via the manifest");
                break;
            case 2:
            case 5:
            default:
                zzic zzicVar5 = this.zzu;
                zzicVar5.zzaV().zzi().zza("App measurement disabled");
                zzicVar5.zzaV().zzc().zza("Invalid scion state in identity");
                break;
            case 3:
                this.zzu.zzaV().zzi().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                break;
            case 4:
                this.zzu.zzaV().zzi().zza("App measurement disabled via the manifest");
                break;
            case 6:
                this.zzu.zzaV().zzh().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                break;
            case 7:
                this.zzu.zzaV().zzi().zza("App measurement disabled via the global data collection setting");
                break;
            case 8:
                this.zzu.zzaV().zzi().zza("App measurement disabled due to denied storage consent");
                break;
        }
        this.zzl = "";
        zzicVar = this.zzu;
        zzicVar.zzaU();
        try {
            zza = zzlt.zza(zzicVar.zzaY(), "google_app_id", zzicVar32.zzq());
            if (TextUtils.isEmpty(zza)) {
                str3 = zza;
            }
            this.zzl = str3;
            if (zzC == 0) {
                zzicVar.zzaV().zzk().zzc("App measurement enabled for app package, google app id", this.zza, this.zzl);
            }
        } catch (IllegalStateException e4) {
            this.zzu.zzaV().zzb().zzc("Fetching Google App Id failed with exception. appId", zzgu.zzl(packageName), e4);
        }
        this.zzi = null;
        zzic zzicVar42 = this.zzu;
        zzicVar42.zzaU();
        zzs = zzicVar42.zzc().zzs("analytics.safelisted_events");
        if (zzs != null) {
            if (zzs.isEmpty()) {
                zzicVar42.zzaV().zzh().zza("Safelisted event list is empty. Ignoring");
            } else {
                Iterator it = zzs.iterator();
                while (it.hasNext()) {
                    if (!zzicVar42.zzk().zzk("safelisted event", (String) it.next())) {
                    }
                }
            }
            if (packageManager != null) {
                this.zzk = InstantApps.isInstantApp(zzicVar42.zzaY()) ? 1 : 0;
                return;
            } else {
                this.zzk = 0;
                return;
            }
        }
        this.zzi = zzs;
        if (packageManager != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x02d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final zzr zzh(String str) {
        long j;
        String str2;
        int i;
        long j2;
        long j3;
        String str3;
        int i2;
        int i3;
        long j4;
        long j5;
        long j6;
        zzg();
        String zzj = zzj();
        String zzk = zzk();
        zzb();
        String str4 = this.zzb;
        zzb();
        long j7 = this.zzc;
        zzb();
        Preconditions.checkNotNull(this.zzd);
        String str5 = this.zzd;
        zzic zzicVar = this.zzu;
        zzicVar.zzc().zzi();
        zzb();
        zzg();
        long j8 = this.zzf;
        if (j8 == 0) {
            zzpp zzk2 = this.zzu.zzk();
            Context zzaY = zzicVar.zzaY();
            String packageName = zzicVar.zzaY().getPackageName();
            zzk2.zzg();
            Preconditions.checkNotNull(zzaY);
            Preconditions.checkNotEmpty(packageName);
            PackageManager packageManager = zzaY.getPackageManager();
            MessageDigest zzO = zzpp.zzO();
            if (zzO == null) {
                zzk2.zzu.zzaV().zzb().zza("Could not get MD5 instance");
                j5 = -1;
            } else if (packageManager != null) {
                try {
                    if (zzk2.zzad(zzaY, packageName)) {
                        j6 = 0;
                    } else {
                        PackageManagerWrapper packageManager2 = Wrappers.packageManager(zzaY);
                        zzic zzicVar2 = zzk2.zzu;
                        PackageInfo packageInfo = packageManager2.getPackageInfo(zzicVar2.zzaY().getPackageName(), 64);
                        if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                            zzicVar2.zzaV().zze().zza("Could not get signatures");
                            j6 = -1;
                        } else {
                            j6 = zzpp.zzP(zzO.digest(packageInfo.signatures[0].toByteArray()));
                        }
                    }
                    j5 = j6;
                } catch (PackageManager.NameNotFoundException e) {
                    zzk2.zzu.zzaV().zzb().zzb("Package name not found", e);
                    j5 = 0;
                }
            } else {
                j5 = 0;
            }
            this.zzf = j5;
            j = j5;
        } else {
            j = j8;
        }
        zzic zzicVar3 = this.zzu;
        zzic zzicVar4 = this.zzu;
        boolean zzB = zzicVar3.zzB();
        boolean z = !zzicVar4.zzd().zzm;
        zzg();
        if (zzicVar3.zzB()) {
            zzrn.zza();
            if (zzicVar4.zzc().zzp(null, zzfy.zzaH)) {
                this.zzu.zzaV().zzk().zza("Disabled IID for tests.");
                str2 = null;
            } else {
                try {
                    Class<?> loadClass = zzicVar4.zzaY().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                    if (loadClass == null) {
                        str2 = null;
                    } else {
                        try {
                            Object invoke = loadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, this.zzu.zzaY());
                            if (invoke == null) {
                                str2 = null;
                            } else {
                                try {
                                    str2 = (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                                } catch (Exception e2) {
                                    this.zzu.zzaV().zzh().zza("Failed to retrieve Firebase Instance Id");
                                    str2 = null;
                                }
                            }
                        } catch (Exception e3) {
                            this.zzu.zzaV().zzf().zza("Failed to obtain Firebase Analytics instance");
                            str2 = null;
                        }
                    }
                } catch (ClassNotFoundException e4) {
                    str2 = null;
                }
            }
        } else {
            str2 = null;
        }
        zzic zzicVar5 = this.zzu;
        long zza = zzicVar5.zzd().zzc.zza();
        long min = zza == 0 ? zzicVar5.zza : Math.min(zzicVar5.zza, zza);
        zzb();
        int i4 = this.zzk;
        zzic zzicVar6 = this.zzu;
        boolean zzu = zzicVar6.zzc().zzu();
        zzhh zzd = zzicVar6.zzd();
        zzd.zzg();
        boolean z2 = zzd.zzd().getBoolean("deferred_analytics_collection", false);
        boolean z3 = zzicVar6.zzc().zzw("google_analytics_default_allow_ad_personalization_signals", true) != zzji.GRANTED;
        long j9 = j;
        long j10 = this.zzg;
        Boolean valueOf = Boolean.valueOf(z3);
        List list = this.zzi;
        String zzl = zzicVar6.zzd().zzl().zzl();
        if (this.zzj == null) {
            this.zzj = zzicVar6.zzk().zzaw();
        }
        String str6 = this.zzj;
        if (zzicVar6.zzd().zzl().zzo(zzjk.ANALYTICS_STORAGE)) {
            zzg();
            i = i4;
            j2 = 0;
            if (this.zzn == 0) {
                j3 = j10;
            } else {
                j3 = j10;
                long currentTimeMillis = zzicVar6.zzaZ().currentTimeMillis() - this.zzn;
                if (this.zzm != null && currentTimeMillis > DateUtils.MILLIS_PER_DAY && this.zzo == null) {
                    zzi();
                }
            }
            if (this.zzm == null) {
                zzi();
            }
            str3 = this.zzm;
        } else {
            i = i4;
            j3 = j10;
            j2 = 0;
            str3 = null;
        }
        boolean zzx = zzicVar6.zzc().zzx();
        zzpp zzk3 = zzicVar6.zzk();
        String zzj2 = zzj();
        zzic zzicVar7 = zzk3.zzu;
        if (zzicVar7.zzaY().getPackageManager() == null) {
            j4 = j2;
            i2 = 0;
        } else {
            try {
                i2 = 0;
            } catch (PackageManager.NameNotFoundException e5) {
                i2 = 0;
            }
            try {
                ApplicationInfo applicationInfo = Wrappers.packageManager(zzicVar7.zzaY()).getApplicationInfo(zzj2, 0);
                i3 = applicationInfo != null ? applicationInfo.targetSdkVersion : 0;
            } catch (PackageManager.NameNotFoundException e6) {
                zzic zzicVar8 = zzk3.zzu;
                zzicVar8.zzaU();
                zzicVar8.zzaV().zzi().zzb("PackageManager failed to find running app: app_id", zzj2);
                i3 = i2;
                j4 = i3;
                zzic zzicVar9 = this.zzu;
                int zzb = zzicVar9.zzd().zzl().zzb();
                String zze = zzicVar9.zzd().zzj().zze();
                zzqp.zza();
                zzal zzc = zzicVar9.zzc();
                zzfx zzfxVar = zzfy.zzaQ;
                if (!zzc.zzp(null, zzfxVar)) {
                }
                zzqp.zza();
                return new zzr(zzj, zzk, str4, j7, str5, 133005L, j9, str, zzB, z, str2, min, i, zzu, z2, valueOf, j3, list, zzl, str6, str3, zzx, j4, zzb, zze, r47, !zzicVar9.zzc().zzp(null, zzfxVar) ? zzicVar9.zzk().zzV() : j2, zzicVar9.zzc().zzz(), new zze(zzicVar9.zzc().zzw("google_analytics_default_allow_ad_personalization_signals", true)).zzb(), this.zzu.zza, this.zzu.zzx().zzj().zza());
            }
            j4 = i3;
        }
        zzic zzicVar92 = this.zzu;
        int zzb2 = zzicVar92.zzd().zzl().zzb();
        String zze2 = zzicVar92.zzd().zzj().zze();
        zzqp.zza();
        zzal zzc2 = zzicVar92.zzc();
        zzfx zzfxVar2 = zzfy.zzaQ;
        int zzU = !zzc2.zzp(null, zzfxVar2) ? zzicVar92.zzk().zzU() : i2;
        zzqp.zza();
        return new zzr(zzj, zzk, str4, j7, str5, 133005L, j9, str, zzB, z, str2, min, i, zzu, z2, valueOf, j3, list, zzl, str6, str3, zzx, j4, zzb2, zze2, zzU, !zzicVar92.zzc().zzp(null, zzfxVar2) ? zzicVar92.zzk().zzV() : j2, zzicVar92.zzc().zzz(), new zze(zzicVar92.zzc().zzw("google_analytics_default_allow_ad_personalization_signals", true)).zzb(), this.zzu.zza, this.zzu.zzx().zzj().zza());
    }

    final void zzi() {
        String format;
        zzg();
        zzic zzicVar = this.zzu;
        if (zzicVar.zzd().zzl().zzo(zzjk.ANALYTICS_STORAGE)) {
            byte[] bArr = new byte[16];
            zzicVar.zzk().zzf().nextBytes(bArr);
            format = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        } else {
            zzicVar.zzaV().zzj().zza("Analytics Storage consent is not granted");
            format = null;
        }
        zzicVar.zzaV().zzj().zza(String.format("Resetting session stitching token to %s", format == null ? "null" : "not null"));
        this.zzm = format;
        this.zzn = zzicVar.zzaZ().currentTimeMillis();
    }

    final String zzj() {
        zzb();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    final String zzk() {
        zzg();
        zzb();
        Preconditions.checkNotNull(this.zzl);
        return this.zzl;
    }

    final String zzl() {
        zzb();
        Preconditions.checkNotNull(this.zze);
        return this.zze;
    }

    final int zzm() {
        zzb();
        return this.zzc;
    }

    final long zzn() {
        return this.zzh;
    }

    final int zzo() {
        zzb();
        return this.zzk;
    }

    final List zzp() {
        return this.zzi;
    }

    final boolean zzq(String str) {
        String str2 = this.zzo;
        boolean z = false;
        if (str2 != null && !str2.equals(str)) {
            z = true;
        }
        this.zzo = str;
        return z;
    }
}
