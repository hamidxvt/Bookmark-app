package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.common.net.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzln implements Runnable {
    final /* synthetic */ zzlo zza;
    private final URL zzb;
    private final byte[] zzc;
    private final zzll zzd;
    private final String zze;
    private final Map zzf;

    public zzln(zzlo zzloVar, String str, URL url, byte[] bArr, Map map, zzll zzllVar) {
        Objects.requireNonNull(zzloVar);
        this.zza = zzloVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzllVar);
        this.zzb = url;
        this.zzc = bArr;
        this.zzd = zzllVar;
        this.zze = str;
        this.zzf = map;
    }

    private final void zzb(final int i, final Exception exc, final byte[] bArr, final Map map) {
        this.zza.zzu.zzaW().zzj(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzlm
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                zzln.this.zza(i, exc, bArr, map);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0160 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0136 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v25 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        HttpURLConnection httpURLConnection;
        OutputStream outputStream;
        Map map;
        OutputStream outputStream2;
        Map map2;
        int responseCode;
        InputStream inputStream;
        zzlo zzloVar = this.zza;
        zzloVar.zzaX();
        int i = 0;
        try {
            URL url = this.zzb;
            int i2 = com.google.android.gms.internal.measurement.zzcj.zzb;
            URLConnection openConnection = url.openConnection();
            if (!(openConnection instanceof HttpURLConnection)) {
                throw new IOException("Failed to obtain HTTP connection");
            }
            httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            zzic zzicVar = zzloVar.zzu;
            zzicVar.zzc();
            httpURLConnection.setConnectTimeout(60000);
            zzicVar.zzc();
            httpURLConnection.setReadTimeout(61000);
            httpURLConnection.setInstanceFollowRedirects(false);
            ?? r5 = 1;
            httpURLConnection.setDoInput(true);
            try {
                try {
                    Map map3 = this.zzf;
                    if (map3 != null) {
                        for (Map.Entry entry : map3.entrySet()) {
                            httpURLConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                    byte[] bArr = this.zzc;
                    if (bArr != null) {
                        try {
                            zzicVar.zzaU();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                            gZIPOutputStream.write(bArr);
                            gZIPOutputStream.close();
                            byteArrayOutputStream.close();
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            zzgs zzk = this.zza.zzu.zzaV().zzk();
                            int length = byteArray.length;
                            zzk.zzb("Uploading data. size", Integer.valueOf(length));
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.addRequestProperty(HttpHeaders.CONTENT_ENCODING, "gzip");
                            httpURLConnection.setFixedLengthStreamingMode(length);
                            httpURLConnection.connect();
                            OutputStream outputStream3 = httpURLConnection.getOutputStream();
                            try {
                                outputStream3.write(byteArray);
                                outputStream3.close();
                                r5 = outputStream3;
                            } catch (IOException e) {
                                e = e;
                                map2 = null;
                                outputStream2 = outputStream3;
                                if (outputStream2 != null) {
                                }
                                if (httpURLConnection != null) {
                                }
                                zzb(i, e, null, map2);
                            } catch (Throwable th) {
                                th = th;
                                map = null;
                                outputStream = outputStream3;
                                if (outputStream != null) {
                                }
                                if (httpURLConnection != null) {
                                }
                                zzb(i, null, null, map);
                                throw th;
                            }
                        } catch (IOException e2) {
                            this.zza.zzu.zzaV().zzb().zzb("Failed to gzip post request content", e2);
                            throw e2;
                        }
                    }
                    responseCode = httpURLConnection.getResponseCode();
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = null;
                    map = null;
                }
            } catch (IOException e3) {
                e = e3;
                outputStream2 = null;
                map2 = null;
            }
            try {
                try {
                    Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                    try {
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        inputStream = httpURLConnection.getInputStream();
                        try {
                            byte[] bArr2 = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr2);
                                if (read <= 0) {
                                    break;
                                } else {
                                    byteArrayOutputStream2.write(bArr2, 0, read);
                                }
                            }
                            byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            zzb(responseCode, null, byteArray2, headerFields);
                        } catch (Throwable th3) {
                            th = th3;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream = null;
                    }
                } catch (IOException e4) {
                    outputStream2 = null;
                    map2 = null;
                    i = responseCode;
                    e = e4;
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (IOException e5) {
                            this.zza.zzu.zzaV().zzb().zzc("Error closing HTTP compressed POST connection output stream. appId", zzgu.zzl(this.zze), e5);
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    zzb(i, e, null, map2);
                } catch (Throwable th5) {
                    outputStream = null;
                    map = null;
                    i = responseCode;
                    th = th5;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e6) {
                            this.zza.zzu.zzaV().zzb().zzc("Error closing HTTP compressed POST connection output stream. appId", zzgu.zzl(this.zze), e6);
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    zzb(i, null, null, map);
                    throw th;
                }
            } catch (IOException e7) {
                map2 = r5;
                outputStream2 = null;
                i = responseCode;
                e = e7;
                if (outputStream2 != null) {
                }
                if (httpURLConnection != null) {
                }
                zzb(i, e, null, map2);
            } catch (Throwable th6) {
                map = r5;
                outputStream = null;
                i = responseCode;
                th = th6;
                if (outputStream != null) {
                }
                if (httpURLConnection != null) {
                }
                zzb(i, null, null, map);
                throw th;
            }
        } catch (IOException e8) {
            e = e8;
            httpURLConnection = null;
            outputStream2 = null;
            map2 = null;
        } catch (Throwable th7) {
            th = th7;
            httpURLConnection = null;
            outputStream = null;
            map = null;
        }
    }

    final /* synthetic */ void zza(int i, Exception exc, byte[] bArr, Map map) {
        this.zzd.zza(this.zze, i, exc, bArr, map);
    }
}
