package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzaej implements zzafd {
    private static final zzaep zza = new zzaeh();
    private final zzaep zzb;

    public zzaej() {
        zzaep zzaepVar;
        zzaep[] zzaepVarArr = new zzaep[2];
        zzaepVarArr[0] = zzadg.zza();
        try {
            zzaepVar = (zzaep) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            zzaepVar = zza;
        }
        zzaepVarArr[1] = zzaepVar;
        zzaei zzaeiVar = new zzaei(zzaepVarArr);
        zzads.zzf(zzaeiVar, "messageInfoFactory");
        this.zzb = zzaeiVar;
    }

    private static boolean zzb(zzaeo zzaeoVar) {
        return zzaeoVar.zzc() == 1;
    }

    @Override // com.google.android.libraries.places.internal.zzafd
    public final zzafc zza(Class cls) {
        zzafe.zzE(cls);
        zzaeo zzb = this.zzb.zzb(cls);
        return zzb.zzb() ? zzadk.class.isAssignableFrom(cls) ? zzaev.zzg(zzafe.zzB(), zzadc.zzb(), zzb.zza()) : zzaev.zzg(zzafe.zzz(), zzadc.zza(), zzb.zza()) : zzadk.class.isAssignableFrom(cls) ? zzb(zzb) ? zzaeu.zzg(cls, zzb, zzaex.zzb(), zzaef.zzd(), zzafe.zzB(), zzadc.zzb(), zzaen.zzb()) : zzaeu.zzg(cls, zzb, zzaex.zzb(), zzaef.zzd(), zzafe.zzB(), null, zzaen.zzb()) : zzb(zzb) ? zzaeu.zzg(cls, zzb, zzaex.zza(), zzaef.zzc(), zzafe.zzz(), zzadc.zza(), zzaen.zza()) : zzaeu.zzg(cls, zzb, zzaex.zza(), zzaef.zzc(), zzafe.zzA(), null, zzaen.zza());
    }
}
