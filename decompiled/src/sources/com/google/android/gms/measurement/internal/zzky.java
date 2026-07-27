package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Objects;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzky implements Application.ActivityLifecycleCallbacks, zzkw {
    final /* synthetic */ zzlj zza;

    zzky(zzlj zzljVar) {
        Objects.requireNonNull(zzljVar);
        this.zza = zzljVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(com.google.android.gms.internal.measurement.zzdf.zza(activity), bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        zzb(com.google.android.gms.internal.measurement.zzdf.zza(activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zzc(com.google.android.gms.internal.measurement.zzdf.zza(activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zzd(com.google.android.gms.internal.measurement.zzdf.zza(activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zze(com.google.android.gms.internal.measurement.zzdf.zza(activity), bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007b  */
    @Override // com.google.android.gms.measurement.internal.zzkw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(com.google.android.gms.internal.measurement.zzdf zzdfVar, Bundle bundle) {
        zzic zzicVar;
        zzlj zzljVar;
        zzic zzicVar2;
        Intent intent;
        Uri uri;
        String stringExtra;
        String str;
        try {
            try {
                zzljVar = this.zza;
                zzicVar2 = zzljVar.zzu;
                zzicVar2.zzaV().zzk().zza("onActivityCreated");
                intent = zzdfVar.zzc;
            } catch (RuntimeException e) {
                this.zza.zzu.zzaV().zzb().zzb("Throwable caught in onActivityCreated", e);
            }
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null && data.isHierarchical()) {
                    uri = data;
                    if (uri != null && uri.isHierarchical()) {
                        zzicVar2.zzk();
                        stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                        if (!"android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) && !"https://www.google.com".equals(stringExtra) && !"android-app://com.google.appcrawler".equals(stringExtra)) {
                            str = DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
                            zzicVar2.zzaW().zzj(new zzkx(this, bundle != null, uri, str, uri.getQueryParameter("referrer")));
                            zzicVar = this.zza.zzu;
                            zzicVar.zzs().zzm(zzdfVar, bundle);
                        }
                        str = "gs";
                        zzicVar2.zzaW().zzj(new zzkx(this, bundle != null, uri, str, uri.getQueryParameter("referrer")));
                        zzicVar = this.zza.zzu;
                        zzicVar.zzs().zzm(zzdfVar, bundle);
                    }
                }
                Bundle extras = intent.getExtras();
                uri = null;
                if (extras != null) {
                    String string = extras.getString("com.android.vending.referral_url");
                    if (!TextUtils.isEmpty(string)) {
                        uri = Uri.parse(string);
                    }
                }
                if (uri != null) {
                    zzicVar2.zzk();
                    stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                    if (!"android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra)) {
                        str = DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
                        zzicVar2.zzaW().zzj(new zzkx(this, bundle != null, uri, str, uri.getQueryParameter("referrer")));
                        zzicVar = this.zza.zzu;
                        zzicVar.zzs().zzm(zzdfVar, bundle);
                    }
                    str = "gs";
                    zzicVar2.zzaW().zzj(new zzkx(this, bundle != null, uri, str, uri.getQueryParameter("referrer")));
                    zzicVar = this.zza.zzu;
                    zzicVar.zzs().zzm(zzdfVar, bundle);
                }
            }
            zzicVar = zzljVar.zzu;
            zzicVar.zzs().zzm(zzdfVar, bundle);
        } catch (Throwable th) {
            this.zza.zzu.zzs().zzm(zzdfVar, bundle);
            throw th;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkw
    public final void zzb(com.google.android.gms.internal.measurement.zzdf zzdfVar) {
        this.zza.zzu.zzs().zzs(zzdfVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkw
    public final void zzc(com.google.android.gms.internal.measurement.zzdf zzdfVar) {
        zzic zzicVar = this.zza.zzu;
        zzicVar.zzs().zzp(zzdfVar);
        zzoc zzh = zzicVar.zzh();
        zzic zzicVar2 = zzh.zzu;
        zzicVar2.zzaW().zzj(new zznv(zzh, zzicVar2.zzaZ().elapsedRealtime()));
    }

    @Override // com.google.android.gms.measurement.internal.zzkw
    public final void zzd(com.google.android.gms.internal.measurement.zzdf zzdfVar) {
        zzic zzicVar = this.zza.zzu;
        zzoc zzh = zzicVar.zzh();
        zzic zzicVar2 = zzh.zzu;
        zzicVar2.zzaW().zzj(new zznu(zzh, zzicVar2.zzaZ().elapsedRealtime()));
        zzicVar.zzs().zzn(zzdfVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkw
    public final void zze(com.google.android.gms.internal.measurement.zzdf zzdfVar, Bundle bundle) {
        this.zza.zzu.zzs().zzq(zzdfVar, bundle);
    }
}
