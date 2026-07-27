package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import androidx.concurrent.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* loaded from: classes.dex */
public final class ProfileVerifier {
    private static final String CUR_PROFILES_BASE_DIR = "/data/misc/profiles/cur/0/";
    private static final String PROFILE_FILE_NAME = "primary.prof";
    private static final String PROFILE_INSTALLED_CACHE_FILE_NAME = "profileInstalled";
    private static final String REF_PROFILES_BASE_DIR = "/data/misc/profiles/ref/";
    private static final String TAG = "ProfileVerifier";
    private static final ResolvableFuture<CompilationStatus> sFuture = ResolvableFuture.create();
    private static final Object SYNC_OBJ = new Object();
    private static CompilationStatus sCompilationStatus = null;

    private ProfileVerifier() {
    }

    public static CompilationStatus writeProfileVerification(Context context) {
        return writeProfileVerification(context, false);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(19:38|39|40|41|(3:83|84|85)|43|(11:50|(1:54)|(1:75)(2:59|(6:61|62|(2:69|70)|66|67|68))|74|62|(1:64)|69|70|66|67|68)|(1:79)(1:(1:81)(1:82))|(1:54)|(1:56)|75|74|62|(0)|69|70|66|67|68) */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0123, code lost:
    
        r4 = androidx.profileinstaller.ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00e1, code lost:
    
        r14 = androidx.profileinstaller.ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_NO_PROFILE_EMBEDDED;
     */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0118 A[Catch: all -> 0x0045, TRY_LEAVE, TryCatch #1 {, blocks: (B:121:0x000e, B:123:0x0012, B:124:0x0014, B:12:0x001a, B:20:0x0032, B:22:0x004b, B:24:0x0051, B:27:0x0059, B:32:0x007e, B:39:0x00a3, B:41:0x00aa, B:84:0x00bd, B:45:0x00d1, B:47:0x00d7, B:50:0x00dc, B:56:0x00f6, B:59:0x00fc, B:62:0x0108, B:64:0x0118, B:66:0x0127, B:67:0x012b, B:70:0x011e, B:89:0x00c8, B:90:0x00cc, B:94:0x012e, B:95:0x0136, B:101:0x0138, B:102:0x013e, B:116:0x0044, B:115:0x0041), top: B:120:0x000e, inners: #2, #5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static CompilationStatus writeProfileVerification(Context context, boolean forceVerifyCurrentProfile) {
        boolean hasEmbeddedProfile;
        int resultCode;
        int resultCode2;
        int resultCode3;
        Cache newCache;
        AssetFileDescriptor afd;
        if (!forceVerifyCurrentProfile && sCompilationStatus != null) {
            return sCompilationStatus;
        }
        synchronized (SYNC_OBJ) {
            if (!forceVerifyCurrentProfile) {
                if (sCompilationStatus != null) {
                    return sCompilationStatus;
                }
            }
            boolean z = false;
            try {
                afd = context.getAssets().openFd("dexopt/baseline.prof");
            } catch (IOException e) {
                hasEmbeddedProfile = false;
            }
            try {
                boolean hasEmbeddedProfile2 = afd.getLength() > 0;
                if (afd != null) {
                    afd.close();
                }
                hasEmbeddedProfile = hasEmbeddedProfile2;
                if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT != 30) {
                    File referenceProfileFile = new File(new File(REF_PROFILES_BASE_DIR, context.getPackageName()), PROFILE_FILE_NAME);
                    long referenceProfileSize = referenceProfileFile.length();
                    boolean hasReferenceProfile = referenceProfileFile.exists() && referenceProfileSize > 0;
                    File currentProfileFile = new File(new File(CUR_PROFILES_BASE_DIR, context.getPackageName()), PROFILE_FILE_NAME);
                    long currentProfileSize = currentProfileFile.length();
                    if (currentProfileFile.exists() && currentProfileSize > 0) {
                        z = true;
                    }
                    boolean hasCurrentProfile = z;
                    try {
                        long packageLastUpdateTime = getPackageLastUpdateTime(context);
                        File cacheFile = new File(context.getFilesDir(), PROFILE_INSTALLED_CACHE_FILE_NAME);
                        Cache currentCache = null;
                        if (cacheFile.exists()) {
                            try {
                                currentCache = Cache.readFromFile(cacheFile);
                            } catch (IOException e2) {
                                return setCompilationStatus(131072, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
                            }
                        }
                        if (currentCache != null && currentCache.mPackageLastUpdateTime == packageLastUpdateTime && currentCache.mResultCode != 2) {
                            resultCode = currentCache.mResultCode;
                            if (forceVerifyCurrentProfile && hasCurrentProfile && resultCode != 1) {
                                resultCode = 2;
                            }
                            if (currentCache != null || currentCache.mResultCode != 2 || resultCode != 1) {
                                resultCode2 = resultCode;
                            } else {
                                resultCode2 = resultCode;
                                if (referenceProfileSize < currentCache.mInstalledCurrentProfileSize) {
                                    resultCode3 = 3;
                                    newCache = new Cache(1, resultCode3, packageLastUpdateTime, currentProfileSize);
                                    if (currentCache != null || !currentCache.equals(newCache)) {
                                        newCache.writeOnFile(cacheFile);
                                    }
                                    return setCompilationStatus(resultCode3, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
                                }
                            }
                            resultCode3 = resultCode2;
                            newCache = new Cache(1, resultCode3, packageLastUpdateTime, currentProfileSize);
                            if (currentCache != null) {
                            }
                            newCache.writeOnFile(cacheFile);
                            return setCompilationStatus(resultCode3, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
                        }
                        if (hasReferenceProfile) {
                            resultCode = 1;
                        } else if (hasCurrentProfile) {
                            resultCode = 2;
                        } else {
                            resultCode = 0;
                        }
                        if (forceVerifyCurrentProfile) {
                            resultCode = 2;
                        }
                        if (currentCache != null) {
                        }
                        resultCode2 = resultCode;
                        resultCode3 = resultCode2;
                        newCache = new Cache(1, resultCode3, packageLastUpdateTime, currentProfileSize);
                        if (currentCache != null) {
                        }
                        newCache.writeOnFile(cacheFile);
                        return setCompilationStatus(resultCode3, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
                    } catch (PackageManager.NameNotFoundException e3) {
                        return setCompilationStatus(65536, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
                    }
                }
                return setCompilationStatus(262144, false, false, hasEmbeddedProfile);
            } finally {
            }
        }
    }

    private static CompilationStatus setCompilationStatus(int resultCode, boolean hasReferenceProfile, boolean hasCurrentProfile, boolean hasEmbeddedProfile) {
        sCompilationStatus = new CompilationStatus(resultCode, hasReferenceProfile, hasCurrentProfile, hasEmbeddedProfile);
        sFuture.set(sCompilationStatus);
        return sCompilationStatus;
    }

    private static long getPackageLastUpdateTime(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getPackageInfo(packageManager, context).lastUpdateTime;
        }
        return packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static ListenableFuture<CompilationStatus> getCompilationStatusAsync() {
        return sFuture;
    }

    static class Cache {
        private static final int SCHEMA = 1;
        final long mInstalledCurrentProfileSize;
        final long mPackageLastUpdateTime;
        final int mResultCode;
        final int mSchema;

        Cache(int schema, int resultCode, long packageLastUpdateTime, long installedCurrentProfileSize) {
            this.mSchema = schema;
            this.mResultCode = resultCode;
            this.mPackageLastUpdateTime = packageLastUpdateTime;
            this.mInstalledCurrentProfileSize = installedCurrentProfileSize;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !(o instanceof Cache)) {
                return false;
            }
            Cache cacheFile = (Cache) o;
            if (this.mResultCode == cacheFile.mResultCode && this.mPackageLastUpdateTime == cacheFile.mPackageLastUpdateTime && this.mSchema == cacheFile.mSchema && this.mInstalledCurrentProfileSize == cacheFile.mInstalledCurrentProfileSize) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.mResultCode), Long.valueOf(this.mPackageLastUpdateTime), Integer.valueOf(this.mSchema), Long.valueOf(this.mInstalledCurrentProfileSize));
        }

        void writeOnFile(File file) throws IOException {
            file.delete();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            try {
                dos.writeInt(this.mSchema);
                dos.writeInt(this.mResultCode);
                dos.writeLong(this.mPackageLastUpdateTime);
                dos.writeLong(this.mInstalledCurrentProfileSize);
                dos.close();
            } catch (Throwable th) {
                try {
                    dos.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        static Cache readFromFile(File file) throws IOException {
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            try {
                Cache cache = new Cache(dis.readInt(), dis.readInt(), dis.readLong(), dis.readLong());
                dis.close();
                return cache;
            } catch (Throwable th) {
                try {
                    dis.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static class CompilationStatus {
        public static final int RESULT_CODE_COMPILED_WITH_PROFILE = 1;
        public static final int RESULT_CODE_COMPILED_WITH_PROFILE_NON_MATCHING = 3;
        public static final int RESULT_CODE_ERROR_CACHE_FILE_EXISTS_BUT_CANNOT_BE_READ = 131072;
        public static final int RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE = 196608;
        private static final int RESULT_CODE_ERROR_CODE_BIT_SHIFT = 16;
        public static final int RESULT_CODE_ERROR_NO_PROFILE_EMBEDDED = 327680;
        public static final int RESULT_CODE_ERROR_PACKAGE_NAME_DOES_NOT_EXIST = 65536;
        public static final int RESULT_CODE_ERROR_UNSUPPORTED_API_VERSION = 262144;

        @Deprecated
        public static final int RESULT_CODE_NO_PROFILE = 0;
        public static final int RESULT_CODE_NO_PROFILE_INSTALLED = 0;
        public static final int RESULT_CODE_PROFILE_ENQUEUED_FOR_COMPILATION = 2;
        private final boolean mHasCurrentProfile;
        private final boolean mHasEmbeddedProfile;
        private final boolean mHasReferenceProfile;
        final int mResultCode;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ResultCode {
        }

        CompilationStatus(int resultCode, boolean hasReferenceProfile, boolean hasCurrentProfile, boolean hasEmbeddedProfile) {
            this.mResultCode = resultCode;
            this.mHasCurrentProfile = hasCurrentProfile;
            this.mHasReferenceProfile = hasReferenceProfile;
            this.mHasEmbeddedProfile = hasEmbeddedProfile;
        }

        public int getProfileInstallResultCode() {
            return this.mResultCode;
        }

        public boolean isCompiledWithProfile() {
            return this.mHasReferenceProfile;
        }

        public boolean hasProfileEnqueuedForCompilation() {
            return this.mHasCurrentProfile;
        }

        public boolean appApkHasEmbeddedProfile() {
            return this.mHasEmbeddedProfile;
        }
    }

    private static class Api33Impl {
        private Api33Impl() {
        }

        static PackageInfo getPackageInfo(PackageManager packageManager, Context context) throws PackageManager.NameNotFoundException {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
        }
    }
}
