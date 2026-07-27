package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzafb implements zzaeo {
    private final zzaer zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    zzafb(zzaer zzaerVar, String str, Object[] objArr) {
        this.zza = zzaerVar;
        this.zzb = str;
        this.zzc = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.zzd = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 < 55296) {
                this.zzd = i | (charAt2 << i2);
                return;
            } else {
                i |= (charAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
        }
    }

    @Override // com.google.android.libraries.places.internal.zzaeo
    public final zzaer zza() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzaeo
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }

    @Override // com.google.android.libraries.places.internal.zzaeo
    public final int zzc() {
        return (this.zzd & 1) == 1 ? 1 : 2;
    }

    final String zzd() {
        return this.zzb;
    }

    final Object[] zze() {
        return this.zzc;
    }
}
