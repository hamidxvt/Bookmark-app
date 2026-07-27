package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzjm;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-api@@23.0.0 */
/* loaded from: classes16.dex */
public final class zze implements zza {
    final Set zza;
    private final AnalyticsConnector.AnalyticsConnectorListener zzb;
    private final AppMeasurementSdk zzc;
    private final zzd zzd = new zzd(this);

    public zze(AppMeasurementSdk appMeasurementSdk, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzb = analyticsConnectorListener;
        this.zzc = appMeasurementSdk;
        this.zzc.registerOnMeasurementEventListener(this.zzd);
        this.zza = new HashSet();
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final AnalyticsConnector.AnalyticsConnectorListener zza() {
        return this.zzb;
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zzb(Set set) {
        boolean z;
        Set set2 = this.zza;
        set2.clear();
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (hashSet.size() >= 50) {
                break;
            }
            int i = zzc.zza;
            if (str != null) {
                if (str.length() != 0) {
                    int codePointAt = str.codePointAt(0);
                    if (!Character.isLetter(codePointAt)) {
                        if (codePointAt != 95) {
                            z = false;
                        } else {
                            codePointAt = 95;
                        }
                    }
                    int length = str.length();
                    int charCount = Character.charCount(codePointAt);
                    while (true) {
                        if (charCount < length) {
                            int codePointAt2 = str.codePointAt(charCount);
                            if (codePointAt2 != 95 && !Character.isLetterOrDigit(codePointAt2)) {
                                z = false;
                                break;
                            }
                            charCount += Character.charCount(codePointAt2);
                        } else {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            if (z && str.length() != 0) {
                int codePointAt3 = str.codePointAt(0);
                if (Character.isLetter(codePointAt3)) {
                    int length2 = str.length();
                    int charCount2 = Character.charCount(codePointAt3);
                    while (true) {
                        if (charCount2 >= length2) {
                            String zzb = zzjm.zzb(str);
                            if (zzb != null) {
                                str = zzb;
                            }
                            Preconditions.checkNotNull(str);
                            hashSet.add(str);
                        } else {
                            int codePointAt4 = str.codePointAt(charCount2);
                            if (codePointAt4 == 95 || Character.isLetterOrDigit(codePointAt4)) {
                                charCount2 += Character.charCount(codePointAt4);
                            }
                        }
                    }
                }
            }
        }
        set2.addAll(hashSet);
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zzc() {
        this.zza.clear();
    }

    final /* synthetic */ AnalyticsConnector.AnalyticsConnectorListener zzd() {
        return this.zzb;
    }
}
