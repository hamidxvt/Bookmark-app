package com.google.android.gms.internal.measurement;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzjr implements zzjv {
    private final ContentResolver zzc;
    private final Uri zzd;
    private final Runnable zze;
    private volatile Map zzi;
    private static final ConcurrentMap zzb = new ConcurrentHashMap();
    public static final String[] zza = {"key", "value"};
    private ContentObserver zzf = null;
    private volatile boolean zzg = true;
    private final Object zzh = new Object();
    private final List zzj = new ArrayList();

    private zzjr(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        Preconditions.checkNotNull(contentResolver);
        Preconditions.checkNotNull(uri);
        this.zzc = contentResolver;
        this.zzd = uri;
        this.zze = runnable;
    }

    public static zzjr zza(final ContentResolver contentResolver, final Uri uri, final Runnable runnable) {
        zzjr zzjrVar = (zzjr) zzb.computeIfAbsent(uri, new Function() { // from class: com.google.android.gms.internal.measurement.zzjq
            @Override // java.util.function.Function
            public final /* synthetic */ Object apply(Object obj) {
                return zzjr.zzf(contentResolver, uri, runnable, (Uri) obj);
            }
        });
        try {
            if (zzjrVar.zzg) {
                synchronized (zzjrVar) {
                    if (zzjrVar.zzg) {
                        zzjo zzjoVar = new zzjo(zzjrVar, null);
                        zzjrVar.zzc.registerContentObserver(zzjrVar.zzd, false, zzjoVar);
                        zzjrVar.zzf = zzjoVar;
                        zzjrVar.zzg = false;
                    }
                }
            }
            return zzjrVar;
        } catch (SecurityException e) {
            return null;
        }
    }

    static void zzd() {
        Iterator it = zzb.values().iterator();
        while (it.hasNext()) {
            zzjr zzjrVar = (zzjr) it.next();
            synchronized (zzjrVar) {
                if (zzjrVar.zzg) {
                    zzjrVar.zzg = false;
                } else {
                    ContentObserver contentObserver = zzjrVar.zzf;
                    if (contentObserver != null) {
                        zzjrVar.zzc.unregisterContentObserver(contentObserver);
                        zzjrVar.zzf = null;
                    }
                }
            }
            it.remove();
        }
    }

    static /* synthetic */ zzjr zzf(ContentResolver contentResolver, Uri uri, Runnable runnable, Uri uri2) {
        return new zzjr(contentResolver, uri, runnable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Map zzb() {
        Map map;
        Map emptyMap;
        Map map2 = this.zzi;
        Map map3 = map2;
        if (map2 == null) {
            synchronized (this.zzh) {
                Map map4 = this.zzi;
                map = map4;
                if (map4 == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        try {
                            emptyMap = (Map) zzjv.zzh(new zzju() { // from class: com.google.android.gms.internal.measurement.zzjp
                                @Override // com.google.android.gms.internal.measurement.zzju
                                public final /* synthetic */ Object zza() {
                                    return zzjr.this.zzg();
                                }
                            });
                        } finally {
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                        }
                    } catch (SQLiteException | IllegalStateException | SecurityException e) {
                        Log.w("ConfigurationContentLdr", "Unable to query ContentProvider, using default values", e);
                        emptyMap = Collections.emptyMap();
                    }
                    this.zzi = emptyMap;
                    allowThreadDiskReads = emptyMap;
                    map = allowThreadDiskReads;
                }
            }
            map3 = map;
        }
        return map3 != null ? map3 : Collections.emptyMap();
    }

    public final void zzc() {
        synchronized (this.zzh) {
            this.zzi = null;
            this.zze.run();
        }
        synchronized (this) {
            Iterator it = this.zzj.iterator();
            while (it.hasNext()) {
                ((zzjs) it.next()).zza();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final /* bridge */ /* synthetic */ Object zze(String str) {
        return (String) zzb().get(str);
    }

    final /* synthetic */ Map zzg() {
        Map emptyMap;
        Cursor query;
        Map emptyMap2;
        ContentResolver contentResolver = this.zzc;
        Uri uri = this.zzd;
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
        try {
            if (acquireUnstableContentProviderClient == null) {
                Log.w("ConfigurationContentLdr", "Unable to acquire ContentProviderClient, using default values");
                return Collections.emptyMap();
            }
            try {
                query = acquireUnstableContentProviderClient.query(uri, zza, null, null, null);
            } catch (RemoteException e) {
                Log.w("ConfigurationContentLdr", "ContentProvider query failed, using default values", e);
                emptyMap = Collections.emptyMap();
            }
            try {
                if (query == null) {
                    Log.w("ConfigurationContentLdr", "ContentProvider query returned null cursor, using default values");
                    emptyMap = Collections.emptyMap();
                    acquireUnstableContentProviderClient.release();
                    return emptyMap;
                }
                int count = query.getCount();
                if (count == 0) {
                    emptyMap2 = Collections.emptyMap();
                } else {
                    Map arrayMap = count <= 256 ? new ArrayMap(count) : new HashMap(count, 1.0f);
                    while (query.moveToNext()) {
                        arrayMap.put(query.getString(0), query.getString(1));
                    }
                    if (query.isAfterLast()) {
                        query.close();
                        acquireUnstableContentProviderClient.release();
                        return arrayMap;
                    }
                    Log.w("ConfigurationContentLdr", "Cursor read incomplete (ContentProvider dead?), using default values");
                    emptyMap2 = Collections.emptyMap();
                }
                query.close();
                acquireUnstableContentProviderClient.release();
                return emptyMap2;
            } catch (Throwable th) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            acquireUnstableContentProviderClient.release();
            throw th3;
        }
    }
}
