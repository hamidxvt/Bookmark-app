package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
public class UserRecoverableAuthException extends GoogleAuthException {
    private final Intent zza;
    private final PendingIntent zzb;
    private final zzn zzc;

    public UserRecoverableAuthException(String msg, Intent intent) {
        this(msg, intent, null, zzn.LEGACY);
    }

    public static UserRecoverableAuthException zza(String str, Intent intent, PendingIntent pendingIntent) {
        Preconditions.checkNotNull(intent);
        Preconditions.checkNotNull(pendingIntent);
        return new UserRecoverableAuthException(str, intent, pendingIntent, zzn.AUTH_INSTANTIATION);
    }

    public Intent getIntent() {
        Intent intent = this.zza;
        if (intent != null) {
            return new Intent(intent);
        }
        switch (this.zzc) {
            case LEGACY:
                Log.w("Auth", "Make sure that an intent was provided to class instantiation.");
                return null;
            case AUTH_INSTANTIATION:
                Log.e("Auth", "This shouldn't happen. Gms API throwing this exception should support the recovery Intent.");
                return null;
            case CALLER_INSTANTIATION:
                Log.e("Auth", "this instantiation of UserRecoverableAuthException doesn't support an Intent.");
                return null;
            default:
                return null;
        }
    }

    private UserRecoverableAuthException(String str, Intent intent, PendingIntent pendingIntent, zzn zznVar) {
        super(str);
        this.zzb = pendingIntent;
        this.zza = intent;
        this.zzc = (zzn) Preconditions.checkNotNull(zznVar);
    }
}
