package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzln extends zzadk implements zzaes {
    private static final zzln zzb;
    private int zze;
    private int zzg;
    private int zzh;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private String zzf = "";
    private String zzi = "";

    static {
        zzln zzlnVar = new zzln();
        zzb = zzlnVar;
        zzadk.zzG(zzln.class, zzlnVar);
    }

    private zzln() {
    }

    public static zzli zza() {
        return (zzli) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzln zzlnVar, String str) {
        zzlnVar.zze |= 1;
        zzlnVar.zzf = str;
    }

    static /* synthetic */ void zze(zzln zzlnVar, int i) {
        zzlnVar.zze |= 2;
        zzlnVar.zzg = i;
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzlh zzlhVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003\u0005င\u0004\u0006ဌ\u0005\u0007ဌ\u0006\bဌ\u0007\tဌ\b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzlj.zza, "zzl", zzll.zza, "zzm", zzlk.zza, "zzn", zzlm.zza});
            case 3:
                return new zzln();
            case 4:
                return new zzli(zzlhVar);
            case 5:
                return zzb;
        }
    }
}
