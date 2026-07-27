package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzqp;
import java.util.Arrays;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzhd {
    final /* synthetic */ zzhh zza;
    private final String zzb;
    private final Bundle zzc;
    private Bundle zzd;

    public zzhd(zzhh zzhhVar, String str, Bundle bundle) {
        Objects.requireNonNull(zzhhVar);
        this.zza = zzhhVar;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        this.zzc = new Bundle();
    }

    public final void zzb(Bundle bundle) {
        zzhh zzhhVar;
        Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        zzhh zzhhVar2 = this.zza;
        SharedPreferences.Editor edit = zzhhVar2.zzd().edit();
        if (bundle2.size() == 0) {
            edit.remove(this.zzb);
        } else {
            String str = this.zzb;
            JSONArray jSONArray = new JSONArray();
            for (String str2 : bundle2.keySet()) {
                Object obj = bundle2.get(str2);
                if (obj != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("n", str2);
                        zzqp.zza();
                        zzic zzicVar = zzhhVar2.zzu;
                        zzhhVar = zzhhVar2;
                        if (zzicVar.zzc().zzp(null, zzfy.zzaQ)) {
                            try {
                                if (obj instanceof String) {
                                    jSONObject.put("v", obj.toString());
                                    jSONObject.put("t", "s");
                                } else if (obj instanceof Long) {
                                    jSONObject.put("v", obj.toString());
                                    jSONObject.put("t", "l");
                                } else if (obj instanceof int[]) {
                                    jSONObject.put("v", Arrays.toString((int[]) obj));
                                    jSONObject.put("t", "ia");
                                } else if (obj instanceof long[]) {
                                    jSONObject.put("v", Arrays.toString((long[]) obj));
                                    jSONObject.put("t", "la");
                                } else if (obj instanceof Double) {
                                    jSONObject.put("v", obj.toString());
                                    jSONObject.put("t", "d");
                                } else {
                                    zzicVar.zzaV().zzb().zzb("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                                    zzhhVar2 = zzhhVar;
                                }
                                jSONArray.put(jSONObject);
                                zzhhVar2 = zzhhVar;
                            } catch (JSONException e) {
                                e = e;
                                this.zza.zzu.zzaV().zzb().zzb("Cannot serialize bundle value to SharedPreferences", e);
                                zzhhVar2 = zzhhVar;
                            }
                        } else {
                            jSONObject.put("v", obj.toString());
                            if (obj instanceof String) {
                                jSONObject.put("t", "s");
                            } else if (obj instanceof Long) {
                                jSONObject.put("t", "l");
                            } else if (obj instanceof Double) {
                                jSONObject.put("t", "d");
                            } else {
                                zzicVar.zzaV().zzb().zzb("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                                zzhhVar2 = zzhhVar;
                            }
                            jSONArray.put(jSONObject);
                            zzhhVar2 = zzhhVar;
                        }
                    } catch (JSONException e2) {
                        e = e2;
                        zzhhVar = zzhhVar2;
                    }
                }
            }
            edit.putString(str, jSONArray.toString());
        }
        edit.apply();
        this.zzd = bundle2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final Bundle zza() {
        char c;
        if (this.zzd == null) {
            zzhh zzhhVar = this.zza;
            String string = zzhhVar.zzd().getString(this.zzb, null);
            if (string != null) {
                try {
                    Bundle bundle = new Bundle();
                    JSONArray jSONArray = new JSONArray(string);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            String string2 = jSONObject.getString("n");
                            String string3 = jSONObject.getString("t");
                            switch (string3.hashCode()) {
                                case 100:
                                    if (string3.equals("d")) {
                                        c = 1;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR /* 108 */:
                                    if (string3.equals("l")) {
                                        c = 2;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 115:
                                    if (string3.equals("s")) {
                                        c = 0;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 3352:
                                    if (string3.equals("ia")) {
                                        c = 3;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 3445:
                                    if (string3.equals("la")) {
                                        c = 4;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                default:
                                    c = 65535;
                                    break;
                            }
                            switch (c) {
                                case 0:
                                    bundle.putString(string2, jSONObject.getString("v"));
                                    break;
                                case 1:
                                    bundle.putDouble(string2, Double.parseDouble(jSONObject.getString("v")));
                                    break;
                                case 2:
                                    bundle.putLong(string2, Long.parseLong(jSONObject.getString("v")));
                                    break;
                                case 3:
                                    zzqp.zza();
                                    if (zzhhVar.zzu.zzc().zzp(null, zzfy.zzaQ)) {
                                        JSONArray jSONArray2 = new JSONArray(jSONObject.getString("v"));
                                        int length = jSONArray2.length();
                                        int[] iArr = new int[length];
                                        for (int i2 = 0; i2 < length; i2++) {
                                            iArr[i2] = jSONArray2.optInt(i2);
                                        }
                                        bundle.putIntArray(string2, iArr);
                                        break;
                                    } else {
                                        break;
                                    }
                                case 4:
                                    zzqp.zza();
                                    if (zzhhVar.zzu.zzc().zzp(null, zzfy.zzaQ)) {
                                        JSONArray jSONArray3 = new JSONArray(jSONObject.getString("v"));
                                        int length2 = jSONArray3.length();
                                        long[] jArr = new long[length2];
                                        for (int i3 = 0; i3 < length2; i3++) {
                                            jArr[i3] = jSONArray3.optLong(i3);
                                        }
                                        bundle.putLongArray(string2, jArr);
                                        break;
                                    } else {
                                        break;
                                    }
                                default:
                                    zzhhVar.zzu.zzaV().zzb().zzb("Unrecognized persisted bundle type. Type", string3);
                                    break;
                            }
                        } catch (NumberFormatException | JSONException e) {
                            this.zza.zzu.zzaV().zzb().zza("Error reading value from SharedPreferences. Value dropped");
                        }
                    }
                    this.zzd = bundle;
                } catch (JSONException e2) {
                    this.zza.zzu.zzaV().zzb().zza("Error loading bundle from SharedPreferences. Values will be lost");
                }
            }
            if (this.zzd == null) {
                this.zzd = this.zzc;
            }
        }
        return new Bundle((Bundle) Preconditions.checkNotNull(this.zzd));
    }
}
