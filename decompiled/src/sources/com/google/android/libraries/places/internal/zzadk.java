package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzadh;
import com.google.android.libraries.places.internal.zzadk;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzadk<MessageType extends zzadk<MessageType, BuilderType>, BuilderType extends zzadh<MessageType, BuilderType>> extends zzacc<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzafu zzc = zzafu.zzc();
    protected int zzd = -1;

    protected static zzadq zzA() {
        return zzaeg.zze();
    }

    protected static zzadr zzB() {
        return zzafa.zzd();
    }

    protected static zzadr zzC(zzadr zzadrVar) {
        int size = zzadrVar.size();
        return zzadrVar.zzf(size == 0 ? 10 : size + size);
    }

    static Object zzE(Method method, Object obj, Object... objArr) {
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

    protected static Object zzF(zzaer zzaerVar, String str, Object[] objArr) {
        return new zzafb(zzaerVar, str, objArr);
    }

    protected static void zzG(Class cls, zzadk zzadkVar) {
        zzb.put(cls, zzadkVar);
    }

    static zzadk zzy(Class cls) {
        zzadk zzadkVar = (zzadk) zzb.get(cls);
        if (zzadkVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzadkVar = (zzadk) zzb.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzadkVar == null) {
            zzadkVar = (zzadk) ((zzadk) zzagd.zze(cls)).zzb(6, null, null);
            if (zzadkVar == null) {
                throw new IllegalStateException();
            }
            zzb.put(cls, zzadkVar);
        }
        return zzadkVar;
    }

    protected static zzado zzz() {
        return zzadl.zze();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzaez.zza().zzb(getClass()).zze(this, (zzadk) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzb2 = zzaez.zza().zzb(getClass()).zzb(this);
        this.zza = zzb2;
        return zzb2;
    }

    public final String toString() {
        return zzaet.zza(this, super.toString());
    }

    @Override // com.google.android.libraries.places.internal.zzaer
    public final /* synthetic */ zzaeq zzD() {
        zzadh zzadhVar = (zzadh) zzb(5, null, null);
        zzadhVar.zzs(this);
        return zzadhVar;
    }

    @Override // com.google.android.libraries.places.internal.zzaer
    public final void zzH(zzacx zzacxVar) throws IOException {
        zzaez.zza().zzb(getClass()).zzi(this, zzacy.zza(zzacxVar));
    }

    protected abstract Object zzb(int i, Object obj, Object obj2);

    @Override // com.google.android.libraries.places.internal.zzacc
    final int zzr() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.internal.zzacc
    final void zzu(int i) {
        this.zzd = i;
    }

    @Override // com.google.android.libraries.places.internal.zzaer
    public final int zzv() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int zza = zzaez.zza().zzb(getClass()).zza(this);
        this.zzd = zza;
        return zza;
    }

    @Override // com.google.android.libraries.places.internal.zzaes
    public final /* synthetic */ zzaer zzw() {
        return (zzadk) zzb(6, null, null);
    }

    protected final zzadh zzx() {
        return (zzadh) zzb(5, null, null);
    }
}
