package com.pusher.client.util;

import com.pusher.client.AuthenticationFailureException;
import com.pusher.client.UserAuthenticator;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes17.dex */
public class HttpUserAuthenticator extends BaseHttpAuthClient implements UserAuthenticator {
    @Override // com.pusher.client.util.BaseHttpAuthClient
    public /* bridge */ /* synthetic */ Boolean isSSL() {
        return super.isSSL();
    }

    @Override // com.pusher.client.util.BaseHttpAuthClient
    public /* bridge */ /* synthetic */ void setHeaders(Map map) {
        super.setHeaders(map);
    }

    public HttpUserAuthenticator(String endPoint) {
        super(endPoint);
    }

    public HttpUserAuthenticator(String endPoint, ConnectionFactory connectionFactory) {
        super(endPoint, connectionFactory);
    }

    @Override // com.pusher.client.UserAuthenticator
    public String authenticate(String socketId) throws AuthenticationFailureException {
        this.mConnectionFactory.setSocketId(socketId);
        return performAuthRequest();
    }

    @Override // com.pusher.client.util.BaseHttpAuthClient
    protected RuntimeException authFailureException(String msg) {
        return new AuthenticationFailureException(msg);
    }

    @Override // com.pusher.client.util.BaseHttpAuthClient
    protected RuntimeException authFailureException(IOException e) {
        return new AuthenticationFailureException(e);
    }
}
