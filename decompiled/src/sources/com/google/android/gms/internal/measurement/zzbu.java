package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzbu extends zzca {
    private final String zzc;
    private final int zzd;
    private final int zze;

    /* synthetic */ zzbu(String str, boolean z, int i, zzbr zzbrVar, zzbs zzbsVar, int i2, byte[] bArr) {
        this.zzc = str;
        this.zzd = i;
        this.zze = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzca)) {
            return false;
        }
        zzca zzcaVar = (zzca) obj;
        if (this.zzc.equals(zzcaVar.zza())) {
            zzcaVar.zzb();
            int i = this.zzd;
            int zze = zzcaVar.zze();
            if (i == 0) {
                throw null;
            }
            if (i == zze) {
                zzcaVar.zzc();
                zzcaVar.zzd();
                int i2 = this.zze;
                int zzf = zzcaVar.zzf();
                if (i2 == 0) {
                    throw null;
                }
                if (zzf == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.zzc.hashCode() ^ 1000003;
        int i = this.zzd;
        if (i == 0) {
            throw null;
        }
        int i2 = (((hashCode * 1000003) ^ 1237) * 1000003) ^ i;
        if (this.zze != 0) {
            return (i2 * 583896283) ^ 1;
        }
        throw null;
    }

    public final String toString() {
        String str;
        String str2 = "null";
        switch (this.zzd) {
            case 1:
                str = "ALL_CHECKS";
                break;
            case 2:
                str = "SKIP_COMPLIANCE_CHECK";
                break;
            case 3:
                str = "SKIP_SECURITY_CHECK";
                break;
            case 4:
                str = "NO_CHECKS";
                break;
            default:
                str = "null";
                break;
        }
        switch (this.zze) {
            case 1:
                str2 = "READ_AND_WRITE";
                break;
        }
        String str3 = this.zzc;
        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 73 + str.length() + 91 + str2.length() + 1);
        sb.append("FileComplianceOptions{fileOwner=");
        sb.append(str3);
        sb.append(", hasDifferentDmaOwner=false, fileChecks=");
        sb.append(str);
        sb.append(", dataForwardingNotAllowedResolver=null, multipleProductIdGroupsResolver=null, filePurpose=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzca
    public final String zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzca
    public final boolean zzb() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzca
    public final zzbr zzc() {
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzca
    public final zzbs zzd() {
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzca
    public final int zze() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzca
    public final int zzf() {
        return this.zze;
    }
}
