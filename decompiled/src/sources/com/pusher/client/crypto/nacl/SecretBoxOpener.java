package com.pusher.client.crypto.nacl;

import com.pusher.client.crypto.nacl.TweetNaclFast;
import com.pusher.client.util.internal.Preconditions;
import java.util.Arrays;

/* loaded from: classes17.dex */
public class SecretBoxOpener {
    private byte[] key;

    public SecretBoxOpener(byte[] key) {
        Preconditions.checkNotNull(key, "null key passed");
        Preconditions.checkArgument(key.length == 32, "key length must be 32 bytes, but is " + key.length + " bytes");
        this.key = key;
    }

    public String open(byte[] cypher, byte[] nonce) throws AuthenticityException {
        Preconditions.checkNotNull(this.key, "key has been cleared, create new instance");
        Preconditions.checkArgument(nonce.length == 24, "nonce length must be 24 bytes, but is " + this.key.length + " bytes");
        try {
            TweetNaclFast.SecretBox secretBox = new TweetNaclFast.SecretBox(this.key);
            byte[] result = secretBox.open(cypher, nonce);
            return new String(result);
        } catch (Exception e) {
            throw new AuthenticityException();
        }
    }

    public void clearKey() {
        Arrays.fill(this.key, (byte) 0);
        if (this.key[0] != 0) {
            throw new SecurityException("key not cleared correctly");
        }
        this.key = null;
    }
}
