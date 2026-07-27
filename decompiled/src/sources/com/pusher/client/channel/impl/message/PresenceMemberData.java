package com.pusher.client.channel.impl.message;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes17.dex */
public class PresenceMemberData {

    @SerializedName("user_id")
    private String id;

    @SerializedName("user_info")
    private Object info;

    public String getId() {
        return this.id;
    }

    public Object getInfo() {
        return this.info;
    }
}
