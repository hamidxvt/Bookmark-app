package com.google.android.gms.internal.common;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
/* loaded from: classes16.dex */
public final class zzl {
    public static Object zza(Class cls, String str, zzj... zzjVarArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return zzc(cls, "isIsolated", null, false, zzjVarArr);
    }

    public static Object zzb(String str, String str2, ClassLoader classLoader, zzj... zzjVarArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return zzc(classLoader.loadClass("com.google.android.gms.common.security.ProviderInstallerImpl"), "reportRequestStats2", null, false, zzjVarArr);
    }

    private static Object zzc(Class cls, String str, Object obj, boolean z, zzj... zzjVarArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int length = zzjVarArr.length;
        Class<?>[] clsArr = new Class[length];
        Object[] objArr = new Object[length];
        for (int i = 0; i < zzjVarArr.length; i++) {
            zzj zzjVar = zzjVarArr[i];
            if (zzjVar == null) {
                throw null;
            }
            clsArr[i] = zzjVar.zzc();
            objArr[i] = zzjVarArr[i].zzd();
        }
        return cls.getDeclaredMethod(str, clsArr).invoke(null, objArr);
    }
}
