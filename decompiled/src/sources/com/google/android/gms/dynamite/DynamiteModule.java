package com.google.android.gms.dynamite;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.firebase.analytics.FirebaseAnalytics;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
/* loaded from: classes16.dex */
public final class DynamiteModule {
    public static final int LOCAL = -1;
    public static final int NONE = 0;
    public static final int NO_SELECTION = 0;
    public static final int REMOTE = 1;
    private static Boolean zzb;
    private static String zzc;
    private static boolean zzd;
    private static zzp zzk;
    private static zzq zzl;
    private final Context zzj;
    private static int zze = -1;
    private static Boolean zzf = null;
    private static final ThreadLocal zzg = new ThreadLocal();
    private static final ThreadLocal zzh = new zzd();
    private static final VersionPolicy.IVersions zzi = new zze();
    public static final VersionPolicy PREFER_REMOTE = new zzf();
    public static final VersionPolicy PREFER_LOCAL = new zzg();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
    public static final VersionPolicy zza = new zzl();

    /* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, zzo zzoVar) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzo zzoVar) {
            super(str, th);
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
    public interface VersionPolicy {

        /* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
        public interface IVersions {
            int zza(Context context, String str);

            int zzb(Context context, String str, boolean z) throws LoadingException;
        }

        /* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzj = context;
    }

    public static int getLocalVersion(Context context, String moduleId) {
        try {
            Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + moduleId + ".ModuleDescriptor");
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), moduleId)) {
                return declaredField2.getInt(null);
            }
            Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + moduleId + "'");
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", "Local module descriptor class for " + moduleId + " not found.");
            return 0;
        } catch (Exception e2) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e2.getMessage())));
            return 0;
        }
    }

    public static int getRemoteVersion(Context context, String moduleId) {
        return zza(context, moduleId, false);
    }

    public static DynamiteModule load(Context context, VersionPolicy policy, String moduleId) throws LoadingException {
        DynamiteModule zzc2;
        Boolean bool;
        IObjectWrapper zzh2;
        zzq zzqVar;
        Boolean valueOf;
        IObjectWrapper zze2;
        Context applicationContext = context.getApplicationContext();
        zzo zzoVar = null;
        if (applicationContext == null) {
            throw new LoadingException("null application Context", zzoVar);
        }
        zzm zzmVar = (zzm) zzg.get();
        zzm zzmVar2 = new zzm(null);
        zzg.set(zzmVar2);
        long longValue = ((Long) zzh.get()).longValue();
        try {
            zzh.set(Long.valueOf(SystemClock.uptimeMillis()));
            VersionPolicy.SelectionResult selectModule = policy.selectModule(context, moduleId, zzi);
            Log.i("DynamiteModule", "Considering local module " + moduleId + ":" + selectModule.localVersion + " and remote module " + moduleId + ":" + selectModule.remoteVersion);
            int i = selectModule.selection;
            if (i != 0) {
                if (i == -1) {
                    if (selectModule.localVersion != 0) {
                        i = -1;
                    }
                }
                if (i != 1 || selectModule.remoteVersion != 0) {
                    if (i == -1) {
                        zzc2 = zzc(applicationContext, moduleId);
                    } else {
                        if (i != 1) {
                            throw new LoadingException("VersionPolicy returned invalid code:" + i, zzoVar);
                        }
                        try {
                            int i2 = selectModule.remoteVersion;
                            try {
                                synchronized (DynamiteModule.class) {
                                    if (!zzf(context)) {
                                        throw new LoadingException("Remote loading disabled", zzoVar);
                                    }
                                    bool = zzb;
                                }
                                if (bool == null) {
                                    throw new LoadingException("Failed to determine which loading route to use.", zzoVar);
                                }
                                if (bool.booleanValue()) {
                                    Log.i("DynamiteModule", "Selected remote version of " + moduleId + ", version >= " + i2);
                                    synchronized (DynamiteModule.class) {
                                        zzqVar = zzl;
                                    }
                                    if (zzqVar == null) {
                                        throw new LoadingException("DynamiteLoaderV2 was not cached.", zzoVar);
                                    }
                                    zzm zzmVar3 = (zzm) zzg.get();
                                    if (zzmVar3 == null || zzmVar3.zza == null) {
                                        throw new LoadingException("No result cursor", zzoVar);
                                    }
                                    Context applicationContext2 = context.getApplicationContext();
                                    Cursor cursor = zzmVar3.zza;
                                    ObjectWrapper.wrap(null);
                                    synchronized (DynamiteModule.class) {
                                        valueOf = Boolean.valueOf(zze >= 2);
                                    }
                                    if (valueOf.booleanValue()) {
                                        Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                        zze2 = zzqVar.zzf(ObjectWrapper.wrap(applicationContext2), moduleId, i2, ObjectWrapper.wrap(cursor));
                                    } else {
                                        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                        zze2 = zzqVar.zze(ObjectWrapper.wrap(applicationContext2), moduleId, i2, ObjectWrapper.wrap(cursor));
                                    }
                                    Context context2 = (Context) ObjectWrapper.unwrap(zze2);
                                    if (context2 == null) {
                                        throw new LoadingException("Failed to get module context", zzoVar);
                                    }
                                    zzc2 = new DynamiteModule(context2);
                                } else {
                                    Log.i("DynamiteModule", "Selected remote version of " + moduleId + ", version >= " + i2);
                                    zzp zzg2 = zzg(context);
                                    if (zzg2 == null) {
                                        throw new LoadingException("Failed to create IDynamiteLoader.", zzoVar);
                                    }
                                    int zze3 = zzg2.zze();
                                    if (zze3 >= 3) {
                                        zzm zzmVar4 = (zzm) zzg.get();
                                        if (zzmVar4 == null) {
                                            throw new LoadingException("No cached result cursor holder", zzoVar);
                                        }
                                        zzh2 = zzg2.zzi(ObjectWrapper.wrap(context), moduleId, i2, ObjectWrapper.wrap(zzmVar4.zza));
                                    } else if (zze3 == 2) {
                                        Log.w("DynamiteModule", "IDynamite loader version = 2");
                                        zzh2 = zzg2.zzj(ObjectWrapper.wrap(context), moduleId, i2);
                                    } else {
                                        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                        zzh2 = zzg2.zzh(ObjectWrapper.wrap(context), moduleId, i2);
                                    }
                                    Object unwrap = ObjectWrapper.unwrap(zzh2);
                                    if (unwrap == null) {
                                        throw new LoadingException("Failed to load remote module.", zzoVar);
                                    }
                                    zzc2 = new DynamiteModule((Context) unwrap);
                                }
                            } catch (RemoteException e) {
                                throw new LoadingException("Failed to load remote module.", e, zzoVar);
                            } catch (LoadingException e2) {
                                throw e2;
                            } catch (Throwable th) {
                                CrashUtils.addDynamiteErrorToDropBox(context, th);
                                throw new LoadingException("Failed to load remote module.", th, zzoVar);
                            }
                        } catch (LoadingException e3) {
                            Log.w("DynamiteModule", "Failed to load remote module: " + e3.getMessage());
                            int i3 = selectModule.localVersion;
                            if (i3 == 0 || policy.selectModule(context, moduleId, new zzn(i3, 0)).selection != -1) {
                                throw new LoadingException("Remote load failed. No local fallback found.", e3, zzoVar);
                            }
                            zzc2 = zzc(applicationContext, moduleId);
                        }
                    }
                    return zzc2;
                }
            }
            throw new LoadingException("No acceptable module " + moduleId + " found. Local version is " + selectModule.localVersion + " and remote version is " + selectModule.remoteVersion + ".", zzoVar);
        } finally {
            if (longValue == 0) {
                zzh.remove();
            } else {
                zzh.set(Long.valueOf(longValue));
            }
            Cursor cursor2 = zzmVar2.zza;
            if (cursor2 != null) {
                cursor2.close();
            }
            zzg.set(zzmVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x01cd A[Catch: all -> 0x01d4, TryCatch #10 {all -> 0x01d4, blocks: (B:3:0x0002, B:9:0x00e6, B:67:0x00ec, B:11:0x010d, B:40:0x0166, B:29:0x0175, B:53:0x01cd, B:54:0x01d0, B:48:0x01c3, B:71:0x00f2, B:131:0x01d3, B:5:0x0003, B:74:0x0009, B:75:0x0025, B:82:0x00e3, B:87:0x0048, B:105:0x00a3, B:113:0x00a6, B:125:0x00c1, B:8:0x00e5, B:128:0x00c7), top: B:2:0x0002, inners: #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[Catch: all -> 0x01d4, SYNTHETIC, TRY_LEAVE, TryCatch #10 {all -> 0x01d4, blocks: (B:3:0x0002, B:9:0x00e6, B:67:0x00ec, B:11:0x010d, B:40:0x0166, B:29:0x0175, B:53:0x01cd, B:54:0x01d0, B:48:0x01c3, B:71:0x00f2, B:131:0x01d3, B:5:0x0003, B:74:0x0009, B:75:0x0025, B:82:0x00e3, B:87:0x0048, B:105:0x00a3, B:113:0x00a6, B:125:0x00c1, B:8:0x00e5, B:128:0x00c7), top: B:2:0x0002, inners: #8, #9 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x01c6 -> B:23:0x01c8). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int zza(Context context, String str, boolean z) {
        Field declaredField;
        Throwable th;
        RemoteException e;
        int zze2;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = zzb;
                int i = 0;
                if (bool == null) {
                    try {
                        declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
                        Log.w("DynamiteModule", "Failed to load module via V2: " + e2.toString());
                        bool = Boolean.FALSE;
                    }
                    synchronized (declaredField.getDeclaringClass()) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader == ClassLoader.getSystemClassLoader()) {
                            bool = Boolean.FALSE;
                        } else if (classLoader != null) {
                            try {
                                zzd(classLoader);
                            } catch (LoadingException e3) {
                            }
                            bool = Boolean.TRUE;
                        } else {
                            if (!zzf(context)) {
                                return 0;
                            }
                            if (zzd || Boolean.TRUE.equals(null)) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    int zzb2 = zzb(context, str, z, true);
                                    String str2 = zzc;
                                    if (str2 != null && !str2.isEmpty()) {
                                        ClassLoader zza2 = zzb.zza();
                                        if (zza2 == null) {
                                            if (Build.VERSION.SDK_INT >= 29) {
                                                String str3 = zzc;
                                                Preconditions.checkNotNull(str3);
                                                String str4 = str3;
                                                zza2 = new DelegateLastClassLoader(str3, ClassLoader.getSystemClassLoader());
                                            } else {
                                                String str5 = zzc;
                                                Preconditions.checkNotNull(str5);
                                                String str6 = str5;
                                                zza2 = new zzc(str5, ClassLoader.getSystemClassLoader());
                                            }
                                        }
                                        zzd(zza2);
                                        declaredField.set(null, zza2);
                                        zzb = Boolean.TRUE;
                                        return zzb2;
                                    }
                                    return zzb2;
                                } catch (LoadingException e4) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                }
                            }
                        }
                        zzb = bool;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return zzb(context, str, z, false);
                    } catch (LoadingException e5) {
                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e5.getMessage());
                        return 0;
                    }
                }
                zzp zzg2 = zzg(context);
                if (zzg2 != null) {
                    try {
                        zze2 = zzg2.zze();
                    } catch (RemoteException e6) {
                        e = e6;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    if (zze2 >= 3) {
                        zzm zzmVar = (zzm) zzg.get();
                        if (zzmVar == null || (cursor = zzmVar.zza) == null) {
                            Cursor cursor2 = (Cursor) ObjectWrapper.unwrap(zzg2.zzk(ObjectWrapper.wrap(context), str, z, ((Long) zzh.get()).longValue()));
                            if (cursor2 != null) {
                                try {
                                    if (cursor2.moveToFirst()) {
                                        int i2 = cursor2.getInt(0);
                                        r2 = (i2 <= 0 || !zze(cursor2)) ? cursor2 : null;
                                        if (r2 != null) {
                                            r2.close();
                                        }
                                        i = i2;
                                    }
                                } catch (RemoteException e7) {
                                    e = e7;
                                    r2 = cursor2;
                                    try {
                                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e.getMessage());
                                        if (r2 != null) {
                                            r2.close();
                                        }
                                        return i;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        if (r2 != null) {
                                            throw th;
                                        }
                                        r2.close();
                                        throw th;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    r2 = cursor2;
                                    if (r2 != null) {
                                    }
                                }
                            }
                            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                        } else {
                            i = cursor.getInt(0);
                        }
                    } else if (zze2 == 2) {
                        Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                        i = zzg2.zzg(ObjectWrapper.wrap(context), str, z);
                    } else {
                        Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                        i = zzg2.zzf(ObjectWrapper.wrap(context), str, z);
                    }
                }
                return i;
            }
        } catch (Throwable th5) {
            CrashUtils.addDynamiteErrorToDropBox(context, th5);
            throw th5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x015b A[Catch: all -> 0x0177, TryCatch #5 {all -> 0x0177, blocks: (B:49:0x0157, B:51:0x015b, B:52:0x015c, B:53:0x0176), top: B:48:0x0157 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x015c A[Catch: all -> 0x0177, TryCatch #5 {all -> 0x0177, blocks: (B:49:0x0157, B:51:0x015b, B:52:0x015c, B:53:0x0176), top: B:48:0x0157 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x017b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int zzb(Context context, String str, boolean z, boolean z2) throws LoadingException {
        MatrixCursor matrixCursor;
        MatrixCursor matrixCursor2 = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        try {
            boolean z3 = true;
            Uri build = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority("com.google.android.gms.chimera").path(true != z ? "api" : "api_force_staging").appendPath(str).appendQueryParameter("requestStartUptime", String.valueOf(((Long) zzh.get()).longValue())).build();
            ContentProviderClient acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(build);
            boolean z4 = false;
            if (acquireUnstableContentProviderClient == null) {
                matrixCursor = null;
            } else {
                try {
                    Cursor query = acquireUnstableContentProviderClient.query(build, null, null, null, null);
                    if (query != null) {
                        try {
                            int count = query.getCount();
                            int columnCount = query.getColumnCount();
                            matrixCursor = new MatrixCursor(query.getColumnNames(), count);
                            for (int i = 0; i < count; i++) {
                                if (!query.moveToPosition(i)) {
                                    throw new RemoteException("Cursor read incomplete (ContentProvider dead?)");
                                }
                                Object[] objArr4 = new Object[columnCount];
                                for (int i2 = 0; i2 < columnCount; i2++) {
                                    switch (query.getType(i2)) {
                                        case 0:
                                            objArr4[i2] = null;
                                            break;
                                        case 1:
                                            objArr4[i2] = Long.valueOf(query.getLong(i2));
                                            break;
                                        case 2:
                                            objArr4[i2] = Double.valueOf(query.getDouble(i2));
                                            break;
                                        case 3:
                                            objArr4[i2] = query.getString(i2);
                                            break;
                                        case 4:
                                            objArr4[i2] = query.getBlob(i2);
                                            break;
                                        default:
                                            throw new RemoteException("Unknown column type");
                                    }
                                }
                                matrixCursor.addRow(objArr4);
                            }
                            query.close();
                            acquireUnstableContentProviderClient.release();
                        } finally {
                        }
                    }
                } catch (RemoteException e) {
                } catch (Throwable th) {
                    acquireUnstableContentProviderClient.release();
                    throw th;
                }
                acquireUnstableContentProviderClient.release();
                matrixCursor = null;
            }
            if (matrixCursor != null) {
                try {
                    if (matrixCursor.moveToFirst()) {
                        int i3 = matrixCursor.getInt(0);
                        if (i3 > 0) {
                            synchronized (DynamiteModule.class) {
                                zzc = matrixCursor.getString(2);
                                int columnIndex = matrixCursor.getColumnIndex("loaderVersion");
                                if (columnIndex >= 0) {
                                    zze = matrixCursor.getInt(columnIndex);
                                }
                                int columnIndex2 = matrixCursor.getColumnIndex("disableStandaloneDynamiteLoader2");
                                if (columnIndex2 >= 0) {
                                    if (matrixCursor.getInt(columnIndex2) == 0) {
                                        z3 = false;
                                    }
                                    zzd = z3;
                                    z4 = z3;
                                }
                            }
                            if (zze(matrixCursor)) {
                                matrixCursor = null;
                            }
                        }
                        if (!z2 || !z4) {
                            if (matrixCursor != null) {
                                matrixCursor.close();
                            }
                            return i3;
                        }
                        try {
                            throw new LoadingException("forcing fallback to container DynamiteLoader impl", objArr2 == true ? 1 : 0);
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                if (e instanceof LoadingException) {
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                matrixCursor2 = matrixCursor;
                                if (matrixCursor2 != null) {
                                    matrixCursor2.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            matrixCursor2 = matrixCursor;
                            if (matrixCursor2 != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    if (e instanceof LoadingException) {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    matrixCursor2 = matrixCursor;
                    if (matrixCursor2 != null) {
                    }
                    throw th;
                }
            }
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new LoadingException("Failed to connect to dynamite module ContentResolver.", objArr3 == true ? 1 : 0);
        } catch (Exception e4) {
            e = e4;
            matrixCursor = null;
            if (e instanceof LoadingException) {
                throw e;
            }
            throw new LoadingException("V2 version check failed: " + e.getMessage(), e, objArr == true ? 1 : 0);
        } catch (Throwable th5) {
            th = th5;
            if (matrixCursor2 != null) {
            }
            throw th;
        }
    }

    private static DynamiteModule zzc(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    private static void zzd(ClassLoader classLoader) throws LoadingException {
        zzq zzqVar;
        zzo zzoVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzqVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzqVar = queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzq(iBinder);
            }
            zzl = zzqVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzoVar);
        }
    }

    private static boolean zze(Cursor cursor) {
        zzm zzmVar = (zzm) zzg.get();
        if (zzmVar == null || zzmVar.zza != null) {
            return false;
        }
        zzmVar.zza = cursor;
        return true;
    }

    private static boolean zzf(Context context) {
        if (Boolean.TRUE.equals(null) || Boolean.TRUE.equals(zzf)) {
            return true;
        }
        boolean z = false;
        if (zzf == null) {
            ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", true != PlatformVersion.isAtLeastQ() ? 0 : 268435456);
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 10000000) == 0 && resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName)) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            zzf = valueOf;
            z = valueOf.booleanValue();
            if (z && resolveContentProvider.applicationInfo != null && (resolveContentProvider.applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                zzd = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    private static zzp zzg(Context context) {
        zzp zzpVar;
        synchronized (DynamiteModule.class) {
            zzp zzpVar2 = zzk;
            if (zzpVar2 != null) {
                return zzpVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzpVar = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzpVar = queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzp(iBinder);
                }
                if (zzpVar != null) {
                    zzk = zzpVar;
                    return zzpVar;
                }
            } catch (Exception e) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e.getMessage());
            }
            return null;
        }
    }

    public Context getModuleContext() {
        return this.zzj;
    }

    public IBinder instantiate(String className) throws LoadingException {
        try {
            return (IBinder) this.zzj.getClassLoader().loadClass(className).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(className)), e, null);
        }
    }
}
