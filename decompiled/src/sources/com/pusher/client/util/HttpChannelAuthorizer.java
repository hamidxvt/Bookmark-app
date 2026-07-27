package com.pusher.client.util;

import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.ChannelAuthorizer;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes17.dex */
public class HttpChannelAuthorizer extends BaseHttpAuthClient implements ChannelAuthorizer {
    @Override // com.pusher.client.util.BaseHttpAuthClient
    public /* bridge */ /* synthetic */ Boolean isSSL() {
        return super.isSSL();
    }

    @Override // com.pusher.client.util.BaseHttpAuthClient
    public /* bridge */ /* synthetic */ void setHeaders(Map map) {
        super.setHeaders(map);
    }

    public HttpChannelAuthorizer(String endPoint) {
        super(endPoint);
    }

    public HttpChannelAuthorizer(String endPoint, ConnectionFactory connectionFactory) {
        super(endPoint, connectionFactory);
    }

    @Override // com.pusher.client.ChannelAuthorizer
    public String authorize(String channelName, String socketId) throws AuthorizationFailureException {
        this.mConnectionFactory.setChannelName(channelName);
        this.mConnectionFactory.setSocketId(socketId);
        return performAuthRequest();
    }

    @Override // com.pusher.client.util.BaseHttpAuthClient
    protected RuntimeException authFailureException(String msg) {
        return new AuthorizationFailureException(msg);
    }

    @Override // com.pusher.client.util.BaseHttpAuthClient
    protected RuntimeException authFailureException(IOException e) {
        return new AuthorizationFailureException(e);
    }
}
