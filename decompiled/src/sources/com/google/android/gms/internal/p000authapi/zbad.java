package com.google.android.gms.internal.p000authapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.auth.api.identity.AuthorizationClient;
import com.google.android.gms.auth.api.identity.AuthorizationRequest;
import com.google.android.gms.auth.api.identity.AuthorizationResult;
import com.google.android.gms.auth.api.identity.ClearTokenRequest;
import com.google.android.gms.auth.api.identity.RevokeAccessRequest;
import com.google.android.gms.auth.api.identity.zba;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public final class zbad extends GoogleApi implements AuthorizationClient {
    private static final Api.ClientKey zba = new Api.ClientKey();
    private static final Api.AbstractClientBuilder zbb = new zbw();
    private static final Api zbc = new Api("Auth.Api.Identity.Authorization.API", zbb, zba);
    private final String zbd;

    public zbad(Activity activity, zba zbaVar) {
        super(activity, (Api<zba>) zbc, zbaVar, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zbd = zbaw.zba();
    }

    @Override // com.google.android.gms.auth.api.identity.AuthorizationClient
    public final Task<AuthorizationResult> authorize(AuthorizationRequest authorizationRequest) {
        Preconditions.checkNotNull(authorizationRequest);
        AuthorizationRequest.Builder zba2 = AuthorizationRequest.zba(authorizationRequest);
        zba2.zbb(this.zbd);
        final AuthorizationRequest build = zba2.build();
        return doRead(TaskApiCall.builder().setFeatures(zbav.zbc).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbac
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final /* synthetic */ void accept(Object obj, Object obj2) {
                zbf zbfVar = (zbf) obj;
                ((zbj) zbfVar.getService()).zbc(new zbx(zbad.this, (TaskCompletionSource) obj2), (AuthorizationRequest) Preconditions.checkNotNull(build), zbaz.zba(zbfVar.getContext()));
            }
        }).setAutoResolveMissingFeatures(false).setMethodKey(1534).build());
    }

    @Override // com.google.android.gms.auth.api.identity.AuthorizationClient
    public final Task<Void> clearToken(ClearTokenRequest clearTokenRequest) {
        Preconditions.checkNotNull(clearTokenRequest);
        ClearTokenRequest.Builder zbb2 = clearTokenRequest.zbb();
        zbb2.zba(this.zbd);
        final ClearTokenRequest build = zbb2.build();
        return doWrite(TaskApiCall.builder().setFeatures(zbav.zbe).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbab
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final /* synthetic */ void accept(Object obj, Object obj2) {
                zbf zbfVar = (zbf) obj;
                ((zbj) zbfVar.getService()).zbe(new zbz(zbad.this, (TaskCompletionSource) obj2), (ClearTokenRequest) Preconditions.checkNotNull(build), zbaz.zba(zbfVar.getContext()));
            }
        }).setAutoResolveMissingFeatures(true).setMethodKey(1721).build());
    }

    @Override // com.google.android.gms.auth.api.identity.AuthorizationClient
    public final AuthorizationResult getAuthorizationResultFromIntent(Intent intent) throws ApiException {
        if (intent == null) {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
        Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, NotificationCompat.CATEGORY_STATUS, Status.CREATOR);
        if (status == null) {
            throw new ApiException(Status.RESULT_CANCELED);
        }
        if (!status.isSuccess()) {
            throw new ApiException(status);
        }
        AuthorizationResult authorizationResult = (AuthorizationResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "authorization_result", AuthorizationResult.CREATOR);
        if (authorizationResult != null) {
            return authorizationResult;
        }
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    }

    @Override // com.google.android.gms.auth.api.identity.AuthorizationClient
    public final Task<Void> revokeAccess(RevokeAccessRequest revokeAccessRequest) {
        Preconditions.checkNotNull(revokeAccessRequest);
        RevokeAccessRequest.Builder zbb2 = revokeAccessRequest.zbb();
        zbb2.zba(this.zbd);
        final RevokeAccessRequest build = zbb2.build();
        return doWrite(TaskApiCall.builder().setFeatures(zbav.zbd).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbaa
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final /* synthetic */ void accept(Object obj, Object obj2) {
                zbf zbfVar = (zbf) obj;
                ((zbj) zbfVar.getService()).zbd(new zby(zbad.this, (TaskCompletionSource) obj2), (RevokeAccessRequest) Preconditions.checkNotNull(build), zbaz.zba(zbfVar.getContext()));
            }
        }).setAutoResolveMissingFeatures(true).setMethodKey(1721).build());
    }

    public zbad(Context context, zba zbaVar) {
        super(context, (Api<zba>) zbc, zbaVar, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zbd = zbaw.zba();
    }
}
