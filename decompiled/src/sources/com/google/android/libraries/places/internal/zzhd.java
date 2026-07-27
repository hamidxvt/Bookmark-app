package com.google.android.libraries.places.internal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
abstract class zzhd extends zzgl {
    final CharSequence zzb;
    final zzgq zzc;
    int zzd = 0;
    int zze;

    protected zzhd(zzhe zzheVar, CharSequence charSequence) {
        zzgq zzgqVar;
        zzgqVar = zzheVar.zza;
        this.zzc = zzgqVar;
        this.zze = Integer.MAX_VALUE;
        this.zzb = "2.6.0";
    }

    @Override // com.google.android.libraries.places.internal.zzgl
    @CheckForNull
    protected final /* bridge */ /* synthetic */ Object zza() {
        int zzc;
        int i = this.zzd;
        while (true) {
            int i2 = this.zzd;
            if (i2 == -1) {
                zzb();
                return null;
            }
            int zzd = zzd(i2);
            if (zzd == -1) {
                zzd = this.zzb.length();
                this.zzd = -1;
                zzc = -1;
            } else {
                zzc = zzc(zzd);
                this.zzd = zzc;
            }
            if (zzc != i) {
                if (i < zzd) {
                    this.zzb.charAt(i);
                }
                if (i < zzd) {
                    this.zzb.charAt(zzd - 1);
                }
                int i3 = this.zze;
                if (i3 == 1) {
                    zzd = this.zzb.length();
                    this.zzd = -1;
                    if (zzd > i) {
                        this.zzb.charAt(zzd - 1);
                    }
                } else {
                    this.zze = i3 - 1;
                }
                return this.zzb.subSequence(i, zzd).toString();
            }
            int i4 = zzc + 1;
            this.zzd = i4;
            if (i4 > this.zzb.length()) {
                this.zzd = -1;
            }
        }
    }

    abstract int zzc(int i);

    abstract int zzd(int i);
}
