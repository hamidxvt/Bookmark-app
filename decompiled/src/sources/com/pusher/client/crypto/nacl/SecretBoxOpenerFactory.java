package com.pusher.client.crypto.nacl;

/* loaded from: classes17.dex */
public class SecretBoxOpenerFactory {
    public SecretBoxOpener create(byte[] key) {
        return new SecretBoxOpener(key);
    }
}
