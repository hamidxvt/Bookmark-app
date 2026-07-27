package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.common.base.Optional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzjz {
    private static volatile Optional zza = null;

    private zzjz() {
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:(2:12|13)|25|26|27|28|29|30|(1:32)(1:81)|33|(9:35|36|37|38|39|(2:40|(3:42|(3:57|58|59)(7:44|45|(2:47|(1:50))|51|(1:53)|54|55)|56)(1:60))|61|62|63)(1:80)|64|13) */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0071, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0072, code lost:
    
        android.util.Log.e("HermeticFileOverrides", "no data dir", r0);
        r0 = com.google.common.base.Optional.absent();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Optional zza(Context context) {
        Context createDeviceProtectedStorageContext;
        StrictMode.ThreadPolicy allowThreadDiskReads;
        Optional optional = zza;
        if (optional == null) {
            synchronized (zzjz.class) {
                optional = zza;
                if (optional == null) {
                    String str = Build.TYPE;
                    String str2 = Build.TAGS;
                    int i = zzkb.zza;
                    try {
                        if ((!str.equals("eng") && !str.equals("userdebug")) || (!str2.contains("dev-keys") && !str2.contains("test-keys"))) {
                            optional = Optional.absent();
                            zza = optional;
                        }
                        StrictMode.allowThreadDiskWrites();
                        char c = 0;
                        File file = new File(createDeviceProtectedStorageContext.getDir("phenotype_hermetic", 0), "overrides.txt");
                        Optional absent = file.exists() ? Optional.of(file) : Optional.absent();
                        if (absent.isPresent()) {
                            File file2 = (File) absent.get();
                            try {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                                try {
                                    SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
                                    HashMap hashMap = new HashMap();
                                    while (true) {
                                        String readLine = bufferedReader.readLine();
                                        if (readLine == null) {
                                            break;
                                        }
                                        String[] split = readLine.split(StringUtils.SPACE, 3);
                                        if (split.length != 3) {
                                            StringBuilder sb = new StringBuilder(readLine.length() + 9);
                                            sb.append("Invalid: ");
                                            sb.append(readLine);
                                            Log.e("HermeticFileOverrides", sb.toString());
                                        } else {
                                            String str3 = new String(split[c]);
                                            String decode = Uri.decode(new String(split[1]));
                                            String str4 = (String) hashMap.get(split[2]);
                                            if (str4 == null) {
                                                String str5 = new String(split[2]);
                                                str4 = Uri.decode(str5);
                                                if (str4.length() < 1024 || str4 == str5) {
                                                    hashMap.put(str5, str4);
                                                }
                                            }
                                            SimpleArrayMap simpleArrayMap2 = (SimpleArrayMap) simpleArrayMap.get(str3);
                                            if (simpleArrayMap2 == null) {
                                                simpleArrayMap2 = new SimpleArrayMap();
                                                simpleArrayMap.put(str3, simpleArrayMap2);
                                            }
                                            simpleArrayMap2.put(decode, str4);
                                            c = 0;
                                        }
                                    }
                                    String obj = file2.toString();
                                    String packageName = createDeviceProtectedStorageContext.getPackageName();
                                    StringBuilder sb2 = new StringBuilder(obj.length() + 28 + String.valueOf(packageName).length());
                                    sb2.append("Parsed ");
                                    sb2.append(obj);
                                    sb2.append(" for Android package ");
                                    sb2.append(packageName);
                                    Log.w("HermeticFileOverrides", sb2.toString());
                                    zzjt zzjtVar = new zzjt(simpleArrayMap);
                                    bufferedReader.close();
                                    optional = Optional.of(zzjtVar);
                                } finally {
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            optional = Optional.absent();
                        }
                        zza = optional;
                    } finally {
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    }
                    createDeviceProtectedStorageContext = (!zzjm.zza() || context.isDeviceProtectedStorage()) ? context : context.createDeviceProtectedStorageContext();
                    allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                }
            }
        }
        return optional;
    }
}
