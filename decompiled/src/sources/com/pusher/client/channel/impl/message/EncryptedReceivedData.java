package com.pusher.client.channel.impl.message;

import com.pusher.client.util.internal.Base64;

/* loaded from: classes17.dex */
public class EncryptedReceivedData {
    private String ciphertext;
    private String nonce;

    public byte[] getNonce() {
        return Base64.decode(this.nonce);
    }

    public byte[] getCiphertext() {
        return Base64.decode(this.ciphertext);
    }
}
