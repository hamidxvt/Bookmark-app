package com.pusher.client.channel.impl;

import com.pusher.client.util.Factory;

/* loaded from: classes17.dex */
public class ChannelImpl extends BaseChannel {
    protected final String name;

    public ChannelImpl(String channelName, Factory factory) {
        super(factory);
        if (channelName == null) {
            throw new IllegalArgumentException("Cannot subscribe to a channel with a null name");
        }
        for (String disallowedPattern : getDisallowedNameExpressions()) {
            if (channelName.matches(disallowedPattern)) {
                throw new IllegalArgumentException("Channel name " + channelName + " is invalid. Private channel names must start with \"private-\" and presence channel names must start with \"presence-\"");
            }
        }
        this.name = channelName;
    }

    @Override // com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.Channel
    public String getName() {
        return this.name;
    }

    @Override // com.pusher.client.channel.impl.BaseChannel
    public String toString() {
        return String.format("[Public Channel: name=%s]", this.name);
    }

    protected String[] getDisallowedNameExpressions() {
        return new String[]{"^private-.*", "^presence-.*"};
    }
}
