package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public class zzadx {
    private static final zzacz zzb = zzacz.zza();
    protected volatile zzaer zza;
    private volatile zzacp zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzadx)) {
            return false;
        }
        zzadx zzadxVar = (zzadx) obj;
        zzaer zzaerVar = this.zza;
        zzaer zzaerVar2 = zzadxVar.zza;
        if (zzaerVar == null && zzaerVar2 == null) {
            return zzb().equals(zzadxVar.zzb());
        }
        if (zzaerVar != null && zzaerVar2 != null) {
            return zzaerVar.equals(zzaerVar2);
        }
        if (zzaerVar != null) {
            zzadxVar.zzc(zzaerVar.zzw());
            return zzaerVar.equals(zzadxVar.zza);
        }
        zzc(zzaerVar2.zzw());
        return this.zza.equals(zzaerVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzacm) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzv();
        }
        return 0;
    }

    public final zzacp zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzacp.zzb;
            } else {
                this.zzc = this.zza.zzs();
            }
            return this.zzc;
        }
    }

    protected final void zzc(zzaer zzaerVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzaerVar;
                    this.zzc = zzacp.zzb;
                } catch (zzadu e) {
                    this.zza = zzaerVar;
                    this.zzc = zzacp.zzb;
                }
            }
        }
    }
}
