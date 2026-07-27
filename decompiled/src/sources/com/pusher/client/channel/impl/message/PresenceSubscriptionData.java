package com.pusher.client.channel.impl.message;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/* loaded from: classes17.dex */
public class PresenceSubscriptionData {

    @SerializedName("presence")
    public PresenceData presence;

    public List<String> getIds() {
        return this.presence.ids;
    }

    public Map<String, Object> getHash() {
        return this.presence.hash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class PresenceData {

        @SerializedName("count")
        public Integer count;

        @SerializedName("hash")
        public Map<String, Object> hash;

        @SerializedName("ids")
        public List<String> ids;

        PresenceData() {
        }
    }
}
