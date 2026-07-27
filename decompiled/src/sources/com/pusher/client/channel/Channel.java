package com.pusher.client.channel;

/* loaded from: classes17.dex */
public interface Channel {
    void bind(String str, SubscriptionEventListener subscriptionEventListener);

    void bindGlobal(SubscriptionEventListener subscriptionEventListener);

    String getName();

    boolean isSubscribed();

    void unbind(String str, SubscriptionEventListener subscriptionEventListener);

    void unbindGlobal(SubscriptionEventListener subscriptionEventListener);
}
