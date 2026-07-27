package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
/* loaded from: classes16.dex */
public class PackageSignatureVerifier {
    static volatile zzab zza;
    private static zzac zzb;

    private static zzac zza(Context context) {
        zzac zzacVar;
        synchronized (PackageSignatureVerifier.class) {
            if (zzb == null) {
                zzb = new zzac(context);
            }
            zzacVar = zzb;
        }
        return zzacVar;
    }

    public PackageVerificationResult queryPackageSignatureVerified(Context context, String callingPackage) {
        PackageVerificationResult packageVerificationResult;
        String str;
        PackageVerificationResult packageVerificationResult2;
        boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(context);
        zza(context);
        if (!zzn.zzf()) {
            throw new zzad();
        }
        String concat = String.valueOf(callingPackage).concat(true != honorsDebugCertificates ? "-0" : "-1");
        if (zza != null) {
            str = zza.zza;
            if (str.equals(concat)) {
                packageVerificationResult2 = zza.zzb;
                return packageVerificationResult2;
            }
        }
        zza(context);
        zzw zzc = zzn.zzc(callingPackage, honorsDebugCertificates, false, false);
        if (!zzc.zza) {
            Preconditions.checkNotNull(zzc.zzb);
            return PackageVerificationResult.zza(callingPackage, zzc.zzb, zzc.zzc);
        }
        zza = new zzab(concat, PackageVerificationResult.zzd(callingPackage, zzc.zzd));
        packageVerificationResult = zza.zzb;
        return packageVerificationResult;
    }

    public PackageVerificationResult queryPackageSignatureVerifiedWithRetry(Context context, String callingPackage) {
        try {
            PackageVerificationResult queryPackageSignatureVerified = queryPackageSignatureVerified(context, callingPackage);
            queryPackageSignatureVerified.zzb();
            return queryPackageSignatureVerified;
        } catch (SecurityException e) {
            PackageVerificationResult queryPackageSignatureVerified2 = queryPackageSignatureVerified(context, callingPackage);
            if (!queryPackageSignatureVerified2.zzc()) {
                return queryPackageSignatureVerified2;
            }
            Log.e("PkgSignatureVerifier", "Got flaky result during package signature verification", e);
            return queryPackageSignatureVerified2;
        }
    }
}
