package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
final class zbf extends zba {
    final /* synthetic */ Context zba;
    final /* synthetic */ GoogleSignInOptions zbb;
    final /* synthetic */ zbg zbc;

    zbf(zbg zbgVar, Context context, GoogleSignInOptions googleSignInOptions) {
        this.zba = context;
        this.zbb = googleSignInOptions;
        Objects.requireNonNull(zbgVar);
        this.zbc = zbgVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zba, com.google.android.gms.auth.api.signin.internal.zbr
    public final void zbb(GoogleSignInAccount googleSignInAccount, Status status) throws RemoteException {
        if (googleSignInAccount != null) {
            Context context = this.zba;
            zbn.zba(context).zbc(this.zbb, googleSignInAccount);
        }
        this.zbc.setResult((zbg) new GoogleSignInResult(googleSignInAccount, status));
    }
}
