package com.pusher.client.connection;

/* loaded from: classes17.dex */
public interface ConnectionEventListener {
    void onConnectionStateChange(ConnectionStateChange connectionStateChange);

    void onError(String str, String str2, Exception exc);
}
