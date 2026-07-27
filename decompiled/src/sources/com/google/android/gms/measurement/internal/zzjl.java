package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzjl {
    public static final zzjl zza = new zzjl(null, null, 100);
    private final EnumMap zzb = new EnumMap(zzjk.class);
    private final int zzc;

    public zzjl(Boolean bool, Boolean bool2, int i) {
        this.zzb.put((EnumMap) zzjk.AD_STORAGE, (zzjk) zzh(null));
        this.zzb.put((EnumMap) zzjk.ANALYTICS_STORAGE, (zzjk) zzh(null));
        this.zzc = i;
    }

    public static zzjl zza(zzji zzjiVar, zzji zzjiVar2, int i) {
        EnumMap enumMap = new EnumMap(zzjk.class);
        enumMap.put((EnumMap) zzjk.AD_STORAGE, (zzjk) zzjiVar);
        enumMap.put((EnumMap) zzjk.ANALYTICS_STORAGE, (zzjk) zzjiVar2);
        return new zzjl(enumMap, -10);
    }

    static String zzd(int i) {
        switch (i) {
            case -30:
                return "TCF";
            case -20:
                return "API";
            case -10:
                return "MANIFEST";
            case 0:
                return "1P_API";
            case 30:
                return "1P_INIT";
            case 90:
                return "REMOTE_CONFIG";
            case 100:
                return "UNKNOWN";
            default:
                return "OTHER";
        }
    }

    public static zzjl zze(Bundle bundle, int i) {
        if (bundle == null) {
            return new zzjl(null, null, i);
        }
        EnumMap enumMap = new EnumMap(zzjk.class);
        for (zzjk zzjkVar : zzjj.STORAGE.zzb()) {
            enumMap.put((EnumMap) zzjkVar, (zzjk) zzg(bundle.getString(zzjkVar.zze)));
        }
        return new zzjl(enumMap, i);
    }

    public static zzjl zzf(String str, int i) {
        EnumMap enumMap = new EnumMap(zzjk.class);
        zzjk[] zza2 = zzjj.STORAGE.zza();
        for (int i2 = 0; i2 < zza2.length; i2++) {
            String str2 = str == null ? "" : str;
            zzjk zzjkVar = zza2[i2];
            int i3 = i2 + 2;
            if (i3 < str2.length()) {
                enumMap.put((EnumMap) zzjkVar, (zzjk) zzj(str2.charAt(i3)));
            } else {
                enumMap.put((EnumMap) zzjkVar, (zzjk) zzji.UNINITIALIZED);
            }
        }
        return new zzjl(enumMap, i);
    }

    static zzji zzg(String str) {
        return str == null ? zzji.UNINITIALIZED : str.equals("granted") ? zzji.GRANTED : str.equals("denied") ? zzji.DENIED : zzji.UNINITIALIZED;
    }

    static zzji zzh(Boolean bool) {
        return bool == null ? zzji.UNINITIALIZED : bool.booleanValue() ? zzji.GRANTED : zzji.DENIED;
    }

    static String zzi(zzji zzjiVar) {
        switch (zzjiVar.ordinal()) {
            case 2:
                return "denied";
            case 3:
                return "granted";
            default:
                return null;
        }
    }

    static char zzm(zzji zzjiVar) {
        if (zzjiVar != null) {
            switch (zzjiVar) {
                case POLICY:
                    return '+';
                case DENIED:
                    return '0';
                case GRANTED:
                    return '1';
            }
        }
        return '-';
    }

    public static boolean zzu(int i, int i2) {
        int i3 = -30;
        if (i == -20) {
            if (i2 == -30) {
                return true;
            }
            i = -20;
        }
        if (i != -30) {
            i3 = i;
        } else if (i2 == -20) {
            return true;
        }
        return i3 == i2 || i < i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzjl)) {
            return false;
        }
        zzjl zzjlVar = (zzjl) obj;
        for (zzjk zzjkVar : zzjj.STORAGE.zzb()) {
            if (this.zzb.get(zzjkVar) != zzjlVar.zzb.get(zzjkVar)) {
                return false;
            }
        }
        return this.zzc == zzjlVar.zzc;
    }

    public final int hashCode() {
        Iterator it = this.zzb.values().iterator();
        int i = this.zzc * 17;
        while (it.hasNext()) {
            i = (i * 31) + ((zzji) it.next()).hashCode();
        }
        return i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source=");
        sb.append(zzd(this.zzc));
        for (zzjk zzjkVar : zzjj.STORAGE.zzb()) {
            sb.append(",");
            sb.append(zzjkVar.zze);
            sb.append("=");
            zzji zzjiVar = (zzji) this.zzb.get(zzjkVar);
            if (zzjiVar == null) {
                zzjiVar = zzji.UNINITIALIZED;
            }
            sb.append(zzjiVar);
        }
        return sb.toString();
    }

    public final int zzb() {
        return this.zzc;
    }

    public final boolean zzc() {
        Iterator it = this.zzb.values().iterator();
        while (it.hasNext()) {
            if (((zzji) it.next()) != zzji.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }

    public final String zzk() {
        StringBuilder sb = new StringBuilder("G1");
        for (zzjk zzjkVar : zzjj.STORAGE.zza()) {
            zzji zzjiVar = (zzji) this.zzb.get(zzjkVar);
            char c = '-';
            if (zzjiVar != null) {
                switch (zzjiVar) {
                    case POLICY:
                    case GRANTED:
                        c = '1';
                        break;
                    case DENIED:
                        c = '0';
                        break;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public final String zzl() {
        StringBuilder sb = new StringBuilder("G1");
        for (zzjk zzjkVar : zzjj.STORAGE.zza()) {
            sb.append(zzm((zzji) this.zzb.get(zzjkVar)));
        }
        return sb.toString();
    }

    public final Bundle zzn() {
        Bundle bundle = new Bundle();
        for (Map.Entry entry : this.zzb.entrySet()) {
            String zzi = zzi((zzji) entry.getValue());
            if (zzi != null) {
                bundle.putString(((zzjk) entry.getKey()).zze, zzi);
            }
        }
        return bundle;
    }

    public final boolean zzo(zzjk zzjkVar) {
        return ((zzji) this.zzb.get(zzjkVar)) != zzji.DENIED;
    }

    public final zzji zzp() {
        zzji zzjiVar = (zzji) this.zzb.get(zzjk.AD_STORAGE);
        return zzjiVar == null ? zzji.UNINITIALIZED : zzjiVar;
    }

    public final zzji zzq() {
        zzji zzjiVar = (zzji) this.zzb.get(zzjk.ANALYTICS_STORAGE);
        return zzjiVar == null ? zzji.UNINITIALIZED : zzjiVar;
    }

    public final boolean zzr(zzjl zzjlVar) {
        EnumMap enumMap = this.zzb;
        for (zzjk zzjkVar : (zzjk[]) enumMap.keySet().toArray(new zzjk[0])) {
            zzji zzjiVar = (zzji) enumMap.get(zzjkVar);
            zzji zzjiVar2 = (zzji) zzjlVar.zzb.get(zzjkVar);
            zzji zzjiVar3 = zzji.DENIED;
            if (zzjiVar == zzjiVar3 && zzjiVar2 != zzjiVar3) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0048 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzjl zzs(zzjl zzjlVar) {
        EnumMap enumMap = new EnumMap(zzjk.class);
        for (zzjk zzjkVar : zzjj.STORAGE.zzb()) {
            zzji zzjiVar = (zzji) this.zzb.get(zzjkVar);
            zzji zzjiVar2 = (zzji) zzjlVar.zzb.get(zzjkVar);
            if (zzjiVar != null) {
                if (zzjiVar2 != null) {
                    zzji zzjiVar3 = zzji.UNINITIALIZED;
                    if (zzjiVar != zzjiVar3) {
                        if (zzjiVar2 != zzjiVar3) {
                            zzji zzjiVar4 = zzji.POLICY;
                            if (zzjiVar != zzjiVar4) {
                                if (zzjiVar2 != zzjiVar4) {
                                    zzji zzjiVar5 = zzji.DENIED;
                                    zzjiVar = (zzjiVar == zzjiVar5 || zzjiVar2 == zzjiVar5) ? zzjiVar5 : zzji.GRANTED;
                                }
                            }
                        }
                    }
                }
                if (zzjiVar == null) {
                    enumMap.put((EnumMap) zzjkVar, (zzjk) zzjiVar);
                }
            }
            zzjiVar = zzjiVar2;
            if (zzjiVar == null) {
            }
        }
        return new zzjl(enumMap, 100);
    }

    public final zzjl zzt(zzjl zzjlVar) {
        EnumMap enumMap = new EnumMap(zzjk.class);
        for (zzjk zzjkVar : zzjj.STORAGE.zzb()) {
            zzji zzjiVar = (zzji) this.zzb.get(zzjkVar);
            if (zzjiVar == zzji.UNINITIALIZED) {
                zzjiVar = (zzji) zzjlVar.zzb.get(zzjkVar);
            }
            if (zzjiVar != null) {
                enumMap.put((EnumMap) zzjkVar, (zzjk) zzjiVar);
            }
        }
        return new zzjl(enumMap, this.zzc);
    }

    private zzjl(EnumMap enumMap, int i) {
        this.zzb.putAll(enumMap);
        this.zzc = i;
    }

    static zzji zzj(char c) {
        switch (c) {
            case '+':
                return zzji.POLICY;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                return zzji.DENIED;
            case '1':
                return zzji.GRANTED;
            default:
                return zzji.UNINITIALIZED;
        }
    }
}
