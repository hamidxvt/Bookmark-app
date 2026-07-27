package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzacx extends zzacf {
    private static final Logger zzb = Logger.getLogger(zzacx.class.getName());
    private static final boolean zzc = zzagd.zzx();
    zzacy zza;

    private zzacx() {
    }

    /* synthetic */ zzacx(zzacw zzacwVar) {
    }

    public static int zzA(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzB(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static zzacx zzC(byte[] bArr) {
        return new zzacu(bArr, 0, bArr.length);
    }

    public static int zzt(zzacp zzacpVar) {
        int zzd = zzacpVar.zzd();
        return zzA(zzd) + zzd;
    }

    @Deprecated
    static int zzu(int i, zzaer zzaerVar, zzafc zzafcVar) {
        int zzA = zzA(i << 3);
        int i2 = zzA + zzA;
        zzacc zzaccVar = (zzacc) zzaerVar;
        int zzr = zzaccVar.zzr();
        if (zzr == -1) {
            zzr = zzafcVar.zza(zzaccVar);
            zzaccVar.zzu(zzr);
        }
        return i2 + zzr;
    }

    public static int zzv(int i) {
        if (i >= 0) {
            return zzA(i);
        }
        return 10;
    }

    public static int zzw(zzadx zzadxVar) {
        int zza = zzadxVar.zza();
        return zzA(zza) + zza;
    }

    static int zzx(zzaer zzaerVar, zzafc zzafcVar) {
        zzacc zzaccVar = (zzacc) zzaerVar;
        int zzr = zzaccVar.zzr();
        if (zzr == -1) {
            zzr = zzafcVar.zza(zzaccVar);
            zzaccVar.zzu(zzr);
        }
        return zzA(zzr) + zzr;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = zzagh.zzc(str);
        } catch (zzagg e) {
            length = str.getBytes(zzads.zzb).length;
        }
        return zzA(length) + length;
    }

    public static int zzz(int i) {
        return zzA(i << 3);
    }

    public final void zzD() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zzE(String str, zzagg zzaggVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzaggVar);
        byte[] bytes = str.getBytes(zzads.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (zzacv e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzacv(e2);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzacp zzacpVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;
}
