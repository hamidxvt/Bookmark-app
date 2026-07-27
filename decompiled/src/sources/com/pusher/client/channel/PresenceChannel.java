package com.pusher.client.channel;

import java.util.Set;

/* loaded from: classes17.dex */
public interface PresenceChannel extends PrivateChannel {
    User getMe();

    Set<User> getUsers();
}
