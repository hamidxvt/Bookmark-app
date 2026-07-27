package com.pusher.client.user.impl.message;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes17.dex */
public class AuthenticationResponse {
    private String auth;

    @SerializedName("user_data")
    private String userData;

    public String getAuth() {
        return this.auth;
    }

    public String getUserData() {
        return this.userData;
    }
}
