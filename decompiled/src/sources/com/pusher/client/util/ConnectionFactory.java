package com.pusher.client.util;

/* loaded from: classes17.dex */
public abstract class ConnectionFactory {
    private String channelName;
    private String socketId;

    public abstract String getBody();

    public abstract String getCharset();

    public abstract String getContentType();

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getSocketId() {
        return this.socketId;
    }

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }
}
