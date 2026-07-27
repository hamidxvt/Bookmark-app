package com.pusher.client.channel;

import java.util.Set;

/* loaded from: classes17.dex */
public interface PresenceChannelEventListener extends PrivateChannelEventListener {
    void onUsersInformationReceived(String str, Set<User> set);

    void userSubscribed(String str, User user);

    void userUnsubscribed(String str, User user);
}
