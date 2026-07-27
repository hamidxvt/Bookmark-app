package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzer extends zzet {
    private final String zza;
    private final int zzb;
    private final int zzc;

    /* synthetic */ zzer(String str, int i, int i2, zzeq zzeqVar) {
        this.zza = str;
        this.zzb = i;
        this.zzc = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzet) {
            zzet zzetVar = (zzet) obj;
            if (this.zza.equals(zzetVar.zzb()) && this.zzb == zzetVar.zza() && this.zzc == zzetVar.zzc()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc;
    }

    public final String toString() {
        String str;
        String str2 = this.zza;
        int i = this.zzb;
        switch (this.zzc) {
            case 1:
                str = "PROGRAMMATIC_API";
                break;
            default:
                str = "AUTOCOMPLETE_WIDGET";
                break;
        }
        StringBuilder sb = new StringBuilder(str2.length() + 68 + str.length());
        sb.append("ClientProfile{packageName=");
        sb.append(str2);
        sb.append(", versionCode=");
        sb.append(i);
        sb.append(", requestSource=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzet
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzet
    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzet
    public final int zzc() {
        return this.zzc;
    }
}
