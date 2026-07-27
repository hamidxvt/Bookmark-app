package com.google.android.gms.auth.api.identity;

import android.content.Intent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public interface AuthorizationClient extends HasApiKey<zba> {
    Task<AuthorizationResult> authorize(AuthorizationRequest authorizationRequest);

    Task<Void> clearToken(ClearTokenRequest clearTokenRequest);

    AuthorizationResult getAuthorizationResultFromIntent(Intent intent) throws ApiException;

    Task<Void> revokeAccess(RevokeAccessRequest revokeAccessRequest);
}
