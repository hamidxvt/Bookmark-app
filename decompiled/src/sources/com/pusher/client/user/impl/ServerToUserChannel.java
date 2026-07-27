package com.pusher.client.user.impl;

import com.pusher.client.channel.impl.BaseChannel;
import com.pusher.client.user.User;
import com.pusher.client.util.Factory;

/* loaded from: classes17.dex */
class ServerToUserChannel extends BaseChannel {
    private final User user;

    public ServerToUserChannel(User user, Factory factory) {
        super(factory);
        this.user = user;
    }

    @Override // com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.Channel
    public String getName() {
        String userId = this.user.userId();
        if (userId == null) {
            throw new IllegalStateException("User id is null in ServerToUserChannel");
        }
        return "#server-to-user-" + this.user.userId();
    }
}
