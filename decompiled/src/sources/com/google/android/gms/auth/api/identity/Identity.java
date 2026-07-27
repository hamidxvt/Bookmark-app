package com.google.android.gms.auth.api.identity;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000authapi.zbad;
import com.google.android.gms.internal.p000authapi.zbaj;
import com.google.android.gms.internal.p000authapi.zbat;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public final class Identity {
    private Identity() {
    }

    public static AuthorizationClient getAuthorizationClient(Activity activity) {
        return new zbad((Activity) Preconditions.checkNotNull(activity), new zba(null));
    }

    public static CredentialSavingClient getCredentialSavingClient(Activity activity) {
        return new zbaj((Activity) Preconditions.checkNotNull(activity), new zbk());
    }

    public static SignInClient getSignInClient(Activity activity) {
        return new zbat((Activity) Preconditions.checkNotNull(activity), new zbx());
    }

    public static AuthorizationClient getAuthorizationClient(Context context) {
        return new zbad((Context) Preconditions.checkNotNull(context), new zba(null));
    }

    public static CredentialSavingClient getCredentialSavingClient(Context context) {
        return new zbaj((Context) Preconditions.checkNotNull(context), new zbk());
    }

    public static SignInClient getSignInClient(Context context) {
        return new zbat((Context) Preconditions.checkNotNull(context), new zbx());
    }
}
