package com.pusher.client.connection;

/* loaded from: classes17.dex */
public interface Connection {
    void bind(ConnectionState connectionState, ConnectionEventListener connectionEventListener);

    void connect();

    String getSocketId();

    ConnectionState getState();

    boolean unbind(ConnectionState connectionState, ConnectionEventListener connectionEventListener);
}
