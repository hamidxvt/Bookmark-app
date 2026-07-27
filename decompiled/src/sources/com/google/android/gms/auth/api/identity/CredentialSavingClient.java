package com.google.android.gms.auth.api.identity;

import android.content.Intent;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public interface CredentialSavingClient extends HasApiKey<zbk> {
    Status getStatusFromIntent(Intent intent);

    Task<SaveAccountLinkingTokenResult> saveAccountLinkingToken(SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest);

    @Deprecated
    Task<SavePasswordResult> savePassword(SavePasswordRequest savePasswordRequest);
}
