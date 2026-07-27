package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzmb;
import com.google.android.gms.internal.measurement.zzmf;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
public abstract class zzmf<MessageType extends zzmf<MessageType, BuilderType>, BuilderType extends zzmb<MessageType, BuilderType>> extends zzks<MessageType, BuilderType> {
    private static final Map zzd = new ConcurrentHashMap();
    private int zzb = -1;
    protected zzoj zzc = zzoj.zza();

    private final int zzc(zznx zznxVar) {
        return zznu.zza().zzb(getClass()).zze(this);
    }

    static zzmf zzco(Class cls) {
        zzmf zzmfVar = (zzmf) zzd.get(cls);
        if (zzmfVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzmfVar = (zzmf) zzd.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzmfVar == null) {
            zzmfVar = (zzmf) ((zzmf) zzop.zzc(cls)).zzl(6, null, null);
            if (zzmfVar == null) {
                throw new IllegalStateException();
            }
            zzd.put(cls, zzmfVar);
        }
        return zzmfVar;
    }

    protected static void zzcp(Class cls, zzmf zzmfVar) {
        zzmfVar.zzcg();
        zzd.put(cls, zzmfVar);
    }

    protected static Object zzcq(zznm zznmVar, String str, Object[] objArr) {
        return new zznw(zznmVar, str, objArr);
    }

    static Object zzcr(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static zzmm zzcs() {
        return zzmg.zzd();
    }

    protected static zzmn zzct() {
        return zzna.zze();
    }

    protected static zzmn zzcu(zzmn zzmnVar) {
        int size = zzmnVar.size();
        return zzmnVar.zzg(size + size);
    }

    protected static zzmo zzcv() {
        return zznv.zzd();
    }

    protected static zzmo zzcw(zzmo zzmoVar) {
        int size = zzmoVar.size();
        return zzmoVar.zzg(size + size);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean zzd(zzmf zzmfVar, boolean z) {
        byte byteValue = ((Byte) zzmfVar.zzl(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zznu.zza().zzb(zzmfVar.getClass()).zzk(zzmfVar);
        if (z) {
            zzmfVar.zzl(2, true != zzk ? null : zzmfVar, null);
        }
        return zzk;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zznu.zza().zzb(getClass()).zzb(this, (zzmf) obj);
    }

    public final int hashCode() {
        if (zzcf()) {
            return zzci();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzci = zzci();
        this.zza = zzci;
        return zzci;
    }

    public final String toString() {
        return zzno.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zznm
    public final void zzcB(zzlm zzlmVar) throws IOException {
        zznu.zza().zzb(getClass()).zzf(this, zzln.zza(zzlmVar));
    }

    @Override // com.google.android.gms.internal.measurement.zznm
    public final /* synthetic */ zznl zzcC() {
        return (zzmb) zzl(5, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final boolean zzcD() {
        return zzd(this, true);
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final /* synthetic */ zznm zzcE() {
        return (zzmf) zzl(6, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    final int zzcd(zznx zznxVar) {
        if (zzcf()) {
            int zze = zznxVar.zze(this);
            if (zze >= 0) {
                return zze;
            }
            StringBuilder sb = new StringBuilder(String.valueOf(zze).length() + 42);
            sb.append("serialized size must be non-negative, was ");
            sb.append(zze);
            throw new IllegalStateException(sb.toString());
        }
        int i = this.zzb & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zze2 = zznxVar.zze(this);
        if (zze2 >= 0) {
            this.zzb = (this.zzb & Integer.MIN_VALUE) | zze2;
            return zze2;
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(zze2).length() + 42);
        sb2.append("serialized size must be non-negative, was ");
        sb2.append(zze2);
        throw new IllegalStateException(sb2.toString());
    }

    final boolean zzcf() {
        return (this.zzb & Integer.MIN_VALUE) != 0;
    }

    final void zzcg() {
        this.zzb &= Integer.MAX_VALUE;
    }

    final zzmf zzch() {
        return (zzmf) zzl(4, null, null);
    }

    final int zzci() {
        return zznu.zza().zzb(getClass()).zzc(this);
    }

    protected final void zzcj() {
        zznu.zza().zzb(getClass()).zzj(this);
        zzcg();
    }

    protected final zzmb zzck() {
        return (zzmb) zzl(5, null, null);
    }

    public final zzmb zzcl() {
        zzmb zzmbVar = (zzmb) zzl(5, null, null);
        zzmbVar.zzbd(this);
        return zzmbVar;
    }

    final void zzcm(int i) {
        this.zzb = (this.zzb & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    protected abstract Object zzl(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.measurement.zznm
    public final int zzcn() {
        int i;
        if (zzcf()) {
            i = zzc(null);
            if (i < 0) {
                StringBuilder sb = new StringBuilder(String.valueOf(i).length() + 42);
                sb.append("serialized size must be non-negative, was ");
                sb.append(i);
                throw new IllegalStateException(sb.toString());
            }
        } else {
            i = this.zzb & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zzc(null);
                if (i < 0) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(i).length() + 42);
                    sb2.append("serialized size must be non-negative, was ");
                    sb2.append(i);
                    throw new IllegalStateException(sb2.toString());
                }
                this.zzb = (this.zzb & Integer.MIN_VALUE) | i;
            }
        }
        return i;
    }
}
