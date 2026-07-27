package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
class zzacm extends zzacl {
    protected final byte[] zza;

    zzacm(byte[] bArr) {
        if (bArr == null) {
            throw null;
        }
        this.zza = bArr;
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacp) || zzd() != ((zzacp) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzacm)) {
            return obj.equals(this);
        }
        zzacm zzacmVar = (zzacm) obj;
        int zzk = zzk();
        int zzk2 = zzacmVar.zzk();
        if (zzk != 0 && zzk2 != 0 && zzk != zzk2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzacmVar.zzd()) {
            int zzd2 = zzd();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(zzd);
            sb.append(zzd2);
            throw new IllegalArgumentException(sb.toString());
        }
        if (zzd > zzacmVar.zzd()) {
            int zzd3 = zzacmVar.zzd();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(zzd);
            sb2.append(", ");
            sb2.append(zzd3);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (!(zzacmVar instanceof zzacm)) {
            return zzacmVar.zzf(0, zzd).equals(zzf(0, zzd));
        }
        byte[] bArr = this.zza;
        byte[] bArr2 = zzacmVar.zza;
        zzacmVar.zzc();
        int i = 0;
        int i2 = 0;
        while (i < zzd) {
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    protected final int zze(int i, int i2, int i3) {
        return zzads.zzd(i, this.zza, 0, i3);
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public final zzacp zzf(int i, int i2) {
        zzj(0, i2, zzd());
        return i2 == 0 ? zzacp.zzb : new zzacj(this.zza, 0, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    protected final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    final void zzh(zzacf zzacfVar) throws IOException {
        ((zzacu) zzacfVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.libraries.places.internal.zzacp
    public final boolean zzi() {
        return zzagh.zze(this.zza, 0, zzd());
    }
}
