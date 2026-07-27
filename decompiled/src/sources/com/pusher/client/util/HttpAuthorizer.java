package com.pusher.client.util;

import com.pusher.client.Authorizer;

@Deprecated
/* loaded from: classes17.dex */
public class HttpAuthorizer extends HttpChannelAuthorizer implements Authorizer {
    public HttpAuthorizer(String endPoint) {
        super(endPoint);
    }

    public HttpAuthorizer(String endPoint, ConnectionFactory connectionFactory) {
        super(endPoint, connectionFactory);
    }
}
