package com.pusher.client.channel.impl.message;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes17.dex */
public class SubscribeMessage {
    private final String event = "pusher:subscribe";
    private final Map<String, String> data = new HashMap();

    public SubscribeMessage(String channelName) {
        this.data.put("channel", channelName);
    }

    public SubscribeMessage(String channelName, String auth, String channelData) {
        this.data.put("auth", auth);
        this.data.put("channel", channelName);
        if (channelData != null) {
            this.data.put("channel_data", channelData);
        }
    }
}
