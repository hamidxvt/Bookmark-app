package com.pusher.client.connection.impl;

import com.pusher.client.connection.Connection;

/* loaded from: classes17.dex */
public interface InternalConnection extends Connection {
    void disconnect();

    void sendMessage(String str);
}
