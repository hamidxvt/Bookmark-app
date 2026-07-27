package com.pusher.client.channel;

/* loaded from: classes17.dex */
public interface SubscriptionEventListener {
    void onEvent(PusherEvent pusherEvent);

    default void onError(String message, Exception e) {
    }
}
