package com.google.android.gms.auth.api;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public final class Auth {
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API;
    public static final GoogleSignInApi GoogleSignInApi;

    @Deprecated
    public static final ProxyApi ProxyApi;
    public static final Api.ClientKey zba = new Api.ClientKey();
    public static final Api.ClientKey zbb = new Api.ClientKey();
    private static final Api.AbstractClientBuilder zbc = new zba();
    private static final Api.AbstractClientBuilder zbd = new zbb();

    @Deprecated
    public static final Api<AuthProxyOptions> PROXY_API = AuthProxy.API;

    static {
        new Api("Auth.CREDENTIALS_API", zbc, zba);
        GOOGLE_SIGN_IN_API = new Api<>("Auth.GOOGLE_SIGN_IN_API", zbd, zbb);
        ProxyApi = AuthProxy.ProxyApi;
        GoogleSignInApi = new com.google.android.gms.auth.api.signin.internal.zbd();
    }

    private Auth() {
    }
}
