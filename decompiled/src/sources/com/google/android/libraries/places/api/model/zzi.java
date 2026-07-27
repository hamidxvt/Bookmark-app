package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzi extends zzbd {
    private int zza;
    private int zzb;
    private byte zzc;

    zzi() {
    }

    final zzbd zza(int i) {
        this.zza = i;
        this.zzc = (byte) (this.zzc | 1);
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.zzbd
    final zzbd zzb(int i) {
        this.zzb = i;
        this.zzc = (byte) (this.zzc | 2);
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.zzbd
    final LocalTime zzc() {
        if (this.zzc == 3) {
            return new zzaj(this.zza, this.zzb);
        }
        StringBuilder sb = new StringBuilder();
        if ((this.zzc & 1) == 0) {
            sb.append(" hours");
        }
        if ((this.zzc & 2) == 0) {
            sb.append(" minutes");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
