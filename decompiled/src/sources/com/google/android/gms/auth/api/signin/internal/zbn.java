package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public final class zbn {
    private static zbn zbd = null;
    final Storage zba;
    GoogleSignInAccount zbb;
    GoogleSignInOptions zbc;

    private zbn(Context context) {
        this.zba = Storage.getInstance(context);
        this.zbb = this.zba.getSavedDefaultGoogleSignInAccount();
        this.zbc = this.zba.getSavedDefaultGoogleSignInOptions();
    }

    public static synchronized zbn zba(Context context) {
        zbn zbf;
        synchronized (zbn.class) {
            zbf = zbf(context.getApplicationContext());
        }
        return zbf;
    }

    private static synchronized zbn zbf(Context context) {
        zbn zbnVar;
        synchronized (zbn.class) {
            zbnVar = zbd;
            if (zbnVar == null) {
                zbnVar = new zbn(context);
                zbd = zbnVar;
            }
        }
        return zbnVar;
    }

    public final synchronized void zbb() {
        this.zba.clear();
        this.zbb = null;
        this.zbc = null;
    }

    public final synchronized void zbc(GoogleSignInOptions googleSignInOptions, GoogleSignInAccount googleSignInAccount) {
        this.zba.saveDefaultGoogleSignInAccount(googleSignInAccount, googleSignInOptions);
        this.zbb = googleSignInAccount;
        this.zbc = googleSignInOptions;
    }

    public final synchronized GoogleSignInAccount zbd() {
        return this.zbb;
    }

    public final synchronized GoogleSignInOptions zbe() {
        return this.zbc;
    }
}
