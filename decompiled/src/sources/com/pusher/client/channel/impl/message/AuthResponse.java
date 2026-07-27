package com.pusher.client.channel.impl.message;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes17.dex */
public class AuthResponse {
    private String auth;

    @SerializedName("channel_data")
    private String channelData;

    @SerializedName("shared_secret")
    private String sharedSecret;

    public String getAuth() {
        return this.auth;
    }

    public String getChannelData() {
        return this.channelData;
    }

    public String getSharedSecret() {
        return this.sharedSecret;
    }
}
