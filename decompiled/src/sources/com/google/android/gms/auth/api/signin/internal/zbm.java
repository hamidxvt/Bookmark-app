package com.google.android.gms.auth.api.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public final class zbm {
    private static final Logger zba = new Logger("GoogleSignInCommon", new String[0]);

    public static Intent zba(Context context, GoogleSignInOptions googleSignInOptions) {
        zba.d("getSignInIntent()", new Object[0]);
        SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, SignInHubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", signInConfiguration);
        intent.putExtra("config", bundle);
        return intent;
    }

    public static Intent zbb(Context context, GoogleSignInOptions googleSignInOptions) {
        zba.d("getFallbackSignInIntent()", new Object[0]);
        Intent zba2 = zba(context, googleSignInOptions);
        zba2.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return zba2;
    }

    public static Intent zbc(Context context, GoogleSignInOptions googleSignInOptions) {
        zba.d("getNoImplementationSignInIntent()", new Object[0]);
        Intent zba2 = zba(context, googleSignInOptions);
        zba2.setAction("com.google.android.gms.auth.NO_IMPL");
        return zba2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002c, code lost:
    
        if (r5 == null) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static OptionalPendingResult zbd(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions, boolean z) {
        GoogleSignInResult googleSignInResult;
        Logger logger = zba;
        logger.d("silentSignIn()", new Object[0]);
        logger.d("getEligibleSavedSignInResult()", new Object[0]);
        Preconditions.checkNotNull(googleSignInOptions);
        GoogleSignInOptions zbe = zbn.zba(context).zbe();
        if (zbe == null) {
            googleSignInResult = null;
        } else {
            Account account = zbe.getAccount();
            Account account2 = googleSignInOptions.getAccount();
            if (account != null) {
                if (!account.equals(account2)) {
                    googleSignInResult = null;
                }
            }
            if (googleSignInOptions.isServerAuthCodeRequested()) {
                googleSignInResult = null;
            } else {
                if (googleSignInOptions.isIdTokenRequested()) {
                    if (!zbe.isIdTokenRequested()) {
                        googleSignInResult = null;
                    } else if (!Objects.equal(googleSignInOptions.getServerClientId(), zbe.getServerClientId())) {
                        googleSignInResult = null;
                    }
                }
                if (new HashSet(zbe.getScopes()).containsAll(new HashSet(googleSignInOptions.getScopes()))) {
                    GoogleSignInAccount zbd = zbn.zba(context).zbd();
                    if (zbd != null && !zbd.isExpired()) {
                        googleSignInResult = new GoogleSignInResult(zbd, Status.RESULT_SUCCESS);
                    }
                    googleSignInResult = null;
                } else {
                    googleSignInResult = null;
                }
            }
        }
        if (googleSignInResult != null) {
            logger.d("Eligible saved sign in result found", new Object[0]);
            return PendingResults.immediatePendingResult(googleSignInResult, googleApiClient);
        }
        if (z) {
            return PendingResults.immediatePendingResult(new GoogleSignInResult(null, new Status(4)), googleApiClient);
        }
        logger.d("trySilentSignIn()", new Object[0]);
        return new OptionalPendingResultImpl(googleApiClient.enqueue(new zbg(googleApiClient, context, googleSignInOptions)));
    }

    public static PendingResult zbe(GoogleApiClient googleApiClient, Context context, boolean z) {
        zba.d("Signing out", new Object[0]);
        zbh(context);
        return z ? PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient) : googleApiClient.execute(new zbi(googleApiClient));
    }

    public static PendingResult zbf(GoogleApiClient googleApiClient, Context context, boolean z) {
        zba.d("Revoking access", new Object[0]);
        String savedRefreshToken = Storage.getInstance(context).getSavedRefreshToken();
        zbh(context);
        return z ? zbb.zba(savedRefreshToken) : googleApiClient.execute(new zbk(googleApiClient));
    }

    public static GoogleSignInResult zbg(Intent intent) {
        if (intent == null) {
            return new GoogleSignInResult(null, Status.RESULT_INTERNAL_ERROR);
        }
        Status status = (Status) intent.getParcelableExtra("googleSignInStatus");
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        if (googleSignInAccount != null) {
            return new GoogleSignInResult(googleSignInAccount, Status.RESULT_SUCCESS);
        }
        if (status == null) {
            status = Status.RESULT_INTERNAL_ERROR;
        }
        return new GoogleSignInResult(null, status);
    }

    private static void zbh(Context context) {
        zbn.zba(context).zbb();
        Iterator<GoogleApiClient> it = GoogleApiClient.getAllClients().iterator();
        while (it.hasNext()) {
            it.next().maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
    }
}
